package effectivejava.chapter4.item20;

public class PreferInterfacesToAbsClasses {
    //接口优于抽象类
    //Java有两种机制来定义允许多个实现的类型：接口和抽象类
    现有的类可以很容易地进行改进来实现一个新的接口：你只需添加所需的方法（如果尚不存在的话），并向类声明中添加一个implements子句
    接口是定义混合类型（mixin）的理想选择。 一般来说，mixin是一个类，除了它的“主类型”之外，还可以声明它提供了一些可选的行为。
    接口允许构建非层级类型的框架。 类型层级对于组织某些事物来说是很好的，但是其他的事物并不是整齐地落入严格的层级结构中。
    假设我们有一个代表歌手的接口，另一个代表作曲家的接口：
    public interface Singer {
        AudioClip sing(Song s);
    }

    public interface Songwriter {
        Song compose(int chartPosition);
    }

    在现实生活中，一些歌手也是作曲家。 因为我们使用接口而不是抽象类来定义这些类型，所以单个类实现歌手和作曲家两个接口是完全允许的。
    事实上，我们可以定义一个继承歌手和作曲家的第三个接口，并添加适合于这个组合的新方法：
    public interface SingerSongwriter extends Singer, Songwriter {
        AudioClip strum();

        void actSensitive();
    }

}
