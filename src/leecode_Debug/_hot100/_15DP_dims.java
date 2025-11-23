package leecode_Debug._hot100;

import java.util.Arrays;

/**
 * @author: Zhou
 * @date: 2025/6/15 19:59
 *
 * 【说明】
 *      1. 涉及到子串、子序列的dp时，二维数组常常会在串长度的基础上多1，便于初始化和推导。
 *      2. 涉及到背包问题时，背包的容量维度通常是从0到target，因此实际的长度时target+1
 *      3. 不同路径、最小路径和问题中，dp数组的维度跟格子的大小一致（不用像串问题那样
 * dp数组比串的长度多一）。。否则不能好的初始化！！思考这个问题
 */
public class _15DP_dims {
    /*62.
    * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标
    * 记为 “Finish” ）。
    问总共有多少条不同的路径？*/
    /*解法1：二维的写法*/
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        //可以将第一行、第一列的初始化挪到双层for循环内————只要i或者j为0，就将dp[i][j]赋值为0
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

    /*解法2：一维的写法*/
    public int uniquePaths1(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }

    /*64.
    * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路
    * 径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。*/
    /*解法1：二维下的动态规划*/
    public int minPathSum(int[][] grid) {
        /*step1：变量的初始化*/
        int res = Integer.MAX_VALUE;
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        /*step2：第一行和第一列的初始化*/
        for (int i = 1; i < grid.length; i++) { //到第一列任何一个位置的路径和，就是第一行之前数的累加和。【因为第一行只能从0，0位置横着到达】
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j++) { //第一行也是一样的道理
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        /*step3：研究其他的每一个位置*/
        for (int i = 1; i < grid.length; i++)
            for (int j = 1; j < grid[0].length; j++) {
                //其他任何位置的路径和最小值 = min（左边位置的最小路径和，上边位置的最小路径和）+ 当前位置的值
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        return dp[grid.length - 1][grid[0].length - 1];
    }


    /*解法2：一维的解法。。。官方第一条评论给的一维解法代码更简*/
    public int minPathSum_1dim(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1]+grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j==0) dp[j] += grid[i][0];
                else
                    dp[j] = Math.min(dp[j-1],dp[j])+grid[i][j];
            }
        }
        return dp[n-1];
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
        /*step1：对于初始值第一行的dp[0][i]和第一列的dp[i][0]，公共子序列都是0，数组元素
        的默认值就是零值，因此这个题可以省略base case的初始化*/
        /*step2：针对每一个位置分别研究。。。根据i-1和j-1位置的字符是否相等来决定dp[i][j]*/
        for (int i=1;i<=m;i++){ /**err：二维dp很多都需要多一个长度，因此i和j是能娶到m和n的。如果忘了等于，结果永远是0，因为那个位置的值一直是默认值*/
            char c = text1.charAt(i-1);
            for (int j=1;j<=n;j++){
                if (text2.charAt(j-1)==c){
                    /*情况1：
                    如果当前位置的字符相等，也可以写成(结果是一样的)：
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]),dp[i - 1][j - 1] + 1);
                    * */
                    dp[i][j] = dp[i-1][j-1]+1; /**【说明】简化的写法，其实是所有做法中公共长度的最大值*/
                }else {
                    /*情况2：
                    如果当前字符不一样，dp[i][j]的值取决于dp[i-1][j]和dp[i][j-1]的最大值。
                    dp[i][j]：第一个字符串的前i个字符和第二个字符串的前j个字符的最长公共序列
                    * */
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n]; /*这里直接返回右下角的元素值就可以。。当然在每一步求出来dp元素值后使用res记录答案取最大值也可以*/
    }

    /**空间优化：两行数组的写法
     【说明】
     1. 两行DP的写法中，prev就看作是二维DP中的dp[i-1][..]，而cur看作是二维DP中的dp[i][..]。（简单点说，计算时代码中的
     prev直接改成dp[i-1]、代码中的cur直接改写成dp[i]）比如，下面的代码中————
         ①“cur[j] = prev[j-1] + 1;”就相当于“dp[i][j] = prev[i-1][j-1] + 1”；
         ②“cur[j] = Math.max(cur[j-1],prev[j]);”就相当于“dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j])”
     可以看出，改写后的表达式跟二维DP的表达式一模一样！！
     2. 两行的DP中，由于习惯研究某一行的每一列，因此“脑补出短的元素在一行”。比如：如果text1短就让text1写在行的位置，这样
     创建的dp长度就是text1.length+1；反之如果text2比较短，就让text2写在行的位置，此时创建的动规数组长度就是text2.lenth+1。
     这样的话空间复杂度能降低到O(M,N)（其中M时text1的长度，N是text2的长度~~~）
     */
    public int longestCommonSubsequence_2row(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence_2row(text2,text1);
        int[] prev = new int[n + 1]; //prev用于存储dp表上一行的信息
        int[] cur = new int[n + 1];  //cur用于存储dp表现在研究的这一行的信息
        for (int i = 1; i <= m; i++) {
            /**这个题由于不会发生值的覆盖问题，因此虽然cur在新的一轮会赋值为prev，这里不涉及cur的重置就OK，
             有的题可能在新的一轮必须对cur进行状态重置，比如“最长回文子序列”
             */
            for (int j = 1; j <= n; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    cur[j] = prev[j-1]+1; /**“prev”这个字符串等价于在二维写法中的“dp[i-1]”，“cur”这个字符串等价于二维写法中的“dp[i]”*/
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

    /**空间优化：使用一行数组*/
    /**
     【说明】
            1. 二维DP中如果一个位置(i,j)会依赖于左上角的三个位置————(i-1,j-1)、(i,j-1)、(i-1,j)，这种形式的DP是
        不能简化成一行DP的！！！可以简化成“2行形式的DP”、“1行+额外变量的DP”
     *【总结】代码中的①②③和72题一维优化的形式是一样的。根本原因在于：1143和72题中二维dp任何一个位
     *    置(i,j)需要依赖到位置(i-1,j)、(i,j-1)、(i-1,j-1)，而优化到一维版本的时候(i-1,j-1)位置
     *    的值必须使用额外的变量来记录原始值，否则原始值就被污染了
     *【代码中的①②③的详细解释】
     *   ①记录dp[0]的值，1143题dp[0]固定是0————dp[0]等价于二维中的第i行第0列，即长度为i的text1和
     *长度为0的text2子串公共子序列必然是0。。。。
     *    区别于72题。在72的1行数组动规中，这里会更新“dp[0]=i”，由于这个题是计算公共子序列，且dp[0]
     *表示第二个串是空串，因此dp[0]必然是0，数组的默认值以及初始值就是0，因此省略此步骤
     *   ②if-else块会更新dp某位置，因此计算之前先记录dp[i]
     *   ③dp[i]流程结束前将tmp更新到prev
     */
    public int longestCommonSubsequence_1dim(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n) return longestCommonSubsequence_1dim(text2,text1);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = 0; //①、对应 dp[i-1][j-1]
            dp[0] = 0; /**对比于72题，这一步可以省略*/
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j]; //②、暂存 dp[i-1][j]，因为它马上要被覆盖
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1==c2){
                    dp[j] = prev+1;
                }else {
                    dp[j] = Math.max(dp[j-1],dp[j]);
                }
                prev = tmp; //③、更新 prev 为 dp[i-1][j]。。因为下一轮要计算dp[i][j+1]，这样prev还是它的左上角位置
            }
        }
        return dp[n];
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
     *      dp[i][j-1]+1：相当于删除第二个串的第j个字符 或者 给第一个串第i个字符位置后添加第二个串的第j个字符
     *      dp[i-1][j]+1：相当于删除第一个串的第i个字符 或者 给第二个串第j个字符位置后添加第一个串的第i个字符
     *      dp[i-1][j-1]+1：相当于修改第一个串的第i个字符 或者 修改第二个串的第j+1个字符
     *      综上：dp[i][j-1]和dp[i-1][j]就集结了添加和删除的操作方式；dp[i-1][j-1]代表着修改的操作方式
     *      [注意]：上面表述中“第j个字符”实际上就是“.charAt(j-1)”————即index=j-1的字符
     * 【补充】
     *      1. 这个题的状态转移和“最长公共子序列”的状态转移有异曲同工，但是细节不同。一个求最长的公共子序列，
     *  一个求最小的操作数
     * */
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        if (m==0||n==0) return m==0?n:m; /**err：特殊情况的考虑*/
        /**易错点：只要数组的长度声明为n，则for循环中就使用"i<n"。比如这个题就尽量使用"i<m+1"，这样的话不会漏*/
        int[][] dp = new int[m+1][n+1];
        /*dp数组的初始化*/
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
                    tmp = dp[i-1][j-1]+1; //当前位置不相等，需要多一次操作
                /**三种操作方式：选出 操作数最少 的那种方式*/
                dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1])+1,tmp);
            }
        }
        return dp[m][n];
    }

    /*编辑距离另外的写法，简化的地方：
    *   1. dp的初始化放在了for循环中，避免了专门的初始化
    *   2. word1的“i-1”位置和word2的“j-1”位置字符相等时，dp[i][j]= dp[i-1][j-1]。简化了取最小
    * 值的过程*/
    public int minDistance1(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= n; j++) {
                if (i==0||j==0){ //第一行和第一列的情况
                    dp[i][j] = (i==0)?j:i;
                    continue;
                }
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    /**其实等价于三种情况的最小值，即：Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j])+1)..
                     * 和else情况的区别就是当前位置的字符相同，所以dp[i-1][j-1]就行，不用再+1*/
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        return dp[m][n];
    }

    /**两行数组的滚动优化版本
     同理1143题，两行数组滚动更新中“prev”就等同于二维dp中的“dp[i-1]”，“cur”等同于二维dp中的“dp[i]
     */
    public int minDistance_2row(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m < n) return minDistance(word2, word1); // 优化空间使用

        int[] prev = new int[n + 1]; // 上一行
        int[] cur = new int[n + 1];  // 当前行

        // 初始化第一行：把 word2 的前 j 个字符变成空串需要 j 次删除
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        // 遍历 word1 的每个字符
        for (int i = 1; i <= m; i++) {
            cur[0] = i; // 把 word1 前 i 个字符变成空串需要 i 次删除
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    cur[j] = prev[j - 1]; // 字符相同，不需要操作
                } else {
                    cur[j] = Math.min(Math.min(prev[j], cur[j - 1]), prev[j - 1]) + 1;
                    // 三种操作：删除（prev[j]）、插入（cur[j-1]）、替换（prev[j-1]）
                }
            }
            // 滚动数组：交换引用，而不是复制
            int[] tmp = prev;
            prev = cur;
            cur = tmp;
        }

        return prev[n]; // 最后一行结果
    }

    /**一行数组+局部变量 空间优化的实现————
     *      复杂度分析：时间复杂度：O(m·n)（还是要遍历整个表）；空间复杂度：O(min(m, n))（只
     *  保留一行）
     *【解释】根据二维dp可以发现，每一个位置依赖于它的”左上角的三个位置“，这样的局面有问题！
     *     问题：dp[i][j]依赖于dp[i-1][j]、dp[i][j-1]、dp[i-1][j-1]。此时从前往后计
     *  算就不对，必须借助额外的变量存储dp[i-1][j-1]的信息————代表着上一行前一个位置的值。
     *     如何解决：在一行数组的动规中，要求在更新任何一个位置的值之前，必须先记录，比如：计
     *  算dp[3]的之前先使用tmp记录dp[3]的值dp[3](old)，dp[3]更新后将dp[3](old)赋值给局
     *  部变量prev;接下来会计算dp[4]，依然先记录dp[4]（old）到变量tmp中，更新dp[4]时二维中
     *  会使用到左上角的位置值即dp[i-1][j-1]，就等价于一维中的dp[3](old)，而此时的dp[3]（old）
     *  是在变量prev中存储的。。。。。综上，整个过程形成了闭环，同时可以看到prev,tmp变量是缺一不
     *  可的！！！
     */
    public int minDistance_1dim(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        /*setep1：保证空间复杂度为O(min(m,n))————即把短的字符串放在二维表中列的位置*/
        if (m<n) return minDistance_1dim(word2,word1);
        /*step2：创建数组 并且 初始化。
            【说明】此时初始化相当于”二维dp“时初始化第一行的操作，即把word1当作是空串。
        * */
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        /*step3：遍历研究其余位置*/
        /**可以看到，在“一行数组+局部变量”的dp形式中。“prev“相当于“二维中的dp[i-1][j-1]”*/
        for (int i = 1; i <= m; i++) {
            int prev = dp[0]; /**要更新dp[0]了，因此先记录一下*/
            dp[0] = i; /**相当于二维中第一列（word2此时是空串）的值，word2是空串，此时的编辑距离就是word1的长度*/
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j]; /**下面要更新dp[j]了，因此也是先记录一下*/
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


    /**===================================hot100外====================================*/
    /*583. 两个字符串的删除操作
    * */
    public int minDistance_583(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        int m = word1.length(),n = word2.length();
        /**err：最长公共子序列"第一行第一列"不用初始化，因为空串的任何串的公共子序列长度为0。
         * 但是这里是编辑距离的问题，必须要初始化为非空串的长度；
         * 【补充】下面的这种形式的初始化其实放在for循环中（见下面的方法minDistance_583_1），是一样的道理，但是下面的写法更清晰一点*/
        for (int i=0;i<=m;i++){
            dp[i][0] = i;
        }
        for (int i=0;i<=n;i++){
            dp[0][i] = i;
        }
        for (int i=1;i<=word1.length();i++) /**i，j都需要从1开始；边界记得带等于*/
            for (int j=1;j<=word2.length();j++){
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    /**【说明】相比于编辑距离，这里少了一个可选条件dp[i-1][j-1]+1，这个条件表示的即是更新操作*/
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+1; /**err!：注意虽然比较的是“i-1”和“j-1”字符，但是计算出来的是dp[i][j]，如果误写为dp[i-1][j-1]，则结果恒为0*/
                }
            }
        return dp[word1.length()][word2.length()];
    }


    public int minDistance_583_1(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        int m = word1.length(),n = word2.length();
        /**将下面的初始化过程放在for循环中做..........*/
//        for (int i=0;i<=m;i++){
//            dp[i][0] = i;
//        }
//        for (int i=0;i<=n;i++){
//            dp[0][i] = i;
//        }
        for (int i=1;i<=word1.length();i++)
            for (int j=1;j<=word2.length();j++){
                if(i==0 || j==0){ /**这个if块其实相当于dp数组的初始化*/
                    dp[i][j] = (i==0)?j:i;
                    continue;
                }
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+1;
                }
            }
        return dp[word1.length()][word2.length()];
    }


    /*115. 不同的子序列
    给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
测试用例保证结果在 32 位有符号整数范围内。
     */
    /*二维的写法*/
    public int numDistinct(String s, String t) {
        /*step1：声明dp数组。
        dp[i][j]表示：s串的前i个字符子串的子序列中有多少个 t的前j个字符组成的子串
         */
        int m = s.length(),n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        /*step2：base case
            第一行的初始化：第一行表示s是空串，t不是空串（除了dp[0][0]）。因此从i=1开始，dp[0][i]都是0，
        由于int数组的默认值就是0，因此第一行的初始化可以省略。
            第一列的初始化：第一列表示t是空串，但是s不是空串。此时能得到t的方案数就是1种————即s中所有的
        字符都不要。因此下面需要进行初始化第一列
        */
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 1;
        }
        /*step3：对于其他位置的计算*/
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char tc = t.charAt(j - 1);
                /*决策1：不使用s的最后一个字符，因此种类数取决于dp[i-1][j]*/
                dp[i][j] = dp[i-1][j];
                /*决策2：如果最后一个字符相等，则可以采用*/
                if (sc==tc){
                    dp[i][j] += dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }

    /*一维的写法*/
    /**
        在二维的写法中，会发现一个位置（i,j）的值依赖于左上角（i-1，j-1）位置的值 以及 上方（i-1，j）位置的值，
     因此可以直接使用一行数组来进行动规。
        但是此时内层循环需要从大到小进行！！否则内层循环如果从小到大计算（i-1，j-1）位置的值就被覆盖了，覆盖后
     会变成（i，j-1）位置的值
     */
    public int numDistinct_1dim(String s, String t) {
        int m = s.length(),n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                char sc = s.charAt(i - 1);
                char tc = t.charAt(j - 1);
                if (sc==tc){
                    dp[j] += dp[j-1];
                }
            }
        }
        return dp[n];
    }

    /*72.编辑距离问题的泛化
    求解编辑的最小代价。其中insert、delete、change的含义
        insert：str1中插入一个字符的代价
        delete：str1中删除一个字符的代价
        change：str1中改变一个字符的代价
     */
    public int minDistance_(String word1,String word2){
        return minDistance(word1,word2,1,1,1);
    }

    private int minDistance(String word1, String word2, int insert, int delete, int change) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        /*step2：base case的情况
            第一行：word1是空串，word2不是空串。因此word1需要插入指定数量的字符才能实现等同；————操作
        的代价为 i*insert
            第一列：word1不是空串，word2是空串。因此word1需要删除所有的字符才能实现和word2相等————操
        作的代价 i*delete
        * */
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i*insert;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i*delete;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                /*情况1：当前位置的字符相等，不需要额外的操作，结果转移到dp[i-1][j-1]*/
                if (c1==c2){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    /*情况2：说明当前位置的字符不相等。此时可以采取三种操作
                        word1删除掉最后的字符，因此等价于"dp[i-1][j]+delete"————取决于word1到最后之前的串和word2中
                    j位置之前的串匹配的代价（简单点说，就是word1不使用最后一个字符，让剩下的字符操作到word2）；
                        word1的末尾插入word2的最后一个字符，因此等价于"dp[i][j-1]+insert"————取决于word1的当前所有
                    到word2除最后一个位置的匹配代价 + word1插入字符的代价
                        word1的末尾的字符改的和word2的最后一个字符相同，因此等价于"dp[i-1][j-1]+change"————即最后一
                    个字符匹配完成，看之前的那部分匹配的代价 + word1最后一个字符change的代价
                        */
                    dp[i][j] = Math.min(Math.min(dp[i-1][j]+delete,dp[i][j-1]+insert),dp[i-1][j-1]+change);
                }
            }
        }
        return dp[m][n];
    }
}
