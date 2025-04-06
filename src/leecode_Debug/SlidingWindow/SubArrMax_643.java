package leecode_Debug.SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
class Solution_643 {
    public double findMaxAverage(int[] nums, int k) {
        int i=0;
        int j=0;
        k = (k>=nums.length)? nums.length : k;
        int max=0;
        int sum=0;
        while(j-i<=k-1){
            sum+=nums[j++];
        }
        max = sum;
        while(j< nums.length){
            sum = sum+nums[j++]-nums[i++];
            max = max<sum?sum:max;
        }
        return (double) max/k;
    }


    /**
     * 由于窗口的大小是固定的k，因此左边界的索引可以通过右边界的索引减出来。
     * */
    public double findMaxAverage_GaiJin(int[] nums, int k) {
        int sum = 0;
        /**
         * 由于窗口的大小是固定的，因此先生成大小为k的窗口
         * */
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        //定义最大值
        int maxSum = sum;
        /**
         * 窗口滑动，过程中保持窗口的大小不变为k
         * */
        for (int j = k; j < nums.length; j++) {
            sum = sum + nums[j] - nums[j-k];        //加上右边界新进来的元素，减去左边界出去的元素
            maxSum = Math.max(sum,maxSum);
        }

        return 1.0 * maxSum / k;
    }
}

public class SubArrMax_643 {
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

    public static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            double ret = new Solution_643().findMaxAverage(nums, k);

            String out = doubleToString(ret);

            System.out.print(out);
        }
    }
}