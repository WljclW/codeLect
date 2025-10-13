package topcodeReview;

import leecode_Debug.BTree.TreeNode;
import leecode_Debug.top100.ListNode;

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
 *   err:5、、、、、、、、、
 */
public class All1_5_review {
    /*215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */

    /*
    手撕快排
     */
    public void quickSort(int[] arr,int left,int right) {
        if (left>right) return;

        int pivotIndex = left + new Random().nextInt(0, right - left + 1);
        swap1(arr,pivotIndex,right);

        pivotIndex = partion(arr,left,right);
        quickSort(arr,left,pivotIndex-1);
        quickSort(arr,pivotIndex+1,right);
    }

    private int partion(int[] arr, int left, int right) {
        /**归并排序的时候需要借助一个临时数组tmp，因为涉及到两个数组的合并！！！
          快排的时候就不需要了，根据大小关系和索引完成交换！*/
        int cur = left;
        for (int i = left; i < right; i++) {
            if (arr[i]<arr[left]){
                swap1(arr,cur++,i);
            }
        }
        swap1(arr,cur,right);
        return cur;
    }

    private void swap1(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
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
//    public void reorderList(ListNode head) {
//
//    }

    /*72.编辑距离
    * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
    你可以对一个单词进行如下三种操作：
    插入一个字符
    删除一个字符
    替换一个字符*/
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
//    public int maxPathSum(TreeNode root) {
//
//    }



    /*1143. 最长公共子序列
    给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。*/
    /*写法1：2维 的写法*/
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[m][n];
    }

    /*写法2：2行动规的写法*/
    /**
     【说明】
            1. 两行DP的写法中，prev就看作是二维DP中的dp[i-1][..]，而cur看作是二维DP中的dp[i][..]。（简单点说，计算时代码中的
        prev直接改成dp[i-1]、代码中的cur直接改写成dp[i]）比如，下面的代码中————
                ①“cur[j] = prev[i-1] + 1;”就相当于“dp[i][j] = prev[i-1][j-1] + 1”；
                ②“cur[j] = Math.max(cur[j-1],prev[j]);”就相当于“dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j])”
                可以看出，改写后的表达式跟二维DP的表达式一模一样！！
            2. 两行的DP中，由于习惯研究某一行的每一列，因此“脑补出短的元素在一行”。比如：如果text1短就让text1写在行的位置，这样
        创建的dp长度就是text1.length+1；反之如果text2比较短，就让text2写在行的位置，此时创建的动规数组长度就是text2.lenth+1。
        这样的话空间复杂度能降低到O(M,N)（其中M时text1的长度，N是text2的长度~~~）
     */
    public int longestCommonSubsequence_2_1dim(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence_2_1dim(text2,text1);
        int[] prev = new int[n + 1]; //prev用于存储dp表上一行的信息
        int[] cur = new int[n + 1]; //cur用于存储dp表现在研究的这一行的信息

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    cur[j] = prev[i-1] + 1;
                }else {
                    cur[j] = Math.max(cur[j-1],prev[j]);
                }
            }
            int[] tmp = cur;
            cur = prev;
            prev = tmp;
        }
        return cur[n];
    }


    /*写法3：1维+额外变量的写法*/
    /**
     【说明】
            1. 二维DP中如果一个位置(i,j)会依赖于左上角的三个位置————(i-1,j-1)、(i,j-1)、(i-1,j)，这种形式的DP是
        不能简化成一行DP的！！！可以简化成“2行形式的DP”、“1行+额外变量的DP”
     */
    public int longestCommonSubsequence_1dim(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence_1dim(text2,text1);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            dp[0] = 0;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    dp[j] = prev+1;
                }else {
                    dp[j] = Math.max(dp[j-1],dp[j]);
                }
                prev = tmp;
            }
        }
        return dp[n];
    }



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
    /**下面的代码为什么是错误的*/
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
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
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
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int valid = 0;
        int start = -1,maxLen = Integer.MAX_VALUE;
        int left = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c)==need.get(c)){
                    valid++;
                }
            }

            while (valid==need.size()){
                if (maxLen>i-left+1){
                    maxLen = i-left+1;
                    start = left;
                }
                char c1 = s.charAt(left);
                if (window.containsKey(c1)){
                    window.put(c1,window.get(c1)-1);
                    if (window.get(c1)<need.get(c1)){
                        valid--;
                    }
                }
            }
        }
        return start==-1?"":s.substring(start,start+maxLen);
    }

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
//    public int sumNumbers(TreeNode root) {
//
//    }




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


//    /**下面的写法对吗？？为什么下面的写法是错误的？？？？？下面的改正需要验证一下*/
//    public int maxProduct(int[] nums) {
//        int res = Integer.MIN_VALUE;
//        int preMin = nums[0],preMax = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            res = Math.max(res,preMax);
//            int curMin = Math.min(Math.min(preMin*nums[i],preMax*nums[i]),nums[i]);
//            int curMax = Math.max(Math.max(preMin*nums[i],preMax*nums[i]),nums[i]);
//            preMin = curMin;
//            preMax = curMax;
//        }
//        res = Math.max(res,preMax); /**err：错误的原因在这里，如果没有这一句，会导致最后一轮的循环不会更新，就可能丢失最大值*/
//        return res;
//    }
//    /**下面的写法呢，对吗？？*/
//    public int maxProduct1(int[] nums) {
//        int res = nums[0];
//        int preMin = nums[0],preMax = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            int curMin = Math.min(Math.min(preMin*nums[i],preMax*nums[i]),nums[i]);
//            int curMax = Math.max(Math.max(preMin*nums[i],preMax*nums[i]),nums[i]);
//            preMin = curMin;
//            preMax = curMax;
//            res = Math.max(res,preMax);
//        }
//        return res;
//    }


    /*112. 路径总和
给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。

叶子节点 是指没有子节点的节点。
     */
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//
//    }

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
