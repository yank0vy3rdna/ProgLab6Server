package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.nio.ByteBuffer;

/***
 * Команда очищения коллекции
 */
public class ClearCommand implements Executable{
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        dispatcher.getCollectionWorker().clear();
        return "Collection clear";
    }
}
