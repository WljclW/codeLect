package zuo_course_01base.No_5_binaryTree.ershua;

import java.util.LinkedList;


/**
 *      1. 如何理解二叉树的题目以及解法：合理的使用四种遍历顺序，同时在应该打印节点值（如果是遍历的话就是直接打印节点值）的地
 * 方，对节点做一些处理————重点是做什么处理，怎么做处理！！
 *          基于上面的描述可以发现：二叉树的题目重点在于：①识别出应该使用哪种遍历方式；②在打印节点的时候应该对节点做什么样
 *      的处理。
 * */
public class feidigui_bianli {
    /**
     * 【说明】先序遍历的顺序是根、左、右。。因此碰到一个弹出节点的时候直接打印，然后先入栈右孩子、在入栈左孩子。这样下来遍历的
     *      顺序才是根、左、右
     * */
    public void xianxu(Node head) {
        if (head == null) return;
        /*1.先将根节点入栈*/
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(head);
        /*3.先访问弹出节点，再压栈右孩子、左孩子(顺序不能错)*/
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.value);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }


    /**
     * 1.后序遍历来源于先序遍历的改动。区别有几点（说明，也可以不多使用一个栈，将一个栈的弹出顺序存在list，然后反序即为后序遍历）：
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
    public void houxu(Node head) {
        if (head == null) return;
        LinkedList<Node> stack1 = new LinkedList<>();
        LinkedList<Node> stack2 = new LinkedList<>(); //1.1相比前序多一个栈
        stack1.push(head);
        /*
         *   将所有的节点压入到栈stack1。顺序是根、左孩子、右孩子（注意顺序和先序
         * 遍历不相同）
         * */
        while (!stack1.isEmpty()) {
            Node cur = stack1.pop();
            stack2.push(cur); //1.2弹出的节点压入到stack2中。
            if (cur.left != null)
                stack1.push(cur.left); //先压左孩子，区别点1.3
            if (cur.right != null)
                stack1.push(cur.right);
        }
        /*依次弹出stack2的节点并访问*/
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().value);
        }
    }

    /*中序遍历的第一种写法*/
    public void zhongxu(Node head) {
        if (head == null) return;
        Node cur = head;
        LinkedList<Node> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            /*沿着左边界一直走，把所有的节点加到栈*/
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            /*沿着左边界走到头了，需要弹出一个节点，然后访问，同时遍历这个节点的右分支，因此将cur置为cur.right*/
            cur = stack.pop();
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    /*中序遍历的另一种写法，while内部换成if、else的形式*/
    public void zhongxu_zuoshen(Node head) {
        if (head == null) return;
        Node cur = head;
        LinkedList<Node> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            /*step1：如果当前研究的节点不是空，则加入栈 并且 研究它的左孩子（”研究它的左孩子”直白点就是cur=cur.left）*/
            if (cur!=null){
                stack.push(cur);
                cur = cur.left;
            }else{ /*step2：否则说明当前研究的节点是空，因此需要弹出一个节点————即最左的节点，弹出时并访问，然后继续研究它的右孩子（”继续研究它的右孩子“直白点说就是cur=cur.right）*/
                cur = stack.pop();
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }

}
