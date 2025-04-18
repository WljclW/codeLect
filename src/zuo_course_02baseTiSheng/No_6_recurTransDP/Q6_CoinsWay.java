package zuo_course_02baseTiSheng.No_6_recurTransDP;

//与2的区别在于每一种面值的货币有无限并且求的是方案数。

public class Q6_CoinsWay {
    //初始版本：暴力递归
    public static int way(int[] arr, int aim){
        return process(arr, 0, aim);}               //从0位置的货币就开始往后找

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length)
            return rest==0?1:0;
        int ways = 0;
        for (int zhang=0;arr[index]*zhang<rest;zhang++){
            ways += process(arr,index+1,rest-arr[index]*zhang);
        }
        return ways;
    }



    //表结构版本
    public static int way1(int[] arr, int aim){
        //特殊情况处理
        if (arr == null || arr.length==0)
            return 0;
        //因为有两个可变参数，因此创建二维表结构
        int[][] dp = new int[arr.length + 1][aim + 1];
        //根据bace-case给表格赋值
        dp[arr.length][0] = 1;

        //循环遍历表格，计算出所有的格子
        for (int index = arr.length-1;index>0;index--){
            for (int rest = 0;rest<=aim;rest++){
                /**
                 *      下面注释的部分是二维表中未优化的版本————就是不断地试验当前面值货币拿一张、两张...
                 * 换句话说就是一个枚举的过程
                 * */
//                int ways = 0;
//                for (int zhang=0;zhang*arr[index]<rest;zhang++){
//                    ways+=dp[index][rest-arr[index]*zhang];
//                }
//                dp[index][rest] = ways;
                /**
                 * 下面是斜率优化的结果：具体是怎么发现的，见打印纸的笔记。
                 * */
                dp[index][rest] = dp[index + 1][rest];
                if (rest-arr[index]>=0){
                    dp[index][rest] = dp[index][rest-arr[index]];
                }
            }
        }
        return dp[0][aim];            // dp[0][aim]就是画出表后，我们发现它就是要求的那一个位置。
    }
}
