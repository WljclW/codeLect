package leecode_Debug.SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class Solution_209 {
    public int minSubArrayLen(int target, int[] nums) {
        int left =0;
        int right = 0;
        int sum = 0;
        int min = nums.length+1;
        while(left<nums.length && right<nums.length){
            sum+=nums[right];
            right++;
            /**
             * 出错的位置————while循环
             * */
//            if (sum>=target){         //这里必须是while循环，为什么？？
            while(sum>=target){
                min = Math.min(min,right-left);
                sum -= nums[left];
                left++;
            }
        }
        return min == nums.length+1?0:min;
    }
}

public class MinLength_209 {
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
            int target = Integer.parseInt(line);
            line = in.readLine();
            int[] nums = stringToIntegerArray(line);

            int ret = new Solution_209().minSubArrayLen(target, nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}