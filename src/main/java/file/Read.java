package file;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiaojialin on 2017/6/7.
 */
public class Read {

    /**
     * read file
     *
     * @param path file path
     * @return contents
     */
    public static List<String> readFile(String path) {
        File srcFile = new File(path);
        List<String> lines = new ArrayList<>();
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));

            String str = bufferedReader.readLine();

            String[] item = str.split("\t");
            System.out.println(item.length);

            List<Long> day_num = new ArrayList<>();

            long num = 0;
            for(i = 0; i < item.length; i++) {
                num += Integer.valueOf(item[i].trim());
//                if(i != 0 && i % 144 == 0) {
//                    day_num.add(num);
//                    num = 0;
//                }
            }
            System.out.println(num);

            System.out.println(day_num);

//            while ((str = bufferedReader.readLine()) != null) {
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(i);
        return lines;
    }

    public static void main(String[] args) {
//        readFile("/Users/qiaojialin/Desktop/disorder");

        System.out.println(Integer.MAX_VALUE);
    }

}
