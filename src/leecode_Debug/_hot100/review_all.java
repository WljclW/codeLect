package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * 【need copy when use】
 * 23. 合并 K 个升序链表
 * 155. 最小栈
 * 287. 寻找重复数
 * 300. 最长递增子序列
 * 41. 缺失的第一个正数
 * 24. 两两交换链表中的节点
 * 105. 从前序与中序遍历序列构造二叉树
 * 31. 下一个排列
 * 101
 * 438、76、73、54、108
 * 347、215、295
 * 25. K 个一组翻转链表
 *  5. 最长回文子串
 *  322. 零钱兑换
 *  279. 完全平方数
 *  394. 字符串解码
 *  4. 寻找两个正序数组的中位数
 *  131. 分割回文串
 *  22. 括号生成
 * 416
 * 112、113、437、（路经总和问题）
 * 82. 删除排序链表中的重复元素 II
 * 143. 重排链表
 * 328
 * 402. 移掉 K 位数字
 * 560
 * 43
 * @author mini-zch
 * @date 2025/7/22 16:20
 */
public class review_all {
    /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
//    public int[] maxSlidingWindow(int[] nums, int k) {
//
//    }

    /*238.
    * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素
    * 的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    * */
//    public int[] productExceptSelf(int[] nums) {
//
//    }

    //75.颜色分类
//    public void sortColors(int[] nums) {
//
//    }

    //25. k个一组翻转链表
//    public ListNode reverseKGroup(ListNode head, int k) {
//
//    }

    /*234.
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返
     * 回 true ；否则，返回 false 。*/
//    public boolean isPalindrome(ListNode head) {
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
//     class LRUCache {
//
//         public LRUCache(int capacity) {
//
//         }
//
//         public int get(int key) {
//
//         }
//
//         public void put(int key, int value) {
//
//         }
//     }



