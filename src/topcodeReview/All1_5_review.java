package topcodeReview;

import leecode_Debug.BTree.TreeNode;
import leecode_Debug.top100.ListNode;

import java.util.*;

/**
 * 2025.9.26
 */

/**
 * 470需要理解一下官方的解、283有没有直接交换的方法省略最后的遍历操作
 * err：234、179、69、146
 * 468、1324、113、堆排序、148、151、76、8、93、227
 * 72、1143写出空间优化版本
 */
public class All1_5_review {
    /*
     * 3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。无重复字符的最长子串
     * */
//    public int lengthOfLongestSubstring(String s) {
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
        class DouNode{
            int key;
            int value;
            DouNode prev;
            DouNode next;
            public DouNode(){}
            public DouNode(int key,int value){
                this.key = key;
                this.value =value;
            }
        }

        int size;
        int capacity;
        HashMap<Integer,DouNode> map;
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
            if (node!=null){
                moveToHead(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            DouNode node = map.get(key);
            if (node!=null){
                node.value = value;
                moveToHead(node);
            }else {
                DouNode newNode = new DouNode(key, value);
                addToHead(newNode);
                map.put(key,newNode);
                this.size++;
                if (this.size>this.capacity){
                    DouNode relTail = tail.prev;
                    removeNode(relTail);
                    size--;
                    map.remove(relTail.value);
                }
            }
        }

        private void moveToHead(DouNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void removeNode(DouNode node) {
            DouNode prev = node.prev;
            DouNode next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        private void addToHead(DouNode newNode) {
            DouNode next = head.next;
            head.next = newNode;
            newNode.next = next;
            next.prev = newNode;
            newNode.prev = head;
        }

//        private void removeNode(DouNode node) {
//            node.prev.next = node.next;
//            node.next.prev = node.prev;
//        }


    }


    /*206. 反转链表
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
//    public ListNode reverseList(ListNode head) {
//
//    }



    /*215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */

    /**
     * 下面的解法会超时
     */
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,k);
    }

    private int findKthLargest(int[] nums, int left, int right, int k) {
        int pivotIndex = left+new Random().nextInt(0,right-left+1);
        swap5(nums,pivotIndex,right);
        pivotIndex = partition2(nums,left,right);
        if (pivotIndex==nums.length-k) return nums[pivotIndex];
        else if (pivotIndex<nums.length-k){
            return findKthLargest(nums,pivotIndex+1,right,k);
        }else {
            return findKthLargest(nums,left,pivotIndex-1,k);
        }
    }

    private int partition2(int[] nums, int left, int right) {
        int cur = left;
        for (int i = left; i < right; i++) {
            if (nums[i]<nums[right]){
                swap5(nums,cur++,i);
            }
        }
        swap5(nums,cur,right);
        return cur;
    }

    private void swap5(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }


    /*25
    k个一组翻转链表
     */
//    public ListNode reverseKGroup(ListNode head, int k) {
//
//    }


    /*15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * 【】：关键的步骤在于双指针 过程中，如何跳过所有相同的元素。
     * */
//    public List<List<Integer>> threeSum(int[] nums) {
//
//    }


    /*53.最大子数组和
        给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
子数组是数组中的一个连续部分。
     */
//    public int maxSubArray(int[] nums) {
//
//    }

    /*
    手撕快排
     */
    public void quickSort(int[] arr,int left,int right) {
        if (left>=right) return;
        int pivotIndex = left+ new Random().nextInt(0, right - left + 1);
        swap1(pivotIndex,right,arr);
        pivotIndex = partition1(arr,left,right);
        quickSort(arr,left,pivotIndex-1);
        quickSort(arr,pivotIndex+1,right);
    }

    private int partition1(int[] arr, int left, int right) {
        int cur = left;
        for (int i = left; i < right; i++) {
            if (arr[i]<arr[right]){
                swap1(i,cur++,arr);
            }
        }
        swap1(cur,right,arr);
        return cur;
    }

    private void swap1(int pivotIndex, int right, int[] arr) {
        int tmp = arr[pivotIndex];
        arr[pivotIndex] = arr[right];
        arr[right] = tmp;
    }

    /*21.合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
//    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//
//    }

    /*5 最长回文子串
    给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
//    public String longestPalindrome(String s) {
//
//    }

    /*102.层序遍历*/
//    public List<List<Integer>> levelOrder(TreeNode root) {
//
//    }

