package leecode_Debug._hot100;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

public class stack {

    /*20.
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是
    * 否有效。
    有效字符串需满足：
    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    每个右括号都有一个对应的相同类型的左括号。*/
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c:s.toCharArray()){
            if (isLeft(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    return another(stack.pop())==c;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isLeft(Character c){
        return c=='(' || c=='{' || c=='[';
    }

    public Character another(Character c){
        if(c=='('){
            return ')';
        }else if(c=='{'){
            return '}';
        }else{
            return ']';
        }
    }



    /*155.
    设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

    实现 MinStack 类:

    MinStack() 初始化堆栈对象。
    void push(int val) 将元素val推入堆栈。
    void pop() 删除堆栈顶部的元素。
    int top() 获取堆栈顶部的元素。
    int getMin() 获取堆栈中的最小元素。
    * */
//    class MinStack {
//
//        public MinStack() {
//
//        }
//
//        public void push(int val) {
//
//        }
//
//        public void pop() {
//
//        }
//
//        public int top() {
//
//        }
//
//        public int getMin() {
//
//        }
//    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


    /*394.
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注
    意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的
    输入。
    * */
//    public String decodeString(String s) {
//
//    }


    /*739.
    给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第
     i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    * */
    /**找右边第一个比当前位置的数大的索引。。因此小于栈顶的入栈，大于栈顶(说明栈顶对应的结果已经计算出来了)的时候栈顶索引要出栈*/
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(temperatures[0]);
        for (int i=1;i<temperatures.length;i++){
            /*
            整体上分为两种情况：
                栈如果是空，当前索引直接入栈；
                栈如果是空，继续判断当前元素 和 栈顶索引在temperatures对应元素的值 的关系
            * */
            if(!stack.isEmpty()){
                if(temperatures[i]<=temperatures[stack.peek()]){
                    stack.push(i);
                }else{
                    while(temperatures[i]>temperatures[stack.peek()]){
                        Integer cur = stack.pop();
                        res[cur] = i-cur;
                    }
                }
            }else{
                stack.push(i);
            }
        }

        return res;
    }


    /*84.
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
    * */
    /**
     *    这个题也可以用一维数组来表示，完整的可能情况：枚举每一个位置，比如该位置的高度是m，从该位置向左右两边查找第一个
     * 小于m的位置，分别即为L和R，则该位置能绘制的最大矩形就是长*高————(R-L-1)*m.
     *    因此解的关键在于：找到每一个位置，左右距离最近的比当前位置的数小的索引；
     *    方法分为两种：
     *       方法1：第一次遍历找到任何一个数左边最近 的小于值；第二次遍历找到任何一个数右边最近 的小于值；第三次遍历
     *    计算每一个位置能画出的最大矩形(长就是两个小于值位置的间隔，高就是heights数组这个位置的值)。
     *            空间方面：一个栈；一个left数组记录左边最近的小于值 的索引；一个right记录右边最近的小于值的索引
     *       方法2：
     * */
    public int largestRectangleArea(int[] heights) {
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        LinkedList<Integer> stack = new LinkedList<>();
        int res = Integer.MIN_VALUE;
        for (int i=0;i<heights.length;i++){
            if(stack.isEmpty() || heights[i]>heights[stack.peek()]){
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty()&& heights[i]<heights[stack.peek()]){
                Integer cur = stack.pop();
                right[cur] = i;
            }
        }
        for (int i=heights.length-1;i>=0;i--){
            if(stack.isEmpty() || heights[i]>heights[stack.peek()]){
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty()&& heights[i]<heights[stack.peek()]){
                Integer cur = stack.pop();
                left[cur] = i;
            }
        }
        for (int i=0;i<heights.length;i++){
            int L = left[i];
            int R = right[i]==0?heights.length-1:right[i];
            int cur = heights[i]*(R-L-1);
            res = Math.max(cur,res);
        }
        return res;
    }


    public int largestRectangleArea1(int[] heights){
        int max = Integer.MIN_VALUE;
        LinkedList<Integer> stack = new LinkedList<>();
        int[] left = new int[heights.length];
        Arrays.fill(left,-1);
        int[] right = new int[heights.length];
        /*
        * 遍历每一个位置，对于来到的每一个位置：
        *    1.什么时候入栈？
        *       只要栈是空，无条件入栈；
        *       栈不是空，但是当前元素大于栈顶。说明当前位置不是小于栈顶的位置，不用出栈
        *    2.什么时候出栈？怎么出？
        *       栈肯定不是空，同时栈顶位置的值必然大于当前遍历到的位置。————即找到了栈顶位置右边最近的小于值就是当前遍历的元素
        * */
        for (int i=0;i<heights.length;i++){
            if(stack.isEmpty() || heights[i]>=heights[stack.peek()]){
                stack.push(i);
            }else{
                while(!stack.isEmpty()&&heights[i]<heights[stack.peek()]){
                    Integer peek = stack.pop();
                    int L = stack.isEmpty()?-1: stack.peek();
                    max = Math.max(max,heights[peek]*(i -L-1));
                }
                stack.push(i);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        stack thisClass = new stack();

        System.out.println(thisClass.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(thisClass.largestRectangleArea1(new int[]{2, 1, 5, 6, 2, 3}));

    }
}
