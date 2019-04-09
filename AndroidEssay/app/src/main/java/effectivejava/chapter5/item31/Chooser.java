package effectivejava.chapter5.item31;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

// Wildcard type for parameter that serves as an T producer (page 141)
public class Chooser<T> {
    private final List<T> choiceList;
    private final Random rnd = new Random();

//    public Chooser(Collection<T> choices)
//    这个构造方法只使用集合选择来生产类型T的值（并将它们存储起来以备后用），
//    所以它的声明应该使用一个extends T的通配符类型。下面是得到的构造方法声明：
//    这种改变有区别吗？ 假你有一个List <Integer>，想通过Chooser<Number>简化，它不能通过初始声明进行编译。
//    ，但是一旦添加有限制通配符类型，就可以进行编译了。
    public Chooser(Collection<? extends T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
        Chooser<Number> chooser = new Chooser<>(intList);
        for (int i = 0; i < 10; i++) {
            Number choice = chooser.choose();
            System.out.println(choice);
        }
    }
}
