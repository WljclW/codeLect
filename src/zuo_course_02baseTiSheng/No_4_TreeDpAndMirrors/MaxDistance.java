package zuo_course_02baseTiSheng.No_4_TreeDpAndMirrors;

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

    /**整个逻辑调用的流程*/
    public static int maxDistance(Node head){  //根据节点执行递归得到返回值对象，从返回的对象中拿出指定的信息
        return process(head).maxDistance;
    }


    /**
     *  经过分析发现：任何一个节点需要返回以它为头节点的子树的最大高度、最大距离。把它们封装为一个类对象。
     * */
    public static class Info{       //需要左右子树给我们的是什么信息
        public int maxDistance;
        public int height;
        public Info(int dis,int h){
            this.maxDistance = dis;
            this.height = h;
        }
    }


    /**
     * 完整的树型dp的流程。。。
     * 【说明】
     *      1.step2的时候我们拿到所想要的信息，但不用操心拿的。然后step3基于已经有的信息决策出当前节点的返回值信息，
     *  这样就相当于黑盒逻辑的拆解。。。。结束后，就会发现每一个节点都能根据得到子树的信息计算出自己的信息，同时base
     *  case会直接返回；循环往复，到头来每一个节点的决策都会求出来
     * */
    public static Info process(Node x){
        /*
        * step1：处理base case。如果节点是null,则最大路径是0，高度是0.注意节点是null，所以高度是0不是1
        * */
        if (x == null)       //这就是能直接解决的时候————base case(base case就是终止条件)。
            return new Info(0,0);
        /*
        * step2：假设从自己的左右子树拿到了想要的信息。。
        *   这一步是黑盒，具体的应该怎么拿，实现的逻辑是什么？不用管，我们假设是可以拿到我们想要的信息
        * */
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        /*
        * step3：基于已经有的信息计算当前节点需要返回的信息。
        * */
        //p1,p2,p3就是为了决策当前节点的最大距离以及高度，我们需要这些信息来进行做决策。可省略，这样写是为了使后面的代码变短。
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height+1+rightInfo.height;        //加1是因为当前节点导致路径长度增1.
        //第二步：根据左右子树返回的信息来计算当前节点的返回值
        int height = Math.max(leftInfo.height,rightInfo.height) + 1; //当前节点为根的子树 的最大高度就是 左右子树的最大高度+1
        int distance = Math.max(p3,Math.max(p1,p2));
        /*
        * step4：返回我们决策出的当前节点的返回值
        * */
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
