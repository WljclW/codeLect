package topcodeReview;

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


}
