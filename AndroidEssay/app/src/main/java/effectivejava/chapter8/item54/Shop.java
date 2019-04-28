package effectivejava.chapter8.item54;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {

    private final List<Cheese> cheesesInStock = new ArrayList<>();

    /**
     *     没有理由对没有奶酪(Cheese)可供购买的情况进行特殊处理。这样需要在客户端做额外的代码处理可能为null的返回值
     *     在几乎每次使用返回null来代替空集合或数组的方法时，都需要使用这种迂回的方式。
     *     它容易出错，因为编写客户端的程序员可能忘记编写特殊情况代码来处理null返回。 多年来这种错误可能会被忽视，
     *     因为这种方法通常会返回一个或多个对象。 此外，返回null代替空容器会使返回容器的方法的实现变得复杂。
     *     有时有人认为，null返回值比空集合或数组更可取，因为它避免了分配空容器的开销。这个论点有两点是不成立的。
     *     首先，除非测量结果表明所讨论的分配是性能问题的真正原因，否则不宜担心此级别的性能(条目67)。
     *     第二，可以在不分配空集合和数组的情况下返回它们。
     */
    public List<Cheese> getCheeses() {
        return cheesesInStock.isEmpty() ? null
                : new ArrayList<>(cheesesInStock);
    }

    //下面是返回可能为空的集合的典型代码。通常，这就是你所需要的
//    public List<Cheese> getCheeses() {
//        return new ArrayList<>(cheesesInStock);
//    }

    /**
     * 如果有证据表明分配空集合会损害性能，可以通过重复返回相同的不可变空集合来避免分配，
     * 因为不可变对象可以自由共享(条目17)。下面的代码就是这样做的，使用了Collections.emptyList方法。
     * 如果你要返回一个Set，可以使用Collections.emptySet;如果要返回Map，则使用Collections.emptyMap。
     * 但是请记住，这是一个优化，很少需要它。如果你认为你需要它，测量一下前后的性能表现，确保它确实有帮助：
     */
//    public List<Cheese> getCheeses() {
//        return cheesesInStock.isEmpty() ? Collections.emptyList()
//                : new ArrayList<>(cheesesInStock);
//    }


    /*
    * 数组的情况与集合的情况相同。 永远不要返回null，而是返回长度为零的数组。
    * 通常，应该只返回一个正确长度的数组，这个长度可能为零。
    * 请注意，我们将一个长度为零的数组传递给toArray方法，以指示所需的返回类型，即Cheese []：
    * */
//    public Cheese[] getCheeses() {
//        return cheesesInStock.toArray(new Cheese[0]);
//    }

    //如果你认为分配零长度数组会损害性能，则可以重复返回相同的零长度数组，因为所有零长度数组都是不可变的：
//    private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];
//
//    public Cheese[] getCheeses() {
//        return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
//
//    }
}
