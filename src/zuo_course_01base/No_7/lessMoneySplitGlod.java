package zuo_course_01base.No_7;

import java.util.PriorityQueue;

public class lessMoneySplitGlod {

    public static int lessMoney(int[] arr){
        /**
         *  霍夫曼的原理：每一次从一堆数中拿出最小的两个形成一个新的节点，将这个新节点放入一堆数；
         *      继续选择列宁哥哥最小的数形成一个新的节点，将新节点放入一堆数。
         *      ..............
         *  由于每一步总是选择最小的两个节点，因此引入小根堆来解决这个问题。每一次弹出两个节点，也就是最小的两个节点了。
         * */
        PriorityQueue<Integer> que = new PriorityQueue<>();     //PriorityQueue就是Java中的小根堆。
        for (int i=0;i<arr.length;i++)
            que.add(arr[i]);            //将初始的一堆数放入小根堆。
        int cur = 0;
        int sum = 0;
        while(que.size()>1){
            /**
             *      while循环就体现了霍夫曼编码的思想
             * */
            cur = que.poll() + que.poll();      //每次拿出两个最小的节点
            sum += cur;
            que.add(cur);                       //将新的节点放入小根堆
        }
        return sum;
    }

    public static int lessMoney_FuXi(int[] arr){
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i=0;i<arr.length;i++){
            que.add(arr[i]);
        }
        int cur = 0;
        int sum = 0;
        while(que.size()>1){
            cur = que.poll() + que.poll();
            que.add(cur);
            sum+=cur;
        }
        return sum;
    }
}
