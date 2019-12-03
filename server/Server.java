package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {

    // Vector to store active clients
    static Vector<ClientHandler> loginUsersClientHandlers = new Vector<>();


    public static void main(String[] args) throws IOException {
        // server is listening on port 1234
        ServerSocket ss = new ServerSocket(1234);

        Socket s;

        // running infinite loop for getting
        // client request
        System.out.println("Server Started");
        while (true) {
            // Accept the incoming request
            System.out.println("accepting/waiting for client to connect :");
            s = ss.accept();
            System.out.println("New client request received : " + s);
            // obtain input and output streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a new handler for this client...");

            // Create a new handler object for handling this request.
            ClientHandler mtch = new ClientHandler(s, dis, dos);

            // Create a new Thread with this object.
            Thread t = new Thread(mtch);

            //System.out.println("Adding this client to active client list");

            // add this client to active clients list
            //ar.add(mtch);

            // start the thread.
            t.start();

            // increment i for new client.
            // i is used for naming only, and can be replaced
            // by any naming scheme


        }
    }
}
