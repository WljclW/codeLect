//package zuo_course_01base.No_8;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
////打印所有的子序列
//public class PrintAllSubsquence {
//    public static void printAllSubsquence(String str){
//        char[] chs = str.toCharArray();
//        process01(chs,0);
//    }
//
//    public static void process01(char[] str,int i){
//        if (i==str.length){
//            System.out.println(String.valueOf(str));
//            return;
//        }
//        process01(str,i+1);
//        char tmp = str[i];
//        str[i] = 0;
//        process01(str,i+1);
//        str[i] = tmp;
//    }
//
//
//    public static void process02(char[] str,int i,List<Character> res){
//        if (i==str.length){
//            printList(str);
//            return;
//        }
//        List<Character> resKeep = copyList(res);
//        resKeep.add(str[i]);
//        process(str,i+1,resKeep);
//        List<Character> resNoInclude = copyList(res);
//        process(str,i+1,resNoInclude);
//    }
//
//    private static List<Character> copyList(List<Character> res) {
//        List<Character> list=new ArrayList<Character>();
//        for(int i=0;i<res.size();i++){
//            list.add(res.get(i));
//        }
//        return list;
//    }
//}
