package zuo_course_01base.No_5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 *    非递归的二叉树遍历————中序、前序、后序、宽度优先
 *
 * */
public class BinaryTreeUnrecurOrder {
    /**
     * 非递归先序遍历：
     *    流程：先将头节点入栈。然后只要栈不是空，就弹出节点并打印值；如果右节点不是空则右节点入栈；如果左节点不是空则
     * 让左节点入栈。注意是 先右后左，这样出栈才是左右
     * */
    public static void preOrderUnRecur(Node head){              //非递归的先序遍历
        System.out.println("pre-order:");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            stack.add(head);                       //先将头节点入栈。这个操作不在循环
            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value+"     ");
                /**
                 *      因为先序的顺序是“头、左、右”，因此要先压入右孩子。
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


    /**
     * 非递归的后序遍历：
     *      1.后序遍历来源于先序遍历的改动。区别有几点：
     *          1.1.多使用一个栈
     *          1.2.从第一个栈弹出的Node不打印，而是压入到第二个栈
     *          1.3.第一个栈压栈的顺序是根节点、左孩子、右孩子
     *      2.为什么跟先序遍历压栈的顺序不一样？
     *          先序遍历的顺序是：根、左孩子、右孩子。因此先压入根节点，根节点弹出时先
     * 压入栈右孩子，因此右孩子子树的数据会后出，即出栈后为根、左、右的局面
     *          后序遍历的顺序是，左孩子，右孩子，根。因此先压入根，弹出根节点的Node压
     * 入到第二个栈，再弹出的是右子树的节点也会压入到第二个栈，最后弹出的是左子树
     * 的节点同样压入到第二个栈。因此最后从第二个栈弹出的顺序就是左子树、右子树、
     * 根节点，同时也就要求第一个栈压栈的顺序：左右根
     * */
    public static void posOrderUnRecur(Node head){                  //非递归的后序遍历
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        if(head != null){
            stack1.add(head);
            while(!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);            //先序遍历这里是打印操作，后序遍历不打印而是将其压入另一个栈
                if(head.left != null){       //先序遍历的话这里会先压入右孩子
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


    /**
     * 一种更清晰的层序遍历(宽度优先遍历)。。。下面的代码还可以实现计算二叉树的宽度
     * */
    public static void widthOrder(Node head){
        if(head==null) return;
        LinkedList<Node> queue = new LinkedList<>();
        queue.push(head);
        int max = Integer.MIN_VALUE; /*这个变量标记这个二叉树中一层 最多有多少个节点*/
        while(!queue.isEmpty()){
            int size = queue.size();
            max = Math.max(max,size);
            ArrayList<Integer> level = new ArrayList<>(); //记录同一层中所有的节点
            /*for循环会把某一层所有的Node的值添加到一个list；同时会把它们的左右孩子添加到queue*/
            for (int i=0;i<size;i++){
                Node cur = queue.poll();
                level.add(cur.value);
                if(cur.left!=null) queue.add(cur.left);
                if(cur.right!=null) queue.add(cur.right);
            }
            level.forEach(item-> System.out.println(item)); //打印某一层所有的节点
        }
    }





    public static void main(String[] args) {
        Node head=new Node(1);
        head.right=new Node(2);
        head.right.left=new Node(3);
        head.right.right=new Node(4);
//        preOrderUnRecurFuXi(head);
//        posOrderUnRecur_FuXi(head);
//        wOrder_FuXi(head);
//        wOrder_FuXi01(head);
//        inOrderUnRecur(head);
//        preOrderUnRecur(head);
//        posOrderUnRecur(head);
//        posOrderUnRecur_FuXi01(head);
        //cengxufuxi(head);
    }
}
