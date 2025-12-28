package service;

import dao.ThongKeDao;
import model.ThongKe;
import java.util.List;

public class ThongKeService {

    private ThongKeDao dao = new ThongKeDao();


    public List<ThongKe> getDoanhThuTheoTuyen() {
        return dao.getDoanhThuTheoTuyen();
    }


    public double getTongDoanhThuThang(int thang, int nam) {
        // Kiểm tra logic: Tháng phải từ 1-12, Năm phải > 1900
        if (thang < 1 || thang > 12) {
            System.out.println("Lỗi: Tháng không hợp lệ (Phải từ 1-12)");
            return 0;
        }
        if (nam < 1900) {
            System.out.println("Lỗi: Năm không hợp lệ");
            return 0;
        }

        return dao.getTongDoanhThuThang(thang, nam);
    }


    public List<ThongKe> getDoanhThuTungThangTrongNam(int nam) {
        if (nam < 1900) {
            System.out.println("Lỗi: Năm không hợp lệ");
            return null; // Hoặc trả về list rỗng
        }
        return dao.getDoanhThuTungThangTrongNam(nam);
    }


    public double getTongDoanhThuToanBo() {
        List<ThongKe> list = dao.getDoanhThuTheoTuyen();
        double tong = 0;
        for (ThongKe item : list) {
            tong += item.getDoanhThu();
        }
        return tong;
    }


    public String getTuyenHotNhat() {
        List<ThongKe> list = dao.getDoanhThuTheoTuyen();
        if (list.isEmpty()) {
            return "Chưa có dữ liệu";
        }

        ThongKe max = list.get(0);
        for (ThongKe item : list) {
            if (item.getDoanhThu() > max.getDoanhThu()) {
                max = item;
            }
        }

        return max.getTenTuyen() + " (" + String.format("%,.0f", max.getDoanhThu()) + " VNĐ)";
    }
}