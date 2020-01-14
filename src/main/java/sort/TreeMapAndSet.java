package sort;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMapAndSet {
    public static void main(String[] args) {

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("device2", 1);
        treeMap.put("device3", 3);
        treeMap.put("device1", 2);

        for(Map.Entry<String, Integer> entry: treeMap.entrySet())
        System.out.println(entry.getKey());

        TreeSet<String> devices = new TreeSet<>();
        devices.add("device2");
        devices.add("device8");
        devices.add("device1");

        for(String s: devices)
            System.out.println(s);

    }
}
