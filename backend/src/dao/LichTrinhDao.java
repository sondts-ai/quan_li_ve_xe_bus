package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.LichTrinh;

public class LichTrinhDao {

    public List<LichTrinh> getAll() {
        List<LichTrinh> list = new ArrayList<>();
        String sql = "SELECT * FROM lichtrinh";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LichTrinh lt = new LichTrinh();
                lt.setLichId(rs.getInt("lichid"));
                lt.setXeId(rs.getInt("xeid"));
                lt.setTuyenId(rs.getInt("tuyenid"));

                lt.setThoiGianKhoiHanh(rs.getTimestamp("thoigiankhoihanh"));
                lt.setThoiGianDen(rs.getTimestamp("thoigianden"));

                lt.setSoGheTrong(rs.getInt("soghetrong"));

                lt.setGiaVe(rs.getDouble("giave"));

                list.add(lt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(LichTrinh lt) {
        String sql = "INSERT INTO lichtrinh(xeid, tuyenid, thoigiankhoihanh, thoigianden, soghetrong, giave) VALUES(?,?,?,?,?,?)";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lt.getXeId());
            ps.setInt(2, lt.getTuyenId());


            ps.setTimestamp(3, new Timestamp(lt.getThoiGianKhoiHanh().getTime()));
            ps.setTimestamp(4, new Timestamp(lt.getThoiGianDen().getTime()));

            ps.setInt(5, lt.getSoGheTrong());

            ps.setDouble(6, lt.getGiaVe());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LichTrinh lt) {
        String sql = "UPDATE lichtrinh SET xeid=?, tuyenid=?, thoigiankhoihanh=?, thoigianden=?, soghetrong=?, giave=? WHERE lichid=?";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lt.getXeId());
            ps.setInt(2, lt.getTuyenId());
            ps.setTimestamp(3, new Timestamp(lt.getThoiGianKhoiHanh().getTime()));
            ps.setTimestamp(4, new Timestamp(lt.getThoiGianDen().getTime()));
            ps.setInt(5, lt.getSoGheTrong());

            ps.setDouble(6, lt.getGiaVe());

            ps.setInt(7, lt.getLichId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int lichId) {
        String sql = "DELETE FROM lichtrinh WHERE lichid=?";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lichId);
            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Lỗi: Lịch trình này đã có vé đặt, không thể xóa!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<LichTrinh> timChuyenXe(String khoiHanh, String diemDen, java.util.Date ngay) {
        List<LichTrinh> list = new ArrayList<>();

        String sql = """
        SELECT lt.*
        FROM lichtrinh lt
        JOIN tuyenxe tx ON lt.tuyenid = tx.tuyenid
        WHERE tx.khoihanh = ?
          AND tx.diemden = ?
          AND CAST(lt.thoigiankhoihanh AS DATE) = ?
          AND lt.soghetrong > 0
    """;

        try (Connection c = Connectdb.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, khoiHanh);
            ps.setString(2, diemDen);
            // FIX: Chuyển đổi util.Date sang sql.Date để tránh lỗi
            ps.setDate(3, new java.sql.Date(ngay.getTime()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichTrinh lt = new LichTrinh();
                lt.setLichId(rs.getInt("lichid"));
                lt.setXeId(rs.getInt("xeid"));
                lt.setTuyenId(rs.getInt("tuyenid"));
                lt.setThoiGianKhoiHanh(rs.getTimestamp("thoigiankhoihanh"));
                lt.setThoiGianDen(rs.getTimestamp("thoigianden"));
                lt.setSoGheTrong(rs.getInt("soghetrong"));
                lt.setGiaVe(rs.getDouble("giave"));
                list.add(lt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

