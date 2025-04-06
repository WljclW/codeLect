package zuo_course_01base.No_5;

/**
 *      得到中序遍历某个节点的直接后继，节点有指向父节点的指针。
 * */

public class getSuccessor {
    public static parNode getHouJi(parNode node){
        if (node == null)
            return null;
        if (node.right!=null){
            /**
             *      若右孩子不为空，则中序的后继时右子树的最左边的节点
             * */
            return getMostLeft(node.right);
        }else{
            /**
             *      进入else语句块则表明没有右孩子节点，也就是说给的形参就是以它为根节点的子树的
             *  中序遍历的最后一个节点。。那要在整棵树找它的后继节点就需要向上追溯父节点了。
             * */
            parNode parent = node.parent;               //这里的题目大多数在循环开始前需要进行变量的赋值、入栈等操作。
            while(parent != null && parent.left != node){
                /**
                 *      循环结束的条件：parent != null但是parent.left == node，也就是说找到了一个节点是它父亲
                 *                的左孩子，这个时候就返回parent即为所求。
                 *                    parent == null，这个时候表示已经追溯到根节点了但没有找到后继，因此该点是
                 *                没有后继节点的，也就是说这个形参node是当前二叉树的最右侧的节点。
                 *
                 * */
                node = parent;                              //node向上走，node来到它父亲的位置
                parent = parent.parent;                     //parent也来到它父亲的位置
            }
            return parent;
        }
    }

    private static parNode getMostLeft(parNode node) {      //该函数是为了得到形参node右子树的最左边的那个节点
        if (node == null)
            return null;
        /**
         *      顺着某个节点一路向下向左，找最左边的节点
         * */
        while(node.left!=null)
            node = node.left;
        return node;
    }



//    public static parNode getHouJi_FuXi(parNode node){
//        if (node.right != null)
//            return getMostLeft_FuXi(node.right);
//        else{
//            parNode parent = node.parent;
//            while(parent != null && parent.left != node){
//                node = parent;
//                parent = parent.parent;
//            }
//            return parent;
//        }
//    }
//
//    private static parNode getMostLeft_FuXi(parNode node) {
//        while (node.left != null)
//            node = node.left;
//        return node;
//    }

    public static void main(String[] args) {
        parNode n2 = new parNode(2,null,null);
        parNode n3 = new parNode(3,null,null);
        parNode n1 = new parNode(1,n2,n3);
        n1.parent = null;
        n2.parent = n1;
        n3.parent = n1;
//        System.out.println(getHouJi_FuXi(n2).value);
    }


}
