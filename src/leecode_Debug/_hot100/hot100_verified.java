package leecode_Debug._hot100;

import leecode_Debug.top100.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author mini-zch
 * @date 2025/8/12 19:27
 */
public class hot100_verified {
    /*
     * 76.给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
    public String minWindow(String s, String t) {
        if (s.length()<t.length()) return "";
        HashMap<Character, Integer> need = new HashMap<>();
        for(char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int start = 0;
        int left = 0,right = 0;
        int len = Integer.MAX_VALUE;
        int valid = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        while (right<s.length()){
            char c = s.charAt(right);
            right++;

            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c)==need.get(c)){
                    valid++;
                }
                /**下面代码的位置跟原来方案是不一样的，是否可行？？*/
                while (valid==need.size()){
                    if (right-left<len){
                        len = right-left;
                        start = left;
                    }
                    char c1 = s.charAt(left);
                    if (need.containsKey(c1)){
                        window.put(c1,window.get(c1)-1);
                        if (window.get(c1)<need.get(c1)) /**【注意】这里必须认真判断一下c1字符的数量是不是不够要求了*/
                            valid--;
                    }
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }



    /*73.
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和
     * 列的所有元素都设为 0 。请使用 原地 算法。
     * */
    public void setZeroes(int[][] matrix) {
        boolean firCol = false,firRow = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0]==0){
                firCol = true;
                break;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i]==0){
                firRow = true;
                break;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]==0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        /**代码到这里第一行和第一列已经变了（根据矩阵中其他元素是不是0，已经修改了它对应第一行和第一列的元素）。。。。。
         * 因此，第一行和第一列是不是0需要根据两个标志变量来决定.*/
        /*
        chatgpt解释的错误原因如下：
            破坏了第一行、第一列的原始标记数据
            在第一次循环里，你把第一行和第一列作为标记位存了哪些行/列需要置零。
            但现在你第二次循环是从 (0,0) 开始直接置零的，这会提前覆盖第一行和第一列的标记。
            这样后面的判断会基于已经被改掉的值，导致逻辑出错。

        和 firRow / firCol 的职责冲突
            firRow 和 firCol 是用来在最后一步统一处理第一行、第一列的。
            但你现在在中间步骤已经改了第一行、第一列，相当于破坏了标记区的“缓存作用”。
            比如，如果第一行本来不需要置零，但由于某一列被置零，第一行的某个元素被提前改成了 0，就会让 firRow 的意义失真。
        * */
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0]==0||matrix[0][j]==0)
                    matrix[i][j] = 0;
            }
        }

        if (firCol){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if (firRow){
            Arrays.fill(matrix[0], 0);
        }
    }


    /*124
    * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
    路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    int resMaxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfsMaxPathSum(root);
        return resMaxPathSum;
    }

    private int dfsMaxPathSum(TreeNode root) {
        if (root==null) return 0;
        int l = dfsMaxPathSum(root.left);
        int r = dfsMaxPathSum(root.right);
        l = Math.max(l,0);
        r = Math.max(r,0);
        resMaxPathSum = Math.max(l+r+root.val,resMaxPathSum);
        return Math.max(r,l)+root.val;
    }


    /*108.升序数组转换为平衡二叉搜索树*/
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums,0,nums.length-1);
    }

    private TreeNode buildTree(int[] nums, int l, int r) {
        if (l>r) return null;
        int mid = l+(r-l)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums,l,mid-1);
        root.right = buildTree(nums,mid+1,r);
        return root;
    }


    /**437需要再看看*/


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
}
