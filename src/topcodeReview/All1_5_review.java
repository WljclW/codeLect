package topcodeReview;

import leecode_Debug.BTree.TreeNode;
import leecode_Debug.top100.ListNode;
import zuo_course_01base.No01_sort.GetRandom;

import java.util.*;

/**
 * 2025.9.26
 */

/**
 * 470需要理解一下官方的解、283有没有直接交换的方法省略最后的遍历操作
 * err：234、179、69、146
 * 468、1324、113、堆排序、148、151、76、8、93、227
 * 72、1143写出空间优化版本
 */

/**
 * 2025.10.13......
 *      单行注释都是以前写的代码
 *      给“leecode_Debug._hot100._11stack.MinStack”补充判断相等的另外方法，使用“.intValue()”
 *   err:5、113、112、、、、、、、
 */
public class All1_5_review {
    /*215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,nums.length-k);
    }

    private int findKthLargest(int[] nums, int left, int right, int k) {
        if (left==right) return nums[left];
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap1(nums,pivotIndex,right);
        pivotIndex = partion1(nums,left,right);
        if (pivotIndex==k){
            return nums[pivotIndex];
        } else if (pivotIndex > k) {
            return findKthLargest(nums,left,pivotIndex-1,k);
        }else {
            return findKthLargest(nums,pivotIndex+1,right,k);
        }
    }

    private int partion1(int[] nums, int left, int right) {
        int cur = left;
        for (int i = left; i < right; i++) {
            if (nums[i]<=nums[right]){
                swap1(nums,cur++,i);
            }
        }
        swap1(nums,left,cur); /**这里不能再让cur加加了，快排的代码种也是一样的道理*/
        return cur;
    }

    private void swap1(int[] nums, int pivotIndex, int right) {
        int tmp = nums[pivotIndex];
        nums[pivotIndex] = nums[right];
        nums[right] = tmp;
    }


    /*5 最长回文子串
    给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
    public String longestPalindrome(String s) {
        if (s==null||s.length()<=1) return s;
        StringBuilder sb = new StringBuilder("#");
        for (char c:s.toCharArray()){
            sb.append(c).append("#");
        }
        String s1 = sb.toString();

        int[] dp = new int[sb.length()];
        int center = 0,maxLen = 0;
        int start = -1,maxLenRel = 0;
        for (int i = 0; i < s1.length(); i++) {
            int mirror = 2*center-i;
            if (i<=maxLen){
                dp[i] = Math.min(dp[mirror],maxLen-i);
            }

            int left = i-dp[i]-1,right = i+dp[i]+1;
            while (left>=0&&right<sb.length()&&s1.charAt(left)==s1.charAt(right)){
                dp[i]++;
                left--;
                right++;
            }

            if (i+dp[i]>maxLen){
                center = i;
                maxLen = i+dp[i];
            }
            /**下面的代码不是很懂！！！！！！！！！！*/
            if (dp[i]>maxLenRel){
                maxLenRel = dp[i];
                start = (i-maxLenRel)/2;
            }
        }
        return s.substring(start,start+maxLenRel);
    }

    /**
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     */
    /*
    143重排链表
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return;
        ListNode mid = findLastMid(head);
        ListNode head2 = mid.next;
        mid.next = null;

        ListNode odd = head,even = head2;
        while (even!=null){
            ListNode oddnext = odd.next;
            ListNode evenNext = even.next;
            odd.next = evenNext;
            even.next = oddnext;

            odd = oddnext;
            even = evenNext;
        }
        /**这里for循环的写法有点不一样，不能像“奇偶链表”或者“复制链表”一样使用“odd=odd.next.next”这样的更新操作*/
//        while (even!=null){
//            ListNode oddnext = odd.next;
//            ListNode evenNext = even.next;
//            odd.next = evenNext;
//            even.next = oddnext;
//
//            odd = odd.next.next;
//            even = even;
//        }
    }

    private ListNode findLastMid(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*72.编辑距离
    * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
    你可以对一个单词进行如下三种操作：
    插入一个字符
    删除一个字符
    替换一个字符*/
    /**二维的写法*/
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1==c2){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }
        return dp[m][n];
    }

    /**两行数组的滚动更新*/
    public int minDistance_2row(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        if (m<n) return minDistance_2row(word2,word1);
        int[] prev = new int[n + 1];
        int[] cur = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            prev[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            cur[0] = i;
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1==c2){
                    cur[j] = prev[j-1]; /**同理，两行数组滚动更新中“prev”就等同于二维dp中的“dp[i-1]”，“cur”等同于二维dp中的“dp[i]”*/
                }else {
                    cur[j] = Math.min(Math.min(cur[j-1],prev[j]),prev[j-1])+1;
                }
            }
            int[] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        return prev[n];
    }

    public int minDistance_1dim(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        if (m<n) return minDistance_1dim(word2,word1);
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        /**可以看到，在“一行数组+局部变量”的dp形式中。“prev“相当于“二维中的dp[i-1][j-1]”*/
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1==c2){
                    dp[j] = prev;
                }else {
                    /**左边的dp[j]相当于二维中的dp[i][j]，右边的dp[j-1]相当于二维中的dp[i][j-1]，右边的dp[j]相当于二维中
                     * 的dp[i-1][j]，右边的prev相当于二维中的dp[i-1][j-1]*/
                    dp[j] = Math.min(Math.min(dp[j-1],dp[j]),prev)+1;
                }
                prev = tmp;
            }
        }
        return dp[n];
    }


    /*解法1：二维dp的写法*/
