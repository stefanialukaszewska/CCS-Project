import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Reports reports;

    public ClientHandler(Socket clientSocket, Reports reports) {
        this.clientSocket = clientSocket;
        this.reports = reports;
    }

    @Override
    public void run() {
        Log log = new Log("TCP");
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String message;
            while ((message = reader.readLine()) != null) {
                log.log("Received message from client: " + message);
                try {
                    Operation.getResult(message, writer, reports, log);
                } catch (Exception e) {
                    log.log("Error processing client request");
                    writer.println("ERROR: Unable to process request");
                }
            }
        } catch (IOException e) {
            log.log("Client disconnected or error occurred");
        }
    }
}
