package reflection;

import java.sql.SQLOutput;


/*
1.类的主动引用(一定会发生类的初始化)
2.类的被动引用(不会发生类的初始化)
 */
public class testClassInitialize {

    static{
        System.out.println("Main类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 1.主动引用 (如果父类没初始化，则先初始化父类)
//        Son s = new Son();
//        System.out.println(Son.m);
        System.out.println(Son.m);

        //2.反射也会引发主动引用
//        Class.forName("reflection.Son");

        //3.调用类的静态成员或静态方法会引发主动引用
//        System.out.println(Son.m);
        // (除了final常量)
//        System.out.println(Son.M);


        //4.通过子类调用父类的静态成员，不会引发子类初始化
//        System.out.println(Son.b);

        //5.定义类型数组，不会引发初始化
//        Son[] array = new Son[5];

        //6.调用常量不会引发初始化
//        System.out.println(Son.W);
    }
}

class Father{


    static {
        b = 300;
        System.out.println("父类初始化:");
    }

    static int b = 1;

    static final int W = 1;
}

class Son extends Father{

    static int m = 1;

    static {
        m = 300;
        System.out.println("子类初始化:");
    }


    static final int M = 1;

}
