package leecode_Debug._hot100;

public class _01bag {
    /*
    416. 分割等和子集
    给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两
    个子集，使得两个子集的元素和相等。
    * */
    /**
     * 【关键】问题等价于求解：target的背包能放下物品的最大价值是不是target
     * 【实质】实质是一道0-1背包问题。。。转化为0-1背包问题：
     *      每一个物品的重量是nums[i]，价值是nums[i]，背包容量是target，能否放下target
     *  价值的物品（其中target=sum/2）.
     * */
    /*一维数组的形式*/
    public boolean canPartition(int[] nums) {
        /*step1：求出数组的和，看看是不是能平分为两部分*/
        int target = 0;
        for (int num : nums) {
            target += num;
        }
        if (target % 2 != 0) return false; //不能平均分两半直接返回false
        target /= 2;
        /*step2：0-1背包的核心逻辑*/
        int[] dp = new int[target + 1];
        /*0-1背包一维数组形式的核心逻辑————
         *   ①一维的写法中遍历的顺序不能颠倒（先遍历背包容量再遍历数组元素也即物品————外层for循环是遍历物品）；
         *   ②一维的写法中背包容量必须从大到小遍历；*/
        for (int i = 0; i < nums.length; i++)
            for (int j = target; j >= nums[i]; j--) {  /**err：还是双重循环，但是内循环需要“倒着遍历求值”*/
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        return dp[target] == target;
    }


    /*二维数组的形式*/
    public boolean canPartition_2dimdp(int[] nums) {
        int target = 0;
        for (int num : nums) {
            target += num;
        }
        if (target % 2 != 0) return false;
        target /= 2;

        int[][] dp = new int[nums.length][target + 1];
        /*第一列代表背包的容量是0————放不下任何物品，因此全初始化为0*/
        for (int i = 0; i < nums.length; i++) { //数组的默认值就是0，可以省略
            dp[i][0] = 0;
        }
        /*第一行代表只能选择装物品0，因此背包容量大于nums[0]时，物品0可以放进去，
         * 最初化对应位置的最大值是nums[0]*/
        for (int i = nums[0]; i <= target; i++) {
            dp[0][i] = nums[0];
        }
        for (int i = 1; i < nums.length; i++)
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) { //说明这种情况下可以选择物品i————物品i即当前物品
                    dp[i][j] = Math.max(dp[i - 1][j], /*不选取物品i，背包最大价值*/
                            dp[i - 1][j - nums[i]] + nums[i]); /*选取物品i，此时背包需要留出nums[i]的空间*/
                } else { //j<nums[i]，表示物品i不能选择，因为背包的容量都没物品i的重量大，放不下物品i
                    dp[i][j] = dp[i - 1][j];
                }
            }
        return dp[nums.length - 1][target] == target;
    }



    /*
    1049. 最后一块石头的重量 II
    有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
    每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，
    且 x <= y。那么粉碎的可能结果如下：
        如果 x == y，那么两块石头都会被完全粉碎；
        如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
        最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返
        回 0。
    * */
    /**
     * 【关键】target的背包能放下的最大价值
     * 【实质】0-1问题————本题其实就是尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，这
     * 样就化解成01背包问题了。。转换为0-1问题：
     *      用sum/2的背包装石头（每个石头重量是stones[i]，价值是stones[i]），包里装下的石头最
     *  大价值是多少！！就说明这麽多价值的石头可以抵消掉————标准的0-1问题话术
     * */
    public int lastStoneWeightII(int[] stones) {
        int target = 0;
        for (int num : stones) {
            target += num;
        }
        int sum = target;
        target /= 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++)
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        return sum - dp[target] - dp[target]; /**区别就是返回的不是最大价值，而是抵消后剩下的价值*/
    }

    /*
    494. 目标和
    给你一个非负整数数组 nums 和一个整数 target 。
    向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

    例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
    返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
    * */
    /**？？？
     * 【关键】1.题目的转换；2.求的是方法数，不是最大价值了
     *      1.题目转换：
     *          x-(sum-x)=target--->x=(target+sum)/2;
     *         即背包的容量是x，问装下x价值的物品有多少种方案？
     * */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num:nums){
            sum += num;
        }
        if ((sum+target)%2!=0) return 0;
        if (Math.abs(target)>sum) return 0;
        int remain = (sum+target)/2;
        // dp[i][j]：遍历到数组第i个数时， remain为j时的能装满背包的方法总数
        int[][] dp = new int[nums.length][remain + 1];

        /* 初始化最上行（dp[0][j])，当nums[0] == j时（注意nums[0]和j都一定是大于等于零的，因此不需要判断
              等于-j时的情况），有唯一一种取法可取到j，dp[0][j]此时等于1
            其他情况dp[0][j] = 0
            java整数数组默认初始值为0*/
        if (nums[0] <= remain) {
            dp[0][nums[0]] = 1;
        }

        /* 初始化最左列（dp[i][0])
         当从nums数组的索引0到i的部分有n个0时（n > 0)，每个0可以取+/-，因此有2的n次方中可以取到j = 0的方案
         n = 0说明当前遍历到的数组部分没有0全为正数，因此只有一种方案可以取到j = 0（就是所有数都不取）*/
        int numZeros = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                numZeros++;
            }
            dp[i][0] = (int) Math.pow(2, numZeros);

        }



        /* 递推公式分析：
           当nums[i] > j时，这时候nums[i]一定不能取，所以是dp[i - 1][j]种方案数
           nums[i] <= j时，num[i]可取可不取，因此方案数是dp[i - 1][j] + dp[i - 1][j - nums[i]]
         由递推公式可知，先遍历i或j都可*/
        for (int i=1;i<nums.length;i++){
            for (int j=1;j<=remain;j++){
                if (j>=nums[i]){
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length-1][remain];
    }

    public static void main(String[] args) {
        _01bag cla = new _01bag();
        System.out.println(cla.canPartition(new int[]{1,2,3,4}));
    }
}
