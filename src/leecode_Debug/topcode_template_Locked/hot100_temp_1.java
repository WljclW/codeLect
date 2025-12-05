package leecode_Debug.topcode_template_Locked;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * 使用时复制一份！！！！！！！
 */
public class hot100_temp_1 {
    /*
    1. 两数之和
    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

    你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

    你可以按任意顺序返回答案。
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return new int[]{i,map.get(nums[i])};
            }
            map.put(target-nums[i],i);
        }
        return new int[]{-1,-1};
    }

    /*
    49. 字母异位词分组
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     */
//    public List<List<String>> groupAnagrams(String[] strs) {
//
//    }

    /*
    128. 最长连续序列
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num:nums){
            set.add(num);
        }

        for (int num:set){
            if (!set.contains(num-1)){
                int len = 0;
                while (set.contains(num+len)) len++;
                res = Math.max(res,len);
            }
        }
        return res;
    }


    /*
    283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public void moveZeroes(int[] nums) {
        int left = 0,cur = 0;
        while (cur<nums.length){
            if (nums[cur]!=0){
                swap1(nums,left++,cur);
            }
            cur++;
        }
    }

    private void swap1(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] =nums[r];
        nums[r]  =tmp;
    }


    /*
    11. 盛最多水的容器
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。
     */
    public int trap(int[] height) {
        int left = 0,right = height.length-1;
        int res =0;
        while (left<right){
            if (height[left]>height[right]){
                /**记得有一种写法可以把++直接河道第一个表达式？？？*/
                int cur = height[right]*(right-left);
                right--;
                res = Math.max(res,cur);
            }else {
                int cur = height[left]*(right-left);
                left++;
                res =Math.max(res,cur);
            }
        }
        return res;
    }

    /*
    15. 三数之和
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i>0&&nums[i]==nums[i-1]) continue;
            int left = i+1,right = nums.length-1;
            while (left<right){
                int curSum = nums[i]+nums[left]+nums[right];
                if (curSum>0){
                    right--;
                } else if (curSum < 0) {
                    left++;
                }else {
                    LinkedList<Integer> ele = new LinkedList<>(Arrays.asList(nums[i], nums[left], nums[right]));
                    res.add(ele);
                    while (left<right&&nums[left]==nums[++left]);
                    while (left<right&&nums[right]==nums[--right]);
                }
            }
        }

        return res;
    }

    /*
    42. 接雨水
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
//    public int trap(int[] height) {
//        int left = 0,right = height.length-1;
//        int leftMax = 0,rightMax = 0;
//        int res = 0;
//        while (left<right){
//            leftMax = Math.max(height[left],leftMax);
//            rightMax = Math.max(height[right],rightMax);
//            if (height[left]<height[right]){
//                /**这里理论上应该用两边最大高度 的 最小值来作为高度的边界。有一个优化：如果"height[left]<height[right]",则必有“leftMax≤rightMax”*/
//                res += (leftMax-height[left++]);
//            }else {
//                res += (rightMax-height[right--]);
//            }
//        }
//        return res;
//    }


    /*
    3. 无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0,res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
            while (map.get(c)>1){
                char cleft = s.charAt(left);
                map.put(cleft,map.get(cleft)-1);
                left++;
            }
            res = Math.max(res,i-left+1);
        }
        return res;
    }


    /*
    438. 找到字符串中所有字母异位词
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     */
//    public List<Integer> findAnagrams(String s, String p) {
//
//    }

    /*
    560. 和为 K 的子数组
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。
     */
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0,res = 0;
        for (int num:nums){
            preSum += num;
            res += map.getOrDefault(preSum-k,0);
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return res;
    }

