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
    /**到达任何一个数有两种选择：要麽和前面的数组成子数组；要麽自己单独成一个数组。
     *      两种选择到底采用哪一个呢？取最大的那个。
     *      那到底最大子数组和是多少呢？使用一个外面的变量来标记,每计算出一个位置的值时更新。*/
    public int maxSubArray(int[] nums) {
        if(nums.length==1) return nums[0];
        int maxSum = Integer.MIN_VALUE; //记录最大子数组和
        int curSum = 0; //记录当前位置 决策出的子数组和
        for(int i=0;i<nums.length;i++){
            curSum = Math.max(nums[i],curSum+nums[i]); /*nums[i]独自成一队；cur+nums[i]和前面的部分子数组组成新的子数组*/
            maxSum = Math.max(curSum,maxSum); /*更新找到的最大子数组和*/
        }
        return maxSum;
    }

    /*56.
    *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
    * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
    * */
    /**这个题的关键：将intervals中的区间先加进结果集(此时默认加在最后一位)，然后往后判断来更新结果集
     *      中最后一个数组的结尾界线。【注意】如果不是先将最后一个元素加进结果集，直接研究会显得很复杂，
     *      逻辑上容易乱
     * 判断的标准：
     *      如果下一个区间的开始值 小于 结果集最后一个元素的结尾界限，比如：[1,4],[2,5]。则将结果集
     *  中最后一个数组的结尾更新为4和5的最大值；
     *      否则的话说明下一个区间的开始值 大于 结果集最后一个元素的结尾界限。说明这两个区间没有公共
     *  部分，因此需要将当前区间放进去成立一个新的区间。比如：[1,3],[5,7]*/
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                else return o1[1] - o2[1];
            }
        });
        res.add(intervals[0]); /*关键步骤：先将区间加到结果集里面，然后再利用后续元素更新这个元素的结尾界限*/
        for (int i=1;i<intervals.length;i++){
            int[] cur = intervals[i];
            if(res.getLast()[1]>=cur[0]){
                res.getLast()[1] = Math.max(res.getLast()[1],cur[1]);
            }else{
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    /*189.
    * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
    * */
    public void rotate(int[] nums, int k) {

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
     *    来计算了。。。。后缀积也是一样的道理*/
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
     * 思路：
     *     第一次正着遍历原始数组nums,res数组位置i存放它的前缀积，遍历到末尾位置。。。。然后接着反序遍历结果数组，
     *  利用局部变量来记录当前索引j的后缀乘积，这样的话倒着遍历的时候每到一个位置，“局部变量”*“结果集的当
     *  前位置值”就是该位置的结果*/
    public int[] productExceptSelf(int[] nums){
        if(nums.length==1) return nums;
        int[] res = new int[nums.length];
        res[0] = 1;
        int q = 1;
        for (int i=1;i<nums.length;i++){
            res[i] = res[i-1]*nums[i-1];
        }
        for (int j=nums.length-2;j>=0;j--){ /**err：等于0的时候也需要计算。否则结果数组的第一个数是1*/
            q *= nums[j+1];
            res[j]*=q;
        }
        return res;
    }





    /*41.
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            /**while会不断的将i位置的元素放到正确的位置....
             * 【注意】
             *      1.虽然交换但是i位置不能变，因为换过来的数还需要继续判断，这就是
             * 为什么这里是while循环。。(跟"颜色交换(荷兰国旗类似)"，交换后不能移动cur指针)
             *      2.与"小于 等于 大于"将数组的数分开有异曲同工，如果当前遍历的位置
             * 的数发现大于flag需要换到大于区域，因此需要继续判断这个"从大于区域换来
             * 的数"应该放到哪里————也就意味着swap之后不能变换当前研究的索引cur。
             *      3.一旦出while循环就表示：位置i位置的元素放到了正确的位置，并且所
             * 有换过来的元素也都放到了正确的位置*/
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        //经过上面的步骤理论上i位置存放的就是数字i+1.比如0位置存放1，1位置存放2....
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return 0;
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
