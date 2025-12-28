import model.LichTrinh;
import model.TuyenXe;
import service.LichTrinhService;
import service.TuyenXeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UIFindBus {
    private JPanel panelMain_findBus;
    private JPanel title_panel;
    private JPanel panel_busroute;
    private JLabel lable_chooceBus;
    private JTable table_busRoute;
    private JPanel panel_carTrip;
    private JTable table_carTrip;
    private JButton button1;
    private JButton button_return;
    private JButton button_OrderTicket;
    JDialog dialog;
    Main main;
    Integer numberchair;

    private TuyenXeService tuyenXeService = new TuyenXeService();
    private LichTrinhService lichTrinhService = new LichTrinhService();

    DefaultTableModel routeModel1 = new DefaultTableModel(
            new Object[]{"thứ tự","Điểm khởi hành", "Điểm đến","thời gian di chuyển","khoảng cách"}, 0);

    DefaultTableModel routeModel_carTrip = new DefaultTableModel(
            new Object[]{"xe","thời gian khởi hành", "thời gian đến"}, 0);

    public UIFindBus() {
        // constructor cho UI Form
    }

    public UIFindBus(Main main) {
          this.main = main;
          this();
          table_busRoute.setModel(routeModel1);
          table_carTrip.setModel(routeModel_carTrip);
          loadTableTuyenXe();


        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formUImain");
            }
        });

        table_busRoute.getSelectionModel().addListSelectionListener(e -> { // hàm event routetable
            if (e.getValueIsAdjusting()) return; // tránh gọi 2 lần

            int viewRow = table_busRoute.getSelectedRow();
            if (viewRow == -1) return;

            // Nếu có sort / filter
            int modelRow = table_busRoute.convertRowIndexToModel(viewRow);

            Object value = table_busRoute.getModel().getValueAt(modelRow, 0);

            System.out.println("Dòng được chọn, ID = " + value);
        });

    }

    private void loadTableTuyenXe() {
        routeModel1.setRowCount(0); // clear dữ liệu cũ

        List<TuyenXe> list = tuyenXeService.getAll();
        for (TuyenXe tx : list) {
            routeModel1.addRow(new Object[]{
                    tx.getTuyenId(),
                    tx.getKhoiHanh(),
                    tx.getDiemDen(),
                    tx.getThoiGianDiChuyen(),
                    tx.getKhoangCach()
            });
        }
    }

    private void loadTablechuyenxe() {
        routeModel1.setRowCount(0); // clear dữ liệu cũ

        List<LichTrinh> list = lichTrinhService.getAllLichTrinh();
        for (LichTrinh lt : list) {
            routeModel1.addRow(new Object[]{
                    lt.getXeId(),
                    lt.getThoiGianKhoiHanh(),
                    lt.getThoiGianDen()
            });
        }
    }
    public void chooseBusroute() // hàm chọn và trả về id tuyến để load data cho table chuyến xe
    {
        table_busRoute.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {

                int viewRow = table_busRoute.getSelectedRow();
                if (viewRow == -1) return;

                // nếu có sort/filter thì convert
                int modelRow = table_busRoute.convertRowIndexToModel(viewRow);

                int routeId = Integer.parseInt(
                        table_busRoute.getModel().getValueAt(modelRow, 0).toString()
                );

                System.out.println("Route ID được chọn: " + routeId);

                // 👉 dùng routeId để load bảng chuyến xe bên dưới
                //loadTripsByRoute(routeId);  // hàm load data cho bảng chuyến xe
            }
        });

    }

    public JPanel getPanelMain_findBus() {
        return panelMain_findBus;
    }

    public void showInforCarTrip() // hiển thị thông tin chuyến xe khi nhấn vào chuyến
    {
        numberchair = 30;  // test số ghế
        UIDialogShowCartrip16 uiDialogShowCartrip = new UIDialogShowCartrip16(main);
        UIDialogShowCartrip30 uiDialogShowCartrip30 = new UIDialogShowCartrip30(main);

        dialog = new JDialog(
                (JFrame) SwingUtilities.getWindowAncestor(main.frame),
                " Thông tin chuyến xe",
                true
        );

        if(numberchair == 16)
        {
            dialog.setContentPane(uiDialogShowCartrip.getPanelMain_showCartrip());
        }
        else
        {
            dialog.setContentPane(uiDialogShowCartrip30.getPanelMain_showCartrip());
        }
        dialog.setLocationRelativeTo(main.frame);
        dialog.pack();
        dialog.setVisible(true);
// ⭐ DÒNG QUYẾT ĐỊNH

    }




}
