package nio;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
        byte[] bytes = new byte[100];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.getLong();
        System.out.println(byteBuffer);

        ByteBuffer buffer1 = byteBuffer.slice();
        buffer1.limit(50);
        System.out.println(buffer1);

        ByteBuffer buffer2 = byteBuffer.slice();
        buffer2.position(50);
        System.out.println(buffer2);

        byte[] bytes1 = new byte[255];
        bytes1[0] = -13;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes1);
        int a = inputStream.read();
        System.out.println(a);

        ByteBuffer buffer = ByteBuffer.wrap(bytes1);
        int b = buffer.get();
        System.out.println(b);
        System.out.println("243: " + toBit(243));
        System.out.println("-13: " + toBit(-13));
        System.out.println("&: " + toBit(0xFF));
        System.out.println(-13 & 0xFF);
    }

    private static String toBit(int b) {
        return ""+ (int) ((b >> 15) & 0x1) + (int) ((b >> 14) & 0x1)
                + (int) ((b >> 13) & 0x1) + (int) ((b >> 12) & 0x1)
                + (int) ((b >> 11) & 0x1) + (int) ((b >> 10) & 0x1)
                + (int) ((b >> 9) & 0x1) + (int) ((b >> 8) & 0x1)
                + (int) ((b >> 7) & 0x1) + (int) ((b >> 6) & 0x1)
                + (int) ((b >> 5) & 0x1) + (int) ((b >> 4) & 0x1)
                + (int) ((b >> 3) & 0x1) + (int) ((b >> 2) & 0x1)
                + (int) ((b >> 1) & 0x1) + (int) ((b >> 0) & 0x1);
    }

    private static String toBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

}
