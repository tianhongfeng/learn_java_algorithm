package StrategyPattern;

/**
 * 抽象出来一个会员种类工厂类
 */
public interface AbstractCustomerFactory {
    CalPrice getCustomer();
}

class GoldVipCustomer implements AbstractCustomerFactory{

    @Override
    public CalPrice getCustomer() {
        return new GoldVip() ;
    }
}

class SuperVipCustomer implements AbstractCustomerFactory{

    @Override
    public CalPrice getCustomer() {
        return new SuperVip() ;
    }
}

class VipCustomer implements AbstractCustomerFactory{

    @Override
    public CalPrice getCustomer() {
        return new Vip() ;
    }
}

class CommonCustomer implements AbstractCustomerFactory{

    @Override
    public CalPrice getCustomer() {
        return new Common() ;
    }
}
