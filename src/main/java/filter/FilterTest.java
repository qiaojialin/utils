package filter;


interface Filter<T extends Comparable <T>> {
    boolean satisfy(T data);

//    boolean satisfy(Digest digest);
}


class Eq<T extends Comparable<T>> implements Filter<T> {

    T value;

    public Eq(T value) {
        this.value = value;
    }

    @Override
    public boolean satisfy(T data) {
        return data.compareTo(value) == 0;
    }

}

class Lt<T extends Comparable<T>> implements Filter<T> {

    T value;

    public Lt(T value) {
        this.value = value;
    }

    @Override
    public boolean satisfy(T data) {
        return data.compareTo(value) < 0;
    }
}

class Or<T extends Comparable<T>> implements Filter<T> {

    Filter<T> left;
    Filter<T> right;

    public Or(Filter<T> left, Filter<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean satisfy(T data) {
        return left.satisfy(data) || right.satisfy(data);
    }
}

class Digest<T> {

    T min;
    T max;

    public Digest(T min, T max) {
        this.min = min;
        this.max = max;
    }

}


public class FilterTest {
    public static void main(String[] args) {
        Filter<Integer> left = new Eq<>(10);
        left.satisfy(10);

        Filter<Integer> right = new Eq<>(20);
        Filter<Integer> or = new Or<>(left, right);


        System.out.println(or.satisfy(10));
        System.out.println(or.satisfy(20));
        System.out.println(or.satisfy(30));

//        Integer a = 1;
//        Integer b = 5;
//        System.out.println(a.compareTo(b));

    }
}