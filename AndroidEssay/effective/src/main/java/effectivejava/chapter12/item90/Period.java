package effectivejava.chapter12.item90;

// Period class with serialization proxy - Pages 363-364

import java.util.*;
import java.io.*;

// Immutable class that uses defensive copying
public final class Period implements Serializable {
    private final Date start;
    private final Date end;

    /**
     * @param  start the beginning of the period
     * @param  end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end   = new Date(end.getTime());
        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(
                    start + " after " + end);
    }

    public Date start () { return new Date(start.getTime()); }

    public Date end () { return new Date(end.getTime()); }

    public String toString() { return start + " - " + end; }


    // Serialization proxy for Period class
    private static class SerializationProxy implements Serializable {
        private final Date start;
        private final Date end;

        SerializationProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }

        private static final long serialVersionUID =
                234098243823485285L; // Any number will do (Item 87)
    }

    //序列化系统永远不会生成外围类的序列化实例，
    // writeReplace method for the serialization proxy pattern
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    //攻击者可能会构造一个实例，试图违反类的不变性。
    // 要确保此类攻击失败，只需把readObject方法添加到外围类中：
    // readObject method for the serialization proxy pattern
    private void readObject(ObjectInputStream stream)
            throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    // 最后，在SerializationProxy类上提供一个readResolve方法，该方法返回外围类逻辑等效的实例。
    // 此方法的存在导致序列化系统在反序列化时把序列化代理转换回外围类的实例。
    // 这个readResolve方法只使用其公共API创建了一个外围类的实例，这就是该模式的美妙之处。
    // 它在很大程度上消除了序列化的语言外特性，因为反序列化实例是使用与任何其他实例相同的构造方法、静态工厂和方法创建的。
    // 这使你不必单独确保反序列化的实例遵从类的不变量。如果类的静态工厂或构造方法确立了这些不变性，
    // 而它的实例方法维护它们，那么就确保了这些不变性也将通过序列化来维护。

    //以下是Period.SerializationProxy的readResolve方法：

    // readResolve method for Period.SerializationProxy
    private Object readResolve() {
        return new Period(start, end);    // Uses public constructor
    }
}