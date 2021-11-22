package thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class unSafeSet {
    public static void main(String[] args) {

/*        // 并发下set
        Set<String> ss = new HashSet<>();

        // 1.解决并发问题，应用Collections工具类
        Set<String> ss1 = Collections.synchronizedSet(new HashSet<>());

        // 2.应用CopyOnWriteArraySet
        Set<String> ss2 = new CopyOnWriteArraySet();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                ss2.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(ss2);
            }).start();
        }*/



        // 并发下 hashmap
        Map<String, String> map = new HashMap<>();

        // 应用 Collections
        Map<String, String> map1 = Collections.synchronizedMap(new HashMap<>());

        //
        Map<String, String> map2 = new ConcurrentHashMap<>();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                map1.put(UUID.randomUUID().toString().substring(0, 5), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map1);
            }).start();
        }

    }
}
