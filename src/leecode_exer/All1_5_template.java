package leecode_exer;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 *
 * 2025.12.21
 * 使用时复制一份！！！！！！
 */
public class All1_5_template {
        /*146.LRU 缓存
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
            public DouNode(){}
            public DouNode(int key,int value){
                this.key = key;
                this.value = value;
            }
        }

        int capacity;
        int size;
        DouNode head;
        DouNode tail;
        HashMap<Integer,DouNode> map;
        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head = new DouNode();
            tail = new DouNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DouNode node = map.get(key);
            if (node!=null){
                moveToHead(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            DouNode node = map.get(key);
            if (node!=null){
                moveToHead(node);
                node.value = value;
            }else {
                DouNode newNode = new DouNode(key, value);
                addToHead(newNode);
                size++;
                map.put(key,newNode);
                if (size>capacity){
                    DouNode relTail = tail.prev;
                    removeNode(relTail);
                    map.remove(relTail.value);
                    size--;
                }
            }
        }

        private void addToHead(DouNode node) {
            node.next = head.next;
            head.next = node;
            node.next.prev = node;
            node.prev = head;
        }

        private void removeNode(DouNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DouNode node) {
            removeNode(node);
            addToHead(node);
        }
    }



    /*215. 数组中的第K个最大元素
    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,nums.length-k);
    }

    private int findKthLargest(int[] nums, int left, int right, int index) {
        if (left==right) return nums[left];
        int pivotIndex = left + new Random().nextInt(0,right-left+1);
        int pivot = nums[pivotIndex];
        int[] cur = partion2(nums,left,right,pivot);
        if (cur[0]>index){
            return findKthLargest(nums,left,cur[0]-1,index);
        } else if (cur[1] <= index) {
            return pivot;
        }else {
            return findKthLargest(nums,cur[1]+1,right,index);
        }
    }

    private int[] partion2(int[] nums, int left, int right, int pivot) {
        int cur = left;
        int now = left;
        while (now<=right){
            if (nums[now]<pivot){
                swap2(nums,cur++,now++);
            } else if (nums[now] == pivot) {
                now++;
            }else {
                swap2(nums,now,right--);
            }
        }
        /**由于”pivot本身就是从nums中选出来的“，因此这里必然能找到，最差的情况也能找到一个*/
        return new int[]{cur,right};
    }

    private void swap2(int[] nums, int left, int right) {
        int tmp  =nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }



    /*
    手撕快排
     */
    /**
     * 下面的解法部分用例会超时-------
            全是2.
     */
    public void quickSort(int[] arr,int left,int right) {
        if (left>=right) return;

        int pivotIndex = left + new Random().nextInt(0, right - left + 1);
        swap1(arr,pivotIndex,right);
        //partion1 的返回值就是 选出的arr[pivotIndex]应该放在的位置
        pivotIndex = partion1(arr,left,right);
        quickSort(arr,left,pivotIndex-1);
        quickSort(arr,pivotIndex+1,right);
    }

    private int partion1(int[] arr, int left, int right) {
        int cur = left;
        for (int i = left; i < right; i++) {
            if (arr[i]<=arr[right]){
                swap1(arr,cur++,i);
            }
        }
        swap1(arr,cur,right);
        return cur;
    }

    private void swap1(int[] arr, int left, int right) {
        int tmp  = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
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
        ListNode dummy = new ListNode(-1, head);
        ListNode lNode = dummy,rNode = dummy;
        for (int i = 0; i < left - 1; i++) {
            lNode= lNode.next;
        }
        for (int i = 0; i < right; i++) {
            rNode=rNode.next;
        }
        ListNode start = lNode.next;
        ListNode next = rNode.next;
        lNode.next = rever2(start);
        start.next = next;
        return dummy.next;
    }

    private ListNode rever2(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /*
    143. 重排链表
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    /**重点校验一下下面的方法*/
    public void reorderList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return;
        ListNode mid = findMid3(head);
        ListNode head2 = rever5(mid.next);
        mid.next = null;

        ListNode odd = head,even = head2;
        while (even!=null&&even.next!=null){
            ListNode oddNext = odd.next;
            ListNode evenNext = even.next;
            odd.next = even;
            even.next = oddNext;
            oddNext.next = evenNext;

            odd = oddNext.next.next;
            even = evenNext.next.next;
        }
    }

    private ListNode rever5(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid3(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }



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
        if (head==null||head.next==null) return head;
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            int val = cur.next.val;
            if (cur.next.next!=null&&cur.next.next.val==val){ //说明 前面两个节点的值相等，则进入 if，跳过所有值相等的节点
                while (cur.next.next!=null&&cur.next.next.val==val){
                    cur.next.next = cur.next.next.next;
                }
                cur.next = cur.next.next;
            }else { //说明 cur.next节点没有重复的值，把这个cur节点添加到结果集中，即拼接到dummy后面————因此cur指向的是结果链条中的最后一个节点（cur指向的节点是有效的，出了初始时的dummy）
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     */
    /*151. 反转字符串中的单词
给你一个字符串 s ，请你反转字符串中 单词 的顺序。

单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。

注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。*/
//    public String reverseWords(String s) {
//
//    }

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
    /**DFS的写法如何？？？？*/
    public int sumNumbers(TreeNode root) {
        if (root==null) return 0;
        int res = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> vals = new LinkedList<>();
        queue.offer(root);
        vals.offer(root.val);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            Integer curVal = vals.poll();
            if (cur.left==null&&cur.right==null) res += curVal;
            if (cur.left!=null){
                queue.offer(cur.left);
                vals.offer(curVal*10+cur.left.val);
            }
            if (cur.right!=null){
                queue.offer(cur.right);
                vals.offer(curVal*10+cur.right.val);
            }
        }
        return res;
    }




    /**
     * =====================================其他写法的一些补充==========================================
     * =====================================其他写法的一些补充==========================================
     * =====================================其他写法的一些补充==========================================
     * =====================================其他写法的一些补充==========================================
     * =====================================其他写法的一些补充==========================================
     */
    /*101对称二叉树
    给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    /**迭代的写法 补充？？*/
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        if (root.left==null&&root.right==null) return true;
        if (root.left==null||root.right==null) return false;

        LinkedList<TreeNode> queueLeft = new LinkedList<>();
        LinkedList<TreeNode> queueRight = new LinkedList<>();
        queueLeft.offer(root.left);
        queueRight.offer(root.right);
        while (!queueLeft.isEmpty()){
            TreeNode curLeft = queueLeft.poll();
            TreeNode curRight = queueRight.poll();
            if (curLeft.val!=curRight.val) return false;
            if (curLeft.left!=null){
                queueLeft.offer(curLeft.left);
                if (curRight.right==null) return false;
                queueRight.offer(curRight.right);
            }else if (curRight.right!=null){
                return false;
            }

            if (curLeft.right!=null){
                queueLeft.offer(curLeft.right);
                if (curRight.left==null) return false;
                queueRight.offer(curRight.left);
            } else if (curRight.left != null) {
                return false;
            }
        }
        return true;
    }


    /*110. 平衡二叉树
给定一个二叉树，判断它是否是 平衡二叉树。。。平衡二叉树 是指该树所有节点的左右子树的高度相差不超过 1。 */
    /**非递归怎么写？？？*/

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
        if (strs.length==1) return strs[0];
        String flag = strs[0];

        for (int i = 0; i < flag.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length()<=i||strs[j].charAt(i)!=flag.charAt(i))
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
    /**下面是Bfs的写法。。。。。。。。DFS应该不好写？DFS怎么写？？*/
    public int widthOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> vals = new LinkedList<>();
        int res = 0;
        queue.offer(root);
        vals.offer(0);
        while (!queue.isEmpty()){
            int size = queue.size();
            int fir = 0,last = 0;
            for (int i = 0; i <size; i++) {
                TreeNode cur = queue.poll();
                Integer curVal = vals.poll();
                if (i==0) fir= curVal;
                if (i==size-1) last = curVal;
                if (cur.left!=null){
                    queue.offer(cur.left);
                    vals.offer(curVal*2);
                }
                if (cur.right!=null){
                    queue.offer(cur.right);
                    vals.offer(curVal*2+1);
                }
            }
            res = Math.max(res,last-fir+1);
        }
        return res;
    }


    /*113. 路径总和 II
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。*/
    /**下面的题 不熟练~~~*/
    List<List<Integer>> resPathSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        resPathSum = new LinkedList<>();
        if (root==null) return resPathSum;
        LinkedList<Integer> path = new LinkedList<>();
        dfs5(root,targetSum,path);
        return resPathSum;
    }

    private void dfs5(TreeNode root, int targetSum, LinkedList<Integer> path) {
        if (root==null) return;
        path.add(root.val);
        if (root.left==null&&root.right==null&&targetSum==root.val){
            resPathSum.add(new LinkedList<>(path));
        }
        dfs5(root.left,targetSum-root.val,path);
        dfs5(root.right,targetSum-root.val,path);
        path.removeLast();
    }


    /*179. 最大数
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     */
    /**不是很熟练*/
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strings,(a,b)->{return (b+a).compareTo(a+b);});
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (sb.length()==0&&"0".equals(strings[i])) continue;
            sb.append(strings[i]);
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
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for (int i = 1; i < m; i++) {
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j-1]+dp[j];
            }
        }
        return dp[n-1];
    }


    /*560. 和为 K 的子数组
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。
     */
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0,res =0;
        for (int num:nums){
            preSum += num;
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
        if (nums.length==2) return Math.max(nums[0],nums[1]);
        int fir = nums[0],sec = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(fir+nums[i],sec);
            fir = sec;
            sec = cur;
        }
        return sec;
    }


    /*152. 乘积最大子数组
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个 32-位 整数。
     */
    public int maxProduct(int[] nums) {
        int preMin = nums[0],preMax = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(Math.min(preMin*nums[i],preMax*nums[i]),nums[i]);
            int curMax = Math.max(Math.max(preMin*nums[i],preMax*nums[i]),nums[i]);
            preMin = curMin;
            preMax = curMax;
            res = Math.max(res,preMax);
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

    public boolean hasPathSum_(TreeNode root, int targetSum) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> vals = new LinkedList<>();
        if (root==null) return false;
        queue.offer(root);
        vals.offer(targetSum-root.val);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            Integer curval = vals.poll();
            if (cur.left==null&&cur.right==null&&curval==0) return true;
            if (cur.left!=null){
                queue.offer(cur.left);
                vals.offer(curval-cur.left.val);
            }
            if (cur.right!=null){
                queue.offer(cur.right);
                vals.offer(curval-cur.right.val);
            }
        }
        return false;
    }


    /*169. 多数元素
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    public int majorityElement(int[] nums) {
        int ticket = 0,flag = 0;
        for (int num:nums){
            if (ticket==0) flag=num;
            ticket += flag==num?1:-1;
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
            if (fast.val==slow.val)
                fast = fast.next;
            else{
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }
        }
        return head;
    }


    /**可以简化成下面的写法*/
    public ListNode deleteDuplicates1_(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode slow = head,fast = head.next;
        while (fast!=null){
            if (slow.val!=fast.val){
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        return head;
    }

    /*226. 翻转二叉树
给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return root;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree_cengxu(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            swap6(cur);
            if (cur.left!=null) queue.offer(cur.left);
            if (cur.right!=null) queue.offer(cur.right);
        }
        return root;
    }

    private void swap6(TreeNode cur) {
        TreeNode tmp = cur.left;
        cur.left = cur.right;
        cur.right  =tmp;
    }


    /*209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int curSum = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            while (curSum>=target){
                res = Math.min(res,i-left+1);
                curSum -= nums[left++];
            }
        }
        return res;
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
                }
            }
        }
        return dp[s.length()];
    }


    /*718. 最长重复子数组
给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length,n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i-1]==nums2[j-1])
                    dp[i][j]  =dp[i-1][j-1]+1;
                res = Math.max(res,dp[i][j]);
            }
        }
        return res;
    }

    /**重点看一下 下面的解法有没问题！！
     */
    public int findLength_(int[] nums1, int[] nums2) {
        int m = nums1.length,n = nums2.length;
        if (m<n)
            return findLength(nums2,nums1);

        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            dp[0] = 0;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if (nums1[i-1]==nums2[j-1])
                    dp[j] = prev+1;
                prev = tmp;
            }
        }
        return dp[n];
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
            cur.next = node2;
            node2.next = node1;

            cur = node1;
        }
        return dummy.next;
    }


    /*283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public void moveZeroes(int[] nums) {
        int left = 0,cur = 0;
        while (cur<nums.length){
            if (nums[cur]!=0){
                swap5(nums,left++,cur++);
            }else {
                cur++;
            }
        }
    }

    private void swap5(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    public void moveZeroes_(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0){
                swap5(nums,left++,i);
            }
        }
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
