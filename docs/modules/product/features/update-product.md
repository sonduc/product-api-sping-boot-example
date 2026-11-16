# Sửa sản phẩm

## 1. Phạm vi

Sửa sản phẩm qua PUT `/api/products/{id}`; chức năng thuộc ProductController. Điều kiện hoàn thành là response tương ứng sau luồng xử lý thành công; thao tác ghi chỉ thành công khi transaction commit.

## 2. Đầu vào và đầu ra

Path id và JSON ProductRequest; 200 với ProductResponse. Chi tiết schema, HTTP errors và ví dụ tại [API](../api.md); dữ liệu tại [data-model](../data-model.md).

Điểm vào: [ProductController.update](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L85). Service: [ProductService.update](../../../../src/main/java/com/example/productapi/service/ProductService.java#L51).

## 3. Điều kiện và quy tắc

ADMIN. Cùng validation như POST. Mapper thay thế name/description/price/stock, nên description bị bỏ qua trong JSON sẽ thành null. createdAt được giữ; version không nằm trong request.

Nguồn: controller/service ở trên, [SecurityConfig.securityFilterChain](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L38), [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11), [CacheConfig.cacheManager](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L15).

## 4. Luồng và tác động

1. Controller nhận body hợp lệ và gọi service trong transaction ghi.
2. findProduct đọc entity; không tồn tại trả 404, không tạo mới thay thế.
3. Mapper thay cả bốn field; saveAndFlush đồng bộ thay đổi, callback onUpdate đặt updatedAt khi entity được update.
4. JPA kiểm tra @Version khi ghi. Xung đột ném ngoại lệ, handler trả 409; transaction rollback, giữ cache cũ.
5. Thành công: commit, xóa toàn bộ cache products sau commit, trả DTO 200.

Client gặp 409 cần tải lại dữ liệu và quyết định thử lại. API không nhận version/If-Match nên không bảo vệ mọi trường hợp form cũ được gửi sau khi một transaction khác đã hoàn tất. Không diễn giải optimistic locking thành cơ chế kiểm tra version của form.

Bằng chứng: [ProductService.update](../../../../src/main/java/com/example/productapi/service/ProductService.java#L51), [ProductMapper](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L10), [CacheConfig.cacheManager](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L15), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L21). Với lưu trữ/version, xem [Product](../../../../src/main/java/com/example/productapi/domain/Product.java#L17).

## 5. Kiểm tra và điểm chưa xác minh

Các test liên quan đã có trong repository:

- [ProductControllerTest](../../../../src/test/java/com/example/productapi/controller/ProductControllerTest.java): `optimisticUpdateFailureReturnsConflict`.
- [ProductCrudIT](../../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java): `concurrentHttpUpdatesReturnOneSuccessAndOneConflict; createReadUpdateDeleteAndCacheInvalidation`.
- [ProductCacheTest](../../../../src/test/java/com/example/productapi/service/ProductCacheTest.java): `rollbackKeepsCacheAndCommitEvictsIt`.

Chưa chạy các test này trong công việc tài liệu. Test cache rollback dùng transaction synchronization được mô phỏng; test HTTP cập nhật đồng thời có barrier để hai transaction cùng đọc một version. Chưa xác nhận cấu hình và dữ liệu của hệ thống triển khai thực tế.

<!-- spring-docs:verification:start -->
## Ghi nhận đối chiếu

- `document_status`: draft.
- `source_snapshot`: commit `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`; đối chiếu mã nguồn sạch tại thời điểm 2026-11-12T10:24:16+00:00. Các file tài liệu mới chưa commit không thuộc snapshot code này.
- `code_scope`: ProductController.update, ProductService và phụ thuộc trực tiếp của thao tác này.
- `dependencies`: security/JWT, DTO, service, mapper, repository/specification, entity, cache, xử lý lỗi và migration liên quan đến Product.
- `code_review`: unknown; chưa có bằng chứng review cho snapshot này.
- `verification_status`: complete trong phạm vi đối chiếu mã nguồn; kiểm tra liên kết cục bộ. Không xác nhận hành vi runtime.
- `verified_commit`: `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`.
- `verified_at`: 2026-11-12T10:24:16+00:00.
- `content_updated_at`: 2026-11-12T10:24:16+00:00.
- `verification_limits`: chưa chạy Maven, ứng dụng, HTTP request hay render Mermaid; test được dẫn là mã test đã có, không phải kết quả chạy.
<!-- spring-docs:verification:end -->
