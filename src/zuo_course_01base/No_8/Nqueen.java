//package zuo_course_01base.No_8;
//
//public class Nqueen {
//
//    public static int num1(int n){      //形参n表示一共有多少行。
//        if (n<1)
//            return 0;
//        /**
//         *          record[m] = s——————表示第m行的皇后放在第s列。
//         * */
//        int[] record = new int[n];
//        return process1(0,record,n);
//    }
//    public static int process1(int i,int[] record,int n){
//        /**
//         *      process1的参数是i，也就是说处理的是第i行皇后在哪里放的问题。
//         * */
//        if (i==n)
//            return 1;
//        int res = 0;
//        for (int j=0;j<n;j++){
//            if (isValid(record,i,j)){
//                record[i] = j;
//                res += process1(i+1,record,n);
//            }
//        }
//    }
//
//    private static boolean isValid(int[] record, int i, int j) {
//
//    }
//}
