package effectivejava.chapter8.item53;

import java.util.stream.IntStream;

// Sample uses of varargs (Pages 245-6)
public class Varargs {
    // Simple use of varargs (Page 245)
    static int sum(int... args) {
        int sum = 0;
        for (int arg : args)
            sum += arg;
        return sum;
    }

//    //假设要编写一个计算其多个参数最小值的方法。 如果客户端不传递任何参数，则此方法定义不明确。 你可以在运行时检查数组长度
//    //该解决方案存在几个问题。 最严重的是，如果客户端在没有参数的情况下调用此方法，则它在运行时而不是在编译时失败。
//    //另一个问题是它很难看。 必须在args参数上包含显式有效性检查，
//    //除非将min初始化为Integer.MAX_VALUE，否则不能使用for-each循环，这也很难看。
//    static int min(int... args) {
//        if (args.length == 0)
//            throw new IllegalArgumentException("Too few arguments");
//        int min = args[0];
//        for (int i = 1; i < args.length; i++)
//            if (args[i] < min)
//                min = args[i];
//        return min;
//    }

    // 幸运的是，有一种更好的方法可以达到预期的效果。
    // 声明方法采用两个参数，一个指定类型的普通参数，另一个此类型的可变参数。 该解决方案纠正了前一个示例的所有缺陷：
    static int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs)
            if (arg < min)
                min = arg;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(min(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
