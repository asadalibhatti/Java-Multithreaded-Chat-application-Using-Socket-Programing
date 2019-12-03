//package Client;// Java implementation for multithreaded chat client
// Save file as Client.ClientMgr.Client.java

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMgr {
    public static void main(String args[]) throws UnknownHostException, IOException {
        Scanner scn = new Scanner(System.in);
        int serverPort = 1234;
        String language = "eng";
        System.out.println("Enter UserName: ");
        String userName = scn.nextLine();
        String password = "pasword";
        String serverIP = "127.0.0.1";
        Client c = new Client(serverPort, serverIP, language, userName, password);


        // getting localhost ip
        InetAddress ip = InetAddress.getByName(serverIP);
        // establish the connection
        Socket s = new Socket(ip, serverPort);
        System.out.println("Client.ClientMgr.Client Connected with ServerPort " + serverPort);


        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());


        //send login req
        System.out.println("sending login request to server");
        boolean lgin = false;
        while (!lgin) {
            dos.writeUTF("1#" + c.userName + "#" + c.password);
            if (dis.readUTF().equals("accepted")) {
                lgin = true;
                System.out.println("login succesful");

                //starting msgSender, msgREciver
                Thread readMessage = new Thread(new msgSender(s, dos, c));
                Thread sendMessage = new Thread(new msgReceiver(s, dis, c));
                readMessage.start();
                sendMessage.start();
                System.out.println("Sender and Reciver thread Started");

            } else {
                System.out.println("login failed , try again");

            }
        }
    }
}
