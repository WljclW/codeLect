package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**暴力递归：更像是一个从所求 依次推到 已知的问题
 * 动态规划：更像是一个从已知的基础问题(base case) 依次推到 想要求解的问题*/
public class _14DP {
    /*
    * 70.假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    * */
    public int climbStairs(int n) {
        if (n==1) return 1;
        if(n==2) return 2;
        /*走到台阶n，就是走到n-1的方案数 和 走到n-2的方案数 之和*/
        return climbStairs(n-1)+climbStairs(n-2);
    }

    /**dp动态规划*/
    public int climbStairs1(int n) {
        if (n==1) return 1;
        if(n==2) return 2;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    /**使用有限的变量来实现*/
    public int climbStairs2(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        int p1 = 1;
        int p2 = 2;
        for(int i=3;i<=n;i++){
            int tmp = p2;
            p2 = p1+p2;
            p1 = tmp;
        }
        return p2;
    }

    /*
    * 118.给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
        在「杨辉三角」中，每个数是它左上方和右上方的数的和。
    * */
    public List<List<Integer>> generate(int numRows) {
        /**这样写有问题，后面的行计算不方便*/
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> lev1 = new LinkedList<>();
        lev1.add(1);
        LinkedList<Integer> lev2 = new LinkedList<>();
        lev2.add(1);
        lev2.add(1);
        if(numRows==1) res.add(lev1);
        if (numRows==2){
            res.add(lev1);
            res.add(lev2);
        }
        for (int i=2;i<numRows;i++){
            LinkedList<Integer> cur = new LinkedList<>();
            cur.add(0,1);
            for(int j=1;j<i;j++){
                int curVal = res.get(i-1).get(j-1)+res.get(i-1).get(j);
                cur.add(j,curVal);
            }
            cur.add(i,1);
            res.add(new LinkedList<>(cur));
        }
        return res;
    }


    /*198
    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃
    的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一
    晚上被小偷闯入，系统会自动报警。

    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一
    夜之内能够偷窃到的最高金额。
    * */
    /**【】1. 注意dp问题base case的解决
     *    */
    //解法1：按照dp套路创建返回值来做
    public int rob(int[] nums) {
      int max = Integer.MIN_VALUE;
        returnType res = rob(nums, 0, 0, max);
        return Math.max(res.curTotal,res.noCurTotal);
    }

    class returnType{
        private int curTotal;
        private int noCurTotal;

        public returnType(int curTotal,int noCurTotal) {
            this.curTotal = curTotal;
            this.noCurTotal = noCurTotal;
        }
    }

    public returnType rob(int[] nums,int index,int curTotal,int max){
        if(index==nums.length-1){
            return new returnType(nums[index],0);
        }
        if (index==nums.length-2){
            return new returnType(Math.max(nums[index],nums[index+1]),nums[index+1]);
        }
        returnType next = rob(nums, index + 1, curTotal, max);
        int param1 = curTotal + nums[index]+next.noCurTotal;
        int param2 = curTotal + Math.max(next.curTotal,next.noCurTotal);
        return new returnType(param1,param2);
    }

    //解法2：最简做法
    /**题目的本质：每到一个位置，根据当前位置前面的两个值来推断当前位置的结果*/
    public int rob01(int[] nums) {
        if (nums.length==1) return nums[0];
        int first = nums[0],sec=Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            int cur = Math.max(nums[i]+first,sec);
            first = sec;
            sec = cur;
        }
        return sec;
    }




    /*279.
    * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

       完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数
       * 自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。*/
//    public int numSquares(int n) {
//
//    }



    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
    public int coinChange(int[] coins, int amount) {
        return coinChange(coins,0,amount);
    }

    public int coinChange(int[] coins,int index,int rest){
        if(index==coins.length) return 0;
        if(rest==0) return 1;

        int ways = 0;
        for (int zhang=0;coins[index]*zhang<=rest;zhang++){
            ways += coinChange(coins,index+1,rest-zhang*coins[index]);
        }
        return ways;
    }



