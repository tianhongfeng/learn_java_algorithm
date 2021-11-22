package thread;

public class testJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("线程VIP来了" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 启动线程
        testJoin join = new testJoin();
        Thread t1 = new Thread(join);
        t1.start();

        //主线程
        for(int i = 0; i < 1000; i++){
            if(i == 20){
                t1.join();
            }
            System.out.println("main" + i);
        }

    }
}
