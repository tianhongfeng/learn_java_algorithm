package test_Generic;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TestGenComparator {

    public static void main(String[] args) {


        /**
         * Set<Cat> set2 = new TreeSet<>(new ComparatorSubThree()); 报错
         * 由于 Comparator<? super E> comparator，这样设计是由于，当我们初始化子类的时候，父类在此之前就已经初始化完了
         * 因此要传入Cat类型或其父类的比较器
         */
        Set<Cat> set = new TreeSet<>(new ComparatorSubTwo());
        Set<Cat> set1 = new TreeSet<>(new ComparatorSubOne());

        set.add(new Cat("Jack", 20));
        set.add(new Cat("Mack", 30));
        set.add(new Cat("Aack", 40));
        set.add(new Cat("Qack", 60));
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }
}


class ComparatorSubOne implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.name.compareTo(o2.name);
    }
}

class ComparatorSubTwo implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.age - o2.age;
    }
}

class ComparatorSubThree implements Comparator<MinCat> {

    @Override
    public int compare(MinCat o1, MinCat o2) {
        return  o1.level - o2.level;
    }
}

