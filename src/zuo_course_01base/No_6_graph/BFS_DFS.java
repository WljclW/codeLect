package zuo_course_01base.No_6_graph;



import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class BFS_DFS {

    //宽度优先遍历
    public static void BFS(Node node){
        if (node==null)
            return;
        /**
         *      deque队列：先进先出，
         *
         * */
        Deque<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        /**
         *      首先将头节点加入到队列和哈希表。
         *      【总结：图、二叉树这里，在开始循环之前都会先把一个节点或者东西加入到栈或者队列中；然后不断循环的条件就是栈或者队列
         *      非空】
         * */
        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()){
            /**
             *      栈不空的情况下需要从队列中弹出元素并访问。
             *      【注意：BFS是从队列中出一个元素后，紧接着对其进行访问。这与DFS是不相同的】
             * */
            node = queue.pop();
            System.out.println(node.value+"     ");     //如果解决某问题时需要BFS的思想，也就是宽度遍历并处理。就是要把这里换成处理问题(操作)的代码
            /**
             *      当前元素访问结束后，需要处理它“所有”的邻居节点
             * */
            for (Node n:node.nexts){                    //查看当前点的所有邻居节点
                if(!set.contains(n)){
                    /**
                     *      如果发现当前点的某些邻居节点还不在set，表明第一次遇到。需要同时加入到set和Deuqeu。
                     *      加入到set是为了以后遇到这个节点不能在访问它了，因为已经碰到过了。
                     *      加入到Deque是为了访问。
                     *
                     * */
                    set.add(n);
                    queue.add(n);
                }
            }
        }
    }

//    public static void BFS_FuXi(Node node){
//        if (node == null)
//            return;
//        LinkedList<Node> queque = new LinkedList<>();
//        HashSet<Node> set = new HashSet<>();
//        queque.add(node);
//        set.add(node);
//        while(!queque.isEmpty()){
//            node = queque.poll();
//            System.out.println(node.value+"     ");
//            for (Node n:node.nexts)
//                if (!set.contains(n)){
//                    set.add(n);
//                    queque.add(n);
//                }
//        }
//    }

//    public static void BFS_FuXi01(Node node){
//        if (node == null)
//            return;
//        LinkedList<Node> que = new LinkedList<>();
//        HashSet<Node> set = new HashSet<>();
//        que.add(node);
//        set.add(node);
//        while(!que.isEmpty()){
//            Node cur = que.poll();
//            System.out.println(cur.value + "    ");
//            for (Node n: cur.nexts)
//                if (!set.contains(n)){
//                    set.add(n);
//                    que.add(n);
//                }
//        }
//    }


    //深度优先遍历
    public static void DFS(Node node){
        if (node==null)
            return;
        /**
         *      stack栈:完成元素的访问。并且记录了遍历的路径
         *      set集合：同样的，跟BFS一样，是为了不重复访问元素。
         *
         * */
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value + "   ");         //DFS是元素入栈的时候就访问，域BFS不同。
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next:cur.nexts){
                /**
                 *  针对cur的所有邻居节点————
                 *              一方面，如果找到了某个邻居节点不在set中，就说明还没有遇到过，因此可以加入set和stack中。
                 *              另外一方面，由于深度优先遍历，因此每一次并不是访问当前节点的所有邻居，因此找到一个满足要
                 *          求的邻居就需要进行后序操作，不能再去访问其他的邻居。
                 * */
                if (!set.contains(next)){
                    set.add(next);
                    /**
                     * 将出栈的节点和不在set中的节点依次入栈。
                     * */
                    stack.push(cur);
                    stack.push(next);
                    System.out.println(next.value + "  ");          //如果解题时需要利用DFS的思想，就需要把这里打印的代码换成具体处理的代码
                    break;              //只要找到了了一个不在set中的邻居就加入set然后进行下一轮的while循环。
                }
            }
        }
    }


//    public static void DFS_FuXi(Node node){
//        if (node == null)
//            return;
//        Stack<Node> stack = new Stack<>();
//        HashSet<Node> set = new HashSet<>();
//        stack.add(node);
//        set.add(node);
//        System.out.println(node.value+"     ");
//        while(!stack.isEmpty()){
//            Node cur = stack.pop();
////            System.out.println(cur.value+"  ");
//            for (Node n:node.nexts){
//                if (!set.contains(n)){
//                    set.add(n);
//                    stack.push(cur);
//                    stack.push(n);
//                    System.out.println(n.value+"    ");
//                    break;
//                }
//            }
//        }
//    }


    public static void DFS_FuXi01(Node node){
        if (node == null)
            return;
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value+"     ");
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node n:cur.nexts){
                if (!set.contains(n)){
                    set.add(n);
                    stack.add(n);
                    System.out.println(n.value+"    ");
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);
        Node n4=new Node(4);
        Edge e1=new Edge(1,n1,n2);
        Edge e2=new Edge(1,n1,n3);
        Edge e3=new Edge(1,n2,n3);
        Edge e4=new Edge(1,n3,n4);
        n1.nexts.add(n2);
        n1.nexts.add(n3);
        n2.nexts.add(n3);
        n3.nexts.add(n4);
        n1.edges.add(e1);
        n1.edges.add(e2);
        n2.edges.add(e3);
        n3.edges.add(e4);
        Graph graph=new Graph();
        graph.nodes.put(1,n1);
        graph.nodes.put(2,n2);
        graph.nodes.put(3,n3);
        graph.nodes.put(4,n4);
        graph.edges.add(e1);
        graph.edges.add(e2);
        graph.edges.add(e3);
        graph.edges.add(e4);
        //dfsfuxi(n1);
//        BFS(n2);
//        BFS_FuXi(n2);
        DFS(n1);
        DFS_FuXi01(n1);
    }
}
