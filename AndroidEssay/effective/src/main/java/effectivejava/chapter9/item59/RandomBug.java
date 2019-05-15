package effectivejava.chapter9.item59;
import java.util.*;

// Random number generation is hard! - Page 215
public class RandomBug {
    // Common but deeply flawed!
    static Random rnd = new Random();

    /**
     * 缺陷：
     * 1.如果n是一个比较小的2的乘方，则随机数的序列将在相当短的时间段后开始重复
     * 2.如果n不是2的乘方，平均而言，某些数字会比其他数字出现得更加频繁。 如果n很大，这种效果可能非常明显
     * 以下main程序有力地证明了这一点，该程序在精心选择的范围内生成了100万个随机数，
     * 然后打印出有多少个数字落在范围的上半部分;如果random方法正常工作，程序将打印接近50万的数字，
     * 但如果运行它，你会发现它打印的数字接近666,666。random方法生成的三分之二数字落在其范围的上半部分！
     * 3.在极少数情况下，它可能会灾难性地失败，返回超出指定范围之外的数字。
     * 这是因为该方法尝试通过调用Math.abs将rnd.nextInt()返回的值映射到非负整数。
     * 如果nextInt()返回Integer.MIN_VALUE，则Math.abs也会返回Integer.MIN_VALUE，
     * 假设n不是2的乘方，取模运算符（％）将返回负数。 这几乎肯定会导致程序失败，并且可能难以重现。
     * */


    static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }

    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 1000000; i++)
            if (random(n) < n/2)
                low++;
        System.out.println(low);
    }
}
