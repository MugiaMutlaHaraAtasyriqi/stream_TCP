import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChatClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 9876;

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            // Memasukan nama klien
            System.out.println("Enter your name:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();

            // Mengirim pesan ke server
            sendMessage(clientSocket, name);

            // Menutup socket setelah mengirim pesan
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(DatagramSocket clientSocket, String name) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Memasukan Pesan
                System.out.println("Enter your message (or type 'exit' to quit):");
                String message = reader.readLine();

                if ("exit".equalsIgnoreCase(message)) {
                    break; // keluar dari loop jika pengguna mengetik 'exit'
                }

                // Mengirim pesan ke server
                byte[] sendData = (name + ":" + message).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        InetAddress.getByName(ChatClient.SERVER_IP), ChatClient.SERVER_PORT);
                clientSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}