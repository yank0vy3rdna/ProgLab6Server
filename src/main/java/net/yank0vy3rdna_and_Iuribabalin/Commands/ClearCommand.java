package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.nio.ByteBuffer;

/***
 * Команда очищения коллекции
 */
public class ClearCommand implements Executable{
    @Override
    public String exec(String command, Dispatcher dispatcher) {
        dispatcher.getCollectionWorker().clear();
        return "Collection clear";
    }
    @Override
    public String exec(String command, Dispatcher dispatcher, ByteBuffer buffer) {
        dispatcher.getCollectionWorker().clear();
        return "Collection clear";
    }
}
