import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIFixPass {
    private JPanel panelMain_fixPass;
    private JPanel title_panel;
    private JPanel panel_fixpass;
    private JTextField textField_oldPass;
    private JTextField textField_newPass;
    private JLabel lable_oldpass;
    private JLabel lable_newPass;
    private JTextField textField_againNewPass;
    private JLabel lable_newPassAgain;
    private JButton button_agree;
    private JButton button_return;

    Main main;
    public UIFixPass(Main main) {
        this.main = main;

        button_agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formFixInfor");
            }
        });
    }

    public JPanel getPanelMain_fixPass()
    {
        return panelMain_fixPass;
    }

}
