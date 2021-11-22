package thread;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 线程异步调用
public class asynchronizationFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

/*        // 异步执行 没有返回值的 runAsync 异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + "runAsync->void");
        });

        // 主线程不阻塞
        System.out.println("1111");

        // 获取异步线程返回结果
        try {
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


        // 有返回值的 supplyAsync 异步回调
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
//            int i = 10/0;
            return 1021;
        });

        System.out.println(
            completableFuture1.whenComplete((t, u) -> {
            System.out.println("t=" + t); // t 为异步执行成功返回结果
            System.out.println("u=" + u); // u 为错误信息
        }).exceptionally(e -> { // 异步执行失败返回结果
            e.getMessage();
            return 233; // 可以获取到错的返回结果
        }).get());// 获取 异步调用返回值
    }

}
