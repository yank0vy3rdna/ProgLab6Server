package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Команда, выполняющая скрипт
 */
public class ExecuteScriptCommand implements Executable{
    static List<String> files = new ArrayList<>();

    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) throws IOException {

        StringBuilder toPrint = new StringBuilder();
        Scanner scanner;
        scanner = dispatcher.getFileReader().getScanner(ByteBuffer.wrap(outputCommand.getExecute_commands().getBytes(StandardCharsets.UTF_8)));
        return execute_script(dispatcher, toPrint, scanner);
    }

    private String execute_script(Dispatcher dispatcher, StringBuilder toPrint, Scanner scanner) throws IOException {
        try {
            try {
                while (dispatcher.getEnabled()) {
                    String line = scanner.nextLine();
                    if (line.indexOf("execute_script") == 0) {
                        if (files.contains(line.trim().split(" ")[1])) {
                            toPrint.append("Рекурсия");
                        } else {
                            files.add(line.trim().split(" ")[1]);
                            dispatchLine(dispatcher, toPrint, line);
                        }
                    }else{
                        dispatchLine(dispatcher, toPrint, line);
                    }
                }
            }catch (NoSuchElementException ex){
                toPrint.append("\nCompleted read");
            }
            return toPrint.toString();

        }catch (ArrayIndexOutOfBoundsException ex){
            return "No filename";
        }
    }

    private void dispatchLine(Dispatcher dispatcher, StringBuilder toPrint, String line) throws IOException {
        OutputCommand outputCommand = new  OutputCommand();
        String[] splitted = line.trim().split(" ");
        outputCommand.setCommand(splitted[0]);
        String[] args = new String[splitted.length-1];
        System.arraycopy(splitted,1,args,0,splitted.length-1);
        outputCommand.setArgs(args);
        toPrint.append(dispatcher.dispatch(outputCommand));
    }
}
