package service; // Hoặc package nào bạn đang để

import dao.Connectdb; // Nhớ import class kết nối của bạn
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("--- ĐANG KIỂM TRA KẾT NỐI ---");

        try {
            // 1. Gọi thử hàm kết nối
            Connection c = Connectdb.getConnection();

            // 2. Kiểm tra kết quả
            if (c != null) {
                System.out.println("✅ KẾT NỐI THÀNH CÔNG!");
                System.out.println("Tên Database: " + c.getCatalog());
                c.close();
            } else {
                System.out.println("❌ KẾT NỐI THẤT BẠI (Trả về null)");
                System.out.println("-> Kiểm tra lại code trong file Connectdb.java");
            }

        } catch (Exception e) {
            System.out.println("❌ CÓ LỖI XẢY RA:");
            e.printStackTrace(); // Quan trọng: In ra lỗi đỏ lòm để biết đường sửa

            // Hướng dẫn đọc lỗi nhanh:
            String loi = e.toString();
            if (loi.contains("ClassNotFoundException")) {
                System.out.println("=> GỢI Ý: Bạn QUÊN thêm thư viện JDBC (.jar) vào Project rồi!");
            } else if (loi.contains("Login failed")) {
                System.out.println("=> GỢI Ý: Sai tài khoản (sa) hoặc mật khẩu SQL.");
            } else if (loi.contains("The TCP/IP connection to the host")) {
                System.out.println("=> GỢI Ý: Sai tên máy chủ (localhost) hoặc sai cổng (1433). Phải bật TCP/IP trong SQL Configuration.");
            } else if (loi.contains("Cannot open database")) {
                System.out.println("=> GỢI Ý: Sai tên Database 'xekhach'.");
            }
        }
    }
}