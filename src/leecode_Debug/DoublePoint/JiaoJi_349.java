package leecode_Debug.DoublePoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

class Solution_349 {
    /**
     * 求两个数组的交集方法：
     *      ①一个数组遍历加入到set。然后遍历第二个数组，如果元素在set中，需要加入到resultSet。最后把resultSet中的元素拷贝到数组
     *      ②下面的方法
     * */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();     //创建集合
        int i=0;
        while(i< nums1.length){           //把第一个数组加入到集合
            set.add(nums1[i++]);
        }
        i = 0;
        for(int n:nums1){
            System.out.println(n);
        }
        for (int j=0;j<nums2.length;j++){      //遍历第二个数组看集合中有没有
            if (set.contains(nums2[j]) && i<nums1.length){
                nums1[i++] = nums2[j];
                set.remove(nums2[j]);           //有的话需要删除，不然可能重复赋给nums1的某个元素。
            }
        }
        //创建新的数组并将结果拷贝过来
        int[] ints = new int[i];
        for (int k=0;k<i;k++){
            ints[k] = nums1[k];
        }
        return ints;
    }
}

public class JiaoJi_349 {
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

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);

            int[] ret = new Solution_349().intersection(nums1, nums2);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}