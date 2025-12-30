package admin_view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Set;

import model.TuyenXe;
import model.LichTrinh;
import model.Xe;
import service.TuyenXeService;
import service.LichTrinhService;
import service.XeService;
import service.VeXeService;

public class ManageRouteFrame extends JFrame {
    private JPanel MainPanel;
    private JTable tblRouteList;
    private JTextField txtMaTuyen;
    private JTextField txtDiemKhoiHanh;
    private JTextField txtKhoangCach;
    private JTextField txtTimeChay;
    private JPanel TieuDe;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnLoad;
    private JButton btnDelete;
    private JButton btnBack;
    private JTextField txtDiemden;
    private JTextField txtTongsoTuyen;
    private JComboBox cboDiemKhoiHanh;
    private JComboBox cboDiemden;
    private JComboBox cboIDTuyen;
    private JPanel panelTrong;
    private JPanel panel16;
    private JPanel panel30;
    private JButton btn16A1;
    private JButton btn16A2;
    private JButton btn16A3;
    private JButton btn16A4;
    private JButton btn16B1;
    private JButton btn16B4;
    private JButton btn16B3;
    private JButton btn16B2;
    private JButton btn16D1;
    private JButton btn16D2;
    private JButton btn16D3;
    private JButton btn16C4;
    private JButton btn16D4;
    private JButton btn16C3;
    private JButton btn16C2;
    private JButton btn16C1;
    private JButton btn30A1;
    private JButton btn30A2;
    private JButton btn30A3;
    private JButton btn30A4;
    private JButton btn30A5;
    private JButton btn30A6;
    private JButton btn30B1;
    private JButton btn30B2;
    private JButton btn30B3;
    private JButton btn30B4;
    private JButton btn30B5;
    private JButton btn30B6;
    private JButton btn30C1;
    private JButton btn30C2;
    private JButton btn30C3;
    private JButton btn30C4;
    private JButton btn30C5;
    private JButton btn30C6;
    private JButton btn30D6;
    private JButton btn30D5;
    private JButton btn30D4;
    private JButton btn30D3;
    private JButton btn30D2;
    private JButton btn30D1;
    private JButton btn30E1;
    private JButton btn30E2;
    private JButton btn30E3;
    private JButton btn30E4;
    private JButton btn30E5;
    private JButton btn30E6;

    private DefaultTableModel model;
    private TuyenXeService tuyenXeService = new TuyenXeService();
    private LichTrinhService lichTrinhService = new LichTrinhService();
    private XeService xeService = new XeService();
    private VeXeService veXeService = new VeXeService();

