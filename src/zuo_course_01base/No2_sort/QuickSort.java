package zuo_course_01base.No2_sort;

import java.util.Arrays;
import java.util.Random;

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
            int[] p = partition(arr, l, r); /**数组p的第0维是最后一个小于基准值的数；第1维是最后一个大于基准值的数所在的位置*/
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
        /**下面的more能不能换成l？？？理论上应该是可以的！
         *      less表示：下一次碰到“小于基准值的数”时，应该放在index=less+1的位置
         *      more表示：下一次碰到“大于基准值的数”时，应该放在index=more-1的位置
         *      l表示：下一次碰到“等于基准值的数”时，应该放在index=l的位置
         *      【补充】为什么less和more会有+1、-1？？因为less的初始值是l-1，这个位置本身不能放数的；more的初始值
         *            是r,而位置r放的是基准值，因此也是不能放数的，索引放置数的时候必须是——先更新指针再放数。。。。
         *            而l的初始值是l，是可以放数的，因此不用先更新l。而是先放数，放了数再更新l.
         *      */
        swap(arr, more, r); //将最后一个数和右边数组的 第一个数交换，即将分组的边界值放在了大于部分的第一个位置
        return new int[]{less + 1, more};
    }











    /**
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     *chatgpt给出的版本
     */
    public void quickSort1(int[] nums, int left, int right) {
        if (left >= right) return;

        // 随机选择一个 索引位置，并交换到末尾
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap1(nums, pivotIndex, right);

        int partitionIndex = partition1(nums, left, right);

        quickSort(nums, left, partitionIndex - 1);
        quickSort(nums, partitionIndex + 1, right);
    }

    private void swap1(int[] nums, int pivotIndex, int right) {
        int tmp = nums[pivotIndex];
        nums[pivotIndex] = nums[right];
        nums[right] = tmp;
    }

    private int partition1(int[] nums, int left, int right) {
        int pivot = nums[right]; //最后一个元素作为pivot
        int i = left; //指向下一个应该放“≤pivot”元素的位置————如果碰到≤pivot的元素，应该放在位置i
        for (int j = left; j < right; j++) {
            /**不加等于会怎么样？？*/
            if (nums[j]<=pivot){  //如果nums[j]应该放在左边
                swap(nums,i++,j);
            }
        }
        swap(nums,i,right); //把分界值pivot放在位置i。因为i之前都是小于等于的，i位置及之后都是大于的
        return i;
    }

    //关于partition方法，有如下不同的写法
    private int[] partition3(int[] a, int left, int right) {
        int pivot = a[right]; //pivot是基准值
        /*
        lt指针：下一次碰到小于基准值的数，应该放在index=lt的位置。（less than）
        gt指针：下一次碰到大于基准值的数，应该放在index=gt的位置。
        i指针：下一次碰到等于基准值的数，应该放在index=i的位置
         */
        int lt = left, i = left, gt = right;
        while (i <= gt) {
            if (a[i] < pivot) swap(a, lt++, i++);
            else if (a[i] > pivot) swap(a, i, gt--);
            else i++;
        }
        return new int[]{lt, gt};
    }
}
