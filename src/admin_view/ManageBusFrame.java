package admin_view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import model.Xe;
import service.XeService;
public class ManageBusFrame extends JFrame {
    private JPanel MainPanel;
    private JPanel ManageBus;
    private JTable tblBusList;
    private JPanel panelGhe;
    private JPanel panel16;
    private JPanel panel30;
    private JComboBox cboLoaiXe;
    private JTextField txtBienSo;
    private JTextField txtIDxe;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnLoad;
    private JButton btnBack;
    private JTextField txtSoGhe;
    private JTextField txtID;
    private JButton btnTimkiem;
    private JTextField txtTenLoaiXe;
    private DefaultTableModel model;
    private String currentAction = "";
    private XeService xeService = new XeService();
    public ManageBusFrame() {
        UIManager.put("ComboBox.disabledForeground", new Color(80, 80, 80));
        UIManager.put("ComboBox.disabledBackground", new Color(245, 245, 245));
        setTitle("Quản lý xe");
        setContentPane(MainPanel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComboBoxData();
        initTable();
        resetToDefault();
        btnTimkiem.addActionListener(e -> {
            String idSearch = txtID.getText().trim();
            if (idSearch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập ID để tìm!");
                return;
            }
            Xe found = null;
            for (Xe x : xeService.getAll()) {
                if (String.valueOf(x.getXeId()).equals(idSearch)) {
                    found = x;
                    break;
                }
            }
            if (found != null) {
                txtIDxe.setText(String.valueOf(found.getXeId()));
                txtBienSo.setText(found.getBienSo());
                txtSoGhe.setText(String.valueOf(found.getTongGhe()));
                txtTenLoaiXe.setText(found.getTenXe());
                cboLoaiXe.setSelectedItem(found.getLoaiXe());
                for (int i = 0; i < tblBusList.getRowCount(); i++) {
                    if (tblBusList.getValueAt(i, 0).toString().equals(idSearch)) {
                        tblBusList.setRowSelectionInterval(i, i);
                        break;
                    }
                }
                disableFields();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy xe ID: " + idSearch);
                resetToDefault();
            }
        });
        cboLoaiXe.addActionListener(e -> {
            if ("ADD".equals(currentAction)) {
                String selected = (String) cboLoaiXe.getSelectedItem();
                if ("Limousine".equals(selected)) txtSoGhe.setText("16");
                else if ("Giường nằm".equals(selected)) txtSoGhe.setText("30");
            }
        });
        btnAdd.addActionListener(e -> {
            clearFields();
            enableFieldsForEditing();
            txtIDxe.setText("");
            currentAction = "ADD";
            txtTenLoaiXe.requestFocus();
        });
        btnDelete.addActionListener(e -> {
            String idStr = txtIDxe.getText().trim();
            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn xe cần xóa!");
                return;
            }
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá xe ID: " + idStr + " ?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    int id = Integer.parseInt(idStr);
                    JOptionPane.showMessageDialog(this, xeService.xoa(id));
                    resetToDefault();
                    loadDataFromDatabase();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage());
                }
            }
        });
        btnUpdate.addActionListener(e -> {
            if (!txtIDxe.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Lỗi!");
                return;
            }
            try {
                String ten = txtTenLoaiXe.getText().trim();
                String bienSo = txtBienSo.getText().trim();
                String loai = (String) cboLoaiXe.getSelectedItem();

                if (ten.isEmpty() || bienSo.isEmpty() || loai == null) {
                    JOptionPane.showMessageDialog(this, "Không đủ điều kiện hoặc không có gì để Lưu!");
                    return;
                }
                Xe x = new Xe(0, ten, bienSo, Integer.parseInt(txtSoGhe.getText()), loai);
                JOptionPane.showMessageDialog(this, xeService.them(x));
                resetToDefault();
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi nhập liệu!");
            }
        });
        btnLoad.addActionListener(e -> {
            loadDataFromDatabase();
            resetToDefault();
            JOptionPane.showMessageDialog(this, "Làm mới thành công!");
        });

        btnBack.addActionListener(e -> {
            new AdminMainFrame().setVisible(true);
            dispose();
        });
    }
    private void resetToDefault() {
        clearFields();
        disableFields();
        currentAction = "";
    }
    private void clearFields() {
        txtIDxe.setText("");
        txtBienSo.setText("");
        txtSoGhe.setText("");
        txtID.setText("");
        txtTenLoaiXe.setText("");
        cboLoaiXe.setSelectedIndex(-1);
    }
    private void disableFields() {
        txtIDxe.setEditable(false);
        txtBienSo.setEditable(false);
        txtSoGhe.setEditable(false);
        txtTenLoaiXe.setEditable(false);
        cboLoaiXe.setEnabled(false);
        txtIDxe.setBackground(new Color(230, 230, 230));
        txtSoGhe.setBackground(new Color(230, 230, 230));
        txtTenLoaiXe.setBackground(new Color(230, 230, 230));
    }
    private void enableFieldsForEditing() {
        txtBienSo.setEditable(true);
        txtTenLoaiXe.setEditable(true);
        txtBienSo.setBackground(Color.WHITE);
        txtTenLoaiXe.setBackground(Color.WHITE);
        cboLoaiXe.setEnabled(true);
    }
    private void loadDataFromDatabase() {
        model.setRowCount(0);
        for (Xe x : xeService.getAll()) {
            model.addRow(new Object[]{x.getXeId(), x.getLoaiXe(), x.getTenXe(), x.getBienSo(), x.getTongGhe()});
        }
    }
    private void initTable() {
        model = new DefaultTableModel(new String[]{"ID xe", "Loại xe", "Tên loại xe", "Biển số", "Số ghế"}, 0);
        tblBusList.setModel(model);
        tblBusList.setDefaultEditor(Object.class, null);
        loadDataFromDatabase();
    }
    private void initComboBoxData() {
        cboLoaiXe.removeAllItems();
        cboLoaiXe.addItem("Limousine");
        cboLoaiXe.addItem("Giường nằm");
    }
}
