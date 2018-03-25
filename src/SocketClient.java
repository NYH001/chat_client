import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by NicholasNie on 2018/3/19.
 */
public class SocketClient {

    Socket skClient;
    InputStreamReader isrReader;
    BufferedReader brReader;
    PrintWriter pwSend;

    SimpleChatClient sccUI;

    public SocketClient(SimpleChatClient simpleChatClient) throws IOException {
        skClient = new Socket("127.0.0.1",5000);
        isrReader = new InputStreamReader(skClient.getInputStream());
        brReader = new BufferedReader(isrReader);
        pwSend = new PrintWriter(skClient.getOutputStream());
        sccUI = simpleChatClient;
        Thread ReaderThread = new Thread(new Receiver());
        ReaderThread.start();
    }

    public void send(String message) throws IOException {
//        pwSend.println(sccUI.getUserName()+ "\n" + message);
//        System.out.println(message);
        pwSend.println(message);
        pwSend.flush();
    }

    public class Receiver implements Runnable{
        @Override
        public void run() {
            String message;

            try {
                while (null != (message = brReader.readLine())){
//                    System.out.println(message);
                    sccUI.editRecord(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
