package zuo_course_02baseTiSheng.No_5;

public class AddMinusMultiDivideByBit {
    public static int add(int a,int b){
        int sum = 0;
        while(b!=0){
            sum = a^b;                  //异或运算就是无进位相加
            b = (a&b)<<1;               //获取进位信息
            a = sum;
        }
        return sum;
    }

    public static int negNum(int a){
        return add(~a,1);               //对形参a取反
    }

    public static int sub(int a,int b){
        return add(a,negNum(b));
    }

    public static int multi(int a,int b){
        int res = 0;
        while(b!=0){
            if ((b&1) !=0)          //如果b的末尾不是0，则a加到res。
                res = add(res,a);
            a<<=1;              //a左移一位模仿了b左移的样子。
            b>>>=1;             //b向右移动1位,无符号移位，左侧用0补齐。
        }
        return res;
    }


    public static boolean isNeg(int n){
        return n<0;
    }
    public static int div(int a,int b){
        int x=isNeg(a)?negNum(a):a;
        int y=isNeg(b)?negNum(b):b;
        int res=0;
        for(int i=31;i>-1;i=sub(i,1)){
            if((x>>i)>=y){
                res|=(1<<i);
                x=sub(x,y<<i);
            }
        }
        return isNeg(a)^isNeg(b)?negNum(res):res;
    }


    public static void main(String[] args) {
        System.out.println(add(1,2));
        System.out.println(div(18,5));
        System.out.println(sub(1,2));
        System.out.println(multi(3,2));
    }

}
