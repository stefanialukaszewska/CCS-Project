import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP {
    public static void openUDPport() throws IOException {

        int PORT = Main.getPORT();
        DatagramSocket socket;
        DatagramPacket packetReceived;
        String message;
        String response = "CCS FOUND";
        String searchFor = "CCS DISCOVER";
        byte[] buf = new byte[1024];
        Log log = new Log("UDP");

        socket = new DatagramSocket(PORT);

        while(true){
            packetReceived = new DatagramPacket(buf, buf.length);
            socket.receive(packetReceived);

            String messengerAdrress = packetReceived.getAddress().getHostAddress();
            int messengerPort = packetReceived.getPort();

            log.log("Packet received from: " + messengerAdrress + ":" + messengerPort);
            message = new String(packetReceived.getData(),0, packetReceived.getLength());
            log.log("Message received: "+message);

            if(message.length()>=searchFor.length()){
                String cutMessage = message.substring(0, searchFor.length());

                if (cutMessage.equals("CCS DISCOVER")) {
                    byte[] responseData = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, InetAddress.getByName(messengerAdrress), messengerPort);
                    socket.send(sendPacket);
                }
            }
        }
    }
}
