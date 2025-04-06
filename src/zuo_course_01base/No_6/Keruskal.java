package zuo_course_01base.No_6;

import java.util.*;

public class Keruskal {

    public static class Mysets{

        public HashMap<Node,List<Node>> setMap = new HashMap<>();
        public Mysets(List<Node> nodes){
            /**
             *      初始的情况下：每一个节点对应一个集合。
             * */
            for (Node cur:nodes){
                ArrayList<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur,set);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            /**
             *   作用：判断两个节点是不是属于一个集合————
             *          判断两个节点对应的set的内存地址一样不一样，set如何获取，通过Map获取。
             * */
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public void unionn(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode:toSet){
                /**
                 * for循环完成的就是两个集合的合并，并且要保证合并后from和to节点对应的set是同一个。
                 * */
                fromSet.add(toNode);
                setMap.put(toNode,fromSet);
            }
        }
    }


    public static Set<Edge> kruskal(Graph graph){
        ArrayList<Node> nodes = new ArrayList<>(graph.nodes.values());
        Mysets mysets = new Mysets(nodes);
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        priorityQueue.addAll(graph.edges);
        HashSet<Edge> result = new HashSet<>();
        while(!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if (!mysets.isSameSet(edge.from,edge.to)){
                mysets.unionn(edge.from,edge.to);
                result.add(edge);
            }
        }
        return result;
    }


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
        Set<Edge> set=kruskal(graph);
        for(Edge edge:set){
            System.out.println(edge.weight);
        }
    }
}
