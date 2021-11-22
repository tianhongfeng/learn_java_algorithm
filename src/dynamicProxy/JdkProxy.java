package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    // 真实对象
    private Object target;
    private BeforeAdvice beforeAdvice;
    private AfterAdvice afterAdvice;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public BeforeAdvice getBeforeAdvice() {
        return beforeAdvice;
    }

    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    public AfterAdvice getAfterAdvice() {
        return afterAdvice;
    }

    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }

    // 获取代理类
    public Object getObjectProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //前置增强
        if (beforeAdvice != null){
            beforeAdvice.doBefore();
        }

        //通过反射执行主业务逻辑
        Object result = method.invoke(target,args);

        //后置增强
        if (afterAdvice != null){
            afterAdvice.doAfter();
        }
        return result;
    }
}


interface BeforeAdvice{
    void doBefore();
}

interface AfterAdvice{
    void doAfter();
}
