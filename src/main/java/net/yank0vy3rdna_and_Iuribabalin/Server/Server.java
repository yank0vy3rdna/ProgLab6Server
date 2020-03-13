package net.yank0vy3rdna_and_Iuribabalin.Server;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.io.IOException;
import java.net.Socket;

public class Server {
    private final ConnectionBuilder connectionBuilder;
    private final ConnectionWorker connectionWorker;
    private final int port;
    private boolean isStarted;
    public Server(int port, Dispatcher dispatcher) throws IOException {
        connectionBuilder = new ConnectionBuilder(port);
        connectionWorker = new ConnectionWorker(dispatcher);
        this.port = port;
        isStarted = true;
    }
    public void start() throws IOException {
        while (isStarted) {
            Socket socket = connectionBuilder.accept();
            connectionWorker.processing(socket);
        }
    }
}
