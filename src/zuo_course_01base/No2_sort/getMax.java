package zuo_course_01base.No2_sort;

/**
 * 递归的方法，求解数组的最大值~
 */
public class getMax {
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 6, 7, 4};
        System.out.println(getMax(arr, 0, arr.length - 1));
    }

    /**
     * 利用递归方法拿到数组的最大值
     * */
    public static int getMax(int[] arr, int l, int r) {
        /**
         *step1————base case，到什么时候就不需要继续递归了
         */
        if (l == r) { //递归到什么程度能直接拿到结果
            return arr[l];
        }
        /**
         *step2：递归时，需要做什么？？递归左右两边，拿到左右两边的结果
         */
        int mid = l + ((r - l) >> 1); //"((r-l)>>1)"必须加括号，不加就会栈溢出
        int leftMax = getMax(arr, l, mid);
        int rightMax = getMax(arr, mid + 1, r);
        /**
         *step3：决策，现在应该返回什么信息？？明显，返回左右两边选出来的最大值
         */
        return Math.max(leftMax, rightMax);
    }
}
