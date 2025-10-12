package leecode_Debug.topcode;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;


/**
 * 1~2————215、53、手撕快排、5、92、1143、93、
 * 3——————151、78、322、8
 * 4——————39、470、122、
 * 5——————112、113、179、718、手撕堆排序、14
 */
public class topcode1_5 {
    //3
    public int lengthOfLongestSubstring(String s) {
        int left = 0,right = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (right<s.length()){
            char c = s.charAt(right++);
            map.put(c,map.getOrDefault(c,0)+1);
            while (map.get(c)>1){
                char c1 = s.charAt(left);
                map.put(c1,map.get(c1)-1);
                left++;
            }
            res  =Math.max(res,right-left);
        }
        return res;
    }

    class LRUCache {
        int size;
        int capacity;
        HashMap<Integer,DouNode> map = new HashMap<Integer,DouNode>();
        DouNode tail;
        DouNode head;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new DouNode();
            tail = new DouNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DouNode node = map.get(key);
            if (node==null) return -1;
            else {
                moveToHead(node);
                return node.value;
            }
        }

        private void moveToHead(DouNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DouNode node) {
            DouNode next = head.next;
            head.next = node;
            node.next = next;
            next.prev = node;
            node.prev  =head;
        }

        private void removeNode(DouNode node) {
            DouNode preNode = node.prev;
            DouNode nextNode = node.next;
            preNode.next = nextNode;
            nextNode.prev = preNode;
        }

        public void put(int key, int value) {
            DouNode node = map.get(key);
            if (node!=null){
                moveToHead(node);
                node.value = value;
            }else {
                DouNode newNode = new DouNode(key, value);
                map.put(key,newNode);
                addToHead(newNode);
                size++;
                if (size>capacity){
                    DouNode relTail = tail.prev;
                    map.remove(relTail.key);
                    removeNode(relTail);
                    size--;
                }
            }
        }

