package user_view;

import service.NguoiDungService;
import app.Main; // Import đúng package chứa Main
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

        // Nút chuyển sang đăng ký
        button_rigister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formrigister");
            }
        });

        // Nút xử lý Đăng Nhập
        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namelogin = textField_name.getText();
                String passlogin = textField_pass.getText();

                NguoiDungService service = new NguoiDungService();

                // Hàm này sẽ gán Auth.user nếu thành công
                String ketQua = service.dangNhap(namelogin, passlogin);

                // Hiện thông báo
                JOptionPane.showMessageDialog(null, ketQua);

                if (ketQua.equals("Đăng nhập thành công!")) {
                    // --- ĐOẠN SỬA QUAN TRỌNG NHẤT ---

                    // 1. Cập nhật người dùng hiện tại vào Main để nó biết ai đang đăng nhập
                    main.setNguoiDung(Auth.user);

                    // 2. Gọi hàm phân luồng (Admin/User) bên Main
                    // Hàm này sẽ tự kiểm tra if(admin) hay if(user) để mở cửa sổ đúng
                    main.xuLySauDangNhap();

                    // ---------------------------------
                }
            }
        });
    }

    public JPanel getMainpanel() {
        return loginmain_panel;
    }
}