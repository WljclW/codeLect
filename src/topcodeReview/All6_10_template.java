package topcodeReview;

import leecode_Debug.top100.TreeNode;

import java.lang.reflect.Array;
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
    /**标记的方法为什么是错误的？？？？？*/
    public int findMin__(int[] nums) {
        int left = 0,right = nums.length-1;
        int ans = 0;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]<nums[right]){ /**是不是因为这里少了等于？？还是说这个题ans必须标记最小值，标记最小值的索引就是错误的写法*/
                ans = mid;
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return nums[ans];
    }

    /**下面的代码确是对的，为什么？？看着也没啥区别*/
    public int findMin_(int[] nums) {
        int min = nums[nums.length - 1];
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < min) {
                min = nums[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return min;
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
    /**
     * ===================================7=====================================
     * ===================================7=====================================
     * ===================================7=====================================
     * ===================================7=====================================
     * ===================================7=====================================
     * ===================================7=====================================
     */
    /*LCR 143. 子结构判断
    给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
注意，空树 不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A==null || B==null) return false;
        return dfs(A,B);
    }

    private boolean dfs(TreeNode a, TreeNode b) {
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        queue1.offer(a);
        while (!queue1.isEmpty()){
            TreeNode cur1 = queue1.poll();
            if (cur1==null) continue;
            if (cur1.val==b.val&&isMatch143(cur1,b)){
                return true;
            }
            queue1.offer(cur1.left);
            queue1.offer(cur1.right);
        }
        return false;
    }

    private boolean isMatch143(TreeNode cur1, TreeNode cur2) {
        if (cur2 == null) return true;
        if (cur1 == null) return false;
        return cur1.val == cur2.val && isMatch143(cur1.left, cur2.left) && isMatch143(cur1.right, cur2.right);
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


    int maxLength = 0;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int[] cur:memo){
            Arrays.fill(cur,-1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                /**这里错了！！！对于matrix中的每一个位置，需要使用“longestIncreasingPath(int[][], int[][], int, int)”计算
                 该位置的最长递增长度*/
                longestIncreasingPath(matrix,memo,0,0);
            }
        }
        return maxLength;
    }

    private int longestIncreasingPath(int[][] matrix, int[][] memo, int i, int j) {
        if (memo[i][j]!=-1) return memo[i][j];
        memo[i][j] = 1;
        for (int[] dir:dirs){
            int x = i+dir[0],y=j+dir[1];
            if (x>0&&x<matrix.length&&j>0&&j<matrix[0].length&&matrix[i][j]<matrix[x][y]){
                memo[i][j] = Math.max(memo[i][j],memo[i][j]+1);
            }
        }
        return memo[i][j];
    }

    int[][] dirs_ = {{1,0},{-1,0},{0,1},{0,-1}};
    int res_ = 0;
    public int longestIncreasingPath_(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int[] row:memo){
            Arrays.fill(row,-1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(matrix,memo,i,j);
            }
        }
        return res_;
    }

    private int dfs(int[][] matrix, int[][] memo, int i, int j) {
        if (memo[i][j]!=-1) return memo[i][j];
        memo[i][j] = 1;
        for (int[] cur:dirs_){
            int x = cur[0]+i,y = cur[1]+j;
            /**chatgpt说这里应该检查“i,j”是否越界，而不是检查“x,y”是不是越界，应该是错误的*/
            if (x>0&&x<matrix.length&&j>0&&j<matrix[0].length&&matrix[i][j]<matrix[x][y]){
                memo[i][j] = Math.max(memo[i][j],memo[x][y]+1);
            }
        }
        res_ = Math.max(res_,memo[i][j]);
        return memo[i][j];
    }


    int[][] direct = {{1,0},{-1,0},{0,1},{0,-1}};
    public int longestIncreasingPath___(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[][] memo = new int[m][n];
        int res = 0;
        for (int[] cur:memo){
            Arrays.fill(cur,-1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                /**dfs函数有返回值，在主方法中更新res*/
                res = Math.max(res,dfsLongestIncreasingPath(matrix,memo,i,j));
            }
        }
        return res;
    }

    private int dfsLongestIncreasingPath(int[][] matrix, int[][] memo, int i, int j) {
        if (memo[i][j]!=-1) return memo[i][j];
        memo[i][j] = 1;
        for (int[] cur:direct){
            int x = i+cur[0],y = j+cur[1];
            if (x>0&&x<matrix.length&&j>0&&j<matrix[0].length&&matrix[x][y]>matrix[i][j]){
                memo[i][j] = Math.max(memo[i][j],dfsLongestIncreasingPath(matrix,memo,x,y));
            }
        }
        return memo[i][j];
    }


    /**
     * 329能不能修改成“动态规划”的版本呢？？确认一下下面的版本有没有问题!!
     【思想】
        1. “递归、回溯遍历”计算答案的解法 改成 “动态规划”的解法，最最最关键、核心的问题（或者说前提条件）————确保每到达
     一个位置，所有它依赖位置的答案已经计算出来了！！（这一点很重要，要求熟记于心）但是前提是对于任何一个节点它的依赖位置
     是明确的。
        2. 换到此题，每一个位置（i，j）可能依赖于四周的四个位置，从这个角度看，是不能进行dp的，没有任何一种遍历方式能满足
     这个二要求。。。下面的longestIncreasingPath__是基于这样的思想，任何一个位置的dp。一定依赖于比他小的位置的dp，因此
     在计算所有位置的dp时，先对matrix的所有值排序，保证计算顺序
           ——————因此，DP的写法中，重要的是”所有位置计算顺序的确定“。这种“计算顺序”，可能是像“编辑距离”、“爬楼梯”等在二
     维表中明确的位置依赖关系，也可能是像这个题一样在二维表中无法确定明确的依赖位置，需要结合matrix该位置的值来判断
     */
    public int longestIncreasingPath__(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                cells.add(new int[]{i, j});
        /*cells中添加所有的位置数组；这一步按照matrix中该位置的值进行排序。
        因此最终cells中的数组，就是matrix元素升序排序后的位置顺序
        */
        cells.sort((a,b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);

        int[][] dp = new int[m][n];
        int ans = 1;
        for (int[] c : cells) {
            int i = c[0], j = c[1];
            dp[i][j] = 1;
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[x][y] + 1);
                }
            }
            ans = Math.max(ans, dp[i][j]);
        }
        return ans;
    }




        /*450.删除二叉搜索树中的节点
    给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

    首先找到需要删除的节点；
    如果找到了，删除它。
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;

        if (key < root.val) {
            return deleteNode(root.left, key);
        } else if (key > root.val) {
            return deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            return deleteNode(root.right, minNode.val);
        }
    }

    private TreeNode findMin(TreeNode root) {
        while (root.left!=null) root = root.left;
        return root;
    }


    /*
    10. 正则表达式匹配
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 2; i <= n; i++) {
            dp[0][i] = dp[0][i-2]&&p.charAt(i-1)=='*';
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc==pc||pc=='.'){
                    dp[i][j] = dp[i-1][j-1];
                } else if (pc == '*') {
                    char pcPrev = p.charAt(j - 2);
                    dp[i][j] = dp[i][j-2];
                    if (pcPrev==sc){ /**这里少考虑了 pcPrev 是字符‘。’的情况，对不对？？验证一下*/
                        dp[i][j] |= dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
    /**
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     */
    /*887. 鸡蛋掉落
    给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     */
    /**最优解的解释————
     1. 逆向思维：与其问“n 层楼最少要多少步”，不如问：如果我们可以扔 m 次，有 k 个鸡蛋，最多能测多少层？因此有如下的转换————
         dp数组的含义：dp[k][m] 表示 k 个鸡蛋，扔 m 次，最多能测试多少层
         dp的状态转移：当我从某一层扔下去：
             如果碎了：能测 dp[k-1][m-1] 层；
             如果没碎：能测 dp[k][m-1] 层；
             再加上当前这一层本身 +1。
     */
    public int superEggDrop(int K, int N) {
        // dp[k][m] 表示 k 个鸡蛋，m 次操作最多能测的楼层数
        int[] dp = new int[K + 1];
        int m = 0;
        // 不断增加尝试次数 m，直到能测完所有 N 层
        while (dp[K] < N) {
            m++;
            for (int k = K; k >= 1; k--) {
                /**最大的疑问：最多能测多少层，不应该是取最值吗？？为什么这里是加和？？？*/
                dp[k] = dp[k - 1] + dp[k] + 1;
            }
        }
        return m;
    }

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
//        if (m+n!=s3.length()) return false;
//        boolean[][] dp = new boolean[m + 1][n + 1];
//        dp[0][0] = true;
//        for (int i = 1; i <= n; i++) {
//            dp[0][i] = dp[0][i-1]&&s2.charAt(i-1)==s3.charAt(i-1);
//        }
//        for (int i = 1; i <= m; i++) {
//            dp[i][0] = dp[i-1][0]&&s1.charAt(i-1)==s3.charAt(i-1);
//        }
//
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                char c1 = s1.charAt(i - 1);
//                char c2 = s2.charAt(j - 1);
//
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
    /**下面的代码是错误的！！！*/
    public int longestSubstring(String s, int k) {
        return dfsss(s,k,0,s.length());
    }

    /*作用：返回[left，right)这段子串满足题目要求的最长合法子串*/
    private int dfsss(String s, int k, int left, int right) {
        if (right - left < k) return 0; /*这一步相当于剪枝优化*/

        int[] flags = new int[26];
        for (int i = left; i < right; i++) {
            flags[s.charAt(left) - 'a']++;
        }
        /*遍历[left,right)的所有字符————
            如果某位置的字符出现的次数小于k，说明该位置的字符不可能在结果中，需要递归它的左右两半，返回左右两半的最大值
        */
        for (int i = left; i < right; i++) {
            /**只研究出现次数不够k的字母*/
            if (flags[s.charAt(i) - 'a'] < k) {
                /*下面两行是跳过所有不足K的字符。相当于回溯的剪枝————因此没有的话整体的逻辑也是对的*/
                int j = i + 1;
                while (j < right && flags[s.charAt(j) - 'a'] < k) j++;
                return Math.max(dfsss(s, k, left, i), dfsss(s, k, j, right));
            }
        }
        return right - left;
    }


    /*994 腐烂的橘子 (Rotting Oranges)
    在一个 m x n 的网格中，每个单元格有三种值：
        0 表示空格
        1 表示新鲜橘子
        2 表示腐烂橘子

        每过 1 分钟，所有腐烂橘子都会让上下左右四个方向的新鲜橘子变腐烂。
        求需要多少分钟，才能让所有新鲜橘子都腐烂。
        如果不可能让所有橘子都腐烂，返回 -1。
     */
    /**验证一下下面的解法对不对，，是错误的*/
    int ressss = 0;
    public int orangesRotting(int[][] grid) {
        LinkedList<int[]> queue = new LinkedList<>();
        int cnt  =0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==2){
                    queue.offer(new int[]{i,j});
                } else if (grid[i][j] == 1) {
                    cnt++;
                }
            }
        }
        /**由于 dfsOrangesRottings 中并不会递归的调用，因此这里 dfsOrangesRottings的逻辑 直接写在 方
         法orangesRotting内就可以*/
        dfsOrangesRottings(grid,queue,cnt);
        return ressss;
    }

    private void dfsOrangesRottings(int[][] grid, LinkedList<int[]> queue, int cnt) {
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (cnt>0&&!queue.isEmpty()){
            int size = queue.size();
            ressss++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir:dirs){
                    int x = dir[0]+cur[0],y = dir[1]+cur[1];
                    if (x>0&&x<grid.length&&y>0&&y<grid[0].length&&grid[x][y]==1){
                        cnt--;
                        grid[x][y] = 2;
                        queue.offer(new int[]{x,y});
                    }
                }
            }
        }
    }

//    private void dfsOrangesRottings(int[][] grid, LinkedList<int[]> queue, int cnt) {
//        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
//        while (!queue.isEmpty()){
//            int size = queue.size();
//            boolean hasRot = false;
////            ressss++;
//            for (int i = 0; i < size; i++) {
//                int[] cur = queue.poll();
//                for (int[] dir:dirs){
//                    int x = dir[0]+cur[0],y = dir[1]+cur[1];
//                    if (x>0&&x<grid.length&&y>0&&y<grid[0].length&&grid[x][y]==1){
//                        grid[x][y] = 2;
//                        hasRot = true;
//                        queue.offer(new int[]{x,y});
//                    }
//                }
//            }
//            if (hasRot) ressss++;
//        }
//    }
}
