package baseReview.testReflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

/**
 * @author mini-zch
 * @date 2025/6/13 14:42
 */
public class TestConstructor {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> thisClass = Class.forName("baseReview.testReflect.User");

        Constructor<?>[] declaredConstructors = thisClass.getDeclaredConstructors();
        for (Constructor con:declaredConstructors){
            System.out.println(con.getClass());
            System.out.println(con.getName());
            System.out.println(con.getModifiers());
            System.out.println(con.getTypeParameters());
            System.out.println(con.getParameters());
            System.out.println(con.getParameterTypes());
            System.out.println("=========");
        }
        Constructor<?> constructor = thisClass.getConstructor(String.class);
        System.out.println(constructor);
        System.out.println("##################################################################");
        /**=========================构造器的常用方法=====================================*/
        Constructor cs3 = thisClass.getDeclaredConstructor(int.class,String.class);
        System.out.println("-----getDeclaringClass-----");
        Class uclazz=cs3.getDeclaringClass();
//Constructor对象表示的构造方法的类
        System.out.println("构造方法的类:"+uclazz.getName());

        System.out.println("-----getGenericParameterTypes-----");
//对象表示此 Constructor 对象所表示的方法的形参类型
        Type[] tps=cs3.getGenericParameterTypes();
        for (Type tp:tps) {
            System.out.println("参数名称tp:"+tp);
        }
        System.out.println("-----getParameterTypes-----");
//获取构造函数参数类型
        Class<?> clazzs[] = cs3.getParameterTypes();
        for (Class claz:clazzs) {
            System.out.println("参数名称:"+claz.getName());
        }
        System.out.println("-----getName-----");
//以字符串形式返回此构造方法的名称
        System.out.println("getName:"+cs3.getName());

        System.out.println("-----getoGenericString-----");
//返回描述此 Constructor 的字符串，其中包括类型参数。
        System.out.println("getoGenericString():"+cs3.toGenericString());

    }
}

class User {
    private int age;
    private String name;
    public User() {
        super();
    }
    public User(String name) {
        super();
        this.name = name;
    }

    /**
     * 私有构造
     * @param age
     * @param name
     */
    private User(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
