package leecode_Debug;

import leecode_Debug.BTree.TreeNode;
import leecode_Debug.top100.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mini-zch
 * @date 2025/5/19 11:14
 */
public class All_02 {

    /*
     * 3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。无重复字符的最长子串
     * */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0,max = 0;
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while(map.get(c)>1){
                Integer leftVal = map.get(s.charAt(left));
                map.put(s.charAt(left),leftVal-1);
                left++;
            }
            max = Math.max(max,i-left+1);
        }
        return max;
    }


    /*198
    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃
    的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一
    晚上被小偷闯入，系统会自动报警。

    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一
    夜之内能够偷窃到的最高金额。
    * */
    public int rob(int[] nums) {  //验证后补充解法2——leecode_Debug._hot100._14DP.rob(int[])
        if (nums.length==0) return nums[0];
        int first = nums[0],sec=Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            int cur = Math.max(nums[i]+first,sec);
            first = sec;
            sec = cur;
        }
        return sec;
    }


    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
//    public int coinChange(int[] coins, int amount){
//        return coinChange(coins,0,amount);
//    }
//
//    public int coinChange(int[] coins,int index,int amount){
//        if (amount==0) return 0;
//        if (index==coins.length || amount<0) return -1;
//        for (int i=0;i<amount/coins[index];i++){
//            int i1 = coinChange(coins, index + 1, amount - i * coins[i]);
//
//        }
//    }


    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        int[] min = new int[nums.length + 1];
        int[] max = new int[nums.length + 1];
        min[0]=1;
        max[0]=1;
        for (int i=1;i<=nums.length;i++){
            min[i] = Math.min(Math.min(nums[i]*min[i-1],nums[i]*max[i-1]),nums[i]);
            max[i] = Math.max(Math.max(nums[i]*min[i-1],nums[i]*max[i-1]),nums[i]);
            res = Math.max(max[i],res);
        }
        return res;
    }


        /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
            返回 滑动窗口中的最大值 。
    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i=0;i<k;i++){
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
                deque.pollLast();
            }
            deque.addLast(i);
        }
        res[0] = nums[deque.peek()];

        for (int i=k;i<nums.length;i++){
            if (deque.peek()==i-k){
                deque.poll();
            }
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
                deque.pollLast();
            }
            deque.addLast(i);
            res[i-k+1] = nums[deque.peek()];
        }
        return res;
    }


    /*54.
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        LinkedList<Integer> res = new LinkedList<>();
        int top=0,bottom=m-1,left=0,right=n-1;
        while(res.size()<m*n){
            if (left<=right){
                for (int i=left;i<=right;i++){
                    res.add(matrix[top][i]);
                }
            }
            top++;
            if (top<=bottom){
                for (int i=top;i<=bottom;i++){
                    res.add(matrix[i][right]);
                }
            }
            right--;
            if (right>=left){
                for (int i=right;i>=left;i--){
                    res.add(matrix[bottom][i]);
                }
            }
            bottom--;
            if (top<=bottom){
                for (int i=bottom;i>=top;i--){
                    res.add(matrix[i][left]);
                }
            }
            left++;
        }

        return res;
    }


    /*48.
    * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。*/
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i=0;i<n;i++)
            for (int j=0;j<i;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }

        for (int i=0;i<n;i++)
            for (int j=0;j<n/2;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
    }



    /*240.
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int i=0,j=n-1;
        while(i>=0&&i<m&&j>=0&&j<n){
            int cur = matrix[i][j];
            if (cur>target){
                j--;
            } else if (cur<target) {
                i++;
            }else
                return true;
        }
        return false;
    }


    /*92？？？
     * 官方解的最优方案不是很懂？？？*/
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//        ListNode dummy = new ListNode(-1,head);
//        ListNode fast = head,slow = head;
//
//        for (int i)
//
//        return dummy.next;
//    }

        /*104
    * 给定一个二叉树 root ，返回其最大深度。
    二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。*/
    public int maxDepth(TreeNode root) {
        if (root==null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right)+1;
    }


    /*
     * 226.给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * */
