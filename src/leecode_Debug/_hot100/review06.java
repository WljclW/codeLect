package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import javax.sound.sampled.Line;
import java.util.*;

/**
 * 25. K 个一组翻转链表
 * 23. 合并 K 个升序链表
 * 155. 最小栈
 * 287. 寻找重复数
 * 300. 最长递增子序列
 * 41. 缺失的第一个正数
 * 24. 两两交换链表中的节点
 * 105. 从前序与中序遍历序列构造二叉树
 * 31. 下一个排列
 * 101
 * 300. 最长递增子序列
 * 416
 * 82. 删除排序链表中的重复元素 II
 * 143. 重排链表
 * 328
 * 402. 移掉 K 位数字
 * @author mini-zch
 * @date 2025/7/22 16:20
 */
public class review06 {
    /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty()&&nums[i]>nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        res[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty()&&nums[i]>nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if (deque.peekFirst()==i-k){
                deque.pollFirst();
            }
            res[i-k+1] = nums[deque.peekFirst()];
        }

        return res;
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
        for (int i = nums.length-1; i >=0 ; i--) {
            res[i] *= post;
            post *= nums[i];
        }

        return res;
    }

    //颜色分类
    public void sortColors(int[] nums) {
        int left = 0,cur = 0,right = nums.length-1;
        while (cur<=right){
            if (nums[cur]<1){
                swap(nums,left++,cur++);
            } else if (nums[cur]==1) {
                cur++;
            }else {
                swap(nums,cur,right--);
            }
        }
    }
    private void swap(int[] nums, int left, int right) {
        int tmp  = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    //k个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head),cur = dummy,end = dummy;
        while (cur.next!=null){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end==null) break;

            ListNode start = cur.next;
            ListNode nextStart = end.next;

            cur.next = reverseK(start);
            start.next = nextStart;

            cur = start;
            end = start;
        }
        return dummy.next;
    }

    private ListNode reverseK(ListNode start) {
        ListNode pre = null,cur = start;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /*234.
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返
     * 回 true ；否则，返回 false 。*/
    public boolean isPalindrome(ListNode head) {
        ListNode mid = findMid(head);
        ListNode head2 = reverse(mid.next);
        while (head2!=null){
            if (head.val!= head2.val){
                return false;
            }
            head2 = head2.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre =  cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow =head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
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
                this.value = value;
            }
        }

        int size = 0;
        int capacity;
        DouNode head;
        DouNode tail;
        HashMap<Integer,DouNode> map  =new HashMap<>();

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
            }else{
                return -1;
            }
        }

        public void put(int key, int value) {
            DouNode node = map.get(key);
            if (node!=null){
                node.value = value;
                moveToHead(node);
            }else{
                DouNode newNode = new DouNode(key, value);
                map.put(key,newNode);
                addToHead(newNode);
                size++;
                if (size>capacity){
                    DouNode lastNode = tail.prev;
                    map.remove(lastNode.key);
                    removeLastNode();
                    size--;
                }
            }
        }

         private void removeLastNode() {
             DouNode lastNode = tail.prev;
             lastNode.prev.next = tail;
             tail.prev = lastNode.prev;
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
             node.prev =head;
         }

         private void removeNode(DouNode node) {
             DouNode pre = node.prev;
             DouNode next = node.next;
             pre.next = next;
             next.prev = pre;
         }
     }


    /*138复制链表*/
