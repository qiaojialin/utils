package io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class RandomAccessFileTest {
    private static String fileName = "src/main/resources/test.myfile";
    private static File file = new File(fileName);


    public static void main(String[] args) throws IOException {
        if (file.exists())
            file.delete();

        byte[] a = new byte[10];

        RandomAccessFile randomAccess = new RandomAccessFile(file, "rw");
        randomAccess.seek(2);
        //写入6个bytes
        randomAccess.write(new byte[]{1,2,3,4,5,6});
        System.out.println("position: " + randomAccess.getFilePointer());

        //移动文件头到 offset=3 的位置，offset相对于文件头部
        randomAccess.seek(3);
        System.out.println("position: " + randomAccess.getFilePointer());

        //从 offset=3 处写入一个byte，直接覆盖原来的数据
        randomAccess.write(new byte[]{7});

        System.out.println("file length: "+randomAccess.length());


        //从头读取 10 个 bytes
        randomAccess.seek(0);
        randomAccess.read(a);
        print(a);

        //seek 到 offset=2 处读取 10 个 bytes
        randomAccess.seek(2);
        a = new byte[10];

        //读 2 个 byte，写入到 a 的第 1 个字节处
        randomAccess.read(a, 1, 2);
        print(a);

        randomAccess.close();
        file.delete();
    }


    public static void print(byte[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }

}
