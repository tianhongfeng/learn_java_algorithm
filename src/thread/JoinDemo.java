package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class JoinDemo {

    AtomicInteger count = new AtomicInteger();
    void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet(); // count++
        }
    }

    public static void main(String[] args) {


        JoinDemo t = new JoinDemo();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                // 等待子线程执行完
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);








//        Thread previousThread = Thread.currentThread();
//        for (int i = 1; i <= 10; i++) {
//            Thread curThread = new JoinThread(previousThread);
//            curThread.start();
//            previousThread = curThread;
//        }
    }

    static class JoinThread extends Thread {
        private Thread thread;

        public JoinThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.out.println(thread.getName() + " terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
