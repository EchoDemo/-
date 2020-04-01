import java.io.Serializable;

/**
 * @author EchoDemo
 * @version 1.0
 * @ClassName DoubleLockSingleton
 * @Description 双重校验锁
 * @date 2020/3/31/0031 22:24
 */
public class DoubleLockSingleton {
    // 私有的构造函数,外部无法访问
    private DoubleLockSingleton () {
        System.out.println("DoubleLockSingleton is created");
    }

    // 在类内部定义一个实例
    private static DoubleLockSingleton instance = null;

    // 对外提供获取实例的静态方法
    public static DoubleLockSingleton getInstance() {
        // 在对象被使用的时候才进行初始化
        if (instance == null) {
            synchronized (DoubleLockSingleton.class) {
                if (instance == null) {
                    instance = new DoubleLockSingleton();
                }
            }
        }
        return instance;
    }
}
