import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIInforUser {
    private JPanel panel_inforMain;
    private JPanel infor_panel;
    private JLabel lable_title;
    private JButton button_fixInfor;
    private JButton button_return;
    private JLabel lable_name;
    private JLabel lable_birth;
    private JLabel lable_numbertele;
    private JLabel lable_gmail;
    private JPanel title_panel;

    Main main;
    public JPanel getPanel_inforMain()
    {
        return  panel_inforMain;
    }
    public UIInforUser(Main main) {
        this.main = main;

        // khi khởi tạo thì đã set sẵn data vào text
        button_fixInfor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button_fixInfor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              main.switchPage("formFixInfor");
            }
        });
        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formUImain");
            }
        });

    }
}
