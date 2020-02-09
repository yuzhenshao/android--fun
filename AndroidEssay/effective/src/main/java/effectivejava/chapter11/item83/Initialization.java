package effectivejava.chapter11.item83;

// Initialization styles - Pages 333-
public class Initialization {

    //在大多数情况下，正常初始化优于延迟初始化，通常初始化的实例属性的典型声明
    private final FieldType field1 = computeFieldValue();

    //如果使用延迟初始化来破坏初始化循环，请使用同步访问器，因为它是最简单，最清晰的替代方法：
    private FieldType field2;
    private synchronized FieldType getField2() {
        if (field2 == null)
            field2 = computeFieldValue();
        return field2;
    }

    //如果需要在静态属性上使用延迟初始化来提高性能，请使用延迟初始化持有者类的用法 use
    //the lazy initialization holder class idiom）这保障一个类直到被使用才会初始化
    private static class FieldHolder {
        static final FieldType field = computeFieldValue();
    }

    //当第一次调用getField方法时，它首次读取FieldHolder.field，导致FieldHolder类的初始化。
    // 这个习惯用法的优点在于getField方法不是同步的，只执行属性访问，因此延迟初始化几乎不会增加访问成本。
    private static FieldType getField() {
        return FieldHolder.field;
    }


    //如果需要使用延迟初始化来提高实例属性的性能，请使用双重检查（double-check ）习惯用法
    //这个习惯用法避免了初始化后访问属性时的锁定成本(条目 79)
    private volatile FieldType field4;

    private FieldType getField4() {
        FieldType result = field4;
        if (result != null)    // First check (no locking)
            return result;

        synchronized(this) {
            if (field4 == null) // Second check (with locking)
                field4 = computeFieldValue();
            return field4;
        }
    }


    //“单一检查”习惯用法
    // Single-check idiom - can cause repeated initialization! - Page 334
    private volatile FieldType field5;

    private FieldType getField5() {
        FieldType result = field5;
        if (result == null)
            field5 = result = computeFieldValue();
        return result;
    }

    private static FieldType computeFieldValue() {
        return new FieldType();
    }
}
