# product-api

Backend quản lý sản phẩm cho frontend Nuxt `product-app`. API sử dụng DTO, JWT và phân quyền ADMIN/USER.

## Stack

- Java 21, Maven 3.9, Spring Boot 3.3.13.
- Spring Web, Security, Validation, Data JPA và Actuator.
- PostgreSQL 16, Flyway V1–V4, optimistic locking.
- JWT (JJWT), BCrypt, cache DTO trong bộ nhớ.
- JUnit 5, Mockito, H2 cho repository test, Testcontainers PostgreSQL cho integration test, JaCoCo.

## Yêu cầu

Docker/Compose và daemon đang chạy; VS Code với extension `ms-vscode-remote.remote-containers`.
Không cần JDK hoặc Maven native trên host macOS. Mọi lệnh Maven chạy trong devcontainer.

Compose mount Docker socket vào app để Testcontainers tạo database test độc lập.
Mặc định socket host là `/var/run/docker.sock`; nếu cần, đặt `DOCKER_SOCKET_PATH` theo Docker context.
`TESTCONTAINERS_HOST_OVERRIDE=host.docker.internal` giúp JVM trong devcontainer truy cập port test trên host.
Không dùng database `product-api-postgres` cho integration test.

## Quick start

1. Mở thư mục `product-api` trong VS Code.
2. Chọn **Dev Containers: Reopen in Container**. Khi thay đổi Compose, chọn **Rebuild and Reopen in Container**.
3. Trong terminal devcontainer chạy `mvn spring-boot:run`.

Từ host có thể chạy `docker compose up -d` trước khi mở devcontainer.
App container mặc định chạy `sleep infinity`; cần chạy Maven để HTTP server bắt đầu.
Nếu app đã chạy từ terminal khác, dừng tiến trình cũ trước khi chạy lại để tránh trùng port debug/HTTP.
Task `docker: up`/`docker: down` dùng trong cửa sổ VS Code host; task `spring: run` dùng trong devcontainer.

## Kiến trúc

```text
HTTP request
    |
    v
JWT Filter + Security (stateless)
    |
    v
Controller -> Service (@Transactional) -> Repository/Specification -> PostgreSQL
    |              |
    |              +-> ProductMapper -> ProductResponse DTO -> Cache
    v
GlobalExceptionHandler -> ErrorResponse JSON
```

Controller không trả entity. Filter name dùng LIKE không phân biệt hoa/thường, escape `%` và `_`.
Khoảng giá bao gồm hai đầu; `inStock=true` là stock > 0, `false` là stock = 0.
Phân trang mặc định page=0, size=10, tối đa 100. Sort mặc định `createdAt,desc`, có id tăng dần làm tie breaker.
Sort cho phép id/name/price/stock/createdAt/updatedAt; input sai trả 400.

Cache dùng ConcurrentMapCacheManager vì môi trường dev chỉ có một app instance. Chỉ cache ProductResponse
ở GET theo id, không cache list hay entity. Cache không có TTL, mất khi restart; create/update/delete xóa cache
sau commit. Rollback giữ cache cũ. Khi triển khai nhiều instance nên chuyển sang Redis với TTL 5 phút.
Dev TRACE log cho biết cache miss/hit theo product id, không log password/token.

`@Version` bảo vệ các transaction cập nhật trùng version; transaction thua trả 409. Client nên tải lại và retry.
API chưa nhận version từ client nên chưa phát hiện form cũ gửi sau khi transaction trước đã hoàn tất.
Runtime exception rollback DB. Side effect bên ngoài nên dùng outbox hoặc AFTER_COMMIT event.

Product hiện chỉ có scalar fields nên mapper không phát sinh N+1. Khi thêm quan hệ, dùng fetch join cho query
cần quan hệ to-one; dùng `@EntityGraph` để tái sử dụng fetch plan trong repository. Với collection và pagination,
page id trước rồi fetch quan hệ bằng query thứ hai, tránh fetch join collection trực tiếp trên query phân trang.
Hibernate batch_size=20, order_inserts/order_updates=true. IDENTITY/BIGSERIAL insert không được Hibernate JDBC batch.

## API endpoints

