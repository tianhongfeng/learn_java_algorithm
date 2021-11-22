package thread;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testCondition {



    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "小红").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "小明").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "小李").start();
    }
}



class Data{
    private Lock lock = new ReentrantLock();
    private Condition cc1 = lock.newCondition();
    private Condition cc2 = lock.newCondition();
    private Condition cc3 = lock.newCondition();
    private int number = 1;

    public void printA(){
        lock.lock();

        try {
            while (number != 1){
                cc1.await();
            }
            number = 2;
            System.out.println(Thread.currentThread().getName() + "->AAAAAAAA");
            cc2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while (number != 2){
                cc2.await();
            }
            number = 3;
            System.out.println(Thread.currentThread().getName() + "->BBBBBB");
            cc3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while (number != 3){
                cc3.await();
            }
            number = 1;
            System.out.println(Thread.currentThread().getName() + "->CCCCC");
            cc1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
