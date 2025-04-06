package test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Deque<Integer> lev = new LinkedList<>();
        System.out.println("============");
        lev.offer(1);
        lev.offer(2);
        lev.offer(3);
        System.out.println(lev.poll());
        System.out.println(lev.poll());
        System.out.println(lev.poll());
        System.out.println("============");
        lev.offerFirst(1);
        lev.offerFirst(2);
        lev.offerFirst(3);
        System.out.println(lev.poll());
        System.out.println(lev.poll());
        System.out.println(lev.poll());
        System.out.println("============");
        lev.offerLast(1);
        lev.offerLast(2);
        lev.offerLast(3);
        System.out.println(lev.poll());
        System.out.println(lev.poll());
        System.out.println(lev.poll());
        System.out.println("&&&&&&&&&&&");

        Queue<Integer> lev1 = new LinkedList<>();
        lev1.offer(1);
        lev1.offer(2);
        lev1.offer(3);


        System.out.println("============");
        System.out.println(lev1.poll());
        System.out.println(lev1.poll());
        System.out.println(lev1.poll());
        lev1.add(1);
        lev1.add(2);
        lev1.add(3);
        System.out.println("============");
        System.out.println(lev1.poll());
        System.out.println(lev1.poll());
        System.out.println(lev1.poll());
    }
}
