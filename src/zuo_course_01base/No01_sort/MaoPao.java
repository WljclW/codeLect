package zuo_course_01base.No01_sort;

import java.util.Arrays;

/**
 * 冒泡排序：思想是每次从0的位置开始比较，将大的数不断的向后传递，直到传递到倒数第n(n就是第n轮)和位置
 *    换句话说：每一轮都是从0位置开始；第m轮会0位置开始将大的数像冒泡一样传递到倒数第m个数的位置
 * 详细的理解————
 *      第一轮：0~n-1范围。0位置和1位置比较，0位置的数大？大的话0位置的数和1位置的数交换；接着比较
 * 1位置和2位置，1位置的数大？大的话1位置的数和2位置的数交换；接着比较2位置和3位置，2位置的数大？大
 * 的话2位置的数和3位置的数交换........n-2位置和n-1位置的数比较，如果n-2位置比n-1位置的数大，则交
 * 换。————这一轮结束后n-1位置的数一定是数组中最大的数，即位置确定了
 *      第二轮：0~n-2范围。0位置和1位置比较，0位置的数大？大的话0位置的数和1位置的数交换；接着比较
 * 1位置和2位置，1位置的数大？大的话1位置的数和2位置的数交换；接着比较2位置和3位置，2位置的数大？大
 * 的话2位置的数和3位置的数交换........n-3位置和n-2位置的数比较，如果n-3位置比n-2位置的数大，则交
 * 换。————这一轮结束后n-2位置的数一定是数组中第二大的数，即位置确定了
 *      第三轮。。。。。。。。
 * */
public class MaoPao {
    public static void main(String[] args) {
        int[] arr = GetRandom.getInts(20);

        maoPao(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }

        maopao02(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void maoPao(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        for (int i= arr.length-1;i>0;i--){ /*规定这一轮的范围是从索引0到哪里*/
            for (int j=0;j<i;j++){ /*进行一轮的遍历比较*/
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    public static void maopao02(int[] arr){
        for (int i=arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }



//    public static void swap(int[] arr,int i,int j){
//        int temp=arr[i];
//        arr[i]=arr[j];
//        arr[j]=temp;
//    }

    /**
     * 交换两个数的另一种实现方式。使用的条件：保证交换的两个数是必须两片不同的内存空间 以及 两个
     * 不同的数。(如果是相同的物理内存空间，则使用后，该数会变为0)，其实最本质的要求是这两个数不
     * 能相等
     * */
    public static void swap(int[] arr,int i,int j){
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
    }

}
