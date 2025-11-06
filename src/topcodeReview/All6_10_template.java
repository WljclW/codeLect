package topcodeReview;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/11/6 15:02
 */
public class All6_10_template {
         /*297.二叉树的序列化与反序列化
    序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在
    一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原
    数据。
请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
    你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格
    式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
    * */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root==null) return "null";
            StringBuilder res = new StringBuilder();
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                TreeNode cur = queue.poll();
                /**StringBuilder的append方法，参数写成字符 和 参数写成一个字符串 的区别是什么？？*/
                if (cur!=null) res.append(cur.val).append(",");
                else res.append("null").append(",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            /**这里如果不去除最后一个多余的“,”行不行？？*/
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if ("null".equals(data)) return null;
            String[] split = data.split(",");
            String val = split[0];
            int index = 1;
            TreeNode root = new TreeNode(Integer.valueOf(val));
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                TreeNode cur = queue.poll();
                if (cur!=null){
                    String s = split[index];
                    if (!"null".equals(s)){
                        cur.left = new TreeNode(Integer.valueOf(s));
                    }
                    index++;
                    queue.offer(cur.left);

                    String s1 = split[index];
                    if (!"null".equals(s1)){
                        cur.right = new TreeNode(Integer.valueOf(s1));
                    }
                    index++;
                    queue.offer(cur.right);
                }
            }
            return root;
        }
    }


    /*153.寻找旋转排序数组中的最小值
    ...154是这个的拓展（允许有重复元素）
返回最小元素的值
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
* */
//    public int findMin(int[] nums) {
//
//    }




      /*
    460. LFU 缓存
    请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。

    实现 LFUCache 类：

    LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
    int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
    void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
    为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。

    当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。

    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     */
//    class LFUCache {
//
//        public LFUCache(int capacity) {
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


        /*224.基本计算器
        给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     */
//        public int calculate(String s) {
//
//        }


    /*40.组合总和 II
    给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的每个数字在每个组合中只能使用 一次 。
    注意：解集不能包含重复的组合。
     */
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//
//    }

    /*123.买卖股票的最佳时机 III
    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
//    public int maxProfit(int[] prices) {
//
//    }


    /*498.对角线遍历
        给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
     */
//    public int[] findDiagonalOrder(int[][] mat) {
//
//    }


    /*
    LCR 125. 图书整理 II
    读者来到图书馆排队借还书，图书管理员使用两个书车来完成整理借还书的任务。书车中的书从下往上叠加存放，图书管理员每次只能拿取书车顶部的书。排队的读者会有两种操作：

    push(bookID)：把借阅的书籍还到图书馆。
    pop()：从图书馆中借出书籍。
    为了保持图书的顺序，图书管理员每次取出供读者借阅的书籍是 最早 归还到图书馆的书籍。你需要返回 每次读者借出书的值 。

    如果没有归还的书可以取出，返回 -1 。
     */
//    class CQueue {
////        public CQueue() {
////
////        }
//
////        public void appendTail(int value) {
////
////        }
//
////        public int deleteHead() {
////
////        }
//    }


    /**
     * ===================================7=====================================
     * ===================================7=====================================
     * ===================================7=====================================
     * ===================================7=====================================
     * ===================================7=====================================
     * ===================================7=====================================
     */

    /*LCR 170 数组中的逆序对总数
    在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
     */
    int res = 0;
    public int reversePairs(int[] record) {
        if (record.length<=1) return 0;
        mergeSort11(record,0,record.length-1);
        return res;
    }

    private void mergeSort11(int[] record, int left, int right) {
        if (left>=right) return;
        int mid = left+(right-left)/2;
        mergeSort11(record,left,mid);
        mergeSort11(record,mid+1,right);
        merge111(record,left,mid,right);
    }

    private void merge111(int[] record, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int cur = 0;
        int i = left,j = mid+1;
        while (i<=mid&&j<=right){
            if (record[i]>record[j]){
                tmp[cur++] = record[j++];
                res += (mid-i+1);
            }else {
                tmp[cur++]  =record[i++];
            }
        }
        /**下面的两个步骤中，还涉及res的更新吗？？？
         TODO：这里的思想也有点绕。。得看”站在什么角度“思考问题。有点类似于”有效三角形的数量“这个题目
         */
        while (i<=mid) tmp[cur++]=record[i++];
        while (j<=right) tmp[cur++]=record[j++];
    }


        /*958
    给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。

在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
     */



    /*
    LCR 155. 将二叉搜索树转化为排序的双向链表
    将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。

    对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

    特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     */
//    public Node treeToDoublyList(Node root) {
//
//    }



    /*7.整数反转
        给定一个 32 位有符号整数 x，返回将其数字部分反转后的结果。
    如果反转后 超过 32 位有符号整数范围 [-2^31, 2^31 - 1]，返回 0
     */
//    public int reverse(int x) {
//        int res = 0;
//        while (x!=0){
//            int digit = x%10;
//            x /= 10;
//
//        }
//    }

            /*LCR 143. 子结构判断
    给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
注意，空树 不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
     */
//    public boolean isSubStructure(TreeNode A, TreeNode B) {
//
//    }

        /*572. 另一个树的子树
    给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。

二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
    * */
//    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//
//    }


    /*50. Pow(x, n)
实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。*/

    public double myPow(double x, int n) {
        double res = 1;
        double base = x;
        while (n!=0){
            if (n%2!=0){
                res *= base;
                n--;
            }
            base *= base;
            n /= 2;
        }
        return res;
    }
    /**
     *=====================8==================================
     *=====================8==================================
     *=====================8==================================
     *=====================8==================================
     *=====================8==================================
     */
      /*
    440. 字典序的第K小数字
    给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
        示例 1:
        输入: n = 13, k = 2
        输出: 10
        解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
     */
//    public int findKthNumber(int n, int k) {
//
//    }

    /*
    LCR 159. 库存管理 III
仓库管理员以数组 stock 形式记录商品库存表，其中 stock[i] 表示对应商品库存余量。请返回库存余量最少的 cnt 个商品余量，返回 顺序不限。
     */
//    public int[] inventoryManagement(int[] stock, int cnt) {
//
//    }


        /*329.矩阵中的最长递增路径
    给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
     */
    /**这种形式写dp应该是错误的*/
//    public int longestIncreasingPath(int[][] matrix) {
//        int[][] directs = {{1,0},{-1,0},{0,1},{0,-1}};
//        int res = 0;
//        int m = matrix.length,n = matrix[0].length;
//        int[][] dp = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i==0||j==0) dp[i][j] = 1;
//                else {
//                    for (int[] cur:directs){
//                        int x = i+cur[0];
//                        int y = j+cur[1];
//                        if (matrix[i][j]>matrix[x][y])
//                            dp[i][j] = Math.max(dp[x][y]+1,dp[i][j]);
//                    }
//                }
//                res = Math.max(dp[i][j],res);
//            }
//        }
//        return res;
//    }



        /*450.删除二叉搜索树中的节点
    给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

    首先找到需要删除的节点；
    如果找到了，删除它。
     */
//    public TreeNode deleteNode(TreeNode root, int key) {
//
//    }


    /*
    10. 正则表达式匹配
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。
     */
//    public boolean isMatch(String s, String p) {
//
//    }


        /*287. 寻找重复数
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
//    public int findDuplicate(int[] nums) {
//
//    }



            /*328.奇偶链表
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */
//    public ListNode oddEvenList(ListNode head) {
//
//    }
    /**
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     */
}
