package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Q3 {
}


class Solution_Q3 {

    /**
     * 自己的写法
     * */
//    public int lengthOfLongestSubstring(String s) {
//        if(s.equals(""))
//            return 0;
//        int l=0,right=0;
//        HashMap<Character, Integer> map = new HashMap<>();
//        int max = 0;
//        while(right<s.length()){
//            if(!map.containsKey(s.charAt(right))){
//                map.put(s.charAt(right),right);
//            }else{
//                Integer index = map.get(s.charAt(right));
//                max = max>(right-l)?max:right-l;
//                for(int i=l;i<index+1;i++){
//                    map.remove(s.charAt(i));
//                }
//                map.put(s.charAt(right),right);
//                l = index+1;
//            }
//            right++;
//        }
//        if(right==s.length())
//            max =   max>(right-l)?max:right-l;
//        return max;
//    }


    /**
     * 更优的代码：
     * */
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                //如果map里面已经包含了当前的字符，则拿出它的索引。。。这个索引就是左指针要移动到的地方
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);         //不用删除原来的值，因为key相同时会替换掉。
            max = Math.max(max,i-left+1);           //更新最大值
        }
        return max;

    }
}

//public class MainClass {
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
//            int ret = new Solution().lengthOfLongestSubstring(s);
//
//            String out = String.valueOf(ret);
//
//            System.out.print(out);
//        }
//    }
//}