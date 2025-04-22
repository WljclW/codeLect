package zuo_course_01base.No_6_graph;

import java.util.ArrayList;

public class Node {
    public int value;       //该节点的值
    public int in;          //有多少条边指向该点
    public int out;            //从该点出去多少边
    public ArrayList<Node> nexts;        //从该点发散出去的边直接相连的点
    public ArrayList<Edge> edges;       //属于该点的边，就是从该点出发的边

    public Node(int value){
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<Node>();
        this.edges = new ArrayList<zuo_course_01base.No_6_graph.Edge>();
    }
}