        class DouNode{
            int key;
            int value;
            DouNode prev;
            DouNode next;
            public DouNode(){

            }
            public DouNode(int key,int value){
                this.key = key;
                this.value =value;
            }
        }
    }


    //206
    public ListNode reverseList(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //TODO：215
    /*写法1：“43 / 44 个通过的测试用例”报错，超出时间限制*/
    Random random = new Random();
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums,0,n-1,n-k);
    }

    private int quickSort(int[] nums, int l, int r, int targetIndex) {
        if (l==r) return nums[l];
        int pivotIndex = l + random.nextInt(r - l + 1);
        pivotIndex = partition(nums,l,r,pivotIndex);

        if (pivotIndex==targetIndex){
            return nums[targetIndex];
        }else if (pivotIndex<targetIndex){
            return quickSort(nums,pivotIndex+1,r,targetIndex);
        }else {
            return quickSort(nums,l,pivotIndex-1,targetIndex);
        }
    }

    private int partition(int[] nums, int l, int r, int pivotIndex) {
        int pivot = nums[pivotIndex];
        swap(nums,pivotIndex,r);

        int storeIndex = l;
        for (int i = l; i < r; i++) {
            if (nums[i]<nums[r]){
                swap(nums,storeIndex,i);
                storeIndex++;
            }
        }

        swap(nums,storeIndex,r);
        return storeIndex;
    }

    private void swap(int[] nums, int l, int r) {
        int tmp  = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    //92
    /*官方解：穿针引线法*/
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) { /**注意是从0到left-1，因为要来到左边界的前一个结点！！（有点类似于旋转链表遍历获取节点数的做法）*/
            pre = pre.next;
        }

        ListNode cur = pre.next;
        /**
         * for循环的4步，顺序不能错。。思考思考~~
         */
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next; /**err：这里"pre.next"不能用cur来代替，为什么？？？*/
            pre.next = next;
        }
        return dummy.next;
    }


    /*解法2：常规的数节点，然后反转*/

    /**
     *【关键问题————捋清楚找哪一个节点；以及对应的i应该取的范围】
     *      捋清楚链表数节点的问题————
     *    1.在i从0开始情况下，i<m。指针会从开始的地方走m步！！
     *    2. 题目中left是第left个节点，因此要从head之前的节点开始走，走left步，指针会指向left节点！但
     * 是由于我们实际需要来到left节点的前一个节点（需要做一些操作，比如：记录left前面的节点 以及 从left
     * 节点开始翻转链表），因此这里“i<left-1”。
     *    3. 题目中right指的也是第right个节点，因此从head前面的节点开始，走right步后指针会指向第right
     * 个节点！由于我们需要特殊处理第right个节点 以及 记录第right+1个节点，所以这里“i<right”。
     */
    public ListNode reverseBetween_2(ListNode head, int left, int right) {
        /**
         【关键建议】
         链表中数节点的时候，建议使用下面的方法——————
         先创建虚拟头节点dummy，cur=dummy，然后计数从0开始数。”for (int i = 0; i < m; i++)“出
         了for循环cur就来到了链表的第m个节点
         */
        ListNode dummy = new ListNode(-1, head),slow = dummy,fast = dummy;
        for (int i = 0; i < left-1; i++) { /**err：slow要来到left的前一个节点，因此满足“i<left-1”*/
            slow = slow.next;
        }
        for (int i = 0; i < right; i++) { /**err：fast要来到right节点，因此“i<right”*/
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        ListNode next = fast.next;
        fast.next = null;
        slow.next = reverse1(newHead);
        newHead.next = next;
        return dummy.next;
    }

    //常规的翻转链表代码
    private ListNode reverse1(ListNode newHead) {
        ListNode pre = null,cur = newHead;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next =pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    //25
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy,end = dummy;
        while (pre.next!=null){
            for (int i = 0; i < k&&end!=null; i++) {
                end = end.next;
            }
            if (end==null) break;

            ListNode nextStart = end.next;
            ListNode curStart = pre.next;
            end.next = null;
            pre.next = rever(curStart);
            curStart.next = nextStart;

            pre = curStart;
            end = curStart;
        }
        return dummy.next;
    }

    private ListNode rever(ListNode curStart) {
        ListNode pre = null,cur = curStart;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    //15
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i>0&&nums[i]==nums[i-1]) continue;
            int l = i+1,r = nums.length-1;
            while (l<r){
                int curSum = nums[i]+nums[l]+nums[r];
                if (curSum<0){
                    l++;
                }else if (curSum>0){
                    r--;
                }else {
                    LinkedList<Integer> ele = new LinkedList<>(Arrays.asList(nums[i], nums[l], nums[r]));
                    res.add(ele);
                    while (l<r&&nums[l]==nums[++l]);
                    while (l<r&&nums[r]==nums[--r]);
                }
            }
        }
        return res;
    }

    //53
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur = Math.max(pre+nums[i],nums[i]);
            pre = cur;
            res  = Math.max(res,cur);
        }
        return res;
    }


    //21
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (list1!=null&&list2!=null){
            if (list1.val< list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = (list1==null)?list2:list1;
        return dummy.next;
    }

    //102
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            LinkedList<Integer> ele = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = deque.poll();
                ele.add(curNode.val);
                if (curNode.left!=null){
                    deque.offer(curNode.left);
                }
                if (curNode.right!=null){
                    deque.offer(curNode.right);
                }
            }
            res.add(ele);
        }
        return res;
    }

    //33
    public int search(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>=nums[l]){
                if (target>=nums[l]&&target<nums[mid]){
                    r = mid-1;
                }else {
                    l = mid+1;
                }
            }else {
                if (target>nums[mid]&&target<=nums[r]){
                    l = mid+1;
                }else {
                    r =mid-1;
                }
            }
        }
        return -1;
    }

    //200
    public int numIslands(char[][] grid) {
        int res =0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    res++;
                    dfs(grid,i,j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]!='1') return;
        grid[i][j] = '\n';
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }

    //46
    List<List<Integer>> resPermute;
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        resPermute = new LinkedList<>();
        used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        premuteBack(nums,path);
        return resPermute;
    }

    private void premuteBack(int[] nums, LinkedList<Integer> path) {
        if (path.size()==nums.length){
            resPermute.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                used[i] = true;
                path.add(nums[i]);
                premuteBack(nums,path);
                used[i] = false;
                path.removeLast();
            }
        }
    }

    //20
    public boolean isValid(String s) {
        if ((s.length()&1)==1) return false;
        HashMap<Character, Character> map = new HashMap<>() {{
            put(']','[');
            put(')','(');
            put('}','{');
        }};
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                if (!stack.isEmpty()&&stack.pop()==map.get(c)){
                    continue;
                }
                return false;
            }
            stack.push(c);
        }
        return stack.isEmpty();
    }

    //121
    public int maxProfit(int[] prices) {
        int flag = prices[0];
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            flag = Math.min(prices[i],flag);
            res = Math.max(res,prices[i]-flag);
        }
        return res;
    }

    //103、锯齿型遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            LinkedList<Integer> ele = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = deque.poll();
                if (res.size()%2==0){
                    ele.addLast(curNode.val);
                }else {
                    ele.addFirst(curNode.val);
                }
                if (curNode.left!=null){
                    deque.offer(curNode.left);
                }
                if (curNode.right!=null){
                    deque.offer(curNode.right);
                }
            }
            res.add(ele);
        }
        return res;
    }

    //236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        if (root==p||root==q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left==null&&right==null) return null;
        if (left==null||right==null) return left==null?right:left;
        return root;
    }

    //300
    public int lengthOfLIS(int[] nums) {
        int size = 0;
        int[] dp = new int[nums.length];
        for (int num:nums){
            int l=0,r=size-1;
            while (l<=r){
                int mid = l+(r-l)/2;
                if (dp[mid]<num){
                    l = mid+1;
                }else {
                    r = mid-1;
                }
            }
            dp[l] = num;
            if (l==size) size++;
        }
        return size;
    }

    //23
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        return mergeKLists(lists,0,lists.length-1);
    }

    private ListNode mergeKLists(ListNode[] lists, int l, int r) {
        if (l==r) return lists[l];
        int mid = l+(r-l)/2;
        ListNode left = mergeKLists(lists, l, mid);
        ListNode right = mergeKLists(lists, mid + 1, r);
        return mergeT(left,right);
    }

    private ListNode mergeT(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = (left==null)?right:left;
        return dummy.next;
    }


    //143
    public void reorderList(ListNode head) {
        ListNode midNext = findMid(head);
        ListNode head2 = reverse(midNext);
        while (head2!=null){
            ListNode preNext = head.next;
            ListNode postNext = head2.next;
            head.next = head2;
            head2.next = preNext;
            head = preNext;
            head2 = postNext;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode pre =null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }


    //415
    public String addStrings(String num1, String num2) {
        int len1 = num1.length(),len2 = num2.length();
        int i = len1-1,j = len2-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i>=0||j>=0||carry!=0){
            int val1 = (i>=0)?num1.charAt(i)-'0':0;
            int val2 = (j>=0)?num2.charAt(j)-'0':0;
            int curSum = val1+val2+carry;
            carry  =curSum/10;
            sb.append(curSum%10);
            i--;
            j--;
        }
        return sb.reverse().toString();
    }

    //56
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(o1,o2)->{
            return o1[0]-o2[0];
        });
        LinkedList<int[]> res = new LinkedList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0]<=res.getLast()[1]){
                res.getLast()[1] = Math.max(res.getLast()[1],cur[1]);
            }else {
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    //42
    public int trap(int[] height) {
        int lMax = height[0],rMax = height[height.length-1];
        int l = 0,r = height.length-1;
        int res =0;
        while (l<r){
            lMax = Math.max(lMax,height[l]);
            rMax = Math.max(rMax,height[r]);
            if (height[l]<height[r]){
                res+=(lMax-height[l++]);
            }else {
                res += (rMax-height[r--]);
            }
        }
        return res;
    }


    //124
    int resMaxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return resMaxPathSum;
    }

    private int dfs(TreeNode root) {
        if (root==null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int leftVal = Math.max(left,0);
        int rightVal = Math.max(right,0);
        resMaxPathSum = Math.max(resMaxPathSum,root.val+leftVal+rightVal);
        return Math.max(leftVal,rightVal)+root.val;
    }

    //94
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode cur = stack.pop();
                res.add(cur.val);
                root = cur.right;
            }
        }

        return res;
    }


    //199
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                if (i==size-1) res.add(cur.val);
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
            }
        }
        return res;
    }

    //19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy,fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


    //82
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            int val = cur.next.val;
            if (cur.next.next.val == val){
                while (cur.next.next!=null&&cur.next.next.val==val){
                    cur.next.next = cur.next.next.next;
                }
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }


    //142
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast){
                fast = head;
                while (slow!=fast){
                    slow = slow.next;
                    fast  =fast.next;
                }
                return slow;
            }
        }
        return null;
    }


    //4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length>nums2.length) return findMedianSortedArrays(nums2,nums1);
        int len1 = nums1.length,len2 = nums2.length;
        int l = 0,r = len1;
        while (l<=r){
            int i = l+(r-l)/2;
            int j = (len1+len2+1)/2-i;

            int nums1L = i==0?Integer.MIN_VALUE:nums1[i-1];
            int nums1R = i==len1?Integer.MAX_VALUE:nums1[i];
            int nums2L = j==0?Integer.MIN_VALUE:nums2[j-1];
            int nums2R = j==len2?Integer.MAX_VALUE:nums2[j];

            if (nums1L<=nums2R&&nums2L<=nums1R){
                if (((len1+len2)&1)==1){
                    return Math.max(nums1L,nums2L);
                }else {
                    return (Math.max(nums1L,nums2L)+
                            Math.min(nums1R,nums2R))/2.0;
                }
            }else if (nums1L>nums2R){
                r = i-1;
            }else {
                l = i+1;
            }
        }
        return -1;
    }


    //165
    public int compareVersion(String version1, String version2) {
        int len1 = version1.length(),len2 = version2.length();
        int i=0,j=0;
        while (i<len1||j<len2){
            int val1 = 0;
            for (;i<len1&&version1.charAt(i)!='.';i++){
                val1 = val1*10 +version1.charAt(i)-'0';
            }
            i++;

            int val2 = 0;
            for (;j<len2&&version2.charAt(j)!='.';j++){
                val2 = val2*10 +version2.charAt(j)-'0';
            }
            j++;

            if (val1!=val2){
                return val1>val2?1:-1;
            }
        }
        return 0;
    }

    //704
