package test_Generic;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.HashMap;
import java.util.Map;

public interface TestGenInterface<T> {
    T getKey();
}

/**
 * 泛型接口的实现类，如果不是泛型类，需要明确实现泛型接口的类型
 */
class SubGenInterface implements TestGenInterface<String>{

    @Override
    public String getKey() {
        return "泛型接口1";
    }

    public static void main(String[] args) {
        SubGenInterface subGenInterface = new SubGenInterface();
        System.out.println(subGenInterface.getKey());
    }
}

/**
 *  泛型接口的实现类，如果是泛型类，那么要保证实现接口的泛型类泛型标识包含泛型接口的泛型标识
 */
class SubDubbleGenInterface<T, K> implements TestGenInterface<T>{

    private T key;
    private K value;

    public SubDubbleGenInterface(T key, K value) {
        this.key = key;
        this.value = value;
    }

    public K getValue() {
        return value;
    }

    @Override
    public T getKey() {
        return key;
    }

    public static void main(String[] args) {
        SubDubbleGenInterface<String, Integer> subDubbleGenInterface = new SubDubbleGenInterface("mm", 100);
        String key = subDubbleGenInterface.getKey();
        Integer value = subDubbleGenInterface.getValue();
        System.out.println(key + "=" + value);
    }
}
