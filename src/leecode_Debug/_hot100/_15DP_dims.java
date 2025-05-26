package leecode_Debug._hot100;

public class _15DP_dims {
    /*62.
    * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标
    * 记为 “Finish” ）。
    问总共有多少条不同的路径？*/
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for (int i=0;i<n;i++){ //第一行的任何位置都只有一条路
            res[0][i] = 1;
        }
        for (int j=0;j<m;j++){ //第一列的任何位置都只有一条路
            res[j][0] = 1;
        }
        for (int i=1;i<n;i++)
            for (int j=1;j<m;j++){
                //其他位置的路径数=上面位置的路径数 + 左边位置的路径数
                res[j][i] = res[j-1][i]+res[j][i-1];
            }
        return res[m-1][n-1];
    }

    /*64.
    * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路
    * 径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。*/
    public int minPathSum(int[][] grid) {
        int res = Integer.MAX_VALUE;
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1;i<grid.length;i++){ //到第一行任何一个位置的路径和，就是第一行之前数的累加和。【因为第一行只能从0，0位置横着到达】
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for (int j=1;j<grid[0].length;j++){ //第一列也是一样的道理
            dp[0][j] = dp[0][j-1]+grid[0][j];
        }
        for (int i=1;i<grid.length;i++)
            for (int j=1;j<grid[0].length;j++){
                //其他任何位置的路径和最小值 = min（左边位置的最小路径和，上边位置的最小路径和）+ 当前位置的值
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        return dp[grid.length-1][grid[0].length-1];
    }


    /*5.
    * 给你一个字符串 s，找到 s 中最长的 回文 子串。*/
//    public String longestPalindrome(String s) {
//
//    }


    /*1143.
    给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果
    不存在 公共子序列 ，返回 0 。
    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺
    序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
    * */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        int[][] dp = new int[m+1][n+1];
        /*对于初始值第一行的dp[0][i]和第一列的dp[i][0]，公共子序列都是0，数组元素
        的默认值就是零值，因此省略base case的初始化*/
        for (int i=1;i<=m;i++){
            char c = text1.charAt(i-1);
            for (int j=1;j<=n;j++){
                if (text2.charAt(j-1)==c){
                    /*
                    如果当前位置的字符相等，也可以写成(结果是一样的)：
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]),dp[i - 1][j - 1] + 1);
                    * */
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    /*
                    如果当前字符不一样，dp[i][j]的值取决于dp[i-1][j]和dp[i][j-1]的最大值。
                    do[i][j]：第一个字符串的前i个字符和第二个字符串的前j个字符的最长公共序列
                    * */
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    /*72.编辑距离
    * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
    你可以对一个单词进行如下三种操作：
    插入一个字符
    删除一个字符
    替换一个字符*/
    /**
     * 【关键】定义dp[i][j]为第一个字符串前i个字符 和 第二个字符串前j个字符 的编辑距离。
     *      则任意的dp[i][j]可以通过dp[i-1][j]、dp[i][j-1]、dp[i-1][j-1]表示。
     *      dp[i-1][j]此时就表示第一个子串的前i-1个字符匹配第二个串的前j个字符。因此暗示这个分支我们需要在第二个字符串
     *  添加第一个串的第i个字符；同理也可以理解为删除第一个串的第i个字符。
     *      dp[i][j-1]此时就表示第一个子串的前i个字符匹配第二个串的前j-1个字符。因此暗示这个分支我们需要在第一个字符串
     *  添加第二个串的第j个字符；同理也可以理解为删除第二个串的第j个字符。
     *      dp[i-1][j-1]此时就表示第一个串的前i-1个字符匹配第二个字符串的前j-1个字符。因此暗示这个分支我们需要做的是替
     *  换第一个串的i字符与第二个串的j字符相同；同时也可以理解为替换第二个串的j字符与第一个串的i字符相同。
     * */
    /**
     * 这个题看官方解析就行
     * 【解题关键】每一个普遍位置dp[i][j]可以由前面的位置推导过来
     *      dp[i][j-1]+1：相当于删除第二个串的第j个字符 或者 给第一个串第i个字符位置添加第二个串的第j个字符
     *      dp[i-1][j]+1：相当于删除第一个串的第i个字符 或者 给第二个串第j个字符位置添加第一个串的第i个字符
     *      dp[i-1][j-1]+1：相当于修改第一个串的第i个字符 或者 修改第二个串的第j+1个字符
     * 【补充】
     *      1. 这个题的状态转移和“最长公共子序列”的状态转移有异曲同工，但是细节不同。一个求最长的公共子序列，
     *  一个求最小的操作数
     * */
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        if (m==0||n==0) return m==0?n:m; /**err：特殊情况的考虑*/
        /**易错点：只要数组的长度声明为n，则for循环中就使用"i<n"。比如这个题就尽量使用"i<m+1"，这样的话不会漏*/
        int[][] dp = new int[m+1][n+1];
        /*base case的考虑*/
        for (int i=0;i<=m;i++){ /**err：由于数组的范围是m+1，因此要带等号*/
            dp[i][0] = i;
        }
        for (int i=0;i<=n;i++){
            dp[0][i] = i;
        }
        /*普遍位置的考虑*/
        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                int tmp = 0;
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    tmp = dp[i-1][j-1];
                }else
                    tmp = dp[i-1][j-1]+1;
                dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1])+1,tmp);
            }
        }
        return dp[m][n];
    }
}
