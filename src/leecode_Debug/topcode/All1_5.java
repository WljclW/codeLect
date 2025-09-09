package leecode_Debug.topcode;

import leecode_Debug.BTree.TreeNode;
import leecode_Debug.top100.ListNode;

import javax.sql.rowset.FilteredRowSet;
import java.io.File;
import java.util.*;

/**codetop 1~5的全量——————=========1~5使用这个文件
 * err：5、20、215、92、105、234、283、、
 * undo：46的最优解、88、415、72题优化空间、1143空间优化的最优解、93、69、8、151、468、718
 * 模糊：54
 */
public class All1_5 {
    /*
     * 3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。无重复字符的最长子串
     * */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int cur = 0,left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (cur<s.length()){
            char c = s.charAt(cur++);
            map.put(c,map.getOrDefault(c,0)+1);
            while (map.get(c)>1){
                char c1 = s.charAt(left);
                map.put(c1,map.get(c1)-1);
                left++;
            }
            res = Math.max(cur-left,res);
        }
        return res;
    }


    /*146.
    请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    实现 LRUCache 类：
    LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
    * */
    class LRUCache {
        class DouNode{
            DouNode prev;
            DouNode next;
            int key;
            int value;
            public DouNode(int key,int value){
                this.key = key;
                this.value = value;
            }
            public DouNode(){}
        }

        int size;
        int capacity;
        HashMap<Integer,DouNode> map;
        DouNode head;
        DouNode tail;
        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new DouNode();
            tail = new DouNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DouNode node = map.get(key);
            if (node==null){
                return -1;
            }else {
                moveToHead(node);
                return node.value;
            }
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
                    removeNode(relTail);
                    map.remove(relTail.key);
                    size--;
                }
            }
        }

        private void moveToHead(DouNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DouNode node) {
            node.next = head.next;
            node.next.prev = node;
            node.prev = head;
            head.next = node;
        }

        private void removeNode(DouNode node) {
            DouNode prevNode = node.prev;
            DouNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    }

    /*206. 反转链表
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
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



    /*215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
//    public int findKthLargest(int[] nums, int k) {
//
//    }



    /*25
    k个一组翻转链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head),pre = dummy,end = dummy;
        while (end.next!=null){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end==null) return dummy.next;

            ListNode start = pre.next;
            ListNode nextStart = end.next;
            end.next = null;

            pre.next = reve(start);
            start.next = nextStart;

            pre = start;
            end = start;
        }
        return dummy.next;
    }

    private ListNode reve(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /*15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * 【】：关键的步骤在于双指针 过程中，如何跳过所有相同的元素。
     * */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i>0&&nums[i]==nums[i-1]) continue;
            int left = i+1,right = nums.length-1;
            while (left<right){
                int curVal = nums[i]+nums[left]+nums[right];
                if (curVal<0){
                    left++;
                }else if (curVal>0){
                    right--;
                }else {
                    res.add(new LinkedList<>(Arrays.asList(nums[i],nums[left],nums[right])));
                    while (left<right&&nums[left]==nums[++left]);
                    while (left<right&&nums[right]==nums[--right]);
                }
            }
        }
        return res;
    }


    /*53.最大子数组和
        给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
子数组是数组中的一个连续部分。
     */
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int preSum = 0;
        for (int num:nums){
            preSum = Math.max(preSum+num,num);
            res = Math.max(preSum,res);
        }
        return res;
    }



    /*
    手撕快排
     */
    public void quickSort(int[] arr,int left,int right){
        if (left>=right) return;
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap1(arr,pivotIndex,right);
        pivotIndex = partion(arr,left,right);

        quickSort(arr,left,pivotIndex-1);
        quickSort(arr,pivotIndex+1,right);
    }

    private int partion(int[] arr, int left, int right) {
        int cur = left;
        for (int i = left; i < right; i++) {
            if (arr[i]<arr[right]){
                swap1(arr,cur++,i);
            }
        }
        swap1(arr,cur,right);
        return cur;
    }

    private void swap1(int[] arr, int pivotIndex, int right) {
        int tmp = arr[pivotIndex];
        arr[pivotIndex] = arr[right];
        arr[right] = tmp;
    }

    /*21.
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (list1!=null&&list2!=null){
            if (list1.val<list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = list1==null?list2:list1;
        return dummy.next;
    }

    /*5 最长回文子串
    给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
    public String longestPalindrome(String s) {
        if (s==null||s.length()==0) return "";
        StringBuilder sb = new StringBuilder("#");
        for (char c:s.toCharArray()){
            sb.append(c).append("#");
        }
        String str = sb.toString();

        int[] p = new int[str.length()];
        int center = 0,rightBound = 0;
        int start = 0,maxLen = 0;
        for (int i = 0; i < str.length(); i++) {
            int mirror = 2*center-i;
            if (i<rightBound){
                p[i] = Math.min(p[mirror],rightBound-i);
            }

            int left = i-p[i]-1,right = i+p[i]+1;
            while (left>=0&&right<str.length()&&str.charAt(left)==str.charAt(right)){
                p[i]++;
                left--;
                right++;
            }

            if (i+p[i]>rightBound){
                center  =i;
                rightBound = i+p[i];
            }

            if (p[i]>maxLen){
                maxLen = p[i];
                start = (i-maxLen)/2;
            }
        }
        return s.substring(start,start+maxLen);
    }

    /*102.层序遍历*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> ele = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                ele.add(cur.val);
                if (cur.left!=null) queue.offer(cur.left);
                if (cur.right!=null) queue.offer(cur.right);
            }
        }
        return res;
    }

    /*1两数之和
    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

你可以按任意顺序返回答案。
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                return new int[]{map.get(target-nums[i]),i};
            map.put(target-nums[i],i);
        }
        return new int[]{-1,-1};
    }



    /*33.....81是扩展（允许有重复元素）
    整数数组 nums 按升序排列，数组中的值 互不相同 。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
    你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    * */
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>=nums[0]){
                if (target>=nums[0]&&target<nums[mid]){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }else {
                if (target>nums[mid]&&target<=nums[right]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }


    /*200.
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。
    * */
    public int numIsland(char[][] grid){
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    res++;
                    dfs1(grid,i,j);
                }
            }
        }
        return res;
    }

    private void dfs1(char[][] grid, int i, int j) {
        if (i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]!='1') return;
        grid[i][j] = '\n';
        dfs1(grid,i-1,j);
        dfs1(grid,i+1,j);
        dfs1(grid,i,j-1);
        dfs1(grid,i,j+1);
    }

    /*46.
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    * */
//    public List<List<Integer>> permute(int[] nums) {
//
//    }


    /*88. 合并两个有序数组
给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。

请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。*/


     /*20.
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是
    * 否有效。
    有效字符串需满足：
    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    每个右括号都有一个对应的相同类型的左括号。*/
    public boolean isValid(String s) {
        if (s.length()%2!=0) return false;
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>() {{
            put(')','(');
            put('}','{');
            put(']','[');
        }};
        for (char c:s.toCharArray()){
            if (map.containsKey(c)){
                if (stack.isEmpty()||stack.pop()!=map.get(c)){
                    return false;
                }
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /*121.买卖股票的最佳时机
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一
        个算法来计算你所能获取的最大利润。
      返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    * */
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int res = 0;
        for (int price:prices){
            minPrice = Math.min(price, minPrice);
            res = Math.max(price-minPrice,res);
        }
        return res;
    }


    /*236.二叉树最近公共祖先
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        if (root==p||root==q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left!=null&&right!=null) return root; /**这样写行不行？？*/
        return left==null?right:left;
    }

    /*
    103. 二叉树的锯齿形层序遍历
    给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。*/
    /**
     *      res中添加了几个list就知道，当前是遍历哪一层，因此根据res.size()判断层数的奇偶性，
     * 根据这个奇偶性来决定“当前这层元素添加时使用addLast() 还是 addFirst()”
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> ele = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (res.size()%2==0){
                    ele.addLast(cur.val);
                }else {
                    ele.addFirst(cur.val);
                }
                if (cur.left!=null) queue.offer(cur.left);
                if (cur.right!=null) queue.offer(cur.right);
            }
            res.add(ele);
        }
        return res;
    }


    /**
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     */

    /*92. 反转链表 II
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。*/
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        /**err：因此计数是从0开始，因此两个指针 都从 dummy开始数！！第几个节点则走多少步*/
        ListNode dummy = new ListNode(-1, head),slow = dummy,fast = dummy;
        for (int i = 0; i < left - 1; i++) { /**err：slow要来到left的前一个节点，因此满足“i<left-1”*/
            slow = slow.next;
        }
        for (int i = 0; i < right; i++) { /**err：fast要来到right节点，因此“i<right”*/
            fast = fast.next;
        }

        ListNode start = slow.next;
        ListNode restStart = fast.next;
        fast.next = null;

        slow.next = reverse1(start);
        start.next = restStart;

        return dummy.next;
    }

    private ListNode reverse1(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur =  next;
        }
        return pre;
    }


    /*141环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。*/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast) return true;
        }
        return false;
    }

    /*54.螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * */
    public List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> res = new LinkedList<>();
        int m = matrix.length,n = matrix[0].length;
        int top = 0,bottom = m-1,left =0,right = n-1;
        while (res.size()<m*n){
            if (top<=bottom){ /**小于等于号，两个符号的顺序有没有关系？？？*/
                for (int i = left; i <=right; i++) {
                    res.add(matrix[top][i]);
                }
            }
            top++;
            if (left<=right){
                for (int i = top; i <=bottom; i++) {
                    res.add(matrix[i][right]);
                }
            }
            right--;
            if (bottom>=top){
                for (int i = right; i >=left; i--) {
                    res.add(matrix[bottom][i]);
                }
            }
            bottom--;
            if (left<=right){
                for (int i = bottom; i >=top; i--) {
                    res.add(matrix[i][left]);
                }
            }
            left++;
        }
        return res;
    }


    /*300.最长递增子序列
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        int size = 0;
        for (int num:nums){
            int left = 0,right = size-1;
            while (left<=right){
                int mid = left+(right-left)/2;
                if (dp[mid]>=num) right = mid-1;
                else left = mid+1;
            }
            dp[left] = num;
            if (left==size){
                size++;
            }
        }
        return size;
    }


    /*
    143重排链表
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return;
        ListNode mid = findMid1(head);
        ListNode head2 = mid.next;
        mid.next = null;

        head2 = reverse2(head2);
        mergerTwo1(head,head2);
    }

    private void mergerTwo1(ListNode head, ListNode head2) {
        while (head2!=null){
            ListNode headNext = head.next;
            ListNode head2Next = head2.next;
            head.next = head2;
            head2.next = headNext;
            head = headNext;
            head2 = head2Next;
        }
    }

    private ListNode reverse2(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid1(ListNode head) {
        ListNode slow = head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /*23.合并 K 个升序链表
    * 给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。*/
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        return mergeKLists(lists,0,lists.length-1);
    }

    private ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = left+(right-left)/2;
        ListNode l = mergeKLists(lists, left, mid);
        ListNode r = mergeKLists(lists, mid + 1, right);

        return mergerTwo2(l,r);
    }

    private ListNode mergerTwo2(ListNode l, ListNode r) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (l!=null&&r!=null){
            if (l.val<r.val){
                cur.next = l;
                l  =l.next;
            }else {
                cur.next = r;
                r = r.next;
            }
            cur = cur.next;
        }
        cur.next = l==null?r:l;
        return dummy.next;
    }


    /*415. 字符串相加
    给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。

你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。*/
    /**
     * 【注】是字符串不是链表
     */
