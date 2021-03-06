package socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class SocketClient {
    // 搭建客户端
    public static void main(String[] args) throws IOException {
        try {
            // 1、创建客户端Socket，指定服务器地址和端口
             Socket socket=new Socket("127.0.0.1",5200);
//            Socket socket = new Socket("www.baidu.com", 9999);

            System.out.println(socket.getLocalAddress());
            System.out.println(socket.getLocalPort());
            System.out.println(socket.getInetAddress());

            socket.getLocalAddress();
            System.out.println("客户端启动成功");
            // 2、获取输出流，向服务器端发送信息
            // 向本机的52000端口发出客户请求
            // 由系统标准输入设备构造BufferedReader对象
            PrintWriter write = new PrintWriter(socket.getOutputStream());
            // 由Socket对象得到输出流，并构造PrintWriter对象
            //3、获取输入流，并读取服务器端的响应信息
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            write.println("aaaaaaaa");
            // 将从系统标准输入读入的字符串输出到Server
            write.flush();

            write.println("aaaaaaaa");
            // 将从系统标准输入读入的字符串输出到Server
            write.flush();
            //4、关闭资源
            write.close(); // 关闭Socket输出流
            socket.close(); // 关闭Socket
        } catch (Exception e) {
            System.out.println("can not listen to:" + e);// 出错，打印出错信息
        }
    }

}