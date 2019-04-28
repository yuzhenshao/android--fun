package effectivejava.chapter9.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

// Reflective instantiaion demo (Page 283)
public class ReflectiveInstantiation {
    // Reflective instantiation with interface access
    /*
     * 这是一个创建Set<String>实例的程序，其实例的类由第一个命令行参数指定。
     * 程序将剩余的命令行参数插入到集合中并打印它。 无论第一个参数如何，程序都会打印剩余的参数，并删除重复项。
     * 但是，打印这些参数的顺序取决于第一个参数中指定的类。 如果指定java.util.HashSet，则它们以明显随机的顺序打印;
     * 如果指定java.util.TreeSet，则它们按字母顺序打印，因为TreeSet中的元素是按顺序排序的：
     * */

    public static void main(String[] args) {
        // Translate the class name into a Class object
        Class<? extends Set<String>> cl = null;
        try {
            cl = (Class<? extends Set<String>>)  // Unchecked cast!
                    Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            fatalError("Class not found.");
        }

        // Get the constructor
        Constructor<? extends Set<String>> cons = null;
        try {
            cons = cl.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fatalError("No parameterless constructor");
        }

        // Instantiate the set
        Set<String> s = null;
        try {
            s = cons.newInstance();
        } catch (IllegalAccessException e) {
            fatalError("Constructor not accessible");
        } catch (InstantiationException e) {
            fatalError("Class not instantiable.");
        } catch (InvocationTargetException e) {
            fatalError("Constructor threw " + e.getCause());
        } catch (ClassCastException e) {
            fatalError("Class doesn't implement Set");
        }

        // Exercise the set
        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }

    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }


    /*
     * 这个例子说明了反射的两个缺点。 首先，该示例在运行时生成六个不同的异常，如果不使用反射实例化，
     * 则所有这些异常都是编译时错误。
     * 第二个缺点是需要25行繁琐的代码才能从其名称生成类的实例， 而构造函数方法使用一行代码即可。
     * 可以通过捕获ReflectiveOperationException来减少程序的长度，该异常是Java 7中引入的各种反射异常的父类。
     * 这两个缺点仅限于实例化对象的程序部分。 实例化后，该集合与任何其他Set实例无法区分。
     * 在真实的程序中，大量的代码因此不会受这种限定的反射使用的影响。
     * */
}
