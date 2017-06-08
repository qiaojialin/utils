package file;

import java.io.*;

/**
 * Created by qiaojialin on 2017/6/4.
 */
public class ReadWrite {

    public static void readWrite(String src, String dest) {
        File srcFile = new File(src);
        File destFile = new File(dest);
        FileWriter writer;
        try {
            writer = new FileWriter(destFile, false);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                writer.write(str);
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        readWrite("", "");
    }

}
