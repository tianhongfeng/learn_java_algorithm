package thread;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class test1 implements Runnable{

    private int tickets = 10;

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        while(true){

            try {
                lock.lock();
                if(tickets <= 0) break;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "--拿到了第" + tickets-- + "票");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args){
        test1 thread = new test1();
        new Thread(thread, "小明").start();
        new Thread(thread, "小红").start();
        new Thread(thread, "老师").start();
    }
}

