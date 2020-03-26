package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonReader;

import java.nio.ByteBuffer;

/**
 * Команда, реализующая удаление всех элементов, больших данного
 */

public class RemoveGreaterCommand implements Executable {
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher){
        StoredType object = outputCommand.getDragon();
        if (object == null){
            object = (new DragonReader()).create("null", outputCommand.getScanner());
        }
        dispatcher.getCollectionWorker().removeGreater(object);
        return "Removed successfully";
    }
}
