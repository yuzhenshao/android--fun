package effectivejava.chapter8.item52;

import java.math.BigInteger;
import java.util.*;

// Repaired  static classifier method. (Page 240)
public class FixedCollectionClassifier {
    //用一个执行显式instanceof测试的方法替换classify的所有三个重载
    public static String classify(Collection<?> c) {
        return c instanceof Set  ? "Set" :
                c instanceof List ? "List" : "Unknown Collection";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections)
            System.out.println(classify(c));
    }
}
