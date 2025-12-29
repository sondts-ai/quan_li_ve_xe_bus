package service;

import dao.NguoiDungDao;
import model.NguoiDung;
import utils.Auth; // Class lưu phiên đăng nhập

public class NguoiDungService {

    private NguoiDungDao dao = new NguoiDungDao();


    public String dangNhap(String taiKhoan, String matKhau) {

        if (taiKhoan.trim().isEmpty() || matKhau.trim().isEmpty()) {
            return "Vui lòng nhập đầy đủ tài khoản và mật khẩu!";
        }

        NguoiDung nd = dao.dangNhap(taiKhoan, matKhau);

        if (nd == null) {
            return "Sai tài khoản hoặc mật khẩu!";
        }

        Auth.user = nd;

        return "Đăng nhập thành công!";
    }


    public String dangKi(NguoiDung nd) {

        if (nd.getTaiKhoan().trim().isEmpty() || nd.getMatKhau().trim().isEmpty()) {
            return "Tài khoản và mật khẩu không được để trống!";
        }
        if (nd.getHoTen().trim().isEmpty()) {
            return "Họ tên không được để trống!";
        }

        if (dao.tonTaiTaiKhoan(nd.getTaiKhoan())) {
            return "Tài khoản '" + nd.getTaiKhoan() + "' đã tồn tại!";
        }

        if (nd.getVaiTro() == null || nd.getVaiTro().isEmpty()) {
            nd.setVaiTro("khachhang");
        }


        if (dao.dangKi(nd)) {
            return "Đăng ký thành công!";
        } else {
            return "Đăng ký thất bại (Lỗi hệ thống)!";
        }
    }


    public void reloadThongTinCaNhan() {
        if (Auth.isLogin()) {
            // Lấy ID từ người đang đăng nhập hiện tại
            int currentId = Auth.user.getNguoiDungId();
            // Gọi DAO lấy dữ liệu mới nhất từ DB
            NguoiDung moiNhat = dao.layThongTinTheoId(currentId);
            // Cập nhật lại vào Auth
            if (moiNhat != null) {
                Auth.user = moiNhat;
            }
        }
    }

    public void dangXuat() {
        Auth.clear();
    }

    public boolean isAdmin() {
        return Auth.isLogin() && (
                Auth.user.getVaiTro().equalsIgnoreCase("admin") ||
                        Auth.user.getVaiTro().equalsIgnoreCase("quanly")
        );
    }
    public boolean capNhatThongTin(NguoiDung nd) {
        if (nd == null || !Auth.isLogin() || nd.getNguoiDungId() != Auth.user.getNguoiDungId()) {
            return false; // Kiểm tra người dùng hợp lệ
        }

        // Gọi DAO để cập nhật vào database
        boolean success = dao.capNhat(nd);

        if (success) {
            // Nếu cập nhật thành công, load lại thông tin mới nhất vào Auth.user
            reloadThongTinCaNhan();
        }

        return success;
    }

}