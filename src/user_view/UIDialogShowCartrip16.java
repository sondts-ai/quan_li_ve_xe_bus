package user_view;

import model.LichTrinh;
import service.LichTrinhService;
import service.VeXeService;
import utils.Auth;
import app.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UIDialogShowCartrip16 {
    private JPanel panelMain_showCartrip;
    private JTable table1;
    private JPanel panel_chair;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton button12a;
    private JButton button14a;
    private JButton button16a;
    private JButton button5a;
    private JButton button6a;
    private JButton button7a;
    private JButton button11a;
    private JButton button8a;
    private JButton button15a;
    private JButton button9a;
    private JButton button10a;
    private JButton button_choose;
    private JButton button13a;
    private JPanel title_panel;
    private JScrollPane scroll_route;
    Main main;
    LichTrinhService lichTrinhService = new LichTrinhService();
    VeXeService veXeService = new VeXeService();
    String idCharChoose = "-1" ;
    Integer idLich;
    JDialog dialog;

    public JPanel getPanelMain_showCartrip() {
        return panelMain_showCartrip;
    }

    DefaultTableModel cartripmodel = new DefaultTableModel(
            new Object[]{"thứ tự","mã xe","thời gian đi","thời gian đến","giá vé"}, 0);

    public UIDialogShowCartrip16(Main main, Integer idchuyen, JDialog dialog){
            this.main = main;
            this.idLich = idchuyen;
            this.dialog = dialog;
            loadTripDetail(idchuyen);
            table1.setModel(cartripmodel);
            checkBookedSeats(idLich);
        button_choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"-1".equals(idCharChoose))
                {
                    bookTicket(idLich);
                    JOptionPane.showMessageDialog(null, "đặt vé thành công");
                    dialog.dispose();
                    main.switchPage("formUImain");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "vui lòng chọn vé");
                }
            }
        });
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "A1" ;
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "A2" ;
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "A3" ;
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "A4" ;
            }
        });
        button5a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "B1" ;
            }
        });
        button6a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "B2" ;
            }
        });
        button7a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "B3" ;
            }
        });
        button8a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "B4" ;
            }
        });
        button9a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "C1" ;
            }
        });
        button10a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "C2" ;
            }
        });
        button11a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "C3" ;
            }
        });
        button12a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "C4" ;
            }
        });
        button13a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "D1" ;
            }
        });
        button14a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "D2" ;
            }
        });
        button15a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "D3" ;
            }
        });
        button16a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 idCharChoose = "D4" ;
            }
        });
    }
    private void bookTicket(int lichid)
    {
        VeXeService veXeService = new VeXeService();

        Integer iduser = Auth.user.getNguoiDungId();
        System.out.println("ghe được chọn: "+idCharChoose +"iduser:" + iduser +"lichtrinh:"+lichid);
        veXeService.datVe(iduser,lichid,idCharChoose);
    }
    private void loadTripDetail(int lichId) {
        cartripmodel.setRowCount(0);

        LichTrinh lt = lichTrinhService.getByLichId(lichId);
        if (lt != null) {
            cartripmodel.addRow(new Object[]{
                    lt.getLichId(),
                    lt.getXeId(),
                    lt.getThoiGianKhoiHanh(),
                    lt.getThoiGianDen(),
                    lt.getGiaVe()
            });
        }
    }
    private void checkBookedSeats(int lichId) {
        List<String> gheDaDat = veXeService.getGheDaDat(lichId);

        disableIfBooked(a1Button, "A1", gheDaDat);
        disableIfBooked(a2Button, "A2", gheDaDat);
        disableIfBooked(a3Button, "A3", gheDaDat);
        disableIfBooked(a4Button, "A4", gheDaDat);


        disableIfBooked(button5a, "B1", gheDaDat);
        disableIfBooked(button6a, "B2", gheDaDat);
        disableIfBooked(button7a, "B3", gheDaDat);
        disableIfBooked(button8a, "B4", gheDaDat);

        disableIfBooked(button9a, "C1", gheDaDat);
        disableIfBooked(button10a, "C2", gheDaDat);
        disableIfBooked(button11a, "C3", gheDaDat);
        disableIfBooked(button12a, "C4", gheDaDat);


        disableIfBooked(button13a, "D1", gheDaDat);
        disableIfBooked(button14a, "D2", gheDaDat);
        disableIfBooked(button15a, "D3", gheDaDat);
        disableIfBooked(button16a, "D4", gheDaDat);
    }
    private void disableIfBooked(JButton btn, String seatName, List<String> bookedList) {

        if (bookedList.contains(seatName)) {
            btn.setEnabled(false);
            btn.setBackground(Color.RED);
            btn.setText(seatName);
        } else {

            btn.setEnabled(true);
            btn.setBackground(null);
        }
    }
    public void loaddata()
    {

    }
}
