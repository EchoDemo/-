/**
 * @author EchoDemo
 * @version 1.0
 * @ClassName LazySingleton
 * @Description 懒汉式
 * @date 2020/3/31/0031 21:44
 */
public class LazySingleton {
    // 私有的构造函数,外部无法访问
    private LazySingleton () {
        System.out.println("LazySingleton is created");
    }

    // 在类内部定义一个实例
    private static LazySingleton instance = null;

    // 对外提供获取实例的静态方法
    public static LazySingleton getInstance() {
        // 在对象被使用的时候才进行初始化
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
