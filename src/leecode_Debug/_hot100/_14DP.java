package leecode_Debug._hot100;

import java.util.*;

/**暴力递归：更像是一个从所求 依次推到 已知的问题
 * 动态规划：更像是一个从已知的基础问题(base case) 依次推到 想要求解的问题*/
public class _14DP {
    /*
    * 70.假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    * */
    public int climbStairs(int n) {
        if (n==1) return 1;
        if(n==2) return 2;
        /*走到台阶n，就是走到n-1的方案数 和 走到n-2的方案数 之和*/
        return climbStairs(n-1)+climbStairs(n-2);
    }

    /**dp动态规划*/
    public int climbStairs1(int n) {
        if (n==1) return 1;
        if(n==2) return 2;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    /**使用有限的变量来实现*/
    public int climbStairs2(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        int p1 = 1;
        int p2 = 2;
        for(int i=3;i<=n;i++){
            int tmp = p2;
            p2 = p1+p2;
            p1 = tmp;
        }
        return p2;
    }

    /*
    * 118.给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
        在「杨辉三角」中，每个数是它左上方和右上方的数的和。
    * */
    public List<List<Integer>> generate(int numRows) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>(Arrays.asList(1)));
        if (numRows == 1) return res;
        res.add(new LinkedList<>(Arrays.asList(1, 1)));
        if (numRows == 2) return res;

        for (int i = 3; i <= numRows; i++) {
            LinkedList<Integer> ele = new LinkedList<>();
            List<Integer> last = res.getLast();
            for (int j = 0; j < i; j++) {
                int leftVal = (j - 1 < 0) ? 0 : last.get(j - 1); //计算上一行左边的那个数
                int rightVal = (j >= last.size()) ? 0 : last.get(j); //计算上一行index相同的那个数
                ele.add(leftVal + rightVal);
            }
            res.add(ele);
        }
        return res;
    }

    /*另外的写法，朴素一点。根据j的位置来分别计算*/
    public List<List<Integer>> generate_1(int numRows) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>(Arrays.asList(1)));
        if (numRows==1) return res;
        res.add(new LinkedList<>(Arrays.asList(1,1)));
        if (numRows==2) return res;
        for (int i = 3; i <= numRows; i++) {
            LinkedList<Integer> ele = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                if (j==0) ele.add(1);
                else if (j==i-1) ele.add(1);
                else{
                    List<Integer> last = res.getLast();
                    int val = last.get(j - 1) + last.get(j);
                    ele.add(val);
                }
            }
            res.add(ele);
        }

        return res;
    }


    /*198
    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃
    的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一
    晚上被小偷闯入，系统会自动报警。

    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一
    夜之内能够偷窃到的最高金额。
    * */
    /**【】1. 注意dp问题base case的解决
     *    */
    //解法1：按照dp套路创建返回值来做
    public int rob(int[] nums) {
      int max = Integer.MIN_VALUE;
        returnType res = rob(nums, 0, 0, max);
        return Math.max(res.curTotal,res.noCurTotal);
    }

    class returnType{
        private int curTotal;
        private int noCurTotal;

        public returnType(int curTotal,int noCurTotal) {
            this.curTotal = curTotal;
            this.noCurTotal = noCurTotal;
        }
    }

    public returnType rob(int[] nums,int index,int curTotal,int max){
        if(index==nums.length-1){
            return new returnType(nums[index],0);
        }
        if (index==nums.length-2){
            return new returnType(Math.max(nums[index],nums[index+1]),nums[index+1]);
        }
        returnType next = rob(nums, index + 1, curTotal, max);
        int param1 = curTotal + nums[index]+next.noCurTotal;
        int param2 = curTotal + Math.max(next.curTotal,next.noCurTotal);
        return new returnType(param1,param2);
    }

    //解法2：最简做法
    /**题目的本质：每到一个位置，根据当前位置前面的两个值来推断当前位置的结果*/
    public int rob01(int[] nums) {
        if (nums.length==1) return nums[0];
        int first = nums[0],sec=Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            int cur = Math.max(nums[i]+first,sec); //偷现在这一家(nums[i]) 和 不偷现在这一家取最大值
            first = sec;
            sec = cur;
        }
        return sec;
    }




    /*279.
    * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

       完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数
       * 自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。*/
