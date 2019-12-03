package server;

import java.io.IOException;
import java.util.StringTokenizer;

class ServerUtility {
    //1#username#password#otherinfo
    public static void signUp(String msg) {
        StringTokenizer stringTokenizer = new StringTokenizer(msg, "#");
        stringTokenizer.nextToken();
        String userName = stringTokenizer.nextToken();
        String pasword = stringTokenizer.nextToken();

        //save to database

    }

    public static boolean login(String msg, ClientHandler clientHandler) {
        StringTokenizer stringTokenizer = new StringTokenizer(msg, "#");
        stringTokenizer.nextToken();//1
        clientHandler.userName = stringTokenizer.nextToken();
        String pasword = stringTokenizer.nextToken();

        //if(check database for username and Pasword)
        Server.loginUsersClientHandlers.add(clientHandler);
        return true;
        //}

        //return false


    }

    public static void forwardMsgToDestClient(String msg) {
        //msg = 3#translate#toAllFriends#source#dest#msg
        //msg = 3#1#0#source#dest#msg
        StringTokenizer stringTokenizer = new StringTokenizer(msg, "#");
        stringTokenizer.nextToken();//3
        stringTokenizer.nextToken();//tranlat
        stringTokenizer.nextToken();//toallfriends
        String source = stringTokenizer.nextToken();
        String destUserName = stringTokenizer.nextToken();
        //String message = stringTokenizer.nextToken();
        boolean found = false;
        for (ClientHandler clientHandler : Server.loginUsersClientHandlers
        ) {
            if (clientHandler.userName.equals(destUserName)) {
                try {
                    // write on the output stream
                    clientHandler.dos.writeUTF(msg);
                    found = true;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!found) {
            System.out.println("Reciver __"+ destUserName+ ", not found of __"+ source +" Msg");
        }
    }
}