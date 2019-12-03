//package Client;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class msgSender implements Runnable {
    Scanner scn = new Scanner(System.in);
    Client c;
    private DataOutputStream dos;
    Socket s;


    // constructor
    public msgSender(Socket s, DataOutputStream dos, Client c) {
        //this.dis = dis;
        this.dos = dos;
        this.c=c;
        this.s = s;

    }

    @Override
    public void run() {
        while (true) {
            // read the message from client to deliver.
            System.out.println("Enter msg to send: ");
            String msg = scn.nextLine();


            if(msg.equals("close")){
                System.out.println(c.userName +"'s Sender ended on Close demand");
                break;
            }
            try {
                // write on the output stream

                System.out.println("Enter Destination : ");
                String dest = scn.nextLine();

                //System.out.println("Enter msg to send: ");
                String  tranlate = "#0";

                String toAllFriends="#0";
                String source="#"+c.userName;



                //msg = 3#translate#toAllFriends#source#dest#msg
                //msg = 3#1#0#source#dest#msg

                msg="3"+tranlate+toAllFriends+source+"#"+dest+"#"+msg;
                dos.writeUTF(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