//    public int minDistance(String word1, String word2) {
//        int m = word1.length(),n = word2.length();
//        int[][] dp = new int[m + 1][n + 1];
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (i==0||j==0){
//                    dp[i][j] = i==0?j:i;
//                }else {
//                    char c1 = word1.charAt(i - 1);
//                    char c2 = word2.charAt(j - 1);
//                    if (c1==c2){
//                        dp[i][j] = dp[i-1][j-1];
//                    }else {
//                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
//                    }
//                }
//            }
//        }
//        return dp[m][n];
//    }
//
//
//    /*解法2：两行形式的dp*/
//    public int minDistance_2_1dim(String word1, String word2) {
//        int m = word1.length(),n = word2.length();
//        if (m<n) return minDistance_2_1dim(word2,word1);
//        int[] prev = new int[n + 1];
//        int[] cur = new int[n + 1];
//        for (int i = 0; i < n + 1; i++) {
//            prev[i] = i;
//        }
//
//        for (int i = 1; i <= m; i++) {
//            cur[0] = i;
//            for (int j = 1; j <= n; j++) {
//                char c1 = word1.charAt(i - 1);
//                char c2 = word2.charAt(j - 1);
//                if (c1==c2)
//                    cur[j] = prev[j-1];
//                else
//                    cur[j] = Math.min(Math.min(prev[j],cur[j-1]),prev[j-1]);
//            }
//            /**这里必须要把两个数组交换吗？？感觉有点不对劲*/
//            int[] tmp = cur;
//            cur = prev;
//            prev = tmp;
//        }
//        return cur[n];
//    }
//
//
//    /*解法3：一行DP+额外变量的写法*/
//    public int minDistance_1dim(String word1, String word2) {
//        int m = word1.length(), n = word2.length();
//        if (m < n) return minDistance_1dim(word2, word1);
//        int[] dp = new int[n + 1];
//
//        for (int i = 0; i <= n; i++) {
//            dp[i] = i;
//        }
//
//        for (int i = 1; i <= m; i++) {
//            int prev = dp[0];
//            dp[0] = 0;
//            for (int j = 1; j <= n; j++) {
//                int tmp = dp[j];
//                char c1 = word1.charAt(i - 1);
//                char c2 = word2.charAt(j - 1);
//                if (c1 == c2)
//                    dp[j] = prev;
//                else
//                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prev) + 1;
//                prev = tmp;
//            }
//        }
//        return dp[n];
//    }


    /*124. 二叉树中的最大路径和
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列
    中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    int resMaxPathSum;
    public int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        dfs2(root);
        return resMaxPathSum;
    }

    private int dfs2(TreeNode root) {
        if (root==null) return 0;
        int left = Math.max(dfs2(root.left),0);
        int right = Math.max(dfs2(root.right),0);
        resMaxPathSum = Math.max(resMaxPathSum,left+right+root.val);
        return root.val+Math.max(left,right);
    }



    /*1143. 最长公共子序列
    给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。*/
    /**2维的写法*/
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        //公共子序列第一行和第一列都是0，因此省略
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    /**两行数组的写法*/
    public int longestCommonSubsequence_2row(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence_2row(text2,text1);
        int[] prev = new int[n + 1];
        int[] cur = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    cur[j] = prev[j-1]+1; /**“prev”这个字符串等价于在二位写法中的“dp[i-1]”，“cur”这个字符串等价于二维写法中的“dp[i]”*/
                }else {
                    cur[j] = Math.max(cur[j-1],prev[j]);
                }
            }
            /**下面的必须要这么写吗？？有没有其他的写法
             这里虽然是把prev给了cur，看着好像是上一行的数据给了cur，但是没关系，因为从index=1位置计算，index=0永远是0，因此在更新
             cur时不会因为交换后的prev受到影响
             */
            int[] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        return prev[n];
    }


    public int longestCommonSubsequence_1dim(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence_1dim(text2,text1);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = 0;
            dp[0] = 0;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    dp[j] = prev;
                }else {
                    dp[j] = Math.max(dp[j-1],dp[j]);
                }
            }
        }
        return dp[n];
    }




    /*写法1：2维 的写法*/
