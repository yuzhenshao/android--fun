package effectivejava.chapter5.item32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Safe method with a generic varargs parameter (page 149)
public class FlattenWithVarargs {
    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists)
            result.addAll(list);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> flatList = flatten(
                Arrays.asList(1, 2), Arrays.asList(3, 4, 5), Arrays.asList(6,7));
        System.out.println(flatList);
    }
}
