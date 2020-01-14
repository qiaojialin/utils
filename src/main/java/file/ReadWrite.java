package file;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by qiaojialin on 2017/6/4.
 */
public class ReadWrite {

    public static void readWrite(String src, String dest) {
        src = "/Users/qiaojialin/Desktop/geolife.longitude";
        dest = "/Users/qiaojialin/Desktop/geolife_int.longitude";
        File srcFile = new File(src);
        File destFile = new File(dest);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");

        List<Line> sorter = new ArrayList<>();

        FileWriter writer;
        try {
            writer = new FileWriter(destFile, false);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
            String str;
            int i = 0;

            // skip 6 lines, which is useless
            for (int j = 0; j < 6; j++) {
                bufferedReader.readLine();
            }

            while ((str = bufferedReader.readLine()) != null) {

                i ++;



//                return time + "," +  Float.parseFloat(items[0]);

                String[] items = str.split(" ");

                int value = (int)(Float.parseFloat(items[1]) * 10000);
                writer.write(items[0] + " " + value + "\n");

//                sorter.add(new Line(items[0], items[1], items[2], items[3], items[4], i));
            }

//            Collections.sort(sorter);
//            System.out.println(i);
//            System.out.println(sorter.size());
//
//            i = 0;
//            Iterator iterator = sorter.iterator();
//            while (iterator.hasNext()) {
//                i++;
//                String tmp = iterator.next().toString();
//                writer.write(tmp);
//                writer.write("\n");
//            }
//            System.out.println(i);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        readWrite("", "");
    }

}

class Line implements Comparable<Line>{

    double tonggou;
    double yigou;
    double down1;
    double down2;
    double down3;
    int suijishu;

    public Line(String tonggou, String yigou, String down1, String down2, String down3, int i) {
        this.tonggou = Double.valueOf(tonggou);
        this.yigou = Double.valueOf(yigou);
        this.down1 = Double.valueOf(down1);
        this.down2 = Double.valueOf(down2);
        this.down3 = Double.valueOf(down3);
        this.suijishu = i;
    }

    public Line(double tonggou, double yigou, double down1, double down2, double down3) {
        this.tonggou = tonggou;
        this.yigou = yigou;
        this.down1 = down1;
        this.down2 = down2;
        this.down3 = down3;
    }

    @Override
    public int compareTo(Line o) {
        return Double.compare(o.tonggou, tonggou);
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public String toString() {
        return tonggou + ", " + yigou + "," + down1 + "," + down2 + "," + down3;
    }
}
