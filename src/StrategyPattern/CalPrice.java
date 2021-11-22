package StrategyPattern;

public interface CalPrice {
    //根据原价返回一个最终的价格
    Double calPrice(Double originalPrice);

}

class Common implements CalPrice{

    public Double calPrice(Double originalPrice) {
        return originalPrice;
    }

}
class Vip implements CalPrice{

    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.8;
    }

}
class SuperVip implements CalPrice{

    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.7;
    }

}
class GoldVip implements CalPrice{

    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.5;
    }

}