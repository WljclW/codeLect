package zuo_course_02baseTiSheng.No_6;

public class Q4_ChessHore {
    /**
     * 从(0,0)出发，到(x,y)。且必须要走k步。
     *      有多少种走法？？
     * */
    //暴力递归
    public static int getWays(int x,int y,int k){
        return process(x,y,k);              
    }
    private static int process(int x, int y, int step) {
        //①处理无效情况
        if (x<0||x>8||y<0||y>9)         //越界返回0表示未找到合理的方案
            return 0;
        //②bace-case；
        if (step==0)                //如果step为0，表示不能走了。此时就是看是不是来到了(0,0)点。
            return (x==0&&y==0)?1:0;


        //③下面就是对于一个常规位置，我们返回的结果
        /**
         *      是八种方案的加和，通过八个不同的点都可以一步到达当前的位置————(x,y)
         * */
        return process(x-1,y+2,step-1)
                +process(x-1,y-2,step-1)
                +process(x+1,y+2,step-1)
                +process(x+1,y-2,step-1)
                +process(x-2,y-1,step-1)
                +process(x-2,y+1,step-1)
                +process(x+2,y-1,step-1)
                +process(x+2,y+1,step-1);
    }


    //表结构
    public static int dpWays(int x,int y,int step){
        //①处理无效情况
        if (x<0||x>8||y<0||y>9||step<0)         //越界返回0表示未找到合理的方案
            return 0;
        int[][][] dp = new int[9][10][step + 1];           //象棋的大小8*9.
        dp[0][0][0] = 1;            //base-case:step=0时，第0层，只有(0.0)位置是1，其他的位置都是0.

        for (int k=1;k<=step;k++){      //从第一层开始递推
            /**
             * （i,j）是当前层的任意一个位置。同一层内的计算顺序没有要求，因为根据决策我们看到任何一个点只依赖于step-1层
             * 的点，不依赖于同一层的其他点。
             * */
            for (int i=0;i<9;i++) {
                for (int j = 0; j < 10; j++) {
                    //【注】三层循环的作用就是填充好三维表。。出了循环后三维表各个位置的结果均已知。
                    dp[i][j][k] = getValue(dp, i - 1, j + 2, k - 1)
                            + getValue(dp, i - 1, j - 2, k - 1)
                            + getValue(dp, i + 1, j + 2, k - 1)
                            + getValue(dp, i + 1, j - 2, k - 1)
                            + getValue(dp, i - 2, j - 1, k - 1)
                            + getValue(dp, i - 2, j + 1, k - 1)
                            + getValue(dp, i + 2, j - 1, k - 1)
                            + getValue(dp, i + 2, j + 1, k - 1);
                }
            }
        }
        return dp[x][y][step];
    }

    private static int getValue(int[][][] dp,int x, int y, int step) {      //获取三维表中(x,y,step)位置的值
        //先判断这个(x,y)是不是越界了，越界了直接返回0
        if (x<0||x>8||y<0||y>9)         //越界返回0表示未找到合理的方案
            return 0;
        //如果没有越界就返回三维表中对应位置的值：我们的计算顺序就决定了如果当前位置不越界就一定计算过它的值了。
        return dp[x][y][step];
    }

    public static void main(String[] args) {
        int x=7,y=7,step=10;
        System.out.println(getWays(x,y,step));
        System.out.println(dpWays(x,y,step));
    }
}
