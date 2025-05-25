package leecode_Debug._hot100;

public class _10binarySearch {
    /*35.
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    请必须使用时间复杂度为 O(log n) 的算法。
    * */
    /**
     * 【关键】题目的本质是：查找给定元素第一次出现的位置(如果数组中有的话)
     *                或者  查找大于目标元素的第一个数的位置(如果数组没有给定的元素)
     * 【说明】
     *      1. 二分查找有很多个版本，以后就使用“左闭右闭”的写法
     *      2. 这个题不同区间的写法建议看"https://leetcode.cn/problems/search-insert-position/solutions/2023391/er-fen-cha-zhao-zong-shi-xie-bu-dui-yi-g-nq23/?envType=study-plan-v2&envId=top-100-liked"
     *      3.
     * */
    public int searchInsert(int[] nums, int target) {
        int l =0,r = nums.length-1;
        while (l<=r){
            int mid = l+((r-l)>>1);
            /*情况1：如果找到目标元素，可能并不是第一个位置，因此移动r指针*/
            if (nums[mid]==target){ /**err：由于要找第一个大于等于target的位置，一昵称相等时还不能返回，要让右边界r左移*/
                r = mid-1;
//                return mid;
            }else if (nums[mid]>target){ /*情况2：如果发现mid对应的数比目标数大，说明在左边，更新r为mid-1*/
                r = mid-1;
            }else { /*情况3：如果发现mid对应的数比目标数小，说明在右边，更新l为mid+1*/
                l = mid+1;
            }
        }
        /*出了循环时，l必然等于r+1。返回l或者r+1均可*/
        /**err：为什么返回l(这里返回r+1也是等价的)*/
        return l; //等价于return r+1;
    }

    public int searchInsert_(int[] nums,int target){
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return left;
    }



    /*74.
    给你一个满足下述两条属性的 m x n 整数矩阵：
    每行中的整数从左到右按非严格递增顺序排列。
    每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
    * */
    //左闭右闭的写法
    public boolean searchMatrix(int[][] matrix, int target){
        int m = matrix.length,n = matrix[0].length;
        int right = m*n-1,left = 0;
        while (left<=right){
            int mid = left + (right-left)/2;
            int cur = matrix[mid/n][mid%n];
            if (cur>target){
                right = mid-1; /**当前区间包括right位置，mid位置不符合，因此right指向mid-1*/
            }else if (cur<target){
                left = mid+1;
            }else {
                return true;
            }
        }
        return false;
    }

    //左闭右开的写法
    public boolean searchMatrix_(int[][] matrix, int target) {
        int m =matrix.length,n = matrix[0].length;
        int right = m*n,left = 0;
        while (left<right){
            int mid = left + ((right-left)>>1);
            int cur = matrix[mid/n][mid%n];
            if (cur<target){
                left = mid+1;
            } else if (cur>target) {
                right = mid; /**当前的区间不包括right，且mid位置的值比目标值大，因此right指向mid————但是区间是不包括right位置的*/
            }else{
                return true;
            }
        }
        return false;
    }


    /*34.
    给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
    如果数组中不存在目标值 target，返回 [-1, -1]。
    你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
    * */
//    public int[] searchRange(int[] nums, int target) {
//        if (nums.length==0) return new int[]{-1,-1};
//        int left = searchLeft(nums,target);
//        int right = searchRight(nums,target);
//        return new int[]{left,right};
//    }
//    public int searchLeft(int[] nums,int target){
//        int l = 0,r = nums.length-1;
//        while (l<=r){
//            int mid = l+((r-l)>>1);
//            if (nums[mid]==target){
//                r = mid-1;
//            }else if (nums[mid]<target){
//                l = mid+1;
//            }else {
//                r = mid-1;
//            }
//        }
//        return nums[r+1]==target?r+1:-1;
//    }
//    public int searchRight(int[] nums,int target){
//        int l = 0,r = nums.length-1;
//        while (l<=r){
//            int mid = l+((r-l)>>1);
//            if (nums[mid]==target){
//                l = mid+1;
//            }else if (nums[mid]<target){
//                l = mid+1;
//            }else {
//                r = mid-1;
//            }
//        }
//        return nums[r+1]==target?r:-1;
//    }



    /*33.
    整数数组 nums 按升序排列，数组中的值 互不相同 。
在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    * */
//    public int search(int[] nums, int target) {
//
//    }


    /*153.
    已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    * */
    /**
     * 【解题关键】
     *      1. 必须是和数组中的最后一个元素比。原因：比之后能知道 应该向左还是向右继续寻
     * 找，才能找到最小值。。即和最后一个元素比能把搜寻区间减半
     * */
//    public int findMin(int[] nums) {
//
//    }



    /*4.
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
    * */
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//    }

    public static void main(String[] args) {
        _10binarySearch thisClass = new _10binarySearch();
//        thisClass.searchInsert(new int[]{1,3,5,6},5);

    }

}
