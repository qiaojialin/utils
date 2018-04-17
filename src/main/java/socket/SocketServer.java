package socket;

import cn.edu.tsinghua.iotdb.jdbc.TsfileJDBCConfig;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 聊天 服务器端
 * Created by tl on 17/3/1.
 */
public class SocketServer {
    private int port = 9999;// 默认服务器端口


    public SocketServer() {
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
                new Thread(new ServerThread(socket, i)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketServer().service();
    }
}


class ServerThread implements Runnable {
    private int index;
    private Socket socket;

    private Connection connection;
    private Statement statement;
    private String insert = "insert into %s(timestamp,%s) values(%s,%s)";

    public ServerThread(Socket socket, int i) throws ClassNotFoundException, SQLException {
//        Class.forName(TsfileJDBCConfig.JDBC_DRIVER_NAME);
//        connection = DriverManager.getConnection("jdbc:tsfile://192.168.130.151:6667/", "root", "root");
//        statement = connection.createStatement();
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
//                    statement.execute(String.format(insert, items[0], items[1], System.currentTimeMillis(), items[2]));
                }
                System.out.println("客户端断开连接");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {// 建立连接失败的话不会执行socket.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
