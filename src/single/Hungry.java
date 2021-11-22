package single;


// 懒汉式 事先创建好实例, 不会出现线程不安全
public class Hungry {


    // 创建一个实例
    private static final Hungry hungry = new Hungry();

    // 单例必须有一个私有构造方法
    private Hungry(){
    }

    // 返回事先创建好的实例
    public static Hungry getInstance(){
        return hungry;
    }
}
