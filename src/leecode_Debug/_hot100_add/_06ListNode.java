package leecode_Debug._hot100_add;

import leecode_Debug.top100.ListNode;

public class _06ListNode {
    /*
    * 203.
    * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满
    * 足 Node.val == val 的节点，并返回 新的头节点 。
    * */
    /**
     * 【关键】设置虚拟头节点。slow指针标志结果链表的最后一个元素，fast指针表
     * 示当前研究的节点。
     *      如果fast节点符合要求则串到slow后面，然后slow、fast都向后移动；
     *      如果fast节点不符合要求则fast继续往后，研究后面的节点
     * 【出错点】slow开始处于dummy节点（因为可能head节点就不满足），fast开
     * 始处于head节点（fast只是当前研究的是哪一个节点，虚拟头节点不用研究）。
     * 【补充】这里有更多的解法：https://programmercarl.com/0203.%E7%A7%BB%E9%99%A4%E9%93%BE%E8%A1%A8%E5%85%83%E7%B4%A0.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC
     * */
    public ListNode removeElements(ListNode head, int val) {
        if(head==null) return null;
        ListNode dummy = new ListNode(-1,head);
        ListNode slow = dummy,fast = head;
        while(fast!=null){
            if(fast.val==val){
                fast = fast.next;
            }else{
                slow.next = fast;
                slow = slow.next;
                fast  =fast.next;
            }
        }
        slow.next = null;
        return dummy.next;
    }
}
