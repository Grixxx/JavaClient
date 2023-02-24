package ConnectToServer;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static InteractionsWithServer interactionsWithServer;
    public void connectToServer() {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 4303);
            interactionsWithServer = new InteractionsWithServer(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
