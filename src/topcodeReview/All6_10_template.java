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


    /*40.组合总和 II
    给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的每个数字在每个组合中只能使用 一次 。
    注意：解集不能包含重复的组合。
     */
    List<List<Integer>> resCombinationSum2;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        resCombinationSum2 = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        combinationSum2(candidates,target,path,0);
        return resCombinationSum2;
    }

    private void combinationSum2(int[] candidates, int target, LinkedList<Integer> path, int index) {
        if (target==0) resCombinationSum2.add(new LinkedList<>(path));
//        if (target<0 || index==candidates.length) return;  /**有target<0这个条件应该没毛病，会更好*/
        if (index==candidates.length) return;
        for (int i = index; i < candidates.length; i++) {
            target -= candidates[i];
            path.add(candidates[i]);
            combinationSum2(candidates,target,path,i+1);
            target += candidates[i];
            path.removeLast();
        }
    }

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
    public int findDuplicate(int[] nums) {
        int slow = nums[0],fast = nums[0];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = nums[0];
        while (slow!=fast){
            fast = nums[fast];
            slow = nums[slow];
        }
//        return nums[slow];   /**这里是不是要使用nums获取slow对应的数？？还是说直接返回slow*/
        return slow;   /**这里是不是要使用nums获取slow对应的数？？还是说直接返回slow，应该是需要直接返回slow*/
    }

    public int findDuplicate_(int[] nums) {
        int slow =0,fast =0;
        do{
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



            /*328.奇偶链表
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode oddHead = head,evenHead = head.next;
        ListNode oddCur = oddHead,evenCur = evenHead;
        while (evenCur!=null&&evenCur.next!=null){
            oddCur.next = oddCur.next.next;
            oddCur = oddCur.next;

            evenCur.next = evenCur.next.next;
            evenCur = evenCur.next;
        }
        oddCur.next = evenHead;
        return head;
    }
    /**
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     */

        /*295.数据流的中位数
    中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的
    平均值。
    例如 arr = [2,3,4] 的中位数是 3 。
    例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
    实现 MedianFinder 类:
        MedianFinder() 初始化 MedianFinder 对象。
        void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
        double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答
        案将被接受。
     */
    class MedianFinder {
        PriorityQueue<Integer> min; /**存放较小的一半数，因此要快速地拿出这一部分的最大值，因此需要大根堆（优先级队列默认是小根堆）*/
        PriorityQueue<Integer> max;
        public MedianFinder() {
            min = new PriorityQueue<>((a,b)->(Integer.compare(b,a)));
            max = new PriorityQueue<>();
        }

        public void addNum(int num) {
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



    /*230.二叉搜索树中第 K 小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。*/
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode cur = stack.pop();
                if (--k == 0) return cur.val;
                root = cur.right;
            }
        }
        return -1;
    }



        /*678.有效的括号字符串
    给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。

    有效 字符串符合如下规则：

    任何左括号 '(' 必须有相应的右括号 ')'。
    任何右括号 ')' 必须有相应的左括号 '(' 。
    左括号 '(' 必须在对应的右括号之前 ')'。
    '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
     */
    public boolean checkValidString(String s) {
        int min=0,max=0;
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


        /*516. 最长回文子序列
    给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n-2; i >=0 ; i--) {
            char ci = s.charAt(i);
            for (int j = i+1; j < n; j++) {
                char cj = s.charAt(j);
                if (ci==cj){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    //两行数组的写法
    public int longestPalindromeSubseq_(String s) {
        int n = s.length();
        int[] prev = new int[n];
        prev[n-1] = 1;
        int[] cur = new int[n];

        for (int i = n-2; i > 0; i--) {
            cur[i] = 1;
            char ci = s.charAt(i);
            for (int j = i+1; j < n; j++) {
                char cj = s.charAt(j);
                if (ci==cj){
                    cur[j] = prev[j-1] + 2;
                }else {
                    cur[j] = Math.max(cur[j-1],prev[j]);
                }
            }
            int[] tmp = prev;
            prev = cur;
            cur = tmp; /**还是需要认真理解一下，为什么滚动数组的情形下，prev赋值给cur不会出问题*/
        }
        return prev[n-1];
    }



    /*106.从中序与后序遍历序列构造二叉树
    给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     */
    HashMap<Integer,Integer> inorderMap;
    int postorderIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderMap = new HashMap<>();
        postorderIndex = postorder.length-1;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(inorder,postorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int left, int right) {
        if (left>right) return null; /**如果没有这一句应该是错误的~~~~~~~*/
        int val = postorder[postorderIndex--];
        TreeNode root = new TreeNode(val);
        Integer index = inorderMap.get(val);
        root.right = buildTree(inorder,postorder,index+1,right);
        root.left = buildTree(inorder,postorder,left,index-1);
        return root;
    }



        /*96. 不同的二叉搜索树
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */
    public int numTrees(int n) {
        if (n<=2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                /**
                 1. 以”第j个节点“座位根节点的方案总数：dp[j-1]*dp[i-j].
                    其中”j-1“是左子树的节点数量，”i-j“是右子树的节点数量
                 2. ”i个节点组成的二叉搜索树数量“等于 以第1个节点作为根节点的二叉搜索树数量 + 以第2个节点作为根节点的二叉搜索树数量 .... + 以第i个节点作为根节点的二叉搜索树数量
                 */
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }



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


        /*9. 回文数
    给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

    回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    例如，121 是回文，而 123 不是。*/
    public boolean isPalindrome(int x) {
        if (x==0) return true;
        if (x<0) return false;
        int res = 0;
        while (x>res){
            int digit = x%10;
            res = res*10 + digit;
            x /= 10;
        }
        return x==res||x==res/10;
    }


        /* 384.打乱数组
        实现一个支持以下操作的类：

        Solution(int[] nums) —— 用整数数组初始化对象

        reset() —— 重置数组到最初状态并返回

        shuffle() —— 返回数组随机打乱后的结果
     */
        class Solution {
            int[] origin;
            int[] use;
            public Solution(int[] nums) {
                origin = nums;
                use = nums.clone();
            }

            public int[] reset() {
                use = origin.clone();
                return use;
            }

            public int[] shuffle() {
                for (int i = 0; i < use.length; i++) {
                    int index = new Random().nextInt(i + 1, use.length);
                    swap11(use,i,index);
                }
                return use;
            }

            private void swap11(int[] use, int l, int r) {
                int tmp = use[l];
                use[l] = use[r];
                use[r]  =tmp;
            }
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


    /*
    LCR 139. 训练计划 I
教练使用整数数组 actions 记录一系列核心肌群训练项目编号。为增强训练趣味性，需要将所有奇数编号训练项目调整至偶数编号训练项目之前。请将调整后的训练项目编号以 数组 形式返回。
     */
    public int[] trainingPlan(int[] actions) {
        int left = 0;
        for (int i = 0; i < actions.length; i++) {
            if ((actions[i]&1)==1){
                swap1(actions,left++,i);
            }
        }
        return actions;
    }

    private void swap1(int[] actions, int l, int r) {
        int tmp = actions[l];
        actions[l] = actions[r];
        actions[r] = tmp;
    }


    /*189.轮转数组
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * */
    public void rotate(int[] nums, int k) {
        k%= nums.length;
        if (k==0) return;
        rever(nums,0,nums.length-1);
        rever(nums,0,k-1);
        rever(nums,k,nums.length-1);
    }

    private void rever(int[] nums, int left, int right) {
        while (left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
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

        /*
    416. 分割等和子集
    给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两
    个子集，使得两个子集的元素和相等。
    * */
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum&1)==1) return false;
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = nums[0]; i <= sum; i++) {
            dp[i] = nums[0];
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j-nums[i]]+nums[i],dp[j]);
            }
        }
        return dp[sum]==sum;
    }


    /*611.有效三角形的个数
    给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length-1; i >= 2; i--) {
            int left = 0,right = i-1;
            while (left<right){
                int curVal = nums[left]+nums[right]-nums[i];
                if (curVal>0){
                    res += (right-left);
                    right--; /**当前满足的时候，尝试减小right。（这种情况下由于right这个边变了，因此下一轮必然是新的方案了）*/
                }else {
                    left++;
                }
            }
        }
        return res;
    }


            /*210 课程表Ⅱ
    现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<>());
        }

        int[] indgree = new int[numCourses];
        for (int[] cur:prerequisites){
            int index = cur[0],preCourse = cur[1];
            graph.get(preCourse).add(index);
            indgree[index]++;
        }

        LinkedList<Integer> zeroIndgree = new LinkedList<>();
        for (int i = 0; i < indgree.length; i++) {
            if (indgree[i]==0) zeroIndgree.offer(i);
        }

        int[] res = new int[numCourses];
        /**好像用下面的”链表的形式“来存储结果不方便？？？*/
