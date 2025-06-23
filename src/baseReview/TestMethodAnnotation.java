package baseReview;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mini-zch
 * @date 2025/6/12 11:43
 */
public class TestMethodAnnotation {
    static {
        System.out.println("静态代码块在执行中");
    }

    @Override
    @MyAnnotation(title = "toStringMethod", description = "override toString method")
    public String toString() {
        return "Override toString method";
    }

    @Deprecated
    @MyAnnotation(title = "old static method", description = "deprecated old static method")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @MyAnnotation(title = "test method", description = "suppress warning static method")
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }
}
