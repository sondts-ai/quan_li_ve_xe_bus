package dao;

import model.TuyenXe;
import model.Xe;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static dao.Connectdb.getConnection;

public class XeDao {
    String url = "jdbc:sqlserver://localhost;"
            + "databaseName=xekhach;"
            + "integratedSecurity=true;"
            + "trustServerCertificate=true";

    private void loadData() {
        // chưa có model

//        model.setRowCount(0);
//        try (Connection conn = getConnection();
//             Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery("SELECT * FROM xe")) {
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("xeid"),
//                        rs.getString("tenxe"),
//                        rs.getString("bienso"),
//                        rs.getInt("tongghe"),
//                        rs.getString("loaixe")
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

    // thêm Xe
    private void themXe(Xe xe) {
        String sql = "INSERT INTO xe VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, xe.getXeId());
            ps.setString(2, xe.getTenXe());
            ps.setString(3, xe.getBienSo());
            ps.setInt(4, xe.getTongGhe());
            ps.setString(5, xe.getLoaiXe());

            ps.executeUpdate();
            loadData();
            clearForm();
        } catch (Exception e) {
            showError(e);
        }
    }

    // Sửa Xe
    private void suaXe(Xe xe) {
        String sql = "UPDATE xe SET tenxe=?, bienso=?, tongghe=?, loaixe=? WHERE xeid=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, xe.getTenXe());
            ps.setString(2, xe.getBienSo());
            ps.setInt(3, xe.getTongGhe());
            ps.setString(4, xe.getLoaiXe());
            ps.setInt(5, xe.getXeId());

            ps.executeUpdate();
            loadData();
        } catch (Exception e) {
            showError(e);
        }
    }

    // Xóa xe
    private void xoaXe(Xe xe) {
        // chưa có JOptionPane

//        int confirm = JOptionPane.showConfirmDialog(this,
//                "Xóa xe này?", "Xác nhận",
//                JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            String sql = "DELETE FROM xe WHERE xeid=?";
//            try (Connection conn = getConnection();
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//                ps.setInt(1, xe.getXeId());
//                ps.executeUpdate();
//                loadData();
//                clearForm();
//            } catch (Exception e) {
//                showError(e);
//            }
//        }
    }
}
