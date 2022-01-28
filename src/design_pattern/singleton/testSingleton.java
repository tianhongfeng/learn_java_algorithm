package design_pattern.singleton;


public class testSingleton {


    // 懒汉式
//    private static testSingleton sl;
//
//    private testSingleton(){
//    }
//
//    public static testSingleton getInstance(){
//        if(sl == null){
//            sl = new testSingleton();
//        }
//        return sl;
//    }

    // 饿汉式
    private static testSingleton sl = new testSingleton();

    private testSingleton(){}

    public static testSingleton getInstance(){
        return sl;
    }


    public static void main(String[] args) {
        System.out.println(testSingleton.getInstance().hashCode());
        System.out.println(testSingleton.getInstance().hashCode());
        System.out.println(testSingleton.getInstance().hashCode());
    }


}
