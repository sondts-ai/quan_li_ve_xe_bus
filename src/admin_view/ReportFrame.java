package admin_view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import dao.Connectdb;

public class ReportFrame extends JFrame {
    private JPanel MainPanel;
    private JTable tblDoanhThuList;
    private JComboBox cboNgay;
    private JComboBox cboThang;
    private JButton btnThongKe;
    private JTextField txtTongVe;
    private JTextField txtDoanhthuTrungBinh;
    private JTextField txtTongDoanhThu;
    private JTextField txtTuyenxeHot;
    private JButton btnBack;
    private DefaultTableModel model;


    public ReportFrame() {
        setTitle("Quản lý doanh thu");
        setContentPane(MainPanel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComboBox();
        initTable();
        initFieldSettings();
        loadOverallTotal();
        btnThongKe.addActionListener(e -> loadFilteredData());
        btnBack.addActionListener(e -> {
            new AdminMainFrame().setVisible(true);
            dispose();
        });
    }

    private void initComboBox() {
        cboNgay.addItem("Ngày");
        for (int i = 1; i <= 31; i++) cboNgay.addItem(String.valueOf(i));
        cboThang.addItem("Tháng");
        for (int i = 1; i <= 12; i++) cboThang.addItem(String.valueOf(i));
    }

    private void initTable() {
        String[] columns = {"Mã vé", "Tuyến đường", "Giá vé (VNĐ)", "Thời gian đặt"};
        model = new DefaultTableModel(columns, 0);
        tblDoanhThuList.setModel(model);
    }

    private void initFieldSettings() {
        txtTongVe.setEditable(false);
        txtDoanhthuTrungBinh.setEditable(false);
        txtTongDoanhThu.setEditable(false);
        txtTuyenxeHot.setEditable(false);
    }

    private void loadOverallTotal() {
        String sql = "SELECT SUM(l.giave), FROM vexe v JOIN lichtrinh l ON v.lichid = l.lichid";
        try (Connection conn = Connectdb.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                txtTongDoanhThu.setText(String.format("%,.0f", rs.getDouble(1)) + " VNĐ");
            }
        } catch (Exception ex) {
            txtTongDoanhThu.setText("0 VNĐ");
        }
    }

    private void loadFilteredData() {
        String selNgay = (String) cboNgay.getSelectedItem();
        String selThang = (String) cboThang.getSelectedItem();
        if (selThang.equals("Tháng") || selNgay.equals("Ngày")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ thời gian!");
            return;
        }
        model.setRowCount(0);
        double subTotal = 0;
        int count = 0;
        Map<String, Integer> routeCount = new HashMap<>();
        String sql = "SELECT v.veid, t.khoihanh, t.diemden, l.giave, v.thoigiandat " +
                "FROM vexe v " +
                "JOIN lichtrinh l ON v.lichid = l.lichid " +
                "JOIN tuyenxe t ON l.tuyenid = t.tuyenid " +
                "WHERE MONTH(v.thoigiandat) = ?";
        if (!selNgay.equals("Ngày")) {
            sql += " AND DAY(v.thoigiandat) = ?";
        }
        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(selThang));
            if (!selNgay.equals("Ngày")) {
                ps.setInt(2, Integer.parseInt(selNgay));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("veid");
                String tuyen = rs.getString("khoihanh") + " - " + rs.getString("diemden");
                double gia = rs.getDouble("giave");
                String ngay = rs.getString("thoigiandat");
                model.addRow(new Object[]{id, tuyen, gia, ngay});
                subTotal += gia;
                count++;
                routeCount.put(tuyen, routeCount.getOrDefault(tuyen, 0) + 1);
            }
            txtTongVe.setText(String.valueOf(count));
            txtDoanhthuTrungBinh.setText(String.format("%,.0f", subTotal) + " VNĐ");
            if (count > 0) {
                String hotRoute = "";
                int max = 0;
                for (Map.Entry<String, Integer> entry : routeCount.entrySet()) {
                    if (entry.getValue() > max) {
                        max = entry.getValue();
                        hotRoute = entry.getKey();
                    }
                }
                txtTuyenxeHot.setText(hotRoute + " (" + max + " vé)");
            } else {
                txtTuyenxeHot.setText("Không có dữ liệu");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối: " + ex.getMessage());
        }
    }

}
