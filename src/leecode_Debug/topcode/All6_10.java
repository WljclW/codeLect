package leecode_Debug.topcode;

import leecode_Debug._hot100.NeedMore;
import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/9/8 14:26
 */

/**
 * err：138、59、213、611、400、316
 * undo：手撕堆排序、297、207、224、460、47、40、123、498、LCR 170；
 *      LCR 155、7、518、LCR 143、91、572、50、440、LCR 159
 *      329、450、10、328、208、225    LCR 216、LCR 127、LCR 161
 *      295、516、LCR 187、9、384、120、44、887、679、97、210
 *      395、349、51、264、253、673、344、
 *
 */
public class All6_10 {
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


    /*手撕堆排序

     */


    /*138复制链表*/
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
        while (resCur.next!=null){
            cur.next = cur.next.next;
            cur = cur.next;

            resCur = resCur.next.next;
            resCur = resCur.next;
        }

        return res;
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


    /*297
    序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在
    一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原
    数据。
请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
    你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格
    式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
    * */
    class Codec {

        // Encodes a tree to a single string.
//        public String serialize(TreeNode root) {
//
//        }

        // Decodes your encoded data to tree.
//        public TreeNode deserialize(String data) {
//
//        }
    }


    /*153....154是这个的拓展（允许有重复元素）
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
            if (nums[mid]>nums[right]){
                left = mid+1;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }


        /*207.
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
    * */
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//
//    }



    /*224.基本计算器

     */



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


    /*79.
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    * */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs1(board,i,j,word,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs1(char[][] board, int i, int j, String word, int index) {
        if (index==word.length()) return true;
        if (i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!=word.charAt(index)) return false;
        board[i][j] = '\n';
        boolean tmp = dfs1(board, i - 1, j, word, index + 1) ||
                dfs1(board, i + 1, j, word, index + 1) ||
                dfs1(board, i, j - 1, word, index + 1) ||
                dfs1(board, i, j + 1, word, index + 1);
        board[i][j] = word.charAt(index);
        return tmp;
    }


    /*47
    给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     */
//    public List<List<Integer>> permuteUnique(int[] nums) {
//
//    }


    /*402
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

        while (k>0&&!stack.isEmpty()){ /**【验证！！】应该是从末尾删除？？*/
            stack.pop();
            k--;
        }

