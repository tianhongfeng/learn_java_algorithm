package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;


public class AtomicIntegerTest {

    private AtomicInteger count = new AtomicInteger();
    //使用AtomicInteger之后，不需要对该方法加锁，也可以实现线程安全。
    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) {
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        atomicIntegerTest.increment();
        System.out.println(atomicIntegerTest.getCount());
    }
}
