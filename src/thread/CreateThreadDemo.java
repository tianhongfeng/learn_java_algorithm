package thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class CreateThreadDemo {
    static Thread t1;
    static Thread t2;

    public static void main(String[] args){

//        // 获取cpu核心数
//        System.out.println(Runtime.getRuntime().availableProcessors());
//
        //1. 继承线程 匿名内部类
        t1 = new Thread(){
            public void run() {
                System.out.println("继承Thread");
                super.run();
            }
        };
        t1.start();
//
//        //2.实现runable接口
        Thread thread1 = new Thread(() -> System.out.println("实现runable接口"));
        thread1.start();
//
//        //3.实现callable
//
        // (1)创建执行服务
        ExecutorService service = Executors.newSingleThreadExecutor();
        // (2)提交执行
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable接口";
            }
        });
//
//
//        //
//        FutureTask<String> ft = new FutureTask(new Callable() {
//            @Override
//            public String call() throws Exception {
//                return "通过实现Callable接口";
//            }
//        });
//        service.submit(ft);
//
        String s = "通过实现Callable接口";
        FutureTask<String> ft_one = new FutureTask(new Callable() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable接口";
            }
        });

        new Thread(ft_one).start();

//
//        try {
//            // (3) 获取结果
//            String result = ft_one.get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        // (4) 关闭服务
//        service.shutdown();
//

//        t1 = new Thread(){
//            public void run() {
//                for(int i = 1; i <= 9; i+=4){
//                    System.out.println(i);
//                    if(i != 9){
//                        System.out.println(i + 2);
//                    }
//                    LockSupport.unpark(t2);
//                    LockSupport.park();
//                }
//            }
//        };
//
//
//        t2 = new Thread(){
//            public void run() {
//                for(int i = 2; i <= 10; i+=2){
//                    LockSupport.park();
//                    System.out.println(i);
//                    LockSupport.unpark(t1);
//                    if(i >= 6){
//                        LockSupport.unpark(t2);
//                    }
//                }
//            }
//        };
//
//        t1.start();
//        t2.start();






























    }
}
