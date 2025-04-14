package zuo_course_01base.No_5;


import java.util.Stack;

/**
 * 判断一棵树是否是二叉搜索树(二叉搜索树最大的特点就是中序遍历是非递减的序列):
 *           不管哪种方法，用的是都是中序遍历，关键点就是搜索二叉树的中序遍历一个是一个非递减的序列。
 *      中序遍历有递归的非递归的两个版本，这里就针对两个版本进行改动实现。改动的主体部分：之前遍历是为了
 *          打印所有节点的值，这里并不需要打印，我们判断每到一个点是不是满足搜索二叉树的要求即可，如果不
 *          满足了，立刻返回。
 * */
public class isBST {

    public static int preValue = Integer.MIN_VALUE;     //定义一个变量来存放前一个结点的值

    /**
     *   方法1：需要按照递归中序遍历，并且声明一个变量存放前一个节点的值
     * */
    public static Boolean is_BST01(Node head){
        if (head == null)
            return true;
        /*
        * step1:递归中序遍历时，递归左子树的部分
        * */
        Boolean isLeft_BST = is_BST01(head.left);
        if (!isLeft_BST)
            return false;
        /*
        * step2:下面的代码其实是 递归中序时打印节点的操作(如果是遍历的话这里应该是打印节点的值)。
        * 只不过这里需要判断搜索树，要检验这个节点的值是不是大于之前节点的值（小于时说明不是搜索树，大于时更新preValue）
        * */
        if (head.value < preValue)
            return false;
        else
            preValue = head.value;
        /*
        * step3:走到这里说明某节点及其左子树都是符合搜索树的。。因此以某节点为根的树是不是搜索树，就取决于它的右子树
        *   是不是搜索树
        * */
        return is_BST01(head.right);
    }




    /**
     * 方法2：基于非递归的中序遍历的改进：
     *      按照中序遍历读出来的值放到一个list中，看是不是升序的。同样需要使用一个变量来存储前一个结点的值
     * */
    public static Boolean is_BST03(Node head) {
        int pre_value = Integer.MIN_VALUE;
//        System.out.println("in-order:");              //中序遍历打印的语句
        Stack<Node> stack = new Stack<>();
        if (head != null) {
            while (!stack.isEmpty() || head != null) {
                if (head != null) {               //进入if说明head节点不为空，则需要顺着该节点找它的左子树
                    stack.push(head);           //把head节点压入栈
                    head = head.left;           //把head指针指向之前节点的左孩子节点。
                } else {                          //进入else说明head为空，此时需要从栈弹出一个节点(这个节点的左孩子必为空)并打印，顺着它的右孩子的左子树继续循环。
                    /**
                     *  进入else语句块说明if不满足，也就是某个节点左边界走到头了，因此此时需要弹出一个节点去该节点的右子树重复if。
                     * */
                    head = stack.pop();
//                    System.out.println(head.value + "   ");       //这里我们不需要打印节点的值，只是判断一下是不是满足二叉搜索树的要求
                    if (head.value <= pre_value){
                        return false;                           //如果小于中序遍历前一个结点的值，返回不是二叉搜索树
                    }else{
                        pre_value = head.value; //更新前一个节点的值 这个标志变量
                    }
                    head = head.right;
                }
            }
        }
        return true; //除了while说明节点都遍历完了，但是所有的节点都符合
    }



    public static void main(String[] args) {
        Node n1=new Node(3);
        Node n2=new Node(5);
        Node n3=new Node(4);
        n1.left=n2;
        n1.right=n3;
        System.out.println(is_BST01(n1));
//        System.out.println(isBST03_FuXi(n1));
    }
}
