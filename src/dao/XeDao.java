package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Xe;

public class XeDao {

    public List<Xe> getAll() {
        List<Xe> list = new ArrayList<>();
        String sql = "SELECT * FROM xe";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Xe xe = new Xe(
                        rs.getInt("xeid"),
                        rs.getString("tenxe"),
                        rs.getString("bienso"),
                        rs.getInt("tongghe"),
                        rs.getString("loaixe")
                );
                list.add(xe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Xe xe) {
        String sql = "INSERT INTO xe(tenxe, bienso, tongghe, loaixe) VALUES(?,?,?,?)";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, xe.getTenXe());
            ps.setString(2, xe.getBienSo());
            ps.setInt(3, xe.getTongGhe());
            ps.setString(4, xe.getLoaiXe());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Xe xe) {
        // SỬA: Update đầy đủ thông tin và đúng tên cột
        String sql = "UPDATE xe SET tenxe=?, bienso=?, tongghe=?, loaixe=? WHERE xeid=?";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, xe.getTenXe());
            ps.setString(2, xe.getBienSo());
            ps.setInt(3, xe.getTongGhe());
            ps.setString(4, xe.getLoaiXe());
            ps.setInt(5, xe.getXeId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int xeId) {
        String sql = "DELETE FROM xe WHERE xeid=?";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, xeId);
            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {

            System.out.println("Xe này đang có lịch trình, không thể xóa!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Xe getById(int id) {
        String sql = "SELECT * FROM xe WHERE xeid = ?";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Tạo đối tượng Xe từ dữ liệu tìm thấy
                    return new Xe(
                            rs.getInt("xeid"),
                            rs.getString("tenxe"),
                            rs.getString("bienso"),
                            rs.getInt("tongghe"),
                            rs.getString("loaixe")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

}