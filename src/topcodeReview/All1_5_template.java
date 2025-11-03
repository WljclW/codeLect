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
     * ==================================================================================================
     * ==================================================================================================
     * ==================================================================================================
     * ==================================================================================================
     * ==================================================================================================
     */
        /*162. 寻找峰值
峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     */
    public int findPeakElement(int[] nums) {
        int left  =0,right = nums.length-1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]>nums[mid+1]){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }


        /*470。用 Rand7() 实现 Rand10()
    给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。

你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。

每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
     */
    /**
     体会一下下面这两种方法的区别
     */
//    public int rand10() {
//        while (true){
//            int val = (rand7()-1)*7 + rand7();
//            if (val<=40){
//                return val%10 + 1;
//            }
//        }
//    }

//    public int rand10() {
//        while (true){
//            int val = (rand7()-1)*7 + (rand7()-1);
//            if (val<40){
//                return val%10 + 1;
//            }
//        }
//    }



        /*8. 字符串转换整数 (atoi)
请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。

函数 myAtoi(string s) 的算法如下：

空格：读入字符串并丢弃无用的前导空格（" "）
符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
返回整数作为最终结果。*/
    public int myAtoi(String s) {
        if (s==null||s.length()==0) return 0;
        int index = 0;
        while (index<s.length()&&s.charAt(index)==' '){
            index++;
        }

        if (index==s.length()) return 0;
        int sign  =1;
        if (s.charAt(index)=='-'||s.charAt(index)=='+'){
            sign = s.charAt(index)=='-'?-1:1;
            index++;
        }

        int res = 0;
        while (index<s.length()){
            char c = s.charAt(index);
            if (!Character.isDigit(c)) return sign*res;
            if (res > (Integer.MAX_VALUE-(c-'0'))/10)
                return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            res = res*10 + (c-'0');
        }
        return res*sign;
    }



        /*69. x 的平方根
给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。*/
    public int mySqrt(int x) {
        if (x<=1) return x;
        int left = 1,right = x/2;
        while (left<=right){
            int mid = left+(right-left)/2;
            long curVal = (long) mid*mid;
            if (curVal<x){
                left = mid+1;
            }else if (curVal>x){
                right = mid-1;
            }else {
                return mid;
            }
        }
        return right;
    }


        /*113. 路径总和 II
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。*/
    List<List<Integer>> resPathSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        resPathSum =new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        pathSum(root,targetSum,path);
        return resPathSum;
    }

    private void pathSum(TreeNode root, int targetSum, LinkedList<Integer> path) {
        if (root==null) return;
        path.add(root.val);
        if (root.left==null&&root.right==null&&targetSum==root.val){
            resPathSum.add(new LinkedList<>(path));
        }
        pathSum(root.left,targetSum-root.val,path);
        pathSum(root.right,targetSum-root.val,path);
        path.removeLast();
    }


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

    /**手撕快排*/
    public void quickSort(int[] arr,int left,int right) {
        if (left>=right) return;
        int pivotIndex = left + new Random().nextInt(left,right-left+1);
        swap2(arr,pivotIndex,right);

        pivotIndex = partition1(arr,left,right);
        quickSort(arr,left,pivotIndex-1);
        quickSort(arr,pivotIndex+1,right);
    }

    private int partition1(int[] arr, int left, int right) {
        int cur = left;
        for (int i = left; i < right; i++) {
            if (arr[cur]<arr[right]){
                swap2(arr,cur++,i);
            }
        }
        swap2(arr,cur,right);
        return cur;
    }

    private void swap2(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }


        /*33.....81是扩展（允许有重复元素）
    整数数组 nums 按升序排列，数组中的值 互不相同 。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
    你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    * */
    /**大于左边界 比较的版本*/
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (nums[mid]==target) return mid;
            if (nums[mid]>nums[left]){ /**大于左边界 比较的版本，大于左边界，说明mid的左侧是有序的*/
                if (target>=nums[left]&&target<nums[mid]){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }else { /**否则说明右侧是有序的*/
                if (target>nums[mid]&&target<=nums[right]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }


    /**小于右边界的版本*/
    public int search1(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if (target==nums[mid]) return mid;
            if (nums[mid]<nums[right]){ /**小于右边界 比较的版本,小于右边界说明mid右侧是有序的*/
                if (target>nums[mid]&&target<=nums[right]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }else { /**否则说明左边是有序的*/
                if (target>=nums[left]&&target<nums[mid]){
                    right = mid-1;
                }else {
                    left =mid+1;
                }
            }
        }
        return -1;
    }


}
