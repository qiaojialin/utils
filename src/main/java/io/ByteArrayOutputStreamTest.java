package io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * 将所有字节缓冲进去
 */
public class ByteArrayOutputStreamTest {

    public static void main(String[] args) throws IOException {
        byte[] b1 = new byte[]{0, 1, 2};
        byte[] b2 = new byte[]{3};
        byte[] b3 = new byte[]{4, 5, 6, 7};

        ByteArrayOutputStream s1 = new ByteArrayOutputStream();
        ByteArrayOutputStream s2 = new ByteArrayOutputStream();
        ByteArrayOutputStream s3 = new ByteArrayOutputStream();
        ByteArrayOutputStream total = new ByteArrayOutputStream();

        s1.write(b1);
        s2.write(b2);
        s3.write(b3);

        System.out.println(s1.size());
        System.out.println(s2.size());
        System.out.println(s3.size());

        //将 s1，s2，s3 的数据写入 total 流
        s1.writeTo(total);
        s2.writeTo(total);
        s3.writeTo(total);

        s2.write(b1);

        byte[] ret = total.toByteArray();
        for (int i = 0; i < ret.length; i++) {
            System.out.print(ret[i]);
        }

    }
}
