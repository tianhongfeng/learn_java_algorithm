package test_Generic;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestGenericArray {

    public static void main(String[] args) {
        Fruit<String> f = new Fruit<>(String.class, 3);
        f.put(0, "1");
        f.put(1, "2");
        f.put(2, "3");
        System.out.println(Arrays.toString(f.getArray()));
    }
}

class Fruit<T>{

    private T[] array;

    public Fruit(Class<T> clz, int length){
        // 通过反射创建泛型数组
        array = (T[]) Array.newInstance(clz, length);
    }

    public void put(int index, T item){
        array[index] = item;
    }

    public T get(int index){
        return array[index];
    }

    public T[] getArray(){
        return array;
    }
}

