import java.io.Serializable;

/**
 * @author EchoDemo
 * @version 1.0
 * @ClassName StaticInnerClassSingleton
 * @Description 静态内部类式
 * @date 2020/3/31/0031 22:04
 */
public class StaticInnerClassSingleton {
    // 私有的构造函数
    private StaticInnerClassSingleton() {
        System.out.println("StaticInnerClassSingleton is created");
    }

    // 在静态内部类中初始化实例对象
    private static class SingletonHolder {
        private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    // 对外提供获取实例的静态方法
    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
