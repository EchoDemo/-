## 单例模式

单例模式（Singleton Pattern）是 Java 中最简单、使用最为普遍的设计模式之一。它是一种对象创建型模式。用于产生一个对象的具体实例，**它可以确保系统中一个类只产生一个实例**。在Java语言中这样的行为能带来两大好处：

>1、对于频繁使用的对象，可以省略创建对象所花费的时间，这对于那些重量级对象而言，是非常可观的一笔系统开销。
>
>2、由于new操作次数的减少，对系统内存的使用频率也会降低，这将会减轻GC的压力，缩短GC停顿的时间。

单例模式的参与者只有单例类和使用者两个：

>1、单例类：提供单例的工厂，返回单例。
>
>2、使用者：获取并使用单例类。

一个类的对象的产生是由类的构造函数来完成的。如果一个类对外提供了public的构造方法，那么外界就可以任意创建该类的对象。所以，如果想限制对象的产生，一个办法就是将构造函数变为私有的，使外面的类不能通过new来产生对象。同时为了保证类的可用性，就必须提供一个自己的实例，以及访问这个实例的静态方法。

### 饿汉式

1、代码示例

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

2、代码测试

	public class Main {
	    public static void main(String[] args) {
	        Singleton singleton1 = Singleton.getInstance();
	        Singleton singleton2 = Singleton.getInstance();
	        System.out.println(singleton1 == singleton2);
	    }
	}

3、测试结果

	true

这里还有一种饿汉式的变种：

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


这种单例的实现方式非常简单，被称之为饿汉式。所谓饿汉指的是对于一个饿汉来说，在他希望用到该实例的时候就能够立即拿到，而不需要进行任何等待。所以，通过static的静态初始化方式，在该类第一次被加载的时候，就有一个Singleton的实例被创建出来。这样就可以保证在第一次想要使用该对象时，它就被初始化好了。

但与此同时，这样会造成不必要的消耗，因为有可能这个实例根本就不会被用到。假如该单例的创建过程很慢，而由于instance成员变量是static定义的，因此在JVM加载单例类的时候，单例对象就会被创建。如果此时这个单例类在系统中还扮演其他的角色，那么在任何使用这个单例类的地方都会初始化这个单例变量，而不管是否会被用到。也就无法做到延迟加载。

	// 代码示例
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

	// 代码测试
	public class Main {
	    public static void main(String[] args) {
	        Singleton2 singleton2 = Singleton2.getInstance();
	        singleton2.doSomething();
	    }
	}

	// 测试结果
	Singleton2 is created
	doSomething in Singleton2

下面提供两种解决方式，第一种是使用懒汉式。第二种是使用静态内部类的形式。

### 懒汉式

代码示例

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

在懒汉式的模式当中，不会提前把实例创建出来，它将类对自己的实例化延迟到第一次被引用的时候。但其实该懒汉式的实现还存在一个线程安全的问题。在多线程的环境下，当线程1新建单例时，在完成赋值操作前；线程2可能判断instance为null，故线程2也将启动新建单例的程序，从而导致多个实例被创建。当然，其实解决方式很简单，就是给创建对象的步骤加锁。

线程安全的懒汉式代码示例：

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

虽然使用以上代码实现了延迟加载的功能，但是因为引入了同步关键字，因此在多线程环境中，它的耗时要大大增加，反而降低了系统的性能，导致有点得不偿失，为了解决这个问题，引入了以下的双重校验锁和静态内部类式。

双重校验锁代码示例：

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

通过使用同步代码块的方式减小了锁的范围。这样可以大大提高效率。

### 静态内部类式

代码示例：

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

在这个实现中单例模式使用内部类来维护单例的实例，当StaticInnerClassSingleton被加载时，其内部类并不会被初始化，故可以确保当StaticInnerClassSingleton类被载入JVM时，不会初始化单例类，而只有当getInstance()方法被调用时，才会加载SingletonHolder，从而初始化instance。

同时，由于实例的建立是在类加载时完成，故天生对多线程友好，getInstance()方法也不需要使用同步关键字。