    /*138复制链表*/
    public Node copyRandomList(Node head) {
        return null;
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
//    public ListNode mergeKLists(ListNode[] lists) {
//
//    }

    /*394.
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
//    public int jump(int[] nums) {
//
//    }


    /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
    public int findDuplicate(int[] nums) {
        return 0;
    }

    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
//    public int lengthOfLIS1(int[] nums) {
//
//    }


    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
//    public int maxProduct(int[] nums) {
//
//    }


    /*49.
   * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
   字母异位词 是由重新排列源单词的所有字母得到的一个新单词。*/
//    public List<List<String>> groupAnagrams(String[] strs) {
//
//    }



    /*
     * 11.给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
     *    成的，柱子之间是没有间隙的。
     * */
//    public int maxArea(int[] height) {
//
//    }


    /*
     * 15.给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * 【】：关键的步骤在于双指针 过程中，如何跳过所有相同的元素。
     * */
    //写法1
//    public List<List<Integer>> threeSum(int[] nums) {
//
//    }


    /*
     * 42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * */
//    public int trap(int[] height) {
//
//    }


    //3
//    public int lengthOfLongestSubstring(String s) {
//
//    }



    /*560.
    * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
//    public int subarraySum(int[] nums, int k) {
//
//    }



    /*56.
     *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
     * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * */
//    public int[][] merge(int[][] intervals) {
//
//    }


    /*41.
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
    /*解法1：简洁*/
    public int firstMissingPositive(int[] nums) {
        return 0;
    }


    /*240.
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
//    public boolean searchMatrix(int[][] matrix, int target) {
//
//    }


    /*2.
    * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。*/
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//    }


    /*19.
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    /*自己的解法*/
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//
//    }



    /*24.
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下
     * 完成本题（即，只能进行节点交换）。*/
    /*非递归的形式；非递归形式看官方讲解*/
//    public ListNode swapPairs1(ListNode head) {
//
//    }


    /*94中序遍历*/
//    public List<Integer> inorderTraversal(TreeNode root) {
//
//    }


    /*104
    * 给定一个二叉树 root ，返回其最大深度。
    二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。*/
//    public int maxDepth(TreeNode root) {
//
//    }


    /*543.
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
//    public int diameterOfBinaryTree(TreeNode root) {
//    }


    /*102.层序遍历*/
//    public List<List<Integer>> levelOrder(TreeNode root) {
//
//    }


    /*98.
    * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。*/
//    public boolean isValidBST(TreeNode root) {
//
//    }


    /*199.
    给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    * */
//    public List<Integer> rightSideView(TreeNode root) {
//
//    }


    /*114.
    * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。*/
//    public void flatten(TreeNode root) {
//
//    }


//    /*105.
//     * 从前序 和 中序 构造出二叉树*/
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//
//    }


    /*236.二叉树最近公共祖先
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return null;
    }

    //35
//    public int searchInsert(int[] nums, int target) {
//
//    }

    //74
//    public boolean searchMatrix_(int[][] matrix, int target) {
//
//    }

    //33
//    public int search(int[] nums, int target) {
//
//    }

    //153
//    public int findMin(int[] nums) {
//
//    }

    //739
//    public int[] dailyTemperatures(int[] temperatures) {
//
//    }

    //121
//    public int maxProfit(int[] prices) {
//
//    }

    /*763.
    * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片
    * 段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或
    * ["ab", "ab", "cc"] 的划分是非法的。
        注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
        返回一个表示每个字符串片段的长度的列表。
    * */
    // 763的写法1
//    public List<Integer> partitionLabels1(String s) {
//
//    }

    //169
//    public int majorityElement(int[] nums) {
//
//    }

    //31
    public void nextPermutation(int[] nums) {

    }


    /*129
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    每条从根节点到叶节点的路径都代表一个数字：

    例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    计算从根节点到叶节点生成的 所有数字之和 。
    叶节点 是指没有子节点的节点。
    * */
//    public int sumNumbers(TreeNode root) {
//
//    }



    /*43
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    * */
//    public String multiply(String num1, String num2) {
//
//    }


    /*162
    峰值元素是指其值严格大于左右相邻值的元素。
    给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在
    位置即可。你可以假设 nums[-1] = nums[n] = -∞ 。
    你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
    * */
//    public int findPeakElement(int[] nums) {
//
//    }


    /*209
    给定一个含有 n 个正整数的数组和一个正整数 target 。
    找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返
    回其长度。如果不存在符合条件的子数组，返回 0 。
    * */
//    public int minSubArrayLen(int target, int[] nums) {
//
//    }


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
//    public int longestConsecutive(int[] nums) {
//
//    }

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
//    public void rotate(int[][] matrix) {
//
//    }

    //160
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//
//    }


    /*142.
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
//    public ListNode detectCycle(ListNode head) {
//
//    }


    /*148.
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
//    public ListNode sortList(ListNode head) {
//
//    }


    /*
     * 226.给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * */
//    public TreeNode invertTree(TreeNode root) {
//
//    }


    /*101.给你一个二叉树的根节点 root ， 检查它是否轴对称。*/
    public boolean isSymmetric(TreeNode root) {
        return false;
    }

    /*98.
    * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。*/
    //中序遍历迭代
//    public boolean isValidBST_diedai(TreeNode root) {
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


    //34
//    public int[] searchRange(int[] nums, int target) {
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
//    public int longestCommonSubsequence(String text1, String text2) {
//
//    }

    //72
//    public int minDistance(String word1, String word2) {
//
//    }

    /*583. 两个字符串的删除操作
     * */
//    public int minDistance_583(String word1, String word2) {
//
//    }

    /*139.
    * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利
    * 用字典中出现的一个或多个单词拼接出 s 则返回 true。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
//    public boolean wordBreak(String s, List<String> wordDict) {
//
//    }


    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    /*解法1：朴素的做法
     * dp[i]：表示以第i个元素结尾的最长递增子序列有多长*/
//    public int lengthOfLIS(int[] nums) {
//
//    }


    /*416.
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两
     * 个子集，使得两个子集的元素和相等。*/
    public boolean canPartition(int[] nums) {
        return false;
    }

    /*32.
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的
     * 长度。*/
//    public int longestValidParentheses(String s) {
//
//    }


    /*46.
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    * */
    List<List<Integer>> resPermute;
    List<Integer> pathPermute;
    boolean[] usedPermute;

    public List<List<Integer>> permute(int[] nums) {
        resPermute = new LinkedList<>();
        pathPermute = new LinkedList<>();
        usedPermute = new boolean[nums.length];
        permuteBack(nums);
        return resPermute;
    }

    private void permuteBack(int[] nums) {
        if (pathPermute.size()==nums.length){
            resPermute.add(new LinkedList<>(pathPermute)); /**8.2 这里如果直接add路径pathPermute，不new行不行*/
            return;
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
        resSubSets.add(new LinkedList<>(pathSubsets)); /**这里不new一个新的应该是不行的*/
        //这里没有return应该也是可以的
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
//    public List<String> letterCombinations(String digits) {
//
//    }


    /*39.
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。
    * */
    List<List<Integer>> resCombinationSum;
    List<Integer> pathCombinationSum;
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        return resCombinationSum;
    }



    /*22.
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
    /*解法1：官方解回溯法*/
//    public List<String> generateParenthesis(int n) {
//
//    }


    /*79.
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    * */
//    public boolean exist(char[][] board, String word) {
//
//    }


    /*51.
    按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    * */
//    public List<List<String>> solveNQueens(int n) {
//
//    }


    /**========================================================_09huisu中补充的非hot100需要做============================*/


    /*
    82
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
//    public ListNode deleteDuplicates(ListNode head) {
//
//    }

    /*83*/
//    public ListNode deleteDuplicates1(ListNode head) {
//
//    }


    /*124
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列
    中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        return 0;
    }


    /*165
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


    /*
    143
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {

    }


    /*662
    给你一棵二叉树的根节点 root ，返回树的 最大宽度 。树的 最大宽度 是所有层中最大的 宽度 。
    每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构
    相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
    题目数据保证答案将会在  32 位 带符号整数范围内。
    * */
//    public int widthOfBinaryTree(TreeNode root) {
//
//    }


    /*113
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
    叶子节点 是指没有子节点的节点。
    * */
    LinkedList<List<Integer>> resPathSum;
    LinkedList<Integer> pathPathSum;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        return resPathSum;
    }


   /*112
   给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条
   路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。叶子节点 是指没有子节
   点的节点。
   */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return false;
    }

