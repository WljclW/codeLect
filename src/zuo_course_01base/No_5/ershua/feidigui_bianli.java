package zuo_course_01base.No_5.ershua;

import java.util.LinkedList;

public class feidigui_bianli {
    public void xianxu(Node head){
        if (head == null) return;
        /*1.先将根节点入栈*/
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(head);
        /*3.先访问弹出节点，再压栈右孩子、左孩子(顺序不能错)*/
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.value);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }


    /**
     * 1.后序遍历来源于先序遍历的改动。区别有几点：
     *    1.1.多使用一个栈
     *    1.2.从第一个栈弹出的Node不打印，而是压入到第二个栈
     *    1.3.第一个栈压栈的顺序是根节点、左孩子、右孩子
     * 2.为什么跟先序遍历压栈的顺序不一样？
     *    先序遍历的顺序是：根、左孩子、右孩子。因此先压入根节点，根节点弹出时先
     * 压入栈右孩子，因此右孩子子树的数据会后出，即出栈后为根、左、右的局面
     *    后序遍历的顺序是，左孩子，右孩子，根。因此先压入根，弹出根节点的Node压
     * 入到第二个栈，再弹出的是右子树的节点也会压入到第二个栈，最后弹出的是左子树
     * 的节点同样压入到第二个栈。因此最后从第二个栈弹出的顺序就是左子树、右子树、
     * 根节点
     * */
    public void houxu(Node head){
        if (head == null) return;
        LinkedList<Node> stack1 = new LinkedList<>();
        LinkedList<Node> stack2 = new LinkedList<>(); //1.1相比前序多一个栈
        stack1.push(head);
        /*
        *   将所有的节点压入到栈stack1。顺序是根、左孩子、右孩子（注意顺序和先序
        * 遍历不相同）
        * */
        while (!stack1.isEmpty()){
            Node cur = stack1.pop();
            stack2.push(cur); //弹出的节点压入到stack2中。
            if (cur.left!=null) stack1.push(cur.left); //先压左孩子，区别点1.2
            if (cur.right!=null) stack1.push(cur.right);
        }
        /*依次弹出stack2的节点并访问.区别点1.3*/
        while(!stack2.isEmpty()){
            System.out.println(stack2.pop().value);
        }
    }
}
