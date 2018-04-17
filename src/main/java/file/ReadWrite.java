package file;

import java.io.*;

/**
 * Created by qiaojialin on 2017/6/4.
 */
public class ReadWrite {

    public static void readWrite(String src, String dest) {
        src = "/Users/qiaojialin/Desktop/memory.csv";
        dest = "/Users/qiaojialin/Desktop/memory2.csv";
        File srcFile = new File(src);
        File destFile = new File(dest);
        FileWriter writer;
        try {
            writer = new FileWriter(destFile, true);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
            String str;
            int i = 0;
            while ((str = bufferedReader.readLine()) != null) {
                i ++;
                if(i % 100 == 0) {
                    System.out.println(str);
                    writer.write(str);
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        readWrite("", "");
    }

}