        StringBuilder res = new StringBuilder();
        for (char c:stack){
            if (res.length()==0&&c=='0') continue;
            res.append(c);
        }
        return res.length()==0?"0":res.toString();
    }



    /*40
    给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的每个数字在每个组合中只能使用 一次 。
    注意：解集不能包含重复的组合。
     */
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {}


    /*
     * 11.盛最多水的容器
     * 给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
     *    成的，柱子之间是没有间隙的。
     * */
    public int maxArea(int[] height) {
        int res = 0;
        int left = 0,right = height.length-1;
        while (left<right){
            if (height[left]<height[right]){
                int curVal = height[left]*(right-left);
                res = Math.max(curVal,res);
                left++;
            }else {
                int curVal = height[right]*(right-left);
                res = Math.max(curVal,res);
                right--;
            }
        }
        return res;
    }


    /*手撕归并排序
     */
    public void mergerSort(int[] nums,int left,int right){
        if (left>=right) return;
        int mid = left+(right-left)/2;
        mergerSort(nums,left,mid);
        mergerSort(nums,mid+1,right);
        mergeTwo(nums,left,mid,right);
    }

    private void mergeTwo(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int cur = 0;
        int p1 = left,p2 = mid+1;
        while (p1<=mid&&p2<=right){
            if (nums[p1]<nums[p2]){
                tmp[cur++] = nums[p1++];
            }else {
                tmp[cur++] = nums[p2++];
            }
        }

        while (p1<=mid) tmp[cur++] = nums[p1++];
        while (p2<=right) tmp[cur++] = nums[p2++];

        for (int i = 0; i < tmp.length; i++) {
            nums[left+i] = tmp[i];
        }
    }


    /*136.
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


    /*123

     */



    /*
    16. 最接近的三数之和
        给定一个长度为 n 的整数数组 nums 和一个目标值 target，
        找出数组中三个整数，使得它们的和与 target 最接近。
        返回这三个数的和。
     */
    public int threeSumClosest(int[] nums, int target) {
        int res = Integer.MAX_VALUE;
        int flag = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1,right = nums.length-1;
            while (left<right){
                int curVal = nums[i]+nums[left]+nums[right];
                if (Math.abs(curVal-target)<flag){
                    res = curVal;
                    flag = Math.abs(curVal-target);
                }
                if (curVal<target){
                    left++;
                }else if (curVal>target){
                    right--;
                }else {
                    return curVal;
                }
            }
        }
        return res;
    }




    /*498

     */




    /*LCR 170 数组中的逆序对总数

     */


    /**
     * =============================================================7==================================================
     * =============================================================7==================================================
     * =============================================================7==================================================
     * =============================================================7==================================================
     * =============================================================7==================================================
     * =============================================================7==================================================
     */
    /*61
    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||k==0) return head;
        int size = 1;
        ListNode cur = head;
        while (cur.next!=null){
            cur = cur.next;
            size++;
        }
        cur.next = head;

        k%=size;
        cur = head;
        for (int i = 1; i < size-k; i++) {
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
        boolean hasNull = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            /**这种类型的题目并不需要关注某一层有多少节点，只要queue不是空就进行循环即可*/
            TreeNode cur = queue.poll();
            if (cur==null) hasNull = true;
            if (hasNull&&cur!=null) return false;
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }



    /*
    * 55.
    * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在
    * 该位置可以跳跃的最大长度。
        判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
    * */
    public boolean canJump(int[] nums) {
        int bound = 0;
        for (int i = 0; i < nums.length&&i<=bound; i++) {
            bound = Math.max(i+nums[i],bound);
            if (bound>=nums.length-1)  return true;
        }
        return false;
    }




    /*剑指offer36

     */


    /*26删除有序数组中的重复项（Remove Duplicates from Sorted Array）。
        给你一个 升序排列 的整数数组 nums，请你原地删除重复元素，使得每个元素只出现一次，返回新数组的长度。

        不要使用额外数组空间，必须在 O(1) 额外空间 的条件下原地修改输入数组。

        返回的数组前部分应包含去重后的元素，至于多余的元素值无所谓。
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length==1) return 1; /**这一句不要也是可以的*/
        int left = 0,cur = 1;
        while (cur< nums.length){
            if (nums[left]!=nums[cur]){
                nums[++left] = nums[cur];
            }
            cur++;
        }
        return left+1;
    }



    /*74.
    给你一个满足下述两条属性的 m x n 整数矩阵：
    每行中的整数从左到右按非严格递增顺序排列。
    每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
    * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int l = 0,r = m*n-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur>target){
                r = mid-1;
            } else if (cur<target) {
                l = mid+1;
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



        /*114.
    * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。*/
    public void flatten(TreeNode root) {
        if (root==null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode res = null;
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (cur.right!=null) stack.push(cur.right);
            if (cur.left!=null) stack.push(cur.left);
            if (res==null){
                res = cur;
            }else {
                res.right = cur;
                res.left = null;
                res = res.right;
            }
        }
    }


    /*
     518. 零钱兑换 II
    * */
//    public int change(int amount, int[] coins) {
//
//    }


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
        int[][] res = new int[n][n];
        int i = 1;
        int top = 0,bottom = n-1;
        int left = 0,right = n-1;
        while (i<=n){
            for (int j = left; j <=right; j++) {
                res[top][j] = i++;
            }
            top++;
            /**这里用不用额外的判断i的值？？？？？*/
            for (int j = top; j <=bottom; j++) {
                res[j][right] = i++;
            }
            right--;
            for (int j = right; j >=left; j--) {
                res[bottom][j] = i++;
            }
            bottom--;
            for (int j = bottom; j >=top ; j--) {
                res[j][left] = i++;
            }
            left++;
        }
        return res;
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
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 2; i < s.length(); i++) {
            char c = s.charAt(i);
            char cPrev = s.charAt(i - 1);
            if (c=='0'){
                if (cPrev>0&&cPrev<='2'){
                    dp[i] = dp[i-2];
                }else {
                    return 0;
                }
            }else {
                int curVal = cPrev-'0'*10+c;
                if (curVal<=26) dp[i] = dp[i-1]+dp[i-2];
                else dp[i] = dp[i-1];
            }
        }
        return dp[s.length()-1];
    }



    /*572
    给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。

二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
    * */
