package effectivejava.chapter5.item30;
import java.util.*;

// Generic union method and program to exercise it  (Pages 135-6)
public class Union {

//可以编译但有两个警告：
//    public static Set union(Set s1, Set s2) {
//
//        Set result = new HashSet(s1);
//
//        result.addAll(s2);
//
//        return result;
//    }

    // Generic method
    //union方法的一个限制是所有三个集合（输入参数和返回值）的类型必须完全相同
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    // Simple program to exercise generic method
    public static void main(String[] args) {
        Set<String> guys = Set.of("Tom", "Dick", "Harry");
        Set<String> stooges = Set.of("Larry", "Moe", "Curly");
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio);
    }
}
