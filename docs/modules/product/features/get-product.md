# Chi tiết sản phẩm

## 1. Phạm vi

Chi tiết sản phẩm qua GET `/api/products/{id}`; chức năng thuộc ProductController. Điều kiện hoàn thành là response tương ứng sau luồng xử lý thành công; thao tác ghi chỉ thành công khi transaction commit.

## 2. Đầu vào và đầu ra

Path id kiểu Long; 200 với ProductResponse. Chi tiết schema, HTTP errors và ví dụ tại [API](../api.md); dữ liệu tại [data-model](../data-model.md).

Điểm vào: [ProductController.getById](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L72). Service: [ProductService.getById](../../../../src/main/java/com/example/productapi/service/ProductService.java#L37).

## 3. Điều kiện và quy tắc

ADMIN hoặc USER. Id không parse được thành Long trả 400; không có ràng buộc @Positive tại controller. Id hợp lệ nhưng không tồn tại trả 404.

Nguồn: controller/service ở trên, [SecurityConfig.securityFilterChain](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L38), [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11), [CacheConfig.cacheManager](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L15).

## 4. Luồng và tác động

1. Controller gọi ProductService.getById(id).
2. Cache interceptor tra cache products theo id; hit trả DTO đã cache, không chạy thân phương thức service.
3. Khi miss, service đọc entity trong transaction read-only qua findProduct; thiếu entity ném ResourceNotFoundException.
4. Mapper tạo ProductResponse; kết quả được cache theo cấu hình transaction-aware rồi controller trả 200.

Không ghi DB. Cache nằm trong bộ nhớ từng instance, không cấu hình TTL; mất khi tiến trình khởi động lại. Các thao tác ghi thành công xóa toàn bộ cache sau commit. Không có cache âm cho id không tồn tại trong luồng này.

Bằng chứng: [ProductService.getById](../../../../src/main/java/com/example/productapi/service/ProductService.java#L37), [ProductMapper](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L10), [CacheConfig.cacheManager](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L15), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L21). Với lưu trữ/version, xem [Product](../../../../src/main/java/com/example/productapi/domain/Product.java#L17).

## 5. Kiểm tra và điểm chưa xác minh

Các test liên quan đã có trong repository:

- [ProductCacheTest](../../../../src/test/java/com/example/productapi/service/ProductCacheTest.java): `cachesDtosAndEvictsAfterSuccessfulWrite`.
- [ProductCrudIT](../../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java): `createReadUpdateDeleteAndCacheInvalidation; missingProductReturnsNotFound`.

Chưa chạy các test này trong công việc tài liệu. Chưa xác nhận cấu hình và dữ liệu của hệ thống triển khai thực tế.

<!-- spring-docs:verification:start -->
## Ghi nhận đối chiếu

- `document_status`: draft.
- `source_snapshot`: commit `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`; đối chiếu mã nguồn sạch tại thời điểm 2026-11-12T10:24:16+00:00. Các file tài liệu mới chưa commit không thuộc snapshot code này.
- `code_scope`: ProductController.getById, ProductService và phụ thuộc trực tiếp của thao tác này.
- `dependencies`: security/JWT, DTO, service, mapper, repository/specification, entity, cache, xử lý lỗi và migration liên quan đến Product.
- `code_review`: unknown; chưa có bằng chứng review cho snapshot này.
- `verification_status`: complete trong phạm vi đối chiếu mã nguồn; kiểm tra liên kết cục bộ. Không xác nhận hành vi runtime.
- `verified_commit`: `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`.
- `verified_at`: 2026-11-12T10:24:16+00:00.
- `content_updated_at`: 2026-11-12T10:24:16+00:00.
- `verification_limits`: chưa chạy Maven, ứng dụng, HTTP request hay render Mermaid; test được dẫn là mã test đã có, không phải kết quả chạy.
<!-- spring-docs:verification:end -->
