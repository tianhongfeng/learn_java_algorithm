package StrategyPattern;

//客户类
public class Customer {

    private Double totalAmount = 0D;//客户在本商店消费的总额
    private Double amount = 0D;//客户单次消费金额
//    private CalPrice calPrice = new Common();//每个客户都有一个计算价格的策略，初始都是普通计算，即原价
    private CalPrice calPrice;

    //客户购买商品，就会增加它的总额
    public void buy(Double amount){
        this.amount = amount;
        totalAmount += amount;
        calPrice = new CustomersFactory().getCustomer(totalAmount);
    }
    //计算客户最终要付的钱
    public Double calLastAmount(){
        return calPrice.calPrice(amount);
    }
}