    /*
    239. 滑动窗口最大值
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回 滑动窗口中的最大值 。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty()&&nums[i]>=nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        res[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty()&&nums[i]>=nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if (deque.peekFirst().intValue()==i-k) deque.pollFirst();
            res[i-k+1] = nums[deque.peekFirst()];
        }

        return res;
    }

    /*
    76. 最小覆盖子串
给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。

测试用例保证答案唯一。
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        HashMap<Character, Integer> window = new HashMap<>();
        int start = -1,len = Integer.MAX_VALUE;
        int valid = 0,left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c).intValue()==need.get(c)){
                    valid++;
                }
            }
            while (valid==need.size()){
                if (i-left+1<len){
                    len = i-left+1;
                    start = left;
                }
                char c1 = s.charAt(left++);
                if (need.containsKey(c1)){
                    window.put(c1,window.get(c1)-1);
                    if (window.get(c1)<need.get(c1)){
                        valid--;
                    }
                }
            }
        }
        return start==-1?"":s.substring(start,start+len);
    }


    /*
    53. 最大子数组和
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组是数组中的一个连续部分。
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0],preSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            preSum =Math.max(preSum+nums[i],nums[i]);
            res = Math.max(res,preSum);
        }
        return res;
    }

    /*
    56. 合并区间
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->{
            return a[0]-b[0];
        });
        LinkedList<int[]> res = new LinkedList<>();
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


    /*
    189. 轮转数组
给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverArr(nums,0,nums.length-1);
        reverArr(nums,0,k-1);
        reverArr(nums,k,nums.length-1);
    }

    private void reverArr(int[] nums, int left, int right) {
        while (left<right){
            int tmp  = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    /*
    238. 除自身以外数组的乘积
给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。

题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。

请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1]*nums[i-1];
        }

        int post = 1;
        for (int i = nums.length-1; i >=0; i--) {
            res[i] *= post;
            post *= nums[i];
        }

        return res;
    }


    /*
    41. 缺失的第一个正数
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i]>0&&nums[i]<=nums.length&&nums[nums[i]-1]!=nums[i]){
                swap2(nums,nums[i]-1,i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=i+1) return i+1;
        }
        return nums.length+1;
    }

    private void swap2(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    /*
    73. 矩阵置零
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     */
    public void setZeroes(int[][] matrix) {
        boolean firRow = false,firCol = false;
        int m = matrix.length,n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0]==0) firCol  =true;
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i]==0) firRow = true;
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
                if (matrix[i][0]==0||matrix[0][j]==0)
                    matrix[i][j] = 0;
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

    /*
    54. 螺旋矩阵
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0,left = 0,bottom = matrix.length-1,right = matrix[0].length-1;
        LinkedList<Integer> res = new LinkedList<>();
        while (true){
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            if (++top>bottom) break;

            for (int i = top; i <=bottom ; i++) {
                res.add(matrix[i][right]);
            }
            if (--right<left) break;

            for (int i = right; i >=left ; i--) {
                res.add(matrix[bottom][i]);
            }
            if (--bottom>top) break;

            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            if (++left>right) break;
        }
        return res;
    }

    /*
    48. 旋转图像
给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。

你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     */
    public void rotate(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }

    /*
    240. 搜索二维矩阵 II
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int i = 0,j = n-1;
        while (i<m&&j>=0){
            int cur = matrix[i][j];
            if (cur>target){
                j--;
            } else if (cur < target) {
                i++;
            }else {
                return true;
            }
        }
        return false;
    }



    /*
    160. 相交链表
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA,p2 = headB;
        while (p1!=p2){
            p1 = p1==null?headB:p1.next;
            p2 = p2==null?headA:p2.next;
        }
        return p1;
    }


    /*
    206. 反转链表
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next =pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /*
    234. 回文链表
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(ListNode head) {
        ListNode mid = findMid(head);
        ListNode head2 = rever1(mid);
        while (head2!=null){
            if (head.val!=head2.val) return false;
            head2 = head2.next;
            head = head.next;
        }
        return true;
    }

    private ListNode rever1(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre=  cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow=  head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /*
    141. 环形链表
给你一个链表的头节点 head ，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。

如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast) return true;
        }
        return false;
    }


    /*
    142. 环形链表 II
给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

不允许修改 链表。
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast==slow){
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
    21. 合并两个有序链表
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (list1!=null&&list2!=null){
            if (list1.val<list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1==null?list2:list1;
        return dummy.next;
    }


    /*
    2. 两数相加
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curSum = val2+val1+carry;
            cur.next = new ListNode(curSum%10);
            cur  =cur.next;
            carry = curSum/10;
            l1 = l1==null?l1:l1.next;
            l2 = l2==null?l2:l2.next;
        }
        return dummy.next;
    }


    /*
    19. 删除链表的倒数第 N 个结点
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        /**注意：这里的起始位置不一样，如果起始位置一样的话，那dummy的意义就几乎没了*/
        ListNode slow = dummy,fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


    /*
    24. 两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;
            node1.next = node2.next;
            node2.next = node1;
            cur.next=  node2;

            cur = node1; /**或者使用“cur = cur.next.next”，也是OK的应该*/
        }
        return dummy.next;
    }


    /*
    25. K 个一组翻转链表
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1,head),pre = dummy,end = dummy;
        while (end.next!=null){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end==null) break;

            ListNode nextStart = end.next;
            end.next = null;
            ListNode thisStart = pre.next;
            pre.next = rever(thisStart);
            thisStart.next = nextStart;

            pre = thisStart;
            end = thisStart;
        }
        return dummy.next;
    }

    private ListNode rever(ListNode end) {
        ListNode pre = null,cur = end;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /*
    138. 随机链表的复制
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。

例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

返回复制链表的头节点。
     */

