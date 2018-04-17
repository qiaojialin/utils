package jmx;

import java.io.*;

public class ParseLog {

    public static void main(String[] args) {
        String src = "/Users/qiaojialin/Desktop/cpu_memory.csv";
        String dest = "/Users/qiaojialin/Desktop/cpu_memory_parsed.csv";

        File srcFile = new File(src);
        File destFile = new File(dest);
        FileWriter writer;
        try {
            writer = new FileWriter(destFile, false);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str.split(":"));
                writer.write(str);
                writer.write("\n");
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
