package leecode_Debug._hot100;

import leecode_Debug.linkList.reverse;
import leecode_Debug.top100.ListNode;

import javax.print.DocFlavor;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;


public class review02 {
    /**=======================06链表*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA,p2 = headB;
        while(p1!=p2){
            p1 = (p1==null)?headB:p1.next;
            p2 = (p2==null)?headA:p2.next;
        }
        return p1;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null,cur = head,next = head;
        while(cur!=null){
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast) return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast==slow){
                fast = head;
                while(slow!=fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(list1!=null&&list2!=null){
            if (list1.val<list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = (list1==null)?list2:list1;
        return dummy.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1!=null||l2!=null||carry!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int sum = val1+val2+carry;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            carry = sum/10;
            if (l1!=null) l1=l1.next;
            if (l2!=null) l2 =l2.next;
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1,head);
        ListNode slow = dummy,fast = head;
        for (int i=0;i<n&&fast!=null;i++){
            fast = fast.next;
        }
        while (fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;
            ListNode next = node2.next;
            cur.next = node2;
            node1.next = next;
            node2.next = node1;
            cur = node1;
        }
        return dummy.next;
    }

//    public ListNode reverseKGroup(ListNode head, int k) {
//        ListNode dummy = new ListNode(-1,head);
//        ListNode cur = dummy,pre = dummy;
//        while(pre!=null){
//            while(k-->0&&pre!=null) pre = pre.next;
//            if (pre==null) return dummy.next;
//            ListNode start = pre.next;
//            ListNode  nextStart = pre.next;
//            pre.next = null;
//            ListNode newStart = reverse(start);
//            cur.next = nextStart;
//        }
//    }
//
//    private ListNode reverse(ListNode start) {
//
//    }

    public Node copyRandomList(Node head) {
        Node cur = head;
        while(cur!=null){
            cur.next = new Node(cur.val);
            cur = cur.next.next;
        }

        cur = head;
        while(cur!=null){
            if (cur.random!=null){
                cur.next.random = cur.random.next;
            }
        }

        cur = head;
        Node res = cur.next;
        Node resCur = res;
        while(cur!=null){
            cur.next = resCur.next;
            cur = cur.next.next;
            resCur.next = cur.next;
            resCur = resCur.next;
        }
        return res;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        if (lists.length==1) return lists[0];
        return merge(lists,0,lists.length-1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = left +(right-left)/2;
        ListNode l = merge(lists, left, mid);
        ListNode r = merge(lists, mid + 1, right);
        return mergeTwo(l,r);
    }

    private ListNode mergeTwo(ListNode m1, ListNode m2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(m1!=null&&m2!=null){
            cur.next = (m1.val<m2.val)?m1:m2;
            if (m1.val<m2.val){
                m1 = m1.next;
            }else{
                m2 = m2.next;
            }
            cur = cur.next;
        }
        cur.next = (m1==null)?m2:m1;
        return dummy.next;
    }

    /**=======================09huisu*/
    List<List<Integer>> res = new LinkedList<List<Integer>>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {

        subsetsTrace(nums,0);
        return res;
    }

    private void subsetsTrace(int[] nums, int index) {
        res.add(new LinkedList<>(path));
        for (int i=index;i<nums.length;i++){
            path.add(nums[i]);
            subsetsTrace(nums,i+1);
            path.removeLast();
        }
    }

    HashMap<Character,String> map;
    List<String> letterCombinationsRes;
    public List<String> letterCombinations(String digits) {
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
        letterCombinationsRes = new LinkedList<>();
        if (digits==null||digits.length()==0) return letterCombinationsRes;
        letterCombinationsTrace(digits,0,new StringBuilder());
        return letterCombinationsRes;
    }

