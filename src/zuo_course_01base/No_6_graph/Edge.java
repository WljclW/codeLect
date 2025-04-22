package zuo_course_01base.No_6_graph;

/**
 * 边的类型信息
 *
 * */
public class Edge {
    public int weight;      //边的权重
    public Node from;       //边出发的点
    public Node to;         //边结束的点

    public Edge(int weight,Node from,Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
