package annotaion;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@myAnnotation(value = "xx")
public class testMyannotation {

    @myAnnotation(value = "xx")
    public void get(){

    }

}

// 自定义一个注解

// Target 表示注解可以用在哪些地方
@Target({TYPE, METHOD})

// Retention 表示注解在什么地方还有效 用于描述注解的生命周期
// RUNTIME > CLASS >SOURCE
@Retention(RetentionPolicy.RUNTIME)

// 表示子类可以继承父类的注解
@Inherited
@interface myAnnotation{
    String value();
}
