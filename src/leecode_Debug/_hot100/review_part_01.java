package leecode_Debug._hot100;

import leecode_Debug.BTree.TreeNode;
import leecode_Debug.linkList.ListNode;

import java.util.HashMap;

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
}
