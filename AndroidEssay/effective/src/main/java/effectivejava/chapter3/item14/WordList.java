package effectivejava.chapter3.item14;
import java.math.BigDecimal;
import java.util.*;

// The benefits of implementing Comparable (Page 66)
public class WordList {
    //依赖于String实现了Comparable接口，去除重复参数，并按照字母顺序打印
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, args);
        System.out.println(s);

        new BigDecimal("0");
        new BigDecimal(0.0);
    }
}
