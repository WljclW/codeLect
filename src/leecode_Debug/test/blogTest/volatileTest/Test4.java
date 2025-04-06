package leecode_Debug.test.blogTest.volatileTest;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.LongAdder;

public class Test4 {
    static volatile A[] a = new A[]{new A(1)};
    static boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("线程1开始休眠：" + LocalDateTime.now().toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1休眠结束：" + LocalDateTime.now().toString());
            a[0] = new A(0);
        }).start();

        while (a[0].val != 0) {
        }
        System.out.println("主线程退出循环：" + LocalDateTime.now().toString());
    }


    static class A {
        public int val;

        A(int val) {
            this.val = val;
        }
    }
}