//    public int longestCommonSubsequence(String text1, String text2) {
//        int m = text1.length(),n = text2.length();
//        int[][] dp = new int[m + 1][n + 1];
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                char c1 = text1.charAt(i - 1);
//                char c2 = text2.charAt(j - 1);
//                if (c1==c2){
//                    dp[i][j] = dp[i-1][j-1] +1;
//                }else {
//                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
//                }
//            }
//        }
//        return dp[m][n];
//    }
//
//    /*写法2：2行动规的写法*/
//    /**
//     【说明】
//            1. 两行DP的写法中，prev就看作是二维DP中的dp[i-1][..]，而cur看作是二维DP中的dp[i][..]。（简单点说，计算时代码中的
//        prev直接改成dp[i-1]、代码中的cur直接改写成dp[i]）比如，下面的代码中————
//                ①“cur[j] = prev[i-1] + 1;”就相当于“dp[i][j] = prev[i-1][j-1] + 1”；
//                ②“cur[j] = Math.max(cur[j-1],prev[j]);”就相当于“dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j])”
//                可以看出，改写后的表达式跟二维DP的表达式一模一样！！
//            2. 两行的DP中，由于习惯研究某一行的每一列，因此“脑补出短的元素在一行”。比如：如果text1短就让text1写在行的位置，这样
//        创建的dp长度就是text1.length+1；反之如果text2比较短，就让text2写在行的位置，此时创建的动规数组长度就是text2.lenth+1。
//        这样的话空间复杂度能降低到O(M,N)（其中M时text1的长度，N是text2的长度~~~）
//     */
//    public int longestCommonSubsequence_2_1dim(String text1, String text2) {
//        int m = text1.length(),n = text2.length();
//        if (m<n) return longestCommonSubsequence_2_1dim(text2,text1);
//        int[] prev = new int[n + 1]; //prev用于存储dp表上一行的信息
//        int[] cur = new int[n + 1]; //cur用于存储dp表现在研究的这一行的信息
//
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                char c1 = text1.charAt(i - 1);
//                char c2 = text2.charAt(j - 1);
//                if (c1==c2){
//                    cur[j] = prev[i-1] + 1;
//                }else {
//                    cur[j] = Math.max(cur[j-1],prev[j]);
//                }
//            }
//            int[] tmp = cur;
//            cur = prev;
//            prev = tmp;
//        }
//        return cur[n];
//    }
//
//
//    /*写法3：1维+额外变量的写法*/
//    /**
//     【说明】
//            1. 二维DP中如果一个位置(i,j)会依赖于左上角的三个位置————(i-1,j-1)、(i,j-1)、(i-1,j)，这种形式的DP是
//        不能简化成一行DP的！！！可以简化成“2行形式的DP”、“1行+额外变量的DP”
//     */
//    public int longestCommonSubsequence_1dim(String text1, String text2) {
//        int m = text1.length(),n = text2.length();
//        if (m<n) return longestCommonSubsequence_1dim(text2,text1);
//        int[] dp = new int[n + 1];
//        for (int i = 1; i <= m; i++) {
//            int prev = dp[0];
//            dp[0] = 0;
//            for (int j = 1; j <= n; j++) {
//                int tmp = dp[j];
//                char c1 = text1.charAt(i - 1);
//                char c2 = text2.charAt(j - 1);
//                if (c1==c2){
//                    dp[j] = prev+1;
//                }else {
//                    dp[j] = Math.max(dp[j-1],dp[j]);
//                }
//                prev = tmp;
//            }
//        }
//        return dp[n];
//    }



    /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
    "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过
    在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
