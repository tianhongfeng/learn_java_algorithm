package staticlink;
import staticlink.StaticLinkedListNode;

public class StaticLinkedList<T> {
    StaticLinkedListNode[] nodes;
    private static final int MAX_SIZE = 100;
    private int length;

    public StaticLinkedList() {
        nodes = new StaticLinkedListNode[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            nodes[i] = new StaticLinkedListNode<T>(null, i + 1);
        }
        nodes[MAX_SIZE - 1].setCursor(0);
        this.length = 0;
    }

    // 链表实际长度
    public int Length() {
        return length;
    }

    // 判断使用数组是否为空
    public boolean isEmpty() {
        return length == 0;
    }

    // 判断备用链表是否为空
    public boolean isFull() {
        return length == MAX_SIZE;
    }

    // 插入一个节点
    public boolean addTo(T data, int index) {
        if (isFull() || index > MAX_SIZE || index < -1 || data == null)
            return false;
        if (index == 0) {
            insert(data);
            return true;
        }
        if (index > Length()) {
            index = Length();
        }
        // 获取第一个使用节点的下标
        int firstUse = nodes[MAX_SIZE - 1].getCursor();
        // 获取备用数组第一个节点的下标
        int firstNull = nodes[0].getCursor();
        for (int i = 1; i < index; i++) {
            firstUse = nodes[firstUse].getCursor();
        }
        // 获取目标节点的后续节点
        int nextUse = nodes[firstUse].getCursor();
        int nextNull = nodes[firstNull].getCursor();
        nodes[0].setCursor(nextNull);
        nodes[firstUse].setCursor(firstNull);
        nodes[firstNull].setCursor(nextUse);
        nodes[firstNull].setData(data);
        length++;
        return true;
    }

    public void insert(T data) {
        int t = nodes[MAX_SIZE - 1].getCursor();
        int firstNull = nodes[0].getCursor();
        nodes[MAX_SIZE - 1].setCursor(firstNull);
        nodes[0].setCursor(nodes[firstNull].getCursor());
        nodes[firstNull].setCursor(t);
        nodes[firstNull].setData(data);
        length++;
    }

    public void print() {
        int first = nodes[MAX_SIZE - 1].getCursor();
        System.out.println("链表：");
        for (int i = first; i != 0; ) {
            System.out.print(nodes[i].getData() + " ");
            i = nodes[i].getCursor();
        }
    }

    // 删除指定节点
    public boolean delete(T data) {
        if (isEmpty()) {
            return false;
        }
        int temp = MAX_SIZE - 1;
        while (temp != 0) {
            if (nodes[nodes[temp].getCursor()].getData() == data) {
                int p = nodes[temp].getCursor();
                nodes[temp].setCursor(nodes[p].getCursor());
                nodes[p].setCursor(nodes[0].getCursor());
                nodes[0].setCursor(p);
                nodes[p].setData(null);
                length--;
                return true;
            }
            temp = nodes[temp].getCursor();
        }
        return false;
    }

    // 删除所有节点
    public boolean deleteAll() {
        if (isEmpty()) {
            return true;
        }
        for (int i = 0; i < MAX_SIZE - 1; i++) {
            nodes[i].setCursor(i + 1);
            nodes[i].setData(null);
        }
        nodes[MAX_SIZE - 1].setCursor(0);
        nodes[MAX_SIZE - 1].setData(null);
        length = 0;
        return true;
    }

    public void printAll() {
        System.out.println("链表：");
        for (int i = 0; i < MAX_SIZE; i++) {
            System.out.print("[" + i + " " + nodes[i].getData() + " " + nodes[i].getCursor() + "]");
            if (i % 5 == 0 && i != 0) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        StaticLinkedList<String> list = new StaticLinkedList<String>();
        list.insert("A");
        list.insert("B");
        list.insert("C");
        list.insert("D");
        list.insert("E");
        list.addTo("X", 2);
        System.out.println(list.Length());
        list.print();
//        list.printAll();
//        list.deleteAll();
    }
}
