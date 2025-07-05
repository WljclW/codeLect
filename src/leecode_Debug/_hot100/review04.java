package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;

import java.util.*;

public class review04 {
    /*49.
   * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
   字母异位词 是由重新排列源单词的所有字母得到的一个新单词。*/
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String flag = new String(chars);
            if (!map.containsKey(flag)){
                LinkedList<String> ls = new LinkedList<>();
                map.put(flag,ls);
            }
           map.get(flag).add(str);
        }
        return new LinkedList<>(map.values());
    }

    /*128.
    * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。*/
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
        int res = 0;

        for (int num:nums){
            if (!set.contains(num-1)){
                int total = 0;
                while(set.contains(num+total)) total++;
                res = Math.max(res,total);
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
                nums[left++]  =nums[right++];
            }else {
                right++;
            }
        }

        for (int i=left;i<nums.length;i++){
            nums[i] = 0;
        }
    }


    /*
     * 11.给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
     *    成的，柱子之间是没有间隙的。
     * */
    public int maxArea(int[] height) {
        int left  =0,right = height.length-1;
        int res = 0;
        while(left<right){
            int curVal = Math.min(height[left],height[right])*(right-left);
            res = Math.max(res,curVal);
            if (height[left]<height[right]) left++;
            else right--;
        }
        return res;
    }


    /*
     * 15.给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * 【】：关键的步骤在于双指针 过程中，如何跳过所有相同的元素。
     * */
    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            if (i>0&&nums[i]==nums[i-1]) continue;
            int l = i+1, r = nums.length-1;
            while (l<r){
                int curSum = nums[i]+nums[l]+nums[r];
                if (curSum>0){
                    r--;
                }else if (curSum<0){
                    l++;
                }else{
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    l++;
                    r--;
                    while (l<r&&nums[l]==nums[l-1]) l++;
                    while (l<r&&nums[r]==nums[r+1]) r--;
                }
            }
        }
        return res;
    }


    /*
     * 3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。无重复字符的最长子串
     * */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left =0;
        int res = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
            while (map.get(c)>1){
                char lChar = s.charAt(left);
                map.put(lChar,map.get(lChar)-1);
                left++;
            }
            res = Math.max(res,i-left+1);
        }
        return res;
    }

    /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> DouQueue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!DouQueue.isEmpty()&&nums[i]>=nums[DouQueue.peekLast()]){
                DouQueue.pollLast();
            }
            DouQueue.offerLast(i);
        }
        res[0] = nums[DouQueue.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!DouQueue.isEmpty()&&nums[i]>=nums[DouQueue.peekLast()]){
                DouQueue.pollLast();
            }
            DouQueue.offerLast(i);
            if (DouQueue.peekFirst()==i-k) DouQueue.pollFirst();
            res[i-k+1] = nums[DouQueue.peekFirst()];
        }
        return res;
    }

    /*560.
    * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int pre = 0;
        int res = 0;
        for (int num:nums){
            pre += num;
            res += map.getOrDefault(pre-k,0);
        }
        return res;
    }

    /*53.
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元
     *   素），返回其最大和。( 子数组是数组中的一个连续部分)
     * */
    public int maxSubArray(int[] nums) {
        int res  = nums[0];
        int curSum = nums[0];
        for (int i=1;i<nums.length;i++){
            curSum = Math.max(curSum+nums[i],nums[i]);
            res = Math.max(curSum,res);
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

        for (int i=1;i<intervals.length;i++){
            int[] cur = intervals[i];
            if (cur[0]<=res.getLast()[1]){
                res.getLast()[1] = Math.max(cur[1],res.getLast()[1]);
            }else{
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /*238.
    * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素
    * 的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    * */
    public int[] productExceptSelf(int[] nums){
        int[] res = new int[nums.length];
        res[0] =1;
        for (int i=1;i< nums.length;i++){
            res[i] = nums[i-1]*res[i-1];
        }

        int post = 1;
        for (int i = nums.length-1;i>=0;i--){
            res[i] *= post;
            post *= nums[i];
        }
        return res;
    }

    /*41.
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while(nums[i]>0&&nums[i]<=nums.length&&nums[i]!=nums[nums[i]-1]){
                swap(nums,i,nums[i]-1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp  =nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /*48.
    * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。*/
    public void rotate(int[][] matrix) {
        for (int i=0;i<matrix.length;i++)
            for (int j=0;j<i;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }

        for (int i=0;i<matrix.length;i++)
            for (int j = 0; j < matrix[0].length / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length-1-j];
                matrix[i][matrix[0].length-1-j] = tmp;
            }
    }

    /*240.
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int i = 0,j = n-1;
        while (i<matrix.length&&j>=0){
            int cur = matrix[i][j];
            if (target<cur){
                j--;
            }else if (target>cur){
                i++;
            }else{
                return true;
            }
        }
        return false;
    }

    /*160.
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果
     * 两个链表不存在相交节点，返回 null 。保证不出现环*/
    /**为什么下面的写法是错误的？？？？？*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA,p2 = headB;
        while (p1!=p2){
            p1 = (p1==null)?headB:p1.next;
            p2 = (p2==null)?headA:p2.next;
        }
        return p1;
    }

    /*206.
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。*/
    public ListNode reverseList(ListNode head) {
        ListNode pre = null,cur = head;
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
        ListNode head2 = extractedReverse(head);
        while (head2!=null){
            if (head2.val!=head.val) return false;
        }
        return true;
    }

    private ListNode extractedReverse(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*142.
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast){
                fast = head;
                while (slow!=fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }


    /*2.
    * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = (l1==null)?0:l1.val;
            int val2 = (l2==null)?0:l2.val;
            int curSum = val1+val2+carry;
            carry = curSum/10;
            ListNode now = new ListNode(curSum % 10);
            cur.next = now;
            cur = cur.next;
        }
        return dummy.next;
    }

    /*19.
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy,fast = head;
        for (int i=0;i<n;i++){
            fast = fast.next;
        }

        while(fast!=null){
            slow =slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }


    /*24.
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下
     * 完成本题（即，只能进行节点交换）。*/
    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
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

    /*25.
    * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
    k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。*/
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head),end = dummy,pre = dummy;
        while (end.next!=null){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end==null) break;

            ListNode start = pre.next;
            ListNode nextStart = end.next;

            end.next = null;
            pre.next = reverseGroup(start);
            start.next = nextStart;

            pre = start;
            end = start;
        }
        return dummy.next;
    }

    private ListNode reverseGroup(ListNode start) {
        ListNode pre = null,cur = start;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /*138*/
    public Node copyRandomList(Node head) {
        if (head==null) return null;

        Node cur = head;
        while (cur!=null){
            Node newNode = new Node(cur.val);
            cur.next  = newNode;
            cur = cur.next.next;
        }

        cur = head;
        while (cur!=null){
            if (cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur =cur.next.next;
        }

        cur = head;
        Node dummy = new Node(-1),newHead = dummy;
        while (cur!=null){
            Node newNode = cur.next;
            cur.next = newNode.next;
            newHead.next = newNode;
        }
        return dummy.next;
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
        return merge(lists,0,lists.length-1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l==r) return lists[l];
        int mid = l+(r-l)/2;
        ListNode left = merge(lists, l, mid);
        ListNode right = merge(lists, mid + 1, r);
        return mergeTwo(left,right);
    }

    private ListNode mergeTwo(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next =  left;
                left = left.next;
            }else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = (left==null)?right:left;
        return dummy.next;
    }


        class LRUCache {
            class DouListNode{
                DouListNode pre;
                DouListNode next;
                int key;
                int value;
                public DouListNode(){}
                public DouListNode(int key,int value){
                    this.key = key;
                    this.value = value;
                }
            }

            int size;
            int capacity;
            HashMap<Integer,DouListNode> map = new HashMap<Integer,DouListNode>();
            DouListNode head;
            DouListNode tail;


            public LRUCache(int capacity) {
                this.size = 0;
                this.capacity = capacity;
                 head = new DouListNode();
                 tail = new DouListNode();
                head.next = tail;
                tail.pre = head;
            }

            public int get(int key) {
                if (map.get(key)==null){
                    return -1;
                }else{
                    DouListNode target = map.get(key);
                    moveToHead(target);
                    return target.value;
                }
            }

            private void moveToHead(DouListNode target) {
                removeNode(target);
                addToHead(target);
            }

            private void addToHead(DouListNode target) {
                DouListNode headNext = head.next;
                head.next = target;
                target.next = headNext;
                headNext.pre = target;
                target.pre = head;
            }

            private void removeNode(DouListNode target) {
                DouListNode preNode = target.pre;
                DouListNode nextNode = target.next;
                preNode.next = nextNode;
                nextNode.pre = preNode;
            }

            public void put(int key, int value) {
                if (map.get(key)==null){
                    DouListNode node = new DouListNode(key, value);
                    map.put(key,node);
                    addToHead(node);
                    if (this.size>capacity){
                        DouListNode last = this.tail.pre;
                        removeNode(last);
                        map.remove(last.key);
                    }
                }else{
                    DouListNode target = map.get(key);
                    target.value = value;
                    moveToHead(target);
                }
            }
        }


    /*
     * 42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * */
    public int trap(int[] height) {
        int left = 0,right = height.length-1;
        int leftMax = 0,rightMax = 0;
        int res = 0;
        while (left<right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
            if (height[left]<height[right]){
                res += (leftMax-height[left]);
                left++;
            }else{
                res += (rightMax-height[right]);
                right--;
            }
        }
        return res;
    }


    class MinStack {
        Stack<Integer> allStack;
        Stack<Integer> minStack;
        public MinStack() {
            allStack = new Stack<Integer>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            allStack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            Integer pop = allStack.pop();
            if (minStack.peek().equals(pop)){
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
        Stack<String> strStack = new Stack<>();
        Stack<Integer> digitStack = new Stack<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (c>='0'&&c<='9') multi = multi*10+(int)(c-'0');
            else if (c=='['){
                strStack.push(new String(res));
//                strStack.push(res.toString());
                digitStack.push(multi);
                res = new StringBuilder();
                multi = 0;
            }else if (c==']'){
                Integer total = digitStack.pop();
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < total; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder(strStack.pop()+tmp);
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }

    /*739.
    给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第
     i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    * */
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


    /*84.
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
    * */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int curHeight = (i== heights.length)?0:heights[i];

            while (!stack.isEmpty()&&curHeight<heights[stack.peek()]){
                Integer cur = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                int curArea  =heights[cur]*(i-left-1);
                res = Math.max(res,curArea);
            }
            stack.push(i);
        }
        return res;
    }


    /*121.
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一
        个算法来计算你所能获取的最大利润。
      返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    * */
    public int maxProfit(int[] prices) {
        int res = 0;
        int flag = Integer.MAX_VALUE;
        for (int i = 0; i <prices.length; i++) {
            if (prices[i]<flag){
                flag = prices[i];
            }
            res = Math.max(res,prices[i]-flag);
        }
        return res;
    }

    /*
    * 55.
    * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在
    * 该位置可以跳跃的最大长度。
        判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
    * */
    public boolean canJump(int[] nums) {
        int bound = 0,cur = 0;
        while (cur<=bound){
            bound = Math.max(cur+nums[cur],bound);
            if (bound>=nums.length-1) return true;
        }
        return false;
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
        int step = 0,maxPosition = 0,bound = 0;
        for (int i = 0; i <=bound; i++) {
            maxPosition = Math.max(maxPosition,i+nums[i]);
            if (bound==i){
                step++;
                bound = maxPosition;
            }
            if (bound>=nums.length-1) return step;
        }
        return -1;
    }


    /*763.
    * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片
    * 段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或
    * ["ab", "ab", "cc"] 的划分是非法的。
        注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
        返回一个表示每个字符串片段的长度的列表。
    * */
    public List<Integer> partitionLabels1(String s) {
        int[] flags = new int[26];
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            flags[c-'a'] = i;
        }

        int left = -1,bound = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            bound = Math.max(bound,flags[c-'a']);
            if (i==bound){
                res.add(i-left);
                left = i;
            }
        }
        return res;
    }

    //多数元素169
    public int majorityElement(int[] nums) {
        int ticket = 0,flag = -1;
        for (int num:nums){
            if (ticket==0) flag = num;
            ticket += (flag==num)?1:-1;
        }
        return flag;
    }

    //75
    public void sortColors(int[] nums) {
        int left = 0,right = nums.length-1,cur = 0;
        while (cur<=right){
            if (nums[cur]==0){
                swapColor(nums,left++,cur++);
            }else if (nums[cur]==1){
                cur++;
            }else {
                swapColor(nums,cur,right--);
            }
        }
    }

    private void swapColor(int[] nums, int left, int right) {
        int tmp  =nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    /*31.下一个排列*/
    public void nextPermutation(int[] nums) {
        int right = nums.length-1;
        if (right>=0){
            while (right>0&&nums[right]<=nums[right-1]){
                right--;
            }
            if (right==0){
                reverNums(nums,0);
                return;
            }
            int flag = right-1;
            right = nums.length-1;
            while (right>flag){
                if (nums[right]>nums[flag]){
                    break;
                }
                right--;
            }
            int tmp = nums[flag];
            nums[flag] = nums[right];
            nums[right] = tmp;
            reverNums(nums,flag+1);
        }
    }

    private void reverNums(int[] nums, int left) {
        int right = nums.length-1;
        while (left<right){
            int tmp = nums[left];
            nums[left] =  nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    /*62.
    * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标
    * 记为 “Finish” ）。
    问总共有多少条不同的路径？*/
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0||j==0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /*64.
    * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路
    * 径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。*/
    public int minPathSum(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0]  =dp[i-1][0]+grid[i][0];
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = dp[0][i-1]+grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
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
        int m = text1.length(),n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i==0||j==0){
                    dp[i][j] = 0;
                }else {
                    if (text1.charAt(i-1)==text2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1]+1;
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                    }
                }
            }
        }
        return dp[m][n];
    }

    //75编辑距离
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i==0||j==0){
                    dp[i][j] = (i==0)?j:i;
                }else {
                    if (word1.charAt(i-1)==word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j]  =Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    }
                }
            }
        }
        return dp[m][n];
    }

    /*139.
    * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利
    * 用字典中出现的一个或多个单词拼接出 s 则返回 true。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] flag = new boolean[s.length() + 1];
        flag[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (flag[j]&&set.contains(s.substring(j,i))){
                    flag[i] = true;
                    break;
                }
            }
        }
        return flag[s.length()];
    }

    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int minMulti = nums[0],maxMulti = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minMulti = Math.min(Math.min(minMulti*nums[i],maxMulti*nums[i]),nums[i]);
            maxMulti = Math.max(Math.max(minMulti*nums[i],maxMulti*nums[i]),nums[i]);
            res = Math.max(res,maxMulti);
        }
        return res;
    }

    /*416.
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两
     * 个子集，使得两个子集的元素和相等。*/
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num:nums){
            sum += num;
        }
        if (sum%2!=0){
            return false;
        }
        sum /=2;

        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >=nums[i]; j--) {
                dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
        }
        return dp[sum]==sum;
    }

    /*32.
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的
     * 长度。*/
    public int longestValidParentheses(String s) {
        int m = s.length();
        int[] dp = new int[m];
        int res = 0;
        for (int i = 1; i < m; i++) {
            if (s.charAt(i)==')'){
                if (s.charAt(i-1)=='('){
                    dp[i] = (i-2>=0)?dp[i-2]+2:2;
                }else if (dp[i-1]!=0){
                    int pre = dp[i-1];
                    if (i-pre-1>=0&&s.charAt(i-pre-1)=='('){
                        dp[i] = (i-pre-2>0)?dp[i-pre-2]+2+dp[i-1]:dp[i-1]+2;
                    }
                }
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }


    public List<List<Integer>> generate(int numRows) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>(Arrays.asList(1)));
        if (numRows==1) return res;
        res.add(new LinkedList<>(Arrays.asList(1,1)));
        if (numRows==2) return res;

        for (int i = 3; i <= numRows; i++) {
            LinkedList<Integer> ele = new LinkedList<>();
            List<Integer> last = res.getLast();
            for (int j = 0; j < i; j++) {
                int leftVal = (j-1<0)?0:last.get(j-1);
                int rightVal = (j>=last.size())?0:last.get(j);
                ele.add(leftVal+rightVal);
            }
            res.add(ele);
        }
        return res;
    }


    /*35.
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    请必须使用时间复杂度为 O(log n) 的算法。
    * */
    public int searchInsert(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>target){
                left = mid+1;
            }else if (nums[mid]<=target){
                right = mid-1;
            }
        }
        return left;
    }


    /*74.
    给你一个满足下述两条属性的 m x n 整数矩阵：
    每行中的整数从左到右按非严格递增顺序排列。
    每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
    * */
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int left = 0,right = m*n-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur>target){
                right = mid-1;
            }else if (cur<target){
                left = mid+1;
            }else{
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
            if (nums[mid]>=nums[0]){
                if (target>=nums[0]&&target<nums[mid]){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }else {
                if (target>nums[mid]&&target<nums[nums.length-1]){
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
        int min = 0;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>nums[nums.length-1]){
                left = mid+1;
            }else{
                min = mid;
                right = mid-1;
            }
        }
        return nums[min];
    }

    //寻找第一次和最后一次的位置
//    public int[] searchRange(int[] nums, int target) {
//        int left = searchLeft(nums, target);
//        if (left== nums.length||nums[left]!=target){
//            return new int[]{-1,-1};
//        }
//        int right = searchLeft(nums,target+1);
//        return new int[]{left,right-1};
//    }
//
//    private int searchLeft(int[] nums, int target) {
//        int left = 0,right = nums.length-1;
//        while (left<=right){
//            int mid = left +(right-left)/2;
//            if (nums[mid]<target) left=mid+1;
//            else right = mid-1;
//        }
//        return left;
//    }


    public int[] searchRange(int[] nums, int target) {
        int left = searchL(nums, target);
        if (left==nums.length||nums[left]!=target){
            return new int[]{-1,-1};
        }
        int right = searchR(nums, target);
        return new int[]{left,right};
    }

    private int searchL(int[] nums, int target) {
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

    private int searchR(int[] nums, int target) {
        int l =0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]>target){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return r;
    }


    public static void main(String[] args) {
        review04 review04 = new review04();
//        review04.longestValidParentheses("(()())");

        review04.findMin(new int[]{3,4,5,1,2});
    }
}
