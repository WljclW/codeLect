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
    108. 将有序数组转换为二叉搜索树
给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        /**调用的方法的含义：使用nums数组[0,nums.length-1]这区间内的数构建出二叉搜索树并返回*/
        return sortedArrayToBST(nums,0,nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left>right) return null;
        if (left==right) return new TreeNode(nums[left]);
        int mid = left+(right-left)/2;
        TreeNode root = new TreeNode(mid);
        root.left = sortedArrayToBST(nums,left,mid-1);
        root.right  =sortedArrayToBST(nums,mid+1,right);
        return root;
    }

    /*
    437. 路径总和 III
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     */
//    public int pathSum(TreeNode root, int targetSum) {
//
//    }

    /*
    994. 腐烂的橘子
在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：

值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。

返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     */
    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        int m = grid.length,n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]==1) fresh++;
                if (grid[i][j]==2) queue.offer(new int[]{i,j});
            }
        }

        int minute = 0;
        if (fresh==0) return minute;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!queue.isEmpty()){
            int size = queue.size();
            minute++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for(int[] dir:dirs){
                    int x = dir[0]+cur[0],y = dir[1]+cur[1];
                    if (x>=0&&x<m&&y>=0&&y<n&&grid[x][y]==1){
                        fresh--;
                        grid[x][y] = 2;
                        queue.offer(new int[]{x,y});
                    }
                }
            }
        }
        return fresh==0?minute:-1;
    }


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
    33. 搜索旋转排序数组
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     */
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>=nums[left]){
                if (target>=nums[left]&&target<nums[mid]){
                    right  = mid-1;
                }else {
                    left =mid+1;
                }
            }else {
                if (target>nums[mid]&&target<=nums[right]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    /*
    153. 寻找旋转排序数组中的最小值
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题
     */
    /**这个题与最左边的比较不合适————
        比如如果nums[mid]>nums[left]，接下来应该取那一边查找呢？？但按时不一定的，没有一个明确的方向，如果使用的话必须添加额外的判断！！！
     举个例子：
            [4,5,6,7,0,1,2]，第一次nums[mid]等于7，满足 nums[mid]>nums[left]，此时的最小值应该去mid的右边查找
            [0,1,2,4,5,6,7]，第一次nums[mid]等于4，满足 nums[mid]>nums[left]，此时的最小值应该去mid的左边查找
        综上，这样的比较并没有一个明确的方向，因此尽量不要这么比较....结论：尽量和 右边界的数 进行比较判断接下来的方向！！！
     */
    public int findMin(int[] nums) {
        int left = 0,right = nums.length-1;
        int ans = -1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]<=nums[right]){
                ans = mid;
            }else {
                left = mid+1;
            }
        }
        return nums[ans];
    }

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

    /*
    5. 最长回文子串
给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
//    public String longestPalindrome(String s) {
//
//    }
}
