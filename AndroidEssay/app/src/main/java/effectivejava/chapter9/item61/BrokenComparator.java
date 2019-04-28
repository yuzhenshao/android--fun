package effectivejava.chapter9.item61;
import java.util.*;

// Broken comparator - can you spot the flaw? - Page 273
public class BrokenComparator {
    public static void main(String[] args) {

        Comparator<Integer> naturalOrder =
                (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);

       // 可以通过添加两个局部变量来存储与装箱Integer参数对应的原始int值，并对这些变量执行所有的比较，
        // 从而修复了损坏的比较器中的问题避免了错误的引用一致性比较
//        Comparator<Integer> naturalOrder = (iBoxed, jBoxed) -> {
//            int i = iBoxed, j = jBoxed; // Auto-unboxing
//            return i < j ? -1 : (i == j ? 0 : 1);
//        };

        int result = naturalOrder.compare(new Integer(42), new Integer(42));
        System.out.println(result);
    }


















    /*
    * 计算表达式i < j会使i和j引用的整数实例自动拆箱；也就是说，它提取它们的基本类型值。
    * 计算的目的是检查得到的第一个int值是否小于第二个int值。但假设是否定的。
    * 然后，下一个测试计算表达式i==j，该表达式对两个对象执行引用标识比较。
    * 如果i和j引用表示相同整型值的不同Integer实例，这个比较将返回false，比较器将错误地返回1，
    * 表明第一个整型值大于第二个整型值。将==操作符应用于装箱的基本类型几乎总是错误的。
    * */
}
