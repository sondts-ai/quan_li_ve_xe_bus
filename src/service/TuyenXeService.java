package service;

import dao.TuyenXeDao;
import model.TuyenXe;
import java.util.List;
public class TuyenXeService {
    private TuyenXeDao dao = new TuyenXeDao();
    public List<TuyenXe> getAll() {
        return dao.getAll();
    }
    public String them(TuyenXe tx) {
        if (tx.getKhoiHanh().trim().isEmpty() || tx.getDiemDen().trim().isEmpty()) {
            return "Lỗi: Điểm đi và điểm đến không được để trống!";
        }
        if (tx.getKhoiHanh().equalsIgnoreCase(tx.getDiemDen())) {
            return "Lỗi: Điểm đi và điểm đến không được trùng nhau!";
        }
        if (tx.getKhoangCach() <= 0) {
            return "Lỗi: Khoảng cách phải lớn hơn 0km!";
        }
        if (tx.getThoiGianDiChuyen() <= 0) {
            return "Lỗi: Thời gian di chuyển phải lớn hơn 0 phút!";
        }
        if (dao.insert(tx)) {
            return "Thêm tuyến xe thành công!";
        } else {
            return "Thêm thất bại (Lỗi kết nối hoặc dữ liệu)!";
        }
    }
    public String capNhat(TuyenXe tx) {
        if (tx.getKhoiHanh().equalsIgnoreCase(tx.getDiemDen())) {
            return "Lỗi: Điểm đi và điểm đến trùng nhau!";
        }
        if (tx.getKhoangCach() <= 0 || tx.getThoiGianDiChuyen() <= 0) {
            return "Lỗi: Số liệu khoảng cách/thời gian không hợp lệ!";
        }
        if (dao.update(tx)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }
    public String xoa(int tuyenId) {
        if (dao.delete(tuyenId)) {
            return "Xóa thành công!";
        } else {
            return "Lỗi: Không thể xóa (Tuyến này đang có lịch trình hoạt động)!";
        }
    }
}