package leecode_Debug._hot100;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author mini-zch
 * @date 2025/8/15 10:19
 */
public class revire_part_01 {
    /*
    71. 简化路径
    给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为 更加简洁的规范路径。

    在 Unix 风格的文件系统中规则如下：

    一个点 '.' 表示当前目录本身。
    此外，两个点 '..' 表示将目录切换到上一级（指向父目录）。
    任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
    任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。
    返回的 简化路径 必须遵循下述格式：

    始终以斜杠 '/' 开头。
    两个目录名之间必须只有一个斜杠 '/' 。
    最后一个目录名（如果存在）不能 以 '/' 结尾。
    此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
    返回简化后得到的 规范路径 。
     */
    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            if (".".equals(str)||"".equals(str)){
                continue;
            } else if ("..".equals(str)) {
                if (!stack.isEmpty()){
                    stack.pop();
                }
            }else {
                stack.push(str);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()){
            return "/";
        }
        for (String str:stack){
            sb.append("/");
            sb.append(str);
        }
        return sb.toString();
    }

    /*
    264. 丑数 II
    给你一个整数 n ，请你找出并返回第 n 个 丑数 。
    丑数 就是质因子只包含 2、3 和 5 的正整数。
     */
    public int nthUglyNumber(int n) {
        int i = 1;
        int res = -1;
        int p1=i,p2=i,p3=i;
        while (i<=n){
            int num1 = p1*2;
            int num2 = p2*3;
            int num3 = p3*3;
            res = Math.min(Math.max(num1,num2),num3);
            if (res==num1) p1++;
            if (res==num2) p2++;
            if (res==num3) p3++;
            i++;
        }
        return res;
    }

     /*611
    给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
     */
    /**跟已有的答案不一样，是否可行*/
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length-2; i++) {
            int l = i+1,r = l+1;
            while (r<nums.length){
                int curVal = nums[i]+nums[l]+nums[r];
                if (nums[i]+nums[l]<nums[r]){
                    res += (r-l);
                }else {
                    r++;
                }
            }
        }
        return res;
    }

}
