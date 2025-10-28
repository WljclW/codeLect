package topcodeReview;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * 这是一段测试环境的代码
 * 使用时复制一份！！！！！！
 * 10.27
 */
public class All1_5_template {
    /*215. 数组中的第K个最大元素
    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
//    public int findKthLargest(int[] nums, int k) {
//
//    }



    /*
    手撕快排
     */
    public void quickSort(int[] arr,int left,int right) {
        if (left>=right) return;
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
            /**
             * 这里是需要和最后一个数比较？？还是需要和第一个数比较？？？
             */
            if (nums[mid]>nums[right]){
                if (nums[0]<=target&&target<nums[mid]){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }else {
                if (nums[mid]<target&&target<=nums[right]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }


    /**
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     */
    /*
    143重排链表
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */

    /**
     * 这个题最后组装链表的时候绕进去了============
     * 这个题最后组装链表的时候绕进去了============
     * 这个题最后组装链表的时候绕进去了============
     * 这个题最后组装链表的时候绕进去了============
     */
    public void reorderList(ListNode head) {
        ListNode mid = findMid1(head);
        ListNode start = mid.next;
        mid.next = null;
        ListNode head2 = reverse3(start);

        ListNode cur2 = head2,cur1 = head;
        while (cur2!=null){
            ListNode next1 = cur1.next;
            ListNode next2 = cur2.next;
            cur1.next = cur2;
            cur2.next = next1;

            cur1 = next1;
            cur2 = next2;
        }
    }

    private ListNode reverse3(ListNode head) {
        ListNode pre =null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode findMid1(ListNode head) {
        ListNode slow = head,fast = head;
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
        if (lists==null) return null;
        if (lists.length==0) return lists[0];
        return mergeKLists(lists,0,lists.length-1);
    }

    private ListNode mergeKLists(ListNode[] lists, int left, int right) {
        /*测试一下改成下面的代码对吗
            if (left>right) return null;
            if (left==right) return lists[left];
         */
        if (left==right) return lists[left]; /**问一下chatgpt，为什么不能写成“if (left>right) return null;”*/
        int mid = left+(right-left)/2;
        ListNode leftNode = mergeKLists(lists, left, mid);
        ListNode rightNode = mergeKLists(lists, mid + 1, right);
        return mergeTwo1(leftNode,rightNode);
    }

    private ListNode mergeTwo1(ListNode left, ListNode right) {
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
            int val = cur.next.val;
            if (cur.next.next.val==val){
                while (cur.next.next!=null&&cur.next.next.val==val){
                    cur.next.next = cur.next.next.next;
                }
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        cur.next = null; /**没有这一句是不行的！！！！！！！！！测试一下*/
        return dummy.next;
    }
    /**
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     */
    /*148.排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode midPrev = findMidPrev(head);
        ListNode nextStart = midPrev.next;
        midPrev.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(nextStart);

        return mergeTwo11(left,right);
    }

    private ListNode mergeTwo11(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur =cur.next;
        }
        cur.next = left==null?right:left;
        return dummy.next;
    }

    private ListNode findMidPrev(ListNode head) {
        ListNode slow = head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*69. x 的平方根
给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。*/
    public int mySqrt(int x) {
        if (x<=1) return x;
        int left = 0,right = x/2;
        while (left<=right){
            int mid = left+(right-left)/2;
            long curVal = mid*mid;
            if (curVal>x){
                right = mid-1;
            }else if (curVal<x){
                left = mid+1;
            }else {
                return mid;
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
    public int myAtoi(String s) {
        int index = 0;
        while (index<s.length()&&s.charAt(index)==' '){
            index++;
        }
        if (index==s.length()) return 0;

        int sign  =1;
        if (s.charAt(index)=='-'||s.charAt(index)=='+'){
            sign = s.charAt(index)=='-'?-1:1;
            index++;
        }

        int res = 0;
        while (index<s.length()){
            char c = s.charAt(index);
            if (!Character.isDigit(c)) return res;
            if (res>(Integer.MAX_VALUE-c-'0')/10) return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            res = res*10 + c-'0';
        }
        return res;
    }


    /*322. 零钱兑换
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,1,amount,amount+1);
        for (int i = coins[0]; i <= amount; i++) {
            if (i%coins[0]==0) dp[i] = i/coins[0];
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j-coins[i]]!=amount+1){
                    dp[j] = Math.min(dp[j-coins[i]]+1,dp[j]);
                }
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }

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

    /*34. 在排序数组中查找元素的第一个和最后一个位置
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。*/
    public int[] searchRange(int[] nums, int target) {
        int left = findLeft(nums,target);
        if (left==nums.length||nums[left]!=target) return new int[]{-1,-1};
        int right = findRight(nums,target);
        return new int[]{left,right};
    }

    private int findRight(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]>target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return right;
    }

    private int findLeft(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if(nums[mid]<target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return left;
    }


    /*39组合总和
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

    candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

    对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */
    List<List<Integer>> resCombinationSum;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        resCombinationSum =new LinkedList<>();
        dfsCombinationSum(candidates,target,0,new LinkedList<Integer>());
        return resCombinationSum;
    }

    private void dfsCombinationSum(int[] candidates, int target, int index, LinkedList<Integer> path) {
        if (target==0){
            resCombinationSum.add(new LinkedList<>(path));
        }
        if (target<0 || index==candidates.length){
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfsCombinationSum(candidates,target-candidates[i],i+1,path);
            path.removeLast();
        }
    }



    /*470。用 Rand7() 实现 Rand10()
    给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。

你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。

每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
     */
//    public int rand10() {
//        while (true){
    /**如何更好的理解这个表达式？？？？？？*/
//            int val = (rand7()-1)*7+rand7();
//            if (val<=40){
//                return 1+val%10;
//            }
//        }
//    }
    /**
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     */
    /*162. 寻找峰值
峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     */

    /**
     * 下面的代码应该是错误的
     *      因为”nums[mid]>=nums[mid+1“的时候nums[mid]可能就是峰值；
     *      带等于、不带等于的区别？？？
     */
//    public int findPeakElement(int[] nums) {
//        int left = 0,right =  nums.length-1;
//        while (left<right){
//            int mid = left+(right-left)/2;
//            if (nums[mid]>=nums[mid+1]){
//                right = mid-1;
//            }else {
//                left = mid+1;
//            }
//        }
//        return left;
//    }

    public int findPeakElement(int[] nums) {
        int left = 0,right =  nums.length-1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]>=nums[mid+1]){ /**按照峰值的定义，这里最好不带等于。带等于对不对？？*/
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
        int res = 0;
        if (root==null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> integers = new LinkedList<>();
        queue.offer(root);
        integers.offer(0);
        int first = -1,last = -1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                Integer curVal = integers.poll();
                if (i==0) first = curVal;
                if (i==size-1) last = curVal;
                if (cur.left!=null){
                    queue.offer(cur.left);
                    integers.offer(2*curVal);
                }
                if (cur.right!=null){
                    queue.offer(cur.right);
                    integers.offer(2*curVal+1);
                }
            }
            res = Math.max(last-first+1,res);
        }
        return res;
    }


    /*113. 路径总和 II
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。*/
    List<List<Integer>> resPathSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        resPathSum  =new LinkedList<>();
        dfsPathSum(root,targetSum,new LinkedList<Integer>());
        return resPathSum;
    }

    private void dfsPathSum(TreeNode root, int targetSum, LinkedList<Integer> path) {
        if (root==null) return;
        if (root.left==null&&root.right==null&&targetSum==root.val) resPathSum.add(new LinkedList<>(path));
        path.add(root.val);
        dfsPathSum(root.left,targetSum-root.val,path);
        path.removeLast();
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

    /*283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public void moveZeroes(int[] nums) {
        int left = 0,cur = 0;
        while (cur<nums.length){
            if (nums[cur]!=0){
                swap2(nums,left++,cur++);
            }else {
                cur++;
            }
        }
    }

    private void swap2(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r]  =tmp;
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
