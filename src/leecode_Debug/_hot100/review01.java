package leecode_Debug._hot100;

import leecode_Debug.linkList.ListNode;
import leecode_Debug.linkList.reverse;

import java.util.*;


/**
 * 41. 缺失的第一个正数
 * 153. 寻找旋转排序数组中的最小值
 * 4. 寻找两个正序数组的中位数
 * 31. 下一个排列
 * 45. 跳跃游戏 II
 * 84. 柱状图中最大的矩形
 * 42. 接雨水
 * 438. 找到字符串中所有字母异位词
 *
 *
 * @author: Zhou
 * @date: 2025/6/15 17:08
 */
public class review01 {
    /**==========================普通数组=========================*/
    public int maxSubArray(int[] nums) {
        if (nums.length==1) return nums[0];
        int pre = nums[0];
        int res = nums[0];
        for (int i=1;i<nums.length;i++){
            pre = Math.max(pre+nums[i],nums[i]);
            res = Math.max(pre,res);
        }
        return res;
    }

    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> list = new LinkedList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        list.add(intervals[0]);
        for (int i=1;i<intervals.length;i++){
            int[] cur = intervals[i];
            if (cur[0]>list.getLast()[1]){
                list.add(intervals[i]);
            }else{
                list.getLast()[1] = Math.max(cur[1],list.getLast()[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k, nums.length-1);
        return;
    }

    private void reverse(int[] nums, int left, int right) {
        while(left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }


    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i=1;i<nums.length;i++){
            res[i] = res[i-1]*nums[i-1];
        }
        int post = 1;
        for (int i=nums.length-1;i>=0;i--){
            res[i] = res[i]*post;
            post *= nums[i];
        }
        return res;
    }


    public int firstMissingPositive(int[] nums) {
        int slow = 0,fast = 0;
        while(fast<nums.length){
            if (nums[fast]>0&&nums[nums[fast]-1]!=nums[fast]){
                int tmp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = tmp;
                slow++;
                continue;
            }else {
                fast++;
            }
        }
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=i+1)
                return i+1;
        }
        return 0;
    }




    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA,p2 = headB;
        while(p1!=p2){
            p1 = (p1==null)?headB:p1.next;
            p2 = (p2==null)?headA:p2.next;
        }
        return p1;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null,cur=head,next = head;
        while(cur!=null){
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public boolean hasCycle(ListNode head) {
        ListNode slow=head,fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast){
                return true;
            }
        }
        return false;
    }


    public ListNode detectCycle(ListNode head) {
        ListNode slow=head,fast=head;
        while (fast!=null&&fast.next!=null){
            slow =slow.next;
            fast = fast.next.next;
            if (slow==fast){
                fast = head;
                while (slow!=fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode cur =dummy;
        while (list1!=null&&list2!=null){
            if (list1.val>list2.val){
                cur.next = list2;
                list2 = list2.next;
            }else{
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next;
        }
        cur.next = (list1==null)?list2:list1;
        return dummy.next;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = (l1==null)?0:l1.val;
            int val2 = (l2==null)?0:l2.val;
            l1 = (l1==null)?l1:l1.next;
            l2 = (l2==null)?l2:l2.next;
            int sum = val1+val2+carry;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            carry = sum/10;
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy,fast = head;
        for (int i=0;i<n;i++){
            fast =fast.next;
        }
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur =dummy;
        while(cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2= cur.next.next;
            node1.next = node2.next;
            node2.next = node1;
            cur.next = node2;
            cur = node1;
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
    public Node copyRandomList(Node head) {
        Node cur = head;
        while (cur!=null){
            Node curCopy = new Node(cur.val);
            curCopy.next = cur.next;
            cur.next = curCopy;
            cur = cur.next.next;
        }

        cur = head;
        while (cur!=null){
            if (cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

//        Node res = null,ress = res;
//        for (cur =head,res =head.next;cur!=null&&res!=null;cur=cur.next.next,res =res.next.next){
//            cur.next = res.next;
//            res.next = cur.next.next;
//        }
//        return ress;
        if (head==null) return null;

        Node res = head.next,ress = res;
        while(ress.next!=null){
            head.next = ress.next;
            ress.next = head.next.next;
            head = head.next;
            ress = ress.next;
        }
        head.next = null;
        ress.next=null;
        return res;
    }


    public int searchInsert(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

//    public boolean searchMatrix(int[][] matrix, int target) {
//        int m = matrix.length,n=matrix[0].length;
//        int left = 0,right = m*n-1;
//        while(left<=right){
//            int mid=left+(right-left)/2;
//            if (matrix[mid/n][mid%n]==target){
//                return true;
//            }else if (matrix[mid/n][mid%n]>target){
//                right = mid-1;
//            }else{
//                left = mid+1;
//            }
//        }
//        return false;
//    }


    public int[] searchRange(int[] nums, int target) {
        int left = searchRangeLeft(nums,target);
        if (left==nums.length||nums[left]!=target){
            return new int[]{-1,-1};
        }
        int right = searchRangeLeft(nums,target+1);
        return new int[]{left,right-1};
    }

    private int searchRangeLeft(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>=target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]==target){
                return mid;
            }
            if (nums[mid]>=nums[0]){
                if (target>=nums[0]&&target<nums[mid]){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }else{
                if (target>nums[mid]&&target<=nums[nums.length-1]){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    public int findMin(int[] nums) {
        int left = 0,right= nums.length-1;
        int min = 0;
        while(left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>nums[0]){
                left = mid+1;
            }else{
                min = mid;
                right = mid-1;
            }
        }
        return nums[min];
    }


    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num:nums){
            res ^= num;
        }
        return res;
    }

    public int majorityElement(int[] nums) {
        int total = 0,flag = 0;
        for (int num:nums){
            if (total==0){
                flag = num;
            }
            total = (num==flag)?total+1:total-1;
        }
        return flag;
    }

    public void sortColors(int[] nums) {
        int left = 0,mid = 0,right = nums.length-1;
        while(mid<=right){
            if (nums[mid]==1){
                mid++;
            }else if (nums[mid]==0){
                swap(nums,mid,left);
                left++;
                mid++;
            }else{
                swap(nums,mid,right);
                right--;
            }
        }
    }

    private void swap(int[] nums, int mid, int left) {
        int tmp = nums[mid];
        nums[mid] = nums[left];
        nums[left] = tmp;
    }



    public void nextPermutation(int[] nums) {
        int flag = nums.length;
        for (int i=nums.length-2;i>=0;i--){
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }

        if (flag==nums.length){
            reverse1(nums,0,nums.length-1);
        }

        for (int i=nums.length-1;i>=flag;i--){
            if (nums[i]>nums[flag]){
                int tmp = nums[flag];
                nums[flag] = nums[i];
                nums[i] = tmp;
                reverse1(nums,flag+1,nums.length-1);
                break;
            }

        }
    }

    private void reverse1(int[] nums, int l, int r) {
        while(l<r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }

    public int maxProfit(int[] prices) {
        int min = prices[0],res = 0;
        for (int i=1;i<prices.length;i++){
            min = Math.min(prices[i],min);
            res = Math.max(prices[i]-min,res);
        }
        return res;
    }

    public boolean canJump(int[] nums) {
        if (nums.length==1) return true;
        int bound = nums[0];
        int cur=0;
        while(cur<=bound){
            bound = Math.max(bound,cur+nums[cur]);
            cur++;
            if (bound>=nums.length){
                return true;
            }
        }
        return false;
    }


    public List<Integer> partitionLabels(String s) {
        LinkedList<Integer> res = new LinkedList<>();
        int[] flags = new int[26];
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            flags[c-'a'] = i;
        }

        int start =0,end = 0;
        int cur = 0;
        while(cur<s.length()){
            char c = s.charAt(cur);
            end = Math.max(end,flags[c-'a']);
            if (cur==end){
                res.add(end-start+1);
                start = end+1;
            }
            cur++;
        }
        return res;
    }

    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>() {{
            put(')','(');
            put(']','[');
            put('}','{');
        }};
        LinkedList<Character> stack = new LinkedList<>();
        if (s.length()%2!=0) return false;
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (!map.containsKey(c)){
                stack.push(c);
                continue;
            }
            if (!stack.isEmpty()&&stack.pop()==c){
                continue;
            }else{
                return false;
            }
        }
        return stack.isEmpty();
    }

//    public int[] dailyTemperatures(int[] temperatures) {
//        int[] res = new int[temperatures.length];
//        LinkedList<Integer> stack = new LinkedList<>();
////        for (int i=0;i<temperatures.length;i++){
////            if (stack.isEmpty()||temperatures[stack.peek()]>=temperatures[i]){
////                stack.push(i);
////            }else{
////                while(!stack.isEmpty()&&temperatures[stack.peek()]<temperatures[i]){
////                    Integer cur = stack.pop();
////                    res[cur] = i-cur;
////                }
////            }
////        }
////        return res;
//    }


    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i=0;i<temperatures.length;i++){
            if (stack.isEmpty()||temperatures[stack.peek()]>=temperatures[i]){
                stack.push(i);
                continue;
            }
            while(!stack.isEmpty()&&temperatures[stack.peek()]<temperatures[i]){
                Integer cur = stack.pop();
                res[cur] = i-cur;
            }
            stack.push(i);
        }

        return res;
    }



    public int largestRectangleArea(int[] heights) {
        int res = Integer.MIN_VALUE;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i=0;i<heights.length;i++){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                Integer cur = stack.pop();
                while(!stack.isEmpty()&&heights[stack.peek()]==heights[cur]){
                    cur = stack.pop();
                }
                if (!stack.isEmpty()&&heights[stack.peek()]<heights[cur]){
                    int curValue = heights[cur]*(i-cur);
                    res = Math.max(res,curValue);
                }
            }
            stack.push(i);
        }
        return res;
    }




    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (i==0||j==0){
                    dp[i][j]  =1;
                }else{
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }


    public int minPathSum(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i=1;i<m;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i=1;i<n;i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        for (int i=1;i<m;i++)
            for (int j=1;j<n;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        return dp[m-1][n-1];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length(),length2 = text2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i=1;i<=length1;i++)
            for (int j=1;j<=length2;j++){
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        return dp[text1.length()][text2.length()];
    }


    public int minDistance(String word1, String word2) {
        int l1 = word1.length(),l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i=0;i<=l1;i++)
            for (int j=0;j<=l2;j++){
                if (i==0){
                    dp[i][j] = j;
                    continue;
                }
                if (j==0) {
                    dp[i][j] = i;
                    continue;
                }
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        return dp[l1][l2];
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                return new int[]{i,map.get(nums[i])};
            }
            map.put(target-nums[i],i);
        }
        return new int[]{-1,-1};
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if (map.containsKey(s)){
                map.get(s).add(str);
            }else{
                LinkedList<String> ele = new LinkedList<>();
                ele.add(str);
                map.put(s,ele);
            }
        }
        return new LinkedList<>(map.values());
    }

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num:nums){
            set.add(num);
        }

        int res = 0;
        for (int num:nums){
            if (!set.contains(num-1)){
                int i=0;
                while(set.contains(num+i)) i++;
                res = Math.max(res,i);
            }
        }
        return res;
    }

    public void moveZeroes(int[] nums) {
        int slow = 0,fast = 0;
        while(fast<nums.length){
            if (nums[fast]!=0){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        for (int i=slow;i<nums.length;i++){
            nums[i] = 0;
        }
    }

    public int maxArea(int[] height) {
        int left = 0,right = height.length-1;
        int res= Integer.MIN_VALUE;
        while(left<right){
            int cur = (right-left)*Math.min(height[left],height[right]);
            res = Math.max(res,cur);
            if (height[left]<height[right]) left++;
            else right--;
        }
        return res;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            if (i>0&&nums[i]==nums[i-1]) continue;
            int left = i+1,right = nums.length-1;
            while(left<right){
                int cur = nums[i] + nums[left] + nums[right];
                if (cur>0){
                    right--;
                }else if (cur<0){
                    left++;
                }else{
                    LinkedList<Integer> ele = new LinkedList<>();
                    ele.add(nums[i]);
                    ele.add(nums[left]);
                    ele.add(nums[right]);
                    res.add(ele);
                    left++;
                    right--;
                    while(left<right&&nums[left]==nums[left-1]) left++;
                    while(left<right&&nums[right]==nums[right+1]) right--;
                }
            }
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0,left = 0;
        int res = 0;
        while (i<s.length()){
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
            while(map.get(c)>1){
                char leftC = s.charAt(left);
                map.put(leftC,map.get(leftC)-1);
                left++;
            }
            i++;
            res = Math.max(res,i-left);
        }
        return res;
    }




    public static void main(String[] args) {
        review01 review01 = new review01();
//        review01.rotate(new int[]{1,2,3,4,5,6,7},3);
//        review01.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
//        review01.findMin(new int[]{11,13,15,17});
//        review01.nextPermutation(new int[]{2,2,0,4,3,1});
        review01.threeSum(new int[]{-1,0,1,2,-1,-4});
    }

}
