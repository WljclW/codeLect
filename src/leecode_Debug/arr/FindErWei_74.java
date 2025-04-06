//package leecode_Debug.arr;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class FindErWei_74 {
//}
//
//
//class Solution {
//    public boolean searchMatrix(int[][] matrix, int target) {
//        int rows = matrix[0].length;
//        int right = rows-1;
//        for(int i=0;i<matrix.length;i++){
//            if(matrix[i][rows-1]>=target){
//                int left = 0;
//                while(left<=right){
//                    int mid = left+(right-left)/2;
//                    if(matrix[i][mid]==target)
//                        return true;
//                    else if(matrix[i][mid]>target){
//                        right = mid-1;
//                    }else if(matrix[i][mid]<target){
//                        left = mid+1;
//                    }
//                }
//                return matrix[i][left] == target?true:false;
//            }
//        }
//        return false;
//    }
//}
//
//public class MainClass {
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
//    public static int[][] stringToInt2dArray(String input) {
//        JsonArray jsonArray = JsonArray.readFrom(input);
//        if (jsonArray.size() == 0) {
//            return new int[0][0];
//        }
//
//        int[][] arr = new int[jsonArray.size()][];
//        for (int i = 0; i < arr.length; i++) {
//            JsonArray cols = jsonArray.get(i).asArray();
//            arr[i] = stringToIntegerArray(cols.toString());
//        }
//        return arr;
//    }
//
//    public static String booleanToString(boolean input) {
//        return input ? "True" : "False";
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            int[][] matrix = stringToInt2dArray(line);
//            line = in.readLine();
//            int target = Integer.parseInt(line);
//
//            boolean ret = new Solution().searchMatrix(matrix, target);
//
//            String out = booleanToString(ret);
//
//            System.out.print(out);
//        }
//    }
//}