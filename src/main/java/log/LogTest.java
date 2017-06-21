package log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qiaojialin on 2017/6/10.
 */
public class LogTest {
    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.debug("afaf");
    }
}
