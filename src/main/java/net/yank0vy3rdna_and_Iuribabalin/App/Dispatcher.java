package net.yank0vy3rdna_and_Iuribabalin.App;

import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredTypeReader;
import net.yank0vy3rdna_and_Iuribabalin.Commands.Executable;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;
import net.yank0vy3rdna_and_Iuribabalin.FileWork.WorkData;
import net.yank0vy3rdna_and_Iuribabalin.JSON.Workerable;
import net.yank0vy3rdna_and_Iuribabalin.Main;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/***
 * Dispatcher - class contains all objects and make them linked.
 */
public class Dispatcher {
    private final Map<String, Executable> commandsMap = new HashMap<>();
    private final CollectionWorker collectionWorker;
    private final StoredTypeReader reader;
    private final Workerable worker;
    private final String filename;
    private final WorkData fileReader;
    private boolean enabled = true;

    public Dispatcher(HashMap<String, Executable> commands, Set<StoredType> list, StoredTypeReader reader, String filename, Workerable worker, WorkData fileRead){
        this.reader = reader;
        this.filename = filename;
        this.worker = worker;
        this.fileReader = fileRead;
        collectionWorker = new CollectionWorker(list);
        collectionWorker.init(filename,worker);
        commandsMap.putAll(commands);
    }

    public String dispatch(OutputCommand outputCommand) throws IOException {

        if(commandsMap.get(outputCommand.getCommand().toLowerCase()) != null) {
            Executable command = commandsMap.get(outputCommand.getCommand().split(" ")[0]);
            return command.exec(outputCommand, this);
        }else if (!outputCommand.getCommand().toLowerCase().equals("")) {
            LogManager.getLogger(Main.class).warn("No command");
            return "No command\n";
        }
        LogManager.getLogger(Main.class).warn("Empty command");
        return ">>";
    }

    public CollectionWorker getCollectionWorker() {
        return collectionWorker;
    }

    public Workerable getWorker(){
        return worker;
    }

    public String getFilename(){
        return filename;
    }

    public boolean getEnabled(){
        return this.enabled;
    }

    public WorkData getFileReader() {
        return fileReader;
    }

}
