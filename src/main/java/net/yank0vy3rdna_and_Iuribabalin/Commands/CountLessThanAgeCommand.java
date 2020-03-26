package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.nio.ByteBuffer;

/***
 * Команда подсчета объектов с полем age меньшим, данное число
 */
public class CountLessThanAgeCommand implements Executable {
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        String[] sorted = outputCommand.getArgs();
        return String.valueOf(dispatcher.getCollectionWorker().coutAge(Integer.parseInt(sorted[1])));
    }
}
