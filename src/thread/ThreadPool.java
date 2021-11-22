package thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPool {

    private static final int corePoolSize = 5;
    private static final int maximumPoolSize = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;



    static class MyThread implements Runnable {
        String name;
        public MyThread(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程:"+Thread.currentThread().getName() +" 执行:"+name +"  run");
        }
    }

    public static void main(String[] args) throws InterruptedException {


        // 创建一个 核心线程数为1 最大线程数为2 阻塞队列容量为2 的线程池
        // 测试抛异常策略
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 0,
//                TimeUnit.MICROSECONDS,
//                new LinkedBlockingDeque<Runnable>(2),
//                new ThreadPoolExecutor.AbortPolicy());

        // 创建一个 核心线程数为1 最大线程数为2 阻塞队列容量为2 的线程池
        // 测试 线程池无法执行该任务时，交给线程池所在的线程执行，该拒绝策略会阻塞线程池所在的执行直至任务执行完
        // (该测试被拒绝的任务会被main线程执行)
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 0,
//                TimeUnit.MICROSECONDS,
//                new LinkedBlockingDeque<Runnable>(2),
//                new ThreadPoolExecutor.CallerRunsPolicy());


        // 该拒绝策略会直接抛弃任务
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 0,
//                TimeUnit.MICROSECONDS,
//                new LinkedBlockingDeque<Runnable>(2),
//                new ThreadPoolExecutor.DiscardPolicy());


        // 该拒绝策略会抛弃任务队列中最旧的任务也就是最先加入队列的，再把这个新任务添加进去。
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 0,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(2),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 6; i++) {
            System.out.println("添加第"+i+"个任务");
            executor.execute(new MyThread("任务"+i));
            Iterator iterator = executor.getQueue().iterator();
            while (iterator.hasNext()){
                MyThread thread = (MyThread) iterator.next();
                System.out.println("列表："+thread.name);
            }
        }



//        // Executors 工具类 3大方法
//
//        // 单个线程
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
//        // 创建一个固定的线程池大小
        ExecutorService threadPool2 = Executors.newFixedThreadPool(5);
//        // 可伸缩的线程池
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 50; i++) {
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "运行了");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，关闭线程池
            threadPool3.shutdown();
        }


        /*
         7大参数
        corePoolSize        核心线程池大小
        maximumPoolSize     最大核心线程池大小
        keepAliveTime       超时了没有人调用就会释放
        TimeUnit unit       超时单位
        BlockingQueue<Runnable> workQueue   阻塞队列
        ThreadFactory threadFactory         线程工厂

        RejectedExecutionHandler handler    拒绝策略
        (1) ThreadPoolExecutor.AbortPolicy() 阻塞队列满了，还有进来的就不处理了，并且抛出异常
        (2) ThreadPoolExecutor.CallerRunsPolicy() 哪来的回哪里去，调用它的方法中执行
        (3) ThreadPoolExecutor.DiscardPolicy() 阻塞队列满了,丢掉任务，不会抛出异常
        (4) ThreadPoolExecutor.DiscardOldestPolicy() 阻塞队列满了,和最早的竞争，不会抛出异常
         */

        /**
         * 线程池的大小，最大值如何设置
         * 1.CPU密集型，几核，就定义几，可以保持CPU效率最高
         * 2.IO密集型 判断程序中十分消耗资源的线程数，然后大于它就可以了，一般是它的两倍
         */

        // 获取CPU核心数
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        ExecutorService threadPool = new ThreadPoolExecutor(
//                3,
//                5,
//                Runtime.getRuntime().availableProcessors(),
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(3),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardOldestPolicy()); //
//
//        try {
//            for (int i = 1; i <= 9; i++) {
//                // 使用了线程池后，用线程池创建线程
//                threadPool.execute(() -> {
//                    System.out.println(Thread.currentThread().getName());
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 线程池用完,关闭线程池
//            threadPool.shutdown();
//        }


        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建

//        ExecutorService executorService = new ThreadPoolExecutor(
//                corePoolSize,
//                maximumPoolSize,
//                KEEP_ALIVE_TIME,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
//                new ThreadPoolExecutor.CallerRunsPolicy());
//
//        List<Future<String>> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Future<String> future = executorService.submit(() -> {
//                TimeUnit.SECONDS.sleep(10);
//                //返回执行当前 Callable 的线程名字
//                return Thread.currentThread().getName();
//            });
//            //将返回值 future 添加到 list，我们可以通过 future 获得 执行 Callable 得到的返回值
//            list.add(future);
//        }
//
//        for(Future<String> ff:list){
//            try {
//                System.out.println(new Date() + "::" + ff.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }






        //关闭线程池
        /*
        shutdown只是阻止新任务加入到队列中，它仍然会让已经执行的以及在就绪队列中的任务接下来执行，只不过主线程不会阻塞下来等剩下的线程来执行。
         */
//        executorService.shutdown();
//        while (! executorService.isTerminated()){
//            System.out.println("Not finished");
//        }

        /*
        让主线程等待剩下线程执行的方法是 awaitTermination
         */
//        executorService.shutdown();
//        executorService.awaitTermination(10, TimeUnit.SECONDS);
//        while (! executorService.isTerminated()){
//            System.out.println("Not finished");
//        }

    }
}
