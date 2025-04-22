package zuo_course_01base.No_4_linkedlist;

import java.util.Stack;

public class link_reverse {
    public static Node m01(Node head){
        if(head == null){
            return head;
        }
        Stack<Node> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.next;
        }
        Node curr = stack.pop();
        head = curr;
        while(!stack.isEmpty()){
            curr.next = stack.pop();
            curr = curr.next;
        }
        return head;
    }

}
