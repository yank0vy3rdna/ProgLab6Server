package net.yank0vy3rdna_and_Iuribabalin.Server;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CommandDeserializer;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;
import net.yank0vy3rdna_and_Iuribabalin.Main;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public class ConnectionWorker {
    private final Dispatcher dispatcher;
    private CommandDeserializer deserializer;

    public ConnectionWorker(Dispatcher dispatcher,CommandDeserializer deserializer){
        this.dispatcher = dispatcher;
        this.deserializer = deserializer;
    }

    public void processing(Socket socket) throws IOException, ClassNotFoundException {

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        ByteBuffer buffer;

        while (in.available() == 0) ; // Ожидание данных


        LogManager.getLogger(Main.class).info("Packet received, length: {}", in.available());

        //Чтение размера строки с командой

        byte[] bytes = new byte[4];
        in.readFully(bytes,0,4);
        buffer = ByteBuffer.wrap(bytes);
        int size = buffer.getInt();

        bytes = new byte[size];
        in.readFully(bytes, 0, size);


        OutputCommand input = deserializer.deserializer(bytes);

        LogManager.getLogger(Main.class).info("Command received: {}", input.getCommand());

        String answ;

        answ = dispatcher.dispatch(input);

        if (answ.isEmpty()){
            LogManager.getLogger(Main.class).warn("Empty answer");
        }

        out.writeUTF(answ);
        LogManager.getLogger(Main.class).info("Answer sent, length: {}", out.size());
        out.flush();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socket.close();
    }
}
