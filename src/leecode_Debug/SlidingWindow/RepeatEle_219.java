package leecode_Debug.SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;


class Solution_219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int left =0;
        int right =0;
        while(right-left<k){                //先搞出来一个大小为K的窗口
            if (set.contains(nums[right]))
                return true;
            set.add(nums[right]);
            right++;
        }
        while(right<nums.length){                   //然后移动窗口看看其他位置
            if (set.contains(nums[right]))
                return true;
            set.add(nums[right]);
            set.remove(nums[left]);
            left++;
            right++;
        }
        return false;
    }
}

public class RepeatEle_219 {
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

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            boolean ret = new Solution_219().containsNearbyDuplicate(nums, k);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}