| Method | Path | Role | Mô tả |
| --- | --- | --- | --- |
| POST | /api/auth/login | Public | Đăng nhập, trả token và expiresIn (ms) |
| GET | /api/auth/me | Đã đăng nhập | Username và role hiện tại |
| GET | /actuator/health | Public | Health của app/DB |
| GET | /api/products | ADMIN, USER | List, filter, pagination và sort |
| GET | /api/products/{id} | ADMIN, USER | Chi tiết, có cache DTO |
| POST | /api/products | ADMIN | Tạo sản phẩm, trả 201 |
| PUT | /api/products/{id} | ADMIN | Cập nhật, trả 200 hoặc 409 khi conflict |
| DELETE | /api/products/{id} | ADMIN | Xóa, trả 204 |

Lỗi trả ErrorResponse gồm timestamp/status/error/message/path/details. Validation trả 400 + field errors;
thiếu/sai token 401, thiếu quyền 403, không tìm thấy 404, conflict 409; lỗi nội bộ trả 500 không lộ stacktrace.

## Auth flow

Migration V2 tạo account dev `admin/password` (ADMIN) và `user/password` (USER), mật khẩu lưu bằng BCrypt.
V2 cũng chạy trên DB mới ở profile prod; phải thay đổi hoặc vô hiệu hóa các account seed trước khi dùng production.
JWT secret là key Base64 (ít nhất 32 byte sau decode); profile prod bắt buộc đặt `JWT_SECRET`.
`JWT_EXPIRATION` tính bằng ms, mặc định 3600000. Khi dùng Compose, thêm JWT_SECRET/JWT_EXPIRATION vào
`app.environment` hoặc env_file để truyền vào container; chỉ export biến trên host không tự truyền mọi biến vào app.

```text
POST login -> token -> Authorization: Bearer token -> kiểm tra chữ ký/hết hạn -> role từ DB -> API
```

Ví dụ dưới đây chạy trên host; trong devcontainer đổi `8082` thành `8080`:

```sh
curl -X POST http://localhost:8082/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"username":"admin","password":"password"}'

curl 'http://localhost:8082/api/products?sort=price,asc&page=0&size=5' \
  -H 'Authorization: Bearer <token>'

curl -i http://localhost:8082/api/products
```

Không ghi token/password vào log hoặc commit token vào repo. Account seed chỉ phục vụ local development.

## Port mapping

| Service | Host | Container |
| --- | --- | --- |
| App HTTP | 8082 | 8080 |
| Debug | 5005 | 5005 |
| PostgreSQL | 5433 | 5432 |
| Adminer | 8081 | 8080 |

Host 8080 và 5432 đã có các service `vcx-*` sử dụng. Compose giữ ba tên cố định
`product-api-dev`, `product-api-postgres`, `product-api-adminer`. Datasource nội bộ là
`jdbc:postgresql://postgres:5432/productdb`, không dùng host port 5433.

- App: http://localhost:8082
- Health: http://localhost:8082/actuator/health
- Adminer: http://localhost:8081 (server postgres, DB productdb, user/password product/product)
- Debug: localhost:5005, chọn launch configuration **Attach to product-api-dev**.

## Test

Trong devcontainer:

```sh
mvn test
mvn verify
```

`mvn test` chạy unit/MVC/H2 repository tests (tên `*Test`). `mvn verify` chạy thêm Failsafe integration tests
(tên `*IT`) với PostgreSQL 16 Testcontainers, Flyway thật, JWT thật và HTTP server random port.
Các account dùng migration seed; sản phẩm dùng ProductBuilder. `@Sql` reset sản phẩm và cache được xóa
trước từng test. Mỗi lớp integration test có lifecycle container/context riêng, không phụ thuộc thứ tự.
Test conflict đồng bộ hai HTTP transactions sau khi cùng đọc version, không giả lập lỗi database.

Testcontainers tạo Postgres/Ryuk tạm với tên/port tự quản lý và tự cleanup; ba service dev vẫn giữ tên/port cố định.
Integration test không bỏ qua khi Docker thiếu: sửa daemon/socket rồi chạy lại. Report test nằm ở
`target/surefire-reports` và `target/failsafe-reports`.