    /*139.
    * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利
    * 用字典中出现的一个或多个单词拼接出 s 则返回 true。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    /**
     * 【关键】dp[i]表示0~i-1的子串是否能由wordDict的数个字符串组成。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        HashSet<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i=1;i<s.length()+1;i++){
            for (int j=0;j<i;j++){ //j<i，因此j的最大值能到s.length()
                /*
                if条件语句的解释————
                    dp[j]：0~j-1这段子串能有列表的字符串拼出来；
                    set.contains(s.substring(j,i))：j~i-1这段子串是不是在题目字符串的列表中
                1. 解释一下if条件：dp[j]表示0~j-1的子串能不能由字典的词组成；然后我们再看[j,i-1)的子串是不是在字典中，如果
                    在字典中就说明[0,i-1]的子串能由字典的一个或者多个词组成
                2. ”set.contains(s.substring(j,i))“中i是能取到s.length()的，但是substring函数是左闭右开的区间，因此实
                    际上获取子串时不会去拿s.length()的子串，不会导致越界异常。
                    */
                if (dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break; //这里加break会继续研究s串的下一个位置，不加也没啥问题
                }
            }
        }
        return dp[s.length()];
    }


    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i=1;i<nums.length;i++){
            for (int j=0;j<i;j++){
                if (nums[j]<nums[i]){
                    dp[i] = Math.max(1,dp[j]+1);
                }
            }
            res = Math.max(dp[i],res);
        }
        return res;
    }


    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
    /**    求的是最大值，但是不能仅仅记录之前子数组的最大值。比如"-2,3,-4"的最大值应该是24，如果
     * 仅仅选取最大值则遍历到元素3的时候，会选择单独成一个子数组，这样是不对的。应该到任何一个
     * 位置，都应该记录当前子数组的最大值、最小值...
     *     然后到每一个位置的时候，需要决策出当前位置的最大目标值*/
    public int maxProduct(int[] nums) {
        //dp数组的定义：以nums[i-1]结尾的最大子数组的乘积..每一个数可以和前面的数个数合成一个子数组 或者 自己成立一个子数组
        int[] dp1 = new int[nums.length + 1];
        int[] dp2 = new int[nums.length + 1];
        dp1[0] = 1;
        dp2[0] = 1;
        int max = Integer.MIN_VALUE;
        for(int i=1;i<=nums.length;i++){
            dp1[i] = Math.max(Math.max(dp2[i-1]*nums[i-1],nums[i-1]),dp1[i-1]*nums[i-1]);
            dp2[i] = Math.min(Math.min(dp2[i-1]*nums[i-1],nums[i-1]),dp1[i-1]*nums[i-1]);
            max = Math.max(max,dp1[i]);
        }
        return max;
    }



    /*416.
    * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两
    * 个子集，使得两个子集的元素和相等。*/
    /**见leecode_Debug._hot100._01bag*/
//    public boolean canPartition(int[] nums) {
//
//    }

    /*32.
    * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的
    * 长度。*/
    public int longestValidParentheses(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') { /*【说明】只研究右括号的位置.。因为如果当前位置是左括号，以左括号结尾的有效括号串长度必然是0*/
                if (s.charAt(i - 1) == '(') { //if块说明跟前面一个位置形成有效括号
                    dp[i] = (i >= 2) ? dp[i - 2] + 2 : 2;
                } else { //else说明前面的一个位置也是右括号')'
                    if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { /**else里面的逻辑容易写错*/
                        dp[i] = (i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] + 2 + dp[i - 1] : 2 + dp[i - 1];
                    }
                }
                res = Math.max(res, dp[i]); //更新结果。。如果index位置不是右括号，则对应的dp[index]就是0，也不用更新最大值
            }
        }
        return res;
    }


    public static void main(String[] args) {
        _14DP dp = new _14DP();
//        System.out.println(dp.numSquares(13));

        String s = "leetcode";
        ArrayList<String> strings = new ArrayList<>() {{
            add("leet");
            add("code");
        }};
        dp.wordBreak(s,strings);
    }

/**================================hot100之外======================================*/
    /*718. 最长重复子数组
    * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
    *   子数组是连续的！
    * */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int res = 0;
        for (int i=1;i<= nums1.length;i++)
            for (int j=1;j<= nums2.length;j++){
                if (nums1[i-1]==nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                /**err：不能出现下面的else。因为子数组必须是连续的，如果nums1[i]≠nums2[j]，则以它两结尾
                 * 的最长相同子数组就是0*/
//                else{
//                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
//                }
                res = Math.max(dp[i][j],res);
            }

        return res;
    }

}