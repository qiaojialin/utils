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
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                lines.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args) {
        readFile("");
    }
}
