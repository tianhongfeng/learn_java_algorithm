package factory_pattern;

public class AppleFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new IPhone();
    }

    @Override
    public PC makePc() {
        return new MAC();
    }
}
