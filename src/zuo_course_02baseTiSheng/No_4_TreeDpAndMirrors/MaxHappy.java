package zuo_course_02baseTiSheng.No_4_TreeDpAndMirrors;

import java.util.List;

public class MaxHappy {

    /**
     * 每一个节点的信息(代表每一个员工)
     * */
    public static class Employee{
        public int happy;           //当前节点的快乐值
        public List<Employee> nexts;        //这名员工的直接下级
    }


    /**
     * 主方法调用————返回以boss为根的多叉树的最大快乐值
     * */
    public static int maxHappy(Employee boss){
        Info headInfo = process(boss);
        return Math.max(headInfo.buMaxHappy,headInfo.laiMaxHappy);
    }



    /**
     *   节点返回值类型的定义，两个信息：一是：该节点来的话,以该节点为根的子树的最大快乐值；
     *                              二是：该节点不来的话，以该节点为根的子树的最大快乐值。
     * */
    public static class Info{
        public int laiMaxHappy;     //该节点来，最大的快乐值；
        public int buMaxHappy;      //该节点不来，最大的快乐值
        public Info(int lai,int bu){
            this.laiMaxHappy = lai;
            this.buMaxHappy = bu;
        }
    }


    public static Info process(Employee x){
        if (x.nexts.isEmpty()){     //Base Case:如果x是基层员工(这样的员工是没有孩子节点的)
            return new Info(x.happy,0);     //他去的话，最大快乐值就是他本身的快乐值x.happy；他若不去由于没有后继节点则最大快乐值就是0.
        }
        int lai = x.happy;      //x来的情况下，整棵树的最大收益(给出初始值是该节点的快乐值)
        int bu = 0;             //x不来的情况下，整棵树的最大收益(给出的初始值是0)
        /*
        *   遍历当前节点的所有孩子，目的是确定两种情况下所能得到的最大值：一是要当前节点不要它所有的孩子；
        * 二是不要当前节点但是可以(可以要但不意味着就一定要)要它所有的孩子。
        * */
        for(Employee next:x.nexts){
            Info nextInfo = process(next);
            lai+=nextInfo.buMaxHappy;          //如果x来，那下一层节点一定不会来。来的话最大快乐就是————当前节点快乐+下一层节点不能来的最大值之和
            bu +=Math.max(nextInfo.laiMaxHappy,nextInfo.buMaxHappy);    //如果x不来，那下一次的各个节点可来但是也可以选择不来。
        }
        return new Info(lai,bu);
    }
}
