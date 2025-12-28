package service;

import dao.XeDao;
import model.Xe;
import java.util.List;

public class XeService {

    private XeDao dao = new XeDao();

    public List<Xe> getAll() {
        return dao.getAll();
    }


    public String them(Xe xe) {
        if (xe.getTenXe() == null || xe.getTenXe().trim().isEmpty()) {
            return "Lỗi: Tên xe không được để trống!";
        }

        if (xe.getBienSo() == null || xe.getBienSo().trim().isEmpty()) {
            return "Lỗi: Biển số không được để trống!";
        }

        if (xe.getTongGhe() <= 0) {
            return "Lỗi: Tổng số ghế phải lớn hơn 0!";
        }


        if (dao.insert(xe)) {
            return "Thêm xe thành công!";
        } else {
            return "Thêm thất bại! (Có thể biển số xe đã tồn tại)";
        }
    }

    public String capNhat(Xe xe) {
        if (xe.getTenXe().trim().isEmpty()) {
            return "Lỗi: Tên xe không được để trống!";
        }
        if (xe.getTongGhe() <= 0) {
            return "Lỗi: Số ghế không hợp lệ!";
        }

        if (dao.update(xe)) {
            return "Cập nhật thành công!";
        } else {
            return "Cập nhật thất bại!";
        }
    }

    public String xoa(int xeId) {

        if (dao.delete(xeId)) {
            return "Xóa thành công!";
        } else {
            return "Lỗi: Không thể xóa (Xe này đang có lịch chạy hoặc lỗi hệ thống)!";
        }
    }
}