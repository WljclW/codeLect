package baseReview;


import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.Method;

/**
 * @author mini-zch
 * @date 2025/6/13 9:51
 */
public class TestMyAnnotation {
    public static void main(String[] args) {
        try {
            Method[] methods = TestMethodAnnotation.class.getClassLoader()
                    .loadClass("baseReview.TestMethodAnnotation")
                    .getMethods();
            for (Method method:methods){
                if (method.isAnnotationPresent(MyAnnotation.class)) {
                    try {
                        // 获取并遍历方法上的所有注解
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '"
                                    + method + "' : " + anno);
                        }

                        // 获取MyMethodAnnotation对象信息
                        MyAnnotation methodAnno = method
                                .getAnnotation(MyAnnotation.class);

                        System.out.println(methodAnno.title());

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
