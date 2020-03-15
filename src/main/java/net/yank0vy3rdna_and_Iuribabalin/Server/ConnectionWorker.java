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
        byte[] bytes = new byte[4];
        in.readFully(bytes,0,4);
        buffer = ByteBuffer.wrap(bytes);
        int size = buffer.getInt();
        bytes = new byte[size];
        in.readFully(bytes, 0, size);
        buffer = ByteBuffer.wrap(bytes);
        String command = bb_to_str(buffer, StandardCharsets.UTF_8);
        bytes = in.readAllBytes();
        buffer = ByteBuffer.wrap(bytes);
        String answ;
        if(bytes.length!=0){
            answ = dispatcher.dispatch(command);
        }
        else{
            answ = dispatcher.dispatch(command, buffer);
        }
        bytes = answ.getBytes(StandardCharsets.UTF_8);
        out.write(bytes);
        out.flush();
        in.close();
        out.close();
        socket.shutdownInput();
        socket.shutdownOutput();
        socket.close();
    }
}
