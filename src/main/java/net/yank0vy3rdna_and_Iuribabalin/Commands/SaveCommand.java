package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.nio.ByteBuffer;

/**
 * Команда, реализующая сохранение коллекции в файл
 */
public class SaveCommand implements Executable{

    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        dispatcher.getCollectionWorker().save(dispatcher.getFilename(),dispatcher.getWorker());
        return "Сохранено";
    }
}
