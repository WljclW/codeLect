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
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1]; //存放结果的数组
        //刚开始窗口先移动到指定的大小
        for (int i=0;i<k;i++){
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
                deque.pollLast();
            }
            deque.addLast(i);
        }
        res[0] = nums[deque.peek()];

        //i作为右边界不断的移动，每次移动i都能生成res中一个位置的结果
        for (int i=k;i<nums.length;i++){
            if (deque.peek()==i-k){ //1.如果当前的队头是左边界则出队列
                deque.poll();
            }
            //2.队列中需要满足单调递减，不满足时从“队尾”弹出元素
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
                deque.pollLast();
            }
            //3.队列满足条件时添加当前索引
            deque.addLast(i);
            //4.每次右边界移动一次，总能生成一个位置的信息
            res[i-k+1] = nums[deque.peek()];
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
        map.put(0,1);
        int res = 0;
        for (int num:nums){
            //先更新前缀和，然后看前缀和pre-k的数量，有的话累加到res.（pre-k到当前位置的子数组和就是k）
            pre += num;
            if (map.containsKey(pre-k)){
                res += map.get(num-k);
            }
            map.put(pre,map.getOrDefault(pre,0)+1);
        }
        return res;
    }
}
