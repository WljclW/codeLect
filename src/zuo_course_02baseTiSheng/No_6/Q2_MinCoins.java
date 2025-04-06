package zuo_course_02baseTiSheng.No_6;

public class Q2_MinCoins {

    /**
     * 版本1：初始的暴力递归的方法，没有任何优化。
     * */
    public static int minCoins01(int[] arr,int aim){  //硬币存放在arr中，aim是最终要达到的面额
        return process(arr,0,aim);          //主函数根据题意调用递归函数。
    }

    private static int process(int[] arr, int index, int rest) {    //rest就是目前还需要凑多少钱才能到aim
        /**
         * 【注】无效解不能return 0.
         * */
        /**
         * 不管那个题，递归函数要先处理无效解（如果有的话），base-case，普遍情况。
         * */
        if (rest<0){            //表示走到当前位置走错了，因为钱多了，导致rest<0了。
            return -1;
        }
        if (rest==0){           //找到了一种可行方案
            return 0;
        }

        //下面是rest>0的情况
        if (index==arr.length){         //没有位置了，也就是说后面没硬币可选了，但是aim没有凑齐。。表示这种方案是错的
            return -1;
        }
        //rest>0 并且 还有硬币
        int p1 = process(arr,index+1,rest);     //表示如果 选择不要index位置的硬币。。也就是从index+1位置开始，凑rest面额
        int p2 = process(arr,index+1,rest-arr[index]);          //表示如果 选择要index位置的硬币。。也就是从rest+1位置开始，凑rest-arr[index]面额。
        /**
         *  if (p1==-1&&p2==-1)
         *             return -1;
         *  这个if代码块是什么意思？？
         *
         * */
        if (p1==-1&&p2==-1)         //如果  不论要不要index位置的货币，返回的都是-1，就表示当前位置无可行方案
            return -1;
        else{
            /**
             * 如果P1有效则返回p1.
             * 如果p2有效则返回p2。
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
     * 记忆搜索优化
     * */
    public static int minCoins02(int[] arr,int aim){  //硬币存放在arr中，aim是最终要达到的面额
        //声明二维表做缓存，并进行初始化
        int[][] dp = new int[arr.length+1][aim+1];
        for (int i=0;i<=arr.length;i++){
            for (int j=0;j<=aim;j++)
                dp[i][j] = -2;              //0表示正常返回，-1表示此路不能走。。。-1和0都被占用，因此这里初始化选择-2.
        }
        return process01(arr,0,aim,dp);
    }

    private static int process01(int[] arr, int index, int rest,int[][] dp) {
        /**
         * 【注】无效解不能return 0.
         * */
        if (rest<0){            //表示走到当前位置走错了，因为钱多了？导致rest<0了。
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
        //rest>0 并且 还有硬币
        int p1 = process(arr,index+1,rest);     //表示如果 选择不要index位置的硬币。。也就是从index+1位置开始，凑rest面额
        int p2 = process(arr,index+1,rest-arr[index]);          //表示如果 选择要index位置的硬币。。也就是从rest+1位置开始，凑rest-arr[index]面额。
        if (p1==-1&&p2==-1)
            return -1;
        else{
            /**
             * 进入else表示p1和p2'不都是'-1.
             *      如果P1有效则返回p1.
             *      如果p2有效则返回p2。
             *      如果两个都有效则作决策，决策就是选出目前硬币最小的方案
             * */
            if (p1 == -1)       //表示可能性1无效，也就是说  选择不要硬币index这条路走不通；
//                return 1+p2;
                dp[index][rest]=p2+1;
            else if (p2 == -1)       //表示可能性2无效，也就是说，选择要index位置的硬币这条路走不通；
//                return p1;
                dp[index][rest]=p1;
            else
                dp[index][rest] = Math.min(p1,p2+1);
        }
        return dp[index][rest];             //最后统一的返回的dp[index][rest].
    }

    /**
     * 严格表结构
     * */
    public static int minCoins03(int[] arr,int aim){
        int length = arr.length;
        int[][] dp = new int[length+1][aim+1];
        for (int i=0;i<=length;i++)               //rest等于0的情况，表示找打了一种可行方案
            dp[i][0] = 0;
        for (int i=1;i<=aim;i++)            //表示当前的步骤，没有找到可行方案
            dp[length][i] = -1;
        for (int index=length-1;index>-1;index--)
            for (int rest=1;rest<=aim;rest++){
                int p1 = dp[index+1][rest];             //因为我们是从表的下往上求值的，因此下一行的值已经求过了。

                //p2，是下一行当前位置列的前面的第arr[index]列，这个可能不在有效范围，因此p2需要做额外的判断
                int p2 = -1;        //p2的值先默认是-1.
                if (rest-arr[index]>=0)                     //如果下一行前面的第arr[index]列没有小于0，则表示有效
                    p2 = dp[index-1][rest-arr[index]];
                if (p1 == -1 && p2 == -1){
                    dp[index][rest] = -1;
                }else{
                    if(p1==-1) dp[index][rest]=p2+1;
                    else if(p2==-1) dp[index][rest]=p1;
                    else dp[index][rest]=Math.min(p1,p2+1);
                }
            }
        return dp[0][aim];              //这个就是我们画出表后，找到的要求的格子(即最终位置)是哪一个？就返回哪一个位置的值。
    }


    public static void main(String[] args) {
        int[] arr={2,3,4,6,7,3,9,10};
        System.out.println(minCoins01(arr,1));
    }
}
