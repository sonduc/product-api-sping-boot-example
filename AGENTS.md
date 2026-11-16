# Hướng dẫn làm việc trong repository

## Chọn ngữ cảnh

- Đọc phần liên quan trong [README](README.md) để biết mục đích, cách chạy và kiểm tra project. Đối chiếu phiên bản khai báo tại [pom.xml](pom.xml) và môi trường tại `.devcontainer/`.
- Tài liệu Product bắt đầu tại [docs/README.md](docs/README.md); phạm vi là các API do `ProductController` cung cấp và các phụ thuộc trực tiếp.
- Đọc `src/main/java/com/example/productapi/controller/ProductController.java`, rồi theo tham chiếu đến DTO, service, repository, security và xử lý lỗi khi cần. Đối chiếu tài liệu với code hiện tại.
- Không xem tên package hoặc một ví dụ triển khai là quy tắc bắt buộc cho toàn bộ hệ thống.

## Thực hiện và kiểm tra

- Xác định phạm vi, HEAD và thay đổi cục bộ trước khi sửa; bảo toàn thay đổi ngoài phạm vi.
- Trình bày kế hoạch tương xứng trước khi viết. Tiếp tục các bước đã được người dùng cho phép, không xin lại theo từng file.
- Với tài liệu, kiểm tra nội dung với nguồn, liên kết tương đối, sơ đồ và diff; ghi rõ snapshot, giới hạn và các kiểm tra thực sự đã chạy.
- Lệnh kiểm tra được khai báo trong `pom.xml` và hướng dẫn trong README: `mvn test` cho unit/MVC/repository test; `mvn verify` chạy thêm integration test. README hướng dẫn chạy Maven trong devcontainer; integration test cần Docker/Testcontainers. Việc liệt kê lệnh không xác nhận đã chạy thành công.
- Báo rõ mâu thuẫn giữa yêu cầu và code, không tự sửa yêu cầu để hợp thức hóa hành vi.

## Phạm vi quyền và bàn giao

- Điều tra không tự cấp quyền sửa code, tài liệu hoặc hướng dẫn agent. Chỉ tạo/cập nhật những nội dung thuộc yêu cầu đã được cho phép.
- Commit/push, publish/deploy, cài toolchain và đổi cấu hình toàn cục cần được cho phép trong phạm vi công việc; quyền môi trường vẫn áp dụng riêng.
- Khi hoàn tất, nêu file thay đổi, kiểm tra đã chạy và giới hạn. Phân biệt đối chiếu mã nguồn, chạy test và code review.
