package net.yank0vy3rdna_and_Iuribabalin.Server;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CommandDeserializer;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.Socket;

public class Server {
    private final ConnectionBuilder connectionBuilder;
    private final ConnectionWorker connectionWorker;
    private boolean isStarted;
    public Server(int port, Dispatcher dispatcher, CommandDeserializer deserializer) throws IOException {
        connectionBuilder = new ConnectionBuilder(port);
        connectionWorker = new ConnectionWorker(dispatcher,deserializer);
        isStarted = true;
    }
    public void start() throws IOException, ClassNotFoundException {
        while (isStarted) {
            Socket socket = connectionBuilder.accept();
            LogManager.getLogger().info("Connection with client({}) accepted", socket.getRemoteSocketAddress());
            connectionWorker.processing(socket);
        }
    }
}
