package jvm;

import com.sun.xml.internal.bind.v2.TODO;

import java.sql.SQLOutput;

public class testClassLoader {

    public static void main(String[] args) {

        /*
         获取类加载器
         */

        // AppClassLoader@18b4aac2(应用程序加载器)
/*        System.out.println(c1.getClass().getClassLoader());

        // ExtClassLoader@135fbaa4(扩展类加载器)
        System.out.println(c1.getClass().getClassLoader().getParent());

        // null java程序获取不到
        System.out.println(c1.getClass().getClassLoader().getParent().getParent());
        
        new Thread(() -> {

        }).start();*/



        // 返回虚拟机试图使用最大内存
        long max = Runtime.getRuntime().maxMemory(); // 返回字节数

        // 返回jvm的初始化总内存
        long total = Runtime.getRuntime().totalMemory();

        System.out.println("max = " + max + "字节\t" + (max/(double)1024/1024 + "MB"));
        System.out.println("total = " + total + "字节\t" + (total/(double)1024/1024 + "MB"));

        // 默认情况下分配的总内存是电脑内存的1/4, 而初始化内存为1/64

        /*
        TODO 如果程序出现 OOM
         (1) 尝试扩大内存看结果 -Xms1024m (设置初始内存大小) -Xmx1024m（设置虚拟机最大申请内存大小） -XX:+PrintGCDetails (打印虚拟机堆内信息)
         (2) 分析内存，看一下哪个地方出现了问题（专业工具）


         java虚拟机堆中分为 新生代(YoungGen) 和 老年代(OldGen) + 元空间(Metaspace, 又叫none-heap,不占用堆空间)，所以 新生代 + 老年代 = total

         新生代：eden(伊甸园) + from + to (幸福0区 + 幸福1区)

         */

    }

    /*
    native:带了native关键字的，回去调用C语言库。
    进入本地方法栈，调用本地方法接口 JNI
    JUI作用:扩展java的使用。
    它在内存区域中专门开辟了一块标记区域 Native Method Stack, 登记Native方法
    在最终执行的时候，加载本地方法库中的方法 （通过JNI）
     */
    private native void start0();
}

class Car{

    int age = 1;
}
