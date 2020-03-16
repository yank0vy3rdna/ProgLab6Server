package net.yank0vy3rdna_and_Iuribabalin.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionBuilder {
    private final int port;
    public ConnectionBuilder(int port) throws IOException {
        this.port = port;
    }
    public Socket accept() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        serverSocket.close();
        return socket;
    }
}
