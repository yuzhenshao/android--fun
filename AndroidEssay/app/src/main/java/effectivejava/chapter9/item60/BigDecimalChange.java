package effectivejava.chapter9.item60;

import java.math.BigDecimal;

public class BigDecimalChange {
    public static void main(String[] args) {
        final BigDecimal TEN_CENTS = new BigDecimal(".10");

        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS;
             funds.compareTo(price) >= 0;
             price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("Money left over: $" + funds);
    }

    /*
    * 四块糖果，剩下0.00美元
    * 使用BigDecimal有两个缺点：它没有比使用基本算术类型方便，而且速度要慢得多。
    * 如果你只解决一个简单的问题，后一种缺点是无关紧要的，但前者可能会让你烦恼；
    * 我们可以使用int或long类型，具体取决于所涉及的数量，并自己控制十进制小数点
    * BigDecimal的一个好处是，它可以完全控制舍入，当执行需要舍入的操作时，
    * 可以从八种舍入模式中进行选择。如果你使用合法的舍入行为执行业务计算，这将非常方便
    * IntChange
    * */
}
