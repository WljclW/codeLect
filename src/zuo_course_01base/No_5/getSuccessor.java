package zuo_course_01base.No_5;

/**
 *  得到中序遍历某个节点的直接后继，节点有指向父节点的指针。
 * */

public class getSuccessor {
    public static parNode getHouJi(parNode node){
        /*1. 特殊情况的考虑*/
        if (node == null)
            return null;
        /*2. 如果右子树不是空，则后继节点就在右子树上。
        * 那具体在右子树的什么地方呢？答：右子树上中序的第一个节点，也就是右子树上最左的节点*/
        if (node.right!=null){
            return getMostLeft(node.right);
        }else{
            /*
             * 3.进入else语句块则表明没有右孩子节点，也就是说给的形参就是以它为根节点的子树的
             *  中序遍历的最后一个节点(本来是要有右子树的节点，但是它的右子树是空的)。。那要在
             * 整棵树找它的后继节点就需要向上追溯父节点了。
             * */
            parNode parent = node.parent;               //这里的题目大多数在循环开始前需要进行变量的赋值、入栈等操作。
            while(parent != null && parent.left != node){
                /**
                 *      循环结束的条件：parent != null但是parent.left == node，也就是说找到了一个节点是它父亲
                 * 的左孩子，这个时候就返回parent即为所求。。。原因：“是它parent的左孩子”说明是parent的右孩子 同
                 * 时 进入到这个while说明没有右孩子，因此它就是以它为根的子树的最后一个节点，同时它还是父亲的右子树，
                 * 所以它就是以父亲为根的子树的"先序的最后一个节点"
                 *      parent == null，这个时候表示已经追溯到根节点了但没有找到后继，因此该点是没有后继节点的，也
                 * 就是说这个形参node是当前二叉树的最右侧的节点。
                 * */
                node = parent;          //node向上走，node来到它父亲的位置
                parent = parent.parent;        //parent也来到它父亲的位置
            }
            return parent;
        }
    }

    private static parNode getMostLeft(parNode node) {      //该函数是为了得到形参node右子树的最左边的那个节点
        if (node == null)
            return null;
        /**
         *  顺着某个节点一路向下向左，找最左边的节点
         * */
        while(node.left!=null)
            node = node.left;
        return node;
    }

    public static void main(String[] args) {
        parNode n4 = new parNode(4, null, null);
        parNode n5 = new parNode(5, null, null);
        parNode n2 = new parNode(2,n4,n5);
        parNode n3 = new parNode(3,null,null);
        parNode n1 = new parNode(1,n2,n3);
        n1.parent = null;
        n2.parent = n1;
        n3.parent = n1;
        n4.parent = n2;
        n5.parent = n2;
        System.out.println(getHouJi(n3).value); //n3节点的后继是空，因此这样校验时出现空指针异常，但是程序是正确的
    }


}
