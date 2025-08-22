package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

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

    /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty()&&nums[deque.peekLast()]<=nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        res[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty()&&nums[i]>=nums[deque.peekLast()]){
                deque.pollLast();
            }
            if (deque.peekFirst().intValue()==i-k){
                deque.pollFirst();
            }
            deque.offerLast(i);
            res[i-k+1] = nums[deque.peekFirst()];
        }
        return res;
    }

    /*
     * 76.给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
    public String minWindow(String s, String t) {
        if (s.length()<t.length()) return "";
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int left  =0,valid = 0;
        int start = -1,len = Integer.MAX_VALUE;
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c).intValue()==need.get(c).intValue()){
                    valid++;
                }
            }
            while (valid==need.size()){
                if (i-left+1<len){
                    start = left;
                    len = i-len+1;
                }
                char c1 = s.charAt(left++);
                if (need.containsKey(c1)){
                    window.put(c1, window.get(c1)-1);
                    if (window.get(c1)<need.get(c1)){
                        valid--;
                    }
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
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
            res[i] = res[i-1]*nums[i-1];
        }

        int post = 1;
        for (int i = nums.length-1; i >=0; i++) {
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
        int m =  matrix.length,n = matrix[0].length;
        boolean firRow = false, firCol = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i]==0){
                firRow = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0]==0){
                firCol = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j]==0){
                    matrix[i][0]=matrix[0][j]=0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0]==0||matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }
        if (firRow){
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
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
        if (nums1.length>nums2.length) return findMedianSortedArrays(nums2,nums1);
        int l = 0,r = nums1.length;
        while (l<=r){
            int index1 = l+(r-l)/2;
            int index2 = (nums1.length+ nums2.length+1)/2-index1;

            int nums1L = index1==0?Integer.MIN_VALUE:nums1[index1-1];
            int nums1R = index1==nums1.length?Integer.MAX_VALUE:nums1[index1];
            int nums2L = index2==0?Integer.MIN_VALUE:nums1[index2-1];
            int nums2R = index2==nums2.length?Integer.MAX_VALUE:nums2[index2];

            if (nums1L<=nums2R&&nums2L<=nums1R){
                if (((nums1.length+nums2.length)&1)==1){
                    return Math.max(nums1L,nums2L);
                }else {
                    return (Math.max(nums1L,nums2L)+Math.min(nums1R,nums2R))/2.0;
                }
            } else if (nums1L > nums2R) {
                r = index1-1;
            }else {
                l = index1+1;
            }
        }
        return -1;
    }


    //颜色分类
    public void sortColors(int[] nums) {
        int left = 0,cur = 0,right = nums.length-1;
        while (cur<=right){
            if (nums[cur]==0){
                swap1(nums,left++,cur++);
            } else if (nums[cur] == 1) {
                cur++;
            }else {
                swap1(nums,right--,cur);
            }
        }
    }

    private void swap1(int[] nums, int i, int cur) {
        int tmp = nums[i];
        nums[i] = nums[cur];
        nums[cur] = tmp;
    }


    //下一个排列
    public void nextPermutation(int[] nums) {
        int flag = -1;
        for (int i = nums.length-2; i >=0 ; i--) {
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }

        if (flag>=0){      /**【说明】这里添加一个if判断flag的值，优化代码的逻辑，即使需要直接执行最后一行也不用写一段特殊逻辑*/
            for (int i = nums.length-1;i>flag; i--) {
                if (nums[i]>nums[flag]){
                    int tmp = nums[i];
                    nums[i] = nums[flag];
                    nums[flag] = tmp;
                }
            }
        }
        reverse2(nums,flag+1,nums.length-1); /**【说】如果整个数组是完整的降序，则falg就是初始值-1，从flag+1开始翻转数组；如果有找到flag，则从flag的下一步开始翻转也没错*/
    }

    private void reverse2(int[] nums, int l, int r) {
        while (l<r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }


    //k个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy,end = dummy;
        while (end.next!=null){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end==null) break;

            ListNode start = pre.next;
            ListNode nextStart = end.next;
            pre.next = reverse(start);
            start.next = nextStart;

            pre = start;
            end = start;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode start) {
        ListNode pre = null,cur = start;
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
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode mid = findMid(head);
        ListNode l = sortList(head);
        ListNode r = sortList(mid);
        return mergeTwo(l,r);
    }

    private ListNode mergeTwo(ListNode l, ListNode r) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (l!=null&&r!=null){
            if (l.val<r.val){
                cur.next = l;
                l = l.next;
            }else {
                cur.next = r;
                r = r.next;
            }
            cur = cur.next;
        }
        cur.next = l==null?r:l;
        return dummy.next;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        return res;
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
        int size;
        int capacity;
        HashMap<Integer,DouNode> map;
        DouNode head;
        DouNode tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size  =0;
            head = new DouNode();
            tail = new DouNode();
            head.next = tail;
            tail.next = head;
        }

        public int get(int key) {
            DouNode node = map.get(key);
            if (node==null){
                return -1;
            }else {
                moveToHead(node);
                return node.value;
            }
        }

        public void put(int key, int value) {
            DouNode node = map.get(key);
            if (node!=null){
                moveToHead(node);
                node.value = value;
            }else {
                DouNode newNode = new DouNode(key, value);
                map.put(key,newNode);
                addToHead(newNode);
                size++;
                if (size>capacity){
                    DouNode relTail = tail.prev;
                    map.remove(relTail.key);
                    removeNode(relTail);
                    size--;
                }
            }
        }

        private void moveToHead(DouNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DouNode node) {
            DouNode next = head.next;
            head.next = node;
            node.next = next;
            next.prev = node;
            node.prev = head;
        }

        private void removeNode(DouNode node) {
            DouNode prevNode = node.prev;
            DouNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        class DouNode{
            int key;
            int value;
            DouNode prev;
            DouNode next;

            public DouNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
            public DouNode(){}
        }
    }



    /*234.
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返
     * 回 true ；否则，返回 false 。*/
    public boolean isPalindrome(ListNode head) {
        ListNode mid = findMid1(head);
        ListNode head2 = reverse1(mid);
        while (head2!=null){
            if (head2.val!=head.val){
                return false;
            }
            head2 = head2.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverse1(ListNode mid) {
        ListNode pre = null,cur = mid;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid1(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }





    /*138复制链表*/
    public Node copyRandomList(Node head) {
        if (head==null) return head;
        Node cur = head;
        while (cur!=null){
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = cur.next.next;
        }

        cur = head;
        while (cur!=null){
            if (cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        cur = head;
        Node res = cur.next,resCur = res;
        while (resCur.next!=null){ /**这里可以省略”resCur!=null“这个判断*/
            cur.next = cur.next.next;
            cur  =cur.next;

            resCur = resCur.next.next;
            resCur = resCur.next;
        }
        cur.next = null;
        return res;
    }

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

    private ListNode mergeKLists(ListNode[] lists, int l, int r) {
        if (l==r) return lists[l];
        int mid = l+(r-l)/2;
        ListNode left = mergeKLists(lists, l, mid);
        ListNode right = mergeKLists(lists, mid + 1, r);
        return mergeTwo1(left,right);
    }

    private ListNode mergeTwo1(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right  = right.next;
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
        int multi = 0;
        StringBuilder res = new StringBuilder();
        Stack<Integer> intStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c>='0'&&c<='9'){
                multi = multi*10 + c-'0';
            } else if (c == '[') {
                intStack.push(multi);
                strStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                Integer num = intStack.pop();
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder().append(strStack.pop()).append(tmp);
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }


    //最小栈
    class MinStack {
        Stack<Integer> minStack;
        Stack<Integer> allStack;

        public MinStack() {
            minStack = new Stack<>();
            allStack = new Stack<>();
        }

        public void push(int val) {
            allStack.push(val);
            if (val<=minStack.peek().intValue()){
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
        int res = 0,maxPosition = 0,bound = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            bound = Math.max(i+nums[i],bound);
            if (i==maxPosition){
                maxPosition = bound;
                res++;
            }
        }
        return res;
    }


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


    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int minPre = nums[0],maxPre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(Math.min(nums[i]*minPre,nums[i]*maxPre),nums[i]);
            int curMax = Math.max(Math.max(nums[i]*minPre,nums[i]*maxPre),nums[i]);
            res = Math.max(curMax,res);
            minPre = curMin;
            maxPre = curMax;
        }
        return res;
    }



    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    public int lengthOfLIS1(int[] nums) {
        int size = 0;
        int[] dp = new int[nums.length];
        for (int num:nums){
            int l = 0,r = size-1;
            while (l<=r){
                int mid = l+(r-l)/2;
                if (dp[mid]<num){
                    l = mid+1;
                }else {
                    r = mid-1;
                }
            }
            dp[l] = num;
            if (l==size) size++;
        }
        return size;
    }


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
    public int findDuplicate(int[] nums) {
        int slow = 0,fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow!=fast);

        slow = 0;
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /*108.升序数组转换为平衡二叉搜索树*/
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums,0,nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int l, int r) {
        if (l>r) return null;
        int mid = l+(r-l)>>1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums,l,mid-1);
        root.right = sortedArrayToBST(nums,mid+1,r);
        return root;
    }


        /*543.
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
    int maxDiameterOfBinaryTree;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        dfs1(root);
        return maxDiameterOfBinaryTree;
    }

    private int dfs1(TreeNode root) {
        if (root==null) return 0;
        int l = dfs1(root.left);
        int r = dfs1(root.right);
        maxDiameterOfBinaryTree = Math.max(l+r,maxDiameterOfBinaryTree);
        return Math.max(l,r)+1;
    }


    //124
    int maxMaxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs2(root);
        return maxMaxPathSum;
    }

    private int dfs2(TreeNode root) {
        if (root==null) return 0;
        int left = dfs2(root.left);
        int right = dfs2(root.right);
        left = Math.max(left,0);
        right = Math.max(right,0);
        maxMaxPathSum = Math.max(maxMaxPathSum,left+right+root.val);
        return Math.max(left,right)+root.val;
    }



    /*347.
     *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率
     * 前 k 高的元素。你可以按 任意顺序 返回答案。
     * */
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            queue.offer(new int[]{key,value});
            if (queue.size()>k){
                queue.poll();
            }
        }

        int i = 0;
        for (int[] cur:queue){
            res[i++] = cur[0];
        }
        return res;
    }

        /*49.
   * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
   字母异位词 是由重新排列源单词的所有字母得到的一个新单词。*/
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String s = new String(charArray);
            if (!map.containsKey(s)){
                map.put(s,new LinkedList<String>());
            }
            map.get(s).add(strs[i]);
        }

        return new LinkedList<>(map.values());
    }


    /*
     * 11.给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
     *    成的，柱子之间是没有间隙的。
     * */
    public int maxArea(int[] height) {
        int left = 0,right = height.length-1;
        int res = 0;
        while (left<right){
            if (height[left]<height[right]) {
                res = Math.max(res, height[left] * (right - left));
                left++;
            }else {
                res = Math.max(res,height[right]*(right-left));
                right--;
            }
        }
        return res;
    }

    /*
     * 15.给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * 【】：关键的步骤在于双指针 过程中，如何跳过所有相同的元素。
     * */
    //写法1
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i>0&&nums[i]==nums[i-1]) continue;
            int l = i+1,r = nums.length-1;
            while (l<r){
                int curSum = nums[i]+nums[l]+nums[r];
                if (curSum<0){
                    l++;
                } else if (curSum > 0) {
                    r--;
                }else {
                    LinkedList<Integer> ele = new LinkedList<>(Arrays.asList(nums[i], nums[l], nums[r]));
                    res.add(ele);
                    while (l<r&&nums[l]==nums[++l]); /**【注意】这里nums[--l]，nums[++r]必须放在最后*/
                    while (l<r&&nums[l]==nums[--r]);
                }
            }
        }
        return res;
    }


    /*
     * 42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * */
    public int trap(int[] height) {
        int leftMax = 0,rightMax = 0;
        int left = 0,right = height.length-1;
        int res = 0;
        while (left<right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[rightMax]);
            if (height[left]<height[right]){
                res += leftMax-height[left];
                left++;
            }else {
                res += rightMax-height[right];
                right--;
            }
        }
        return res;
    }


    //3
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0,cur = 0;
        int res = 0;
        while (cur<s.length()){
            char c = s.charAt(cur);
            map.put(c,map.getOrDefault(c,0)+1);
            while (map.get(c)>1){
                char c1 = s.charAt(left++);
                map.put(c1,map.getOrDefault(c1,0)-1);
            }
            res = Math.max(cur-left+1,res);
        }
        return res;
    }


    /*560.
    * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            res += map.getOrDefault(preSum-k,0);
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return res;
    }



    /*56.
     *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
     * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals,(o1,o2)->{
            return o1[0]-o2[0];
        });
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0]<=res.getLast()[1]){
                res.getLast()[1] = Math.max(cur[1],res.getLast()[1]);
            }else {
                res.add(cur);
            }
        }

        return res.toArray(new int[res.size()][]);
    }


    /*41.
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
    /*解法1：简洁*/
