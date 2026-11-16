# Tạo sản phẩm

## 1. Phạm vi

Tạo sản phẩm qua POST `/api/products`; chức năng thuộc ProductController. Điều kiện hoàn thành là response tương ứng sau luồng xử lý thành công; thao tác ghi chỉ thành công khi transaction commit.

## 2. Đầu vào và đầu ra

JSON ProductRequest; 201 với ProductResponse và Location `/api/products/{id}`. Chi tiết schema, HTTP errors và ví dụ tại [API](../api.md); dữ liệu tại [data-model](../data-model.md).

Điểm vào: [ProductController.create](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L78). Service: [ProductService.create](../../../../src/main/java/com/example/productapi/service/ProductService.java#L45).

## 3. Điều kiện và quy tắc

ADMIN. @Valid áp dụng các ràng buộc name/price/stock tại [API](../api.md). description tùy chọn. Tên không có ràng buộc unique trong schema đã khảo sát.

Nguồn: controller/service ở trên, [SecurityConfig.securityFilterChain](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L38), [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11), [CacheConfig.cacheManager](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L15).

## 4. Luồng và tác động

1. Security kiểm tra quyền, MVC parse JSON và validate body trước khi gọi phương thức controller.
2. Service mở transaction ghi, mapper tạo Product mới từ request.
3. saveAndFlush ghi entity; callback onCreate đặt createdAt/updatedAt; DB sinh id và JPA quản lý version.
4. Mapper tạo DTO; transaction commit thành công thì eviction toàn bộ cache products được áp dụng. Controller trả 201 với Location tương đối.

Validation/body sai trả 400; thiếu quyền trả 401/403. Runtime exception làm rollback transaction; không áp dụng eviction chờ commit. Flush chưa đồng nghĩa commit. Không có job/event hay lời gọi ngoài trong phương thức này.

Bằng chứng: [ProductService.create](../../../../src/main/java/com/example/productapi/service/ProductService.java#L45), [ProductMapper](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L10), [CacheConfig.cacheManager](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L15), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L21). Với lưu trữ/version, xem [Product](../../../../src/main/java/com/example/productapi/domain/Product.java#L17).

## 5. Kiểm tra và điểm chưa xác minh

Các test liên quan đã có trong repository:

- [ProductCrudIT](../../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java): `validationReturnsFieldErrors; createReadUpdateDeleteAndCacheInvalidation`.
- [ProductCacheTest](../../../../src/test/java/com/example/productapi/service/ProductCacheTest.java): `rollbackKeepsCacheAndCommitEvictsIt`.

Chưa chạy các test này trong công việc tài liệu. Test cache rollback dùng transaction synchronization được mô phỏng; chưa có kết quả HTTP kiểm tra rollback riêng cho thao tác này trong lần đối chiếu. Chưa xác nhận cấu hình và dữ liệu của hệ thống triển khai thực tế.

<!-- spring-docs:verification:start -->
## Ghi nhận đối chiếu

- `document_status`: draft.
- `source_snapshot`: commit `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`; đối chiếu mã nguồn sạch tại thời điểm 2026-11-12T10:24:16+00:00. Các file tài liệu mới chưa commit không thuộc snapshot code này.
- `code_scope`: ProductController.create, ProductService và phụ thuộc trực tiếp của thao tác này.
- `dependencies`: security/JWT, DTO, service, mapper, repository/specification, entity, cache, xử lý lỗi và migration liên quan đến Product.
- `code_review`: unknown; chưa có bằng chứng review cho snapshot này.
- `verification_status`: complete trong phạm vi đối chiếu mã nguồn; kiểm tra liên kết cục bộ. Không xác nhận hành vi runtime.
- `verified_commit`: `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`.
- `verified_at`: 2026-11-12T10:24:16+00:00.
- `content_updated_at`: 2026-11-12T10:24:16+00:00.
- `verification_limits`: chưa chạy Maven, ứng dụng, HTTP request hay render Mermaid; test được dẫn là mã test đã có, không phải kết quả chạy.
<!-- spring-docs:verification:end -->
