package leecode_Debug.topcode_template_Locked;

import leecode_Debug.BTree.TreeNode;
import leecode_Debug.top100.ListNode;

import java.util.*;

/**
 * 1~2————215`、53、手撕快排、5、92`、1143、93`、143
 * 3——————151`、78、322、8
 * 4——————39`、470、122、
 * 5——————112、113`、179、718、手撕堆排序、14
 */
/**codetop 1~5的全量——————=========1~5使用这个文件
 * err：5、20、215、92、105、234、283、39、146、148、76、227`
 * undo：46的最优解、88、415、72题优化空间、1143空间优化的最优解、93、69、8、151、468`、718
 * 模糊：54
 *
 * 2025.10.13：
 *      _hot100._06ListNode#isPalindrome(leecode_Debug.top100.ListNode)这个题中的注释需要核对，两半链
 *  表的数量以及翻转的位置等相关注释有问题
 *      _hot100._06ListNode#isPalindrome(leecode_Debug.top100.ListNode)这个题上面的注释中也是错误的，尤
 *  其是翻转后半部分后两半链表的形态————根源：这是单链表，一个节点的后继必然是相同的节点
 *
 *  2025.10.28
 *      162，470表达式的理解，8. 字符串转换整数 (atoi)，69 求解平方根，82，33，912 快排，113，215，93，
 *   151，227`，468
 */
