/**
 * @author EchoDemo
 * @version 1.0
 * @ClassName Singleton2
 * @Description 无法做到延迟加载
 * @date 2020/3/31/0031 21:37
 */
public class Singleton2 {
    // 私有的构造函数,外部无法访问
    private Singleton2() {
        System.out.println("Singleton2 is created");
    }

    // 在类内部实例化一个实例
    private static Singleton2 instance = new Singleton2();

    // 对外提供获取实例的静态方法
    public static Singleton2 getInstance() {
        return instance;
    }

    // 模拟单例类扮演其他角色
    public static void doSomething() {
        System.out.println("doSomething in Singleton2");
    }
}
