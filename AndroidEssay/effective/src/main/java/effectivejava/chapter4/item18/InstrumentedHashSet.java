package effectivejava.chapter4.item18;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


/*
 * 组合优于继承
 *
 * 继承缺点：
 *继承打破了封装， 换句话说，一个子类依赖于其父类的实现细节来保证其正确的功能。
 *超类的实现可能会随发布版本不断变化，如果真的发生变化，子类可能会被破坏，即使它的代码没有任何改变。
 *因此，子类必须要跟着其超类的更新而演变，除非超类是专门为了扩展而设计的，并有很好的文档的说明
 */
// Broken - Inappropriate use of inheritance!
public class InstrumentedHashSet<E> extends HashSet<E> {
    // The number of attempted element insertions
    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet();
        s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(s.getAddCount());
    }

    /*
     * 我们可以通过消除addAll方法的重写来“修复”子类。 尽管生成的类可以正常工作，
     * 但是它的功能正确性依赖于：HashSet的addAll方法是在其add方法之上实现的。
     * 这个“自我使用（self-use）”是一个实现细节，并不保证在Java平台的所有实现中都可以适用，
     * 并且可以随发布版本而变化。 因此，产生的InstrumentedHashSet类是脆弱的。
     */

    /*
     * 稍微好一点的做法是，重写addAll方法遍历指定集合，为每个元素调用add方法一次。
     *不管HashSet的addAll方法是否在其add方法上实现，都会保证正确的结果，因为HashSet的addAll实现将不再被调用。
     *然而，这种技术并不能解决所有的问题。 这相当于重新实现了父类方法，这样的方法可能不能确定到底是否时自用（self-use）的，
     *实现起来也是困难的，耗时的，容易出错的，并且可能会降低性能。 此外，这种方式并不能总是奏效，因为子类无法访问一些私有属性，所以有些方法就无法实现。
     */

    /*
     * 导致子类脆弱的一个相关原因是，他们的超类在后续的发型版本中可以获得新的方法：
     * 假设所有被插入到集中的元素都满足一个先决条件。可以通过对集合进行子类化，然后并重写所有添加元素的方法，
     * 以确保在添加每个元素之前满足这个先决条件，来确保这一问题。
     * 如果在后续的版本中，父类没有新增添加元素的方法，那么这样做没有问题。
     * 但是，一旦父类增加了这样的新方法，则很有肯能由于调用了未被重写的新方法，将非法的元素添加到子类的实例中。
     * */
}
