package zuo_course_01base.No01_sort;

import java.util.Arrays;

/**
 * 插入排序：每次分别从1位置,2位置,3位置...(这里的位置就是轮次)开始从该位置向前遍历直到
 *    把这个数放在正确的位置。之所以开始就从1位置开始，是因为0位置压根没有必要进行看。。然
 *    后这种方式第i轮会保证前面的i个数的大小顺序是正确的
 * 详细解释————
 *      第一轮：最后保证了0~1位置的元素有序。从1位置开始，依次向前比较将这个数放在合适的位置；
 *      第二轮：最后保证了0~2位置的元素有序。从2位置开始，依次向前比较将这个数放在合适的位置；比如：
 * 如果2位置的数小于1位置，则2位置和1位置交换，然后继续向前比较，如果2位置(即最新的1位置的数)
 * 的数还是小于0位置的数，则将2位置的数和0位置的数交换。
 *      第三轮：最后保证了0~3位置的元素有序。从3位置开始，依次向前比较将这个数放在合适的位置；比如：
 * 如果3位置的数小于2位置，则3位置和2位置交换，然后继续向前比较，如果3位置(即最新的2位置的数)
 * 的数还是小于1位置的数，则将3位置的数和1位置的数交换。然后继续比较，如果3位置(即最新的1位置
 * 的数)还是小于0位置的数，则将3位置的数和0位置的数交换。
 *      第四轮：最后保证了0~4位置的元素有序。从4位置开始倒着研究
 *      ......
 *      第n-1轮：保证0~n-1位置的元素有序。
 * */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = GetRandom.getInts(20);

        insertSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }

        insertSort02(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[] arr){
        for (int i=1;i<arr.length;i++){ /*表明这一轮的目标是保证 0~i 范围有序*/
            for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
    }

    public static void insertSort02(int[] arr){
        for (int i=1;i<arr.length;i++){
            for(int j=i;j>0;j--){
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }else
                    break; //因为每一轮都会保证前面的m位是有效的，因此找到一个小于当前数的位置就能停止了(说明前面的数更小，也必然小于当前数)
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
