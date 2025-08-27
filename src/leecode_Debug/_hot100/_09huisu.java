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
 *    2. 回溯的模板中，for循环负责某一层的选择和操作；for循环中递归的调用实现的是从当前节点向更深的一层（下一层）的继续研究
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
 *  动，但是在一些情况下会存在严重的问题，比如：多个实例同时调用的时候、并发的时候，导致问题的根本原因就是StringBuilder是全局变量，但
 *  是在回溯的过程中会改变，因此任何实例走完一遍后需要将StringBuilder恢复到初始状态，从而保证不会玷污下次调用！！！————综上，对于类似
 *  的这种 在调用中会发生改变的量并且深度绑定本次调用过程 的量，强烈建议声明在形参的位置。
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


    /*78.
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
    * */
    /**
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



    /*17.
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
            return;     /**err：如果这里不加return，就会导致执行下面的"charAt(index)时出现越界异常"*/
        }
        char c = digits.charAt(index);
        String str = map.get(c);
        /*尝试每次添加一个str中的字符*/
        for (int i=0;i<str.length();i++){
            sb.append(str.charAt(i));
            letterCombinationsBack(digits,index+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }


    /*39.
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。
    * */
    List<List<Integer>> resCombinationSum;
    List<Integer> pathCombinationSum;
    int sum = 0;
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
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
            return;
        }
        if (target<0){ /**err：必须有，否则会StackOverflow！！*/
            return;
        }
        for (int i=index;i<candidates.length;i++){
            target -= candidates[i];
            path.add(candidates[i]);
            /**err：递归的时候要从i开始而不是index!
             *     【疑问】为什么递归时依然是从i开始，而不是从i+1开始？？
             *          答：因为题中说了每一个数可以无限次被选取。也正因为下一次递归依然从i开始，因
             *     此必须要有"if(target<0) return;"这句逻辑，否则就会出现StackOverflow！！————即这
             *     个题使用递归达到重复选择每一个数的目的！！
             * */
//            combinationSumTrace(candidates,index,target,path);
            combinationSumTrace(candidates,i,target,path); /**递归时需要从i开始，而不是i+1，更不是index*/
            target += candidates[i];
            path.removeLast();
        }
    }


    /*22.
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
    /**
     * 【解题关键】尝试，用open和close分别表示左右括号，在合法的前提下（合法的要求：①任意时刻左括号的
     *      数量必须不小于右括号的数量 且 ②左括号的数量小于n），尝试添加一个左括号或者右括号。
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


    /*79.
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
            board[i][j] = '\0'; /**err：这里需要使用特殊字符标记遍历过的位置，否则会导致本来没有的结果返回true*/
            boolean cur = dfs(board, i + 1, j, index + 1, word) || /**只要四个方法有一个方向能匹配到index+1字符，就返回true*/
                    dfs(board, i - 1, j, index + 1, word) ||
                    dfs(board, i, j + 1, index + 1, word) ||
                    dfs(board, i, j - 1, index + 1, word);
            board[i][j] = word.charAt(index); /**【注意】之前利用'\n'来标记那些遍历了的位置，现在需要还原回去*/
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


    /*51.
    按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    * */
    /**
     * 【注意】这里的chessBoard是当前的棋盘样子，必须要进行初始化使用字符'.'进行填充，否则可能会报如下的错：
     *      [[".Q\u0000\u0000","\u0000\u0000.Q","Q.\u0000\u0000","\u0000\u0000Q\u0000"],["..Q\u0000","Q\u0000..","..\u0000Q","\u0000Q.\u0000"]]
     * 【解题思路】从第0行开始，依次研究每一行。
     *      找到可行解的标志：本轮研究的行来到了棋盘最后一行的下一行；
     *      for循环处理逻辑：对于本轮研究行的每一个位置(即每一列)，判断放皇后是不是合理。如果合理的话就放一个皇后
     *  继续研究row+1行；否则的话研究该行的下一个位置。
     * */
    /*
    * 解法1：朴素的做法。
    *       可以使用布尔数组来优化"判断某一个位置放皇后"这个过程，见解法2
    * */
    List<List<String>> resSolveNQueens = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] chessBoard = new char[n][n];
        for (int i=0;i<n;i++){ /**err：必须进行初始化*/
            Arrays.fill(chessBoard[i],'.');
        }
        solveNQueensBack(n,0,chessBoard);
        return resSolveNQueens;
    }

    private void solveNQueensBack(int n, int row /*现在需要研究哪一行*/, char[][] chessBoard) {
        /*step1：如果row已经不小于n，说明所有行都放了皇后且合规————即找到一个可行解，添加进resSolveNQueens*/
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
            if (chessBoard[rowIndex][col]=='Q')
                return false;
        }
        //②判断45方向，是不是有皇后。。此时每一次"行坐标-1，纵坐标也是-1"
        for (int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if (chessBoard[i][j]=='Q')
                return false;
        }
        //③判断135度方向，是不是有皇后。。此时每一次"行坐标-1，但是纵坐标+1"
        for (int i=row-1,j=col+1;i>=0&&j<=n-1;i--,j++){
            if (chessBoard[i][j]=='Q')
                return false;
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
     */
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
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            通俗理解：
                当前数和前面的数相等，但是前面的数没有选，当前数就不能选。
            详细的理解见：https://programmercarl.com/0047.%E5%85%A8%E6%8E%92%E5%88%97II.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC
            尤其是注意理解卡尔给出的"used[i - 1] == true"和"used[i - 1] == false"都能去重，在树中的区别体现
            * */
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


    /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
    "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过
    在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
    /**
     * 【建议】建议看下面的解法restoreIpAddresses
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
            return;
        }
        if (pointNum==3){ /**err：这里必须加这一句，因为前面的if是两个条件，可以发现如果pointNum等于3但是不符合条件会走到这里，就会导致栈溢出*/
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isValid(s.substring(index, i + 1))) {
                sbRestoreIpAddresses.insert(i+1 + pointNum, '.');
                traceback(s, i+1, pointNum + 1); /**这里不执行pointNum+=1行不行*/
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


    /*解法2：根上面的解法大同小异，只不过这里将sb也声明为了局部变量*/
    List<String> resRestoreIpAddresses1;
    public List<String> restoreIpAddresses1(String s) {
        resRestoreIpAddresses1 = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        resRestoreIpAddressesBack1(0,sb,0);
        return resRestoreIpAddresses1;
    }

    private void resRestoreIpAddressesBack1(int index, StringBuilder sb,int pointNum) {
        if (pointNum==3){
            if (isValidIp01(index,sb.length()-1,sb))
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

    public static void main(String[] args) {
        _09huisu huisu = new _09huisu();
        huisu.combinationSum3(3,7);
    }
}
