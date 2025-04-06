package zuo_course_02baseTiSheng.No_6;

public class Q1_RobotWalk {
    //初始版本：暴力递归
    public static int walkWays(int N,int E,int S,int K){
        return process(N,E,K,S);
    }

    private static int process(int N, int E, int rest, int cur) {
        //处理base-case情况
        if (rest == 0)                        //如果rest=0时，来到了E位置则返回1表示找到一种方案
            return cur == E?1:0;
        if (cur == 1){                      //如果当前来到1位置，则下一步只能去2位置
            return process(N,E,rest-1,2);
        }
        if (cur == N){                          //如果当前来到N位置，则下一步只能去左边的位置N-1。
            return process(N,E,rest-1,N-1);
        }
        //处理普遍位置————一个普遍位置既可以往左走也可以往右走，是这两种方案的加和。
        return process(N,E,rest-1,cur-1)
                + process(N,E,rest-1,cur+1);
    }

    //记忆化搜索
    public static int walkWay2(int N,int E,int S,int K){
        //创建缓存并进行初始化。。。以后在计算之前先判断一下是不是计算过了，如果计算过了直接返回。
        int[][] dp = new int[K+1][N+1];
        for (int i=0;i<=K;i++)
            for (int j=0;j<=N;j++){
                dp[i][j] = -1;                  //缓存初始化为-1，以便区分开计算过的情况
            }

        return process01(N,E,K,S,dp);
    }

    private static int process01(int N, int E, int rest, int cur, int[][] dp) {
        /**
         * 对比：
         *      这里只要涉及计算都是先记录到dp[rest][cur]，最后做return.
         * */
        //如果不是初始化的-1，表示计算过了直接返回
        if(dp[rest][cur]!=-1) return dp[rest][cur];
        //basecase，如果剩余可走的步数为0，就看是不是当前在终止位置。
        if(rest==0) {
            dp[rest][cur]=cur==E?1:0;
            return dp[rest][cur];
        }
        //特殊情况的考虑
        if (cur==1)
            dp[rest][cur] = process01(N,E,rest-1,2,dp);
        if (cur == N)
                dp[rest][cur] = process01(N,E,rest-1,cur-1,dp);

        //普遍位置的考虑
        dp[rest][cur] = process01(N,E,rest-1,cur-1,dp)
                        + process01(N,E,rest-1,cur+1,dp);
        return dp[rest][cur];
    }
}
