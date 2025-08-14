package leecode_Debug._hot100;

import java.util.LinkedList;

/**
 * @author mini-zch
 * @date 2025/8/14 14:19
 */
public class review_part_01 {
    /*316
    给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     */
    public String removeDuplicateLetters(String s) {
        int[] flags = new int[26];  /**官方这里会使用一个布尔数组记录栈或队列出现的字符，不使用也能判断，但是时间复杂度高*/
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            flags[c-'a'] = i;
        }

        LinkedList<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!deque.contains(c)){
                while (c<deque.peekLast()&&flags[deque.peekLast()-'a']>i){
                    deque.pollLast();
                }
                deque.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c:deque){
            sb.append(c);
        }
        return sb.toString();
    }


    /*1004
    给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0,right = 0;
        int zeroCount = 0;
        int res =0;
        while (right<nums.length){
            if (nums[right++]==0){
                zeroCount++;
            }
            while (zeroCount>k){
                int leftNum = nums[left++];
                if (leftNum==0){
                    zeroCount--;
                }
            }
            res = Math.max(res,right-left);
        }
        return res;
    }


    //581
    public int findUnsortedSubarray(int[] nums) {
        int start = -1, end = -2;
        int maxNum = nums[0], minNum = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < maxNum) {
                end = i;
            }
            maxNum = Math.max(maxNum, nums[i]);
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > minNum) {
                start = i;
            }
            minNum = Math.min(minNum, nums[i]);
        }
        return end - start + 1; /**【注】start、end的初始值就包含了“原数组就完整有序”的情况。*/
    }


    /*96. 不同的二叉搜索树
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            for (int j = 0; j < n; j++) {
                dp[i] = dp[i-1-j]*dp[j];
            }
        }
        return dp[n];
    }
}
