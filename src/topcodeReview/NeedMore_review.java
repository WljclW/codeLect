package topcodeReview;

/**
 * 【need copy when use】
 * @author: Zhou
 * @date: 2025/6/21 16:23
 *  * 41. 缺失的第一个正数
 *  * 153. 寻找旋转排序数组中的最小值
 *  * 4. 寻找两个正序数组的中位数
 *  * 31. 下一个排列
 *  * 45. 跳跃游戏 II
 *  * 84. 柱状图中最大的矩形
 *  * 42. 接雨水
 *  * 438. 找到字符串中所有字母异位词
 *  287. 寻找重复数
 *  5. 最长回文子串
 *  152. 乘积最大子数组
 *  300. 最长递增子序列
 *  322. 零钱兑换
 *  45. 跳跃游戏 II
 *  101。的迭代法
 *  279. 完全平方数
 *  416. 等和子集问题
 *  394. 字符串解码
 *  4. 寻找两个正序数组的中位数
 *  131. 分割回文串
 *  22. 括号生成
 */

import leecode_Debug.top100.ListNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Zhou
 * @date: 2025/6/29 19:50
 * 【合并区间】将List转换为数组：return res.toArray(new int[res.size()][]);
 */
public class NeedMore_review {

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
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty()&&nums[i]>=nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        res[0] = nums[queue.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty()&&nums[i]>=nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i);
            if (queue.peekFirst().intValue()==i-k) queue.pollFirst();
            res[i-k+1] = nums[queue.peekFirst()];
        }
        return res;
    }

    /*
     * 76.给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int valid = 0;
        int start = -1,minLen = Integer.MAX_VALUE;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                map.put(c,map.getOrDefault(c,0)+1);
                if (map.get(c).intValue()==need.get(c).intValue()){
                    valid++;
                }
                while (valid==need.size()){
                    if (i-left+1<minLen){
                        start = left;
                        minLen = i-left+1;
                    }
                    char c1 = s.charAt(left++);
                    if (need.containsKey(c1)){
                        map.put(c1,map.get(c1)-1);
                        if (map.get(c1)<need.get(c1)){
                            valid--;
                        }
                    }
                }
            }
        }
        return start==-1?"":s.substring(start,start+minLen);
    }


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
            res[i] = res[i-1]*nums[i];
        }

        int post = 1;
        for (int i = nums.length-2; i >0; i--) {
            post *= nums[i+1];
            res[i] *= post;
        }
        return res;
    }

    /*73.
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和
     * 列的所有元素都设为 0 。请使用 原地 算法。
     * */
    public void setZeroes(int[][] matrix) {
        boolean firRow = false;
        boolean firCol = false;
        int m = matrix.length,n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0]==0){
                firCol = true;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (matrix[0][i]==0){
                firRow =  true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j]==0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0]==0||matrix[0][j]==0) matrix[i][j] = 0;
            }
        }

        if (firRow) Arrays.fill(matrix[0],0);
        if (firCol){
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /*4.
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
    * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length,n = nums2.length;
        if (m>n) return findMedianSortedArrays(nums2,nums1);

        int left = 0,right = m;
        while (left<=right){
            int nums1Mid = left+(right-left)/2;
            int nums2Mid = (m+n+1)/2-nums1Mid;

            int nums1Left = nums1Mid==0?Integer.MIN_VALUE:nums1[nums1Mid-1];
            int nums1Right = nums1Mid== m?Integer.MAX_VALUE:nums1[nums1Mid];
            int nums2Left = nums2Mid==0?Integer.MIN_VALUE:nums1[nums2Mid-1];
            int nums2Right = nums2Mid==n?Integer.MAX_VALUE:nums2[nums2Mid];

            if (nums1Left>nums2Right){
                right = nums1Mid-1;
            } else if (nums1Right < nums2Left) {
                left = nums1Mid+1;
            }else {
                if (((m+n)&1)==1){
                    return Math.max(nums1Left,nums2Left);
                }else {
                    return (Math.max(nums1Left,nums2Left)+
                            Math.min(nums1Right,nums2Right))/2.0;
                }
            }
        }
        return -1;
    }


    //颜色分类
    public void sortColors(int[] nums) {
        int left = 0,cur =0,right = nums.length-1;
        while (cur<=right){
            if (nums[cur]<1){
                swap11(nums,left++,cur++);
            } else if (nums[cur] > 1) {
                swap11(nums,cur,right--);
            }else {
                cur++;
            }
        }
    }

    private void swap11(int[] nums, int l, int r) {
        int tmp  =nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    //下一个排列
    public void nextPermutation(int[] nums) {
        int flag = -1;
        for (int i = nums.length-2; i >=0; i--) {
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }

        if (flag>=0){
            for (int i = nums.length-1; i > flag; i--) {
                if (nums[i]>=nums[flag]){
                    swap11(nums,i,flag);
                    break;
                }
            }
        }
        reverse11(nums,flag+1);
    }

    private void reverse11(int[] nums, int left) {
        int right = nums.length-1;
        while (left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }


    //k个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy,end = dummy;
        while (end.next!=null){
            for (int i = 0; i < k&&end!=null; i++) {
                end = end.next;
            }
            if (end.next!=null) break;

            ListNode nextStart = end.next;
            end.next = null;
            ListNode thisStart = pre.next;
            pre.next = reverse22(pre.next);
            thisStart.next = nextStart;

            pre = thisStart;
            end = thisStart;
        }
        return dummy.next;
    }

    private ListNode reverse22(ListNode head) {
        ListNode pre =null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre =cur;
            cur = next;
        }
        return pre;
    }

    /*148.
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode prevMid = findPrevMid(head);
        ListNode head2 = prevMid.next;
        ListNode right = sortList(head2);
        ListNode left = sortList(head);
        return mergeTTWo(left,right);
    }

    private ListNode mergeTTWo(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur =dummy;
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
        cur.next = left==null?right:left;
        return dummy.next;
    }

    private ListNode findPrevMid(ListNode head) {
        ListNode slow = head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /*146.
    请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    实现 LRUCache 类：
    LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
    * */
    class LRUCache {
        class DouNode{
            int key;
            int value;
            DouNode prev;
            DouNode next;
            public DouNode(){}
            public DouNode(int key,int value){
                this.key = key;
                this.value  =value;
            }
        }

        int size =0;
        HashMap<Integer,DouNode> map;
        DouNode head;
        DouNode tail;
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new DouNode();
            tail = new DouNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DouNode node = map.get(key);
            if (node!=null){
                moveToHead(node);
                return node.value;
            }
            return -1;
        }

        private void moveToHead(DouNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DouNode node) {
            DouNode headNext = head.next;
            headNext.next = node;
            node.next = headNext;
            headNext.prev = node;
            node.prev = head;
        }

        public void put(int key, int value) {
            DouNode node = map.get(key);
            if (node!=null){
                node.value = value;
                moveToHead(node);
            }else {
                DouNode newNode = new DouNode(key, value);
                size++;
                addToHead(newNode);
                if (size>this.capacity){
                    DouNode relTail = tail.prev;
                    removeNode(relTail);
                    size--;
                    map.remove(relTail.key);
                }
            }
        }

        private void removeNode(DouNode node) {
            DouNode next = node.next;
            DouNode prev = node.prev;
            prev.next = next;
            next.prev = prev;
        }
    }

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        if (lists.length==1) return lists[0];
        return mergeKLists(lists,0,lists.length-1);
    }

    private ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = left+(right-left)/2;
        ListNode leftList = mergeKLists(lists, left, mid);
        ListNode rightList = mergeKLists(lists, mid + 1, right);
        return mergeTwo11(leftList,rightList);
    }

    private ListNode mergeTwo11(ListNode left, ListNode right) {
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

        cur.next = left==null?right:left;
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
        int multi = 0;
        Stack<Integer> intStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c>='0'&&c<='9'){
                multi = multi*10 + c-'0';
            } else if (c == '[') {
                intStack.push(multi);
                stringStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                Integer num = intStack.pop();
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stringStack.pop()).append(tmp);
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }


    /*155.
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

实现 MinStack 类:

MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。
* */
    class MinStack {
        Stack<Integer> allStack;
        Stack<Integer> minStack;
        public MinStack() {
            allStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            allStack.push(val);
            if (minStack.isEmpty()||val<=minStack.peek()){
                minStack.push(val);
            }
        }

        public void pop() {
            Integer cur = allStack.pop();
            if (cur.intValue()==minStack.peek().intValue()){
                minStack.pop();
            }
        }

        public int top() {
            return allStack.peek();
        }

        public int getMin() {
            return minStack.peek();
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
        int right = 0,bound = 0;
        int step  =0;
        for (int i = 0; i < nums.length - 1; i++) {
            bound = Math.max(i+nums[i],bound);
            if (i==right){
                step++;
                right = Math.max(right,bound);
            }
        }
        return step;
    }

    public int jump_(int[] nums) {
        int right = 0,bound = 0;
        int cur = 0;
        int step = 0;
        while (cur<nums.length-1){
            bound = Math.max(bound,cur+nums[cur]);
            if (cur==right){
                right = bound;
                step++;
            }
        }
        return step;
    }


    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
        dp[0]  =0;
        for (int i = coins[0]; i <= amount; i++) {
            if (i%coins[0]==1) dp[i] = dp[i-coins[0]]+1;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j-coins[i]]+1,dp[j]);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }


    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int preMin = nums[0],preMax= nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(Math.min(preMin*nums[i],preMax*nums[i]),nums[i]);
            int curMax = Math.max(Math.max(preMin*nums[i],preMax*nums[i]),nums[i]);
            res = Math.max(res,curMax);
            preMin = curMin;  /**注意：这里”不能再进行比较取值了“，因为”子数组必须是连续的“，因此到i+1位置研究的时候前面的”乘积最大值“，”乘积最小值“必须是以”index=i位置的数“结尾的*/
            preMax = curMax;
        }
        return res;
    }

    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        int size = 0;
        for (int num:nums){
            int left = 0,right = size-1;
            while (left<=right){
                int mid = left+(right-left)/2;
                if (nums[mid]>=num){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }
            dp[left] = num;
            if (left==size) size++;
        }
        return size;
    }


    /*279.
    * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

       完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数
       * 自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。*/
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,n);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i*i <= n; i++) {
            for (int j = i*i; j <= n; j++) {
                dp[j] = Math.min(dp[j-i*i]+1,dp[j]);
            }
        }
        return dp[n];
    }


    //最长回文子串
    public String longestPalindrome(String s) {
        if (s==null||s.length()==0) return "";
        if (s.length()==1) return s;
        StringBuilder tmp = new StringBuilder("#");
        for (char c:s.toCharArray()){
            tmp.append(c).append("#");
        }
        String str = tmp.toString();

        int[] dp = new int[str.length()];
        int center = 0,r = 0;
        int start = -1,maxLen = 0;
        for (int i = 0; i < str.length(); i++) {
            int mirror = 2*center-i;
            if (i<r){   /**这里需要带等于吗？？？*/
                dp[i] = Math.min(dp[mirror],r-i);
            }

            int left = i-dp[i]-1,right = i+dp[i]+1;
            while (left>=0&&right<str.length()&&str.charAt(left)==str.charAt(right)){
                dp[i]++;
                left--;
                right++;
            }

            if (i+dp[i]>r){
                r = i+dp[i];
                center = i;
            }
            if (dp[i]>maxLen){
                maxLen = dp[i];
                start = (i-maxLen)/2;
            }
        }
        return s.substring(start,start+maxLen);
    }


    /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
    public int findDuplicate(int[] nums) {
        int slow = nums[0],fast = nums[0];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = nums[0];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
