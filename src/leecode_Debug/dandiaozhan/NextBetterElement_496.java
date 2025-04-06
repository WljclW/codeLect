package leecode_Debug.dandiaozhan;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /**
         * 题目就是找右边最近且大于当前元素的值，不存在返回-1.
         *      ①不用考虑相等的时候用不用链表，不值得用，相等的看为两个元素，直接进栈就可以。因此每一个栈元素用基本int的包装类就可以
         *      ②这个题中甚至入栈的不是索引就可以，直接入栈数组元素。。但是要明确数组元素包含更多的信息————既包含了位置信息、也包含了
         *  该位置的值的信息。
         * */
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums1.length];
        for (int i=0;i<nums2.length;i++){
            while(!stack.isEmpty() && nums2[i]>nums2[stack.peek()]){
                Integer popIndex = stack.pop();
                for (int j=0;j<nums1.length;j++){
                    if (nums1[j] == nums2[popIndex])
                        res[j] = nums2[i];
                }
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int popIndex = stack.pop();
            for (int j=0;j<nums1.length;j++){
                if (nums1[j] == nums2[popIndex])
                    res[j] = -1;
            }
        }
        return res;
    }
}

public class NextBetterElement_496 {
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
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);

            int[] ret = new Solution496().nextGreaterElement(nums1, nums2);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}
