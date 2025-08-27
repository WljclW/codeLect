package leecode_Debug.topcode;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 *6——————468，297，207，224，460，手撕归并，47，40，数组中的逆序对
 *7——————123、958、7、572、50、59、91、440、
 *8——————329、450、10、208、295、213、
 *9——————106、384、120、516、44、887、9、679、210、400、611、
 *10—————264、395、97、253、63写出一维的解法、673、、、、
 */
public class topcode6_10 {
    //739
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                Integer cur = stack.pop();
                res[cur] = i-cur;
            }
            stack.push(i);
        }

        return res;
    }

    //138
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
        if (head==null) return head;
        Node cur = head;
        while (cur!=null){
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = cur.next.next;
        }

        cur = head;
        while (cur!=null){
            if (cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        Node res = head.next,curCopy = res;
        cur = head;
        while (curCopy.next!=null){
            cur.next = cur.next.next;
            cur = cur.next;

            curCopy.next = curCopy.next.next;
            curCopy = curCopy.next;
        }
        cur.next = null;
        return res;
    }


    //153
    public int findMin(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (nums[mid]<nums[r]){
                r = mid;
            }else {
                l = mid+1;
            }
        }
        return l;
    }

    //79
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]==word.charAt(0)){
                    if (dfs(word,board,i,j,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(String word, char[][] board, int i, int j, int index) {
        if (index==word.length()) return true;
        if (i<0||j<0||i>=board.length||j>=board[0].length||word.charAt(index)!=board[i][j]) return false;
        board[i][j] = '\n';
        boolean res = dfs(word, board, i + 1, j, index + 1) ||
                dfs(word, board, i - 1, j, index + 1) ||
                dfs(word, board, i, j + 1, index + 1) ||
                dfs(word, board, i, j - 1, index + 1);
        board[i][j]  =word.charAt(index);
        return res;
    }

    //402
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            while (!stack.isEmpty()&&num.charAt(i)<stack.peek()&&k>0){
                k--;
                stack.pop();
            }
            stack.push(num.charAt(i));
        }

        while (k>0&&!stack.isEmpty()){
            k--;
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (char c:stack){
            if (sb.length()==0&&c=='0') continue;
            sb.append(c);
        }
        return sb.length()==0?"0":sb.toString();
    }

    //11
    public int maxArea(int[] height) {
        int l = 0,r = height.length-1;
        int res = 0;
        while (l<r){
            int curVal = 0;
            if (height[l]<height[r]){
                curVal = height[l]*(r-l);
                l++;
            }else {
                curVal = height[r]*(r-l);
                r--;
            }
            res = Math.max(res,curVal);
        }
        return res;
    }

    //136
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num:nums){
            res ^= num;
        }
        return res;
    }


    //16
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = -1,cha = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length-2; i++) {
            int l =i+1,r =nums.length-1;
            while (l<r){
                int curSum = nums[i]+nums[l]+nums[r];
                int curCha = Math.abs(curSum-target);
                if (curCha==0) return target;
                if (curCha<cha){
                    cha = curCha;
                    res = curSum;
                }
                if (curSum<target) l++;
                else r--;
            }
        }
        return res;
    }

    //61
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null||k==0) return head;
        int size = 1;
        ListNode cur = head;
        while (cur.next!=null){
            cur = cur.next;
            size++;
        }
        ListNode tail = cur;
        tail.next = head;

        cur = head;
        k %= size;
        for (int i = 0; i < size-k-1; i++) {
            cur = cur.next;
        }
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }

    //47
    List<List<Integer>> resPermuteUnique;
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        resPermuteUnique = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUniqueBack(nums,path);
        return resPermuteUnique;
    }

    private void permuteUniqueBack(int[] nums, LinkedList<Integer> path) {
        if (path.size()==nums.length){
            resPermuteUnique.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                if (i>0&&nums[i]==nums[i-1]&&!used[i-1]) continue;
                used[i] = true;
                path.add(nums[i]);
                permuteUniqueBack(nums,path);
                used[i] = false;
                path.removeLast();
            }
        }
    }


    //40
    List<List<Integer>> resCombinationSum2;
    boolean[] usedCombinationSum2;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        resCombinationSum2 = new LinkedList<>();
        usedCombinationSum2 = new boolean[candidates.length];
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        combinationSum2Back(candidates,target,path,0);
        return resCombinationSum2;
    }

    private void combinationSum2Back(int[] candidates, int target, LinkedList<Integer> path, int index) {
        if (target==0) resCombinationSum2.add(new LinkedList<>(path));
        if (target<0) return;
        for (int i = index; i < candidates.length; i++) {
            if (i>0&&candidates[i-1]==candidates[i]&&!usedCombinationSum2[i]) continue;
            target -= candidates[i];
            usedCombinationSum2[i]=true;
            path.add(candidates[i]);
            combinationSum2Back(candidates,target,path,i+1);
            target += candidates[i];
            usedCombinationSum2[i] = false;
            path.removeLast();
        }
    }

    //55
    public boolean canJump(int[] nums) {
        int bound = 0;
        for (int i = 0; i < nums.length&&i<=bound; i++) {
            bound = Math.max(i+nums[i],bound);
            if (bound>=nums.length-1){
                return true;
            }
        }
        return false;
    }

    //74
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int l =0,r = m*n-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur==target) return true;
            else if (cur>target) r = mid-1;
            else l=mid+1;
        }
        return false;
    }

    //26
    public int removeDuplicates(int[] nums) {
        if (nums.length==0||nums.length==1) return nums.length;
        int left=0,right =1;
        while (right<nums.length){
            if (nums[right]==nums[left]){
                right++;
            }else {
                nums[++left] = nums[right++];
            }
        }
        return left+1;
    }

    //114
    /**递归的写法呢？？怎么解？？？*/
    public void flatten(TreeNode root) {
        if (root==null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = null;
        while (!stack.isEmpty()){
            TreeNode curNode = stack.pop();
            if (cur==null) cur = curNode;
            else {
                cur.right = curNode;
                cur.left = null;
                cur = curNode.right;
            }
            if (cur.right!=null){
                stack.push(cur.right);
            }
            if (cur.left!=null){
                stack.push(cur.left);
            }
        }
    }


    //59
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];


        return res;
    }


    //145
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.left!=null){
                stack.push(cur.left);
            }
            if (cur.right!=null){
                stack.push(cur.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    public List<Integer> postorderTraversal_digui(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        dfsPostorder(root,res);
        return res;
    }

    private void dfsPostorder(TreeNode root, LinkedList<Integer> res) {
        if (root==null) return;
        dfsPostorder(root.left,res);
        dfsPostorder(root.right,res);
        res.add(root.val);
    }

    //75
    public void sortColors(int[] nums) {
        int left = 0,cur = 0,right = nums.length-1;
        while (cur<=right){
            if (nums[cur]==1){
                cur++;
            }else if (nums[cur]<1){
                swap(nums,left++,cur++);
            }else {
                swap(nums,cur,right--);
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    //287
    public int findDuplicate(int[] nums) {
        int slow = 0,fast =0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow!=fast);

        slow = 0;
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    //106
    int postorderIndex;
    HashMap<Integer,Integer> inorderMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length-1;
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(inorder,postorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int l, int r) {
        if (l>r) return null;
        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.left = buildTree(inorder,postorder,l,index-1);
        root.right = buildTree(inorder,postorder,index+1,r);
        return root;
    }


    //96
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <=n; i++) {
            for (int j = 1; j <=i; j++) {
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }

    //189
    public void rotate(int[] nums, int k) {
        k%=nums.length;
        if (k==0) return;
        reve(nums,0,nums.length-1);
        reve(nums,0,k-1);
        reve(nums,k,nums.length-1);
    }

    private void reve(int[] nums, int l, int r) {
        while (l<r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }

    //678
//    public boolean checkValidString(String s) {
//
//    }


    //400
    public int findNthDigit(int n) {
        int digit = 1,min = 1;
        while (n>digit*min*9){
            n-=digit*min*9;
            digit++;
            min *= 10;
        }
        int num = min+(n-digit)/digit;
        return String.valueOf(num).charAt((n-1)%digit)-'0';
    }



    //134
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curTotal  =0,total = 0;
        int res = 0;
        for (int i = 0; i < gas.length; i++) {
            curTotal += (gas[i]-cost[i]);
            total += (gas[i]-cost[i]);
            if (curTotal<0){
                res = i+1;
                curTotal = 0;
            }
        }
        return total<0?-1:res;
    }


    //611
//    public int triangleNumber(int[] nums) {
//        Arrays.sort(nums);
//        int res = 0;
//        for (int i = 0; i < nums.length - 2; i++) {
//            int l = i+1,r = i+2;
//            while (l<r){
//                if (nums[i]+nums[l]>nums[r]){
//                    res += (r-l);
//                }
//            }
//        }
//    }

    //LCR 174
    public int findTargetNode(TreeNode root, int cnt) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.right;
            }else {
                TreeNode cur = stack.pop();
                if (--cnt==0) return cur.val;
                root = cur.left;
            }
        }
        return -1;
    }

    //135
    public int candy(int[] ratings) {
        int[] res = new int[ratings.length];
        Arrays.fill(res,1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i]>ratings[i-1]) res[i] = res[i-1]+1;
        }

        for (int i = ratings.length-2; i >=0 ; i--) {
            if (ratings[i]>ratings[i+1])
                res[i] = Math.max(res[i],res[i+1]+1);
        }
        return Arrays.stream(res).sum();
    }

    //442
    public List<Integer> findDuplicates(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();
        for (int num:nums){
            int cur = Math.abs(num);
            if (nums[cur-1]>0) nums[cur-1]*=-1;
            else res.add(cur);
        }
        return res;
    }

    //LCR 161
    public int maxSales(int[] sales) {
        int res = sales[0];
        int preSum =sales[0];
        for (int i = 1; i < sales.length; i++) {
            preSum = Math.max(preSum+sales[i],sales[i]);
            res = Math.max(preSum,res);
        }
        return res;
    }

    //LCR 127
    public int trainWays(int num) {
        int fir = 1,sec =1;
        for (int i = 2; i <=num ; i++) {
            int cur = (fir+sec)%1000000007;
            fir = sec;
            sec = cur;
        }
        return sec;
    }

    //445
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null||l2==null) return l1==null?l2:l1;
        ListNode rL1 = rever(l1);
        ListNode rL2 = rever(l2);
        ListNode res = addTwo(rL1,rL2);
