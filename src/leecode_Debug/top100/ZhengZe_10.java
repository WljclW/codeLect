package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class ZhengZe_10 {

}


class Solution10 {
    int[][] memo;
    public boolean isMatch(String s, String p) {
        int m = s.length(),n=p.length();
        //建立备忘录,-1表示没有计算过....备忘录有三种数值=====-1,0,1...分别表示没计算过/计算过是false/计算过是true.
        memo = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(memo[i],-1);
        }

        return dp(s,0,p,0);             //具体返回什么取决于dp数组的定义
    }

    public boolean dp(String s,int i,String p,int j){
        int m = s.length();
        int n = p.length();

        /**
         * base-case有两种
         *      ①模式串匹配结束了...如果字符串匹配结束则返回true,如果字符串还没有匹配结束返回false.
         *      ②字符串匹配结束了...如果剩下的模式串可以匹配空串则表示匹配成功;如果剩下的模式串不能匹配空串则返回false.
         * */
        if (j==n)
            return i==m;
        if (i==m){
            if ((n-j)%2==1)
                return false;
            for (;j+1<n;j+=2){
                if(p.charAt(j+1)!='*')
                    return false;
            }
            return true;
        }

        boolean res = false;

        //查找备忘录有没有当前记录,如果有则直接返回
        if(memo[i][j]!=-1){
            return memo[i][j] == 1 ? true : false;
        }

        /**
         * 对于一个普通位置的分析...
         * */
        //如果两个串的当前位置相等 或者 模式串的当前位置是'.'
        if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'){
            if(j+1<n && p.charAt(j+1)=='*'){            //如果模式串当前位置的后一个位置是"*",则此时可能匹配0次或者多次.
                res =  dp(s,i,p,j+2)
                        || dp(s,i+1,p,j);
            }else{                                      //否则表示没有'*'参与,就直接往后匹配就可
                res = dp(s,i+1,p,j+1);
            }
        }
        /**
         * 出了if表示-----s.charAt(i)!=p.charAt(j) && p.charAt(j)!='.'
         *      此时如果模式串的下一个位置不是"*"表示匹配不成功;如果模式串的下一个位置p.charAt(j+1)是"*",则必须匹配前面的字符即
         * p.charAt(j)0次,因此此时res = dp(s,i,p,j+2);
         *      dp(s,i,p,j+2);表示字符串的指针不动,把模式串的指针往后移2个位置....比如:字符串是"abcd",模式串是"ac*b",则模式串中
         * 的"c*"必须是匹配0次c,因为p.charAt(1) = c,但是字符串中指针对应的值是b...
        */
        else{
            if(j+1<n && p.charAt(j+1)=='*'){
                res = dp(s,i,p,j+2);
            }else{
                res = false;
            }
        }
        memo[i][j] = res == true ? 1 : 0;
        return res;
    }
}



//
//public class MainClass {
//    public static String stringToString(String input) {
//        return JsonArray.readFrom("[" + input + "]").get(0).asString();
//    }
//
//    public static String booleanToString(boolean input) {
//        return input ? "True" : "False";
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            String s = stringToString(line);
//            line = in.readLine();
//            String p = stringToString(line);
//
//            boolean ret = new Solution().isMatch(s, p);
//
//            String out = booleanToString(ret);
//
//            System.out.print(out);
//        }
//    }
//}