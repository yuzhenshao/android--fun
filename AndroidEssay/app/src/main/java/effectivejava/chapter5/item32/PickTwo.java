package effectivejava.chapter5.item32;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

// Subtle heap pollution (Pages 147-8)
public class PickTwo {
    // UNSAFE - Exposes a reference to its generic parameter array!
    static <T> T[] toArray(T... args) {
        return args;
    }

//    这个方法本身不是危险的，除了调用具有泛型可变参数的toArray方法之外，不会产生警告。
//    编译此方法时，编译器会生成代码以创建一个将两个T实例传递给toArray的可变参数数组。
//    这段代码分配了一个Object []类型的数组，它是保证保存这些实例的最具体的类型，
//    而不管在调用位置传递给pickTwo的对象是什么类型。 toArray方法只是简单地将这个数组返回给pickTwo，
//    然后pickTwo将它返回给调用者，所以pickTwo总是返回一个Object []类型的数组。
    //它接受三个类型T的参数，并返回一个包含两个参数的数组，随机选择：

    static <T> T[] pickTwo(T a, T b, T c) {
        switch(ThreadLocalRandom.current().nextInt(3)) {
            case 0: return toArray(a, b);
            case 1: return toArray(a, c);
            case 2: return toArray(b, c);
        }
        throw new AssertionError(); // Can't get here
    }


//    这种方法没有任何问题，因此它编译时不会产生任何警告。 但是当运行它时，
//    抛出一个ClassCastException异常，尽管不包含可见的转换。 你没有看到的是，
//    编译器已经生成了一个隐藏的强制转换为由pickTwo返回的值的String []类型，
//    以便它可以存储在属性中。 转换失败，因为Object []不是String []的子类型。
//    这种故障相当令人不安，因为它从实际导致堆污染（toArray）的方法中移除了两个级别，
//    并且在实际参数存储在其中之后，可变参数数组未被修改。

    public static void main(String[] args) {
        String[] attributes = pickTwo("Good", "Fast", "Cheap");
        System.out.println(Arrays.toString(attributes));
    }
}
