package zuo_course_01base.No2_sort;

import zuo_course_01base.No01_sort.GetRandom;

import java.util.Arrays;

/**
 * 归并排序
 * */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = GetRandom.getInts(20);

        mergeSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i + " ");
        }

        mergerSort02(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     *   step1:计算出中点
     *   step2:归并排序左半部分，归并排序右半部分
     *   step3:合并左右两半归并后的子数组
     * */
    public static void mergeSort(int[] arr,int l,int r){
        if(l==r) return;
        int mid = l + ((r-l)>>1); /*用这种方法计算中点时，"(r-l)>>1"整体必须加括号，否则会StackOverFlow*/
        /*归并排序左右两半*/
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        /*合并排序后的左右两半*/
        merge(arr,l,mid,r);
    }

    public static void merge(int[] arr,int l,int mid,int r){
        //辅助空间存放两个子问题排序后的合并后的数组
        int[] help = new int[r-l+1];
        int i=0;
        int p1 = l;
        int p2 = mid+1;
        /*
        * p1和p2分别指向两个数组，如果没有都没有越界，依次将小的那个数挪到help数组（挪之后需要更新指针）
        * */
        while(p1<=mid&&p2<=r){ //p1和p2两个指针分别指向两个子数组，将小的那个数copy到help中并移动指针
            help[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        /*
        * 下面的两个while必须进入 且 只能进入 一个。含义就是将没越界那个子数组的剩余部分拷贝进help数组
        * */
        while (p1<=mid) //如果p1没有越界，把p1剩下的部分copy到help
            help[i++] = arr[p1++];
        while (p2<=r) //如果p2没有越界，把p2剩下的部分copy到help
            help[i++] = arr[p2++];
        for (int j = 0; j < help.length; j++) { //把help中的内容copy到原数组
            arr[l+j] = help[j];
        }
    }

    public static void mergerSort02(int[] arr,int l,int r){
        if(l==r) return;
        int mid = l+((r-l)>>1);
        mergerSort02(arr,l,mid);
        mergerSort02(arr,mid+1,r);
        merge02(arr,l,mid,r);
    }

    public static void merge02(int[] arr,int l, int mid,int r) {
        int[] helper = new int[r - l + 1];
        int index = 0;
        int left = l;
        int right = mid + 1;
        while (left <= mid && right <= r) {
            if (arr[left] < arr[right]) {
                helper[index++] = arr[left++];
            } else {
                helper[index++] = arr[right++];
            }
        }
        while (left <= mid) {
            helper[index++] = arr[left++];
        }
        while (right <= r) {
            helper[index++] = arr[right++];
        }
        for (int i = 0; i < helper.length; i++) {
            arr[l + i] = helper[i];
        }
    }

}
