import java.util.ArrayList;
import java.util.Stack;

public class test1 {

    public int calculate(String s) {
        Stack<Integer> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();
        int sum = 0;
        char[] cc = s.toCharArray();
        for(char i : cc){
            if(i == '('){

            }
        }
        return sum;

    }
    public static void main(String[] args){
        String ss = "(1+(4+5+2)-3)+(6+8)";
        test1 l1 = new test1();
        System.out.println(l1.calculate(ss));
    }

}
