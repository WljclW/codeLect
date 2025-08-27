package leecode_Debug._hot100;

import java.util.*;

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
    /**
     * 【建议】建议使用lengthOfLongestSubstring2 或者 lengthOfLongestSubstring01
     * 【关键】遍历s的每一个字符。。。
     *      每到一个位置right，先将这个字符加进map（无脑的先加到map）；然后利用while循环保证窗口是合法的
     * */
    public int lengthOfLongestSubstring(String s) {
        int left = 0,right=0; //窗口左边界、右边界
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while(right<s.length()){
            char c = s.charAt(right);
            /*什么都不管，碰到的字符都先放进去；然后利用while循环保证当前窗口合法;
            * 保证当前窗口合法的前提下更新res的值*/
            map.put(c,map.getOrDefault(c,0)+1); //step1：什么都不管，先入map
            while(map.get(c)>1){ //step2：保证窗口合法
                char cur = s.charAt(left);
                map.put(cur,map.get(cur)-1);
                left++;
            }
            right++; /**注意要移动当前指针，否则“java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "java.util.HashMap.get(Object)" is null”*/
            res = Math.max(res,right-left); //③保证窗口合法在更新结果
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

    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0,cur = 0;
        int res = 0;
        while (cur<s.length()){
            char c = s.charAt(cur);
            map.put(c,map.getOrDefault(c,0)+1);
            while (map.get(c)>1){
                char cLeft = s.charAt(left++);
                map.put(cLeft,map.get(cLeft)-1);
            }
            res = Math.max(res,cur-left+1);
            cur++; /**这句话如果是在上一句话之前，就不用使用”cur-left+1“了，不需要+1。比如方法 lengthOfLongestSubstring 就是这样*/
        }
        return res;
    }




    /*
    * 438.给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
    * */
    public List<Integer> findAnagrams(String s, String p) {
        LinkedList<Integer> res = new LinkedList<>();
        if (s.length()<p.length()) return res;
        int[] countS = new int[26];
        int[] countP = new int[26];
        for (char c:p.toCharArray()){
            countP[c-'a']++;
        }

        for (int i = 0; i < p.length(); i++) {
            countS[p.charAt(i)-'a']++;
        }
        if (Arrays.equals(countS,countP)) res.add(0);

        for (int i = p.length(); i < s.length(); i++) {
            char right = s.charAt(i);
            char left = s.charAt(i - p.length());
            countS[left-'a']--;
            countS[right-'a']++;
            if (Arrays.equals(countS,countP)) res.add(i-p.length());
        }

        return res;
    }


    /**下面是github的diamagnetic，目测也是对的。。。。。
     举例（s="cbaebabacd", p="abc"）
     初始化 count：a:1 b:1 c:1 其余:0

     初始化 match：26 - 3 = 23（因为有 3 个非零，其它 23 个是 0）

     i=0, in='c'：--count[c] 由 1->0 → match=24

     i=1, in='b'：--count[b] 由 1->0 → match=25

     i=2, in='a'：--count[a] 由 1->0 → match=26 → 此时 i - pLen + 1 = 0 加入结果
     （窗口为 "cba" 与 "abc" 异位词）

     之后继续滑动，移除、加入字符并维护 match，找到下一个起点 6。
     */
    public List<Integer> findAnagrams_github(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) return res;

        int[] count = new int[26];
        for (char c : p.toCharArray()) count[c - 'a']++;

        // 关键：初始化 match 为 count 中等于 0 的条目数
        int match = 0;
        for (int v : count) if (v == 0) match++;

        for (int i = 0; i < sLen; i++) {
            // 新字符进入窗口：先 -- 操作，再判断是否刚好变为 0
            int in = s.charAt(i) - 'a';
            if (--count[in] == 0) {
                match++; // 从非0变为0，表示该字符目前在窗口中的频率与 p 中一致
            }

            // 窗口超出长度时，移除最左侧字符
            if (i >= pLen) {
                int out = s.charAt(i - pLen) - 'a';
                if (count[out]++ == 0) {
                    match--; // 移除使得该字符不再与 p 中匹配
                }
            }

            // 当所有 26 个字符的 count 都为 0，说明当前窗口是 p 的异位词
            if (match == 26) {
                res.add(i - pLen + 1);
            }
        }
        return res;
    }

}
