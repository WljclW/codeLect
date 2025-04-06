package zuo_course_01base.No_5;

/**
 *      二叉树的递归遍历————前序、中序、后序
 *
 * */

public class digui_bianli {
    public static void f_siKao(Node head){
        if (head == null)
            return;
        f_siKao(head.left);
        //这里函数调用完了会回到本体
        f_siKao(head.right);
        //f_siKao(head.right);调用完了依然会回到本体，回来发现没有语句了因此执行结束
    }

    public static void preOrder(Node head){
        if (head == null)
            return;
        System.out.println(head.value + "   ");
        preOrder(head.left);
        //这里函数调用完了会回到本体
        preOrder(head.right);
        //f_siKao(head.right);调用完了依然会回到本体，回来发现没有语句了因此执行结束
    }


    public static void inOrder(Node head){
        if (head == null)
            return;
        inOrder(head.left);
        System.out.println(head.value + "   ");
        //这里函数调用完了会回到本体
        inOrder(head.right);
        //f_siKao(head.right);调用完了依然会回到本体，回来发现没有语句了因此执行结束
    }


    public static void posOrder(Node head){
        if (head == null)
            return;
        posOrder(head.left);
        //这里函数调用完了会回到本体
        posOrder(head.right);
        System.out.println(head.value + "   ");
        //f_siKao(head.right);调用完了依然会回到本体，回来发现没有语句了因此执行结束
    }


    public static void main(String[] args) {
        Node head=new Node(1);
        head.right=new Node(2);
        head.right.left=new Node(3);
        head.right.right=new Node(4);
//        posOrder(head);
//        inOrder(head);
//        preOrder(head);
        posOrder(head);
    }
}
