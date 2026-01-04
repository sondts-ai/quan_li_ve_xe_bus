package app;

import model.NguoiDung;
import admin_view.AdminMainFrame;
import user_view.UIFindBus;
import user_view.UIlogin;
import user_view.UIrigister;
import user_view.UImain; // <--- MỚI THÊM: Import màn hình Dashboard
import javax.swing.*;
import java.awt.*;
public class Main {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);
    private JFrame frame;
    private NguoiDung nguoiDung;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().khoiTaoGiaoDien();
        });
    }
    public void khoiTaoGiaoDien() {
        frame = new JFrame("Hệ thống đặt vé xe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        UIlogin uiLogin = new UIlogin(this);
        UIrigister uiRegister = new UIrigister(this);
        UIFindBus uiFindBus = new UIFindBus(this);
        mainContainer.add(uiLogin.getMainpanel(), "formlogin");
        mainContainer.add(uiRegister.getPanel_register(), "formrigister");
        mainContainer.add(uiFindBus.getPanelMain_findBus(), "formFinBus");
        frame.setContentPane(mainContainer);
        cardLayout.show(mainContainer, "formlogin");
        frame.setVisible(true);
    }
    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }
    public JFrame getFrame() {
        return frame;
    }
    public JPanel getMainContainer() {
        return mainContainer;
    }
    public void switchPage(String pageName) {
        cardLayout.show(mainContainer, pageName);
    }
    public void xuLySauDangNhap() {
        if (nguoiDung == null) {
            System.out.println("Lỗi: Người dùng bị null!");
            return;
        }
        String vaiTro = nguoiDung.getVaiTro();
        System.out.println("Check vai trò gốc: '" + vaiTro + "'");
        if (vaiTro == null) {
            vaiTro = "";
        }
        vaiTro = vaiTro.trim();
        System.out.println("Check vai trò sau khi trim: '" + vaiTro + "'");
        if (vaiTro.equalsIgnoreCase("admin") || vaiTro.equalsIgnoreCase("quanly")) {
            System.out.println("--> OK! Vào trang Admin");
            new AdminMainFrame().setVisible(true);
            frame.dispose();
        }
        else {
            System.out.println("--> Vào trang Khách hàng");

            UImain uiMainScreen = new UImain(this, nguoiDung);
            mainContainer.add(uiMainScreen.getPanel_UImain(), "formUImain");
            switchPage("formUImain");
        }
    }
}