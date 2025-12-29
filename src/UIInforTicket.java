import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.VeXe;
import model.VeXeTriTiet;
import service.VeXeService;
import utils.Auth;
public class UIInforTicket {
    private JPanel panelMain_Inforticket;
    private JPanel title_panel;
    private JTable table_infor;
    private JScrollPane scroll_infor;
    private JButton button_destroiTicket;
    private JButton button_return;
    Main main;
    int idve = -1;
    VeXeService veXeService = new VeXeService();
    DefaultTableModel routeModel1 = new DefaultTableModel(
            new Object[]{"mã vé", "vị trí ghế","thời gian đặt","trạng thái","thời điểm khởi hành","giá vé"}, 0);

    public JPanel getPanelMain_Inforticket() {
        return panelMain_Inforticket;
    }

    public UIInforTicket(Main main)
    {
        this.main = main;
        table_infor.setModel(routeModel1);
        loadTicketsByUserId(Auth.user.getNguoiDungId());

        table_infor.getSelectionModel().addListSelectionListener(e -> { // hàm event routetable
            if (e.getValueIsAdjusting()) return; // tránh gọi 2 lần

            int viewRow = table_infor.getSelectedRow();
            if (viewRow == -1) return;

            // Nếu có sort / filter
            int modelRow = table_infor.convertRowIndexToModel(viewRow);

            idve = (int) table_infor
                    .getModel()
                    .getValueAt(modelRow, 0); // ⭐ cột ID
        });

        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formUImain");
            }
        });
        button_destroiTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idve != -1)
                {
                    veXeService.huyVe(idve);
                    JOptionPane.showMessageDialog(
                            panelMain_Inforticket,
                            "huỷ vé thành công!"
                    );
                    loadTicketsByUserId(Auth.user.getNguoiDungId());
                    idve = -1;
                }
                else
                {
                    JOptionPane.showMessageDialog(
                            panelMain_Inforticket,
                            "Chưa chọn vé nào!"
                    );
                }
            }
        });
    }

    public void loadTicketsByUserId(int userId) {
        // Xóa dữ liệu cũ
        routeModel1.setRowCount(0);

        // Giả sử VeXeService có phương thức lấy vé theo user
        List<VeXeTriTiet> tickets = veXeService.getLichSuDatVe(userId);

        for (VeXeTriTiet ticket : tickets) {
            routeModel1.addRow(new Object[]{
                    ticket.getVeId(),                          // thứ tự
                    ticket.getViTriGhe(),           // vị trí ghế
                    ticket.getThoiGianDat(),        // thời gian đặt
                    ticket.getTrangThai(),
                    ticket.getThoiGianKhoiHanh(),
                    ticket.getGiaVe()
            });
        }
    }

}
