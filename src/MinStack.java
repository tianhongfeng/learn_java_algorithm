import java.util.*;

public class MinStack {
//    /** initialize your data structure here. */
//    List<Integer> ll = new ArrayList<>();
//
//    int ss = Integer.MIN_VALUE;
//    public MinStack() {
//
//    }
//
//    public void push(int val) {
//        ll.add(val);
//    }
//
//    public void pop() {
//        ll.remove(ll.size() - 1);
//    }
//
//    public int top() {
//        return ll.get(ll.size() - 1);
//    }
//
//    public int getMin() {
//        List<Integer> ls = new ArrayList<>();
//        ls.addAll(ll);
//        Collections.sort(ls);
//        return ls.get(0);
//    }
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }
}