    /*1两数之和
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
        int[] res = {-1,-1};
        return new int[]{-1,-1}; /**声明二维数组的时候，可以直接使用”{{}{}{}}“来进项快速的初始化，但是不能直接返回”{}“来标识一维数组？？？？*/
    }



    /*33.....81是扩展（允许有重复元素）
    整数数组 nums 按升序排列，数组中的值 互不相同 。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
    你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    * */
//    public int search(int[] nums, int target) {
//
//    }


    /*200.
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。
    * */
//    public int numIsland(char[][] grid) {
//
//    }


    /*46.
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    * */
//    public List<List<Integer>> permute(int[] nums) {
//
//    }


    /*88. 合并两个有序数组
给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。

请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。*/
//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//
//    }


     /*20.
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是
    * 否有效。
    有效字符串需满足：
    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    每个右括号都有一个对应的相同类型的左括号。*/
//    public boolean isValid(String s) {
//
//    }

    /*121.买卖股票的最佳时机
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一
        个算法来计算你所能获取的最大利润。
      返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    * */
//    public int maxProfit(int[] prices) {
//
//    }


    /*236.二叉树最近公共祖先
     * */
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//
//    }

    /*
    103. 二叉树的锯齿形层序遍历
    给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。*/
//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//
//    }


    /**
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     */

    /*92. 反转链表 II
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。*/
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//
//    }


    /*141环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。*/
//    public boolean hasCycle(ListNode head) {
//
//    }

    /*54.螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * */
//    public List<Integer> spiralOrder(int[][] matrix) {
//
//    }


    /*300.最长递增子序列
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
//    public int lengthOfLIS1(int[] nums) {
//
//    }


    /*
    143重排链表
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
//    public void reorderList(ListNode head) {
//
//    }


    /*23.合并 K 个升序链表
    * 给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。*/
//    public ListNode mergeKLists(ListNode[] lists) {
//
//    }


    /*415. 字符串相加
    给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。

你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。*/
    /*写法1：下面的写法中，最后更新i和j变量，可能会忘记，因此可以考虑使用写法2*/
//    public String addStrings(String num1, String num2) {
//
//    }

    /*56.合并区间
     *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
     * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * */
