package prim;

public class ObjectTest {

    private static int length = 100000000;

    private static int[] a = new int[length];

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i = 0; i < length; i++) {
            getInt(i);
        }
        start = System.currentTimeMillis() - start;
        System.out.println("getInt: " + start + "ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < length; i++) {
            getObject(i);
        }
        start = System.currentTimeMillis() - start;
        System.out.println("getObject: " + start + "ms");

    }

    public static int getInt(int i) {
        return a[i];
    }

    public static Object getObject(int i) {
        return a[i];
    }
}