//    public List<String> restoreIpAddresses(String s) {
//
//    }


    /*
    82. 删除排序链表中的重复元素 II
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return head;
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            int curVal = cur.next.val;
            if (cur.next.next.val == curVal){
                while (cur.next.next!=null&&cur.next.next.val==curVal){
                    cur.next.next = cur.next.next.next;
                }
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }


    /**
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     */

    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode mid = findPrevMid(head);
        ListNode midRel = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(midRel);
        return mergeTwo(left,right);
    }

    private ListNode findPrevMid(ListNode head) {
        ListNode slow=  head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeTwo(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left==null?right:left;
        return dummy.next;
    }


    /**下面的代码为什么是错误的.....
        错误的原因是，“排序链表”的题目中要求如果是偶数个节点，必须来到中间的第一个节点。然后记录下一个节点，最后将当前节点(即找到的中
     间节点)的next置为null
        但是下面方法的findMid就是错误的，这样的代码如果是偶数个节点会来到中间的下一个节点
     */
//    public ListNode sortList(ListNode head) {
//        if (head==null||head.next==null) return head;
//        ListNode mid = findMid(head);
//        ListNode start = mid.next;
//        mid.next = null;
//        ListNode left = sortList(head);
//        ListNode right = sortList(start);
//        return mergeTwo(left,right);
//    }
//
//    private ListNode mergeTwo(ListNode left, ListNode right) {
//        ListNode dummy = new ListNode(-1),cur = dummy;
//        while (left!=null&&right!=null){
//            if (left.val<right.val){
//                cur.next = left;
//                left = left.next;
//            }else {
//                cur.next = right;
//                right = right.next;
//            }
//            cur = cur.next;
//        }
//        cur.next = left==null?right:left;
//        return dummy.next;
//    }
//
//    private ListNode findMid(ListNode head) {
//        ListNode slow = head,fast = head;
//        while (fast!=null&&fast.next!=null){
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        return slow;
//    }


    /*322. 零钱兑换
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/

    /**
        这个题填充为-1行不行？？就尽量不要填充为-1了，统一填充为一个比amount更大的数
     下面的代码为什么是错误的？？？
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
        /**应该错误在这里。。。。。这里少了一句，需要将dp[0]重置为0*/
        for (int i = coins[0]; i <= amount; i++) {
            if (i%coins[0]==0) dp[i] = dp[i-coins[0]]+1;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j-coins[i]]+1,dp[j]);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }


    /*
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        HashMap<Character, Integer> window = new HashMap<>();
        int invalid = 0,left = 0,start = -1,len = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c).intValue()==need.get(c).intValue()){
                    invalid++;
                }
            }

            while (invalid==need.size()){
                if (i-left+1<len){
                    len = i-left+1;
                    start = left;
                }
                char tmp = s.charAt(left++);
                if (need.containsKey(tmp)){
                    window.put(tmp, window.get(tmp)-1);
                    if (window.get(tmp)<need.get(tmp)){
                        invalid--;
                    }
                }
            }
        }
        return start==-1?"":s.substring(start,start+len);
    }


    /**10.13写的是下面的代码，貌似是错误的。。。。错误应该是出现在“if (window.get(c)==need.get(c))”，map得到的值是Integer，判断相等不能用等于*/
//    public String minWindow(String s, String t) {
//        HashMap<Character, Integer> need = new HashMap<>();
//        for (int i = 0; i < t.length(); i++) {
//            char c = t.charAt(i);
//            need.put(c,need.getOrDefault(c,0)+1);
//        }
//
//        int valid = 0;
//        int start = -1,maxLen = Integer.MAX_VALUE;
//        int left = 0;
//        HashMap<Character, Integer> window = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (need.containsKey(c)){
//                window.put(c,window.getOrDefault(c,0)+1);
//                if (window.get(c)==need.get(c)){
//                    valid++;
//                }
//            }
//
//            while (valid==need.size()){
//                if (maxLen>i-left+1){
//                    maxLen = i-left+1;
//                    start = left;
//                }
//                char c1 = s.charAt(left);
//                if (window.containsKey(c1)){
//                    window.put(c1,window.get(c1)-1);
//                    if (window.get(c1)<need.get(c1)){
//                        valid--;
//                    }
//                }
//            }
//        }
//        return start==-1?"":s.substring(start,start+maxLen);
//    }

