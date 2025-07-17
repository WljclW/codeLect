package leecode_Debug._hot100;

import leecode_Debug.linkList.ListNode;

import java.util.List;

/**
 * 快排
 * 5. 最长回文子串
 * 143. 重排链表
 * 82. 删除排序链表中的重复元素 II
 * 93. 复原 IP 地址
 * 堆排序
 */
public class codetop_5 {
    /*93
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
//    public List<String> restoreIpAddresses(String s) {
//
//    }

    /*
    82
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
//    public ListNode deleteDuplicates(ListNode head) {
//
//    }

    /*124
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
//    public int maxPathSum(TreeNode root) {
//
//    }

    /*165
    给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。

比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。

返回规则如下：

如果 version1 < version2 返回 -1，
如果 version1 > version2 返回 1，
除此之外返回 0。
    * */
//    public int compareVersion(String version1, String version2) {
//
//    }

    /*105
    给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
    * */
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//
//    }


    /*129
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
每条从根节点到叶节点的路径都代表一个数字：

例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。

叶节点 是指没有子节点的节点。
    * */
//    public int sumNumbers(TreeNode root) {
//
//    }


    /*98
    给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左子树只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
    * */
//    public boolean isValidBST(TreeNode root) {
//
//    }


    //143
//    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
//
//    L0 → L1 → … → Ln - 1 → Ln
//    请将其重新排列后变为：
//
//    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
//    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    /**
     * 【详细的思路】
     *      找到链表中间节点（这里最好是找到中间的前面那个节点）、翻转链表、合并两个链表
     * */
    public void reorderList(ListNode head) {
        if (head==null||head.next==null) return;
        ListNode mid = findMid(head);
        ListNode head2 = reverse(mid);
        while (head2!=null){
            ListNode head2Next = head2.next;
            ListNode headNext = head.next;
            head.next = head2;
            head2.next = headNext;
            head = headNext;
            head2 = head2Next;
        }
        head.next = null;
    }

    private ListNode reverse(ListNode mid) {
        ListNode pre = null,cur = mid;
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
            fast  =fast.next.next;
        }
        return slow;
    }


    /*43
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    * */
//    public String multiply(String num1, String num2) {
//
//    }



    /*162
    峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
    * */
//    public int findPeakElement(int[] nums) {
//
//    }


    /*662
    给你一棵二叉树的根节点 root ，返回树的 最大宽度 。

树的 最大宽度 是所有层中最大的 宽度 。

每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。

题目数据保证答案将会在  32 位 带符号整数范围内。
    * */
//    public int widthOfBinaryTree(TreeNode root) {
//
//    }


    /*113
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。
    * */
//    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//
//    }


    /*209
    给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    * */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0,cur = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (cur<nums.length){
            /*step1：不管三七二十一，都先放进窗口*/
            sum += nums[cur];
            /*step2：满足条件时，不断的缩小窗口*/
            while (sum >= target) {
                res = Math.min(res, cur - left + 1);
                sum -= nums[left++];
            }
            cur++;
        }
        return res==Integer.MAX_VALUE?0:res;
    }

    public static void main(String[] args) {
        codetop_5 codetop_5 = new codetop_5();
        codetop_5.minSubArrayLen(7,new int[]{2,3,1,2,4,3});
    }


    /*718
    给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
    * */
//    public int findLength(int[] nums1, int[] nums2) {
//
//    }


    /*543
    给你一棵二叉树的根节点，返回该树的 直径 。

二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。

两节点之间路径的 长度 由它们之间边数表示。
    * */
//    public int diameterOfBinaryTree(TreeNode root) {
//
//    }
}
