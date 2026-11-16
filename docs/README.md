# Tài liệu Product API

Bộ tài liệu tiếng Việt phục vụ người phát triển backend và người tích hợp API. Phạm vi bao gồm cả năm thao tác của `ProductController` cùng các phụ thuộc trực tiếp; không bao gồm toàn bộ API Auth hoặc vận hành hệ thống.

## Thứ tự đọc

1. [Tổng quan Product](modules/product/OVERVIEW.md): trách nhiệm và quan hệ giữa các lớp.
2. [API](modules/product/api.md): endpoint, quyền, request/response và ví dụ sử dụng.
3. [Dữ liệu](modules/product/data-model.md): entity, DTO và ràng buộc lưu trữ.
4. Luồng cụ thể: [danh sách](modules/product/features/list-products.md), [chi tiết](modules/product/features/get-product.md), [tạo](modules/product/features/create-product.md), [sửa](modules/product/features/update-product.md), [xóa](modules/product/features/delete-product.md).

## Chạy và kiểm tra project

Dùng [README gốc](../README.md) làm nguồn hướng dẫn setup, đăng nhập, cổng kết nối và kiểm tra. [pom.xml](../pom.xml) khai báo Java 21, Spring Boot 3.3.13 và Failsafe integration test; đây là phiên bản khai báo, chưa kiểm tra phiên bản runtime hoặc dependency đã resolve.

Theo README, chạy Maven trong devcontainer: `mvn test` cho unit/MVC/repository tests; `mvn verify` chạy thêm integration test với Docker và Testcontainers PostgreSQL. Report nằm tại `target/surefire-reports` và `target/failsafe-reports`. Các lệnh này chưa được chạy trong công việc viết tài liệu.

## Cách đọc bằng chứng

Thông tin mô tả là hành vi đã đối chiếu trong code ở snapshot ghi cuối mỗi trang. Ví dụ JSON/curl là minh họa. Mã test cho biết kỳ vọng được viết trong project; không chứng minh test đang pass. Chưa xác minh database hay triển khai đang chạy.

<!-- spring-docs:verification:start -->
## Ghi nhận đối chiếu

- `document_status`: draft.
- `source_snapshot`: commit `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`; đối chiếu mã nguồn sạch tại thời điểm 2026-11-12T10:24:16+00:00. Các file tài liệu mới chưa commit không thuộc snapshot code này.
- `code_scope`: ProductController và điều hướng bộ tài liệu; pom.xml, README.md, .devcontainer/devcontainer.json.
- `dependencies`: security/JWT, DTO, service, mapper, repository/specification, entity, cache, xử lý lỗi và migration liên quan đến Product.
- `code_review`: unknown; chưa có bằng chứng review cho snapshot này.
- `verification_status`: complete trong phạm vi đối chiếu mã nguồn; kiểm tra liên kết cục bộ. Không xác nhận hành vi runtime.
- `verified_commit`: `92d4ff87e67f2a90bff2e379f4a6af71c4d1d621`.
- `verified_at`: 2026-11-12T10:24:16+00:00.
- `content_updated_at`: 2026-11-12T10:24:16+00:00.
- `verification_limits`: chưa chạy Maven, ứng dụng, HTTP request hay render Mermaid; test được dẫn là mã test đã có, không phải kết quả chạy.
<!-- spring-docs:verification:end -->
