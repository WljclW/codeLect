package zuo_course_01base.No_4_linkedlist;

public class findFirstXiangJiao {

    //找有环链表的第一个入环节点
    public static Node getLoopNode(Node head){  //找有环链表的第一个入环节点（不是相交节点），快慢指针法
        if(head==null||head.next==null)
            return null;
        Node slow=head.next;
        Node fast=head.next.next;
        while(slow!=fast){
            if(fast.next==null||fast.next.next==null){
                return null;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }


    /**
     * 下面是找链表的相交节点的不同情况
     * */
    //一、两个链表都没有环
    public static Node noLoop(Node head1,Node head2){
        Node curr1 = head1;
        Node curr2 = head2;
        int n = 0;
        while(curr1.next != null){
            n++;
            curr1 = curr1.next;
        }
        while(curr2.next != null){
            n--;
            curr2 = curr2.next;
        }
        if(curr1 != curr2){
            return null;           //如果两个单链表最后一个节点不相同，则必然没有相交。因为一旦有相交，后面的节点都是相同的。
        }
        //curr1和curr2的当前值已经没有作用了，因此下面可以重复利用
        curr1 = n>0?head1:head2;            //谁长谁的头节点赋给curr1,curr1变量锁定在长链表的表头。
        curr2 = curr1 == head1?head2:head1;          //谁短谁的头节点赋给curr2
        n = n>0?n:-n;                   //n取绝对值
        while(n!=0){                        //长链表的头节点指针要先移动长的步数
            curr1 = curr1.next;
            n--;
        }
        while(curr1 != curr2){              //如果两个指针指的不相同，则不断的同时后移。
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return curr1;
    }


    public static Node bothLoopCase(Node head1,Node loop1,Node head2,Node loop2){
        /**
         *      求解两个有环链表的相交节点
         * */
        if (loop1 == loop2){        //loop1和loop2相等就表明是情况2————两个链表在进入换之前就相遇了。
            Node curr1 = head1;
            Node curr2 = head2;
            int n=0;
            while(curr1 != loop1){
                n++;
                curr1 = curr1.next;
            }
            while(curr2 != loop1){
                n--;
                curr2 = curr2.next;
            }
            curr1 = n>0?head1:head2;
            curr2 = curr1 == head1?head2:head1;
            n = n>0?n:-n;
            while(n!=0){
                curr1 = curr1.next;
                n--;
            }
            while (curr1 != curr2){
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
            return curr1;               //到这里if语句块结束，就等价于处理了无环的相交问题。
        }else{
            /**
             *      进入else表明loop1 ！= loop2，属于1）或者3）
             * */
            Node curr = loop1.next;
            while(curr != loop1){       //让loop1转一圈
                if(curr == loop2)       //如果这个过程中碰到了loop2，那就是3），返回loop1或者loop2的一个。
                    return loop1;
                curr = curr.next;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);
        Node n4=new Node(4);
        Node n5=new Node(5);
        Node n6=new Node(6);
        n1.next=n4;
        n2.next=n3;
        n4.next=n3;
        n3.next=n5;
        n5.next=n6;
        n6.next=n4;
        System.out.println(noLoop(n1,n2).value);
//        System.out.println(bothLoopCase(n1,n2).value);

    }
}
