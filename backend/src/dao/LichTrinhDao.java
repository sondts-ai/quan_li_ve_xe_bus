package dao;

import model.LichTrinh;
import model.TuyenXe;
import model.Xe;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static dao.Connectdb.getConnection;

public class LichTrinhDao {
    String url = "jdbc:sqlserver://localhost;"
            + "databaseName=xekhach;"
            + "integratedSecurity=true;"
            + "trustServerCertificate=true";

    private void loadData() {
        // chưa có model

//        model.setRowCount(0);
//        try (Connection conn = getConnection();
//             Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery("SELECT * FROM lichtrinh")) {
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("lichid"),
//                        rs.getInt("xeid"),
//                        rs.getInt("tuyenid"),
//                        rs.getTimestamp("thoigiankhoihanh"),
//                        rs.getTimestamp("thoigianden"),
//                        rs.getInt("soghetrong"),
//                        rs.getDouble("giave")
//                });
//            }
//        } catch (Exception e) {
//            showError(e);
//        }
    }

    private void clearForm() {
        // chưa có UI

//        txtId.setText("");
//        txtName.setText("");
//        txtAddress.setText("");
//        table.clearSelection();
    }

    private void showError(Exception e) {
        // chưa có JOptionPane

//        JOptionPane.showMessageDialog(this, e.getMessage(),
//                "Error", JOptionPane.ERROR_MESSAGE);
    }

    // thêm Lịch Trình
    private void themLichTrinh(LichTrinh lt) {
        String sql = "INSERT INTO lichtrinh VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lt.getLichId());
            ps.setInt(2, lt.getXeId());
            ps.setInt(3, lt.getTuyenId());
            ps.setTimestamp(4, lt.getThoiGianKhoiHanh());
            ps.setTimestamp(5, lt.getThoiGianDen());
            ps.setInt(6, lt.getSoGheTrong());
            ps.setDouble(7, lt.getSoGheTrong());

            ps.executeUpdate();
            loadData();
            clearForm();
        } catch (Exception e) {
            showError(e);
        }
    }

    // Sửa Lịch Trình
    private void suaLichTrinh(LichTrinh lt) {
        String sql = "UPDATE lichtrinh SET xeid=?, tuyenid=?, thoigiankhoihanh=?, thoigianden=?, soghetrong=?, giave=? WHERE lichid=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lt.getXeId());
            ps.setInt(2, lt.getTuyenId());
            ps.setTimestamp(3, lt.getThoiGianKhoiHanh());
            ps.setTimestamp(4, lt.getThoiGianDen());
            ps.setInt(5, lt.getSoGheTrong());
            ps.setDouble(6, lt.getSoGheTrong());
            ps.setInt(7, lt.getLichId());

            ps.executeUpdate();
            loadData();
        } catch (Exception e) {
            showError(e);
        }
    }
    

    // Xóa Lich Trình
    private void xoaLichTrinh(LichTrinh lt) {
        // chưa có JOptionPane

//        int confirm = JOptionPane.showConfirmDialog(this,
//                "Xóa lịch trình này?", "Xác nhận",
//                JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            String sql = "DELETE FROM lichtrinh WHERE lichid=?";
//            try (Connection conn = getConnection();
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//                ps.setInt(1, lt.getLichId());
//                ps.executeUpdate();
//                loadData();
//                clearForm();
//            } catch (Exception e) {
//                showError(e);
//            }
//        }
    }
}
