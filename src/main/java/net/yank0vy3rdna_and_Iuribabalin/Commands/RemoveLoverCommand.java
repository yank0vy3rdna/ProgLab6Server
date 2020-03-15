package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;

import java.nio.ByteBuffer;

/**
 * Команда, реализующая удаление всех элементов меньших данного
 */
public class RemoveLoverCommand implements Executable {
    @Override
    public String exec(String command, Dispatcher dispatcher, ByteBuffer byteBuffer){
        StoredType object = dispatcher.getReader().create(byteBuffer);
        dispatcher.getCollectionWorker().removeLower(object);
        return "Removed successfully";
    }
    public String exec(String command, Dispatcher dispatcher){
        return null;
    }
}
