package zuo_course_02baseTiSheng.No_4;

/**
 *  求二叉树任意两节点的最大距离。
 * */

public class MaxDistance {
    public static class Node{       //二叉树节点的定义
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static int maxDistance(Node head){       //根据节点执行递归得到返回值对象，从返回的对象中拿出指定的信息
        return process(head).maxDistance;
    }

    public static class Info{       //需要左右子树给我们的是什么信息
        /**
         *  经过分析发现：任何一个节点需要返回以它为头节点的子树的最大高度、最大距离。把它们封装为一个类对象。
         * */
        public int maxDistance;
        public int height;
        public Info(int dis,int h){
            this.maxDistance = dis;
            this.height = h;
        }
    }


    public static Info process(Node x){
        /**
         * 这是递归函数的主题
         * */
        if (x == null)                      //这就是能直接解决的时候————base case(base case就是终止条件)。
            return new Info(0,0);
        //第一步：递归处理X节点的左右子树
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //p1,p2,p3就是为了决策当前节点的最大距离以及高度，我们需要这些信息来进行做决策。可省略，这样写是为了使后面的代码变短。
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height+1+rightInfo.height;        //加1是因为当前节点导致路径长度增1.
        //第二步：根据左右子树返回的信息来计算当前节点的返回值
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        int distance = Math.max(p3,Math.max(p1,p2));
        //第三步：返回
        return new Info(distance,height);
    }


    public static void main(String[] args) {
        Node head=new Node(0);
        Node n1=new Node(0);
        Node n2=new Node(0);
        head.left=n1;
        head.right=n2;
        System.out.println(maxDistance(head));
    }
}
