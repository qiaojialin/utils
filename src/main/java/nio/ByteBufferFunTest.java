package nio;

import java.nio.ByteBuffer;

public class ByteBufferFunTest {
    public static void main(String[] args) {
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer1.capacity(); i++) {
            buffer1.put((byte)i);
        }

        buffer1.position(3);
        buffer1.limit(7);
        System.out.println("origin: " + buffer1);

        ByteBuffer buffer2 = buffer1.slice();
        System.out.println("slice: " + buffer2);

        ByteBuffer buffer3 = buffer1.duplicate();
        System.out.println("duplicate: " + buffer3);

        ByteBuffer buffer4 = ByteBuffer.wrap(buffer1.array());
        System.out.println("wrap: " + buffer4);

    }
}
