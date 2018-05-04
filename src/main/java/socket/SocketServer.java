package socket;

import cn.edu.tsinghua.iotdb.jdbc.TsfileJDBCConfig;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


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
            System.out.println("need 1 arg: ip");
        new SocketServer(args[0]).service();
    }
}


class ServerThread implements Runnable {
    private int index;
    private Socket socket;

    private Connection connection;
    private Statement statement;
    private String insert = "insert into %s(timestamp,%s) values(%s,%s)";

    public ServerThread(String ip, Socket socket, int i) throws ClassNotFoundException, SQLException {
        Class.forName(TsfileJDBCConfig.JDBC_DRIVER_NAME);
        connection = DriverManager.getConnection("jdbc:tsfile://" + ip + ":6667/", "root", "root");
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
                while (!((info = br.readLine()) == null)) {
                    System.out.println("第" + index + "个客户端发出消息：" + info);
                    String[] items = info.split(":");
                    if(items.length!=3)
                        continue;
                    String sql = String.format(insert, items[0], items[1], System.currentTimeMillis(), items[2]);
                    System.out.println(sql);
                    statement.execute(sql);
                }
                System.out.println("客户端断开连接");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
