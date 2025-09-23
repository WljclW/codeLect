package dynmac;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author mini-zch
 * @date 2025/5/6 11:33
 */
public class DynamicProxy {
    public static Object getProxyInstance(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理方法执行之前。。。。。");
                        System.out.println("原始的参数时："+ Arrays.toString(args));
                        args[0] = "dhada";
                        System.out.println("修改后的参数："+Arrays.toString(args));
                        Object invoke = method.invoke(target, args);
                        System.out.println("执行结果。。"+invoke);
                        System.out.println("代理方法执行之后。。。。。。。。。");
                        return invoke;
                    }
                });
    }

    public static void main(String[] args) {
        calcuImpl calcu = new calcuImpl();
        /*在使用之前需要将动态代理类强制转换为接口类型，不然不能调用方法*/
        calcu proxyInstance = (dynmac.calcu) DynamicProxy.getProxyInstance(calcu);
        proxyInstance.add(2,3);
    }
}
