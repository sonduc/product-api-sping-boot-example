# API Product

## Endpoint và phân quyền

Base path `/api/products` tại [ProductController.@RequestMapping](../../../src/main/java/com/example/productapi/controller/ProductController.java#L27). Tất cả request bên dưới cần `Authorization: Bearer <token>`. GET cho `ADMIN` hoặc `USER`, ghi cho `ADMIN` tại [SecurityConfig.securityFilterChain](../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L38); controller còn đặt `@PreAuthorize` cho các thao tác ghi. JWT filter nạp người dùng từ AuthService ([JwtAuthenticationFilter.doFilterInternal](../../../src/main/java/com/example/productapi/security/JwtAuthenticationFilter.java#L29)).

| Method và path | Input | Thành công | Luồng / code |
|---|---|---|---|
| GET `/api/products` | Query lọc, phân trang, sort | 200 `PageResponse<ProductResponse>` | [Danh sách](features/list-products.md), [ProductController.getAll](../../../src/main/java/com/example/productapi/controller/ProductController.java#L40) |
| GET `/api/products/{id}` | id kiểu Long | 200 `ProductResponse` | [Chi tiết](features/get-product.md), [ProductController.getById](../../../src/main/java/com/example/productapi/controller/ProductController.java#L72) |
| POST `/api/products` | JSON `ProductRequest` | 201, body `ProductResponse`, Location `/api/products/{id}` | [Tạo](features/create-product.md), [ProductController.create](../../../src/main/java/com/example/productapi/controller/ProductController.java#L78) |
| PUT `/api/products/{id}` | id và JSON `ProductRequest` | 200 `ProductResponse` | [Sửa](features/update-product.md), [ProductController.update](../../../src/main/java/com/example/productapi/controller/ProductController.java#L85) |
| DELETE `/api/products/{id}` | id kiểu Long | 204, không body | [Xóa](features/delete-product.md), [ProductController.delete](../../../src/main/java/com/example/productapi/controller/ProductController.java#L92) |

## Query danh sách

| Query | Mặc định | Quy tắc |
|---|---|---|
| `page` | 0 | Bắt đầu từ 0, không âm |
| `size` | 10 | 1–100 |
| `sort` | `createdAt,desc` | Hai phần field và direction; field: id/name/price/stock/createdAt/updatedAt; asc hoặc desc |
| `name` | Không lọc | Chứa chuỗi, không phân biệt hoa/thường; trim, bỏ qua nếu blank; `%`, `_`, `!` được escape |
| `minPrice`, `maxPrice` | Không giới hạn phía tương ứng | Không âm; min không lớn hơn max; bao gồm hai đầu |
| `inStock` | Không lọc | true: stock > 0; false: stock = 0 |

Các bộ lọc kết hợp bằng AND. Sort thêm `id,asc` làm khóa phụ nếu field chính khác id. Nguồn: [ProductController.parseSort](../../../src/main/java/com/example/productapi/controller/ProductController.java#L52), [ProductSpecification.public final class](../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L10).

## Body ghi và response

[ProductRequest](../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) quy định `name` không null/blank, dài tối đa 255; `price` bắt buộc, > 0, tối đa 10 chữ số phần nguyên và 2 phần thập phân; `stock` bắt buộc, số nguyên ≥ 0. `description` tùy chọn và có thể null. PUT yêu cầu đủ các field bắt buộc, không phải cập nhật từng phần.

Body minh họa cho POST hoặc PUT:

```json
{"name":"Bàn phím","description":"USB","price":450000.00,"stock":12}
```

Response minh họa; id và thời gian thực tế do hệ thống sinh:

```json
{"id":1,"name":"Bàn phím","description":"USB","price":450000.00,"stock":12,"createdAt":"2026-11-12T09:00:00","updatedAt":"2026-11-12T09:00:00"}
```

GET danh sách bọc các DTO trong `{"content":[...],"page":0,"size":10,"totalElements":1,"totalPages":1}`. Schema lấy từ [ProductResponse](../../../src/main/java/com/example/productapi/dto/ProductResponse.java#L6) và [PageResponse](../../../src/main/java/com/example/productapi/dto/PageResponse.java#L6).

## Lỗi

[ErrorResponse](../../../src/main/java/com/example/productapi/exception/ErrorResponse.java#L6) gồm `timestamp`, `status`, `error`, `message`, `path`, `details`. `details` chứa field errors khi Bean Validation thất bại, các nhánh lỗi khác nêu dưới đây dùng map rỗng.

| Status | Trường hợp | Nguồn |
|---|---|---|
| 400 | Body/kiểu tham số sai, validation, page/size/sort hoặc khoảng giá sai | [GlobalExceptionHandler.handleMalformedRequest](../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L36), controller, Specification |
| 401 | Không xác thực được | SecurityConfig |
| 403 | Thiếu quyền | SecurityConfig / handler AccessDenied |
| 404 | Không tìm thấy id cho GET/PUT/DELETE | [ProductService.private Product findProduct](../../../src/main/java/com/example/productapi/service/ProductService.java#L64) |
| 409 | Optimistic locking conflict | [GlobalExceptionHandler.handleOptimisticConflict](../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L66) |
| 500 | Ngoại lệ ngoài các nhánh đã xử lý; message `Internal server error` | [GlobalExceptionHandler.handleUnexpected](../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L72) |

Ví dụ lỗi cấu trúc query:

```json
{"timestamp":"2026-11-12T02:00:00Z","status":400,"error":"Bad Request","message":"Invalid price range","path":"/api/products","details":{}}
```

## Thử API

Sau khi chạy app và lấy token theo [README](../../../README.md), thay `<token>` và id bằng giá trị thật. Ví dụ dùng cổng host mặc định trong README; nếu đổi cổng hãy đổi `BASE_URL`.

```sh
BASE_URL=http://localhost:18082
TOKEN='<token>'
curl "$BASE_URL/api/products?page=0&size=5&sort=price,asc&inStock=true" -H "Authorization: Bearer $TOKEN"
curl "$BASE_URL/api/products/1" -H "Authorization: Bearer $TOKEN"
curl -i -X POST "$BASE_URL/api/products" -H "Authorization: Bearer $TOKEN" -H 'Content-Type: application/json' -d '{"name":"Bàn phím","description":"USB","price":450000,"stock":12}'
curl -i -X PUT "$BASE_URL/api/products/1" -H "Authorization: Bearer $TOKEN" -H 'Content-Type: application/json' -d '{"name":"Bàn phím","description":null,"price":420000,"stock":10}'
curl -i -X DELETE "$BASE_URL/api/products/1" -H "Authorization: Bearer $TOKEN"
```

Ba lệnh ghi cần token ADMIN; DELETE xóa dữ liệu thật khi thực thi. Các lệnh chỉ là ví dụ, chưa chạy trong công việc này.

<!-- spring-docs:verification:start -->
## Ghi nhận đối chiếu

- `document_status`: draft.
- `source_snapshot`: commit `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`; đối chiếu mã nguồn sạch tại thời điểm 2026-11-12T10:24:16+00:00. Các file tài liệu mới chưa commit không thuộc snapshot code này.
- `code_scope`: Năm endpoint, DTO, validation, SecurityConfig, JWT filter, Specification và GlobalExceptionHandler.
- `dependencies`: security/JWT, DTO, service, mapper, repository/specification, entity, cache, xử lý lỗi và migration liên quan đến Product.
- `code_review`: unknown; chưa có bằng chứng review cho snapshot này.
- `verification_status`: complete trong phạm vi đối chiếu mã nguồn; kiểm tra liên kết cục bộ. Không xác nhận hành vi runtime.
- `verified_commit`: `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`.
- `verified_at`: 2026-11-12T10:24:16+00:00.
- `content_updated_at`: 2026-11-12T10:24:16+00:00.
- `verification_limits`: chưa chạy Maven, ứng dụng, HTTP request hay render Mermaid; test được dẫn là mã test đã có, không phải kết quả chạy.
<!-- spring-docs:verification:end -->
