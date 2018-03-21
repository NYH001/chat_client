import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by NicholasNie on 2018/3/19.
 */
public class SimpleChatClient {

    public static void main(String[] args){
        new SimpleChatClient().initUI();
    }

    private JFrame frame;
    private JLabel lbOpponent;
    private JTextArea taRecord;
    private JScrollPane spRecord;
    private JLabel lbEmpty;
    private JTextArea taInput;
    private JScrollPane spInput;
    private JButton btSend;
    private JPanel pnMainPanel;

    private SocketClient scSocket;

    public void initUI(){

        frame = new JFrame("Simple Chat Client");

        lbOpponent = new JLabel();
        lbOpponent.setText("Opponent");
        taRecord = new JTextArea(20,200);
        taRecord.setEditable(false);
        taRecord.setLineWrap(true);
        spRecord = new JScrollPane(taRecord);
        lbEmpty = new JLabel("      ");
        taInput = new JTextArea(10,200);
        taInput.setLineWrap(true);
        spInput = new JScrollPane(taInput);
        btSend = new JButton("Send");
        btSend.addActionListener(new SendListener());
        pnMainPanel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnMainPanel.setLayout(new BoxLayout(pnMainPanel, BoxLayout.Y_AXIS));
        pnMainPanel.add(lbOpponent);
        pnMainPanel.add(spRecord);
        pnMainPanel.add(lbEmpty);
        pnMainPanel.add(spInput);
        pnMainPanel.add(btSend);

        frame.setContentPane(pnMainPanel);
        frame.setSize(500,500);
        frame.setVisible(true);

        try {
            scSocket = new SocketClient(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editRecord(String message){
        taRecord.append(message + "\n");
    }

    class SendListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String strInput = taInput.getText();
            try {
                scSocket.send(strInput);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            taInput.setText("");
            taInput.requestFocus();
//            taRecord.append(strInput + "\n");
        }
    }
}
