package leecode_Debug._hot100;

import java.util.PriorityQueue;

/**215、掌握最优的解法；347；295*/
public class _12heap {
    public static void main(String[] args) {
        _12heap heap = new _12heap();
    }


    /*215.
    * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。*/
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int res = 0;
        for (int i:nums){
            queue.offer(i);
        }
        while (queue.size()>k){
            queue.poll();
        }
        return queue.poll();
    }



    /*347. //无思路
    *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率
    * 前 k 高的元素。你可以按 任意顺序 返回答案。
    * */
//    public int[] topKFrequent(int[] nums, int k) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i:nums){
//            map.put(i,map.getOrDefault(i,0)+1);
//        }
//    }



    /*295
    中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的
    平均值。
    例如 arr = [2,3,4] 的中位数是 3 。
    例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
    实现 MedianFinder 类:
        MedianFinder() 初始化 MedianFinder 对象。
        void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
        double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答
        案将被接受。
     */
    /**
     * [说明]：
     *    1.这种方式是错的：添加元素的时候挑一个堆入，入进去了再调整
     *    2.小根堆保存了nums中较大的一半；大根堆保存了nums中较小的一半*/
    class MedianFinder {

        PriorityQueue<Integer> small;
        PriorityQueue<Integer> big;
        public MedianFinder() {
            small = new PriorityQueue<>();
            big = new PriorityQueue<>((a, b) -> {
                return b - a;
            });
        }

        /**添加时下面的逻辑是错误的。。这样的添加会导致最后两个堆中的数据没有必然的大小关系*/
//        public void addNum(int num) {
//            small.offer(num);
//            while (small.size()- big.size()>1){
//                big.offer(small.poll());
//            }
//        }
        //正确的方法应该是下面的形式，原因？？
        public void addNum(int num) {
            if(small.size()>big.size()){
                small.offer(num);
                big.offer(small.poll());
            }else{
                big.offer(num);
                small.offer(big.poll());
            }
        }



        public double findMedian() {
            int size = small.size() + big.size();
            if(size%2==0){
                return (big.peek()+small.peek())/2.0;
            }else{
                return small.peek();
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
