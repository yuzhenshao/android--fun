package effectivejava.chapter4.item20;

import java.util.*;

// Concrete implementation built atop skeletal implementation (Page 101)

/*
* 通过提供一个抽象的骨架实现类（abstract skeletal implementation class）来与接口一起使用，
* 将接口和抽象类的优点结合起来。 接口定义了类型，可能提供了一些默认的方法，
* 而骨架实现类负责实现除基本类型接口方法外，剩下的非基本类型接口方法。这就是模板方法设计模式[Gamma95]。
*按照惯例，骨架实现类被称为AbstractInterface，其中Interface是它们实现的接口的名称。
*例如，集合框架（ Collections Framework）提供了一个框架实现以配合每个主要集合接口：
*AbstractCollection，AbstractSet，AbstractList和AbstractMap。 可以说，
*将它们称为SkeletalCollection，SkeletalSet，SkeletalList和SkeletalMap是有道理的，
*但是现在已经确立了抽象约定。 如果设计得当，骨架实现（无论是单独的抽象类还是仅由接口上的默认方法组成）可以使程序员非常容易地提供他们自己的接口实现。
*例如，下面是一个静态工厂方法，在AbstractList的顶层包含一个完整的功能齐全的List实现：
* */
public class IntArrays {
    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        // The diamond operator is only legal here in Java 9 and later
        // If you're using an earlier release, specify <Integer>
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int i) {
                return a[i];  // Autoboxing (Item 6)
            }

            @Override
            public Integer set(int i, Integer val) {
                int oldVal = a[i];
                a[i] = val;     // Auto-unboxing
                return oldVal;  // Autoboxing
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++)
            a[i] = i;

        List<Integer> list = intArrayAsList(a);
        Collections.shuffle(list);
        System.out.println(list);
    }
}
