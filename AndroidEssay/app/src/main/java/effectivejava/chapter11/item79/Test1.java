package effectivejava.chapter11.item79;
import java.util.*;

public class Test1 {
    //以下程序打印0到99之间的数字：
    //现在让我们尝试一些更好玩的东西。看下Test2
    public static void main(String[] args) {
        ObservableSet<Integer> set =
                new ObservableSet<>(new HashSet<>());

        set.addObserver((s, e) -> System.out.println(e));

        for (int i = 0; i < 100; i++)
            set.add(i);
    }
}
