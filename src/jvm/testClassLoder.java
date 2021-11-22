package jvm;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class testClassLoder {


    public static void main(String[] args) {
//        Son son = new Son();
        try {
            // 1.获取string的类加载器 为引导类加载器是获取不到
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);

            // 2.获取当前线程的上下文加载器
            ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
            System.out.println(classLoader1);

            // 3.获取系统类加载器
            ClassLoader classLoader3 = ClassLoader.getSystemClassLoader();
            System.out.println(classLoader3);

            // 4.获取扩展类加载器
            ClassLoader classLoader4 = ClassLoader.getSystemClassLoader().getParent();
            System.out.println(classLoader4);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Father{

    public Father(){
        System.out.println("父类构造方法");
    }

//    static {
//        A = 2;
//    }
//    public static int A = 1;
}

class Son extends Father{
//    public static int B = A;

    public Son(){
        System.out.println("子类构造方法");
    }
}
