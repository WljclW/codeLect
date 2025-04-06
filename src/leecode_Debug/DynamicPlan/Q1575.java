package leecode_Debug.DynamicPlan;//package leecode_Debug.DynamicPlan;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution1575 {
    // int res = 0;
    int[][] dp;
    int mod = 1000000007;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        dp = new int[locations.length][fuel+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        dp(locations,start,finish,fuel);
        return dp[start][fuel];
    }
    public int dp(int[] locations, int start, int finish, int fuel){
        if(fuel<0){
            return -1;
            // return dp[start][fuel];
        }
        if(dp[start][fuel] != -1){
            return dp[start][fuel];
        }

        if(start == finish){
            dp[start][fuel] = 1;
            // return dp[start][fuel];
        }
        else{
            for(int i=0;i<locations.length;i++){
                int tmp = dp(locations,i,finish,fuel-Math.abs(locations[i]-locations[start]));
                if(i!=start && tmp!=-1){
                    dp[start][fuel] += dp(locations,i,finish,fuel-Math.abs(locations[i]-locations[start]));
                }
                dp[start][fuel] %= mod;
            }

        }

        return dp[start][fuel];
    }
}
//class Solution1575 {
//    // int res = 0;
//    int[] dp;
//    public int countRoutes(int[] locations, int start, int finish, int fuel) {
//        dp = new int[locations.length];
//        dp(locations,start,finish,fuel);
//        return dp[0];
//    }
//    public int dp(int[] locations, int start, int finish, int fuel){
//        if(fuel<0)
//            return 0;
//        if(fuel == 0 && start!=finish)
//            return 0;
//        if(finish == start){
//            return 1;
//        }
//        for(int i=0;i<locations.length;i++){
//            if(i!=start);
//            dp[i] = dp[i] + dp(locations,i,finish,fuel-Math.abs(locations[i]-locations[start]));
//        }
//        return 0;
//    }
//}
//
public class Q1575 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] locations = stringToIntegerArray(line);
            line = in.readLine();
            int start = Integer.parseInt(line);
            line = in.readLine();
            int finish = Integer.parseInt(line);
            line = in.readLine();
            int fuel = Integer.parseInt(line);

            int ret = new Solution1575().countRoutes(locations, start, finish, fuel);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}