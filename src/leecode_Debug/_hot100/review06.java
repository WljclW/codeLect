package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;
import org.w3c.dom.ls.LSInput;

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
    public Node copyRandomList(Node head) {
        if (head==null) return head;
        Node cur = head;
        while (cur!=null){
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }

        cur = head;
        while (cur!=null){
            if (cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        Node res = head.next,now = res;
        Node origin = head;
        while (now.next!=null){
            origin.next = origin.next.next;
            origin = origin.next;

            now.next = now.next.next;
            now = now.next;
        }
        origin.next = null;
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
}
