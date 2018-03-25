import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NicholasNie on 2018/3/22.
 */
public class LoginPanel {

    public static void main(String[] args){
        new LoginPanel().initUI();
    }

    JFrame frame;
    JPanel pnLoginPanel;
    JTextField tfUserName;
    JButton btLogin;
    SimpleChatClient sccUI;

    public void initUI(){
        frame = new JFrame("Login");
        pnLoginPanel = new JPanel();
        tfUserName = new JTextField(20);
        btLogin = new JButton("Login");
        btLogin.addActionListener(new LoginListener());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pnLoginPanel.setLayout(new BoxLayout(pnLoginPanel,BoxLayout.Y_AXIS));
        pnLoginPanel.add(tfUserName);
        pnLoginPanel.add(btLogin);

        frame.setContentPane(pnLoginPanel);
        frame.setSize(300,90);
        frame.setVisible(true);
    }

    public class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked!");
            sccUI = new SimpleChatClient();
            sccUI.initUI();
            sccUI.setUserName(tfUserName.getText());
            frame.setVisible(false);
        }
    }
}
