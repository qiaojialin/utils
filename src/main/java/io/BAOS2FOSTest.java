package io;

import java.io.*;

/**
 * ByteArrayOutputStream to FileOutputStream
 */
public class BAOS2FOSTest {

    private static final String fileName = "src/main/resources/test.myfile";
    private static final File file = new File(fileName);

    public static void main(String[] args) throws IOException {
        if (file.exists())
            file.delete();

        byte[] b1 = new byte[]{0, 1, 2};
        ByteArrayOutputStream s1 = new ByteArrayOutputStream();
        s1.write(b1);

        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        s1.writeTo(fileOutputStream);
        s1.flush();
        s1.close();

        read();

        file.delete();
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
