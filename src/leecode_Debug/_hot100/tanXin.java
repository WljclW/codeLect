package leecode_Debug._hot100;

import java.util.List;

public class tanXin {
    /*121.
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一
        个算法来计算你所能获取的最大利润。
      返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    * */
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxpro = Integer.MIN_VALUE;
        for(int i=1;i<prices.length;i++){
            maxpro = Math.max(prices[i]-minPrice,maxpro);
            minPrice = Math.min(minPrice,prices[i]);
        }
        return maxpro;
    }


    /*
    * 55.
    * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在
    * 该位置可以跳跃的最大长度。
        判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
    * */
    public boolean canJump(int[] nums) {
        int cur = 0;
        int bound = 0;
        while(cur<=bound){ /*只要当前到达的位置没有越界*/
            int now = nums[cur];
            bound = Math.max(bound,cur+now);
            if(bound>= nums.length) return true;
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
//    public int jump(int[] nums) {
//
//    }

    /*73.
    * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片
    * 段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或
    * ["ab", "ab", "cc"] 的划分是非法的。
        注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
        返回一个表示每个字符串片段的长度的列表。
    * */
//    public List<Integer> partitionLabels(String s) {
//
//    }
}
