package user_view;

import service.NguoiDungService;
import utils.Auth;
import app.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UIFixInfor {
    private JPanel panelMain_FixInfor;
    private JPanel panel_fixInfor;
    private JLabel lable_fixInfor;
    private JButton button_FixPass;
    private JButton button_return;
    private JTextField textField_name;
    private JTextField textField_birth;
    private JTextField textField_numbertele;
    private JTextField textField_Gmail;
    private JLabel lable_name;
    private JLabel lable_birth;
    private JLabel lable_numbertele;
    private JLabel lable_gmail;
    private JPanel title_panel;
    private JButton button_agree;
    NguoiDungService nguoiDungService = new NguoiDungService();
    Main main;
    public JPanel getPanelMain_FixInfor() {
        return panelMain_FixInfor;
    }
    public UIFixInfor(Main main) {
        this.main = main;
        button_agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            updateUserInfo();
            }
        });
        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formInfor");
            }
        });
    }
    private void updateUserInfo() {
        String name = textField_name.getText().trim();
        String birth = textField_birth.getText().trim();
        String phone = textField_numbertele.getText().trim();
        String gmail = textField_Gmail.getText().trim();
        if(name.isEmpty() || birth.isEmpty() || phone.isEmpty() || gmail.isEmpty()) {
            JOptionPane.showMessageDialog(panelMain_FixInfor, "Vui lòng điền đầy đủ thông tin!");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        java.sql.Date ngaysinh;
        try {
            Date d = sdf.parse(birth);
            ngaysinh = new java.sql.Date(d.getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Ngày sinh không hợp lệ! Định dạng: dd/MM/yyyy"
            );
            return;
        }
        Auth.user.setHoTen(name);
        Auth.user.setNgaySinh(ngaysinh);
        Auth.user.setSdt(phone);
        Auth.user.setEmail(gmail);
        nguoiDungService.capNhatThongTin(Auth.user);
        JOptionPane.showMessageDialog(panelMain_FixInfor, "Cập nhật thông tin thành công!");
        main.switchPage("formUImain");
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