//    public String minWindow(String s, String t) {
//        if (s.length()==0||s.length()<t.length()) return "";
//        HashMap<Character, Integer> need = new HashMap<>();
//        for (char c:t.toCharArray()){
//            need.put(c,need.getOrDefault(c,0)+1);
//        }
//
//        HashMap<Character, Integer> map = new HashMap<>();
//        int left = 0,valid = 0;
//        int start = -1;
//        int len = Integer.MAX_VALUE;
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (need.containsKey(c)){
//                map.put(c,map.getOrDefault(c,0)+1);
//                if (map.get(c)==need.get(c)){ /**【注意】涉及到map.get的时候，如果是“==”比较，则必须使用“.intValue()”或者“.equals()”~~不要使用“==”,因为map中get得到的是Integer！！！！！！*/
//                    valid++;
//                }
//            }
//            while (valid==need.size()){
//                if (i-left+1<len){
//                    len = i-left+1;
//                    start = left;
//                }
//                char c1 = s.charAt(left++);
//                if (need.containsKey(c1)){
//                    map.put(c1,map.get(c1)-1);
//                    if (map.get(c1)<need.get(c1)){
//                        valid--;
//                    }
//                }
//            }
//        }
//        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
//    }

    /*151. 反转字符串中的单词
    给你一个字符串 s ，请你反转字符串中 单词 的顺序。
    单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
    返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
    注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。*/
//    public String reverseWords(String s) {
//
//    }


    /**chatgpt给出的代码如下*/
//    public String reverseWords(String s) {
//        String trim = s.trim();
//        String s1 = reverString(trim);
//        String[] s2 = s1.split("\\s+"); // ✅ 用正则去除多余空格
//        StringBuilder sb = new StringBuilder();
//        for (String str : s2) {
//            sb.append(reverString(str)).append(" ");
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        return sb.toString();
//    }
//
//    private String reverString(String s) {
//        return new StringBuilder(s).reverse().toString();
//    }
//
//    /**
//     split("\\s+") 会按“至少一个空白字符”去拆分字符串，并自动去掉多余的空白。
//         String s1 = "  hello   world  ";
//         String[] s2 = s1.split("\\s+");
//             拆出来结果：
//                 s2[0] = "hello"
//                 s2[1] = "world"
//     如果写 split(" ")：
//            " hello world ".split(" ")会得到：["", "", "hello", "", "", "world"] 里面有空字符串。
//     */
//    /*下面的代码是自己写的，可能会出错*/
//    public String reverseWords_(String s) {
//        String trim = s.trim();
//        String s1 = reverString1(trim);
//        String[] s2 = s1.split(" ");
//        StringBuilder sb = new StringBuilder();
//        for (String str:s2){
//            sb.append(reverString1(str));
//            sb.append(" ");
//        }
//        sb.deleteCharAt(sb.length()-1);
//        return sb.toString();
//    }
//
//    private String reverString1(String s) {
//        StringBuilder sb = new StringBuilder(s);
//        return sb.reverse().toString();
//    }
//


    /**
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     */


    /*129.求根到叶子节点数字之和
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    每条从根节点到叶节点的路径都代表一个数字：

    例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    计算从根节点到叶节点生成的 所有数字之和 。
    叶节点 是指没有子节点的节点。
    * */
            /**补充一下递归的写法*/
    public int sumNumbers(TreeNode root) {
        if(root==null) return 0;
        return sumNumbers(root,0);
    }

    private int sumNumbers(TreeNode root, int sum) {
        if (root==null) return 0;
        if (root.left==null&&root.right==null)
            return root.val+sum*10;
        return sumNumbers(root.left,sum*10+ root.val)+
                sumNumbers(root.right,sum*10+root.val);
    }


    /*39组合总和
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */
    List<List<Integer>> resCombinationSum;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        resCombinationSum = new LinkedList<>();
        combinationSum(candidates,target,0,new LinkedList<Integer>());
        return resCombinationSum;
    }

    private void combinationSum(int[] candidates, int target, int index, LinkedList<Integer> path) {
        if (target==0){
            resCombinationSum.add(new LinkedList<>(path));
        }
        if (target<0||index==candidates.length){
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            target -= candidates[i]; /**组合、子集问题需要注意，for循环内部研究的是index=i位置的元素*/
            combinationSum(candidates,target,i+1,path);
            path.removeLast();
            target += candidates[i];
        }
    }

    /**
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     */
    /*113. 路径总和 II
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。*/

    List<List<Integer>> resPathSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        resPathSum = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs1(root,targetSum,path);
        return resPathSum;
    }

    private void dfs1(TreeNode root, int targetSum, LinkedList<Integer> path) {
        if (root==null) return;
        if (root.left==null&&root.right==null&&root.val==targetSum){
            path.add(root.val);
            resPathSum.add(new LinkedList<>(path));
            return;
        }
        path.add(root.val);
        dfs1(root.left,targetSum-root.val,path);
        dfs1(root.right,targetSum-root.val,path);
        path.removeLast();
    }

    /**113题BFS的写法（强烈建议结合112题的BFS写法理解）。。。
        112题仅仅是判断有没有那样的路径，遍历时需要的信息：节点、从根节点到这个节点的路径和。因此使用到
     两个队列————一个存储节点，一个存储从根节点到这个节点的路径和。。
            疑问1：为什么使用队列？？因为是BFS的写法，BFS需要使用队列；
            疑问2：为什么使用两个队列，因为需要两个信息（其实这里使用Pair这个类型也可以，但是为了逻辑清
        晰，二叉树中的BFS通常将不同的信息存储到不同的队列）
        113题不仅仅需要得到是不是有这样的路径，还要将所有满足的路径返回。此时我们需要的信息：节点（判断是
     不是叶子节点以及得到它的左右孩子，BFS遍历必须的一个元素）、从根节点到该节点的路径和信息（判断是不是符
     合题目要求的路径）、从根节点到该节点的路径（如果满足条件时需要打印）。因此此时需要使用3个队列分别存储
     这些不同的信息
      */
    public List<List<Integer>> pathSum_diedai(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;

        LinkedList<TreeNode> queueNodes = new LinkedList<>();
        LinkedList<Integer> queueVal = new LinkedList<>();
        LinkedList<List<Integer>> queuePath = new LinkedList<>();
        queueNodes.offer(root);
        queueVal.offer(root.val);
        queuePath.offer(new LinkedList<>(List.of(root.val)));
        while (!queueNodes.isEmpty()){
            TreeNode curNode = queueNodes.poll();
            Integer curVal = queueVal.poll();
            List<Integer> curPath = queuePath.poll();
            if (curNode.left==null&&curNode.right==null&&curVal==targetSum){
                res.add(curPath);
            }

            if (curNode.left!=null){
                queueNodes.offer(curNode.left);
                queueVal.offer(curVal+curNode.left.val);
                LinkedList<Integer> newPath = new LinkedList<>(curPath);
                newPath.add(curNode.left.val);
                queuePath.offer(newPath);
            }

            if (curNode.right != null) {
                queueNodes.offer(curNode.right);
                List<Integer> newPath = new LinkedList<>(curPath);
                newPath.add(curNode.right.val);
                queuePath.offer(newPath);
                queueVal.offer(curVal + curNode.right.val);
            }
        }

        return res;
    }

    /**同理，我们只要任何时刻能拿到节点、节点对应的路径和、从根到节点的路径，且这三个信息是对应的，这就OK。
     因此还可以写出下面的dfs的迭代版本。
        1. 113题bfs的迭代版本，依然可以结合112题的bfs迭代版本理解。
        2. 与bfs的代码比起来，几乎是一摸一样的，唯一不同的就是使用栈还是队列 以及 左右孩子入队的顺序。由于
     下面的dfs是先序遍历的迭代，因此入栈的顺序是“右孩子————>再左孩子”
     * */
    public List<List<Integer>> pathSum_diedaidfs(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;
        Stack<TreeNode> stackNode = new Stack<>();
        Stack<Integer> stackVal = new Stack<>();
        Stack<List<Integer>> stackPath = new Stack<>();
        stackNode.push(root);
        stackVal.push(root.val);
        stackPath.push(new LinkedList<>(List.of(root.val)));
        while (!stackNode.isEmpty()){
            TreeNode curNode = stackNode.pop();
            Integer curVal = stackVal.pop();
            List<Integer> curPath = stackPath.pop();
            if (curNode.left==null&&curNode.right==null&&curVal==targetSum){
                res.add(new LinkedList<>(curPath));
            }

            if (curNode.right!=null){
                stackNode.push(curNode.right);
                stackVal.push(curVal+curNode.right.val);
                LinkedList<Integer> newPath = new LinkedList<>(curPath);
                newPath.add(curNode.right.val);
                stackPath.push(newPath);
            }

            if (curNode.left!=null){
                stackNode.push(curNode.left);
                stackVal.push(curVal+curNode.left.val);
                LinkedList<Integer> newPath = new LinkedList<>(curPath);
                newPath.add(curNode.left.val);
                stackPath.push(newPath);
            }
        }
        return res;
    }


    /**
     下面的代码是错误的。。。。
     */
