package thread;

import java.util.concurrent.TimeUnit;

public class testVolatile {

    // 加 volatile 可以保证可见性
    private static volatile int num = 0;
    public static void main(String[] args) {

        /*
         volatile 1.保证可见性
                  2.不保证原子性
                  3.禁止指令重排序
         */


        new Thread(() -> { // 该线程对主内存的变化不知道，需要加上 volatile 关键字修饰
            while(num == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);










    }
}
