package ext;

public abstract class Human {

    public void fun1() {
        System.out.println("we are human");
        print();
    }

    abstract void print();

    public static void main(String[] args) {

    }
}


class Man extends Human {

    @Override
    void print() {
        System.out.println("i am man");
    }
}


class Woman extends Human {

    @Override
    void print() {
        System.out.println("i am woman");
    }
}