//    public TreeNode invertTree(TreeNode root) {
//        if (root==null) return root;
//        invertTree(root,root.left,root.right);
//    }
//
//    public TreeNode inverTree(TreeNode root,TreeNode left,TreeNode right){
//
//    }


    /*101.给你一个二叉树的根节点 root ， 检查它是否轴对称。*/
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        if (root.left==null&&root.right==null) return true;
        if (root.left==null||root.right==null) return false;
        Boolean flag = (root.left.val==root.right.val);
        return isSymmetric(root.left)&&isSymmetric(root.right)&&flag;
    }


        /*543.
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
//    public int diameterOfBinaryTree(TreeNode root) {
//        if (root.left==null && root.right==null) return 0;
//        if (root.left==null || root.right==null) return 1;
//        return Math.max(root.left)
//    }


    /*102.层序遍历*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            LinkedList<Integer> ele = new LinkedList<>();
            for (int i=0;i<size;i++){
                TreeNode poll = deque.poll();
                ele.add(poll.val);
                if (poll.left!=null) deque.offer(poll.left);
                if (poll.right!=null) deque.offer(poll.right);
            }
            res.add(new LinkedList<Integer>(ele));
        }
        return res;
    }


    /*199.
    给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    * */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i=0;i<size;i++){
                TreeNode cur = deque.poll();
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
                if (i==size-1) res.add(cur.val);
            }
        }
        return res;
    }



    /*236.二叉树最近公共祖先
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        if (root==p||root==q) return root;
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if (leftNode!=null&&rightNode!=null) return root;
        return (leftNode==null)?rightNode:leftNode;
    }


    /*94中序遍历*/
//    public List<Integer> inorderTraversal(TreeNode root) {
//        LinkedList<Integer> res = new LinkedList<>();
//        if (root==null) return res;
//        while (){
//
//        }
//
//        return res;
//    }

    //72.编辑距离
    public int minDistance(String str1,String str2){
        int m = str1.length(),n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i=0;i<n;i++){
            dp[0][i] = i;
        }
        for (int i=0;i<m;i++){
            dp[i][0] = i;
        }
        for (int i=1;i<m;i++)
            for (int j=1;j<n;j++){
                int tmp = 0;
                if (str1.charAt(i)==str2.charAt(j)){
                    tmp = dp[i-1][j-1];
                }else{
                    tmp = dp[i-1][j-1] + 1;
                }
                dp[i][j] = Math.min(tmp,Math.min(dp[i-1][j],dp[i][j-1])+1);
            }
        return dp[m-1][n-1];
    }


    /**139.
    * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利
    * 用字典中出现的一个或多个单词拼接出 s 则返回 true。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i=1;i<=s.length();i++){
            for (int j=0;j<i;j++){
                if (dp[j]&&wordDict.contains(s.substring(j+1,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    //128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i:nums){
            set.add(i);
        }

        int res = -1;
        for (int i=0;i<nums.length;i++){
            if (!set.contains(nums[i])){
                int j=0;
                while(set.contains(nums[i]+j)) j++;
                res = Math.max(res,j);
            }
        }
        return res;
    }


    //200
    public int numIsland(char[][] grid){
        int res = 0;
        int m = grid.length,n = grid[0].length;
        for (int i=0;i<m;i++)
            for (int j=0;j<n;j++){
                if (grid[i][j]=='1'){
                    res++;
                    dfs(grid,i,j,m,n);
                }
            }
        return res;
    }

    void dfs(char[][] grid,int i,int j,int m,int n){
        if (i<0||i>=m||j<0||j>=n)
            return;
        if (grid[i][j]=='1') grid[i][j]='0';
        dfs(grid,i-1,j,m,n);
        dfs(grid,i,j-1,m,n);
        dfs(grid,i+1,j,m,n);
        dfs(grid,i,j+1,m,n);
    }


    //221最大正方形
    public int max(char[][] matrix){
        int m = matrix.length,n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i=0;i<n;i++){
            dp[0][i] = matrix[0][i]-'0';
            max = Math.max(max,dp[0][i]);
        }
        for (int i=0;i<m;i++){
            dp[i][0] = matrix[i][0]-'0';
            max = Math.max(max,dp[0][i]);
        }
        for (int i=1;i<m;i++)
            for (int j=0;j<n;j++){
                if (matrix[i][j]=='1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    max = Math.max(dp[i][j],max);
                }
            }
        return max*max;
    }



        /*1.
    * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
    你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
    你可以按任意顺序返回答案。*/
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])) return new int[]{i,map.get(nums[i])};
            map.put(target-nums[i],i);
        }
        return new int[]{-1,-1};
    }

}