//    List<List<Integer>> resPathSum;
//    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//        resPathSum = new LinkedList<>();
//        LinkedList<Integer> path = new LinkedList<>();
//        pathSum(root,targetSum,path);
//        return resPathSum;
//    }
//
//    private void pathSum(TreeNode root, int targetSum, LinkedList<Integer> path) {
//        if (root==null) return;
//        if (root.left==null&&root.right==null&&root.val==targetSum){
//            resPathSum.add(new LinkedList<>(path));
//            return;
//        }
//        path.add(root.val);
//        pathSum(root.left,targetSum-root.val,path);
//        pathSum(root.right,targetSum-root.val,path);
//        path.removeLast();
//    }


    /*112. 路径总和
给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。

叶子节点 是指没有子节点的节点。
     */
    /**递归的写法*/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null) return false;
        if (root.left==null&&root.right==null&&root.val==targetSum) return true;
        return hasPathSum(root.left,targetSum-root.val)||
                hasPathSum(root.right,targetSum-root.val);
    }

    /**层序遍历的写法，使用队列*/
    public boolean hasPathSum_cengxu(TreeNode root, int targetSum) {
        if (root==null) return false;
        LinkedList<TreeNode> queueNodes = new LinkedList<>();
        LinkedList<Integer> queueVal = new LinkedList<>();
        queueNodes.offer(root);
        queueVal.offer(root.val);
        while (!queueNodes.isEmpty()){
            TreeNode curNode = queueNodes.poll();
            Integer curVal = queueVal.poll();

            if (curNode.left==null&&curNode.right==null&&curVal==targetSum){
                return true;
            }

            if (curNode.left!=null){
                queueNodes.offer(curNode.left);
                queueVal.offer(curVal+curNode.left.val);
            }

            if (curNode.right!=null){
                queueNodes.offer(curNode.right);
                queueVal.offer(curVal+curNode.right.val);
            }
        }
        return false;
    }

    /*468. 验证IP地址
    给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
    有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
    一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
    1 <= xi.length <= 4
    xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
    在 xi 中允许前导零。
    例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
     */
    /**
     *什么是合法的IP地址？
     *     IPV4（三个条件缺一不可）：必须是4段；每一段不能有前导零；每一段不能超过255；
     *     IPV6：
     *【完整步骤】
     *
     */
