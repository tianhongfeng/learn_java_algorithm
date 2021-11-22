package thread;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.*;

public class BlockQueueDemo {
    public static void main(String[] args) {
//        try {
//            test4();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////        test2();
//
////        test1();

        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "put 3");
                blockingQueue.put("3");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }


    // 抛出异常
    public static void test1(){

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();

        SynchronousQueue synchronousQueueb = new SynchronousQueue<>();


        System.out.println(arrayBlockingQueue.add("c"));
        System.out.println(arrayBlockingQueue.add("d"));
        System.out.println(arrayBlockingQueue.add("b"));

        // 检测队首元素
        System.out.println(arrayBlockingQueue.element());


        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());

        System.out.println(arrayBlockingQueue.element());

    }

    // 不抛出异常，有返回值
    public static void test2(){

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        arrayBlockingQueue.offer("c");
        arrayBlockingQueue.offer("d");
        arrayBlockingQueue.offer("b");
        arrayBlockingQueue.offer("b");

        System.out.println(arrayBlockingQueue.peek());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        System.out.println(arrayBlockingQueue.peek());

    }

    // 等待一直阻塞
    public static void test3() throws InterruptedException {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        arrayBlockingQueue.put("c");
        arrayBlockingQueue.put("cd");
        arrayBlockingQueue.put("f");
//        arrayBlockingQueue.put("g"); 没有位置了，会一直阻塞

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
//        System.out.println(arrayBlockingQueue.take()); 没有元素了，一直阻塞

    }

    // 超时等待
    public static void test4() throws InterruptedException {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        arrayBlockingQueue.offer("c");
        arrayBlockingQueue.offer("cd");
        arrayBlockingQueue.offer("f");
        arrayBlockingQueue.offer("d", 4, TimeUnit.SECONDS);
//        arrayBlockingQueue.put("g"); 没有位置了，会一直阻塞

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        arrayBlockingQueue.poll(2, TimeUnit.SECONDS);
//        System.out.println(arrayBlockingQueue.take()); 没有元素了，一直阻塞

    }

}