//    public int firstMissingPositive(int[] nums) {
//        int cur = 0;
//        while (cur<nums.length){
//            if (nums[cur]>=0&&nums[cur]<nums.length&&nums[nums[cur]])
//        }
//    }


    /*240.
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int i = 0,j = n-1;
        while (i<m&&n>=0){
            if (matrix[i][j]<target){
                i++;
            }else if (matrix[i][j]>target){
                j--;
            }else {
                return true;
            }
        }
        return false;
    }


    /*2.
    * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curSum = val2+val1+carry;
            cur.next = new ListNode(curSum%10);
            cur = cur.next;
            carry = curSum/10;
            if (l1!=null) l1=l1.next;
            if (l2!=null) l2=l2.next;
        }
        return dummy.next;
    }


    /*19.
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    /*自己的解法*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head),cur =dummy;
        ListNode fast = head;
        for (int i = 0; i < n; i++) { /**for循环的含义是，从现在的位置开始，走n步！！！比如开始fast=head，for循环表示fast需要走两步*/
            fast = fast.next;
        }

        while (fast!=null){
            cur = cur.next;
            fast = fast.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }


    /*24.
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下
     * 完成本题（即，只能进行节点交换）。*/
    /*非递归的形式；非递归形式看官方讲解*/
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;
            node1.next = node2.next;
            cur.next = node2;
            node2.next = node1;

            cur = node1;
        }
        return dummy.next;
    }


    /*94中序遍历*/
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode cur = stack.pop();
                res.add(cur.val);
                root = cur.right;
            }
        }
        return res;
    }


    /*104
    * 给定一个二叉树 root ，返回其最大深度。
    二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。*/
    public int maxDepth(TreeNode root) {
        if (root ==null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(r,l)+1;
    }

    /*102.层序遍历*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root==null) return res;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> ele = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                ele.add(cur.val);
                if (cur.left!=null) queue.offer(cur.left);
                if (cur.right!=null) queue.offer(cur.right);
            }
            res.add(ele);
        }

        return res;
    }


    /*98.
    * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。*/
    public boolean isValidBST(TreeNode root) {
        if (root==null) return true;
        return isValidBST(root,null,null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root==null) return true;
        if (min!=null&&root.val<=min) return false;
        if (max!=null&&root.val>=max) return false;
        return isValidBST(root.left,min,root.val)&&isValidBST(root.right,root.val,max);
    }


    /*199.
    给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    * */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left!=null) queue.offer(cur.left);
                if (cur.right!=null) queue.offer(cur.right);
                if (i==size-1) res.add(cur.val);
            }
        }
        return res;
    }


    /*114.
    * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。*/
    public void flatten(TreeNode root) {

    }

    /*105.
     * 从前序 和 中序 构造出二叉树*/
    HashMap<Integer,Integer> inorderMap;
    int preorderIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
        if (l>r) return null;
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.left = buildTree(preorder,inorder,l,index-1);
        root.right = buildTree(preorder,inorder,index+1,r);
        return root;
    }


    /*236.二叉树最近公共祖先
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left==null&&right==null) return null;
        if (left==null||right==null) return left==null?right:left;
        return root;
    }

    //35
    public int searchInsert(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<target){
                l = mid+1;
            }else {
                r = mid-1;
            }
        }
        return l;
    }


    //74
    public boolean searchMatrix_(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int l = 0,r = m*n-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            int cur = matrix[mid / n][mid % n];
            if (cur<target){
                l = mid+1;
            } else if (cur > target) {
                r = mid-1;
            }else {
                return true;
            }
        }
        return false;
    }


    //33
    public int search(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>=nums[l]){
                if (target>=nums[l]&&target<nums[mid]){
                    r = mid-1;
                }else {
                    l = mid=1;
                }
            }else {
                if (target>nums[mid]&&target<=nums[nums[r]]){
                    l = mid+1;
                }else {
                    r = mid-1;
                }
            }
        }
        return -1;
    }

    //153
    public int findMin(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (nums[mid]<nums[r]){
                r = mid;
            }else {
                l = mid+1;
            }
        }
        return nums[l];
    }


    //739
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                Integer index = stack.pop();
                res[index] = i-index;
            }
            stack.push(i);
        }
        return res;
    }

    //121
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int price:prices){
            min = Math.min(price,min);
            res = Math.max(res,price-min);
        }
        return res;
    }

      /*763.
    * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片
    * 段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或
    * ["ab", "ab", "cc"] 的划分是非法的。
        注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
        返回一个表示每个字符串片段的长度的列表。
    * */
    // 763的写法1
    public List<Integer> partitionLabels1(String s) {
        int[] flags = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            flags[c-'a'] = i;
        }

        int bound = 0,left = 0;
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            bound = Math.max(bound,flags[c-'a']);
            if (i==bound){
                res.add(i-left+1);
                left = i+1;
            }
        }
        return res;
    }


    //169
    public int majorityElement(int[] nums) {
        int total = 0;
        int flag = 0;
        for (int num:nums){
            if (total==0) flag = num;
            total += flag==num?1:-1;
        }
        return flag;
    }

    /*129
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    每条从根节点到叶节点的路径都代表一个数字：

    例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    计算从根节点到叶节点生成的 所有数字之和 。
    叶节点 是指没有子节点的节点。
    * */
    int resSumNumbers;
    public int sumNumbers(TreeNode root) {
        dfs3(root,0);
        return resSumNumbers;
    }

    /**这麽写应该是错的，答案扩大了一倍*/
    private void dfs3(TreeNode root, int i) {
        if (root==null) resSumNumbers += i;
        dfs3(root.left,i*10+root.val);
        dfs3(root.right,i*10+root.val);
    }

    public int sumNumbers_(TreeNode root) {
        int res = 0;
        if (root==null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> intQueue = new LinkedList<>();
        queue.offer(root);
        intQueue.offer(root.val);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                Integer curVal = intQueue.poll();
                /**下面的这句应该是错的*/
//                if (cur.left==null&&cur.right==null) res += (curVal*10+cur.val);
                if (cur.left==null&&cur.right==null) res += curVal*10;
                if (cur.left!=null){
                    queue.offer(cur.left);
                    intQueue.offer(curVal*10+cur.left.val);
                }
                if (curVal.toString()!=null){
                    queue.offer(cur.right);
                    intQueue.offer(curVal*10+cur.right.val);
                }
            }
        }
        return res;
    }



    /*43
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    * */
    public String multiply(String num1, String num2) {
        int len1 = num1.length(),len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1-1; i >=0 ; i--) {
            for (int j = len2-1; j >=0 ; j--) {
                int val1 = num1.charAt(i)-'0';
                int val2 = num2.charAt(j)-'0';
                int curSum = val1*val2+res[i+j+1];
                res[i+j+1] = curSum%10;
                res[i+j] += curSum/10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (sb.length()==0&&res[i]==0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }


    /*162
    峰值元素是指其值严格大于左右相邻值的元素。
    给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在
    位置即可。你可以假设 nums[-1] = nums[n] = -∞ 。
    你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
    * */
    public int findPeakElement(int[] nums) {
        int l = 0,r =nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (nums[mid]>nums[mid+1]){
                r = mid;
            }else {
                l = mid+1;
            }
        }
        return l;
    }

        /*209
    给定一个含有 n 个正整数的数组和一个正整数 target 。
    找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返
    回其长度。如果不存在符合条件的子数组，返回 0 。
    * */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0,sum = 0;
        int res = nums.length+1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum>=target){
                res = Math.min(i-left+1,res);
                sum -= nums[left++];
            }
        }
        return res==nums.length+1?0:res;
    }

    /*128.
    * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。*/
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num:nums) set.add(num);

        for (int num:set){
            if (!set.contains(num-1)){
                int len = 0;
                while (set.contains(len+num)) len++;
                res = Math.max(res,len);
            }
        }
        return res;
    }

    /*
     * 283.给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 【升级题目】：熟悉颜色分类，75。。。。
     *       这种题目的思路就是：假想left指向左边界的最后一个；right指向右边界的第一个；cur表示当前
     *    研究到的位置
     * */
