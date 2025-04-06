package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Solution494 {
    int count = 0;
        public int findTargetSumWays(int[] nums, int target) {
            tracBack(nums,0,target);
            return count;
        }

        private void tracBack(int[] nums, int start,int remain) {       //remain表示还需要多少凑出target。
            if(start == nums.length){
                if(remain==0)
                    count++;
                return;
            }
            //给当前元素nums[start]选择减号，则后面还需要凑够remain+nums[start]的数，更新remain参数
            remain+=nums[start];
            tracBack(nums,start+1,remain);
            //撤销选择
            remain-=nums[start];

            //给当前元素nums[start]选择加号，则后面还需要凑够remain-nums[start]的数，更新remain参数
            remain-=nums[start];
            tracBack(nums,start+1,remain);
            remain+=nums[start];
        }
    }

public class Q494 {
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
            line = in.readLine();
            int target = Integer.parseInt(line);

            int ret = new Solution494().findTargetSumWays(nums, target);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}