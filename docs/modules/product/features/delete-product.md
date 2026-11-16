# Xóa sản phẩm

## 1. Phạm vi

Xóa sản phẩm qua DELETE `/api/products/{id}`; chức năng thuộc ProductController. Điều kiện hoàn thành là response tương ứng sau luồng xử lý thành công; thao tác ghi chỉ thành công khi transaction commit.

## 2. Đầu vào và đầu ra

Path id kiểu Long; 204, không response body. Chi tiết schema, HTTP errors và ví dụ tại [API](../api.md); dữ liệu tại [data-model](../data-model.md).

Điểm vào: [ProductController.delete](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L92). Service: [ProductService.delete](../../../../src/main/java/com/example/productapi/service/ProductService.java#L59).

## 3. Điều kiện và quy tắc

ADMIN. Không có body hay @Positive cho id. Phải tìm thấy sản phẩm; xóa lại cùng id sau khi đã xóa trả 404 theo nhánh findProduct.

Nguồn: controller/service ở trên, [SecurityConfig.securityFilterChain](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L38), [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11), [CacheConfig.cacheManager](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L15).

## 4. Luồng và tác động

1. Controller gọi service, service mở transaction ghi.
2. findProduct tải entity; thiếu trả 404.
3. Repository delete(entity), sau đó flush để đồng bộ lệnh xóa. Entity có @Version; nếu phát sinh optimistic conflict thì handler chung trả 409.
4. Commit thành công xóa toàn bộ cache products, controller trả 204.

Không soft delete trong code/schema được khảo sát. Ngoại lệ runtime rollback DB; cache eviction chờ commit không được áp dụng. Không có lời gọi job/event/dịch vụ ngoài trong phương thức này.

Bằng chứng: [ProductService.delete](../../../../src/main/java/com/example/productapi/service/ProductService.java#L59), [ProductMapper](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L10), [CacheConfig.cacheManager](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L15), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L21). Với lưu trữ/version, xem [Product](../../../../src/main/java/com/example/productapi/domain/Product.java#L17).

## 5. Kiểm tra và điểm chưa xác minh

Các test liên quan đã có trong repository:

- [ProductCrudIT](../../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java): `createReadUpdateDeleteAndCacheInvalidation; missingProductReturnsNotFound`.
- [ProductCacheTest](../../../../src/test/java/com/example/productapi/service/ProductCacheTest.java): `rollbackKeepsCacheAndCommitEvictsIt`.

Chưa chạy các test này trong công việc tài liệu. Test cache rollback dùng transaction synchronization được mô phỏng; chưa có kết quả HTTP kiểm tra rollback riêng cho thao tác này trong lần đối chiếu. Chưa xác nhận cấu hình và dữ liệu của hệ thống triển khai thực tế.

<!-- spring-docs:verification:start -->
## Ghi nhận đối chiếu

- `document_status`: draft.
- `source_snapshot`: commit `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`; đối chiếu mã nguồn sạch tại thời điểm 2026-11-12T10:24:16+00:00. Các file tài liệu mới chưa commit không thuộc snapshot code này.
- `code_scope`: ProductController.delete, ProductService và phụ thuộc trực tiếp của thao tác này.
- `dependencies`: security/JWT, DTO, service, mapper, repository/specification, entity, cache, xử lý lỗi và migration liên quan đến Product.
- `code_review`: unknown; chưa có bằng chứng review cho snapshot này.
- `verification_status`: complete trong phạm vi đối chiếu mã nguồn; kiểm tra liên kết cục bộ. Không xác nhận hành vi runtime.
- `verified_commit`: `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`.
- `verified_at`: 2026-11-12T10:24:16+00:00.
- `content_updated_at`: 2026-11-12T10:24:16+00:00.
- `verification_limits`: chưa chạy Maven, ứng dụng, HTTP request hay render Mermaid; test được dẫn là mã test đã có, không phải kết quả chạy.
<!-- spring-docs:verification:end -->
