//package leecode_Debug.DoublePoint;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//
//class Solution_287 {
//    public int findDuplicate(int[] nums) {
//        /**
//         * 暴力的话超出时间限制。
//         * */
////        for (int i=0;i<nums.length;i++){
////            for(int j=i+1;j<nums.length;j++){
////                if (j==i){
////                    continue;
////                }
////                if (nums[i]==nums[j])
////                    return nums[i];
////            }
////        }
////        return 0;
//        return 0;
//    }
//}
//
//public class FindRepeatEle_287 {
//    public static int[] stringToIntegerArray(String input) {
//        input = input.trim();
//        input = input.substring(1, input.length() - 1);
//        if (input.length() == 0) {
//            return new int[0];
//        }
//
//        String[] parts = input.split(",");
//        int[] output = new int[parts.length];
//        for(int index = 0; index < parts.length; index++) {
//            String part = parts[index].trim();
//            output[index] = Integer.parseInt(part);
//        }
//        return output;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            int[] nums = stringToIntegerArray(line);
//
//            int ret = new Solution_287().findDuplicate(nums);
//
//            String out = String.valueOf(ret);
//
//            System.out.print(out);
//        }
//    }
//}