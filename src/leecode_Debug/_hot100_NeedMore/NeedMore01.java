package leecode_Debug._hot100_NeedMore;

/**
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
 *  84. 柱状图中最大的矩形
 *  394. 字符串解码
 *  4. 寻找两个正序数组的中位数
 *  131. 分割回文串
 *  22. 括号生成
 */

import leecode_Debug.top100.ListNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: Zhou
 * @date: 2025/6/29 19:50
 * 【合并区间】将List转换为数组：return res.toArray(new int[res.size()][]);
 */
public class NeedMore01 {

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
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> Dqueue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!Dqueue.isEmpty()&&nums[i]>=nums[Dqueue.getLast()]){
                Dqueue.pollLast();
            }
            Dqueue.offerLast(i);
        }
        res[0] = nums[Dqueue.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!Dqueue.isEmpty()&&nums[i]>=nums[Dqueue.getLast()]){
                Dqueue.pollLast();
            }
            Dqueue.offerLast(i);
            if (Dqueue.peekFirst()==i-k){
                Dqueue.pollFirst();
            }
            res[i-k+1] = nums[Dqueue.peekFirst()];
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


    /*238.
    * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素
    * 的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    * */
    public int[] productExceptSelf(int[] nums){
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1]*nums[i-1];
        }

        int post = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            res[i] *= post;
            post *= nums[i];
        }

        return res;
    }

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
        int left = 0,cur = 0,right = nums.length-1;
        while (cur<=right){
            if (nums[cur]<1){
                swap(nums,left++,cur++);
            } else if (nums[cur]>1) {
                swap(nums,cur,right--);
            }else {
                cur++;
            }
        }
    }
    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }


    //下一个排列
    public void nextPermutation(int[] nums) {

    }


    //k个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy,end = dummy;
        while (end.next!=null){
            for (int i = 0; i < k&&end!=null; i++) {
                end = end.next;
            }
            if (end==null) return dummy.next;

            ListNode curStart = pre.next;
            ListNode nextStart = end.next;

            end.next = null;
            pre.next = reverseK(curStart);
            curStart.next = nextStart;

            pre = curStart;
            end = curStart;
        }
        return dummy.next;
    }
    private ListNode reverseK(ListNode curStart) {
        ListNode pre = null,cur = curStart;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

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
    class LRUCache {
        class DouListNode{
            int value;
            DouListNode pre;
            DouListNode next;
            public DouListNode(int value) {
                this.value = value;
            }
        }

        int capacity;
        int size;
        DouListNode head;
        DouListNode tail;
        HashMap<Integer,DouListNode> map = new HashMap<Integer,DouListNode>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new DouListNode(-1);
            tail = new DouListNode(-1);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            DouListNode node = map.get(key);
            if (node==null){
                return -1;
            }else {
                removeNode(node);
                addNodeToHead(node);
                return node.value;
            }
        }

        public void put(int key, int value) {
            DouListNode node = map.get(key);
            if (node==null){
                DouListNode newNode = new DouListNode(key);
                map.put(key,newNode);
                addNodeToHead(newNode);
                size++;
                if (size>this.capacity){
                    DouListNode tailReal = tail.pre;
                    removeNode(tailReal);
                    map.remove(tailReal.value);
                }
            }else {
                node.value = key;
                removeNode(node);
                addNodeToHead(node);
            }
        }

        private void removeNode(DouListNode node) {
            DouListNode pre = node.pre;
            DouListNode next = node.next;
            pre.next = next;
            next.pre = pre;
        }

        private void addNodeToHead(DouListNode newNode) {
            DouListNode next = head.next;
            head.next = newNode;
            newNode.next = next;
            next.pre = newNode;
            newNode.pre = head;
        }
    }

    /*234.
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返
     * 回 true ；否则，返回 false 。*/
    public boolean isPalindrome(ListNode head) {
        ListNode midNode = findMid(head);
        ListNode nextHead = reverse(midNode);
        while (nextHead!=null){
            if (nextHead.val!=head.val){
                return false;
            }
            nextHead = nextHead.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverse(ListNode midNode) {
        ListNode pre = null,cur = midNode;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        return mergeKLists(lists,0,lists.length-1);
    }
    public ListNode mergeKLists(ListNode[] lists,int l,int r){
        if (l==r) return lists[l];
        int mid = l+(r-l)/2;
        ListNode left = mergeKLists(lists, l, mid - 1);
        ListNode right = mergeKLists(lists, mid, r);
        return mergeTwo(left,right);
    }

    private ListNode mergeTwo(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }




    /*394.
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注
    意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的
    输入。
    * */
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi  = 0;
        LinkedList<String> stringStack = new LinkedList<>();
        LinkedList<Integer> multiStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c>='0'&&c<='9'){
                multi = multi*10 + c-'0';
            } else if (c=='[') {
                stringStack.push(res.toString());
                multiStack.push(multi);
                multi = 0;
                res = new StringBuilder();
            } else if (c==']') {
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < multi; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stringStack.pop()+tmp);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }


    //最小栈
    class MinStack {
        Stack<Integer> all;
        Stack<Integer> min_stack;
        public MinStack() {
            all = new Stack<Integer>();
            min_stack = new Stack<>();
        }

        public void push(int val) {
            all.push(val);
            if (min_stack.isEmpty()||val<=min_stack.peek()){
                min_stack.push(val);
            }
        }

        public void pop() {
            Integer pop = all.pop();
            if (pop==min_stack.peek()){
                min_stack.pop();
            }
        }

        public int top() {
            return all.peek();
        }

        public int getMin() {
            return min_stack.peek();
        }
    }


    /*45.
    * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
    每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你
    * 可以跳转到任意 nums[i + j] 处:
    0 <= j <= nums[i]
    i + j < n
    返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
    * */
    public int jump(int[] nums) {
        int maxPosition = 0,curBound = 0,step = 0;
        for (int i = 0; i < nums.length-1; i++) { /**注意这里必须是nums.length-1，因为这个题目是正好跳到最后一位。如果跳到最后一个位置，则代码中maxPosition必然等于curBound*/
            maxPosition = Math.max(maxPosition,i+nums[i]);
            if (i==curBound){
                step++;
                curBound = maxPosition;
            }
        }
        return step;
    }


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
    public int maxProduct(int[] nums) {
        int preMax = 1,preMin = 1;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int curMax = Math.max(nums[i],Math.max(preMax*nums[i],preMin*nums[i]));
            preMin = Math.min(nums[i],Math.min(preMax*nums[i],preMin*nums[i]));
            preMax = curMax;
            res = Math.max(res,curMax);
        }
        return res;
    }

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