//        l1 = rever(rL1); /*不用复原原始链表，没问题的~*/
//        l2 = rever(rL2);
        return rever(res);
    }

    private ListNode addTwo(ListNode rL1, ListNode rL2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (rL1!=null||rL2!=null||carry!=0){
            int val1 = rL1==null?0:rL1.val;
            int val2 = rL2==null?0:rL2.val;
            int curSum = val1+val2+carry;
            ListNode newNode = new ListNode(curSum % 10);
            cur.next = newNode;
            cur = cur.next;
            carry = curSum/10;
            /**err：不能少了下面的两句，否则会爆出内存超出限制*/
            rL1 = rL1==null?rL1:rL1.next;
            rL2 = rL2==null?rL2:rL2.next;
        }
        return dummy.next;
    }

    private ListNode rever(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    //45
    public int jump(int[] nums) {
        int step = 0,maxPosition =0,bound =0;
        for (int i = 0; i < nums.length - 1; i++) {
            bound = Math.max(bound,i+nums[i]);
            if (i==maxPosition){
                maxPosition = bound;
                step++;
            }
        }
        return step;
    }

    //LCR 121
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        if (plants==null||plants.length==0) return false;
        int m = plants.length,n = plants[0].length;
        int i=0,j=n-1;
        while (i<m&&j>=0){
            if (plants[i][j]==target){
                return true;
            }else if (plants[i][j]>target){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    //328
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return head;
        ListNode oddHead = head;
        ListNode evenHead = head.next,evenCur = evenHead;
        while (evenCur!=null&&evenCur.next!=null){
            oddHead.next = oddHead.next.next;
            oddHead = oddHead.next;

            evenCur.next = evenCur.next.next;
            evenCur = evenCur.next;
        }
        oddHead.next = evenHead;

        return head;
    }

    //230
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode cur = stack.pop();
                root = cur.right;
                if (--k==0){
                    return cur.val;
                }
            }
        }
        return -1;
    }

    //125
    public boolean isPalindrome(String s) {
        int l=0,r=s.length()-1;
        while (l<r){
            char c1 = s.charAt(l);
            char c2 = s.charAt(r);
            if (!Character.isLetterOrDigit(c1)){
                l++;
            }else if (!Character.isLetterOrDigit(c2)){
                r--;
            }else if (Character.toLowerCase(c1)!=Character.toLowerCase(c2)){
                return false;
            }else {
                l++;
                r--;
            }
        }
        return true;
    }


    //85
    public int maximalRectangle(char[][] matrix) {
        int m =matrix.length,n =matrix[0].length;
        int res  =0;
        int[] height = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]!='0'){
                    height[j]++;
                }else {
                    height[j] = 0;
                }
            }
            res = Math.max(res,getArea(height));
        }
        return res;
    }

    private int getArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length + 1; i++) {
            int curHeight = (i==height.length)?0:height[i];
            while (!stack.isEmpty()&&curHeight<height[stack.peek()]){
                Integer curIndex = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                int curArea = height[curIndex]*(i-left-1);
                res = Math.max(res,curArea);
            }
            stack.push(i);
        }
        return res;
    }

    //LCR 144
    public TreeNode flipTree(TreeNode root) {
        if (root==null) return root;
        TreeNode left = flipTree(root.left);
        TreeNode right = flipTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


    public TreeNode flipTree_cengxu(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> deque = new LinkedList<>();
        TreeNode res = root;
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                swap(cur);
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
            }
        }
        return res;
    }

    private void swap(TreeNode cur) {
        TreeNode tmp = cur.left;
        cur.left = cur.right;
        cur.right = tmp;
    }

    //264
