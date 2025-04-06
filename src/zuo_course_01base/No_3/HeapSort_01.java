package zuo_course_01base.No_3;

import java.util.PriorityQueue;

/**
 * 堆排序的例题
 *
 * */
public class HeapSort_01 {
    public void sortedArrDistanceLessK(int[] arr,int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();        //如果没有实参，这个就是java内置的小根堆结构。
        int index =0;
        for(;index<=Math.min(arr.length,k);index++){        //先把前面的k+1个数放进小根堆
            heap.add(arr[index]);
        }
        int i = 0;
        for(;index<arr.length;index++,i++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while(!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }
}
