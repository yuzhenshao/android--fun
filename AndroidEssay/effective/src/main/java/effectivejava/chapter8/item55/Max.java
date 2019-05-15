package effectivejava.chapter8.item55;

import java.util.*;

// Using Optional<T> as a return type (Pages 249-251)
public class Max {
//    // Returns maximum value in collection - throws exception if empty (Page 249)
//    //如果给定集合为空，此方法将抛出IllegalArgumentException异常。
//    //我们在条目30中提到，更好的替代方法是返回Optional<E>。
    public static <E extends Comparable<E>> E max0(Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("Empty collection");

        E result = null;
        for (E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        return result;
    }

//    // Returns maximum value in collection as an Optional<E> (Page 250)
//    // 如你所见，返回Optional很简单。 你所要做的就是使用适当的静态工厂创建Optional。
//    // 在这个程序中，我们使用两个：Optional.empty()返回一个空的Optional，
//    // Optional.of(value)返回一个包含给定非null值的Optional。 将null传递给Optional.of(value)是一个编程错误。
//    // 如果这样做，该方法通过抛出NullPointerException异常作为回应。
//    // 永远不要通过返回Optional的方法返回一个空值：它破坏Optional设计的初衷。
    public static <E extends Comparable<E>>
    Optional<E> max1(Collection<E> c) {
        if (c.isEmpty())
            return Optional.empty();

        E result = null;
        for (E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        return Optional.of(result);
    }

    // Returns max val in collection as Optional<E> - uses stream (Page 250)
    // Stream上的很多终止操作返回Optional。如果我们重写max方法来使用一个Stream，
    // 那么Stream的max操作会为我们生成Optional的工作:
    public static <E extends Comparable<E>>
    Optional<E> max(Collection<E> c) {
        return c.stream().max(Comparator.naturalOrder());
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList(args);

        System.out.println(max(words));

        // Using an optional to provide a chosen default value (Page 251)
        String lastWordInLexicon = max(words).orElse("No words...");
        System.out.println(lastWordInLexicon);
    }
}
