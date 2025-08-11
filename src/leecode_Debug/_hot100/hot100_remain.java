package leecode_Debug._hot100;

import leecode_Debug.top100.TreeNode;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author mini-zch
 * @date 2025/8/11 15:25
 */
public class hot100_remain {
    /*108.升序数组转换为平衡二叉搜索树*/
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums,0,nums.length-1);
    }

    private TreeNode build(int[] nums, int l, int r) {
        if (l<r) return null;
        int mid = l+(r-l)/2;
        TreeNode root = new TreeNode(mid);
        root.left = build(nums,l,mid-1);
        root.right = build(nums,mid+1,r);
        return root;
    }


    /*73.
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和
     * 列的所有元素都设为 0 。请使用 原地 算法。
     * */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        boolean firRow = false,firCol = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0]==0){
                firCol = true;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (matrix[0][i]==0){
                firRow = true;
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]==0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][0]==0||matrix[0][j]==0)
                    matrix[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            if (firCol)
                matrix[i][0] = 0;
        }

        for (int i = 0; i < n; i++) {
            if (firRow)
                matrix[0][i] = 0;
        }
    }


    /*
     * 76.给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
    public String minWindow(String s, String t) {
        if (s.length()<t.length()) return "";

        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int left = 0,right = 0;
        int valid = 0;
        int start = 0,len = Integer.MAX_VALUE;

        HashMap<Character, Integer> window = new HashMap<>();
        while (right<s.length()){
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c)==need.get(c)){  /**这里拿到的是Integer，用“==”会不会有问题*/
                    valid++;
                }
            }

            while (valid==t.length()){
                if (right-left<len){
                    start = left;
                    len = right-left;
                }

                char leftChar = s.charAt(left);
                left++;
                if (need.containsKey(leftChar)){
                    window.put(leftChar,window.get(leftChar)-1);
                    if (window.get(leftChar)<need.get(leftChar)){
                        valid--;
                    }
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }

    /**
     * 体会下面的两个题都必须额外的使用一个方法来深度优先遍历二叉树的原因。
     */
    /*543.
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDiameter;
    }

    private int dfs(TreeNode root) {
        if (root==null) return 0;
        int lDepth = dfs(root.left);
        int rDepth = dfs(root.right);
        maxDiameter = Math.max(lDepth+rDepth,maxDiameter);
        return Math.max(lDepth,rDepth)+1;
    }

    /*124
    * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
    路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    /**
     * 1.实际上更新结果的时候是左右两边都能走的
     * 2. 对于每一个节点，我们希望左右子树返回什么信息？
     *      比如，左子树，我们希望它返回以他为根的数的最大路径和（注意和题目提到的路径是有区别的，这里的路径不能既包括左子树又包括右
     *  子树。只能包括左右子树中的一边！！）
     * 3. 1是题目需要求解的信息，2是题目中我们期望左右孩子节点返回的信息————但这两者很明显是不一样的，这种区别就造成了必须使用额外的
     *      方法来完成遍历二叉树，并在这个过程中更新结果。
     * */
    int resMaxPathSum = Integer.MIN_VALUE; /**【注】：这里初始化为0应该是不行的*/
    public int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        dfsMaxPathSum(root);
        return resMaxPathSum;
    }

    private int dfsMaxPathSum(TreeNode root) {
        if (root==null) return 0;
        int lSum = dfsMaxPathSum(root.left);
        int rSum = dfsMaxPathSum(root.right);
        lSum = Math.max(lSum,0);
        rSum = Math.max(rSum,0);
        resMaxPathSum = Math.max(lSum+rSum+root.val,resMaxPathSum);
        return root.val+Math.max(lSum,rSum);
    }


     /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
    /*方法1：链表找环原理*/
    public int findDuplicate(int[] nums) {
        int slow =0,fast =0;
        do {
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

    /**chatgpt给出的是下面的这种形式，有什么区别？
     * 疑问？最开始slow和fast的初始值，是不是必须和阶段2中slow的重置值一样，如果不一样是不是就错了
     * */
    public int findDuplicate_chatgpt(int[] nums) {
        // 阶段 1：快慢指针找相遇点
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 阶段 2：找到入口点（重复数）
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /*方法2：二分查找*/



    /*4.
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
    * */

    /**
     *【思路】1. 想象在nums1中的每一个间隙都能插入隔板，这个隔板的索引从[0,nums1.length].因此隔板的索引值就指出了这个隔板之前一共有
     *      多少个数。。。。
     *       2. 已知两个数组，一半有多少个数呢？(len1+len2+1)/2........这样的计算方法包含了技术和偶数的情况————
     *          如果总共有偶数个数，则左右两半数的数量是相等的；
     *          如果总共有奇数哥数，则左边的数比右边的数多1个。
     *【求解过程】
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);
        int len1 = nums1.length, len2 = nums2.length;
        int l = 0, r = nums1.length;
        while (l < r) {  /**如果这里不带=，是不是下面计算中位数的过程就需要放在while循环之外进行了*/
            int i = l + (r - l) / 2;
            int j = (len1 + len2 + 1) / 2 - i;

            int nums1Left = (i - 1) < 0 ? Integer.MIN_VALUE : nums1[i - 1]; /**挡板在的位置i代表左边有i个元素，因此左边的元素是索引为“i-1”的元素*/
            int nums1Right = (i == len1) ? Integer.MAX_VALUE : nums1[i];/**挡板右边的第一个元素是第i+1个元素，即索引为i的元素————nums1[i]*/
            int nums2Left = (j - 1) < 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2Right = (j == len2) ? Integer.MAX_VALUE : nums2[j];

            if (nums1Left <= nums2Right && nums2Left <= nums1Right) {
                if ((len1 + len2) % 2 == 0) {
                    return (Math.max(nums1Left, nums2Left) +
                            Math.min(nums2Right, nums1Right)) / 2.0;
                } else {
                    return Math.max(nums1Left, nums2Left) * 1.0;
                }
            } else if (nums1Left > nums2Right) { /**说明nums1挑选出的数太大了，这个数需要变小*/
                r = i - 1;
            } else { /**说明nums1选出来的数太小了，导致从nums2中挑选”总数一半的数“后得到的最大值比nums1剩下的最小值还大。。。需要把nums1变大，因此挪左指针*/
                l = i + 1;
            }
        }
        return -1;
    }


    /*347.
     *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率
     * 前 k 高的元素。你可以按 任意顺序 返回答案。
     * */
//    public int[] topKFrequent(int[] nums, int k) {
//
//    }


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
    class MedianFinder {

        PriorityQueue<Integer> small; //要求堆顶是最小值
        PriorityQueue<Integer> big;
        public MedianFinder() {
            small = new PriorityQueue<>();
            big = new PriorityQueue<>((o1,o2)->{
                return o2-o1;
            });
        }

        //正确的方法应该是下面的形式，原因？？
        public void addNum(int num) {
            small.offer(num);
            if (small.size()>big.size()){
                big.offer(small.poll());
            }
        }



        public double findMedian() {
            if (small.size()==big.size()){
                return (small.poll()+big.poll())/2.0;
            }
                return small.peek();
        }
    }

}
