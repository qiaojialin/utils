package io;

import java.io.*;

public class FileIOStreamTest {

    private static final String fileName = "src/main/resources/test.myfile";
    private static final File file = new File(fileName);

    public static void main(String[] args) {
        if (file.exists())
            file.delete();

        //写入，不追加
        write(new byte[]{1,2,3,4,5,6}, false);
        read();

        //写入，不追加
        write(new byte[]{7,8}, false);
        read();

        //写入，追加
        write(new byte[]{1,2,3}, true);
        read();
    }

    private static void write(byte[] a, boolean mode) {
        try (OutputStream out = new FileOutputStream(file, mode)) {
            out.write(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //读取 10 个 bytes 验证
    public static void read() {
        try (InputStream in = new FileInputStream(file)) {
            byte[] a = new byte[10];
            in.read(a);
            for(int i = 0; i < a.length; i++) {
                System.out.print(a[i]);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
