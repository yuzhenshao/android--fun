package effectivejava.chapter11.item81;
import java.util.concurrent.*;

// Concurrent canonicalizing map atop ConcurrentMap - Pages 273-274
public class Intern {
    // Concurrent canonicalizing map atop ConcurrentMap - not optimal
    private static final ConcurrentMap<String, String> map =
            new ConcurrentHashMap<>();

    // 此方法模拟String.intern 方法的行为，
//    public static String intern(String s) {
//        String previousValue = map.putIfAbsent(s, s);
//        return previousValue == null ? s : previousValue;
//    }

    //事实上，你可以做的更好，ConcurrentHashMap针对get进行了优化，
    // 只有在get表明有必要时，才首先调用get并调用putIfAbsent方法:
    // Concurrent canonicalizing map atop ConcurrentMap - faster!
    public static String intern(String s) {
        String result = map.get(s);
        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null)
                result = s;
        }
        return result;
    }
}