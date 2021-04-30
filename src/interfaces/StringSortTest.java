package interfaces;

import java.util.Arrays;
import java.util.Date;

public class StringSortTest {

    public static void main(String[] args) {

        String[] friends = {"Peter", "Paul ", "Mary"};
        Arrays.sort(friends, new LengthComparator());
        for (String s : friends){
            System.out.println(s);
        }
    }
}