//    public Node copyRandomList(Node head) {
//        if (head==null) return head;
//        Node cur = head;
//        while (cur!=null){
//            Node node = new Node(cur.val);
//            node.next = cur.next;
//            cur.next = node;
//            cur = cur.next.next;
//        }
//
//        cur = head;
//        while (cur!=null){
//            if (cur.random!=null){
//                cur.next.random = cur.random.next;
//            }
//            cur = cur.next.next;
//        }
//
//        Node res = head.next,now = res;
//        Node origin = head;
//        while (now.next!=null){
//            origin.next = origin.next.next;
//            origin = origin.next;
//
//            now.next = now.next.next;
//            now = now.next;
//        }
//        origin.next = null;
//        return res;
//    }
//
//    class Node {
//        int val;
//        Node next;
//        Node random;
//
//        public Node(int val) {
//            this.val = val;
//            this.next = null;
//            this.random = null;
//        }
//    }



    /*23.
    * 给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。*/
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null) return null;
        if (lists.length==1) return lists[0];
        return merge(lists,0,lists.length-1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = left +(right-left)/2;
        ListNode leftNode = merge(lists, left, mid);
        ListNode rightNode = merge(lists, mid + 1, right);
        return mergeTwo(leftNode,rightNode);
    }

    private ListNode mergeTwo(ListNode leftNode, ListNode rightNode) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (leftNode!=null&&rightNode!=null){
            if (leftNode.val<rightNode.val){
                cur.next = leftNode;
                leftNode = leftNode.next;
            }else{
                cur.next = rightNode.next;
                rightNode = rightNode.next;
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
        int multi  =0;
        Stack<String> strStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c>='0'&&c<='9'){
                multi =multi*10+c-'0';
            } else if (c=='[') {
                strStack.push(res.toString()); /**toString行不行？new String（）*/
                intStack.push(multi);
                multi = 0;
                res = new StringBuilder();
            } else if (c==']') {
                Integer curNum = intStack.pop();
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < curNum; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder(strStack.pop()+tmp);
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }


    //最小栈
        class MinStack {

        Stack<Integer> allStack;
        Stack<Integer> minStack;
        public MinStack() {
            allStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            allStack.push(val);
            if (minStack.isEmpty() || val<minStack.peek()){
                minStack.push(val);
            }
        }

        public void pop() {
            Integer pop = allStack.pop();
            if (pop.equals(minStack.peek())){
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
        int maxPosition = 0,bound = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            bound = Math.max(bound,i+nums[i]);
            if (maxPosition==i){
                maxPosition = bound;
                step++;
            }
        }
        return step;
    }


    /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
//    public int findDuplicate(int[] nums) {
//
//    }

    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
//    public int lengthOfLIS1(int[] nums) {
//        int[] dp = new int[nums.length];
//        int size = 0;
//        int l = 0,r = size-1;
//        while (l<=r){
//            int mid = l+(r-l)/2;
//            if (nums[mid])
//        }
//    }


    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
    public int maxProduct(int[] nums) {
        int minPre = nums[0],maxPre = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(nums[i],Math.min(minPre*nums[i],maxPre*nums[i]));
            int curMax = Math.max(nums[i],Math.max(maxPre*nums[i],maxPre*nums[i]));
            minPre = curMin;
            maxPre = curMax;
            res = Math.max(res,maxPre);
        }
        return res;
    }


    /*49.
   * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
   字母异位词 是由重新排列源单词的所有字母得到的一个新单词。*/
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str:strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String s = new String(charArray);
            if (map.get(s)==null){
                LinkedList<String> strings = new LinkedList<>();
                strings.add(str);
                map.put(s,strings);
            }else
                map.get(s).add(str);
        }
        return new LinkedList<>(map.values());
    }



    /*
     * 11.给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
     *    成的，柱子之间是没有间隙的。
     * */
    /**
     * 【步骤】双指针相向而行，计算以它们为边界能盛多少水。。只要中间有间距（即left<right）
     *      1. 每到一个位置计算能盛的水（高度取决于左右指针height的最小值，宽度取决于两个指针的距离）
     *      2. 看两个指针对应的height哪一个高，更新height小的那一个指针
     */
    public int maxArea(int[] height) {
        int left = 0,right = height.length-1;
//        int leftMax = Integer.MIN_VALUE,rightMax = Integer.MIN_VALUE;
        int res = 0;
        while (left<right){
            int curVal = 0;
            if (height[left]<height[right]){
                curVal = height[left]*(right-left);
                left++;
            }else{
                curVal = height[right]*(right-left);
                right--;
            }
            res = Math.max(res,curVal);
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
                int curVal = nums[i]+nums[l]+nums[r];
                if (curVal<0){
                    l++;
                } else if (curVal>0) {
                    r--;
                }else {
                    List<Integer> list = Arrays.asList(nums[i], nums[l], nums[r]);
                    res.add(list);
                    while (l<r&&nums[l]==nums[++l]);
                    while (l<r&&nums[r]==nums[--r]);
                }
            }
        }

        return res;
    }


    /*
     * 42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * */
    public int trap(int[] height) {
        int l = 0,r = height.length-1;
        int leftMax = height[0],rightMax = height[height.length-1];
        int res = 0;
        while (l<r){
            leftMax = Math.max(height[l], leftMax);
            rightMax = Math.max(height[r], rightMax);
            if (height[l]<height[r]){
                res += (leftMax-height[l]);
                l++;
            }else {
                res += (rightMax-height[r]);
                r--;
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
                char cLeft = s.charAt(left++);
                map.put(cLeft,map.get(cLeft)-1);
            }
            res = Math.max(res,cur-left+1);
            cur++; /**这句话如果是在上一句话的上面，就不用使用”cur-left+1“了，不需要+1*/
        }
        return res;
    }


    /*560.
    * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0,res = 0;
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
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        LinkedList<int[]> res = new LinkedList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0]<=res.getLast()[1]){
                res.getLast()[1] = Math.max(cur[1],res.getLast()[1]);
            }else{
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
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0&&nums[i]<=nums.length&&nums[nums[i]-1]!=nums[i]){
                swapNum(nums,nums[i]-1,i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }
    private void swapNum(int[] nums, int left, int right) {
        int tmp  = nums[left];
        nums[left] =nums[right];
        nums[right] = tmp;
    }


    /*240.
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0,col = matrix[0].length;
        while (row<matrix.length&&col>=0){
            int cur = matrix[row][col];
            if (cur>target){
                col--;
            } else if (cur<target) {
                row++;
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
        int carry = 0;
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = (l1==null)?0:l1.val;
            int val2 = (l2==null)?0:l2.val;
            int curSum = val2+val1+carry;
            ListNode newNode = new ListNode(curSum % 10);
            cur.next = newNode;
            cur = cur.next;
            carry =curSum/10;
            if (l1!=null) l1=l1.next;
            if (l2!=null) l2 = l2.next;
        }
        return dummy.next;
    }


    /*19.
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    /*自己的解法*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy,fast = head;
        while (n>0){
            fast = fast.next;
            n--;
        }
        while (fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }



    /*24.
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下
     * 完成本题（即，只能进行节点交换）。*/
    /*非递归的形式；非递归形式看官方讲解*/
    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;
            node1.next = node2.next;
            node2.next = cur;
            cur = node1;
        }
        return dummy.next;
    }


    /*94中序遍历*/
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.add(root);
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
        if (root==null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right)+1;
    }

    public int maxDepth_(TreeNode root) {
        if (root==null) return 0;
        int depth = 0;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root); /**当作队列使用，因此使用offer方法*/
        while (!deque.isEmpty()){
            depth++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                if (cur.left!=null){
                    deque.offer(cur.left);
                }
                if (cur.right!=null){
                    deque.offer(cur.right);
                }
            }
        }
        return depth;
    }


    /*543.
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
    int resDiameterOfBinaryTree = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return resDiameterOfBinaryTree;
    }
    /**其实返回的是以root为根的子树的深度信息*/
    private int dfs(TreeNode root) {
        if (root==null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        resDiameterOfBinaryTree = Math.max(left+right,resDiameterOfBinaryTree);
        return Math.max(left,right)+1;
    }


    /*102.层序遍历*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            LinkedList<Integer> ele = new LinkedList<>();
            for (int i = 0; i <size; i++) {
                TreeNode cur = deque.poll();
                ele.add(cur.val);
                if (cur.left!=null){
                    deque.offer(cur.left);
                }
                if (cur.right!=null){
                    deque.offer(cur.right);
                }
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
        return isValidBST(root,null,null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root==null) return true;
        if ((min!=null&&root.val<=min)||(max!=null&&root.val>=max)){
            return false;
        }
        return isValidBST(root.left,min,root.val)&&isValidBST(root.right,root.val,max);
    }


    /*199.
    给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    * */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                if (i==size-1){
                    res.add(cur.val);
                }
                if (cur.left!=null){
                    deque.offer(cur.left);
                }
                if (cur.right!=null){
                    deque.offer(cur.right);
                }
            }
        }
        return res;
    }


    /*114.
    * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。*/
    public void flatten(TreeNode root) {
        if (root==null) return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode cur = null;
        while (!stack.isEmpty()){
            TreeNode nowNode = stack.pop();
            if (cur==null){
                cur = nowNode;
            }else{
                cur.right = nowNode;
                cur.left = null;
                cur = cur.right;
            }
            if (nowNode.right!=null){
                stack.push(nowNode.right);
            }
            if (nowNode.left!=null){
                stack.push(nowNode.left);
            }
        }
    }


