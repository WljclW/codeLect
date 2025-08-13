package leecode_Debug._hot100;

import leecode_Debug.All;
import leecode_Debug.BTree.TreeNode;
import leecode_Debug.linkList.ListNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author mini-zch
 * @date 2025/8/13 20:16
 */
public class review_part_01 {
    /*61
    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;
        int size = 1;
        ListNode cur = head;
        while (cur.next!=null){
            cur = cur.next;
            size++;
        }
        ListNode tail = cur;
        tail.next = head;

        k %= size;
        cur = head;
        for (int i = 0; i < k-1; i++) {
            cur = cur.next;
        }
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }


    /*148.
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode mid = findMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return mergeTwo(left,right);
    }

    private ListNode mergeTwo(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur  =cur.next;
        }
        return dummy.next;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head,fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }


    /*105.
     * 从前序 和 中序 构造出二叉树*/
    HashMap<Integer,Integer> inorderMap = new HashMap<>();
    int preOrderIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
        if (l>r) return null;
        int rootVal = preorder[preOrderIndex++];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.left = buildTree(preorder,inorder,l,index-1);
        root.right = buildTree(preorder,inorder,index+1,r);
        return root;
    }


    /*
    82
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            int val = cur.next.val;
            if (cur.next.next.val==val){
                while (cur.next.next!=null&&cur.next.next.val==val){
                    cur.next.next = cur.next.next.next;
                }
                cur.next = cur.next.next;
            }
            cur = cur.next; /**这里不能放在else块*/
        }
        return dummy.next;
    }


    /*
    143
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {
        ListNode midNext = findMidNext(head); /**这里我们实际上计算中间节点的后面一个*/
        ListNode head2 = rever(midNext);
        ListNode cur = head;
        while (head2!=null){
            ListNode next = head2.next;
            head2.next = cur.next;
            cur.next = head2.next;
            cur = cur.next.next;
            head2 = next;
        }
        return;
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

    private ListNode findMidNext(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }


    /*85
    给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     */
    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]!=0){
                    height[j]++;
                }else {
                    height[j]=0;
                }
            }
            res = Math.max(res,getArea(height));
        }
        return res;
    }

    private int getArea(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length + 1; i++) {
            int curHeight = (i==height.length)?0:height[i];
            while (!stack.isEmpty()&&height[i]<height[stack.peek()]){
                Integer cur = stack.pop();
                int curH = height[cur];
                int left = stack.isEmpty()?-1:stack.peek();
                int curVal = curH*(i-left-1);
                res = Math.max(res,curVal);
            }
            stack.push(i);
        }
        return res;
    }



        /*279.
    * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

       完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数
       * 自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。*/
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp,n);
        dp[0] = 0;
        for (int i = 1; i <=n; i++) {
            for (int j = 0; j*j <=i; j++) {
                dp[i] = dp[i-j] + 1;
            }
        }
        return dp[n];
    }


    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
    public int coinChange(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i=coins[0];i<=amount&&i%coins[0]==0;i++){
            dp[i] = i/coins[0];
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount+1; j++) {
                if (j>=coins[i]&&dp[j-coins[i]]!=Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j],dp[j-coins[i]]+1); /**因为题目求解的是组成amount的硬币数量，因此这里加的是1；否则的话这里应该加硬币的面值*/
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

    /*416.
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两
     * 个子集，使得两个子集的元素和相等。*/
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum&1)==1){
            return false;
        }
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]); /**这里实际上想要求的是sum容量的背包能放的物品总价值是多少，因此需要加的是nums[i]*/
            }
        }
        return dp[sum] == sum;
    }


}
