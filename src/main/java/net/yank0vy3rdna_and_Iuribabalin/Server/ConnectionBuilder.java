package net.yank0vy3rdna_and_Iuribabalin.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionBuilder {
    private final ServerSocket serverSocket;
    public ConnectionBuilder(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public Socket accept() throws IOException {
        return serverSocket.accept();
    }
}
