package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlideWindow {
    /*
    * 3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。无重复字符的最长子串
     * */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,i);
            }else{
                Integer index = map.get(c);
                map.clear();
                res = Math.max(i-index,res);
            }
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
