package zuo_course_01base.No_5;


/**
 *
 *       判断是不是二叉搜索树的递归版本。——————体会二叉树的递归套路
 *
 * */

public class taoLu_BTdigui {
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
        if(x == null){
            return null;
        }

        ReturnData leftData = process(x.left);
        ReturnData rightData = process(x.right);

        int min = x.value;
        int max = x.value;
        if (leftData != null){              //如果leftData不为空，求左子树的最大值、最小值。。
            /**
             *      虽然我们并不需要左子树的最小值，但是我们需要右子树的最小值。为了递归能进行，左右子树需要返回同样的信息。
             * */
            min = Math.min(min,leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null){
            min = Math.min(min,rightData.min);
            max = Math.max(max, rightData.max);
        }

        boolean isBST = true;
        if (leftData!=null && (!leftData.isBST || leftData.max >= x.value))
            /**
             *      !leftData.isBST为真时表明左子树不是搜索树，将isBST置为false。
             *      leftData.max >= x.value表明父节点小于左孩子节点的值，将isBST置为false。
             * */
            isBST = false;
        if (rightData!=null && (!rightData.isBST || x.value >= rightData.min))
            /**
             *      !rightData.isBST为真时表明左子树不是搜索树，将isBST置为false。
             *      x.value >= rightData.min表明父节点大于右孩子节点的值，将isBST置为false。
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
