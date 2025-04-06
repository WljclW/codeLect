package leecode_Debug.DoublePoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution_189 {
    public void rotate(int[] nums, int k) {
//        int tmp = 0;
//        int start = 0;
//        k = k>=nums.length?0:k;
//        for(int j=nums.length-k;j<nums.length;j++){
//            int i = j-1;
//            while(i>=start){
//                tmp = nums[i+1];
//                nums[i+1] = nums[i];
//                nums[i] = tmp;
//                i--;
//            }
//            start++;
//        }

    }
}

public class LunZhuanEle_189 {
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
            int k = Integer.parseInt(line);

            new Solution_189().rotate(nums, k);
            String out = integerArrayToString(nums);

            System.out.print(out);
        }
    }
}