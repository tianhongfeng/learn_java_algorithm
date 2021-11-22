package innerClass;

public class TestInnerClass {

    public static void main(String[] args)  {

        Outter outter = new Outter();

        //第一种方式：访问内部类
       // Outter.Inner inner = outter.new Inner();  //必须通过Outter对象来创建

        //第二种方式：访问内部类
        //Outter.Inner inner1 = outter.getInnerInstance();


        //
        outter.getLocalInnerInstance(3);

        // 访问静态内部类
        Outter.staticInnerClass staticInnerClass = new Outter.staticInnerClass();

    }

}




