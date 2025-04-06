package zuo_course_02baseTiSheng.No_2;

/**
 * 求解字符串的最长回文子串长度(最长且是回文的，子串必须连续)只是manacher算法的一种运用而已。。它的作用于远不止此。
 *      manacher问题中的pArr数组的作用非常大————存储的是各个元素的最大回文半径。
 * */
public class Manacher {
    public static char[] manacherString(String str){
        /**
         *      该函数会将形参的str预处理为manachaer需要的格式————
         *          以'#'开始，'#'结束，中间每隔一个字符插入一个'#'。
         * */
        char[] charArr = str.toCharArray();
        char[] res = new char[charArr.length * 2 + 1];     //创建新的char数组存放预处理后的结果
        int index = 0;
        for (int i=0;i<charArr.length;i++)
            /**
             *      一种巧妙的实现：奇数位置放"#",偶数位置放实际的数。
             *      如何判断一个数是否是奇数？？？与1进行按位想与。
             * */
            res[i] = (i&1)==0? '#':charArr[index++];        //奇数位置插入'#',偶数位置插入str的某字符
        return res;
    }

    public static int maxLcpsLength(String str){
        if (str == null || str.length()==0)
            return 0;
        char[] charArr = manacherString(str);           //根据初始字符串str获得一个预处理后的字符串数组。。11222-》#1#1#2#2#2#
        int[] pArr = new int[charArr.length];           //记录charArr对应index位置的最大回文子串的长度
        int C = -1;     //中心
        int R = -1;     //右边界再往右的一个位置。最右的有效区其实是R-1位置
        int max = Integer.MIN_VALUE;                //记录charArr字符串中回文子串的最大长度
        for (int i=0;i<charArr.length;i++){         //需要循环处理charArr中的每个字符

            //下面的这一行代码表述的信息：根据i和R的关系得到i至少的回文半径。
            pArr[i] = R>i? Math.min(pArr[2*C-i],R-i):1;
            /**
             * ①如果i在R外，则i的回文半径至少是1，自己和自己构成回文。。这就对应第一种大情况(i在R外)。
             * ②如果R在i内，则i的回文半径至少得是pArr[2*C-i]和R-i的最小值。。这一步对应第二种大情况中的三种小情况。
             *      【说明】pArr[2*C-i]————2*C-i就是i的对称位置i`的位置，pArr[2*C-i]表述的就是对称点i`的回文半径。
             * */

            while(i+pArr[i]<charArr.length && i-pArr[i]>-1){        //while的条件就是保证左右两边都不越界
                if (charArr[i+pArr[i]] == charArr[i-pArr[i]])
                    pArr[i]++;
                else
                    break;
            }

            //决定是否更新
            if (i+pArr[i]>R){       //i+pArr[i]得出来得就是目前i位置最大回文串得右边界
                /**
                 *  如果当前i位置求出来得右边界大于目前记录得最大右边界R值，则同时更新R和对应的C。
                 * */
                R = i+pArr[i];          //更新回文子串的右边界
                C = i;                  //更新回文中心
            }
            max = Math.max(max,pArr[i]);        //需要记录目前为止找到得最大回文子串得长度
        }
        /**
         *  为什么返回max-1？？？
         *      max通过Math.max(max,pArr[i])得出，也就是说max是pArr数组得最大值。那pArr数组存放得又是什么呢？？存放的就是预处理
         *  后字符串各个位置对应的该位置最大回文子串长度。。
         *      max是处理后的回文子串的半径最大值，返回的max-1是预处理之前(原始字符串)的最大回文子串的长度，为什么？？？
         *          画图即可看出，其实max-1就是原始串中最大回文子串的长度。
         * */
        return max-1;
    }

    public static void main(String[] args) {
        String str1 = "abc123321cba";
        System.out.println(maxLcpsLength(str1));
    }
}
