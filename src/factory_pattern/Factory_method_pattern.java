//package factory_pattern;
//
//// 工厂方法模式
//public class Factory_method_pattern {
//    public static void main(String[] args) {
//        IKeyboardFactory iKeyboardFactory = new HPKeyboardFactory();
//        Keyboard keyboard = iKeyboardFactory.getInstance();
//        keyboard.print();
//    }
//
//}
//
//
//interface IKeyboardFactory {
//    Keyboard getInstance();
//}
//
//
//
//class HPKeyboardFactory implements IKeyboardFactory {
//    @Override
//    public Keyboard getInstance(){
//        return new HPKeyboard();
//    }
//}
//
//class LenovoFactory implements IKeyboardFactory {
//    @Override
//    public Keyboard getInstance(){
//        return new LenovoKeyboard();
//    }
//}
//
//class DellKeyboardFactory implements IKeyboardFactory {
//    @Override
//    public Keyboard getInstance(){
//        return new DellKeyboard();
//    }
//}