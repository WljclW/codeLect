package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;

import java.util.PriorityQueue;

/**142、23（优先级队列解法）、19、、、
 * 没做过：148、23（分治解法）、24、92(反转中间的某一段链表)、*/
public class _06ListNode {

    public static void main(String[] args) {
        ListNode l5 = new ListNode(5, null);
        ListNode l4 = new ListNode(4, l5);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(3, l3);
        ListNode l1 = new ListNode(3, l2);
        _06ListNode thisClass = new _06ListNode();
        thisClass.reverseList(l1);
    }



    /*160.
    * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果
    * 两个链表不存在相交节点，返回 null 。保证不出现环*/
    /**为什么下面的写法是错误的？？？？？*/
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        if (headA==null||headB==null) return null;
//        ListNode ha = headA,hb = headB;
//        while(headA!=headB){
//            headA = headA.next;
//            headB = headB.next;
    /**直接移动headA和headBhi错误的！！！
     *    原因：比如这里指向headA空时（即第一个链表走到了末尾）如果重置为headB,此时的headB并不是第二个链
     * 表的头节点，因为headB已经移动了。。。最终会导致两个指针走的路程不一样————而这个解法的关键在于两个指
     * 针的总路长是一致的，因此最后返回headA或者headB都是可以的。*/
//            headA = (headA==null)?hb:headA;
//            headB = (headB==null)?ha:headB;
//        }
//        return headA;
//    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA,p2 = headB;
        while(p1!=p2){
            /*【注】由于headA和headB涉及到重新赋值，因此需要用两个备份指针p1,p2来遍历
            p1要是来到null，就指向HeadB;否则的话p1来到下一个；
            * p2要是来到null，就指向HeadA;否则的话p2来到下一个*/
            p1 = (p1==null)?headB:p1.next;
            p2 = (p2==null)?headA:p2.next;
        }
        return p1;
    }



    /*206.
    * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。*/
    /**总的思路是递归 和 迭代；迭代的空间复杂度更低*/
    /*方法1：cur和pre开始时指向head的前一个节点；next指向head节点。
    *       while的条件是next！=null，最后返回的是cur或者pre*/
    public ListNode reverseList(ListNode head) {
        if (head==null ||head.next==null) return head;
        ListNode pre = null,cur = pre,next = head;
        while(next!=null){
            cur = next;
            next = next.next; /*先将next指针更新,然后再该边cur的next域*/
            cur.next = pre;
            pre = cur;
        }
        return pre; /*每一次出while循环时pre和cur是一样的，因此返回哪一个都可以*/
    }

    /*方法2：开始时pre变量指向head的前一个节点，cur变量指向head节点；
            while的条件是cur！=null，返回的是pre.
         [说明]这种方法的pre跟方法1的pre是同样的角色，但是cur类似于方法1的next角色。因此
     方法1的while条件是next!=null，方法2的while条件是cur!=null
    * */
    public ListNode reverseList1(ListNode head) {
        if (head==null ||head.next==null) return head;
        ListNode pre = null,cur = head;
        while(cur!=null){
            ListNode next = cur.next; /*先将next指针更新,然后再该边cur的next域*/
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }



    /*234.
    * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返
    * 回 true ；否则，返回 false 。*/
//    public boolean isPalindrome(ListNode head) {
//
//    }


    /*141
    * 给你一个链表的头节点 head ，判断链表中是否有环。*/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast) return  true;
        }
        return false;
    }


    /*142.
    * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
    /**  【注】跟141类似，因为快指针每一次需要移动两步，java语句就是“fast=faast.next.next”.因此
     * while循环的条件是"fast!=null&&fast.next!=null"，保证在走两步的时候不会出现空指针异常*/
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head,fast=head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast){
                fast = head;
                while(fast!=slow){
                    fast=fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }


    /*21.
    * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
    /**[]：使用额外的头节点res，返回的时候返回res.next。*/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while (list1!=null&&list2!=null){
            if(list1.val< list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1==null) cur.next = list2;
        if(list2==null) cur.next = list1;
        return res.next;
    }

    /*2.
    * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode curLs = res; /*需要将结果链表的指针复制一份。一个指针不动代表返回值；两一个指针移动指向当前位置*/
        int carry = 0, cur = 0;
        while (l1 != null || l2 != null || carry != 0) { /**设置三个条件*/
            int l1Val = (l1 == null) ? 0 : l1.val;
            int l2Val = (l2 == null) ? 0 : l2.val;
            cur = l1Val + l2Val + carry; /*计算当前位置的总和值*/
            carry = cur / 10; //记录进位信息
            cur %= 10; //当前位置的数值
            curLs.next = new ListNode(cur); //给结果串 拼接数值为cur的节点
            curLs = curLs.next;
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return res.next;
    }


    /*19.
    * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    /**【解题关键】开始时slow指向虚拟头dummy节点；
     *            但是fast指向head 并且 fast先走n步
     * 【注】1. 这个题重要的是在删除的时候slow指针指向要删除节点的前一个节点。
     *      2. 一开始，要让fast指针先走N步*/
    /*自己的解法*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head,fast = head;
        while(fast!=null&&n>0){
            fast=fast.next;
            n--;
        }
        if(fast==null){
            return head.next;
        }
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }


    /**【建议采样这种】官方解，官方解的通用性更好。。
     *      这种写法下fast指针指向slow指针前面的第n+1个节点，因此当fast指针指向null
     *  的时候，slow指针指向要删除节点的前一个节点。举个例子：
     *      比如想删除倒数第二个节点，则fast指针指向null的时候，slow指针指向的是倒数
     *  第3个节点，想象一下*/
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        /**官方解中慢指针开始时指向了head节点的前一个；快指针指向head节点*/
        ListNode fast = head;
        ListNode slow = dummy;
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        ListNode res = dummy.next;
        return res;
    }


    /**24.
    * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下
    * 完成本题（即，只能进行节点交换）。*/
    /*非递归的形式；非递归形式看官方讲解*/
    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode node1 = cur.next; //原始的第一个节点
            ListNode node2 = cur.next.next; //原始的第二个节点
            /*以前是cur——>node1——>node2，下面的语句完成变成cur——>node2——>node1的形式*/
            cur.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            cur = node1;
        }
        return dummy.next;
    }


    /*递归形式看这个版本：https://leetcode.cn/problems/swap-nodes-in-pairs/solutions/2374872/tu-jie-die-dai-di-gui-yi-zhang-tu-miao-d-51ap/*/
    public ListNode swapPairs(ListNode head){
        if (head==null||head.next==null) return head;
        ListNode node1 = head;  //拆分出某一个部分的第一个节点
        ListNode node2 = head.next; //拆分出某一部分中的第二个节点
        ListNode node3 = node2.next; //拆分出剩下递归调用的头节点
        /*下面是完成指向关系的改变*/
        node1.next = swapPairs(node3); //①原来的第一个节点node1指向递归调用的返回
        node2.next = node1; //原来的第二个节点指向第一个节点
        return node2; //交换后的关系"node2——>node1——>以node3为头的递归调用返回"
    }


    /*25.
    * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
    k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。*/
