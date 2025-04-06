package leecode_Debug.DoublePoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution_283 {
    public void moveZeroes(int[] nums) {
        int i=0;
        //找到第一个是0的数组元素
        while(i< nums.length){
            if (nums[i]==0) {
                break;
            }
            i++;
        }
        //j从i的下一个位置开始
        int j=i+1;
        while(j< nums.length){
            if (nums[j]!=0){
                nums[i++] = nums[j];
            }
                j++;
        }
        //对数组后面的数位赋值为0...这是必须进行的，否则后面的还是原来的值。
        while(i<nums.length){
            nums[i] = 0;
            i++;
        }
    }
}

public class MoveZeroEle_283 {
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

            new Solution_283().moveZeroes(nums);
            String out = integerArrayToString(nums);

            System.out.print(out);
        }
    }
}