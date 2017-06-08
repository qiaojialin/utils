package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qiaojialin on 2017/6/8.
 */
public class Time {
    public static void main(String[] args) throws ParseException {
        String str = "2016-11-16 16:22:33:175";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date date = sdf.parse(str);
        System.out.println(date);

        long time = date.getTime();
        Date newDate = new Date(time);
        String newStr = sdf.format(newDate);
        System.out.println(newStr);
    }
}
