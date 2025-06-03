package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//15，42
public class _02DoublePoint {
    /*
    * 283.给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    * 【升级题目】：熟悉颜色分类，75。。。。
    *       这种题目的思路就是：假想left指向左边界的最后一个；right指向右边界的第一个；cur表示当前
    *    研究到的位置
    * */
    public void moveZeroes(int[] nums) {
        int left = 0;
        int cur = 0;
        while(cur<nums.length){
            if(nums[cur]!=0){
                nums[left++] = nums[cur++];
            }else
                cur++;
        }
        /**err：需要将left及后续的都置零*/
        for (int j=left;j< nums.length;j++){
            nums[j] = 0;
        }
    }

    /*
    * 11.给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
    *    成的，柱子之间是没有间隙的。
    * */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int result = Integer.MIN_VALUE;
        while(left<right){
            int cur = (right-left)*Math.min(height[left],height[right]);
            result = Math.max(cur,result);
            if(height[left]<height[right]){
                left++;
            }else{
                right--; //right指针需要减1.
            }
        }
        return result;
    }

    /*
    * 15.给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    * 【】：关键的步骤在于双指针 过程中，如何跳过所有相同的元素。
    * */
    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i=0;i< nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]){ /**err：用if不用while，while里面使用continue就只是回到了外层循环*/
                continue;
            }
            int cur = nums[i];
            int left = i+1;
            int right = nums.length-1;
            while (left<right){
                int curRes = cur+nums[left]+nums[right];
                if(curRes<0){
                    /*>0，<0的时候不去重也可以，但是=0必须去重。
                    * 比如：这里是<0，则下面的两句使用哪一句都可以，后面的一句去重只是把去重操作提前了*/
//                    left++;
                    while (left<right && nums[left]==nums[++left]); /*关键语句：跳过所有相等的值*/
                }else if(curRes>0){
//                    right--;
                    while(left<right && nums[right]==nums[--right]);
                }else {
//                    ArrayList<Integer> curLev = new ArrayList<>();
//                    curLev.add(nums[i]);
//                    curLev.add(nums[left]);
//                    curLev.add(nums[right]);
//                    res.add(curLev);
                    res.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right]))); /**err：使用这个添加list方便*/
                    while (left<right && nums[left]==nums[++left]);
                    while (left<right && nums[right]==nums[--right]);
                }
            }
        }
        return res;
    }

    /*
    * 42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    * */
    public int trap(int[] height) {
        int res = 0;
        if(height.length<=2){
            return res;
        }
        int leftMax = height[0];
        int rightMax = height[height.length-1];
        int result = 0;
        int left = 1;
        int right = height.length-2;

        while(left<right){

            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
        }
        return res;
    }


    public static void main(String[] args) {
        List<List<Integer>> lists = new _02DoublePoint().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }
}


/**
 * 1. 双指针问题汇总
 * */