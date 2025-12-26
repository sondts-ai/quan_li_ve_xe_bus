package service;

import dao.VeXeDao;
import model.LichTrinh; // Import để dùng trong hàm main
import java.text.SimpleDateFormat; // Import để xử lý ngày tháng
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestApp {

    private VeXeDao dao = new VeXeDao();

    // ==========================================
    // 1. CÁC HÀM NGHIỆP VỤ (Service Methods)
    // ==========================================

    public boolean datVe(int nguoiDungId, int lichId, String viTriGhe) {
        if (nguoiDungId <= 0 || lichId <= 0) return false;
        if (viTriGhe == null || viTriGhe.trim().isEmpty()) return false;
        return dao.datVe(nguoiDungId, lichId, viTriGhe.trim());
    }

    public boolean huyVe(int veId) {
        if (veId <= 0) return false;
        return dao.huyVe(veId);
    }

    public List<String> getGheDaDat(int lichId) {
        if (lichId <= 0) return new ArrayList<>();
        return dao.getGheDaDat(lichId);
    }

    // ==========================================
    // 2. HÀM MAIN ĐỂ CHẠY TEST (Code bạn gửi)
    // ==========================================
    public static void main(String[] args) {
        // Khởi tạo các Service cần thiết
        LichTrinhService ltService = new LichTrinhService();
        VeXeService vxService = new VeXeService(); // Chính là class này

        try {
            System.out.println("====== BẮT ĐẦU TEST ======");

            // ---------------------------------------------------------
            // TEST 1: TÌM CHUYẾN XE (Ngày 30/12/2025)
            // ---------------------------------------------------------
            System.out.println("\n--- 1. TEST TÌM CHUYẾN XE ---");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date ngayDi = sdf.parse("30/12/2025"); // Ngày khớp với DB

            // Gọi sang LichTrinhService để tìm
            List<LichTrinh> listChuyen = ltService.timChuyenXe("Hà Nội", "Đà Nẵng", ngayDi);

            if (listChuyen.isEmpty()) {
                System.out.println("❌ Không tìm thấy chuyến nào! (Kiểm tra lại DB hoặc ngày tháng)");
                return; // Dừng luôn
            } else {
                System.out.println("✅ Tìm thấy " + listChuyen.size() + " chuyến xe:");
                for (LichTrinh lt : listChuyen) {
                    System.out.println("   -> ID: " + lt.getLichId()
                            + " | Giờ: " + lt.getThoiGianKhoiHanh()
                            + " | Ghế trống: " + lt.getSoGheTrong()
                            + " | Giá: " + lt.getGiaVe());
                }
            }

            // Lấy ID của chuyến đầu tiên để test (thường là ID 1)
            int lichIdTest = listChuyen.get(0).getLichId();
            System.out.println("=> Chọn chuyến ID = " + lichIdTest + " để test tiếp.");

            // ---------------------------------------------------------
            // TEST 2: KIỂM TRA GHẾ ĐÃ ĐẶT TRƯỚC KHI MUA
            // ---------------------------------------------------------
            System.out.println("\n--- 2. DANH SÁCH GHẾ ĐÃ CÓ NGƯỜI ĐẶT ---");
            List<String> gheDaDat = vxService.getGheDaDat(lichIdTest);
            System.out.println("Các ghế đang bận: " + gheDaDat);
            // Kỳ vọng: [A01, A02] (nếu bạn đã chạy script SQL mẫu của mình)

            // ---------------------------------------------------------
            // TEST 3: THỬ ĐẶT VÉ
            // ---------------------------------------------------------
            System.out.println("\n--- 3. TIẾN HÀNH ĐẶT VÉ MỚI ---");
            int userId = 2; // Giả sử người dùng ID 2 (Khách hàng)
            String gheMuonDat = "B05"; // Ghế muốn test

            System.out.println("Đang đặt ghế " + gheMuonDat + "...");
            boolean ketQua = vxService.datVe(userId, lichIdTest, gheMuonDat);

            if (ketQua) {
                System.out.println("✅ Đặt vé THÀNH CÔNG!");
            } else {
                System.out.println("❌ Đặt vé THẤT BẠI (Có thể ghế trùng hoặc lỗi DB)");
            }

            // ---------------------------------------------------------
            // TEST 4: KIỂM TRA LẠI SAU KHI ĐẶT
            // ---------------------------------------------------------
            System.out.println("\n--- 4. KIỂM TRA LẠI DỮ LIỆU ---");
            List<String> gheMoi = vxService.getGheDaDat(lichIdTest);
            System.out.println("Danh sách ghế bận mới: " + gheMoi);

            if (gheMoi.contains(gheMuonDat)) {
                System.out.println("=> Test chuẩn: Ghế " + gheMuonDat + " đã xuất hiện trong danh sách bận.");
            } else {
                System.out.println("=> Test sai: Đặt rồi mà không thấy ghế đâu!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}