// Definition for a Node.
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


    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = cur.next.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        cur = head;
        Node res = head.next, even = head.next;
        while (even != null && even.next != null) {
            cur.next = cur.next.next;
            cur = cur.next;

            even.next = even.next.next;
            even = even.next;
        }
        /**出了这里的while循环应该要特殊处理“cur.next = null”？？还是说这道题原数组改变了没关系*/
        return res;
    }



    /*
    148. 排序链表
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     */
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode mid = findMidRel(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return mergerTwo(left,right);
    }

    private ListNode mergerTwo(ListNode left, ListNode right) {
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
        cur.next = left==null?right:left;
        return dummy.next;
    }

    private ListNode findMidRel(ListNode head) {
        ListNode slow=  head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow =slow.next;
            fast = fast.next.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }


    /*
    23. 合并 K 个升序链表
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        return mergeTwoLists(lists,0,lists.length-1);
    }

    private ListNode mergeTwoLists(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = left+(right-left)/2;
        ListNode leftList = mergeTwoLists(lists, left, mid);
        ListNode rightList = mergeTwoLists(lists, mid + 1, right);
        return mergeT(leftList,rightList);
    }

    private ListNode mergeT(ListNode leftList, ListNode rightList) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (leftList!=null&&rightList!=null){
            if (leftList.val<rightList.val){
                cur.next = leftList;
                leftList = leftList.next;
            }else {
                cur.next = rightList;
                rightList = rightList.next;
            }
            cur  =cur.next;
        }
        return dummy.next;
    }



    /*
    146. LRU 缓存
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     */
    class LRUCache {
        class DouNode{
            DouNode prev;
            DouNode next;
            int key;
            int value;
            public DouNode(){}
            public DouNode(int key,int value){
                this.key = key;
                this.value = value;
            }
        }

        HashMap<Integer,DouNode> map;
        int capacity;
        int size;
        DouNode head;
        DouNode tail;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head = new DouNode();
            tail = new DouNode();
            head.next = tail;
            tail.prev = head;
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

        private void moveToHead(DouNode node) {
            removeNode(node);
            addToHead(node);
        }

        public void put(int key, int value) {
            DouNode node = map.get(key);
            if (node!=null){
                node.value = value;
                moveToHead(node);
            }else {
                DouNode newNode = new DouNode(key,value);
                addToHead(newNode);
                map.put(key,newNode);
                size++;
                if (size>capacity){
                    DouNode relTail = tail.prev;
                    removeNode(relTail);
                    size--;
                    map.remove(relTail.key);
                }
            }
        }

        /**看一下下面的方法有没有问题？？面试的时候如果不确认，就先使用额外的变量来记录“node的前一个结点、后一个节点”，然后再进行删除拼接节点*/
        private void removeNode(DouNode node) {
            node.prev.next = node.next;
            node.next = node.prev.next;
        }

        private void addToHead(DouNode newNode) {
            newNode.next = head.next;
            head.next = newNode;
            newNode.prev = head;
            newNode.next.prev = newNode;
        }
    }



    /*
    94. 二叉树的中序遍历
给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> res = new LinkedList<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root=  root.left;
            }else {
                TreeNode cur = stack.pop();
                root = cur.right;
                res.add(cur.val);
            }
        }
        return res;
    }

    List<Integer> resInorder;
    public List<Integer> inorderTraversal_(TreeNode root) {
        resInorder = new LinkedList<>();
        if (root==null) return resInorder;
        dfs1(root);
        return resInorder;
    }

    private void dfs1(TreeNode root) {
        dfs1(root.left);
        resInorder.add(root.val);
        dfs1(root.right);
    }


    /*
    104. 二叉树的最大深度
给定一个二叉树 root ，返回其最大深度。

二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     */
    public int maxDepth(TreeNode root) {
        if (root==null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right)+1;
    }
    public int maxDepth_(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        int res = 0;
        if (root==null) return 0;
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
            }
        }
        return res;
    }


    /*
    226. 翻转二叉树
给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     */
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return root;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right  =left;
        return root;
    }


    public TreeNode invertTree_(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        if (root==null) return root;
        deque.offer(root);
        while (!deque.isEmpty()){
            TreeNode cur = deque.poll();
            swap3(cur);
            if (cur.left!=null) deque.offer(cur.left);
            if (cur.right!=null) deque.offer(cur.right);
        }
        return root;
    }

    private void swap3(TreeNode cur) {
        TreeNode tmp = cur.left;
        cur.left = cur.right;
        cur.right = tmp;
    }


    /*
    101. 对称二叉树
给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        return isSymmetric(root.left,root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left==null&&right==null) return true;
        if (left==null||right==null) return false;
        return left.val==right.val&&isSymmetric(left.left,right.right)&&isSymmetric(left.right,right.left);
    }


    /*
    543. 二叉树的直径
给你一棵二叉树的根节点，返回该树的 直径 。

二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。

两节点之间路径的 长度 由它们之间边数表示。
     */
    int resDiameterOfBinaryTree = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        dfs2(root);
        return resDiameterOfBinaryTree;
    }

    private int dfs2(TreeNode root) {
        if (root==null) return 0;
        int left = dfs2(root.left);
        int right = dfs2(root.right);
        resDiameterOfBinaryTree  =Math.max(resDiameterOfBinaryTree,left+right);
        return Math.max(left,right)+1;
    }


    /*
    102. 二叉树的层序遍历
给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            LinkedList<Integer> ele = new LinkedList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                ele.add(cur.val);
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
            }
            res.add(ele);
        }

        return res;
    }


    /*
    108. 将有序数组转换为二叉搜索树
给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        /**调用的方法的含义：使用nums数组[0,nums.length-1]这区间内的数构建出二叉搜索树并返回*/
        return sortedArrayToBST(nums,0,nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left>right) return null;
        if (left==right) return new TreeNode(nums[left]);
        int mid = left+(right-left)/2;
        TreeNode root = new TreeNode(mid);
        root.left = sortedArrayToBST(nums,left,mid-1);
        root.right  =sortedArrayToBST(nums,mid+1,right);
        return root;
    }


    /*
    98. 验证二叉搜索树
给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左子树只包含 严格小于 当前节点的数。
节点的右子树只包含 严格大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root==null) return true;
        if (min!=null&&root.val<=min) return false;
        if (max!=null&&root.val>=max) return false;
        return isValidBST(root.left,min,root.val)&&
                isValidBST(root.right,root.val,max);
    }

//    public boolean isValidBST_(TreeNode root) {
//
//    }


    /*
    230. 二叉搜索树中第 K 小的元素
给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()||root!=null){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode cur = stack.pop();
                if (--k==0) return cur.val;
                root = cur.right;
            }
        }
        return -1;
    }


    /**
     * 用迭代的方法怎么写合适？？
     * */
    int res11 = -1;
