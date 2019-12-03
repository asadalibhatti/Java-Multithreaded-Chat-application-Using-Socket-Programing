//package Client;

public  class Client {
    int serverPort;
    String serverIP;
    String language;String userName;String password;

    public Client(int serverPort, String serverIP, String language, String userName, String password) {
        this.serverPort = serverPort;
        this.serverIP = serverIP;
        this.language = language;
        this.userName = userName;
        this.password = password;
    }
}
