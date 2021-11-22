package thread;

import java.sql.SQLOutput;

public class testDaemon {



    public static void main(String[] args) {

        god g = new god();
        you y = new you();

        Thread thread = new Thread(g);
        thread.setDaemon(true);
        thread.start();

        new Thread(y).start();

    }

}

class god implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("上帝一直守护着你");
        }
    }
}

class you implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 365; i++) {
            System.out.println("你一生都开心的活着");
        }
        System.out.println("goodbye world");
    }
}