    private void letterCombinationsTrace(String digits, int index, StringBuilder stringBuilder) {
        if (index==digits.length()){
            letterCombinationsRes.add(new String(stringBuilder));
            return;
        }
        for (int i=0;i<map.get(digits.charAt(index)).length();i++){
            String s = map.get(digits.charAt(index));
            char c = s.charAt(i);
            stringBuilder.append(c);
            letterCombinationsTrace(digits,index+1,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

    List<List<Integer>> combinationSumRes;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumRes = new LinkedList<>();
        combinationSumTrace(candidates,0,target,new LinkedList<Integer>());
        return combinationSumRes;
    }

    private void combinationSumTrace(int[] candidates, int index, int target, LinkedList<Integer> path) {
        if (target==0){
            combinationSumRes.add(new LinkedList<>(path));
            return;
        }
        if (target<0){
            return;
        }
        for (int i=index;i<candidates.length;i++){
            target -= candidates[i];
            path.add(candidates[i]);
            combinationSumTrace(candidates,index,target,path);
            target += candidates[i];
            path.removeLast();
        }
    }

    List<List<Integer>> permuteRes;
    public List<List<Integer>> permute(int[] nums) {
        permuteRes = new LinkedList<>();
        List<Integer> path = new LinkedList<Integer>();
        boolean[] used = new boolean[nums.length];
        permuteTrace(nums,path,used);
        return permuteRes;
    }

    private void permuteTrace(int[] nums, List<Integer> path, boolean[] used) {
        if (path.size()==nums.length){
            permuteRes.add(new LinkedList<>(path));
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

    public boolean exist(char[][] board, String word) {
        int m = board.length,n = board[0].length;
        for (int i=0;i<m;i++)
            for (int j=0;j<n;j++){
                if (board[i][j]==word.charAt(0)){
                    if (existTrace(board,i,j,word,0)){
                        return true;
                    }
                }
            }
        return false;
    }

    private boolean existTrace(char[][] board, int i, int j, String word, int index) {
        if (index==word.length()){
            return true;
        }
        if (i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!=word.charAt(index)){
            return false;
        }
        board[i][j] = '\n';
        boolean cur =  existTrace(board,i-1,j,word,index+1)||
                existTrace(board,i+1,j,word,index+1)||
                existTrace(board,i,j-1,word,index+1)||
                existTrace(board,i,j+1,word,index+1);
        board[i][j] = word.charAt(index);
        return cur;
    }

    List<List<String>> solveNQueensRes;
    public List<List<String>> solveNQueens(int n) {
        solveNQueensRes = new LinkedList<>();
        char[][] dp = new char[n][n];
        for (int i=0;i<n;i++){
            Arrays.fill(dp[i],'.');
        }
        solveNQueensTrace(n,dp,0);
        return solveNQueensRes;
    }

    private void solveNQueensTrace(int n, char[][] dp, int row) {
        if (row==n){
            solveNQueensRes.add(convertToEle(dp));
            return;
        }
        for (int i=0;i<n;i++){
            if (checkValid(dp,row,i)){
                dp[row][i] = 'Q';
                solveNQueensTrace(n,dp,row+1);
                dp[row][i] = '.';
            }
        }
    }

    private boolean checkValid(char[][] dp, int row, int col) {
        for (int i=0;i<row;i++){
            if (dp[i][col]=='Q') return false;
        }
        for (int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if (dp[i][j] =='Q') return false;
        }
        for (int i=row-1,j=col+1;i>=0&&j<dp[0].length;i--,j++){
            if (dp[i][j]=='Q') return false;
        }
        return true;
    }

    private List<String> convertToEle(char[][] dp) {
        LinkedList<String> res = new LinkedList<>();
        for (int i=0;i<dp.length;i++){
            res.add(new String(dp[i]));
        }
        return res;
    }


    /**===========================10二分查找*/
    public int searchInsert(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<target){
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return l;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int l = 0,r = m*n-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur==target) return true;
            else if (cur>target) r = mid-1;
            else l = mid+1;
        }
        return false;
    }

    public int[] searchRange(int[] nums, int target) {
        int left = serLeft(nums,target);
        if (left==nums.length||nums[left]!=target) return new int[]{-1,-1};
        int right = serLeft(nums,target+1);
        return new int[]{left,right-1};
    }

    private int serLeft(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]<target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return left;
    }

    public int findMin(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]<nums[nums.length-1]){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return nums[left];
    }


    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>=nums[0]){
                if (target>=nums[0]&&target<nums[mid]){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }else{
                if (target>nums[mid]&&target<=nums[nums.length-1]){
                    left = mid +1;
                }else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    /**==============================11栈*/
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        HashMap<Character, Character> map = new HashMap<>(){{
            put(')','(');
            put('}','{');
            put(']','[');
        }};
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (!map.containsKey(c)){
                stack.push(c);
            }else{
                if (stack.isEmpty()||stack.peek()!=map.get(c)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int[] dailyTemperatures(int[] temperatures) {
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

    public int largestRectangleArea(int[] heights) {
        int res = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i=0;i<heights.length;i++){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                Integer cur = stack.pop();
                int left = stack.isEmpty()?0:stack.peek();
                int curVal = (i-left-1)*heights[cur];
                res = Math.max(res,curVal);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            Integer cur = stack.pop();
            int left = stack.isEmpty()?0:stack.peek();
            int curVal = (heights.length-left-1)*heights[cur];
            res = Math.max(res,curVal);
        }

        return res;
    }


    public int largestRectangleArea01(int[] heights) {
        Deque<Integer> stack = new LinkedList<>(); // 存储索引
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i]; // 处理最后一个元素

            // 当前高度小于栈顶元素，弹出栈顶并计算面积
            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i); // 当前索引入栈
        }

        return maxArea;
    }

    /**======================贪心*/
    public int maxProfit(int[] prices) {
        int res = 0,min = prices[0];
        for(int i=0;i<prices.length;i++){
            min = Math.min(min,prices[i]);
            res = Math.max(res,prices[i]-min);
        }
        return res;
    }

    public boolean canJump(int[] nums) {
        int cur = 0,bound = 0;
        while(cur<nums.length){
            bound = Math.max(bound,cur+nums[cur]);
            if (bound>=nums.length-1){
                return true;
            }
            if (cur==bound){
                return false;
            }
            cur++;
        }
        return false;
    }

    public List<Integer> partitionLabels(String s) {
        int[] flag = new int[26];
        for (int i = 0; i < s.length(); i++) {
            flag[s.charAt(i)-'a'] = i;
        }

        LinkedList<Integer> res = new LinkedList<>();
//        int left = -1,cur = 0;
        int left = 0,cur = 0;
        int bound = 0;
        while(cur<s.length()){
            bound = Math.max(bound,flag[s.charAt(cur)-'a']);
            if (cur==bound){
                res.add(cur-left+1);
                left = bound+1;
            }
            cur++;
        }
        return res;

    }

    /**===================================14dp*/
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i=0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        for (int i=0;i<coins.length;i++){
            dp[i][0] = 0;
        }
        for (int i=1;i<=amount;i++){
            if (i%coins[0]==0) dp[0][i] = i/coins[0];
        }
        for (int i=0;i<coins.length;i++)
            for (int j = 0; j <= amount; j++) {
                if (j>coins[i]&&dp[i][j-coins[i]]!=-1){
                    if (dp[i-1][j]!=-1)
                        dp[i][j] = Math.min(dp[i-1][j],dp[i][j-coins[i]]+1);
                    else
                        dp[i][j] = dp[i][j-coins[i]]+1;
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        return dp[coins.length-1][amount];
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String str:wordDict){
            set.add(str);
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i=1;i<=s.length();i++){
            for (int j=0;j<i;j++){
                if (dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public int maxProduct(int[] nums) {
//        int[] maxPre = new int[nums.length];
//        int[] minPre = new int[nums.length];
//        int res = nums[0];
//        for (int i=1;i<nums.length;i++){
//            maxPre[i] = Math.max(nums[i],Math.max(minPre[i-1]*nums[i],maxPre[i-1]*nums[i]));
//            minPre[i] = Math.min(nums[i],Math.min(minPre[i-1]*nums[i],maxPre[i-1]*nums[i]));
//            res = Math.max(maxPre[i],res);
//        }
//        return res;

        int[] maxPre = new int[nums.length];
        int[] minPre = new int[nums.length];
        int res = nums[0];
        minPre[0] = nums[0];
        maxPre[0] = nums[0];
        for (int i=1;i<nums.length;i++){
            maxPre[i] = Math.max(nums[i],Math.max(minPre[i-1]*nums[i],maxPre[i-1]*nums[i]));
            minPre[i] = Math.min(nums[i],Math.min(minPre[i-1]*nums[i],maxPre[i-1]*nums[i]));
            res = Math.max(maxPre[i],res);
        }
        return res;
    }


    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num:nums){
            sum += num;
        }
        if (sum%2!=0) return false;
        int target = sum/2;

        int[] dp = new int[target + 1];
        for (int j=target;j>=0;j--)
            for (int i = 0; i < nums.length; i++) {
                if (j>=nums[i])
                    dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]);
                else
                    break;
        }
        return dp[target]==target;
    }

    public int longestValidParentheses(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i=1;i<s.length();i++){
            char c = s.charAt(i);
            if (c==')'){
                if (s.charAt(i-1)=='('){
                    dp[i] = (i>=2)?dp[i-2]+2:2;
                }else { //')'
                    if (i-dp[i-1]>0&&s.charAt(i-dp[i-1]-1)=='('){
                        dp[i] = (i-dp[i-1]-2>=0)?dp[i-dp[i-1]-2]+2+dp[i-1]:2+dp[i-1];
                    }
                }
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }


    /**================================15多维dp*/
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i=1;i<grid.length;i++){
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for (int i=1;i<grid[0].length;i++){
            dp[0][i] = dp[0][i-1]+grid[0][i];
        }
        for (int i=1;i<grid.length;i++)
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]);
            }
        return dp[grid.length-1][grid[0].length];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i=1;i<=m;i++)
            for (int j = 0; j <= n; j++) {
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = Math.max(dp[i-1][j],Math.max(dp[i][j-1],dp[i-1][j-1]+1));
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        return dp[m][n];
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= n; j++) {
                if (i==0||j==0){
                    dp[i][j] = (i==0)?j:i;
                    continue;
                }
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        return dp[m][n];
    }


    /**=============================trike*/
    public int majorityElement(int[] nums) {
        int candi = 0,flag = nums[0];
        for (int num:nums){
            if (candi==0) flag = num;
            candi += (flag==num)?1:-1;
        }
        return flag;
    }

    public void sortColors(int[] nums) {
        int left  =0,cur = 0,right = nums.length-1;
        while (cur<=right){
            if (nums[cur]<1){
                swap(nums,left,cur);
                left++;
            }else if (nums[cur]==1){
                cur++;
            }else{
                swap(nums,cur,right);
                right--;
            }
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right]  =tmp;
    }

    public void nextPermutation(int[] nums) {
        int flagIndex = -1;
        for (int i = nums.length-2; i >= 0; i--) {
            if (nums[i]<nums[i+1]){
                flagIndex = i;
                break;
            }
        }
        if (flagIndex == -1){
            int left = 0,right = nums.length-1;
            while(left<right){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                right--;
                left++;
            }
        }

        for (int i= nums.length-1;i>=flagIndex;i--){
            if (nums[i]>nums[flagIndex]){
                int tmp = nums[i];
                nums[i] = nums[flagIndex];
                nums[flagIndex] = tmp;
                break;
            }
        }

        int left = flagIndex+1,right = nums.length-1;
        while (left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            right--;
            left++;
        }
    }

    public static void main(String[] args) {
        review02 review02 = new review02();
        review02.nextPermutation(new int[]{1,3,2});

        review02.maxProduct(new int[]{-4,-3,-2});

        review02.canPartition(new int[]{3,3,3,4,5});

        review02.longestValidParentheses("()(())");

        review02.subsets(new int[]{1,2,3});
    }
}
