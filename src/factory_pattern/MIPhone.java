package factory_pattern;

public class MIPhone implements Phone{
    public MIPhone() {
        this.make();
    }
    @Override
    public void make() {
        // TODO Auto-generated method stub
        System.out.println("make xiaomi phone!");
    }
}
