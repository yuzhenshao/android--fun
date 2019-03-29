package effectivejava.chapter11.item79;
import java.util.*;

// More complex test of ObservableSet - Page 318-9
public class Test2 {

    /*
    * 实际上确实打印出0-23的数字然后抛出异常ConcurrentModificationException。
    * 问题在于，当notifyElementAdded调用观察者的added方法时，他正处于遍历objservers列表的过程中。
    * added方法调用可观察集合的removeObserver方法，从而调用observers.remove方法。现在我们有麻烦了。
    * 我们正企图在遍历列表的过程中，将一个元素从列表中删除，这是非法的，
    * notifyElementAdded方法中的迭代式在一个同步块中，可以防止并发修改，
    * 但是无法防止迭代线程本身回调到可观察的集合中，也无法防止修改它的observers列表。
    * */
    public static void main(String[] args) {
        ObservableSet<Integer> set =
                new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<Integer>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23)
                    s.removeObserver(this);
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
    }
}
