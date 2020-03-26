package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;

import java.nio.ByteBuffer;
import java.util.stream.Collectors;

/**
 * Команда, выводящая те элементы, поле name которых содержит данную строку
 */
public class FilterContainsNameCommand implements Executable{
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        return getString(outputCommand.getArgs()[0], dispatcher);
    }

    private String getString(String regex, Dispatcher dispatcher) {
        StringBuilder answ = new StringBuilder();
        for (StoredType element: dispatcher.getCollectionWorker().getSet().stream().filter(
                x->x.getName().contains(regex)).collect(Collectors.toSet())) {
            answ.append(element.toString(dispatcher.getWorker())).append("\n");
        }
        answ.append("It is full info");
        return answ.toString();
    }
}
