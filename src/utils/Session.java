package utils;
import model.NguoiDung;
public class Session {
    public static NguoiDung taiKhoan = null;
    public static boolean isLogin() {
        return taiKhoan != null;
    }
}