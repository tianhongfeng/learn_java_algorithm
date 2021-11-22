package thread;

import java.util.concurrent.Callable;

public class testYield {

    public static void main(String[] args) {
        myYield yy = new myYield();
        new Thread(yy, "A").start();
        new Thread(yy,"B").start();
    }
}


class myYield implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程开始执行");
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + "线程停止执行");
        }
}
