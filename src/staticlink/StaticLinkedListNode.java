package staticlink;

public class StaticLinkedListNode<T> {
    public T data;
    private int cursor;

    public StaticLinkedListNode(T data, int cursor) {
        this.cursor = cursor;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

}
