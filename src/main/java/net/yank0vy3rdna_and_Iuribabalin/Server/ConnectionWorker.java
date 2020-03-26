package net.yank0vy3rdna_and_Iuribabalin.Server;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CommandDeserializer;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public class ConnectionWorker {
    private final Dispatcher dispatcher;
    private CommandDeserializer deserializer;

    public static String bb_to_str(ByteBuffer buffer, Charset charset){
        byte[] bytes;
        if(buffer.hasArray()) {
            bytes = buffer.array();
        } else {
            bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
        }
        return new String(bytes, charset);
    }

    public ConnectionWorker(Dispatcher dispatcher,CommandDeserializer deserializer){
        this.dispatcher = dispatcher;
        this.deserializer = deserializer;
    }

    public void processing(Socket socket) throws IOException, ClassNotFoundException {

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        ByteBuffer buffer;

        while (in.available() == 0) ; // Ожидание данных


        //Чтение размера строки с командой

        byte[] bytes = new byte[4];
        in.readFully(bytes,0,4);
        buffer = ByteBuffer.wrap(bytes);
        int size = buffer.getInt();

        bytes = new byte[size];
        in.readFully(bytes, 0, size);


        OutputCommand input = deserializer.deserializer(bytes);

        String answ;

        answ = dispatcher.dispatch(input);

        out.writeUTF(answ);
        out.flush();
    }
}
