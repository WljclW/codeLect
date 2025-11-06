package topcodeReview;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/11/6 15:02
 */
public class All6_10_template {
    /*739.每日温度
    给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第
     i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    * */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int cur = temperatures[i];
            while (!stack.isEmpty()&&cur>temperatures[stack.peek()]){
                Integer index = stack.pop();
                res[index] = cur-index;
            }
            stack.push(i);
        }
        return res;
    }


    /*138. 随机链表的复制
    给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
     */
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
        if (head==null) return null;
        Node cur = head;
        while (cur!=null){
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = cur.next.next;
        }

        cur = head;
        while (cur!=null){
            if (cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        cur = head;
        Node res = head.next,curRes = res;
        while (curRes!=null){
            cur.next = cur.next.next;
            cur = cur.next;

            curRes = curRes.next.next;
            curRes = curRes.next;
        }
        return res;
    }


     /*207.课程表
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
    * */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        LinkedList<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<>());
        }

        int[] indgree = new int[numCourses];
        for (int[] cur:prerequisites){
            int pre = cur[1],index = cur[0];
            indgree[index]++;
            graph.get(pre).add(index);
        }

        LinkedList<Integer> zeroQueue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indgree[i]==0) zeroQueue.offer(i);
        }

        int num = 0;
        while (!zeroQueue.isEmpty()){
            Integer curIndex = zeroQueue.poll();
            num++;
            for (int i:graph.get(curIndex)){
                indgree[i]--;
                if (indgree[i]==0){
                    zeroQueue.offer(i);
                }
            }
        }
        return num==numCourses;
    }


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


    /*79.单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
* */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,i,j,0,word)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int index, String word) {
        if (index==word.length()) return true;
        if (i<0||j<0||i>=board.length||j>=board[0].length||board[i][j]!=word.charAt(index)) return false;
        board[i][j] = '\n';
        boolean res = dfs(board, i + 1, j, index + 1, word) ||
                dfs(board, i - 1, j, index + 1, word) ||
                dfs(board, i, j + 1, index + 1, word) ||
                dfs(board, i, j - 1, index + 1, word);
        board[i][j] = word.charAt(index);
        return res;
    }


    /*402.移掉K位数字
给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字
符串形式返回这个最小的数字。
* */
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty()&&c<stack.peek()&&k>0){
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        while (!stack.isEmpty()&&k>0){
            k--;
            stack.pop();
        }

        StringBuilder res = new StringBuilder();
        for (char c:stack){
            if (res.length()==0&&c=='0') continue;
            res.append(c);
        }
        return res.length()==0?"0":res.toString();
    }


        /*47.全排列 II
    给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     */
    List<List<Integer>> resPermuteUnique;
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        resPermuteUnique = new LinkedList<>();
        used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        permuteUnique(nums,path);
        return resPermuteUnique;
    }

    private void permuteUnique(int[] nums, LinkedList<Integer> path) {
        if (path.size()==nums.length){
            resPermuteUnique.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                if (i>0&&nums[i-1]==nums[i]&&!used[i]) continue;
                path.add(nums[i]);
                used[i] = true;
                permuteUnique(nums,path);
                used[i] = false;
                path.removeLast();
            }
        }
    }

    /*
     * 11.盛最多水的容器
     * 给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
     *    成的，柱子之间是没有间隙的。
     * */
    public int maxArea(int[] height) {
        int left = 0,right  =height.length-1;
        int res = 0;
        while (left<right){
            if (height[left]<height[right]){
                res = Math.max(res,height[left]*(right-left));
                left++;
            }else {
                res = Math.max(res,height[right]*(right-left));
                right--;
            }
        }
        return res;
    }


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


    /*手撕归并排序*/
    public void mergeSort(int[] nums,int left,int right) {
        if (left==right) return;
        int mid = left+(right-left)/2;
        mergeSort(nums,left,mid);
        mergeSort(nums,mid+1,right);
        mergeTwo1(nums,left,mid,right);
    }

    private void mergeTwo1(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left,j = mid+1;
        int cur = 0;
        while (i<=mid&&j<=right){
            if (nums[i]<nums[j]){
                tmp[cur++] = nums[i++];
            }else {
                tmp[cur++] = nums[j++];
            }
        }
        while (i<=mid) tmp[cur++] = nums[i++];
        while (j<=right) tmp[cur++] = nums[j++];

        for (int k = 0; k < tmp.length; k++) {
            nums[left+k] =  tmp[k];
        }
    }


    /*136.只出现一次的数字
* 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均
* 出现两次。找出那个只出现了一次的元素。
你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。*/
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num:nums){
            res ^= num;
        }
        return res;
    }


    /*
* 55. 跳跃游戏
* 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在
* 该位置可以跳跃的最大长度。
    判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
* */
    public boolean canJump(int[] nums) {
        int bound = 0;
        for (int i = 0; i < nums.length && i <= bound; i++) {
            bound = Math.max(bound,i+nums[i]);
            if (bound>=nums.length-1) return true;
        }
        return false;
    }


    /*
    16. 最接近的三数之和
    给定一个长度为 n 的整数数组 nums 和一个目标值 target，
    找出数组中三个整数，使得它们的和与 target 最接近。
    返回这三个数的和。
    */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1,right = nums.length-1;
            while (left<right){
                int curVal = nums[i]+nums[left]+nums[right];
                if (curVal==target) return 0;
                else if (curVal < target) {
                    left++;
                }else
                    right--;
                res = Math.min(res,Math.abs(curVal-target));
            }
        }
        return res;
    }


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


        /*61
    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;
        int size = 1;
        ListNode cur = head;
        while (cur.next!=null){
            cur = cur.next;
            size++;
        }
        cur.next = head;

        k %= size;
        if (k==0) return head;
        cur = head;
        /**这里注意：i的初始值 和 i的终止值是需要配合的！！*/
        for (int i = 1; i < size - k; i++) {
            cur = cur.next;
        }
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }


        /*958
    给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。

在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root==null) return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasNull = false;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur.left!=null){
                if (hasNull) return false;
                queue.offer(cur.left);
            }else {
                hasNull  =true;
            }

            if (cur.right!=null){
                if (hasNull) return false;
                queue.offer(cur.right);
            }else {
                hasNull = true;
            }
        }
        return true;
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



    /*26. 删除有序数组中的重复项
给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。

考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：

更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
返回 k 。*/
    public int removeDuplicates(int[] nums) {
        int cur = 0,right = 1;
        while (right<nums.length){
            if (nums[right]!=nums[cur]){
                nums[++cur] = nums[right];
            }
            right++;
        }
        return cur+1;
    }


    /*
    518. 零钱兑换 II
    给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。

    请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。

    假设每一种面额的硬币有无限个。

    题目数据保证结果符合 32 位带符号整数。
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        for (int i = coins[0]; i < amount+1; i++) {
            if (i%coins[0]==0) dp[i] = 1;
        }
        dp[0] = 1;

        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j-coins[i]] + dp[j];
            }
        }
        return dp[amount];
    }


    /*74.搜索二维矩阵
    给你一个满足下述两条属性的 m x n 整数矩阵：
    每行中的整数从左到右按非严格递增顺序排列。
    每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
    * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int left  =0,right = m*n-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur==target) return true;
            else if (cur > target) {
                right = mid-1;
            }else
                left = mid+1;
        }
        return false;
    }



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



    /*114.二叉树展开为链表
    * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。*/
    public void flatten(TreeNode root) {
        if (root==null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = null;
        while (!stack.isEmpty()){
            TreeNode curNode = stack.pop();
            if (cur==null)
                cur = curNode;
            else{
                cur.left = null;
                cur.right = curNode;
                cur = cur.right;
            }

            if (curNode.right!=null) stack.push(curNode.right);
            if (curNode.left!=null) stack.push(curNode.left);
        }
    }


    /*
    91. 解码方法
    一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

    "1" -> 'A'

    "2" -> 'B'

    ...

    "25" -> 'Y'

    "26" -> 'Z'

    然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。

    例如，"11106" 可以映射为：

    "AAJF" ，将消息分组为 (1, 1, 10, 6)
    "KJF" ，将消息分组为 (11, 10, 6)
    消息不能分组为  (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
    注意，可能存在无法解码的字符串。

    给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。

    题目数据保证答案肯定是一个 32 位 的整数。
     */
    public int numDecodings(String s) {
        if (s==null||s.length()==0) return 0;
        if (s.charAt(0)=='0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            char cur = s.charAt(i - 1);
            char prev = s.charAt(i - 2);
            if (cur!='0') dp[i] += dp[i-1];
            int val = (prev-'0')*10+cur-'0';
            if (val>=10&&val<=26) dp[i] += dp[i-2];
        }
        return dp[s.length()];
    }

    /*75.颜色分类
    * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使
    * 得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    必须在不使用库内置的 sort 函数的情况下解决这个问题。*/
    /*
    * 【注意】cur位置小于1的时候，left和cur指针都需要++。。。否则会报错，如下：
    *       输入
            nums =
            [2,0,2,1,1,0]
            输出
            [1,1,2,2,0,0]
            预期结果
            [0,0,1,1,2,2]
    * */
    public void sortColors(int[] nums) {
        int left =0,cur =0,right = nums.length-1;
        while (cur<nums.length){
            if (nums[cur]<1){
                swap1(nums,left++,cur++);
            }else if (nums[cur]>1){
                swap1(nums,cur,right--);
            }else {
                cur++;
            }
        }
    }

    private void swap1(int[] nums, int l, int r) {
        int tmp  =nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


        /*145.二叉树的后序遍历
    给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
    * */
    /*迭代的形式*/
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.left!=null) stack.push(cur.left);
            if (cur.right!=null) stack.push(cur.right);
        }
        Collections.reverse(res);
        return res;
    }

    List<Integer> resP;
    public List<Integer> postorderTraversal_(TreeNode root) {
        resP = new LinkedList<>();
        dfs(root);
        return resP;
    }

    private void dfs(TreeNode root) {
        if (root==null) return;
        dfs(root.left);
        dfs(root.right);
        resP.add(root.val);
    }

            /*LCR 143. 子结构判断
    给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
注意，空树 不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
     */
