package leecode_Debug.test.blogTest.volatileTest;

import java.time.LocalDateTime;

public class Test1 {
    static int[] a = new int[]{1};

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("线程1开始休眠：" + LocalDateTime.now().toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1休眠结束：" + LocalDateTime.now().toString());
            a[0] = 0;
        }).start();

        while (a[0] != 0) {
            System.out.println(" ");
        }
        System.out.println("主线程退出循环：" + LocalDateTime.now().toString());
    }
}