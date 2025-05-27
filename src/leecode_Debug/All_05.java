package leecode_Debug;

/**
 * @author mini-zch
 * @date 2025/5/26 19:14
 */
public class All_05 {
        /*153.
    已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    * */
    /**
     * 【注意】
     * 【关键】每次拿着mid位置的值 和 最后一个数比较：
     *           如果小于数组的最后一个数，说明mid必然在后半边，移动r指针为mid-1，在后面的区域继续找
     *           如果大于数组的最后一个数，说明mid必然在前半边，移动l指针为mid+1，在后面的取余继续找
     *  1. 为什么选择跟最后一个数比较？与第一个数比较行不行？
     *    答：
     * */
//    public int findMin(int[] nums) {
//        int l = 0,r = nums.length-1;
//        while (l<=r){
//            int mid = l+(r-l)/2;
//            if (nums[mid]<=nums[nums.length-1]){
//                r = mid-1;
//            }else {
//                l = mid+1;
//            }
//        }
//        return nums[l];
//    }
    /*
    eg1:[7,8,9,10,1,2,3]  中间数10，比右边界3大，因此中间数左边的数以及中间数都不是最小值，这时需要缩小搜索区间，往右走，左边界设置为left = mid + 1;
    eg2:[6,7,1,2,3]  中间数1，比3小，说明1-3这个区间是升序的，那么中间数右边的数一定不是最小数（但是中间数就可能是最小数），故缩小搜索区间，设置有右边界为right = mid;
    * */
     public int findMin(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (nums[mid]>nums[r]){ /*只要大于右边界的值————说明mid一定在左半升序的那边，并且mid肯定不是最小值位置*/
                l = mid+1;
            }else { /*只要小于右边界的值————说明mid一定是在右半升序的那边，并且mid可能就是最小值的位置*/
                r = mid; //mid位置可能是最小值，因此r不能赋值为mid-1.
            }
        }
        return l;
    }

    //待验证
    public int findMin_01(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]>nums[r]){
                l = mid+1;
            }else {
                r = mid;
            }
        }
        return r;
    }


    public int findMin_02(int[] nums){
         int l=0,r=nums.length-1;
         int min = nums[0];
         while(l<=r){
             int mid = l+(r-l)/2;
             if (nums[mid]<nums[r]){
                 min = nums[mid];
                 r = mid-1;
             }else {
                 l = mid+1;
             }
         }
         return min;
    }






    public int[] searchRange(int[] nums, int target) {
        int left = searchRangeL(nums,target);
        if (left==nums.length || nums[left]!=target){
            return new int[]{-1,-1};
        }

        int right = searchRangeL(nums,target+1);
        return new int[]{left,right-1};
    }

    private int searchRangeL(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]>target){
                r = mid-1;
            }else if (nums[mid]==target){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return l;
    }
}
