package leecode_Debug.topcode_template_Locked;

import com.sun.source.tree.Tree;
import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * 使用时复制一份！！！！！！！
 */
public class hot100_temp_1 {

    /*
    49. 字母异位词分组
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     */
//    public List<List<String>> groupAnagrams(String[] strs) {
//
//    }

    /*
    438. 找到字符串中所有字母异位词
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     */
//    public List<Integer> findAnagrams(String s, String p) {
//
//    }


    /*
    101. 对称二叉树
给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
//    public boolean isSymmetric(TreeNode root) {
//        if(root==null) return true;
//        if (root.left==null&&root.right==null) return true;
//        if (root.left==null||root.right==null) return false;
//        LinkedList<TreeNode> left = new LinkedList<>();
//        LinkedList<TreeNode> right = new LinkedList<>();
//        left.offer(root.left);
//        right.offer(root.right);
//        while (!left.isEmpty()){
//            TreeNode pollLeft = left.poll();
//            TreeNode pollRight = right.poll();
//            if (pollLeft.val!=pollRight.val) return false;
//            if (pollLeft)
//        }
//    }

    /*
    437. 路径总和 III
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     */
//    public int pathSum(TreeNode root, int targetSum) {
//
//    }

    /*
    131. 分割回文串
给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     */
//    public List<List<String>> partition(String s) {
//
//    }



    /*
    51. N 皇后
按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     */
//    public List<List<String>> solveNQueens(int n) {
//
//    }

    /*
    215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
//    public int findKthLargest(int[] nums, int k) {
//        return findKthLargest(nums,0,nums.length-1,nums.length-k);
//    }

//    private int findKthLargest(int[] nums, int left, int right, int index) {
//        int pivotIndex = left+new Random().nextInt(0,right-left+1);
//        int pivot = nums[pivotIndex];
//
//        int[] cur = partion11(nums,left,right,pivot);
//        if (cur[0]>index){
//            findKthLargest(nums,left,cur[0]-1,index);
//        } else if (cur[1] < index) {
//            findKthLargest(nums,cur[1]+1,right,index);
//        }else {
//            return pivot;
//        }
//        return -1;
//    }

    /**for循环不好写？？？*/
//    private int[] partion11(int[] nums, int left, int right, int pivot) {
//        int i = left,j = right;
//        for (int k = left; k <= right; k++) {
//            if (nums[k]<pivot){
//                swap5(nums,i++,k);
//            }
//            while ()
//        }
//    }

//    private int[] partion11(int[] nums, int left, int right, int pivot) {
//        int cur = left;
//
//    }
}
