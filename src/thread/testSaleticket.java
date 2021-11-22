package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testSaleticket {



    public static void main(String[] args) {
        Sale ss = new Sale();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ss.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ss.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ss.sale();
            }
        }, "C").start();
    }

}



class Sale{
    private int ticket = 50;

    // 1.定义lock锁
    private final Lock lock = new ReentrantLock();

    public void sale(){
        //  2.加锁
        lock.lock();

        try {
            // 业务代码
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + "卖了第" + ticket-- + "票" + "还剩余" + ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 3.解锁
            lock.unlock();
        }







    }
}
