package net.yank0vy3rdna_and_Iuribabalin.Dragon;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredType;
import net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces.StoredTypeReader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DragonReader implements StoredTypeReader {
    public DragonReader(){ }

    public StoredType create(ByteBuffer buffer){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteBufferBackedInputStream(buffer));
            return (Dragon)objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