JaCoCo gộp coverage unit + integration, report `target/site/jacoco/index.html`. Mục tiêu line coverage >=70%,
không bật gate ép tỷ lệ và không exclude code để nâng tỷ lệ. Để đo lại từ đầu dùng `mvn clean verify`.
Từ terminal host macOS, trong thư mục project:

```sh
open target/site/jacoco/index.html
```

`open` là lệnh host macOS; không chạy trong Linux devcontainer.

## Troubleshooting

- **Port conflict:** trên macOS dùng `lsof -nP -iTCP -sTCP:LISTEN`; kiểm tra port 8082/8081/5433/5005.
  Không dừng `vcx-*`; đổi host mapping nếu cần và giữ datasource nội bộ postgres:5432.
- **Container cũ trùng tên:** `docker ps -a --filter name=product-api`; xác nhận thuộc project này trước khi xử lý.
  Dùng `docker compose down` từ đúng project để dừng/gỡ các container của project, giữ volume DB.
- **Reset DB dev:** `docker compose down -v` xóa dữ liệu productdb của project; rồi `docker compose up -d`
  và chạy lại Maven để Flyway dựng/seed DB. Không sửa migration đã áp dụng; thêm migration mới.
- **Testcontainers không kết nối:** kiểm tra Docker daemon, mount `/var/run/docker.sock`, DNS host.docker.internal,
  sau đó Rebuild/Reopen devcontainer. Socket phải là socket của daemon đang dùng.
- **HTTP chưa hoạt động:** app container đang sleep; chạy `mvn spring-boot:run`. Nếu dependency mới vừa được thêm,
  dừng/chạy lại Maven để nạp classpath mới. DevTools restart khi class đã compile; thay source có thể cần `mvn compile`.
- **Debug bind failed:** chỉ chạy một app JVM. Maven wrapper tách JDWP khỏi JVM Maven;
  nếu chạy java/jshell thủ công trong container, dùng `env -u JAVA_TOOL_OPTIONS` cho JVM không cần debug.

## Bài tập mở rộng — CRUD Coupon

Tự triển khai Coupon theo kiến trúc hiện tại; project chưa có lời giải hay migration V5.

Entity Coupon gồm id, code (unique), discountPercent (1–100), expiredAt, maxUses, usedCount, createdAt, updatedAt.
Thêm validation, DTO/mapper/service/repository, migration `V5__create_coupons.sql` và các endpoint:

| Method | Path | Role |
| --- | --- | --- |
| POST | /api/coupons | ADMIN |
| GET | /api/coupons | ADMIN, USER; phân trang |
| GET | /api/coupons/{id} | ADMIN, USER |
| PUT | /api/coupons/{id} | ADMIN |
| DELETE | /api/coupons/{id} | ADMIN |
| POST | /api/coupons/{code}/apply | USER |

Business rules: coupon hết hạn trả 400; hết lượt/usedCount >= maxUses trả 400; code trùng trả 409.
Apply hợp lệ tăng usedCount trong transaction. Viết unit + integration tests cho CRUD, validation,
role, hết hạn, hết lượt, code trùng và concurrent apply. Gợi ý: dùng optimistic lock cho usedCount để tránh vượt maxUses.

## Liên kết với product-app (Nuxt FE)

Nuxt dev chạy tại http://localhost:3000, đã có CORS cho origin này. Base URL browser là
http://localhost:8082; nếu Nuxt server chạy trong container riêng, localhost là container Nuxt và cần địa chỉ
host.docker.internal:8082 hoặc network/service URL tương ứng. FE đăng nhập, giữ token theo chiến lược của FE,
gửi Authorization Bearer, xử lý 401/403/409, và đọc content/page/size/totalElements/totalPages cho list.
Project này chưa chỉnh sửa frontend.

## Checklist cuối project

- [ ] docker compose up -d → 3 container tên cố định
- [ ] mvn spring-boot:run trong devcontainer
- [ ] curl /actuator/health → 200
- [ ] login admin → token
- [ ] CRUD product với ADMIN
- [ ] GET product với USER
- [ ] POST product với USER → 403
- [ ] mvn verify → all green
- [ ] Coverage >= 70%
- [ ] Bài tập coupon hoàn thành
