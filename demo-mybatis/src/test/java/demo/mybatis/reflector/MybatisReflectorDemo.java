package demo.mybatis.reflector;

import demo.mybatis.domain.Address;
import org.apache.ibatis.reflection.Reflector;

/**
 * ReflectorDemo
 *
 * @author yangjian
 * @date 2021-03-25 下午 05:58
 * @since 1.0.0
 */
public class MybatisReflectorDemo {
    public static void main(String[] args) {
        Reflector reflector = new Reflector(Address.class);
        System.out.println("reflector.hasDefaultConstructor() = " + reflector.hasDefaultConstructor());


    }
}
