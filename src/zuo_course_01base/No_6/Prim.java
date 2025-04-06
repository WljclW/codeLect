package zuo_course_01base.No_6;

import java.util.*;

public class Prim {
    public static Set<Edge> prim(Graph graph){
        /**
         *      HashSet<Edge> result----存储结果，边集
         *      HashSet<Node> set————节点集，存放所有可达的点。prim算法中只出现一个集合，就是我们目前可达的点
         *      PriorityQueue<Edge> que————小根堆，因为我们每一次操作需要找到目前最小的边。
         * */
        HashSet<Edge> result = new HashSet<>();
        HashSet<Node> set = new HashSet<>();
        PriorityQueue<Edge> que = new PriorityQueue<>();
        for (Node node:graph.nodes.values()) {          //随机选取第一个节点
            if (!set.contains(node)){
                set.add(node);
                for (Edge edge:node.edges){
                    //将第一个节点的所有相邻节点都加入到小根堆que。
                    que.add(edge);
                }
                /**
                 *      随着上面的for循环操作，小根堆里面已经有内容了。接下来的while就是————循环遍历小根堆中的边的第一个值，判断
                 *  该边的另外一个节点是不是在set里面（在的话就说明之前已经加过某条边经过该节点，此时再添加就出现环了。。那接下来
                 *  呢？这条边就不能加了，需要去小根堆找第二小的边循环操作）。不在的话就把那个节点加入set，并把这条边加到结果集中，
                 *  也就是说我们认为它是构成最小生成树的一个边。
                 *      为什么while循环加完了所有节点呢？？因为我们不断的判断新节点能否加入。一旦加入就会更新小根堆，因为新加入的
                 *  节点会带来属于它的边。这样小根堆就会不断丰富起来。
                 * */
                while(!que.isEmpty()){
                    /**
                     * 最外层的for循环其实只执行了树次：第一次仅仅是随机选取了一个节点；第二次就是新的节点但是紧接着的if条件不满足，
                     * 因此结束第二次循环；第三次同样是新的节点但是紧接着的if条件不满足，因此结束第三次循环..........也就是说只有
                     * for第一次循环的时候会进入到if语句块，后面的所有节点都不会进入到if。。。因为在if语句块的while语句块中，随着
                     * 循环就会把所有的节点加入到set中。
                     * */
                    Edge e = que.poll();
                    if (!set.contains(e.to)){
                        /**
                         *      ①!set.contains(e.to)：如果某条边的to邻节点没有在set，需要将其加入，因为这里的边是从小根堆里出来
                         *  的。set.add(e.to);
                         *      ②每往set中添加一个节点的时候，就需要向结果result中添加一条边。result.add(e);
                         *      ③因为set中加入了新的节点，因此也就多了从该节点出发的边可以研究，因此一次将该节点相连接的边加入小根
                         * 堆que.
                         *
                         * */
                        set.add(e.to);
                        result.add(e);
                        for(Edge edge1:e.to.edges){
                            que.add(edge1);
                        }
                    }
                }
            }
        }
        return result;
    }


//    public static HashSet<Edge> prim_FuXi(Graph graph){
//        HashSet<Edge> result = new HashSet<>();
//        HashSet<Node> set = new HashSet<>();
//        PriorityQueue<Edge> que = new PriorityQueue<>();
//        for (Node node:graph.nodes.values()){
//            if (!set.contains(node)){
//                set.add(node);
//                for (Edge edge:node.edges){
//                    que.add(edge);
//                }
//                while(!que.isEmpty()){
//                    Edge e = que.poll();
//                    if (!set.contains(e.to)){
//                        set.add(e.to);
//                        result.add(e);
//                    }
//                    for (Edge n:e.to.edges){
//                        que.add(n);
//                    }
//                }
//            }
//        }
//        return result;
//    }


    public static void main(String[] args) {
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);

        Edge e1=new Edge(1,n1,n2);
        Edge e2=new Edge(2,n2,n3);
        Edge e3=new Edge(3,n3,n1);
        n1.edges.add(e1);
        n1.nexts.add(n2);
        n2.edges.add(e2);
        n2.nexts.add(n3);
        n3.edges.add(e3);
        n3.nexts.add(n1);

        Graph graph=new Graph();
        graph.nodes.put(1,n1);
        graph.nodes.put(2,n2);
        graph.nodes.put(3,n3);
        graph.edges.add(e1);
        graph.edges.add(e2);
        graph.edges.add(e3);
        Set<Edge> set=prim(graph);
        for(Edge edge:set){
            System.out.println(edge.weight);
        }
    }
}