//    /*105.
//     * 从前序 和 中序 构造出二叉树*/
//    HashMap<Integer,Integer> inorderMap;
//    int preorderIndex;
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        for (int i = 0; i <inorder.length; i++) {
//            inorderMap.put(inorder[i],i);
//        }
//        return buildTree(preorder,inorder,0,inorder.length-1);
//    }
//
//    private TreeNode buildTree(int[] preorder, int[] inorder, int left, int right) {
//        int val = preorder[preorderIndex++];
//        Integer index = inorderMap.get(val);
//        TreeNode root = new TreeNode(val);
//        root.left = buildTree(preorder,inorder,left,index-1);
//        root.right = buildTree(preorder,inorder,index+1,right);
//        return root;
//    }


    /*236.二叉树最近公共祖先
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        if (root==p||root==q){
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if (leftNode==null&&rightNode==null) return null;
        if (leftNode==null||rightNode==null) return (leftNode==null)?rightNode:leftNode;
        return root;
    }

    //35
    public int searchInsert(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<target){
                l = mid+1;
            }else{
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
            int mid =l+(r-l)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur<target){
                l = mid+1;
            } else if (cur>target) {
                r = mid-1;
            }else {
                return true;
            }
        }
        return false;
    }

    //33
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>=nums[0]){ /**说明左边肯定是有序的*/
                if (target>=nums[0]&&target<nums[mid]){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }else { //else说明mid的右半部分是有序的
                if (target>nums[mid]&&target<=nums[nums.length-1]){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    //153
    public int findMin(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]<=nums[nums.length-1]){ /**换成小于行不行*/
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return nums[left];
    }

    //739
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                Integer cur = stack.pop();
                res[cur] = i-cur;
            }
            stack.push(i);
        }

        return res;
    }

    //121
    public int maxProfit(int[] prices) {
        int flag = Integer.MAX_VALUE;
        int res = 0;
        for (int price:prices){
            flag = Math.min(price,flag);
            res  =Math.max(res,price-flag);
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

        LinkedList<Integer> res = new LinkedList<>();
        int left = 0,cur =0;
        int bound = 0;
        while (cur<s.length()){
            char c = s.charAt(cur);
            bound = Math.max(bound,flags[c-'a']);
            if (cur==bound){
                res.add(cur-left+1);
                left = bound+1;
            }
            cur++;
        }
        return res;
    }

    //169
    public int majorityElement(int[] nums) {
        int ticket = 0,flag = 0;
        for (int num:nums){
            if (ticket==0) flag = num;
            ticket += (flag==num)?1:-1;
        }
        return flag;
    }

    //31
    public void nextPermutation(int[] nums) {
        int flag = nums.length;
        for (int i = nums.length-2; i >=0 ; i--) {
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }
        if (flag==nums.length){
            reverseNum(nums,0);
            return;
        }

        int index = flag;
        for (int i = nums.length-1; i > flag; i--) {
            if (nums[i]>nums[flag]){
                index =i;
                break;
            }
        }
        int tmp = nums[index];
        nums[index]  =nums[flag];
        nums[flag] = tmp;
        reverseNum(nums,flag+1);
        return;
    }

    private void reverseNum(int[] nums, int index) {
        int left  =index,right = nums.length-1;
        while (left<right){
            int tmp  =nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
    }


    /*129
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    每条从根节点到叶节点的路径都代表一个数字：

    例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    计算从根节点到叶节点生成的 所有数字之和 。
    叶节点 是指没有子节点的节点。
    * */
    public int sumNumbers(TreeNode root) {
        int sum = 0;
        if (root==null) return sum;
        LinkedList<TreeNode> deque = new LinkedList<>();
        LinkedList<Integer> integers = new LinkedList<>();
        deque.offer(root);
        integers.offer(root.val);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                Integer curVal = integers.poll();
                if (cur.left==null&&cur.right==null){
                    sum += curVal;
                }
                if (cur.left!=null){
                    deque.offer(cur.left);
                    integers.offer(curVal*10+cur.left.val);
                }
                if (cur.right!=null){
                    deque.offer(cur.right);
                    integers.offer(curVal*10+cur.right.val);
                }
            }
        }
        return sum;
    }



    /*43
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    * */
    public String multiply(String num1, String num2) {
        int len1 = num1.length(),len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1-1; i >=0 ; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = len2-1; j >=0 ; j--) {
                int y  =num2.charAt(j)-'0';
                int curSum = x*y + res[i+j+1];
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
        int l = 0,r = nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (nums[mid]>nums[mid+1]){
                r = mid;
            }else{
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
        int sum = 0;
        int l = 0,r = 0;
        int res = Integer.MAX_VALUE;
        while (r<nums.length){
            sum += nums[r];
            while (sum>=target){
                res = Math.min(r-l+1,res);
                sum -= nums[l++];
            }
            r++;
        }
        return res==Integer.MAX_VALUE?0:res;
    }


    /**7.23
     * ======================================================================================================
     * ======================================================================================================
     * ======================================================================================================
     * ======================================================================================================
     * ======================================================================================================
     * ============================================================================================================================================================================================================
     */
    /*128.
    * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。*/
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int res =0;
        for (int num:nums){
            set.add(num);
        }
        for (int num:set){
            if (!set.contains(num-1)){
                int count = 0;
                while (set.contains(num+count)) count++;
                res = Math.max(res,count);
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
    public void moveZeroes(int[] nums) {
        int left = 0,right = 0;
        while (right<nums.length){
            if (nums[right]!=0){
                nums[left++] = nums[right++];
            }else{
                right++;
            }
        }
        for (int i = left; i <nums.length ; i++) {
            nums[i] = 0;
        }
    }


    /*48.
    * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。*/
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        int m = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][m-1-j];
                matrix[i][m-1-j] = tmp;
            }
        }
    }

    //160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pa = headA,pb = headB;
        while (pa!=pb){
            pa = (pa==null)?headB:pa.next;
            pb = (pb==null)?headA:pb.next;
        }
        return pa;
    }


    /*142.
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
    public ListNode detectCycle(ListNode head) {
        ListNode slow =head,fast = head;
        while (fast!=null&&fast.next!=null){
            fast =fast.next.next;
            slow = slow.next;
            if (fast==slow){
                fast = head;
                while (fast!=slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }


    /*148.
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode mid = findMiddle(head);
        ListNode head2 = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(head2);
        return mergeT(left,right);
    }

    private ListNode mergeT(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur  =cur.next;
        }
        return dummy.next;
    }

    /**下面是找链表中间第一个节点的方法：如果是偶数个slow会来到中间的第一个；如果是奇数个节点会来到中间节点。。。。
     * 【总结】因此fast指针的初始位置决定了寻找中间哪一个节点。slow指针不影响，固定初始值是head*/
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }



    /*
     * 226.给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * */
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                swapNode(cur);
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
            }
        }
        return root;
    }

    private void swapNode(TreeNode cur) {
        TreeNode tmp = cur.left;
        cur.left = cur.right;
        cur.right = tmp;
    }


    /*101.给你一个二叉树的根节点 root ， 检查它是否轴对称。*/