//    public int[][] merge(int[][] intervals) {
//
//    }


    /*160.相交链表
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果
     * 两个链表不存在相交节点，返回 null 。保证不出现环*/
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//
//    }


    /*
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * */
//    public int trap(int[] height) {
//
//    }


    /*72.编辑距离
    * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
    你可以对一个单词进行如下三种操作：
    插入一个字符
    删除一个字符
    替换一个字符*/
    /*解法1：二维dp的写法*/
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i==0||j==0){
                    dp[i][j] = i==0?j:i;
                }else {
                    char c1 = word1.charAt(i - 1);
                    char c2 = word2.charAt(j - 1);
                    if (c1==c2){
                        dp[i][j] = dp[i-1][j-1];
                    }else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    }
                }
            }
        }
        return dp[m][n];
    }


    /*解法2：两行形式的dp*/
    public int minDistance_2_1dim(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        if (m<n) return minDistance_2_1dim(word2,word1);
        int[] prev = new int[n + 1];
        int[] cur = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            prev[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            cur[0] = i;
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1==c2)
                    cur[j] = prev[j-1];
                else
                    cur[j] = Math.min(Math.min(prev[j],cur[j-1]),prev[j-1]);
            }
            /**这里必须要把两个数组交换吗？？感觉有点不对劲*/
            int[] tmp = cur;
            cur = prev;
            prev = tmp;
        }
        return cur[n];
    }


    /*解法3：一行DP+额外变量的写法*/
    public int minDistance_1dim(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m < n) return minDistance_1dim(word2, word1);
        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            dp[0] = 0;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2)
                    dp[j] = prev;
                else
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prev) + 1;
                prev = tmp;
            }
        }
        return dp[n];
    }


    /*124. 二叉树中的最大路径和
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列
    中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
//    public int maxPathSum(TreeNode root) {
//
//    }



    /*1143. 最长公共子序列
    给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。*/
    /*写法1：2维 的写法*/
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[m][n];
    }

    /*写法2：2行动规的写法*/
    /**
     【说明】
            1. 两行DP的写法中，prev就看作是二维DP中的dp[i-1][..]，而cur看作是二维DP中的dp[i][..]。（简单点说，计算时代码中的
        prev直接改成dp[i-1]、代码中的cur直接改写成dp[i]）比如，下面的代码中————
                ①“cur[j] = prev[i-1] + 1;”就相当于“dp[i][j] = prev[i-1][j-1] + 1”；
                ②“cur[j] = Math.max(cur[j-1],prev[j]);”就相当于“dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j])”
                可以看出，改写后的表达式跟二维DP的表达式一模一样！！
            2. 两行的DP中，由于习惯研究某一行的每一列，因此“脑补出短的元素在一行”。比如：如果text1短就让text1写在行的位置，这样
        创建的dp长度就是text1.length+1；反之如果text2比较短，就让text2写在行的位置，此时创建的动规数组长度就是text2.lenth+1。
        这样的话空间复杂度能降低到O(M,N)（其中M时text1的长度，N是text2的长度~~~）
     */
    public int longestCommonSubsequence_2_1dim(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence_2_1dim(text2,text1);
        int[] prev = new int[n + 1]; //prev用于存储dp表上一行的信息
        int[] cur = new int[n + 1]; //cur用于存储dp表现在研究的这一行的信息

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    cur[j] = prev[i-1] + 1;
                }else {
                    cur[j] = Math.max(cur[j-1],prev[j]);
                }
            }
            int[] tmp = cur;
            cur = prev;
            prev = tmp;
        }
        return cur[n];
    }


    /*写法3：1维+额外变量的写法*/
    /**
     【说明】
            1. 二维DP中如果一个位置(i,j)会依赖于左上角的三个位置————(i-1,j-1)、(i,j-1)、(i-1,j)，这种形式的DP是
        不能简化成一行DP的！！！可以简化成“2行形式的DP”、“1行+额外变量的DP”
     */
    public int longestCommonSubsequence_1dim(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence_1dim(text2,text1);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            dp[0] = 0;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    dp[j] = prev+1;
                }else {
                    dp[j] = Math.max(dp[j-1],dp[j]);
                }
                prev = tmp;
            }
        }
        return dp[n];
    }



    /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
    "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过
    在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
    /**
     * 下面的代码大概率，不对！！！！！！！
     */
    List<String> resRestoreIpAddresses;
    public List<String> restoreIpAddresses(String s) {
        resRestoreIpAddresses = new LinkedList<>();
        StringBuilder path = new StringBuilder(s);
        restoreIpAddresses(s,path,0,0);
        return resRestoreIpAddresses;
    }

    private void restoreIpAddresses(String s, StringBuilder path, int index, int num) {
        if (num==3){
            if (isValid(s,index,s.length()))
                resRestoreIpAddresses.add(new String(path));
            return;
        }
        for (int i = index+1; i < s.length(); i++) {
            if (isValid(s,index,i)){
                path.insert(i+num,'.');
                restoreIpAddresses(s,path,i,num+1);
                path.deleteCharAt(i+num);
            }
        }
    }

    private boolean isValid(String s, int index,int end) {
        String substring = s.substring(index,end);
        if (substring.length()>=4) return false;
        if (substring.length()==3&&Integer.parseInt(substring)>=100&&Integer.parseInt(substring)<=255) return true;
        if (substring.length()==2&&Integer.parseInt(substring)>=10) return true;
        if (substring.length()==1) return true;
        return false;
    }


    /*
    82. 删除排序链表中的重复元素 II
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
//    public ListNode deleteDuplicates(ListNode head) {
//
//    }


    /*142.环形链表 II
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
//    public ListNode detectCycle(ListNode head) {
//
//    }


    /*19.删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//
//    }


    /*4.寻找两个正序数组的中位数
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
    * */
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//    }



    /*199.二叉树的右视图
    给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    * */
//    public List<Integer> rightSideView(TreeNode root) {
//
//    }




    /*165. 比较版本号
    给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
    比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
    返回规则如下：
    如果 version1 < version2 返回 -1，
    如果 version1 > version2 返回 1，
    除此之外返回 0。
    * */
//    public int compareVersion(String version1, String version2) {
//
//    }


    /**
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     */
    /*94. 二叉树的中序遍历
给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。*/
//    public List<Integer> inorderTraversal(TreeNode root) {
//
//    }



    /*704.二分查找
     */