//    public int numSquares(int n) {
//
//    }



    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
    /**详细的解析和实现见leecode_Debug._hot100._01completeBag的coinChange方法*/
    public int coinChange(int[] coins, int amount) {
        return coinChange(coins,0,amount);
    }

    public int coinChange(int[] coins,int index,int rest){
        if(index==coins.length) return 0;
        if(rest==0) return 1;

        int ways = 0;
        for (int zhang=0;coins[index]*zhang<=rest;zhang++){
            ways += coinChange(coins,index+1,rest-zhang*coins[index]);
        }
        return ways;
    }



    /*139.
    * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利
    * 用字典中出现的一个或多个单词拼接出 s 则返回 true。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    /**
     * 【关键】dp[i]表示0~i-1的子串是否能由wordDict的数个字符串组成。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        HashSet<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i=1;i<s.length()+1;i++) {
            for (int j = 0; j < i; j++) { //j<i，因此j其实并不能取到s.length()，但是i可以取到。
                /*
                if条件语句的解释————
                    dp[j]：0~j-1这段子串能有列表的字符串拼出来；【注意：dp[j]对应的是0~j-1的子串是否可匹配到】
                    set.contains(s.substring(j,i))：j~i-1这段子串是不是在题目字符串的列表中
                1. 解释一下if条件：dp[j]表示0~j-1的子串能不能由字典的词组成；然后我们再看[j,i-1)的子串是不是在字典中，如果
                    在字典中就说明[0,i-1]的子串能由字典的一个或者多个词组成
                2. ”set.contains(s.substring(j,i))“中i是能取到s.length()的，但是substring函数是左闭右开的区间，因此实
                    际上获取子串时不会去拿s.length()的子串，不会导致越界异常。
                    */
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; //这里加break会继续研究s串的下一个位置，不加也没啥问题
                }
            }
        }
        return dp[s.length()];
    }


    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    /**
     * 【强烈建议】使用lengthOfLIS2————解法2
     * 【解法1的思路】定义 dp[i] 表示以 nums[i] 结尾的最长递增子序列长度。
            对于每个 i，遍历 0 ~ i-1 的所有 j：
                如果 nums[j] < nums[i]，可以接在 nums[j] 后面，更新 dp[i] = max(dp[i], dp[j] + 1)。
            最终答案为所有 dp[i] 中的最大值。
      【解法2的思路】维护一个数组 tails，tails[i] 表示长度为 i+1 的递增子序列的的末尾元素 的最小值。
            遍历 nums，对每个元素二分查找 tails 中第一个 ≥ 当前值的位置替换；若找不到，则说明当
       前元素比所有尾部都大，直接追加到 tails。tails 的长度就是最长递增子序列的长度。
     * */
    /*解法1：朴素的做法
    * dp[i]：表示以第i个元素结尾的最长递增子序列有多长*/
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i=1;i<nums.length;i++){
            for (int j=0;j<i;j++){
                if (nums[j]<nums[i]){
                    dp[i] = Math.max(1,dp[j]+1);
                }
            }
            res = Math.max(dp[i],res);
        }
        return res;
    }


    /*解法2：优化解法————贪心+二分查找。（左闭右闭区间的写法）
    【理解一个关键】要想让某一个子序列尽可能的长，则子序列最后一个数应该尽可能的小
    * 看K神的讲解：https://leetcode.cn/problems/longest-increasing-subsequence/，K神的写法是左闭右开的写法
    * 此时tails[i]的定义如下：
    *       tails[i]的值为num代表————数组nums 以num结尾的最长子序列长度为i+1。
    * */
    /**
     * 【关键】把数字num放在以它结尾的最长递增序列处。对于每一个数研究应该放在哪一个位置。
     * 【思路】首先，创建数组dp数组，每一个index位置的数代表“index+1长度的递增子序列的最后一个数最小是多少”【补充说明】基于dp数组的定义
     *      可以发现dp数组一定是严格单调递增的。
     *        变量size，初始时size是0，表示目前最长的递增子序列长度是0，因为啥也没研究呢。
     *        接下来对每一个数研究即for(int num:nums)块要干的事：
     *             ①左边界l = 0，右边界r = size-1。可以是一个闭区间查找。查找当前的数num应该插入在什么位置————力扣35的原代码。最
     *          后出了while循环l即是当前的数应该插入的位置。
     *             ②将num放在dp[l]的位置
     *             ③判断：l==size?size表示到目前位置发现的最长递增子序列长度是size，但是如果l等于size说明num插入到了第size+1个位
     *          置————表明找到了一个更长的递增子序列，长度是size++。因此：如果l==size，则size++；否则size不用更新
     *
     * 【易错点】1. 研究任意一个数时，左边界是0，右边界是size-1，因为此时总共确定了size个数的
     *      位置，因此右边界是size-1，而不能是nums.length-1!!!
     * */
    public int lengthOfLIS2(int[] nums) {
        /*step1：初始化*/
        int[] tails = new int[nums.length];
        int size = 0; //size的含义：tails数组中0~size-1的位置已经放了满足的数字
        /**for循环的完整逻辑：遍历nums数组的每一个数，判断它应该插入到tails数组的什么位置*/
        for (int num : nums) {
            /*step2：下面的逻辑就是在数组中二分查找num应该插入的位置..跟力扣35题的原理、代码是一样的
            * 【目的】计算出num应该插入到tails数组的什么位置*/
            int left = 0, right = size - 1; /**err：注意是在已经存放的数中查找应该放的位置，因此右边界是size-1*/
            while (left <= right) {
                int mid = (left + right) / 2;
                if (tails[mid] < num) { /**err：注意是和tails数组的数比较，而不是和原始数组*/
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            /*step3：left就是num应该插入的索引位置，将num放在应该插入的位置left*/
            tails[left] = num;
            /*step4：如果num插在了索引为size的位置（实际上该位置之前还没有放过数，如果是换做35题的话就相当
            于本来是size大小的数组，下标0~size-1，但是计算出的插入位置是在size，相当于放在数组的最后），则
            更新size*/
            if (left == size)
                size++;     // 或者也可以理解为：如果 num 比所有 tail 中的元素都大，size 扩大
        }

        return size;
    }



    /*152.
    * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至
    * 少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。*/
    /**    求的是最大值，但是不能仅仅记录之前子数组的最大值。比如"-2,3,-4"的最大值应该是24，如果
     * 仅仅选取最大值则遍历到元素3的时候，会选择单独成一个子数组，这样是不对的。应该到任何一个
     * 位置，都应该记录当前子数组的最大值、最小值...
     *     然后到每一个位置的时候，需要决策出当前位置的最大目标值
     * 【建议】建议使用maxProduct2 或者 maxProduct3，条理清晰
     * */
    public int maxProduct(int[] nums) {
        //dp数组的定义：以nums[i-1]结尾的最大子数组的乘积..每一个数可以和前面的数个数合成一个子数组 或者 自己成立一个子数组
        int[] dp1 = new int[nums.length + 1];
        int[] dp2 = new int[nums.length + 1];
        dp1[0] = 1;
        dp2[0] = 1;
        int max = Integer.MIN_VALUE;
        for(int i=1;i<=nums.length;i++){
            dp1[i] = Math.max(Math.max(dp2[i-1]*nums[i-1],nums[i-1]),dp1[i-1]*nums[i-1]);
            dp2[i] = Math.min(Math.min(dp2[i-1]*nums[i-1],nums[i-1]),dp1[i-1]*nums[i-1]);
            max = Math.max(max,dp1[i]);
        }
        return max;
    }


    public int maxProduct1(int[] nums) {
        int preMax = 1,preMin = 1; //分别记录以前一个数结尾的子数组的最小乘积、最大乘积分别是多少。
        int res = Integer.MIN_VALUE; //结果
        for (int i = 0; i < nums.length; i++) {
            /*计算以当前数nums[i]结尾子数组最大乘积是多少*/
            int curMax = Math.max(nums[i],Math.max(preMax*nums[i],preMin*nums[i])); /**【注意】必须新声明变量，不能直接给preMax！！否则会导致preMin更新不准确*/
            /*利用preMax（注意此时它是以nums[i-1]结尾的最大子数组乘积）来更新以nums[i]结尾的子数组
            * 的最小值*/
            preMin = Math.min(nums[i],Math.min(preMax*nums[i],preMin*nums[i]));
            preMax = curMax;
            res = Math.max(res,curMax);
        }
        return res;
    }

    /*maxProduct2和maxProduct3是一样的道理，只不过for循环一个是从1开始，一个是从0开始。*/
    public int maxProduct2(int[] nums) {
        int minPre = nums[0],maxPre = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) { /**err：i要从1开始，因为res的初始值已经是nums[0]了*/
            int curMin = Math.min(nums[i],Math.min(minPre*nums[i],maxPre*nums[i]));
            int curMax = Math.max(nums[i],Math.max(maxPre*nums[i],maxPre*nums[i]));
            minPre = curMin; /**err：这里是赋值，而不是取最值*/
            maxPre = curMax;
            res = Math.max(res,maxPre);
        }
        return res;
    }

    public int maxProduct3(int[] nums) {
        int res = Integer.MIN_VALUE;
        int minPre = 1,maxPre = 1;
        for (int i=0;i<nums.length;i++){
            int curMin = Math.min(Math.min(minPre*nums[i],maxPre*nums[i]),nums[i]);
            int curMax = Math.max(Math.max(minPre*nums[i],maxPre*nums[i]),nums[i]);
            minPre = curMin;
            maxPre = curMax;
            res = Math.max(res,curMax);
        }
        return res;
    }


    /*416.
    * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两
    * 个子集，使得两个子集的元素和相等。*/
    /**题解和解析 见leecode_Debug._hot100._01bag的canPartition方法*/
//    public boolean canPartition(int[] nums) {
//
//    }

    /*32.
    * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的
    * 长度。*/

    /**
     * 【dp思路】1. 从index=1的位置开始研究。（因为以index=0的字符结尾的有效括号长度必然为0————就只有一个字符不可能是有效的）。
     *       2. 仅仅研究”）“的位置，因为以左括号结尾的有效括号长度必然是0，有效括号子串必须是以右括号结尾的
     * 【难点】研究右括号位置的时候，需要根据前一个位置的字符来分别研究（即for循环if块内的if-else块）————
     *      情况1：if (s.charAt(i - 1) == '(')。说明前一个位置是左括号，则可以与当前的位置结合形成”()“；因此需要判断”前
     *  前一个位置“的有效括号长度，即获取值dp[i-2]，如果i-2<0则dp[i]=2，否则dp[i]=dp[i-2]+2。
     *      情况2：else.说明其哪一个位置是右括号。则记住这句话，很关键！！！！！”①跳过前一个位置有效长度后索引是大于0的 并且
     *  ②跳过前一个位置有效括号的长度后前面那个字符是”(“，dp[i]才可能不是0.。。否则dp[i]必然是0，即不用赋值“————这句话中的
     *  两个条件缺一不可。
     */
    /*动态规划的解法：*/
    public int longestValidParentheses(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') { /*【说明】只研究右括号的位置.。因为如果当前位置是左括号，以左括号结尾的有效括号串长度必然是0*/
                if (s.charAt(i - 1) == '(') { //if块说明跟前面一个位置形成有效括号
                    dp[i] = (i >= 2) ? dp[i - 2] + 2 : 2;
                } else { //else说明前面的一个位置也是右括号')'
                    if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { /**else里面的逻辑容易写错。【注意】else中我们只关注dp[i]不是0的情况，如果dp[i]必然是0，就不用研究和赋值，因为int数组的默认值就是0*/
                        dp[i] = (i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] + 2 + dp[i - 1] : 2 + dp[i - 1];
                    }
                }
                res = Math.max(res, dp[i]); //更新结果。。如果index位置不是右括号，则对应的dp[index]就是0，也不用更新最大值
            }
        }
        return res;
    }

    /*双指针的解法————空间复杂度降到O(1)*/
    /**
     *【双指针的解法】两次遍历，分别用left和right记录遍历过程遇到的左括号和右括号。两次遍历的区别在于————
     *     1. 从左到右遍历时，如果过程中右括号的数量大于左括号的数量即right>left，则需要将left和right重置为0......因为此时
     *  在当前的基础上不能再增加有效括号的长度了；但是left>right时，后续如果遇到right，就有可能增大有效括号的长度
     *     2. 从右到左遍历时，正好相反。如果某一时刻left>right，则将left以及right重置为0
     * 【疑问】为什么需要两次遍历？？
     * 【相似的题】①678（包含左右括号、星号）；②力扣135分发糖果
     */
    public int longestValidParentheses_point(String s) {
        int left = 0,right = 0; //分别记录遍历中遇到的左括号、右括号
        int res = 0; //记录答案
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='(') left++;
            else right++;
            if (left==right) res = Math.max(res,2*right);
            if (right>left) left = right =0;  /**两次遍历的唯一区别*/
        }

        left = right = 0; /**err：left和right会出初始值，少不了。。否则出事的测试用例"(()"就返回了错误的结果4*/
        for (int i = s.length()-1; i >=0 ; i--) {
            if (s.charAt(i)==')') right++;
            else  left++;
            if (left==right) res = Math.max(res,2*right);
            if (left>right) left = right = 0;
        }
        return res;
    }



    public static void main(String[] args) {
        _14DP dp = new _14DP();
//        System.out.println(dp.numSquares(13));

        String s = "leetcode";
        ArrayList<String> strings = new ArrayList<>() {{
            add("leet");
            add("code");
        }};
        dp.wordBreak(s,strings);
    }

/**================================hot100之外======================================*/
    /*718. 最长重复子数组
    * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
    *   子数组是连续的！
    * */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int res = 0;
        for (int i=1;i<= nums1.length;i++)
            for (int j=1;j<= nums2.length;j++){
                if (nums1[i-1]==nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                /**err：不能出现下面的else。因为子数组必须是连续的，如果nums1[i]≠nums2[j]，则以它两结尾
                 * 的最长相同子数组就是0*/
//                else{
//                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
//                }
                res = Math.max(dp[i][j],res);
            }

        return res;
    }

}