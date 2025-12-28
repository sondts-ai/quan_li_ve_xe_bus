package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ThongKe;

public class ThongKeDao {


    public List<ThongKe> getDoanhThuTheoTuyen() {
        List<ThongKe> list = new ArrayList<>();


        String sql = "SELECT " +
                "t.khoihanh + ' - ' + t.diemden AS TenTuyen, " +
                "l.thoigiankhoihanh, " +
                "COUNT(v.veid) AS SoVeDaBan, " +
                "COALESCE(SUM(l.giave), 0) AS TongDoanhThu " +
                "FROM vexe v " +
                "JOIN lichtrinh l ON v.lichid = l.lichid " +
                "JOIN tuyenxe t ON l.tuyenid = t.tuyenid " +
                "WHERE v.trangthai = N'đã đặt' " +
                "GROUP BY t.khoihanh, t.diemden, l.thoigiankhoihanh " +
                "ORDER BY l.thoigiankhoihanh DESC";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setTenTuyen(rs.getString("TenTuyen"));
                // Chuyển timestamp thành String hiển thị cho đẹp
                tk.setNgayKhoiHanh(rs.getDate("thoigiankhoihanh"));
                tk.setSoVeDaBan(rs.getInt("SoVeDaBan"));
                tk.setDoanhThu(rs.getDouble("TongDoanhThu"));

                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public double getTongDoanhThuThang(int thang, int nam) {
        double tongTien = 0;
        String sql = "SELECT SUM(l.giave) FROM vexe v " +
                "JOIN lichtrinh l ON v.lichid = l.lichid " +
                "WHERE MONTH(l.thoigiankhoihanh) = ? " +
                "AND YEAR(l.thoigiankhoihanh) = ? " +
                "AND v.trangthai = N'đã đặt'";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, thang);
            ps.setInt(2, nam);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tongTien = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongTien;
    }

    public List<ThongKe> getDoanhThuTungThangTrongNam(int nam) {
        List<ThongKe> list = new ArrayList<>();

        // Query này sẽ nhóm doanh thu theo từng tháng
        String sql = "SELECT MONTH(l.thoigiankhoihanh) AS Thang, " +
                "COUNT(v.veid) AS SoVe, " +
                "COALESCE(SUM(l.giave), 0) AS TongTien " +
                "FROM vexe v " +
                "JOIN lichtrinh l ON v.lichid = l.lichid " +
                "WHERE YEAR(l.thoigiankhoihanh) = ? AND v.trangthai = N'đã đặt' " +
                "GROUP BY MONTH(l.thoigiankhoihanh) " +
                "ORDER BY Thang ASC";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nam);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                ThongKe tk = new ThongKe();

                tk.setTenTuyen("Tháng " + rs.getInt("Thang"));
                tk.setSoVeDaBan(rs.getInt("SoVe"));
                tk.setDoanhThu(rs.getDouble("TongTien"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}