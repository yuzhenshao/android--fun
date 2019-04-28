package effectivejava.chapter7.item44;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class function {


   /* Function	对数据内容进行操作，比如修改、删除，比如数据类型转换，有返回值
    Operator	对数据内容进行操作，有返回值，只不过参数类型和返回值类型全部一致，可以看做Function的特例情况
    Consumer	对数据内容进行消费，没有返回值
    Supplier	不需要传入参数，有返回值，可以看做Consumer的反函数，而且Supplier主要用于引用函数
    Predicate	对数据进行检测判断的规则，相当于判断条件的封装*/

    private void fuction() {
        BiFunction<String, String, Integer> biFunction = (a, b) -> (a + b).length();
//        Function<Integer, String> function = a -> a+"hello";
//        biFunction.apply("Hello", "Bob");
//        biFunction.andThen(function);
        System.out.println("BiFunction<String, String, Integer> => " + biFunction.apply("Hello", "Bob"));
    }

    //Operator系列接口使用范例，Operator相当于Function的特例，只是入参类型和返回值类型都一样
    private void Operator() {
        BinaryOperator<String> binaryOperator = (a, b) -> a + b;
        System.out.println("BinaryOperator<String> => " + binaryOperator.apply("Hello", "Bob"));
    }

    private void Consumer() {

        ObjIntConsumer<String> objIntConsumer = (a, b) -> System.out.println(a + b);
        objIntConsumer.accept("Hello Num: ", 11);
    }

    // Supplier系列接口使用范例，一种重要的使用情景是方法引用，具体的方法引用见下节详解：
    private void Supplier() {
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();
        System.out.println(person.name);
    }

    //Predicate系列接口使用范例，可以看做是判断条件的封装，比如判断当前对象是否大于18岁，是否是男性：
    private void Predicate() {
        BiPredicate<Integer, String> biPredicate = (a, b) -> a > 18 && b.equals("Male");
        Person person = new Person();
        System.out.println("BiPredicate<Integer, String> => " + biPredicate.test(person.age, person.gender));

        List<String> wordList=new ArrayList<>();
        List<String> output = wordList.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
    }


    private class Person {
        String name;
        String gender;
        int age;
    }


}
