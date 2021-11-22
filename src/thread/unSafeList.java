package thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class unSafeList {

    public static void main(String[] args) {

        // 并发下ArrayList 是不安全的
        List<String> list1 = new ArrayList<>();

        // 解决方法 1.
        List<String> list2 = new Vector<>();
        // 2.
        List<String> list3 = Collections.synchronizedList(new ArrayList<>());

        // 3.
        List<String> list4 = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 100; i++) {

            new Thread(() ->{
//                try {
//                    TimeUnit.SECONDS.sleep(4);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                list4.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list4);
            }, String.valueOf(i)).start();
        }

    }
}
