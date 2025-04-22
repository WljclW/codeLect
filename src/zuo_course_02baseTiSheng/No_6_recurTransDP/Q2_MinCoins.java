package zuo_course_02baseTiSheng.No_6_recurTransDP;


/**
 * 一、题目：arr是一系列面值的硬币(可能有相同的面值)，目标是拼出aim的面值，求出至少需要的硬币数量，每个硬币只能使用一次
 * 二、动态规划中5个关键的问题：
 *  1. 可变的变量是哪几个？变化范围是什么
 *  2. 在表中我们需要求的是哪一个位置的值
 *  3. base case是什么
 *  4.
 *  5. 根据计算关系判断表结构中年，如何计算。“从上到下？”“从左到右？”
 * */
public class Q2_MinCoins {

    /**
     * 版本1：初始的暴力递归的方法，没有任何优化。
     * */
    public static int minCoins01(int[] arr /*硬币的列表，每种硬币最多选一个*/,
                                 int aim /*最终的目标，要凑出的钱数*/){
        return process01(arr,0,aim);    //主函数根据题意调用递归函数。
    }

    private static int process01(int[] arr, int index/*从哪一个位置开始选硬币*/, int rest) {    //rest就是目前还需要凑多少钱才能到aim
        /**
         * 【注】无效解不能return 0.
         * */
        if (rest<0){      //表示走到当前位置走错了，因为钱多了，导致rest<0了。
            return -1;
        }
        if (rest==0){   //找到了一种可行方案。。但是不用再添加硬币数量了，因此返回0
            return 0;
        }

        //下面是rest>0的情况
        if (index==arr.length){         //没有位置了，也就是说后面没硬币可选了，但是aim没有凑齐。。表示这种方案是错的
            return -1;
        }
        //rest>0 并且 还有硬币
        int p1 = process01(arr,index+1,rest);     //表示如果 选择不要index位置的硬币。。也就是从index+1位置开始，凑rest面额
        int p2 = process01(arr,index+1,rest-arr[index]);     //表示如果 选择要index位置的硬币。。也就是从index+1位置开始，凑rest-arr[index]面额。

        if (p1==-1&&p2==-1)    //表示  不论要不要index位置的货币，返回的都是-1，就表示当前位置无可行方案
            return -1;
        else{
            /**
             * 如果P1有效则返回p1。
             * 如果p2有效则返回p2+1，因为方案p2表示选择了index位置的硬币，因此需要加1
             * 如果两个都有效则作决策，决策就是选出目前硬币最小的方案
             * */
            if (p1 == -1)       //表示可能性1无效，也就是说  选择不要硬币index这条路走不通；
                return 1+p2;        //因为此时的决策：选择要index位置的货币，因此结果需要加1.
            if (p2 == -1)       //表示可能性2无效，也就是说，选择要index位置的硬币这条路走不通；
                return p1;
            return Math.min(p1,p2+1);   //表示取两种的最小值
        }
    }



    /**
     * 版本2：记忆搜索优化，在原始版本的基础上有几点改动：
     *    1. 在调用递归方法前，创建dp数组并依据base case进行初始化。(初始化的时候要避免使用后续可能使用的值)
     *    2. 在每一次return之前，要确保当前位置的值记录到dp表
     * */
    public static int minCoins02(int[] arr,int aim){  //硬币存放在arr中，aim是最终要达到的面额
        //声明二维表做缓存，并进行初始化
        int[][] dp = new int[arr.length+1][aim+1];
        for (int i=0;i<=arr.length;i++){
            for (int j=0;j<=aim;j++)
                dp[i][j] = -2;       //0表示正常返回，-1表示此路无有效解。。。-1和0都被占用，因此这里初始化选择-2.
        }
        return process02(arr,0,aim,dp);
    }

    private static int process02(int[] arr, int index, int rest,int[][] dp) {
        /*
        * step1：base case的处理
        * */
        if (rest<0){    //表示走到当前位置走错了，因为钱多了？导致rest<0了。
            return -1;
        }

        /**
         * 多了如下的逻辑判断————判断二维表当前位置是不是不是初始化值-2。如果不是，说明我们已经计算过它的值，直接拿出来返回就可；
         *      否则表示表中的值是-2，我们还没有碰到过这个位置，因此下面的部分就需要先在表中登记，然后再返回，这样下一次碰到了直
         *      接返回。
         * */
        if(dp[index][rest]!=-2)
            return dp[index][rest];

        if (rest==0){
//            return 0;
            dp[index][rest] = 0;        //现在先在二维表中记录一下值，然后再返回。
            return dp[index][rest];
        }
        //下面是rest>0的情况
        if (index==arr.length){         //没有位置了，也就是说后面没硬币可选了，但是aim没有凑齐。。表示这种方案是错的
//            return -1;
            dp[index][rest] = -1;        //现在先在二维表中记录一下值，然后再返回。
            return dp[index][rest];
        }
        /*
        * step2：正常位置的处理。走到这里说明rest>0，位置也有效(即还有硬币可选)。
        *   但是处理之后先是记录在dp表中，然后最后返回
        * */
        int p1 = process02(arr,index+1,rest,dp);     //表示如果 选择不要index位置的硬币。。也就是从index+1位置开始，凑rest面额
        int p2 = process02(arr,index+1,rest-arr[index],dp);          //表示如果 选择要index位置的硬币。。也就是从rest+1位置开始，凑rest-arr[index]面额。

        if (p1==-1&&p2==-1){
            dp[index][rest]=-1;
//            return -1;
        }
        else{
            /**
             * 进入else表示p1和p2'不都是'-1.
             *      如果P1有效则返回p1.
             *      如果p2有效则返回p2。
             *      如果两个都有效则作决策，决策就是选出目前硬币最小的方案
             * */
            if (p1 == -1)       //表示可能性1无效，也就是说 选择不要硬币index这条路 走不通；
//                return 1+p2;
                dp[index][rest]=p2+1;
            else if (p2 == -1)       //表示可能性2无效，也就是说 选择要index位置的硬币这条路 走不通；
//                return p1;
                dp[index][rest]=p1;
            else
                dp[index][rest] = Math.min(p1,p2+1); //当前位置的值选择小的那个值
        }
        return dp[index][rest];             //最后统一的返回的dp[index][rest].
    }

