package net.yank0vy3rdna_and_Iuribabalin;

import com.google.gson.JsonDeserializer;
import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredTypeReader;
import net.yank0vy3rdna_and_Iuribabalin.Commands.*;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonDeserializer;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonReader;
import net.yank0vy3rdna_and_Iuribabalin.FileWork.DataReader;
import net.yank0vy3rdna_and_Iuribabalin.FileWork.WorkData;
import net.yank0vy3rdna_and_Iuribabalin.JSON.JSONWorker;
import net.yank0vy3rdna_and_Iuribabalin.JSON.Workerable;
import net.yank0vy3rdna_and_Iuribabalin.Server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main
{
    public static void main( String[] args ){
        Logger logger = LogManager.getLogger(Main.class);
        try {
            int port = 2323;

            String filename;
            if (args.length != 0) {
                filename = "resources/" + args[0];
            } else {
                filename = "resources/data.json";
            }

            // Init concrete deserializer

            JsonDeserializer<StoredType> deserializer = DragonDeserializer.getDeserializer();

            // Init JSONWorker

            Workerable worker = new JSONWorker(deserializer);

            //Init commands

            HashMap<String, Executable> commandsMap = new HashMap<>();

            commandsMap.put("help", new HelpCommand());
            commandsMap.put("exit", new ExitCommand());
            commandsMap.put("add", new AddCommand());
            commandsMap.put("show", new ShowCommand());
            commandsMap.put("sum_of_age", new SumOfAgesCommand());
            commandsMap.put("clear", new ClearCommand());
            commandsMap.put("info", new InfoCommand());
            commandsMap.put("save", new SaveCommand());
            commandsMap.put("update", new UpdateCommand());
            commandsMap.put("remove_by_id", new RemoveCommand());
            commandsMap.put("execute_script", new ExecuteScriptCommand());
            commandsMap.put("count_less_than_age", new CountLessThanAgeCommand());
            commandsMap.put("remove_lower", new RemoveLoverCommand());
            commandsMap.put("remove_greater", new RemoveGreaterCommand());
            commandsMap.put("add_if_max", new AddIfMaxCommand());
            commandsMap.put("filter_contains_name", new FilterContainsNameCommand());

            // Init reader

            StoredTypeReader reader = new DragonReader();

            // Specify set type

            Set<StoredType> set = new LinkedHashSet<>();

            WorkData fileRead = new DataReader();

            Dispatcher dispatcher = new Dispatcher(commandsMap, set, reader, filename, worker, fileRead);

            Server server = new Server(port, dispatcher, new CommandDeserializer());


            logger.info("Server started on port {}, file {}", port, filename);

            server.start();

        }catch (java.util.NoSuchElementException ex){
            LogManager.getLogger(Main.class).info("Server finished");
//            System.out.println("Досвидания, можно было выйти и через exit, там уточка");
        }catch (IOException | ClassNotFoundException ex) {
            logger.error(ex);
            ex.printStackTrace();
        }
    }
}
