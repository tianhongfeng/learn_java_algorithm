package thread;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class testCallable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        myThread1 thread = new myThread1();
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}

class myThread1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}
