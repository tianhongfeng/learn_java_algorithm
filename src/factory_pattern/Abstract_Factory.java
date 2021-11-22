package factory_pattern;

// 抽象工厂
public class Abstract_Factory {
    public static void main(String[] args) {
        IFactory iFactory = new DellFactory();
        Keyboard keyboard = iFactory.createKeyboard();
        Monitor monitor = iFactory.createMainFrame();
        keyboard.print();
        monitor.play();
    }

}


interface IFactory{
    Monitor createMainFrame();
    Keyboard createKeyboard();
}


class DellFactory implements IFactory{

    @Override
    public Monitor createMainFrame() {
        return new DellMonitor();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}

class HPFactory implements IFactory{

    @Override
    public Monitor createMainFrame() {
        return new HPMonitor();
    }

    @Override
    public Keyboard createKeyboard() {
        return new HPKeyboard();
    }
}


interface Keyboard {
    void print();
}
class DellKeyboard implements Keyboard {
    @Override
    public void print() {
        //...dell...dell;
    }
}
class HPKeyboard implements Keyboard {
    @Override
    public void print() {
        //...HP...HP;
    }
}
interface Monitor {
    void play();
}
class DellMonitor implements Monitor {
    @Override
    public void play() {
        //...dell...dell;
    }
}
class HPMonitor implements Monitor {
    @Override
    public void play() {
        //...HP...HP;
    }
}




