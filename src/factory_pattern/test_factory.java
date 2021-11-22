package factory_pattern;


public class test_factory {

    public static void main(String[] args) {
//        AbstractFactory miFactory = new XiaoMiFactory();
//        AbstractFactory appleFactory = new AppleFactory();
//        miFactory.makePhone();
//        appleFactory.makePhone();
//
//        miFactory.makePc();
//        appleFactory.makePc();
        Application application = new CreateProductA();
        Product product =  application.getObject();
        product.method();


    }
}


abstract class Application{

    abstract Product creation();

    Product getObject(){
        Product product = creation();
        return product;
    }
}

interface Product{
    public void method();
}

class ProductA implements Product{

    @Override
    public void method() {
        System.out.println("生产商品A");
    }
}

class ProductB implements Product{

    @Override
    public void method() {
        System.out.println("生产商品B");
    }
}


class CreateProductA extends Application {

    @Override
    Product creation() {
        return new ProductA();
    }
}

class CreateProductB extends Application {

    @Override
    Product creation() {
        return new ProductB();
    }
}


