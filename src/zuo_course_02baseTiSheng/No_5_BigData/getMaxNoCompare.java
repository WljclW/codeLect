package zuo_course_02baseTiSheng.No_5_BigData;

public class getMaxNoCompare {
    public static int flip(int a){     //保证形参a不是0就是1.
        return a^1;
    }

    public static int sign(int a){
        return flip(a>>31 & 1);
    }


    public static int getMax(int a,int b){
        /**
         * 没毛病版
         * */
        int c = a-b;
        int sa = sign(a);       //a的符号
        int sb = sign(b);
        int sc = sign(c);       //差值c的符号
        int difSab = sa^sb;         //a的符号和b的符号一样时是0，否则为1
        int sameSab = flip(difSab);     //difSab=0，则sanmeSab=1;difSab=1，则sanmeSab=0
        /**
         * 什么时候返回a???
         *      a和b的符号不一样且a的符号大于0的时候返回a。。（a是正数b是负数）
         *      a和b的符号一样的时候，差值c的符号是正。。。（表示a是比b大的正数）
         *      上面的两个情况，表达式表示就是————difSab*sa + sameSab*sc
         * */
        int returnA = difSab*sa + sameSab*sc;
        int returnB = flip(returnA);
        return a*returnA+b*returnB;
    }


    public static void main(String[] args) {
        int a=-16;
        int b=1;
        System.out.println(getMax(a,b));
        a=2147483647;
        b=-214748000;
        System.out.println(getMax(a,b));
    }
}
