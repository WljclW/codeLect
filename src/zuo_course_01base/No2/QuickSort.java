package zuo_course_01base.No2;

import java.util.Arrays;

/**
 * 快速排序3.0，也就是常规说法的快速排序
 *    思路：每一次随机选择一个数，将其他的数按照这个数分为三部分。
 * */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 6, 7, 4, 9, 10, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int l,int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    private static void swap(int[] arr, int i, int r) {
        int tmp = arr[i];
        arr[i] = arr[r];
        arr[r] = tmp;
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1; //小于基准值的数的右边界(这个位置的数是包含在左边的)
        int more = r; //大于基准值的数的左边界(这个位置的数是包含在右边的)
        while (l < more) { //l代表当下研究的数，
            if (arr[l] < arr[r]) { //当前值小于基准值
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) { //当前值大于基准值
                swap(arr, --more, l);
            } else { //当前值等于基准值
                l++;
            }
        }
        swap(arr, more, r); //将最后一个数和右边数组的 第一个数交换，即将分组的边界值放在了大于部分的第一个位置
        return new int[]{less + 1, more};
    }

}
