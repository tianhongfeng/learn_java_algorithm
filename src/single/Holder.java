package single;


// 静态内部类模式下的单例
public class Holder {

    // 单例模式 构造器必须私有
    private Holder(){
    }

    public Holder getInstance(){
        return innerclass.HOLDER;
    }

    public static class innerclass{
        private static final Holder HOLDER = new Holder();
    }
}
