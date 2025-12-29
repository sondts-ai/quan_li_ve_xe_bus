package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.VeXeTriTiet;

public class VeXeDao {


    public boolean datVe(int nguoiDungId, int lichId, String viTriGhe) {

        String checkGheSql = "SELECT 1 FROM vexe WHERE lichid=? AND vitrighe=? AND trangthai=N'đã đặt'";
        String insertVeSql = "INSERT INTO vexe(nguoidungid, lichid, vitrighe, trangthai) VALUES(?, ?, ?, N'đã đặt')";
        String updateGhe = "UPDATE lichtrinh SET soghetrong=soghetrong-1 WHERE lichid=? AND soghetrong>0";

        Connection conn = null;

        try {
            conn = Connectdb.getConnection();
            if (conn == null) {
                return false;
            }
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(checkGheSql)) {
                ps.setInt(1, lichId);
                ps.setString(2, viTriGhe);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(updateGhe)) {
                ps.setInt(1, lichId);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(insertVeSql)) {
                ps.setInt(1, nguoiDungId);
                ps.setInt(2, lichId);
                ps.setString(3, viTriGhe);
                ps.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean huyVe(int veId) {

        String getLichSql = "SELECT lichid FROM vexe WHERE veid=? AND trangthai=N'đã đặt'";
        String updateVeSql = "UPDATE vexe SET trangthai=N'đã huỷ' WHERE veid=?";
        String updateGheSql = "UPDATE lichtrinh SET soghetrong=soghetrong+1 WHERE lichid=?";

        Connection conn = null;
        try {
            conn = Connectdb.getConnection();
            conn.setAutoCommit(false);

            int lichId;
            try (PreparedStatement ps = conn.prepareStatement(getLichSql)) {
                ps.setInt(1, veId);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    conn.rollback();
                    return false;
                }
                lichId = rs.getInt("lichid");
            }

            try (PreparedStatement ps = conn.prepareStatement(updateVeSql)) {
                ps.setInt(1, veId);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = conn.prepareStatement(updateGheSql)) {
                ps.setInt(1, lichId);
                ps.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<String> getGheDaDat(int lichId) {
        List<String> dsGhe = new ArrayList<>();

        String sql = "SELECT vitrighe FROM vexe WHERE lichid=? AND trangthai=N'đã đặt'";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lichId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dsGhe.add(rs.getString("vitrighe"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsGhe;
    }

    public List<VeXeTriTiet> getLichSuDatVe(int nguoiDungId) {
        List<VeXeTriTiet> list = new ArrayList<>();

        // Câu lệnh SQL nối 3 bảng để lấy thông tin chi tiết
        String sql = """
            SELECT v.veid, 
                   tx.khoihanh + ' - ' + tx.diemden AS tentuyen, 
                   lt.thoigiankhoihanh, 
                   v.vitrighe, 
                   lt.giave, 
                   v.thoigiandat, 
                   v.trangthai
            FROM vexe v
            JOIN lichtrinh lt ON v.lichid = lt.lichid
            JOIN tuyenxe tx ON lt.tuyenid = tx.tuyenid
            WHERE v.nguoidungid = ?
            ORDER BY v.thoigiandat DESC
        """;
        // ORDER BY DESC để vé mới nhất hiện lên đầu

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nguoiDungId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VeXeTriTiet item = new VeXeTriTiet();
                item.setVeId(rs.getInt("veid"));
                item.setTenTuyen(rs.getString("tentuyen"));
                item.setThoiGianKhoiHanh(rs.getTimestamp("thoigiankhoihanh")); // Dùng getTimestamp để lấy cả giờ
                item.setViTriGhe(rs.getString("vitrighe"));
                item.setGiaVe(rs.getDouble("giave"));
                item.setThoiGianDat(rs.getTimestamp("thoigiandat"));
                item.setTrangThai(rs.getString("trangthai")); // Đã đặt / Đã huỷ

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}