package zuo_course_01base.No_4_linkedlist;

public class linkPartition {
    public static Node m02(Node head,int num){
        //分别创建3个链表————分别存放大于、小于、等于指定值num的。
        Node sh = null;
        Node st = null;
        Node eh = null;
        Node et = null;
        Node mh = null;
        Node mt = null;
        while(head!=null){                 //如果当前的节点不是null，说明没有到表尾。则看它的vlaue属于那一部分，就添加到哪一个链表
            if (head.value < num){
                if (sh == null) {
                    sh = head;
                    st = head;
                }
                else
                    st.next = head;
            }else if (head.value == num){
                if(eh == null){
                    eh = head;
                    et = head;
                }
                else
                    et.next = head;
            }else{
                if(mh == null){
                    mh = head;
                    mt = head;
                }
                else
                    mt.next = head;
            }
        }
        if(st != null){     //如果有小于区域
            st.next = eh;
            et = et==null?st:et;
        }
        if (et == null){
            et.next = null;
        }
        return sh != null?sh:(eh != null?eh:mh);
    }
}
