package thread;


import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class testFunctionalInterface {

    public static void main(String[] args) {

        // 函数型接口
        Function<String, String> function = (str) -> {
            return str;
        };

//        Function function = new Function<String, String>() {
//            @Override
//            public String apply(String o) {
//                return o;
//            }
//        };


        // 断定型接口
        Predicate<String> predicate = (str) -> {
            return str.isEmpty();
        };

//        Predicate<String> predicate1 = new Predicate<String>() {
//            @Override
//            public boolean test(String o) {
//                return o.isEmpty();
//            }
//        };



        // 消费型接口，只接受，不返回
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
        Consumer<String> consumer = (str) -> {
            System.out.println(str);
        };

        // 供给型接口，只返回不接受
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "1024";
//            }
//        };

        Supplier<String> supplier = () -> {
            return "1024";
        };



        System.out.println(function.apply("123"));
        System.out.println(predicate.test("123"));
        consumer.accept("123");
        System.out.println(supplier.get());

    }
}
