import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class Main {
    private static int PORT;

    public static void main(String[] args) throws IOException, InterruptedException {
        Log log = new Log("SERVER");

        if(args.length!=1){
            log.log("Incorrect number of parameters");
            System.exit(0);
        }

        try{
            PORT = Integer.parseInt(args[0]);
        } catch (Exception e){
            log.log("Bad type of parameter");
            System.exit(0);
        }

        if(!isPortAvailableTCP(PORT)){
            log.log("TCP port is being used. Use another one.");
            System.exit(0);
        }
        if(!isPortAvailableUDP(PORT)){
            log.log("UDP port is being used. Use another one.");
            System.exit(0);
        }

        Reports reports = new Reports();

        Thread udpThread = new Thread(() -> {
            try {
                UDP.openUDPport();
            } catch (IOException e) {
                log.log("UDP server error");
            }
        });
        Thread tcpThread = new Thread(() -> {
            try {
                TCP.openTCPport(reports);
            } catch (IOException e) {
                log.log("TCP server error");
            }
        });

        Thread reportsThread = new Thread(() -> {
            try {
                reports.getStatisticsNotstatic();
            } catch (InterruptedException e) {
                log.log("Reports error");
            }
        }
        );

        udpThread.start();
        tcpThread.start();
        reportsThread.start();

    }
    public static int getPORT() {
        return PORT;
    }

    public static boolean isPortAvailableTCP(int port) {
        try (DatagramSocket datagramSocket  = new DatagramSocket (port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static boolean isPortAvailableUDP(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
