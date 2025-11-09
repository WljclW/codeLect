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
    public int findMin(int[] nums) {
        int left = 0,right = nums.length-1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]<nums[right]){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return nums[left];
    }

    /**标记的方法为什么是错误的？？？？？*/
    public int findMin__(int[] nums) {
        int left = 0,right = nums.length-1;
        int ans = 0;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]<nums[right]){
                ans = mid;
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return nums[ans];
    }




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
//        public CQueue() {
//
//        }
//
//        public void appendTail(int value) {
//
//        }
//
//        public int deleteHead() {
//
//        }
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
        for(int k=0;k<tmp.length;k++){ /**是不是错误的原因在于这里？？？*/
            record[left+k] = tmp[k];
        }
    }

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
    /**
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     */
    /*
    LCR 187. 破冰游戏
社团共有 num 位成员参与破冰游戏，编号为 0 ~ num-1。成员们按照编号顺序围绕圆桌而坐。社长抽取一个数字 target，从 0 号成员起开始计数，排在第 target 位的成员离开圆桌，且成员离开后从下一个成员开始计数。请返回游戏结束时最后一位成员的编号。
     */
    public int iceBreakingGame(int num, int target) {
        int res = 0;
        for (int i = 1; i < num; i++) {
            res = (res+1+target)%i;
        }
        return res;
    }


    /*120. 三角形最小路径和
    给定一个三角形 triangle ，找出自顶向下的最小路径和。

    每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     */
    /**这个题只能从最后一行倒着计算吗？？？
        1. 从最后一行倒着往上计算；每一行从左往右依次计算；
        2. 能不能从第一行开始计算？？如果能的话，每一行如何计算？？
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.get(triangle.size() - 1).size();
        int[] dp = new int[size];
        for (int i = triangle.size()-2; i >=0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int curVal = triangle.get(i).get(j);
                dp[j] = Math.min(dp[j],dp[j+1]) + curVal;
            }
        }
        return dp[0];
    }

    /*679。24 点游戏
    给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。

    你须遵守以下规则:

    除法运算符 '/' 表示实数除法，而不是整数除法。
    例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
    每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
    例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
    你不能把数字串在一起
    例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
    如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
     */
//    public boolean judgePoint24(int[] cards) {
//
//    }


    /*44.通配符匹配
    给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
    '?' 可以匹配任何单个字符。
    '*' 可以匹配任意字符序列（包括空字符序列）。
    判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
     */
    /**
     【说明】二维的dp中位置（i,j）依赖于（i-1,j-1）、（i,j-1）、（i-1,j），因此这个依赖关系和”编辑距离“这种题的依赖关系是一样的。。所
        以简化到1维的时候和”编辑距离“也有相似的写法
     **/
    public boolean isMatch(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i-1]&&p.charAt(i-1)=='*';
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc==pc||pc=='?'){
                    dp[i][j] |= dp[i-1][j-1];
                } else if (pc == '*') {
                    dp[i][j] |= dp[i][j-1];
                    dp[i][j] |= dp[i-1][j];
                }
            }
        }
        return dp[m+1][n+1];
    }


    /**两个一维数组 滚动的写法*/
    public boolean isMatch_(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[] prev = new boolean[n + 1];
        boolean[] cur = new boolean[n + 1];
        prev[0] = true;
        for (int i = 1; i <= n; i++) {
            prev[i] = prev[i-1]&&p.charAt(i-1)=='*';
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (pc==sc||pc=='?'){
                    cur[j] = prev[j-1];
                }else if (pc=='*'){
                    cur[j] |= cur[j-1];
                    cur[j] |= prev[j];
                }
            }
            boolean[] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        return prev[n];
    }

    public boolean isMatch__(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1]&&p.charAt(i-1)=='*';
        }

        for (int i = 1; i <= m; i++) {
            boolean prev = dp[0];
            dp[0] = false;
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                boolean tmp = dp[j];
                if (sc==pc||pc=='?'){
                    dp[j] |= prev;
                } else if (pc=='*') {
                    dp[j] |= dp[j-1];
                }
                prev = tmp;
            }
        }
        return dp[n];
    }


    /*887. 鸡蛋掉落
    给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     */
//    public int superEggDrop(int k, int n) {
//
//    }

    /*97. 交错字符串
    给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

    两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

    s = s1 + s2 + ... + sn
    t = t1 + t2 + ... + tm
    |n - m| <= 1
    交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
    注意：a + b 意味着字符串 a 和 b 连接。
    * */
//    public boolean isInterleave(String s1, String s2, String s3) {
//        int m = s1.length(),n = s2.length();
//        if (m+n != s3.length()) return false;
//        boolean[][] dp = new boolean[m + 1][n + 1];
//        dp[0][0] = true;
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                char c1 = s1.charAt(i - 1);
//                char c2 = s2.charAt(j - 1);
//                char c3 = s3.charAt(i+j-2);
//                if (c1==c3){
//                    dp[i][j] |= dp[]
//                }
//            }
//        }
//    }
    /**
     * =================================10=================================
     * =================================10=================================
     * =================================10=================================
     * =================================10=================================
     * =================================10=================================
     */
    /*395. 至少有 K 个重复字符的最长子串
    给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

    如果不存在这样的子字符串，则返回 0。
     */
//    public int longestSubstring(String s, int k) {
//
//    }


        /*673. 最长递增子序列的个数
给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。

注意 这个数列必须是 严格 递增的。
     */
//    public int findNumberOfLIS__(int[] nums) {
//
//    }
}
