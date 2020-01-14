package serialize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import org.apache.iotdb.jdbc.Config;


/**
 * 自动驾驶汽车
 */
public class SocketServer {
    private int port = 9999;// 默认服务器端口
    private String ip;

    public SocketServer(String ip) {
        this.ip = ip;
    }

    // 创建指定端口的服务器
    public SocketServer(int port) {
        this.port = port;
    }

    // 提供服务
    public void service() {
        int i = 0;
        try {
            // 建立服务器连接,设定客户连接请求队列的长度
            ServerSocket server = new ServerSocket(port, 1000);
            System.out.println("server started, listening port: " + port + " iotdb ip: " + ip);
            while (true) {
                // 等待客户连接
                Socket socket = server.accept();
                i++;
                System.out.println("第" + i + "个客户连接成功！");
                new Thread(new ServerThread(ip, socket, i)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length < 1)
            System.out.println("need 1 arg: iotdb-ip");
        new SocketServer(args[0]).service();
    }
}


class ServerThread implements Runnable {
    private int index;
    private Socket socket;

    private Connection connection;
    private Statement statement;
    private String insert = "insert into root.car(timestamp,gyro_x,gyro_y,gyro_z,acce_x,acce_y,acce_z,Temperature) values(%s,%s,%s,%s,%s,%s,%s,%s)";

    public ServerThread(String ip, Socket socket, int i) throws ClassNotFoundException, SQLException {
//        Class.forName(Config.JDBC_DRIVER_NAME);
//        connection = DriverManager.getConnection(Config.IOTDB_URL_PREFIX + ip + ":6667/", "root", "root");
        statement = connection.createStatement();
        this.socket = socket;
        this.index = i;
    }

    // 任务是为一个用户提供服务
    @Override
    public void run() {
        try {
            try {
                //2.使用accept()方法阻止等待监听，获得新连接
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                //4.读取用户输入信息
                String info = null;
                long i = 0;
                while (!((info = br.readLine()) == null)) {
                    i++;
                    if(i % 100 != 0) {
                        continue;
                    }
                    System.out.println("第 " + index + " 个客户端发出消息：" + info);
                    /**
                     * TimeTag:1:2:3:4:5:6:7
                     */
                    String[] items = info.split(":");
                    if(items.length!=8)
                        continue;
                    String sql = String.format(insert, System.currentTimeMillis(), items[1], items[2], items[3],
                        items[4], items[5], items[6], items[7]);
                    System.out.println(sql);
                    statement.execute(sql);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                socket.close();
                System.out.println("客户端 " + index + " 断开连接");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
