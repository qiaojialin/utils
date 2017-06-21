package extend;

/**
 * Created by qiaojialin on 2017/6/9.
 */
public class Father {
    protected void print() {
        System.out.println("father");
    }

    public static void main(String[] args) {
        Father a = new Child();
        a.print();
    }
}

class Child extends Father {

    @Override
    protected void print() {
        System.out.println("child");
    }
}
