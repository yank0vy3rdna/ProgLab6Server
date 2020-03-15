package net.yank0vy3rdna_and_Iuribabalin.FileWork;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public interface WorkData {
    Scanner getScanner(ByteBuffer buffer) throws IOException;
    Scanner getScanner(String filename) throws IOException;
}
