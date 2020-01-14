package bytes;

import java.nio.ByteBuffer;

public class ByteBufferAndBytes {

    public static void main(String[] args) {
        int num = 10000000;
        int capacity = 8*num;
        byte[] bytes = new byte[capacity];

        for(int i = 0; i < capacity; i++) {
            bytes[i] = 1;
        }

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        long start = System.currentTimeMillis();
        for(int i = 0; i < num; i++) {
            ByteBufferAndBytes.bytesToLongFromOffset(bytes, 8, i*8);
        }
        start = System.currentTimeMillis() - start;
        System.out.println("Deserialized from byte[]: " + start + "ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < num; i++) {
            byteBuffer.getLong();
        }
        start = System.currentTimeMillis() - start;
        System.out.println("Deserialized from bytebuffer: " + start + "ms");

    }

    public static long bytesToLongFromOffset(byte[] byteNum, int len, int offset) {
        assert byteNum.length - offset >= len;
        long num = 0;
        for (int ix = 0; ix < len; ix++) {
            num <<= 8;
            num |= (byteNum[offset + ix] & 0xff);
        }
        return num;
    }
}
