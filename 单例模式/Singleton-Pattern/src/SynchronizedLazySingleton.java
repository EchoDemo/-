/**
 * @author EchoDemo
 * @version 1.0
 * @ClassName SynchronizedLazySingleton
 * @Description 线程安全的懒汉式
 * @date 2020/3/31/0031 21:56
 */
public class SynchronizedLazySingleton {
    // 私有的构造函数,外部无法访问
    private SynchronizedLazySingleton () {
        System.out.println("SynchronizedLazySingleton is created");
    }

    // 在类内部定义一个实例
    private static SynchronizedLazySingleton instance = null;

    // 对外提供获取实例的静态方法
    public static synchronized SynchronizedLazySingleton getInstance() {
        // 在对象被使用的时候才进行初始化
        if (instance == null) {
            instance = new SynchronizedLazySingleton();
        }
        return instance;
    }
}
