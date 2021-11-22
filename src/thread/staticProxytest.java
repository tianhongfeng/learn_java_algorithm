package thread;

import staticProxy.SmsProxy;

public class staticProxytest {

    public static void main(String[] args){

        // 注释 ctrl + shift + /
        // 快速复制 ctrl + d
        // psvm 快速 public static void main(String[] args)
        // sout 快速 System.out.println();
        // alt + insert 显示要重写或实现的方法


        // lamdba 表达式
        SmsProxy smsProxy = new SmsProxy((String x)->{
            System.out.println("send message:" + x);
        });

        // lamdba 表达式 简化参数类型
        SmsProxy smsProxy2 = new SmsProxy((x)->{
            System.out.println("send message:" + x);
        });

        // lamdba 表达式 简化小括号
        SmsProxy smsProxy3 = new SmsProxy(x->{
            System.out.println("send message:" + x);
            System.out.println("send message:" + x);
        });

        // lamdba 表达式 简化花括号(接口方法实现只有一行代码时)
        SmsProxy smsProxy4 = new SmsProxy(x -> System.out.println("send message:" + x));

        System.out.println();
        smsProxy.send("java");

        /**
         * 1.必须是函数式接口
         * 2.如果为多个参数则不可以去掉参数的括号，参数类型可以去掉
         */

        /**
         * 1.静态内部类
         * 2.局部内部类
         * 3.
         */


    }

}
