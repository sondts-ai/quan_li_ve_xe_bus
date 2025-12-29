package user_view;

import model.NguoiDung;
import app.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UImain {
    private JPanel panel_UImain;
    private JPanel title_panel;
    private JButton button_order;
    private JButton button_findbus;
    private JButton button_infoUser;
    private JLabel lable_nameuser;
    Main main;
    NguoiDung nguoiDung;
    public  JPanel getPanel_UImain()
    {
        return panel_UImain;
    }
    public UImain(Main main, NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
        this.main = main;
        String nameuser = nguoiDung.getHoTen();
        lable_nameuser.setText("chào : " + nameuser);
        button_order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIInforTicket uiInforTicket = new UIInforTicket(main);
                main.getMainContainer().add(uiInforTicket.getPanelMain_Inforticket(),"formInforTicket");
                main.switchPage("formInforTicket");
            }
        });
        button_findbus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formFinBus");
            }
        });
        button_infoUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIInforUser uIinferUser = new UIInforUser(main,nguoiDung); // khởi tạo form và lấy class main
                main.getMainContainer().add(uIinferUser.getPanel_inforMain(),"formInfor");
                main.switchPage("formInfor");
            }
        });
    }
}
