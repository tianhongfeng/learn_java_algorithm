import java.util.*;

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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode i = headA;
        ListNode j = headB;
        while(i != null){
            while(j != null){
                if(i == j) return i;
                j = j.next;
            }
            i = i.next;
            j = headB;
        }
        return null;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(right == left) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        int p = 1;
        ListNode newHead = null;
        while(head != null){
            if(p == left) break;
            pre = pre.next;
            p++;
        }
        p++;
        newHead = pre.next;
        ListNode tail = null;
        ListNode tmp = null;
        while(p <= right){
            tail = newHead.next;
            tmp = pre.next;
            pre.next = newHead;
            newHead.next = tmp;
            tmp.next = tail;
            p++;
            newHead = tail;
        }
        return dummy.next;
    }

    public int[] reversePrint(ListNode head) {
        if(head == null) return new int[0];
        List<Integer> ll = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while(head != null){
            ll.add(head.val);
            stack.push(head.val);
            head = head.next;
        }
        int[] ss = new int[stack.size()];
        for(int i = 0; i < ss.length; i++){
            ss[i] = stack.pop();
        }
        return ss;
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 == null? l2:l1;
        return dummy.next;
    }

    public void reorderList(ListNode head) {
        if(head == null && head.next == null) return;
        //1.先找到中间节点
        ListNode l1 = head;
        ListNode mid = findmiddle(head);
        ListNode l2 = mid.next;
        mid.next = null;
        //2.在把后半部分 反转
        l2 = reverseList2(l2);
        //3.然后两半部分 进行按规则插入
        while(l2 != null){
            ListNode tmp = l2.next;
            l2.next = l1.next;
            l1.next = l2;
            l1 = l2.next;
            l2 = tmp;
        }
        return;
    }

    public ListNode findmiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList2(ListNode head){
        ListNode pre = null;
        while(head != null){
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    public ListNode copyList(ListNode head){
        if(head == null) return null;
        ListNode newHead = new ListNode(head.val);
        ListNode p = newHead;
        while(head.next !=null){
            p.next = new ListNode(head.next.val);
            p = p.next;
            head = head.next;
        }
        return newHead;
    }

//    public ListNode copyRandomList(ListNode head) {
//        if(head == null) return null;
//        Map<ListNode, ListNode> map = new HashMap<>();
//
//        ListNode tmp = head;
//        while(tmp != null){
//            map.put(tmp, new ListNode(tmp.val));
//            tmp = tmp.next;
//        }
//
//        ListNode newHead = null;
//        tmp = head;
//        while(tmp != null){
//            if(newHead != null){
//                newHead = map.get(head);
//                newHead.next = map.get(head.next);
//                newHead.random = map.get(head.random);
//            }else{
//                ListNode node = map.get(head);
//                node.next = map.get(head.next);
//                node.random = map.get(head.random);
//            }
//        }
//        return newHead;
//    }

    public boolean isPalindrome(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        // 找到中间节点
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转 后半部分
        ListNode l2 = slow.next;
        ListNode pre = null;
        while(l2 != null){
            ListNode tmp = l2.next;
            l2.next = pre;
            pre = l2;
            l2 = tmp;
        }
        l2 = pre;

        while(l2 != null){
            if(head.val != l2.val) return false;
            head = head.next;
            l2 = l2.next;
        }
        return true;
    }

    public void reorderList3(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode h1 = head;
        ListNode h2 = slow.next;

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while(h2 != null){
            ListNode tmp = h2.next;
            cur = h2;
            cur.next = dummy.next;
            dummy.next = cur;
            h2 = tmp;
        }


        h2 = dummy.next;

        while(h1 != null && h2 != null){
            ListNode p = h1.next;
            ListNode q = h2.next;
            h1.next = h2;
            h1.next.next = p;
            h1 = p;
            h2 = q;
        }
        h1.next = null;

        System.out.println();

    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        Set<ListNode> set = new HashSet<>();
        while(pHead != null){
            if(set.contains(pHead)){
                return pHead;
            }else{
                set.add(pHead);
            }
            pHead = pHead.next;
        }
        return null;
    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode p = list1;
        ListNode q = list2;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        if(p.val <= q.val){
            cur.next = p;
        }else{
            cur.next = q;
        }
        cur = cur.next;
        while(p != null && q != null){
            if(p.val <= q.val){
                ListNode tmp = p.next;
                cur.next = q;
                cur = cur.next;
                p = tmp;
            }else{

                ListNode tmp = q.next;
                cur.next = p;
                cur = cur.next;
                q = tmp;

            }
        }
        return dummy.next;

    }



    public static void main(String[] args){

        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);

        int[] ll = {1,2,2,4,5};
        ListNode s = l1;

        int[] ll1 = {1,2,2,4,5};
        ListNode s1 = l2;

        for(int i = 0; i < ll1.length; i++){
            ListNode tmp = new ListNode(ll1[i]);
            s1.next = tmp;
            s1 = tmp;
        }

        for(int i = 0; i < ll.length; i++){
            ListNode tmp = new ListNode(ll[i]);
            s.next = tmp;
            s = tmp;
        }
        LinkList link = new LinkList();
//        System.out.println(link.Merge(l1.next, l1.next));

        ListNode reverseList = link.Merge(l1.next, l2.next);
        while(reverseList != null){
            System.out.println(reverseList.val);
            reverseList = reverseList.next;
        }

        for(int i = 0; i < ll1.length; i++){
            ListNode tmp = new ListNode(ll1[i]);
            s1.next = tmp;
            s1 = tmp;
        }


    }
}

//class MyStack {
//
//    Queue<Integer> q1;
//    Queue<Integer> q2;
//    /** Initialize your data structure here. */
//    public MyStack() {
//        q1 = new LinkedList<>();
//        q2 = new LinkedList<>();
//
//    }
//
//    /** Push element x onto stack. */
//    public void push(int x) {
//        q1.add(x);
//    }
//
//    /** Removes the element on top of the stack and returns that element. */
//    public int pop() {
//        int result = -1;
//        if(q1.isEmpty()) return result;
//        while(!q1.isEmpty()){
//            result = q1.poll();
//            if(q1.isEmpty()){
//               break;
//            }else{
//                q2.add(result);
//            }
//        }
//        while(!q2.isEmpty()){
//            q1.add(q2.poll());
//        }
//        return result;
//    }
//
//    /** Get the top element. */
//    public int top() {
//        int result = -1;
//        if(q1.isEmpty()) return result;
//        while(!q1.isEmpty()){
//            result = q1.poll();
//            q2.add(result);
//        }
//
//        while(!q2.isEmpty()){
//            q1.add(q2.poll());
//        }
//        return result;
//    }
//
//    /** Returns whether the stack is empty. */
//    public boolean empty() {
//        return q1.isEmpty();
//    }
//}


