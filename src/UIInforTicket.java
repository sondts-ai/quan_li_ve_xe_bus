import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIInforTicket {
    private JPanel panelMain_Inforticket;
    private JPanel title_panel;
    private JTable table_infor;
    private JScrollPane scroll_infor;
    private JButton button_destroiTicket;
    private JButton button_return;
    Main main;
    DefaultTableModel routeModel1 = new DefaultTableModel(
            new Object[]{"thứ tự", "vị trí ghế","thời gian đặt","trạng thái", "khởi hành", "điểm đến"}, 0);

    public JPanel getPanelMain_Inforticket() {
        return panelMain_Inforticket;
    }

    public UIInforTicket(Main main)
    {
        this.main = main;
        table_infor.setModel(routeModel1);
        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formUImain");
            }
        });
        button_destroiTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        main.frame,                     // component cha (panel / frame)
                        "Đã huỷ vé thành công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}
