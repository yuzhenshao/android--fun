package effectivejava.chapter5.item32;

import java.util.ArrayList;
import java.util.List;

// List as a typesafe alternative to a generic varargs parameter (page 149)
public class FlattenWithList {
    static <T> List<T> flatten(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists)
            result.addAll(list);
        return result;
    }

//    这种方法的优点是编译器可以证明这种方法是类型安全的。
//    不必使用SafeVarargs注解来证明其安全性，也不用担心在确定安全性时可能会犯错。
//    主要缺点是客户端代码有点冗长，运行可能会慢一些。

    public static void main(String[] args) {
        List<Integer> flatList = flatten(List.of(
                List.of(1, 2), List.of(3, 4, 5), List.of(6,7)));
        System.out.println(flatList);
    }
}
