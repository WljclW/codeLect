package zuo_course_02baseTiSheng.No_6_recurTransDP;


/**
 * 一、题目：一个数组，知道机器人的初始位置 以及 终点位置；每一步机器人只能向左走一步 或者 向右走一步；走到第一个位
 * 置时下一步只能向右走，走到最后一个位置时下一步只能向左走；机器人只能走确定的步数，问有多少种方案可以让机器人
 * 从初始位置 走到 终点位置
 *
 * 【说明】区别于Q2：Q2表示需要多少枚硬币拼出来，因此每次选择一个硬币都需要加1；
 *                Q1表示有多少种方案，因此找到一种方案的时候才会返回1，整个过程不需要+1.
 */
public class Q1_RobotWalk {

    public static void main(String[] args) {
        System.out.println(walkWays(5, 5, 3, 4));
        System.out.println(walkWay2(5, 5, 3, 4));
    }


    /**
     * 版本1——初始版本：暴力递归
     */
    public static int walkWays(int N /*总共几个位置*/,
                               int E/*要走到哪里*/,
                               int cur/*还能走几步*/,
                               int rest/*当前位置*/) {
        return process(N, E, rest, cur);
    }

    /**
     * 初始版本：暴力递归的实现
     * 方法的含义：从cur位置，走rest步，走到E的方法数。
     */
    private static int process(int N, int E, int rest, int cur) {
        /*1.处理base-case情况*/
        if (rest == 0)       //如果rest=0(表示不能再走了)时，来到了E位置则返回1表示找到一种方案。否则返回0表示没有找到方案
            return cur == E ? 1 : 0;
        /*2.处理任意位置的情况*/
        /*2.1 如果当前位置是特殊的情况怎么处理*/
        if (cur == 1) {      //如果当前来到1位置，则下一步只能去2位置
            return process(N, E, rest - 1, 2);
        }
        if (cur == N) {       //如果当前来到N位置，则下一步只能去左边的位置N-1。
            return process(N, E, rest - 1, N - 1);
        }
        /*2.2处理普遍位置————一个普遍位置既可以往左走也可以往右走，是这两种情况所有方案数的加和。*/
        return process(N, E, rest - 1, cur - 1)
                + process(N, E, rest - 1, cur + 1);
    }

    /**
     * 版本2：记忆化搜索的版本。
     *    从暴力递归 到 记忆化搜索 的过程，是不需要关注位置之间的关系的，就仅仅是傻傻的加缓存(每一个位置的结果计算出来后先记录在
     * dp表中，然后再return)
     */
    public static int walkWay2(int N, int E, int cur, int rest) {
        //创建缓存并进行初始化。。。以后在计算之前先判断一下是不是计算过了，如果计算过了直接返回。
        int[][] dp = new int[rest + 1][N + 1];
        for (int i = 0; i <= rest; i++)
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;                  //缓存初始化为-1，以便区分开计算过的情况
            }

        return process01(N, E, rest, cur, dp);
    }

    /**
     * 1. 记忆化搜素的实现。。。。记忆化搜索就只是在 暴力递归 的基础上 添加了缓存！
     * 2. 记忆化搜索就是添加了缓存，保证计算过的参数不用再重复计算了，但是这种过程必须保证是无后效性的。比如：这个题中其实可变的
     * 参数就是cur和rest，只要“当前位置”和“剩余的步数”确定的时候，值就是确定的。具体的说————
     * 第一点：比如f(2，3)表示当前位置是2，剩余3步可走；①f(1,4)的递归会调用到，当前位置时1位置只能走到2位置同时步数-1。
     * ②同时f(3，4)的递归计算中也会调用到，当前位置是3可以向左走到2同时步数-1。
     * 第二点：除此以外这两次调用时f(2,3)的结果应该是一样的————这就是所谓的无后效性。。比如f(2,3)————当前位置是2，还能走3
     * 步到目标位置的方案数是确定的，并且这个方案数与谁调用到f(2,3)这个过程是没有关系的，这就是无后效性
     * 满足第一点、第二点时就可以将 暴力递归 借助 缓存 实现为 “记忆化搜索”
     * 3. 记忆化搜索带来的区别：
     * 只要涉及计算都是先记录到dp[rest][cur]，最后做return.
     */
    private static int process01(int N, int E, int rest, int cur, int[][] dp) {
        /*保证下标不越界。后续在递归调用的时候没有校验，因此在方法开始需要校验*/
        if (rest < 0 || rest >= dp.length || cur < 0 || cur >= dp[0].length)
            return 0;
        //如果不是初始化的-1，表示计算过了直接返回
        if (dp[rest][cur] != -1) return dp[rest][cur];
        //basecase，如果剩余可走的步数为0，就看是不是当前在终止位置。
        if (rest == 0) {
            dp[rest][cur] = (cur == E) ? 1 : 0;
            return dp[rest][cur];
        }
        /*
         * 2.任意位置的结果计算。区别在于：
         *   计算出的结果先记录在dp表的对应位置，然后最后返回dp表该位置的值
         * */
        /*2.1特殊位置的考虑*/
        if (cur == 1)
            dp[rest][cur] = process01(N, E, rest - 1, 2, dp);
        if (cur == N)
            dp[rest][cur] = process01(N, E, rest - 1, cur - 1, dp);
        /*2.2普通位置的考虑*/
        dp[rest][cur] = process01(N, E, rest - 1, cur - 1, dp)
                + process01(N, E, rest - 1, cur + 1, dp);
        return dp[rest][cur];
    }
}
