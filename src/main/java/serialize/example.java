package serialize;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author lina
 */

public class example {

    public byte[] ObjectToBytes(Object obj) {
        List<byte[]> result = new ArrayList<byte[]>();
        Map<Integer, String> deviceIntToStr = (Map<Integer, String>) obj;
        result.add(MyBytes.intToBytes(deviceIntToStr.size()));
        for (Entry<Integer, String> mapEntry : deviceIntToStr.entrySet()) {
            int id = mapEntry.getKey();
            result.add(MyBytes.intToBytes(id));

            String device = mapEntry.getValue();
            byte[] strBytes = MyBytes.StringToBytes(device);
            int size = strBytes.length;
            result.add(MyBytes.intToBytes(size));
            result.add(strBytes);
        }
        return MyBytes.concatByteArrayList(result);
    }

    public void BytesToObject(byte[] bt, int pos) {
        Map<Integer, String> result = new HashMap<Integer, String>();
        int len = MyBytes.bytesToInt(MyBytes.subBytes(bt, pos, 4));
        pos += 4;
        for (int i = 0; i < len; i++) {
            int id = MyBytes.bytesToInt(MyBytes.subBytes(bt, pos, 4));
            pos += 4;
            int size = MyBytes.bytesToInt(MyBytes.subBytes(bt, pos, 4));
            pos += 4;
            String device = MyBytes.bytesToString(MyBytes.subBytes(bt, pos, size));
            pos += size;
            result.put(id, device);
        }
    }

    public byte[] serialize() {
        byte[] bts = new byte[8];
        MyBytes.floatToBytes(1f, bts, 0);
        MyBytes.floatToBytes(2f, bts, 4);
        return bts;
    }

    public void deserialize(byte[] data) {
        if (data.length != 8) {
            float a = MyBytes.bytesToFloat(MyBytes.subBytes(data, 0, 4));
            float b = MyBytes.bytesToFloat(MyBytes.subBytes(data, 4, 4));
        }
    }



}