//    public boolean isSymmetric(TreeNode root) {
//        LinkedList<TreeNode> deque1 = new LinkedList<>();
//        LinkedList<TreeNode> deque2 = new LinkedList<>();
//        if (root)
//    }

    /*98.
    * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。*/
    public boolean isValidBST_diedai(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;
        while (root!=null || !stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode cur = stack.pop();
                if (cur.val<=pre){
                    return false;
                }
                pre = cur.val;
                root = cur.right;
            }
        }
        return true;
    }


    /*200.
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。
    * */
    public int numIsland(char[][] grid){
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    res++;
                    dfs(grid,i,j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i<0||i>=grid.length||j<0||j>grid[0].length||grid[i][j]=='0'){
            return;
        }
        grid[i][j] = '0';
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }


    //34
    public int[] searchRange(int[] nums, int target) {
        int left = searchLeft(nums,target);
        if (left==nums.length||nums[left]!=target){
            return new int[]{-1,-1};
        }
        int right = searchRight(nums,target);
        return new int[]{left,right};
    }

    /**【注意】下面的方法仅仅是while内的if-else有区别。其中：
     *      寻找target左边界的时候，等于的时候也要收缩右边界————right=mid-1；
     *      寻找target右边界的时候，等于的时候也要搜索左边界————left=mid+1*/
    private int searchRight(int[] nums, int target) {
        int left = 0,right =nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return right;
    }

    private int searchLeft(int[] nums, int target) {
        int left = 0,right = nums.length;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]<target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return left;
    }


    /*20.
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是
    * 否有效。
    有效字符串需满足：
    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    每个右括号都有一个对应的相同类型的左括号。*/
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>() {{
            put(')','(');
            put(']','[');
            put('}','{');
        }};
        Stack<Character> characters = new Stack<>();
        for (char c:s.toCharArray()){
            if (!map.containsKey(c)){
                characters.push(c);
            }else{
                if (characters.isEmpty() || characters.pop()!=map.get(c)){
                    return false;
                }
            }
        }
        return characters.isEmpty();
    }

    //84
    public int largestRectangleArea1(int[] heights){
        int res = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < heights.length+1; i++) {
            int curHeight = (i==heights.length)?0:heights[i];
            while (!stack.isEmpty()&&curHeight<heights[stack.peek()]){
                Integer pop = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                int curArea = heights[pop]*(i-left-1);
                res = Math.max(curArea,res);
            }
            stack.push(i);
        }
        return res;
    }


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
        for (int i = 1; i <=len1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
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
            for (int j = 0; j < len2+1; j++) {
                if (i==0 || j==0){
                    dp[i][j] = (i==0)?j:i;
                }else {
                    if (word1.charAt(i-1)==word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    /*583. 两个字符串的删除操作
     * */
    public int minDistance_583(String word1, String word2) {
        int len1 = word1.length(),len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            for (int j = 0; j < len2 + 1; j++) {
                if (i==0||j==0){
                    dp[i][j] = (i==0)?j:i;
                }else{
                    if (word1.charAt(i-1)==word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+1;
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    /*139.
    * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利
    * 用字典中出现的一个或多个单词拼接出 s 则返回 true。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    /*解法1：朴素的做法
     * dp[i]：表示以第i个元素结尾的最长递增子序列有多长*/
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = 0, r = size - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < nums[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (l >= size)
                dp[size++] = nums[i];
            else
                dp[l] = nums[i];
        }
        return size + 1;
    }


    /*416.
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两
     * 个子集，使得两个子集的元素和相等。*/
    /**见leecode_Debug._hot100._01bag*/
//    public boolean canPartition(int[] nums) {
//        int sum = 0;
//        for (int num:nums){
//            sum += num;
//        }
//        if (sum%2!=0) return false;
//        sum /= 2;
//
//        int[] dp = new int[sum + 1];
//
//    }

    /*32.
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的
     * 长度。*/
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int res =0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i)==')'){
                if (s.charAt(i-1)=='('){
                    dp[i] = (i-2>=0)?dp[i-2]+2:2;
                }else {
                    if (i-dp[i-1]>0&&s.charAt(i-dp[i-1]-1)=='('){
                        dp[i] = i-dp[i-1]-2>=0?dp[i-dp[i-1]-2]+2+dp[i-1]:2+dp[i-1];
                    }
                }
                res  =Math.max(res,dp[i]);
            }
        }
        return res;
    }


//    public int longestValidParentheses(String s) {
//        int left = 0,right = 0;
//        int res = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i)=='(') left++;
//            else right++;
//            if (left==right) res = Math.max(res,2*left);
//            if (left<right) left=right=0;
//        }
//
//        left=right=0;
//        for (int i = s.length()-1; i >=0 ; i--) {
//            if (s.charAt(i)=='(') left++;
//            else right++;
//            if (left==right) res = Math.max(res,2*left);
//            if (left>right) left=right=0;
//        }
//        return res;
//    }


    /*46.
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    * */
    List<List<Integer>> resPermute;
    List<Integer> pathPermute;
    boolean[] usedPermute;

    public List<List<Integer>> permute(int[] nums) {
        resPermute=new LinkedList<>();
        pathPermute = new LinkedList<>();
        usedPermute = new boolean[nums.length];
        permuteBack(nums);
        return resPermute;
    }

    private void permuteBack(int[] nums) {
        if (pathPermute.size()==nums.length){
            resPermute.add(new LinkedList<>(pathPermute));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!usedPermute[i]){
                usedPermute[i] = true;
                pathPermute.add(nums[i]);
                permuteBack(nums);
                usedPermute[i] = false;
                pathPermute.remove(pathPermute.size()-1);
            }
        }
    }

    /**
     * 比较上面的代码和下面的代码，会发现：仅仅是把变量pathPermute、usedPermute放在了形参的位置。其他的代码
     * 都没有变——————体会回溯这里 全局变量和形参变量的区别
     */
//    List<List<Integer>> resPermute1;
//    public List<List<Integer>> permute(int[] nums) {
//        resPermute1=new LinkedList<>();
//        List<Integer> pathPermute = new LinkedList<>();
//        boolean[] usedPermute = new boolean[nums.length];
//        permuteBack1(nums,pathPermute,usedPermute);
//        return resPermute1;
//    }
//
//    private void permuteBack1(int[] nums, List<Integer> pathPermute, boolean[] usedPermute) {
//        if (pathPermute.size()==nums.length){
//            resPermute1.add(new LinkedList<>(pathPermute));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (!usedPermute[i]){
//                usedPermute[i] = true;
//                pathPermute.add(nums[i]);
//                permuteBack1(nums,pathPermute,usedPermute);
//                usedPermute[i] = false;
//                pathPermute.remove(pathPermute.size()-1);
//            }
//        }
//    }


    /*78.
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
    * */
    List<List<Integer>> resSubSets;
    List<Integer> pathSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubSets = new LinkedList<>();
        pathSubsets = new LinkedList<>();
        subsetsBack(nums,0);
        return resSubSets;
    }

    private void subsetsBack(int[] nums, int index) {
        resSubSets.add(new LinkedList<>(pathPermute));
        if (index==nums.length) return;
        for (int i = index; i < nums.length; i++) {
            pathSubsets.add(nums[i]);
            subsetsBack(nums,i+1);
            pathSubsets.remove(pathSubsets.size()-1);
        }
    }

    /**
     * 上面的代码和下面的代码唯一的区别是pathSubsets放在了形参的位置，其他的diamond都是一样的。这两种都没问题
     */
//    List<List<Integer>> resSubSets;
//    public List<List<Integer>> subsets(int[] nums) {
//        resSubSets = new LinkedList<>();
//        List<Integer> pathSubsets = new LinkedList<>();
//        subsetsBack(nums,0,pathSubsets);
//        return resSubSets;
//    }
//
//    private void subsetsBack(int[] nums, int index,List<Integer> pathSubsets) {
//        resSubSets.add(new LinkedList<>(pathSubsets));
//        if (index==nums.length) return;
//        for (int i = index; i < nums.length; i++) {
//            pathSubsets.add(nums[i]);
//            subsetsBack(nums,i+1,pathSubsets);
//            pathSubsets.remove(pathSubsets.size()-1);
//        }
//    }



    /*17.
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    * */
    List<String> resLetterCombinations;
    Map<Character,String> map;
    StringBuilder sb = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        resLetterCombinations = new LinkedList<>();
        sb = new StringBuilder();
        map = new HashMap<>(){{
            put('2',"abc");
            put('3',"def");
            put('4',"ghi");
            put('5',"jkl");
            put('6',"mno");
            put('7',"pqrs");
            put('8',"tuv");
            put('9',"wxyz");
        }};
        if (digits.length()==0) return resLetterCombinations;
        letterCombinationsBack(digits,0);
        return resLetterCombinations;
    }

    private void letterCombinationsBack(String digits, int index) {
        if (index==digits.length()){
            resLetterCombinations.add(new String(sb));
            return;
        }
        String str = map.get(digits.charAt(index));
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            letterCombinationsBack(digits,index+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }


    /*39.
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。
    * */
    List<List<Integer>> resCombinationSum;
    List<Integer> pathCombinationSum;
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        resCombinationSum = new LinkedList<>();
        pathCombinationSum = new LinkedList<>();
        combinationSum1Back(candidates,target);
        return resCombinationSum;
    }

    private void combinationSum1Back(int[] candidates, int target) { /**组合是不是一定要遍历到最后一位？？*/
        if (target==0){
            resCombinationSum.add(new LinkedList<>(pathCombinationSum));
            return;
        }

    }



    /*22.
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
    /**
     * 【解题关键】尝试，用open和close分别表示左右括号，在合法的前提下（合法的要求：①任意时刻左括号的
     *      数量必须不小于右括号的数量 且 ②左括号的数量小于n），尝试添加一个左括号或者右括号。
     * 【同理】这段代码sbGenerateParenthesis放在形参的位置，其他的代码也不用变，把它作为形参变量每一
     *      次递归的时候传即可。
     */
    /*解法1：官方解回溯法*/
    List<String> resGenerateParenthesis;
    StringBuilder sbGenerateParenthesis;
    public List<String> generateParenthesis(int n) {
        resGenerateParenthesis = new LinkedList<>();
        sbGenerateParenthesis = new StringBuilder();
        generateParenthesisBack(n,0,0);
        return resGenerateParenthesis;
    }

    private void generateParenthesisBack(int n, int left, int right) {
        if (sb.length()==2*n){
            resGenerateParenthesis.add(new String(sbGenerateParenthesis));
            return;
        }
        if (left<n){
            sbGenerateParenthesis.append('(');
            generateParenthesisBack(n,left+1,right);
            sbGenerateParenthesis.deleteCharAt(sbGenerateParenthesis.length()-1);
        }
        if (right<left){
            sbGenerateParenthesis.append(')');
            generateParenthesisBack(n,left,right+1);
            sbGenerateParenthesis.deleteCharAt(sbGenerateParenthesis.length()-1);
        }
    }


    /*79.
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    * */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,word,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j,int index) {
        if (i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!= sb.charAt(index)){
            return false;
        }
        boolean res = dfs(board, word, i + 1, j, index + 1) ||
                dfs(board, word, i - 1, j, index + 1) ||
                dfs(board, word, i, j - 1, index + 1) ||
                dfs(board, word, i, j + 1, index + 1);
        return res;
    }



    /*51.
    按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    * */
    List<List<String>> resSolveNQueens = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] nums = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(nums[i],'.');
        }
        solveNQueensBack(n,nums,0);
        return resSolveNQueens;
    }

    private void solveNQueensBack(int n, char[][] nums, int row) {
        if (row==n){
            resSolveNQueens.add(getListFromNums(nums));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValidHere(nums,row,i)){
                nums[row][i] = 'Q';
                solveNQueensBack(n,nums,row+1);
                nums[row][i] = '.';
            }
        }
    }

    private List<String> getListFromNums(char[][] nums) {
        LinkedList<String> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
//            res.add(nums[i].toString()); //这个代码和下面的是等效的吗？？
            res.add(new String(nums[i])); //new String创建了几个字符串的问题
        }
        return res;
    }

    private boolean isValidHere(char[][] nums, int row, int col) {
        int m = nums.length,n = nums[0].length;
        for (int i = 0; i < row; i++) {
            if (nums[i][col]=='Q') return false;
        }

        for (int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if (nums[i][j]=='Q') return false;
        }

        for (int i=row-1,j=col+1;i>=0&&j<n;i--,j++){
            if (nums[i][j]=='Q') return false;
        }

        return true;
    }






    /**========================================================_09huisu中补充的非hot100需要做============================*/


    /*
    82
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head),slow=dummy;
        while (slow.next!=null&&slow.next.next!=null){
            int val = slow.next.val;
            if (slow.next.next.val==val){
                while (slow.next.next!=null&&slow.next.next.val==val){
                    slow.next.next = slow.next.next.next;
                }
                slow.next = slow.next.next;
            }
            slow = slow.next;
        }
        return dummy.next;
    }

    /*83*/
    public ListNode deleteDuplicates1(ListNode head) {
        if (head==null) return head;
        ListNode slow = head,fast = head.next;
        while (fast!=null){
            if (slow.val!=fast.val){
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }else{
                fast = fast.next;
            }
        }
        slow.next = null;
        return head;
    }


    /*124
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列
    中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumAno(root);
        return maxSum;
    }

    private int maxPathSumAno(TreeNode root) {
        if (root==null) return 0;
        int left = Math.max(maxPathSumAno(root.left), 0);
        int right = Math.max(maxPathSumAno(root.right), 0);
        maxSum = Math.max(maxSum,left+right+root.val);
        return Math.max(left,right)+root.val;
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
        int i=0,j=0;
        while (i<len1||j<len2){
            int val1 = 0;
            for (;i<len1&&version1.charAt(i)!='.';i++){
                val1 = val1*10 + version1.charAt(i)-'0';
            }
            i++;

            int val2 = 0;
            for (;j<len2&&version2.charAt(j)!='.';j++){
                val2 = val2*10 + version2.charAt(j)-'0';
            }
            j++;

            if (val1!=val2){
                return val1>val2?1:-1;
            }
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
        ListNode midd = findMidd(head);
        ListNode midNext = midd.next;
        midd.next = null;
        ListNode head2 = reverseList(midNext);

        ListNode cur = head;
        while (head2!=null){ /**这里的逻辑有一点绕*/
            ListNode oriNext = cur.next;
            ListNode next = head2.next;
            cur.next = head2;
            head2.next = oriNext;
            oriNext.next = next;
            cur = cur.next.next;
            head2 = head2.next.next;
        }
    }

    private ListNode reverseList(ListNode midNext) {
        ListNode pre = null,cur = midNext;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMidd(ListNode head) {
        ListNode slow =head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast =fast.next.next;
        }
        return slow;
    }


    /*662
    给你一棵二叉树的根节点 root ，返回树的 最大宽度 。树的 最大宽度 是所有层中最大的 宽度 。
    每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构
    相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
    题目数据保证答案将会在  32 位 带符号整数范围内。
    * */
    public int widthOfBinaryTree(TreeNode root) {
        int res = 0;
        if (root==null) return 0;
        LinkedList<TreeNode> deque = new LinkedList<>();
        LinkedList<Integer> integers = new LinkedList<>();
        deque.offer(root);
        integers.offer(0);
        while (!deque.isEmpty()){
            int size = deque.size();
            int left = 0,right = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curnode = deque.poll();
                Integer curVal = integers.poll();
                if (i==0) left = curVal;
                if (curnode.left!=null){
                    deque.offer(curnode.left);
                    integers.offer(curVal*2);
                }
                if (curnode.right!=null){
                    deque.offer(curnode.right);
                    integers.offer(curVal*2+1);
                }
                if (i==size-1) right = curVal;
            }
            res  =Math.max(res,right-left+1);
        }
        return res;
    }


    /*113
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
    叶子节点 是指没有子节点的节点。
    * */
    LinkedList<List<Integer>> resPathSum;
    LinkedList<Integer> pathPathSum;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum);
        return resPathSum;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root==null){
            if (targetSum==0) resPathSum.add(new LinkedList<>(pathPathSum));
            return;
        }
