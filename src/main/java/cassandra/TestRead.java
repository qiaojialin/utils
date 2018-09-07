package cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestRead {
    public static void main(String[] args) throws Exception {

        List<String> dates = getBetweenDate("2016-01-01", "2016-12-31");

        File f = new File("src/main/resources/micapsdata86.csv");
        FileWriter writer = new FileWriter(f, true);

        CassandraCluster cluster = CassandraCluster.getInstance("192.168.3.51,192.168.3.52,192.168.3.53,192.168.3.54");

        ResultSet rSet = null;

        for(int level = 1; level < 150; level++) {
            if(level<137)
                continue;

            for(int i = 0; i < dates.size()-1; i++) {
                String start = dates.get(i).replace("-", "").substring(2);
                String end = dates.get(i+1).replace("-", "").substring(2);
                rSet = cluster.session.execute("select \"dataPath\",column1 from micapsdataserver.\"ECMWF_HR\" where \"dataPath\"='FRACTION_OF_CLOUD_COVER/" + level + "' and column1 >= '"+ start +"01' and column1<'"+end+"01';");

                long count = 0;
                for (Row r : rSet) {
                    String key = r.getString("dataPath");
                    String[] items1 = key.split("/");
                    String column1 = r.getString("column1");
                    String[] items2 = column1.split("\\.");
                    writer.write(items1[0] + "," + items1[1] + "," + items2[0] + "," + items2[1]+"\n");
                    count++;
                }
                System.out.println("level: " + level + "  start: " + start + "~" + end + " count: " + count);

            }



        }
        writer.close();

        cluster.close();

    }

    public static List<String> getBetweenDate(String start, String end){
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
