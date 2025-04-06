package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Solution_560 {
    /**
     *      由于不是有序数组，因此不能用双指针(找不到窗口缩小的条件。比如窗口内的和大于k，但是可能下一个元素是负数，进入窗口后
     * 正好使窗口内的元素和为k)。。。
     *      这里需要引入前缀和的知识，推导出何为k的子数组前缀和应该满足什么关系？？然后每次得到一个前缀和，去map里面查有几个符合
     * 条件的另一半。
     * */
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(0,1);
        int preSum = 0;
        int count = 0;
        for(int n:nums){
            preSum += n;
            if(map.containsKey(preSum-k)){
                count += map.get(preSum-k);
            }
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return count;
    }
}

public class Q560 {
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
            int k = Integer.parseInt(line);

            int ret = new Solution_560().subarraySum(nums, k);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}