//        LinkedList<Integer> res = new LinkedList<>();
        int cnt = 0;
        while (!zeroIndgree.isEmpty()){
            Integer index = zeroIndgree.poll();
            res[cnt] = index;
            cnt++;
//            this.res.offer(index);
            for (int curIndex:graph.get(index)){
                indgree[curIndex]--;
                if (indgree[curIndex]==0) zeroIndgree.offer(curIndex);
            }
        }
//        return cnt==numCourses? this.res.toArray():new int[0];
        return cnt==numCourses? res:new int[0];
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


        /*400.第N个数字
    给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
     */
    public int findNthDigit(int n) {
        int digit = 1, min = 1;
        while (n > 9 * digit * min) {
            n -= 9 * digit * min;
            digit++;
            min *= 10;
        }
        int num = min + (n - 1) / digit;
        return String.valueOf(num).charAt((n - 1) % digit);
    }


    /**
     * =================================10=================================
     * =================================10=================================
     * =================================10=================================
     * =================================10=================================
     * =================================10=================================
     */


        /*85.最大矩形
    给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[] height = new int[n];
        int resArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]=='1'){
                    height[j]++;
                }else {
                    height[j] = 0;
                }
            }
            resArea = Math.max(resArea,getArea(height));
        }
        return resArea;
    }

    private int getArea(int[] height) {
        int res1 = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length + 1; i++) {
            int curHeight = i==height.length?0:height[i];
            while (!stack.isEmpty()&&curHeight<height[stack.peek()]){
                int curIndex = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                res1 = Math.max(res1,(i-left-1)*height[curIndex]);
            }
            stack.push(i);
        }
        return res1;
    }



        /*1004.最大连续1的个数 III
    给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
     */
    public int longestOnes(int[] nums, int k) {
        int cnt =0;
        int left = 0,cur = 0;
        int res =0;
        while (cur<nums.length){
            cnt++;
            while (cnt>k){
                int lNum = nums[left++];
                if (lNum==0) cnt--;
            }
            res = Math.max(res,cnt-left);
        }
        return res;
    }



    /*63.不同路径 II
   给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。

   网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。

   返回机器人能够到达右下角的不同路径数量。

   测试用例保证答案小于等于 2 * 109。
    */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i]==0) dp[0][i] = 1;
            else break;
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0]==0) dp[i][0] = 1;
            else  break;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j]==0){
                    if (obstacleGrid[i-1][j]==0) dp[i][j] += dp[i-1][j];
                    if (obstacleGrid[i][j-1]==0) dp[i][j] += dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }


    /*395. 至少有 K 个重复字符的最长子串
    给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

    如果不存在这样的子字符串，则返回 0。
     */
