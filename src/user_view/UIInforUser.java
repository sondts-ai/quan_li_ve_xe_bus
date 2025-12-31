package user_view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import app.Main;

import model.NguoiDung;

public class UIInforUser {
    private JPanel panel_inforMain;
    private JPanel infor_panel;
    private JLabel lable_title;
    private JButton button_fixInfor;
    private JButton button_return;
    private JLabel lable_name;
    private JLabel lable_birth;
    private JLabel lable_numbertele;
    private JLabel lable_gmail;
    private JPanel title_panel;
    Main main;
    NguoiDung nguoiDung;
    public JPanel getPanel_inforMain()
    {
        return  panel_inforMain;
    }
    public UIInforUser(Main main, NguoiDung nguoiDung) {

        this.main = main;
        this.nguoiDung = nguoiDung;

        loadData();
        button_fixInfor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFixInfor uiFixInfor = new UIFixInfor(main);
                main.getMainContainer().add(uiFixInfor.getPanelMain_FixInfor(),"formFixInfor");
                main.switchPage("formFixInfor");
            }
        });
        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formUImain");
            }
        });
    }
    public void loadData() {
        if (nguoiDung != null) {

            lable_name.setText("Họ tên: " +nguoiDung.getHoTen());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            lable_birth.setText("Ngày sinh: " +sdf.format(nguoiDung.getNgaySinh()));

            lable_numbertele.setText("Số điện thoại: " + nguoiDung.getSdt());
            lable_gmail.setText("Gmail: " + nguoiDung.getEmail());
        }
        else
        {
            System.out.println("không tìm thấy Auth");
        }
    }
}
