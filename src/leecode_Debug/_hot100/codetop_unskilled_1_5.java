package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;

import java.util.Arrays;
import java.util.Random;

/**
 * @author mini-zch
 * @date 2025/9/2 14:43
 */
public class codetop_unskilled_1_5 {


    public static void main(String[] args) {
        codetop_unskilled_1_5 codetop_unskilled_1_5 = new codetop_unskilled_1_5();
        codetop_unskilled_1_5.findKthLargest(new int[]{3,2,1,5,6,4},2);
    }

    //215
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums,0,n-1,n-k);
    }

    private int quickSort(int[] nums, int left, int right, int index) {
        if (left==right) return nums[left];

        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap(nums,pivotIndex,right);
        pivotIndex = partion(nums,left,right);

        if (pivotIndex==index){
            return nums[pivotIndex];
        } else if (pivotIndex < index) {
            return quickSort(nums,pivotIndex+1,right,index);
        }else {
            return quickSort(nums,left,pivotIndex-1,index);
        }
    }

    private int partion(int[] nums, int left, int right) {
        int fir = left;
        for (int i = 0; i < right; i++) {
            if (nums[i]<nums[right]){
                swap(nums,fir++,i);
            }
        }
        swap(nums,fir,right);
        return fir;
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }



    //53
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            preSum = Math.max(preSum,nums[i]);
            res = Math.max(preSum,res);
        }
        return res;
    }


    //手撕快排


    //5
    /**
     * 马拉车算法 可以实现将时间复杂度降为O(N)，但是空间复杂度高于“中心扩散法”，空间复杂度为O(N)。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        /*step1：特殊情况的考虑*/
        if (s == null || s.length() == 0) return "";
        /*step2：StringBuilder预处理字符串并构造出新字符串。做法————给原字符串所有的间隔（包括开始位置和结束位置）都加“#”*/
        StringBuilder sb = new StringBuilder("#");
        for (char c : s.toCharArray()) {
            sb.append(c).append("#");
        }
        String str = sb.toString();
        int n = str.length();

        int[] p = new int[n]; //声明int数组用于存放每一个位置的回文半径
        int center = 0, right = 0; /*center表示当前的回文中心；right表示当前最远的回文半径。*/
        int start = 0, maxLen = 0; /*这是返回结果的关键信息。start表示“最长回文子串”的开始位置，maxLen表示“最长回文子串”的长度*/
        /*step3：for循环依次研究每一个位置*/
        for (int i = 0; i < n; i++) {
            /*3.1 这一步就是“马拉车算法”的核心精髓。。具体的做法如下————
                      （1）计算出i位置关于“目前回文中心”center的对称位置。
                      （2）如果现在研究的位置i不超过“最远回文右边界”right，则可以快速计算出p[i]————这一步会充分用到之前已经计算的信息*/
            int mirror = 2 * center - i;
            if (i < right) { /**err：i小于“回文串的最右边界”，会误写成mirror*/
                p[i] = Math.min(right - i, p[mirror]); /**得到i位置回文半径的最小值，i位置的回文串还可能往两边扩————3.2干的活*/
            }

            /*3.2   尝试向两边继续扩展，看看位置i是否能得到更长的回文子串。
                    这一步具体的做法呢，如下————
                        ①声明两个指针l,r分别为i位置的左右；
                        ②只要l和r不越界 并且 l和r位置的字符相等，就“增加p[i]”、移动l和r指针*/
            int l = i - p[i] - 1, r = i + p[i] + 1;
            while (l >= 0 && r < n && str.charAt(l) == str.charAt(r)) {
                p[i]++;
                l--;
                r++;
            }
            /*3.3 更新“最远回文右边界”。
             *   “最远回文右边界”right 和 “当前的回文中心”center 是成对起作用的，因此更新right的时候就要更新center。
             *   为什么说是“成对起作用”的呢？？因为right和i比较能加速p[i]计算；center用于计算位置i关于回文中心的对称位置*/
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
            /*3.4 更新最长回文子串。
             *   “最长回文子串”maxLen 和 “回文子串的开始位置”start也是成对出现的，因此更新maxLen的时候呀需要更新start。*/
            if (p[i] > maxLen) {
                maxLen = p[i]; /**回文半径就是最长的长度*/
                start = (i - maxLen) / 2; /**？？？*/
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
     * 中心扩散法的复杂度分析：
     *      时间复杂度：O(N^2)；
     *      空间复杂度：O(1)
     *中心扩散法中最关键的有两点：
     *      第一点：双指针 + while循环计算回文长度返回，此时返回值是“(r-1)-(l+1)+1”即r-l-1。并且这个值就
     *  是真实的回文子串的长度，因为计算方式是边界索引值相减的，与“回文中心是（i，i）还是（i，i+1）”是没有关
     *  系的。
     *      第二点：更新回文子串的最大长度时，同时更新回文子串的起始位置，这个起始位置是关键————i-(len-1)/2。
     * @param s
     * @return
     */
    public String longestPalindrome_1(String s) {
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int odd = getLength(s, i, i);
            int even = getLength(s, i, i + 1);
            int curLen = Math.max(odd, even);
            if (curLen > maxLen) {
                maxLen = curLen;
                start = i - (curLen - 1) / 2; /**这里是如何理解的？？*/
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int getLength(String s, int l, int r) {
        while (l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        /**
         * 如何理解下面返回值的计算？？？
         *      根据while循环可知，只要“满足不越界 并且 字符相等”，就会执行while循环。。。。如果某次不再进
         *   入了，说明此时此刻此时l和r“要么越界了 要么对应的字符不相等”————即回文子串不包含r位置也不包含l位
         *   置。
         *      如果是之前滑动窗口、二分法等包括左右边界，此时计算长度的表达式就是“r-l+1”。
         *      但是这里是既不包含左边界，也不包含右边界，因此最后有效回文是闭区间 [l+1, r-1]，此时的长度
         *   是“(r-1)-(l+1)+1”即r-l-1
         */
        return r-l-1; /***这里是如何理解的？？*/
    }


    //92
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head),slow = dummy,fast = dummy;
        for (int i = 0; i < left - 1; i++) {
            slow = slow.next;
        }
        for (int i = 0; i < right; i++) {
            fast = fast.next;
        }
        ListNode start = slow.next;
        ListNode nextStar = fast.next;
        fast.next = null;
        slow.next = rever(start);
        start.next = nextStar;
        return dummy.next;
    }

    private ListNode rever(ListNode head) {
        ListNode pre=  null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    //1143
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(),n = text2.length();
        if (n>m) return longestCommonSubsequence(text2,text1);

        int[] dp = new int[n + 1];
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n; j++) {
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[j] = dp[j-1]+1;
                }else {
                    dp[j] = Math.max(dp[j],dp[j-1]);
                }
            }
        }
        return dp[n];
    }

    //151



    //78



    //322
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        /**
         *      这里也可以用-1，填充数组。。此时代码需要改动，必须保证dp[j-coins[i]]不是-1才能取最小，否则取最
         * 小时-1会干扰。。。换言之我们确保金额“j-coins[i]”是能凑出来的，才会根据它决策
         *      否则“j-coins[i]”都凑不出来，“dp[j] = Math.min(dp[j-coins[i]]+1,dp[j]);”就没有意义，dp[i]
         * 的值只能保持原值不动
         */
        Arrays.fill(dp,amount+2);
        dp[0] = 0; /**err：0位置必须初始化0，否则永远返回-1*/
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < amount + 1; j++) {
                dp[j] = Math.min(dp[j-coins[i]]+1,dp[j]);
            }
        }
        return dp[amount]==amount+2?-1:dp[amount];
    }


    //8
    public int myAtoi(String s) {
        int flag = 1;
        String trim = s.trim();
        int cur = 0;
        if (trim.charAt(cur)=='-'){
            flag = -1;
            cur++;
        }else if (trim.charAt(cur)=='+'){
            cur++;
        }

        int res = 0;
        for (int i = cur; i < trim.length(); i++) {
            char c = trim.charAt(i);
            res = res*10 + c-'0';
            if (res>Integer.MAX_VALUE/10)
                return flag==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
        }
        return res;
    }


    //39


    //470


    //112



    //113



    //718



    //14
    /*写法1*/
    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0) return "";
        /*step1：使用第一个字符串作为基准*/
        String str = strs[0];
        /*step2：依次拿出str每一个位置的字符，遍历所有的字符串看看对应的位置字符是不是相等；如果不相等立即返回*/
        for (int i = 0; i < str.length(); i++) { /*依次拿出第一个字符串0、1、2....位置的字符*/
            char c = str.charAt(i);
            for (int j = 1; j < strs.length; j++) { /*从第二个字符串开始，依次研究每一个字符串位置i的字符，看看是不是等于c。如果发现不相等，立即返回*/
                if (i>=strs[j].length()||c!=strs[j].charAt(i)){
                    return str.substring(0,i);
                }
            }
        }
        /**
         *到了这里有两种情况————
         *      情况1：压根就没进入双重for循环，表示strs只有一个字符串，因此返回strs[0]；
         *      情况2：进入到双重for循环了，但是for循环完整执行结束。。。表示所有的字符串都研究了，strs[0]中有的
         *          字符其他的字符串对应都有，因此返回strs[0]。
         *      综上，虽然是两种情况，但是返回值是统一的。
         */
        return str;
    }

    /*写法2*/
    public String longestCommonPrefix_(String[] strs) {
        String flag = strs[0];
        for (int i = 0; i < flag.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                String cur = strs[j];
                if (cur.length()==i || cur.charAt(i)!=flag.charAt(i))
                    return flag.substring(0,i);
            }
        }
        return flag;
    }
}
