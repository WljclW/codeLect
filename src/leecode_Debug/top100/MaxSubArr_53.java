package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution_53 {
    public int maxSubArray(int[] nums) {
        int max=Integer.MIN_VALUE;
        int left = 0,right=0;
        int sum = 0;
        while(right< nums.length){
            sum+=nums[right];
            right++;

            max = sum>max?sum:max;
            while(sum<0){
                sum-=nums[left];
                left++;
            }
        }
        return max;
    }
}

public class MaxSubArr_53 {
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
            int[] nums = stringToIntegerArray(line);

            int ret = new Solution_53().maxSubArray(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}