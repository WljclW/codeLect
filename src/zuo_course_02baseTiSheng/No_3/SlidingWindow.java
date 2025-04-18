package zuo_course_02baseTiSheng.No_3;

import java.util.LinkedList;

/**
 *  求滑动窗口内的最值。
 *      双端队列(首尾两端都可以进出节点)中进的是数在数组中的索引(存下标可以代表更多的信息。不仅知道了索引的信息，还知道
 *  了数，无非就是根据索引取数组拿一次)
 * */
public class SlidingWindow {
    public static class MaxWindow{
        private int L;
        private int R;
        private int[] arr;
        private LinkedList<Integer> qmax;
        public MaxWindow(int[] a){
            arr =a;
            L=-1;
            R=0;
            qmax = new LinkedList<>();
        }
        public void addNumFromRight(){
            if (R==arr.length){
                return;
            }
            while(!qmax.isEmpty() && arr[qmax.peekLast()]<=arr[R]){     //等于也弹出
                qmax.pollLast();
            }
            qmax.addLast(R);
            R++;
        }

        public void removeNumFromLeft(){
            if (L>R-1){
                return;
            }
            L++;
            if (arr[L] == arr[qmax.peekFirst()]){
                qmax.peekFirst();
            }
        }

        public Integer getMax(){
            if(!qmax.isEmpty()){
                return arr[qmax.peekFirst()];       //规定双端队列头部最大
            }
            return null;
        }

    }
}
