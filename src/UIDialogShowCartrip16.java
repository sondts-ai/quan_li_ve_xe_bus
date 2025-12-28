import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIDialogShowCartrip16 {
    private JPanel panelMain_showCartrip;
    private JTable table1;
    private JPanel panel_chair;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button15;
    private JButton button16;
    private JButton button_choose;
    private JButton button14;
    private JPanel title_panel;
    private JScrollPane scroll_route;

    Main main;
    public JPanel getPanelMain_showCartrip() {
        return panelMain_showCartrip;
    }

    DefaultTableModel routeModel1 = new DefaultTableModel(
            new Object[]{"thứ tự","xe", "tuyến","thời gian đi","thời gian đến","số ghế trống","giá vé"}, 0);

    public UIDialogShowCartrip16(Main main){
            this.main = main;
        table1.setModel(routeModel1);
        button_choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        panelMain_showCartrip,                     // component cha (panel / frame)
                        "Đã đặt vé thành công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }

    public void loaddata()
    {
        //data lấy từ hàm trả về rs để gă vào table
    }
}