//    public String addStrings(String num1, String num2) {
//
//    }


    /*56.
     *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重
     * 叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0]<=res.getLast()[1]){
                res.getLast()[1] = Math.max(cur[1],res.getLast()[1]);
            }else {
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    /*160.相交链表
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果
     * 两个链表不存在相交节点，返回 null 。保证不出现环*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA,p2= headB;
        while (p1!=p2){
            p1 = p1==null?headB:p1.next;
            p2 = p2==null?headA:p2.next;
        }
        return p1;
    }


    /*
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * */
    public int trap(int[] height) {
        int leftMax = 0,rightMax = 0;
        int left = 0,right = height.length-1;
        int res = 0;
        while (left<right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
            if (height[left]<height[right]){
                res += leftMax-height[left];
                left++;
            }else {
                res += rightMax-height[right];
                right--;
            }
        }
        return res;
    }


    /*72.编辑距离
    * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
    你可以对一个单词进行如下三种操作：
    插入一个字符
    删除一个字符
    替换一个字符*/
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(),len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            for (int j = 0; j < len2 + 1; j++) {
                if (i==0||j==0){
                    dp[i][j] = i==0?j:i;
                }else {
                    if (word1.charAt(i-1)==word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
                    }
                }
            }
        }
        return dp[len1][len2];
    }


    /*124. 二叉树中的最大路径和
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列
    中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        dfs2(root);
        return maxSum;
    }

    private int dfs2(TreeNode root) {
        if (root==null) return 0;
        int left = dfs2(root.left);
        int right = dfs2(root.right);
        left = Math.max(left,0);
        right = Math.max(right,0);
        maxSum = Math.max(maxSum,left+right+root.val);
        return root.val+Math.max(left,right);
    }


    /*1143. 最长公共子序列
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。*/
//    public int longestCommonSubsequence(String text1, String text2) {
//
//    }



    /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
    "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过
    在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
