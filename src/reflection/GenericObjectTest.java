package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericObjectTest {

    public static void main(String[] args) throws Throwable {

        //1. GenericObject.class
//        Class clz = GenericObject.class;

        //2.
        Class clz = Class.forName("reflection.GenericObject");
//        Method method = clz.getMethod("getLists");
//
//        Type genericType = method.getGenericReturnType();
//        if(genericType instanceof ParameterizedType){
//            ParameterizedType parameterizedType = ((ParameterizedType) genericType);
//            Type[] types = parameterizedType.getActualTypeArguments();
//            for (Type type : types){
//                Class actualClz = ((Class) type);
//                System.out.println("参数化类型为 ： " + actualClz);
//            }
//        }

        Method setMethod = clz.getMethod("setLists", List.class);
        Type[] genericParameterTypes = setMethod.getGenericParameterTypes();
        for (Type genericParameterType: genericParameterTypes){
            System.out.println("GenericParameterTypes为 ： " + genericParameterType.getTypeName());
            if(genericParameterType instanceof ParameterizedType){
                ParameterizedType parameterizedType = ((ParameterizedType) genericParameterType);
                System.out.println("ParameterizedType为 :" + parameterizedType.getTypeName());
                Type types[] = parameterizedType.getActualTypeArguments();
                for (Type type : types){
                    System.out.println("参数化类型为 ： " + ((Class) type).getName());
                }
            }
        }
    }
}
