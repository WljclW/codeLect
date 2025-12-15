package leecode_Debug._hot100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 【易错】
 *    1. 模板中for循环的调用时，一般是和当前i有关系，而不和形参的index有关系。原因：因为for循环是挨个看哪一个选择可以做，
 *          这一步做出选择之后往往需要继续后面的步骤，因此往往要从i+1之后开始继续研究。比如：
 *      ①见combinationSumTrace的for循环，递归调用时需要使用i而不是index，使用index的话会有重
 *          复的现象；
 *      ②再比如subsetsBack方法中的for循环中递归调用时要从i+1开始，而不是index+1！！！
 *    2. 回溯的模板中————
 *          for循环负责调用树中某一层的选择和操作(即在某一层，看看节点可能是哪些值)；
 *          for循环中递归的调用实现的是从当前节点向更深的一层（下一层）的继续研究(通俗理解就是：for循环我们确定了某个节点的
 *       值是什么，递归调用时我们继续研究它的孩子节点)。
 *    3. 关于这里去重的逻辑，需要详细理解”树层去重“ 和 ”树枝去重“，可参见方法combinationSum2
 *          见网址：https://programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html#%E6%80%9D%E8%B7%AF
 *      3.1 去重的通用代码
 *          //①树层去重————保证某一层不会从相同的元素开始继续向下一层研究。比如：现在path=[4,6]，nums排序后是[4,5,6,7,7,9....]，
 *          //表示现在研究第三层，假设第一次选取第一个7（索引为3的数7），路径变为[4,6,7]继续向第4层研究；这里递归结束后继续回到path=[4,6]，
 *          //此时就不能继续研究7（索引为4的7），因为它满足下面的条件。。。这就是”数层去重“
 *          if (i > 0 && candidates[i] == candidates[i - 1] && !usedCombinationSum2[i - 1]) {
 *                 continue;
 *          }
 *
 *          //②树枝去重————保证从某一分支继续向下一层研究时不会选相同的元素。比如：现在path=[4,6]，nums排序后是[4,5,6,7,7,9...]，
 *          //表示现在研究第三层，假设第一次选取第一个7（索引为3的数7），路径变为[4,6,7]继续向第4层研究。。在第4层中其实可以在[7,9...]
 *          //这些数中继续选择，但是此时并不能选择开始的7（原始数组索引为4的数7），因为used数组中used[3]==true，并且索引为4的也是7，满
 *          //足下面的if条件，因此继续下一轮for循环，跳过了索引为4的7，从索引为5的9开始继续向后研究剩下的数
 *          if (i > 0 && candidates[i] == candidates[i - 1] && usedCombinationSum2[i - 1]) {
 *                 continue;
 *          }
 *      3.2 【这一点很重要】全排列Ⅱ去重的问题中used[i-1]==true也可完成去重的底层原理：
 *          https://programmercarl.com/0047.%E5%85%A8%E6%8E%92%E5%88%97II.html#%E6%8B%93%E5%B1%95
 *      3.3 【需要测试】组合总和Ⅱ问题中，是不是3.1的理论就不行了？？验证一下，确实不行！！，见方法注释
 * 【变量的声明位置】
 *      1. 对于最终的返回结果，这里通常是List<String>、List<List<String>>这种类型。。。建议声明为全局变量，这种变量是只增不减的。
 *      2. 其他的不可变的变量建议声明为全局变量，比如力扣17题中，map是不变的，因此建议将map声明为全局变量。
 *      3. 除了1、2两点之外的变量，建议将变量写在形参的位置。。。。比如力扣17题中的StringBuilder，虽然声明为全局变量代码看起来不用
 *    动，但是在一些情况下会存在严重的问题，比如：多个实例同时调用的时候、并发的时候，导致问题的根本原因就是StringBuilder是全局变量，但
 *    是在回溯的过程中会改变，因此任何实例走完一遍后需要将StringBuilder恢复到初始状态，从而保证不会玷污下次调用！！！————综上，对于类似
 *    的这种 在调用中会发生改变的量并且深度绑定本次调用过程 的量，强烈建议声明在形参的位置。
 * 【组合问题 、排列问题、子集问题的区别】
 *      1. 一般来说：组合问题和排列问题是在树形结构的“叶子节点”上收集结果，而子集问题就是取树上“所有节点”的结果。
 *      2. 组合问题可以有重复值，但是[1,2,2]和[2,1,2]属于同样的集合。因此要排序后在“数层维度”进行去重（方案1）
 *      或者借助startIndex来进行去重，这种思路得益于组合问题递归调用时需要从i+1位置开始递归（方案2）
 *         子集问题也可以有重复值，但是[1,2,2]和[2,1,2]属于相同的子集，因此在排序后要在“数层维度”进行去重（方
 *      案1）；或者借助startIndex进行去重，这种思路得益于子集问题递归调用时需要从i+1位置开始递归（方案2）
 *          全排列问题也可以有重复值，但是[1,2,2]和[2,1,2]属于不同的全排列，因此在排序后可以在“数层维度”去重，
 *      也可以去重后在“树枝维度”去重。全排列问题每一次都要从0位置开始研究，因此没有额外的去重思路。
 *          组合问题 和 子集问题的区别在于————“组合问题”会有约束，比如：要求仅包含k个数、要求组合中元素和是target等。
 *      因此“组合问题”就是要求输出指定约束的子集，而不是输出所有的子集。
 * 【如何理解set去重】
 *          使用set进行去重时，set声明在for循环之前(且不会在递归调用中传递)。for循环的作用是在某一个树层，依次研
 *      究每一个位置可能放什么树，因此这种位置的set作用就是：在同一树层的节点间进行去重！！！同时set不会使用递归继
 *      续传递，因此再深入子节点的时候set就没了，所以不会再树枝间去重，因为很明显到子节点的时候，并没有父节点的set
 *      信息。
 * 【去重的逻辑 和 在树中的体现的解释】建议看“组合总和Ⅱ”
 *      https://programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 *
 * */
