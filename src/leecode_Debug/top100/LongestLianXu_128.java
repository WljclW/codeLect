package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution_128 {
    /**
     * 第一种方法：
     * 一个参数代表指针的位置，一个参数表示当前序列的长度，一个参数记录目前找到的最大序列长度
     *      先对数组排序，然后依次判断；
     *      如果当前元素等于下一个元素，指针后移；
     *      如果不相等————
     *           如果后面的元素比当前的元素大1，则计数器加1；
     *           如果后面的元素比当前元素不止大1，则重置计数器。并且在重置计数器之前需要先更新max的值
     * */
    public int longestConsecutive(int[] nums) {
        if(nums.length ==0)
            return 0;
        Arrays.sort(nums);
        int l = 0;
        int con = 0;
        int max = 1;
        while(l+1< nums.length){
            if (nums[l+1] == nums[l]){
                l++;
            }else{
                if (nums[l+1] - nums[l] == 1){
                    con+=1;
                    l++;
                    max = max<con+1?con+1:max;
                }else{
                    con =0;
                    l++;
                }
            }
        }
        return max;
    }
}

public class LongestLianXu_128 {
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

            int ret = new Solution_128().longestConsecutive(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}