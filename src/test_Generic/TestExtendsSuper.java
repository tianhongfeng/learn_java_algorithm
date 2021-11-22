package test_Generic;

import java.util.*;

public class TestExtendsSuper {

    public static void main(String[] args) {


        /**
         * 泛型数组
         * 可以声明带泛型数组的引用，但是不能直接创建带泛型数组的对象
         */

        //可以声明带泛型数组的引用
        ArrayList[] ls = new ArrayList[5];
        ArrayList<String>[] ll = ls;

        /*
          不能直接创建带泛型数组的对象
          ArrayList<String>[] ll1 = new ArrayList<String>[5]; 报错
         */
        ArrayList<String>[] ll1 = new ArrayList[5];

        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<MinCat> minCats = new ArrayList<>();

        showExtendsAnimal(cats);
        showExtendsAnimal(minCats);

        showSuperAnimal(cats);
        showSuperAnimal(animals);

        Set<Integer> set = new TreeSet<>();

        cats.addAll(minCats);
        cats.addAll(cats);

    }

    /**
     * 泛型上限通配符， 即传入的类型实参必须是指定类型或指定类型的子类型
     * showAnimal(animals); 编译错误，因为Animal不是Cat的子类
     * @param list
     */
    public static void showExtendsAnimal(List<? extends Cat> list){

        /*
          list.add(new Cat()); 会报错
          由于是上限通配符，所以我们无法确定list现在存储的是什么类型，所以不允许添加元素
         */
        for (int i = 0; i < list.size(); i++) {
            Animal animal = list.get(i);
            System.out.println(animal);
        }
    }

    /**
     * 泛型下限通配符， 即传入的类型实参必须是指定类型或指定类型的父类型
     * showSuperAnimal(minCats); 编译错误，因为MinCat不是Cat的父类
     * @param list
     */
    public static void showSuperAnimal(List<? super Cat> list){

        list.add(new Cat("jack", 20));
        /*
         list.add(new Animal()); 报错
         因为我们无法确定具体哪个父类型，只能添加 Cat 或 Cat的子类，
         */

        // 因为我们无法确定具体哪个父类型，所以获取的时候用Object
        for (Object o : list) {
            System.out.println(o);
        }
    }

}

class Animal{
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Cat extends Animal{

    public int age;

    public Cat(String name, int age) {
        super(name);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class MinCat extends Cat{
    public int level;

    public MinCat(String name, int age, int level) {
        super(name, age);
        this.level = level;
    }

    @Override
    public String toString() {
        return "MinCat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", level=" + level +
                '}';
    }
}
