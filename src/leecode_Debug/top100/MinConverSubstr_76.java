package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MinConverSubstr_76 {
    class Solution {
        public String minWindow(String s, String t) {
            HashMap<Character, Integer> need = new HashMap<>();
            HashMap<Character, Integer> window = new HashMap<>();
            int inval = 0;
            char[] chars = t.toCharArray();
            for (char c:chars)
                need.put(c,need.getOrDefault(c,0)+1);
            //定义左右指针表示窗口的左右边界。。表示的区域市左闭右开的。
            int left = 0,right = 0;
            //记录满足的子串长度，以及在s中的起始位置
            int len = Integer.MAX_VALUE, start = -1;
            char[] ss = s.toCharArray();
            while (right<ss.length){
                /**
                 * 得到即将进入窗口的元素
                 * */
                //c是将要移入窗口的字符，这里记录c————c的加入可能会导致一些元素的更新，到底会不会更新呢？？需要判断。
                char c = ss[right];
                right++;                        //之前已经记录了c，这里就可以改变右指针了

                /**
                 * c加入了窗口，需要对一些部件更新嘛？？？下面的if语句块就是判断是不是需要更新，以及怎么更新————最终
                 * 某一次加入后窗口可能满足题意了
                 * */
                //进行窗口内数据的更新
                if (need.containsKey(c)){
                    window.put(c,window.getOrDefault(c,0)+1);       //修改window的c对应的值
                    if (window.get(c).equals(need.get(c))){         //如果某一字符达到了要求数量，inval++;
                        inval++;
                    }
                }

                /**
                 * 一旦某一时刻窗口达到题目的要求了，我们就需要收缩窗口，并判断如下：
                 *      ①是不是收缩后窗口还是满足要求，如果满足则继续收缩————while循环
                 *      ②窗口的收缩也会涉及到一些部件的更新，需要对其做出更新。
                 * */
                //判断左侧窗口是不是需要收缩，这里左边界可能不止移动一次，因此要用while循环
                while(inval==need.size()){              //如果当前窗口已经包含了一个子串，那就考虑收缩窗口直到不满足要求。
                    //如果此时找到的子串right-left小于记录值len，则更新len以及结果的index；
                    if (right-left<len){
                        len = right-left;
                        start = left;
                    }
                    //现在窗口要收缩，记录要出去的字符；并移动左指针
                    char d = ss[left];
                    left ++;
                    /**
                     * d将要移出窗口，d一旦出去了可能窗口就不再满足题目要求等，因此它出去可能引起一些部件值的更新，下面的if
                     * 语句块完成的就是这样作用
                     * */
                    //进行窗口内一系列数据的更新
                    if (need.containsKey(d)){
                        window.put(d,window.get(d)-1);
                        if (window.get(d)<need.get(d)){
                            inval--;
                        }
                    }
                }
            }
            //返回最小覆盖字串
            return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
        }
    }

}


