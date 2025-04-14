package zuo_course_01base.No_5;

/**
 *  二叉树的递归遍历————前序、中序、后序
 * */

public class digui_bianli {
    /**
     *   递归序 的完整逻辑。
     *   同一个节点会来到三次，不同的次序只是打印该节点值的时机不同。
     * 前序遍历是在第一次来到某个节点就打印；
     * 中序遍历是在第二次来到某个节点时打印；
     * 后序遍历是最后一次来到某个节点才打印。
     * */
    public static void f_siKao(Node head){
        if (head == null)
            return;
        /*这里是第一次来到某个节点*/
        f_siKao(head.left);
        /*这里是第二次来到某个节点*/
        f_siKao(head.right);
        /*这里是第三次回到某个节点。只不过这次回来发现没有语句了，整个方法对应的栈帧出栈*/
    }

    public static void preOrder(Node head){
        if (head == null)
            return;
        System.out.println(head.value + "   ");
        preOrder(head.left);
        preOrder(head.right);
    }


    public static void inOrder(Node head){
        if (head == null)
            return;
        inOrder(head.left);
        System.out.println(head.value + "   ");
        inOrder(head.right);
    }


    public static void posOrder(Node head){
        if (head == null)
            return;
        posOrder(head.left);
        posOrder(head.right);
        System.out.println(head.value + "   ");
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
