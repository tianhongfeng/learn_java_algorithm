package thread;

import java.util.concurrent.TimeUnit;

public class Lock1 {
    public static void main(String[] args) {
        Phone p = new Phone();

        new Thread(() -> {
            p.sendmassage();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            p.sendmassage();
        }, "B").start();

        new Thread(() -> {
            p.hello();
        }, "C").start();
    }

}

class Phone{

    // 锁的是方法的调用者
    // 由于是同一个对象调用的，所以sendmassage() 和 call() 用的是同一把锁，谁先拿谁先执行
    public synchronized void sendmassage(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "发短信");
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "打电话");
    }

    // 这里没有锁，不是同步方法，不受锁的影像
    public void hello(){
        System.out.println("你好");
    }

}
