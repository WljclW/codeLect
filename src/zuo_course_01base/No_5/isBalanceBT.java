package zuo_course_01base.No_5;


/**
 *   判断一棵树是否是平衡二叉树————递归思想
 *
 * */
public class isBalanceBT {
    public static Boolean isBalance(Node head){
        /**
         *      这个方法充分的体现了二叉树的递归套路。
         * */
        return process(head).isBalanced;
    }
    public static class ReturnType{         //我们需要所有字数提供什么信息呢？？
        /**
         *      平衡二叉树这里左右子树需要提供两个信息：①是否平衡   ②高度。
         *      且左右子树需要提供的是同样的两个信息————并集后依然是两个信息，因此返回类型就是包含二者的结合体。
         * */
        public boolean isBalanced;
        public int height;
        public ReturnType(boolean isB,int h){           //形参的名字写成isBalanced、height的时候运行结果是错误的，为什么？？
            isBalanced = isB;
            height = h;
        }
    }

    public static ReturnType process(Node x){
        if (x == null)                                  //空节点是平衡的，并且高度是0
            return new ReturnType(true,0);

        //递归处理左右子树
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        //需要把返回的2个信息如何得到进行说明
        int height = Math.max(leftData.height,rightData.height)+1;          //高度就是左右子树高度的最大值+1,1表示x的高度。
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                            && Math.abs(leftData.height-rightData.height)<2;        //平衡的判断标准————左右子树都平衡并且左右子树高度差小于2
        return new ReturnType(isBalanced,height);
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
        n3.right=n4;
//        n3.left = n5;           //true
        n4.right=n5;              //false
        System.out.println(isBalance(n1));
    }
}
