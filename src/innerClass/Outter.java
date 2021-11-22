package innerClass;

class Outter1{

}

public class Outter {

    private int noStaticInt = 1;
    private static int STATIC_INT = 2;

    private Inner inner = null;
    public Outter() {

    }

    public Inner getInnerInstance() {
        if(inner == null)
            inner = new Inner();
        return inner;
    }

    /**
     * 1. 局部内类不允许使用访问权限修饰符 public private protected 均不允许
     * 2. 局部内部类对外完全隐藏，除了创建这个类的方法可以访问它,其他的地方是不允许访问的。
     */
    // 局部内部类
    public void getLocalInnerInstance(final int age) {

        // TODO public private protected 均不允许
        class LocalInner extends Outter1{
             private void fun(){
                 System.out.println("局部内部类的输出");
                 System.out.println(STATIC_INT);
                 System.out.println(noStaticInt);
                 System.out.println(age);
                // System.out.println(params);
             }
        }
        LocalInner localInner = new LocalInner();
        localInner.fun();
    }

    /**
     * 静态内部类和非静态内部类的区别
     *
     * 静态内部类可以有静态成员，而非静态内部类则不能有静态成员。
     * 静态内部类可以访问外部类的静态变量，而不可访问外部类的非静态变量；
     * 非静态内部类的非静态成员可以访问外部类的非静态变量。
     * 静态内部类的创建不依赖于外部类，而非静态内部类必须依赖于外部类的创建而创建。
     *
      */

    //静态内部类
    static class staticInnerClass {
        static int a = 1; // 静态内部类可以有静态成员
        public staticInnerClass(){

        }
        public void fun(){

            // TODO 静态内部类 不可访问外部类的非静态变量
            //  System.out.println(noStaticInt);

            // TODO 静态内部类  可以访问外部类的静态变量
            System.out.println(STATIC_INT);
        }
    }

    //静态内部类
    static class staticInnerClass1 {
        static int a = 1; // 静态内部类可以有静态成员
        public staticInnerClass1(){

        }
        public void fun(){

            // TODO 静态内部类 不可访问外部类的非静态变量
            //  System.out.println(noStaticInt);

            // TODO 静态内部类  可以访问外部类的静态变量
            System.out.println(STATIC_INT);
        }
    }

    // 内部类
    protected class Inner {
        // static int i = 1; 非静态内部类则不能有静态成员,编译错误
        public Inner() {

        }
        public void fun(){
            // TODO 非静态内部类的非静态成员可以访问外部类的 非静态变量和 静态变量。
            System.out.println(noStaticInt);
            System.out.println(STATIC_INT);
        }
    }

    /**
     * 1. 匿名内部类是没有访问修饰符的
     * 2. 匿名内部类中不能存在任何静态成员或方法
     *
     */
    public void click(final int params){
        //匿名内部类，实现的是ActionListener接口
        new ActionListener(){
            //  匿名内部类中不能存在任何静态成员或方法 static int i = 0;
            public void onAction(){
                System.out.println("click action..." + params);
            }
        }.onAction();
    }

    //匿名内部类必须继承或实现一个已有的接口
    public interface ActionListener{
        public void onAction();
    }

}
