package leecode_Debug._hot100;

import java.util.*;


/**84、*/
public class _11stack {

    /*20.
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是
    * 否有效。
    有效字符串需满足：
    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    每个右括号都有一个对应的相同类型的左括号。*/
    /**1. 如果用一个栈就需要结合其他的方法比如下面的anthor方法得到一个括号的右括号；如果用一
     * 个hasnmap就方便了*/
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c:s.toCharArray()){
            if (isLeft(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    /*相等时需要继续下一个字符的校验*/
                    if(another(stack.pop())==c) continue;
                    else return false;
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

    /*推荐使用下面的写法*/
    public boolean isValid_01(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        HashMap<Character, Character> map = new HashMap<>(){{
            put(')','(');
            put('}','{');
            put(']','[');
        }};
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (!map.containsKey(c)){ //说明是左括号直接入栈
                stack.push(c);
            }else{ //说明是右括号
                if (stack.isEmpty()||stack.peek()!=map.get(c)){ //如果栈顶不是c对应的左括号
                    return false;
                }
                stack.pop(); /**err：切记验证完后pop出栈顶。。在if条件中写也可以*/
            }
        }
        return stack.isEmpty(); /**err：注意返回值。否则“((”这样的字符串结果是错误的*/
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
    /**
     * 【关键思想】两个栈。一个存放进入的元素，一个用于存放最小值。
     *      入栈时，看一下当前的入栈元素是不是小于最小栈的栈顶(特例：如果最小栈时空
     * 的，则无条件入栈)，如果小于的话当前元素不仅入栈，还要入最小栈；
     *      出栈时，看一下当前出栈的元素是不是最小栈的栈顶元素，如果是的话，最小栈的
     * 栈顶也得出栈。
     * @author: Zhou
     * @date: 2025/6/1 13:54
     */
    /**下面是常错的一个点！！！！！！！！！*/
    /*
    ======================错误1
    输入
        ["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
        [[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]

        添加到测试用例
        输出
        [null,null,null,null,null,null,-1024,null,-1024,null,-1024]
        预期结果
        [null,null,null,null,null,null,-1024,null,-1024,null,512]
        注意判断相等使用equals方法，否则这个测试用例报错
        【说明】会发现最后的最小元素应该是512，但是错误的输出是-1024！！说明前面的pop操作并没有弹出
    最小栈中栈顶的-1024，根源就在于Integer判断相等的时候需要使用equals方法，详细见下面的pop方法中的
    代码：if (minStack.peek().equals(pop))
    =============================错误2
    java.util.EmptyStackException
          at line 103, java.base/java.util.Stack.peek
          at line 29, MinStack.getMin
          at line 74, __Driver__.__helperSelectMethod__
          at line 92, __Driver__.__helper__
          at line 113, __Driver__.main
    最后执行的输入
    ["MinStack","push","push","push","getMin","pop","getMin"]
    [[],[0],[1],[0],[],[],[]]
        【原因】if的判断条件"if (minStack.isEmpty() || val <= minStack.peek())"中的
    "val <= minStack.peek()"漏掉了等于条件
    */
    /**
     * 补充说明Integer和int涉及到运算符时的区别：
     *    1. 比较运算符只能用于基本数据类型！！因此Integer参与比较运算符时会自动拆箱成int。
     *    2. 由于Integer比较时会自动拆箱成int，因此如果此时的Integer是null，拆箱时会报空指针异常。
     *    3. “==”运算符时不同情况的区分————
     *          情况1：两个int类型数据比较，直接比较；
     *          情况2：一个int数据，一个Integer数据。比较时Integer会拆箱成int再比较
     *          情况3：两个Integer类型数据比较。直接比较引用地址是不是相等
     *    4. Integer有一个内部类IntegerCache，其中缓存了[-128,127]的所有Integer
     *【注意题目说明】pop、top 和 getMin 操作总是在 非空栈 上调用。
     * */
    class MinStack {
        Stack<Integer> allStack;
        Stack<Integer> minStack;
        public MinStack() {
            allStack = new Stack<Integer>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            allStack.push(val);
            /**err：小于等于栈顶的时候，都需要进入最小栈！！不能漏掉等于，否则一个等于minStack栈顶
             * 的时候，到底应不应该出栈呢？就没有标准了。
             * */
            if (minStack.isEmpty() || val <= minStack.peek()) { /**err：两个条件缺一不可 并且 条件的关系是"||"关系，否则可能测试用例报错“java.util.EmptyStackException”*/
                minStack.push(val);
            }
        }

        public void pop() {
            Integer pop = allStack.pop();
            if (minStack.peek().equals(pop)){ /**▲err：注意这里比u下使用equals。否则或有个用例“-1024”不对*/
                minStack.pop();
            }
        }

        public int top() {
            return allStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }


    /*394.
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注
    意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的
    输入。
    * */
    /**
     * 【建议】看K神的题解：https://leetcode.cn/problems/decode-string/solutions/19447/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/?envType=study-plan-v2&envId=top-100-liked
     *      重要的是看K神题解中的动图，理解整个过程
     * 【提示】思考一下这个题的过程中创建了多少字符串的问题
     */
    /*
    【注意】计算multi的公式，如果写成了“digit = 10 * digit + digit”，会出错如下：
            s =
            "3[a]2[bc]"
            输出
            ""
            预期结果
            "aaabcbc"
    * */
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int digit = 0;
        LinkedList<Integer> stack_digit = new LinkedList<>();
        LinkedList<String> stack_str = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c >= '0' && c <= '9') //①碰到数字，计算multi
                digit = 10 * digit + Integer.parseInt(c + "");
            else if (c == '[') { //碰到左括号
                /*只要碰到左括号，说明接下来要重新计算了。因此将目前的数字和res分别入栈；
                然后将multi重置为0，res重置为新建状态
                * */
                stack_digit.push(digit);
                stack_str.push(res.toString());
                digit = 0;
                res = new StringBuilder();
            } else if (c == ']') { //碰到右括号
                /*【说明】这个题目的这个解法来说，只要碰到]，此时multi的值必然就是0；并且res就是
                当前这组括号中的字符串————潜藏规则。因此从数字栈stack_digit中弹出数字，将res添加
                对应的次数，然后在前面拼接上从res_stack弹出的字符串
                【tip】其中从stack_digit中弹出的数字就是“现在这对括号”之前的那个数字————暗示着括号
                内的字符串重复的次数，而括号内的字符串其实就是此时此刻res的值
                * */
                StringBuilder tmp = new StringBuilder();
                Integer cur_digit = stack_digit.pop(); /**err：【注意】这里必须用新的变量来记录弹出的数字，不能污染digit变量*/
                for (int i = 0; i < cur_digit; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stack_str.pop() + tmp);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }


    /*739.
    给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第
     i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    * */
    /**找右边第一个比当前位置的数大的索引。。因此小于栈顶的入栈，大于栈顶(说明栈顶对应的结果已经计算出来了)的时候栈顶索引要出栈。
     * 【强烈建议】使用解法4！！即dailyTemperatures04方法
     * 【说明】
     *      1. 如果解决题目时要求栈中的数严格递减或递增————
     *              如果没有相同元素，好说，直接入栈 或者 出栈生成信息；
     *              如果待添加有重复元素，则每一个位置需要放一个链表，存放所有元素值相等的index。大于小于栈顶都会操作（即当前
     *           元素添加进链表入栈 或者 栈顶的链表所有元素出栈），等于栈顶的时候，将当前元素添加到栈顶的哪个链表
     *      2. 【重要说明】
     *          针对这个题，其实并不要求栈内的元素严格单调递增 或者 递减。等于栈顶index对应元素值的时候直接入栈就可以了
     *      3. 建议使用第二中种解法*/
    //解法1
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
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
                    while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                        /*while循环里面有出栈操作，因此循环条件需要注意判断栈*/
                        Integer cur = stack.pop();
                        res[cur] = i-cur;
                    }
                    /*除了while循环需要将当前元素入栈*/
                    stack.push(i);
                }
            }else{
                stack.push(i);
            }
        }
        return res;
    }

    public int[] dailyTemperatures02(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            //这里的入栈的判断可以省略。。。。省略后即为解法2
            if (stack.isEmpty() || temperatures[stack.peek()] >= temperatures[i]) {
                stack.push(i);
                continue;
            }
            /**每一次入栈之前保证合法，如何保证？？将栈顶不合规的数据依次弹出，并生成res信息。
             * 【注意】栈顶可能不止弹出一次，因此需要使用while循环*/
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer cur = stack.pop();
                res[cur] = i - cur;
            }
            stack.push(i);
        }

        return res;
    }

    public int[] dailyTemperatures03(int[] temperatures) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty()||temperatures[stack.peek()]>=temperatures[i]){
                stack.push(i);
            }else{
                while(!stack.isEmpty()&&temperatures[stack.peek()]<temperatures[i]){
                    Integer cur = stack.pop();
                    res[cur] = i-cur;
                }
                stack.push(i);
            }
        }

        return res;
    }

    /**[建议使用]：求解每日温度问题更好的解。
     *    求解每日温度题目 并不要求栈内索引对应的值严格单调，相邻的数相等也是可以的！————
     * 因此每一个位置存放索引就行，不用存放链表*/
    //解法4，这其实是一种精简的写法。精简的原型见dailyTemperatures02、dailyTemperatures03
    public int[] dailyTemperatures04(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>(); //LinkedList也可以作为栈使用
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            /*只要当前值严格大于栈顶索引对应的值，就弹出栈顶并生成栈顶索引的信息；
             * 经过while保证"当前的场景"符合单调栈的规则，然后当前索引入栈*/
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }



    /*84.
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
    * */
    /**
     * 【建议的解法】使用下面的largestRectangleArea2
     * 【题目的本质】————针对每一个位置i，找到左右两边最近的小于heights[i]的位置。就得到了该位置所能绘制出的最大矩形，等
     * 每一个位置的值都求出来了，全局的最大矩形也就得到了。
     *    这个题也可以用一维数组来表示，完整的可能情况：枚举每一个位置，比如该位置的高度是m，从该位置向左右两边查找第一个
     * 小于m的位置，分别即为L和R，则该位置能绘制的最大矩形就是长*高————(R-L-1)*m.
     * 1. 因此解的关键在于：找到每一个位置，左右距离最近的比当前位置的数即heights[i]小的位置；
     * 2. 方法分为两种：
     *       方法1：第一次遍历找到任何一个数左边最近 的小于值；第二次遍历找到任何一个数右边最近 的小于值；第三次遍历
     *    计算每一个位置能画出的最大矩形(长就是两个小于值位置的间隔，高就是heights数组这个位置的值)。
     *            空间方面：一个栈；一个left数组记录左边最近的小于值 的索引；一个right记录右边最近的小于值的索引
     *       方法2：使用一次遍历，见方法largestRectangleArea2，遍历到height.length的时候，将高度视为0，此时栈中所有
     *   索引位置的高度都一定大于0，因此根据“递增栈”的特性可知，所有的所有都会出栈，并生成对应位置的面积信息——————编码详见
     *   largestRectangleArea2方法.
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

    /**由于要找到左右两边最近且小于的数，因此只要当前的数大于栈顶对应的数就入栈*/
    public int largestRectangleArea1(int[] heights){
        int max = Integer.MIN_VALUE;
        LinkedList<Integer> stack = new LinkedList<>();
        /*
        * 遍历每一个位置，对于来到的每一个位置：
        *    1.什么时候入栈？
        *       只要栈是空，无条件入栈；
        *       栈不是空，但是当前元素大于栈顶。说明当前位置不是小于栈顶的位置，不用出栈
        *    2.什么时候出栈？怎么出？
        *       栈肯定不是空，同时栈顶位置的值必然大于当前遍历到的位置。————即找到了栈顶位置右边最近的小于值就是当前遍历的元素
        * */
        for (int i=0;i<heights.length;i++){
            if(stack.isEmpty() || heights[i]>=heights[stack.peek()]){ /*如果当前的数不小于栈顶的数，入栈*/
                stack.push(i);
            }else{
                while(!stack.isEmpty()&&heights[i]<heights[stack.peek()]){ /*只要当前的数小于栈顶对应的数，则”栈顶元素“的信息就生成了*/
                    Integer peek = stack.pop();
                    int L = stack.isEmpty()?-1: stack.peek();
                    max = Math.max(max,heights[peek]*(i -L-1)); //i当前位置也是右边所求的数；L是弹出后新栈顶的数，也即左边所求的数
                }
                stack.push(i); /**▲err：注意如果没有入栈，会导致计算出的面积永远是0————这是单调栈中常见的马虎*/
            }
        }
        /*清空栈。依次弹出并生成对应的信息*/
        while(!stack.isEmpty()){
            Integer cur = stack.pop();
            int L = stack.isEmpty()?-1: stack.peek();
            max = Math.max(max,heights[cur]*(cur-L-1));
        }
        return max;
    }

    /*【最好的解法】
    * 【关键】循环i能取到height.length，height.length理论就越界了，但是我们提前判断，如果来到了末
    * 尾位置，就将高度当为0，因此此时栈中所有的元素均会出栈————很好的避免了两次遍历分别寻找左边 和
    * 右边的边界
    * */
    public int largestRectangleArea2(int[] heights) {
        Deque<Integer> stack = new LinkedList<>(); // 存储索引
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            /**当i=n时，就相当于清理最后栈中残留的索引。。。
             *      因为任何一个高度都大于0，索引当i来到n的时候，我们将当前的高度赋值为0，根
             * 据"currHeight<height[stack.peek()]"可知，此时stack中所有的索引都会出栈，并计
             * 算出可以绘制的矩形的面积
             * * */
            int currHeight = (i == n) ? 0 : heights[i]; // 处理最后一个元素

            // 只要满足 当前高度小于栈顶元素，弹出栈顶并计算面积
            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()]; //矩形的高：弹出来栈顶index对应的高度
                int width = stack.isEmpty() ? i : i - stack.peek() - 1; //矩形的宽，注意此时栈顶的stack.peek()位置的高度是不满足的
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i); /**err：当前索引入栈。。漏掉时得出的结果总是0！！！（反馈的测试用例就是这样）*/
        }

        return maxArea;
    }


    public static void main(String[] args) {
        _11stack thisClass = new _11stack();

        System.out.println(thisClass.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(thisClass.largestRectangleArea1(new int[]{2,4}));

        System.out.println(thisClass.isValid("()[]{}"));
    }
}
