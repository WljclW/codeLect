package zuo_course_01base.No2;

public class getMax {
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 6, 7, 4};
        System.out.println(getMax(arr, 0, arr.length - 1));
    }

    public static int getMax(int[] arr, int l, int r) {
        if (l == r) { //递归到什么程度能直接拿到结果
            return arr[l];
        }
        int mid = l + ((r - l) >> 1); //"((r-l)>>1)"必须加括号，不加就会栈溢出
        int leftMax = getMax(arr, l, mid);
        int rightMax = getMax(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }
}
