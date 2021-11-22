package test_Generic;

import java.sql.SQLOutput;

public class NumberTool<T> {

    private T kry;

    public NumberTool(T kry) {
        this.kry = kry;
    }

    public T getKry() {
        return kry;
    }

    public void showKey(NumberTool<?> nt){
        System.out.println(nt.getKry());
    }

    public void setKry(T kry) {
        this.kry = kry;
    }

    @Override
    public String toString() {
        return "NumberTool{" +
                "kry=" + kry +
                '}';
    }

    public static void main(String[] args) {

        NumberTool<Integer> nt  = new NumberTool<>(100);
        System.out.println(nt);
        nt.showKey(nt);
        System.out.println("------------------");


        NumberTool<String> nt1  = new NumberTool<>("100");
        System.out.println(nt1);
        nt1.showKey(nt1);

        // TODO 泛型类在创建对象的时候，没有指定类型，将按照Object类型来操作
        NumberTool nt2  = new NumberTool("100");
        Object k = nt2.getKry();
        System.out.println(k);

        // TODO 泛型类不支持基本数据类型
//        NumberTool<int> nt3  = new NumberTool<>(10);

        // TODO  泛型类性在逻辑上可以看成是多个不同的类型，但实际上是相同类型
        System.out.println(nt.getClass()); // class test_Generic.NumberTool
        System.out.println(nt1.getClass()); // class test_Generic.NumberTool
        System.out.println(nt2.getClass()); // class test_Generic.NumberTool
    }
}
