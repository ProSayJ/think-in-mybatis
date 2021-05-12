package java.lang;

/**
 * TODO
 *
 * @author yangjian
 * @date 2021-05-05 下午 01:31
 * @since 1.0.0
 */
public class String {
    static {
        System.out.println("customer String static code block");
    }

    //在类 java.lang.String 中找不到 main 方法
    public static void main(String[] args) {
        System.out.println("hello String");
    }
}
