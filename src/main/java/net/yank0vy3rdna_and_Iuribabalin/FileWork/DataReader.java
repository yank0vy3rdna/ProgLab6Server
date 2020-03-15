package net.yank0vy3rdna_and_Iuribabalin.FileWork;

import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;

import java.io.*;
import java.nio.ByteBuffer;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DataReader implements WorkData {
    public DataReader(){}

    @Override
    public Scanner getScanner(ByteBuffer buffer) {
        return new Scanner(new ByteBufferBackedInputStream(buffer));
    }

    @Override
    public Scanner getScanner(String filename) throws IOException {
        BufferedReader reader = new BufferedReader((new InputStreamReader(new FileInputStream(filename))));
        char[] charBuffer = new char[8 * 1024];
        StringBuilder builder = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = reader.read(charBuffer, 0, charBuffer.length)) != -1) {
            builder.append(charBuffer, 0, numCharsRead);
        }
        InputStream targetStream = new ByteArrayInputStream(
                builder.toString().getBytes(StandardCharsets.UTF_8));
        return new Scanner(targetStream);
    }
}
