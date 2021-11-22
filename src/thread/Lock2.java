package thread;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Lock2 {
    public static void main(String[] args) {

        // 两个对象，两个调用，有两把锁，两个对象的锁互不影响
        Phone1 p = new Phone1();
        Phone1 p2 = new Phone1();

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

class Phone1{

    // 锁的是方法的调用者
    public synchronized void sendmassage(){
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

    // 这里没有锁，不是同步方法，不受锁的影像
    public void hello(){
        System.out.println("你好");
    }

}
