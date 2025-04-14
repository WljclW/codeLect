package zuo_course_01base.No_5;


/**
 * 判断一棵树是否是平衡二叉树————递归思想
 * */
public class isBalanceBT {
    public static Boolean isBalance(Node head){
        return process(head).isBalanced;
    }

    /**
     * 定义返回类型。是每一个节点为根的子树返回的数据的封装
     * */
    public static class ReturnType{         //我们需要所有子树提供什么信息呢？？
        /**
         *   平衡二叉树这里左右子树需要提供两个信息：①是否平衡   ②高度。
         *   且左右子树需要提供的是同样的两个信息————并集后依然是两个信息，因此返回类
         * 型就是包含二者的结合体。
         * */
        public boolean isBalanced;
        public int height;
        public ReturnType(boolean isB,int h){           //形参的名字写成isBalanced、height的时候运行结果是错误的，为什么？？
            isBalanced = isB;
            height = h;
        }
    }

    public static ReturnType process(Node x){
        /**1. base case处理*/
        if (x == null)      //空节点是平衡的，并且高度是0
            return new ReturnType(true,0);

        /**2. 假设利用黑盒拿到了左右子树返回的，我们需要的数据*/
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        /**3. 基于左右子树返回的结果，如何计算出当前节点应该返回的信息。。。即黑盒
         * 逻辑的实现，最终实现闭环*/
        /**3.1 根据已有信息，计算当前节点为根的子树的高度————即返回值的其中一个信息*/
        int height = Math.max(leftData.height,rightData.height)+1;  //高度就是左右子树高度的最大值+1,1表示x的高度。
        /**3.2 根据已有的信息，计算当前的节点是不是平衡的————这是返回值的第二个信息*/
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                            && Math.abs(leftData.height-rightData.height)<2;   //平衡的判断标准————左右子树都平衡并且左右子树高度差小于2
        /**3.3 将两个信息封装，并返回*/
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
