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
            mostRight = cur.left;       //mostRight是cur的左孩子
            if (mostRight != null){     //mostRight != null就是说cur有左孩子
                while(mostRight.right!=null && mostRight.right!=cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;           //继续下一次循环，也就是说执行这里时下面的29行的else并不会执行了
                }else{
                    mostRight.right = null;
                }
            }else           //细节中的1），没有左孩子，则cur向右移动
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
        n1.left=n2;
        n1.right=n3;
        n3.right=n4;
        morrirs(n1);
    }
}
