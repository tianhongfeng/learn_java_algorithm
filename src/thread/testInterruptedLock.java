package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class testInterruptedLock {

    private  static ReentrantLock reentrantLock = new ReentrantLock();


    public static void testsync(){

        String name = Thread.currentThread().getName();
        try {
            reentrantLock.lockInterruptibly();
            System.out.println(name);
        } catch (InterruptedException e) {
            System.out.println(name + "被中止了");
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reentrantLock.unlock();
    }

    public static void main(String[] args) {

        Thread thread = new Thread() {
            public void run() {
                System.out.println("线程启动了");
                while (!isInterrupted()) {
                    System.out.println(isInterrupted());//调用 interrupt 之后为true
                }
                System.out.println("线程结束了");
            }
        };
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("线程是否被中断：" + thread.isInterrupted());//true

//        Thread t1 = new Thread(() -> testsync());
//        Thread t2 = new Thread(() -> testsync());
//
//        t1.start();
//        t2.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        t2.interrupt();

    }



}
