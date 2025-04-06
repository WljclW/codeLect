package leecode_Debug.dandiaozhan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int j=0;j<height.length;j++){
            while(!stack.isEmpty() && height[j] > height[stack.peek()]){
                Integer popIndex = stack.pop();
                if (stack.isEmpty())
                    break;
                Integer left = stack.peek();
                int curWidth = j-popIndex;
                int cur = Math.min(height[left],height[j])-height[popIndex];
                sum += cur * height[popIndex];
            }
            stack.push(j);
        }
        return sum;
    }
}

public class YuShuiLiang_42 {
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
            int[] height = stringToIntegerArray(line);

            int ret = new Solution().trap(height);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}