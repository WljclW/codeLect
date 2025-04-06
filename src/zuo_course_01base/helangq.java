package zuo_course_01base;

import java.util.Arrays;
/**
 * 左边小于等于 右边大于
 * */
public class helangq {
    public static void helanguoqi(int[] arr,int num){
        int p=-1;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<=num){
                swap(arr,p+1,i);
                p++;
            }
        }
    }
    private static void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    public static void main(String[] args) {
        int[] arr={3,5,6,3,4,5,2,6,9};
        helanguoqi(arr,4);
        System.out.println(Arrays.toString(arr));
    }
}
