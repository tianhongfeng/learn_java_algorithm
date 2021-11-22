package test_Generic;

import javax.sound.midi.Soundbank;
import java.time.Instant;
import java.util.*;

public class Producer<T> {

    private T product;

    List<T> ll = new ArrayList<>();

    public T getProduct() {
        return ll.get(new Random().nextInt(ll.size()));
    }


    /**
     * 在泛型类中声明了一个泛型方法，使用泛型 E，这种泛型 E可以为任意类型。可以类型与 T相同，也可以不同。
     */
    public <E> E getDubbleProduct(ArrayList<E> list){
        return list.get(new Random().nextInt(list.size()));
    }


    /*
      泛型方法总结
      泛型方法能使方法独立于类而产生变化，以下是一个基本的指导原则：
      1)无论何时，如果你能做到，你就该尽量使用泛型方法。也就是说，如果使用泛型方法将整个类泛型化，那么就应该使用泛型方法。
      2)另外对于一个static的方法而已，无法访问类上定义的泛型。所以如果static方法要使用泛型能力，就必须使其成为泛型方法。
     */

    /**
     * 定义泛型方法,
     * 1) public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     * 2) 只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * 3) <T>表明该方法将使用泛型类型 T，此时才可以在方法中使用泛型类型T。
     * 4) 与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     * 5) 泛型方法，是在调用方法的时候指明泛型的具体类型 。
     * 6) 泛型方法能使方法独立于类而产生变化
     * @param list
     * @param <T>
     * @return
     */
    public <T> T getProduct(ArrayList<T> list){
        return list.get(new Random().nextInt(list.size()));
    }

    /**
     * 可变参数泛型
     * @param e
     * @param <T>
     */
    public <T> void getvariational(T... e){
        for(T t : e){
            System.out.println(t);
        }
    }

    /*
        ？代替具体的类型实参
        再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，
        可以把？看成所有类型的父类。是一种真实的类型。

        可以解决当具体类型不确定的时候，这个通配符就是 ?
        当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。
     */
    public void getWildcardProduct(ArrayList<?> list){
        System.out.println(list.get(new Random().nextInt(list.size())));
    }

    /**
     * 1) 如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法 。
     * 2) 在类中的静态方法使用泛型：静态方法无法访问类上定义的泛型
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 如：public static void show(T t){
     *         ..
     *    }
     *此时编译器会提示错误信息：
     "StaticGenerator cannot be refrenced from static context"
     */

    // 如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法 。
    public static <T> void show(T t){

    }


    public void setProduct(T product) {
        ll.add(product);
    }



    public static void main(String[] args) {

//        Producer<String> producer1 = new Producer<>();
//        String[] ss= new String[]{"苹果手机","小米手机","华为手机"};
//        for (String s : ss) {
//            producer1.setProduct(s);
//        }
//        System.out.println("恭喜您获得了" + producer1.getProduct());

        System.out.println("----------------");

        Producer<Integer> producer2 = new Producer<>();
//        Integer[] tt= new Integer[]{100, 200, 300};
//        for (Integer s : tt) {
//            producer2.setProduct(s);
//        }
//        System.out.println("恭喜您获得了" + producer2.getProduct() + "元现金");

        ArrayList<String> ll = new ArrayList<>();
        ll.add("苹果手机");
        ll.add("小米手机");
        ll.add("华为手机");
        //System.out.println("恭喜您获得了" + producer2.getProduct(ll));
        //producer2.getvariational(1,2,3);
        ArrayList<Integer> ll2 = new ArrayList<>();
        ll2.add(1);
        ll2.add(1);
        ll2.add(1);


        producer2.getWildcardProduct(ll);

        System.out.println("----------------");

        producer2.getWildcardProduct(ll2);


//        int k = 4;
//        int[] input = new int[]{4,5,1,6,2,7,3,8};
//        Queue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
//        for(int i = 0; i < k; i++){
//            q.offer(input[i]);
//        }
//        for(int i = k; i < input.length; i++){
//            if(input[i] < q.peek()){
//                q.remove();
//                q.offer(input[i]);
//            }
//        }
//
//        ArrayList<Integer> ll = new ArrayList<>();
//        for(Integer i : q){
//            ll.add(i);
//        }




    }
}