    public ManageRouteFrame() {
        setTitle("Quản lý tuyến xe");
        setContentPane(MainPanel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initTable();
        initComboBoxData();
        resetToDefault();

        cboDiemKhoiHanh.addActionListener(e -> filterRoutes());
        cboDiemden.addActionListener(e -> filterRoutes());

        cboIDTuyen.addActionListener(e -> {
            String selectedID = (String) cboIDTuyen.getSelectedItem();
            if (selectedID != null && !selectedID.equals("Chọn ID") && cboIDTuyen.isEnabled()) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equals(selectedID)) {
                        showDetail(i);
                        loadSeatMap(Integer.parseInt(selectedID));
                        break;
                    }
                }
            }
        });

        btnAdd.addActionListener(e -> {
            clearFields();
            enableFields(true);
            txtMaTuyen.setText("");
            txtDiemKhoiHanh.requestFocus();
            showPanel("cardTrong");
        });

        btnUpdate.addActionListener(e -> {
            try {
                String di = txtDiemKhoiHanh.getText().trim();
                String den = txtDiemden.getText().trim();
                float km = Float.parseFloat(txtKhoangCach.getText().trim());
                int phut = Integer.parseInt(txtTimeChay.getText().trim());

                if (txtMaTuyen.getText().equals("") || txtMaTuyen.getText().equals("Tự động")) {
                    TuyenXe tx = new TuyenXe(di, den, km, phut);
                    JOptionPane.showMessageDialog(this, tuyenXeService.them(tx));
                } else {
                    int id = Integer.parseInt(txtMaTuyen.getText());
                    TuyenXe tx = new TuyenXe(id, di, den, km, phut);
                    JOptionPane.showMessageDialog(this, tuyenXeService.capNhat(tx));
                }
                resetToDefault();
                loadDataFromDatabase();
                JOptionPane.showMessageDialog(this, "Dữ liệu đã lưu!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        });

        btnDelete.addActionListener(e -> {
            String idStr = txtMaTuyen.getText();
            if (idStr.isEmpty() || idStr.equals("Tự động")) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tuyến cần xóa!");
                return;
            }
            int choice = JOptionPane.showConfirmDialog(this, "Xóa trong DB tuyến ID " + idStr + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, tuyenXeService.xoa(Integer.parseInt(idStr)));
                resetToDefault();
                loadDataFromDatabase();
            }
        });

        btnLoad.addActionListener(e -> {
            loadDataFromDatabase();
            initComboBoxData();
            resetToDefault();
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thành công!");
        });

        btnBack.addActionListener(e -> {
            new AdminMainFrame().setVisible(true);
            dispose();
        });
    }

    private void loadSeatMap(int tuyenId) {
        List<LichTrinh> listLT = lichTrinhService.getLichTrinhByTuyenId(tuyenId);
        if (listLT.isEmpty()) {
            showPanel("cardTrong");
            return;
        }
        LichTrinh lt = listLT.get(0);
        Xe xe = xeService.getById(lt.getXeId());

        JPanel activePanel;
        if (xe != null && xe.getTongGhe() == 16) {
            showPanel("card16");
            activePanel = panel16;
        } else if (xe != null && xe.getTongGhe() == 30) {
            showPanel("card30");
            activePanel = panel30;
        } else {
            showPanel("cardTrong");
            return;
        }

        List<String> gheDaDat = veXeService.getGheDaDat(lt.getLichId());
        for (Component comp : activePanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                if (gheDaDat.contains(btn.getText())) {
                    btn.setBackground(Color.RED);
                } else {
                    btn.setBackground(Color.GREEN);
                }
            }
        }
    }

    private void showPanel(String cardName) {
        // Parent của panelTrong phải dùng CardLayout m nhé
        CardLayout cl = (CardLayout) (panelTrong.getParent().getLayout());
        cl.show(panelTrong.getParent(), cardName);
    }

    private void initTable() {
        String[] columns = {"Mã tuyến", "Điểm đi", "Điểm đến", "Khoảng cách (km)", "Thời gian (phút)"};
        model = new DefaultTableModel(columns, 0);
        tblRouteList.setModel(model);
        tblRouteList.setDefaultEditor(Object.class, null);
        loadDataFromDatabase();
    }

    private void initComboBoxData() {
        cboDiemKhoiHanh.setEnabled(false);
        cboDiemden.setEnabled(false);
        cboDiemKhoiHanh.removeAllItems();
        cboDiemden.removeAllItems();
        cboDiemKhoiHanh.addItem("");
        cboDiemden.addItem("");

        List<TuyenXe> list = tuyenXeService.getAll();
        Set<String> tinhThanh = new TreeSet<>();
        for (TuyenXe tx : list) {
            tinhThanh.add(tx.getKhoiHanh());
            tinhThanh.add(tx.getDiemDen());
        }
        for (String tinh : tinhThanh) {
            cboDiemKhoiHanh.addItem(tinh);
            cboDiemden.addItem(tinh);
        }
        cboDiemKhoiHanh.setEnabled(true);
        cboDiemden.setEnabled(true);
    }

    private void loadDataFromDatabase() {
        model.setRowCount(0);
        List<TuyenXe> list = tuyenXeService.getAll();
        for (TuyenXe tx : list) {
            model.addRow(new Object[]{tx.getTuyenId(), tx.getKhoiHanh(), tx.getDiemDen(), tx.getKhoangCach(), tx.getThoiGianDiChuyen()});
        }
        updateTotalCount();
    }

    private void filterRoutes() {
        String di = (String) cboDiemKhoiHanh.getSelectedItem();
        String den = (String) cboDiemden.getSelectedItem();
        if (di == null || di.isEmpty() || den == null || den.isEmpty()) return;
        List<Integer> matchingRows = new ArrayList<>();
        cboIDTuyen.removeAllItems();
        cboIDTuyen.addItem("Chọn ID");

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).toString().equals(di) && model.getValueAt(i, 2).toString().equals(den)) {
                matchingRows.add(i);
                cboIDTuyen.addItem(model.getValueAt(i, 0).toString());
            }
        }

        if (matchingRows.isEmpty()) {
            clearFields();
            showPanel("cardTrong");
            cboIDTuyen.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Hiện tại không có tuyến xe nào!");
        } else if (matchingRows.size() == 1) {
            showDetail(matchingRows.get(0));
            cboIDTuyen.setEnabled(false);
            loadSeatMap(Integer.parseInt(model.getValueAt(matchingRows.get(0), 0).toString()));
        } else {
            clearFields();
            showPanel("cardTrong");
            cboIDTuyen.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Có nhiều tuyến trùng lộ trình, vui lòng chọn ID cụ thể!");
        }
    }

    private void showDetail(int rowIndex) {
        txtMaTuyen.setText(model.getValueAt(rowIndex, 0).toString());
        txtDiemKhoiHanh.setText(model.getValueAt(rowIndex, 1).toString());
        txtDiemden.setText(model.getValueAt(rowIndex, 2).toString());
        txtKhoangCach.setText(model.getValueAt(rowIndex, 3).toString());
        txtTimeChay.setText(model.getValueAt(rowIndex, 4).toString());
        tblRouteList.setRowSelectionInterval(rowIndex, rowIndex);
        enableFields(false);
    }

    private void resetToDefault() {
        clearFields();
        disableFields();
        showPanel("cardTrong");
        cboDiemKhoiHanh.setSelectedIndex(0);
        cboDiemden.setSelectedIndex(0);
        cboIDTuyen.setEnabled(false);
    }

    private void clearFields() {
        txtMaTuyen.setText(""); txtDiemKhoiHanh.setText("");
        txtDiemden.setText(""); txtKhoangCach.setText(""); txtTimeChay.setText("");
    }

    private void disableFields() { enableFields(false); }

    private void enableFields(boolean isEnabled) {
        txtDiemKhoiHanh.setEditable(isEnabled);
        txtDiemden.setEditable(isEnabled);
        txtKhoangCach.setEditable(isEnabled);
        txtTimeChay.setEditable(isEnabled);
        Color bg = isEnabled ? Color.WHITE : new Color(240, 240, 240);
        txtDiemKhoiHanh.setBackground(bg);
        txtDiemden.setBackground(bg);
        txtKhoangCach.setBackground(bg);
        txtTimeChay.setBackground(bg);
    }

    private void updateTotalCount() {
        txtTongsoTuyen.setText(String.valueOf(model.getRowCount()));
    }
}
