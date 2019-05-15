package effectivejava.chapter5.item32;

import java.util.Arrays;
import java.util.List;

// It is unsafe to store a value in a generic varargs array parameter (Page 146)
public class Dangerous {
    // Mixing generics and varargs can violate type safety!
//    此方法没有可见的强制转换，但在调用一个或多个参数时抛出ClassCastException异常。
//    它的最后一行有一个由编译器生成的隐形转换。
//    这种转换失败，表明类型安全性已经被破坏，因此将值保存在泛型可变参数数组参数中是不安全的。
    static void dangerous(List<String>... stringLists) {
        //为什么声明一个带有泛型可变参数的方法是合法的，当明确创建一个泛型数组是非法的时候呢？
        //答案是，带有泛型可变参数或者参数话类型的方法在实践中可能非常有用，因此语言设计人员选择忍受这种矛盾
        //事实上，Java类库导出了几个这样的方法，包括Arrays.asList(T... a)，
        //Collections.addAll(Collection<? super T> c, T... elements)，EnumSet.of(E first, E... rest

        //List<String>[] strList = new List<String>[1];
        List<Integer> intList = Arrays.asList(42);
        Object[] objects = stringLists;
        objects[0] = intList; // Heap pollution：当参数化类型的变量指向不是该类型的对象时会发生堆污染
        String s = stringLists[0].get(0); // ClassCastException

    }

    public static void main(String[] args) {
        dangerous(Arrays.asList("There be dragons!"));
    }
}
