package json;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by qiaojialin on 2017/6/21.
 */
public class JsonTest {
    private static String jsonStr = "{\n" +
            "    \"schema\": [\n" +
            "        {\n" +
            "            \"measurement_id\": \"sensor_1\",\n" +
            "            \"data_type\": \"FLOAT\",\n" +
            "            \"encoding\": \"RLE\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"measurement_id\": \"sensor_2\",\n" +
            "            \"data_type\": \"INT32\",\n" +
            "            \"encoding\": \"TS_2DIFF\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"measurement_id\": \"sensor_3\",\n" +
            "            \"data_type\": \"INT32\",\n" +
            "            \"encoding\": \"TS_2DIFF\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"properties\": \n" +
            "        {\n" +
            "            \"key1\": \"value1\",\n"+
            "            \"key2\": \"value2\"\n"+
            "        },\n" +
            "    \"row_group_size\": 134217728\n" +
            "}";

    public static void main(String[] args) {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray jsonArray = object.getJSONArray("schema");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject subObject = jsonArray.getJSONObject(i);
            System.out.println(subObject);
        }

        JSONObject pro = object.getJSONObject("properties");
        System.out.println(pro);
        System.out.println(pro.getString("key1"));

        int row = object.getInt("row_group_size");
        System.out.println(row);

    }
}
