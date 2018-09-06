package stackoverflow;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TestStackOverFLow {

    public static void main(String[] args) {
        oom();
        recursive();
    }

    public static void recursive() {
        recursive();
    }

    public static void oom() {
        List<ByteBuffer> buffers = new ArrayList<>();
        while(true){
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 1024);
            buffers.add(buffer);
        }
    }

}
