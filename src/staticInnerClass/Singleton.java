package staticInnerClass;

import com.sun.org.apache.xalan.internal.utils.ObjectFactory;

public class Singleton {

    //声明为 private 避免调用默认构造方法创建对象
    private Singleton() {
    }

    static{
        System.out.print("静态代码块！--");
    }

    //非静态代码块
    {
        System.out.print("非静态代码块！--");
    }

    private int age;

    // 声明为 private 表明静态内部该类只能在该 Singleton 类中被访问
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args){
        try {
            Class ss = Class.forName("staticInnerClass.Singleton");
            Object s = ss.newInstance();    
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}