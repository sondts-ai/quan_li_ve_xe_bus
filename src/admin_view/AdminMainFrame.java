package admin_view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import app.Main;
import service.NguoiDungService;
import model.NguoiDung;
public class AdminMainFrame extends JFrame{
    private JPanel MainPanel;
    private JPanel adminPanel;
    private JTable tblUserList;
    private JTextField txtID;
    private JButton btnOpenManageBus;
    private JButton btnOpenManageRoute;
    private JButton btnOpenReport;
    private JButton btnTimkiem;
    private JTextField txtSoLuong;
    private JTextField txtHoTen;
    private JTextField txtNgaySinh;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtTaiKhoan;
    private JPasswordField txtPassword;
    private JTextField txtVaitro;
    private JButton button_logout;
    private DefaultTableModel model;
    private NguoiDungService service = new NguoiDungService();
    public AdminMainFrame() {
        setTitle("Hệ thống Quản lý người dùng");
        setContentPane(MainPanel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initTable();
        disableFields();
        loadDataToTable();
        btnTimkiem.addActionListener(e -> {
            String idStr = txtID.getText().trim();
            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập ID để tìm!");
                return;
            }
            try {
                int id = Integer.parseInt(idStr);
                NguoiDung nd = service.layThongTinTheoId(id);
                if (nd != null) {
                    txtHoTen.setText(nd.getHoTen());
                    txtNgaySinh.setText(nd.getNgaySinh() != null ? nd.getNgaySinh().toString() : "");
                    txtSDT.setText(nd.getSdt());
                    txtEmail.setText(nd.getEmail());
                    txtTaiKhoan.setText(nd.getTaiKhoan());
                    txtPassword.setText(nd.getMatKhau());
                    txtVaitro.setText(nd.getVaiTro());

                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, 0).toString().equals(idStr)) {
                            tblUserList.setRowSelectionInterval(i, i);
                            break;
                        }
                    }
                } else {
                    clearDetails();
                    JOptionPane.showMessageDialog(this, "Không tìm thấy người dùng có ID là: " + idStr);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID phải là số nguyên!");
            }
        });
        btnOpenManageBus.addActionListener(e -> {
            new ManageBusFrame().setVisible(true);
            dispose();
        });
        btnOpenManageRoute.addActionListener(e -> {
            new ManageRouteFrame().setVisible(true);
            dispose();
        });
        btnOpenReport.addActionListener(e -> {
            new ReportFrame().setVisible(true);
            dispose();
        });
        button_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // đóng Admin
                new Main().khoiTaoGiaoDien();
            }
        });
    }
    private void loadDataToTable() {
        model.setRowCount(0);
        List<NguoiDung> dsnd = service.layTatCaNguoiDung();
        for (NguoiDung nd : dsnd) {
            model.addRow(new Object[]{
                    nd.getNguoiDungId(),
                    nd.getHoTen(),
                    nd.getNgaySinh(),
                    nd.getSdt(),
                    nd.getEmail(),
                    nd.getTaiKhoan(),
                    nd.getMatKhau(),
                    nd.getVaiTro()
            });
        }
        txtSoLuong.setText(String.valueOf(dsnd.size()));
    }
    private void initTable() {
        String[] columns = {"ID", "Họ tên", "Ngày sinh", "SĐT", "Email", "Tài khoản", "Mật khẩu", "Vai trò"};
        model = new DefaultTableModel(columns, 0);
        tblUserList.setModel(model);
        tblUserList.setDefaultEditor(Object.class, null);
    }
    private void disableFields() {
        JTextField[] fields = {txtSoLuong, txtHoTen, txtNgaySinh, txtSDT, txtEmail, txtTaiKhoan, txtVaitro};
        for (JTextField f : fields) {
            f.setEditable(false);
            f.setBackground(new Color(235, 235, 235));
        }
        txtPassword.setEditable(false);
        txtPassword.setBackground(new Color(235, 235, 235));
    }
    private void clearDetails() {
        txtHoTen.setText(""); txtNgaySinh.setText(""); txtSDT.setText("");
        txtEmail.setText(""); txtTaiKhoan.setText(""); txtPassword.setText(""); txtVaitro.setText("");
    }
}