//    public int search1(int[] nums, int target) {}


    /*232. 用栈实现队列
请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：

实现 MyQueue 类：

void push(int x) 将元素 x 推到队列的末尾
int pop() 从队列的开头移除并返回元素
int peek() 返回队列开头的元素
boolean empty() 如果队列为空，返回 true ；否则，返回 false
说明：

你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。*/
//    class MyQueue {
//
//        public MyQueue() {
//
//        }
//
//        public void push(int x) {
//
//        }
//
//        public int pop() {
//
//        }
//
//        public int peek() {
//
//        }
//
//        public boolean empty() {
//
//        }
//    }




    /*22.括号生成
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
    /*解法1：官方解回溯法*/
//    public List<String> generateParenthesis(int n) {
//
//    }

    /*
    239.滑动窗口最大值
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
//    public int[] maxSlidingWindow(int[] nums, int k) {
//
//    }


    /*148.
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/

    /**
     排序链表的题目中：偶数节点的时候，必须让中间后面的那个节点在后一半中，必须吗？？？？应该代码也能跑通
     下面的写法中“如果是偶数个节点，中间的两个节点都在前一半链表，会导致两边的递归深度不一样”。。。
        如果“偶数节点的时候让中间的两个节点，第一个位于前一半链表，第二个位于后一半链表，这样两边的递归深度就接近”，这样的话，
     我们希望偶数节点的时候，从最中间的位置断开——————因此希望①偶数时找到中间两个中的第一个节点；②然后记录中间的第二个节点；
     ③将第一个节点的next置为null。。。
     */
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode mid = findMid(head);
        ListNode start = mid.next;
        mid.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(start);
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
        cur.next = left==null?right:left;
        return dummy.next;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /*31. 下一个排列
整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。

必须 原地 修改，只允许使用额外常数空间。*/
//    public void nextPermutation(int[] nums) {
//
//    }

    /*69. x 的平方根
    给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
    由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
    注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。*/
//    public int mySqrt(int x) {
//
//    }


    /*8. 字符串转换整数 (atoi)
    请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
    函数 myAtoi(string s) 的算法如下：
    空格：读入字符串并丢弃无用的前导空格（" "）
    符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
    转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
    舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
    返回整数作为最终结果。*/
//    public int myAtoi(String s) {
//        int res = 0;
//        s = s.trim();
//        int index = 0;
//        int flag = 1;
//        if (s.charAt(index)=='-'||s.charAt(index)=='+'){
//            flag = s.charAt(index)=='-'?-1:1;
//            index++;
//        }
//
//        while (index<s.length()&&s.charAt(index)=='0') index++;
//
//        for (int i = index; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (!Character.isDigit(c)) break;
//            res = res*10 + c-'0';
//        }
//        return res;
//    }


    /*32.最长有效括号
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的
     * 长度。*/
//    public int longestValidParentheses(String s) {
//
//    }


    /*2两数相加
    给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//    }


    /*7爬楼梯
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
//    public int climbStairs(int n) {
//
//    }


    /*322. 零钱兑换
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/

    /**
        这个题填充为-1行不行？？
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j],dp[j-coins[i]]+1);
            }
        }

        return dp[amount]==amount+1?-1:dp[amount];
    }


     /*43 字符串相乘
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    * */
//    public String multiply(String num1, String num2) {
//
//    }


    /*
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
    public String minWindow(String s, String t) {
        if (s.length()==0||s.length()<t.length()) return "";
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0,valid = 0;
        int start = -1;
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                map.put(c,map.getOrDefault(c,0)+1);
                if (map.get(c)==need.get(c)){ /**【注意】涉及到map.get的时候，如果是“==”比较，则必须使用“.intValue()”或者“.equals()”~~不要使用“==”,因为map中get得到的是Integer！！！！！！*/
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
                    map.put(c1,map.get(c1)-1);
                    if (map.get(c1)<need.get(c1)){
                        valid--;
                    }
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }


    /*41.缺失的第一个正数
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
//    public int firstMissingPositive(int[] nums) {
//
//    }


    //    /*105.
//     * 从前序 和 中序 构造出二叉树*/
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//
//    }


    /*151. 反转字符串中的单词
    给你一个字符串 s ，请你反转字符串中 单词 的顺序。
    单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
    返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
    注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。*/
