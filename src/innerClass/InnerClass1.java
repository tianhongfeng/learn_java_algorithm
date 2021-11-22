package innerClass;
import static java.lang.System.out;

//类一
class ClassA {
    public String name(){
        return "liutao";
    }
    public String doSomeThing(){
        // doSomeThing
        return "";
    }
}
//类二
class ClassB {
    public int age(){
        return 25;
    }
}


/**
 * 内部弥补了 java 无法多继承
 */
public class InnerClass1 {

    private class Test1 extends ClassA{
        public String name(){
            return super.name();
        }
    }
    private class Test2 extends ClassB{
        public int age(){
            return super.age();
        }
    }

    public String name(){
        return new Test1().name();
    }
    public int age(){
        return new Test2().age();
    }

    public static void main(String args[]){

        InnerClass1 mi=new InnerClass1();
        System.out.println("姓名:"+mi.name());
        System.out.println("年龄:"+mi.age());
    }

}
