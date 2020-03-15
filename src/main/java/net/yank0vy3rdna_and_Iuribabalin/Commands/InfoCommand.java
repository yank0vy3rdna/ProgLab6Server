package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.nio.ByteBuffer;

/**
 * Команда, выводящая информацию о коллекции
 */
public class InfoCommand implements Executable {

    @Override
    public String exec(String command, Dispatcher dispatcher) {

        return "Collection type: " + dispatcher.getCollectionWorker().getCollectionType() + "\nInitialization data: " + dispatcher.getCollectionWorker().getCreationDate() + "\nNumber of elements: " +  dispatcher.getCollectionWorker().getSize();

    }
    public String exec(String command, Dispatcher dispatcher, ByteBuffer byteBuffer){
        return exec(command,dispatcher);
    }
}
