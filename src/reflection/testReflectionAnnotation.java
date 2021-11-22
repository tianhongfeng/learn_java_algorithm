package reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class testReflectionAnnotation {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("reflection.student");

        // 通过反射获得注解
        Annotation[] annotations = c1.getAnnotations();
        for(Annotation annotation: annotations){
            System.out.println(annotation);
        }

        // 获得注解里面的值
        Table table = (Table) c1.getAnnotation(Table.class);
        String value = table.value();
        System.out.println(value);

        // 获得类中的注解
        Field f = c1.getDeclaredField("age");
        FieldStudent fieldStudent = f.getAnnotation(FieldStudent.class);
        System.out.println(fieldStudent.length());
        System.out.println(fieldStudent.name());
        System.out.println(fieldStudent.type());
    }
}

@Table("student")
class student{

    @FieldStudent(type = "int", name = "age", length = 10)
    private int age;

    @FieldStudent(type = "int", name = "name", length = 10)
    private String name;

    public student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


// 类名注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

// 属性注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldStudent{
    int length();
    String name();
    String type();
}
