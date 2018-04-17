package file;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiaojialin on 2017/6/7.
 */
public class Read {

    /**
     * read file
     *
     * @param path file path
     * @return contents
     */
    public static List<String> readFile(String path) {
        File srcFile = new File(path);
        List<String> lines = new ArrayList<>();
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
            String str;
            Map<String, List<String>> sensor_device = new HashMap<>();
            while ((str = bufferedReader.readLine()) != null) {

                String[] sensors = str.split("#")[1].split(",");
                String[] devices = str.split("#")[0].split(",");

                for(String device: devices)
                    for(String sensor: sensors) {
                        if(!sensor_device.containsKey(sensor))
                            sensor_device.put(sensor, new ArrayList<String>());
                        sensor_device.get(sensor).add(device);
                        i++;
                    }
            }

            for(Map.Entry<String, List<String>> entry: sensor_device.entrySet()) {
                String sensor = entry.getKey();
                for(String device: entry.getValue()) {
                    System.out.println(device + "#" + sensor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(i);
        return lines;
    }

    public static void main(String[] args) {
        readFile("/Users/qiaojialin/Desktop/fujian_timeseries.txt");
    }
}
