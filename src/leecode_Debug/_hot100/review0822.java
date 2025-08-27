package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/8/22 10:44
 */
public class review0822 {

    /*
     * 438.给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * */
//    public List<Integer> findAnagrams(String s, String p) {
//
//    }

    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
//    public int coinChange(int[] coins, int amount) {
//        int[] dp = new int[amount + 1];
//        for (int i = 0; i < coins.length; i++) {
//            for (int j = 0; j <=amount; j++) {
//                if (j>=coins[i]){
//                    dp[j] = Math.                }
//            }
//        }
//    }


    //最长回文子串
//    public String longestPalindrome(String s) {
//
//    }


    /**
     * 复原IP地址，为什么不对？？
     */
    List<String> res;
    public List<String> restoreIpAddresses(String s) {
        res = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        restoreIpAddresses(s,sb,0,0);
        return res;
    }

    private void restoreIpAddresses(String s,StringBuilder sb, int index, int num) {
        if (index>=s.length()){
            return;
        }
        if (num == 3 && isValid(s.substring(index))) {
            res.add(new String(sb));
            return;
        }
        if (num==3) return;
        for (int i = index+1; i < s.length(); i++) {
            if (isValid(s.substring(index,i))){
                sb.insert(i+num,'.');
                restoreIpAddresses(s,sb,i+1,num+1);
                sb.deleteCharAt(i+num);
            }
        }
    }

    private boolean isValid(String substring) {
        if (substring.length()>3) return false;
        if (substring.length()>1&&substring.startsWith("0")) return false;
        if (Integer.valueOf(substring)<=255) return true;
        return false;
    }


    public String reverseWords(String s) {
        String str = s.trim();
        StringBuilder sb = new StringBuilder(str);
        int l = 0,r = s.length()-1;
        while (l<r){
            char c = sb.charAt(l);
            char c1 = sb.charAt(r);
            sb.deleteCharAt(l);
            sb.insert(l,c);
            sb.deleteCharAt(r);
            sb.insert(r,c1);
            l++;
            r--;
        }
        return sb.toString();
    }

/**
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 */
    /*5
    给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
    //    public String longestPalindrome(String s) {
    //
    //    }



    /*215
    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
//    public int findKthLargest(int[] nums, int k) {
//
//    }



    /*53
    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组是数组中的一个连续部分。
     */
//    public int maxSubArray(int[] nums) {
//
//    }


    /*92
    给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     */
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//
//    }



    /*93
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     */
//    public List<String> restoreIpAddresses(String s) {
//
//    }



    /*151
    给你一个字符串 s ，请你反转字符串中 单词 的顺序。

单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。

注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     */
//    public String reverseWords(String s) {
//
//    }



    /*78
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
//    public List<List<Integer>> subsets(int[] nums) {
//
//    }



    /*8

     */
//    public int myAtoi(String s) {
//
//    }

    /*470
    给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。

你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。

每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
    * */
    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     * @return a random integer in the range 1 to 7
     */
//    public int rand10() {
//
//    }


    //39
    /*
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//
//    }


    //122
    /*
    给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

返回 你能获得的 最大 利润 。
     */
//    public int maxProfit(int[] prices) {
//
//    }


    /*112
    给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。

叶子节点 是指没有子节点的节点。
     */
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//
//    }

    /*113
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。
     */
//    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//
//    }


    /*179
    给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     */
//    public String largestNumber(int[] nums) {
//
//    }

    /*718
    给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     */
//    public int findLength(int[] nums1, int[] nums2) {
//
//    }


    /*14
    编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。
     */
//    public String longestCommonPrefix(String[] strs) {
//
//    }

}
