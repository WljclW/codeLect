//package leecode_Debug.dandiaozhan;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.Stack;
//
//
//class Solution {
//    public String removeKdigits(String num, int k) {
//        return "";
//    }
//}
//
//public class DelKDigitFromStr {
//    public static String stringToString(String input) {
//        return JsonArray.readFrom("[" + input + "]").get(0).asString();
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            String num = stringToString(line);
//            line = in.readLine();
//            int k = Integer.parseInt(line);
//
//            String ret = new Solution().removeKdigits(num, k);
//
//            String out = (ret);
//
//            System.out.print(out);
//        }
//    }
//}
