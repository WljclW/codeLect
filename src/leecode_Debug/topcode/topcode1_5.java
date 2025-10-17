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


    //78
    List<List<Integer>> resSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubsets = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        subsetsBack(nums,path,0);
        return resSubsets;
    }

    private void subsetsBack(int[] nums, LinkedList<Integer> path, int index) {
//        if (index==nums.length) return; //写在这里是错误的！
        resSubsets.add(new LinkedList<>(path));
        /**err：不加这一句就可以，并不会发生StackOverflow！！但是如果加了这一句，则——————
         *      这一句必须在“resSubsets.add(new LinkedList<>(path));”的后面，不然结果会
         *  少很多，一句话概况少了多少，凡是包含最后nums最后一个元素的 子集，结果都没有。
         *      进一步解释为什么？因为如果index==nums.length，根据for循环逻辑可知，一定是
         *  上一步把最后一个元素添加进path了，然后递归调用subsetsBack，此时index==nums.length。
         *  如果下面的这句话放在subsetsBack的第一行，就导致方法直接返回了，path没有添加进
         *  结果！！!
         *      再解释一下为什么不会发生StackOverflow？？方法的返回值是null，即使没有这一句，当
         * index来到nums.length的时候，for循环由于循环条件不满足因此不会循环，导致方法结束，因此
         * 并不会无终止的持续递归下去，因此不会栈溢出。
         *  */
        if (index==nums.length) return;
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsBack(nums,path,i+1);
            path.removeLast();
        }
    }


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