//    public int kthSmallest_(TreeNode root, int k) {
//
//    }


    /*
    199. 二叉树的右视图
给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        if (root==null) return res;
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                if (i==size-1) res.add(cur.val);
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
            }
        }

        return res;
    }


    /*
    114. 二叉树展开为链表
给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */
//    public void flatten(TreeNode root) {
//        if (root==null) return;
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        TreeNode cur = null;
//        while (!stack.isEmpty()){
//            TreeNode now = stack.pop();
//            if (cur==null) cur = now;
//            else {
//                cur.right = now;
//                cur.left = null;
//            }
//            cur = cur.right;
//        }
//    }

    /*
    105. 从前序与中序遍历序列构造二叉树
给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     */
    int preorderIndex;
    HashMap<Integer,Integer> inorderMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }

        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int left, int right) {
        if (left>right) return null;
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.left = buildTree(preorder,inorder,left,index-1);
        root.right = buildTree(preorder,inorder,index+1,right);
        return root;
    }


    /*
    437. 路径总和 III
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     */
//    public int pathSum(TreeNode root, int targetSum) {
//
//    }


    /*
    236. 二叉树的最近公共祖先
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        if (root==p||root==q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left!=null&&right!=null) return root;
        return left==null?right:left;
    }


    /*
    124. 二叉树中的最大路径和
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。
     */
    int resMaxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs6(root);
        return resMaxPathSum;
    }

    private int dfs6(TreeNode root) {
        if (root==null) return 0;
        int left = Math.max(0,dfs6(root.left));
        int right = Math.max(0,dfs6(root.right));
        resMaxPathSum = Math.max(left+right+root.val,resMaxPathSum);
        return Math.max(left,right)+root.val;
    }


    /*
    200. 岛屿数量
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length,n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]=='1'){
                    dfs3(grid,i,j);
                    res++;
                }
            }
        }
        return res;
    }

    int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1}};
    private void dfs3(char[][] grid, int i, int j) {
        if (i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]!='1') return;
        grid[i][j] = '0';
        for (int[] dir:dirs){
            int x = i+dir[0],y = j+dir[1];
            dfs3(grid,x,y);
        }
    }



    /*
    994. 腐烂的橘子
在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：

值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。

返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     */
//    public int orangesRotting(int[][] grid) {
//
//    }


    /*
    207. 课程表
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        LinkedList<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] cur:prerequisites) {
            int post = cur[0],pre = cur[1];
            indegree[post]++;
            graph.get(pre).add(post);
        }

        LinkedList<Integer> zeroIndegree = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i]==0) zeroIndegree.offer(i);
        }

        int con = 0;
        while (!zeroIndegree.isEmpty()){
            con++;
            Integer curCourse = zeroIndegree.poll();
            for (int course:graph.get(curCourse)){
                indegree[course]--;
                if (indegree[course]==0) zeroIndegree.offer(course);
            }
        }
        return con==numCourses;
    }


    /*
    208. 实现 Trie (前缀树)
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     */
    class Trie {

        class TrieNode{
            TrieNode[] children = new TrieNode[26];
            boolean isEnd;
            public TrieNode(){}
        }


        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char c:word.toCharArray()){
                int index = c-'a';
                if (cur.children[index]==null) cur.children[index] = new TrieNode();
                cur  =cur.children[index];
            }
            cur.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode cur = root;
            for (char c:word.toCharArray()){
                int index = c-'a';
                if (cur.children[index]==null) return false;
                cur = cur.children[index];
            }
            return cur.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (char c:prefix.toCharArray()){
                int index = c-'a';
                if (cur.children[index]==null) return false;
                cur = cur.children[index];
            }
            return true;
        }
    }



    /*
    46. 全排列
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     */
    boolean[] used;
    List<List<Integer>> resPermute;
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        resPermute = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs5(nums,path);
        return resPermute;
    }

    private void dfs5(int[] nums, LinkedList<Integer> path) {
        if (nums.length==path.size()){
            resPermute.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                used[i] = true;
                path.add(nums[i]);
                dfs5(nums,path);
                path.removeLast();
                used[i] = false;
            }
        }
    }


    /*
    78. 子集
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
    List<List<Integer>> resSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubsets = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        subsets(nums,0,path);
        return resSubsets;
    }

    private void subsets(int[] nums, int index, LinkedList<Integer> path) {
        /**不需要return语句，应该是可以正常结束的*/
        resSubsets.add(new LinkedList<>(path));
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            subsets(nums,i+1,path);
            path.removeLast();
        }
    }


    /*
    17. 电话号码的字母组合
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    List<String> resLetterCombinations;
    HashMap<Character,String> map;
    public List<String> letterCombinations(String digits) {
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
        resLetterCombinations = new LinkedList<>();
        letterCombinations(digits,0,new StringBuilder());
        return resLetterCombinations;
    }

    private void letterCombinations(String digits, int index, StringBuilder path) {
        if (index==digits.length()){
            resLetterCombinations.add(new String(path));
            return;
        }
        String str = map.get(digits.charAt(index));
        for (int i = 0; i < str.length(); i++) { /**跟以往的回溯这里有区别，这里的界限是*/
            char c = str.charAt(i);
            path.append(c);
            letterCombinations(digits,index+1,path);
            path.deleteCharAt(path.length()-1);
        }
    }


    /*
    39. 组合总和
给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */
    List<List<Integer>> resCombinationSum;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        resCombinationSum = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        combinationSum(candidates,target,0,path);
        return resCombinationSum;
    }

    private void combinationSum(int[] candidates, int target, int index, LinkedList<Integer> path) {
        if (target==0){
            resCombinationSum.add(new LinkedList<>(path));
        }
        if (index==candidates.length||target<0) return;
        for (int i = index; i < candidates.length; i++) {
            target -= candidates[i];
            path.add(candidates[i]);
            combinationSum(candidates,target,i,path);
            target += candidates[i];
            path.removeLast();
        }
    }


    /*
    22. 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     */
    List<String> resGenerateParenthesis;
    public List<String> generateParenthesis(int n) {
        resGenerateParenthesis = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        generateParenthesis(n,0,0,sb);
        return resGenerateParenthesis;
    }

    private void generateParenthesis(int n, int open, int close, StringBuilder path) {
        if (path.length()==2*n){
            resGenerateParenthesis.add(new String(path));
            return;
        }
        if (open<n){
            path.append('(');
            generateParenthesis(n,open+1,close,path);
            path.deleteCharAt(path.length()-1);
        }
        if (close<open){
            path.append(')');
            generateParenthesis(n,open,close+1,path);
            path.deleteCharAt(path.length()-1);
        }
    }


    /*
    79. 单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs4(board,word,0,i,j)) return true;
            }
        }
        return false;
    }

    private boolean dfs4(char[][] board, String word, int index, int i, int j) {
        if (index==word.length()) return true;
        if (i<0||j<0||i>=board.length||j>=board[0].length||word.charAt(index)!=board[i][j]) return false;
        board[i][j] = '\n';
        boolean tmp = dfs4(board,word,index+1,i-1,j)||
                dfs4(board,word,index+1,i+1,j)||
                dfs4(board,word,index+1,i,j+1)||
                dfs4(board,word,index+1,i,j-1);
        board[i][j] = word.charAt(index);
        return tmp;
    }


    /*
    131. 分割回文串
给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     */
//    public List<List<String>> partition(String s) {
//
//    }



    /*
    51. N 皇后
按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     */
//    public List<List<String>> solveNQueens(int n) {
//
//    }


    /*
    35. 搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 O(log n) 的算法。
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>=target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return left;
    }

    /*
    74. 搜索二维矩阵
给你一个满足下述两条属性的 m x n 整数矩阵：

每行中的整数从左到右按非严格递增顺序排列。
每行的第一个整数大于前一行的最后一个整数。
给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
     */
    public boolean searchMatrix_(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int left = 0,right = m*n-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur<target){
                left = mid+1;
            } else if (cur > target) {
                right = mid-1;
            }else {
                return true;
            }
        }
        return false;
    }


    /*
    34. 在排序数组中查找元素的第一个和最后一个位置
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     */
    public int[] searchRange(int[] nums, int target) {
        int index = findLeft(nums,target);
        if (index==nums.length||nums[index]!=target) return new int[]{-1,-1};
        int index1 = findLeft(nums,target+1);
        return new int[]{index,index1-1};
    }

    private int findLeft(int[] nums, int target) {
        int left = 0,right =nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>=target){
                right=mid-1;
            }else {
                left =mid+1;
            }
        }
        return left;
    }


    public int[] searchRange__(int[] nums, int target) {
        int left = findL(nums,target);
        if (left==nums.length||nums[left]!=target) return new int[]{-1,-1};
        int right = findR(nums,target);
        return new int[]{left,right};
    }

    private int findR(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            /**
             * 寻找左右边界的关键：理解“等于target”的时候，应该去哪一边找？？？
             *      如果是找左边界，则相等的时候，要继续尝试去左边，因此 right=mid-1;
             *      如果是找右边界，则相等的时候，要继续尝试去右边，因此 left=mid+1;
             */
            int mid = left+(right-left)/2;
            if (nums[mid]<=target){
                left=mid+1;
            }else {
                right = mid-1;
            }
        }
        return right;
    }

    private int findL(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>=target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return left;
    }





    /*
    33. 搜索旋转排序数组
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     */
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>=nums[left]){
                if (target>=nums[left]&&target<nums[mid]){
                    right  = mid-1;
                }else {
                    left =mid+1;
                }
            }else {
                if (target>nums[mid]&&target<=nums[right]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    /*
    153. 寻找旋转排序数组中的最小值
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题
     */
    /**这个题与最左边的比较不合适————
        比如如果nums[mid]>nums[left]，接下来应该取那一边查找呢？？但按时不一定的，没有一个明确的方向，如果使用的话必须添加额外的判断！！！
     举个例子：
            [4,5,6,7,0,1,2]，第一次nums[mid]等于7，满足 nums[mid]>nums[left]，此时的最小值应该去mid的右边查找
            [0,1,2,4,5,6,7]，第一次nums[mid]等于4，满足 nums[mid]>nums[left]，此时的最小值应该去mid的左边查找
        综上，这样的比较并没有一个明确的方向，因此尽量不要这么比较....结论：尽量和 右边界的数 进行比较判断接下来的方向！！！
     */
    public int findMin(int[] nums) {
        int left = 0,right = nums.length-1;
        int ans = -1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]<=nums[right]){
                ans = mid;
            }else {
                left = mid+1;
            }
        }
        return nums[ans];
    }

    /*
    4. 寻找两个正序数组的中位数
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

算法的时间复杂度应该为 O(log (m+n)) 。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length,n = nums2.length;
        if (n<m) return findMedianSortedArrays(nums2,nums1);
        int left = 0,right = m;
        while (left<=right){
            int mid1 = left+(right-left)/2;
            int mid2 = (m+n+1)/2-mid1;

            int left1 = mid1==0?Integer.MIN_VALUE:nums1[mid1-1];
            int right1 = mid1==m?Integer.MAX_VALUE:nums1[mid1];
            int left2 =mid2==0?Integer.MIN_VALUE:nums2[mid2-1];
            int right2 = mid2==n?Integer.MAX_VALUE:nums2[mid2];

            if (left1>right2){
                right = mid1-1;
            } else if (left2 > right1) {
                left = mid1+1;
            }else {
                if ((m+n&1)==1){ /**这样写的与逻辑行不行？？*/
                    return Math.max(left1,left2);
                }else {
                    return (
                            Math.max(left1,left2)+
                                    Math.min(right1,right2)
                            )/2.0;
                }
            }
        }
        return -1;
    }


    /*
    20. 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。
     */
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                if (stack.isEmpty()||stack.pop()!=map.get(c)) return false;
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /*
    155. 最小栈
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

实现 MinStack 类:

MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。
     */
    class MinStack {
        Stack<Integer> allStack;
        Stack<Integer> minStack;

        public MinStack() {
            allStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            allStack.push(val);
            if (minStack.isEmpty()||allStack.peek().intValue()>=val){
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


    /*
    394. 字符串解码
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

测试用例保证输出的长度不会超过 105。
     */
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Stack<String> stackStr = new Stack<>();
        Stack<Integer> stackInt = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c>='0'&&c<='9'){
                multi = multi*10 + c-'0';
            }else if (c=='['){
                stackStr.push(new String(res));
                stackInt.push(multi);
                multi = 0;
                res = new StringBuilder();
            }else if (c==']'){
                /**碰到右括号的时候：先把当前括号内的字符串res重复左括号前的次数。左括号前的次数是多少呢？需要从stackInt弹出一个。。。因
                 * 为每一次碰到左括号的时候需要把前面计算出来的系数入栈；然后需要前面拼接上之前的字符串，之前的字符串是啥呢？也需要从栈中
                 * 弹出来！！！*/
                Integer num = stackInt.pop();
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder(tmp).append(stackStr.pop()); /**这么写应该是错误的*/
//                res = new StringBuilder(stackStr.pop()).append(tmp);
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }


    /*
    739. 每日温度
给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     */
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


    /*
    84. 柱状图中最大的矩形
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < heights.length + 1; i++) {
            int curHeight = i==heights.length?0:heights[i];
            while (!stack.isEmpty()&&curHeight<heights[stack.peek()]){
                Integer index = stack.peek();
                int left = stack.isEmpty()?-1:stack.peek();
                res =Math.max(res,heights[index]*(i-left-1));
            }
            stack.push(i);
        }
        return res;
    }


    /*
    215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
//    public int findKthLargest(int[] nums, int k) {
//        return findKthLargest(nums,0,nums.length-1,nums.length-k);
//    }

//    private int findKthLargest(int[] nums, int left, int right, int index) {
//        int pivotIndex = left+new Random().nextInt(0,right-left+1);
//        int pivot = nums[pivotIndex];
//
//        int[] cur = partion11(nums,left,right,pivot);
//        if (cur[0]>index){
//            findKthLargest(nums,left,cur[0]-1,index);
//        } else if (cur[1] < index) {
//            findKthLargest(nums,cur[1]+1,right,index);
//        }else {
//            return pivot;
//        }
//        return -1;
//    }

    /**for循环不好写？？？*/
//    private int[] partion11(int[] nums, int left, int right, int pivot) {
//        int i = left,j = right;
//        for (int k = left; k <= right; k++) {
//            if (nums[k]<pivot){
//                swap5(nums,i++,k);
//            }
//            while ()
//        }
//    }

//    private int[] partion11(int[] nums, int left, int right, int pivot) {
//        int cur = left;
//
//    }


    /*
    347. 前 K 个高频元素
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)-> a[0]-b[0]);
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            Integer value = entry.getKey();
            Integer num = entry.getValue();
            queue.offer(new int[]{num,value});
            if (queue.size()>k){
                queue.poll();
            }
        }

        int[] res = new int[k];
        int index =0;
        for (int[] cur:queue){
            res[index++] = queue.poll()[1];
        }
        return res;
    }


    /*
    295. 数据流的中位数
中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。

例如 arr = [2,3,4] 的中位数是 3 。
例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
实现 MedianFinder 类:

MedianFinder() 初始化 MedianFinder 对象。

void addNum(int num) 将数据流中的整数 num 添加到数据结构中。

double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
     */
    class MedianFinder {
        PriorityQueue<Integer> min;
        PriorityQueue<Integer> max;

        public MedianFinder() {
            min = new PriorityQueue<>((a,b)->b-a);
            max = new PriorityQueue<>();
        }

        public void addNum(int num) {
            /**1. min的数多还是max的数多？就看相等的时候往哪一边放数据。下面的是往min放数据，因此min的数据量大于等于max的数据量。
             * 2. 南辕北辙的想法：如果最终想往min放数据————先放到max中；再从max弹出最小的放进min。。。。原因：保证全局的有效有序*/
            if (min.size()==max.size()){
                max.offer(num);
                min.offer(max.poll());
            }else {
                min.offer(num);
                max.offer(min.poll());
            }
        }

        public double findMedian() {
            if (min.size()==max.size()){
                return (min.peek()+max.peek())/2.0;
            }else {
                return min.peek();
            }
        }
    }


    /*
    121. 买卖股票的最佳时机
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     */
    public int maxProfit(int[] prices) {
        int flag = Integer.MAX_VALUE;
        int res = Integer.MIN_VALUE;
        for (int price:prices){
            flag = Math.min(price,flag);
            res = Math.max(res,price-flag);
        }
        return res;
    }


    /*
    55. 跳跃游戏
给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     */
    public boolean canJump(int[] nums) {
        int right = 0;
        for (int i = 0; i < nums.length&&i<=right; i++) {
            right = Math.max(i+nums[i],right);
            if (right>=nums.length-1) return true;
        }
        return false;
    }


    /*
    45. 跳跃游戏 II
给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。

每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：

0 <= j <= nums[i] 且
i + j < n
返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
     */
    public int jump(int[] nums) {
        int step = 0,right = 0,bound = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            bound = Math.max(i+nums[i],bound);
            if (i==right){
                step++;
                right=bound;
            }
        }
        return step;
    }

    public int jump_(int[] nums) {
        int step=0;
        int bound = 0,right = 0;
        int cur = 0;
        while (cur<nums.length-1){
            bound = Math.max(bound,cur+nums[cur]);
            if (cur==right){
                step++;
                right=bound;
            }
        }
        return step;
    }


    /*
    763. 划分字母区间
给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。

注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。

返回一个表示每个字符串片段的长度的列表。
     */
    public List<Integer> partitionLabels(String s) {
        LinkedList<Integer> res = new LinkedList<>();
        int[] flags = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            flags[c-'a'] = i;
        }

        int left = 0,maxRight = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            maxRight = Math.max(maxRight,flags[c-'a']);
            if (i==maxRight){
                res.add(i-left+1);
                left = i+1;
            }
        }
        return res;
    }


    /*
    70. 爬楼梯
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
    public int climbStairs(int n) {
        if (n<=2) return n;
        int fir = 1,sec  =2;
        for (int i = 3; i <= n; i++) {
            int cur = fir+sec;
            fir = sec;
            sec = cur;
        }
        return sec;
    }


    /*
    118. 杨辉三角
给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。

在「杨辉三角」中，每个数是它左上方和右上方的数的和。

输入: numRows = 5
输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     */
//    public List<List<Integer>> generate(int numRows) {
//
//    }


    /*
    198. 打家劫舍
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     */
    public int rob(int[] nums) {
        if (nums.length==1) return nums[0];
        if (nums.length==2) return Math.max(nums[0],nums[1]);
        int fir = nums[0],sec = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(fir+nums[i],sec);
            fir = sec;
            sec = cur;
        }
        /**能偷盗的最大金额，一定是在遍历完数字取得。。因此只用返回最后的值就可以*/
        return sec;
    }


    /*
    279. 完全平方数
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     */
    public int numSquares(int n) {
        if (n<=1) return 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp,n+1);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j*j < n; j++) {
                dp[i] = Math.min(dp[i-j*j]+1,dp[i]);
            }
        }
        return dp[n];
    }


    /*
    322. 零钱兑换
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for (int i = coins[0]; i <= amount; i++) {
            dp[i] = dp[i-coins[0]] + 1;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[j] = Math.min(dp[j],dp[j-coins[i]]+1);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }


    /*
    139. 单词拆分
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     */
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


    /*
    300. 最长递增子序列
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int size = 0;
        for (int num:nums){
            int left = 0,right = size-1;
            while (left<=right){
                /**下面就是带女性的寻找左边界的做法*/
                int mid = left+(right-left)/2;
                if (dp[mid]>=num){
                    right = mid-1;
                }else {
                    left=mid+1;
                }
            }
            dp[left] = num;
            if (left==size) size++;
        }
        return size;
    }


    /*
    152. 乘积最大子数组
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个 32-位 整数。

请注意，一个只包含一个元素的数组的乘积是这个元素的值。
     */
    public int maxProduct(int[] nums) {
        int preMax = nums[0],preMin = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(Math.min(preMin*nums[i],preMax*nums[i]),nums[i]);
            int curMax = Math.max(Math.max(preMin*nums[i],preMax*nums[i]),nums[i]);
            /**这里必须是取代的关系。。。不能再使用“Math.max”取最大值了*/
            preMax = curMax;
            preMin = curMin;
            res = Math.max(preMax,res);
        }
        return res;
    }


    /*
    416. 分割等和子集
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num:nums) sum += num;
        if (sum%2==1) return false;
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j++) {
                dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
        }
        return dp[sum]==sum;
    }


    public boolean canPartition_(int[] nums) {
        int sum = 0;
        for (int num:nums) sum+= num;
        if (sum%2==1) return false;
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = nums[0]; i <= sum; i++) {
            dp[sum] = nums[0];
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = sum; j >=nums[i]; j--) {
                dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
        }
        return dp[sum]==sum;
    }


    /*
    32. 最长有效括号
给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号 子串 的长度。

左右括号匹配，即每个左括号都有对应的右括号将其闭合的字符串是格式正确的，比如 "(()())"。
     */
    public int longestValidParentheses(String s) {
        int left = 0,right = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='(') left++;
            if (c==')') right++;
            if (left==right) res = Math.max(2*left,res);
            if (left<right) left=right=0;
        }

        left=right=0;
        for (int i = s.length()-1; i >=0 ; i--) {
            char c = s.charAt(i);
            if (c=='(') left++;
            if (c==')') right++;
            if (left==right) res = Math.max(2*left,res);
            if (left>right) left=right=0;
        }
        return res;
    }


    /*
    62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j==0) dp[j] = 1;
                else dp[j] += dp[j-1];
            }
        }
        return dp[n];
    }


    /*
    64. 最小路径和
给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
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


    /*
    5. 最长回文子串
给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
//    public String longestPalindrome(String s) {
//
//    }


    /*
    1143. 最长公共子序列
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence(text2,text1);

        //都是0省略掉初始化操作

        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            dp[0] = 0;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[1];
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2) dp[j] = prev+1;
                else dp[j] = Math.max(dp[j-1],dp[j]);
                prev = tmp;
            }
        }
        return dp[n];
    }


    /*
    72. 编辑距离
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        if (m<n) return minDistance(word2,word1);
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            int prev =dp[0];
            dp[0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                int tmp = dp[j];
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1==c2) dp[j] = prev;
                else dp[j] = Math.min(Math.min(dp[j-1],dp[j]),prev)+1;
                prev = tmp;
            }
        }
        return dp[n];
    }


    /*
    136. 只出现一次的数字
给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num:nums){
            res ^= num;
        }
        return res;
    }


    /*
    169. 多数元素
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    public int majorityElement(int[] nums) {
        int flag = 0,ticket = 0;
        for (int num:nums){
            if (ticket==0) flag = num;
            ticket += flag==num?1:-1;
        }
        return flag;
    }


    /*
    75. 颜色分类
给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

必须在不使用库内置的 sort 函数的情况下解决这个问题。
     */
