package single;


/**
 * 如上述代码所示，我们可以使用内部类实现线程安全的懒汉式单例，这种方式也是一种效率比较高的做法，它与饿汉式单例的区别就是：
 * 这种方式不但是线程安全的，还是延迟加载的，真正做到了用时才初始化。
 *
 * 当客户端调用getSingleton5()方法时，会触发Holder类的初始化。
 * 由于singleton5是Hold的类成员变量，因此在JVM调用Holder类的类构造器对其进行初始化时，
 * 虚拟机会保证一个类的类构造器在多线程环境中被正确的加锁、同步，如果多个线程同时去初始化一个类，
 * 那么只会有一个线程去执行这个类的类构造器，其他线程都需要阻塞等待，直到活动线程执行方法完毕。
 * 在这种情形下，其他线程虽然会被阻塞，但如果执行类构造器方法的那条线程退出后，其他线程在唤醒之后不会再次进入/执行类构造器，
 * 因为 在同一个类加载器下，一个类型只会被初始化一次，因此就保证了单例。
 *
 */
public class SingletonLazyHungry {

    // 私有内部类，按需加载，用时加载，也就是延迟加载
    private static class Holder {
        private static SingletonLazyHungry singleton5 = new SingletonLazyHungry();
    }

    private SingletonLazyHungry() {

    }

    public static SingletonLazyHungry getSingleton5() {
        return Holder.singleton5;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                System.out.println(LazyHungry.getInstance().hashCode());;
//            }).start();
//        }
    }

}
