package leecode_Debug._hot100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class normalArr {
    /*53.
    * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元
    *   素），返回其最大和。
            子数组是数组中的一个连续部分。
    * */
    public int maxSubArray(int[] nums) {
        if(nums.length==1) return nums[0];
        int maxSum = Integer.MIN_VALUE;
        int curSum = nums[0];
        for(int i=1;i<nums.length;i++){
            if (nums[i]>0){
                curSum += nums[i];
            }else{
                curSum = nums[i];
            }
            maxSum = Math.max(curSum,maxSum);
        }

        return maxSum;
    }

    /*56.
    *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
    * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
    * */
//    public int[][] merge(int[][] intervals) {
//        LinkedList<int[]> res = new LinkedList<>();
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0] != o2[0]) return o1[0]-o2[0];
//                else return o1[1]-o2[1];
//            }
//        });
//        int last = Integer.MIN_VALUE;
//        int start = intervals[0][0];
//        for (int i=0;i<intervals.length;i++){
//            if(i+1<intervals.length&&intervals[i][1]<intervals[i+1][0]){
//                res.add(new int[]{start,last});
//                start = intervals[i+1][0];
//            }
//            last = Math.max(last,intervals[i][1]);
//        }
//    }


    /*189.
    * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
    * */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
    }


    /*238.
    * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素
    * 的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    * */
//    public int[] productExceptSelf(int[] nums) {
//
//    }

    /*41.
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
//    public int firstMissingPositive(int[] nums) {
//
//    }


}
