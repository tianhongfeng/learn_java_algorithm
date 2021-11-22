package thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testPC{


    public static void main(String[] args) {



















//        System.out.println(new String("欢饮你! ").length());
//
//        short i = 1;
//        i = (short) (i + 1);
//        i +=1;
//        SynContainer container = new SynContainer();
//        new Thread(new Product(container), "A1").start();
//        new Thread(new Consumer(container), "B1").start();
//        new Thread(new Product(container), "A2").start();
//        new Thread(new Consumer(container), "B2").start();


/*        Chicken c1 = new Chicken(2, "a", 21);
        Chicken c2 = new Chicken(3, "b", 22);
        Chicken c3 = new Chicken(4, "c", 23);
        Chicken c4 = new Chicken(8, "d", 24);
        Chicken c5 = new Chicken(6, "e", 26);

        List<Chicken> ll = Arrays.asList(c1, c2, c3, c4, c5);
        ll.stream().filter(u -> {return u.getId() % 2 == 0;})
                .filter(u -> {return u.getAge() > 23;})
                .map(u -> {return u.getName().toUpperCase();})
                .sorted((uu1, uu2) -> {return uu2.compareTo(uu1);})
                .limit(1)
                .forEach(System.out::println);*/
    }

}


class Product implements Runnable{

    SynContainer container;
    public Product(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println(Thread.currentThread().getName() + "生产了" + i + "只鸡");
        }
    }
}

class Consumer implements Runnable{


    SynContainer container;
    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "消费了" + container.pop().id + "只鸡");
        }
    }
}

class Chicken {
    int id;
    String name;
    int age;

    public Chicken(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}


// 缓冲区
class SynContainer{


    // 需要一个容器大小
    private Chicken[] chickens = new Chicken[10];

    // 容器计数器
    private int count = 0;

    // 1.定义lock锁
    private final Lock lock = new ReentrantLock();
    private Condition cc = lock.newCondition();

    //
    public void push(Chicken c){
        lock.lock();
        // 如果容器满了就等待消费者消费
        try {
            while (count == chickens.length){
                try {
                    cc.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 容器增加
            chickens[count++] = c;
            // 通知
            cc.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Chicken pop(){

        lock.lock();
        Chicken c = null;
        try {
            // 如果容器空了就等生产者生产
            while (count == 0){
                try {
                    cc.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 容器减少
            c = chickens[--count];
            // 通知
            cc.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return c;
    }


}
