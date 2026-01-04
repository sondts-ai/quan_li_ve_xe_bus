package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.TuyenXe;
public class TuyenXeDao {
    public List<TuyenXe> getAll() {
        List<TuyenXe> list = new ArrayList<>();
        String sql = "SELECT * FROM tuyenxe";
        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TuyenXe tx = new TuyenXe(
                        rs.getInt("tuyenid"),
                        rs.getString("khoihanh"),
                        rs.getString("diemden"),
                        rs.getFloat("khoangcach"),
                        rs.getInt("thoigiandichuyen")
                );
                list.add(tx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean insert(TuyenXe tx) {
        String sql = "INSERT INTO tuyenxe(khoihanh, diemden, khoangcach, thoigiandichuyen) VALUES(?,?,?,?)";
        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tx.getKhoiHanh());
            ps.setString(2, tx.getDiemDen());
            ps.setFloat(3, tx.getKhoangCach());
            ps.setInt(4, tx.getThoiGianDiChuyen());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean update(TuyenXe tx) {
        String sql = "UPDATE tuyenxe SET khoihanh=?, diemden=?, khoangcach=?, thoigiandichuyen=? WHERE tuyenid=?";
        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tx.getKhoiHanh());
            ps.setString(2, tx.getDiemDen());
            ps.setFloat(3, tx.getKhoangCach());
            ps.setInt(4, tx.getThoiGianDiChuyen());
            ps.setInt(5, tx.getTuyenId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(int tuyenId) {
        String sql = "DELETE FROM tuyenxe WHERE tuyenid=?";
        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tuyenId);
            return ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Lỗi: Tuyến xe này đang có lịch trình, không thể xóa!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}