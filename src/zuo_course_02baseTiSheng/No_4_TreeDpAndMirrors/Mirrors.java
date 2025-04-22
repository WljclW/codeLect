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


    public static void morrirs(Node head){
        if (head == null)
            return;
        Node cur = head;
        Node mostRight = null;
        while(cur!=null){
            System.out.println(cur.value); //打印出这个二叉树的morrirs序列
            mostRight = cur.left;       //mostRight先来到cur的左节点
            if (mostRight != null){     //mostRight != null就是说cur有左孩子
                /*原理的2)，如果有左孩子，则来到左孩子的最有的节点*/
                while(mostRight.right!=null && mostRight.right!=cur){
                    mostRight = mostRight.right;
                }
                /*原理中2)的内容，根据左子树最右节点的右指针，做不同的操作*/
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


    public static void morrisPre(Node head){
        if(head==null)
            return;
        Node cur=head;
        Node mostRight=null;
        while(cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){                                        //有左子树，则先进左子树
                while(mostRight.right!=null&&mostRight.right!=cur) {
                    mostRight = mostRight.right;
                }
                if(mostRight.right==null){//第一次来到 ，打印
                    System.out.println(cur.value);
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }
                else{
                    mostRight.right=null;
                }
            }
            else{                                                       //无左子树，直接打印
                System.out.println(cur.value);
            }
            cur=cur.right;
        }
    }


//    public static void morrirs(Node head){
//        if (head == null)
//            return;
//        Node cur = head;
//        Node mostRight = null;
//        while(cur!=null){
//            mostRight = cur.left;       //mostRight是cur的左孩子
//            if (mostRight != null){     //mostRight != null就是说cur有左孩子
//                while(mostRight.right!=null && mostRight.right!=cur){
//                    mostRight = mostRight.right;
//                }
//                if (mostRight.right == null){
//                    mostRight.right = cur;
//                    cur = cur.left;
//                    continue;           //继续下一次循环，也就是说执行这里时下面的29行的else并不会执行了
//                }else{
//                    mostRight.right = null;
//                }
//            }else{          //细节中的1），没有左孩子，则cur向右移动
//                System.out.println(cur.value);
//                cur = cur.right;
//            }
//        }
//    }

    public static void main(String[] args) {
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);
        Node n4=new Node(4);
        Node n5=new Node(5);
        Node n6=new Node(6);
        Node n7=new Node(7);
        Node n8=new Node(8);
        n1.left=n2;
        n1.right=n3;
        n2.left=n4;
        n2.right=n5;
        n3.left=n6;
        n3.right=n7;
        morrirs(n1);
    }
}
