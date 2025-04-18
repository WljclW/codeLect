package zuo_course_02baseTiSheng.No_6_recurTransDP;

/**
 *  N*M的区域  bob在x,y位置  走k步 没走出区域就生存下来 返回bob生存的概率
 * */

public class Q5_BobDie {
    //初始版本
    public static String bobalivepro(int N,int M,int x,int y,int k){
        long all=(long)Math.pow(4,k);       //总共的可能路线4的k次方。。因为每一步都可以走上下左右4个可能。
        long live=process(N,M,x,y,k) ;      //计算它能活下来的方法数
        long gcd=gcd(all,live);
        return live/gcd+"/"+all/gcd;
    }

    private static long process(int N, int M, int x, int y, int step) {
        //先处理异常的情况
        if (x<0||x>N||y<0||y>M)
            return 0;
        //base-case就是step=0的时候并且当前位置不越界，就表明找到了一个方案。
        if (step==0)
            return 1;
        //下面计算bob走的总方法数，就是朝上下左右四个方向走的方法数累加。
        return process(N,M,x-1,y,step-1)
                +process(N,M,x+1,y,step-1)
                +process(N,M,x,y-1,step-1)
                +process(N,M,x,y+1,step-1);
    }

    public static long gcd(long m,long n){return n==0?m:gcd(n,m%n);}


    //版本1：优化
    public static String bobaliveprodp(int N,int M,int x,int y,int k){
        //三个可变参数需要一个三维表结构
        int[][][] dp = new int[N][M][k+1];
        //对bace-case进行处理
        for (int i=0;i<N;i++)
            for (int j=0;j<M;j++){
                dp[i][j][0] = 1;
            }

        //对普遍位置做处理，找出依赖关系确定计算顺序
        /**
         *  根据调用过程，我们可以发现需要从下往上计算各个层的各个点的值。
         *  同一层间的节点没有先后依赖关系，因此同一层的各个点计算顺序随意就可
         * */
        for (int step=1;step<=k;step++)
            for (int i=0;i<N;i++)
                for (int j=0;j<M;j++){
                    dp[i][j][step] += getValue(dp,N,M,i-1,j,step-1);
                    dp[i][j][step] += getValue(dp,N,M,i+1,j,step-1);
                    dp[i][j][step] += getValue(dp,N,M,i,j+1,step-1);
                    dp[i][j][step] += getValue(dp,N,M,i,j-1,step-1);
                }
        long live=dp[x][y][k];
        long all=(long)Math.pow(4,k);//总共的可能路线4的k次方
        long gcd=gcd(all,live);
        return live/gcd+"/"+all/gcd;
    }

    //从dp三维表中获取值的函数。。需要先判断这个位置是不是越界了。
    public static int getValue(int[][][] dp,int N,int M,int x,int y,int k){
        if(x<0||y<0||x>N||x>M) return 0;
        return dp[x][y][k];
    }

}
