package jvm;

import java.util.concurrent.TimeUnit;

public class FinalDemp {

    private int a;  //普通域
    private final int b; //final域
    private static FinalDemp finalDemo;

    public FinalDemp() {
        a = 1; // 1. 写普通域
        b = 2; // 2. 写final域
    }

    public static void writer() {
        finalDemo = new FinalDemp();
    }

    public static void reader() {
        FinalDemp demo = finalDemo; // 3.读对象引用
        while (demo == null){

        }
        int a = demo.a;    //4.读普通域
        int b = demo.b;    //5.读final域
        System.out.println(Thread.currentThread().getName() + "i:=" + a);
        System.out.println(Thread.currentThread().getName() + "j:=" + b);
    }

    public static void main(String[] args) {

        new Thread(() -> {
            FinalDemp.writer();
        }, "A").start();

        new Thread(() -> {
            FinalDemp.reader();
        }, "C").start();

        new Thread(() -> {
            FinalDemp.reader();
        }, "B").start();




    }

}
