package zuo_course_01base.No2_sort;

import zuo_course_01base.No01_sort.GetRandom;

import java.util.Arrays;

/**
 * 归并排序
 * */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = GetRandom.getInts(20);
        System.out.println(Arrays.toString(arr));

        mergeSort1(arr,0,arr.length-1);
//        mergeSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     *【具体步骤】
     *   step1:先指出递归终止的条件————l==r，直接return。（也可以跟“快排版本”的相同，统一写成“if(l>=r) return;”）
     *   step2:归并排序左半部分，归并排序右半部分
     *   step3:合并左右两半归并后的子数组
     *【补充说明】
     *      “mergeSort”是不需要返回值的。最后合并的时候，只需要三个索引就可以“l,mid,r”，表明我们希望合并的两个子数组
     *   的索引范围为：[l,mid]、[mid+1,r]。因此使用两个指针依次合并即可(类似于合并两个有序链表)
     * */
    public static void mergeSort(int[] arr,int l,int r){
        /*step1：递归终止条件*/
        if(l==r) return; /**err：没有这一句相当于没有终止条件，不断的入栈。*/
        int mid = l + ((r-l)>>1); /*用这种方法计算中点时，"(r-l)>>1"整体必须加括号，否则会StackOverFlow*/
        /*step2：归并排序左右两半*/
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        /*step3：合并排序后的左右两半*/
        merge(arr,l,mid,r);
    }

    /**
     *合并两个有序数组的代码，跟合并两个有序链表类似。。。。区别在于最后多了一步copy回源数组的过程
     */
    public static void merge(int[] arr,int l,int mid,int r) {
        /*step1：辅助空间存放两个子问题排序后的合并后的数组*/
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l, p2 = mid + 1; /*两个指针分别指向两个子数组的第一个数*/
        /*
         * step2：p1和p2分别指向两个数组，如果没有都没有越界，依次将小的那个数挪到help数组（挪之后需要更新指针）
         * */
        while (p1 <= mid && p2 <= r) { //p1和p2两个指针分别指向两个子数组，将小的那个数copy到help中并移动指针
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++]; /**copy后需要移动对应的指针*/
        }
        /*
         * step3：拼接两个数组中剩下的部分
         *   下面的两个while必须进入 且 只能进入 一个。含义就是将没越界那个子数组的剩余部分拷贝进help数组
         * */
        while (p1 <= mid) //如果p1没有越界，把p1剩下的部分copy到help
            help[i++] = arr[p1++];
        while (p2 <= r) //如果p2没有越界，把p2剩下的部分copy到help
            help[i++] = arr[p2++];
        /*
         step4：将暂存结果的数组复制回源数组arr
         */
        for (int j = 0; j < help.length; j++) { //把help中的内容copy到原数组
            arr[l + j] = help[j]; /*辅助数组的i位置 应该复制到源数组的 l+i位置————辅助数组对应源数组索引为[l,r]的这一段*/
        }
    }


    /**
     *======================================================================================================
     *======================================================================================================
     *======================================================================================================
     *======================================================================================================
     *======================================================================================================
     *======================================================================================================
     * 自己review的版本
     */
    public static void mergeSort1(int[] arr,int l,int r){
        if (l==r) return;
        int mid = l+(r-l)/2;
        mergeSort1(arr,l,mid);
        mergeSort1(arr,mid+1,r);
        mergeTwo(arr,l,mid,r);
    }

    private static void mergeTwo(int[] arr, int l, int mid, int r) {
        int[] nums = new int[r - l + 1];
        int cur = 0;
        int p1 = l,p2 = mid+1;
        while (p1<=mid&&p2<=r){
            nums[cur++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            nums[cur++] = arr[p1++];
        }
        while (p2<=r){
            nums[cur++] = arr[p2++];
        }
        for (int i = 0; i < nums.length; i++) {
            arr[l+i] = nums[i];
        }
    }

}
