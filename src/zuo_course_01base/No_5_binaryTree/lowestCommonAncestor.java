package zuo_course_01base.No_5_binaryTree;


/**
 * 给出两个节点，返回它们最早相交的父节点。
 */
public class lowestCommonAncestor {
    public static Node m02(Node head, Node n1, Node n2) {
        if (head == null || head == n1 || head == n2)
            return head;
        Node left = m02(head.left, n1, n2);
        Node right = m02(head.right, n1, n2);
        /*如果左子树、右子树查找的结果都不是空，表明当前的节点head就是 最近公共祖先*/
        if (left != null && right != null)
            return head;
        //左右两棵树并不都有返回值
        return left != null ? left : right;
    }
}
