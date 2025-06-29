package leecode_Debug._hot100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class _04normalArr {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
//        new normalArr().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        new _04normalArr().merge(new int[][]{{1,4},{2,3}});
    }



    /*53.
    * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元
    *   素），返回其最大和。( 子数组是数组中的一个连续部分)
    * */
    /**【解题思想】到达任何一个数有两种选择：要麽和前面的数组成子数组；要麽自己单独成一个数组。
     *      两种选择到底采用哪一个呢？取最大的那个。
     *      那到底最大子数组和是多少呢？使用一个外面的变量来标记,每计算出一个位置的值时更新。*/
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE; //记录最大子数组和
        int curSum = 0; //记录以当前位置结尾的最大子数组的和
        for(int i=0;i<nums.length;i++){
            curSum = Math.max(nums[i],curSum+nums[i]); /*nums[i]独自成一队；cur+nums[i]和前面的部分子数组组成新的子数组*/
            maxSum = Math.max(curSum,maxSum); /*更新找到的最大子数组和————即返回值*/
        }
        return maxSum;
    }

    /*56.
    *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
    * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
    * */
    /**【题的关键】：将intervals中的区间先加进结果集(此时默认加在最后一位)，然后往后判断来更新结果集
     *      中最后一个数组的结尾界线。【注意】如果不是先将第一个元素加进结果集，直接研究会显得很复杂，
     *      逻辑上容易乱
     * 判断的标准：
     *      如果下一个区间的开始值 小于 结果集最后一个元素的结尾界限，比如：[1,4],[2,5]。则将结果集
     *  中最后一个数组的结尾更新为4和5的最大值；
     *      否则的话说明下一个区间的开始值 大于 结果集最后一个元素的结尾界限。说明这两个区间没有公共
     *  部分，因此需要将当前区间放进去成立一个新的区间。比如：[1,3],[5,7]*/
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        //step1：将数组按照第一个维度升序排列
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; /*参数1-参数2 表示升序(若是优先级队列则是小根堆)*/
            }
        });
        res.add(intervals[0]); /*step1的关键步骤：先将区间加到结果集里面，然后再利用后续元素更新这个元素的结尾界限*/
        /*step2：遍历剩下的数组。更新res中最后一个元素的右边界？还是往res中新添加一个区间？*/
        for (int i=1;i<intervals.length;i++) {
            int[] cur = intervals[i];
            if (res.getLast()[1] >= cur[0]) { //情况1:更新res中最后一个区间的右边界（说明cur的起点在最后一个数组区间内）
                res.getLast()[1] = Math.max(res.getLast()[1], cur[1]);
            } else {    //情况2：需要在res中添加新区间（因为cur的起点在res最后一个数组区间之外了）
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][]); /**err！！！：List转换为数组的方法。。错了多次*/
    }


    /*189.
    * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
    * */
    /**【解题关键】等价于三次翻转：先整体翻转；再分别翻转左右两部分*/
    public void rotate(int[] nums, int k) {
        k %= nums.length; /**🔺err：这里要对k取余，否则会报“IndexOutOfBoundException”*/
        swapL_R(nums,0,nums.length-1);
        swapL_R(nums,0,k-1);
        swapL_R(nums,k,nums.length-1);
    }

    public void swapL_R(int[] nums, int l, int r) {
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++; /**err：每一步之后，left指针++，但是right指针--。记得更新指针！！*/
            r--;  /**err：忘记更新指针，出现了“超出时间限制”*/
        }
    }


    /*238.
    * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素
    * 的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    * */
    /**注意这道题————
     *    1."不包括自己"的其余数乘积，因此是"当前位置"前面的数的乘积 乘以 后面的数的乘积。
     *    2.因此计算nums[1]的前缀积其实就是nums[1-1]*1；nums[2]的前缀积就是nums[2-1]*preMutil[2-1]
     *    整个过程要想串起来，就需要给preMutil[0]赋值为1，这样后面的就可以使用"preMutil[i-1]*nums[i-1"
     *    来计算了。。。。后缀积也是一样的道理
     *    3.不用看注释的方法了！*/
