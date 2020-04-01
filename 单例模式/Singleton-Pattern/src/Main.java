import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws Exception{
        VolatileSingleton s1 = null;
        VolatileSingleton s = VolatileSingleton.getInstance();
        // 先将实例串行化到文件
        FileOutputStream fos = new FileOutputStream("F:\\VolatileSingleton.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s);
        oos.flush();
        oos.close();
        // 从文件读出原有的单例类
        FileInputStream fis = new FileInputStream("F:\\VolatileSingleton.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        s1 = (VolatileSingleton) ois.readObject();
        System.out.println(s == s1);
    }
}
