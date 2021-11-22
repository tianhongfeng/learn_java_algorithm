//package factory_pattern;
//
//
//import com.sun.xml.internal.ws.util.StringUtils;
//
//import java.util.Objects;
//
//// 简单工厂
//public class Simple_factory {
//    Keyboard keyboard;
//    public Keyboard getObject(String ss){
//        if(Objects.equals("HP", ss)){
//            keyboard = new HPKeyboard();
//        }else if(Objects.equals("Dell", ss)){
//            keyboard = new DellKeyboard();
//        }else if(Objects.equals("LENOVO", ss)){
//            keyboard = new LenovoKeyboard();
//        }
//        return keyboard;
//    }
//
//
//    public static void main(String[] args) {
//        Simple_factory keyboardFactory = new Simple_factory();
//        Keyboard keyboard = keyboardFactory.getObject("Dell");
//        keyboard.print();
//        //...
//    }
//}
//
//
//
//interface Keyboard {
//    void print();
//}
//
//class HPKeyboard implements Keyboard {
//
//    @Override
//    public void print() {
//        //...输出逻辑;
//    }
//}
//
//class DellKeyboard implements Keyboard {
//
//    @Override
//    public void print() {
//        //...输出逻辑;
//    }
//}
//
//class LenovoKeyboard implements Keyboard {
//
//    @Override
//    public void print() {
//        //...输出逻辑;
//    }
//}