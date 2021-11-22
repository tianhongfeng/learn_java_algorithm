package factory_pattern;

public class IPhone implements Phone{
    public IPhone() {
        this.make();
    }
    @Override
    public void make() {
        // TODO Auto-generated method stub
        System.out.println("make iphone!");
    }
}
