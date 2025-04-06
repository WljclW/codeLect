package zuo_course_01base;

import java.util.Arrays;

public class helangq02 {//左边小于 中间等于 右边大于
    public static int[] helanguoqi(int[] arr,int num){
        int p=0,q=arr.length;
        int i=0;
        while(i<q){ //结束条件
            if(arr[i]<num) {
                swap(arr,p,i);
                p++;
                i++;
            }
            else if(arr[i]>num){
                swap(arr,i,q-1);
                q--;
            }
            else i++;
        }
        return arr;
    }


    private static void swap(int[] arr, int i, int low) {
        int tmp=arr[i];
        arr[i]=arr[low];
        arr[low]=tmp;

    }
    public static void main(String[] args) {
        int[] arr={3,5,41,8};
        arr=helanguoqi(arr,4);
        System.out.println(Arrays.toString(arr));
    }
}