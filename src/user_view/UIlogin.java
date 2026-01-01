package user_view;

import service.NguoiDungService;
import app.Main;
import utils.Auth;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIlogin {
    private JPanel loginmain_panel;
    private JPanel login_panel;
    private JTextField textField_name;
    private JTextField textField_pass;
    private JButton button_rigister;
    private JButton button_login;
    private JLabel lable_login;
    private JLabel lable_name;
    private JLabel lable_pass;
    private JPanel title_panel;

    Main main;
    public UIlogin(Main main) {
        this.main = main;

        button_rigister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formrigister");
            }
        });
        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namelogin = textField_name.getText();
                String passlogin = textField_pass.getText();
                NguoiDungService service = new NguoiDungService();
                String ketQua = service.dangNhap(namelogin, passlogin);
                JOptionPane.showMessageDialog(null, ketQua);
                if (ketQua.equals("Đăng nhập thành công!")) {
                    main.setNguoiDung(Auth.user);
                    main.xuLySauDangNhap();
                }
            }
        });
    }
    public JPanel getMainpanel() {
        return loginmain_panel;
    }
}