//        if (root.left==null&&root.right==null){
//            if (targetSum==0){
//                resPathSum.add(new LinkedList<>(pathPathSum));
//            }
//            return;
//        }
        dfs(root.left,targetSum-root.val);
        dfs(root.right,targetSum-root.val);
    }


   /*112
   给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条
   路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。叶子节点 是指没有子节
   点的节点。
   */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null&&targetSum==0) return true;
        if (root==null) return false;
        return hasPathSum(root.left,targetSum-root.val)||
                hasPathSum(root.right,targetSum-root.val);
    }

    /*437,与560题是类似的
    给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
    路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
    * */
//    public int pathSum_437(TreeNode root, int targetSum) {
//
//    }

    /**看一下17题StringBuilder声明为全局变量、声明在形参位置时，代码的区别*/

    /*105.
     * 从前序 和 中序 构造出二叉树*/
    HashMap<Integer,Integer> inorderMap;
    int preorderIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i <inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int left, int right) {
        int val = preorder[preorderIndex++];
        Integer index = inorderMap.get(val);
        TreeNode root = new TreeNode(val);
        root.left = buildTree(preorder,inorder,left,index-1);
        root.right = buildTree(preorder,inorder,index+1,right);
        return root;
    }


    /*61
    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null||k==0) return head;
        /*step1：*/
        int size = 1;
        ListNode cur = head;
        while (cur.next!=null){
            cur = cur.next;
            size++;
        }

        cur.next = head;

        k %= size;
        cur = head;
        for (int i = 0; i < size - k -1; i++) {
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
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty()&&c-'0'<stack.peekLast()-'0'&&k>0){
                k--;
                stack.pollLast();
            }
            stack.offerLast(c);
        }

        while (k>0){
            k--;
            stack.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            Character c = stack.pollFirst();
            if (sb.length()==0&&c=='0') continue;
            sb.append(c);
        }
        return sb.toString()==""?"0":sb.toString();
    }

    /*145
    给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
    * */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.left!=null){
                stack.push(cur.left);
            }
            if (cur.right!=null){
                stack.push(cur.right);
            }
        }
        Collections.reverse(res); /**注意：这个方法是原地翻转，并且返回值是void，因此最后还是得返回res*/
        return res;
    }


    /*328
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */
//    public ListNode oddEvenList(ListNode head) {
//
//    }

    /*135
    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

    你需要按照以下要求，给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */
    public int candy(int[] ratings) {
        int[] dp = new int[ratings.length];
        Arrays.fill(dp,1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i]>ratings[i-1]){
                dp[i] = Math.max(dp[i-1]+1,dp[i]);
            }
        }

        for (int i = ratings.length-2; i >=0 ; i--) {
            if (ratings[i]>ratings[i+1]){
                dp[i] = Math.max(dp[i],dp[i+1]+1);
            }
        }

        int sum =0;
        for (int num:dp){
            sum += num;
        }
        return sum;
    }


    /*134
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0,curSum = 0;
        int res = 0;
        for (int i = 0; i < gas.length; i++) {
            total += (gas[i]-cost[i]);
            curSum += (gas[i]-cost[i]);
            if (curSum<0){
                curSum= 0;
                res = i+1;
            }
        }
        return total<0?-1:res;
    }


    /**
     * ===========================================================7.25===============================================
     */
    /*LCR 155. 将二叉搜索树转化为排序的双向链表
    将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     */
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
    /**这个题最后需要返回，双向链表。因此尽量不要使用虚拟头节点*/
    public Node treeToDoublyList(Node root) {
        Node cur = root;
        Node pre = null;
        Stack<Node> stack = new Stack<>();
        while (cur!=null||!stack.isEmpty()){
            if (cur!=null){
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                if (pre!=null){
                   cur.left = pre;
                   pre.right = cur;
                }else{
//                    cur = pre;
                }
                pre = cur;
                cur = cur.left;
            }
        }
        root.left = pre;
        pre.right = root;

        return root;
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
        int low = 0,high = 0;
        for (char c:s.toCharArray()){
            if (c=='('){
                low++;
                high++;
            } else if (c==')') {
                low--;
                high--;
            }else {
                low--;
                high++;
            }
            if (high<0) return false;
            /**这里写的代码和原始方案是不一样的，确认一下是不是可行，no*/
        }
        return low<=0;
    }

    public static void main(String[] args) {
        review06 review06 = new review06();
    }

    //https://mp.weixin.qq.com/s?__biz=MzA4NDE4MzY2MA==&mid=2647522825&idx=1&sn=302c27da39845ab4952a2de067cfdea8&scene=21&poc_token=HGZug2ij4wx7mT7pbRLRTaHh3HHWsK6WRmMarBm3
}
