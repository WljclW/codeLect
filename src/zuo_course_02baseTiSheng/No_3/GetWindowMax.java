package zuo_course_02baseTiSheng.No_3;

import java.util.LinkedList;

/**
 *  求窗口内的最值。
 *      思路:双端队列(首尾两端都可以进出节点)中进的是数在数组中的索引(存下标可以代表更多的信息。不仅知道了索引的信息，还知道
 *  了数，无非就是根据索引取数组拿一次)。任何一个元素进队时从队尾进，任何一个元素想出队时从队头出。
 *      举例（比如求解任意时刻窗口内的最大值），则需要满足如下：
 *          1. 任意时刻，保证双端队列内的元素严格单调递减。
 *          2. R移动时，就说明R位置的元素需要入队列，则需要从队尾依次判断，是不是队尾的元素值大于当前的值。如果满足，则当前
 *      元素入队列；否则的话从队尾弹出元素直到满足当前元素进入后严格递减
 *          3. L移动时，如果当前队头的索引正好出窗口了，则需要从队头弹出元素
 * */
public class GetWindowMax {
    public static class MaxWindow {
        private int L;
        private int R;
        private int[] arr;
        private LinkedList<Integer> qmax;

        public MaxWindow(int[] a) {
            arr = a;
            L = -1;
            R = 0;
            qmax = new LinkedList<>();
        }

        /**
         * 添加元素时从队尾添加
         */
        public void addNumFromRight() {
            if (R == arr.length) {
                return;
            }
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {     //等于也弹出
                qmax.pollLast();
            }
            qmax.addLast(R);
            R++;
        }

        /**
         * 如果左边界已经出了窗口了，需要将左边界的元素(如果在双端队列)移除。
         * 如果这个元素在双端队列中，则此时必然在队头位置
         */
        public void removeNumFromLeft() {
            if (L > R - 1) {
                return;
            }
            L++;
            if (arr[L] == arr[qmax.peekFirst()]) {
                qmax.peekFirst();
            }
        }

        /**
         * 获取当前窗口内的最大值
         */
        public Integer getMax() {
            if (!qmax.isEmpty()) {
                return arr[qmax.peekFirst()];  //规定双端队列头部最大
            }
            return null;
        }

    }
}
