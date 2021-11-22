package thread;

import java.util.concurrent.TimeUnit;

public class Lock3 {
    public static void main(String[] args) {

        // 两个对象的class对象只有一个，(static synchronized) 锁的是class
        Phone2 p = new Phone2();
        Phone2 p2 = new Phone2();

        new Thread(() -> {
            p.sendmassage();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            p2.call();
        }, "B").start();

/*        new Thread(() -> {
            p.hello();
        }, "C").start();*/
    }

}
// Phon2 唯一的一个class对象
class Phone2{

    // static 静态方法 ，类一加载就有了，锁的是Class对象
    public static synchronized void sendmassage(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "发短信");
    }

    public static synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "打电话");
    }

    // 这里没有锁，不是同步方法，不受锁的影像
    public void hello(){
        System.out.println("你好");
    }

}
