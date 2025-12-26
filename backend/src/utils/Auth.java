package utils;

import model.NguoiDung;

public class Auth {
    /**
     * Biến user này đóng vai trò như một cái kho lưu trữ toàn cục.
     * - Khi chưa đăng nhập: user = null
     * - Khi đăng nhập thành công: user = đối tượng NguoiDung vừa lấy từ DB
     */
    public static NguoiDung user = null;

    /**
     * Xóa thông tin người dùng khi đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }

    /**
     * Kiểm tra xem đã đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }

    /**
     * Kiểm tra xem người đang đăng nhập có phải là Admin/Quản lý không
     * (Dùng để hiện/ẩn các nút chức năng trên giao diện)
     */
    public static boolean isManager() {
        return Auth.isLogin() &&
                (Auth.user.getVaiTro().equalsIgnoreCase("admin") ||
                        Auth.user.getVaiTro().equalsIgnoreCase("quanly"));
    }
}
