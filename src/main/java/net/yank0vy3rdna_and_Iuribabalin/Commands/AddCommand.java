package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.Dragon;

import java.nio.ByteBuffer;

/***
 * Add - команда добавления нового элемента
 */
public class AddCommand implements Executable{
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        StoredType object = outputCommand.getDragon();
        if(dispatcher.getCollectionWorker().insert(object)) {
            return "Добавлено";
        }else{
            return "Такой элемент уже есть";
        }
    }
}
