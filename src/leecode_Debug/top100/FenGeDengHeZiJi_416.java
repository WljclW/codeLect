package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution_416 {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int n:nums)
            sum+=n;
        if (sum%2!=0)
            return false;
        /**
         * 数组是有默认初始化的。。。与局部变量不一样。
         * */
        boolean[][] dp = new boolean[nums.length+1][sum/2+1];
        for (int i=0;i< nums.length+1;i++)
            dp[i][0] = true;
        for (int i=1;i< nums.length+1;i++)
            for (int j=1;j<sum/2+1;j++){
                if (j-nums[i]<0)
                    dp[i][j] = dp[i-1][j];
                else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        return dp[nums.length][sum/2];
    }
}

public class FenGeDengHeZiJi_416 {
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

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            boolean ret = new Solution_416().canPartition(nums);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}