    /**
     * 版本3：严格表结构.
     *    在前面逻辑的基础上可以看到整个过程中，位置的依赖关系，根据这种依赖关系去找到合理的求解顺序。。
     *    比如拿这个题来说：(index,rest)位置的值依赖于下一行(index+1，rest)的值 以及 (index+1,rest-arr[index])的值，因
     * 此就要求求解的顺序————所有行从下到上，同一行从左到右
     * */
    public static int minCoins03(int[] arr,int aim){
        int length = arr.length;
        int[][] dp = new int[length+1][aim+1];
        /*
        * step1：按照base case填充dp表
        * */
        for (int i=0;i<=length;i++)               //rest等于0的情况，表示找打了一种可行方案
            dp[i][0] = 0;
        for (int i=1;i<=aim;i++)            //表示当前的步骤，没有找到可行方案
            dp[length][i] = -1;
        /*
        * step2：对于普遍的位置，按照“特定顺序”依次求dp表对应位置的值
        * */
        for (int index=length-1;index>-1;index--)
            for (int rest=1;rest<=aim;rest++){
                int p1 = dp[index+1][rest];             //因为我们是从表的下往上求值的，因此下一行的值已经求过了。

                //p2，是下一行当前位置列的前面的第arr[index]列，这个可能不在有效范围，因此p2需要做额外的判断
                int p2 = -1;        //p2的值先默认是-1.
                if (rest-arr[index]>=0)                     //如果下一行前面的第arr[index]列没有小于0，则表示有效
                    p2 = dp[index+1][rest-arr[index]];
                if (p1 == -1 && p2 == -1){
                    dp[index][rest] = -1;
                }else{
                    if(p1==-1) dp[index][rest]=p2+1;
                    else if(p2==-1) dp[index][rest]=p1;
                    else dp[index][rest]=Math.min(p1,p2+1);
                }
            }
        return dp[0][aim];      //这个就是我们画出表后，找到的要求的格子(即最终位置)是哪一个？就返回哪一个位置的值。
    }


    public static void main(String[] args) {
        int[] arr={2,3,4,6,7,3,9,10};
        System.out.println("studying code.......");
        System.out.println(minCoins01(arr,19)); //暴力递归
        System.out.println(minCoins02(arr,19)); //记忆化搜素哦
        System.out.println(minCoins03(arr,19)); //dp
        System.out.println("review code.....");
        System.out.println(process_copy(arr,0,19));
        System.out.println(minCoins02_review(arr,19));

    }




    //=========================================下面是review的代码=======================================================================
    /**
     * 下面是review的代码
     * */

    public static int minCoins02_review(int[] arr,int rest){
        int[][] dp = new int[arr.length+1][rest+1];
        for (int i=0;i<=arr.length;i++)
            for (int j=0;j<=rest;j++){
                dp[i][j] = -2;
            }
//        for (int i=0;i<= arr.length;i++){
//            dp[i][0] = 0;
//        }
//        for(int j=1;j<=rest;j++){
//            dp[arr.length][j] = -1;
//        }
        process02_copy(arr,0,rest,dp);
        return dp[0][rest];
    }

    public static int process02_copy(int[] arr,int index,int rest,int[][] dp) {
        /**注意：这里的base case需要立刻返回，原因？？*/
        if (rest < 0) return -1;
        if (rest == 0) {
            dp[index][rest] = 0;
            return dp[index][rest];
        }
        if (index == arr.length){
            dp[index][rest]=-1;
            return dp[index][rest];
        }
        if (dp[index][rest] != -2) return dp[index][rest];

        /**如果下面这么写就会出错*/
//        if (rest < 0) return -1;
//        if (rest == 0) dp[index][rest] = 0;
//        if (index == arr.length) dp[index][rest]=-1;
//        if (dp[index][rest] != -2) return dp[index][rest];

        int p1 = process02_copy(arr, index + 1, rest, dp);
        int p2 = process02_copy(arr, index + 1, rest - arr[index], dp);
        if (p1 == -1 && p2 == -1) dp[index][rest] = -1;
        else {
            if (p1 == -1) {
                dp[index][rest] = 1 + p2;
            } else if (p2 == -1) {
                dp[index][rest] = p1;
            } else {
                dp[index][rest] = Math.min(1 + p2, p1);
            }
        }
        return dp[index][rest];
    }


    public static int process_copy(int[] arr,int index,int rest){ /*从index位置开始，还需要拼凑rest数目的钱*/
        if(rest<0) return -1;
        if(rest==0) return  0;
        if(index==arr.length) return -1;

        int p1 = process_copy(arr,index+1,rest);
        int p2 = process_copy(arr,index+1,rest-arr[index]);
        if(p1==-1 && p2==-1){
            return -1;
        }else {
            if(p1==-1){
                return p2+1;
            } else if (p2==-1) {
                return p1;
            }else {
                return Math.min(p2+1,p1);
            }
        }
    }


}
