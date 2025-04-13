package zuo_course_01base.No01_sort;

/**
 * 选择排序：每次分别从第0,1,2...位置(这里的0,1,2...位置就是具体的第1轮,第2轮,第3轮.....)开始遍历到最
 *    后一个选出一个最小值，放在开始遍历的0,1,2...位置，
 * 详细的解释————
 *      第一轮：先在0~n-1位置中找出最小值(最小数的索引位置初始标记为0)，放在0位置；
 *      第二轮：接着在1~n-1位置中找出最小值(最小数所在的索引初始标记为1)，放在1位置；
 *      第三轮：接着在2~n-1位置中找出最小值(最小数所在的索引初始标记为2)，放在2位置；
 * ......
 * 简单的说：每一轮找出一个最小值，一次放在0，1，2....位置
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5, 6, 7, 10, 9, 8};
        selectSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int flag = i; //记录最小值的索引
            for (int j = i + 1; j < arr.length; j++) {
                flag = arr[j] < arr[flag] ? j : flag;
            }
            swap(arr, i, flag);
        }
        return;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
