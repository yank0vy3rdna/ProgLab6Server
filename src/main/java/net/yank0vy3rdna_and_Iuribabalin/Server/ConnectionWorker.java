package net.yank0vy3rdna_and_Iuribabalin.Server;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.io.IOException;
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
        SocketChannel channel = socket.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4);
        channel.read(buffer);
        int size = buffer.getInt();
        buffer = ByteBuffer.allocate(size);
        channel.read(buffer);
        String command = bb_to_str(buffer, StandardCharsets.UTF_8);
        buffer = ByteBuffer.allocate(1024);
        String answ;
        if(channel.read(buffer)!=-1){
            answ = dispatcher.dispatch(command);
        }
        else{
            answ = dispatcher.dispatch(command, buffer);
        }
        byte[] bytes = answ.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buf = ByteBuffer.allocate(bytes.length);
        buf.clear();
        buf.put(bytes);
        channel.write(buf);
        channel.finishConnect();
    }
}
