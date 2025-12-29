import model.NguoiDung;

import javax.swing.*;
import java.awt.*;
import  utils.Auth;
public class Main {

    CardLayout cardLayout = new CardLayout();
    JPanel mainContainer = new JPanel(cardLayout); // khởi tạo maincontainer với cartlayout để quản lý các form
    public JFrame frame;
    public NguoiDung nguoiDung;


    public static void main(String args[]) {
        // Thông tin kết nối SQL Server
        Main main = new Main(); // khởi tạo 1 main mới để sử dụng

        main.frame = new JFrame("Quản lý xe khách");
        main.frame.setDefaultCloseOperation(main.frame.EXIT_ON_CLOSE);
        main.frame.setSize(700, 600);
        main.frame.setLocationRelativeTo(null);


        UIlogin uIlogin = new UIlogin(main);
        UIrigister uIrigister = new UIrigister(main);
        UIFindBus uiFindBus = new UIFindBus(main);

        main.mainContainer.add(uIlogin.getMainpanel(), "formlogin"); // thêm panel vàp cartlayout để điều khiển với định danh
        main.mainContainer.add(uIrigister.getPanel_register(), "formrigister");
        main.mainContainer.add(uiFindBus.getPanelMain_findBus(),"formFinBus");


        main.frame.add(main.mainContainer);
        main.cardLayout.show(main.mainContainer,"formlogin");
        main.frame.setVisible(true);
    }
    public void setnguoidung(NguoiDung nguoiDung)
    {
        this.nguoiDung = nguoiDung;
    }

    public void switchPage(String pageName) // hàm thay đổi form
    {
        cardLayout.show(mainContainer,pageName);
    }
}