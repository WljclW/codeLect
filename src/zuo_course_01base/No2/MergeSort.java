package zuo_course_01base.No2;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {7, 2, 5, 6, 7, 4, 9, 10, 1};
        mergeSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void mergeSort(int[] arr,int l,int r){
        if(l==r) return;
        int mid = l + ((r-l)>>1);
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    public static void merge(int[] arr,int l,int mid,int r){
        //辅助空间存放两个子问题排序后的合并后的数组
        int[] help = new int[r-l+1];
        int i=0;
        int p1 = l;
        int p2 = mid+1;
        while(p1<=mid&&p2<=r){ //p1和p2两个指针分别指向两个子数组，将小的那个数copy到help中并移动指针
            help[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid) //如果p1没有越界，把p1剩下的部分copy到help
            help[i++] = arr[p1++];
        while (p2<=r) //如果p2没有越界，把p2剩下的部分copy到help
            help[i++] = arr[p2++];
        for (int j = 0; j < help.length; j++) { //把help中的内容copy到原数组
            arr[l+j] = help[j];
        }
    }

}
