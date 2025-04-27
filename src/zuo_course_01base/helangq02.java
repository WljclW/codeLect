package zuo_course_01base;

import java.util.Arrays;

public class helangq02 {//左边小于 中间等于 右边大于


    /**使用下面的版本，容易理解*/
    public static void helanguoqi_(int[] arr,int num){
        int left = 0,right = arr.length-1;
        int cur =0;
        while(cur<=right){ //这里只用遍历到right，右边的已经确定是大于num的
            if (arr[cur]<num){
                swap(arr,cur++,left++);
            }else if (arr[cur]>num){
                swap(arr,right--,cur);
            }else {
                cur++;
            }
        }
    }


    /*另一个版本*/
//    public static int[] helanguoqi(int[] arr,int num){
//        int p=0,q=arr.length;
//        int cur=0;
//        while(cur<q){ //结束条件
//            if(arr[cur]<num) {
//                swap(arr,p,cur);
//                p++;
//                cur++;
//            }
//            else if(arr[cur]>num){
//                swap(arr,cur,q-1);
//                q--;
//            }
//            else cur++;
//        }
//        return arr;
//    }


    private static void swap(int[] arr, int i, int low) {
        int tmp=arr[i];
        arr[i]=arr[low];
        arr[low]=tmp;

    }
    public static void main(String[] args) {
        int[] arr={4,10,3,5,41,8,2,3,4,1};
//        arr=helanguoqi(arr,4);
        helanguoqi_(arr,4);
        System.out.println(Arrays.toString(arr));
    }
}