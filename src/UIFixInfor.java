import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIFixInfor {
    private JPanel panelMain_FixInfor;
    private JPanel panel_fixInfor;
    private JLabel lable_fixInfor;
    private JButton button_FixPass;
    private JButton button_return;
    private JTextField textField_name;
    private JTextField textField_birth;
    private JTextField textField_numbertele;
    private JTextField textField_Gmail;
    private JLabel lable_name;
    private JLabel lable_birth;
    private JLabel lable_numbertele;
    private JLabel lable_gmail;
    private JPanel title_panel;
    private JButton button_agree;

    Main main;

    public JPanel getPanelMain_FixInfor() {
        return panelMain_FixInfor;
    }

    public UIFixInfor(Main main) {
        this.main = main;

        button_agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formInfor");
            }
        });
        button_FixPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchPage("formFixPass");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
