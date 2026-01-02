# 🚌 Phần Mềm Quản Lý Vé Xe Khách

Đây là ứng dụng máy tính (Desktop Application) được xây dựng bằng ngôn ngữ **Java**, hỗ trợ quản lý hệ thống đặt vé xe khách/xe bus. Hệ thống bao gồm các chức năng quản lý người dùng, lịch trình, tuyến xe và đặt vé, phục vụ cho cả quản trị viên (Admin) và người dùng (User).

## 🚀 Tính Năng Chính

Dựa trên cấu trúc mã nguồn hiện tại, phần mềm cung cấp các chức năng sau:

### 1. Quản lý hệ thống (Dành cho Admin)
* **Quản lý Tuyến xe (`TuyenXe`):** Thêm mới, cập nhật, xóa các tuyến đường vận hành.
* **Quản lý Xe (`Xe`):** Quản lý thông tin chi tiết các xe (biển số, loại xe, số ghế...).
* **Quản lý Lịch trình (`LichTrinh`):** Sắp xếp thời gian khởi hành và gán xe cho các tuyến.
* **Thống kê báo cáo (`ThongKe`):** Xem báo cáo doanh thu, số lượng vé bán ra theo thời gian.

### 2. Người dùng & Đặt vé (Dành cho Khách hàng)
* **Hệ thống tài khoản:** Đăng ký thành viên mới (`UIrigister`) và Đăng nhập (`UIlogin`).
* **Tìm kiếm chuyến xe:** Tra cứu chuyến xe theo điểm đi, điểm đến và ngày giờ (`UIFindBus`).
* **Đặt vé xe:** Xem sơ đồ ghế, chọn chỗ và đặt vé (`UIDialogShowCartrip`, `VeXe`).
* **Quản lý cá nhân:** Xem lịch sử vé đã đặt (`UIInforTicket`), cập nhật thông tin cá nhân (`UIFixInfor`) và đổi mật khẩu (`UIFixPass`).

## 🛠 Công Nghệ Sử Dụng

* **Ngôn ngữ lập trình:** Java (JDK 11 trở lên).
* **Giao diện (GUI):** Java Swing (được thiết kế bằng IntelliJ GUI Designer - `.form`).
* **Cơ sở dữ liệu:** Microsoft SQL Server.
* **IDE Phát triển:** IntelliJ IDEA.
* **Thư viện kết nối:** JDBC (`mssql-jdbc`).

## 📂 Cấu Trúc Dự Án

Dự án được tổ chức thành các module rõ ràng:

* **`backend/`**: Chứa mã nguồn xử lý logic nghiệp vụ.
    * `src/dao/`: (Data Access Object) Các lớp giao tiếp trực tiếp với CSDL (Connectdb, NguoiDungDao...).
    * `src/model/`: Các lớp thực thể (Entity) mô tả dữ liệu (NguoiDung, VeXe, Xe...).
    * `src/service/`: Lớp dịch vụ xử lý logic trung gian.
* **`frontend/`**: Chứa mã nguồn giao diện người dùng.
    * Các lớp `UI...`: Màn hình giao diện (Login, Main, FindBus...).
    * `src/sprites/`: Tài nguyên hình ảnh, icon.
* **`database/`**: Chứa file kịch bản SQL (`xekhach.sql`) để khởi tạo CSDL.

## ⚙️ Hướng Dẫn Cài Đặt (Installation)

### Bước 1: Chuẩn bị môi trường
* Cài đặt **Java JDK 11** (hoặc mới hơn).
* Cài đặt **Microsoft SQL Server**.
* Cài đặt **IntelliJ IDEA**.

### Bước 2: Thiết lập Cơ sở dữ liệu
1. Mở SQL Server Management Studio (SSMS).
2. Mở file script tại đường dẫn: `database/xekhach.sql`.
3. Thực thi (Execute) script để tạo Database và các bảng dữ liệu.

### Bước 3: Cấu hình mã nguồn
1. Mở dự án bằng IntelliJ IDEA.
2. Mở file cấu hình kết nối: `backend/src/dao/Connectdb.java`.
3. Cập nhật thông tin kết nối CSDL của bạn:
   ```java
   String url = "jdbc:sqlserver://localhost:1433;databaseName=TenDatabaseCuaBan";
   String user = "sa"; // Tên đăng nhập SQL của bạn
   String password = "mat_khau_sql_cua_ban"; // Mật khẩu SQL của bạn
