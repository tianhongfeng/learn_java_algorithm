package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Apple {

    private int price;

    private int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