//    public int[] productExceptSelf(int[] nums) { /**使用了三倍的数组来存储*/
//        if(nums.length==1) return nums;
//        int[] preMutil = new int[nums.length];
//        int[] postMutil = new int[nums.length];
//        int[] res = new int[nums.length];
//        preMutil[0] = 1;
//        for (int i=1;i<nums.length;i++){
//            preMutil[i] = preMutil[i-1]*nums[i-1];
//        }
//        postMutil[nums.length-1] = 1;
//        res[nums.length-1] = postMutil[nums.length-1]*preMutil[nums.length-1];
//        for (int i=nums.length-2;i>=0;i--){
//            postMutil[i] = postMutil[i+1]*nums[i+1];
//            res[i] = preMutil[i]*postMutil[i];
//        }
//        return res;
//    }

    /**其实可以在返回的数组基础上进行记录，从而避免其余空间的浪费。
     * 【思路】：
     *     第一次正着遍历原始数组nums,res数组位置i存放它的前缀积，遍历到末尾位置。。。。然后接着反序遍历结果数组，
     *  利用局部变量来记录当前索引j的后缀乘积，这样的话倒着遍历的时候每到一个位置，“局部变量”✖“结果集的当
     *  前位置值”就是该位置的结果*/
    public int[] productExceptSelf(int[] nums){
        if(nums.length==1) return nums;
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i=1;i<nums.length;i++){
            res[i] = res[i-1]*nums[i-1];
        }

        int post = 1; //用于记录当前位置以后的数的连乘积是多少
        for (int i=nums.length-2;i>=0;i--){ /**err：等于0的时候也需要计算。否则结果数组的第一个数是1*/
            post *= nums[i+1];
            res[i] *= post;
        }
        return res;
    }





    /*41.
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
    /**
     * 【解题建议】建议使用解法1
     */
    /*解法1：简洁*/
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            /**while会不断的将i位置的元素放到正确的位置....
             * 【注意】
             *      1.虽然交换但是i位置不能变，因为换过来的数还需要继续判断，这就是
             * 为什么这里是while循环。。(跟"颜色交换(荷兰国旗类似)"，交换后不能移动cur指针)，
             * 因为交换来的元素需要研究一下
             *      2.与"小于 等于 大于"将数组的数分开有异曲同工，如果当前遍历的位置
             * 的数发现大于flag需要换到大于区域，因此需要继续判断这个"从大于区域换来
             * 的数"应该放到哪里————也就意味着swap之后不能变换当前研究的索引cur。
             *      3.一旦出while循环就表示：位置i位置的元素放到了正确的位置，并且所
             * 有换过来的元素也都放到了正确的位置*/
            /**err：三个条件缺一不可*/
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) { /**err：nums[i]是两个条件，否则会出现".....Index 6 out of bounds for length 5"*/
                swap(nums, i, nums[i] - 1); /**err：这里用额外的方法来实现！*/
            }
        }
        //经过上面的步骤理论上i位置存放的就是数字i+1.比如0位置存放1，1位置存放2....
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return nums.length + 1; /**err：到了这里说明数组中不缺，因此应该返回下一个数*/
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*解法2：*/
    public int firstMissingPositive01(int[] nums) {
        int cur = 0;
        /*step1：将每一个数放在正确的位置*/
        while(cur<nums.length){
            if (nums[cur]>0&&nums[cur]<=nums.length&&nums[nums[cur]-1]!=nums[cur]){ //进入if说明数没有放在正确的位置
                swap01(nums,nums[cur]-1,cur);
                continue; //交换来的数可能也不在正确的位置，因此cur指针不能动，下一轮循环还需要继续研究交换来的数
            }
            cur++; //走到这一步必然没有进入if，没有进入if说明nums[cur]放在正确的位置，下一轮循环继续研究下一个位置即cur++;
        }
        /*step2：从index=0开始，找第一个缺失的数*/
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=i+1){ /**err：if条件语句写错。索引0处的应该是1，因此nums[i]和i+1比较*/
                return i+1; /**err：返回i+1。。求的是第一个缺失的整数，而不是索引*/
            }
        }
        return nums.length+1; /**err：如果所有的数都在正确位置比如：[1,2,3]。这种情况应该返回4*/
    }

    private void swap01(int[] nums, int num, int cur) {
        int tmp = nums[num];
        nums[num] = nums[cur];
        nums[cur] = tmp;
    }


}
