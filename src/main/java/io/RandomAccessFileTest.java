package io;

import serialize.BytesUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class RandomAccessFileTest {
    private static final String fileName = "src/main/resources/test.myfile";

    public static void main(String[] args) throws IOException {
        byte bt = 127;
        File file = new File(fileName);
        if (file.exists())
            file.delete();

        RandomAccessFile out = new RandomAccessFile(file, "rw");

        //write to file directly
        out.write(bt);  // 1 byte
        out.write(BytesUtils.intToBytes(123123)); // 4 bytes
        out.write(BytesUtils.boolToBytes(false));  // 1 bytes
        out.write(BytesUtils.longToBytes(134578845L));  // 8 bytes
        out.write(BytesUtils.floatToBytes(13547.2376f)); // 4 bytes
        System.out.println("position: " + out.length());
        out.write(BytesUtils.doubleToBytes(520d));  // 8 bytes
        System.out.println("position: " + out.length());
        out.close();

        // test correctness
        RandomAccessFile input = new RandomAccessFile(fileName, "r");

        System.out.println(input.readByte());
        System.out.println(input.readInt());
        System.out.println(input.readBoolean());
        input.seek(5);  // seek 到距文件首部 5 bytes 的距离
        byte[] a = new byte[1];
        input.read(a);
        System.out.println(BytesUtils.bytesToBool(a));
        System.out.println(input.readLong());
        System.out.println(input.readFloat());
        System.out.println(input.readDouble());
        input.close();
        file.delete();
    }
}
