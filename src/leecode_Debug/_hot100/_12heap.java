package leecode_Debug._hot100;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
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



    /*347.
    *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率
    * 前 k 高的元素。你可以按 任意顺序 返回答案。
    * */
    /**
     *【提示】这个题官方的测试用例有问题！！！
     *【说明】求解“数组的第K个最大元素”时最优做法是快速排序，但是这个题的最优做法就是下面
     *   的方法————map统计次数，再使用优先级队列求结果！！
     *      造成这种区别的一种因素是：“数组的第K个最大元素”时并不用了解整个数组的每个元素
     *   的结果，并不一定所有元素都搞好；但是这个题要比较次数，因此每一个元素都必须统计好
     *【解题步骤】
     *     step1：统计nums中每一个数出现的次数；（使用map统计）
     *     step2：根据map的entry，将"数字->出现的次数"这样的键值对创建数组添加进优先级队
     * 列，指定排序规则————按照出现的次数升序(同时添加的时候保证优先级队列只有k个元素。方
     * 法是：不管三七二十一，先加进去，如果加进去发现queue.size()大于k了，就弹出堆顶，因
     * 为堆顶是出现次数最少的)
     *     step3：从优先级队列依次弹出每个元素（数组形式），将数字（即数组的第0维）添加进
     * 结果数组
     * @author: Zhou
     * @date: 2025/6/1 15:35
     */
    public int[] topKFrequent(int[] nums, int k) {
        /*step1：统计数组每一个元素出现的次数*/
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        /*step2：声明优先级队列，并指定按照次数（即map的value，也即一维数组的第1维）升序*/
        PriorityQueue<int[]> queue = new PriorityQueue<>((O1,O2)->{
            return O1[1]-O2[1];
        });
        /**上面的排序规则可以使用下面的来代替，原理是什么？？*/
//        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        /*step3：（1）遍历map，根据键值对创建一维int数组，放进优先级队列；
        *       （2）整个过程保证优先级队列的size不大于k
        * */
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            int[] cur = new int[2];
            cur[0] = entry.getKey();
            cur[1] = entry.getValue();
            queue.add(cur);
            if (queue.size()>k){ //如果优先级队列size大了，就弹出一个
                queue.poll();
            }
        }
        /*step4：声明存结果的数组，将优先级队列中数组的第0维拿出来*/
        int[] res = new int[k];
        int i=0;
        while(!queue.isEmpty()){
            res[i++] = queue.poll()[0];
        }
        return res;
    }



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
