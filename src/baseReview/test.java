package baseReview;

/**
 * @author mini-zch
 * @date 2025/9/9 15:25
 *
      方法调用多态：运行时决定（动态绑定）。
      方法重载：编译期决定（静态绑定）。
      成员变量：编译期决定，不具备多态性。
      向上转型：安全，只能调用父类定义的方法。
      向下转型：需要强制类型转换，可能抛 ClassCastException。
 */

class A {
    public int x = 10;
    public void print() {
        System.out.println("A: " + x);
    }
}

class B extends A {
    public int x = 20;
    @Override
    public void print() {
        System.out.println("B: " + x);
    }
}

public class test {
    public static void main(String[] args) {
        A obj = new B();
        System.out.println(obj.x); // 10 (变量看编译期类型)
        obj.print();               // B: 20 (方法看运行期类型)
    }
}

