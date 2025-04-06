//package leecode_Debug.DynamicPlan;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//
//class Solution_70 {
//    /**
//     *  暴力递归：超出时间访问限制
//     */
//    public int climbStairs(int n) {
//        return process(n,0);
//    }
//
//    private int process(int n, int cur) {
//        int[] dp = new int[n+1];
//        dp[n-1] = 1;
//        dp[n-2] = 1;
//        for (int i=n-3;i>=0;i--)
//            dp[i] = dp[i+1] + dp[i+2];
//        return dp[cur];
//    }
//}
//
//public class PaLouTi_70 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            int n = Integer.parseInt(line);
//
//            int ret = new Solution_70().climbStairs(n);
//
//            String out = String.valueOf(ret);
//
//            System.out.print(out);
//        }
//    }
//}