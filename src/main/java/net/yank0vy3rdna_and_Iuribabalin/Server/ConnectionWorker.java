package net.yank0vy3rdna_and_Iuribabalin.Server;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConnectionWorker {
    private final Dispatcher dispatcher;

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

    public ConnectionWorker(Dispatcher dispatcher){
        this.dispatcher = dispatcher;
    }

    public void processing(Socket socket) throws IOException {
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        ByteBuffer buffer;

        while (in.available()==0); // Ожидание данных


        //Чтение размера строки с командой

        byte[] bytes = new byte[4];
        in.readFully(bytes,0,4);
        buffer = ByteBuffer.wrap(bytes);
        int size = buffer.getInt();


        // Чтение строки с командой

        bytes = new byte[size];
        in.readFully(bytes, 0, size);
        buffer = ByteBuffer.wrap(bytes);
        String command = bb_to_str(buffer, StandardCharsets.UTF_8);


        String answ;
        System.out.println(in.available());
        // Если есть, чтение доп данных
        if(in.available()!=0){
            bytes = new byte[in.available()];
            in.readFully(bytes, 0, in.available());
            buffer = ByteBuffer.wrap(bytes);
            answ = dispatcher.dispatch(command, buffer);
        }
        else{
            answ = dispatcher.dispatch(command);
        }

        // Отправка ответа

        out.writeUTF(answ);
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