//    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//
//    }





    /*50

     */


    /*145
    给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
    * */
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




    /*75.
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
        int left = 0,cur = 0,right = nums.length-1;
        while (cur<=right){
            if (nums[cur]==0){
                swap1(nums,left++,cur++);
            }else if (nums[cur]==2){
                swap1(nums,cur,right--);
            }else
                cur++;
        }
    }

    private void swap1(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r]  =tmp;
    }




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


    /**
     * =================================8=====================================================================
     * =================================8=====================================================================
     * =================================8=====================================================================
     * =================================8=====================================================================
     * =================================8=====================================================================
     * =================================8=====================================================================
     */



    /*二叉搜索树的第K大节点

     */



    /*
    125. 验证回文串
    如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
    字母和数字都属于字母数字字符。

    给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(String s) {
        {
            int left = 0, right = s.length() - 1;
            while (left < right) {
                while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
                while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
                left++;
                right--;
            }
            return true;
        }
    }



    /*135
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

        for (int i = ratings.length-2; i >=0; i--) {
            if (ratings[i]>ratings[i+1])
                res[i] = Math.max(res[i],res[i+1]+1);
        }

        return Arrays.stream(res).sum();
    }


    /*442
    给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 最多两次 。请你找出所有出现 两次 的整数，并以数组形式返回。

你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间（不包括存储输出所需的空间）的算法解决此问题。
    * */
    public List<Integer> findDuplicates(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.abs(nums[i]);
            if (nums[cur-1]<0){
                res.add(cur);
            }else {
                nums[cur-1] *= -1;
            }
        }
        return res;
    }




    /*329
    给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
     */
//    public int longestIncreasingPath(int[][] matrix) {
//
//    }



    /*450
    给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
     */
