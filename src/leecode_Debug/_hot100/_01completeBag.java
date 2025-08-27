package leecode_Debug._hot100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 补充一些完全背包问题的运用
 * @author: Zhou
 * @date: 2025/6/14 13:13
 */
public class _01completeBag {
    /*
     518. 零钱兑换 II
    * */
    /**
     * 【实质】多重背包问题，求解方案的组合数。————方案(1,2,1)和方案(2,1,1)是相同的方案
     * */
    //解法1：二维dp
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        /*第一列表示凑出0块，因此只有一种方案数！！【注意是有前提的————每一个硬币面值都是大于0
        的，因此任选一个硬币都不行。只能一个都不选才能凑出0】*/
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        /*第一行的初始化，只能选coins[0]。因此如果用多个coins[0]可以凑出amount，就表示
        有一种方案，否则也是没有方案数
        * */
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) dp[0][i] = 1;
        }
        for (int i = 1; i < coins.length; i++)
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) /**err：大于等于的时候都能进行状态转移*/
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                else
                    dp[i][j] = dp[i - 1][j];
            }

        return dp[coins.length - 1][amount];
    }

    /*
    解法2：一维dp，题目中求解的是方案(组合)数。
    * */
    public int change_1dim(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int j = 0; j < coins.length; j++)
            for (int i = 1; i <= amount; i++) {
                if (i >= coins[j]) dp[i] += dp[i - coins[j]];
            }
        return dp[amount];
    }



    /*
    * 377. 组合总和 Ⅳ
    * */
    /**
     * 【实质】多重背包问题。求解方案的排列数。————方案(1,2,1)和方案(2,1,1)认为是不同的方案
     */
    //解法1：一维dp
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] =1;
        for (int i=1;i<=target;i++) /**求解的是排列总数。必须要先遍历target（背包）*/
            for (int j=0;j<nums.length;j++){
                if (i>=nums[j]) dp[i] += dp[i-nums[j]];
            }
        return dp[target];
    }


    /*
    爬楼梯的进阶版本
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？

    注意：给定 n 是一个正整数。

    输入描述：输入共一行，包含两个正整数，分别表示n, m

    输出描述：输出一个整数，表示爬到楼顶的方法数。

    输入示例：3 2

    输出示例：3

    提示：

    当 m = 2，n = 3 时，n = 3 这表示一共有三个台阶，m = 2 代表你每次可以爬一个台阶或者两个台阶。

    此时你有三种方法可以爬到楼顶。

    1 阶 + 1 阶 + 1 阶段
    1 阶 + 2 阶
    2 阶 + 1 阶
    * */
    /**
     * 【实质】多重背包问题。求解方案的排列数。————即先走2步再走1步的(2,1)；先走1步再走2步的(1,2)
     * 认为是不同的方案
     * */
    public void palouti(){
        Scanner scanner = new Scanner(System.in);
        int m,n;
        while (scanner.hasNextInt()){
            n = scanner.nextInt(); //一共有多少个台阶
            m = scanner.nextInt(); //每一次最多可以走几步

            Integer[] dp = new Integer[n + 1];
            Arrays.fill(dp,0);
            dp[0] =1; /*dp[0]必为1，如果是0，后面递推下去后续的都是0*/
            /**err：先遍历背包*/
            for (int i=1;i<=n;i++)
                for (int j=1;j<=m;j++){
                    if (i-j>=0) dp[i] += dp[i-j];
                }
            System.out.println(Arrays.deepToString(dp));
        }
    }



    /*
    322.零钱兑换
    * */
    /**
     * 【问题本质】求解需要凑出amount金额的最少硬币数量。
     * */
    /*二维dp的写法*/
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i=0;i<coins.length;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for (int i=0;i<coins.length;i++){
            dp[i][0] = 0;
        }
        for (int i=coins[0];i<=amount;i++){
            if (i%coins[0]==0) dp[0][i] = i/coins[0];
        }
        for (int i=1;i<coins.length;i++)
            for (int j=1;j<=amount;j++) {
                /**err：这里的条件多了一个。
                 * 只有之前的位置不是最大值时，才说明之前的位置由可达方案，否则说明dp[i][j - coins[i]]都不
                 * 可达，更不用说需要的最少硬币数了*/
                if (j >= coins[i] && dp[i][j-coins[i]] != Integer.MAX_VALUE)
                    /*if表明可以选择coins[i]。
                    情况1：不选coins[i]，需要的数量dp[i-1][j]，即用0~i-1的硬币凑成amount；
                    情况2：选coins[i]，因此要凑出amount-coins[i]的金额，硬币可以重复选，因此需要在
                        0~i的硬币凑出amount-coins[i]的金额，需要的硬币数dp[i][j-coins[i]]，并且由
                        于选择了使用coins[i]，因此还需要再＋1。即dp[i][j-coins[i]]+1。
                    综上，两种情况取最小值。*/
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j-coins[i]] + 1);
                else
                    dp[i][j] = dp[i - 1][j]; //否则说明coins[i]没法选，需要的硬币数的dp[i-1][j]
            }
        return dp[coins.length-1][amount]==Integer.MAX_VALUE?-1:dp[coins.length-1][amount];
    }

    /*一维dp的写法*/
    public int coinChange_1dim(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            dp[0] = 0; //表示凑够0元的总价值，需要的硬币数是0
            //初始化dp数组为最大值
            for (int j = 1; j < dp.length; j++) {
                dp[j] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < coins.length; i++) {
                //正序遍历：完全背包每个硬币可以选择多次
                for (int j = coins[i]; j <= amount; j++) { /**区别：完全背包问题的以为解法中，背包容量也要从小到大遍历*/
                    //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                    if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                        //选择硬币数目最小的情况
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1); /**【注】由于这个题求解的是硬币数量，因此这里加1，而不是加硬币面值coin[i]*/
                    }
                }
            }
            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /*一维dp自己的写法*/
    public int coinChange_(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i=coins[0];i<=amount;i++){
            if (i%coins[i]==0)
                dp[i] = i/coins[0];
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount+1; j++) {
                if (j>=coins[i]&&dp[j-coins[i]]!=Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j],dp[j-coins[i]]+1); /**因为题目求解的是组成amount的硬币数量，因此这里加的是1；否则的话这里应该加硬币的面值*/
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }




    public static void main(String[] args) {
        _01completeBag completeBag = new _01completeBag();
//        completeBag.palouti();

        completeBag.coinChange_1dim(new int[]{1,2,5},11);
    }
}
