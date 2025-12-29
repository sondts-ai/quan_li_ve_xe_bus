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

    // Layout điều hướng các màn hình trong cùng 1 JFrame
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);

    private JFrame frame;
    private NguoiDung nguoiDung;


    // ===== ENTRY POINT DUY NHẤT =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().khoiTaoGiaoDien();
        });
    }


    // ===== KHỞI TẠO GIAO DIỆN CHÍNH =====
    public void khoiTaoGiaoDien() {
        frame = new JFrame("Hệ thống đặt vé xe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);

        // Truyền app.Main vào các UI con để callback
        UIlogin uiLogin = new UIlogin(this);
        UIrigister uiRegister = new UIrigister(this);
        UIFindBus uiFindBus = new UIFindBus(this);

        // Thêm các màn hình ban đầu vào CardLayout
        mainContainer.add(uiLogin.getMainpanel(), "formlogin");
        mainContainer.add(uiRegister.getPanel_register(), "formrigister");
        mainContainer.add(uiFindBus.getPanelMain_findBus(), "formFinBus");

        frame.setContentPane(mainContainer);

        // Mặc định mở màn hình Login đầu tiên
        cardLayout.show(mainContainer, "formlogin");

        frame.setVisible(true);
    }

    // ===== GETTER / SETTER =====

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public JFrame getFrame() {
        return frame;
    }

    // --- MỚI THÊM: Hàm này quan trọng để các UI con add được màn hình mới ---
    public JPanel getMainContainer() {
        return mainContainer;
    }

    // ===== CHUYỂN MÀN HÌNH =====
    public void switchPage(String pageName) {
        cardLayout.show(mainContainer, pageName);
    }

    // ===== LOGIC ĐIỀU HƯỚNG SAU KHI ĐĂNG NHẬP THÀNH CÔNG =====
    public void xuLySauDangNhap() {
        if (nguoiDung == null) {
            System.out.println("Lỗi: Người dùng bị null!");
            return;
        }

        // 1. Lấy vai trò gốc
        String vaiTro = nguoiDung.getVaiTro();

        // Debug: In ra để xem lỗi
        System.out.println("Check vai trò gốc: '" + vaiTro + "'");

        // 2. Xử lý an toàn
        if (vaiTro == null) {
            vaiTro = "";
        }

        // --- QUAN TRỌNG NHẤT: CẮT KHOẢNG TRẮNG ---
        vaiTro = vaiTro.trim();

        System.out.println("Check vai trò sau khi trim: '" + vaiTro + "'");

        // 3. So sánh
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