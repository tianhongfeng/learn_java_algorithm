package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class testSleep {
    public static void main(String[] args) {
        Date startime = new Date(System.currentTimeMillis());

        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:MM:ss").format(startime));
                startime = new Date(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