//    public int nthUglyNumber(int n) {
//        int p1=1,p2=1,p3=1;
//        int res=1;
//        for (int i = 1; i < n; i++) {
//            int val1 = p1*2;
//            int val2 = p2*3;
//            int val3 = p3*5;
//            res = Math.min(Math.min(val1,val2),val3);
//            if (res==val1)p1++;
//            if (res==val2)p2++;
//            if (res==val3)p3++;
//        }
//        return res;
//    }

    //1004
    public int longestOnes(int[] nums, int k) {
        int left = 0,cur =0;
        int zeroCount = 0;
        int res =0;
        while (cur<nums.length){
            if (nums[cur++]==0) zeroCount++;
            while (zeroCount>k){
                if (nums[left++]==0){
                    zeroCount--;
                }
            }
            res = Math.max(res,cur-left);
        }
        return res;
    }

    //63
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        if (obstacleGrid[0][0]==1) return 0;
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[0][i] = obstacleGrid[0][i]==1?0:dp[0][i-1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0]==1?0:dp[i-1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j]==0){
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }else {
                    dp[i][j] =0;
                }
            }
        }
        return dp[m-1][n-1];
    }

    //316
    public String removeDuplicateLetters(String s) {
        int[] flags = new int[26];
        boolean[] used = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            flags[s.charAt(i)-'a'] = i;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!used[c-'a']){
                while (!stack.isEmpty()&&flags[s.charAt(stack.peek())-'a']>i&&c<s.charAt(stack.peek())){
                    Integer index = stack.pop();
                    used[s.charAt(index)-'a'] = false;
                }
                used[c-'a'] = true;
                stack.push(i);
            }
        }

        StringBuilder res = new StringBuilder();
        for (int index:stack){
            res.append(s.charAt(index));
        }
        return res.toString();
    }

    //链表求和
    public ListNode addTwoNumbers_(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curSum = val1+val2+carry;
            cur.next = new ListNode(curSum%10);
            cur = cur.next;
            carry = curSum/10;
            if (l1!=null) l1=l1.next;
            if (l2!=null) l2=l2.next;
        }
        return dummy.next;
    }

    //71
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            if ("".equals(split[i])||".".equals(split[i]))
                continue;
            else if ("..".equals(split[i])){
                if (!stack.isEmpty()) stack.pop();
            }
            else
                stack.push(split[i]);
        }

        if (stack.isEmpty()) return "/";
        StringBuilder res = new StringBuilder();
        for(String str:stack){
            res.append("/").append(str);
        }
        return res.toString();
    }

    /**下面的代码是错误的，为什么？？*/
    public String simplifyPath_error(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            if ("".equals(split[i])||".".equals(split[i]))
                continue;
            else if ("..".equals(split[i]))
                if (!stack.isEmpty()) stack.pop(); /**这里的if块必须加“{}”包起来，否则答案是错的*/
            else
                stack.push(split[i]);
        }

        if (stack.isEmpty()) return "/";
        StringBuilder res = new StringBuilder();
        for(String str:stack){
            res.append("/").append(str);
        }
        return res.toString();
    }

    //416
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum&1)==1) return false;
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum+1; j >=nums[i] ; j--) {
                dp[j] = Math.max(dp[i],dp[i-nums[i]]+nums[i]);
            }
        }
        return dp[sum]==sum;
    }
}
