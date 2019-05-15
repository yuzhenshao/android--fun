package effectivejava.chapter4.item20;
import java.util.*;

// Skeletal implementation class (Pages 102-3)
/*考虑一下Map.Entry接口。 显而易见的基本方法是getKey，getValue和（可选的）setValue。
接口指定了equals和hashCode的行为，并且有一个toString的明显的实现。
 由于不允许为Object类方法提供默认实现，因此所有实现均放置在骨架实现类中：*/
public abstract class AbstractMapEntry<K,V>
        implements Map.Entry<K,V> {
    // Entries in a modifiable map must override this method
    @Override public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    // Implements the general contract of Map.Entry.equals
    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?,?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(),   getKey())
                && Objects.equals(e.getValue(), getValue());
    }

    // Implements the general contract of Map.Entry.hashCode
    @Override public int hashCode() {
        return Objects.hashCode(getKey())
                ^ Objects.hashCode(getValue());
    }

    @Override public String toString() {
        return getKey() + "=" + getValue();
    }

    /*
    * 由于骨架实现类是为了继承而设计的，所以你应该遵循条目 19中的所有设计和文档说明。
    * 为了简洁起见，前面的例子中省略了文档注释，但是好的文档在骨架实现中是绝对必要的，无论它是否包含 一个接口或一个单独的抽象类的默认方法*/



//
//    与骨架实现有稍许不同的是简单实现，以AbstractMap.SimpleEntry为例。
//    一个简单的实现就像一个骨架实现，它实现了一个接口，并且是为了继承而设计的，
//    但是它的不同之处在于它不是抽象的：它是最简单的工作实现。 你可以按照情况使用它，也可以根据情况进行子类化。
//
//    总而言之，一个接口通常是定义允许多个实现的类型的最佳方式。 如果你导出一个重要的接口，应该强烈考虑提供一个骨架的实现类。
//    在可能的情况下，应该通过接口上的默认方法提供骨架实现，以便接口的所有实现者都可以使用它。 也就是说，对接口的限制通常要求骨架实现类采用抽象类的形式。
}
