package effectivejava.chapter8.item56;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// Documentation comment examples (Pages 255-9)
public class DocExamples<E> {
    // 请注意在此文档注释（<p>和<i>）中使用HTML标记。 Javadoc实用工具将文档注释转换为HTML，
    // 文档注释中的任意HTML元素最终都会生成HTML文档。 有时候，程序员甚至会在他们的文档注释中嵌入HTML表格，尽管这种情况很少见。
    //
    // 还要注意在@throw子句中的代码片段周围使用Javadoc的 {@code}标签。这个标签有两个目的:
    // 它使代码片段以代码字体形式呈现，并且它抑制了代码片段中HTML标记和嵌套Javadoc标记的处理。
    // 后一个属性允许我们在代码片段中使用小于号(<)，即使它是一个HTML元字符。
    // 换句话说，在代码示例前面加上字符<pre>{@code，然后在代码后面加上}</pre>。这保留了代码中的换行符，
    // 并消除了转义HTML元字符的需要，但不需要转义at符号(@)，如果代码示例使用注释，则必须转义at符号(@)。
    //最后，请注意文档注释中使用的单词“this list”。按照惯例，“this”指的是在实例方法的文档注释中，指向方法调用所在的对象。

    /**
     * Returns the element at the specified position in this list.
     *
     * <p>This method is <i>not</i> guaranteed to run in constant
     * time. In some implementations it may run in time proportional
     * to the element position.
     *
     * @param  index index of element to return; must be
     *         non-negative and less than the size of this list
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= this.size()})
     */
    E get(int index) {
        return null;
    }

    // @implSpec注释描述了方法与其子类之间的契约，如果它继承了方法或通过super调用方法，那么允许子类依赖于实现行为
    /**
     * Returns true if this collection is empty.
     *
     * @implSpec This implementation returns {@code this.size() == 0}.
     *
     * @return true if this collection is empty
     */
    public boolean isEmpty() {
        return false;
    }

    // Use of the @literal tag to include HTML and javadoc metacharacters in javadoc comments.
    // 该标签禁止处理HTML标记和嵌套的Javadoc标记
    /**
     * A geometric series converges if {@literal |r| < 1}.
     */
    public void fragment() {
    }

    // Controlling summary description when there is a period in the first "sentence" of doc comment. (Page 257)
    /**
     * A suspect, such as Colonel Mustard or {@literal Mrs. Peacock}.
     */
    public enum FixedSuspect {
        MISS_SCARLETT, PROFESSOR_PLUM, MRS_PEACOCK, MR_GREEN, COLONEL_MUSTARD, MRS_WHITE
    }

    // 有时，可能希望索引对你的API很重要的其他术语。为此添加了{@index}标签。
    // 对文档注释中出现的术语进行索引，就像将其包装在这个标签中一样简单
    /**
     * This method complies with the {@index IEEE 754} standard.
     */
    public void fragment2() {
    }

    // 在记录枚举类型时，一定要记录常量，以及类型和任何公共方法
    /**
     * An instrument section of a symphony orchestra.
     */
    public enum OrchestraSection {
        /** Woodwinds, such as flute, clarinet, and oboe. */
        WOODWIND,

        /** Brass instruments, such as french horn and trumpet. */
        BRASS,

        /** Percussion instruments, such as timpani and cymbals. */
        PERCUSSION,

        /** Stringed instruments, such as violin and cello. */
        STRING;
    }

    // 在为注解类型记录文档时，一定要记录任何成员，以及类型本身。
    // 用名词短语表示的文档成员，就好像它们是属性一样。对于类型的概要描述，
    // 请使用动词短语，它表示当程序元素具有此类型注解的所表示的含义:
    /**
     * Indicates that the annotated method is a test method that
     * must throw the designated exception to pass.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExceptionTest {
        /**
         * The exception that the annotated test method must throw
         * in order to pass. (The test is permitted to throw any
         * subtype of the type described by this class object.)
         */
        Class<? extends Throwable> value();
    }
}

