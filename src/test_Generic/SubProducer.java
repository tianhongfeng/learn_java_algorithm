package test_Generic;

/**
 * todo 泛型类派生子类时，如果子类也是泛型类，那么子类的泛型标识要和父类一致
 * @param <T>
 */
public class SubProducer<T> extends Producer<T> {
    @Override
    public T getProduct() {
        return super.getProduct();
    }

    @Override
    public void setProduct(T product) {
        super.setProduct(product);
    }

    public static void main(String[] args) {
        SubProducer<String> subProducer = new SubProducer<>();
        subProducer.setProduct("123");
        System.out.println(subProducer.getProduct());
    }
}

/**
 * 泛型类派生子类时，如果子类不是泛型类，那么父类要明确泛型的数据类型
 */
class SubBubbleProducer extends Producer<String> {
    @Override
    public String getProduct() {
        return super.getProduct();
    }

    @Override
    public void setProduct(String product) {
        super.setProduct(product);
    }

    public static void main(String[] args) {
        SubBubbleProducer subProducer = new SubBubbleProducer();
        subProducer.setProduct("456");
        System.out.println(subProducer.getProduct());
    }
}
