package baseReview.testReflect;

/**
 * @author mini-zch
 * @date 2025/6/13 14:24
 */
public class TestReflect {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * Class.forName仅仅是把某一个类加载进jvm，仅仅是生成了这个类的Class对象。。。
         *      1. 常规意义的类对象并没有生成
         *      2. 类在加载的时候静态代码块、静态变量就会执行和初始化
         * */
        Class<?> thisClass = Class.forName("baseReview.TestMethodAnnotation");
        System.out.println(thisClass.getClass()); /**打印出来的信息是“java.lang.Class”*/
    }
}
