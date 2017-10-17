package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaojialin on 2017/6/4.
 */
public class Write {

    /**
     * write file
     *
     * @param path file path
     * @param lines contents
     */
    public static void writeFile(String path, List<String> lines) {
        File f = new File(path);
        FileWriter writer;
        try {
            writer = new FileWriter(f, true);
            for(String line: lines) {
                writer.write(line);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        List<String> lines = Read.readFile("");

        List<String> lines = new ArrayList<>();
        lines.add("adfsa");
        String path = "src/main/resources/random_500_525600.csv";
        writeFile(path, lines);
    }
}
