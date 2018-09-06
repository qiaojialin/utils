import org.junit.Test;
import java.nio.ByteBuffer;

public class TestDirectMemory {
    /**
     * 测试DirectMemory和Heap读写速度。
     */
    @Test
    public void testDirectMemoryWriteAndReadSpeed() {
        long tsStart = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocateDirect(4000);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100; j++) {
                buffer.putInt(j);
            }
            buffer.flip();
            for (byte j = 0; j < 100; j++) {
                buffer.getInt();
            }
            buffer.clear();
        }
        System.out.println("DirectMemory读写耗用： " + (System.currentTimeMillis() - tsStart) + " ms");
        tsStart = System.currentTimeMillis();
        buffer = ByteBuffer.allocate(4000);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100; j++) {
                buffer.putInt(j);
            }
            buffer.flip();
            for (byte j = 0; j < 100; j++) {
                buffer.getInt();
            }
            buffer.clear();
        }
        System.out.println("Heap读写耗用： " + (System.currentTimeMillis() - tsStart) + " ms");
    }

    /**
     * 测试DirectMemory和Heap内存申请速度。
     */
    @Test
    public void testDirectMemoryAllocate() {
        long tsStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(400000);

        }
        System.out.println("DirectMemory申请内存耗用： " + (System.currentTimeMillis() - tsStart) + " ms");
        tsStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(400000);
//            byte[] bytes=new byte[400000];
//            buffer.get(bytes);
        }
        System.out.println("Heap申请内存耗用： " + (System.currentTimeMillis() - tsStart) + " ms");
    }
}