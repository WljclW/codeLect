package baseReview;


import java.lang.annotation.*;

/**
 * 注解必须要保留到运行时(即Retention属性是RUNTIME)才能被程序获取到
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    public String title() default "";
    public String description() default "";
}
