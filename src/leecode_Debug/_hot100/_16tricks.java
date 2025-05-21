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
    public void nextPermutation(int[] nums) {
    }


    public static void main(String[] args) {
        int[] arr = {0,0,0,2,0,2,2,0};
        new _16tricks().sortColors(arr);
        System.out.println(Arrays.toString(arr));

        int[] par = {1, 2, 3, 2};
        int duplicate = new _16tricks().findDuplicate(par);
        System.out.println(duplicate);
    }
}
