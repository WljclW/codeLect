package zuo_course_01base.No_6_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * 迪杰斯特拉最短路径：
 * */
public class Dijkstra {
    public static HashMap<Node,Integer> dijkstra(Node node){
        /**
         *      distanceMap:任何一个value都是从初始节点到该key(节点)目前的最短路径。也就是说distanceMap中的形式是“{点名称，初始
         *      点到该点的最小距离}”
         * */
        HashMap<Node, Integer> distanceMap = new HashMap<>();       //这就是最后要返回的结果map。每一步就是对它进行不断的更新，直到所有的点都过了一遍
        distanceMap.put(node,0);        //把规定的起始节点加入map.
        HashSet<Node> selectedNode = new HashSet<>();       //记录锁定的点.每个点都需要进行研究一次，这里记录的就是研究过的点。
        /**
         *      minNode就是从方法getMinDistanceAndUnselectedNode得到目前distanceMap中value最小的那个key。接下来就是拿着这个
         * minNode来进行下一轮次的循环。
         *      也就是说拿着minNode该点继续研究，minNode可能有直接连向其他节点的边，这些边就会使得初始节点到那些节点有了新的路径，
         * 看是否到达同一个点有更小的路径。
         * */
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap,selectedNode);
        while (minNode!=null){
            /**
             *      进入循环就表明目前还有未研究过的点
             * */
            int distance = distanceMap.get(minNode);        //distance：目前已知的初始点到它的最小距离
            for (Edge edge:minNode.edges){
                /**
                 *      循环遍历该点出去的每一条边
                 * */
                Node toNode = edge.to;          //查找该条边到了哪一个点
                /**
                 *      如果distanceMap还没有这个到达的点，需要将到达该点距离以及该点加入到map中;
                 *      否则说明之前的步骤中已经能到该点了，就要看目前的这个值是不是变小了，变小了
                 *          就需要更新distanceMap中key=toNode所对应的value.
                 * */
                if (!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,edge.weight+distance);
                }else
                distanceMap.put(toNode,Math.min(distanceMap.get(toNode),edge.weight+distance));
            }
            selectedNode.add(minNode);          //记录一下minNode这个点已经研究过了。
            minNode=getMinDistanceAndUnselectedNode(distanceMap,selectedNode);      //继续寻找下一个需要研究的点，就是表中距离最小但是还没有研究过的点
        }
        return distanceMap;
    }

    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNode) {
        /**
         *      从distanceMap中找到一个最小的距离。distanceMap中存储的就是目前可以到达的各个点(map中的key)、以及到它们的距离(map中的value)
         * */
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node,Integer> entry:distanceMap.entrySet()){
            /**
             *      遍历distanceMap中所有的entry，找到目前这个状态中map中的最小距离(但要求这个最小距离的点必须是没有锁定的点)。
             * 由于这里用到的是遍历方法，一昵称是可以优化的，可以引入小根堆来进行优化
             * */
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNode.contains(node) && distance < minDistance){
                /**
                 *      如果map的key即node没有锁定并且它小于目前的最小距离则更新minDistance
                 * */
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }


    /**
     * 【注】系统的小根堆，一旦发生某个元素的更改，系统并没有提供正确的方法。来将此时的堆调整为小根堆。
     * */

    /**
     * Dijkstera的改进
     * */
//    public static HashMap<Node,Integer> dijkstra(Node head,int size){
//        NodeHeap nodeHeap = new NodeHeap(size);
//        HashMap<Node, Integer> result = new HashMap<>();
//        /**
//         * addOrUpdateOrIgnore——————添加、更新或忽略。
//         * */
//        nodeHeap.addOrUpdateOrIgnore(head,0);
//        while(!nodeHeap.isempty()){
//            NodeRecord record = nodeHeap.pop();
//            Node cur = record.node;
//            int distance = record.distance;
//            for (Edge e:cur.edges){
//                nodeHeap.addOrUpdateOrIgnore(e.to,distance+e.weight);
//            }
//            result.put(cur.distance);
//        }
//        return result;
//    }
//
//
//    public static void addOrUpdateOrIgnore(Node node,int dis){
//        if (inHeap(node)){
//            distanceMap.put(node,Math.min(distanceMap.get(node),dis));
//            in
//        }
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
        HashMap<Node,Integer> hashMap=dijkstra(n1);
        for(Map.Entry<Node,Integer> entry:hashMap.entrySet()){
            System.out.println(entry.getKey().value);
            System.out.println(entry.getValue());
        }
    }
}
