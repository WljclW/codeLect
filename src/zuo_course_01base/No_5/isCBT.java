package zuo_course_01base.No_5;

import java.util.LinkedList;

/**
 *  判断一棵树是不是完全二叉树？按层次遍历，因此需要借助队列
 *
 *
 * */
public class isCBT {
    public static boolean isFBT(Node head){
        if (head == null)
            return true;
        LinkedList<Node> que = new LinkedList<>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        que.add(head);
        while(!que.isEmpty()){
            head = que.poll();
            l = head.left;
            r = head.right;
            if(
                    (leaf && (l!=null||r!=null))        //leaf为true，表示后面的所有节点都应该是叶子节点；l!=null||r!=null表明当前节点不是叶子节点
                    || (l == null && r!=null)           //如果有右孩子但是没有左孩子，那肯定不是满二叉树(满二叉树要求从上到下，从左到右)。
            )
                /**
                 *      这个if的条件解析：
                 *          leaf && (l!=null||r!=null)————leaf已为true但是碰到了非叶节点
                 *          (l == null && r!=null)————表明有右孩子但是没有左孩子。
                 *
                 * */
                return false;
            if (l!=null)
                que.add(l);
            if (r!=null)
                que.add(r);
            if (l==null || r == null)       //如果发现某个节点的左右孩子不全，就修改leaf为true。一旦改为true，后序它就一直都是true。
                leaf = true;
        }
        return true;
    }


    public static boolean isFBT_Fuxi(Node head) {
        if (head == null)
            return true;
        LinkedList<Node> deque = new LinkedList<>();
        deque.add(head);
        boolean flag = false;
        while(!deque.isEmpty()){
            head = deque.poll();
            Node l = head.left;
            Node r = head.right;
            if(
                    (flag && (r!=null || l!=null))
                    ||
                            (l==null && r!=null)
            )
                return false;
            if (l==null || r==null)
                flag = true;
            if (l!=null)
                deque.add(l);
            if (r!=null)
                deque.add(r);
        }
        return true;
    }


//    public static boolean isFBT_FuXi02(Node head){
//        if (head == null)
//            return true;
//        LinkedList<Node> deque = new LinkedList<>();
//        deque.add(head);
//        boolean flag = false;
//        while(!deque.isEmpty()){
//            head = deque.poll();
//            if (head.left == null && head.right != null) {
//                return false;
//            }
//            if (flag && (head.right != null || head.left != null)) {
//                return false;
//            }
//            if (head.right == null || head.left == null)
//                flag = true;
//            if (head.left != null) {
//                deque.add(head.left);
//            }
//            if (head.right != null) {
//                deque.add(head.right);
//            }
//        }
//        return true;
//    }


        public static void main(String[] args) {
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);
        Node n4=new Node(4);
        Node n5=new Node(5);
        Node n6=new Node(6);
        n1.left=n2;
        n1.right=n3;
        n2.left=n4;
//        n2.right = n5;
//        n3.left = n6;
        n3.left=n5;
        n3.right=n6;
        System.out.println(isFBT(n1));
//        System.out.println(isFBT_FuXi02(n1));
    }
}
