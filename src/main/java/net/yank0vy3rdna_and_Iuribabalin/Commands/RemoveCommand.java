package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;

import java.nio.ByteBuffer;

/**
 * Команда, реализующая удаление элемента из коллекции по его id
 */
public class RemoveCommand implements Executable{
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        try {
            for (StoredType element : dispatcher.getCollectionWorker().getSet()) {
                if (element.getId() == Long.parseLong(outputCommand.getArgs()[0])) {
                    dispatcher.getCollectionWorker().remove(element);
                    return "Delete accept";
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return "Id do not search!!!";
        }
        return "Id is not valid";
    }
}