//    public void sortColors(int[] nums) {
//        int left = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i]<1){
//                swap4(nums,left++,i);
//            } else if (nums[i] > 1) {
//                swap4(nums,)
//            }
//        }
//    }

    public void sortColors(int[] nums) {
        int left = 0,cur =0,right=nums.length-1;
        while (cur<nums.length){
            if (nums[cur]<1){
                swap4(nums,left++,cur++);
            } else if (nums[cur] == 1) {
                cur++;
            }else {
                swap4(nums,cur,right--);
            }
        }
    }

    private void swap4(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] =tmp;
    }


    /*
    31. 下一个排列
整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。

必须 原地 修改，只允许使用额外常数空间。
     */
    public void nextPermutation(int[] nums) {
        int flag = -1;
        for (int i = nums.length-1; i >=0 ; i--) {
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }

        if (flag>=0){
            for (int i = nums.length-1; i > flag; i--) {
                if (nums[i]>nums[flag]){
                    int tmp = nums[i];
                    nums[i] = nums[flag];
                    nums[flag] = tmp;
                    break;
                }
            }
        }
        rever2(nums,flag+1);
    }

    private void rever2(int[] nums, int i) {
        int left = i,right = nums.length-1;
        while (left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }


    /*
    287. 寻找重复数
给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。

你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0],fast = nums[0];
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow!=fast);
        slow = nums[0];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;  /**这里还需不需要去取数？？*/
    }
}