    /*437,与560题是类似的
    给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
    路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
    * */
    public int pathSum_437(TreeNode root, int targetSum) {
        return 0;
    }

    /**看一下17题StringBuilder声明为全局变量、声明在形参位置时，代码的区别*/


    /*61
    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
//    public ListNode rotateRight(ListNode head, int k) {
//
//    }


    /*402
    给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字
    符串形式返回这个最小的数字。
    * */
//    public String removeKdigits(String num, int k) {
//
//    }

    /*145
    给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
    * */
//    public List<Integer> postorderTraversal(TreeNode root) {
//
//    }


    /*328
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */
    public ListNode oddEvenList(ListNode head) {
        return null;
    }

    /*135
    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

    你需要按照以下要求，给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */
//    public int candy(int[] ratings) {
//
//    }


    /*134
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     */
//    public int canCompleteCircuit(int[] gas, int[] cost) {
//
//    }


    /**
     * ===========================================================7.25===============================================
     */
    /*LCR 155. 将二叉搜索树转化为排序的双向链表
    将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     */
//    class Node {
//        public int val;
//        public Node left;
//        public Node right;
//
//        public Node() {}
//
//        public Node(int _val) {
//            val = _val;
//        }
//
//        public Node(int _val, Node _left, Node _right) {
//            val = _val;
//            left = _left;
//            right = _right;
//        }
//    }
    /**这个题最后需要返回，双向链表。因此尽量不要使用虚拟头节点*/
//    public Node treeToDoublyList(Node root) {
//        if (root==null) return root;
//        Node res = null,resTmp = res;
//        Stack<Node> stack = new Stack<>();
//        while (root!=null||!stack.isEmpty()){
//            if (root!=null){
//                stack.push(root);
//                root = root.left;
//            }else {
//                Node cur = stack.pop();
//                root = cur.right;
//                if (res==null){
//                    res = cur;
//                }else {
//                    res.right = cur;
//                    cur.left = res;
//                }
//                if (root==null){
//                    cur.right = resTmp;
//                    resTmp.left = cur;
//                }
//            }
//        }
//        return resTmp;
//    }


    /*678
    给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。

    有效 字符串符合如下规则：

    任何左括号 '(' 必须有相应的右括号 ')'。
    任何右括号 ')' 必须有相应的左括号 '(' 。
    左括号 '(' 必须在对应的右括号之前 ')'。
    '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
     */
//    public boolean checkValidString(String s) {
//
//    }

    //https://mp.weixin.qq.com/s?__biz=MzA4NDE4MzY2MA==&mid=2647522825&idx=1&sn=302c27da39845ab4952a2de067cfdea8&scene=21&poc_token=HGZug2ij4wx7mT7pbRLRTaHh3HHWsK6WRmMarBm3
}
