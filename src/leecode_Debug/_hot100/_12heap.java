package leecode_Debug._hot100;

import java.util.*;

/**215、掌握最优的解法；347；295*/
public class _12heap {
    public static void main(String[] args) {
        _12heap heap = new _12heap();
    }


    /*215. 数组中的第K个最大元素
    * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。*/

    /**
     这里给出的写法都不好，
        强烈建议使用的解法：leecode_Debug._hot100.codetop_unskilled#findKthLargest_best
     */
    /*写法1：偷懒的写法，借助优先级队列*/
    public int findKthLargest_queue(int[] nums, int k) {
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

    /*写法2：使用常规的快排版本。但是会有错————"超出时间限制   43 / 44 个通过的测试用例"*/
    public int findKthLargest_1(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,nums.length-k);
    }

    private int findKthLargest(int[] nums, int left, int right, int k) {
        if (left==right) return nums[left];
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap1(nums,pivotIndex,right);
        pivotIndex = partion1(nums,left,right);
        if (pivotIndex==k){
            return nums[pivotIndex];
        } else if (pivotIndex > k) {
            return findKthLargest(nums,left,pivotIndex-1,k);
        }else {
            return findKthLargest(nums,pivotIndex+1,right,k);
        }
    }

    private int partion1(int[] nums, int left, int right) {
        int cur = left;
        for (int i = left; i < right; i++) {
            if (nums[i]<=nums[right]){
                swap1(nums,cur++,i);
            }
        }
        swap1(nums,right,cur); /**与for循环块内的swap参数有区别。这里不能再让cur加加了，快排的代码中也是一样的道理。。因为partion1方法要返回随机选的数放在哪个位置了(位置指的是索引值)*/
        return cur;
    }

    private void swap1(int[] nums, int pivotIndex, int right) {
        int tmp = nums[pivotIndex];
        nums[pivotIndex] = nums[right];
        nums[right] = tmp;
    }



    /*347. 前 K 个高频元素
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
     */
    public int[] topKFrequent(int[] nums, int k) {
        /*step1：统计数组每一个元素出现的次数*/
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        /*step2：声明优先级队列，并指定按照"出现次数"升序排列（即map的value，也即一维数组的第1维；升序就是“前-后”）*/
        PriorityQueue<int[]> queue = new PriorityQueue<>((O1,O2)->{
            return O1[1]-O2[1];
        });
        /**上面的排序规则可以使用下面的来代替，原理是什么？？*/
//        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        /*step3：（1）遍历map，根据键值对创建一维int数组，放进优先级队列；
                 （2）整个过程保证优先级队列的size不大于k
        */
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            int[] cur = new int[2];
            cur[0] = entry.getKey();
            cur[1] = entry.getValue();
            queue.add(cur);
            if (queue.size()>k){ //如果优先级队列size大了，就弹出一个
                queue.poll();
            }
        }
        /*step4：声明存结果的数组，将优先级队列中数组的第0维拿出来依次存进数组*/
        int[] res = new int[k];
        int i=0;
        while(!queue.isEmpty()){
            res[i++] = queue.poll()[0];
        }
        return res;
    }



    /*295. 数据流的中位数
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
     *【建议】建议使用解法 MedianFinder_1。思想很简单、容易想（南辕北辙，想进min，则先进max，再从max弹出，弹出的入min）。
     * [说明]：
     *    1.这种方式是错的：添加元素的时候挑一个堆入，入进去了再调整
     *    2.【重要⚠】小根堆存了nums中较大的一半；大根堆保存了nums中较小的一半
     *【详细思路，建议使用下面的方法MedianFinder_1】
     *    1. 使用两个优先级队列。其中——
     *      ①min的含义：存放较小的那一半元素，因此顶应该是 较小那一半元素 的最大值！
     *      ②max的含义：存放较大的那一半元素，因此顶应该是 较大那一半元素 的最小值！
     *      疑问：为什么“min顶是较小那些元素的最大值，max顶部是较大那些元素的最小值”？？
     *          因为如果元素总数是偶数的话，就需要用到中间的两个元素————即较小那半元素的最
     *      大值 和 较大那半元素的最小值 的平均值。
     *          而min存储的是较小的那一半元素，因此它的最大值要能快速的拿到，因此顶部应
     *      该是最大值，即大顶堆（注意：PriorityQueue默认是小顶堆——升序，因此大顶堆需要
     *      指定排序规则）。
     *          同理，由于max存储的是较大的那一半元素，因此需求是能快速的拿出它的最小值，即
     *      小顶堆，PriorityQueue默认就是小顶堆，因此不用指定排序规则，，需要想一个不满足
     *      的例子？？？
     */
    class MedianFinder_1 {
        PriorityQueue<Integer> min; //存放较小的那一半 数据
        PriorityQueue<Integer> max; //存放较大的那一半 数据
        public MedianFinder_1() {
            /**min：存放较小的那一半。要求：能快速拿出小数中的最大值；
               max：存放较大的那一半。要求：能快速拿出大数中的最小值
                 由于优先级队列默认是"小根堆（升序的）————“小根堆”堆顶是最小值"，min要快速拿出最大值，因此需
             要指定排序规则。
             * */
            min = new PriorityQueue<>((A,B)->B.compareTo(A));
            max = new PriorityQueue<>();
        }

        public void addNum(int num) {
            /**
                 只要两边的数是相等的，最终的目的就是将数放在较小的那一半即 min，但是要想把数放到 min 中，必须先
             放进 max，然后弹出 max 的顶，把弹出来的数放入 min。
                 只要两边的数不相等，在这种设计下只有可能是 min 的元素比 max 多1，因为每一次相等时都是向 min 中
             放元素。此时需要把数放进 max（这个是最终目的）————也是同样的道理，数据最终要放进 max，需要先放进 min，
             然后从 min 中弹出数放入 max
                上面的这种“南辕北辙”的方式，目的是保证数据的全局有序和有效，仅仅弹出顶的做法不可靠！！
             */
            if (min.size()-max.size()==0){
                max.offer(num);
                min.offer(max.poll());
            }else {
                min.offer(num);
                max.offer(min.poll());
            }
        }

        public double findMedian() {
            /*
                1. 如果元素数量不相等，则必然是min的元素数量多1，此时直接返回min的顶部元素
                2. 如果元素数量相等，则需要min和max的顶部元素取平均值。
             */
            if (min.size()!=max.size()){
                return min.peek();
            }else {
                return (min.peek()+max.peek())/2.0;
            }
        }
    }

    /*跟 MedianFinder_1 是一样的思路，看 MedianFinder_1 即可，不用看下面的写法*/
    class MedianFinder {
        PriorityQueue<Integer> small;
        PriorityQueue<Integer> big;
        public MedianFinder() {
            small = new PriorityQueue<>(); /*small.peek() 得到的是 small 里最小的数。*/
            big = new PriorityQueue<>((a, b) -> {
                return b - a;
            }); /*big.peek() 得到的是 big 里最大的数。*/
        }

        /**添加时下面的逻辑是错误的。。这样的添加会导致最后两个堆中的数据没有必然的大小关系.。。
            根本原因：根本原因在于num仅仅与small部分的数据进行比较了，然后拿出small的最大值，将
         这个最大值直接放进big————大顶堆。。。。这样的操作可能会导致两边的数据并不具有明确的大小关
         系*/
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
