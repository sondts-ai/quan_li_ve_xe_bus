package user_view;

import model.NguoiDung;
import service.NguoiDungService;
import app.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UIrigister {
    private JPanel panel_registermain;
    private JPanel rigister_panel;
    private JTextField textField_pass;
    private JLabel lable_rigister;
    private JButton button_rigister;
    private JTextField textField_account;
    private JLabel lable_account;
    private JLabel lable_pass;
    private JButton button_return;
    private JPanel title_panel;
    private JTextField textField_name;
    private JTextField textField_birth;
    private JTextField textField_numbertele;
    private JTextField textField_Gmail;
    private JLabel lable_name;
    private JLabel lable_birth;
    private JLabel lable_numbertele;
    private JLabel lable_gmail;

    Main main;

    public UIrigister(Main main) {
        this.main = main;


        button_rigister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String hoten = textField_name.getText().trim();
                String ngaysinhstr = textField_birth.getText().trim();
                String sdt = textField_numbertele.getText().trim();
                String gmail = textField_Gmail.getText().trim();
                String tk = textField_account.getText().trim();
                String mk = new String(textField_pass.getText());
                String vaitro = "khackhang";

                if (hoten.isEmpty() || ngaysinhstr.isEmpty() || tk.isEmpty() || mk.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);

                java.sql.Date ngaysinh;
                try {
                    Date d = sdf.parse(ngaysinhstr);
                    ngaysinh = new java.sql.Date(d.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Ngày sinh không hợp lệ! Định dạng: dd/MM/yyyy"
                    );
                    return;
                }

                NguoiDung newnguoidung = new NguoiDung();
                newnguoidung.setHoTen(hoten);
                newnguoidung.setNgaySinh(ngaysinh);
                newnguoidung.setSdt(sdt);
                newnguoidung.setEmail(gmail);
                newnguoidung.setTaiKhoan(tk);
                newnguoidung.setMatKhau(mk);
                newnguoidung.setVaiTro(vaitro);

                NguoiDungService service = new NguoiDungService();

                String ketQua = service.dangKi(newnguoidung);

                JOptionPane.showMessageDialog(null, ketQua);

                if (ketQua.equals("Đăng ký thành công!")) {
                    main.switchPage("formLogin");
                }
            }
        });



        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formlogin");
            }
        });
    }

    public JPanel getPanel_register()
    {
        return panel_registermain;
    }
}
