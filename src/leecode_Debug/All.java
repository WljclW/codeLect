package leecode_Debug;

import leecode_Debug.top100.ListNode;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/5/16 15:20
 */
public class All {
    public void moveZeroes(int[] nums) { //283
        int cur = 0,left = 0;
        while(cur<nums.length){
            if (nums[cur]!=0){
                nums[left] = nums[cur];
                left++;
                cur++;
            }else
                cur++;
        }
        /*需要将后续的置零*/
        while(left<nums.length){
            nums[left++]=0;
        }
    }

    public int maxArea(int[] height){ //11
        int left = 0,right = height.length-1;
        int Max = Integer.MIN_VALUE;
        while(left<right){
            int cur = Math.min(height[left],height[right])*(right-left);
            Max = Math.max(cur,Max);
            if (height[left]<height[right]) left++;
            else right--;
        }
        return Max;
    }


    public List<List<Integer>> threeSum(int[] nums){
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int first = 0;
        while (first<nums.length-2){
            while (first>0&&nums[first]==nums[first-1]) first++;
            int sec = first+1,third = nums.length-1;
            int target = -nums[first];
            while(sec<third){
                int cur = nums[sec]+nums[third];
                while (sec<third&&nums[sec]==nums[sec-1]) sec++;
                while(sec<third&&third< nums.length&&nums[third]==nums[third+1]) third--;
                if (cur<target) sec++;
                else if (cur>target) {
                    third--;
                }else{
                    LinkedList<Integer> ele = new LinkedList<>();
                    ele.add(nums[first]);
                    ele.add(nums[sec]);
                    ele.add(nums[third]);
                }
            }
        }
        return res;
    }

    /*53.
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元
     *   素），返回其最大和。( 子数组是数组中的一个连续部分)
     * */
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int pre = 0;
        for (int i=0;i<nums.length;i++){
            int cur = Math.max(pre+nums[i],nums[i]);
            pre = cur;
            res = Math.max(res,cur);
        }
        return res;
    }



    /*56.
     *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
     * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * */
    public int[][] merge(int[][] intervals){
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        LinkedList<int[]> res = new LinkedList<>();
        res.add(intervals[0]);
        for (int i=1;i<intervals.length;i++){
            if (intervals[i][0]>res.getLast()[1]){
                res.add(intervals[i]);
            }else {
                res.getLast()[1] = Math.max(intervals[i][1],res.getLast()[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    /*238.
    * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素
    * 的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    * */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i=1;i<nums.length;i++){
            res[i] = res[i-1]*nums[i-1];
        }
        int post = nums[nums.length-1];
        for (int i=nums.length-2;i>=0;i--){
            res[i] *= (post);
            post = post*nums[i];

        }
        return res;
    }



    /*41.
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
//    public int firstMissingPositive(int[] nums) {
//
//    }


     /*121.
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一
        个算法来计算你所能获取的最大利润。
      返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    * */
    public int maxProfit(int[] prices) {
        int curMin = prices[0];
        int res = 0;
        for (int i=0;i<prices.length;i++){
            curMin  = Math.min(curMin,prices[i]);
            res = Math.max(res,prices[i]-curMin);
        }
        return res;
    }



        /*
    * 55.
    * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在
    * 该位置可以跳跃的最大长度。
        判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
    * */
    public boolean canJump(int[] nums) {
        if (nums.length==1) return true;
        int cur = 0,bound = 0;
        while(cur<=bound){
            bound = Math.max(cur+nums[cur],bound);
            if (bound>=nums.length-1) return true;
            cur++;
        }
        return false;
    }


     /*763.
    * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片
    * 段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或
    * ["ab", "ab", "cc"] 的划分是非法的。
        注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
        返回一个表示每个字符串片段的长度的列表。
    * */
    // 763的写法1
    public List<Integer> partitionLabels(String s) {
        LinkedList<Integer> res = new LinkedList<>();
        int[] flags = new int[26];
        for (int i=0;i<s.length();i++){
            flags[s.charAt(i)-'a'] = i;
        }

        int bound = flags[s.charAt(0)-'a'];
        int cur = 0;
        for (int i=0;i<s.length();i++){
            bound = Math.max(bound,flags[s.charAt(i)-'a']);
            if (i==bound){
                res.add(bound- cur+1);
                cur = bound+1;
            }
        }
        if (cur!=s.length()){
            res.add(bound-cur+1);
        }
        return res;
    }


    /*75.
    * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使
    * 得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    必须在不使用库内置的 sort 函数的情况下解决这个问题。*/
    public void sortColors(int[] nums) {
        int left  =0,right = nums.length-1;
        int cur = 0;
        while(cur<=right){
            if (nums[cur]==0){
                swap(nums,cur++,left++);
            } else if (nums[cur]==2) {
                swap(nums,cur,right--);
            }else
                cur++;
        }
    }

    void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }



    /*136.
   * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均
   * 出现两次。找出那个只出现了一次的元素。
   你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。*/
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i:nums){
            res ^= i;
        }
        return res;
    }




    /*169.
    题解：https://leetcode.cn/problems/majority-element/solutions/2362000/169-duo-shu-yuan-su-mo-er-tou-piao-qing-ledrh/
    * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现
    * 次数 大于 ⌊ n/2 ⌋ 的元素。
    你可以假设数组是非空的，并且给定的数组总是存在多数元素。*/
    public int majorityElement(int[] nums) {
        int cur = 0;
        int flag = -1;
        for (int i:nums){
            if (cur==0){
                flag = i;
            }
            cur = flag==i?cur+1:cur-1;
        }
        return flag;
    }



    /**287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
    public int findDuplicate(int[] nums) {
        for (int i=0;i<nums.length;i++){
            int cur = nums[i];
            if (nums[cur-1]!=cur){
                swap(nums,nums[i]-1,i);
            }
        }

        for (int i=0;i<nums.length;i++){
            if (nums[i]!=nums[i]+1){
                return nums[i];
            }
        }
        return -1;
    }



    /**20.
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是
    * 否有效。
    有效字符串需满足：
    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    每个右括号都有一个对应的相同类型的左括号。*/
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>() {
            {
                put('(',')');
                put('[',']');
                put('{','}');
            }
        };
        LinkedList<Character> stack = new LinkedList<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (map.containsKey(c)){
                stack.push(c);
            }else {
                if (stack.isEmpty() || map.get(stack.peek())!=c){
                    return false;
                }
                stack.pop();
            }
        }
        return !stack.isEmpty();
    }



    /**739.
    给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第
     i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    * */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<ArrayList<Integer>> stack = new LinkedList<>();
        for (int i=1;i<temperatures.length;i++){
            int cur = temperatures[i];
            int size = stack.peek().size();
            if (stack.isEmpty() || cur<temperatures[stack.peek().get(size-1)]){
                ArrayList<Integer> ele = new ArrayList<>();
                ele.add(i);
                stack.push(ele);
                continue;
            }
            if (cur==temperatures[stack.peek().get(size-1)]){
                stack.peek().add(i);
                continue;
            }
            while (cur>temperatures[stack.peek().get(size-1)]){
                ArrayList<Integer> pop = stack.pop();
                for (int j:pop){
                    res[j] = i;
                }
            }
        }
        return res;
    }



    /*160.
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果
     * 两个链表不存在相交节点，返回 null 。保证不出现环*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA,p2 = headB;
        while(p1!=p2){
            p1 = (p1==null)?headB:p1.next;
            p2 = (p2==null)?headA:p2.next;
        }
        return p1;
    }

    /**206
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre=null,cur=head,next = head;
        while(cur!=null){
            cur.next = pre;
            next = next.next;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*141
     * 给你一个链表的头节点 head ，判断链表中是否有环。*/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast) return true;
        }
        return false;
    }


    /*142.
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast){
                fast = head;
                while(slow!=fast){
                    slow=slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }


    /**21.
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while (list1!=null || list2!=null){
            if (list1.val<list2.val){
                res.next = list1;
                list1 = list1.next;
            }else{
                res.next = list2;
                list2 = list2.next;
            }
            res = res.next;
        }
        res.next = (list1==null)?list2:list1;
        return cur.next;
    }


    /**2.
    * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int carry = 0;
        while(l1!=null||l2!=null){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curVal = val1+val2;
            carry = curVal /10;
            cur.next = new ListNode(carry%10);
            cur = cur.next;
            if (l1!=null) l1 = l1.next;
            if (l2!=null) l2=l2.next;
        }
        return res.next;
    }



    /*19.
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy,fast = head;
        while (n>0){
            fast = fast.next;
            n--;
        }
        while (fast!=null){
            slow =slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }




    public void permutation(int[] nums){

    }





    /*23.
    * 给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。*/
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        for (ListNode ln:lists){
            if (ln!=null) queue.offer(ln);
        }
        while(!queue.isEmpty()){
            ListNode now = queue.poll();
            if (now.next!=null) queue.offer(now.next);
            cur.next = now;
            cur = cur.next;
        }
        return dummy.next;
    }


