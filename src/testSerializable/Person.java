package testSerializable;

import java.io.*;

public class Person implements Serializable {

    private String name;

    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(String name, int age) {
        System.out.println("构造方法被调用了");
        this.name = name;
        this.age = age;
    }


}

class WriteObject {

    public static void main(String[] args) {

        try (//创建一个ObjectOutputStream输出流
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PersonSerializable.txt"))) {
            //将对象序列化到文件s
            Person person = new Person("田洪锋", 23);
            oos.writeObject(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class ReadObject  {

    public static void main(String[] args) {

        try (//创建一个ObjectInputStream输入流
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PersonSerializable.txt"))) {
            Person brady = (Person) ois.readObject();
            System.out.println(brady);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
