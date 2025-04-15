package zuo_course_01base.No_5;

import java.util.LinkedList;

/**
 *  判断一棵树是不是完全二叉树？按层次遍历，因此需要借助队列。
 *  思路：借助层序遍历的是实现，什么情况下判断就不是完全二叉树了呢？
 *      什么情况就返回false:某一个节点有右节点但是没有左节点 或者 之前已经碰到了左右孩子不全但是呢当前的节点有孩子节点。
 *      什么情况下返回true:如果所有的系欸但都遍历一遍了，就返回true.
 *      基于上面的思路，我们需要一个标志变量leaf，表示是不是遇到了某个节点的左右孩子不双全，一旦更新为true，后续就一直
 *   为true，暗示后续的所有节点都必须是叶子节点
 * */
public class isCBT {
    public static boolean isFBT(Node head){
        if (head == null)
            return true;
        LinkedList<Node> que = new LinkedList<>();
        boolean leaf = false; //表示是否碰到过一个"左右孩子不全的节点"。遇到过了就说明后面的必须都是叶子节点
        Node l = null;
        Node r = null;
        que.add(head);
        while(!que.isEmpty()){
            head = que.poll();
            l = head.left;
            r = head.right;
            /*层序遍历的区别点：不用for循环，因为每一层不用区分*/

            /*判断是不是违规*/
            if(
                    (leaf && (l!=null||r!=null))     //leaf为true，表示后面的所有节点都应该是叶子节点；l!=null||r!=null表明当前节点不是叶子节点
                    || (l == null && r!=null)        //如果有右孩子但是没有左孩子，那肯定不是满二叉树(满二叉树要求从上到下，从左到右)。
            )
                return false;

            /*层序遍历的操作*/
            if (l!=null)
                que.add(l);
            if (r!=null)
                que.add(r);

            /*更新leaf标志*/
            if (l==null || r == null)       //如果发现某个节点的左右孩子不全，就修改leaf为true。一旦改为true，后续它就一直都是true。
                leaf = true;
        }
        return true;
    }


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
