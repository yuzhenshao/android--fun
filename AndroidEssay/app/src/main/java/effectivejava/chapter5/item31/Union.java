package effectivejava.chapter5.item31;
import java.util.*;

// Generic union method with wildcard types for enhanced flexibility (Pages 142-3)
public class Union {
    //两个参数s1和s2都是E的生产者，所以PECS助记符告诉我们该声明应该如下
    //返回类型仍然是Set <E>。 不要用通配符类型作为返回类型
    public static <E> Set<E> union(Set<? extends E> s1,
                                   Set<? extends E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }

    // Simple program to exercise flexible generic staticfactory
    public static void main(String[] args) {
        Set<Integer> integers = new HashSet<>();
        integers.add(1); 
        integers.add(3); 
        integers.add(5); 

        Set<Double> doubles =  new HashSet<>();
        doubles.add(2.0); 
        doubles.add(4.0); 
        doubles.add(6.0); 

        Set<Number> numbers = union(integers, doubles);

//      // Explicit type parameter - required prior to Java 8
//      Set<Number> numbers = Union.<Number>union(integers, doubles);

        System.out.println(numbers);
    }
}
