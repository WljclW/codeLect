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
    /**
     * 【关键】先排序；然后从左向右遍历位置cur，每到一处位置cur，设置left、right指针，根据当前三个位置数的和
     *      与0的关系，来移动left或者right指针。
     * 【去重】必须去重的地方有两个————
     *      ①遍历到位置cur，如果它和前面的数相等即nums[cur-1]==nums[cur]，说明cur-1位置的时候已经研究过
     *  了，cur位置需要跳过。【举例】：{-2，-2，-1，0，2}如果0位置的-2作为cur已经研究过了，cur来到1时就要跳
     *  过来到2位置的-1。。如果不跳过就会出现相同的组合：[-2(索引0的-2)，0，2]、[-2(索引1的-2)，0，2]，实
     *  际上是同一种解。
     *      ②如果找到一个可行解即nums[cur]+nums[left]+nums[right]==0，此时需要移动left指针和right指针，
     *  移动时也必须跳过所有相同的元素。【举例】：{-2，-1，0，0，1，1，2，2}在cur=0、left=2、right=7时
     *  得到了一组可行解[-2,0,2]....此时需要left++，right--，left来到了索引3处的0，right指针指向了索
     *  引6处的2，此时还是一个可行解[-2,0,2]，如果这种情况不排重就会导致添加了重复解。
     *      综上，这两个地方必须要去重。剩下的>0、<0的情况下去重不去重都是可以的（此时去重仅仅是把排除
     *  操作提前了，本质上在于他们并不会影响结果）。
     * */
    //写法1
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
//                    left++; //这麽写表示不去重，也是可以的
                    while (left<right && nums[left]==nums[++left]); /*关键语句：跳过所有相等的值*/
                }else if(curRes>0){
//                    right--; //这麽写表示不去重，也是可以的
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


    //另一种写法其实思想是一样的。。。主要区别在于">、<"不会进行排重
    public List<List<Integer>> threeSum02(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            if (i>0&&nums[i]==nums[i-1]) continue;  /**这里必须进行去重*/
            int left = i+1,right = nums.length-1;
            while(left<right){
                int cur = nums[i] + nums[left] + nums[right];
                if (cur>0){
                    right--;
                }else if (cur<0){
                    left++;
                }else{
                    LinkedList<Integer> ele = new LinkedList<>();
                    ele.add(nums[i]);
                    ele.add(nums[left]);
                    ele.add(nums[right]);
                    res.add(ele);
                    left++;
                    right--;
                    /**得出一种可行解，也必须进行去重*/
                    while(left<right&&nums[left]==nums[left-1]) left++;
                    while(left<right&&nums[right]==nums[right+1]) right--;
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