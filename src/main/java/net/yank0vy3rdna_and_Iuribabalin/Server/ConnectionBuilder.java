package net.yank0vy3rdna_and_Iuribabalin.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ConnectionBuilder {
    private final int port;
    private Selector selector;
    private ServerSocketChannel serverSocket;
    public ConnectionBuilder(int port) throws IOException {
        this.port = port;
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress(port));
    }
    public Selector accept() throws IOException {
        selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                if (key.isAcceptable()) {
                    key.cancel();
                    SocketChannel client = serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ);
                    return selector;
                }
            }
        }
    }
}
