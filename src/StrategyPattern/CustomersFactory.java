package StrategyPattern;

public class CustomersFactory {



    public CalPrice getCustomer(Double totalAmount){

        if (totalAmount > 3000) {//3000则改为金牌会员计算方式
            return  new GoldVip();
        }else if (totalAmount > 2000) {//类似
            return new SuperVip();
        }else if (totalAmount > 1000) {//类似
            return new Vip();
        }else{
           return new Common();
        }
    }
}
