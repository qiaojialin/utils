package cassandra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ColumnDefinitions.Definition;


import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Hello world!
 */
public class Cassandra_CQL_data {

    public static void main(String[] args) {
        Cluster cluster = null;
        Session session = null;
        Set<String> sensors = new HashSet<String>();
        Set<String> devices = new HashSet<String>();

        try {

            //定义一个Cluster类
            cluster = Cluster.builder().addContactPoint("166.111.80.202").build();

            //需要获取Session对象
            session = cluster.connect("flok");

            ResultSet rSet1 = session.execute("select * from flok.data1001132060;");
            for (Definition definition : rSet1.getColumnDefinitions()) {
                if (!definition.getName().equals("timestamp"))
                    sensors.add(definition.getName());
            }

            ResultSet rSet2 = session.execute("select * from system_schema.columns;");

            for (Row row : rSet2) {
                if (row.getString("keyspace_name").equals("flok")) {
                    devices.add(row.getString("table_name"));
                }
            }
            System.out.println(sensors.size());
            System.out.println(devices.size());


            try {
                Class.forName("org.apache.iotdb.jdbc.IoTDBDriver");
                Connection connection = DriverManager.getConnection("jdbc:iotdb://192.168.10.57:6667/", "root", "root");
                Statement statement = connection.createStatement();
                int batch_count = 0;
                long total = 0;
                for (String device : devices) {
                    if(device.equals("data1001132060"))
                        continue;
                    ResultSet rSet = session.execute("select * from flok." + device + ";");
//                    for (Definition definition : rSet.getColumnDefinitions()) {
//                        System.out.print(definition.getName() + " ");
//                    }

                    for (Row row : rSet) {
                        String sql_head = "insert into root.flok." + device + "(timestamp";
                        String sql_rear = ") values(";
                        String timestamp = row.getString("timestamp");
                        sql_rear = sql_rear + timestamp;
                        for (String sensor : sensors) {
                            sql_head = sql_head + "," + sensor;
                            sql_rear = sql_rear + "," + "\"" + row.getString(sensor) + "\"";
                        }
                        String sql = sql_head + sql_rear + ")";
                        statement.addBatch(sql);

                        batch_count++;
                        total++;

                        if (batch_count == 10000) {
                            statement.executeBatch();
                            statement.clearBatch();
                            batch_count = 0;
                        }
                    }
                    System.out.println("resolved device: " + device);
                }
                System.out.println("total insert: " + total);
                statement.executeBatch();
                statement.clearBatch();
            } catch (SQLException | ClassNotFoundException e) {
	   			e.printStackTrace();
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            //关闭Session和Cluster
            session.close();
            cluster.close();
        }

        return;
    }
}