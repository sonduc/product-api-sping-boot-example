# Dữ liệu Product

## Entity và bảng

[Product](../../../src/main/java/com/example/productapi/domain/Product.java#L17) ánh xạ tới bảng `products`. [V1](../../../src/main/resources/db/migration/V1__init_schema.sql) tạo bảng; [V4](../../../src/main/resources/db/migration/V4__add_version_to_products.sql) thêm version.

| Field Java | Cột / kiểu SQL | Đặc điểm |
|---|---|---|
| `id` | `id BIGSERIAL` | Khóa chính, IDENTITY |
| `name` | `name VARCHAR(255)` | Bắt buộc; có index, không khai báo unique |
| `description` | `description TEXT` | Có thể null |
| `price` | `price NUMERIC(12,2)` | Bắt buộc |
| `stock` | `stock INT` | Bắt buộc |
| `createdAt`, `updatedAt` | `created_at`, `updated_at TIMESTAMP` | Bắt buộc, cập nhật qua callback entity |
| `version` | `version BIGINT` | `@Version`, NOT NULL, migration đặt default 0 |

Trong entity và hai migration nêu trên không có quan hệ entity/FK từ Product tới bảng khác. Bảng `users` phục vụ xác thực, không phải chủ sở hữu được gắn vào Product. Schema không khai báo CHECK cho giá/tồn kho; ràng buộc HTTP nằm tại DTO. Chưa kiểm tra schema của database đang chạy.

## DTO và ánh xạ

| Kiểu | Mục đích | Nguồn |
|---|---|---|
| `ProductRequest` | name, description, price, stock cho cả POST và PUT | [ProductRequest](../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) |
| `ProductResponse` | id, name, description, price, stock, createdAt, updatedAt; không có version | [ProductResponse](../../../src/main/java/com/example/productapi/dto/ProductResponse.java#L6) |
| `ProductFilter` | name, minPrice, maxPrice, inStock | [ProductFilter](../../../src/main/java/com/example/productapi/dto/ProductFilter.java#L5) |
| `PageResponse<T>` | content, page, size, totalElements, totalPages | [PageResponse](../../../src/main/java/com/example/productapi/dto/PageResponse.java#L6) |

[ProductMapper](../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L10) tạo entity mới hoặc thay thế cả bốn field request trên entity hiện có. Mapper không tự trim `name` khi ghi. Tìm kiếm tên có trim riêng ở Specification.

## Thời gian và cập nhật đồng thời

[Product.void onCreate](../../../src/main/java/com/example/productapi/domain/Product.java#L56) đặt hai mốc thời gian bằng `LocalDateTime.now()` khi tạo; `onUpdate` cập nhật `updatedAt`. `LocalDateTime` không mang offset/timezone; API không khai báo một múi giờ chuẩn riêng trong DTO này.

`@Version` hỗ trợ phát hiện transaction ghi cạnh tranh trên cùng version. Request/response không có version nên form cũ gửi sau khi lần ghi trước đã kết thúc vẫn có thể ghi đè dữ liệu mới. Xem [luồng sửa](features/update-product.md).

<!-- spring-docs:verification:start -->
## Ghi nhận đối chiếu

- `document_status`: draft.
- `source_snapshot`: commit `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`; đối chiếu mã nguồn sạch tại thời điểm 2026-11-12T10:24:16+00:00. Các file tài liệu mới chưa commit không thuộc snapshot code này.
- `code_scope`: Product entity, DTO, mapper và migration V1/V4.
- `dependencies`: security/JWT, DTO, service, mapper, repository/specification, entity, cache, xử lý lỗi và migration liên quan đến Product.
- `code_review`: unknown; chưa có bằng chứng review cho snapshot này.
- `verification_status`: complete trong phạm vi đối chiếu mã nguồn; kiểm tra liên kết cục bộ. Không xác nhận hành vi runtime.
- `verified_commit`: `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`.
- `verified_at`: 2026-11-12T10:24:16+00:00.
- `content_updated_at`: 2026-11-12T10:24:16+00:00.
- `verification_limits`: chưa chạy Maven, ứng dụng, HTTP request hay render Mermaid; test được dẫn là mã test đã có, không phải kết quả chạy.
<!-- spring-docs:verification:end -->
