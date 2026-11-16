# Danh sách sản phẩm

## 1. Phạm vi

Danh sách sản phẩm qua GET `/api/products`; chức năng thuộc ProductController. Điều kiện hoàn thành là response tương ứng sau luồng xử lý thành công; thao tác ghi chỉ thành công khi transaction commit.

## 2. Đầu vào và đầu ra

Query tại [API](../api.md); 200 với PageResponse<ProductResponse>. Chi tiết schema, HTTP errors và ví dụ tại [API](../api.md); dữ liệu tại [data-model](../data-model.md).

Điểm vào: [ProductController.getAll](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L40). Service: [ProductService.getAll](../../../../src/main/java/com/example/productapi/service/ProductService.java#L30).

## 3. Điều kiện và quy tắc

ADMIN hoặc USER. Page ≥ 0; size 1–100; sort thuộc danh sách cho phép. Bộ lọc AND, khoảng giá gồm hai đầu; tên dùng LIKE có escape.

Nguồn: controller/service ở trên, [SecurityConfig.securityFilterChain](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L38), [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11), [ProductSpecification.public final class](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L10).

## 4. Luồng và tác động

1. Controller kiểm tra page/size, tách sort, tạo ProductFilter và PageRequest.
2. Service mở transaction read-only, dựng Specification; khoảng giá sai ném BusinessException trước khi query.
3. Repository truy vấn theo điều kiện/phân trang/sort, mapper chuyển từng entity thành DTO.
4. Controller đổi Page thành PageResponse và trả 200. Trang ngoài phạm vi có thể trả content rỗng; không chuyển thành 404.

Không ghi DB, không dùng cache danh sách. Tham số sai hoặc khoảng giá sai trả 400; 401/403 đi qua security.

Bằng chứng: [ProductService.getAll](../../../../src/main/java/com/example/productapi/service/ProductService.java#L30), [ProductMapper](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L10), [ProductSpecification.public final class](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L10), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L21). Với lưu trữ/version, xem [Product](../../../../src/main/java/com/example/productapi/domain/Product.java#L17).

## 5. Kiểm tra và điểm chưa xác minh

Các test liên quan đã có trong repository:

- [ProductControllerTest](../../../../src/test/java/com/example/productapi/controller/ProductControllerTest.java): `appliesDefaultPaginationAndStableSort; rejectsOversizedPagesAndInvalidSort`.
- [ProductServiceTest](../../../../src/test/java/com/example/productapi/service/ProductServiceTest.java): `returnsDtoPageAndPreservesPaginationMetadata; rejectsInvalidFilterBeforeQueryingRepository`.
- [ProductSpecificationTest](../../../../src/test/java/com/example/productapi/repository/spec/ProductSpecificationTest.java): `nameUsesLowercaseAndEscapesLiteralWildcards; rejectsNegativeAndReversedPriceRanges`.

Chưa chạy các test này trong công việc tài liệu. Chưa xác nhận cấu hình và dữ liệu của hệ thống triển khai thực tế.

<!-- spring-docs:verification:start -->
## Ghi nhận đối chiếu

- `document_status`: draft.
- `source_snapshot`: commit `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`; đối chiếu mã nguồn sạch tại thời điểm 2026-11-12T10:24:16+00:00. Các file tài liệu mới chưa commit không thuộc snapshot code này.
- `code_scope`: ProductController.getAll, ProductService và phụ thuộc trực tiếp của thao tác này.
- `dependencies`: security/JWT, DTO, service, mapper, repository/specification, entity, cache, xử lý lỗi và migration liên quan đến Product.
- `code_review`: unknown; chưa có bằng chứng review cho snapshot này.
- `verification_status`: complete trong phạm vi đối chiếu mã nguồn; kiểm tra liên kết cục bộ. Không xác nhận hành vi runtime.
- `verified_commit`: `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`.
- `verified_at`: 2026-11-12T10:24:16+00:00.
- `content_updated_at`: 2026-11-12T10:24:16+00:00.
- `verification_limits`: chưa chạy Maven, ứng dụng, HTTP request hay render Mermaid; test được dẫn là mã test đã có, không phải kết quả chạy.
<!-- spring-docs:verification:end -->
