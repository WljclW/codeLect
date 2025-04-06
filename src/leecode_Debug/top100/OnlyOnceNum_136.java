package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution_136 {
    /**
     * 法1：直接位运算。异或运算————相同为0，不同为1，因此两个相同的数二进制按位异或运算后的值就是0
     * */
//    public int singleNumber(int[] nums) {
//        int single = 0;
//        for(int num:nums){
//            single ^= num;
//        }
//        return single;
//    }


    /**
     * 法2：排序后然后依次判断
     * */
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int l=1;
        while(l<nums.length){
            if(nums[l-1]==nums[l]){
                l+=2;               //如果相等则指针直接向后跳两步。
            }else
                return nums[l-1];
        }
        return nums[nums.length-1];
    }
}

public class OnlyOnceNum_136 {
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

            int ret = new Solution_136().singleNumber(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}