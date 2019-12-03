package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


class ClientHandler implements Runnable {
    //Scanner scn = new Scanner(System.in);
    String userName;

    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    //boolean isloggedin;

    // constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        //this.name = name;
        this.s = s;
        //this.isloggedin = true;
    }

    @Override
    public void run() {

        while (true) {// clientHandler to handle infinte req of client n
            String msg;
            try {
                // receiving the Req from client after handShake

                msg = dis.readUTF();
                System.out.println("Msg req: " + msg);

                //System.out.println(msg.substring(0, 1));
                if (msg.substring(0, 1).equals("0")) {//signup
                    //signup();
                    ServerUtility.signUp(msg);
                }

                else if (msg.substring(0, 1).equals("1")) {//login
                    //login();
                    if (ServerUtility.login(msg, this)) {//add to userLogin
                        //login
                        dos.writeUTF("accepted");
                        System.out.println("login accepted of " + s);
                    } else {
                        //not login
                        System.out.println("login NOT accepted of " + s);
                        dos.writeUTF("Not accepted");
                    }
                }


                else if (msg.substring(0, 1).equals("2")) {//files
                    //files();
                }

                else if (msg.substring(0, 1).equals("3")) {//msg
                    //msg = 3#translate#toAllFriends#source#dest#msg
                    //msg = 3#1#0#source#dest#msg
                    //chk tran
                    //
                    //api taranls
                    //msg=msgTransl
                    ServerUtility.forwardMsgToDestClient(msg);

                }

                else if (msg.substring(0, 1).equals("4")) {
                    //free
                }
                else if (msg.substring(0, 1).equals("5")) {//video
                    //signup();
                }
                else if (msg.substring(0, 1).equals("6")) {//signup
                    //signup();
                }
                else if (msg.substring(0, 1).equals("X")) {//signup
                    //end handler
                    break;
                }
                //handles msgs,files sending reciving
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Client.ClientMgr.Client handler for " + s + " ended");
    }
}
