/**
 * @author EchoDemo
 * @version 1.0
 * @ClassName Singleton1
 * @Description 饿汉式的变种
 * @date 2020/3/31/0031 21:31
 */
public class Singleton1 {
    // 私有的构造函数,外部无法访问
    private Singleton1() {
        System.out.println("Singleton1 is created");
    }

    // 在类内部定义一个实例
    private static Singleton1 instance = null;

    static {
        // 实例化该实例
        instance = new Singleton1();
    }

    // 对外提供获取实例的静态方法
    public static Singleton1 getInstance() {
        return instance;
    }
}
