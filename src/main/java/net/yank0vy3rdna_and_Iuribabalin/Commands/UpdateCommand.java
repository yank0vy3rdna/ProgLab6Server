package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonReader;

import java.nio.ByteBuffer;

/**
 * Команда, заменяющая значение элемента с данным id на данное
 */
public class UpdateCommand implements Executable {

    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        try {
            for (StoredType element : dispatcher.getCollectionWorker().getSet()) {
                if (element.getId() == Long.parseLong(outputCommand.getArgs()[0])) {
                    dispatcher.getCollectionWorker().remove(element);
                    StoredType object = outputCommand.getDragon();
                    if (object == null){
                        object = (new DragonReader()).create("null", outputCommand.getScanner());
                    }
                    dispatcher.getCollectionWorker().insert(object);
                    return "Update data";
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return "Id wasn't found!!!";
        }
        return "Id is not valid";
    }
}
