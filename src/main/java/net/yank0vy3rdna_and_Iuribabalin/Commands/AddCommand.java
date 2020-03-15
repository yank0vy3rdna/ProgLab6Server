package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.nio.ByteBuffer;

/***
 * Add - команда добавления нового элемента
 */
public class AddCommand implements Executable{
    @Override
    public String exec(String command, Dispatcher dispatcher) {
        /*if(dispatcher.getCollectionWorker().insert(dispatcher.getReader().create())) {
            return "Добавлено";
        }else{
            return "Такой элемент уже есть";
        }*/
        return null;
    }
    @Override
    public String exec(String command, Dispatcher dispatcher, ByteBuffer buffer) {
        if(dispatcher.getCollectionWorker().insert(dispatcher.getReader().create(buffer))) {
            return "Добавлено";
        }else{
            return "Такой элемент уже есть";
        }
    }
}
