package zuo_course_01base.No_4;

import java.util.Stack;

public class linkHuiWen {
    //方法1,所有的数据压栈
    public static Boolean m01(Node head){
        if(head == null || head.next ==null){
            return true;
        }
        Node res = head;
        Stack<Node> stack = new Stack<>();
        while(res!=null){
            stack.push(res);
            res = res.next;
        }
        while(head!=null){
            if(stack.pop().value!=head.value)
                return false;
            head = head.next;
        }
        return true;
    }

    //方法2：将中点后面的一半压栈
    public static Boolean m02(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node res = head;
        Node tail = head;
        Stack<Node> nodes = new Stack<>();
        /**
         * tail.next!=null && tail.next.next!=null
         *      这里其实是要判断tail.next.next!=null的，但是这一步必须先判断tail.next!=null。
         *              如果tail.next==null，则说明为假，后面的判断tail.next.next!=null就不会执行了。
         *              但如果没有前面的条件，只有tail.next.next!=null，一旦tail.next==null，就会导致
         *           空指针异常，程序终止。
         * */
        while(tail.next!=null && tail.next.next!=null){
            res = res.next;
            tail = tail.next.next;
        }
        while(res!=null){
            nodes.push(res);
            res = res.next;
        }
        while(!nodes.isEmpty()){
            if(nodes.pop().value!=head.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //方法3：空间复杂度O(1)
//    public static Boolean m03(Node head){
//
//    }
}
