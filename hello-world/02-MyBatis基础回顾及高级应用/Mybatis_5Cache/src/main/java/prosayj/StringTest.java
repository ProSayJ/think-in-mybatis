package prosayj;

/**
 * TODO
 *
 * @author yangjian
 * @date 2021-05-05 下午 01:29
 * @since 1.0.0
 */
public class StringTest {
    public static void main(String[] args) {
        java.lang.String str = new java.lang.String();
        System.out.println("hello world");

        StringTest stringTest = new StringTest();
        ClassLoader classLoader = stringTest.getClass().getClassLoader();
        System.out.println(classLoader);

    }
}
