package jmx;

import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.concurrent.TimeUnit;

public class JMXMonitor {

    private static Logger logger = LoggerFactory.getLogger(JMXMonitor.class);
    private MemoryMXBean memoryMXBean;
    private OperatingSystemMXBean opMXbean;

    public JMXMonitor(JMXConnector connector) throws IOException {
        memoryMXBean = ManagementFactory.newPlatformMXBeanProxy(connector.getMBeanServerConnection(), "java.lang:type=Memory", MemoryMXBean.class);
        MBeanServerConnection mbs = connector.getMBeanServerConnection();
        opMXbean = ManagementFactory.newPlatformMXBeanProxy(mbs, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
    }

    /**
     * MemoryUsage 对象包含四个值：
     * <p>
     * init		表示 Java 虚拟机在启动期间从操作系统请求的用于内存管理的初始内存容量（以字节为单位）。Java 虚拟机可能在运行过程中从操作系统请求更多的内存，也可能将内存释放给系统。init 的值可以是不明确的。
     * used		表示当前已经使用的内存量（以字节为单位）。
     * committed	表示保证可以由 Java 虚拟机使用的内存量（以字节为单位）。已提交的内存量可以随时间而变化（增加或减少）。Java 虚拟机可能会将内存释放给系统，committed 可以小于 init。committed 将始终大于或等于 used。
     * max		表示可以用于内存管理的最大内存量（以字节为单位）。可以不定义其值。如果定义了该值，最大内存量可能随时间而更改。已使用的内存量和已提交的内存量将始终小于或等于 max（如果定义了 max）。如果内存分配试图增加满足以下条件的已使用内存将会失败：used > committed，即使 used <= max 仍然为 true（例如，当系统的虚拟内存不足时）。 以下是内存池的示例图：
     * +----------------------------------------------+
     * +////////////////           |                  +
     * +----------------------------------------------+
     * <p>
     * |--------|
     * init
     * |---------------|
     * used
     * |---------------------------|
     * committed
     * |----------------------------------------------|
     * max
     */
    public String monitorMemory() {
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();//堆内存使用情况
        MemoryUsage nonHeapUsage = memoryMXBean.getNonHeapMemoryUsage();//非堆内存使用情况
        long init = heapUsage.getInit();//初始内存容量(byte)
        long used = heapUsage.getUsed();//已使用内存量(byte)
        long max = heapUsage.getMax();//内存最大量(byte)
        long commited = heapUsage.getCommitted();//已提交的内存量(byte)
//        logger.info("memory: used:{}, max:{}", ConvertSize.getSize(used), ConvertSize.getSize(max));
        float gbFormat = used/((float)1024 * 1024 * 1024);
        float precision2 = (float)(Math.round(gbFormat*100))/100;
        return "used," + precision2 + ",GB";
    }

    public String monitorCpu() throws IOException {
        Long start = System.currentTimeMillis();
        long startT = opMXbean.getProcessCpuTime();
        /**    Collect data every 60 seconds      */
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error("InterruptedException occurred while MemoryCollector sleeping...");
        }
        Long end = System.currentTimeMillis();
        long endT = opMXbean.getProcessCpuTime();
        //end - start 即为当前采集的时间单元，单位ms
        //endT - startT 为当前时间单元内cpu使用的时间，单位为ns
        double ratio = (endT-startT)/1000000.0/(end-start)/opMXbean.getAvailableProcessors();
//        logger.info("cpu: {}", ratio);
        double precision2 = (double) (Math.round(ratio*100))/100;
        return "usage," + precision2;
    }


    public String monitorFileSize(String path) {
        double size = getSize(new File(path));
        float precision2 = (float)(Math.round(size*100))/100;
        return "total," + precision2 + ",GB";
    }


    public double getSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小，如果是文件则直接返回其大小
            if (!file.isFile()) {
                //获取文件大小
                File[] fl = file.listFiles();
                double ss = 0;
                for (File f : fl)
                    ss += getSize(f);
                return ss;
            } else {
                return (double) file.length() / 1024 / 1024 / 1024;
            }
        } else {
            return 0.0;
        }
    }

    public static void main(String[] args) throws IOException {
        //1、创建JMX连接URL，格式是固定的，只需要替换IP和端口
        JMXServiceURL serviceURL = new JMXServiceURL(String.format("service:jmx:rmi:///jndi/rmi://%s:%s/jmxrmi", "127.0.0.1", "31999"));
        //2、与JVM建立连接
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
        //3、获取MemoryMXBean其中第二个参数为ObjectName，即为JConsole中看到的，也是固定值
        JMXMonitor jmxMonitor = new JMXMonitor(connector);

        logger.info("memory: {}, file size: {}", jmxMonitor.monitorMemory(), jmxMonitor.monitorFileSize(args[0]));

        while (true) {
            try {
                TimeUnit.MINUTES.sleep(60);
            } catch (InterruptedException e) {
                logger.error("InterruptedException occurred while MemoryCollector sleeping...");
            }
            logger.info("memory: {}, cpu: {}, file size: {}", jmxMonitor.monitorMemory(), jmxMonitor.monitorCpu(), jmxMonitor.monitorFileSize(args[0]));
        }
    }


}

