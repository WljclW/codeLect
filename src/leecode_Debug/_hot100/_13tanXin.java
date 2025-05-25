package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**45.763*/
public class _13tanXin {
    /*121.
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一
        个算法来计算你所能获取的最大利润。
      返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    * */
    /**关键的点：
     *     1.“每次判断更新最小值”：原因是，假设当前的位置i是目前遍历到的位置也是最小值m，遍历i位置之前遇到的
     *  最小值是n。因此基于前面的假设有m<n。那么对于未来的任何一天，从m买入肯定有更大的利润，因为m和n是买入
     *  的价格，相当于成本
     *     2.“贪心”贪的是什么？贪的是遇到的最小值。认为更小的成本对于同样的卖出价会有更高的利润。
     *     3.综上每到一个位置先更新遇到的最小值。结果有两种可能：
     *          3.1如果现在的值比当前标记的最小值minPrice 还小，就更新minPrice。并且当前位置获得的利润是0；
     *          3.2如果现在的值比当前标记的最小值minPrice 大，就不更新minPrice。并且当前位置获得的利润=当前
     *     索引的价格-minPrice*/
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxpro = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            maxpro = Math.max(prices[i] - minPrice, maxpro); /*遍历到每一个位置的决策*/
            minPrice = Math.min(minPrice, prices[i]); /*每一个位置都需要更新最小值，对于未来的某个位置可以获得更多的位置*/
        }
//        return maxpro;
        return maxpro < 0 ? 0 : maxpro;  //利润最小是0，大不了不买。。其实把maxpro初始值设置为0就可以了
    }


    /*
    * 55.
    * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在
    * 该位置可以跳跃的最大长度。
        判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
    * */
    /**1. "能不能到最后一个下标"只是看能走的最远距离，用一个变量标识能到的最远距离
     * 2. "贪心"贪的是什么？？是当前走过的位置，决定了最远能到达多远*/
    public boolean canJump(int[] nums) {
        if(nums.length==1) return true;

        int cur = 0; //表示当前研究到的索引
        int bound = 0; //表示 最远可以到达的边界
        while(cur<=bound){ /*只要当前到达的位置 没有越界(没有超过当前情况下最远可以去的地方)就循环逻辑*/
            int now = nums[cur]; //表示当前的位置最多走几步
            bound = Math.max(bound,cur+now); //更新可以到达的最远边界
            cur++;
            if(bound>= nums.length-1) return true;  /**err：bound是索引，因此达到“nums.length-1”就可以*/
        }
        return false; //出了循环说明bound没有到数组末尾，但是不能再走了，因为cur已经超出能走的边界了
    }

    //for循环的写法
    public boolean canJump_for(int[] nums) {
        int bound = 0;
        for (int i=0;i<nums.length;i++){
            /**err：这句话必须写在if中，保证位置i是有效的*/
//            bound = Math.max(bound, i + nums[i]);
            if (i <= bound) {
                bound = Math.max(bound, i + nums[i]);
                if (bound >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    /*45.
    * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
    每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你
    * 可以跳转到任意 nums[i + j] 处:
    0 <= j <= nums[i]
    i + j < n
    返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
    * */
    /**
     * 【注意】题目中说了，生成的用例可以到nums[n-1]，即肯定能到达最后一个元素
     * 【关键】关键在于搞清楚什么时候让步数+1
     * */
//    public int jump(int[] nums) {
//
//    }

    /*763.
    * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片
    * 段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或
    * ["ab", "ab", "cc"] 的划分是非法的。
        注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
        返回一个表示每个字符串片段的长度的列表。
    * */
    /**
     * 思路：官方解、灵茶山艾
     * 关键的点：
     *    1."flags数组"记录每个字母出现的最后的位置
     *    2.“依次遍历更新窗口内所有字符的最后位置变量，如果当前遍历到的位置能匹配上‘最后位置’说明就是一个合格的子串”
     * */
    // 763的写法1
    public List<Integer> partitionLabels(String s) {
        ArrayList<Integer> res = new ArrayList<>(); //记录结果

        /*记录每一个字符最后出现的位置*/
        int[] flags = new int[26];
        for (int i = 0; i < s.length(); i++) {
            flags[s.charAt(i) - 'a'] = i;
        }

        /*循环遍历，求满足要求的子串*/
        int start = 0, end = flags[s.charAt(0) - 'a'];
        for (int i = 0; i < s.length(); i++) {
            //每次到一个位置，先根据这个字符更新end的值。（end标记当前窗口内字符出现的右边界 这个信息）
            end = Math.max(flags[s.charAt(i) - 'a'], end);
            //如果当前位置i 来到 边界位置i。则产生一个区间，将长度添加进res
            if (i == end) {
                res.add(end - start + 1);
                start = i;
            }
        }
        return res;
    }


    //763的写法2.
    public List<Integer> partitionLabels0(String s) {
        LinkedList<Integer> res = new LinkedList<>();
        int[] flags = new int[26];
        for (int i=0;i<s.length();i++){
            flags[s.charAt(i)-'a'] = i;
        }

        int start = 0; /*表示当前一个划分的左边界*/
        int cur = 0; /*初始化遍历的位置为当前位置*/
        int bound = flags[s.charAt(0)-'a']; /*初始化边界为第一个字符的边界*/
        while(cur<s.length()){
            bound = Math.max(bound,flags[s.charAt(cur)-'a']/*表示当前这个字符最后出现的位置(索引，从0开始的)*/);
            if(cur==bound){ //到最后一个字符的时候必然会进入到这个if(最后一段区间的边界必然就是s的长度)，因为if外面不用额外添加剩余区间了
                res.add(bound-start+1);
                start = bound;
            }
            cur++;
        }
        return res;
    }


    public static void main(String[] args) {
        _13tanXin curClass = new _13tanXin();
        System.out.println(curClass.partitionLabels("dsadssbtyb"));
        System.out.println(curClass.partitionLabels0("dsadssbtyb"));

        curClass.canJump_for(new int[]{3,2,1,0,4});
    }
}
