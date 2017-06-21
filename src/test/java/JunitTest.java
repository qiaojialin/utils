import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by qiaojialin on 2017/6/8.
 */

@RunWith(Parameterized.class)
public class JunitTest {
    private String expected;
    private String result;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"a", "a"},
                {"b", "b"}});
    }

    public JunitTest(String parameter1, String parameter2) {
        expected = parameter1;
        result = parameter2;
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("before all");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("after all");
    }

    @Before
    public void beforeEach() {
        System.out.println("before each");
    }

    @After
    public void afterEach() {
        System.out.println("after each");
    }

    @Test
    public void test1() {
        assertEquals(expected, result);
    }

    @Test
    public void test2() {
        assertEquals(expected, result);
    }
}
