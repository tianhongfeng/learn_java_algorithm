package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    独占锁（写锁）一次只能被一个线程占有
    共享锁（读锁）多个线程可以同时占有
    ReadWriteLock
    1. 读-读 可以共存
    2. 读-写 不能共存
    3. 写-写 不能共存
 */
public class ReentrantReadWriteLockDemo {


    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }

}


class MyCache{

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    Map<String, Integer> map = new HashMap<>();

    // 写锁, 希望只有一个线程写
    public void put(String name, int i){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + name);
            map.put(name, i);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    // 读锁
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
