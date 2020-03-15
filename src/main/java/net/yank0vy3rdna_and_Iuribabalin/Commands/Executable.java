package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.io.IOException;
import java.nio.ByteBuffer;

/***
 * Интерфейс, который имплементируют все классы команд
 */
public interface Executable{
    String exec(String command, Dispatcher dispatcher) throws IOException;
    String exec(String command, Dispatcher dispatcher, ByteBuffer buffer) throws IOException;
}
