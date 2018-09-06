import java.util.ArrayList;
import java.util.List;

public class HelloWorld {

    public native void hello();

    static {
        //设置查找路径为当前项目路径
        System.setProperty("java.library.path", ".");
        //加载动态库的名称
        System.loadLibrary("hello");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("I am alive");
        new HelloWorld().hello();
        List<String> list = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 1000000; i++) {
                list.add(i + "asdfddddddddddddddddddd");
            }
            new HelloWorld().hello();
        }
        for (int i = 0; i < 1000000; i++) {
            list.add(i + "asdfddddddddddddddddddd");
        }
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("I am alive");
        }
    }
}