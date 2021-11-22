package thread;


/**
 * 在使用synchronized 代码块时,可以与wait()、notify()、nitifyAll()一起使用，从而进一步实现线程的通信。
 * 其中wait()方法会释放占有的对象锁，当前线程进入等待池，释放cpu,而其他正在等待的线程即可抢占此锁，获得锁的线程即可运行程序；
 * 线程的sleep()方法则表示，当前线程会休眠一段时间，休眠期间，会暂时释放cpu，但并不释放对象锁，也就是说，
 * 在休眠期间，其他线程依然无法进入被同步保护的代码内部，当前线程休眠结束时，会重新获得cpu执行权,从而执行被同步保护的代码。
 *
 * wait()和sleep()最大的不同在于wait()会释放对象锁，而sleep()不会释放对象锁。
 *
 * notify()方法会唤醒因为调用对象的wait()而处于等待状态的线程，从而使得该线程有机会获取对象锁。
 * 调用notify()后，当前线程并不会立即释放锁，而是继续执行当前代码，直到synchronized中的代码全部执行完毕，才会释放对象锁。
 * JVM会在等待的线程中调度一个线程去获得对象锁，执行代码。
 *
 * todo 需要注意的是，wait()和notify()必须在synchronized代码块中调用。notifyAll()是唤醒所有等待的线程
 */
public class testSynchronized {

    static final testSynchronized obj = new testSynchronized();

    int tickets = 15;

    class SellTickets implements Runnable {
        @Override
        public void run() {
            //同步方法
            while (tickets > 0) {
                synMethod();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (tickets <= 0) {
                    System.out.println(Thread.currentThread().getName() + "--->售票结束");
                }
            }
        }

        synchronized void synMethod() {
            if (tickets <= 0) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + "---->售出第 " + tickets + " 票 ");
            tickets--;
        }
    }

    public static void main(String[] args) {
        SellTickets sellTickets = new testSynchronized().new SellTickets();
        Thread thread1 = new Thread(sellTickets, "1号窗口");
        Thread thread2 = new Thread(sellTickets, "2号窗口");
        Thread thread3 = new Thread(sellTickets, "3号窗口");
        Thread thread4 = new Thread(sellTickets, "4号窗口");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int count = 5;
//                while (count > 0) {
//                    synchronized (obj) {
//                        System.out.println("A-----" + count);
//                        count--;
//                        synchronized (obj) {
//                            //notify()方法会唤醒因为调用对象的wait()而处于等待状态的线程，从而使得该线程有机会获取对象锁。
//                            // TODO 调用notify()后，当前线程并不会立即释放锁，而是继续执行当前代码，直到synchronized中的代码全部执行完毕，
//                            obj.notify();
//                            try {
//                                obj.wait();
//                            } catch (InterruptedException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int count = 5;
//                while (count > 0) {
//                    synchronized (obj) {
//                        System.out.println("B-----" + count);
//                        count--;
//                        synchronized (obj) {
//                            //notify()方法会唤醒因为调用对象的wait()而处于等待状态的线程，从而使得该线程有机会获取对象锁。
//                            //调用notify()后，当前线程并不会立即释放锁，而是继续执行当前代码，直到synchronized中的代码全部执行完毕，
//                            obj.notify();
//                            try {
//                                obj.wait();
//                            } catch (InterruptedException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//                }
//            }
//        }).start();
    }
}
