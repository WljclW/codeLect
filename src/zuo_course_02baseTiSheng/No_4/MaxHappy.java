package zuo_course_02baseTiSheng.No_4;

import zuo_course_02baseTiSheng.No_2.UnionFind;

import java.util.List;

public class MaxHappy {

    public static class Employee{
        public int happy;               //当前节点的快乐值
        public List<Employee> nexts;        //这名员工的直接下级
    }

    public static class Info{
        /**
         *      节点返回值类型的定义，两个信息：该节点来的话,以该节点为根的子树的最大快乐值；
         *                                  该节点不来的话，以该节点为根的子树的最大快乐值。
         * */
        public int laiMaxHappy;     //该节点来，最大的快乐值；
        public int buMaxHappy;      //该节点不来，最大的快乐值
        public Info(int lai,int bu){
            this.laiMaxHappy = lai;
            this.buMaxHappy = bu;
        }
    }


    public static int maxHappy(Employee boss){
        Info headInfo = process(boss);
        return Math.max(headInfo.buMaxHappy,headInfo.laiMaxHappy);
    }


    public static Info process(Employee x){
        if (x.nexts.isEmpty()){             //Base Case:如果x是基层员工
            return new Info(x.happy,0);     //他去的话，最大快乐值就是他本身的快乐值x.happy；他若不去则最大快乐值就是0.
        }
        int lai = x.happy;      //x来的情况下，整棵树的最大收益
        int bu = 0;             //x不来的情况下，整棵树的最大收益
        for(Employee next:x.nexts){
            Info nextInfo = process(next);
            lai+=nextInfo.buMaxHappy;          //如果x来，那下一层节点一定不会来。来的话最大快乐就是————当前节点快乐+下一层节点不能来的最大值之和
            bu +=Math.max(nextInfo.laiMaxHappy,nextInfo.buMaxHappy);    //如果x不来，那下一次的各个节点可来但是也可以选择不来。
        }
        return new Info(lai,bu);
    }
}
