package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**滑动窗口的实质还是双指针*/
public class _03SlideWindow {
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
        int left = 0; //窗口左边界
        int res = 0;
        int right = 0; //窗口右边界
        HashMap<Character, Integer> map = new HashMap<>();
        while(right<s.length()){
            char c = s.charAt(right);
            /*什么都不管，碰到的字符都先放进去；然后利用while循环保证当前窗口合法;
            * 保证当前窗口合法的前提下更新res的值*/
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

    /*自己写的方法————更容易理解*/
    public int lengthOfLongestSubstring01(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0,max = 0;
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while(map.get(c)>1){
                Integer leftVal = map.get(s.charAt(left));
                map.put(s.charAt(left),leftVal-1);
                left++;
            }
            max = Math.max(max,i-left+1);
        }
        return max;
    }




    /*
    * 438.给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
    * */
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> res = new ArrayList<>();

        return res;
    }
}
