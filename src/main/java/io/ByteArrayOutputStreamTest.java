package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * 将所有字节缓冲进去
 */
public class ByteArrayOutputStreamTest {

    public static void main(String[] args) throws IOException {

        outputStreamTest();
        inputStreamTest();
    }

    public static void outputStreamTest() throws IOException {
        byte[] b1 = new byte[]{0, 1, 2};
        byte[] b2 = new byte[]{3};

        ByteArrayOutputStream s1 = new ByteArrayOutputStream();
        ByteArrayOutputStream s2 = new ByteArrayOutputStream();

        s1.write(b1);
        s1.write(b2);

        s1.writeTo(s2);

        byte[] ret = s2.toByteArray();
        print(ret);
    }


    public static void inputStreamTest() throws IOException {
        byte[] b1 = new byte[]{1,2,3,4};
        ByteArrayInputStream s1 = new ByteArrayInputStream(b1);

        System.out.println("s1 available: "+s1.available());
        byte[] b2 = new byte[2];
        s1.read(b2);
        print(b2);


        System.out.println("s1 available: " + s1.available());
        s1.read(b2);
        print(b2);

        System.out.println("s1 available: " + s1.available());
    }

    public static void print(byte[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }


}