//    public String reverseWords(String s) {
//
//    }


    /**chatgpt给出的代码如下*/
    public String reverseWords(String s) {
        String trim = s.trim();
        String s1 = reverString(trim);
        String[] s2 = s1.split("\\s+"); // ✅ 用正则去除多余空格
        StringBuilder sb = new StringBuilder();
        for (String str : s2) {
            sb.append(reverString(str)).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String reverString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /**
     split("\\s+") 会按“至少一个空白字符”去拆分字符串，并自动去掉多余的空白。
         String s1 = "  hello   world  ";
         String[] s2 = s1.split("\\s+");
             拆出来结果：
                 s2[0] = "hello"
                 s2[1] = "world"
     如果写 split(" ")：
            " hello world ".split(" ")会得到：["", "", "hello", "", "", "world"] 里面有空字符串。
     */
    /*下面的代码是自己写的，可能会出错*/
    public String reverseWords_(String s) {
        String trim = s.trim();
        String s1 = reverString1(trim);
        String[] s2 = s1.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str:s2){
            sb.append(reverString1(str));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private String reverString1(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }



    /*78. 子集
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。*/
    List<List<Integer>> resSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubsets = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        subsets(nums,0,path);
        return resSubsets;
    }

    private void subsets(int[] nums, int index, LinkedList<Integer> path) {
        resSubsets.add(new LinkedList<>(path));
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            subsets(nums,i+1,path);
            path.removeLast();
        }
    }


    /**
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     */


    /*129.求根到叶子节点数字之和
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    每条从根节点到叶节点的路径都代表一个数字：

    例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    计算从根节点到叶节点生成的 所有数字之和 。
    叶节点 是指没有子节点的节点。
    * */
//    public int sumNumbers(TreeNode root) {
//
//    }




    /*155. 最小栈
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

实现 MinStack 类:

MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。*/
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


    /*34. 在排序数组中查找元素的第一个和最后一个位置
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。*/
//    public int[] searchRange(int[] nums, int target) {
//
//    }


    /*394.. 字符串解码
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注
    意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的
    输入。
    * */
//    public String decodeString(String s) {
//
//    }


    /*101。对称二叉树
    给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
//    public boolean isSymmetric(TreeNode root) {
//
//    }



    /*39组合总和
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//
//    }


    /*104二叉树的最大深度
    给定一个二叉树 root ，返回其最大深度。

二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     */
//    public int maxDepth(TreeNode root) {
//
//    }


    /*470.用 Rand7() 实现 Rand10()
    给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。

你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。

每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
     */
//    public int rand10() {
//
//    }



    /*64.最小路径和
    给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。
     */
//    public int minPathSum(int[][] grid) {
//
//    }

    /*144.二叉树的先序遍历
     */
//    public List<Integer> preorderTraversal(TreeNode root) {
//
//    }


    /*110. 平衡二叉树
给定一个二叉树，判断它是否是 平衡二叉树 */
//    public boolean isBalanced(TreeNode root) {
//
//    }

    /*122. 买卖股票的最佳时机 II
给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

返回 你能获得的 最大 利润 。*/
//    public int maxProfit1(int[] prices) {
//
//    }



    /*48旋转图像
     */
//    public void rotate(int[][] matrix) {
//
//    }


    /*128最长连续序列
    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
//    public int longestConsecutive(int[] nums) {
//
//    }


    /*240 搜索二维矩
    编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
     */
//    public boolean searchMatrix(int[][] matrix, int target) {
//
//    }


    /*221最大正方形
    在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     */
//    public int maximalSquare(char[][] matrix) {
//
//    }


    /*234。回文链表
    给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(ListNode head) {
        ListNode mid = findMid2(head);
        ListNode head2 = reverse5(mid.next);
        while (head2!=null){
            if (head2.val!=head.val){
                return false;
            }
            head2 = head2.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverse5(ListNode head) {
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
            slow =slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /*98 验证二叉搜索树
        给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

    有效 二叉搜索树定义如下：

    节点的左子树只包含 严格小于 当前节点的数。
    节点的右子树只包含 严格大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。
     */
//    public boolean isValidBST(TreeNode root) {
//
//    }


    /*543.二叉树的直径
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
//    public int diameterOfBinaryTree(TreeNode root) {
//
//    }


    /*695. 岛屿的最大面积
给你一个大小为 m x n 的二进制矩阵 grid 。

岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

岛屿的面积是岛上值为 1 的单元格的数目。

计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     */
//    public int maxAreaOfIsland(int[][] grid) {
//
//    }

    /**
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     */

    /*
    14. 最长公共前缀
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。
     */
//    public String longestCommonPrefix(String[] strs) {
//
//    }


    /*162. 寻找峰值
峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     */
//    public int findPeakElement(int[] nums) {
//
//    }


    /*662. 二叉树最大宽度
给你一棵二叉树的根节点 root ，返回树的 最大宽度 。

树的 最大宽度 是所有层中最大的 宽度 。

每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。

题目数据保证答案将会在  32 位 带符号整数范围内。*/
//    public int widthOfBinaryTree(TreeNode root) {
//
//    }


    /*113. 路径总和 II
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。*/
    /**
     下面的代码是错误的。。。。
     */
    List<List<Integer>> resPathSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        resPathSum = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        pathSum(root,targetSum,path);
        return resPathSum;
    }

    private void pathSum(TreeNode root, int targetSum, LinkedList<Integer> path) {
        if (root==null) return;
        if (root.left==null&&root.right==null&&root.val==targetSum){
            resPathSum.add(new LinkedList<>(path));
            return;
        }
        path.add(root.val);
        pathSum(root.left,targetSum-root.val,path);
        pathSum(root.right,targetSum-root.val,path);
        path.removeLast();
    }


    /*179. 最大数
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     */
//    public String largestNumber(int[] nums) {
//        String[] strings = new String[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            strings[i] = String.valueOf(nums[i]);
//        }
//
//        Arrays.sort(strings,(a,b)-> {
//            return (b+a)-(a+b);
//        });
//        StringBuilder sb = new StringBuilder();
//        for (String str:strings){
//            sb.append(str);
//        }
//        if (sb.charAt(0)=='0') return "0";
//        return sb.toString();
//    }


    /*
    62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？
     */
