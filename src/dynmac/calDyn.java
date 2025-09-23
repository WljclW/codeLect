package dynmac;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author mini-zch
 * @date 2025/5/6 11:12
 */
public class calDyn {
    public static void main(String[] args) {
        calcu cal = new calcuImpl();
//        submit(cal);

        calcu o = (calcu) Proxy.newProxyInstance(cal.getClass().getClassLoader(),
                cal.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("哇能的方法执行之前。。。。。。");
                        System.out.println("args的参数列表："+ Arrays.toString(args));
                        Object invoke = method.invoke(cal, args);
                        System.out.println("万能的方法执行之后。。。。。。。。。");
                        return invoke;
                    }
                });
        o.add(3,4);

    }

    private static void submit(calcu cal) {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("目标方法执行之前");
                Object invoke = method.invoke(cal, args);
                System.out.println("==============invoke:"+invoke);
                System.out.println("==============invoke。class:"+invoke.getClass().getSimpleName());
                System.out.println("目标方法执行之后");
                return invoke;
            }
        };
        calcu o = (calcu) Proxy.newProxyInstance(cal.getClass().getClassLoader(), cal.getClass().getInterfaces(), invocationHandler);
        o.add(2,3);
    }
}