//    public boolean isSubStructure(TreeNode A, TreeNode B) {
//
//    }



        /*59 螺旋矩阵Ⅱ
        给定参数n，产生一个矩阵，顺时针填写1，2，.....
     */
    public int[][] generateMatrix(int n) {
        int cur = 1;
        int left=0,top=0,right=n-1,bottom=n-1;
        int[][] res = new int[n][n];
        while (cur<=n*n){ /**这里写成”while(true)“应该也没问题*/
            for (int i = left; i <= right; i++) {
                res[top][i] = cur++;
            }
            if (++top>bottom) break;

            for (int i = top; i <= bottom; i++) {
                res[i][right] = cur++;
            }
            if (--right<left) break;

            for (int i = right; i >= left; i--) {
                res[bottom][i] = cur++;
            }
            if (--bottom<top) break;

            for (int i = bottom; i >= top; i--) {
                res[left][i] = cur++;
            }
            if (++left>right) break;
        }
        return res;
    }


        /*135。分发糖果
    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

    你需要按照以下要求，给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */
    public int candy(int[] ratings) {
        int[] res = new int[ratings.length];
        Arrays.fill(res,1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i]>ratings[i-1]){
                res[i] = res[i-1]+1;
            }
        }

        for (int i = ratings.length-2; i >=0 ; i--) {
            if (ratings[i]>ratings[i+1]){
                res[i] = Math.max(res[i],res[i+1]+1);
            }
        }
        return Arrays.stream(res).sum();
    }


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

    /*
    125. 验证回文串
    如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
    字母和数字都属于字母数字字符。

    给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(String s) {
        int left =0,right = s.length()-1;
        while (left<right){
            while (left<right&&!Character.isLetterOrDigit(s.charAt(left))) left++;
            /**TODO 转换为小写字母的方法，”toLowerCase“如果参数时数字字符会不会报错??
             答：不会。”Character.toLowerCase('9')“的结果是'9'，并不会报异常 或 报错。
             */
            char cLeft = Character.toLowerCase(s.charAt(left));
            while (left<right&&!Character.isLetterOrDigit(s.charAt(right))) right--;
            char cRight = Character.toLowerCase(s.charAt(right));
            if (cRight!=cLeft) return false;
        }
        return true;
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
    LCR 174. 寻找二叉搜索树中的目标节点
