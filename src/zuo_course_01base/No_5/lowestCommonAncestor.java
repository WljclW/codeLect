package zuo_course_01base.No_5;


/**
 *      给出两个节点，返回它们最早相交的父节点。
 * */
public class lowestCommonAncestor {
    public static Node m02(Node head,Node n1,Node n2){
        if(head==null||head==n1||head==n2)
            return head;
        /**
         *      如果某一个子树没有n1，也没有n2，那该子树的返回值就是null。
         *
         * */
        Node left=m02(head.left,n1,n2);
        Node right=m02(head.right,n1,n2);
        if(left!=null&&right!=null)
            return head;
        //左右两棵树并不都有返回值
        return left!=null?left:right;
    }
}
