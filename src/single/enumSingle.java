package single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum enumSingle {

    INSTANCE;
    public  enumSingle getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        enumSingle instance1 = enumSingle.INSTANCE;

        Constructor<enumSingle> constructor = enumSingle.class.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);

        /**
         * TODO 枚举可以保证单例模式，不会被反射破坏
         * java.lang.IllegalArgumentException: Cannot reflectively create enum objects
          */
        enumSingle instance2 = constructor.newInstance();

        System.out.println(instance1);
        System.out.println(instance2);

    }
}



