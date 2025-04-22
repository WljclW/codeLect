package zuo_course_01base.No_6_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class tuoPuPaiXu {
    //拓扑排序的实现。
    public static List<Node>  m(Graph graph){
        /**
         *      inMap:键值对，键是各个节点，值是该节点的入度；
         *      zeroInQueue：存放入度为0的点。
         * */
        HashMap<Node, Integer> inMap = new HashMap<>();
        LinkedList<Node> zeroInQueue = new LinkedList<>();
        for (Node node:graph.nodes.values()){
            /**
             *      初始时挨个判断图的各个节点。目的是对inMap和zeroInQueue做初始化。
             *          将每个点、该点的入度存到inMap;
             *          如果某个节点的入度为0，存储到zeroInQueue。
             * */
            inMap.put(node,node.in);
            if (node.in == 0)
                zeroInQueue.add(node);
        }
        //  声明一个list,用来存储我们拓扑排序的顺序。也就是结果
        List<Node> result = new ArrayList<>();
        while(!zeroInQueue.isEmpty()){                   //循环的条件就是目前还有入度为0的点。
            /**
             *      每次从zeroInQueue先弹出一个节点，这个节点加入到结果集中。
             * */
            Node cur = zeroInQueue.poll();
            result.add(cur);
            /**
             *      由于cur节点弹出了，因此由它通过边指向的节点的入度都需要减一。
             * */
            for (Node n:cur.nexts){
                /**
                 *      循环判断该节点的nexts中的节点，依次将他们的in属性(也就是入度)减1，但是注意的是不是在初始的图结构更改，
                 *  而是在我们声明的inMap进行更改即可。我们不动原始的图结构。
                 *      所有的邻接点的in属性减一后，如果发现有入度为零的点，需要加入到zeroInQueue中。
                 * */
                inMap.put(n,inMap.get(n)-1);        //修改cur的邻接点的入度。Map修改value的API跟加入的一样，是put。
                if (inMap.get(n) == 0)
                    zeroInQueue.add(n);
            }
        }
        return result;
    }


//    public static List<Node>  m_FuXi(Graph graph) {
//        LinkedList<Node> res = new LinkedList<>();
//        HashMap<Node, Integer> map = new HashMap<>();
//        for (Node node:graph.nodes.values()){
//            map.put(node, node.in);
//            if (node.in == 0)
//                res.add(node);
//        }
//        LinkedList<Node> result = new LinkedList<>();
//        while(!res.isEmpty()){
//            Node cur = res.poll();
//            result.add(cur);
//            for (Node n:cur.nexts){
//                map.put(n,map.get(n)-1);
//                if (map.get(n)==0)
//                    res.add(n);
//            }
//        }
//        return result;
//    }


    public static void main(String[] args) {
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);
        Node n4=new Node(4);
        Edge e1=new Edge(1,n1,n2);
        Edge e2=new Edge(1,n1,n3);
        Edge e3=new Edge(1,n2,n4);
        Edge e4=new Edge(1,n3,n4);
        n1.nexts.add(n2);
        n1.nexts.add(n3);
        n2.nexts.add(n4);
        n3.nexts.add(n4);
        n1.in=0;
        n2.in=1;
        n3.in=1;
        n4.in=2;
        n1.edges.add(e1);
        n1.edges.add(e2);
        n2.edges.add(e3);
        n3.edges.add(e4);
        Graph graph=new Graph();
        graph.nodes.put(1,n1);
        graph.edges.add(e1);
        graph.nodes.put(2,n2);
        graph.edges.add(e2);
        graph.nodes.put(3,n3);
        graph.edges.add(e3);
        graph.nodes.put(4,n4);
        graph.edges.add(e4);
//        List<Node> list=m_FuXi(graph);
        List<Node> list=m(graph);
        for(Node node:list){
            System.out.println(node.value);
        }
    }
}
