import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ListNode{
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }

public class LinkList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode s = head;
        ListNode f = head.next;
        while(s != null && f != null){
            if(s.val == f.val){
                ListNode tmp = f.next;
                s.next = tmp;
                f = tmp;
            }else{
                if(s.next != null && f != null){
                    if(s.next.val == f.val) f = f.next;
                }
                s = s.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates1(ListNode head) {

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }


    public ListNode deleteDuplicates2(ListNode head) {

        if (head == null || head.next == null) return head;

        if (head.val != head.next.val){
            head.next = deleteDuplicates2(head.next);
        }else {
            ListNode move = head.next;
            while (move != null && head.val == move.val){
                move = move.next;
            }
            return deleteDuplicates2(move);
        }
        return head;
    }

//    public ListNode rotateRight(ListNode head, int k) {
//        if(head == null || head.next == null) return head;
//        ListNode ll = head;
//        int l = 1;
//        while(ll.next != null){
//            l += 1;
//            ll = ll.next;
//        }
//        if(l == k) return head;
//        ll.next = head;
//        int add = l - k % l;
//        while(add-- > 0){
//            head = head.next;
//        }
//        ListNode tmp = head.next;
//        head.next = null;
//        return tmp;
//    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }

    public ListNode reverseList(ListNode head) {
        ListNode top = new ListNode();
        ListNode pre = null;
        ListNode tail = null;
        while(head != null){
            pre = head;
            head = head.next;
            pre.next = tail;
            top.next = pre;

            tail = pre;
        }
        return top.next;
    }

    public boolean hasCycle(ListNode head) {

        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(!set.add(head)) return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if(head == null ) return null;
        ListNode slow = head, fast = head;
        while(true){
            if(fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        while(n > 0){
            fast = fast.next;
            n--;
        }
        ListNode pre = null;
        while(fast != null){
            fast = fast.next;
            if(fast == null) pre = slow;
            slow = slow.next;
        }

        if(pre == null) return head.next;
        ListNode tmp = slow.next;
        pre.next = tmp;
        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                head.next = l1;
                l1 = l1.next;
            }else{
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }

        head.next = l1 == null? l2:l1;
        return dummy.next;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int s = 0;
        int sum = 0;
        while(l1 != null && l2 != null){
            sum = l1.val + l2.val + s;
            if(sum >= 10){
                head.next = new ListNode(sum%10);
                s = 1;
            }else{
                head.next = new ListNode(sum);
                s = 0;
            }
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null){
            sum = l1.val + s;
            if(sum >= 10){
                head.next = new ListNode(sum%10);
                s = 1;
            }else{
                head.next = new ListNode(sum);
                s = 0;
            }
            l1 = l1.next;
            head = head.next;
        }

        while(l2 != null){
            sum = l2.val + s;
            if(sum >= 10){
                head.next = new ListNode(sum%10);
                s = 1;
            }else{
                head.next = new ListNode(sum);
                s = 0;
            }
            l2 = l2.next;
            head = head.next;
        }
        if(s == 1) head.next = new ListNode(1);
        return dummy.next;
    }


    public static void main(String[] args){

        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode cycle = null;
        int[] ll = {9};
        int[] ss = {1,9,9,9,9,9,9,9,9,9};
        ListNode s = l1;
        for(int i = 0; i < ll.length; i++){
            ListNode tmp = new ListNode(ll[i]);
            s.next = tmp;
            s = tmp;
        }

        s = l2;
        for(int i = 0; i < ss.length; i++){
            ListNode tmp = new ListNode(ss[i]);
            s.next = tmp;
            s = tmp;
        }

        LinkList link = new LinkList();
        ListNode reverseList = link.addTwoNumbers(l1.next, l2.next);
        while(reverseList != null){
            System.out.println(reverseList.val);
            reverseList = reverseList.next;
        }
    }
}