//    public String validIPAddress(String queryIP) {
//
//    }



}


//下面的这段话补充在”leecode_Debug._hot100._07binarytree.maxPathSum“124题注释的下面
/**
 任何一个节点有如下的2种可能————
 1️⃣ 作为路径“中间点”（此时需要判断是否更新结果）
 可以连接它的左子树路径和右子树路径；此时的路径形态：left → root → right；这时的路径和为：leftGain + root.val + rightGain。
 2️⃣ 作为“上行路径”的一部分（此时需要返回给父节点信息————以它为根的这段路程的最大路径和，此时左右子树只能二选一）
 只能选择一边（因为路径不能分叉）；返回给父节点的最大贡献是：root.val + max(leftGain, rightGain)
 因此到一个节点的时候，需要根据左右子树的情况来更新”全局的最大路径和“，同时任何一个节点需要返回给父节点以它为根的最大路径和信息。这两个值
 是不一样的，尤其是题目要求返回的是”最大路径和“信息，但是任一节点需要返回的是”以它为根左右子树选其一“的最大路径和，这种差异就以为着必须使
 用额外的方法来完成遍历
 */
/**【补充说明】
 1. 这个题实际上使用的是后序遍历DFS，为什么必须用后序遍历，使用先序/中序不可以吗？
 ✅ 必须用后序遍历，因为：
 只有当我们「先拿到左右子树的最大贡献值」之后，才能计算以当前节点为“最高点”的最大路径和。
 换句话说：当前节点的计算依赖于左右子树的结果，这正是「后序遍历」的定义：先处理子树 → 再处理自己。
 */


//大总结。关于先序遍历、后序遍历、中序遍历的思考。。大总结的代码也尽量拷贝到_07binarytree.java
/**DFS三种遍历的核心对比
 🌲 一、DFS 三种形式的核心区别（直观对比）
 遍历方式	访问顺序	关键点
 前序遍历 (preorder)	先访问当前节点 → 再访问左右子树	当前节点在子树之前，用于“前向传播”信息
 中序遍历 (inorder)	左子树 → 当前节点 → 右子树	主要用于「二叉搜索树」的有序性问题
 后序遍历 (postorder)	先访问左右子树 → 最后访问当前节点	当前节点依赖子树结果，用于“自底向上”汇总信息
 */
/**1. 中序遍历
    （1） 👉 中序遍历（inorder traversal）在普通树题中确实很少单独使用（对「普通二叉树」题来说，中序遍历几乎用不到；）
    但在「特定类型的树」——尤其是 二叉搜索树（BST） 里，它的作用极其关键。
    （2） 常见的题目比如：
         题号	题目	为什么用中序遍历
         94	二叉树的中序遍历	最基本题，输出访问顺序
         98	验证二叉搜索树	中序遍历结果应严格递增
         99	恢复二叉搜索树	找到中序序列中两个交换的节点
         230	BST 第 K 小元素	中序遍历第 k 次访问的节点即答案
         501	BST 众数	中序遍历可顺序统计频率
         530	BST 最小差值	相邻节点差值最小，中序遍历自然相邻
         700 / 701 / 450	BST 查找 / 插入 / 删除节点	用中序结构辅助定位区间
 */
