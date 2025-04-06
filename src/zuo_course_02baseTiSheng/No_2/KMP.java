package zuo_course_02baseTiSheng.No_2;

/**
 * 查找子串的算法
 * */
public class KMP {
    public static int getIndexOf(String s,String m){
        if (s == null || m == null || m.length()<1 || s.length()<m.length())
            return -1;
        char[] str1 = s.toCharArray();
        char[] str2 = s.toCharArray();
        //i1，i2分别表示指向两个字符串的指针
        int i1 = 0;
        int i2 = 0;
        //next数组中index=m位置存放的是字串str2中index=m位置的最长匹配前缀
        int[] next = getNextArray(str2);
        while(i1<str1.length && i2<str2.length){
            if (str1[i1] == str2[i2]){          //如果相等则两个指针同时往后跳
                //进入if表示i1和i2位置的字符匹配成功
                i1++;
                i2++;           //如果匹配成功的话，i2的值会到str2.length，这个位置并不在str2的索引范围。
            }else if (next[i2] == -1){          //只有i2=0的时候next[i2]才等于-1；也就是说，进入这个else if表示i2都不能往前跳了。
                //进入这里表示子串的指针已经到索引为0的位置
                i1++;
            }else{
                /**
                 *      next[i2]存放index=i2时next数组的值—————也就是str2在i2当前位置的最长前缀匹配长度假设为m，表示开始的m位为
                 *  最长匹配，存在关系式next[i2] == m，也就是说最长前缀的index范围为0，1，2，3，4，5.....m-1。
                 *      此时由于str1[i1] ！= str2[i2]，i2要回调到最长前缀匹配的下一个元素即索引为m的位置，就是next[i2]的值。
                 * */
                i2 = next[i2];          //子串的指针往前跳。这一句要看懂
            }
        }
        /**
         *      如果此时i2 == str2.length成立，就表明i2指针已经到了子串str2的最后了，即匹配成功。要返回str2在str1中出现的index，
         *  因此返回i1-i2；
         *      否则表明i1指针到了str1的最后，表示匹配失败。
         * */
        return i2 == str2.length?i1-i2:-1;
    }

    private static int[] getNextArray(char[] ms) {
        /**
         *  获得子串对应位置的最长前缀匹配的数组
         * */
        if (ms.length == 1)
            return new int[]{-1};
        int[] next = new int[ms.length];
        //规定index=0的最长前缀匹配为-1，index=1的最长前缀匹配为0
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;         //cn————表示拿哪一个位置字符和i-1位置得字符比较；也等于next[i-1]得值。
        while(i<next.length){
            /**
             * 注：每一次比较不是从头开始挨个比较，而是利用i-1位置(假设当前要判断index=i位置得next数组得值)的信息得结果
             * */
            if (ms[i-1] == ms[cn]){
                //如果i-1位置的字符等于索引cn位置的字符，直接返回i位置的最长前缀匹配————即next[i]的值
                next[i++] = ++cn;
                /**
                 *  next[i++] = ++cn等价于下面的三个操作===>
                 *      cn = cn+1;
                 *      next[i+1] = cn;
                 *      i = i+1;
                 * */
            }
            else if (cn>0){
                //cn>0的时候表示还能往前跳。
                cn = next[cn];
            }else{
                //否则就表示该位置字符的最长前缀匹配是0，给next[i]赋值为0，继续往后研究next[i+1]的值
                next[i++] = 0;
            }
        }
        return next;
    }
}
