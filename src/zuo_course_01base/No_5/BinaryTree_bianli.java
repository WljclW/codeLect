package zuo_course_01base.No_5;

import java.util.LinkedList;
import java.util.Stack;
/**
 *          非递归的二叉树遍历————中序、前序、后序、宽度优先
 *
 * */

public class BinaryTree_bianli {
    public static void preOrderUnRecur(Node head){              //非递归的先序遍历
        /**
         *      先序遍历的非递归版本
         * */
        System.out.println("pre-order:");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            stack.add(head);                       //先将头节点入栈。这个操作不在循环
            while(!stack.isEmpty()){
                /**
                 * 【注】
                 *      元素出栈和获取元素的值不能一起执行———— System.out.println(head.pop().value+"     ");
                 *  这是错误的，会导致死循环，也就是栈顶元素并没出栈。
                 *
                 * */
                head = stack.pop();
                System.out.println(head.value+"     ");
                /**
                 *      因为先序的顺序是“头、左、右”，因此要先压入右孩子。
                 *
                 * */
                if(head.right != null) {                    //先将右节点压入栈。
                    stack.push(head.right);
                }
                if (head.left != null){                     //再将左节点压入栈。
                    stack.push(head.left);
                }
            }
        }
        System.out.println("");
    }


//    public static void preOrderUnRecurFuXi(Node head){
//        if (head == null)
//            return;
//        Stack<Node> stack = new Stack<>();
//        stack.add(head);
//        while(!stack.isEmpty()){
//            head = stack.pop();
//            System.out.println(head.value+"     ");
//            if(head.right!=null)
//                stack.push(head.right);
//            if (head.left!=null)
//                stack.push(head.left);
//        }
//        System.out.println();
//    }


    public static void posOrderUnRecur(Node head){                  //非递归的后序遍历
        /**
         *      后序遍历的非递归版本
         *
         * */
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        if(head != null){
            stack1.add(head);
            while(!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);                     //先序遍历这里是打印操作，后序遍历不打印而是将其压入另一个栈
                if(head.left != null){
                    stack1.push(head.left);
                }
                if(head.right != null){
                    stack1.push(head.right);
                }
            }
            while(!stack2.isEmpty()){               //经过上面的while，当前二叉树的所有节点均已入了stack2。
                /**
                 *      将这个栈的所有节点依次出栈。
                 * */
                head = stack2.pop();
                System.out.println(head.value + " ");
            }
        }
        System.out.println();
    }


//    public static void posOrderUnRecur_FuXi01(Node head){
//        if(head == null)
//            return ;
//        Stack<Node> s1 = new Stack<>();
//        Stack<Node> s2 = new Stack<>();
//        s1.add(head);
//        while(!s1.isEmpty()){
//            head = s1.pop();
//            s2.push(head);
//            if (head.left != null)
//                s1.push(head.left);
//            if (head.right != null)
//                s1.push(head.right);
//        }
//        while(!s2.isEmpty()){
//            System.out.println(s2.pop().value+"   ");
//        }
//    }



//    public static void posOrderUnRecur_FuXi(Node head){
//        if (head != null){
//            Stack<Node> stack1 = new Stack<>();
//            Stack<Node> stack2 = new Stack<>();
//            stack1.add(head);
//            while(!stack1.isEmpty()){
//                head = stack1.pop();
//                stack2.push(head);
//                if (head.right!=null)
//                    stack1.push(head.right);
//                if (head.left!=null)
//                    stack1.push(head.left);
//            }
//            while(!stack2.isEmpty()){
//                System.out.println(stack2.pop().value+"     ");
//            }
//        }
//        System.out.println();
//    }


    public static void inOrderUnRecur(Node head){               //非递归的中序遍历
        System.out.println("in-order:");
        Stack<Node> stack = new Stack<>();
        if(head != null){
            while(!stack.isEmpty() || head != null){
                if(head != null){               //进入if说明head节点不为空，则需要顺着该节点找它的左子树
                    /**
                     *      只要没有到空节点，就沿着一条路一致往左走。走到头表示为空了，此时需要得到它的父节点。但是
                     *  没有指向父节点的指针，如何得到呢？答：栈顶元素即为它的父节点。
                     *
                     * */
                    stack.push(head);           //把head节点压入栈
                    head = head.left;           //把head指针指向之前节点的左孩子节点。
                }else{                          //进入else说明head为空，此时需要从栈弹出一个节点(这个节点的左孩子必为空)并打印，顺着它的右孩子的左子树继续循环。
                    /**
                     *      进入else语句块说明if不满足，也就是从根节点A一直往左走，走到某个节点B到头了，此时需要返回该空节点的父节点。
                     *   做法就是从栈弹出一个节点，去该节点的右子树重复if。
                     *      "去该节点的右子树重复if"就是从该节点的右孩子结点开始，有一只往左走，走到尽头。
                     * */
                    head = stack.pop();
                    System.out.println(head.value + "   ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }


    public static void wOrder(Node head){                   //非递归的按层遍历
        /**
         *
         *      宽度优先遍历——————也就是说，从上到下遍历所有层，每一层从左向右。
         * */
        if(head == null){
            return;
        }
        LinkedList<Node> que = new LinkedList<>();          //按层遍历借助队列
        que.add(head);
        while (!que.isEmpty()){
            /**
             * 【注意】
             *      System.out.println(deque.poll().value+"  ");不能替换下面的两句。也就是说deque.poll().value
             *  不会导致队列首的元素出队列。
             *
             * */
            head = que.poll();
            System.out.println(head.value + "    ");
            if (head.left!=null){                       //由于队列结构是先进先出。按层遍历是先左后右，因此要左子树先进队列才能它先出
                que.add(head.left);
            }
            if(head.right != null){
                que.add(head.right);
            }
        }
    }


    public static void wOrder_FuXi01(Node head){
        if (head == null)
            return;
        LinkedList<Node> deque = new LinkedList<>();
        deque.add(head);
        while(!deque.isEmpty()){
//            System.out.println(deque.poll().value+"  ");
            head = deque.poll();
            System.out.println(head.value + "   ");
            if (head.left != null)
                deque.add(head.left);
            if (head.right != null)
                deque.add(head.right);
        }
    }


//    public static void wOrder_FuXi(Node head){
//        if (head == null)
//            return;
//        LinkedList<Node> que = new LinkedList<>();
//        que.add(head);
//        while(!que.isEmpty()){
//            head = que.poll();
//            System.out.println(head.value + "   ");
//            if (head.left!=null)
//                que.add(head.left);
//            if (head.right!=null)
//                que.add(head.right);
//        }
//        System.out.println();
//    }



    public static void main(String[] args) {
        Node head=new Node(1);
        head.right=new Node(2);
        head.right.left=new Node(3);
        head.right.right=new Node(4);
//        preOrderUnRecurFuXi(head);
//        posOrderUnRecur_FuXi(head);
//        wOrder_FuXi(head);
        wOrder_FuXi01(head);
//        inOrderUnRecur(head);
//        preOrderUnRecur(head);
//        posOrderUnRecur(head);
//        posOrderUnRecur_FuXi01(head);
        //cengxufuxi(head);
    }
}
