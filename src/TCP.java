import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP {
    public static void openTCPport(Reports reports) throws IOException {
        int PORT = Main.getPORT();
        Log log = new Log("TCP");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            log.log("Server is working on port: " + PORT);

            while (true) {

                Socket clientSocket = serverSocket.accept();
                log.log("Connected with client: " + clientSocket.getInetAddress());
                reports.addNewClientsCount();

                new Thread(new ClientHandler(clientSocket, reports)).start();
            }
        } catch (Exception e) {
            log.log("Server error");
        }
    }
}

