package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyHandler implements InvocationHandler {

    // 声明被代理对象
    private Object target;

    public MyHandler(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){

        // 通过Proxy类的 newProxyInstance 方法动态的生成 ‘中介’
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    /**
     *  通过invoke 方法进行业务增强操作
     *  proxy  动态生成的代理类对象
     *  method 被代理对象当前正在执行的方法
     *  args   被代理对象当前正在执行的方法中的参数
      */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("获取连接");

        // 方法名称
        System.out.println(method.getName());

        // 调用被代理对象的方法
        method.invoke(target, args);

        System.out.println("关闭连接");

        return null;
    }

    public static void main(String[] args) {
        // 被代理的类
        SmsService sm = new SmsServiceImpl();
        SvmService sv = new SvmServiceImpl();

        // 定义一个 invocationHandler 实现代理方法
        MyHandler myHandler = new MyHandler(sm);
        MyHandler myHandler_1 = new MyHandler(sv);



        // 获取代理类
        SmsService sproxy = (SmsService) myHandler.getProxyInstance();

        SvmService sproxy_1 = (SvmService)myHandler_1.getProxyInstance();

        // sproxy.send("执行数据库语句");
        System.out.println("--------------");
        //sproxy_1.send("执行数据库语句");
        System.out.println(sproxy_1.equals(sv));

        // 打印生成的代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

    }
}
