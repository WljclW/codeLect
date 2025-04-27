package zuo_course_02baseTiSheng.No_4_TreeDpAndMirrors;

public class Mirrors {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }


    /**
     * 1. morris序列中，有左孩子的节点会遍历两次，没有左孩子的节点只会来一次。
     * 2. 如何判断是第几次来到某个节点？
     *      2.1 首先没有左孩子的节点只会来到一次；
     *      2.2 如果有左孩子，则这个节点(cur)会来到两次。
     *          a.如果cur左子树的最右孩子的右指针是null，说明是第一次来到cur；(补充说明：此时依据morris规则会将右指针指向cur)
     *          b.如果cur左子树的最右孩子的右指针指向cur，说明是第二次来到cur；
     * */
    public static void morrirs(Node head){
        if (head == null)
            return;
        Node cur = head;
        Node mostRight = null;
        while(cur!=null){
            System.out.println(cur.value); //打印出这个二叉树的morrirs序列
            mostRight = cur.left;       //mostRight先来到cur的左节点
            if (mostRight != null){     //mostRight != null就是说cur有左孩子
                /*原理的2)。如果有左孩子，则来到左孩子的最右的节点*/
                while(mostRight.right!=null && mostRight.right!=cur){
                    mostRight = mostRight.right;
                }
                /*原理中2)的内容。根据左子树最右节点的右指针，做不同的操作。
                * 出了while循环只有两种可能，
                *       要么mostRight的右孩子是null，说明是第一次来到这个节点；
                *       要么mostRight的右孩子指向cur节点，说明是第二次来到这个节点*/
                if (mostRight.right == null){ /*原理中2)的1，如果最右节点的右孩子是空，则指向当前节点*/
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{ /*原理中2)的2，如果最右节点的右孩子不是空(其实就必然指向当前节点cur),则将右孩子指向空*/
                    mostRight.right = null;
                }
            }      //细节中的1），没有左孩子，则cur向右移动
            cur = cur.right;
        }
    }


    /**
     * morris序列的基础上如何实现前序遍历？
     *    能来到两次的节点在第一次来到的时候就打印，只来到一次的节点在来到时就直接打印
     * */
    public static void morrisPre(Node head){
        if(head==null)
            return;
        Node cur=head;
        Node mostRight=null;
        while(cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){              //有左子树，则先进左子树
                while(mostRight.right!=null&&mostRight.right!=cur) {
                    mostRight = mostRight.right;
                }
                if(mostRight.right==null){ //能来到两次的节点在第一次来到时，打印
                    System.out.println(cur.value);
                    mostRight.right=cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {         //无左子树，说明cur节点只会来一次，因此直接打印
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }


    /**
     * 在morris序列的基础上如何实现中序遍历？
     *     遍历到两次的节点在第二次来到的时候打印，只能遍历一次的节点在第一次就打印
     * */
    public static void morrisIn(Node head){
        if(head==null) return;
        Node cur = head;
        Node mostRight = null;
        while(cur!=null){
            mostRight = cur.left; //先来到cur的左孩子
            if (mostRight!=null){
                //while循环一直走右孩子
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right==null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }


    public static void main(String[] args) {
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);
        Node n4=new Node(4);
        Node n5=new Node(5);
        Node n6=new Node(6);
        Node n7=new Node(7);
        n1.left=n2;
        n1.right=n3;
        n2.left=n4;
        n2.right=n5;
        n3.left=n6;
        n3.right=n7;
        morrirs(n1);
        System.out.println("=========pre======");
        morrisPre(n1);
        System.out.println("==========In========");
        morrisIn(n1);
    }
}
