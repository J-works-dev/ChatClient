package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread {
    private Socket socket;
    
    @Override
    public void run() {
        super.run();
        
        try {
            BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receiveString;
            
            while (true) {
                receiveString = buf.readLine();
                if (receiveString != null) {
                    System.out.println("Admin : " + receiveString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setSocket(Socket s) {
        socket = s;
    }
}
