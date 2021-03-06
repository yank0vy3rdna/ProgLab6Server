package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonReader;

import java.nio.ByteBuffer;

/***
 * Команда добавления при условии, что элемент больше всех тех, что уже находятся в коллекции
 */
public class AddIfMaxCommand  implements Executable {
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        StoredType object = outputCommand.getDragon();
        if (object == null){
            object = (new DragonReader()).create("null", outputCommand.getScanner());
        }
        if (dispatcher.getCollectionWorker().ifMax(object)) {
            dispatcher.getCollectionWorker().insert(object);
            return "Добавлено";
        }else{
            return "Не максимальный";
        }
    }
}