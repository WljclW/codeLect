package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**滑动窗口的实质还是双指针*/
public class SlideWindow {
    /*
    * 3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。无重复字符的最长子串
     * */
//    public int lengthOfLongestSubstring(String s) {
//        int res = 0;
//        int index = 0;
//        HashMap<Character, Integer> map = new HashMap<>();
//        for (int i=0;i<s.length();i++){
//            char c = s.charAt(i);
//            if(!map.containsKey(c)){
//                map.put(c,i);
//            }else{
//                /**
//                 * 这种逻辑是错误的，这里需要将两个重复字符之间的字符都出栈
//                 * */
//                Integer pre = map.get(c);
//                res = Math.max(i-index+1,res);
//                index = pre;
//            }
//        }
//        return res;
//    }

    /*
    * []: map+双指针。
    *     关键在于如何当前的字符在map中得到的value>1，就循环出栈前面的元素。前面的元素如何查
    *   找呢？因此需要借助left指针
    * */
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int res = 0;
        int right = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while(right<s.length()){
            char c = s.charAt(right);
            map.put(c,map.getOrDefault(c,0)+1);
            while(map.get(c)>1){
                char cur = s.charAt(left);
                map.put(cur,map.get(cur)-1);
                left++;
            }
            res = Math.max(res,right-left+1);
        }
        return res;
    }


    /*
    * 438.给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
    * */
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> res = new ArrayList<>();
        if(p.length()>s.length()){
            return res;
        }
        for (int i=0;i<s.length()-p.length();i++){

        }

        return res;
    }
}
