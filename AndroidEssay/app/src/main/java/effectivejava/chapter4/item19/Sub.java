package effectivejava.chapter4.item19;

import android.annotation.TargetApi;
import android.os.Build;

import java.time.Instant;

// Demonstration of what can go wrong when you override a method  called from constructor (Page 96)
public final class Sub extends Super {
    // Blank final, set by constructor
    private final Instant instant;

    @TargetApi(Build.VERSION_CODES.O)
    Sub() {
        instant = Instant.now();
    }

    // Overriding method invoked by superclass constructor
    @Override
    public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }


    /*
     * 你可能期望这个程序打印两次instant实例，但是它第一次打印出null，
     * 因为在Sub构造方法有机会初始化instant属性之前，overrideMe被Super构造方法调用。
     * 请注意，这个程序观察两个不同状态的final属性！ 还要注意的是，
     * 如果overrideMe方法调用了instant实例中任何方法，
     * 那么当父类构造方法调用overrideMe时，它将抛出一个NullPointerException异常。
     * 这个程序不会抛出NullPointerException的唯一原因是println方法容忍null参数。
     * */
}
