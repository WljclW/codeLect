package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution_34 {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = findLeft(nums,target);
        res[1] = findRight(nums, target);
        return res;
    }

    public int findLeft(int[] nums, int target){
        int left=0,right= nums.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>target){
                right = mid-1;
            }else if (nums[mid]<target){
                left = mid+1;
            }else if(nums[mid]==target){
                right = mid-1;
            }
        }
        if (left== nums.length) return -1;
        //为什么不相等的时候就知道肯定没有呢？？
        return nums[left]==target?left:-1;
    }

    public int findRight(int[] nums, int target){
        int left=0,right= nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if (nums[mid]<target){
                left = mid+1;
            }else if (nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]==target){
                left = mid+1;
            }
        }
        /**
         * 除了循环就表示left=right+1，此时需要判断右边界的值是不是等于target，即nums[right]==target？
         *      而right=left-1,因此可以写为nums[left-1]==target??
         * */
        if (left-1<0) return -1;
        return nums[left-1]==target?left-1:-1;
    }
}

public class StartEnd_34 {
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

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            int[] ret = new Solution_34().searchRange(nums, target);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}