package zuo_course_01base.No2;

import java.util.Arrays;

/**
 * 给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的
 * 数放在数组的右边。要求额外空间复杂度0(1），时间复杂度0(N)
 * */
public class Q1 {
    public static void main(String[] args) {
        int[] arr = {7, 2, 5, 6, 7, 4, 9, 10, 1};
        solution(arr,6);
        System.out.println(Arrays.toString(arr));
    }

    public static void solution(int[] arr,int flag){
        int cur = 0; //当前研究的是该索引处的数
        int left = 0; //左边界的位置+1
        while (cur<arr.length){
            if(arr[cur]<=flag){
                /*  如果当前研究的数小于等于flag，则把当前研究的数(arr[cur])和left位置的数交换。
                * left不属于左区域(是小于等于区域的右边界+1)。
                *   操作完成后left+1：因为往左区域放了一个数；cur+1：因为当前位置的数研究过了，
                * 即使是交换来的数也是看过的，因此不用再看了
                * */
                swap(arr,left++,cur++);
            }else{
                /*
                *   如果当前研究的数大于flag，说明不能往左区域放。。只需要cur+1，继续研究后面的数
                * */
                cur++;
            }
        }
    }

    private static void swap(int[] arr, int i, int cur) {
        int tmp = arr[i];
        arr[i] = arr[cur];
        arr[cur] = tmp;
    }
}
