package effectivejava.chapter9.item60;

public class Change {

    /**
     * 假设你口袋里有一美元，你看到一个货架上有一排好吃的糖果，它们的价格仅仅是10美分，20美分，30美分，
     * 以此类推，直到1美元。你每买一颗糖，从10美分的那颗开始，直到你买不起货架上的下一颗糖。你买了多少糖果，换了多少零钱?
     */
    public static void main(String[] args) {
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("Change: $" + funds);
    }

    /**
     * 三块糖果，剩下0.3999999999999999美元，这是错误答案；解决此问题的正确方法是使用BigDecimal，int或long进行货币计算。
     * BigDecimalChange
     * */
}
