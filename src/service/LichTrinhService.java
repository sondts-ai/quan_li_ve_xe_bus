package service;

import dao.LichTrinhDao;
import model.LichTrinh;
import java.util.List;
import java.util.ArrayList;

public class LichTrinhService {
    private LichTrinhDao lichTrinhDAO = new LichTrinhDao();

    public List<LichTrinh> getAllLichTrinh() {
        return lichTrinhDAO.getAll();
    }

    public String themLichTrinh(LichTrinh lt) {
        if (lt.getThoiGianKhoiHanh() == null || lt.getThoiGianDen() == null) {
            return "Lỗi: Thời gian không được để trống!";
        }

        if (lt.getThoiGianDen().before(lt.getThoiGianKhoiHanh())) {
            return "Lỗi: Thời gian đến phải sau thời gian khởi hành!";
        }

        if (lt.getGiaVe() <= 0) {
            return "Lỗi: Giá vé phải lớn hơn 0!";
        }


        if (lichTrinhDAO.insert(lt)) {
            return "Thêm lịch trình thành công!";
        } else {
            return "Lỗi: Không thể thêm vào CSDL (Có thể do lỗi mạng hoặc trùng ID)";
        }
    }

    public String xoaLichTrinh(int lichId) {

        if (lichTrinhDAO.delete(lichId)) {
            return "Xóa thành công!";
        } else {
            return "Lỗi: Không thể xóa (Có thể lịch trình đã có người đặt vé)!";
        }
    }

    public String capNhat(LichTrinh lt) {
        if (lt.getGiaVe() <= 0) {
            return "Lỗi: Giá vé phải lớn hơn 0!";
        }
        if (lichTrinhDAO.update(lt)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }

    public List<LichTrinh> timChuyenXe(String khoiHanh, String diemDen, java.util.Date ngay) {


        if (khoiHanh == null || khoiHanh.trim().isEmpty()) {
            return new ArrayList<>();
        }
        if (diemDen == null || diemDen.trim().isEmpty()) {
            return new ArrayList<>();
        }
        if (ngay == null) {
            return new ArrayList<>();
        }

        return lichTrinhDAO.timChuyenXe(khoiHanh, diemDen, ngay);
    }

    public List<LichTrinh> getLichTrinhByTuyenId(int tuyenId) {
        if (tuyenId <= 0) {
            return new ArrayList<>();
        }
        return lichTrinhDAO.getByTuyenId(tuyenId);
    }

    public LichTrinh getByLichId(int lichId) {
        return lichTrinhDAO.getByLichId(lichId);
    }


}