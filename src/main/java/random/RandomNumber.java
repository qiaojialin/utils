package random;

import java.util.Random;

/**
 * Created by qiaojialin on 2017/6/7.
 */
public class RandomNumber {

    public static void main(String[] args) {
        Random random = new Random(10);
        for(int i=0; i<10; i++) {
            System.out.println(random.nextInt(100));
        }

        for(int i=0; i<10; i++) {
            System.out.println(random.nextDouble());
        }
    }

}
