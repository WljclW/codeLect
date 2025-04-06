package leecode_Debug.dandiaozhan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Solution739 {
    /**
     * 本质就是确定右边最近且大于当前元素的另外元素距离当前元素的距离
     * */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<temperatures.length;i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                Integer popIndex = stack.pop();
                res[popIndex] = i-popIndex;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            Integer popIndex = stack.pop();
            res[popIndex] = 0;
        }
        return res;
    }
}

public class NextBetterTemperature_739 {
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
            int[] temperatures = stringToIntegerArray(line);

            int[] ret = new Solution739().dailyTemperatures(temperatures);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}