//    public int uniquePaths(int m, int n) {
//
//    }



    /*560. 和为 K 的子数组
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。
     */
//    public int subarraySum(int[] nums, int k) {
//
//    }


    /*198. 打家劫舍
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     */
//    public int rob(int[] nums) {
//
//    }


    /*152. 乘积最大子数组
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个 32-位 整数。
     */
    /**下面的写法对吗？？为什么下面的写法是错误的？？？？？下面的改正需要验证一下*/
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        int preMin = nums[0],preMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res,preMax);
            int curMin = Math.min(Math.min(preMin*nums[i],preMax*nums[i]),nums[i]);
            int curMax = Math.max(Math.max(preMin*nums[i],preMax*nums[i]),nums[i]);
            preMin = curMin;
            preMax = curMax;
        }
        res = Math.max(res,preMax); /**err：错误的原因在这里，如果没有这一句，会导致最后一轮的循环不会更新，就可能丢失最大值*/
        return res;
    }
    /**下面的写法呢，对吗？？*/
    public int maxProduct1(int[] nums) {
        int res = nums[0];
        int preMin = nums[0],preMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(Math.min(preMin*nums[i],preMax*nums[i]),nums[i]);
            int curMax = Math.max(Math.max(preMin*nums[i],preMax*nums[i]),nums[i]);
            preMin = curMin;
            preMax = curMax;
            res = Math.max(res,preMax);
        }
        return res;
    }


    /*112. 路径总和
给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。

叶子节点 是指没有子节点的节点。
     */
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//
//    }


    /*169. 多数元素
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
//    public int majorityElement(int[] nums) {
//
//    }


    /*227. 基本计算器 II
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。

你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。

注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     */
//    public int calculate(String s) {
//
//    }



    /*83. 删除排序链表中的重复元素
给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     */
//    public ListNode deleteDuplicates1(ListNode head) {
//
//    }

    /*226. 翻转二叉树
给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
     */
//    public TreeNode invertTree(TreeNode root) {
//
//    }

    /*209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */
//    public int minSubArrayLen(int target, int[] nums) {
//
//    }


    /*139. 单词拆分
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     */
//    public boolean wordBreak(String s, List<String> wordDict) {
//
//    }


    /*718. 最长重复子数组
    给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     */
//    public int findLength(int[] nums1, int[] nums2) {
//
//    }


    /*24. 两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     */
//    public ListNode swapPairs(ListNode head) {
//
//    }


    /*283. 移动零
    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
//    public void moveZeroes(int[] nums) {
//
//    }


    /*468. 验证IP地址
    给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
    有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
    一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
    1 <= xi.length <= 4
    xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
    在 xi 中允许前导零。
    例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
     */
    /**
     *什么是合法的IP地址？
     *     IPV4（三个条件缺一不可）：必须是4段；每一段不能有前导零；每一段不能超过255；
     *     IPV6：
     *【完整步骤】
     *
     */
//    public String validIPAddress(String queryIP) {
//
//    }



}
