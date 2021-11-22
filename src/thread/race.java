package thread;


import java.util.concurrent.*;

public class race implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //
        race raceCallable = new race();

        // 创建服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Boolean> future = service.submit(raceCallable);
        Future<Boolean> future1 = service.submit(raceCallable);
        Future<Boolean> future2 = service.submit(raceCallable);
        // 获取结果
        Boolean R1 = future.get();
        Boolean R2 = future1.get();
        Boolean R3 = future2.get();
        // 提交服务
        service.shutdownNow();



    }
}
