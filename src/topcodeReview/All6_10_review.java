package topcodeReview;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;
import leecode_Debug.topcode.All6_10;

import java.util.*;

public class All6_10_review {
    /*739.每日温度
    给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第
     i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    * */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                Integer cur = stack.pop();
                res[cur] = i-cur;
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
        if (head==null) return head;
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
        Node res = head.next,resCur = res;
        while (resCur!=null&&resCur.next!=null){
            cur.next = cur.next.next;
            cur = cur.next;

            resCur.next = resCur.next.next;
            resCur = resCur.next;
        }
        cur.next = null; /**【注】没有这一步应该就是错的*/
        return res;
    }


     /*207.
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
    * */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<>());
        }

        int[] indgres = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int preCourse = prerequisites[i][1],index = prerequisites[i][0];
            indgres[index]++;
            graph.get(preCourse).add(index);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indgres.length; i++) {
            if (indgres[i]==0)
                queue.offer(i);
        }

        int count = 0;
        while (!queue.isEmpty()){
            Integer curCourse = queue.poll();
            count++;
            for (int index:graph.get(curCourse)){
                indgres[index]++;
                if (indgres[index]==0)
                    queue.offer(index);
            }
        }
        return count==numCourses;
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
                /**
                 这里有两种写法：
                    一种就是下面的if里面添加continue，因此命中if后后面的就不会执行了。
                    第二种就是将后面的代码整体写入到else分支
                 * */
                if (cur==null){
                    res.append("null,");
                    continue;
                }
                res.append(cur.val).append(",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if ("null".equals(data)){
                return null;
            }
            String[] split = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int index = 1;
            while (!queue.isEmpty()){
                TreeNode curnode = queue.poll();
                if (!"null".equals(split[index])){ /**这里的条件中是不是还必须限制index的范围？？？*/
                    TreeNode node = new TreeNode(Integer.parseInt(split[index]));
                    curnode.left = node;
                    queue.offer(node);
                }
                index++;

                if (!"null".equals(split[index])){
                    TreeNode node = new TreeNode(Integer.parseInt(split[index]));
                    curnode.right = node;
                    queue.offer(node);
                }
                index++;
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
            if (nums[mid]<=nums[right]){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return nums[left];
    }

    /*记录可能的位置*/
    public int findMin_(int[] nums) {
        int left = 0, right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= nums[right]) {
                /**如果直接将right更新为mid-1，可能错过最小值。比如”3，4，1，2“，right如果指向1，再减1就会导致错过最小值*/
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[index];
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


    /*79.单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
* */

    /**
        借助这个题认真的体会一下下面的问题————
            1. 为什么这个题需要标记走过的路，但是”矩阵中最长的递增路径“中并不需要标记走过的路；
            2. dfs的一般使用方法，尤其是主函数的调用参数是什么？？怎么确定？？dfs的流程又是什么，返回值的确定怎么做？？
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,i,j,word,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        if (index==word.length()) return true;
        if (i<0||i==board.length||j<0||j==board[0].length||word.charAt(index)!=board[i][j]) return false;
        board[i][j] = '\n';
        boolean tmp = dfs(board, i + 1, j, word, index + 1) ||
                dfs(board, i - 1, j, word, index + 1) ||
                dfs(board, i, j - 1, word, index + 1) ||
                dfs(board, i, j + 1, word, index + 1);
        board[i][j] = word.charAt(index);
        return tmp;
    }


    /*402.移掉K位数字
给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字
符串形式返回这个最小的数字。
* */
    public String removeKdigits(String num, int k) {
        LinkedList<Character> deque = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!deque.isEmpty()&&c<=deque.peekLast()&&k>0){
                k--;
                deque.pollLast();
            }
            deque.offerLast(c);
        }

        while (!deque.isEmpty()&&k>0){
            k--;
            deque.pollLast();
        }

        StringBuilder res = new StringBuilder();
        for (char c:deque){
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
        resPermuteUnique = new LinkedList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUnique(nums,new LinkedList<Integer>());
        return resPermuteUnique;
    }

    private void permuteUnique(int[] nums, LinkedList<Integer> path) {
        if (path.size()==nums.length){
            resPermuteUnique.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                if (i>0&&nums[i]==nums[i-1]&&!used[i-1]) continue;
                used[i] = true;
                path.add(nums[i]);
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
        int left = 0,right = height.length-1;
        int res = 0;
        while (left<right){
            int curVal  =0;
            if (height[left]<height[right]){
                curVal = height[left]*(right-left);
                left++;
            }else {
                curVal = height[right]*(right-left);
                right--;
            }
            res = Math.max(res,curVal);
        }
        return res;
    }


    /*40.组合总和 II
    给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的每个数字在每个组合中只能使用 一次 。
    注意：解集不能包含重复的组合。
     */
    List<List<Integer>> resCombinationSum2;
    boolean[] used1;
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        used1 = new boolean[candidates.length];
//        combinationSum2(candidates,target,0,new LinkedList<Integer>());
//        return resCombinationSum2;
//    }

//    private void combinationSum2(int[] candidates, int target, int index, LinkedList<Integer> path) {
//        if (target==0){
//            resCombinationSum2.add(new LinkedList<>(path));
//        }
//        if (target<0||index==candidates.length){
//            return;
//        }
//        for (int i = index; i < candidates.length; i++) {
//            if (i>0&&)
//        }
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
    public void mergeSort(int[] nums,int left,int right){

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
        for (int i = 0; i < nums.length; i++) {
            if (i<=bound){
                bound = Math.max(i+nums[i],bound);
                if (bound>=nums.length-1) return true;
            }
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
            if (i>0&&nums[i]==nums[i-1]) continue; /**这个题跳过不跳过都可以，不重要*/
            int left = i+1,right= nums.length-1;
            while (left<right){
                int curVal = nums[i]+nums[left]+nums[right];
                if (curVal==target){
                    return target;
                }
                int curAbs = Math.abs(target - curVal);
                if (curAbs<res){
                    res = curAbs;
                }
                if (curVal<target){
                    left++;
                }else {
                    right--;
                }
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
    class CQueue {
        Stack<Integer> inStack;
        Stack<Integer> outStack;
        public CQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if (!outStack.isEmpty()){
                return outStack.pop();
            }else if (!inStack.isEmpty()){
                while (!inStack.isEmpty()){
                    outStack.push(inStack.pop());
                }
                return outStack.pop();
            }else {
                return -1;
            }
        }
    }


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
//    public int reversePairs(int[] record) {
//
//    }


        /*61
    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||head.next==null||k==0) return head;
        int size = 1;
        ListNode cur = head;
        while (cur.next!=null){
            size++;
            cur = cur.next;
        }
        ListNode tail = cur;
        tail.next = head;
        k %= size;

        cur = head;
        for (int i = 0; i < size - k - 1; i++) {
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
    /**层序遍历方法解题的时候，每一层的节点必须分明吗？？——————即访问每一层之前必须结合”queue.size()以及for循环来进行吗“？？，应该是不需要的！！*/
    public boolean isCompleteTree(TreeNode root) {
        if (root==null) return true;
        boolean hasNull = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (hasNull&&cur!=null){
                return false;
            }
            if (cur==null){
                hasNull = true;
            }else {
                queue.offer(cur.left);
                queue.offer(cur.right);
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
        if (nums.length==1) return nums[0];
        int left = 0,right = 1;
        while (right<nums.length){
            if (nums[left]!=nums[right]){
                nums[++left] = nums[right];
            }
        }
        return left+1;
    }


    /*
    518. 零钱兑换 II
    给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。

    请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。

    假设每一种面额的硬币有无限个。

    题目数据保证结果符合 32 位带符号整数。
     */
//    public int change(int amount, int[] coins) {
//        int[] dp = new int[amount + 1];
//        dp[0] = 1;
//        for (int i = coins[0]; i < amount + 1; i++) {
//            dp[i] = dp[i-coins[0]];
//        }
//
//    }


    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            if (i%coins[0]==0) dp[i] = 1;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j-coins[i]];
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
        int i = 0,j = m*n-1;
        while (i<=j){
            int mid = i+(j-i)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur<target){
                i = mid+1;
            } else if (cur > target) {
                j = mid-1;
            }else {
                return true;
            }
        }
        return false;
    }



    /*7.整数反转
        给定一个 32 位有符号整数 x，返回将其数字部分反转后的结果。
    如果反转后 超过 32 位有符号整数范围 [-2^31, 2^31 - 1]，返回 0
     */
    public int reverse(int x) {
        int res = 0;
        while (x!=0){ /**【注】结束的标志是x==0，而不是x<0（每一轮就将x除10留下整数部分）*/
            int digit = x%10;
            x /= 10;
            if (res>Integer.MAX_VALUE/10 ||
                    (res==Integer.MAX_VALUE/10&&digit>7)) return 0;
            if (res<Integer.MIN_VALUE/10 ||
                    (res==Integer.MIN_VALUE/10&&digit<-8)) return 0;
            res = res*10 + digit;
        }
        return res;
    }



    /*114.二叉树展开为链表
    * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。*/
    public void flatten(TreeNode root) {
        if (root==null) return;
        TreeNode curNode = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (curNode==null){
                curNode = cur;
            }else {
                curNode.right = cur;
                curNode.left = null;
                curNode = cur;
            }
            /*上面的逻辑可以简化为下面的形式*/
//            if (curNode!=null){
//                curNode.right = cur;
//                curNode.left = null;
//            }
//            curNode = cur;
            if (cur.right!=null) stack.push(cur.right);
            if (cur.left!=null) stack.push(cur.left);
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
        if (s==null||s.length()==0||s.charAt(0)=='0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            char sec = s.charAt(i - 1);
            char fir = s.charAt(i - 2);
            if (sec>'0'&&sec<='9'){
                dp[i] += dp[i-1];
            }
            int val = (fir-'0')*10+sec-'0';
            if (val>=10&&val<=26){
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }


    /*由于一个位置最多依赖于之前的两个位置，因此简化为仅仅使用2个变量来滚动计算....
        写出一维的dp以后，这里的优化其实就很像”斐波那契数列“的题目*/
    public int numDecodings_2param(String s) {
        if (s==null||s.length()==0||s.charAt(0)=='0') return 0;
        int fir = 1;
        int sec = 1;
        for (int i = 2; i <= s.length(); i++) {
            char curChar = s.charAt(i - 1);
            char preChar = s.charAt(i - 2);
            int curVal = 0;
            if (curChar>'0'&&curChar<='9'){
                curVal += sec;
            }
            int val = (preChar-'0')*10+curChar-'0';
            if (val>=10&&val<=26){
                curVal += fir;
            }

            fir = sec;
            sec = curVal;
        }
        return sec;
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
        int left = 0,right = nums.length-1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i]>1){
                swap(nums,right--,i);
            }
            if (nums[i]<1){
                swap (nums,left++,i);
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    /**while循环的形式*/
    public void sortColors_while(int[] nums) {
        int left = 0,right = nums.length-1;
        int cur = 0;
        while (cur<nums.length){
            if (nums[cur]==1){
                cur++;
            } else if (nums[cur] < 1) {
                swap(nums,left++,cur++);
            }else {
                swap(nums,right--,cur);
            }
        }
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

    /*递归的形式*/
    public List<Integer> postorderTraversal_(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        dfs1(root,res);
        return res;
    }

    private void dfs1(TreeNode root, LinkedList<Integer> res) {
        if (root==null) return;
        dfs1(root.left,res);
        dfs1(root.right,res);
        res.add(root.val);
    }


            /*LCR 143. 子结构判断
    给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
注意，空树 不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A==null||B==null) return false;
        return isMatch1(A,B)||
                isSubStructure(A.left,B)||
                isSubStructure(A.right,B);
    }

    private boolean isMatch1(TreeNode a, TreeNode b) {
        if (b==null) return true;
        if (a==null) return false;
        if (a.val!=b.val) return false;
        return isMatch1(a.left,b.left)&&
                isMatch1(a.right,b.right);
    }


        /*59 螺旋矩阵Ⅱ
        给定参数n，产生一个矩阵，顺时针填写1，2，.....
     */
//    public int[][] generateMatrix(int n) {
//
//    }


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
                res[i] = Math.max(res[i+1]+1,res[i]);
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
实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。*/
    public double myPow(double x, int n) {
        if (x<2) return x;
        if (n<0){
            x = 1/x;
            n = -n;
        }
        double base = x;
        double res = 1;
        while (n!=0){
            if ((n&1)==1){
                res *= base;
                n -= 1;
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
        if (s==null||s.length()==0) return true;
        int left = 0,right = s.length()-1;
        while (left<right){
            while (left<right&&!Character.isLetterOrDigit(left)) left++;
            while (left<right&&!Character.isLetterOrDigit(right)) right--;
            if (Character.toLowerCase(left)!=Character.toLowerCase(right)){
                return false;
            }
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
//    public int findTargetNode(TreeNode root, int cnt) {
//
//    }

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
//    public List<Integer> findDuplicates(int[] nums) {
//
//    }


        /*329.矩阵中的最长递增路径
    给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
     */
//    public int longestIncreasingPath(int[][] matrix) {
//
//    }


    /*
    LCR 161. 连续天数的最高销售额
某公司每日销售额记于整数数组 sales，请返回所有 连续 一或多天销售额总和的最大值。

要求实现时间复杂度为 O(n) 的算法。
     */
//    public int maxSales(int[] sales) {
//
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



    /*
    LCR 127. 跳跃训练
今天的有氧运动训练内容是在一个长条形的平台上跳跃。平台有 num 个小格子，每次可以选择跳 一个格子 或者 两个格子。请返回在训练过程中，学员们共有多少种不同的跳跃方式。

结果可能过大，因此结果需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    public int trainWays(int num) {
        if (num<=2) return num;
        int fir = 1;
        int sec = 2;
        for (int i = 3; i <= num; i++) {
            int curVal = (fir+sec)%1000000007;
            fir = sec;
            sec = curVal;
        }
        return sec;
    }


    /*347.前 K 个高频元素
     *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率
     * 前 k 高的元素。你可以按 任意顺序 返回答案。
     * */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->(a[1]-b[1]));
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            queue.offer(new int[]{key,value});
            if (queue.size()>k) queue.poll();
        }

        int[] res = new int[k];
        int index = 0;
        for (int[] cur:queue){
            res[index++]  =cur[0];
        }
        return res;
    }


        /*445.两数相加 II
        两个链表代表的数相加，两个数正序存放——————即高位在前
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newL1 = reverse1(l1);
        ListNode newL2 = reverse1(l2);
        return reverse1(add(newL1,newL2));
    }

    private ListNode add(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (head1!=null||head2!=null||carry!=0){
            int val1 = head1==null?0:head1.val;
            int val2 = head2==null?0:head2.val;
            int curVal = val1+val2+carry;
            cur.next = new ListNode(curVal%10);
            cur = cur.next;
            carry = curVal/10;
            if (head1!=null) head1=head1.next;
            if (head2!=null) head2=head2.next;
        }
        return dummy.next;
    }

    private ListNode reverse1(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
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
        int step = 0;
        int bound = 0,maxPosition = 0;
        for (int i = 0; i < nums.length-1; i++) {
            maxPosition = Math.max(maxPosition,i+nums[i]);
            if (i==bound){
                step++;
                bound = maxPosition;
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
        int fir = 0;
        int sec =1;
        for (int i = 2; i <= n; i++) {
            int curVal = (fir+sec)%1000000007;
            fir = sec;
            sec = curVal;
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
        int i = 0,j = plants[0].length-1;
        while (i<plants.length&&j>=0){
            int cur = plants[i][j];
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



        /*287. 寻找重复数
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
    /**【注】这个题数组是n+1个数，但是数值是[1,n]，因此数值作为下标是不会越界的*/
    public int findDuplicate(int[] nums) {
        int slow = 0,fast = 0;
        do{
            slow =nums[slow];
            fast = nums[nums[fast]];
        }while (slow!=fast);
        return slow;
    }



            /*328.奇偶链表
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return head;
        ListNode even = head;
        ListNode odd = head.next,oddCur = odd;
        while (oddCur!=null&&oddCur.next!=null){
            even.next = even.next.next;
            even = even.next;

            oddCur.next = oddCur.next.next;
            oddCur = oddCur.next;
        }
        even.next = odd;
        return head;
    }


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
            TrieNode cur = root; /**疑问？每一个方法之前都会这样记录一下这个变量，不记录的话行不行？？记录的意义又是什么？？*/
            for (char c:word.toCharArray()){
                int index = c-'0';
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
                int index = c-'0';
                if (cur.children[index]==null)
                    return false;
                cur = cur.children[index];
            }
            return cur.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (char c:prefix.toCharArray()){
                int index = c-'0';
                if (cur.children[index]==null)
                    return false;
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
            int size = queue.size();
            for (int i = 0; i < size - 1; i++) {
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
        int fir = nums[left];
        int sec = Math.max(nums[left],nums[left+1]);
        for (int i = left+2; i <= right; i++) {
            int curVal = Math.max(fir+nums[i],sec);
            fir = sec;
            sec = curVal;
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
        PriorityQueue<Integer> min;
        PriorityQueue<Integer> max;
        public MedianFinder() {
            min = new PriorityQueue<>((A,B)->B.compareTo(A));
            max = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (min.size()-max.size()<=1){
                max.offer(num);
                min.offer(max.poll());
            }else {
                min.offer(num);
                max.offer(min.poll());
            }
        }

        public double findMedian() {
            if (min.size()!=max.size()){
                return min.peek();
            }else {
                return (min.peek()+max.peek())/2.0;
            }
        }
    }



    /*230.二叉搜索树中第 K 小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。*/
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode cur = stack.pop();
                if (--k==0){
                    return cur.val;
                }
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
        int min = 0,max = 0;
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
            if (min<0) min = 0;
            if (max<0) return false;
        }
        return min==0;
    }


        /*516. 最长回文子序列
    给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     */
//    public int longestPalindromeSubseq(String s) {
//
//    }



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
        int rootVal = postorder[postorderIndex];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.right = buildTree(inorder,postorder,index+1,right);
        root.left = buildTree(inorder,postorder,left,index-1);
        return root;
    }



        /*96. 不同的二叉搜索树
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */
    public int numTrees(int n) {
        if (n<=1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) { /**【注】j的含义是”把第几个节点作为根节点“，因此j取0是没有意义的*/
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }



    /*
    LCR 187. 破冰游戏
社团共有 num 位成员参与破冰游戏，编号为 0 ~ num-1。成员们按照编号顺序围绕圆桌而坐。社长抽取一个数字 target，从 0 号成员起开始计数，排在第 target 位的成员离开圆桌，且成员离开后从下一个成员开始计数。请返回游戏结束时最后一位成员的编号。
     */
//    public int iceBreakingGame(int num, int target) {
//
//    }


        /*9. 回文数
    给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

    回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    例如，121 是回文，而 123 不是。*/
    public boolean isPalindrome(int x) {
        if (x<0||x%10==0) return false;
        int res = 0;
        /**不论x是什么数，也不管是不是回文。。循环条件”res<x“就保证了res最多只会比x多一位数*/
        while (res<x){
            int digit = x%10;
            res = res*10+digit;
            x /= 10;
        }
        return res == x|| res/10 == x;
    }


        /* 384.打乱数组
        实现一个支持以下操作的类：

        Solution(int[] nums) —— 用整数数组初始化对象

        reset() —— 重置数组到最初状态并返回

        shuffle() —— 返回数组随机打乱后的结果
     */
    class Solution {
            //        Random() random = new Random();  为什么这样写是错的，报错？？？？？
            int[] origin;
            int[] cur;

            public Solution(int[] nums) {
                origin = nums;
                cur = nums.clone();
            }

            public int[] reset() {
                cur = origin;
                return cur;
            }

            public int[] shuffle() {
                for (int i = 0; i < cur.length; i++) {
                    int index = i + new Random().nextInt(0, cur.length - i);
                    swap1(cur, i, index);
                }
                return cur;
            }

            private void swap1(int[] cur, int i, int index) {
                int tmp = cur[i];
                cur[i] = cur[index];
                cur[index] = tmp;
            }
        }


    /*120. 三角形最小路径和
    给定一个三角形 triangle ，找出自顶向下的最小路径和。

    每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     */
//    public int minimumTotal(List<List<Integer>> triangle) {

//    }


    /*
    LCR 139. 训练计划 I
教练使用整数数组 actions 记录一系列核心肌群训练项目编号。为增强训练趣味性，需要将所有奇数编号训练项目调整至偶数编号训练项目之前。请将调整后的训练项目编号以 数组 形式返回。
     */
//    public int[] trainingPlan(int[] actions) {
//
//    }


    /*189.轮转数组
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        if (k==0) return;
        reverse2(nums,0,nums.length-1);
        reverse2(nums,0,k-1);
        reverse2(nums,k,nums.length-1);
    }

    private void reverse2(int[] nums, int left, int right) {
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
//    public boolean isMatch(String s, String p) {
//
//    }

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
        int sum = 0;
        for (int num:nums){
            sum += num;
        }
        if ((sum&1)==1) return false;
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {
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
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1,right = nums.length-1;
            while (left<right){
                int val = nums[i]+nums[left]-nums[right];
                if (val>0){
                    /**如果此时满足”nums[i]+nums[left]>nums[right]“，则”left到right之间的位置作为新的left肯定还是可以满足
                     条件的，因此i和right固定不动，left的可取值有，left、left+1、left+2......right-1；一共有”right-left“
                     可取的值*/
                    res += (right-left);
                    right--;
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
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
//        if (m+n!=s3.length()) return false;
//        boolean[][] dp = new boolean[m + 1][n + 1];
//        dp[0][0] = true;
//        for (int i = 1; i <= n; i++) {
//            if (s2.substring(0,i).equals(s3.substring(0,i))){
//                dp[0][i] = true;
//            }
//        }
//
//        for (int i = 1; i <= m; i++) {
//            if (s1.substring(0,i).equals(s3.substring(0,i))){
//                dp[i][0] = true;
//            }
//        }
//
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                char c1 = s1.charAt(i - 1);
//                char c2 = s2.charAt(j - 1);
//                char c3 = s3.charAt(i + j - 1);
//                if (c1==c3||c2==c3){
//                    dp[i][j] = dp[i-1][j-1];
//                }else {
//
//                }
//            }
//        }
//    }


        /*400.第N个数字
    给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
     */
    public int findNthDigit(int n) {
        int digit = 1,start = 1;
        while (n>9*digit*start){
            n -= 9*digit*start;
            digit++;
            start*=10;
        }
        int num = start+(n-1)/digit;
        int index = (n-1)%digit;
        return String.valueOf(num).charAt(index)-'0';
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
//    public int maximalRectangle(char[][] matrix) {}



        /*1004.最大连续1的个数 III
    给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
     */
//    public int longestOnes(int[] nums, int k) {}



    /*63.不同路径 II
   给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。

   网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。

   返回机器人能够到达右下角的不同路径数量。

   测试用例保证答案小于等于 2 * 109。
    */
//    public int uniquePathsWithObstacles(int[][] obstacleGrid) {}


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
//    public int canCompleteCircuit(int[] gas, int[] cost) {}


    /*
    LCR 144. 翻转二叉树
给定一棵二叉树的根节点 root，请左右翻转这棵二叉树，并返回其根节点。
     */
//    public boolean isSymmetric(TreeNode root) {
//
//    }



    /*
    LCR 146. 螺旋遍历二维数组
给定一个二维数组 array，请返回「螺旋遍历」该数组的结果。

螺旋遍历：从左上角开始，按照 向右、向下、向左、向上 的顺序 依次 提取元素，然后再进入内部一层重复相同的步骤，直到提取完所有元素。
     */
//    public int[] spiralArray(int[][] array) {
//
//    }


        /*673. 最长递增子序列的个数
给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。

注意 这个数列必须是 严格 递增的。
     */
//    public int findNumberOfLIS(int[] nums) {
//
//    }



        /*349.两个数组的交集
        给定两个数组 nums1 和 nums2，返回它们的交集。

        结果中的每个元素 唯一

        顺序不限
     */
//    public int[] intersection(int[] nums1, int[] nums2) {
//
//    }



    /*51.N皇后
    按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    * */
//    public List<List<String>> solveNQueens(int n) {}



        /*
    264. 丑数 II
    给你一个整数 n ，请你找出并返回第 n 个 丑数 。
    丑数 就是质因子只包含 2、3 和 5 的正整数。
     */
//    public int nthUglyNumber(int n) {
//
//    }

     /*84.柱状图中最大的矩形
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
    * */
//    public int largestRectangleArea(int[] heights) {}


    /*253. 会议室 II   vip题目*/


    /*
    279. 完全平方数
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     */
//    public int numSquares(int n) {
//
//    }



        /*316.去除重复字母
    给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     */
//    public String removeDuplicateLetters(String s) {}


    /*
    面试题 02.05. 链表求和
给定两个用链表表示的整数，每个节点包含一个数位。

这些数位是反向存放的，也就是个位排在链表首部。

编写函数对这两个整数求和，并用链表形式返回结果。
     */
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//    }



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
//    public String simplifyPath(String path) {}

}