//    public List<String> restoreIpAddresses(String s) {
//
//    }


    /*
    82. 删除排序链表中的重复元素 II
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            int curVal = cur.next.val;
            if (cur.next.next.val==curVal){
                while (cur.next.next!=null&&cur.next.next.val==curVal){
                    cur.next.next = cur.next.next.next;
                }
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }


    /*142.环形链表 II
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast =fast.next.next;
            if (fast==slow){
                slow  =head;
                while (slow!=fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }


    /*19.删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head),slow = dummy,fast = head;
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


    /*4.寻找两个正序数组的中位数
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
    * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length>nums2.length)return findMedianSortedArrays(nums2,nums1);
        int m =nums1.length,n = nums2.length;
        int l = 0,r = m;
        while (l<=r){
            int mid = l+(r-l)/2;
            int midj = (m+n+1)/2-mid;

            int nums1L = mid==0?Integer.MIN_VALUE:nums1[mid-1];
            int nums1R = mid==m?Integer.MAX_VALUE:nums1[mid];
            int nums2L = midj==0?Integer.MIN_VALUE:nums2[midj-1];
            int nums2R = midj==n?Integer.MAX_VALUE:nums2[midj];

            if (nums1L<=nums2R&&nums2L<=nums1R){ /**是不是必须带等于？？应该是的*/
                if ((m+n)%2==0){
                    return (Math.max(nums1L,nums2L)+Math.min(nums1R,nums2R))/2.0;
                }else {
                    return Math.max(nums1L,nums2L);
                }
            }else if (nums1L>nums2R){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return -1;
    }



    /*199.二叉树的右视图
    给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    * */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root==null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i==size-1) res.add(cur.val);
                if (cur.left!=null) queue.offer(cur.left);
                if (cur.right!=null) queue.offer(cur.right);
            }
        }

        return res;
    }




    /*165. 比较版本号
    给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
    比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
    返回规则如下：
    如果 version1 < version2 返回 -1，
    如果 version1 > version2 返回 1，
    除此之外返回 0。
    * */
    public int compareVersion(String version1, String version2) {
        int m = version1.length(),n = version2.length();
        int i =0,j =0;
        while (i<m||j<n){
            int val1 = 0;
            for (;i<m&&version1.charAt(i)!='.';i++){
                val1 = val1*10+version1.charAt(i)-'0';
            }
            i++;

            int val2 = 0;
            for (;j<n&&version2.charAt(j)!='.';j++){
                val2 = val2*10+version2.charAt(j)-'0';
            }
            j++;

            if (val1!=val2) return val1>val2?1:-1;
        }
        return 0;
    }


    /**
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     */
    /*94. 二叉树的中序遍历
给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。*/
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
                root =cur.right;
            }
        }

        return res;
    }



    /*704.二分查找
     */
    public int search1(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]==target){
                return mid;
            }else if (nums[mid]>target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return -1;
    }


    /*232. 用栈实现队列
请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：

实现 MyQueue 类：

void push(int x) 将元素 x 推到队列的末尾
int pop() 从队列的开头移除并返回元素
int peek() 返回队列开头的元素
boolean empty() 如果队列为空，返回 true ；否则，返回 false
说明：

你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。*/

    /**
     *【关键】两个栈：一个用于接收输入；一个用来给出输出
     *      如果outStack是空，就必须“把inStack中所有的数据弹出放入到outStack”，【注意】全部数据！！因
     *  为这样做才能保证先进先出；
     *      否则如果outStack不是空，则直接从outStack弹出即可，此时也满足先进先出
     */
    class MyQueue {
        Stack<Integer> inStack;
        Stack<Integer> outStack;
        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if (outStack.isEmpty()){
                while (!inStack.isEmpty()){
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }

        public int peek() {
            if (outStack.isEmpty()){
                while (!inStack.isEmpty()){
                    outStack.push(inStack.pop());
                }
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty()&&outStack.isEmpty();
        }
    }



    /*22.括号生成
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
    /*解法1：官方解回溯法*/
    List<String> resGenerateParenthesis;
    public List<String> generateParenthesis(int n) {
        resGenerateParenthesis = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        dfs3(n,0,0,sb);
        return resGenerateParenthesis;
    }

    private void dfs3(int n, int open, int close, StringBuilder sb) {
        if (sb.length()==2*n){
            resGenerateParenthesis.add(new String(sb));
            return;
        }
        if (open<n){
            sb.append('(');
            dfs3(n,open+1,close,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if (close<open){
            sb.append(')');
            dfs3(n,open,close+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    /*
    239.滑动窗口最大值
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!stack.isEmpty()&&nums[i]>=nums[stack.peekLast()]){
                stack.pollLast();
            }
            stack.offerLast(i);
        }
        res[0] = nums[stack.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!stack.isEmpty()&&nums[i]>=nums[stack.peekLast()]){
                stack.pollLast();
            }
            stack.offerLast(i);
            if (stack.peekFirst()==i-k) stack.pollFirst();
            res[i-k+1] = nums[stack.peekFirst()];
        }
        return res;
    }


    /*148.
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
//    public ListNode sortList(ListNode head) {
//
//    }


    /*31. 下一个排列
整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。

必须 原地 修改，只允许使用额外常数空间。*/
    public void nextPermutation(int[] nums) {
        int flag = -1;
        for (int i = nums.length-2; i >=0 ; i--) {
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }

        if (flag>=0){
            for (int i = nums.length-1; i >flag; i--) {
                if (nums[i]>nums[flag]){
                    swap2(nums,flag,i);
                    break;
                }
            }
        }
        reverse3(nums,flag+1,nums.length-1);
    }

    private void swap2(int[] nums, int flag, int i) {
        int tmp = nums[flag];
        nums[flag] = nums[i];
        nums[i] = tmp;
    }

    private void reverse3(int[] nums, int l, int r) {
        while (l<r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }


    /*69. x 的平方根
给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。*/
//    public int mySqrt(int x) {
//
//    }


    /*8. 字符串转换整数 (atoi)
请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。

函数 myAtoi(string s) 的算法如下：

空格：读入字符串并丢弃无用的前导空格（" "）
符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
返回整数作为最终结果。*/
//    public int myAtoi(String s) {
//
//    }


    /*32.最长有效括号
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的
     * 长度。*/
    public int longestValidParentheses(String s) {
        int left = 0,right = 0;
        int maxLen= 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='(') left++;
            else  right++;
            if(left==right) maxLen = Math.max(maxLen,2*left);
            if (left<right) left=right=0;
        }

        left=right=0;
        for (int i = s.length()-1; i >=0; i--) {
            if (s.charAt(i)=='(') left++;
            else  right++;
            if (left==right) maxLen = Math.max(maxLen,2*left);
            if (left>right) left=right=0;
        }
        return maxLen;
    }


    /*2两数相加
    给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        int carry = 0;
        while (l1!=null||l2!=null||carry!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curVal = val1+val2+carry;
            carry = curVal/10;
            cur.next = new ListNode(curVal%10);
            cur = cur.next;
            l1 = l1==null?l1:l1.next;
            l2 = l2==null?l2:l2.next;
        }
        return dummy.next;
    }


    /*7爬楼梯
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
    /**
     *【说明】题目中说了台阶数的最小值是1，不会是0
     */
    public int climbStairs(int n) {
        if (n<=2) return n;
        int first = 1,second = 2;
        for (int i = 3; i <=n; i++) {
            int curVal = first+second;
            first = second;
            second = curVal;
        }
        return second;
    }


    /*322. 零钱兑换
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <=amount; j++) {
                dp[j] = Math.min(dp[j-coins[i]]+1,dp[j]);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }


     /*43 字符串相乘
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    * */
    public String multiply(String num1, String num2) {
        int m = num1.length(),n = num2.length();
        int[] res = new int[m + n];
        for (int i = m-1; i >=0 ; i--) {
            for (int j = n-1; j >=0; j--) {
                int int1 = num1.charAt(i) - '0';
                int int2 = num2.charAt(j) - '0';
                int curVal = int1*int2+res[i+j+1];
                res[i+j] += curVal/10;
                res[i+j+1] = curVal%10;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (stringBuilder.length()==0&&res[i]==0) continue;
            stringBuilder.append(res[i]);
        }
        return stringBuilder.length()==0?"0":stringBuilder.toString();
    }


    /*
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
    public String minWindow(String s, String t) {
        if (s.length()<t.length()) return "";
        HashMap<Character, Integer> need = new HashMap<>();
        for(char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int valid = 0;
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


    /*41.缺失的第一个正数
    * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    * */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i]>0&&nums[i]<=nums.length&&nums[nums[i]-1]!=nums[i]){
                swap3(nums,nums[i]-1,i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }

    private void swap3(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    //    /*105.
//     * 从前序 和 中序 构造出二叉树*/
    int preorderIndex;
    HashMap<Integer,Integer> inorderMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
        if (l>r) return null; /**err：这里的条件判断不能缺失！！！*/
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.left = buildTree(preorder,inorder,l,index-1);
        root.right = buildTree(preorder,inorder,index+1,r);
        return root;
    }


    /*151. 反转字符串中的单词
给你一个字符串 s ，请你反转字符串中 单词 的顺序。

单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。

注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。*/
//    public String reverseWords(String s) {
//
//    }


    /*78. 子集
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。*/
    List<List<Integer>> resSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubsets = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs6(nums,0,path);
        return resSubsets;
    }

    private void dfs6(int[] nums, int index, LinkedList<Integer> path) {
        //        if (index==nums.length) return; //写在这里是错误的！
        resSubsets.add(new LinkedList<>(path));
        /**err：不加这一句就可以，并不会发生StackOverflow！！但是如果加了这一句，则——————
         *      这一句必须在“resSubsets.add(new LinkedList<>(path));”的后面，不然结果会
         *  少很多，一句话概况少了多少，凡是包含nums最后一个元素的 子集，结果都没有。
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
            dfs6(nums,i+1,path);
            path.removeLast();
        }
    }


    /**
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     * ==================================4==============================
     */


    /*129.求根到叶子节点数字之和
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    每条从根节点到叶节点的路径都代表一个数字：

    例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    计算从根节点到叶节点生成的 所有数字之和 。
    叶节点 是指没有子节点的节点。
    * */
    public int sumNumbers(TreeNode root) {
        if (root==null) return 0;
        int res = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> integers = new LinkedList<>();
        queue.offer(root);
        integers.offer(root.val);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                Integer curVal = integers.poll();
                /**
                 *这里也是同样的道理，不能到了null节点才更新res，那样就错了，相当于叶子节点的
                 * 两个孩子都统计了，不对
                 */
                if (cur.left==null&&cur.right==null) res+= curVal;
                if (cur.left!=null){
                    queue.offer(cur.left);
                    integers.offer(curVal*10+cur.left.val);
                }
                if (cur.right!=null){
                    queue.offer(cur.right);
                    integers.offer(curVal*10+cur.right.val);
                }
            }
        }
        return res;
    }


//    public int sumNumbers_digui(TreeNode root) {
//
//    }




    /*155. 最小栈
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

实现 MinStack 类:

MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。*/
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
            Integer pop = allStack.pop();
            if (pop.intValue()==minStack.peek().intValue()){
                minStack.pop();
            }
            /**或者使用下面的方法来判断相等。。但就是不能使用“==”来判断*/
//            if (pop.equals(minStack.peek())){
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



    /*34. 在排序数组中查找元素的第一个和最后一个位置
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。*/
//    public int[] searchRange(int[] nums, int target) {
//
//    }


    /*394.. 字符串解码
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注
    意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的
    输入。
    * */
    public String decodeString(String s) {
        int multi = 0;
        StringBuilder res = new StringBuilder();
        Stack<String> strStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c>='0'&&c<='9'){
                multi = multi*10+c-'0';
            }else if (c=='['){
                strStack.push(new String(res));
                intStack.push(multi);
                res = new StringBuilder();
                multi = 0;
            }else if (c==']'){
                Integer curVal = intStack.pop();
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < curVal; j++) {
                    sb.append(res);
                }
                res = new StringBuilder().append(strStack.pop()).append(sb);
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }


    /*90对称二叉树

     */

    /*39组合总和

     */


    /*104

     */


    /*470

     */



    /*64

     */

    /*144

     */

    /*110. 平衡二叉树
给定一个二叉树，判断它是否是 平衡二叉树 */
    public boolean isBalanced(TreeNode root) {
        if (root==null) return true;
        int left = getLength1(root.left);
        int right = getLength1(root.right);
        if (Math.abs(left-right)>1) return false;
        return isBalanced(root.left)&&isBalanced(root.right);
    }

    private int getLength1(TreeNode root) {
        if (root==null) return 0;
        int left = getLength1(root.left);
        int right = getLength1(root.right);
        return 1+Math.max(left,right);
    }

    /*122. 买卖股票的最佳时机 II
给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

返回 你能获得的 最大 利润 。*/
    public int maxProfit1(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i]>prices[i-1]){
                res += prices[i]-prices[i-1];
            }
        }
        return res;
    }



    /*48旋转图像
     */
    public void rotate(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
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


    /*128最长连续序列
    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num:nums) set.add(num);

        int res = 1;
        for (int num:set){
            if (!set.contains(num-1)){
                int len = 0;
                while (set.contains(num+len)) len++;
                res = Math.max(res,len);
            }
        }
        return res;
    }


    /*240 搜索二维矩
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int i = 0,j = n-1;
        while (i<m&&j>=0){
            int cur = matrix[i][j];
            if (cur>target){
                j--;
            }else if (cur<target){
                i++;
            }else {
                return true;
            }
        }
        return false;
    }


    /*221最大正方形
    在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     */
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i==0||j==0){
                    dp[i][j] = matrix[i][j]-'0';
                }else {
                    if (matrix[i][j]=='1'){
                        dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
                    }
                }
                res = Math.max(dp[i][j],res);
            }
        }
        return res*res;
    }


    /*234。回文链表
    给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(ListNode head) {
        ListNode mid = findMid2(head);
        ListNode head2 = reverse4(mid);
        while (head2!=null){
            if (head.val!=head2.val){
                return false;
            }
            head2 = head2.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverse4(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**“回文链表”题目中找中间节点的时候，就走常规的逻辑没问题，甚至前一半链表最后一个节点的next域不置null也没问题*/
    private ListNode findMid2(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /*98 验证二叉搜索树
        给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左子树只包含 严格小于 当前节点的数。
节点的右子树只包含 严格大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
     */
    public boolean isValidBST(TreeNode root) {
        if (root==null) return true;
        return isValidBST(root,null,null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if(root==null) return true;
        if (min!=null&&root.val<=min) return false;
        if (max!=null&&root.val>=max) return false;
        return isValidBST(root.left,min,root.val)&&
                isValidBST(root.right,root.val,max);
    }

    /*543.二叉树的直径
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
    int resDiameterOfBinaryTree = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs4(root);
        return resDiameterOfBinaryTree;
    }

    private int dfs4(TreeNode root) {
        if (root==null) return 0;
        int left = dfs4(root.left);
        int right = dfs4(root.right);
        resDiameterOfBinaryTree = Math.max(left+right,resDiameterOfBinaryTree);
        return Math.max(left,right)+1;
    }


    /*695. 岛屿的最大面积
给你一个大小为 m x n 的二进制矩阵 grid 。

岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

岛屿的面积是岛上值为 1 的单元格的数目。

计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     */
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1){
                    res  =Math.max(res,dfs5(grid,i,j));
                }
            }
        }
        return res;
    }

    private int dfs5(int[][] grid, int i, int j) {
        if (i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]!=1) return 0;
        grid[i][j] = 0;
        return 1+dfs5(grid,i,j-1) /**【注】记得+1，走到这里说明当前位置grid[i][j]是陆地，需要+1*/
                +dfs5(grid,i,j+1)
                +dfs5(grid,i-1,j)
                +dfs5(grid,i+1,j);
    }

    /**
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     */

    /*
    14. 最长公共前缀
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0) return "";
        String flag = strs[0];
        for (int i = 0; i < flag.length(); i++) {
            char c = flag.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length()<=i||strs[j].charAt(i)!=c){
                    return flag.substring(0,i);
                }
            }
        }
        return flag;
    }


    /*162. 寻找峰值
峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     */
    public int findPeakElement(int[] nums) {
        int left = 0,right = nums.length-1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]>nums[mid+1]){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }


    /*662. 二叉树最大宽度
给你一棵二叉树的根节点 root ，返回树的 最大宽度 。

树的 最大宽度 是所有层中最大的 宽度 。

每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。

题目数据保证答案将会在  32 位 带符号整数范围内。*/
    public int widthOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        LinkedList<Integer> number = new LinkedList<>();
        nodes.offer(root);
        number.offer(0);
        int first = 0;
        int last = 0;
        int res = 0;
        while (!nodes.isEmpty()){
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = nodes.poll();
                Integer curVal = number.poll();
                if (i==0) first = curVal;
                if (i==size-1) last = curVal;
                if (cur.left!=null){
                    nodes.offer(cur.left);
                    number.offer(curVal*2);
                }
                if (cur.right!=null){
                    nodes.offer(cur.right);
                    number.offer(curVal*2+1);
                }
            }
            res = Math.max(last-first+1,res);
        }
        return res;
    }


    /*113. 路径总和 II
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。*/
//    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//
//    }


    /*179. 最大数
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     */
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs,(a,b)->(b+a).compareTo(a+b));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (sb.length()==0&&"0".equals(strs[i])){
                continue;
            }
            sb.append(strs[i]);
        }
        return sb.length()==0?"0":sb.toString();
    }


    /*
    62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？
     */
    public int uniquePaths(int m, int n) {
        int res = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0||j==0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


    /*560. 和为 K 的子数组
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。
     */
    public int subarraySum(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            res += map.getOrDefault(preSum-k,0);
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return res;
    }


    /*198. 打家劫舍
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     */
    public int rob(int[] nums) {
        if (nums.length==1) return nums[0];
        int first = nums[0],second = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int curVal = Math.max(first+nums[i],second);
            first = second;
            second = curVal;
        }
        return second;
    }


    /*152. 乘积最大子数组
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个 32-位 整数。
     */
    public int maxProduct(int[] nums) {
        int preMin = nums[0],preMax = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax = Math.max(nums[i],Math.max(preMax*nums[i],preMin*nums[i]));
            int curMin = Math.min(nums[i],Math.min(preMax*nums[i],preMin*nums[i]));
            res = Math.max(res,curMax);
            preMin = curMin;
            preMax = curMax;
        }
        return res;
    }


    /*112. 路径总和
给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。

叶子节点 是指没有子节点的节点。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null) return false;
        if (root.left==null&&root.right==null&&targetSum==root.val) return true;
        return hasPathSum(root.left,targetSum-root.val)||
                hasPathSum(root.right,targetSum-root.val);
    }


    /*169. 多数元素
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    public int majorityElement(int[] nums) {
        int flag = 0,total = 0;
        for (int num:nums){
            if (total==0) flag = num;
            total += flag==num?1:-1;
        }
        return flag;
    }


    /*227. 基本计算器 II
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。

你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。

注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     */
//    public int calculate(String s) {
//
//    }



    /*83. 删除排序链表中的重复元素
给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode slow = head,fast = head.next;
        while (fast!=null){
            if (slow.val!=fast.val){
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    /*226. 翻转二叉树
给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left =  right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree_cengxu(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                swap4(cur);
                if (cur.left!=null) queue.offer(cur.left);
                if (cur.right!=null) queue.offer(cur.right);
            }
        }
        return root;
    }

    private void swap4(TreeNode cur) {
        TreeNode tmp  =cur.left;
        cur.left = cur.right;
        cur.right = tmp;
    }


    /*209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */
    public int minSubArrayLen(int target, int[] nums) {
        int res = nums.length+1;
        int sum = 0;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum>=target){
                res = Math.min(res,i-left+1);
                sum -= nums[left++];
            }
        }
        return res==nums.length+1?0:res;
    }


    /*139. 单词拆分
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    /*718. 最长重复子数组
给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     */
//    public int findLength(int[] nums1, int[] nums2) {
//
//    }


    /*24. 两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;

            node1.next = node2.next;
            node2.next = node1;
            cur.next = node2;

            cur = node1;
        }
        return dummy.next;
    }


    /*283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public void moveZeroes(int[] nums) {
        if (nums.length<=1) return;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i]!=0){
                swap5(nums,left++,i);
            }
        }
    }

    private void swap5(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }



    /*468. 验证IP地址
给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。

有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。

一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:

1 <= xi.length <= 4
xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
在 xi 中允许前导零。
例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
     */
//    public String validIPAddress(String queryIP) {
//
//    }


}
