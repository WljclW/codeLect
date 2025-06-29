package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;

import javax.swing.*;
import java.util.*;

/**
 * @author mini-zch
 * @date 2025/6/27 17:28
 */
public class review03 {

    //239
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> Dqueue = new LinkedList<>();
        for (int i=0;i<k;i++){
            while(!Dqueue.isEmpty()&&nums[i]>=nums[Dqueue.peekLast()]){
                Dqueue.pollLast();
            }
            Dqueue.offerLast(i);
        }

        res[0] = nums[Dqueue.peekFirst()];
        for (int i=k;i<nums.length;i++){
            while(!Dqueue.isEmpty()&&nums[i]>=nums[Dqueue.peekLast()]){
                Dqueue.pollLast();
            }
            Dqueue.offerLast(i);
            if (Dqueue.peekFirst()==i-k){
                Dqueue.pollFirst();
            }
            res[i-k+1] = nums[Dqueue.peekFirst()];
        }
        return res;
    }

    //560
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0;
        int res = 0;
        for (int i=0;i<nums.length;i++){
            preSum += nums[i];
            res += map.getOrDefault(preSum-k,0);
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return res;
    }

    //53
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int curSum = nums[0];
        for (int i=1;i<nums.length;i++){
            curSum = Math.max(curSum+nums[i],nums[i]);
            res  =Math.max(res,curSum);
        }
        return res;
    }

    //56
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        LinkedList<int[]> res = new LinkedList<>();
        res.add(intervals[0]);
        for (int i=1;i<intervals.length;i++){
            int[] cur = intervals[i];
            if (cur[0]<=res.getLast()[1]){
                res.getLast()[1] = Math.max(cur[1],res.getLast()[1]);
            }else{
                res.add(cur);
            }
        }
        return new int[res.size()][];
    }

    //189
    public void rotate(int[] nums, int k) {
        rever(nums,0,nums.length-1);
        rever(nums,0,k-1);
        rever(nums,k, nums.length-1);
    }

    private void rever(int[] nums, int left, int right) {
        while(left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    //238
    public int[] productExceptSelf(int[] nums){
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i=1;i<nums.length;i++){
            res[i] = res[i-1]*nums[i-1];
        }

        int post = 1;
        for (int i=nums.length-2;i>=0;i--){
            post *= nums[i+1];
            res[i] *= post;
        }
        return res;
    }

    //41
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while(nums[i]>0&&nums[nums[i]-1]!=nums[i]){
                swap(nums,i,nums[i]-1);
            }
        }

        for (int i = 0; i <nums.length; i++) {
            if (nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    //234
    public boolean isPalindrome(ListNode head) {
        ListNode slow=head,fast = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode head2 = reverseNode(slow);
        while(head2!=null){
            if (head2.val!= head.val){
                return false;
            }
            head2 = head2.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverseNode(ListNode head) {
        ListNode cur = head,pre = null;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //142
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head,fast = head;
        while(fast!=null&&fast.next!=null){
            slow =slow.next;
            fast =fast.next.next;
            if (fast==slow){
                fast = head;
                while(fast!=slow){
                    fast  =fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    //2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while(l1!=null||l2!=null||carry!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curSum = val1+val2+carry;
            cur.next = new ListNode(curSum%10);
            carry = curSum/10;
            cur = cur.next;

            if (l1!=null) l1=l1.next;
            if (l2!=null) l2=l2.next;
        }
        return dummy.next;
    }

    //19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy,fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    //24
    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;
//            ListNode nextStart = node2
            node1.next = node2.next;
            cur.next = node2;
            node2.next = node1;
            cur = cur.next.next;
        }
        return dummy.next;
    }

    //25
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy,end = dummy;
        while (pre.next!=null){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end==null) break;

            ListNode start = pre.next;
            ListNode nextStart = end.next;

            end.next = null;
            pre.next = reverseKGroup_reverse(start);
            start.next = nextStart;

            pre = start;
            end = start;
        }
        return dummy.next;
    }

    private ListNode reverseKGroup_reverse(ListNode start) {
        ListNode pre = null,cur = start;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //23
    /*
    lists =
    [[1,4,5],[1,3,4],[2,6]]
    输出
    [1,1,2,3,4]
    预期结果
    [1,1,2,3,4,4,5,6]
    * */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        return merger(lists,0,lists.length-1);
    }

    private ListNode merger(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = left+(right-left)/2;
        ListNode leftNode = merger(lists, left, mid);
        ListNode rightNode = merger(lists, mid+1, right);
        return mergerTwo(leftNode,rightNode);
    }

    private ListNode mergerTwo(ListNode leftNode, ListNode rightNode) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (leftNode!=null&&rightNode!=null){
            if (leftNode.val<rightNode.val){
                cur.next = leftNode;
                leftNode = leftNode.next;
            }else{
                cur.next = rightNode;
                rightNode = rightNode.next;
            }
            cur = cur.next;
        }
        cur.next = leftNode==null?rightNode:leftNode;
        return dummy.next;
    }

    /**================================138*/
    public Node copyRandomList(Node head) {
        Node cur = head;
        while(cur!=null){
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }

        cur = head;
        while (cur!=null){
            if (cur.random!=null){
                cur.next.random =  cur.random.next;
            }
            cur = cur.next.next;
        }

        Node dummy = new Node(-1);
        Node curCopy = dummy;
        while (cur!=null){
            curCopy.next = cur.next;
            curCopy = curCopy.next;

            cur.next = curCopy.next;
        }
        return dummy.next;
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

    //33
    public int search(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if (target==nums[mid]) return mid;
            if (nums[mid]>nums[0]){ /**不带等于行不行？？？*/
                if (target>=nums[0]&&target<nums[mid]){
                    r = mid-1;
                }else{
                    l = mid+1;
                }
            }else{
                if (target>nums[mid]&&target<=nums[nums.length-1]){
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
        int l=0,r=nums.length-1;
        int min = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<nums[nums.length-1]){
                min = mid;
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return min;
    }

    //739
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i=0;i<temperatures.length;i++){
            while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                Integer cur = stack.pop();
                res[cur] = i-cur;
            }
            stack.push(i);
        }
        return res;
    }

    //84
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int res = 0;
        for (int i=0;i<=heights.length;i++){
            int curHeight = i==heights.length?0:heights[i];

            while (!stack.isEmpty()&&curHeight<heights[stack.peek()]){
                Integer cur = stack.pop();
                /*为什么这里要赋值为-1？
                    当前的循环变量i是右边第一个小于height[cur]的位置；
                    left变量用于标记左边第一个小于height[cur]的位置。
                  因此以height[cur]为高的矩形，长度最大是i-left-1。*/
                int left = (stack.isEmpty())?-1:stack.peek();
                int curVal = (i-left-1)*heights[cur];
                res  =Math.max(curVal,res);
            }
            stack.push(i);
        }
        return res;
    }

    /**=========================================下一个排列*/
    public void nextPermutation(int[] nums) {
        int flag = nums.length;
        for (int i = nums.length-2; i >=0 ; i--) {
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }

        for (int i=nums.length-1;i>flag;i--){
            if (nums[i]>nums[flag]){
                int tmp  =nums[i];
                nums[i] = nums[flag];
                nums[flag] = tmp;
                break;
            }
        }

        int left  =flag+1,right = nums.length-1;
        while (left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
    }

    //394. 字符串解码
    public String decodeString(String s) {
        int multi = 0;
        StringBuilder res = new StringBuilder();
        LinkedList<String> res_stack = new LinkedList<>();
        LinkedList<Integer> multi_stack = new LinkedList<>();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)>='0'&&s.charAt(i)<='9'){
                multi = multi*10 + Integer.parseInt(s.charAt(i)+"");
            }else if (s.charAt(i)=='['){
                res_stack.push(res.toString());
                multi_stack.push(multi);
                multi = 0;
                res = new StringBuilder();
            }else if (s.charAt(i)==']'){
                StringBuilder tmp = new StringBuilder();
                Integer pop = multi_stack.pop();
                for (int j=0;j<pop;j++){
                    tmp.append(res);
                }
                res = new StringBuilder(res_stack.pop()+tmp);
            }else{
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    //22
    List<String> res;
    StringBuilder path;
    public List<String> generateParenthesis(int n) {
        res = new LinkedList<>();
        path = new StringBuilder();
        generateParenthesisBack(n,0,0);
        return res;
    }

    private void generateParenthesisBack(int n, int left, int right) {
        if (path.length()==2*n){
            res.add(path.toString());
            return;
        }
        if (left<n){
            path.append('(');
            generateParenthesisBack(n,left+1,right);
            path.deleteCharAt(path.length()-1);
        }
        if (right<left){
            path.append(')');
            generateParenthesisBack(n,left,right+1);
            path.deleteCharAt(path.length()-1);
        }
    }

    //131
    List<List<String>> resPartition;
    List<String> pathPartition;
    public List<List<String>> partition(String s) {
        resPartition = new LinkedList<>();
        pathPartition = new LinkedList<>();
        partitionBack(s,0);
        return resPartition;
    }

    private void partitionBack(String s, int index) {
        if (index==s.length()){
            resPartition.add(new LinkedList<>(pathPartition));
            return;
        }
        for (int i=index;i<s.length();i++){
            if (isHuiwen(s,index,i)){
                pathPartition.add(s.substring(index,i+1));
                partitionBack(s,i+1);
                pathPartition.remove(pathPartition.size()-1);
            }
        }
    }

    private boolean isHuiwen(String s, int left, int right) {
        while(left<right){
            if (s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    //139
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] flag = new boolean[s.length() + 1];
        flag[0] = true;
        HashSet<String> set = new HashSet<>(wordDict);
        for (int i=1;i<=s.length();i++){
            for (int j=0;j<i;j++){
                if (flag[j]&&set.contains(s.substring(j,i))){
                    flag[i] = true;
                    break;
                }
            }
        }
        return flag[s.length()];
    }

    /**================================416*/
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num:nums){
            sum += num;
        }
        if (sum%2!=0){
            return false;
        }
        sum /=2;

        int[] dp = new int[sum + 1];
        for (int i=0;i<nums.length;i++)
            for (int j=sum;j>=nums[i];j--){
                dp[j]  =Math.max(dp[j-nums[i]]+nums[i],dp[j]);
            }
        return dp[sum]==sum;
    }


        public int jump(int[] nums) {
            int step = 0;
            int bound = 0;
            int maxPosition = 0;
            for (int i=0;i<nums.length-1;i++){
                maxPosition = Math.max(maxPosition,i+nums[i]);
                if (i==bound){
                    step++;
                    bound = maxPosition;
                }
            }
            return step;
    }

    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];

        for (int i=0;i<coins.length;i++){
            Arrays.fill(dp[i],-1);
        }
        for (int i=1;i<=amount;i++){
            if (amount%coins[i]==0) dp[0][amount] = amount/coins[0];
        }

        for (int i=1;i<coins.length;i++)
            for (int j=1;j<=amount;j++){
                if (j>=coins[i] && dp[i][j-coins[i]]!=-1){
                    dp[i][j]  = Math.min(dp[i][j-coins[i]]+1,dp[i-1][j]);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        return dp[coins.length-1][amount];
    }


}
