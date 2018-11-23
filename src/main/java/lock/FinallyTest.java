package lock;

public class FinallyTest {
    public static void main(String[] args) {
        try {
            return;
        } finally {
            System.out.println("aaa");
        }
    }
}