/**2. 先序遍历
        （1）使用场景：当当前节点的决策依赖于「从上往下传递的信息」时，比如路径、深度、状态等，需要“带着信息下去”的题。
        （2）特征总结：当前节点独立决策，不依赖子树返回值 或者 需要把父节点的信息传下去。。典型的代码结构如下
                 void dfs(TreeNode root, int pathSum) {
                     if (root == null) return;
                     // 先处理当前节点
                     pathSum += root.val;
                     // 再往下递归
                     dfs(root.left, pathSum);
                     dfs(root.right, pathSum);
                 }
        （3）必须使用先序遍历的题目比如：
             题号	题目	原因
             257	二叉树的所有路径	当前路径信息从上到下延伸，遍历时即记录路径
             112	路径总和（是否存在）	每访问一个节点，就更新剩余目标和
             113	路径总和 II	同上，只是要保存路径
             129	求根到叶子节点数字之和	“从根到叶”形成数字，信息从上往下传递
             100/101	相同树、对称树	比较结构，从根开始递归
 */
/**3. 后序遍历
        （1）使用场景：当当前节点的计算依赖于左右子树的结果时，必须等左右子树都算完，再算当前节点。
        （2）特征总结：当前节点依赖子树结果；“自底向上”的信息汇总；通常在「返回值」中带有子树信息；。。。典型的代码结构如下
                 int dfs(TreeNode root) {
                     if (root == null) return 0;
                     int left = dfs(root.left);
                     int right = dfs(root.right);
                     return Math.max(left, right) + 1; // 依赖左右
                 }
        （3）必须使用后序遍历的题目比如：
             题号	题目	原因
             104	二叉树的最大深度	当前节点深度 = max(左,右)+1
             110	平衡二叉树	依赖左右子树高度差
             543	二叉树直径	当前节点的直径 = 左高+右高
             124	二叉树最大路径和	当前节点最大路径依赖左右子树最大贡献
             337	打家劫舍 III	当前节点能偷的最大值取决于子节点偷/不偷
             226	翻转二叉树	先翻转左右，再翻转根（后序自然）
 */
/**4. 什么时候”先序遍历“和”后序遍历“都可以使用
        （1）使用场景：当题目是“路径搜索”类（回溯、组合、排列、子集），你可以在进入子节点时做操作，也可以在退出子节点时做操作。
            两种方式都可以，只是处理逻辑稍不同。
        （2）典型的题目比如：
             题号	题目	特点
             46	全排列	前序添加元素、后序回溯都可
             78	子集	前序/后序都能在不同位置收集结果
             39/40	组合总和	前序加元素，后序删除元素（回溯模板）
             17	电话号码的字母组合	同上，回溯树结构
 */
/**5. 二叉树4种遍历顺序的使用场景 以及 可替换性 分类
         题目特征	推荐遍历	可替代遍历	说明
         ✅ 路径型（路径和 / 所有路径 / 路径回溯）	前序	（部分 BFS）	需要边走边维护路径
         ✅ 统计型（最大/最小深度、每层统计）	层序 / 后序	二者互通	一层层统计 or 自底向上
         ✅ 构建型（翻转、合并、连接）	前序 / 层序	二者互通	从上到下传递状态
         ✅ DP型（树上最值 / 打家劫舍）	后序	无	必须先计算子树结果
         ✅ BST型（有序特性）	中序	无	依赖中序有序性
         ✅ 搜索型（剪枝、匹配）	前序	（部分 BFS）	需控制递归或剪枝时机
【口诀】
     前序：传递状态（路径/构建）
     后序：汇总计算（DP/深度）
     中序：顺序输出（BST）
     层序：分层观察（统计/构建）
 */
/**6. 关于二叉树几个典型题目的说明
 🧩 举几个代表性对比
     题目	        可用遍历	            说明
     104. 最大深度	后序 ✅ / 层序 ✅	后序自底向上，层序数层数
     111. 最小深度	层序 ✅ / 后序 ✅	层序最快找到叶子，后序递归计算
     112. 路径总和	前序 ✅ / 层序 ✅	前序传递和，层序带状态遍历
     113. 路径总和 II	前序 ✅	        路径需回溯，层序不方便
     124. 最大路径和	后序 ✅	            必须自底向上汇总子树最大贡献
     226. 翻转二叉树	前序 ✅ / 层序 ✅	同时交换子树，层序迭代也行
     98. 验证 BST	中序 ✅	            需要中序单调性判断
     543. 直径	    后序 ✅	            需要汇总左右子树深度
 */