//    public void moveZeroes(int[] nums) {
//
//    }


    /*48.
    * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。*/
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }



    //160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA,p2 = headB;
        while (p1!=p2){
            p1 = p1==null?headB:p1.next;
            p2 = p2==null?headA:p2.next;
        }
        return p1;
    }


    /*142.
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast){
                slow = head;
                while (slow!=fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }


    /*
     * 226.给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * */
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return root;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
//    public TreeNode invertTree_cengxu(TreeNode root) {
//        if (root==null) return root;
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()){
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode cur = queue.poll();
//
//            }
//        }
//        return root;
//    }


        /*200.
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。
    * */
    public int numIsland(char[][] grid) {
        int m = grid.length,n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]=='1'){
                    res++;
                    dfs4(grid,i,j);
                }
            }
        }
        return res;
    }

    private void dfs4(char[][] grid, int i, int j) {
        if (i<0||j<0||i==grid.length||j==grid[0].length||grid[i][j]!='1') return;
        grid[i][j] = '\n';
        dfs4(grid,i+1,j);
        dfs4(grid,i-1,j);
        dfs4(grid,i,j-1);
        dfs4(grid,i,j+1);
    }


    //34
    public int[] searchRange(int[] nums, int target) {
        int left = searchL(nums,target);
        if (left==nums.length||nums[left]!=target) return new int[]{-1,-1};
        int right = searchR(nums,target);
        return new int[]{left,right}; /**这里的right需不需要-1，这麽写应该是不需要-1的*/
    }

    private int searchR(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]>target) r = mid-1;
            else l =mid+1;
        }
        return r;
    }

    private int searchL(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<target) l = mid+1;
            else r = mid;
        }
        return l;
    }


    //84
