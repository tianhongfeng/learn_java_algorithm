package single;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

// 懒汉式

/**
 * 为了在保证单例的前提下提高运行效率，我们需要对 getInstance()进行第二次检查，
 * 目的是避开过多的同步（因为这里的同步只需在第一次创建实例时才同步，一旦创建成功，以后获取实例时就不需要同步获取锁了
 */
public class LazyHungry {

    // 设置一个标志位来检测是否使用反射创建实例
    private static boolean flag = false;

    // 私有构造方法
    @SuppressWarnings("all")
    private LazyHungry(){
        synchronized (LazyHungry.class){

            // 1. 一个使用getInstance，一个使用反射创建是可以避免的，但是如果通过反射创建两个对象还是可以的
//            if(lazyHungry != null){
//                throw new RuntimeException("不要试图用反射创建实例");
//            }

            // 2. 通过使用标志位来禁止使用反射创建实例，但是还可以通过反射修改flag值，来破坏单例
//            if(flag == false){
//                flag = true;
//            }else{
//                throw new RuntimeException("不要试图用反射创建实例");
//            }
        }
//        System.out.println(Thread.currentThread().getName() + "OK");
    }

    // 多线程下加上 volatile
    private volatile static LazyHungry lazyHungry;


    // 多线程下 必须加上锁才能保证是单例
    public LazyHungry getInstance(){

        // 双重检测锁的 懒汉式单例
        if(lazyHungry == null){
            synchronized (LazyHungry.class){
                // TODO 假如没有第二次校验 如果多个线程进入第一个if判断，则会创建多个对象
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                lazyHungry = new LazyHungry();

                // TODO: 加了二次验证
                if(lazyHungry == null){
                    /*
                        创建一个对象不是原子性操作,经过以下操作
                        1. 分配内存空间
                        2. 执行构造方法，初始化对象
                        3. 把这个对象指向这个空间

                        多线程下可能出现 1，3，2 加入当执行到3时另一个线程 判断 lazyHungry!= null,就会直接返回，此时对象还是空的没有执行构造方法
                        所以要是多线程下会发生指令重排问题，需要 加上 volatile
                     */
                    lazyHungry = new LazyHungry();
                }
            }
        }
        return lazyHungry;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                System.out.println(new LazyHungry().getInstance().hashCode());
//            }).start();
            System.out.println(new LazyHungry().getInstance().hashCode());
        }

//        // 获取构造方法
//        Constructor<LazyHungry> constructor = LazyHungry.class.getDeclaredConstructor(null);
//        constructor.setAccessible(true);
//        // 创建实例
//        // 通过反射创建两个实例
//        LazyHungry lazyHungry1 = constructor.newInstance();
//
//        // 修改flag的值
//        Field field = LazyHungry.class.getDeclaredField("flag");
//        field.setAccessible(true);
//        field.set(lazyHungry1, false);
//        LazyHungry lazyHungry = constructor.newInstance();
//
//        System.out.println(lazyHungry);
//        System.out.println(lazyHungry1);

    }
}
