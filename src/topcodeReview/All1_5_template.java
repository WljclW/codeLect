package topcodeReview;

import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * 这是一段测试环境的代码
 * 使用时复制一份！！！！！！
 * 10.27
 */
public class All1_5_template {
    /*151. 反转字符串中的单词
给你一个字符串 s ，请你反转字符串中 单词 的顺序。

单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。

注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。*/
    public String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(arr));
        return String.join(" ",arr);
    }


    public String reverseWords1(String s) {
        StringBuilder res = new StringBuilder();
        s = s.trim();
        int i = s.length()-1,j = i;
        while (i>=0){
            while (i>=0&&s.charAt(i)!=' ') i--;
            res.append(s.substring(i+1,j+1)).append(' ');
            while (i>=0&&s.charAt(i)==' ') i--;
            j = i;
        }
        return res.toString();
    }

    /*227. 基本计算器 II
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。

你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。

注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     */
//    public int calculate(String s) {
//
//    }


    /*468. 验证IP地址
给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。

有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。

一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:

1 <= xi.length <= 4
xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
在 xi 中允许前导零。
例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
     */
    public static void main(String[] args) {
        All1_5_template all15Template = new All1_5_template();
        all15Template.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:");
    }

    public String validIPAddress(String queryIP) {
        if (queryIP.contains(".")){
            String[] split = queryIP.split("\\.",-1);
            return isIPV4(split);
        }
        if (queryIP.contains(":")){
            String[] split = queryIP.split(":",-1);
            return isIPV6(split);
        }
        return "Neither";
    }

    private String isIPV6(String[] split) {
        if (split.length == 8){
            for (int i = 0; i < split.length; i++) {
                if (split[i].length()<=4&&split[i].length()>0){
                    for (char c:split[i].toCharArray()){
                        if ((c>='0' && c<='9')||(c>='a'&&c<='z')||(c>='A'&&c<='Z')){
                            continue;
                        }else {
                            return "Neither";
                        }
                    }
                    return "IPv6";
                }else {
                    break;
                }
            }
        }
        return "Neither";
    }

    private String isIPV4(String[] split) {
        if (split.length!=4)
            return "Neither";
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (!(s.length()==1 ||
                    (s.length()==2&&Integer.valueOf(s)>=10) ||
                    (s.length()==3&&Integer.valueOf(s)>=100&&Integer.valueOf(s)<=255)
                ))
                return "Neither";
        }
        return "IPv4";
    }


    /**
     * ==================================================================================================
     * ==================================================================================================
     * ==================================================================================================
     * ==================================================================================================
     */
        /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
    "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过
    在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
    List<String> resRestoreIpAddresses;
    public List<String> restoreIpAddresses(String s) {
        resRestoreIpAddresses = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        restoreIpAddresses(s,sb,0,0);
        return resRestoreIpAddresses;
    }

    /**重要的是捋清楚每次再什么位置添加‘.’*/
    private void restoreIpAddresses(String s, StringBuilder sb, int pointNum, int index) {
        if (pointNum==3){
            if (isValid1(s,index,s.length()-1)){
                resRestoreIpAddresses.add(new String(sb));
            }
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isValid1(s,index,i)){
                sb.insert(i+1+pointNum,'.');
                restoreIpAddresses(s,sb,pointNum+1,index);
                sb.deleteCharAt(i+1+pointNum);
            }
        }
    }

    private boolean isValid1(String s, int left, int right) {
        if (left==right) return true;
        if (left>right) return false;
        if (right-left>2) return false;
        if (right-left>=1&&s.charAt(left)=='0') return false;
        if ((right-left==2&&Integer.valueOf(s.substring(left,right+1))>255)) return false;
        return true;
    }
}