//    public TreeNode deleteNode(TreeNode root, int key) {
//
//    }






    /*10
     */




    /*347.
     *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率
     * 前 k 高的元素。你可以按 任意顺序 返回答案。
     * */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->a[1]-b[1]); /**err：要按照频率排序，因此应该是a[1]-b[1]*/
        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            int[] cur = {key, value};
            queue.offer(cur);
            if (queue.size()>k){
                queue.poll();
            }
        }

        int[] res = new int[k];
        int i=0;
        for (int[] cur:queue){
            res[i++] = cur[0];
        }
        return res;
    }





    /*445.
        两个链表代表的数相加，两个数正序存放——————即高位在前
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newL1 = reverse1(l1);
        ListNode newL2 = reverse1(l2);

        int carry = 0;
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (newL1!=null||newL2!=null||carry!=0){
            int val1 = newL1==null?0:newL1.val;
            int val2 = newL2==null?0:newL2.val;
            int curVal = val1+val2+carry;
            ListNode newNode = new ListNode(curVal % 10);
            cur.next = newNode;
            cur = cur.next;
            carry = curVal/10;
            if (newL1!=null) newL1 = newL1.next;
            if (newL2!=null) newL2 = newL2.next;
        }
        return reverse1(dummy.next);
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


    /*45.
    * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
    每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你
    * 可以跳转到任意 nums[i + j] 处:
    0 <= j <= nums[i]
    i + j < n
    返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
    * */
    public int jump(int[] nums) {
        int step  =0; /**初始值设置为0？？还是1？？？应该是0*/
        int right = 0,bound = 0;
        for (int i = 0; i < nums.length-1; i++) {
            bound = Math.max(bound,i+nums[i]);
            if (i==right){
                right = bound;
                step++;
            }
        }
        return step;
    }


    /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
    public int findDuplicate(int[] nums) {
        int slow = 0,fast = 0;
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



        /*328
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
//    class Trie {
//
//        public Trie() {
//
//        }
//
//        public void insert(String word) {
//
//        }
//
//        public boolean search(String word) {
//
//        }
//
//        public boolean startsWith(String prefix) {
//
//        }
//    }



    /*225

     */




    /*213 打家劫舍Ⅱ
        所有的房屋成环
     */
    public int rob(int[] nums) {
        if (nums.length==1) return nums[0];
        if (nums.length==2) return Math.max(nums[0],nums[1]);
        return Math.max(rob(nums,0,nums.length-2),rob(nums,1,nums.length-1));
    }

    private int rob(int[] nums, int left, int right) {
        int first = nums[left];
        int second = Math.max(nums[left],nums[left+1]); /**err：第二个元素要取前两个的最大值*/
        for (int i = left+2; i <=right; i++) {
            int cur = Math.max(first+nums[i],second);
            first = second;
            second = cur;
        }
        return second;
    }


    /**
     * =============================================9==============================================
     * =============================================9==============================================
     * =============================================9==============================================
     * =============================================9==============================================
     * =============================================9==============================================
     * =============================================9==============================================
     */

    /*295
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




    /*230.
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。*/
    public int kthSmallest(TreeNode root, int k) {
        if (root==null) return -1;
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
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



    /*106
    给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     */
    int postorderIndex;
    HashMap<Integer,Integer> inorderMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length-1;
        inorderMap = new HashMap<>();
        /**记得把inorder所有的数据放进map，否则会报错：
            “java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because "<local7>" is null”
         原因是从map中获取到的值是null，因为map就没东西*/
        for(int i=0;i<postorder.length;i++){
            inorderMap.put(inorder[i],i);
        }
        return buildTree(inorder,postorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int l, int r) { /**l，r表示中序遍历的区间边界，即现在用哪些数来构建二叉树*/
        if (l>r) return null; /**err：如果没有这个的话，会报错“java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 5”*/
        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.right = buildTree(inorder,postorder,index+1,r); /**必须要先构建右子树，是不是应该是（index+1,r），应该是没问题的*/
        root.left = buildTree(inorder,postorder,l,index-1);
        return root;
    }


    /*678
    给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。

    有效 字符串符合如下规则：

    任何左括号 '(' 必须有相应的右括号 ')'。
    任何右括号 ')' 必须有相应的左括号 '(' 。
    左括号 '(' 必须在对应的右括号之前 ')'。
    '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
     */
    public boolean checkValidString(String s) {
        int min = 0,max = 0;
        for (char c:s.toCharArray()){
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
//    public int longestPalindromeSubseq(String s) {
//
//    }




    /*96. 不同的二叉搜索树
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */
    public int numTrees(int n) {
        if (n<=2) return n;
        int[] dp = new int[n+1];
        dp[0] = 1; /**err：必须要初始化dp[0]，否则初始的测试用例有错。。。比如3个节点，左子树两个、右子树没节点，此时有dp[2]*dp[0]种可能，如果不初始化dp[0]得到的就是0*/
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <=n; i++) {
            for (int j = 1; j <=i; j++) {
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }


    /*LCR 187. 破冰游戏
    社团共有 num 位成员参与破冰游戏，编号为 0 ~ num-1。成员们按照编号顺序围绕圆桌而坐。社长抽
    取一个数字 target，从 0 号成员起开始计数，排在第 target 位的成员离开圆桌，且成员离开后从下
    一个成员开始计数。请返回游戏结束时最后一位成员的编号。
     */
//    public int iceBreakingGame(int num, int target) {
//
//    }



    /*9. 回文数
    给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

    回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    例如，121 是回文，而 123 不是。*/



    /* 384
        实现一个支持以下操作的类：

        Solution(int[] nums) —— 用整数数组初始化对象

        reset() —— 重置数组到最初状态并返回

        shuffle() —— 返回数组随机打乱后的结果
     */



    /*120
    给定一个三角形 triangle ，找出自顶向下的最小路径和。

    每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     */
//    public int minimumTotal(List<List<Integer>> triangle) {

//    }




    /*189.
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * */
    /**
     *【说明】这里必须将k对nums.length取余，否则提交时用例报错————
              nums =
              [-1]
              k =
              2
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;    /**err：务必对k进行取余*/
        reverse2(nums,0,nums.length-1);
        reverse2(nums,0,k-1); /**是不是索引应该是这种样子？？是。前k个数因此，索引是闭区间[0,k-1]*/
        reverse2(nums,k,nums.length-1);
    }

    private void reverse2(int[] nums, int l, int r) {
        while (l<r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }



        /*44
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


    /*679
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


    /*611
    给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res =0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1,right = nums.length-1;
            while (left<right){
                if (nums[i]+nums[left]>nums[right]){
                    /**是不是应该把这里理解成”right“能取中间的这些值？？？
                     * 1. 如果理解成left取中间的值就乱了？？
                     * 2. right当前的位置都满足了”nums[i]+nums[left]>nums[right]“，中间的位置越靠左，因此nums[right]，
                     *      所以三角形的条件也必然成立*/
                    res += (right-left);
                }else {
                    left++;
                }
            }
        }
        return res;
    }



    /*400
    给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
     */
    public int findNthDigit(int n) {
        int digit =1;
        int start = 1;
        while (n>9*digit*start){
            n -= 9*digit*start;
            start*= 10;
            digit++;
        }
        int i = start + (n - 1) / digit;  /**这样写对不对？？？*/
        int index = (n-1)%digit;
        return (int) String.valueOf(i).charAt(index)-'0';
    }


    /*85
    给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[] height = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]=='1'){
                    height[j]++;
                }else {
                    height[j] = 0;
                }
            }
            int curVal = gerArea(height);
            res = Math.max(res,curVal);
        }
        return res;
    }

    private int gerArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length + 1; i++) {
            int curHeight = i==height.length?0:height[i];
            while (!stack.isEmpty()&&curHeight<height[stack.peek()]){
                Integer curIndex = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                int curVal = height[curIndex]*(i-left-1);
                res = Math.max(curVal,res);
            }
            stack.push(i);
        }
        return res;
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
    /*解法1：网友解法。官方有滚动数组优化的解法*/
//    public boolean isInterleave(String s1, String s2, String s3) {}




        /*210 课程表Ⅱ
    现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     */


    /**
     * ==============================================10==========================================================
     * ==============================================10==========================================================
     * ==============================================10==========================================================
     * ==============================================10==========================================================
     * ==============================================10==========================================================
     * ==============================================10==========================================================
     */
    /*1004
    给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
     */
    public int longestOnes(int[] nums, int k) {
        int zeroNum = 0;
        int left = 0,cur = 0;
        int res = 0;
        while (cur<nums.length){
            if (nums[cur]==0) zeroNum++;
            while (zeroNum>k){
                if (nums[left]==0){
                    zeroNum--;
                }
                left++;
            }
            res = Math.max(cur-left+1,res);
            cur++;  /**cur++写在这里的话上面的”cur-left“就需要+1*/
        }
        return res;
    }




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
        if (sum%2!=0) return false;
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
        }
        return dp[sum]==sum;
    }




    /*63
   给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。

   网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。

   返回机器人能够到达右下角的不同路径数量。

   测试用例保证答案小于等于 2 * 109。
    */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        if (obstacleGrid[m-1][n-1]==1) return 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0]==1){
                for (int j = i; j < m; j++) {
                    dp[j][0] = 0;
                }
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i]==1){
                for (int j = i; j < n; j++) {
                    dp[0][j] = 0;
                }
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j]==0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
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


        /*134
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int curTotal = 0,total =0;
        for (int i = 0; i < gas.length; i++) {
            curTotal += (gas[i]-cost[i]);
            total += (gas[i]-cost[i]);

            if (curTotal<0){
                start = i+1;
                curTotal = 0;
            }
        }
        return total<0?-1:start;
    }



    /*进制加法

     */


    /*349
        给定两个数组 nums1 和 nums2，返回它们的交集。

        结果中的每个元素 唯一

        顺序不限
     */



        /*51.
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




    /*84.
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
    * */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int curHeight = i==heights.length?0:heights[i];
            while (!stack.isEmpty()&&curHeight<heights[stack.peek()]){
                Integer curIndex = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                int curVal = heights[curIndex]*(i-left-1);
                res = Math.max(res,curVal);
            }
            stack.push(i);
        }
        return res;
    }




    /*253

     */


    /*316
    给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     */
    public String removeDuplicateLetters(String s) {
        int[] flag = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            flag[c-'a'] = i;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while (!stack.contains(c)&&!stack.isEmpty()&&c<stack.peek()&&flag[stack.peek()-'a']>i){
                stack.pop();
            }
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        for (char c:stack){
            sb.append(c);
        }
        return sb.toString();
    }





    /*673. 最长递增子序列的个数
给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。

注意 这个数列必须是 严格 递增的。
     */
//    public int findNumberOfLIS(int[] nums) {
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
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (".".equals(s)||"".equals(s)){
                continue;
            } else if ("..".equals(s)) {
                if (!stack.isEmpty()) /**err：if条件要放在这里*/
                    stack.pop();
            }else {
                stack.push(s);
            }
        }
        if (stack.isEmpty()) return "/"; /**最后栈里面是空，也必须返回”/“*/

        StringBuilder sb = new StringBuilder();
        for (String c:stack){
            sb.append("/").append(c);
        }
        return sb.toString();
    }



    /*344

     */



}
