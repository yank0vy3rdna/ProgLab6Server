package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.Main;
import org.apache.logging.log4j.LogManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CommandDeserializer {
    public OutputCommand deserializer(byte[] bytes ) {
        try {
            ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes));

            return (OutputCommand) input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            LogManager.getLogger(Main.class).error(e);
            e.printStackTrace();
        }
        return null;
    }
}
