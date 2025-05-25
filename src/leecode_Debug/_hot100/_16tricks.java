package leecode_Debug._hot100;

import java.util.Arrays;

/**
 * 25.4.22
 * */
public class _16tricks {
    /*136.
    * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均
    * 出现两次。找出那个只出现了一次的元素。
    你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。*/
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i:nums){
            res ^= i;
        }
        return res;
    }

    /*169.
    题解：https://leetcode.cn/problems/majority-element/solutions/2362000/169-duo-shu-yuan-su-mo-er-tou-piao-qing-ledrh/
    * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现
    * 次数 大于 ⌊ n/2 ⌋ 的元素。
    你可以假设数组是非空的，并且给定的数组总是存在多数元素。*/
    /**
     * 整体的思想：
     *      假设数组中的数进行投票，每一个数都投给自己一票，不是自己的话就-1，则最终多数元素的票数绝对是正值。
     * 解题关键：
     *      每一次碰到票数是0，就更新标记值（候选值），然后针对这个数投票
     */
    public int majorityElement(int[] nums) {
        int cur = 0; //标记当前的票数
        int flag = -1; //标记值，初始值无所谓
        for (int i:nums){
            if (cur==0){ //如果票数为0，则更新标记值
                flag = i;
            }
            cur = flag==i?cur+1:cur-1; //票数不为0则投票，只要不是标记值，票数-1
        }
        return flag;
    }

    /*75.
    * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使
    * 得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    必须在不使用库内置的 sort 函数的情况下解决这个问题。*/
    public void sortColors(int[] nums) {
        int left = 0,right = nums.length-1;
        int cur = 0;
        while(cur<=right) {
            if (nums[cur] < 1) { /*如果是0，交换到left指向位置，*/
                swap(nums,left++,cur++);
            } else if (nums[cur] == 1) {
                cur++;
            } else { //交换后cur交换来的数还没有比较，所以cur指针不能动
                swap(nums,right--,cur);
            }
        }
    }

    /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
    public int findDuplicate(int[] nums) {
        int res = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1)
                i++;
            else {
                if (nums[nums[i] - 1] != nums[i]) {
                    swap(nums, nums[i] - 1, i);
                } else {
                    return nums[i];
                }
            }
        }

        return res;
    }

    public void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /*31.下一个排列*/
    /**
     * 【如果有下一个更大的排列，则做法分为三步】
     *      1. 从最后一个数倒着找，找到第一次升序的位置。即数组元素满足nums[i]<nums[i+1]
     *      2. 从最后一个数倒着找，找第一个大于nums[i]的数。。【注意】这里一定会找到，因为
     *  由第一步可以知道最起码nums[i+1]是大于nums[i]的。
     *      3. 倒序"从i+1位置往后的数"。。。【注意】此时i+1往后所有数一定是降序的，因为根据
     *  第一步原来就是降序的，然后第2步将一个数换成了比它小但比后面数大的nums[i]，因此此时还
     *  是严格降序的
     *  【如果没有下一个更大的排列，说明原始数组就是最大的排列，因此逆序整个数组】
     *
     * */
    public void nextPermutation(int[] nums) {
        int i = nums.length-2;
        while (i>=0){
            /*step1：先是从后面开始找到第一个升序的位置。。【即满足nums[i]<nums[i+1】*/
            if (nums[i]<nums[i+1]){
                /*step2：再从后面开始找第一个大于nums[i]的数，并交换。【一定会找到，因为至少nums[i+1]>nums[i]】*/
                for (int j=nums.length-1;j>=i+1;j--){
                    if (nums[j]>nums[i]){
                        int tmp = nums[j];
                        nums[j]= nums[i];
                        nums[i] = tmp;
                        break;
                    }
                }
                /*step3：将从i+1索引开始的子数组倒序*/
                reverse(nums,i+1);
                break;
            }
            i--; /**err：更新变量i*/
        }
        /*step4：如果原数组是严格降序的，说明此时就是最大的序列。。要改变为最小的序列————将原数组倒序*/
        if (i==-1)
            reverse(nums,0);
    }

    public void reverse(int[] nums,int l){
        int r = nums.length-1;
        while (l<r){
            int tmp = nums[r];
            nums[r--] = nums[l];
            nums[l++] = tmp;
        }
    }

    /**下一个排列的官方写法。。。官方的写法比较优雅且清晰*/
    public void nextPermutation_offical(int[] nums) {
        int i = nums.length - 2;
        //step1
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //step2，说明确实找到了升序的位置
        if (i >= 0) {
            int j = nums.length - 1;
            //倒着找第一个大于nums[i]的位置
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap1(nums, i, j);
        }
        //step3：倒序i+1开始的子数组。。并且还包含了特殊情况：整个数组已经是最大的排列了
        reverse(nums, i + 1);
    }

    public void swap1(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    public static void main(String[] args) {
//        int[] arr = {0,0,0,2,0,2,2,0};
//        new _16tricks().sortColors(arr);
//        System.out.println(Arrays.toString(arr));
//
//        int[] par = {1, 2, 3, 2};
//        int duplicate = new _16tricks().findDuplicate(par);
//        System.out.println(duplicate);


        int[] a = new int[]{3,2,1};
        new _16tricks().nextPermutation(a);
    }
}
