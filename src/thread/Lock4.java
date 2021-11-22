package thread;

import java.util.concurrent.TimeUnit;

public class    Lock4 {
    public static void main(String[] args) {


        // 两个对象的class对象只有一个，(static synchronized) 锁的是class
        // 一个是锁的class 对象 ，一个是锁的方法的调用者，二者互不影响
        Phone4 p = new Phone4();
        Phone4 p2 = new Phone4();

        new Thread(() -> {
            p.sendmassage();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            p2.sendmassage();
        }, "B").start();

    }
}

class Phone4{
    // static synchronized 静态方法 ，类一加载就有了，锁的是Class对象
    public static synchronized void sendmassage(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "发短信");
    }


    // 锁的是方法的调用者
    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "打电话");
    }
}
