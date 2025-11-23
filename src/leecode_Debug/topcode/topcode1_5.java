package leecode_Debug.topcode;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;


/**
 * 1~2————215、53、手撕快排、5、92、1143、93、
 * 3——————151、78、322、8
 * 4——————39、470、122、
 * 5——————112、113、179、718、手撕堆排序、14
 */
public class topcode1_5 {

    //TODO：215
    /*写法1：“43 / 44 个通过的测试用例”报错，超出时间限制*/
    Random random = new Random();
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums,0,n-1,n-k);
    }

    private int quickSort(int[] nums, int l, int r, int targetIndex) {
        if (l==r) return nums[l];
        int pivotIndex = l + random.nextInt(r - l + 1);
        pivotIndex = partition(nums,l,r,pivotIndex);

        if (pivotIndex==targetIndex){
            return nums[targetIndex];
        }else if (pivotIndex<targetIndex){
            return quickSort(nums,pivotIndex+1,r,targetIndex);
        }else {
            return quickSort(nums,l,pivotIndex-1,targetIndex);
        }
    }

    private int partition(int[] nums, int l, int r, int pivotIndex) {
        int pivot = nums[pivotIndex];
        swap(nums,pivotIndex,r);

        int storeIndex = l;
        for (int i = l; i < r; i++) {
            if (nums[i]<nums[r]){
                swap(nums,storeIndex,i);
                storeIndex++;
            }
        }

        swap(nums,storeIndex,r);
        return storeIndex;
    }

    private void swap(int[] nums, int l, int r) {
        int tmp  = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    //92


    //110

    /**
     【注意】其他的解法需要了解一下~~
     */
    public boolean isBalanced(TreeNode root) {
        if (root==null) return true;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return isBalanced(root.left)&&
                isBalanced(root.right)&&
                Math.abs(left-right)<=1; /**【注意】高度之差的要求：小于等于1（等于1是包含的）*/
    }

    private int getDepth(TreeNode root) {
        if (root==null) return 0;
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        return Math.max(l,r)+1;
    }
}
