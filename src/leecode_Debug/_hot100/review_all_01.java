package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * 438、76、73、54、108、437、287、5、4、
 * 279、322、416
 * 347、215、295
 * */
/**
 * 25. K 个一组翻转链表
 * 23. 合并 K 个升序链表
 * 155. 最小栈
 * 287. 寻找重复数
 * 300. 最长递增子序列
 * 41. 缺失的第一个正数
 * 24. 两两交换链表中的节点
 * 105. 从前序与中序遍历序列构造二叉树
 * 31. 下一个排列
 * 101
 * 114
 * 300. 最长递增子序列
 * 416
 * 82. 删除排序链表中的重复元素 II
 * 143. 重排链表
 * 328
 * 402. 移掉 K 位数字
 * 287
 * 560
 * 43
 * @author mini-zch
 * @date 2025/7/22 16:20
 */
public class review_all_01 {
    public List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> res = new LinkedList<>();
        int top = 0,bottom = matrix.length-1,left = 0,right = matrix[0].length-1;
        int m = matrix.length,n = matrix[0].length;
        while (res.size()<m*n){
            if (res.size()<m*n&&left<=right){
                for (int i = left; i <= right; i++) {
                    res.add(matrix[top][i]);
                }
                top++;
            }
            if (res.size()<m*n&&top<=bottom){
                for (int i = top; i <=bottom ; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
            }
            if (res.size()<m*n&&left<=right){
                for (int i = right; i >=left ; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (res.size()<m*n&&top<=bottom){
                for (int i = bottom; i >=top ; i--) {
                    res.add(matrix[left][i]);
                }
                left++;
            }
        }

        return res;
    }

    //74
//    public boolean searchMatrix_(int[][] matrix, int target) {
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


//    public TreeNode invertTree_diedai(TreeNode root) {
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

    /*583. 两个字符串的删除操作
     * */
//    public int minDistance_583(String word1, String word2) {
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
    /**见leecode_Debug._hot100._01bag*/
    public boolean canPartition(int[] nums) {
        return false;
    }

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


    /*328
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */
    public ListNode oddEvenList(ListNode head) {
        return null;
    }


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
