import java.io.Serializable;

/**
 * @author EchoDemo
 * @version 1.0
 * @ClassName VolatileSingleton
 * @Description Volatile双重校验锁
 * @date 2020/4/1/0001 21:55
 */
public class VolatileSingleton implements Serializable {
    // 私有的构造函数,外部无法访问
    private VolatileSingleton () {
        System.out.println("VolatileSingleton is created");
    }

    // 在类内部定义一个实例
    private static volatile VolatileSingleton instance = null;

    // 对外提供获取实例的静态方法
    public static VolatileSingleton getInstance() {
        // 在对象被使用的时候才进行初始化
        if (instance == null) {
            synchronized (VolatileSingleton.class) {
                if (instance == null) {
                    instance = new VolatileSingleton();
                }
            }
        }
        return instance;
    }

    private Object readResolve () {
        return instance;
    }
}
