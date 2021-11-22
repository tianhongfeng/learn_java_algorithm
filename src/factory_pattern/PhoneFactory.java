package factory_pattern;


public class PhoneFactory {



    /**
     * 简单工厂模式
     * @param
     */
    public Phone makePhone(String phoneType) {
        if(phoneType.equalsIgnoreCase("MiPhone")){
            return new MIPhone();
        }
        else if(phoneType.equalsIgnoreCase("iPhone")) {
            return new IPhone();
        }
        return null;
    }

    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        Phone miphone = phoneFactory.makePhone("MiPhone");
        Phone iPhone = phoneFactory.makePhone("iPhone");
    }
}
