package net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces;

import java.nio.ByteBuffer;

public interface StoredTypeReader {
    StoredType create(ByteBuffer buffer);
}
