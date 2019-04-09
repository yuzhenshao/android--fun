package effectivejava.chapter5.item31;
import java.util.*;

// Generic stack with bulk methods using wildcard types (Pages 139-41)
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // The elements array will contain only E instances from push(E).
    // This is sufficient to ensure type safety, but the runtime
    // type of the array won't be E[]; it will always be Object[]!
    @SuppressWarnings("unchecked")
        public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size==0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

//    // pushAll staticfactory without wildcard type - deficient!
//    //方法一  这种方法可以干净地编译，但不完全令人满意。 如果可遍历的src元素类型与栈的元素类型完全匹配，那么它工作正常。
//    //否则error，因为参数化类型是不变的
//    public void pushAll(Iterable<E> src) {
//        for (E e : src)
//            push(e);
//    }


    //幸运的是，有对应的解决方法。 java提供了一种特殊的参数化类型：有限制的通配符类型，它可以处理这种情况。
    //pushAll的输入参数的类型不应该是“E的Iterable接口”，而应该是“E的某个子类型的Iterable接口”，
    //通配符类型，Iterable <？ extends E> 就是这个意思。（关键字extends的使用有点误导：回忆条目 29中，
    //子类型被定义为每个类型都是它自己的子类型，即使它本身没有继承。）：
    // Wildcard type for parameter that serves as an E producer
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src)
            push(e);
    }

    //同样，如果目标集合的元素类型与栈的元素类型完全匹配，则干净编译并且工作正常。 但是，这又不完全令人满意。
    //popAll的输入参数的类型不应该是“E的集合”，而应该是“E的某个父类型的集合”（其中父类型被定义为E是它自己的父类型
    // popAll staticfactory without wildcard type - deficient!
//    public void popAll(Collection<E> dst) {
//        while (!isEmpty())
//            dst.add(pop());
//    }

    // Wildcard type for parameter that serves as an E consumer
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty())
            dst.add(pop());
    }

    // Little program to exercise our generic Stack
    public static void main(String[] args) {
        Stack<Number> numberStack = new Stack<>();
        Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        numberStack.pushAll(integers);

        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects);

        System.out.println(objects);
    }
}
