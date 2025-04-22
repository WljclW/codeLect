package zuo_course_01base.No_5_binaryTree;


/**
 *判断是不是二叉搜索树的递归版本。——————体会二叉树的递归套路
 * */

public class isBST_taoLu_BTdigui {
    /**
     * 对于任何一个节点，它的左子树、右子树分别返回三个信息：最大值、最小值、是否是搜索树。
     * */
    public static class ReturnData{
        int min;
        int max;
        boolean isBST;
        public ReturnData(int min,int max,boolean isBST){
            this.min=min;
            this.max=max;
            this.isBST=isBST;
        }
    }

    public static ReturnData process(Node x){
        /**1. base case的处理*/
        if(x == null){
            return null;
        }

        /**2. 默认从左右子树拿到了想要的数据。。黑盒怎么实现不用管，我们只是假设就能拿
         * 到ReturnData这种类型的数据，这就是我们需要的信息*/
        ReturnData leftData = process(x.left);
        ReturnData rightData = process(x.right);

        /**3. 对于当前节点，利用已有信息计算需要返回的信息。。实现黑盒的拆解，拆解的完整内容就是计
         * 算当前节点需要返回的信息*/
        /**3.1 计算需要返回的min，max信息。min就是左右的min和当前节点取min；max就是左右的max和当前节点取max*/
        int min = x.value;
        int max = x.value;
        if (leftData != null){  //如果leftData不为空，求左子树的最大值、最小值。。
            min = Math.min(min,leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null){
            min = Math.min(min,rightData.min);
            max = Math.max(max, rightData.max);
        }
        /**3.2 计算需要返回的isBST信息。即当前节点为根的子树 是不是平衡二叉树*/
        boolean isBST = true; //先默认是true，然后判断是不是违规
        if (leftData!=null && (!leftData.isBST || leftData.max >= x.value))
        /**
         *   !leftData.isBST为真时表明左子树不是搜索树，将isBST置为false。
         *   leftData.max >= x.value表明父节点小于左子树某节点的值，将isBST置为false。
         * */
            isBST = false;
        if (rightData!=null && (!rightData.isBST || x.value >= rightData.min))
        /**
         *   !rightData.isBST为真时表明左子树不是搜索树，将isBST置为false。
         *   x.value >= rightData.min表明父节点大于右孩子某节点的值，将isBST置为false。
         * */
            isBST = false;

        return new ReturnData(min,max,isBST);
    }


    public static void main(String[] args) {
        Node n1=new Node(2);
        Node n2=new Node(2);
        Node n3=new Node(3);
        n1.left=n2;
        n1.right=n3;
//        System.out.println(is_BST03(n1));
        System.out.println(process(n1).isBST);
    }
}
