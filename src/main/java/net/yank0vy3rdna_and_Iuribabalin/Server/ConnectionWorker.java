package net.yank0vy3rdna_and_Iuribabalin.Server;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CommandDeserializer;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;
import net.yank0vy3rdna_and_Iuribabalin.Main;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;


public class ConnectionWorker {
    private final Dispatcher dispatcher;
    private CommandDeserializer deserializer;

    public ConnectionWorker
            (Dispatcher dispatcher,CommandDeserializer deserializer){
        this.dispatcher = dispatcher;
        this.deserializer = deserializer;
    }

    public void processing(Selector selector) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel channel = null;
        while (channel == null){
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                if(key.isReadable()){
                    channel = (SocketChannel)key.channel();
                    channel.read(buffer);
                    buffer.flip();
                    channel.register(selector, SelectionKey.OP_WRITE);

                    break;
                }
            }
        }


        LogManager.getLogger(Main.class).info("Packet received, length: {}", buffer.array().length);

        //Чтение размера строки с командой

        byte[] bytes =buffer.array();

        OutputCommand input = deserializer.deserializer(bytes);

        LogManager.getLogger(Main.class).info("Command received: {}", input.getCommand());

        String answ;

        answ = dispatcher.dispatch(input);
        buffer.clear();
        if (answ.isEmpty()){
            LogManager.getLogger(Main.class).warn("Empty answer");
        }
        bytes = answ.getBytes(StandardCharsets.UTF_8);
        buffer = ByteBuffer.wrap(bytes);

        channel = null;
        while (channel == null){
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                if(key.isWritable()){
                    channel = (SocketChannel)key.channel();
                    while(buffer.hasRemaining()) {
                        channel.write(buffer);
                    }
                    break;
                }
            }
        }

        LogManager.getLogger(Main.class).info("Answer sent, length: {}", buffer.array().length);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.socket().close();
        channel.close();
        selector.close();
    }
}
