package effectivejava.chapter10;

import java.io.IOException;

public class Exception {

    //69 只针对异常的情况才使用异常：异常应该只用于异常的 情况下；它们永远不应该用于正常的控制流
    //设计良好的API不应该强迫他的客户端为了正常的控制流而使用异常
     /*
     for (Iterator<Foo> i = collection.iterator(); i.hasNext(); ) {
        Foo foo = i.next();
        ...
    }
   try {
        Iterator<Foo> i = collection.iterator();
        while(true) {
            Foo foo = i.next();
            ...
        }
    } catch (NoSuchElementException e) {
    }
    */


    //70三种可抛出结构：checked exceptions（可恢复的情况），runtime exceptions（不确定是否可恢复）, errors.

    /*    受检异常：Java编译器要求程序必须捕获或声明抛出这种异常。
         一个方法必须通过throws语句在方法的声明部分说明它可能抛出但并未捕获的所有checkedException。*/ ClassNotFoundException FileNotFoundException;
    IOException InterruptedException;
    NoSuchFieldException NoSuchMetodException;

    /*  非受检异常：RuntimeException的任何子类都无需在throws子句中进行声明
        编程人员的逻辑问题，java编译器不惊醒强制要求处理*/ ClassCastException EnumConstantNotPresentException;
    IllegalArgumentException IllegalThreadStateException;
    NumberFormatException IllegalMonitorStateException;
    IllegalStateException IndexOutOfBoundsException;
    ArrayIndexOutOfBoundsException StringIndexOutOfBoundsException;
    NegativeArraySizeException NullPointerException;
    SecurityException TypeNotPresentException;


    //71避免不惜要地使用受检异常
    //45-48 受检异常不能直接在Stream中使用
    //消除受检异常最容易的方法：返回所要的结果类型的一个optional（55）：缺点无法返回任何额外的信息来说明无法执行想要的结果。
    //把受检异常编程未受检异常


    //72 优先使用标准的异常
//    1.它使你的API可读性更强，因为它与程序员习惯的用法一致。
//    2.异常类越少，程序在类装载阶段的负担就越少，时间开销也越少。
//    常用的标准异常：
//    IllegalArgumentException 参数不符合条件
//    IllegalStateException 接受对象的状态异常
//    NullpointerException 空指针异常
//    IndexOutOfBoundsException 数组越界
//    ConcurrentModificationException 并发修改异常
//    UnsopportedOperationException 不支持操作异常

//    73.抛出与抽象类对应的异常
//  异常转译(exception translation)：  如果一个方法抛出的异常与它执行的任务没有明显的关联关系，这种情形会让人不知所措。
// 当一个方法传递一个由低层抽象抛出的异常时，往往会发生这种情况。这种情况发生时，不仅让人困惑，而且也"污染"了高层API。
// 为了避免这个问题，高层实现应该捕获低层的异常，同时抛出一个可以按照高层抽象进行介绍的异常。
// e.g AbstractSequentialList的get();
// 而在get()方法中，抛出NoSuchElementException异常会让人感到困惑。所以，
// get()对NoSuchElementException进行了捕获，并抛出了IndexOutOfBoundsException异常。
// 即，相当于将NoSuchElementException转译成了IndexOutOfBoundsException异常。
    //建议：
    //处理来自底层的异常最好的做法是在调用底层之前确保他们会成功执行；
    // 其次让耗层来悄悄的处理异常，从而将高层的方法调用者与底层的问题隔离开；

//    74 每个方法抛出的异常都要有文档
//　　要单独的声明被检查的异常，并且利用Javadoc的@throws标记，准确地记录下每个异常被抛出的条件。
//    如果一个类中的许多方法处于同样的原因而抛出同一个异常，那么在该类的文档注释中对这个异常做文档，而不是为每个方法单独做文档，这是可以接受的。

//    74.在细节消息中包含失败 -- 捕获消息
//　　简而言之，当我们自定义异常或者抛出异常时，应该包含失败相关的信息。
//   当一个程序由于一个未被捕获的异常而失败的时候，系统会自动打印出该异常的栈轨迹。在栈轨迹中包含该异常的字符串表示。典型情况下它包含该异常类的类名，以及紧随其后的细节消息。

   /* 75 在细节消息中包含失败 -- 捕获消息
    简而言之，当我们自定义异常或者抛出异常时，应该包含失败相关的信息。
    当一个程序由于一个未被捕获的异常而失败的时候，系统会自动打印出该异常的栈轨迹。
    在栈轨迹中包含该异常的字符串表示。典型情况下它包含该异常类的类名，以及紧随其后的细节消息。*/

   /* 76努力使失败保持原子性
    当一个对象抛出一个异常之后，我们总期望这个对象仍然保持在一种定义良好的可用状态之中。
    对于被检查的异常而言，这尤为重要，因为调用者通常期望从被检查的异常中恢复过来。
    一般而言，一个失败的方法调用应该保持使对象保持在"它在被调用之前的状态"。
    具有这种属性的方法被称为具有"失败原子性(failure atomic)"。
    可以理解为，失败了还保持着原子性。对象保持"失败原子性"的方式有几种：
            (01) 设计一个非可变对象。
            (02) 对于在可变对象上执行操作的方法，获得"失败原子性"的最常见方法是，在执行操作之前检查参数的有效性。如下(Stack.java中的pop方法)：
    public Object pop() {
        if (size==0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null;
        return result;
        }
            (03) 与上一种方法类似，可以对计算处理过程调整顺序，使得任何可能会失败的计算部分都发生在对象状态被修改之前。
            (04) 编写一段恢复代码，由它来解释操作过程中发生的失败，以及使对象回滚到操作开始之前的状态上。
            (05) 在对象的一份临时拷贝上执行操作，当操作完成之后再把临时拷贝中的结果复制给原来的对象。
            虽然"保持对象的失败原子性"是期望目标，但它并不总是可以做得到。
            例如，如果多个线程企图在没有适当的同步机制的情况下，并发的访问一个对象，
            那么该对象就有可能被留在不一致的状态中。

       即使在可以实现"失败原子性"的场合，它也不是总被期望的。对于某些操作，它会显著的增加开销或者复杂性。
       总的规则是：作为方法规范的一部分，任何一个异常都不应该改变对象调用该方法之前的状态，
       如果这条规则被违反，则API文档中应该清楚的指明对象将会处于什么样的状态。*/

    /*77 不要忽略异常
    当一个API的设计者声明一个方法会抛出某个异常的时候，他们正在试图说明某些事情。所以，请不要忽略它！*/

}
