package zuo_course_01base.No_7;

import java.util.Comparator;
import java.util.PriorityQueue;

public class moreProfit {
    public static class Node{       //这是一个项目结构的类，每一个项目对应这个类的一个实例
        public int p;   //收益
        public int c;   //需要的启动资金
        public Node(int p,int c){
            this.p = p;
            this.c = c;
        }
    }


    public static int findMaxProfit(int k,int w,int[] profits,int[] Capital){
        /**
         *      创建按照项目的启动资金来进行排序的小根堆
         *      PriorityQueue是系统提供的小根堆，但是如果要排序我们自定义的对象——————要求该对象的类要实现Comparator接口
         * */
        PriorityQueue<Node> capital_DESC = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.c-o2.c;
            }
        });
        /**
         * 创建按照项目的利润来进行排序的大根堆
         * */
        PriorityQueue<Node> profit_ASC = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.p- o1.p;
            }
        });
        for (int i=0;i<profits.length;i++)
            capital_DESC.add(new Node(profits[i],Capital[i]));
        for (int i=0;i<k;i++){
            while(!capital_DESC.isEmpty() && capital_DESC.peek().c<=w){
                /**
                 * 如果小根堆不是空并且小根堆的堆顶元素不大于我们手里的钱。就表示这个项目可以做，将其加入到大根堆。重复这一操作依次将
                 * 目前我们能做的项目加入到大根堆。
                 * */
                profit_ASC.add(capital_DESC.poll());
            }
            if (profit_ASC.isEmpty())
                /**
                 * 如果经过上面的while循环后大根堆是空，表示目前靠我们手里的这点钱，哪一个项目都做不了
                 * */
                return w;
            w+=profit_ASC.poll().p;     //获取大根堆堆顶项目的利润。
        }
        return w;
    }
}
