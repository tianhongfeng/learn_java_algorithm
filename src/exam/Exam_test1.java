package exam;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Exam_test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < t; i++){
            char[] ss = sc.nextLine().toCharArray();
            function1(ss);
        }

    }

    public static void function1(char[] ss){
        Deque<Character> stack = new LinkedList<>();
        int res = 0;
        for(Character s: ss){
            if(s == '[' || s== '{'){
                stack.push(s);
            }else{
                char tmp = stack.pop();
                if(s == ']' && tmp != '[') res++;
                if(s == '}' && tmp != '{') res++;
            }
        }
        System.out.println(res);
    }
}
