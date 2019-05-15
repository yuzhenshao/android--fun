package effectivejava.chapter11.item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Simple test of ObservableSet - Page 319
public class Test3 {

    /*
     *这一次我们没有遇到异常，而是遭到死锁。后台线程调用set.removeObserver，
     * 他企图锁定observers，但他无法获得该锁，因为主线程已经有锁了。
     * 在这期间，主线程一直在等待后台程序来完成对观察者的删除，这正是造成死锁的原因。
     *
     * */


    public static void main(String[] args) {
        ObservableSet<Integer> set =
                new ObservableSet<>(new HashSet<>());

// Observer that uses a background thread needlessly
        set.addObserver(new SetObserver<Integer>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23) {
                    ExecutorService exec =
                            Executors.newSingleThreadExecutor();
                    try {
                        exec.submit(() -> s.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);
                    } finally {
                        exec.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
    }
}