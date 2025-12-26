package dao;

import model.TuyenXe;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static dao.Connectdb.getConnection;

public class TuyenXeDao {
     String url = "jdbc:sqlserver://localhost;"
            + "databaseName=xekhach;"
            + "integratedSecurity=true;"
            + "trustServerCertificate=true";

    private void loadData() {
        // chưa có model

//        model.setRowCount(0);
//        try (Connection conn = getConnection();
//             Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery("SELECT * FROM xekhach")) {
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("tuyenid"),
//                        rs.getString("khoihanh"),
//                        rs.getString("diemden"),
//                        rs.getFloat("khoangcach"),
//                        rs.getInt("thoigiandichuyen")
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

    // thêm Tuyến Xe
    private void themTuyenXe(TuyenXe tx) {
        String sql = "INSERT INTO tuyenxe VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tx.getTuyenId());
            ps.setString(2, tx.getKhoiHanh());
            ps.setString(3, tx.getDiemDen());
            ps.setFloat(4, tx.getKhoangCach());
            ps.setInt(5, tx.getThoiGianDiChuyen());

            ps.executeUpdate();
            loadData();
            clearForm();
        } catch (Exception e) {
            showError(e);
        }
    }

    // Sửa Tuyến xe
    private void suaTuyenXe(TuyenXe tx) {
        String sql = "UPDATE tuyenxe SET khoihanh=?, diemden=?, khoangcach=?, thoigiandichuyen=? WHERE tuyenid=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tx.getKhoiHanh());
            ps.setString(2, tx.getDiemDen());
            ps.setFloat(3, tx.getKhoangCach());
            ps.setInt(4, tx.getThoiGianDiChuyen());
            ps.setInt(5, tx.getTuyenId());

            ps.executeUpdate();
            loadData();
        } catch (Exception e) {
            showError(e);
        }
    }

    // Xóa tuyến xe
    private void xoaTuyenXe(TuyenXe tx) {
        // chưa có JOptionPane

//        int confirm = JOptionPane.showConfirmDialog(this,
//                "Xóa tuyến xe này?", "Xác nhận",
//                JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            String sql = "DELETE FROM tuyenxe WHERE tuyenid=?";
//            try (Connection conn = getConnection();
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//                ps.setInt(1, tx.getTuyenId());
//                ps.executeUpdate();
//                loadData();
//                clearForm();
//            } catch (Exception e) {
//                showError(e);
//            }
//        }
    }

}
