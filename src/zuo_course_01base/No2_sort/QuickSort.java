package zuo_course_01base.No2_sort;

import zuo_course_01base.No01_sort.GetRandom;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序3.0，也就是常规说法的快速排序
 *    思路：每一次随机选择一个数，将其他的数按照这个数分为三部分。
 * */
public class QuickSort {
    /**
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     *  左神的解法
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = GetRandom.getInts(20);
        System.out.println(Arrays.toString(arr));

//        quickSort2(arr, 0, arr.length - 1);
        quickSort3(arr, 0, arr.length - 1);
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
        /*step1：base-case的考虑*/
        if (left >= right) return;

        /*step2：随机选择一个数，并且交换到“要排序的子数组”的末尾即交换到arr[r]*/
        /**
         "new Random().nextInt(right - left + 1)"会产生一个[0,r-l+1)的整数。
         比如：l = 3,r = 7。
              我们的需求是产生3、4、5、6、7这几个整数，可以转换为l加一个数，这个数的取值范围是0、1、2、3、4，因
            此这些数的取值范围是[0,4]，但是"new Random().nextInt(right - left)"即“new Random().nextInt(4)”只能
            产生0、1、2、3，因此要使用“right-left+1”~
         */
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap1(nums, pivotIndex, right);
        /*step3：把step2选择的那个数放在正确的位置*/
        int partitionIndex = partition1(nums, left, right);
        /*step4：对选择数的左右两半递归排序*/
        quickSort(nums, left, partitionIndex - 1);
        quickSort(nums, partitionIndex + 1, right);
    }

    private void swap1(int[] nums, int pivotIndex, int right) {
        int tmp = nums[pivotIndex];
        nums[pivotIndex] = nums[right];
        nums[right] = tmp;
    }

    /**
     *【重要】快排中有partition方法，这个方法的目的：将right位置（右边界那个数）的那个数放在这段子数组的正确位置！！
     *      同时，由于进入partition之前已经把主程序中随机选择的那个数放在了最后位置，因此到这个方法执行结束，做了的
     *      事就是：①在主程序中随机选择一个数；②把随机选择的数放在子数组的末尾；③调用partition方法把随机选择的数
     *      放在正确的位置......最后在主程序中递归的排序“放好随机数”的位置的左右两半。
     */
    private int partition1(int[] nums, int left, int right) {
        int pivot = nums[right]; //最后一个元素作为pivot
        int flag = left; //指向下一个应该放“≤pivot”元素的位置————如果碰到≤pivot的元素，应该放在位置flag
        for (int j = left; j < right; j++) {
            /**不加等于会怎么样？？*/
            if (nums[j]<=pivot){  //如果nums[j]应该放在左边
                swap(nums,flag++,j);
            }
        }
        swap(nums,flag,right); //把分界值pivot放在位置i。因为flag之前都是小于等于的，i位置及之后都是大于的
        return flag;
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


    /**
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * =======================================================================================================
     * 自己，review代码
     */
    public static void quickSort3(int[] arr,int l,int r){
        /*step1：base-case的考虑*/
        if (l>=r) return; /**err：如果写成“if (l==r) return;”，是错误的，必须是“>=”。。。说明某些时候l不存在等于r的时候，直接来到了l>r*/
        /*step2：产生一个索引；并将这个数交换到索引为r的位置*/
        int pivotIndex = l + new Random().nextInt(r - l + 1);
        swap3(arr,pivotIndex,r);
        /**/
        pivotIndex = partion3(arr,l,r);
        quickSort3(arr,l,pivotIndex-1);
        quickSort3(arr,pivotIndex+1,r);
    }

    /**
     *partion方法的作用：①使用for循环将小于arr[r]的数移动到flag位置的左边；②将arr[r]放在[l,r]区间正确的位置flag；
     *      ③返回flag。。。。。然后后面的步骤我们递归的对flag索引左右两边分别排序
     *      如果一句话概括作用，就是：将arr[r]放在正确的位置；且左边的小于(小于等于也可)它，右边的大于等于(大于也可)它；最后返回位置
     *【补充说明】：其中③中把arr[r]换到了flag的位置，flag就是第一个不小于arr[r]的位置，因此①②③结束以后有结论：
     *      1. arr[flag]左边的数都是小于它的，右边的数都是大于它的。
     *      2. arr[flag]这个数就在正确的位置
     */
    private static int partion3(int[] arr, int l, int r) {
        int flag = l;
        for (int i = l; i < r; i++) {
            if (arr[i]<arr[r]){
                swap(arr,flag++,i);
            }
        }
        swap3(arr,flag,r);
        return flag;
    }

    private static void swap3(int[] arr, int pivotIndex, int r) {
        int tmp = arr[pivotIndex];
        arr[pivotIndex] = arr[r];
        arr[r] = tmp;
    }




    /*partion方法的另一种写法。。。
    * 但是这种写法逻辑清晰，尽量使用上面的partion3即for循环的方式*/
    private static int partion(int[] arr, int l, int r) {
        /**下面的代码是方法内部把l改了，其实如果像上面的for循环，这里应该再增加一个变量，初始值也是l。
         *      cur表示遍历研究到的位置；l表示“碰到下一个小于等于基准值”的元素应该放的位置。
         *      因此最后结束后需要把基准值放在位置l————同时返回l。(之所以要返回l是因为：在主程序中
         *   还要继续对基准值所有两边的数进行排序)
         *  */
        int cur = l;
        while (cur<r){
            if (arr[cur]<=arr[r]){
                swap(arr,l++,cur);
            }
            cur++;
        }
        swap(arr,l,r); //把基准值放在正确的位置
        return l; //返回l
    }
}
