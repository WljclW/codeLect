//package leecode_Debug.dandiaozhan;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashSet;
//import java.util.Stack;
//
//class Solution {
//    public String removeDuplicateLetters(String s) {
//        char[] chs = s.toCharArray();
//        String res = new String("");
//        Stack<Character> stack = new Stack<>();
//        HashSet<Character> set = new HashSet<>();
//        for (int i=0;i< chs.length;i++){
//            while(!stack.isEmpty() && chs[i] )
//        }
//        return "";
//    }
//}
//
//public class LowestZidianxu_316 {
//    public static String stringToString(String input) {
//        return JsonArray.readFrom("[" + input + "]").get(0).asString();
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            String s = stringToString(line);
//
//            String ret = new Solution().removeDuplicateLetters(s);
//
//            String out = (ret);
//
//            System.out.print(out);
//        }
//    }
//}