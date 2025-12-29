import model.LichTrinh;
import model.VeXe;
import service.LichTrinhService;
import service.VeXeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import utils.Auth;

public class UIDialogShowCartrip30 {
    private JPanel title_panel;
    private JPanel panelMain_ShowCartrip;
    private JTable table1;
    private JPanel panel_chair;
    private JButton button_1;
    private JButton button_2;
    private JButton a3Button;
    private JButton a4Button;
    private JButton button7a;
    private JButton button8;
    private JButton Button9;
    private JButton button13a;
    private JButton button14a;
    private JButton button15a;
    private JButton button10;
    private JButton button16a;
    private JButton button19a;
    private JButton button20a;
    private JButton button21a;
    private JButton Button22a;
    private JButton button_choose;
    private JButton a5Button;
    private JButton button11a;
    private JButton button17a;
    private JButton button23a;
    private JButton button25a;
    private JButton button26a;
    private JButton button27a;
    private JButton button28a;
    private JButton button29a;
    private JButton button6a;
    private JButton button12;
    private JButton button18a;
    private JButton button24a;
    private JButton e6Button;
    Main main;
    LichTrinhService lichTrinhService = new LichTrinhService();
    VeXeService veXeService = new VeXeService();
    String idCharChoose = "-1" ;
    Integer idLich;
    JDialog dialog;

    public JPanel getPanelMain_showCartrip() {
        return panelMain_ShowCartrip;
    }

    DefaultTableModel cartripmodel = new DefaultTableModel(
            new Object[]{"thứ tự","mã xe", "mã tuyến","thời gian đi","thời gian đến","số ghế trống","giá vé"}, 0);

    public UIDialogShowCartrip30(Main main,Integer idchuyen,JDialog dialog){
        this.main = main;
        this.idLich = idchuyen;
        this.dialog = dialog;
        table1.setModel(cartripmodel);
        loadTripDetail(idchuyen);
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

        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "A1";
            }
        });
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "A2";
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "A3";
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "A4";
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "A5";
            }
        });
        button6a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "A6";
            }
        });
        button7a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "B1";
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "B2";
            }
        });
        Button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "B3";
            }
        });
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "B4";
            }
        });
        button11a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "B5";
            }
        });
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "B6";
            }
        });
        button13a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "C1";
            }
        });
        button14a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "C2";
            }
        });
        button15a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "C3";
            }
        });
        button16a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "C4";
            }
        });
        button17a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "C5";
            }
        });
        button18a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "C6";
            }
        });
        button19a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "D1";
            }
        });
        button20a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "D2";
            }
        });
        button21a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "D3";
            }
        });
        Button22a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "D4";
            }
        });
        button23a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "D5";
            }
        });
        button24a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "D6";
            }
        });
        button25a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "E1";
            }
        });
        button26a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "E2";
            }
        });
        button27a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "E3";
            }
        });
        button28a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "E4";
            }
        });
        button29a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "E5";
            }
        });
        e6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idCharChoose = "E6";
            }
        });
    }

    // 3. Hàm kiểm tra và khóa ghế
    private void checkBookedSeats(int lichId) {
        List<String> gheDaDat = veXeService.getGheDaDat(lichId);

        // Hàng A
        disableIfBooked(button_1, "A1", gheDaDat);
        disableIfBooked(button_2, "A2", gheDaDat);
        disableIfBooked(a3Button, "A3", gheDaDat);
        disableIfBooked(a4Button, "A4", gheDaDat);
        disableIfBooked(a5Button, "A5", gheDaDat);
        disableIfBooked(button6a, "A6", gheDaDat);

        // Hàng B
        disableIfBooked(button7a, "B1", gheDaDat);
        disableIfBooked(button8,  "B2", gheDaDat);
        disableIfBooked(Button9,  "B3", gheDaDat);
        disableIfBooked(button10, "B4", gheDaDat);
        disableIfBooked(button11a,"B5", gheDaDat);
        disableIfBooked(button12, "B6", gheDaDat);

        // Hàng C
        disableIfBooked(button13a, "C1", gheDaDat);
        disableIfBooked(button14a, "C2", gheDaDat);
        disableIfBooked(button15a, "C3", gheDaDat);
        disableIfBooked(button16a, "C4", gheDaDat);
        disableIfBooked(button17a, "C5", gheDaDat);
        disableIfBooked(button18a, "C6", gheDaDat);

        // Hàng D
        disableIfBooked(button19a, "D1", gheDaDat);
        disableIfBooked(button20a, "D2", gheDaDat);
        disableIfBooked(button21a, "D3", gheDaDat);
        disableIfBooked(Button22a, "D4", gheDaDat);
        disableIfBooked(button23a, "D5", gheDaDat);
        disableIfBooked(button24a, "D6", gheDaDat);

        // Hàng E
        disableIfBooked(button25a, "E1", gheDaDat);
        disableIfBooked(button26a, "E2", gheDaDat);
        disableIfBooked(button27a, "E3", gheDaDat);
        disableIfBooked(button28a, "E4", gheDaDat);
        disableIfBooked(button29a, "E5", gheDaDat);
        disableIfBooked(e6Button,  "E6", gheDaDat);
    }

    // 4. Hàm phụ trợ disable nút
    private void disableIfBooked(JButton btn, String seatName, List<String> bookedList) {
        if (btn == null) return; // Tránh lỗi nếu chưa bind nút

        if (bookedList.contains(seatName)) {
            btn.setEnabled(false);
            btn.setBackground(Color.RED);
            // btn.setText(seatName + " (X)"); // Bỏ comment nếu muốn đổi text
        } else {
            btn.setEnabled(true);
            btn.setBackground(null);
        }
    }
    private void bookTicket(int lichid)  // hàm đặt vé
    {
        VeXeService veXeService = new VeXeService();
        LichTrinh lt = lichTrinhService.getByLichId(lichid);

        Integer iduser = Auth.user.getNguoiDungId();

            veXeService.datVe(iduser,lichid,idCharChoose);
    }

    private void loadTripDetail(int lichId) {  // hàm load data
        cartripmodel.setRowCount(0); // clear bảng mới

        LichTrinh lt = lichTrinhService.getByLichId(lichId);
        if (lt != null) {
            cartripmodel.addRow(new Object[]{
                    lt.getLichId(),
                    lt.getXeId(),
                    lt.getTuyenId(),
                    lt.getThoiGianKhoiHanh(),
                    lt.getThoiGianDen(),
                    lt.getSoGheTrong(),
                    lt.getGiaVe()
            });
        }
    }

    public void loaddata()
    {
        //data lấy từ hàm trả về rs để gă vào table
    }
}
