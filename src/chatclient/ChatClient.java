/**
 * Portfolio Question 4
 * SangJoon Lee
 * 30024165
 * 23/04/2021
 */
package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
//        InetAddress ip = InetAddress.getLocalHost();
//        String host = ip.getHostName();
            Socket sock = new Socket("localhost", 9999);
    //        System.out.println(sock.getInetAddress().getLocalHost().getHostName() + ": connected");
            while (true) {
                System.out.print("ID: ");
                String id = sc.next();
                System.out.print("Password: ");
                String password = sc.next();
                String sendString = id + "," + password;
                PrintWriter pw = new PrintWriter(sock.getOutputStream());
                pw.println(sendString);
                pw.flush();
                BufferedReader buf = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String receiveString;
                receiveString = buf.readLine();
                System.out.println(receiveString);
                if (receiveString.equals("verified")) {
                    System.out.println(id + " logged in");
                    break;
                }
            }

            ReceiveThread rt = new ReceiveThread();
            rt.setSocket(sock);

            SendThread st = new SendThread();
            st.setSocket(sock);

            st.start();
            rt.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
