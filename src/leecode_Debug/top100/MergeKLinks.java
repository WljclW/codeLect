package leecode_Debug.top100;


import java.util.LinkedList;
import java.util.PriorityQueue;



public class MergeKLinks {

    /**
     * 合并两个升序链表的方法
     * */
    public static ListNode merge(ListNode list1,ListNode list2){
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        ListNode curr = new ListNode(0);
        ListNode res = curr;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                curr.next = list1;
                list1 = list1.next;
            }else{
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 == null?list2:list1;
        return res.next;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        /**
         * 方法一：不断的两两合并，直至结束
         * */
        //第一部分写出合并两个链表的方法

        //第二部分，k个链表两两合并
//        if(lists.length == 0)
//            return null;
//        if(lists.length == 1)
//            return lists[0];
//        ListNode list1 = Solution.merge(lists[0],lists[1]);
//        if(lists.length == 2)
//            return list1;
//        for(int i=2;i<lists.length;i++){
//            list1 = Solution.merge(list1,lists[i]);
//        }
//        return list1;




        /**
         * 方法2：合并链表的实质是每一次找到最小的一个节点连接到结果链的后面。。如何快速选择最小的？？小根堆
         * */
        if(lists.length==0)
            return null;
        ListNode res = new ListNode(-1);
        ListNode res1 = res;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length,(a,b)->(a.val-b.val));
        for (ListNode head:lists){
            if(head!=null)
                pq.add(head);
        }
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            res.next = node;
            res = node;
            if (node.next!=null)
                pq.add(node.next);
        }
        return res1.next;
    }
}