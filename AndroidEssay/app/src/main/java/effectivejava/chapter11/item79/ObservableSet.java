package effectivejava.chapter11.item79;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Broken - invokes alien method from synchronized block!
public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set<E> set) { super(set); }

    private final List<SetObserver<E>> observers
            = new ArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        synchronized(observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized(observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized(observers) {
            for (SetObserver<E> observer : observers)
                observer.added(this, element);
        }
    }

    /*
    * 幸运的是，通过将外来方法的调用移出同步代码块来解决这个问题通常并不太难，
    * 对于notofyElementAdded方法，这还设计给observers列表拍张“快照“,
    * 然后没有锁也可以安全的遍历这个列表了，进过这一修改，前面两个例子运行起来便在也不会出现异常或者死锁了：
    * */
//    // Alien method moved outside of synchronized block - open calls
//    private void notifyElementAdded(E element) {
//        List<SetObserver<E>> snapshot = null;
//        synchronized(observers) {
//            snapshot = new ArrayList<>(observers);
//        }
//        for (SetObserver<E> observer : snapshot)
//            observer.added(this, element);
//    }

    /*
    *  将外来的方法调用移出同步代码块，还有一种更好的方法。并发集合：CopyOnWriteArrayList
    *这是专门为此定制的。这是ArrayList的一种变体，通过重新拷贝整个底层数组，在这里实现所有的写操作。
    * 由于内部数组永远不会改动，因此迭代不需要锁定，速度也非常快。
    * 如果大量的使用，CopyOnWriteArrayList的性能将大受影响，但是对于观察者列表来说却是很好的，因为他们几乎不改动，并且经常遍历。
    * */


    // Thread-safe observable set with CopyOnWriteArrayList
//    private final List<SetObserver<E>> observers =
//            new CopyOnWriteArrayList<>();
//
//    public void addObserver(SetObserver<E> observer) {
//        observers.add(observer);
//    }
//
//    public boolean removeObserver(SetObserver<E> observer) {
//        return observers.remove(observer);
//    }
//
//    private void notifyElementAdded(E element) {
//        for (SetObserver<E> observer : observers)
//            observer.added(this, element);
//    }
//
//    @Override public boolean add(E element) {
//        boolean added = super.add(element);
//        if (added)
//            notifyElementAdded(element);
//        return added;
//    }
//
//    @Override public boolean addAll(Collection<? extends E> c) {
//        boolean result = false;
//        for (E element : c)
//            result |= add(element);  // Calls notifyElementAdded
//        return result;
//    }
}