//    public int longestSubstring(String s, int k) {
//
//    }


     /*134. 加油站
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSum = 0,curSum = 0;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += (gas[i]-cost[i]);
            totalSum += (gas[i]-cost[i]);
            if (curSum<0){
                curSum = 0;
                index = i+1;
            }
        }
        return totalSum<0?-1:index;
    }


    /*
    LCR 144. 翻转二叉树
给定一棵二叉树的根节点 root，请左右翻转这棵二叉树，并返回其根节点。
     */
    public TreeNode flipTree(TreeNode root) {
        if (root==null) return root;
        TreeNode left = flipTree(root.left);
        TreeNode right = flipTree(root.right);
        root.left =right;
        root.right = left;
        return root;
    }

    public TreeNode flipTree_(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            swap12(cur);
            if (cur.left!=null) queue.offer(cur.left);
            if (cur.right!=null) queue.offer(cur.right);
        }
        return root;
    }

    private void swap12(TreeNode cur) {
        TreeNode tmp = cur.left;
        cur.left = cur.right;
        cur.right  = tmp;
    }

    /*
    LCR 146. 螺旋遍历二维数组
给定一个二维数组 array，请返回「螺旋遍历」该数组的结果。

螺旋遍历：从左上角开始，按照 向右、向下、向左、向上 的顺序 依次 提取元素，然后再进入内部一层重复相同的步骤，直到提取完所有元素。
     */
    public int[] spiralArray(int[][] array) {
        if (array.length==0) return new int[0];
        int top =0,bottom=array.length-1,left =0,right=array[0].length-1;
        int[] res = new int[array.length * array[0].length];
        int index = 0;
        /**由于这个题中引入了”index变量“，因此每一个for循环结束后”根据index来判断是不是终止也是🆗的“*/
        while (true){
            for (int i = left; i <= right; i++) {
                res[index++] = array[top][i];
            }
            if (++top>bottom) break;

            for (int i = top; i <= bottom; i++) {
                res[index++] = array[i][right];
            }
            if (--right<left) break;

            for (int i = right; i >= left; i--) {
                res[index++] = array[bottom][i];
            }
            if (--bottom<top) break;

            for (int i = bottom; i >= top; i--) {
                res[index++] = array[i][left];
            }
            if (++left>right) break;
        }
        return res;
    }


        /*673. 最长递增子序列的个数
给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。

注意 这个数列必须是 严格 递增的。
     */
    /**下面是”最长递增子序列长度“的解法*/
    public int findNumberOfLIS(int[] nums) {
        int size = 0;
        int[] dp = new int[nums.length];
        for (int num : nums) {
            int left = 0, right = size - 1;
            while (left <= right) {
                /**本质上是”找到第一个大于等于num“的位置*/
                int mid = left + (right - left) / 2;
                if (dp[mid] >= num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            dp[left] = num;
            if (left == size) size++;
        }
        return size;
    }


//    public int findNumberOfLIS__(int[] nums) {
//
//    }


        /*349.两个数组的交集
        给定两个数组 nums1 和 nums2，返回它们的交集。

        结果中的每个元素 唯一

        顺序不限
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> tmp = new HashSet<>();
        HashSet<Integer> resSet = new HashSet<>();
        for (int num:nums1){
            tmp.add(num);
        }

        for (int num:nums2){
            if (tmp.contains(num)){
                resSet.add(num);
            }
        }

        int[] result = new int[resSet.size()];
        int index = 0;
        for (int num:resSet){
            result[index++] = num;
        }
        return result;
    }



    /*51.N皇后
    按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    * */
    List<List<String>> resSolveQueens;
    public List<List<String>> solveNQueens(int n) {
        resSolveQueens = new LinkedList<>();
        char[][] dp = new char[n][n];
        Arrays.fill(dp,'.');
        dfsSolveQueens(dp,n,0);
        return resSolveQueens;
    }

    private void dfsSolveQueens(char[][] dp, int n, int index) {
        if (index==n){
            resSolveQueens.add(getString1(dp));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid11(dp,index,i)){
                dp[index][i] = 'Q';
                dfsSolveQueens(dp,n,index+1);
                dp[index][i] = '.';
            }
        }
    }

    private boolean isValid11(char[][] dp, int row, int colmn) {
        for (int i = 0; i < row; i++) {
            if (dp[i][colmn]=='Q') return false;
        }

        for (int i = row-1,j = colmn-1; i >=0&&j>=0 ; i--,j--) {
            if (dp[i][j]=='Q') return false;
        }

        for (int i = row-1,j = colmn+1; i >=0&&j<dp[0].length ; i--,j++) {
            if (dp[i][j]=='Q') return false;
        }
        return true;
    }

    private List<String> getString1(char[][] dp) {
        LinkedList<String> res = new LinkedList<>();
        for (char[] cur:dp){
            res.add(new String(cur));
        }
        return res;
    }



        /*
    264. 丑数 II
    给你一个整数 n ，请你找出并返回第 n 个 丑数 。
    丑数 就是质因子只包含 2、3 和 5 的正整数。
     */
    /**
     1. 拿1作为启动的数（因此dp数组声明为”n+1“长度比较顺手，0索引的位置放1）。但是注意1不是丑数
     2. p2,p3,p5分别是指针（或者称作索引），指向当前”乘2乘3乘5“分别乘到哪个位置了。
     */
    public int nthUglyNumber(int n) {
        int p2=0,p3=0,p5=0;
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int val2 = dp[p2]*2;
            int val3 = dp[p3]*3;
            int val5 = dp[p5]*5;
            dp[i] = Math.min(Math.min(val3,val5),val2);
            if (dp[i]==val2) p2++;
            if (dp[i]==val3) p3++;
            if (dp[i]==val5) p5++;
        }
        return dp[n];
    }



     /*84.柱状图中最大的矩形
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
    * */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res1 = 0;
        for (int i = 0; i < heights.length + 1; i++) {
            int curHeight = i==heights.length?0:heights[i];
            while (!stack.isEmpty()&&curHeight<heights[stack.peek()]){
                Integer index = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                res1 = Math.max(res1,(i-left-1)*heights[index]);
            }
            stack.push(i);
        }
        return res1;
    }


    /*253. 会议室 II   vip题目*/


    /*
    279. 完全平方数
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp,n);
        dp[0] = 0; /**dp[0]应该是要初始化为0，否则的话下面的决策过程”Math.min(dp[j],dp[j-i*i]+1);“就永远都是最大值了*/
        for (int i = 1; i*i < n; i++) { /**”一个i就相当于之前的一个物品的质量/空间 nums[i]“*/
            for (int j = 1; j <= n; j++) {
                dp[j] = Math.min(dp[j],dp[j-i*i]+1);
            }
        }
        return dp[n];
    }



        /*316.去除重复字母
    给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     */
    public String removeDuplicateLetters(String s) {
        int[] flags = new int[26];
        boolean[] used = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            flags[c-'a'] = i;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while (!stack.isEmpty()&&c<stack.peek()&&flags[stack.peek()-'a']>i&&!used[c-'a']){
                Character cur = stack.pop();
                used[cur-'a'] = false;
            }
            stack.push(c);
            used[c-'a'] = true;
        }

        StringBuilder res = new StringBuilder();
        for (char c:stack){
            res.append(c);
        }
        return res.toString();
    }


    /*
    面试题 02.05. 链表求和
给定两个用链表表示的整数，每个节点包含一个数位。

这些数位是反向存放的，也就是个位排在链表首部。

编写函数对这两个整数求和，并用链表形式返回结果。
     */
    public ListNode addTwoNumbers_(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry =0;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curSum = val1+val2+carry;
            cur.next = new ListNode(curSum%10);
            cur = cur.next;
            carry = curSum/10;
            l1 = l1==null?l1:l1.next;
            l2 = l2==null?l2:l2.next;
        }
        return dummy.next;
    }



    /*
    71. 简化路径
    给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为 更加简洁的规范路径。

    在 Unix 风格的文件系统中规则如下：

    一个点 '.' 表示当前目录本身。
    此外，两个点 '..' 表示将目录切换到上一级（指向父目录）。
    任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
    任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。
    返回的 简化路径 必须遵循下述格式：

    始终以斜杠 '/' 开头。
    两个目录名之间必须只有一个斜杠 '/' 。
    最后一个目录名（如果存在）不能 以 '/' 结尾。
    此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
    返回简化后得到的 规范路径 。
 */
    public String simplifyPath(String path) {
        String[] split = path.split("///", 1);
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            String cur = split[i];
            if (".".equals(cur)||"".equals(cur)){
                continue;
            } else if ("..".equals(cur)&&!stack.isEmpty()) { /**这里不判空可能会有问题~~~~~~*/
                stack.pop();
            } else {
                stack.push(cur);
            }
        }
        StringBuilder res = new StringBuilder();
        for (String c:stack){
            res.append("/").append(c);
        }
        return res.length()==0?"/":res.toString();
    }
}
