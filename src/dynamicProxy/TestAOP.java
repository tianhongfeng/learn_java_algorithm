package dynamicProxy;

import javax.sound.midi.Soundbank;

public class TestAOP {



    public static void main(String[] args) {
        BeforeAdvice beforeAdvice = new BeforeAdvice() {
            @Override
            public void doBefore() {
                System.out.println("前置增强");
            }
        };

        AfterAdvice afterAdvice = new AfterAdvice() {
            @Override
            public void doAfter() {
                System.out.println("后置增强");
            }
        };

        // 定义被代理对象
        SmsService sm = new SmsServiceImpl();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        // 定义代理工厂
        JdkProxy jdkProxy = new JdkProxy();
        jdkProxy.setTarget(sm);
        jdkProxy.setBeforeAdvice(beforeAdvice);
        jdkProxy.setAfterAdvice(afterAdvice);

        // 获取代理类
        SmsService sproxy = (SmsService)jdkProxy.getObjectProxy();

        sproxy.send("java");

    }

}


