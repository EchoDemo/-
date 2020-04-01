/**
 * @author EchoDemo
 * @version 1.0
 * @ClassName Singleton
 * @Description 饿汉式
 * @date 2020/3/31/0031 21:09
 */
public class Singleton {
    // 私有的构造函数,外部无法访问
    private Singleton() {
        System.out.println("Singleton is created");
    }

    // 在类内部实例化一个实例
    private static Singleton instance = new Singleton();

    // 对外提供获取实例的静态方法
    public static Singleton getInstance() {
        return instance;
    }
}
