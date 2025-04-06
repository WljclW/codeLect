package zuo_course_02baseTiSheng.No_5;

public class power {
    //2的幂 只有一位是1
    public static boolean is2Power1(int a){
        return a==(a&(~a+1));
    }
    public static boolean is2Power2(int a){
        return (a&(a-1))==0;
    }

    //4的幂 先判断是不是2的幂（只有一位是1）然后 与 0101……01！=0 ？是：不是
    public static boolean is4Power(int a){
        return is2Power1(a)&&(a&0x55555555)!=0;//32位 01010101
    }
    public static void main(String[] args) {
        int a=4;
        System.out.println(is2Power2(a));
        System.out.println(is4Power(a));
    }
}
