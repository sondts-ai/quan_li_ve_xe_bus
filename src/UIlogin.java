import model.NguoiDung;
import service.NguoiDungService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Auth;
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

        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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

                // Hiện thông báo
                JOptionPane.showMessageDialog(null, ketQua);

                // Chỉ vào main khi đăng nhập thành công
                if (ketQua.equals("Đăng nhập thành công!")) {

                    UImain uImain = new UImain(main, Auth.user);
                    main.mainContainer.add(uImain.getPanel_UImain(), "formUImain");
                    main.switchPage("formUImain");
                }
            }
        });

    }

    public JPanel getMainpanel()
    {
        return loginmain_panel;
    }
}
