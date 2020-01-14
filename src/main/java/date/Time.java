package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

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

    /**
     * 获取两个日期间隔的所有日期
     * @param start 格式必须为'2018-01-25'
     * @param end 格式必须为'2018-01-25'
     * @return
     */
    private List<String> getBetweenDate(String start, String end){
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }
}
