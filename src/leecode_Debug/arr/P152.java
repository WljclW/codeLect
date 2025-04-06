package leecode_Debug.arr;

public class P152 {
    public static void main(String[] args) {
        int[] nums = new int[]{-4,-3,-2};
        System.out.println(maxProduct(nums));
    }
    public static int maxProduct(int[] nums) {
        int res=nums[0];
        int n = nums.length;
        int max=nums[0];
        int min=nums[0];
        for(int i=1;i<n;i++){
            max = Math.max(max*nums[i],Math.max(min*nums[i],nums[i]));
            min = Math.min(max*nums[i],Math.min(min*nums[i],nums[i]));
            res = Math.max(max,res);
        }
        return res;
    }
}
