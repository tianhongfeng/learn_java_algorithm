package thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testThreadpool {


    public static void main(String[] args) {
        // 1.创建服务，创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        //2.提交线程
        service.execute(new myThread());
        service.execute(new myThread());
        service.execute(new myThread());
        service.execute(new myThread());

        //3.关闭线程池链接
        service.shutdown();
    }
}


class myThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}