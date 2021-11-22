package reflection;

import map.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AppleTest {

    public static void main(String[] args) {


        //正常的调用
//        Apple apple = new Apple();
//        apple.setPrice(5);
//        System.out.println("reflection.Apple Price:" + apple.getPrice());

        try {
            //使用反射 实例化
            Class clz = Class.forName("reflection.Apple");

            Constructor appleConstructor = clz.getConstructor();
            Object appleObj = appleConstructor.newInstance();

            // 获取setPrice方法
            Method setPriceMethod = clz.getMethod("setPrice", int.class);
            setPriceMethod.invoke(appleObj, 14);

            /**
             * 获取指定参数并对参数进行修改
             */


            // 获取getPrice
            // 获取共有方法
//            Method getPriceMethod = clz.getMethod("getPrice");

            // 获取私有方法
            Method getPriceMethod = clz.getDeclaredMethod("getPrice");
            getPriceMethod.setAccessible(true);
            System.out.println("reflection.Apple Price:" + getPriceMethod.invoke(appleObj));


            // 获取私有属性
            Field field = clz.getDeclaredField("price");
            //为了对类中的参数进行修改我们取消安全检查
            field.setAccessible(true);
            field.set(appleObj, 15);
            System.out.println("reflection.Apple Price:" + getPriceMethod.invoke(appleObj));


        } catch (Exception  e) {
            e.printStackTrace();
        }

    }
}
