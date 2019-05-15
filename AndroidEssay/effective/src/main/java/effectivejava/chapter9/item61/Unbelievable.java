package effectivejava.chapter9.item61;

// What does this program do? - Page 274
public class Unbelievable {
    static Integer i;
   //几乎在每种情况下，当在基本类型和包装基本类型进行混合操作时，包装基本类型会自动拆箱
    public static void main(String[] args) {
        if (i == 42)
            System.out.println("Unbelievable");
    }
}