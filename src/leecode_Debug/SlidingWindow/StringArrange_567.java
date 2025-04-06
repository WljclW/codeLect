//package leecode_Debug.SlidingWindow;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//class Solution_567 {
//    public boolean checkInclusion(String s1, String s2) {
//
//        return false;
//    }
//}
//
//public class StringArrange_567 {
//    public static String stringToString(String input) {
//        return JsonArray.readFrom("[" + input + "]").get(0).asString();
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
//            String s1 = stringToString(line);
//            line = in.readLine();
//            String s2 = stringToString(line);
//
//            boolean ret = new Solution_567().checkInclusion(s1, s2);
//
//            String out = booleanToString(ret);
//
//            System.out.print(out);
//        }
//    }
//}