package zuo_course_01base.No_7_tanxin;

import java.util.PriorityQueue;

public class lessMoneySplitGlod {

    /**
     *  霍夫曼的原理：每一次从一堆数中拿出最小的两个形成一个新的节点，将这个新节点放入一堆数；
     *      继续选择最小的两个数形成一个新的节点，将新节点放入一堆数；.........
     *  由于每一步总是选择最小的两个节点，因此引入小根堆来解决这个问题。每一次弹出两个节点，也就是最小的两个节点了。
     * */
    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> que = new PriorityQueue<>();   //PriorityQueue就是Java中的小根堆。
        for (int i=0;i<arr.length;i++)
            que.add(arr[i]);            //将初始的一堆数放入小根堆。
        int cur = 0;
        int sum = 0;
        while(que.size()>1){  /*while循环就是霍夫曼原理的体现：每次利用最小的两个生成一个新的入队(反复执行这个操作)*/
            cur = que.poll() + que.poll();      //每次拿出两个最小的节点
            sum += cur;
            que.add(cur);                       //将新的节点放入小根堆
        }
        return sum;
    }
}