//    public ListNode mergeKLists(ListNode[] lists) {
//
//    }
//
//
//    public ListNode merge(ListNode[] lists,int l,int r){
//        if (l==r){
//            return lists[l];
//        }
//        int mid = l+(r-l)>>1;
//        return merge(lists,l,mid)
//    }



    /*
    * 70.假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    * */
    public int climbStairs(int n) {
        if (n==1) return 1;
        if (n==2) return 2;
        return climbStairs(n-1)+climbStairs(n-2);
    }




    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
//    public int coinChange(int[] coins, int amount){
//
//    }



        /*62.
    * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标
    * 记为 “Finish” ）。
    问总共有多少条不同的路径？*/
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for (int i=0;i<n;i++){
            res[0][i] = 1;
        }
        for (int j=0;j<m;j++){
            res[j][0] = 1;
        }
        for (int i=1;i<n;i++)
            for (int j=1;j<m;j++){
                res[j][i] = res[j-1][i]+res[j][i-1];
            }
        return res[m-1][n-1];
    }



    /*64.
    * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路
    * 径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。*/
    public int minPathSum(int[][] grid) {
        int res = Integer.MAX_VALUE;
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1;i<grid.length;i++){
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for (int j=1;j<grid[0].length;j++){
            dp[0][j] = dp[0][j-1]+grid[0][j];
        }
        for (int i=1;i<grid.length;i++)
            for (int j=1;j<grid[0].length;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        return dp[grid.length-1][grid[0].length-1];
    }








    //438
//    public List<Integer> findAnagrams(String s, String p) {
//        ArrayList<Integer> res = new ArrayList<>();
//        int[] needs = new int[26];
//        int[] window = new int[26];
//
//    }


    public static void main(String[] args) {
        All all = new All();
        all.partitionLabels("ababcc");
    }

}
