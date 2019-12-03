//package Client;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class msgReceiver implements Runnable {
        private DataInputStream dis;
        Socket s;
        Client c;
        // constructor
        public msgReceiver(Socket s, DataInputStream dis, Client c) {
            this.dis = dis;
            this.s = s;
            this.c=c;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // read the message sent to this client

                    //msg = 3#translate#toAllFriends#source#dest#msg
                    //msg = 3#1#0#source#dest#msg

                    //tokenizing
                    String msg = dis.readUTF();
                    StringTokenizer stringTokenizer =new StringTokenizer(msg,"#");
                    stringTokenizer.nextToken();//3
                    String translate =stringTokenizer.nextToken();//translate
                    String toAllFriends =stringTokenizer.nextToken();//toallfriends
                    String sender =stringTokenizer.nextToken();//sender
                    String reciver=stringTokenizer.nextToken();
                    String message=stringTokenizer.nextToken();

                    //display msg
                    System.out.println(sender + " : "+ message);


                    System.out.println("Enter msg to send: ");
                    if(msg.equals("close")){
                        System.out.println(c.userName +"'s Sender ended on Close demand");

                        break;
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }


