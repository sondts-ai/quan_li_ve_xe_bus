package dao;

import model.NguoiDung;

public class TestNguoiDungDao {
    public static void main(String[] args) {
        NguoiDungDao dao = new NguoiDungDao();

        NguoiDung nd = dao.dangNhap("admin", "123456");
        if (nd != null) {
            System.out.println("Đăng nhập thành công: " + nd.getHoTen());
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu");
        }
    }
}
