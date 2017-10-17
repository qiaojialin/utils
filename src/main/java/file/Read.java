package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            String str;
            while ((str = bufferedReader.readLine()) != null) {
//                lines.add(str);

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(i);
        return lines;
    }

    public static void main(String[] args) {
        readFile("/Users/qiaojialin/Desktop/uber-all.csv");
    }
}