某公司组织架构以二叉搜索树形式记录，节点值为处于该职位的员工编号。请返回第 cnt 大的员工编号。
     */
    public int findTargetNode(TreeNode root, int cnt) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.right;
            }else {
                TreeNode cur = stack.pop();
                if (--cnt==0) return cur.val;
                root = cur.left;
            }
        }
        return -1;
    }

    /*
    LCR 159. 库存管理 III
仓库管理员以数组 stock 形式记录商品库存表，其中 stock[i] 表示对应商品库存余量。请返回库存余量最少的 cnt 个商品余量，返回 顺序不限。
     */
//    public int[] inventoryManagement(int[] stock, int cnt) {
//
//    }


        /*442.数组中重复的数据
    给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 最多两次 。请你找出所有出现 两次 的整数，并以数组形式返回。

你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间（不包括存储输出所需的空间）的算法解决此问题。
    * */
    public List<Integer> findDuplicates(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.abs(nums[i]);
            if (nums[cur-1]>0){
                nums[cur-1] *= -1;
            }else {
                res.add(cur);
            }
        }
        return res;
    }


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

    /*
    LCR 161. 连续天数的最高销售额
某公司每日销售额记于整数数组 sales，请返回所有 连续 一或多天销售额总和的最大值。

要求实现时间复杂度为 O(n) 的算法。
     */
    public int maxSales(int[] sales) {
        int res = sales[0];
        int preSum = sales[0];
        for (int i = 1; i < sales.length; i++) {
            preSum = Math.max(preSum+sales[i],sales[i]);
            res = Math.max(preSum,res);
        }
        return res;
    }



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



    /*
    LCR 127. 跳跃训练
今天的有氧运动训练内容是在一个长条形的平台上跳跃。平台有 num 个小格子，每次可以选择跳 一个格子 或者 两个格子。请返回在训练过程中，学员们共有多少种不同的跳跃方式。

结果可能过大，因此结果需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    public int trainWays(int num) {
        if (num<=2) return num;
        int fir = 1,sec =2;
        for (int i = 3; i <= num; i++) {
            int cur = fir+sec;
            cur %= 1000000007;
            fir = sec;
            sec = cur;
        }
        return sec;
    }


    /*347.前 K 个高频元素
     *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率
     * 前 k 高的元素。你可以按 任意顺序 返回答案。
     * */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (Integer.compare(a[1], b[1])));
        for (Map.Entry<Integer,Integer> cur:map.entrySet()){
            int[] now = {cur.getKey(), cur.getValue()};
            queue.offer(now);
            if (queue.size()>k) queue.poll();
        }

        int[] res = new int[k];
        int index = 0;
        for (int[] cur:queue){
            res[index++] = cur[0];
        }
        return res;
    }


        /*445.两数相加 II
        两个链表代表的数相加，两个数正序存放——————即高位在前
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1New = reverse11(l1);
        ListNode l2New = reverse11(l2);
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (l1New!=null||l2New!=null||carry!=0){
            int val1 = l1New==null?0:l1New.val;
            int val2 = l2New==null?0:l2New.val;
            int curSum = val1+val2+carry;
            cur.next = new ListNode(curSum%10);
            cur = cur.next;
            carry = curSum/10;
            l1New = l1New==null?l1New:l1New.next;
            l2New = l2New==null?l2New:l2New.next;
        }
        return reverse11(dummy.next);
    }

    private ListNode reverse11(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur =next;
        }
        return pre;
    }



    /*45.跳跃游戏 II
* 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你
* 可以跳转到任意 nums[i + j] 处:
0 <= j <= nums[i]
i + j < n
返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
* */
    public int jump(int[] nums) {
        int right = 0,bound =0;
        int cur = 0,step = 0;
        while (cur<nums.length-1){
            right = Math.max(right,cur+nums[cur]);
            if (cur==bound){
                step++;
                bound = right;
            }
            cur++;
        }
        return step;
    }

    /**for循环的形式======*/
    public int jump_(int[] nums) {
        int right = 0,bound = 0;
        int step  =0;
        for (int i = 0; i < nums.length - 1; i++) {
            right = Math.max(i+nums[i],right);
            if (i==bound){
                step++;
                bound = right;
            }
        }
        return step;
    }


    /*LCR 126. 斐波那契数
斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

F(0) = 0，F(1) = 1
F(n) = F(n - 1) + F(n - 2)，其中 n > 1
给定 n ，请计算 F(n) 。

答案需要取模 1e9+7(1000000007) ，如计算初始结果为：1000000008，请返回 1。*/
    public int fib(int n) {
        if (n<=1) return n;
        int fir = 0,sec =1;
        for (int i = 2; i <= n; i++) {
            int cur = fir+sec;
            cur %= 1000000007;
            fir = sec;
            sec = cur;
        }
        return sec;
    }


    /*
    LCR 121. 寻找目标值 - 二维数组
m*n 的二维数组 plants 记录了园林景观的植物排布情况，具有以下特性：

每行中，每棵植物的右侧相邻植物不矮于该植物；
每列中，每棵植物的下侧相邻植物不矮于该植物。


请判断 plants 中是否存在目标高度值 target。
     */
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        int m =plants.length,n = plants[0].length;
        int i = 0,j =  n-1;
        while (i<m&&j>=0){
            int cur = plants[i][j];
            if (cur==target) return true;
            else if (cur>target) {
                j--;
            }else {
                i++;
            }
        }
        return false;
    }



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


      /*208.
    Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：
    Trie() 初始化前缀树对象。
    void insert(String word) 向前缀树中插入字符串 word 。
    boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
    boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
    **/
    class Trie {

        class TrieNode{
            TrieNode[] children = new TrieNode[26];
            boolean isEnd;
        }

          TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for(char c:word.toCharArray()){
                int index = c-'a';
                if (cur.children[index]==null){
                    cur.children[index] = new TrieNode();
                }
                cur = cur.children[index];
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
    225. 用队列实现栈
请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。

实现 MyStack 类：

void push(int x) 将元素 x 压入栈顶。
int pop() 移除并返回栈顶元素。
int top() 返回栈顶元素。
boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
     */
    class MyStack {
        Queue<Integer> queue;
        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }



    /*
    213. 打家劫舍 II
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     */
    public int rob(int[] nums) {
        if (nums.length==1) return nums[0];
        if (nums.length==2) return Math.max(nums[0],nums[1]);

        return Math.max(rob(nums,0,nums.length-2),rob(nums,1,nums.length-1));
    }

    private int rob(int[] nums, int left, int right) {
        int fir = nums[left],sec = nums[left+1];
        for (int i = left+2; i <= right; i++) {
            int cur = Math.max(fir+nums[i],sec);
            fir = sec;
            sec = cur;
        }
        return sec;
    }


    /**
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     * ==============================9==============================
     */
}
