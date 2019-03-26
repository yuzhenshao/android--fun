package effectivejava.chapter4.item18;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Wrapper class - uses composition in place of inheritance  (Page 90)

/*
 *
 * InstrumentedSet类被称为包装类，因为每个InstrumentedSet实例都包含（“包装”）另一个Set实例。
 * 这也被称为装饰器模式，因为InstrumentedSet类通过添加计数功能来“装饰”一个集合。
 * 有时组合和转发的结合被错误地称为委托（delegation）。 从技术上讲，除非包装对象把自身传递给被包装对象，否则不是委托。
 *
 * InstrumentedSet类的设计是通过存在的Set接口来实现的，该接口包含HashSet类的功能特性。
 * 除了健壮性外，这个设计是非常灵活的。InstrumentedSet类实现了Set接口，并有一个构造方法，其参数也是Set类型的。
 * 本质上，这个类把Set转换为另一个类型Set， 同时添加了计数的功能。
 * */

public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        //与基于继承的方法不同，这里的包装类可以被用来包装任何Set实现，并且可以与任何先前存在的构造方法结合使用：HashSet,TreeSet
        InstrumentedSet<String> s = new InstrumentedSet(new HashSet<>());
        s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(s.getAddCount());
    }

    /*
     * 缺点：
     * 包装类不适合用在回调框架（callback frameworks）中使用，其中对象将自身引用传递给其他对象以用于后续调用（“回调”）。
     * 因为一个被包装的对象不知道它外面的包装对象，所以它传递一个指向自身的引用（this），回调时并不记得外面的包装对象。
     * 这被称为SELF问题[Lieberman86]。 有些人担心转发方法调用的性能影响，以及包装对象对内存占用。
     * 两者在实践中都没有太大的影响。
     */

    /*
     * 只有在子类真的是父类的子类型的情况下，继承才是合适的。
     * 换句话说，只有在两个类之间存在“is-a”关系的情况下，B类才能继承A类。
     * 如果你试图让B类继承A类时，问自己这个问题：每个B都是A吗？ 如果你不能如实回答这个问题，那么B就不应该继承A。
     * 如果答案是否定的，那么B通常包含一个A的私有实例，并且暴露一个较小的、较简单的API：A不是B的一部分 ，只是其实现细节。
     * */
}
