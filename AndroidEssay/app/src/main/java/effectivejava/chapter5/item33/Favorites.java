package effectivejava.chapter5.item33;
import java.util.*;

// Typesafe heterogeneous container pattern (Pages 151-4)
//Favorites实例是类型安全的：当你请求一个字符串时它永远不会返回一个整数。
//        它也是异构的：与普通Map不同，所有的键都是不同的类型。
//        因此，我们将Favorites称为类型安全异构容器（typesafe heterogeneous container.）
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

//    // Achieving runtime type safety with a dynamic cast
//    public <T> void putFavorite(Class<T> type, T instance) {
//        favorites.put(Objects.requireNonNull(type), type.cast(instance));
//    }

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);
        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
        System.out.printf("%s %x %s%n", favoriteString,
                favoriteInteger, favoriteClass.getName());
        //这个程序打印Java cafebabe Favorites
    }
}