package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.io.IOException;
import java.nio.ByteBuffer;
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
    public String exec(String command, Dispatcher dispatcher) throws IOException {

        String[] sorted = command.split(" ");

        StringBuilder toPrint = new StringBuilder();
        Scanner scanner;
        try {
             scanner = dispatcher.getFileReader().getScanner("resources/" + sorted[1]);
        } catch (IOException | ArrayIndexOutOfBoundsException e){
            try {
                scanner = dispatcher.getFileReader().getScanner("resources/" + sorted[1] + ".txt");
            }catch (IOException | ArrayIndexOutOfBoundsException ex){
                return "No filename";
            }
        }
        return execute_script(dispatcher, toPrint, scanner);
    }

    @Override
    public String exec(String command, Dispatcher dispatcher, ByteBuffer buffer) throws IOException {

        StringBuilder toPrint = new StringBuilder();
        Scanner scanner;
        scanner = dispatcher.getFileReader().getScanner(buffer);
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
                            toPrint.append(dispatcher.dispatch(line.trim()));
                        }
                    }else{
                        toPrint.append(dispatcher.dispatch(line.trim()));
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
}
