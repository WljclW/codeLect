package leecode_Debug.dandiaozhan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Solution84 {
    /**
     * 分析：
     *      实际就是找到左右两边小于当前数且最近的索引。。知道了这两个索引值就知道了以当前索引的数组值heights[index]为高，最
     * 多可以画多宽的矩形，也就知道了在当前index位置最大的举行面积能达到多少。
     * */
    public int largestRectangleArea(int[] heights) {
        /**
         * 方法1：栈中的元素是链表，存放某个相等的值
         * */
//        int max = 0;
//        Stack<LinkedList<Integer>> stack = new Stack<>();
//        for (int i=0;i<heights.length;i++){
//            while(!stack.isEmpty() && heights[i]<heights[stack.peek().get(0)]){
//                LinkedList<Integer> popList = stack.pop();
//                for (int index:popList){
//                    int left = stack.isEmpty()?-1:stack.peek().get(stack.peek().size()-1);
//                    max = (heights[index]*(i-left-1)) >=max ?heights[index]*(i-left-1):max;
//                }
//            }
//            if (!stack.isEmpty() && heights[i]==heights[stack.peek().get(0)]){
//                stack.peek().add(i);
//            }else{
//                LinkedList<Integer> ls = new LinkedList<>();
//                ls.add(i);
//                stack.push(ls);
//            }
//        }
//        while(!stack.isEmpty()){
//            LinkedList<Integer> popList = stack.pop();
//            for (int index:popList){
//                int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
//                max = (heights[index]*(heights.length-1-left)) >=max ?heights[index]*(heights.length-1-left):max;
//            }
//        }
//        return max;



        /**
         * 方法2：栈中直接存放索引值，相等的索引值就看成不一样，挨个入栈。。。虽然不合理，但是这个题不影响结果
         * */
        int ans=0,n=heights.length;
        Stack<Integer> stk = new Stack<>();
        for(int i=0;i<n;++i){
            while(!stk.empty()&&heights[i]<heights[stk.peek()]){
                int cur=stk.peek();
                stk.pop();
                int pre=stk.empty()?-1:stk.peek();
                ans=Math.max(ans,heights[cur]*(i-pre-1));
            }
            stk.push(i);
        }
        while(!stk.empty()){
            int cur=stk.peek();
            stk.pop();
            int pre=stk.empty()?-1:stk.peek();
            ans=Math.max(ans,heights[cur]*(n-pre-1));
        }
        return ans;
    }
}

public class MaxArea_84 {
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
            int[] heights = stringToIntegerArray(line);

            int ret = new Solution84().largestRectangleArea(heights);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
