package leecode_Debug._hot100;

import leecode_Debug._hot100_NeedMore.NeedMore01;
import leecode_Debug.top100.ListNode;

import java.util.HashMap;
import java.util.PriorityQueue;

/**142、23（优先级队列解法）、19、、、
 * 没做过：148、23（分治解法）、24、92(反转中间的某一段链表)*/

/**
 *【链表的常见问题】
 *      1. “超出时间限制”、“超出内存限制”类似的报错通常是因为：循环操作的时候，忘记了"cur=cur.next"类似的代
 *  码，导致cur指针一直未动，因此情况如下————
 *          （1）如果是计算任务，会长时间得不到结果，这种情况是最多的；
 *          （2）如果涉及到创建节点，每一次操作都会创建节点，就会导致节点无限的被创建，因此最终在idea中应该会
 *      报"StackOverflow"；在力扣的网页展示的是“超出内存限制”。
 *【技巧1】找中间节点。
 *     1. 找到常规的中间节点，即常规的方法就可以。
 *          场景：适用于不需要将链表前后两半断开连接的时候。
 *          比如：234
 *     2. 找到链表中间的前一个结点，即：常规的方法使用时"fast"指针的初始值需要变为"head.next"，其他不变。
 *          场景：适用于需要将链表前后两半断开连接
 *          比如：排序链表、重排链表
 *          具体的代码如下：
                private ListNode findMid(ListNode head) {
                     ListNode slow = head,fast = head.next; //fast的初始值是head.next
                     while (fast!=null && fast.next!=null){
                     slow = slow.next;
                     fast = fast.next.next;
                     }
                     //如果是偶数个节点，代码执行到这里slow会指向中间两个节点的第一个
                     ListNode res = slow.next;
                    //将原始链表的前后两部分断开（如果偶数节点则断开后两部分节点数相等，如果是奇数节点则前一半多一个）
                     slow.next = null;
                     return res;
                }
 */
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
            p2要是来到null，就指向HeadA;否则的话p2来到下一个*/
            p1 = (p1==null)?headB:p1.next; /**err："p1==null"不能替换成p1.next==null，否则会出现“超出时间限制”*/
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

    //第三种写法。
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null,cur=head,next = head;
        while(cur!=null){
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }



    /*234.
    * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返
    * 回 true ；否则，返回 false 。*/
    /**
     * 【思路】
     *      1. 正常找出链表的 中点（1，4，7会来到4） 或者 中间的后一个节点（1，4，6，7会
     *  来到6）。。。此时后半部分的节点数必然少于前半部分的节点数（根本原因是前一半链表并没有
     *  和后一半断开连接）。其实此时的链表是两个
     *      ①如果是奇数节点：1——>4——>7——>null；7——>4——>null.此时的循环终止条件head2!=null
     *      ②如果是偶数节点：1——>4———>6————>7———>null；7——>6——>null.此时循环的终止条件head2！=null
     *      综上，奇数偶数节点的判断终止条件都是head2!=null即可。并且从中间节点 或者 中间节点
     *  的后一个进行翻转（也就是说，使用常规的翻转链表代码就可以。。根"排序链表"是有很大区别的）
     *      2. 反转后半部分，拿到反转后的头结点head2。————注意是翻转链表的后半部分而不是全部翻转
     *      3. 因为前半链表没有断开连接，所以后半部分的节点数少，因此“head2!=null”时挨个判
     * 断head.val和head.val是不是相等
     * 【补充说明】这道题“找中间节点”的做法 和 排序链表中“找中间节点”的做法是有区别的————
     *      ①这道题找中间节点使用普通的思路，就够了。因为最后在挨个比较节点值的时候，后一半链表的节点会先来到
     * null，此时就知道结果了。
     *      ②但是排序链表使用普通的思路，就不对。因为最后需要将排完序的两半合并为一个链表，如果前一半链表的最
     * 后节点next不指向null，就会导致节点重复。所以“排序链表”题目中，实际上是要先找出①方案中的前一个节点，
     * 再记录该节点的next节点（这是要返回的节点res，其实还是①的返回值），最后将该节点的next置为null，并返回
     * 记录的next节点，即res。
     *      综上，其实最本质的区别在于，判断“回文链表”的题目中，找到重点后会翻转后一半链表（并且翻转链表的时候pre
     * 是null，也就意味着后一半链表必然是以null为结束节点的），因此head2==null就是结束条件。。前一半链表即使没有
     * 断开连接没关系，因为使用第二半链表就会结束判断————没毛病。。。
     * 但是“排序链表”题目中，如果不人为的将前一半链表的最后一个节点设置为null，最后归并排序完合并就会导致后一半会被
     * 重复添加。
     * */
    public boolean isPalindrome(ListNode head) {
        /*step1：slow来到中间（奇数个节点）或者 中间的第二个（偶数个节点）节点。。
        *     根据这个位置可以知道，以slow为头的后半部分的节点数量必然不少于第一部分的节点数量。。但是前一半链表的
        * 最后节点next并不是null————即不像"排序链表"那样断开连接，因此使用head2!=null就是可以的，并且也必须这样判
        * 断，不能用"head!=null"作为结束条件，因此这种情况下以head为头的链表仍然是整个链表
        * */
        ListNode slow = findMiddle(head);
        /*step2：head2是后半部分反转后的头结点*/
        ListNode head2 = reverseIsPalindrome(slow);
        while(head2!=null){
            if (head2.val!= head.val){ //挨个比较值，不相等时直接返回false
                return false;
            }
            head2 = head2.next;  /*后半部分的指针移动*/
            head = head.next;    /*前半部分的指针移动*/
        }
        return true;
    }

    /*step1：slow来到中间（奇数个节点）或者 中间的第二个（偶数个节点）节点。。
     *     根据这个位置可以知道，以slow为头的后半部分的节点数量必然不大于第一部分的节点数量。。但是slow之
     * 前的节点next指针并不会变化。因此下面的while循环判断的时候使用“head2!=null”来判断后半部分的链表是不
     * 是判断结束了*/
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head,fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*完整的反转链表代码————一模一样*/
    private ListNode reverseIsPalindrome(ListNode slow) {
        ListNode pre = null,cur = slow;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


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
            /*step1：移动两个指针*/
            slow = slow.next;
            fast = fast.next.next;
            /*step2：说明链表有环，if块计算第一个入环节点*/
            if (slow==fast){
                fast = head;
                while(fast!=slow){
                    fast=fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null; //链表节点中有null，说明是没有环的
    }


    /*21.
    * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
    /**[]：使用额外的头节点res，返回的时候返回res.next。*/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;


        while (list1!=null&&list2!=null){
            //拼接val小的节点，然后对应的链表指针后移
            if(list1.val< list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                list2 = list2.next;
            }
            //结果链表指针后移
            cur = cur.next;
        }

        /*step1：拼接还有节点的那个链表*/
        if (list1==null) cur.next = list2;
        if(list2==null) cur.next = list1;
        return res.next;
    }

    /*2.
    * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1),cur = res; /*需要将结果链表的指针复制一份。其中一个指针res不动代表返回值；一个指针cur移动指向当前位置*/
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) { /**err：设置三个条件*/
            int l1Val = (l1 == null) ? 0 : l1.val;
            int l2Val = (l2 == null) ? 0 : l2.val;
            int sum = l1Val + l2Val + carry; /*计算当前位置的总和值*/
            carry = sum / 10; //记录进位信息
            sum %= 10; //当前位置的数值
            cur.next = new ListNode(sum); //给结果串 拼接数值为cur的节点
            cur = cur.next;
            l1 = (l1 == null) ? l1 : l1.next; /**🔺err：这里如果没有移动指针，会报错“超出内存限制”*/
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return res.next;
    }


    /*19.
    * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    /**    🔺强烈建议使用官方解,即方法removeNthFromEnd1
     * 【解题关键&&官方解精髓】开始时slow指向虚拟头dummy节点；
     *                       开始时fast指向head 并且 fast先走n步（注意slow和fast的开始位置不一样）
     *                      （最后fast指向null的时候，slow正好指向倒数第n+1个节点）
     * 【步骤】 1. slow结点指向dummy节点，fast节点指向head节点；
     *         2. fast节点先走n步；
     *         3. slow走一步，fast走一步。。。知道fast指向null节点
     *         4. 删除slow的下一个节点————单链表的删除下一个节点，其实就是操作：slow.next=slow.next.next
     * 【注】1. 这个题重要的是在删除的时候slow指针指向要删除节点的前一个节点。
     *      2. 一开始，要让fast指针先走N步*/
    /*自己的解法*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head;
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }


    /*官方解*/
    /**【建议采样这种】官方解，官方解的通用性更好。。
     *      这种写法下fast指针指向slow指针前面的第n+1个节点，因此当fast指针指向null
     *  的时候，slow指针指向要删除节点的前一个节点。举个例子：
     *      比如想删除倒数第二个节点，则fast指针指向null的时候，slow指针指向的是倒数
     *  第3个节点————即后面时刻fast会指向slow节点 前面的第n+1个节点*/
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        /**     官方解中，slow指针开始时指向了head节点的前一个；fast指针指向head节点！！解释：这样相当
         * 于fast指针和slow指针之间有n个节点，最后fast来到null的时候，slow指针指向倒数n+1个节点，只用
         * 将slow.next = slow.next.next即可*/
        ListNode slow = dummy;
        ListNode fast = head;
        for (int i = 0; i < n; ++i) { /*step1：fast从head先走n步。。。此时fast相比slow其实多走了k+1步*/
            fast = fast.next;
        }
        while (fast != null) { /*step2：将fast指针移动到链表最后*/
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; /*step3：删除倒数第N个节点*/
        return dummy.next;
    }


    /*24.
    * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下
    * 完成本题（即，只能进行节点交换）。*/
    /**
     * 【解题步骤】
     *          1. 创建虚拟头节点（虚拟头节点的本质就是记录了 前一组"两两节点"的最后节点，也即当前这
     *      组“两两节点”的前置节点）。。这就要求每一轮反转完两个节点后，需要将cur指针指向它两中的最
     *      后一个节点；
     *        2. 在每一轮循环中先记录两两节点————第一个node1，第二个node2，然后调整指针指向
     *  【建议】使用非递归解法————swapPairs1方法
     */
    /*非递归的形式；非递归形式看官方讲解*/
    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(-1,head);
        ListNode cur = dummy;

        //while条件的含义：保证下一次还能搞到2个节点，如果不够两个了，说明不必要进行下一轮操作了
        while (cur.next != null && cur.next.next != null) {
            /**step1：每一轮开始前，用node1、node2分别记录原始链表的第一个节点node1、第二个节点node2。*/
            ListNode node1 = cur.next; //原始的第一个节点
            ListNode node2 = cur.next.next; //原始的第二个节点
            /**step2：然后进行节点的拼接*/
            /*以前是cur——>node1——>node2，下面的语句完成变成cur——>node2——>node1的形式*/
            cur.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            /**step3：每一轮结束后cur节点移动到这一组的最后一个节点————其实就相当于任何一轮循环开始前，cur节点的位置应该不变类似
             * 于状态一致（一定要处于前面已经完成交换的最后一个节点。比如：开始的时候cur指向head的前一个节点；每一轮结束后我们将
             * cur指向了node1，而node1节点就是这一轮研究结束时的最后一个节点）*/
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy,end = dummy;
        while (end.next != null) { /**end是已经完成反转部分的最后一个节点即A组前面一组的最后一个节点。“end.next!=null”是保证下一组A组还有节点*/
            /*step1：先数k个节点，如果不够k个(end==null)，说明剩下的不够k个，因此结束循环，返回*/
            for (int i = 0; i < k && end != null; i++) { //经过这一轮循环，end会来到新的一组即A组的最后一个节点
                end = end.next;
            }
            if (end == null) break; //说明这一组不够k个节点了，因此不用动，结束循环即可
            /*step2：记录几个节点。此时——————
             *      curStart指向这一组的第一个节点；end指向这一组的最后一个节点；pre指向这一组前面的那个节点（即前一组的
             * 最后一个节点，对于第一组来说，前一个结点即为dummy节点————此思想类似于“两两交换链表节点”）；nextStart指
             * 向下一组的第一个节点*/
            ListNode curStart = pre.next; //当前这一组的第一个节点
            ListNode nextStart = end.next; //下一组的第一个节点
            /*step3：几个节点之间调整连接关系*/
            /**err：用nextStart记录下end.next以后，务必将end.next置为null*/
            end.next = null; //当前这一组节点的最后一个节点的next置为null，便于翻转链表
            pre.next = reverse(curStart); //reverse返回的是 这一组翻转 之后的开始节点，要拼在前一组后面————即赋给pre.next。
            curStart.next = nextStart; //这一组翻转完成后，start变成了这一组的最后一个节点。。因此将start.next赋值为nextStart(其中nextStart为下一组的第一个节点)————这一步就将当前组和下一组连起来了
            /*step4：pre和end更新，更新为这一组的最后一个节点。。和最开始的“pre = dummy,end = dummy”呼应，每一轮循环之后状
            态回归到循环开始时的样子*/
            pre = curStart;
            end = curStart;
        }
        return dummy.next;
    }

    /*翻转链表的原始代码————不变*/
    public ListNode reverse(ListNode start){ /*形参的start是每一组节点的第一个*/
        ListNode cur = start,pre = null;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre; //结束时，cur会来到null，cur来到这组节点的最后一个
    }



    /*138*/
    /**
     * 【强烈建议】使用解法copyRandomList1，尤其是最后一步拆分链表的操作
     * 【分解步骤】：
     *      1. 从头到尾遍历：给原始链表每一个节点后面添加一个与之val相等的新节点
     *      2. 从头到尾遍历：如果一个节点的random指针不是null，给它的下一个节点(即它的副本节点
     *  random指针赋值)
     *      3. 从头到尾遍历拆分原始链表，和copy的链表。【注意】每一个节点都要对，且原始链表不能
     *  改变！！否则会报错，信息类似于："Next pointer of node with label 7 from the original
     *  list was modified."
     * 【出错步骤】第三步的代码难写，建议看下面的方法copyRandomList1
     * */
    public Node copyRandomList(Node head) {
        if(head==null) return null; /**err：特例的判断*/
        Node cur = head;
        /*step1：给每一个节点后面放一个新创建的节点*/
        while(cur!=null){
            Node newCur = new Node(cur.val);
            newCur.next = cur.next;
            cur.next = newCur;
            cur = cur.next.next;
        }

        /*step2：从头遍历到尾copy原来节点的random指针*/
        cur = head;
        while(cur!=null){
            if(cur.random!=null){
                cur.next.random = cur.random.next; //如果if条件不判断，这里会出现空指针异常
            }else{
                cur.next.random = null; //由于指针的默认值其实就是null,因此这个else分支其实可以不要
            }
            cur = cur.next.next;
        }

        /*step3：需要将原始链表 以及 copy后的链表拆分开*/
        /**err：如果原始链表没有还原会报错*/
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;

        /*step3，也可以改写成下面的形式*/
//        if (head==null) return null;
//        Node res = head.next,ress = res;
//        while(ress.next!=null){
//            head.next = ress.next;
//            ress.next = head.next.next;
//            head = head.next;
//            ress = ress.next;
//        }
//        head.next = null;
//        ress.next=null;
//        return res;

        /*step3，chatgpt的写法*/
//        Node dummy = new Node(0);
//        Node copyCur = dummy;     //①copy的链表的遍历指针
//        Node cur = head;          //②原始链表的遍历指针
//
//        while (cur != null) {     /*使用原始链表的节点部位null作为终止条件*/
//            Node copy = cur.next; //开始的3行拼接复制后的链表
//            copyCur.next = copy;
//            copyCur = copy;
//
//            cur.next = copy.next; // 这两行 还原 原始链表
//            cur = cur.next;
//        }
//        return dummy.next;
    }


    /*解法2：K神的解法————
    * https://leetcode.cn/problems/copy-list-with-random-pointer/solutions/2361362/138-fu-zhi-dai-sui-ji-zhi-zhen-de-lian-b-6jeo/
        复制节点的操作不变；赋值random指针的操作不变。
     唯一的区别在于第三步拆分链表的时候逻辑更清晰。
    */
    public Node copyRandomList1(Node head) {
        if (head == null) return head;

        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        /*第三步：拆分原始链表和复制的链表*/
        /*
        解释：到了这里说明链表至少有两个节点，因为如果head等于null在第一行就返回了。
            res初始值为head.next，用于组装复制后的链表————
                因为复制的节点一定是最后一个，因此最后没有节点时res.next一定是null；
            pre初始值为head，用于还原原来的链表；
                因为对于每一个原始节点，复制的节点是在后面，原始的节点在前面。所以最后没有节点的时候pre会来到倒数第二个节点，这里
            需要把它的next指针指向null.
        * */
        /**链表拆分时强烈建议参考328题！！！*/
        Node res = head.next, resCur = res; /*res---复制得到的链表的头；resCur---拼接复制得到的链表指针，用这个resCur指针完成遍历拆分*/
        cur = head; /*cur---拼接原始的链表的指针*/
        while (resCur.next != null) { /**【说明】只要“resCur.next!=null”，则resCur后面至少还有两个节点。。。后面的节点必然是成对出现的*/
            //拼接原始链表，拼接后移动cur指针
            cur.next = cur.next.next;
            cur = cur.next;
            //拼接复制的链表的节点，拼接后移动resCur指针
            resCur.next = resCur.next.next;
            resCur = resCur.next;
        }
        cur.next = null; /**err：最后需要把原始链表的末尾置为null*/
        return res;
    }



    /*148.
    * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
    /**
     * 【思路】归并排序
     * 【具体步骤】
     *      1.结束时机？如果没有节点或者只有一个节点，不用排序即直接返回；
     *      2.（说明目前的链表有不止一个节点）找到中间节点，同时需要把前一半最后节点next指针指向null
     *      3.递归调用方法排序前半的链表、后半的链表
     *      4.经过3两半的链表都排序完成，这里将排序后的两半链表合并为一个。。【力扣；合并两个有序链表】
     * 【难点】在找中间节点的时候，一定要把前一半链表的最后节点指向null，否则会死循环，超出运行时间。
     * */
    public ListNode sortList(ListNode head) {
        /*1.特殊情况的考虑————没有节点或者只有一个节点，此时不用排序*/
        if (head==null||head.next==null) return head;
        /*2.找到中间节点的后一个节点（奇数得到中间，偶数得到中间的后一个）。
        但是其中有一个细节————在findMid中必须要先找到中间的第一个节点*/
        ListNode mid = findMid(head);
        /*3.分别排序左、右两半链表*/
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        /*4.合并左右排序后的两部分*/
        return merge(left,right);
    }

    /*合并两个有序链表的原始代码，不动！！
    * 【注】出了while循环，记得拼接上不是null的部分，否则初始用例会出现如下的错误————
            输入
            head =
            [4,2,1,3]
            输出
            [1]
            预期结果
            [1,2,3,4]
    */
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1),cur = dummy;
        while (left!=null&&right!=null){
            if (left.val<right.val){
                cur.next =  left;
                left = left.next;
            }else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left==null?right:left;
        return dummy.next;
    }

    /*【重要🔺】找中间节点的代码。与hot100求中间节点不同，区别————
    *       1. 如果是奇数个节点，两种都会得到中间节点；
    *       2. 如果是偶数个节点，下面的方案会先得到中间两个的前一个节点。但是返回值跟常规的中间节点是一样
    *    的————即中间节点的后一个节点。。只不过排序链表中需要先拿到中间的前一个节点将next置为null，多了这
    *    一步处理操作。
    *【注】“排序链表”找中间节点时虽然返回的是常规意义的中间节点————即奇数返回中间节点，偶数返回中间节点的后一个。但
    *   是方法内的逻辑：让偶数节点的时候slow来到中间两个节点的第一个，奇数节点不变————目的是让偶数节点的时候需要将
    *   前一半链表最后节点的next置null。
    * */
    private ListNode findMid(ListNode head) {
        ListNode slow = head,fast = head.next; /**与“找中间节点”的区别，fast的初始位置是在head.next而不是head*/
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //如果是偶数个节点，代码执行到这里slow会指向中间两个节点的第一个；奇数节点时值得就是中间节点
        ListNode res = slow.next;
        slow.next = null; /**【说明】把前一半链表的最后节点指向null，其实最根本的原因在于结束排序的代码是“head==null||head.next==null”，我们是用null作为链表结束的标志*/
        return res;
    }


    /*23.
    * 给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。*/
    /**【2种解法】：1. "优先级队列"的编码有两点需要注意。①添加节点时校验不是null(优先级队列添加null时会抛NullPointerException)；
     *         ②更新cur指针
     *      2. 需要熟悉一下"分治合并"————跟归并排序的思路是一样的，区别就是在merge的时候时两个链表的合并。
     *      关于"分治"的代码可以参考：https://leetcode.cn/problems/merge-k-sorted-lists/solutions/3787/leetcode-23-he-bing-kge-pai-xu-lian-biao-by-powcai/?envType=study-plan-v2&envId=top-100-liked
     * */
    /*解法1：借助优先级队列。假设lists中有k个队列，每一个队列有n个节点
    * 时间复杂度：元素数量k*n,每一个元素进队列出队列一次，复杂度logK，因此总体时间复杂度O(kn*logk)
    * 空间复杂度：O（K），优先级队列的耗费
    * */
    public ListNode mergeKLists(ListNode[] lists) {
        /*优先级队列。空间复杂度O(K)，优先级队列需要存K个节点；
            时间复杂度O(kn*logk)，kn个节点，优先级队列k个节点，因此插入删除的时间复杂度logk。*/
        ListNode demmy = new ListNode(-1),cur = demmy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        /*step1：先将lists种所有链表的第一个节点加紧优先级队列*/
        for (ListNode node:lists){
            if(node!=null){ //①添加之前校验，保证不是空
                queue.add(node);
            }
        }
        /*step2：每一次弹出一个元素(一定是队列中最小的元素)；如果它后面还有元素，把它后面的元素入优先级队列*/
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

    /*解法2：分治思想(假设一共K个链表；一共有N个节点)
    * 空间复杂度：O（logk）
    * 时间复杂度的理解————
          思路 1：递归层数
            每次把 K 条链表一分为二，递归深度是 O(log K)。
          思路 2：每一层的合并工作量
            第一层：K 条链表，两两合并，最终得到 K/2 条链表，总共处理 N 个节点 → O(N)。
            第二层：K/2 条链表，两两合并，最终得到 K/4 条链表，总共处理 N 个节点 → O(N)。
            …
            最后一层：合并两条链表，仍然要扫一遍所有节点 → O(N)。
          所以综上可知，每一层的合并代价是 O(N)，一共有 O(log K) 层。时间复杂度：O(NlogK)
     如果K很大，优先考虑优先级队列的方法！！
    */
    /*
    【易错点】初始方法中需要进行判断："if (lists == null || lists.length == 0) return null;"，两个条件
         缺一不可。如果没有"lists.length == 0"，会出错：
            java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0。
         如果没有这个前置校验，就需要在方法merge(ListNode[] lists, int l, int r)中添加：
            if(l<r) return null;
    * */
    public ListNode mergeKLists_01(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null; /**err：注意这里if条件需要添加"lists.length == 0"*/
        return merge(lists,0,lists.length-1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l==r) return lists[l];
        /*如果上面的if条件中没有“lists.length == 0”，就必须有下面的话。否则会报错：
        * java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
        * 意思是存在length=0的lists*/
//        if (l<r) return null;
        int mid = l+(r-l)/2;
        ListNode mergeLeft = merge(lists, l, mid); /**右边界必须是mid，不能是mid-1..为什么？？*/
        ListNode mergeRight = merge(lists, mid + 1, r); /**err：右边界是r不是nums.length-1*/
        return mergeTwoList(mergeLeft,mergeRight);
    }

    /*合并两个升序链表的原始代码————一模一样*/
    /*
    有一种报错如下：
        lists =
        [[1,4,5],[1,3,4],[2,6]]
        输出
        [6]
        预期结果
        [1,1,2,3,4,4,5,6]
        是因为在while内部没有移动cur指针“cur =cur.next”。
    * */
    private ListNode mergeTwoList(ListNode mergeLeft, ListNode mergeRight) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        /*step1：只要两个都还有元素，就将val小的那一个拼接到结果链。。
        * 【注】拼接后需要移动那个链表的指针 以及 结果链表的指针*/
        while (mergeLeft!=null&&mergeRight!=null){
            if (mergeLeft.val< mergeRight.val){
                cur.next = mergeLeft;
                mergeLeft = mergeLeft.next;
            }else{
                cur.next = mergeRight;
                mergeRight = mergeRight.next;
            }
            cur = cur.next; /**err：注意移动cur指针*/
        }
        /*step2：将不空的那个链表的其他元素拼接到结果链后面。。。
        * 如果忘记拼接还不是null的链表，会出现如下的错：
                lists =
                [[1,4,5],[1,3,4],[2,6]]
                输出
                [1,1,2,3,4]
                预期结果
                [1,1,2,3,4,4,5,6]
        * */
        cur.next = mergeLeft==null?mergeRight:mergeLeft; /**err：注意拼接还有节点的那个链表*/
        return dummy.next;
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
        /*
        1. LRU中每一个节点的结构
        2. 每一个节点都是key-value的形式存储值，并且有pre、next指针————可以实现前溯、后溯遍历*/
        class DouListNode{
            int key;
            int value;
            DouListNode pre;
            DouListNode next;
            public DouListNode(int key, int value) { /**【注】必须使用key-value构造器，涉及到put操作的时候新建节点存储key-value*/
                this.key = key;
                this.value = value;
            }
        }

        int capacity; /*LRU链表的容量*/
        int size; /*LRU当前的大小*/
        DouListNode head; /*LRU链表的头节点*/
        DouListNode tail; /*LRU链表的尾节点*/
        HashMap<Integer, DouListNode> map = new HashMap<Integer, DouListNode>();

        /*
        LRUCache构造器中完成：
            ①capacity的指定；②size的初始化；③head节点、tail节点的初始化 以及 指针的连接
        */
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new DouListNode(-1, -1);
            tail = new DouListNode(-1, -1);
            head.next = tail;
            tail.pre = head;
        }

        /*
        get的思路：从map里面获取————
            情况1：如果找到的节点是null，说明没有直接返回-1；
            情况2：如果找到了节点，则：①把这个节点移动到头节点；②返回节点的值————即node.value
        * */
        public int get(int key) {
            DouListNode node = map.get(key);
            if (node==null){
                return -1;
            }else {
                removeNode(node);
                addNodeToHead(node);
                return node.value;
            }
        }

        /*
        put操作的思路：从map中获取节点node————
            情况1：如果找到了node不是null，说明LRU链表中已经有key对应的节点。则：①移动这个节点到LRU的头结点；
        ②把这个node的值更新为put方法的参数value
            情况2：如果找到的node是null，说明LRU链表中之前没有key对应的节点。则：①使用key-value创建新的节点newNode；
        ②map中添加key-newNode的映射关系；③将newNode添加到LRU的head节点；------至此完成了新节点的添加操作
        ④更新size变量；④判断是不是超出容量，超出的话需要删除尾节点。------这两步完成添加节点的后置校验
        * */
        public void put(int key, int value) {
            DouListNode node = map.get(key);
            if (node==null){
                DouListNode newNode = new DouListNode(key,value);
                map.put(key,newNode);
                addNodeToHead(newNode);
                size++;
                if (size>this.capacity){
                    removeRealTail();
                }
            }else {
                node.value = value;
                removeNode(node);
                addNodeToHead(node);
            }
        }

        /*
        删除尾部节点：
            ①通过tail.pre拿到真正的尾节点删除；②从map中删除节点
        * */
        private void removeRealTail() {
            DouListNode tailReal = tail.pre;
            removeNode(tailReal);
            map.remove(tailReal.key);
        }

        /*删除一个节点：
            ①拿到node的前驱节点 以及 node的后继节点；②将前驱节点 和 后继节点连在一起*/
        private void removeNode(DouListNode node) {
            DouListNode pre = node.pre;
            DouListNode next = node.next;
            pre.next = next;
            next.pre = pre;
        }

        /*把一个节点添加为头节点*/
        private void addNodeToHead(DouListNode newNode) {
            DouListNode next = head.next;
            head.next = newNode;
            newNode.next = next;
            next.pre = newNode;
            newNode.pre = head;
        }
    }



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
