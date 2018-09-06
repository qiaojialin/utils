package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaojialin on 2017/6/4.
 */
public class ReadWrite {

    public static void readWrite(String src, String dest) {
        src = "/Users/qiaojialin/Desktop/data_t.csv";
        dest = "/Users/qiaojialin/Desktop/data_t_qjl.csv";
        File srcFile = new File(src);
        File destFile = new File(dest);
        FileWriter writer;
        try {
            writer = new FileWriter(destFile, false);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
            String str;
            int i = 0;
            while ((str = bufferedReader.readLine()) != null) {
                i ++;
                String[] items = str.split(",");
                List<String> result = new ArrayList<>();
                int count = 0;
                float sum = 0;
                for(int j = 2; j < items.length; j++) {
                    if(!items[j].equals("")) {
                        sum += Float.parseFloat(items[j]);
                        count ++;
                    }
                }

                float avg = sum / count;
                float maxcha1 = 0;
                int maxchaIndex1 = 0;
                float maxcha2 = 0;
                int maxchaIndex2 = 0;

                for(int j = 2; j < items.length; j++) {
                    if(!items[j].equals("")) {
                        float cha = Math.abs(Float.parseFloat(items[j]) - avg);
                        if(cha >= maxcha1) {
                            maxcha2 =maxcha1;
                            maxchaIndex2 = maxchaIndex1;
                            maxcha1 = cha;
                            maxchaIndex1 = j;
                        } else if(cha >= maxcha2) {
                            maxcha2 = cha;
                            maxchaIndex2 = j;
                        }
                    }
                }

                sum = 0;
                count = 0;
                for(int j = 2; j < items.length; j++) {
                    if(!items[j].equals("") && j != maxchaIndex1 && j != maxchaIndex2) {
                        sum += Float.parseFloat(items[j]);
                        count ++;
                    }
                }

                avg = sum / count;
                double sigma = 0;
                for(int j = 2; j < items.length; j++) {
                    if(!items[j].equals("") && j != maxchaIndex1 && j != maxchaIndex2) {
                        double value = Double.parseDouble(items[j]);
                        sigma += (value - avg)*(value - avg);
                    }
                }
                sigma = Math.sqrt(sigma/count);

                boolean flag = true;
                StringBuilder builder = new StringBuilder();
                for(int j = 2; j < items.length; j++) {
                    if(!items[j].equals("")) {
                        double value = Double.parseDouble(items[j]);
                        if(Math.abs(value - avg) > 3 * sigma) {
                            if(flag) {
                                flag = false;
                                builder.append(value);
                            } else {
                                builder.append("," + value);
                            }
                        }
                    }
                }



//                StringBuilder stringBuilder = new StringBuilder();
//                boolean flag = true;
//                for(String a: result) {
//                    if(flag) {
//                        stringBuilder.append(a);
//                        flag = false;
//                    } else {
//                        stringBuilder.append("," + a);
//                    }
//                }
//                System.out.println(stringBuilder.toString());
//                if(i % 100 == 0) {
//                    System.out.println(str);
                    writer.write(builder.toString());
                    writer.write("\n");
//                }
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