//    public ListNode reverseKGroup(ListNode head, int k) {
//
//    }


    /*138*/
//    public Node copyRandomList(Node head) {
//    }

    /*148.
    * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
//    public ListNode sortList(ListNode head) {
//
//    }


    /*23.
    * 给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。*/
    /**【】：1. "优先级队列"的编码有两点需要注意。①添加节点时校验不是null(优先级队列添加null时会抛NullPointerException)；
     *      ②更新cur指针
     *      2. 需要熟悉一下"分治合并"————跟归并排序的思路是一样的，区别就是在merge的时候时两个链表的合并。
     *      关于"分治"的代码可以参考：https://leetcode.cn/problems/merge-k-sorted-lists/solutions/3787/leetcode-23-he-bing-kge-pai-xu-lian-biao-by-powcai/?envType=study-plan-v2&envId=top-100-liked
     * */
    public ListNode mergeKLists(ListNode[] lists) {
        /*优先级队列。空间复杂度O(K)，优先级队列需要存K个节点；
            时间复杂度O(kn*logk)，kn个节点，优先级队列k个节点，因此插入删除的时间复杂度logk。*/
        ListNode demmy = new ListNode(-1),cur = demmy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        for (ListNode node:lists){
            if(node!=null){ //①添加之前校验，保证不是空
                queue.add(node);
            }
        }
        while (!queue.isEmpty()){
            ListNode now = queue.poll();
            if (now.next!=null){
                queue.add(now.next);
            }
            cur.next = now; //将优先级队列弹出的节点拼到cur后面
            cur = cur.next; //然后更新cur指针
            now.next = null;
        }
        return demmy.next;
    }


    /*146.
    请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    实现 LRUCache 类：
    LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
    * */
//    class LRUCache {
//
//        public LRUCache(int capacity) {
//
//        }
//
//        public int get(int key) {
//
//        }
//
//        public void put(int key, int value) {
//
//        }
//    }



    /*92？？？
    * 官方解的最优方案不是很懂？？？*/
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p0 = dummy;
        /*p0最终会来到left的前一个节点*/
        for (int i=0;i<left-1;i++){
            p0 = p0.next;
        }
        /*中间的这部分就是反转链表的标准代码*/
        ListNode pre = null;
        ListNode cur = p0.next;
        for (int i=0;i<right-left+1;i++){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        /*下面的代码不是很懂*/
        p0.next.next = cur; /*cur其实就是反转链表后的下一个节点*/
        p0.next = pre; //pre就是反转链表部分的最后一个节点
        return dummy.next;
    }


    /*138题会用到，随即链表的复制*/
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
