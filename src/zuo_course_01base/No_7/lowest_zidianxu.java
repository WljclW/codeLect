package zuo_course_01base.No_7;

import java.util.Arrays;
import java.util.Comparator;

public class lowest_zidianxu {

    //比较器
    public static class myComparator implements Comparator<String> {        //用于实现两个字符串不同拼接顺序的结果字典序比较
        @Override
        public int compare(String a,String b){
            return (a+b).compareTo(b+a);                //这种规则才是正确的。必须看两个串不同拼接顺序导致的拼接结果来确定谁前谁后。
        }
    }

    public static String lowestzidianxu(String[] str){
        if (str==null || str.length==0)
            return null;
        Arrays.sort(str,new myComparator());
        StringBuilder res = new StringBuilder();
        for (String s:str){
            res.append(s);
        }
        return  res.toString();
    }

    public static void main(String[] args) {
        String[] strs1={"b","ba","bc","jp","bw","ib"};
        System.out.println(lowestzidianxu(strs1));
    }
}