/*源文件————codetop_unskilled_1_5
    215、53、5、92、1143、151、78、322、8、39、470、112、718、14、手撕快排
* */
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
    912，手撕快排
     */
    public void quickSort(int[] arr,int left,int right) {
        if (left>=right) return; /**err：快排中这里的条件必须是“left>=right”或者“left>right”.如果仅仅有等号是不对的*/
        /**如果上面的if写成“if (left==right) return;”，912题提交时下面这行会报错————
         java.lang.IllegalArgumentException: bound must be greater than origin
         at line 236, java.base/jdk.internal.util.random.RandomSupport.checkRange
         at line 678, java.base/java.util.random.RandomGenerator.nextInt
         【原因】right-left可能得到-1，因此会导致“nextInt方法的第二参数是0，该方法要求 第二个参数必须大于第一个参数”
         */
        int pivotIndex = left + new Random().nextInt(0, right - left + 1);
        swap1(arr,pivotIndex,right);
        pivotIndex = partion1(arr,left,right);
        quickSort(arr,left,pivotIndex-1);
        quickSort(arr,pivotIndex+1,right);
    }

    private int partion1(int[] arr, int left, int right) {
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
        int tmp  =arr[pivotIndex];
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
    /**
     * 马拉车算法 可以实现将时间复杂度降为O(N)，但是空间复杂度高于“中心扩散法”，空间复杂度为O(N)。
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder("#");
        for (char c : s.toCharArray()) {
            sb.append(c).append("#");
        }
        String str = sb.toString();
        int n = str.length();

        int[] p = new int[n];
        int center = 0, right = 0;
        int start = 0, maxLen = 0;
        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;
            if (i < right) {
                p[i] = Math.min(right - i, p[mirror]);
            }

            int l = i - p[i] - 1, r = i + p[i] + 1;
            while (l >= 0 && r < n && str.charAt(l) == str.charAt(r)) {
                p[i]++;
                l--;
                r++;
            }
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
            if (p[i] > maxLen) {
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
     * 中心扩散法的复杂度分析：
     *      时间复杂度：O(N^2)；
     *      空间复杂度：O(1)
     *中心扩散法中最关键的有两点：
     *      第一点：双指针 + while循环计算回文长度返回，此时返回值是“(r-1)-(l+1)+1”即r-l-1。并且这个值就
     *  是真实的回文子串的长度，因为计算方式是边界索引值相减的，与“回文中心是（i，i）还是（i，i+1）”是没有关
     *  系的。
     *      第二点：更新回文子串的最大长度时，同时更新回文子串的起始位置，这个起始位置是关键————i-(len-1)/2。
     * @param s
     * @return
     */
    public String longestPalindrome_1(String s) {
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int odd = getLength(s, i, i);
            int even = getLength(s, i, i + 1);
            int curLen = Math.max(odd, even);
            if (curLen > maxLen) {
                maxLen = curLen;
                start = i - (curLen - 1) / 2; /**这里是如何理解的？？*/
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int getLength(String s, int l, int r) {
        while (l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        /**
         * 如何理解下面返回值的计算？？？
         *      根据while循环可知，只要“满足不越界 并且 字符相等”，就会执行while循环。。。。如果某次不再进
         *   入了，说明此时此刻此时l和r“要么越界了 要么对应的字符不相等”————即回文子串不包含r位置也不包含l位
         *   置。
         *      如果是之前滑动窗口、二分法等包括左右边界，此时计算长度的表达式就是“r-l+1”。
         *      但是这里是既不包含左边界，也不包含右边界，因此最后有效回文是闭区间 [l+1, r-1]，此时的长度
         *   是“(r-1)-(l+1)+1”即r-l-1
         */
        return r-l-1; /***这里是如何理解的？？*/
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
    /**
     *【关键】倒序合并————两个指针p1、p2，一个指着cur。。。p1、p2从nums1、nums2最后一个数倒着研究，将比
     *   较大的数放到nums1的cur位置(即末尾)
     *      原因：nums1中的数存在前面的m个位置，所以如果从开始合并的话，nums1中的数可能会被污染，此时就要
     *   借助额外的数组空间暂存结果。。。比如：nums2[0]<nums1[0] 并且 nums2[0]<nums1[0]，此时nums2
     *   最开始的两个数需要copy到nums1，那nums1原来的数应该存到哪里去？？综上，最好使用倒序合并
     *【注意】
     *      1. 由于是倒序合并，因此需要把“较大的那个数”先放入nums1！！
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1,p2 = n-1;
        int cur = m+n-1;
        while (p1>=0&&p2>=0){
            nums1[cur--] = nums1[p1]>nums2[p2]?nums1[p1--]:nums2[p2--]; /**err：谁大先放谁，因为是倒序合并*/
        }

        while (p2>=0){
            nums1[cur--] = nums2[p2--];
        }
    }

    /*如果写的清晰一些，就是下面的写法*/
    public void merge_1(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n - 1;

        /*step1：p1和p2倒序研究nums1和nums2的每一个数；将比较大的那个数放在nums1[cur]对应的位置*/
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[tail--] = nums1[p1--];
            } else {
                nums1[tail--] = nums2[p2--];
            }
        }

        /*step2：如果 nums2 还有剩余，直接拷贝到nums1。（说明：如果nums1还有剩余，不用管，因为他本来
               就在nums1中，并且是排序好的，因此不动直接结束就好）
         */
        while (p2 >= 0) {
            nums1[tail--] = nums2[p2--];
        }
    }

    //自己写的朴素版本
    public void merge_2(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) nums1[index--] = nums1[i--];
            else nums1[index--] = nums2[j--];
        }
        while (i >= 0) nums1[index--] = nums1[i--]; /**其实这一行可以省略，如果nums1没研究完，则剩下的数就在nums1，不用动！！*/
        while (j >= 0) nums1[index--] = nums2[j--];
    }


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
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode dummy = new ListNode(-1, head),slow = dummy,fast = dummy;
        for (int i = 0; i < left - 1; i++) {
            slow = slow.next;
        }
        for (int i = 0; i < right; i++) {
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
            /**
             *【每一段的完整逻辑说明】比如：打印某一行————
                if (top<=bottom){ //①先保证还有行/列，即边界符合条件
                    for (int i = left; i <=right; i++) { //②for循环列举每一个列位置i，位置i的范围是闭区间[left，right]
                        res.add(matrix[top][i]);
                    }
                }
             */
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
        //step1：base case的考虑
        if (head==null||head.next==null||head.next.next==null) return;
        /*step2：找到中间的后面（偶数节点的话）那个节点*/
        ListNode mid = findMid1(head);
        ListNode head2 = mid.next;
        mid.next = null; /**重点*/
        /*step3：翻转后半部分链表*/
        head2 = reverse2(head2);
        /*step4：合并"以head为头"和"以head2为头"的两半链表*/
        mergerTwo1(head,head2); /**易错*/
    }

    private void mergerTwo1(ListNode head, ListNode head2) {
        while (head2!=null){
            /**每一轮包含三个操作————
             ①记录两段链表当前位置的下一个节点；
             ②两段链表分别拿出一个拼接；
             ③移动两段链表的指针
             * */
            //①
            ListNode headNext = head.next;
            ListNode head2Next = head2.next;
            //②
            head.next = head2;
            head2.next = headNext;
            //③
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

    /*1. 此方法的目标：奇数节点的话返回中间节点，偶数节点的话返回中间的第二个节点。
      2. 与常规找中间节点方法的唯一区别：fast指针初始值不是head，而是"head.next"
    * */
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
     *【思路】两个字符串倒着相加，字符串是可以倒着取数相加的（从这个角度看，字符串的相加要比链表的相加简单）
     *【注】是字符串不是链表
     *【说明】跟”两数相加“其实代码是一样的。
     *      ”两数相加“题目中：
     *          什么时候结束循环？两个链表都来到最后 并且 进位信息carry是0
     *          位置如何更新？？由于这个题两个数本身就是倒序存在链表的（即数的高位在链表的末尾）。因
     *       此顺序相加，指针后移
     *      这个题中：
     *          什么时候结束？两个指针都无效 并且 carry也是0(carry==0说明最高位无进位了)
     *          位置如何更新？？由于两个字符串就是本来的数（即数的高位是在字符串的开始）。两数相加必须
     *       从地位开始依次相加，因此应该倒着依次相加，并将两个指针向前移动。
     */
    /*写法1：下面的写法中，最后更新i和j变量，可能会忘记，因此可以考虑使用写法2*/
    public String addStrings(String num1, String num2) {
        int len1 = num1.length(),len2 = num2.length();
        int i = len1-1,j = len2-1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (i>=0||j>=0||carry!=0){
            int val1 = i<0?0:num1.charAt(i)-'0';
            int val2 = j<0?0:num2.charAt(j)-'0';
            int curval = val1+val2+carry;
            res.append(curval%10);
            carry = curval/10;
        }
        return res.reverse().toString();
    }

    /*更好的写法。*/
    public String addStrings1(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int x = (i >= 0) ? num1.charAt(i--) - '0' : 0; /**把”取某位置的字符“和”更新索引位置“结合，避免忘记。。（前提是后续不会使用这个变量，比如：计算长度等，就需要及逆行矫正了）*/
            int y = (j >= 0) ? num2.charAt(j--) - '0' : 0;
            int sum = x + y + carry;
            sb.append(sum % 10);   // 当前位
            carry = sum / 10;      // 更新进位
        }

        return sb.reverse().toString();
    }


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
    /*二维dp的写法*/
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

    public int minDistance_1dim(String word1, String word2) {
        /*setep1：保证空间复杂度为O(min(m,n))————即把短的字符串放在二维表中列的位置*/
        int m = word1.length(), n = word2.length();
        if (m < n) {
            return minDistance_1dim(word2, word1);
        }
        /*step2：创建数组 并且 初始化。
            【说明】此时初始化相当于”二维dp“时初始化第一行的操作，即把word1当作是空串。
        * */
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = i; /**word1是空串的时候，word2的长度就是最小的操作次数*/
        }
        /*step3：遍历研究其余位置*/
        /**err：研究的过程中，更新任何一个dp[i]之前，都必须先记录原始的值，它在二维中的地位就是dp[i-1][j-1]*/
        for (int i = 1; i < m + 1; i++) {
            /**【注】此时的dp[0]并不单单是dp[0]，而是二维表中第i行的第0列————即取word1的前i个字符，word2的0个字符*/
            int prev = dp[0];
            dp[0] = i; /**更新dp某位置的值①：这里会更新dp某位置的值，因此更新前必须记录原来的值————即上一行干的事*/
            for (int j = 1; j < n + 1; j++) {
                int tmp = dp[j]; /**err：这里必须用一个新的变量来暂时存储dp值*/
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev; /**更新dp某位置的值②：这里会更新dp某位置的值，因此更新前必须记录原来的值————即if-else块之外所干的事*/
                } else {
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prev) + 1;
                }
                prev = tmp;
            }
        }
        return dp[n];
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
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=n; j++) {
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    /*空间优化到一维版本*/
    public int longestCommonSubsequence1(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (m<n){
            return longestCommonSubsequence1(text2,text1);
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i < m+1; i++) {
            int prev = 0;
            for (int j = 1; j < n+1; j++) {
                int tmp = dp[j];
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[j] = prev + 1;
                }else {
                    dp[j] = Math.max(dp[j-1],dp[j]);
                }
                prev = tmp;
            }
        }
        return dp[n];
    }



    /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
    "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过
    在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
    List<String> resRestoreIpAddresses;
    public List<String> restoreIpAddresses(String s) {
        resRestoreIpAddresses = new LinkedList<>();
        StringBuilder path = new StringBuilder(s);
        restoreIpAddresses(s,0,path,0);
        return resRestoreIpAddresses;
    }

    private void restoreIpAddresses(String s, int index, StringBuilder path,int pointNum) {
        if (pointNum==3&&isValidIP(s.substring(index))){
            resRestoreIpAddresses.add(new String(path));
            return;
        }
        if (pointNum>=3) return;
        for (int i = index+1; i <= s.length(); i++) {
            if (isValidIP(s.substring(index,i))){
                path.insert(i+pointNum,'.');
                restoreIpAddresses(s,i,path,pointNum+1);
                path.deleteCharAt(i+pointNum);
            }
        }
    }

    private boolean isValidIP(String substring) {
        if (substring.length()==1) return true;
        else if (substring.length()==2&&substring.charAt(0)!='0') return true;
        else if (substring.length()==3&&Integer.valueOf(substring)>=100&&Integer.valueOf(substring)<=255) return true;
        else return false;
    }


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
    /**
     *【思路】双指针，从左往右依次判断。
     *【关键】while循环条件的“||”暗含着两个版本号长度不一致的情况。出现版本号长度不一样的时候，版本号短的那一个
     *      后面的值就是0.
     * */
    /*建议的解法*/
    public int compareVersion(String version1, String version2) {
        int len1 = version1.length(),len2 = version2.length();
        int cur1 = 0,cur2 = 0;
        while (cur1 < len1 || cur2 < len2) { /**err：注意这里必须是“||”连接，多次错。。。跟“链表两数相加”类似，只要有一个还没到头就继续*/
            /*step1：分别初始化两个val1和val2，计算两个'.'之间的数；并且保证如果没有数也是初始值0*/
            int val1 = 0;
            for (; cur1 < len1 && version1.charAt(cur1) != '.'; cur1++) {
                val1 = val1 * 10 + version1.charAt(cur1) - '0';
            }
            cur1++; //跳过版本号之间的"."

            int val2 = 0;
            for (; cur2 < len2 && version2.charAt(cur2) != '.'; cur2++) {
                val2 = val2 * 10 + version2.charAt(cur2) - '0';
            }
            cur2++;

            /*step2：如果不相等就说明已经比较出两个的大小关系了*/
            if (val1 != val2) {
                return val1 > val2 ? 1 : -1;
            }
        }
        return 0;
    }

    /*写法2：分割字符串*/
    public int compareVersion2(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int n = Math.max(v1.length, v2.length);
        for (int i = 0; i < n; i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 != num2) {
                return num1 > num2 ? 1 : -1;
            }
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
        Stack<Integer> inStack; /**任何一个输入，都必须先进入到这个栈*/
        Stack<Integer> outStack; /**只能从这个栈中弹出*/
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
    /**
     【解法】实质就是二分查找“自身平方小于等于x的最大的整数”————因此等价于二分查找右边界问题。
     【关键问题】
            1. 0、1的特殊处理，平方根是它自身。
            2. 二分查找过程中————
                ①如果计算mid*mid，可能会发生int溢出的现象，因此如果计算mid*mid必须使用long来保存，此时需
              要使用这种写法“long square = (long) mid * mid”；
                ②可以使用“mid与x/mid比较大小”的方式来进行，就规避了这种风险。如果mid>x/mid，此时
              的mid必然偏大了，因此right=mid-1;反之，left=mid+1。
            综上，这个题的解法，建议使用 mySqrt_best
     */
    public int mySqrt(int x) {
        int left = 0,right = x; /**如果不优化的话，这里右边界应该是x，因为0和1的平方根就是本身，因此右边界要声明为x*/
        while (left<=right){
            int mid = left+(right-left)/2;
            /**err：mid*mid可能会发生整型溢出，需不需要考虑这个情况？？？需要验证！！！chatgpt给的优化建议：long square = (long) mid * mid;*/
            if ((long)mid*mid>x){ /**err：在要转换为long类型！！*/
                right = mid-1;
            }else if ((long)mid*mid<x){
                left = mid+1;
            }else {
                return mid;
            }
        }
        return right; /*由于现在的写法是求解”平方小于等于x的右边界“那个数，因此应该返回right*/
    }

    /**
     *【易错点】
     *      错误点1. mid*mid可能超出int的范围，因此必须要用long类型表示。需要使用“long curVal = (long)mid*mid”
     *      错误点2. 这个题的left左边界定为0不合适，应该定为1
     *【解的本质】查找”某数的平方“小于等于x的右边界，即”最大的 满足平方小于等于x“的数..
     *      基于这句话的本质，是不是同样也可以理解成”求解平方大于x的左边界“的那个数-1？？？（应该是可以的）
     *【优化】0和1的平方根的特殊考虑；右边界初始化为x/2。
     *     下面的方法采用的是记录值的形式，而不是返回指针；类似于”35求插入位置“的两种做法————
     *          做法1：除了while循环直接返回指针
     *          做法2：使用额外的变量来标记最近的一次符合要求的位置
     *     但是这个题和35题是有区别的，区别如下————
     *          35求解的是插入位置，本质上求的是”第一个大于等于“target的数所在的位置；
     *          这个题求解的是平方根，并且要向下取整。。。因此本质上求的是”最后一个满足
     *      自身平方小于等于x的数所在的位置“ 或者表述为 ”第一个满足自身平方大于x的位置-1“.
     *      详细的解释一下这两个表述，比如求解8的平方根，可选的数据为”0、1、2、3、4....“，
     *      ”第一个自身平方大于8的位置“是4对应3这个数，因此应该返回前一个位置的2；”最后一个
     *      自身平方小于等于8的位置“是3对应2这个数，因此返回的也是2.
     *     综上，两个题目是有区别的。这种区别直接导致两种写法的区别————
     *          35题返回边界的做法中应该返回left边界值; 记录最小值位置的做法中在大于等于的时候才
     *       记录；
     *          这个题返回边界的做法中应该返回right边界值; 记录最小值位置的做法中在小于的时候才
     *       记录位置(如果等于的时候就直接返回了)；
     *
     */
    public int mySqrt1(int x) {
        if (x < 2) return x; // 0 和 1 特殊处理，平方根是自己

        int left = 1, right = x / 2, ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            /**
                err：下面的语句必须使用“long square = (long) mid * mid;”，由于大数的平方可能导致超出int的范
             围，因此必须使用long类型。
                使用“long square =  mid * mid;”也是错误的！！！
             【⚠重要】
                1. 语句①：long curVal = (long)mid*mid、语句②：long curVal = mid*mid 的区别————
                    语句①：(long)mid会先把mid转换为long类型；然后再相乘；最后的结果是long类型；并且赋值给long类
                型的curVal（即不会出现越界现象）
                    语句②：mid*mid的结果化石int类型，如果超过MAX_VALUE就会溢出不会转换为long类型；最后仅仅是把
                结果赋值给一个long类型的变量curVal。
             */
            long square = (long) mid * mid; /**err：要先把一个mid转换为long类型再计算！！！！！！错了多次*/

            if (square == x) {
                return mid; //如果是等于的话直接返回
            } else if (square < x) {
                ans = mid;      /*记录上一次平方小于x的解，此时的mid可能是答案*/
                left = mid + 1; // 去右边找更大的
            } else {
                right = mid - 1; // 去左边找
            }
        }
        return ans;
    }

    /*下面是另外的一种写法。这种避免溢出的方式与“题目8. 字符串转换整数 (atoi)”有异曲同工之妙。
     【补充说明】
          如果left误初始化为0，则方法“mySqrt1”的代码依然没问题，但是下面的代码就不对，因为下面的代码中mid出现在分
      母的位置，因此mid变为0会出现问题————用例报错“java.lang.ArithmeticException: / by zero”
    * */
    public int mySqrt_best(int x) {
        if (x <= 1) return x;
        int left = 1, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) { /**这种写法避免了使用long，避免了mid*mid发生int溢出的风险*/
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }


    /*8. 字符串转换整数 (atoi)
    请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
    函数 myAtoi(string s) 的算法如下：
    空格：读入字符串并丢弃无用的前导空格（" "）
    符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
    转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
    舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
    返回整数作为最终结果。*/
    /**【重要的说明】————解析到第一个非数字的字符(!isDigit(ch))，就直接break返回，后面的就不用管了
     1. 解析数字时，对于不是数字的字符怎么处理？？
     ❗❗❗❗❗❗这个题的一个重要隐含条件：解析到第一个不是数字的位置就停止解析，返回当前解析到的结果。如果开始的位
     置就不是数字，直接返回0。同时，题目也明确的说明了最后得到的结果要在int范围内————
     如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。
     2. 判断是否越界的逻辑是重点。为什么下面的判断越界逻辑是对的？
     声明：最大正数：2147483647；最小负数：-2147483648
     ①例子1===》输入："-2147483649"。最后一位累加前，res = 214748364、当前 digit = 9、
     判断 (Integer.MAX_VALUE - 9)/10 = (2147483647-9)/10 = 214748363，
     res = 214748364 > 214748363 → 越界 → 返回 Integer.MIN_VALUE ✅
     ②例子2===》输入：“-2147483648”。最后一位累加前，res = 214748364、当前 digit = 8、
     判断 (Integer.MAX_VALUE - 8)/10 = (2147483647-8)/10 = 214748363，
     res = 214748364 > 214748363→ 越界 → 返回 Integer.MIN_VALUE ✅
     【补充】类似的道理可以参考69题的”mySqrt_best“方法实现，69题中的mid*mid可能超出int范围，因此直接计算不
     方便，但是比较”mid“和”x/mid“的大小可以避免这种溢出情况
     */
    public int myAtoi(String s) {
        int index = 0 /*index表示当前来到的位置*/, n = s.length();
        // 1. 跳过空格
        while (index < n && s.charAt(index) == ' ') index++;
        if (index == n) return 0;

        // 2. 判断符号
        int sign = 1;
        char c = s.charAt(index);
        if (c == '+' || c == '-') {
            sign = (c == '-') ? -1 : 1;
            index++;
        }

        // 3. 遍历数字
        int res = 0;
        while (index < n) {
            char ch = s.charAt(index);
            if (!Character.isDigit(ch)) break; /*如果index位置不是数字就停止解析，跳出while循环*/

            int digit = ch - '0';

            // 4. 溢出判断
            /**
             【注意】
                1. 如果没有提前计算digit，如下的写法：
                     if (res>(Integer.MAX_VALUE-c+'0')/10) return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
                注意表达式“Integer.MAX_VALUE-c+'0'”去掉括号要变号，因此不再是“c-‘0’”了
             */
            if (res > (Integer.MAX_VALUE - digit) / 10) { /**err：溢出判断是精髓*/
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit;
            index++; /**err：需要更新index变量，否则运行代码报错“超出时间限制”，没有任何一个通过的用例*/
        }

        return res * sign;
    }

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

    /**
     【易错点】
            1. 强烈建议将dp的所有值初始化为amount+1，不要初始化为-1.
            2. dp[0]必须是0，不然的话结果一直是-1.
     【关于dp数组初始化值的经验】
             题型	        初始化思路	    示例
             求最小值	    用大数	        dp[i] = amount + 1
             求最大值	    用小数	        dp[i] = -∞
             求组合/方案数	用 0	        dp[i] = 0
             用 -1 表示无效	必须手动判断	    见下面的解法coinChange_
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        /**
         *【不要填充为-1，虽然可以，但是要改很多逻辑】
         *      这里也可以用-1，填充数组。。此时代码需要改动，必须保证dp[j-coins[i]]不是-1才能取最小，否则取最
         * 小时-1会干扰。。。换言之我们确保金额“j-coins[i]”是能凑出来的，才会根据它决策
         *      否则“j-coins[i]”都凑不出来，“dp[j] = Math.min(dp[j-coins[i]]+1,dp[j]);”就没有意义，dp[i]
         * 的值只能保持原值不动
         */
        Arrays.fill(dp,amount+1);
        dp[0] = 0; /**err：0位置必须初始化0，否则永远返回-1*/
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <=amount; j++) {
                dp[j] = Math.min(dp[j-coins[i]]+1,dp[j]);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }

    /*chatgpt给出下面的答案，应该也是可以的*/
    public int coinChange_chatgpt(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int coin : coins) { /**这里不用使用for循环，反而更好。。。因为现在是一行数组，用不到二维中的i，因此第一个for循环不需要知道索引就可以*/
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /*dp数组初始时填充为-1的写法*/
    public int coinChange_(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                /**如果每一个位置初始是-1，在状态转移时每使用一个位置，要确保这个位置不是-1————即保证那个位置是可达的*/
                if (dp[i - coin] != -1) { // 表示可达
                    if (dp[i] == -1)
                        dp[i] = dp[i - coin] + 1;
                    else
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount];
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
        /**err：这里的条件判断不能缺失！！！等于的时候不用考虑，因为l==r的时候，根节点可以构造成
         * 功，但是构造左右子树时执行buildTree，由于”l>r“因此得到的都是null*/
        if (l>r) return null;  /**err：必须有这个终止条件，并且不能带等号~~*/
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
    /**
     *【注】题目要求是仅仅倒序单词的顺序，并不要求单词内字母的顺序
     *【最优解法的思路】从右向左一次遍历，用StringBuilder来收集结果
     */
    public String reverseWords(String s) {
        /*step1：去除字符串首尾的空格*/
        s = s.trim();
        /*step2：声明几个标志变量*/
        StringBuilder res = new StringBuilder();
        int i = s.length()-1,j = i;
        /*stp3：while循环依次研究每一个位置*/
        while (i>=0){
            /*①找到最后一个单词的开头。————只要索引合法 并且 i位置的字符不是空格，就继续向左。
              此步结束后，要么i不合法了，要么来到了空格！！
              【注】由于第一步就已经去除了首尾的空格，因此while循环倒着遍历的时候不可能一开始就是
           空格！！同时也意味这i<0即i不合法的时候，不可能i+1位置的字符不合法(i不合法时i+1是0，但
           是trim()已经去除了首尾的空格，因此0位置的字符必然是合法的字符)
             */
            while (i>=0&&s.charAt(i)!=' ') i--; /**step1：第一个while循环i会走过当前的最后一个单词，最终有两种可能：①i非法了；②i位置的字符是空格*/
            /*②将(i+1,j+1)的子串拼接到res。————
                    经过①可知i位置的字符是不合法的，并且j位置是合法的位置。因此子串的拼接应该
               是(i+1,j+1)。解释：
                    获取子串的方法是包括左边界不包括右边界的，因此“s.substring(i+1,j+1)”实际
               上获取的是[i+1，j]这些位置的字符。由于i位置不合法不能包括，j位置合法需要包括，所以
               表达式是正确的。
             */
            res.append(s.substring(i+1,j+1)).append(' '); /**step2：然后我们获取[i+1,j]这些字符拼接到结果，然后再拼接一个空格*/
            /*③经过①可知i位置是不合法的，从i位置开始向前找到第一个字符。————
                即只要i位置合法 并且 i位置的字符是空格，就继续向前，直到i位置的字符不是空格。----所
             以从这里可以知道i位置就是单词的最后一个位置，下一步将值给j。因此j就是下一个单词的最后一个
             位置。
             */
            while (i>=0&&s.charAt(i)==' ') i--; /**step3：step1之后i要么非法 要么i位置的字符是空格。step3就是让i跳过所有的空格。step3结束后i有两种可能：①索引i非法了；②i位置的字符不是空格*/
            j = i; /**step4：step3以后i要么非法 要么i位置的字符不是空格。因此用变量j记录下i位置，这个位置可能就是有效单词的最后一个位置。然后继续下一轮循环 如果i非法了循环就结束了~~*/
        }
        return res.toString().trim();
    }


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
        /**err：不加注释掉的这一句就可以，并不会发生StackOverflow！！但是如果加了这一句，则——————
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



    /*64

     */

    /*144

     */

    /*110. 平衡二叉树
    给定一个二叉树，判断它是否是 平衡二叉树 */
    /**
     【注意】其他的解法需要了解一下~~
     */
    public boolean isBalanced(leecode_Debug.top100.TreeNode root) {
        if (root==null) return true;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return isBalanced(root.left)&&
                isBalanced(root.right)&&
                Math.abs(left-right)<=1; /**【注意】高度之差的要求：小于等于1（等于1是包含的）*/
    }

    private int getDepth(leecode_Debug.top100.TreeNode root) {
        if (root==null) return 0;
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        return Math.max(l,r)+1;
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

    /*写法2*/
    /**
     最简化的写法，不断的增加能获得的利润
     */
    public int maxProfit2(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(0,prices[i]-prices[i-1]);
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
    /**
     【第一行 或者 第一列】 第一行或者第一列时1的位置就是1，否则时0；
     【矩阵中间的位置】 如果matrix的该位置时‘1’，则取决于左上角三个位置dp值的最小值；否则matrix该位置的值
     是0，则该位置的dp值也是0
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

    /**“回文链表”题目中找中间节点的时候，就走常规的逻辑没问题————即偶数时找到中间偏后的节点，甚至前一半链表最
           后一个节点的next域不置null也没问题(不置为null反而简化了流程的处理)。。。这里不像“排序链表”一样必
           须将前后两半链表拆开，因为“排序链表”中需要根据null来判断链表的结束，需要不断的拆链表每次规模减小为
           原来的一半*/
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

    /**解法2：中序遍历验证升序*/
    public boolean isValidBST1(TreeNode root) {
        {
            if (root == null) return true;
            Stack<TreeNode> queue = new Stack<>();
            Integer pre = null;
            while (root != null || !queue.isEmpty()) {
                if (root != null) {
                    queue.push(root);
                    root = root.left;
                } else {
                    TreeNode cur = queue.pop();
                    if (pre != null && cur.val <= pre) return false;
                    pre = cur.val;
                    root = cur.right;
                }
            }
            return true;
        }
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
        if (i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]!=1) return 0; /*若缺少判断条件，会发生StackOverFlow*/
        grid[i][j] = 0;
        return 1+dfs5(grid,i,j-1) /**【注】记得+1，走到这里说明当前位置grid[i][j]是陆地，需要+1*/
                +dfs5(grid,i,j+1)
                +dfs5(grid,i-1,j)
                +dfs5(grid,i+1,j);
        /**这个题不还原grid[i][j]的值是没问题的*/
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
    /**
     【复杂度分析】
     时间复杂度————O(n×m)
     其中:n = 字符串个数;m = '最短'字符串的长度（因为碰到最短的就一定知道答案了）
     空间复杂度————O(1)
     【强烈建议使用解法】longestCommonPrefix_
     */
    /*写法1*/
    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0) return "";
        /*step1：使用第一个字符串作为基准*/
        String str = strs[0];
        /*step2：依次拿出str每一个位置的字符，遍历所有的字符串看看对应的位置字符是不是相等；如果不相等立即返回*/
        for (int i = 0; i < str.length(); i++) { /*依次拿出第一个字符串0、1、2....（i位置）的字符*/
            char c = str.charAt(i);
            for (int j = 1; j < strs.length; j++) { /*从第二个字符串开始，依次研究每一个字符串位置i的字符，看看是不是等于c。如果发现不相等，立即返回*/
                if (i>=strs[j].length()||c!=strs[j].charAt(i)){
                    return str.substring(0,i);
                }
            }
        }
        /**
         *到了这里有两种情况————
         *      情况1：压根就没进入双重for循环，表示strs只有一个字符串，因此返回strs[0]；
         *      情况2：进入到双重for循环了，但是for循环完整执行结束。。。表示所有的字符串都研究了，strs[0]中有的
         *          字符其他的字符串对应都有，因此返回strs[0]。
         *      综上，虽然是两种情况，但是返回值是统一的。
         */
        return str;
    }

    /*写法2*/
    public String longestCommonPrefix_(String[] strs) {
        String flag = strs[0];
        for (int i = 0; i < flag.length(); i++) {  /*这个for循环表示从0开始研究str[0]的每一个位置*/
            for (int j = 1; j < strs.length; j++) { /*这个for循环表示：对于str[0]的每一个位置，研究剩下的所有字符串，看i位置的字符是不是相等*/
                String cur = strs[j];
                if (cur.length()==i || cur.charAt(i)!=flag.charAt(i)) /*但凡某一个位置的字符不相等 或者 i已经越界 ===》直接返回。此时i就是取子串的右边界*/
                    return flag.substring(0,i);
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
    /*最简化，且效率最高的写法*/
    /**
     易错点：为什么for循环内不能使用while循环？？比如nums = {1,0,2,0,0,0}.
            开始时left=0，i=0，执行while循环时发现“nums[i]!=0”，因此会进入while循环执行代
        码“swap5(nums,left++,i)”，由于i等于left，因此交换后其实相当于不交换因为是一个位置。。
        交换完成后left++所以left变为1，但是i的值依然是0。。。。。
            然后再回到while判断条件“ while (nums[i]!=0)”，由于交换后i的值没变依然h是0，并且由
        于原来i和left相等，因此相当于同一个位置交换，nums[0]的值依然是1，因此满足while条件，进入
        到循环执行“swap5(nums,left++,i);”......
     */
    /*错误的写法。。。*/
//    public void moveZeroes(int[] nums) {
//        if (nums.length<=1) return;
//        int left = 0;
//        for (int i = 0; i < nums.length; i++) {
//            while (nums[i]!=0){ /**err：会导致死循环。。。。。这里需要使用if，不能用while*/
//                swap5(nums,left++,i);
//            }
//        }
//    }

    /*写法1：使用for循环。*/
    public void moveZeroes(int[] nums) {
        if (nums.length<=1) return;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0){ /**err：这里需要使用if，不能用while*/
                swap5(nums,left++,i);
            }
        }
    }

    /*写法2：使用while循环，主方法可以写成下面的形式*/
    public void moveZeroes1(int[] nums) {
        if (nums.length<=1) return;
        int left = 0;
        int cur = 0;
        while (cur<nums.length){
            /*
                情况1：nums[cur]的值不是0，因此交换 并且 移动left和cur指针
                情况2：nums[cur]的值是0，则仅仅移动cur指针
             */
            if (nums[cur]!=0){
                swap5(nums,left++,cur++);
            }else {
                cur++;
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
    /**
     *什么是合法的IP地址？
     *     IPV4（三个条件缺一不可）：必须是4段；每一段不能有前导零；每一段不能超过255；
     *     IPV6：
     *【完整步骤】
     *
     */
    public String validIPAddress(String queryIP) {
        /*step1：先根据包括"."还是":"来决定校验IPV4还是IPV6*/
        if (queryIP.chars().filter(ch->ch=='.').count()==3){
            return isIPV4_1(queryIP)?"IPv4":"Neither";
        }
        if (queryIP.chars().filter(ch->ch==':').count()==7){
            return isIPV6(queryIP)?"IPv6":"Neither";
        }
        return "Neither";
    }

    private boolean isIPV6(String queryIP) {
        /**注意：这里分割的时候必须使用带参数-1的分割方法。否则"2001:0db8:85a3:0:0:8A2E:0370:7334:"这
        样的字符串分割后只有八部分，理论上应该是9个，最后一个是""。但是没有参数-1的话最后的""会被删除！！*/
        String[] split = queryIP.split(":", -1); /**err：参数-1是必须的*/
        if (split.length!=8) return false;
        String hexDigits = "0123456789ABCDEF";
        for (String part:split){
            if (part.length()==0||part.length()>4) return false;
            for (char c:part.toCharArray()){
                if (hexDigits.indexOf(c)==-1){
                    return false;
                }
            }
        }
        return true;
    }

    /**下面的写法是错误的。这个题不能用“Integer.valueOf”来转换字符串维整数。因为含有“3s4”这样的内容*/
    /*
    private boolean isIPV4(String queryIP) {
        String[] split = queryIP.split(".", -1);
        if (split.length!=4) return false;
        for (String part:split) {
            if (part.length()>3) return false;
            if (part.length()==0 || (part.length()>1&&part.charAt(0)=='0')) return false;
            Integer intVal = Integer.valueOf(part);
            if (intVal<0||intVal>255) return false;
        }
        return true;
    }
    */


    /*判断的流程————
        1. 按照”.“进行分割，如果parts不是4段，直接返回false；
        2. for循环研究每一段part：
           2.1 如果part的长度是0 或者 part的长度大于3，返回false；
           2.2 如果part的长度大于1，但是part的第一个位置是‘0’，返回false;
           2.3 使用for循环计算part的每一个字符：
               如果某一个字符不是数字，直接返回false;
               计算当前遇到的数val
               退出for循环说明数计算完了，如果这个数小于0 或者 这个数大于255，直接返回false。
           退出大的for循环说明：在part的数量合法的前提下，每一个part都通过了校验，返回true.
    * */
    private boolean isIPV4_1(String queryIP) {
        String[] parts = queryIP.split("\\.", -1);
        if (parts.length!=4) return false;
        for (String part:parts){
            if (part.length()==0 || part.length()>3) return false;
            if (part.length()>1 && part.charAt(0)=='0') return false;
            int val = 0;
            for (char c:part.toCharArray()){
                if (!Character.isDigit(c)) return false;
                val = val*10 + c-'0';
            }
            if (val<0 || val>255) return false;
        }
        return true;
    }


    /*判断的流程————
        1. 按照”:“进行分割，如果不是8段，直接返回false;
        2. for循环研究每一部分
           2.1 如果part的长度等于0 或者part的长度大于4===>false
           2.2 for循环判断part的每一个字符，使用三个布尔变量存储”是不是小写字母“、”是不是大写
         字母“、”是不是数字“。如果三个布尔变量都是false，返回false，说明该字母不合法。
        3. 退出for循环说明所有的part都是合法的，返回true。
     */
    private boolean isIPV6_1(String queryIP) {
        String[] parts = queryIP.split(":", -1);
        if (parts.length!=8) return false;
        for (String part:parts){
            if (part.length()==0 || part.length()>4) return false;
            for (char c:part.toCharArray()){
                boolean isLow = (c>='a'&&c<='e');
                boolean isBig = (c>='A'&&c<='E');
                boolean isDig = (c>='0'&&c<='9');
                if (!(isLow || isBig || isDig))
                    return false;
            }
        }
        return true;
    }


}