public class _09huisu {
    /*46.
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    * */
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>(); //记录结果
        path = new LinkedList<>(); //记录路径
        used = new boolean[nums.length]; //记录每一个数是不是在path中
        permuteBack(nums);
        return res;
    }

    /*
    以nums=[1,2,3,4,5]为例
        最外层for循环每一次选取一个数作为第一位，当选取1的时候能得到24个答案，因为以1开始，剩下4个数全排列就是
    24种；同理第二次执行最外层for循环时，1已经是false，因为for循环的最后两句取消选择了，这轮循环得到的就是以2开
    所有的以2开始的全排列，也是24种。
        for循环内的permuteBack(nums)执行完，就会“把当前path作为头列，剩下的所有数全排列的方案添加进res”
    */
    private void permuteBack(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) { /*全排列需要包含所有的数，每一次都要看一遍哪些数没有用*/
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            permuteBack(nums); /*相当于选择了当前元素，继续研究当前节点的子节点*/
            used[i] = false;
            path.remove(path.size() - 1); /*相当于撤销选择，即不选择i元素，研究下一个元素nums[i+1]————对应同一层的下一个节点*/
        }
    }

    /*全排列的另一种写法：形参数量变多*/
    /**
     * 比较上面的代码和下面的代码，会发现：仅仅是把变量pathPermute、usedPermute放在了形参的位置。其他的代码
     * 都没有变——————体会回溯这里 全局变量和形参变量的区别
     */
    List<List<Integer>> permuteRes;
    public List<List<Integer>> permute1(int[] nums) {
        permuteRes = new LinkedList<>();
        List<Integer> path = new LinkedList<Integer>();
        boolean[] used = new boolean[nums.length];
        permuteTrace(nums,path,used);
        return permuteRes;
    }

    private void permuteTrace(int[] nums, List<Integer> path, boolean[] used) {
        if (path.size()==nums.length){
            //java是值传递，这里必须要新创建一份使用
            permuteRes.add(new LinkedList<>(path)); /**err：必须使用“new LinkedList<>(path)”，如果写path就错了*/
            return;
        }
        for (int i=0;i<nums.length;i++){
            if (!used[i]){
                path.add(nums[i]);
                used[i] = true;
                permuteTrace(nums,path,used);
                path.remove(path.size()-1);
                used[i] = false;
            }
        }
    }


    /*78. 子集
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
    * */
    /**
     *【建议的解法】建议使用写法 subsets1。（尤其要注意 方法中写的"return的相关事项"）
     *【思路讲解】https://leetcode.cn/problems/subsets/solutions/2059409/hui-su-bu-hui-xie-tao-lu-zai-ci-pythonja-8tkl/
     */
    List<List<Integer>> resSubSets;
    List<Integer> pathSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubSets = new LinkedList<>();
        pathSubsets = new LinkedList<>();
        subsetsBack(nums,0);
        return resSubSets;
    }

    private void subsetsBack(int[] nums, int index) {
        /**【注意】回溯问题在给res中添加结果时，必须使用new的方法新创建一个，不能直接使用path的引用！！*/
        resSubSets.add(new LinkedList<>(pathSubsets)); /**err：子集问题每次添加到结果集不用return，因为要研究树所有的节点*/
        /**    【说明】进入到for循环后循环的变量是i，研究的是i位置的数，因此往path中添加等操作都是针
         * 对索引为i的那个数*/
        for (int i = index; i < nums.length; i++) {
            pathSubsets.add(nums[i]); /**🔺err：【注意，反复错】循环中的循环变量已经是i了!!!*/
            subsetsBack(nums, i + 1); /**err：循环中的循环变量已经是i了*/
            pathSubsets.remove(pathSubsets.size() - 1);
        }
    }


    /**
     * 另外一种写法——————
     *      上面的代码和下面的代码唯一的区别是pathSubsets放在了形参的位置，其他的diamond都是一样的。这两种都没问题
     */
    List<List<Integer>> resSubSets1;
    public List<List<Integer>> subsets1(int[] nums) {
        resSubSets1 = new LinkedList<>();
        List<Integer> pathSubsets = new LinkedList<>();
        subsetsBack(nums,0,pathSubsets);
        return resSubSets1;
    }

    private void subsetsBack(int[] nums, int index,List<Integer> pathSubsets) {
//        if (index==nums.length) return; //写在这里是错误的！
        /**err：不加注释掉的这一句就可以，并不会发生StackOverflow！！但是如果加了这一句，则——————
         *      这一句必须在“resSubsets.add(new LinkedList<>(path));”的后面，不然结果会
         *  少很多，一句话概况少了多少，凡是包含nums最后一个元素的 子集，结果都没有。
         *      进一步解释为什么？因为如果index==nums.length，根据for循环逻辑可知，一定是
         *  上一步把最后一个元素添加进path了，然后递归调用subsetsBack，此时index==nums.length。
         *  如果下面的这句话放在subsetsBack的第一行，就导致方法直接返回了，path没有添加进
         *  结果！！!
         *      再解释一下为什么不会发生StackOverflow？？方法的返回值是null，即使没有这一句，当
         * index来到nums.length的时候，for循环由于循环条件不满足因此不会循环，导致方法结束，因此
         * 并不会无终止的持续递归下去，因此不会栈溢出。
         *  */
        resSubSets1.add(new LinkedList<>(pathSubsets)); /**注：这里必须是new一个*/
        if (index==nums.length) return; /**注：这里不retrun也是可以的*/
        for (int i = index; i < nums.length; i++) {
            pathSubsets.add(nums[i]);
            subsetsBack(nums,i+1,pathSubsets);
            pathSubsets.remove(pathSubsets.size()-1);
        }
    }



    /*17. 电话号码的字母组合
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    * */
    /**
     *【时间复杂度分析】
     *      时间复杂度：O(3^m×4^n),其中 m 是输入中对应 3 个字母的数字个数（包括数字 2、3、4、5、6、8），n
     * 是输入中对应 4 个字母的数字个数（包括数字 7、9），m+n 是输入数字的总个数。当输入包含 m 个对应 3 个字
     * 母的数字和 n 个对应 4 个字母的数字时，不同的字母组合一共有3^m×4^n种，需要遍历每一种字母组合。
     *
     *【空间复杂度分析】
     *      空间复杂度：O(m+n)，其中 m 是输入中对应 3 个字母的数字个数，n 是输入中对应 4 个字母的数字个
     * 数，m+n 是输入数字的总个数。除了返回值以外，空间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，
     * 哈希表的大小与输入无关，可以看成常数，递归调用层数最大为 m+n。
     */
    List<String> resLetterCombinations;
    Map<Character,String> map;
    StringBuilder sb = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        resLetterCombinations = new LinkedList<>();
        /*这里需要特殊判断一下，否则有一个错误用例：
            输入
            digits =
            ""
            输出
            [""]
            预期结果
            []
        */
        if (digits==null||digits.length()==0) return resLetterCombinations;
        map = new HashMap<>(){{
           put('2',"abc");
           put('3',"def");
           put('4',"ghi");
           put('5',"jkl");
           put('6',"mno");
           put('7',"pqrs");
           put('8',"tuv");
           put('9',"wxyz");
        }};
        letterCombinationsBack(digits,0);
        return resLetterCombinations;
    }

    private void letterCombinationsBack(String digits, int index) {
        if (index==digits.length()){ //sb.length()==digits.length
            resLetterCombinations.add(new String(sb));
            /**err：如果这里不加return，就会导致执行下面的"charAt(index)时出现越界异常"，报错信息类似：
                 java.lang.StringIndexOutOfBoundsException: Index 2 out of bounds for length 2
             */
            return;
        }
        char c = digits.charAt(index);
        String str = map.get(c);
        /*尝试每次添加一个str中的字符*/
        for (int i=0;i<str.length();i++){
            sb.append(str.charAt(i));
            letterCombinationsBack(digits,index+1); /*从 digits 中的下一个索引继续研究*/
            sb.deleteCharAt(sb.length()-1);
        }
    }


    /*39.组合总和
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。
    * */
    /**
     【建议的解法】使用 combinationSum02
     【备注】
            1. 这个题的特殊点有二————
                1.1 每一个数可以被重复选择，因此必须要有提前终止的逻辑，不然就会栈溢出，见combinationSum02中
        if块之前的备注
                1.2 因为每一个数可以重复被选择，因此在递归的时候不能再从下一位开始了，而是要从当前位继续递归
     */
    List<List<Integer>> resCombinationSum;
    List<Integer> pathCombinationSum;
    int sum = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        resCombinationSum = new LinkedList<>();
        pathCombinationSum = new LinkedList<>();
        combinationSumback(candidates,target,0);
        return resCombinationSum;
    }

    private void combinationSumback(int[] candidates, int target, int index) {
        if (sum==target){
            resCombinationSum.add(new LinkedList<>(pathCombinationSum));
            return;  /**err：因为这里的数都是大于0的，因此这里可以直接return*/
        }
        if (sum>target || index>=candidates.length){
            return;
        }
        for (int i=index;i<candidates.length;i++){
            pathCombinationSum.add(candidates[i]);
            sum += candidates[i];
            combinationSumback(candidates,target,i);
            pathCombinationSum.remove(pathCombinationSum.size()-1);
            sum -= candidates[i];
        }
    }

    /*解法2：只用一个target来表示sum*/
    List<List<Integer>> combinationSumRes;
    public List<List<Integer>> combinationSum02(int[] candidates, int target) {
        combinationSumRes = new LinkedList<>();
        combinationSumTrace(candidates,0,target,new LinkedList<Integer>());
        return combinationSumRes;
    }
    private void combinationSumTrace(int[] candidates, int index, int target, LinkedList<Integer> path) {
        if (target==0){
            combinationSumRes.add(new LinkedList<>(path));
            return; /*这里不 return 也是ok的*/
        }
        /**err：下面的if逻辑必须有，否则会StackOverflow！！
            *这是这个题与其他题的区别所在，这个题每一个数可以反复被选择，因此必须要有终止逻辑！！
         */
        if (target<0||index==candidates.length){
            return;
        }
        for (int i=index;i<candidates.length;i++){
            target -= candidates[i];
            path.add(candidates[i]);
            /**err：递归的时候要从i开始而不是index!
             *     【疑问】为什么不是从index开始？？
             *          答：集合、子集、组合问题都是从下一个位置继续研究，前面的位置一概不管！！！
             *     【疑问】为什么递归时依然是从i开始，而不是从i+1开始？？
             *          答：因为题中说了"每一个数可以无限次被选取！！！（即这一轮选了，下一轮也能继续选）"。也
             *     正因为下一次递归依然从i开始，因此必须要有"if(target<0) return;"这句逻辑，否则就会出现StackOverflow！！
             *     ————即这个题使用递归达到重复选择每一个数的目的！！
             【如果错写成“combinationSumTrace(candidates,i+1,target,path);”】第一个case，第二个case都会报错————
                             输入
                             candidates =
                             [2,3,6,7]
                             target =
                             7
                             输出
                             [[7]]
                             预期结果
                             [[2,2,3],[7]]
             * */
//            combinationSumTrace(candidates,index,target,path);
            combinationSumTrace(candidates,i,target,path); /**err(反复错)：递归时需要从i开始因为每一个数可以反复被选取，而不是i+1，更不是index*/
            target += candidates[i];
            path.removeLast();
        }
    }


    /*22. 括号生成
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
    /**
     * 【建议的解法】建议使用 generateParenthesis_own
     * 【解题关键】尝试，用open和close分别表示左右括号，在合法的前提下（合法的要求：①任意时刻左括号的
     *      数量必须不小于右括号的数量 且 ②左括号的数量小于n），尝试添加一个左括号或者右括号。
     * 【同理】这段代码sbGenerateParenthesis放在形参的位置，其他的代码也不用变，把它作为形参变量每一
     *      次递归的时候传即可。
     */
    /*解法1：官方解回溯法*/
    public List<String> generateParenthesis_offical(int n) {
        List<String> ans = new ArrayList<String>();
//        StringBuilder sb = new StringBuilder(); /**在这里new跟直接在传参的时候new有什么区别？？运行都是正确的*/
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        /*如果左括号小于最大数量。可以添加一个左括号*/
        if (open < max) {
            cur.append('('); /**做选择：添加一个左括号*/
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1); /**撤销 上上一行 刚刚做出的选择*/
        }
        /*如果右括号数量小于左括号数量。可以做选择添加一个右括号*/
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /*解法2：基于官方解回溯法的改进。。。
    * 【想说明的问题】回溯问题中只有当前回溯依赖的信息必须通过形参传递，其他的信息都可以使
    *       用全局变量。  */
    List<String> ans = new ArrayList<String>();
    int max;
    StringBuilder cur = new StringBuilder();
    public List<String> generateParenthesis(int n) {
        max = n;
        backtrack( 0, 0);
        return ans;
    }

    public void backtrack(int open, int close) {
        if (cur.length() == max * 2) {  //括号的长度达到2*n，添加结果
            ans.add(cur.toString());
            return;
        }
        if (open < max) {   //情况1：左括号小于n，添加左括号到path；然后继续研究下一个位置
            cur.append('(');
            backtrack( open + 1, close);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) { //情况2：右括号小于左括号，添加右括号到path；然后继续研究下一个位置
            cur.append(')');
            backtrack(  open, close + 1);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /*自己常用的解法*/
    List<String> resGenerateParenthesis;
    public List<String> generateParenthesis_own(int n) {
        resGenerateParenthesis = new LinkedList<>();
        generateParenthesis(n,0,0,new StringBuilder());
        return resGenerateParenthesis;
    }

    private void generateParenthesis(int n, int open, int close, StringBuilder path) {
        if (path.length()==2*n){
            resGenerateParenthesis.add(new String(path));
            return;
        }
        if (open<n){
            path.append('(');
            generateParenthesis(n,open+1,close,path);
            path.deleteCharAt(path.length()-1);
        }
        if (close<open){
            path.append(')');
            generateParenthesis(n,open,close+1,path);
            path.deleteCharAt(path.length()-1);
        }
    }


    /*其他的写法：路径变量声明为全局变量也OK*/
    List<String> resGenerateParenthesis1;
    StringBuilder sbGenerateParenthesis;
    public List<String> generateParenthesis_(int n) {
        resGenerateParenthesis1 = new LinkedList<>();
        sbGenerateParenthesis = new StringBuilder();
        generateParenthesisBack(n,0,0);
        return resGenerateParenthesis1;
    }

    private void generateParenthesisBack(int n, int l, int r) {
        if (sbGenerateParenthesis.length()==2*n){
            resGenerateParenthesis1.add(new String(sbGenerateParenthesis));
            return;
        }
        if (l<n){
            sbGenerateParenthesis.append('(');
            generateParenthesisBack(n,l+1,r);
            sbGenerateParenthesis.deleteCharAt(sbGenerateParenthesis.length()-1);
        }
        if (r<l){
            sbGenerateParenthesis.append(')');
            generateParenthesisBack(n,l,r+1);
            sbGenerateParenthesis.deleteCharAt(sbGenerateParenthesis.length()-1);
        }
    }


    /*79.单词搜索
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    * */
    /**
     * 【思路】从每一个位置展开研究(即从这个位置开始，一一对比word的每一个字符，看能不能找到可行解)。
     *      "展开研究"的具体逻辑(即for循环的逻辑)：
     *          ①什么时候找到了可行解？来到了word的最后字符的后面，即word字符的所有数据都匹配成功了。
     *      即递归方法的形参index来到word.length()。
     *          ②特殊情况？下标越界需要直接返回false，认为当前方式匹配失败；
     *                    当前来到的word字符校验失败，返回false。
     *          ③到这里就说明word的index索引的字符检验成功！此时首先标记下这个位置在当前的路径中已经
     *       研究过了(参考官方解的布尔数组 或者 解法1的特殊字符)，然后递归调用研究index+1位置，最后
     *       撤销之前的选择即取消做的标记。
     * 【补充】借助这个题认真的体会一下下面的问题————
     *      1. 为什么这个题需要标记走过的路，但是”矩阵中最长的递增路径“中并不需要标记走过的路；
     *      2. dfs的一般使用方法，尤其是主函数的调用参数是什么？？怎么确定？？dfs的流程又是什么，返回值的确定怎么做？？
     * ⚠TODO：注意一点很重要的区别
     *      一般来说，像这种二维表的回溯，往往会联想到DP。如果某一个格子的结果（Boolean类型）能根据“有限的数个变量”确定
     *   出它的值，那就能改成dp 或者 记忆化搜索！！！—————这一点总结很重要。
     *      但是遗憾的是这个题不能。比如根据（i,j,index）能唯一确定位置（i,j）的结果吗？不能，因为这个题有条件————走过的路
     *   不能再走，因此从位置（i,j）开始后续还能走哪些位置，是受“之前是怎么来到位置（i，j）的”这个路径影响。换言之，我们从
     *   不同的路径走到位置（i,j），可能继续走下去结果是不同的。。。。这一区别，就导致79题无法改写成 记忆化搜索 或者 dp的版本
     * */
    /*
    * 解法1：在每一轮中，研究过的元素使用字符'\0'来标记
    * */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word)) { /*从word的第0个字符开始；从二维数据的(i,j)开始递归搜索单词word*/
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int index, String word) {
        if (index == word.length())
            return true; /*写成"index==word.length()-1"就错了*/
        if (i >= 0 && j >= 0 && i < board.length && j < board[0].length && board[i][j] == word.charAt(index)) {
            board[i][j] = '\n'; /**err：这里需要使用特殊字符标记遍历过的位置，否则会导致本来没有的结果返回true*/
            boolean cur = dfs(board, i + 1, j, index + 1, word) || /**只要四个方法有一个方向能匹配到index+1字符，就返回true*/
                    dfs(board, i - 1, j, index + 1, word) ||
                    dfs(board, i, j + 1, index + 1, word) ||
                    dfs(board, i, j - 1, index + 1, word);
            board[i][j] = word.charAt(index); /**【注意】之前利用'\n'来标记那些遍历了的位置，现在需要还原回去.....因为后面从别的位置出发可能还会用到这个位置的值*/
            return cur;
        } else {
            return false;
        }
    }

    /*
    * 解法2：官方解使用boolean数组来记录当前的路径中(i,j)是不是被遍历过了
    * */
    boolean[][] exist_offical_used;
    public boolean exist_offical(char[][] board, String word) {
        exist_offical_used = new boolean[board.length][board[0].length];
        for (int i=0;i<board.length;i++)
            for (int j=0;j<board[0].length;j++){
                if (exist_offical_dfs(i,j,board,word,0)){
                    return true;
                }
            }
        return false;
    }

    private boolean exist_offical_dfs(int i, int j, char[][] board, String word,int index) {
        if (index>=word.length()){
            return true;
        }
        if (i<0||i>=board.length||j<0||j>=board[0].length||word.charAt(index)!=board[i][j]){
            return false;
        }
        if(!exist_offical_used[i][j]){
            exist_offical_used[i][j] = true;
            boolean curFlag = exist_offical_dfs(i-1,j,board,word,index+1)||
                    exist_offical_dfs(i,j-1,board,word,index+1)||
                    exist_offical_dfs(i+1,j,board,word,index+1)||
                    exist_offical_dfs(i,j+1,board,word,index+1);
            exist_offical_used[i][j] = false;
            return curFlag;
        }
        return false;
    }


    /*131.
    给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
    * */
    /**
     *【思路】假设当前这一轮需要从i位置开始，我们就从i位置开始划分子串（i~i+1的子串、i~1+2的子串、i~i+3的子串），如果划
     * 分出的子串是回文的，则继续往后尝试剩下的部分，具体来说————
     *      如果尝试划分的子串是回文的，则将划分的子串添加进路径pathPartition，接着从子串后的下一个位置继续研究；
     *      否则如果划分出的子串不是回文的，我们就继续下一个位置划分子串。具体的代码是在for循环内if的条件语句
     *【解法】解法1和解法2的区别在于，如何判断某一个子串是不是回文的。其中————
     *      解法1对于每一个子串使用双指针相向而行，每到一个位置判断字符是不是相等；
     *      解法2使用动态规划提前计算，二维布尔数组标识每一个子串是不是回文的
     */
    /*
    * 解法1：朴素的解法。
    *       可以通过引入布尔数组来优化"判断某个子串是不是回文串"的这个过程，见解法2
    * 题目的难点：
            切割问题可以抽象为组合问题
            如何模拟那些切割线
            切割问题中递归如何终止
            在递归循环中如何截取子串
            如何判断回文
    * */
    List<List<String>> resPartition;
    List<String> pathPartition;
    public List<List<String>> partition(String s) {
        resPartition = new LinkedList<>();
        pathPartition = new LinkedList<>(); //存放当前选择的路径
        partitionBack(s,0);
        return resPartition;
    }

    private void partitionBack(String s, int index) {
        /*step1：如果当前研究到了最后的位置，则添加进结果集*/
        if (index>=s.length()){
            resPartition.add(new LinkedList<>(pathPartition)); /*注意创建一个新的copy*/
            return;
        }
        /*step2：从index位置开始，依次判断生成的各种长度子串是不是回文。如果是回文，则将这个回文
        子串添加进psth，并且递归调用“partitionBack(s,i+1)——即从下一个位置开始继续切割回文串”*/
        for (int i=index;i<s.length();i++){
            if (isPalindrome(s.substring(index,i+1))){ /**err：每一轮截取子串时左边界是index，但是右边界是i+1。取子串是左闭右开区间，右边界至少是index+1*/
                pathPartition.add(s.substring(index,i+1)); /*做出选择。这里做选择的反映就是 把某一段回文子串添加到路径pathPartition*/
                partitionBack(s,i+1);
                pathPartition.remove(pathPartition.size()-1); /*上一步是递归，会不断的向树的更深层寻找；到这一行代码会回到初始调用的地方，撤销做出的选择*/
            }
        }
    }

    /*双指针判断一个串是不是回文串*/
    private boolean isPalindrome(String substring) {
        //两个指针相向而行，判断是不是指向的字符永远相等。。一旦出现不相等就返回false
        for (int i = 0, j = substring.length() - 1; i < j; i++, j--) {
            if (substring.charAt(i) != substring.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /*
    * 解法2：使用动规来判断一个串是不是回文串
    * */
    List<List<String>> result_partition_dp;
    LinkedList<String> path_partition_dp;
    boolean[][] dp_partition_dp;

    public List<List<String>> partition_dp(String s) {
        result_partition_dp = new ArrayList<>();
        char[] str = s.toCharArray();
        path_partition_dp = new LinkedList<>();
        dp_partition_dp = new boolean[str.length][str.length];
        isPalindrome(str);
        backtracking(s, 0);
        return result_partition_dp;
    }

    public void backtracking(String str, int startIndex) {
        if (startIndex >= str.length()) {
            //如果起始位置大于s的大小，说明找到了一组分割方案
            result_partition_dp.add(new ArrayList<>(path_partition_dp));
        } else {
            for (int i = startIndex; i < str.length(); ++i) { /**i从startIndex开始，取不到str.length()。因此这里截取子串是左闭右闭的思想*/
                if (dp_partition_dp[startIndex][i]) {
                    //是回文子串，先将当前子串保存入path，然后进入下一步递归
                    path_partition_dp.offerLast(str.substring(startIndex, i + 1));
                    //要从下一个位置开始研究，保证不重复
                    backtracking(str, i + 1);
                    path_partition_dp.pollLast();
                } else {
                    //不是回文子串，跳过
                    continue;
                }
            }
        }
    }

    //通过动态规划判断是否是回文串,参考动态规划篇 52 回文子串
    public void isPalindrome(char[] str) {
        for (int i = str.length-1; i >=0 ; --i) {
            for (int j = i; j < str.length; ++j) {
                if (str[j] == str[i]) {
                    if (j - i <= 1) { //情况1：当前子串的长度为1或者为2
                        dp_partition_dp[i][j] = true;
                    } else if (dp_partition_dp[i + 1][j - 1]) { //情况2：中间的一大段子串都是回文的
                        dp_partition_dp[i][j] = true;
                    }
                }

                /*for循环的逻辑也可以使用下面的逻辑实现，这两行代码就集结了情况1和情况2。
                *       【补充说明】虽然i是从最后一行开始，并且dp[i][j]依赖dp[i+1][j-1]，看着依赖了下一行，其
                * 实并不会越界，因为第二层for循环规定了j从i开始到str.length-1，因此最后一行只有一个元素需要计算，
                * 并且这个元素的位置i和j是相等的，因此"j-i<=1"就得到true了，后面不会继续计算的
                * */
//                if (str[i]==str[j] || (j-i<=1 || dp_partition_dp[i+1][j-1])){
//                    dp_partition_dp[i][j] = true;
//                }
            }
        }
    }


    /*51. N 皇后
    按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    * */
    /**
     * 【注意】这里的chessBoard是当前的棋盘样子，必须要进行初始化使用字符'.'进行填充，否则可能会报如下的错：
     *      [[".Q\u0000\u0000","\u0000\u0000.Q","Q.\u0000\u0000","\u0000\u0000Q\u0000"],["..Q\u0000","Q\u0000..","..\u0000Q","\u0000Q.\u0000"]]
     * 【解题思路】从第0行开始，依次研究每一行。
     *      step1：找到可行解的标志：本轮研究的行来到了棋盘最后一行的下一行，即 row == n ；
     *      step2：for循环处理逻辑：对于本轮研究行（即参数row）的每一个位置(即每一列)，判断放皇后是不是合理。如果合理的话就放一个皇后
     *  继续研究row+1行；否则的话研究该行的下一个位置。
     * */
    /*
    * 解法1：朴素的做法。
    *       可以使用布尔数组来优化"判断某一个位置放皇后"这个过程，见解法2
    * */
    List<List<String>> resSolveNQueens = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] chessBoard = new char[n][n];
        /**【注意】"Arrays.fill"方法是填充一维数组！！如果使用“Arrays.fill(chessBoard,'.');”执行时会
         报下面的错误————
                 java.lang.ArrayStoreException: java.lang.Character
                 at line 3427, java.base/java.util.Arrays.fill
                 at line 7, Solution.solveNQueens
                 at line 56, __DriverSolution__.__helper__
                 at line 86, __Driver__.main
         */
        for (int i=0;i<n;i++){ /**err：必须进行初始化*/
            Arrays.fill(chessBoard[i],'.');
        }
        solveNQueensBack(n,0,chessBoard);
        return resSolveNQueens;
    }

    /*方法的作用：研究“row行皇后可以放置在哪里”————即“填充 chessBoard 的row行”。完成后继续处理 row+1 行。*/
    private void solveNQueensBack(int n, int row /*现在需要研究哪一行*/, char[][] chessBoard) {
        /*step1：如果row已经不小于n，说明所有行都放了皇后且合规————即找到一个可行解，讲 chessBoard 代表的解添加进resSolveNQueens*/
        if (row >= n) { //只要当前需要研究的row来到最后一行的后一行，就说明找到了一个可行解。
            resSolveNQueens.add(Array2List(chessBoard));
            return;
        }
        /*step2：依次尝试第row行的每一个位置，如果放皇后合法的话就放皇后继续研究第row+1行的位置*/
        for (int col = 0; col < n; col++) { //研究当前行的每一个（列）位置
            if (isVaid(row, col, n, chessBoard)) { /**如果该位置放置皇后不会冲突的话（即isValid方法返回true）,才做选择并向更深的一层研究*/
                chessBoard[row][col] = 'Q';
                solveNQueensBack(n, row + 1, chessBoard); //递归的决策后面的行
                chessBoard[row][col] = '.';
            }
        }
    }

    /*判断如果(row,col)放置一个皇后，是否合规*/
    private boolean isVaid(int row, int col, int n, char[][] chessBoard) {
        //①判断col这一列是不是有皇后
        for (int rowIndex=0;rowIndex<row;rowIndex++){
            if (chessBoard[rowIndex][col]=='Q') return false;
        }
        //②判断45方向，是不是有皇后。。此时每一次"行坐标-1，纵坐标也是-1"
        for (int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if (chessBoard[i][j]=='Q') return false;
        }
        //③判断135度方向，是不是有皇后。。此时每一次"行坐标-1，但是纵坐标+1"
        for (int i=row-1,j=col+1;i>=0&&j<=n-1;i--,j++){
            if (chessBoard[i][j]=='Q') return false;
        }
        return true;
    }

    /*将一个可行性解转换为List类型*/
    public List Array2List(char[][] chessboard) {
        ArrayList<String> res = new ArrayList<>();
        for (char[] row:chessboard){
            res.add(String.copyValueOf(row));
        }
        return res;
    }


    /* 解法2：最优解，使用位运算的优化
            缺陷：由于思路是用3个32位的整数分别代表列、主对角线、副对角线的位置，因此“N皇后”的N要不大于32。
    */
    List<List<String>> resSolveNQueens1;
    int numOfQueen;

    public List<List<String>> solveNQueens_best(int n) {
        numOfQueen = n;
        backtrack(0, 0, 0, 0, new int[n]);
        return resSolveNQueens1;
    }

    /*
     * @param row：现在（这一次递归）研究哪一行
     * @param cols：存放哪些列放置了皇后，二进制是1的话就代表别的行已经在这一列放过皇后了
     * @param diag1：存放主队角中，哪些列放置了皇后
     * @param diag2：存放副对角线中，哪些列放置了皇后
     * @param path：一个长度为n的数组。。。path[i]的值代表 index=i 这一行中的皇后，放在 index=path[i] 的位置
     */
    private void backtrack(int row, int cols, int diag1, int diag2, int[] path) {
        /*step1：如果当前来到了 index==n 的这一行，说明所有行都研究过了，因此找到了一个可行解。
            因此，一个完整的path就代表一种解，path中每个元素代表了每一行皇后放在什么位置
        */
        if (row == numOfQueen) {
            resSolveNQueens1.add(buildBoard(path));
            return;
        }
        /*step2：是关键。available的含义 available中二进制是1的位置就是所有能放置皇后的位置
            cols | diag1 | diag2————所有 不能放皇后的位置；
            (~(cols | diag1 | diag2)————取反后得到所有能放置皇后的位置（但这个结果不能直接用）；
            & ((1 >> numOfQueen) - 1)————与之后的整数中，为1的位置是能放置皇后的列
         */
        int available = (~(cols | diag1 | diag2)) & ((1 >> numOfQueen) - 1);
        /*step3：使用while循环，依次拿出 二进制available中最右边的1，拿出后就在row行的这个位置放置一个皇后。继续研究后面的行*/
        while (available != 0) {
            /*①：一个数和它的相反数相与，得到二进制最低位的1*/
            int position = available & -available;
            /*②：在二进制 available 中，把最低位的1抹为0*/
            available &= (available - 1);
            /*③：position-1 就得出了哪一列放置皇后是合理的。curCol就是对应的列号*/
            int curCol = Integer.bitCount(position - 1);
            path[row] = curCol;

            backtrack(
                    row + 1,
                    cols |= position,
                    (diag1 |= position) << 1,
                    (diag2 |= position) >> 1,
                    path
            );
        }
    }

    /*buildBoard(path)的作用：根据path得到一个可行解————即List<String>。*/
    private List<String> buildBoard(int[] path) {
        /*①：创建出结果，每一个path代表一个完整的可行解，即对应一个 List<String>，也即存到这里的board。
            【说明】棋盘中的每一行转换成一个String；path的每一个元素对应棋盘的一行，path[i]就指出了棋盘第i行的皇后放在path[i]列的位置*/
        LinkedList<String> board = new LinkedList<>();
        for (int i = 0; i < numOfQueen; i++) {
            /*思路：依次研究 path 中的每一个元素。
                  step1：创建一个长度位n的字符串数组；
                  step2：将这一行填充为'.'；
                  step3：把皇后放在path[i]的位置；
                  step4：这一行组成一个String，放进board；
                  step5：循环。计算出下一行的String添加进board.....
            * */
            char[] curRow = new char[numOfQueen];
            Arrays.fill(curRow, '.');
            curRow[path[i]] = 'Q';
            board.add(new String(curRow));
        }
        return board;
    }


/**=============================非100补充=======================================*/
/**=============================非100补充=======================================*/
/**=============================非100补充=======================================*/
    /**============================================hot100之外======================*/
    /* 377. 组合总和 Ⅳ
     *给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     * 题目数据保证答案符合 32 位整数范围。
     */
     /*解法1：使用回溯法，会超时。
        这里使用第39题的代码，并修改。主要的的修改体现在3个地方：
            ①不用记录每一种具体的方案
            ②声明int变量sumCombinationSum4，计算具体的方案数
            ③for循环每一次从0开始，因为不同的顺序被认为是不同的方案
      * */
    int resCombinationSum4 = 0;
    // List<List<Integer>> resCombinationSum;
    // List<Integer> pathCombinationSum;
    int sumCombinationSum4 = 0;
    public int combinationSum4(int[] nums, int target) {
        // resCombinationSum = new LinkedList<>();
        // pathCombinationSum = new LinkedList<>();
        combinationSum4back(nums,target,0);
        // return resCombinationSum.size();
        return resCombinationSum4;
    }

    private void combinationSum4back(int[] candidates, int target, int index) {
        if (sumCombinationSum4==target){
            // resCombinationSum.add(new LinkedList<>(pathCombinationSum));
            resCombinationSum4++;
            return;
        }
        if (sumCombinationSum4>target || index>=candidates.length){
            return;
        }
        for (int i=0;i<candidates.length;i++){ /**【注】与组合的区别，每一轮都从0开始，因为顺序不同也认为是不同的*/
            // pathCombinationSum.add(candidates[i]);
            sumCombinationSum4 += candidates[i];
            combinationSum4back(candidates,target,i);
            // pathCombinationSum.remove(pathCombinationSum.size()-1);
            sumCombinationSum4 -= candidates[i];
        }
    }

    /*解法2：动态规划
    * */
    public int combinationSum4_dp(int[] nums, int target) {
        return 0;
    }


    /*47.全排列Ⅱ————给定一个包含可重复数的数组，返回这个数组所有不相同的全排列*/
    /**
     * 47.全排列Ⅱ ————注意对比40组合总和Ⅱ，尤其是去重这里的逻辑
     * 一组可能有重复值的数组，写出所有的全排列。要求不能有重复的全排列
     * 【注意】这个题就必须借助 boolean数组来存储每个位置是否被选择了！！与“不含重复元素”的排列题目有区别，那个题
     *      可以通过交换元素来避免使用 boolean数组
     */
    /*写法1：使用used数组标记是否被选；for循环每次从0位置开始研究*/
    List<List<Integer>> resUnique;
    List<Integer> pathUnique;
    boolean[] usedUnique;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        resUnique = new LinkedList<>();
        pathUnique = new LinkedList<>();
        usedUnique = new boolean[nums.length];
        permuteUniqueBack(nums);
        return res;
    }

    private void permuteUniqueBack(int[] nums) {
        if (path.size() == nums.length) {
            resUnique.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /*
              写法1： used[i - 1] == true，说明同⼀树枝nums[i - 1]使⽤过，即当前研究位置的父节点使用过了同样的值
              写法2： used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过，即当前研究位置同一层某个节点已经研究了
                    放该值的情况。如果同⼀树层nums[i - 1]使⽤过则直接跳过。。因此如果这个位置继续放这个值，后续的子树
                    就是相同的，必然会重复！
              参考图示：leecode_Debug/_hot100_pic/_09huisu 下的图片
            通俗理解：
                当前数和前面的数相等，但是前面的数没有选，当前数就不能选。
            详细的理解见：https://programmercarl.com/0047.%E5%85%A8%E6%8E%92%E5%88%97II.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC
            尤其是注意理解卡尔给出的"used[i - 1] == true"和"used[i - 1] == false"都能去重，在树中的区别体现
            */
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                continue;
            }
            /*上面if的等价写法。（但是在“组合总和Ⅱ”就不等价了，那个题下面的if块不能用）*/
//            if (i > 0 && nums[i - 1] == nums[i] && used[i - 1]) {
//                continue;
//            }
            if (!used[i]) { /**err：全排列每一个数都必须选 并且 每一个数字只能选一次*/
                path.add(nums[i]);
                used[i] = true;
                permuteUniqueBack(nums);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }


    /*另外的写法*/
    List<List<Integer>> resPermuteUnique;
    boolean[] usedPermuteUnique;

    public List<List<Integer>> permuteUnique1(int[] nums) {
        resPermuteUnique = new LinkedList<>();
        Arrays.sort(nums); // 排序，方便去重
        usedPermuteUnique = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        backtrack(nums, path);
        return resPermuteUnique;
    }

    private void backtrack(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            resPermuteUnique.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (usedPermuteUnique[i]) continue; /**这个不能忘*/

            // 去重：相邻元素相等时，必须保证前一个已被用过
            if (i > 0 && nums[i] == nums[i - 1] && !usedPermuteUnique[i - 1]) continue;

            // 选择
            usedPermuteUnique[i] = true;
            path.add(nums[i]);

            // 递归
            backtrack(nums, path);

            // 撤销选择
            usedPermuteUnique[i] = false;
            path.removeLast();
        }
    }


    /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
    "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过
    在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
    /**
     * 【强烈建议】使用写法 restoreIpAddresses2。关键的需要记住以下几点————
     *      ①substring拿子串通过原始串s来拿。（不要从StringBuilder来拿，索引就晕乎了）————这一点就决定了递
     *   归的参数中index代表着原始串s的索引
     *      ②递归时for循环尽量从index+1开始研究，这样“i+num”就代表着这次递归'.'尝试添加的位置
     * 【难点】子串的区间问题很繁琐
     * 【思路】首先将字符串转换为StringBuilder方便'.'的插入。
     *      什么时候找到一个结果？已经添加了三个'.'，并且剩下的子串也符合ip规则
     *      每一次尝试的逻辑(即for循环逻辑)？尝试从index开始，往后面一个长度的子串是不是符合ip规则，如果符合
     *  的话，则在这个子串后面添加'.'，然后调用递归从index+1继续研究，等index+1后面的研究完了又会回到这里；此
     *  时继续研究从index开始长度为2的子串是不是满足ip规则，如果符合要求的话，则在这个子串后买你添加'.'，然后
     *  调用递归从index+2位置开始研究........
     * */
    StringBuilder sbRestoreIpAddresses;
    List<String> resRestoreIpAddresses;
    public List<String> restoreIpAddresses(String s) {
        sbRestoreIpAddresses = new StringBuilder(s);
        resRestoreIpAddresses = new LinkedList<>();
        traceback(s,0,0);
        return resRestoreIpAddresses;
    }

    private void traceback(String s, int index, int pointNum) {
        if (pointNum == 3 && isValid(s.substring(index))) {
            resRestoreIpAddresses.add(new String(sbRestoreIpAddresses));
            return; /**不return是可以的。。。*/
        }
        /**err：这里必须加这一句，因为前面的if是两个条件，可以发现如果pointNum等于3但是不符合条件会走到这里，就会导致栈溢出 ❌
           ——————上一行的话是错误的*/
//        if (pointNum==3){
//            return;
//        }
        for (int i = index; i < s.length(); i++) {
            if (isValid(s.substring(index, i + 1))) {
                sbRestoreIpAddresses.insert(i+1 + pointNum, '.');
                traceback(s, i+1, pointNum + 1);
                sbRestoreIpAddresses.deleteCharAt(i+1 + pointNum);
            }
        }
    }

    private boolean isValid(String substring) {
        if (substring.length()==0) return false;
        if (substring.length() != 1 && substring.charAt(0) == '0') {
            return false;
        }
        if (substring.length() > 3 || (substring.length() == 3 && Integer.valueOf(substring) > 255)) {
            return false;
        }
        return true;
    }


    /*解法2：根上面的解法大同小异，只不过这里将sb也声明为了局部变量（也是ok的）*/
    List<String> resRestoreIpAddresses1;
    public List<String> restoreIpAddresses1(String s) {
        resRestoreIpAddresses1 = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        resRestoreIpAddressesBack1(0,sb,0);
        return resRestoreIpAddresses1;
    }

    private void resRestoreIpAddressesBack1(int index, StringBuilder sb,int pointNum) {
        if (pointNum==3){
            if (isValidIp01(index,sb.length()-1,sb)) //这里子串其实是闭区间的
                resRestoreIpAddresses1.add(new String(sb.toString()));
            return;
        }
        for (int i=index;i<sb.length();i++){
            if (isValidIp01(index,i,sb)){
                /*如果[index,i]这个子串符合规则，则在i+1位置插入'.'，从i+2位置继续进行划分*/
                sb.insert(i+1,'.');
                pointNum++;
                resRestoreIpAddressesBack1(i+2,sb,pointNum); /**【注】这里使用了i+2，因此isValidIp01方法要判断越界*/
                pointNum--;
                sb.deleteCharAt(i+1);
            }
        }
    }
    /*检验sb中闭区间[left,right]之间的值是不是满足ip地址的某一段规则*/
    private boolean isValidIp01(int left, int right,StringBuilder sb) {
        if (left > right) return false;
        //如果一位数必然是正确的，额可以划分
        if (right == left) return true;
        //如果超过一位，就必须保证：第一位不是0 && [left，right]的子串转为int不超过255
        /**err：注意这里包括255，小于等于255都是可以的*/
        if (sb.charAt(left) != '0' && right - left <= 2 && Integer.parseInt(sb.substring(left, right + 1)) <= 255)
            return true;
        return false;
    }

    /**解法3：最重要的区别就是【dfs方法中的for循环中“循环变量i是从index+1位置开始的”】
     重要的总述：
        ①递归的参数index：代表s串中研究到哪一个位置了。（取子串的操作不要从StringBuilder中获取，索引很容易搞混）
        ②插入'.'的位置需要计算，应该插入在StringBuilder的 index+num 的位置
     * */
    List<String> resRestoreIpAddresses11;
    public List<String> restoreIpAddresses2(String s) {
        resRestoreIpAddresses11 = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        /*sb用于记录此步之前的选择；
        index标识当前研究到s哪一个位置了，这个位置之前已经选择'.'的位置了（注：index代表着原始串s的索引）；
        num代表当前的选择中'.'的数量*/
        dfs(s, sb, 0, 0);
        return resRestoreIpAddresses11;
    }

    private void dfs(String s, StringBuilder sb, int index, int num) {
        /*step1：如果'.'已经有三个，并且剩下的部分也是有效的。则把这种选择添加进答案*/
        if (num == 3 && isValid1(s.substring(index))) {
            resRestoreIpAddresses11.add(new String(sb));
            /**
             【说明】这里直接return就没有任何问题（因为本次递归这一步之前没有修改过任何变量————即状态没变）！！
             【重要的，要理解】
             如果要详细理解所说内容，结合“113题 方法pathSum_3”的实现。。感悟一下这里能直接return，但是
             pathSum_3中找到方案添加进答案不能直接return的原因。————根本原因：
             （1）pathSum_3方法中，在调用“resPathSum_3.add(new LinkedList<>(path));”将选择添加
             进答案之前，修改过path（if块上一行调用了“path.add(root.val);”）。所以在return之前必须回溯path的
             状态。
             （2）这个题中递归进来就是if，满足条件就会添加进结果。return之前没有修改index、num、sb等变量
             也即 没有修改状态。因此可以直接return。
             */
            return;
        }
        /*step2：从index+1位置开始研究，看看能不能添加'.'。
              疑问1：为什么选择index+1作为i的起始值？
              疑问2：选择index作为i的起始值行不行？*/
        for (int i = index + 1; i < s.length(); i++) {
            if (isValid1(s.substring(index, i))) {
                /*如果num=0，此时在i位置插入就可以，因为“i的起始值是index+1”,因此保证至少有一位；*/
                sb.insert(i + num, '.'); /**这里到底应该在什么位置插入字符？以及形参的index代表的是s的索引还是sb的索引？这两者是要协调的搭配的，对应关系是怎样的？？*/
                dfs(s, sb, i, num + 1); /**err：根据for循环变量i可以知道i位置的字符还没有研究过呢，因为i是substring方法的第二个参数是不包含这个位置的。。因此下一轮递归是index参数就是i*/
                /*这里移除'.'的时候，方法指定的索引一定和上面插入的位置相等。。————这样才能实现状态回溯。
                否则，如果前面实在3位置插入的‘.’，但是下面调用时移除4位置的字符，答案就不可能对
                * */
                sb.deleteCharAt(i + num);
            }
        }
    }

    /**验证子串这段ip是不是有效的。
     【关键】只关注那些是正确的！！！即只关注返回ture的情况
     */
    private boolean isValid1(String substring) {
        /*情况1：如果子串的长度就是1，即只有一位数，则必然返回true；
          情况2：如果子串的长度是2，则不能以'0'作为开始；
          情况3：如果子串的长度是3，则不能以'0'作为开始 并且 这个数值要小于等于255
          其他情况：一律返回false
        */
        if (substring.length() == 1) return true;
        if (substring.length() == 2 && substring.charAt(0) != '0') return true;
        if (substring.length() == 3 && substring.charAt(0) != '0' && Integer.valueOf(substring) <= 255) return true;
        return false;
    }


    /*216. 组合总和 III
    * */
    List<List<Integer>> resCombinationSum3;
    List<Integer> pathCombinationSum3;
    int sumCombinationSum3 =0;
    public List<List<Integer>> combinationSum3(int k, int n) {
        resCombinationSum3 =  new LinkedList<>();
        pathCombinationSum3 = new LinkedList<>();
        combinationSum3Back(1,k,n,sumCombinationSum3);
        return resCombinationSum3;
    }

    private void combinationSum3Back(int index, int k, int n, int sum) {
        if (sumCombinationSum3==n&&pathCombinationSum3.size()==k){
            resCombinationSum3.add(new LinkedList<>(pathCombinationSum3));
            return;
        }
        if (sum>n||pathCombinationSum3.size()>k){
            return;
        }
        for (int i=index;i<10;i++){
            /**err：注意下面给path添加选择、sum增加值都是用变量i，真正加的是i。
             *     ① index变量仅仅是规定了这一层的变量能从哪个下标之后取；
             *     ② for循环就是完成某一层的结果寻找*/
            pathCombinationSum3.add(i);
            sumCombinationSum3+=i;
            combinationSum3Back(i+1,k,n,sum); /**err：这里index变量也是要使用i+1*/
            pathCombinationSum3.remove(pathCombinationSum3.size()-1);
            sumCombinationSum3-=i;
        }
    }

    /*
    * 40.组合总和Ⅱ【注意去重的地方】
    * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
       candidates 中的每个数字在每个组合中只能使用 一次 。
       注意：解集不能包含重复的组合。（但是candidates可能会有重复的数）
    * */
    /**
     * 【建议的解法】见
     * 【区分”全排列Ⅱ“】
     *    1. 二者的本质区别是因为：
     *          全排列Ⅱ要求每一个path中都不能有重复的数；
     *          组合总和Ⅱ要求每一个相同的数最多使用一次 并且 组合之间不能重复（即组合总和Ⅱ中可以有重复值，只是它们对应原
     *      始数组的不同下标的元素，看起来仅仅是值是相同的）
     *    2. 造成在编码上的区别：
     *          全排列Ⅱ数层去重和树枝去重都可以。只要保证相邻相等的数保持一样的规则————
     *          组合总和Ⅱ必须是同一层去重，如果树枝去重就意味着每一个找到的组合中都不会有重复值，这很明显会漏解的。比如：
     *      原始数组candidates是[2,2,1,3]，则可能的组合是[2,2,1]，[2,3]，如果树枝去重[2,2,1]这个解就会错过
     * */
    LinkedList<Integer> pathCombinationSum2 = new LinkedList<>();
    List<List<Integer>> ansCombinationSum2 = new ArrayList<>();
    boolean[] usedCombinationSum2;
    int sumCombinationSum2 = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        usedCombinationSum2 = new boolean[candidates.length];
        // 加标志数组，用来辅助判断同层节点是否已经遍历
        Arrays.fill(usedCombinationSum2, false);
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        backTracking(candidates, target, 0);
        return ansCombinationSum2;
    }

    private void backTracking(int[] candidates, int target, int startIndex) {
        if (sumCombinationSum2 == target) {
            ansCombinationSum2.add(new ArrayList(pathCombinationSum2));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (sumCombinationSum2 + candidates[i] > target) { //这个if块可以写在for循环之外，写在这里只是提前返回了”总和超过target“的情况
                break;
            }
            // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && !usedCombinationSum2[i - 1]) {
                continue;
            }
            /**【注意】下面的if块就是错的。。思考为什么？？
             * 和全排列Ⅱ是有区别的*/
//            if (i > 0 && candidates[i] == candidates[i - 1] && usedCombinationSum2[i - 1]) {
//                continue;
//            }
            usedCombinationSum2[i] = true;
            sumCombinationSum2 += candidates[i];
            pathCombinationSum2.add(candidates[i]);
            // 每个节点仅能选择一次，所以从下一位开始
            backTracking(candidates, target, i + 1);
            usedCombinationSum2[i] = false;
            sumCombinationSum2 -= candidates[i];
            pathCombinationSum2.removeLast();
        }
    }

    List<List<Integer>> resCombinationSum2_;
    boolean[] usedCombinationSum2_;
    public List<List<Integer>> combinationSum2_(int[] candidates, int target) {
        Arrays.sort(candidates);
        usedCombinationSum2_ = new boolean[candidates.length];
        resCombinationSum2_ = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        combinationSum2(candidates,target,path,0);
        return resCombinationSum2_;
    }

    private void combinationSum2(int[] candidates, int target, LinkedList<Integer> path, int index) {
        if (target==0) resCombinationSum2_.add(new LinkedList<>(path));
        if (target<0 || index==candidates.length) return;  /**err：target<0 的条件不能缺少*/
        /**这个题下面的if提交后部分用例超时————"超出时间限制   124 / 176 个通过的测试用例"*/
//        if (index==candidates.length) return;
        for (int i = index; i < candidates.length; i++) {
            if (i>0&&candidates[i]==candidates[i-1]&&!usedCombinationSum2_[i-1]) continue;
            usedCombinationSum2_[i] = true;
            target -= candidates[i];
            path.add(candidates[i]);
            combinationSum2(candidates,target,path,i+1);
            target += candidates[i];
            path.removeLast();
            usedCombinationSum2_[i] = false;
        }
    }

    public static void main(String[] args) {
        _09huisu huisu = new _09huisu();
        huisu.combinationSum3(3,7);
    }
}
