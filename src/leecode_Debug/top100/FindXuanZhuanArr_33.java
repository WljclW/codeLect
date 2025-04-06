package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class Solution_33 {
    /**
     * 我们发现这样的数组可以拆分为两个升序数组。。因此思路就是先找出分段的点，然后分别在两个片段查找
     * */
    public int search(int[] nums, int target) {
        //利用2个指针判断出两个片段的分割位置
        int left=0,right=nums.length-1;
        while(nums[left]>nums[right]){
            right--;
        }

        //根据两片段中最小的数和target的关系，来判断去哪一段找
        if(nums[left]<=target){
            return find(nums,left,right,target);
        }else{
            return find(nums,right+1,nums.length-1,target);
        }
    }
    //根据左边界和右边界在指定的范围判断，注意根据实参可知这里使用的是"左闭右闭"区间。
    public int find(int[] nums,int left,int right, int target){
        while(left<=right){
            int mid = left+(right-left)/2;
            if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }else if(nums[mid]==target){
                return mid;
            }
        }
        return -1;
    }
}

public class FindXuanZhuanArr_33 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            int ret = new Solution_33().search(nums, target);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}