//    public int search(int[] nums, int target) {
//
//    }

    //148
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode mid = findmid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left,right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left==null?right:left;
        return dummy.next;
    }

    private ListNode findmid(ListNode head) {
        ListNode slow = head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }


    //22
    List<String> resGenerateParenthesis;
    StringBuilder sbGenerateParenthesis;
    public List<String> generateParenthesis(int n) {
        resGenerateParenthesis = new LinkedList<>();
        sbGenerateParenthesis = new StringBuilder();
        generateParenthesisBack(n,0,0);
        return resGenerateParenthesis;
    }

    private void generateParenthesisBack(int n, int l, int r) {
        if (sbGenerateParenthesis.length()==2*n){
            resGenerateParenthesis.add(new String(sbGenerateParenthesis));
            return;
        }
        if (l<n){
            sbGenerateParenthesis.append('(');
            generateParenthesisBack(n,l+1,r);
            sbGenerateParenthesis.deleteCharAt(sbGenerateParenthesis.length()-1);
        }
        if (r<l){
            sbGenerateParenthesis.append(')');
            generateParenthesisBack(n,l,r+1);
            sbGenerateParenthesis.deleteCharAt(sbGenerateParenthesis.length()-1);
        }
    }


    //239
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty()&&nums[i]>=nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        res[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty()&&nums[i]>=nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if (deque.peekFirst()==i-k){
                deque.pollFirst();
            }
            res[i-k+1] = nums[deque.peekFirst()];
        }

        return res;
    }

    //31
    public void nextPermutation(int[] nums) {
        int flag = -1;
        for (int i = nums.length-2; i >=0 ; i--) {
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }

        if (flag==-1){
            rever(nums,0,nums.length-1);
            return;
        }

        for (int i = nums.length-1; i > flag; i--) {
            if (nums[i]>nums[flag]){
                int tmp = nums[i];
                nums[i] = nums[flag];
                nums[flag] = tmp;
                rever(nums,flag+1,nums.length-1);
                return;
            }
        }
    }

    private void rever(int[] nums, int l, int r) {
        while (l<r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }


    //2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curSum =val1+val2+carry;
            cur.next = new ListNode(curSum%10);
            cur = cur.next;
            carry = curSum/10;
            if (l1!=null) l1=l1.next;
            if (l2!=null) l2=l2.next;
        }
        return dummy.next;
    }

    //32
    public int longestValidParentheses(String s) {
        int l =0,r=0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='(') l++;
            else r++;
            if (l==r) res = Math.max(res,2*l);
            if (r>l) l=r=0;
        }

        l=r=0;
        for (int i = s.length()-1; i >=0 ; i--) {
            char c = s.charAt(i);
            if (c=='(') l++;
            else r++;
            if (l==r) res = Math.max(res,2*l);
            if (l>r) l=r=0;
        }
        return res;
    }

    //70
    public int climbStairs(int n) {
        if (n==1) return 1;
        int pre1 = 1;
        int pre2 = 1;
        for (int i = 2; i <=n ; i++) {
            int cur = pre1+pre2;
            pre1 = pre2;
            pre2 = cur;
        }
        return pre2;
    }

    /**改成下面的代码，虽然看着不优雅，但也是可以的*/
    public int climbStairs_(int n) {
        if (n==1) return 1;
        int pre1 = 1;
        int pre2 = 1;
        for (int i = 2; i <=n ; i++) {
            int cur = pre1+pre2;
            pre1 = pre2;
            pre2 = cur;
            if(i==n) return cur;
        }
        return -1;
    }

    //43
    public String multiply(String num1, String num2) {
        if ("0".equals(num1)||"0".equals(num2)) return "0";
        int len1 = num1.length(),len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1-1; i >=0 ; i--) {
            for (int j = len2-1; j >=0; j--) {
                int val1 = num1.charAt(i)-'0';
                int val2 = num2.charAt(j)-'0';
                int curMul = val1*val2+res[i+j+1];
                res[i+j+1] = curMul%10;
                res[i+j] += curMul/10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (sb.length()==0&&res[i]==0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }


    //76
    public String minWindow(String s, String t) {
        if (s.length()<t.length()) return "";
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        HashMap<Character, Integer> window = new HashMap<>();
        int valid = 0;
        int left = 0;
        int start = -1,len = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c).intValue()==need.get(c).intValue()){
                    valid++;
                }
                while (valid==need.size()){
                    if (i-left+1<len){
                        len = i-left+1;
                        start = left;
                    }
                    char c1 = s.charAt(left++);
                    if (need.containsKey(c1)){
                        window.put(c1,window.get(c1)-1);
                        if (window.get(c1)<need.get(c1)){
                            valid--;
                        }
                    }
                }
            }
        }
        return start==-1?"":s.substring(start,start+len);
    }


    //41
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i<nums.length) {
            if (nums[i]>0&&nums[i]<=nums.length&&nums[nums[i]-1]!=nums[i]){
                swap(nums,nums[i]-1,i);
            }else {
                i++;
            }

        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j]!=j+1) return j+1;
        }
        return nums.length+1;
    }