//    public int largestRectangleArea1(int[] heights) {
//
//    }


    /*1143.
   给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果
   不存在 公共子序列 ，返回 0 。
   一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺
   序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
   例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
   两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
   * */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(),len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i < len1+1; i++) {
            for (int j=1;j<len2+1;j++){
                if (text1.charAt(len1-1)==text2.charAt(len2-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }

    //72
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(),len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1+1; i++) {
            for (int j = 0; j < len2 + 1; j++) {
                if (i==0||j==0)
                    dp[i][j]= i==0?j:i;
                else {
                    if (word1.charAt(i-1)==word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    /*583. 两个字符串的删除操作
     * */
//    public int minDistance_583(String word1, String word2) {
//
//    }


        /*139.
    * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利
    * 用字典中出现的一个或多个单词拼接出 s 则返回 true。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        HashSet<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break; /**这里break的含义？？*/
                }
            }
        }
        return dp[s.length()];
    }


    /*32.
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的
     * 长度。*/
    public int longestValidParentheses(String s) {
        int open=0,close=0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='(') open++;
            else close++;
            if (open==close) res = Math.max(res,2*open);
            if (open<close) open=close=0;
        }

        open=close=0;
        for (int i = s.length()-1; i >=0 ; i--) {
            char c = s.charAt(i);
            if (c=='(') open++;
            else close++;
            if (open==close) res = Math.max(res,2*open);
            if (open>close) open=close=0;
        }
        return res;
    }


    /*22.
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
    /*解法1：官方解回溯法*/
    List<String> resGenerateParenthesis;
    public List<String> generateParenthesis(int n) {
        resGenerateParenthesis = new LinkedList<>();
        StringBuilder sb = new StringBuilder(); /**在这里new跟直接在传参的时候new有什么区别？？*/
        generateParenthesisBack(sb,n,0,0);
        return resGenerateParenthesis;
    }

    private void generateParenthesisBack(StringBuilder sb, int n, int open, int close) {
        if (sb.length()==2*n) resGenerateParenthesis.add(new String(sb));
        if (open<n){
            sb.append('(');
            generateParenthesisBack(sb,n,open+1,close);
            sb.deleteCharAt(sb.length()-1);
        }
        if (close<open){
            sb.append(')');
            generateParenthesisBack(sb,n,open,close+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }


    /*79.
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    * */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs5(board,word,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs5(char[][] board, String word, int index, int i, int j) {
        if (i<0||j<0||i==board.length||j==board[0].length||board[i][j]!=word.charAt(index)) return false;
        board[i][j] = '\n';
        boolean res = dfs5(board, word, index + 1, i + 1, j) ||
                dfs5(board, word, index + 1, i - 1, j) ||
                dfs5(board, word, index + 1, i, j - 1) ||
                dfs5(board, word, index + 1, i, j + 1);
        board[i][j] = word.charAt(index);
        return res;
    }



    /*
    82
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
    /**cur指向的是已经拼接到结果链表中的最后一个节点（即cur及之前的位置一定是在结果链表中（除了dummy），即保证不会有重复值的节点）；
     * cur.next指向的是第一个没有研究的节点————即目前还不确定它是不是应该留下来*/
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){ /**保证后面最少还有两个节点，有两个节点才能比较*/
            int curVal = cur.next.val;
            if (cur.next.next.val==curVal){
                while (cur.next.next!=null&&cur.next.next.val==curVal){
                    cur.next.next = cur.next.next.next;
                }
                cur.next = cur.next.next; /**作用：删除cur.next这个有相等值的节点*/
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /*83*/
    public ListNode deleteDuplicates1(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode slow = head,fast = head.next;
        while (fast!=null){
            if (slow.val!=fast.val){
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }else {
                fast =fast.next;
            }
        }
        slow.next = null; /**不置null会发生什么现象*/
        return head;
    }


    /*165
    给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
    比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
    返回规则如下：
    如果 version1 < version2 返回 -1，
    如果 version1 > version2 返回 1，
    除此之外返回 0。
    * */
    public int compareVersion(String version1, String version2) {
        int len1 = version1.length(),len2 = version2.length();
        int i = 0,j = 0;
        while (i<len1||j<len2){
            int val1 = 0;
            for (;i<len1&&version1.charAt(i)!='.';i++) {
                val1 = val1*10+version1.charAt(i)-'0';
            }
            i++;

            int val2 = 0;
            for (;j<len2&&version2.charAt(j)!='.';j++){
                val2 = val2*10+version2.charAt(j)-'0';
            }
            j++;

            if (val2!=val1) return val1>val2?1:-1;
        }
        return 0;
    }


    /*
    143
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {
        ListNode mid = findMid2(head);
        ListNode head2 = reverse3(mid);
        while (head2!=null){
            ListNode headNext = head.next;
            ListNode head2Next = head2.next;
            head.next = head2;
            head2.next = headNext;
            head = headNext;
            head2 = head2Next;
        }
    }

    private ListNode reverse3(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid2(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }


    /*61
    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;
        ListNode cur = head;
        int size = 1;
        while (cur.next!=null){
            size++;
            cur = cur.next;
        }
        ListNode tail = cur;
        tail.next = head;

        k%=size;
        cur = head;
        for (int i = 0; i < size-k-1; i++) { /**这里的值很关键！！！cur是从head开始，因此只能移动size-k-1步，否则就移动到头节点了，但是不行，必须移动到”头节点的前一个节点“，把next域置null*/
            cur = cur.next;
        }
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }


    /*402
    给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字
    符串形式返回这个最小的数字。
    * */
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            while (!stack.isEmpty()&&num.charAt(i)<stack.peek()&&k>0){
                k--;
                stack.pop();
            }
            stack.push(num.charAt(i));
        }
        while (k>0&&!stack.isEmpty()){
            k--;
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (char c:stack){
            if (sb.length()==0&&c=='0') continue;
            sb.append(c);
        }
        return sb.length()==0?"0":sb.toString();
    }



    /*145
    给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
    * */
//    public List<Integer> postorderTraversal(TreeNode root) {
//        LinkedList<Integer> res = new LinkedList<>();
//        if (root==null) return res;
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while (!stack.isEmpty()){
//            if (root!=null){
//                stack.push(root);
//                root = root.left;
//            }else {
//
//            }
//        }
//
//        return res;
//    }


    /*328
 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
  */
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return head;
        ListNode odd = head,oddCur = head;
        ListNode even = head.next,evenCur = even;
        while (evenCur!=null&&evenCur.next!=null){
            oddCur.next = oddCur.next.next;
            oddCur = oddCur.next;

            evenCur.next = evenCur.next.next;
            evenCur = evenCur.next;
        }
        oddCur.next = even;
        return head;
    }

    /*135
    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

    你需要按照以下要求，给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */
    public int candy(int[] ratings) {
        int[] res = new int[ratings.length];
        Arrays.fill(res,1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i]>ratings[i-1])
                res[i] = res[i-1]+1;
        }

        for (int i = ratings.length-2; i >=0 ; i--) {
            if (ratings[i]>ratings[i=1]){
                res[i] = Math.max(res[i],res[i+1]+1);
            }
        }
        return Arrays.stream(res).sum();
    }


    /*134
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curTotal = 0,allTotal = 0;
        int res  =0;
        for (int i = 0; i < gas.length; i++) {
            curTotal += gas[i]-cost[i];
            allTotal += gas[i]-cost[i];
            if (curTotal<0){
                res  =i+1;
                curTotal = 0;
            }
        }
        return allTotal<0?-1:res;
    }


        /*678
    给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。

    有效 字符串符合如下规则：

    任何左括号 '(' 必须有相应的右括号 ')'。
    任何右括号 ')' 必须有相应的左括号 '(' 。
    左括号 '(' 必须在对应的右括号之前 ')'。
    '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
     */
    public boolean checkValidString(String s) {
        int min = 0,max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='('){
                min++;
                max++;
            } else if (c == ')') {
                min--;
                max--;
            }else {
                min--;
                max++;
            }
            if (max<0) return false;
            if (min<0) min=0;
        }
        return min==0;
    }


}
