package factory_pattern;

public class XiaoMiFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new MIPhone();
    }

    @Override
    public PC makePc() {
        return new MiPC();
    }
}