//    private void swap(int[] nums, int l, int r) {
//        int tmp = nums[l];
//        nums[l] = nums[r];
//        nums[r] = tmp;
//    }

    //105
    HashMap<Integer,Integer> inorderMap;
    int preorderIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
        if (l>r) return null;
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.left = buildTree(preorder,inorder,l,index-1);
        root.right = buildTree(preorder,inorder,index+1,r);
        return root;
    }

    //LCR 140
    /**解法1：套用“删除链表倒数第n个节点”的代码*/
    public ListNode trainingPlan(ListNode head, int cnt) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy,fast = head;
        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }
        while (fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        //出了while循环slow会指向倒数第 n+1 个节点。因此返回slow.next
        return slow.next;
    }
    /**另一种写法*/
    public ListNode trainingPlan_(ListNode head, int cnt) {
        ListNode slow = head,fast = head;
        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }
        while (fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }



    //78
    List<List<Integer>> resSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubsets = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        subsetsBack(nums,path,0);
        return resSubsets;
    }

    private void subsetsBack(int[] nums, LinkedList<Integer> path, int index) {
//        if (index==nums.length) return; //写在这里是错误的！
        resSubsets.add(new LinkedList<>(path));
        /**err：不加这一句就可以，并不会发生StackOverflow！！但是如果加了这一句，则——————
         *      这一句必须在“resSubsets.add(new LinkedList<>(path));”的后面，不然结果会
         *  少很多，一句话概况少了多少，凡是包含最后nums最后一个元素的 子集，结果都没有。
         *      进一步解释为什么？因为如果index==nums.length，根据for循环逻辑可知，一定是
         *  上一步把最后一个元素添加进path了，然后递归调用subsetsBack，此时index==nums.length。
         *  如果下面的这句话放在subsetsBack的第一行，就导致方法直接返回了，path没有添加进
         *  结果！！!
         *      再解释一下为什么不会发生StackOverflow？？方法的返回值是null，即使没有这一句，当
         * index来到nums.length的时候，for循环由于循环条件不满足因此不会循环，导致方法结束，因此
         * 并不会无终止的持续递归下去，因此不会栈溢出。
         *  */
        if (index==nums.length) return;
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsBack(nums,path,i+1);
            path.removeLast();
        }
    }


    //151
    public String reverseWords(String s) {
        String trim = s.trim();
        String s1 = reverString(trim);
        String[] s2 = s1.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str:s2){
            sb.append(reverString(str));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private String reverString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    //129
    public int sumNumbers(TreeNode root) {
        int res = 0;
        if (root==null) return 0;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        LinkedList<Integer> vals = new LinkedList<>();
        nodes.offer(root);
        vals.offer(root.val);
        while (!nodes.isEmpty()){
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = nodes.poll();
                Integer curVal = vals.poll();
                if (cur.left==null&&cur.right==null)
                    res += curVal; /**err：如果没有左右孩子，则把当前节点的值累加到结果，不能乘10*/
                if (cur.left!=null){
                    nodes.offer(cur.left);
                    vals.offer(curVal*10+cur.left.val);
                }
                if (cur.right!=null){
                    nodes.offer(cur.right);
                    vals.offer(curVal*10+cur.right.val);
                }
            }
        }
        return res;
    }

    //155
    class MinStack {
        Stack<Integer> allStack;
        Stack<Integer> minStack;

        public MinStack() {
            allStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            allStack.push(val);
            if (minStack.isEmpty()||val<=minStack.peek()){
                minStack.push(val);
            }
        }

        public void pop() {
            Integer cur = allStack.pop();
            if (cur.equals(minStack.peek())){
                minStack.pop();
            }
            /**或者使用下面的方法来判断相等。。但就是不能使用“==”来判断*/
//            if (cur.intValue()==minStack.peek().intValue()){
//                minStack.pop();
//            }
        }

        public int top() {
            return allStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    //34
    public int[] searchRange(int[] nums, int target) {
        int left = searchLeft(nums,target);
        if (left==nums.length||nums[left]!=target) return new int[]{-1,-1};
        int right = searchRight(nums,target);
        return new int[]{left,right};
    }

    private int searchRight(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while (l<=r){
            int mid =l+(r-l)/2;
            if (nums[mid]>target){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return r;
    }

    private int searchLeft(int[] nums, int target) {
        int l =0,r= nums.length-1;
        while (l<=r){
            int mid =l+(r-l)/2;
            if (nums[mid]<target){
                l = mid+1;
            }else {
                r =mid-1;
            }
        }
        return l;
    }


    //394
    public String decodeString(String s) {
        int multi=0;
        StringBuilder res = new StringBuilder();
        Stack<Integer> intStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c>='0'&&c<='9'){
                multi =multi*10+c-'0';
            }else if (c=='['){
                strStack.push(new String(res));
                intStack.push(multi);
                multi = 0;
                res = new StringBuilder();
            }else if (c==']'){
                Integer cur = intStack.pop();
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < cur; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder().append(strStack.pop()).append(tmp);
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }

    //39
    List<List<Integer>> resCombinationSum;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        resCombinationSum = new LinkedList<>();
        LinkedList<Integer> pathCombinationSum = new LinkedList<>();
        combinationSumBack(candidates,pathCombinationSum,target,0);
        return resCombinationSum;
    }

    private void combinationSumBack(int[] candidates, LinkedList<Integer> pathCombinationSum, int target,int index) {
        if (target==0) resCombinationSum.add(new LinkedList<>(pathCombinationSum));
        if (index==candidates.length) return;
        if(target<0) return;
        for (int i = index; i < candidates.length; i++) {
            target -= candidates[i];
            pathCombinationSum.add(candidates[i]);
            combinationSumBack(candidates,pathCombinationSum,target,i); //
            target += candidates[i];
            pathCombinationSum.removeLast();
        }
    }

    //144
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root==null) return res;
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right!=null){
                stack.push(cur.right);
            }
            if (cur.left!=null){
                stack.push(cur.left);
            }
        }
        return res;
    }


    public List<Integer> preorderTraversal_digui(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        dfs(root,res);
        return res;
    }

    private void dfs(TreeNode root, LinkedList<Integer> res) {
        if (root==null) return;
        res.add(root.val);
        dfs(root.left,res);
        dfs(root.right,res);
    }


    //64
    public int minPathSum(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1]+grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]= Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    //48
    public void rotate(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                int tmp  = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }


    //128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num:nums){
            set.add(num);
        }
        int res =0;
        for (int num:set){
            if (!set.contains(num-1)){
                int len = 0;
                while (set.contains(len+num)) len++;
                res = Math.max(res,len);
            }
        }
        return res;
    }

    //240
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int i=0,j = n-1;
        while (i<m&&j>=0){
            if (matrix[i][j]==target) return true;
            else if (matrix[i][j]>target) j--;
            else i++;
        }
        return false;
    }

    //98
    public boolean isValidBST(TreeNode root) {
        if (root==null) return true;
        return isValidBST(root,null,null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root==null) return true;
        if (min!=null&&root.val<=min) return false;
        if (max!=null&&root.val>=max) return false;
        return isValidBST(root.left,min,root.val)&&isValidBST(root.right,root.val,max);
    }

    public boolean isValidBST_inorder(TreeNode root) {
        if (root==null) return true;
        Integer pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode cur = stack.pop();
                if (pre==null) pre = cur.val;
                else {
                    if (pre>=cur.val) return false;
                    pre = cur.val;
                }
                root = cur.right;
            }
        }
        return true;
    }

    //110

    /**
     【注意】其他的解法需要了解一下~~
     */
    public boolean isBalanced(TreeNode root) {
        if (root==null) return true;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return isBalanced(root.left)&&
                isBalanced(root.right)&&
                Math.abs(left-right)<=1; /**【注意】高度之差的要求：小于等于1（等于1是包含的）*/
    }

    private int getDepth(TreeNode root) {
        if (root==null) return 0;
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        return Math.max(l,r)+1;
    }


    //543
    int resDiameterOfBinaryTree = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs1(root);
        return resDiameterOfBinaryTree;
    }

    private int dfs1(TreeNode root) {
        if (root==null) return 0;
        int l = dfs1(root.left);
        int r = dfs1(root.right);
        resDiameterOfBinaryTree = Math.max(resDiameterOfBinaryTree,l+r);
        return Math.max(l,r)+1;
    }


    //221

    /**
     【第一行 或者 第一列】 第一行或者第一列时1的位置就是1，否则时0；
     【矩阵中间的位置】 如果matrix的该位置时‘1’，则取决于左上角三个位置dp值的最小值；否则matrix该位置的值
            是0，则该位置的dp值也是0
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[][] dp = new int[m][n];
        int res =0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0||j==0){
                    if (matrix[i][j]=='1') dp[i][j] = 1;
                }else if (matrix[i][j]=='1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
                res = Math.max(dp[i][j],res);
            }
        }
        return res*res;
    }

    //234
    public boolean isPalindrome(ListNode head) {
        ListNode mid = findMiddle(head);
        ListNode head2 = reve(mid);
        while (head2!=null){
            if (head.val!=head2.val){
                return false;
            }
            head2 = head2.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reve(ListNode mid) {
        ListNode pre = null,cur = mid;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**“回文链表”题目中找中间节点的时候，就走常规的逻辑没问题，甚至前一半链表最后一个节点的next域不置null也没问题*/
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow  =slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //695
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]==1){
                     int cur = dfs2(grid,i,j);
                     res = Math.max(cur,res);
                }
            }
        }
        return res;
    }

    private int dfs2(int[][] grid, int i, int j) {
        if (i<0||j<0||i==grid.length||j==grid[0].length||grid[i][j]!=1){
            return 0;
        }
        grid[i][j] = 0;
        return (dfs2(grid,i+1,j)+
                dfs2(grid,i-1,j)+
                dfs2(grid,i,j-1)+
                dfs2(grid,i,j+1)) + 1; /**【注】记得+1，走到这里说明当前位置grid[i][j]是陆地，需要+1*/
    }


    //101
    /*解法1：递归*/
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        return isSymmetric(root.left,root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left==null&&right==null) return true;
        if (left==null||right==null) return false;
        return isSymmetric(left.left,right.right)&&
                isSymmetric(left.right,right.left)&&
                left.val==right.val;
    }

    /**解法2：层序遍历迭代*/


    //162
    public int findPeakElement(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (nums[mid]>nums[mid+1]){
                r = mid;
            }else {
                l = mid+1;
            }
        }
        return l;
    }


    //662
    public int widthOfBinaryTree(TreeNode root) {
        int res = 0;
        if (root ==null)return res;
        LinkedList<TreeNode> deque = new LinkedList<>();
        LinkedList<Integer> nodeVal = new LinkedList<>();
        deque.offer(root);
        nodeVal.offer(0);
        while (!deque.isEmpty()){
            int size = deque.size();
            int first = -1,last = -1;
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                Integer curVal = nodeVal.poll();
                if (i==0) first = curVal;
                if (i==size-1) last = curVal;
                if (cur.left!=null){
                    deque.offer(cur.left);
                    nodeVal.offer(curVal*2);
                }
                if (cur.right!=null){
                    deque.offer(cur.right);
                    nodeVal.offer(curVal*2+1);
                }
            }
            res = Math.max(last-first+1,res);
        }
        return res;
    }

    //62
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0||j==0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    //152
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int preMin = nums[0],preMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(Math.min(preMin*nums[i],preMax*nums[i]),nums[i]);
            int curMax = Math.max(Math.max(preMin*nums[i],preMax*nums[i]),nums[i]);
            res = Math.max(curMax,res);
            preMax = curMax;
            preMin = curMin;
        }
        return res;
    }

    //112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null){
            if (targetSum==0) return true;
            return false;
        }
        return hasPathSum(root.left,targetSum-root.val)||
                hasPathSum(root.right,targetSum-root.val);
    }

    //560
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0;
        int res  =0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            res += map.getOrDefault(preSum-k,0);
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return res;
    }

    //169
    public int majorityElement(int[] nums) {
        int flag = -1,total = 0;
        for (int num:nums){
            if (total==0) flag = num;
            total += (flag==total)?1:-1;
        }
        return flag;
    }

    //226
    /**第一种解法：递归反转*/
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**第二种写法：迭代*/
    public TreeNode invertTree_diedai(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                swapLR(cur);
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
            }
        }
        return root;
    }

    private void swapLR(TreeNode cur) {
        TreeNode tmp = cur.left;
        cur.left = cur.right;
        cur.right  =tmp;
    }

    //83
    public ListNode deleteDuplicates_(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode slow = head,fast = head.next;
        while (fast!=null){
            if (fast.val==slow.val){
                fast = fast.next;
            }else {
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }
        }
        slow.next = null;
        return head;
    }

    //209
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0,right= 0;
        int sum = 0;
        int res = nums.length+1;
        while (right<nums.length){
            sum += nums[right++];
            while (sum>=target){
                res = Math.min(res,right-left);
                sum -= nums[left++];
            }
        }
        return res==nums.length+1?0:res;
    }

    //139
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < s.length()+1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    //24
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1,head),cur=dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;

            cur.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            cur = node1;
        }
        return dummy.next;
    }


    //14
    /**
     * 不建议使用下面的写法，比较乱
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        String flag = strs[0];
        int len = -1;
        for (int i = 0; i < flag.length(); i++) {
            char c = flag.charAt(i);
            int j = 1;
            for (; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    break;
                }
            }
            if (j == strs.length - 1) continue;
            else {
                len = i;
                break;
            }
        }
        return flag.substring(0, len);

    }
}
