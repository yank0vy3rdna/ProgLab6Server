package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;

import java.nio.ByteBuffer;

/**
 * Команда, реализующая удаление всех элементов меньших данного
 */
public class RemoveLoverCommand implements Executable {
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher){
        StoredType object = outputCommand.getDragon();
        dispatcher.getCollectionWorker().removeLower(object);
        return "Removed successfully";
    }
}
