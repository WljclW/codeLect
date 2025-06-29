package leecode_Debug._hot100;

import java.util.HashMap;
import java.util.LinkedList;

public class _03subString {
    /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
    /**
     * 【思路】使用双端队列，队列内的数严格单调减（换言之，只要当前的数nums[i]"大于等于"双端队列最后一个
     * 数nums[m]，则双端队列尾部的nums[m]永远不可能是最大值了）。。
     *      1。先将前面的k个数组添加进双端队列，此时会生成0位置的信息；
     *      2. for循环内依次将剩下的元素添加进双端队列；每一轮循环需要确保双端队列头的位置还在窗口内，并
     *  生成对应位置的信息
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1]; //比如nums长度为3，窗口大小为3，则res中应该包含一个数
        LinkedList<Integer> queue = new LinkedList<>();
        /*step1：先将窗口的大小扩大为k，结束后生成res[0]的信息*/
        for (int i = 0; i < k; i++) {
            //队列内要求单调减
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) { /**等于的时候队尾也要出队，比如"2,4"（2,4位置的值都是7）则2位置的7永远不会成为窗口内的最大值了*/
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        res[0] = nums[queue.peekFirst()];
        /*step2：对于剩下的元素依次进入。【注意】由于窗口的大小固定k，因此要注意判断队列头的元素是不是已经出窗口了*/
        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) { //①先将当前位置的元素入栈
                queue.pollLast();
            }
            queue.offerLast(i);
            if (queue.peekFirst() == i - k) { //②判断队头元素是不是变得不合法（即当前队列头的索引是不是已经出窗口了），不合法时弹出
                queue.pollFirst();
            }
            res[i - k + 1] = nums[queue.peekFirst()]; //③这一轮会生成”i-k+1位置“的信息
        }
        return res;
    }


    /*
    * 76.给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
    * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    * */
//    public String minWindow(String s, String t) {
//
//    }

    /*560.
    * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
    /**
     * 【解题关键】计算每一个位置的前缀和pre，放入map前判断是不是有pre-k。map中存放的是
     *      每一个前缀和值 对应的数量
     * */
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int pre = 0;
        map.put(0,1);   /**err：需要先放入pre为0的key-value*/
        int res = 0;
        for (int num:nums){
            //先更新前缀和，然后看前缀和pre-k的数量，有的话累加到res.（pre-k到当前位置的子数组和就是k）
            pre += num;
            res += map.getOrDefault(pre-k,0);
            map.put(pre,map.getOrDefault(pre,0)+1); //将前缀和为pre对应的子数组数量加1.
        }
        return res;
    }
}
