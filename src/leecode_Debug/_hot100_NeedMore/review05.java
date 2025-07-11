package leecode_Debug._hot100_NeedMore;

import leecode_Debug.top100.ListNode;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/7/11 15:29
 */
public class review05 {
    //01
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num:nums){
            set.add(num);
        }

        int res =1;
        for (int num:nums){
            if (!set.contains(num-1)){
                int sum = 0;
                while (set.contains(num+sum)) sum++;
                res = Math.max(res,sum);
            }
        }
        return res;
    }


    /*
     * 283.给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 【升级题目】：熟悉颜色分类，75。。。。
     *       这种题目的思路就是：假想left指向左边界的最后一个；right指向右边界的第一个；cur表示当前
     *    研究到的位置
     * */
    public void moveZeroes(int[] nums) {
        int left = 0,cur = 0;
        for (cur = 0;cur<nums.length;cur++){
            if (nums[cur]!=0){
                swap(nums,cur,left++);
            }
        }
    }
    private void swap(int[] nums, int left, int right) {
        int tmp  =nums[left];
        nums[left] = nums[right];
        nums[right] =tmp;
    }

    /*
     * 11.给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
     *    成的，柱子之间是没有间隙的。
     * */
    /**
     * 【步骤】双指针相向而行，计算以它们为边界能盛多少水。。只要中间有间距（即left<right）
     *      1. 每到一个位置计算能盛的水（高度取决于左右指针height的最小值，宽度取决于两个指针的距离）
     *      2. 看两个指针对应的height哪一个高，更新height小的那一个指针
     */
    public int maxArea(int[] height) {
        int l = 0,r = height.length-1;
        int res = 0;
        while (l<r){
            int curVal = Math.min(height[l],height[r])*(r-l);
            res = Math.max(res,curVal);
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
        }
        return res;
    }

    //15
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i>0&&nums[i]==nums[i-1]) continue;
            int left = i=1,right = nums.length-1;
            while (left<right){
                int curVal = nums[i] + nums[left] + nums[right];
                if (curVal>0){
                    right--;
                } else if (curVal<0) {
                    left++;
                }else {
                    LinkedList<Integer> ele = new LinkedList<>(Arrays.asList(nums[i], nums[left], nums[right]));
                    res.add(ele);
                    while (left<right&&nums[left]==nums[++left]);
                    while (left<right&&nums[right]==nums[--right]);
                }
            }
        }
        return res;
    }

    /*
     * 42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * */
    public int trap(int[] height) {
        int leftMax = height[0],rightMax = height[height.length-1];
        int left = 0,right = 0;
        int res = 0;
        while (left<right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
            if (height[left]<height[right]){
                res += (leftMax-height[left++]);
            }else{
                res += (rightMax-height[right--]);
            }
        }
        return res;
    }

    //3
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int res = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
            while (map.get(c)>1){
                char leftChar = s.charAt(left);
                map.put(leftChar,map.get(leftChar)-1);
                left++;
            }
            res  = Math.max(i-left+1,res);
        }
        return res;
    }


    /*560.
    * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int pre = 0;
        int sum = 0;
        for (int num:nums){
            pre += num;
            sum += map.getOrDefault(pre-k,0);
            map.put(pre,map.getOrDefault(pre,0)+1);
        }
        return sum;
    }

    /*56.
     *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
     * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals,(o1,o2)->{
            return o1[0]-o2[0];
        });
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0]<=res.getLast()[1]){
                res.getLast()[1] = Math.max(cur[1],res.getLast()[1]);
            }else{
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /*41.
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i = 1;
            }
        }
        return nums.length + 1;
    }


    /*240.
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int row = 0,col = n-1;
        while (row<m && col>=0){
            if (target==matrix[row][col]){
                return true;
            } else if (target>matrix[row][col]) {
                col--;
            }else {
                row++;
            }
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
                while (fast!=slow){
                    slow = slow.next;
                    fast =fast.next;
                }
                return fast;
            }
        }
        return null;
    }

    /*19.
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    /**解答此题的关键在于：块指针其实需要比满指针先走n+1步*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy,fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast!=null){
            slow = slow.next;
            fast =fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


    /*24.
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下
     * 完成本题（即，只能进行节点交换）。*/
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next!=null && cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;

            node1.next = node2.next;
            node2.next  =node1;
            cur.next  = node2;

            cur = node1; //重置cur指针为当前这组的第一个节点
        }
        return dummy.next;
    }

    //35
    public int searchInsert(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]>=target){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return l;
    }

    //74
    public boolean searchMatrix1(int[][] matrix, int target) {
        int n = matrix[0].length,l = 0,r = matrix.length*n-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (target>matrix[mid/n][mid%n]){
                r = mid-1;
            } else if (target<matrix[mid/n][mid%n]) {
                l = mid+1;
            }else{
                return true;
            }
        }
        return false;
    }

    //33
    public int search(int[] nums, int target) {
        int l = 0,r= nums.length-1;
        while (l<=r){
            int mid = l +(r-l)/2;
            if (target==nums[mid]){
                return mid;
            }
            if (nums[mid]>=nums[0]){
                if (nums[0]<=target&&target<nums[mid]){
                    r = mid-1;
                }else {
                    l = mid+1;
                }
            }else {
                if (nums[mid]<target&&target<=nums[nums.length-1]){
                    l = mid+1;
                }else{
                    r = mid-1;
                }
            }
        }
        return -1;
    }

    //153
    public int findMin(int[] nums) {
        int l = 0,r = nums.length;
        while (l<r){
            int mid = l +(r-l)/2;
            if (nums[mid]>=nums[nums.length-1]){
                l = mid+1;
            }else {
                r = mid;
            }
        }
        return r;
    }

    //84
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int curHeight = (i== heights.length)?0:heights[i];

            while (!stack.isEmpty()&&heights[i]<heights[stack.peek()]){
                Integer pop = stack.pop();
                int leftBound = stack.isEmpty()?-1:stack.peek();
                int curArea = heights[pop]*(i-leftBound-1);
                res = Math.max(res,curArea);
            }
            stack.push(i);
        }
        return res;
    }

    //739
    public int[] dailyTemperatures(int[] temperatures) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                Integer cur = stack.pop();
                res[cur] = i-cur;
            }
            stack.push(i);
        }
        return res;
    }

    //121
    public int maxProfit(int[] prices) {
        int res = 0;
        int curMin = Integer.MAX_VALUE;
        for (int num:prices){
            curMin  =Math.min(num,curMin);
            res = Math.max(res,num-curMin);
        }
        return res;
    }

    //763
    public List<Integer> partitionLabels1(String s) {
        LinkedList<Integer> res = new LinkedList<>();
        int[] flags = new int[26];
        for (int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            flags[c-'a'] = i;
        }

        int left = 0,bound = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            bound = Math.max(bound,flags[i]);
            if (i==bound){
                res.add(i-left+1);
                left = bound+1;
            }
        }
        return res;
    }

    //169
    public int majorityElement(int[] nums) {
        int flag = -1,ticket = 0;
        for (int num:nums){
            if (ticket==0){
                flag = num;
            }
            flag += (flag==num)?1:-1;
        }
        return flag;
    }

    //46
    List<List<Integer>> resPermute;
    List<Integer> pathPermute;
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        resPermute = new LinkedList<List<Integer>>();
        pathPermute = new LinkedList<Integer>();
        used = new boolean[nums.length];
        permuteBack(nums);
        return resPermute;
    }

    private void permuteBack(int[] nums) {
        if (pathPermute.size()==nums.length){
            resPermute.add(new LinkedList<>(pathPermute));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                used[i] = true;
                pathPermute.add(nums[i]);
                permute(nums);
                used[i] = false;
                pathPermute.remove(pathPermute.size()-1);
            }
        }
    }


    //78
    List<List<Integer>> resSubsets;
    List<Integer> pathSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubsets = new LinkedList<>();
        pathSubsets = new LinkedList<>();
        subsetsBack(nums,0);
        return resSubsets;
    }

    private void subsetsBack(int[] nums, int index) {
        resSubsets.add(new LinkedList<>(pathSubsets));
        for (int i = index; i < nums.length; i++) {
            pathSubsets.add(nums[index]);
            subsetsBack(nums,i+1);
            pathSubsets.remove(pathSubsets.size()-1);
        }
    }


    //17
    List<String> resLetterCombinations;
    StringBuilder curLetterCombinations;
    public List<String> letterCombinations(String digits) {
        resLetterCombinations = new LinkedList<>();
        curLetterCombinations = new StringBuilder();
        HashMap<Character, String> map = new HashMap<>() {{
            put('2',"abc");
            put('3',"def");
            put('4',"ghi");
            put('5',"jkl");
            put('6',"mno");
            put('7',"pqrs");
            put('8',"tuv");
            put('9',"wxyz");
        }};
        letterCombinationsBack(digits,0,map);
        return resLetterCombinations;
    }

    private void letterCombinationsBack(String digits, int index,HashMap<Character,String> map) {
        if (curLetterCombinations.length()==digits.length()){
            resLetterCombinations.add(new String(curLetterCombinations));
            return;
        }

        char c = digits.charAt(index);
        String str = map.get(c);
        for (int i = 0; i < str.length(); i++) {
            char c1 = str.charAt(i);
            curLetterCombinations.append(c1);
            letterCombinationsBack(digits,index+1,map);
            curLetterCombinations.deleteCharAt(curLetterCombinations.length()-1);
        }
    }


     /*22.
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
    List<String> resGenerateParenthesis;
    StringBuilder curGenerateParenthesis;
    public List<String> generateParenthesis(int n) {
        resGenerateParenthesis = new LinkedList<>();
        curGenerateParenthesis = new StringBuilder();
        generateParenthesisback(n,0,0);
        return resGenerateParenthesis;
    }

    private void generateParenthesisback(int n, int open, int close) {
        if (curGenerateParenthesis.length()==2*n){
            resGenerateParenthesis.add(new String(curGenerateParenthesis));
            return;
        }
        if (curGenerateParenthesis.length()>2*n){
            return;
        }
        if (open<n){
            curGenerateParenthesis.append('(');
            generateParenthesisback(n,open+1,close);
            curGenerateParenthesis.deleteCharAt(curGenerateParenthesis.length()-1);
        }
        if (open>close){
            curGenerateParenthesis.append(')');
            generateParenthesisback(n,open,close+1);
            curGenerateParenthesis.deleteCharAt(curGenerateParenthesis.length()-1);
        }
    }


    //79
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(traceBack(board,i,j,word,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean traceBack(char[][] board,int i, int j, String word, int index) {
        if (index==word.length()){
            return true;
        }
        if (i<0||j<0||i>=board.length||j>=board[0].length||board[i][j] == '\n' ||board[i][j]!=word.charAt(index)){
            return false;
        }
        board[i][j] = '\n';
        return traceBack(board,i-1,j,word,index+1)||
         traceBack(board,i+1,j,word,index+1)||
         traceBack(board,i,j-1,word,index+1)||
         traceBack(board,i,j+1,word,index+1);
    }


    //51
    List<List<String>> resSolveNQueens;
    public List<List<String>> solveNQueens(int n) {
        char[][] flags = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(flags[i],'.');
        }
        solveNQueensBack(n,0,flags);
        return resSolveNQueens;
    }

    private void solveNQueensBack(int n, int row, char[][] flags) {
        if (row==n){
            resSolveNQueens.add(getString(flags));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(col,row,flags)){
                flags[row][col] = 'Q';
                solveNQueensBack(n,row+1,flags);
                flags[row][col] = '.';
            }
        }
    }

    private boolean isValid(int col, int row, char[][] flags) {
        for (int i = 0; i < row; i++) {
            if (flags[row][col]=='Q') return false;
        }

        for (int i = row-1,j = col-1; i >=0&&j>=0 ; i--,j--) {
            if (flags[row][col]=='Q') return false;
        }

        for (int i = row-1,j = col+1; i >=0&&j<flags.length ; i--,j++) {
            if (flags[row][col]=='Q') return false;
        }
        return true;
    }

    private List<String> getString(char[][] flags) {
        LinkedList<String> res = new LinkedList<>();
        for (int i = 0; i < flags.length; i++) {
           res.add(new String(flags[i]));
        }
        return res;
    }


}
