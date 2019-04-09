package effectivejava.chapter5.item31;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

// Using a recursive type bound with wildcards (Page 143)


/* PECS助记符
*接下来让我们把注意力转向条目 30中的max方法。这里是原始声明：
public static <T extends Comparable<T>> T max(List<T> list)
以下是使用通配符类型的修改后的声明：
public static <T extends Comparable<? super T>> T max(List<? extends T> list)
为了从原来到修改后的声明，我们两次应用了PECS。首先直接的应用是参数列表。 它生成T实例，
所以将类型从List <T>更改为List<? extends T>。 棘手的应用是类型参数T。
这是我们第一次看到通配符应用于类型参数。最初，T被指定为继承Comparable <T>，
但Comparable的T消费T实例（并生成指示顺序关系的整数）。 因此，
参数化类型Comparable <T>被替换为限定通配符类型Comparable<? super T>。
Comparable实例总是消费者，所以通常应该使用Comparable<? super T>优于Comparable <T>。 Comparator也是如此。

因此，通常应该使用Comparator<? super T>优于Comparator<T>。

增加的复杂性是否真的起作用了吗？ 同样，它的确如此。
这是一个列表的简单例子，它被原始声明排除，但在被修改后的版本里是允许的：
List<ScheduledFuture<?>> scheduledFutures = ... ;
无法将原始方法声明应用于此列表的原因是ScheduledFuture不实现Comparable <ScheduledFuture>。
相反，它是Delayed的子接口，它继承了Comparable <Delayed>。 换句话说，
一个ScheduledFuture实例不仅仅和其他的ScheduledFuture实例相比较： 它可以与任何Delayed实例比较，
并且足以导致原始的声明拒绝它。
更普遍地说，需要用通配符来支持那些没有直接实现Comparable（或Comparator）而是扩展实现了该接口的类型。
* */
public class RecursiveTypeBound {
    public static <E extends Comparable<? super E>> E max(
        List<? extends E> list) {
        if (list.isEmpty())
            throw new IllegalArgumentException("Empty list");

        E result = null;
        for (E e : list)
            if (result == null || e.compareTo(result) > 0)
                result = e;

        return result;
    }

    public static void main(String[] args) {
        List<String> argList = Arrays.asList(args);
        //List<ScheduledFuture<?>> argList1 = Arrays.asList();
        //System.out.println(effectivejava.chapter5.item30.RecursiveTypeBound.max(argList1));
        System.out.println(max(argList));
    }
}