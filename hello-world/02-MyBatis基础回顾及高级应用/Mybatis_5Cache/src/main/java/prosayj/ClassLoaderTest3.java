package prosayj;


import java.sql.DriverManager;

/**
 * 获取ClassLoader的途径
 *
 * @author yangjian
 * @date 2021-05-05 上午 11:17
 * @since 1.0.0
 */
public class ClassLoaderTest3 {
    public static void main(String[] args) throws ClassNotFoundException {
        //1
        ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader);
        //2
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        //3
        ClassLoader parent = ClassLoader.getSystemClassLoader().getParent();
        System.out.println(parent);
    }
}
