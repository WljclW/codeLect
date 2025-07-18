package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode; /**
 * @author: Zhou
 * @date: 2025/6/21 16:23
 *  31. 下一个排列
 *  287. 寻找重复数
 *  5. 最长回文子串
 *  152. 乘积最大子数组
 *  300. 最长递增子序列
 *  322. 零钱兑换
 *  45. 跳跃游戏 II
 *  279. 完全平方数
 *  416. 等和子集问题
 *  84. 柱状图中最大的矩形
 *  394. 字符串解码
 *  4. 寻找两个正序数组的中位数
 *  131. 分割回文串
 *  22. 括号生成
 */
/**
 * @author: Zhou
 * @date: 2025/6/29 19:50
 * 【合并区间】将List转换为数组：return res.toArray(new int[res.size()][]);
 */
public class NeedMore {

    /*
     * 438.给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * */
//    public List<Integer> findAnagrams(String s, String p) {
//
//    }

    /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
//    public int[] maxSlidingWindow(int[] nums, int k) {}

    /*
     * 76.给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
//    public String minWindow(String s, String t) {
//
//    }


    /*238.
    * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素
    * 的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    * */
//    public int[] productExceptSelf(int[] nums){}

    /*73.
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和
     * 列的所有元素都设为 0 。请使用 原地 算法。
     * */
    public void setZeroes(int[][] matrix) {

    }

    /*4.
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
    * */
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//    }


    //颜色分类
    public void sortColors(int[] nums) {

    }


    //下一个排列
    public void nextPermutation(int[] nums) {

    }


    //k个一组翻转链表
//    public ListNode reverseKGroup(ListNode head, int k) {
//
//    }

    /*148.
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
//    public ListNode sortList(ListNode head) {
//
//    }


    /*146.
    请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    实现 LRUCache 类：
    LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
    * */
//    class LRUCache {
//
//        public LRUCache(int capacity) {
//
//        }
//
//        public int get(int key) {
//
//        }
//
//        public void put(int key, int value) {
//
//        }
//    }

    /*234.
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返
     * 回 true ；否则，返回 false 。*/
//    public boolean isPalindrome(ListNode head) {}

    /*138复制链表*/
//    public Node copyRandomList(Node head) {}

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /*23.
    * 给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。*/
//    public ListNode mergeKLists(ListNode[] lists) {}




    /*394.
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注
    意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的
    输入。
    * */
//    public String decodeString(String s) {}


    //最小栈
    //    class MinStack {
//
//        public MinStack() {
//
//        }
//
//        public void push(int val) {
//
//        }
//
//        public void pop() {
//
//        }
//
//        public int top() {
//
//        }
//
//        public int getMin() {
//
//        }
//    }


    /*45.
    * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
    每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你
    * 可以跳转到任意 nums[i + j] 处:
    0 <= j <= nums[i]
    i + j < n
    返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
    * */
//    public int jump(int[] nums) {}


    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
//    public int coinChange(int[] coins, int amount) {}


    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
//    public int maxProduct(int[] nums) {}

    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
//    public int lengthOfLIS1(int[] nums) {}


    /*279.
    * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

       完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数
       * 自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。*/
//    public int numSquares(int n) {
//
//    }


    //最长回文子串
//    public String longestPalindrome(String s) {
//
//    }


    /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
//    public int findDuplicate(int[] nums) {}
}
