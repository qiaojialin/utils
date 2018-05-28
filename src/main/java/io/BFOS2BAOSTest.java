package io;

import java.io.*;

/**
 * BufferedOutputStream 缓存 8Kb 的数据，超过 8Kb 就刷，没满需要手动flush
 */
public class BFOS2BAOSTest {

    public static void main(String[] args) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //默认缓冲区大小 8Kb
        BufferedOutputStream bfos = new BufferedOutputStream(baos);

        //向BufferedOutputStream中写入 8Kb
        for(int i = 0; i < 8192; i++) {
            bfos.write(1);
        }
        System.out.println(baos.size());

        //再写入 3 bytes，触发 flush，将缓冲区的 8Kb 刷入 ByteArrayOutputStream
        //并将3 bytes 写入缓冲区
        bfos.write(new byte[]{1,2,3});
        System.out.println(baos.size());

        //手动flush
        bfos.flush();
        bfos.close();
        System.out.println(baos.size());

        baos.close();

    }
}
