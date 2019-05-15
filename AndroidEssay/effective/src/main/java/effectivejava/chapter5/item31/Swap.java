package effectivejava.chapter5.item31;
import java.util.*;

// Private helper method for wildcard capture (Page 145)
public class Swap {
    public static void swap(List<?> list, int i, int j) {

        /*
        * 看起来我们不能把元素放回到我们刚刚从中方取出的列表中。
        * 问题是列表的类型是List <？>，你不能将除null外的任何值放入List <？>中。
        * 幸运的是，有一种方法可以在不使用不安全的转换或原始类型的情况下实现此方法。
        * 这个想法是写一个私有辅助方法来捕捉通配符类型。 辅助方法必须是泛型方法才能捕获类型
        */
        //list.set(i, list.set(j, list.get(i)));这个简单的实现编译error
        swapHelper(list, i, j);

    }

    /*
    * swapHelper方法知道该列表是一个List <E>。 因此，它知道从这个列表中获得的任何值都是E类型，
    * 并且可以安全地将任何类型的E值放入列表中。 这个稍微复杂的swap的实现可以干净地编译。
     * 它允许我们导出基于通配符的漂亮声明，同时利用内部更复杂的泛型方法。
    * swap方法的客户端不需要面对更复杂的swapHelper声明，但他们从中受益。
    * 辅助方法具有我们认为对公共方法来说过于复杂的签名。*/
    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public static void main(String[] args) {
        // Swap the first and last argument and print the resulting list
        List<String> argList = Arrays.asList(args);
        swap(argList, 0, argList.size() - 1);
        System.out.println(argList);
    }
}
