package thread;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.*;

public class testThreadHelp {

    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(6);
//
//        for (int i = 0; i <= 6; i++) {
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + "出去了");
//                countDownLatch.countDown();
//            }, String.valueOf(i)).start();
//        }
//
//        countDownLatch.await();
//        System.out.println("关门了");


//        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->{
//            System.out.println("召唤神龙");
//        });
//        for (int i = 0; i < 100; i++) {
//            int temp = i;
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + "集齐了" + temp + "颗龙珠");
//                try {
//                    cyclicBarrier.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }

        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }


}
