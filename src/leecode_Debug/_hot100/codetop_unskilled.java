package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.lang.management.MonitorInfo;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *记录topcode中不熟练的
 */
public class codetop_unskilled {

    /*
     * 438.给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * */
//    public List<Integer> findAnagrams(String s, String p) {
//
//    }

    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
//    public int coinChange(int[] coins, int amount) {}


    //最长回文子串
//    public String longestPalindrome(String s) {
//
//    }


    /**
     * 复原IP地址，为什么不对？？
     */
    List<String> res;

    public List<String> restoreIpAddresses(String s) {
        res = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        restoreIpAddresses(s, sb, 0, 0);
        return res;
    }

    private void restoreIpAddresses(String s, StringBuilder sb, int index, int num) {
        if (index >= s.length()) {
            return;
        }
        if (num == 3 && isValid(s.substring(index))) {
            res.add(new String(sb));
            return;
        }
        if (num == 3) return;
        for (int i = index + 1; i < s.length(); i++) {
            if (isValid(s.substring(index, i))) {
                sb.insert(i + num, '.');
                restoreIpAddresses(s, sb, i + 1, num + 1);
                sb.deleteCharAt(i + num);
            }
        }
    }

    private boolean isValid(String substring) {
        if (substring.length() > 3) return false;
        if (substring.length() > 1 && substring.startsWith("0")) return false;
        if (Integer.valueOf(substring) <= 255) return true;
        return false;
    }


    public String reverseWords(String s) {
        String str = s.trim();
        StringBuilder sb = new StringBuilder(str);
        int l = 0, r = s.length() - 1;
        while (l < r) {
            char c = sb.charAt(l);
            char c1 = sb.charAt(r);
            sb.deleteCharAt(l);
            sb.insert(l, c);
            sb.deleteCharAt(r);
            sb.insert(r, c1);
            l++;
            r--;
        }
        return sb.toString();
    }

/**
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 * ==================================1~5 page==================================
 */
    /*5
    给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
//    public String longestPalindrome(String s) {
//
//    }



    /*215
    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
//    public int findKthLargest(int[] nums, int k) {
//
//    }



    /*53
    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组是数组中的一个连续部分。
     */
//    public int maxSubArray(int[] nums) {
//
//    }


    /*92
    给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     */
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//
//    }



    /*93
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     */
//    public List<String> restoreIpAddresses(String s) {
//
//    }



    /*151
    给你一个字符串 s ，请你反转字符串中 单词 的顺序。

单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。

注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     */
//    public String reverseWords(String s) {
//
//    }



    /*78
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
//    public List<List<Integer>> subsets(int[] nums) {
//
//    }

    /*470
    给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。

你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。

每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
    * */
    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     * @return a random integer in the range 1 to 7
     */
//    public int rand10() {
//
//    }


    //39
    /*
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//
//    }


    /*112
    给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。

叶子节点 是指没有子节点的节点。
     */
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//
//    }

    /*113
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。
     */
//    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//
//    }


    /*179
    给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     */
//    public String largestNumber(int[] nums) {
//
//    }

    /*718
    给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     */
//    public int findLength(int[] nums1, int[] nums2) {
//
//    }


    /*14
    编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。
     */


    /**
     * ==========================================================================================================
     * ==========================================================================================================
     * ==========================================================================================================
     * ==========================================================================================================
     */


    //5
    /**
     * 马拉车算法 可以实现将时间复杂度降为O(N)，但是空间复杂度高于“中心扩散法”，空间复杂度为O(N)。
     【注意】原串中s的任何一个回文串，不论是奇数长度还是偶数长度，预处理后（比如下面的是通过加”#“预处理）的串中，任何一个回文串
        的长度都是奇数，此时任何一个字母以及#都是回文中心。。。而如果是偶数长度，回文中心可能会出现在最中间的间隔位置，比如”abba“的
        回文中心严格来说在两个b的中间
     【容易忘记的东西】
            1. 如何利用dp[i]以及i计算出对应的”回文串起始位置start“以及”回文串长度maxLen“...
     【一次难忘的bug排查记录】
            step2中创建StringBuilder的时候使用“StringBuilder sb = new StringBuilder('#');”，导致初始用例错误
        错误原因：构造器的参数传的是字符'#'，必须要使用字符串"#"。如果是字符'#'（ASCII码是35），表示创建容量35
        的StringBuilder
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
            /**err：注意这个if条件不能缺。否则报错————
             java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 11
             at line 16, Solution.longestPalindrome
             at line 56, __DriverSolution__.__helper__
             at line 86, __Driver__.main
             */
            if (i < right) { /**说明：理论上这里不带等于，但是写成“if (i <= right)”结果也不错*/
                p[i] = Math.min(right - i, p[mirror]); /**得到i位置回文半径的最小值，i位置的回文串还可能往两边扩————3.2干的活*/
            }

            /*3.2   尝试向两边继续扩展，看看位置i是否能得到更长的回文子串。
                    这一步具体的做法呢，如下————
                        ①声明两个指针l,r分别为i位置的左右；
                        ②只要l和r不越界 并且 l和r位置的字符相等，就“增加p[i]”、移动l和r指针*/
            int l = i - p[i] - 1, r = i + p[i] + 1;
            while (l >= 0 && r < n && str.charAt(l) == str.charAt(r)) {
                p[i]++;
                l--; /**err：此时是向两边扩散，因此left--，right++*/
                r++;
            }
            /*3.3 更新“最远回文右边界”（如果需要更新的话）。
             *   “最远回文右边界”right 和 “当前的回文中心”center 是成对起作用的，因此更新right的时候就要更新center。
             *   为什么说是“成对起作用”的呢？？因为right和i比较能加速p[i]计算；center用于计算位置i关于回文中心的对称位置*/
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
            /*3.4 更新最长回文子串（）。
             *   “最长回文子串”maxLen 和 “回文子串的开始位置”start也是成对出现的，因此更新maxLen的时候呀需要更新start。*/
            if (p[i] > maxLen) {
                /**❗❗❗❗❗❗❗❗❗
                    因为在预处理串中，每个实际字母都被 # 分开；
                     所以 P[i] 计数单位“刚好”是“字母 + 分隔符”的半径；
                    回文半径 P[i]（不包括中心）所覆盖的有效字符数就是原串回文长度。
                 */
                maxLen = p[i]; /**回文半径就是最长的长度*/
                /**❗❗❗❗❗❗❗
                    映射的原理：i是当前的位置————回文中心，maxLen是上一步更新后的p[i]，因此”i-maxLen“代表处理串str中i位置的最长
                 回文串的左边界，包含这个字符。
                    这个字符对应原字符串的哪个位置呢？？除以2即可。因此需要除以2得到映射到原字符串的下标，每两个位置对应原字符串的一
                 个位置！
                 */
                start = (i - maxLen) / 2; /**？？？*/
            }
        }
        return s.substring(start, start + maxLen);
    }



    //92
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//
//    }

    /**
     * ==============================1~5 page====================================================================================
     * ==============================1~5 page====================================================================================
     * ==============================1~5 page====================================================================================
     * ==============================1~5 page====================================================================================
     * ==================================================================================================================
     */
    //215
    /*
    方法1：调用Arrays.sort()进行完整的排序。时间复杂度——O(n log n)，空间复杂度O(1).
    方法2：借助优先级队列。只要优先级队列的数字超过k，就弹出。
            时间复杂度——O(n logK),空间复杂度——O(k)
            适合处理数据流或 n 很大但 k 较小的情况。
    方法3：快排思想的排序。
     */
    //215
    /*解法1：借助优先级队列。保证优先级队列中只有k个元素，最后弹出即可*/
    public int findKthLargest(int[] nums, int k) {
        /**
         *优先级队列，默认就是升序排序的
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }

    /*解法2：快排的思路。下面的写法会超时，为什么？？？
    *   快排是每一轮会把一个数放在正确的位置，如果某一轮结束某个数被放在n-k的位置，说明这个数就是答案。
    * 为什么最优？这个题求解的是第K个最大数，其他的数并不要求有序！
    *   此时的复杂度分析：
    *       时间复杂度：O(N)
    *       空间复杂度：O(1)（原地操作，递归栈深度 O(log n)）。
    * */
    public int findKthLargest_quickSort(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums, 0, n - 1, n - k);
    }

    private int quickSort(int[] nums, int l, int r, int index) {
        if (l == r) return nums[l];
        int pivotIndex = l + new Random().nextInt(r - l + 1);
        swap(nums, pivotIndex, r);

        pivotIndex = partion(nums, l, r);
        if (pivotIndex == index) {
            return nums[pivotIndex];
        } else if (pivotIndex < index) {
            return quickSort(nums, pivotIndex + 1, r, index);
        } else {
            return quickSort(nums, l, pivotIndex - 1, index);
        }
    }

    private int partion(int[] nums, int l, int r) {
        for (int i = l; i < r; i++) {
            if (nums[i] < nums[r]) {
                swap(nums, l++, i);
            }
        }
        swap(nums, l, r);
        return l;
    }

    /**
     * chatgpt给出的partion，与上面的写法相比：多了一个变量的声明
     */
//    private int partition(int[] nums, int left, int right, int pivotIndex) {
//        int pivot = nums[pivotIndex];
//        swap(nums, pivotIndex, right); // 把 pivot 放到末尾
//        int storeIndex = left;
//
//        for (int i = left; i < right; i++) {
//            if (nums[i] < pivot) {
//                swap(nums, storeIndex, i);
//                storeIndex++;
//            }
//        }
//
//        swap(nums, storeIndex, right); // 把 pivot 放到正确位置
//        return storeIndex;
//    }
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    /*
    *K神快排的写法，这种写法就不会超时，为什么？？
    * */
    private int quickSelect(List<Integer> nums, int k) {
        // 随机选择基准数
        Random rand = new Random();
        int pivot = nums.get(rand.nextInt(nums.size()));
        // 将大于、小于、等于 pivot 的元素划分至 big, small, equal 中
        List<Integer> big = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        for (int num : nums) {
            if (num > pivot)
                big.add(num);
            else if (num < pivot)
                small.add(num);
            else
                equal.add(num);
        }
        // 第 k 大元素在 big 中，递归划分
        if (k <= big.size())
            return quickSelect(big, k);
        // 第 k 大元素在 small 中，递归划分
        if (nums.size() - small.size() < k)
            return quickSelect(small, k - nums.size() + small.size());
        // 第 k 大元素在 equal 中，直接返回 pivot
        return pivot;
    }

    public int findKthLargest_good(int[] nums, int k) {
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        return quickSelect(numList, k);
    }


    //53. 最大子数组和
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            preSum = Math.max(preSum,nums[i]); /**每一个数要麽跟前面的数形成子数组；要麽自己独立开始形成一个子数组*/
            res = Math.max(res,preSum);
        }
        return res;
    }


    //5
    public String longestPalindrome_review(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder("#");
        for (char c:s.toCharArray()){
            sb.append(c).append("#");
        }
        String str = sb.toString();
        int n = str.length();

        int[] p = new int[n]; //声明int数组用于存放每一个位置的回文半径
        int center = 0,right = 0; /*center表示当前的回文中心；right表示当前最远的回文半径。*/
        int start = 0,maxLen = 0; /*这是返回结果的关键信息。start表示“最长回文子串”的开始位置，maxLen表示“最长回文子串”的长度*/
        /*step3：for循环依次研究每一个位置*/
        for (int i = 0; i < n; i++) {
            /*3.1 这一步就是“马拉车算法”的核心精髓。。具体的做法如下————
                      （1）计算出i位置关于“目前回文中心”center的对称位置。
                      （2）如果现在研究的位置i不超过“最远回文右边界”right，则可以快速计算出p[i]————这一步会充分用到之前已经计算的信息*/
            int mirror = 2*center-i;
            if (i<right){
                p[i] = Math.min(right-i,p[mirror]);
            }

            /*3.2   尝试向两边继续扩展，看看位置i是否能得到更长的回文子串。
                    这一步具体的做法呢，如下————
                        ①声明两个指针l,r分别为i位置的左右；
                        ②只要l和r不越界 并且 l和r位置的字符相等，就“增加p[i]”、移动l和r指针*/
            int l = i-p[i]-1,r = i+p[i]+1;
            while (l>=0&&r<n&&str.charAt(l)==str.charAt(r)){
                p[i]++;
                l--;
                r++;
            }
            /*3.3 更新“最远回文右边界”（同时更新对应的回文中心）。
             *   “最远回文右边界”right 和 “当前的回文中心”center 是成对起作用的，因此更新right的时候就要更新center。
             *   为什么说是“成对起作用”的呢？？因为right和i比较能加速p[i]计算；center用于计算位置i关于回文中心的对称位置*/
            if (i+p[i]>right){
                center = i;
                right = i+p[i];
            }
            /*3.4 更新最长回文子串。
             *   “最长回文子串”maxLen 和 “回文子串的开始位置”start也是成对出现的，因此更新maxLen的时候呀需要更新start。*/
            if (p[i]>maxLen){
                /**
                 * err：更新的顺序不能错了，必须先更新maxLen
                 */
                maxLen = p[i]; /**err：回文半径就是最长的长度*/
                start = (i-maxLen)/2; /**？？？*/
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
                start = i - (curLen - 1) / 2; /**err：这里是如何理解的？？*/
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
        return r-l-1; /***err：这里是如何理解的？？*/
    }


    //92

    /**
     *【思路】
     *      1、使用虚拟头节点dummy；
     *      2、for循环移动指针。目标：让pre指向left的前一个节点，让end指向right节点。
     *      3、记录pre.next(此节点是需要翻转部分的第一个节点)，记录end.next(此节点即是反转部分
     *          过后的第一个节点)；然后将end.next=null。
     *          [补充说明]K个一组反转链表、这个题都会涉及到将某节点的next指针置为null，原因：翻
     *          转链表的代码中，"while (cur!=null)"因此停止翻转的标志是cur来到null，如果不设置，
     *          就会导致每一次都反转到链表的末尾才结束，这明显是错的。因为我们只需要翻转中间的某
     *          一部分链表。
     *      4、翻转从pre.next开始的链表，并进行节点的拼接。
     *      5、返回dummy.next
     */
    public ListNode reverseBetween_(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head),pre = dummy,end = dummy;
        for (int i = 0; i < left - 1; i++) { /**注意i的范围：让pre来到left的前一个节点，因此i只能走left-1步*/
            pre = pre.next;
        }
        for (int i = 0; i < right; i++) { /**注意i的范围：让end来到right节点，因此i需要走right步*/
            end = end.next;
        }
        /**这里的操作类似于“K个一组翻转链表”的操作。
         *      1. 首先两个指针，分别来到“要反转部分的前一个节点pre（相当于 k个一组中前一组的最后一个节点）” 以及 “要反转部分的最后一个
         *  节点end（相当于 k个一组中当前组的最后一个节点）”
         *      2. 记录一下要反转部分的第一个节点start即pre.next；要反转部分之后的第一个节点next即end.next；
         *      3. 将end.next置为null，之所以这麽设置是因为：翻转链表的时候“cur!=null”是循环结束的标志。
         *      4. 翻转需要翻转的部分，并进行结果的拼接*/
        ListNode start = pre.next;
        ListNode next = end.next;
        end.next = null;

        pre.next = rever(start);
        start.next = next;
        return dummy.next;
    }

    //翻转链表的完整代码
    private ListNode rever(ListNode head) {
        ListNode pre = null,cur = head;
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
        int len1 = text1.length(),len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        int res = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                res = Math.max(dp[i][j],res);
            }
        }
        return res;
    }


    //93
    List<String> resRestoreIpAddresses;
    public List<String> restoreIpAddresses_(String s) {
        resRestoreIpAddresses = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        dfs(s, sb, 0, 0);
        return resRestoreIpAddresses;
    }

    private void dfs(String s, StringBuilder sb, int index, int num) {
        if (num == 3 && isValid1(s.substring(index))) {
            resRestoreIpAddresses.add(new String(sb));
        }
        for (int i = index + 1; i < s.length(); i++) {
            if (isValid1(s.substring(index, i))) {
                sb.insert(i + num, '.'); /**这里到底应该在什么位置插入字符？以及形参的index代表的是s的索引还是sb的索引？这两者是要协调的搭配的，对应关系是怎样的？？*/
                dfs(s, sb, i, num + 1);
                sb.deleteCharAt(i + num);
            }
        }
    }

    private boolean isValid1(String substring) {
        if (substring.length() == 1) return true;
        if (substring.length() == 2 && substring.charAt(0) != '0') return true;
        if (substring.length() == 3 && substring.charAt(0) != '0' && Integer.valueOf(substring) <= 255) return true;
        return false;
    }


    //78
    List<List<Integer>> resSubsets;
    public List<List<Integer>> subsets(int[] nums) {
        resSubsets = new LinkedList<>();
        dfs(nums, new LinkedList<Integer>(), 0);
        return resSubsets;
    }

    private void dfs(int[] nums, LinkedList<Integer> path, int index) {
        resSubsets.add(new LinkedList<>(path));
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums,path,i+1);
            path.removeLast();
        }
    }

    //8 字符串转换为整形



    //470，实现rand10()

    /**
     * 下面的写法暗示错误的，不能保证等概率的生成1~49！！！！具体原因如下：
     *      rand7() 是 [1,7] 的均匀分布。但两个独立的均匀随机数相乘，结果分布就 不均匀 了。。举个例子：
     2 可以来自 (2,1) 或 (1,2) → 概率 = 2/49。
     7 可以来自 (7,1)、(1,7) → 概率 = 2/49。
     但是 12 可以来自 (3,4)、(4,3)、(6,2)、(2,6) → 概率 = 4/49。
     明显不同结果出现的概率差别很大~~~
     */
//    public int rand10() {
//        while (true){
//            int tmp = rand7()*rand7();
//            if (tmp<=40){
//                return 1+ tmp%10;
//            }
//        }
//    }


    /**
     *下面的做法为什么是正确的？？
     *      1. “(rand7()-1)*7+rand7()”可以等概率的选择出1~49的每一个数，为什么？？
     *          第一个rand7()理解为第几行，第二个rand7()理解为第几列。这个表达的结果就是在7*7的棋盘上等概率的选出
     *          某一个位置。因此是等概率的
     *      2. “if (tmp<=40)”，只认为不超过40的数是有效的。这样的数一共有1~40这些可能，并且每一个数都是等概率的，
     *        我们保证1~10、11~20、21~30、31~40分别映射到1~10就可以了。.同时由于tmp%10的范围是[0,9]，因此为
     *        了得到[1,10]返回的应该是“tmp%10+1”。
     *          对于tmp>40的情况，直接重新生成
     */
//    public int rand10() {
//        while (true){
//            int tmp = (rand7()-1)*7+rand7();
//            if (tmp<=40){
//                return 1+tmp%10;
//            }
//        }
//    }

    /**基于上面的写法延申，可知下面的写法也是正确*/
//    public int rand10() {
//        while (true){
//            /**这个就是常规看到的二维数组的标号————行从0开始，列也从0开始。
//                此时所有的位置编号“0，1，2.....6”（第一行），“7，8，9....13”（第二行）....“42，43，44...48”（最后一行）
//             根据第一个“rand()7-1”可以得到行号，根据第二个“rand7()-1”可以得到列号！因此任何一个结果对应了二维数组中一个确定
//             的位置，所以每一个位置的概率是相等的，此时取“0~39”一共40个值，等概率的生成0~9，如果再+1就是结果了！！
//             */
//            int val = (rand7()-1)*7+(rand7()-1); /*区别1：这里后面加的是“rand7()-1”*/
//            if (val<40){ /*区别2：val<40的时候生成结果。此时“0~39”共40个可能的数，带等号就错了！！*/
//                return 1+val%10;
//            }
//        }
//    }


    //122
    /**
     * 【买卖股票问题总结】
     * Q121：要求只能买卖一次。
     * 贪心做法：
     * ①每到一个位置i，更新现在及之前遇到的最小值。
     * ②计算位置i能得到的利润（因为先做了①，因此这的结果必然不小于0）
     * ③使用②的结果更新最大利润————profit=max(profit,prices[i]−minPrice)
     * Q122：可以买卖多次但是任何一个时间只能持有一股
     * 贪心做法（假设只要i+1天的价格高于i天，第i天就买）：
     * ①从index=1的位置开始遍历，只要当前位置i的价格大于第i-1天的价格————说明如果第i天买，
     * 此时就能盈利，因此把“price[i]-price[i-1]”累加到res，即res += (price[i]-
     * price[i-1])；
     * ②从index=1开始遍历，结束遍历的时候res就是能得到的最大利润。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += (prices[i] - prices[i - 1]);
            }
        }
        return res;
    }

    /*更简化的写法*/
    public int maxProfit1(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(0,prices[i]-prices[i-1]);
        }
        return res;
    }


    //179
    /**
     * 【复杂度分析】假设n个数组元素，每一个数组元素最大有k位数。
     *      时间复杂度：涉及到了字符串中字符的排序，因此复杂度O(n*logn*k);
     *      空间复杂度：存储字符串数组 以及 拼接答案。O(N*K)
     * 【比较器的排序规则，容易搞混】
     *      比较器的返回值含义（Java 约定），根据接口方法的返回值决定形参a和b的先后顺序
     *  compare(a, b) < 0 → 形参a 排在 形参b 前面（a < b）。
     *  compare(a, b) > 0 → 形参a 排在 形参b 后面（a > b）。
     *  compare(a, b) == 0 → 视为相等（顺序不变或由稳定性决定）。
     * 【如何更好的理解compare方法返回值大于0的时候“第一个形参排在后面”，因为数组默认是升序的，返回值大于0表示
     * 第一个参数大，因此第一个参数放在后面】
     */
    /*
    这里排序常见的错误问题————
      错误1：
            下面的写法是错误的，Arrays指定排序规则要求数组必须是引用类型，基本数据类型是不行的
            Required type:T[]
            Provided:int[]
        Arrays.sort(nums,(a,b)->{
        String str1 = String.valueOf(a);
        String str2 = String.valueOf(b);
        return (str1+str2).compareTo(str2+str1);
        });
     修改方法：
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(arr,(a,b)->{
            String str1 = String.valueOf(a);
            String str2 = String.valueOf(b);
            return (str1+str2).compareTo(str2+str1);
        });
     */
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strings, (a, b) -> (b + a).compareTo(a + b)); /**err：如何比较两个字符串的大小*/
        /**验证一下没有下面的这句话会怎样？？是不是可能出现“00000000000000”这样的情况*/
        if (strings[0].equals("0")) return "0"; /**【说明】如果最开始的就是0，说明所有数都是0，直接返回“0”*/
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**下面是另外的写法*/
    public String largestNumber1(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings,(a,b)->(b+a).compareTo(a+b));

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (res.length()==0&&"0".equals(strings[i])) continue;
            res.append(strings[i]);
        }
        return res.length()==0?"0":res.toString();
    }


    //14
//    public String longestCommonPrefix_(String[] strs) {
//
//    }


    /**
     * ===========================================6~10==================================================================
     * ===========================================6~10==================================================================
     * ===========================================6~10==================================================================
     * ===========================================6~10==================================================================
     * ===========================================6~10==================================================================
     */
    //468
//    public String validIPAddress(String queryIP) {
//
//    }


    //297


    //207

    /**
     【实质】是判断一个有向图是不是无环
     【方法1】BFS（拓扑排序）
        1. 建立邻接表，统计每个节点的 入度。
        2. 把入度为 0 的节点加入队列。
        3. 每次弹出队列的节点，把它指向的邻居入度减 1，如果入度为 0，再入队。
        4. 最后如果能弹出所有节点（拓扑序列长度 == 课程数），说明无环；否则有环。有节点（拓扑
      序列长度 == 课程数），说明无环；否则有环。
     【注意】
           我们的想法是每一次拿到某个入度维0的节点，然后将所有“前置课程”是该节点的课程即为m入
        度-1，如果-1后发现课程m的入度也是0了（即课程m的全部前置课程已经完成），则将课程m也添加
        到入度为0的课程队列
     */
    //BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*step1：声明必须的信息，并初始化
            graph————存放一个个列表，索引i位置的列表存储课程i所有的后置课程，即如果课程i
        完成，接下来可学习的课程列表。可以描述为“前置课程i====>课程i所有后置课程的列表”
            indegree————入度表，索引i位置的值表示课程i的入度，即课程i的前置课程还有多少
         */
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<>());
        }
        int[] indegree = new int[numCourses];
        /*step2：使用prerequisites构建图和入度表。
            【详细说明】
                1. 构建图：List<List<Integer>>，根据p[1]拿到课程，p[0]则为它的后置课程，因此
             是”p[1]===>p[0]“这样的指向关系，所以拿到p[1]位置的List，将p[0]放进去，表示着图中
             有一条”p[1]节点指向p[0]节点的边“————graph.get(p[1]).add(p[0])。
                2. 入度表：因为”有一条p[1]指向p[0]的边“，因此对于节点p[0]来说，它的入度多了1，因
             此————indegree[p[0]]++。
            【注意】graph中位置index存储的是“课程index====>课程index所有的后继课程”，但是根据
         题目中描述的信息可知prerequisites数组存储的是“cur = [课程m，课程m的前置课程]”，因此这
         里添加的时候应该是倒序的关系，反过来添加即“拿到cur[1]对应的list然后把cur[0]加进去”
        */
        for (int[] p : prerequisites) { /**err：最绕的是这个for循环的内容*/
            int index = p[0], ele = p[1];
            graph.get(ele).add(index); /**这里容易写错，需要捋清楚添加的顺序~~~*/
            indegree[index]++;
        }

        /*step3：将入度为0的节点入队列*/
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        /*step4：依次拿出入度为0的节点进行处理，具体的步骤如下————
         *       ①从队列中弹出一个元素。（该课程对应的入度为0，即可直接学习）
         *       ②更新count。（可学习的课程数+1）
         *       ③从graph拿出“curVal是前置课程的课程列表”，将它对应的入度-1。如果-1后入度为0，则加进队列*/
        int count = 0;
        while (!queue.isEmpty()) {
            Integer curVal = queue.poll();
            count++;
            for (int index : graph.get(curVal)) {
                indegree[index]--; /**将对应后置课程的入度减一*/
                if (indegree[index] == 0) queue.offer(index);
            }
        }
        return count == numCourses;
    }


    //DFS


    //47
    /**
     * 在_06huisu文件中补充这个题目的多种实现
     */
    /*写法1：使用used数组进行去重*/
    List<List<Integer>> resPermuteUnique;
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        resPermuteUnique = new LinkedList<>();
        used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        dfs(nums, path);
        return resPermuteUnique;
    }

    private void dfs(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            resPermuteUnique.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                    continue; /**说明：有重复数字全排列问题的去重关键步骤。相当于在同一树层去重*/
                used[i] = true;
                path.add(nums[i]);
                dfs(nums, path);
                used[i] = false;
                path.removeLast();
            }
        }
    }


    /*写法2：使用set+swap方法来实现*/
    public List<List<Integer>> permuteUnique_set(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 排序，方便剪枝
        backtrack(nums, 0, res);
        return res;
    }

    private void backtrack(int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int num : nums) perm.add(num);
            res.add(perm);
            return;
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            // 剪枝：避免同一层重复选择相同的数
            if (seen.contains(nums[i])) continue;
            seen.add(nums[i]);

            swap1(nums, start, i);
            backtrack(nums, start + 1, res);
            swap1(nums, start, i); // 回溯
        }
    }

    private void swap1(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    //数组中的逆序对
    /**
     * 逆序对的关键：满足 i < j 且 nums[i] > nums[j] 的数对。在归并时，左半部分 [left..mid] 和右半部
     * 分 [mid+1..right] 都已经是有序的。统计逆序对的关键就在 合并过程中。
     */
    /*补充几个问题：
        1. 形参不传temp这个数组行不行
            不传 temp 也能跑，结果是对的。但性能会差很多，因为每次递归合并都要重新分配一个临时数组。
        如果题目有大输入规模（剑指 Offer 51 这类），最好传一个全局的 temp 数组进去，只分配一次，性
        能更好。
            因此可以把归并排序的代码也统一一下，初始时形参直接传入tmp数组
     */
    /*写法1：官方的写法*/
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] temp = new int[nums.length];
        return mergeSortAndCount(nums, 0, nums.length - 1, temp);
    }

    private int mergeSortAndCount(int[] nums, int left, int right, int[] temp) {
        if (left >= right) return 0; /**base case：只有一个数，说明逆序数就是0，直接返回*/
        int mid = left + (right - left) / 2;
        int count = 0;
        int leftCount = mergeSortAndCount(nums, left, mid, temp);
        int rightCount = mergeSortAndCount(nums, mid + 1, right, temp);
        int betweenCount = mergeAndCount(nums, left, mid, right, temp);

        count = leftCount + rightCount + betweenCount;
        return count;
    }

    /*下面的方法就等价于“归并排序中 合并左右两半数组”的方法*/
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        int p1 = left, p2 = mid + 1;
        int cur = left;
        int curCount = 0;
        while (p1 <= mid && p2 <= right) {
            if (nums[p1] < nums[p2]) {
                temp[cur++] = nums[p1++];
            } else {
                /**
                 *    只有右边的数比左边的数小的时候才会更新count!!举个例子：
                 *    假设当前左边的指针位置在p1，右边的指针位置在p2。并且nums[p1]>nums[p2]，因此前面的数
                 * 比后面的数大了，产生逆序对。产生多少呢？？
                 *    答：nums[p1]>nums[p2]，并且左边排好序了，因此左半的区间[left,mid]这个闭区间内p1位置之
                 * 后的数也都大于nums[p2]，那一共有多少个数呢？由于包含p1位置，包含mid位置，因此产生逆序对“mid-p1+1”
                 */
                temp[cur++] = nums[p2++];
                curCount += (mid - p1 + 1); /**这里是关键！！！理解这里是怎么计算的*/
            }
        }
        while (p1 <= mid) {
            temp[cur++] = nums[p1++];
            /**这里不需要更新curCount，为什么？？？
             当执行 while(p1<=mid) 时，说明 右半部分 [mid+1..right] 已经全部合并进去了。
             这意味着右边所有元素都比左边剩下的元素小或等于，因此逆序对该算的已经在之前算过了；
             左边剩下的元素只能直接放到 temp 里，不会再产生新的逆序对。所以这里 不能再加 curCount，
             否则就重复计数了。
             【因此】这个题目中我们的思想是“对于[mid+1,right]”中的每一个数，看前面有多少个数比它看，
             比它大的有多少个，就说明逆序对有多少个。。最后如果区间[mid+1,right]已经没有数了，说明它
             对应的逆序对已经算过了，因此仅仅需要在while循环更新逆序对总数
             * */
//            curCount += (right-mid);
        }
        while (p2 <= right) temp[cur++] = nums[p2++];

        for (int p = left; p <= right; p++) {
            nums[p] = temp[p];
        }

        return curCount;
    }

    /*错误的写法，后面给出纠正的方法*/
//    public int reversePairs(int[] record) {
//        int res = 0;
    /**在 Java 里，所有参数传递都是值传递。
        当你把一个基本类型（例如 int res）传进函数时，函数里拿到的是它的一个拷贝。因此在mergerSort方法中
     修改的res仅仅是自己拷贝的那一份，reversePairs方法中定义的res并不会发生改变！！
        修改的思路分为三种（这三种思路不仅仅体现在这里，在回溯的题目、二叉树递归的题目中也有体现）————
            第一种：定义全局int变量来更新；
            第二种：扩展的方法直接返回int类型；
            第三种：由于java是值传递，因此考虑传递引用类型（比如这个题的话可以使用int数组）
     */
//        mergerSort(record,0,record.length-1,res);
//        return res;
//    }
//
//    private void mergerSort(int[] record, int left, int right, int res) {
//        if (left>right) return;
//        int mid = left+(right-left)/2;
//        mergerSort(record,left,mid,res);
//        mergerSort(record,mid+1,right,res);
//        mergeTwo(record,left,mid,right,res);
//    }
//
//    private void mergeTwo(int[] record, int left, int mid, int right, int res) {
//        int[] tmp = new int[right - left + 1];
//        int index = 0;
//        int p1 = left,p2 = mid+1;
//        while (p1<=mid&&p2<=right){
//            if (record[p1]>record[p2]){
//                res += (mid-p1+1);
//                tmp[index++] = record[p2++];
//            }else {
//                tmp[index++] = record[p1++];
//            }
//        }
//        while (p1<=mid) tmp[index++] = record[p1++];
//        while (p2<=right) tmp[index++] = record[p2++];
//
//        int cur = 0;
//        while (cur<tmp.length){
//            record[cur+left] = tmp[cur++];
//        }
//    }

    /*修改写法2：*/
    public int reversePairs1(int[] record) {
        return mergeSort(record, 0, record.length - 1);
    }

    private int mergeSort(int[] record, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int res = mergeSort(record, left, mid)
                + mergeSort(record, mid + 1, right)
                + merge(record, left, mid, right);
        return res;
    }

    private int merge(int[] record, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int index = 0, count = 0;
        int p1 = left, p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (record[p1] > record[p2]) {
                count += (mid - p1 + 1);
                tmp[index++] = record[p2++];
            } else {
                tmp[index++] = record[p1++];
            }
        }
        while (p1 <= mid) tmp[index++] = record[p1++];
        while (p2 <= right) tmp[index++] = record[p2++];

        for (int i = 0; i < tmp.length; i++) record[left + i] = tmp[i];
        return count;
    }


    /*修改写法3：引用传递*/
    public int reversePairs2(int[] record) {
        int[] res = new int[1];
        mergerSort(record, 0, record.length - 1, res);
        return res[0];
    }

    private void mergerSort(int[] record, int left, int right, int[] res) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergerSort(record, left, mid, res);
        mergerSort(record, mid + 1, right, res);
        mergeTwo(record, left, mid, right, res);
    }

    private void mergeTwo(int[] record, int left, int mid, int right, int[] res) {
        int[] tmp = new int[right - left + 1];
        int index = 0;
        int p1 = left, p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (record[p1] > record[p2]) {
                res[0] += (mid - p1 + 1);
                tmp[index++] = record[p2++];
            } else {
                tmp[index++] = record[p1++];
            }
        }
        while (p1 <= mid) tmp[index++] = record[p1++];
        while (p2 <= right) tmp[index++] = record[p2++];

        for (int i = 0; i < tmp.length; i++) record[left + i] = tmp[i];
    }




    //958
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean hasNull = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                hasNull = true;
            } else {
                if (hasNull) return false;
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return true;
    }


    /*写法2：不使用if-else*/
    public boolean isCompleteTree1(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasNull = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                hasNull = true;
                continue;
            }
            if (hasNull) {
                return false;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }


    /*写法3：*/
    public boolean isCompleteTree2(TreeNode root) {
        if (root==null) return true;
        boolean hasNull = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            /**这种类型的题目并不需要关注某一层有多少节点，只要queue不是空就进行循环即可*/
            TreeNode cur = queue.poll();
            if (cur==null) hasNull = true;
            if (hasNull&&cur!=null) return false;
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }


    //572

    /**
     * 解法1：递归。
     * 复杂度：时间复杂度O(mn)，空间复杂度O(h)。。。。其中m，n分别是root、subRoot的节点数；h是递归的深度
     * 解法2：最优的解法应该是序列化出来(对于null节点也需要记录)，转换成求解子串存在与否的问题，使用KMP算法求解。
     * 复杂度：时间复杂度O(m+n)，空间复杂度O(m+n)————空间复杂度主要体现在存储序列化结果
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSameTree(root.left, subRoot) || isSameTree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.val != subRoot.val) return false;
        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }


    //59
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        while (num <= n * n) {
            for (int i = left; i <= right; i++) {
                res[top][i] = num++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                res[i][right] = num++;
            }
            right--;

            for (int i = right; i >= left; i++) {
                res[bottom][i] = num++;
            }
            bottom--;

            for (int i = bottom; i >= top; i++) {
                res[i][left] = num++;
            }
            left++;
        }
        return res;
    }


    //91
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        /*base case：需要初始化两个。其中dp[0]作为启动的参数、dp[1]是判断第一个字符是不是0(如果第一个字
            符是0，说白了这个字符串是不能被解码的。因为0不能解码并且它前面也没有字符不能组成“10”、“20”的样
            子)*/
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            char cur = s.charAt(i - 1);
            char curPrev = s.charAt(i - 2);
            /*
             根据1位数进行解码 和 2位数解码，更新dp[i].
                   方案1：和前面的数合起来进行解码。前提条件————是不是能和前面的数组成[10,26]中的一个数，不在这个范围就不行
                   方案2：当前字符(即index=i-1的字符)单独及逆行解码。前提条件————当前的这一位数位于区间(0,9]，如果不在这个
                        区间，则不行
             */
            int twoDigit = (curPrev - '0') * 10 + cur - '0';
            if (twoDigit >= 10 && twoDigit <= 26) dp[i] += dp[i - 2]; //方案1
            if (cur != '0') dp[i] += dp[i - 1]; //方案2
        }
        return dp[n];
    }


    //440、重点需要结合字典树来理解
    //可以参考博客：https://blog.csdn.net/qq_36389060/article/details/124107294
    /**不要使用下面的写法，不好理解。。。。其实代码翻译一下是一模一样的*/
    public int findKthNumber1(int n, int k) {
        int prefix = 1;
        k--;
        while (k > 0) {
            long steps = countSteps1(n, prefix, prefix + 1);

            if (steps <= k) {
                /**
                 *     steps<=k，说明————“以prefix为前缀的子树的数的个数小于k”，因此我们直接在
                 * 同一层跳到下一个节点，比如：
                 *     现在所在的节点是“10”，同一层的所有节点有“11、12、13、14、15.....19”，它
                 * 孩子节点呢？就是以“10”为前缀的节点，即“101、102、103、104、105.....109”。
                 */
                prefix++;
                k -= steps;
            } else {
                /**
                 *     else说明steps>k，即以prefix为子树的数的个数大于k，说明————想找的数就在
                 * 这个子树，因此继续向下(深潜)一层(向下深潜一层就是来到了prefix*10这个节点)，来
                 * 到孩子节点；同时k--因为跳过了父节点prefix。
                 */
                prefix *= 10;
                k--;
            }
        }
        return prefix;
    }

    private long countSteps1(int n, int prefix, int next) {
        long steps = 0;
        while (prefix <= n) {
            steps += Math.min(n + 1, next) - prefix; /*等价于“steps += Math.min(n -prefix+ 1, next-prefix);”*/
            prefix *= 10;
            next *= 10;
        }
        return steps;
    }

    /**建议使用下面的写法*/
    public int findKthNumber(int n, int k) {
        long cur = 1;
        k--;
        while (k>0){
            int nodes = getNodes(cur,n);
            if (nodes<=k){
                k -= nodes;
                cur++;
            }else {
                k --;
                cur *= 10;
            }
        }
        return (int) cur;
    }

    /*方法的作用：算出以 cur 为根的树 ≤n 的数字总数。（包含cur本身）*/
    private int getNodes(long cur, int n) {
        long next = cur+1;
        long totalNodes = 0;
        while (cur<=n){
            totalNodes += Math.min(n-cur+1,next-cur);
            cur *= 10;
            next *= 10;
        }
        return (int) totalNodes;
    }


    //329、矩阵中的最长递增路径
    /**
     * dp数组定义：dp[i][j] 表示从单元格 (i,j) 出发的最长递增路径的长度。
     * 转移方程：从 (i,j) 出发，尝试走到上下左右四个方向 (x,y)，前提是 matrix[x][y] > matrix[i][j]；
            dp[i][j] = 1 + max(dp[x][y])。
     * 记忆化优化：每个格子只算一次，后续直接复用结果，避免指数级复杂度。
     * 整体结果：遍历所有单元格计算对应的dp值，返回 max(dp[i][j])。
     */
    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    /**
     * 二维数组的初始化方式~~~，不用使用“new int[][]。这样初始化反而简单”
     */
    int m, n;

    /**
     * 主函数的思路：
     *      ①声明二维数组memo存储每一个点的最长递增子路径；
     *      ②双层for循环遍历matrix的每一个位置计算出该位置的最长递增路径（任何位置的结果都会记录在memo
     * 数组），用res记录遇到的最大值(memo中每一点的值怎么求解？使用dfs方法，dfs的作用就是求解(i，j)点的
     * 最长递增子路径)；
     *      ③返回res
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;

        int[][] memo = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(memo, i, j, matrix));
            }
        }
        return res;
    }

    /**
     * dfs函数的作用：求解从(i,j)开始的最长递增子路径
         问题1：为什么不会导致重复计数？
              重复计算（Re-computation）和重复计数（Double-counting）要分开理解：
              如果没有 memo，某个格子可能会被多次 DFS 到，造成重复计算。但是 memo[i][j] 保存
         了该格子的最长路径长度，下一次再访问时直接返回，不会再递归。因此 每个格子最多算一次，结果
         缓存下来，不会出现指数级的重复。不会重复计数：
              在 DFS 过程中，每个递归路径表示一条「严格递增路径」。我们在 max 里取的是 最长路径
         长度，而不是「路径数量」；所以不会把不同路径的节点数「叠加」起来，而是只选择最长的那条。
     * 【疑问：大白话解释根本原因】
     *      在研究某一个位置的最长递增路径的时候没有记录走过的路，比如“岛屿数量”题目中会用特殊字符如‘\n’
     *  记录走过的位置。根本原因在于：“递增”这个要求是不可逆的，比如“3，4”是递增的，但是“4，3”就不是递增
     *  路径了；跟“最大陆地面积”、“陆地的数量”是有区别的，“最大陆地面积”、“岛屿的数量”这种题目可能会重复
     *  计数，因此需要使用特殊字符“\n”(方案1) 或者 使用used数组(方案2) 记录哪些位置已经研究过了
     */
    private int dfs(int[][] memo, int i, int j, int[][] matrix) {
        if (memo[i][j] != 0) return memo[i][j]; /*如果不是0说明已经计算过了直接返回*/
        int max = 1; //初始化为1（初始化位0其实结果也是对的），递增子路径至少包含自己，因此长度至少为1。。。这里不用考虑matrix是空的情况
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[i][j] < matrix[x][y]) { /**err：比较之前先判断x和y不能越界*/
                /**
                 当前位置(i,j)的dp值取决于4个方向的最大值，记录为max，最后需要返回。
                 【疑问】为什么不是加？？而是取最大值？？
                    从当前格子出发的最长递增路径 = 所有可选邻居路径长度的最大值 + 1（当前格子）。所以我们要
                 做的是：取最大值，不是 累加。
                    也就是说，对于任何一个位置，memo对应的值代表以该位置为起点的最长递增路径。从该点出发，四
                 个方向只能选择一个去扩张。。比如下面的局面——————
                                            5
                                        6   3   7
                                            8
                    3位置的memo代表以“3”开始的最长递增路径，虽然现在3的4个方向都可以走，但是最终最长递增路径只
                 有一种选择，因此这里需要取最大值，而不是把4个方向的结果加和
                    其实：上面这个问题的根源也是深入理解“递增”来两个字的含义
                 */
//                memo[i][j] += dfs(memo,x,y,matrix);
                max = Math.max(max, 1 + dfs(memo, x, y, matrix));
            }
        }
        memo[i][j] = max;
        return max;
    }


    //213
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRange(0, n - 2, nums), robRange(1, n - 1, nums));
    }

    /**
     * 偷取指定闭区间[left,right]的房屋，能得到的最大金额
     */
    private int robRange(int left, int right, int[] nums) {
        int fir = nums[left], sec = Math.max(nums[left], nums[left + 1]);
        for (int i = left + 2; i <= right; i++) {
            int curVal = Math.max(fir + nums[i], sec);
            fir = sec;
            sec = curVal;
        }
        return sec;
    }

    /**
     * chatgpt给出的版本，这种版本将初始化和其他位置的计算统一起来，不用特殊考虑最开始时的 第一个位置 和 第二个位置
     */
    private int robRange(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    /* 384
        实现一个支持以下操作的类：

        Solution(int[] nums) —— 用整数数组初始化对象

        reset() —— 重置数组到最初状态并返回

        shuffle() —— 返回数组随机打乱后的结果
     */
    /**
         这是典型的 Fisher–Yates 洗牌算法（又叫 Knuth Shuffle）：如果直接 Collections.shuffle 会占用额外空间。
     Fisher–Yates 可以原地 O(n) 打乱并且保证所有排列等概率。
     【算法详述（打乱时）】
        方案1：
            ①从index=0开始。
            ②对于下标 i=0..n-1：从区间 [i,n-1] 中随机选一个 j，交换 arr[i] 和 arr[j]。————即从i及后面的数随机随机选择一个
        索引，交换 这个随机索引 和 i位置 对应的数。
        方案2：
            ①从index=n-1开始。
            ②对于任意的下标 i=n-1、n-2.....1、0，从区间 [0,i]中随机选择一个j，交换arr[i] 和 arr[j]。————即从i及前面的数随
        机选择一个索引，交换 这个随机索引 和 i位置 对应的数。

            综上，【打乱顺序的关键】
            如果是从前往后遍历数组，对于任意的位置i,需要从[i,n-1]随机选择一个索引比如记为k，则需要交换i位置 和  k位置的值。
            反之，类似
     */
    class Solution {
        int[] origin;
        int[] array;
        Random random;

        public Solution(int[] nums) {
            origin = nums.clone();
            array = nums.clone();
            random = new Random();
        }

        public int[] reset() {
            /*
                由于要保证origin数组保持不变，因此在reset的时候必须使用“ origin.clone()”给array拷贝一份。
            这个道理有点类似于“前缀树”题目中，每一次查找、插入等方法时“拷贝一份root”是类似的，因为前缀树的
            题目中必须使当前类知道root节点，这个代表根节点，根节点是不能变得，否则在查找插入等过程由于会不
            断的移动指针导致root的指向变化了
             */
            array = origin.clone(); /**err：这里必须是使用“clone()”方法给array*/
            return array;
        }

//        public int[] shuffle() {
//            for (int i = 0; i < array.length; i++) {
//                int j = random.nextInt(origin.length); /**err：这样的写法是错误的*/
//                swap2(array, i, j);
//            }
//            return array;
//        }

        //chatgpt给的其实是下面的样子
        /*
        shuffle() 要实现 等概率随机打乱，经典算法是 Fisher–Yates 洗牌算法。
            2. Fisher–Yates 洗牌
            从数组最后一个元素开始，依次与前面任意一个元素交换。每个元素被放到任意位置的概率相同，保证公平性。
                步骤（长度 = n）：
                   i = n-1 → 从 [0, i] 里随机选一个 j，交换 nums[i] 和 nums[j]
                   i = n-2 → 从 [0, i] 里随机选一个 j，交换
                   直到 i = 0 结束
         */
        public int[] shuffle() {
            for (int i = array.length - 1; i > 0; i--) { /**err：注意这种写法i必须是倒序~~*/
                int j = random.nextInt(i + 1); // [0, i] 之间随机
                swap(array, i, j);
            }
            return array;
        }

        //或者下面的写法也可以？？？
//        public int[] shuffle() {
//            for (int i = 0; i < array.length; i++) {
//                int j = i + rand.nextInt(array.length - i); // [i, n-1]
//                swap(array, i, j);
//            }
//            return array;
//        }

        private void swap2(int[] array, int i, int j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }


    //120
    /**
     * 可以将空间优化到O(N),时间复杂度为O(n^2)
     * 【关键】需要从最后一行倒着遍历。最后返回dp[0]。原因解释————
     * 第m行的i位置能移动到第m+1行的i位置和i+1位置；第m行的i+1位置能移动到第m+1行的i+1位置和i+2位置。
     * 比如“第m行的i位置能移动到第m+1行的i位置和i+1位置”，此时如果是从第一行往下面的行遍历，dp[i]和dp[i+1]
     * 就可能会被更新，更新后会发现dp[i+1]存的就是第m+1行的信息；但是继续计算时“第m行的i+1位置能移动到第m+1
     * 行的i+1位置和i+2位置”需要使用到第m行的dp[i+1]信息，但是在前一步的时候这个信息被覆盖了！！
     * 结论：空间优化为O(N)时，遍历顺序必须时从最后一行倒着遍历。（从最后一行遍历，但是同一行内从前往后遍
     * 历就可以）
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        int[] dp = new int[lastRow.size()];
        for (int i = 0; i < lastRow.size(); i++) {
            dp[i] = lastRow.get(i);
        }

        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> curRow = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - 1]) + curRow.get(j);
            }
        }
        return dp[0];
    }

    /*516
    给你一个字符串 s，返回其中 最长回文子序列 的长度。注意是 子序列（不要求连续），不同于“最长回文子串”。
     */
    /*枚举子串的长度 这种思路*/
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) { /**i表示枚举子串的长度，从2开始*/
            for (int i = 0; i + len - 1 < n; i++) { /**i表示子串的左端点；i+len-1表示子串的右端点*/
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = len == 2 ? 2 : dp[i + 1][j - 1] + 1; /**首尾字符相同，在中间子串的长度基础上+2*/
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    /*填表的写法————这种写法更好理解
        情况1：如果首尾的字符相同，则取决于中间子串的dp值，即dp[i+1][j-1]。
        情况2：如果首尾的字符不相同，则取决于舍弃首字符 或者 舍弃尾字符 的dp值，
            即Math.max(dp[i+1][j],dp[i][j-1])。
        综上，可以看出（i，j）位置的计算依赖于位置（i+1,j-1）、（i+1,j）、
            （i,j-1）。其中前面两个是第i+1行的dp值，第三个是第i行前一个位置的
            dp值，因此不同行需要倒着遍历，同一行内需要从前往后遍历
     */
    public int longestPalindromeSubseq_1(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n-1; i >=0; i--) {
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i)==s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }



    //400
    public int findNthDigit(int n) {
        int digit = 1;
        int start = 1;
        while (n > digit * start * 9) {
            n -= digit * start * 9;
            digit++;
            start *= 10;
        }

        /**
         * 下面在求解第几个数，以及这个数的第几位的时候，为什么必须使用“n-1”？？
                 题目说“第 n 位”，这是 人类的 1-based 计数。但在程序里，字符串和数组都是 0-based 索引。
                 👉 所以必须用 (n-1) 来转化。
                     例如：n=1，表示“第 1 个数字”。。。但在字符串 "123456..." 中，它对应的索引是 0
                 如果你直接用 n 当索引，就会错一位，这就是 off-by-one 错误。
            【感想】0、1、2、3.........把这里的0、1、2当作真实的数，就像数学中的1，2，3....
         */
//        int num = n / digit;
//        int index = n%digit;
        int num = (n-1)/digit;
        int index =(n-1)%digit;
        String res = String.valueOf(start + num);
        /**下面的写法不会报错，为什么？？不报错的话会输出什么？？*/
//        return Integer.valueOf(res.charAt(index));
        return res.charAt(index)-'0';
    }


    //611
    /**
     *【说明】什么时候移动left、right指针还是不太理解，为什么注释的移动方式就不对？？
     *【错误点】
     *      1. 找到满足的解 以及 未找到满足的解，指针应该怎么移动？？
     *
     *      针对于这个问题，chatgpt举出的例子如下————
                 nums = [2, 3, 4, 5]
                 i = 0（固定最小边 = 2）
                 正确逻辑：

                 left = 1(索引), right = 3 → 2+3=5 > 5 ❌ → j++

                 left = 2, right = 3 → 2+4=6 > 5 ✅ → res += (3-2)=1 → k--
                 ✅ 结果：找到 (2,4,5)。

                 你的逻辑：

                 left = 1, right = 3 → 2+3=5 > 5 ❌ → else → right-- → right=2

                 left = 1, right = 2 → 2+3=5 > 4 ✅ → res += (2-1)=1 → left++=2 → 循环结束
                 ❌ 漏掉了 (2,4,5)，而错误地把 (2,3,4) 算进去。
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1,right = nums.length-1;
            while (left<right){
                /*
                条件成立时：
                    要移动 right--，因为我们已经把 (right - left) 全部统计了，接下来需要更小的最大边。
                条件不成立时：
                    要移动 left++，因为此时最小边太小了，必须换大一点的 left 才可能满足条件。
                 */
                if (nums[i]+nums[left]>nums[right]){
                    res += (right-left);
//                    left++; //❌
                    right--; /**我们能保证后续的操作中，right右边的位置一定不可能是其中的某一条边*/
                }else {
                    left++;
//                    right--; //❌
                }
            }
        }
        return res;
    }

    //264
    /**
     * 下面的代码是错误的
     */
//    public int nthUglyNumber(int n) {
//        if (n==1) return 1;
//        int p1=1,p2=1,p3=1;
//        for (int i = 1; i <= n; i++) {
//            int val1 = p1*2;
//            int val2 = p2*3;
//            int val3 = p3*5;
//            int curVal = Math.min(val1,Math.min(val2,val3));
//            if (i==n) return curVal;
//            if (curVal==val1) p1++;
//            if (curVal==val2) p2++;
//            if (curVal==val3) p3++;
//        }
//        return -1;
//    }



    //253
    /**
     *【建议的写法】建议在“优先级队列”中添加int[]，跟“合并区间题目”类似。即建议使用写法minMeetingRooms_1
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            /**如果当前活动的开始时间要 晚于 堆顶的结束时间，则说明会议室可以复用————做操作：弹出栈顶*/
            if (intervals[i][0]>=queue.peek()){ /**err：不能漏掉等于*/
                queue.poll();
            }
            queue.offer(intervals[i][1]);
        }
        return queue.size();
    }

    /**给“优先级队列”中添加数组也是可以的，统一成“合并区间”的写法。区别————
     *      合并区间：如果cur[0]>queue.peek()[1]，说明cur区间的左边界已经在优先级队列顶部区
     *  间之外了，表明需要往队列中添加新的元素即cur；如果cur[0]≤queue.peek()[1]，则需要更新
     *  queue.peek()的右边界，即更新queue.peek()[1]的值。
     *      最小会议室数量：如果cur[0]≥queue.peek()[1]，说明当前会议cur的开始时间已经晚于堆
     *  顶会议的结束时间，因此当前的会议和堆顶的会议能共用会议室，因此弹出堆顶会议【思考：为什么
     *  这里弹出？？】；将当前会议添加进“优先级队列”*/
    public int minMeetingRooms_1(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);

        PriorityQueue<int[]> queue = new PriorityQueue<>();
        queue.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            /**如果当前活动的开始时间要 晚于 堆顶的结束时间，则说明会议室可以复用————做操作：弹出栈顶*/
            if (intervals[i][0]>=queue.peek()[1]){ /**err：不能漏掉等于*/
                queue.poll();
            }
            queue.offer(intervals[i]);
        }
        return queue.size();
    }

    //673
    public int findNumberOfLIS(int[] nums) {
        /*step1：声明两个数组
              dp数组用于存放“以nums[i]结尾的最长递增子序列的长度”；
              count数组用于存放“以nums[i]结尾的最长递增子序列的数量”。
              比如：以nums[8]结尾的最长递增子序列的长度是5，则dp[8]=5；同时以nums[8]结
           尾且长度是5的子序列一共有4种，则count[8]=4。
         */
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int[] count = new int[nums.length];
        Arrays.fill(count,1);
        /*step2：声明两个变量
             maxLen————最长递增子序列的长度
             num存放————长度为maxLen的递增子序列一共有多少种
         */
        int maxLen = 0,num = 0;
        /*step3：计算dp以及count数组
            对于任意的位置i，j从0位置开始，看看nums[i]放在nums[j]后面能不能形成一个更长
         的递增子序列
         */
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                /*双重循环的核心
                    【核心的话】因为j<i，所有可以得出：“如果nums[j]<nums[i]，则nums[i]可以放
                 在nums[j]的后面形成一个更长的递增子序列”
                    此时，可能会有两种情况————
                        情况1：如果dp[j]+1>dp[i]，说明nums[i]放在nums[j]后面以后形成的递增子
                    序列长度超过了目前已经发现的“以nums[i]结尾的最长递增子序列”。则说明对于nums[i]
                    而言，找到了一个更长的递增子序列，那此时最长递增子序列长度是多少呢？？这样长度的
                    递增子序列又有多少呢？？首先最长递增子序列的长度就是dp[j]+1————即把nums[i]放在
                    nums[j]后面即可。。这样递增子序列的长度是多少呢？应该就是count[j]，因为nums[i]
                    放在nums[j]后面形成递增子序列，但是以nums[j]结尾的子序列有count[j]种，因此以nums[i]
                    结尾的最长递增子序列也有count[j]种
                        情况2：如果dp[j]+1=dp[i]，则更新以nums[i]即为最长递增子序列的样本数
                 */
                if (nums[j]<nums[i]){
                    if (dp[j]+1>dp[i]){
                        dp[i] = dp[j]+1;
                        count[i] = count[j]; /**【注】这里不要初始化为1*/
                    }else if (dp[j]+1==dp[i]){
                        count[i] += count[i];
                    }
                }
            }
            /*step4：更新maxLen 以及 num.
                情况1：如果dp[i]是最大的，则更新maxLen = dp[i];并且num更新为count[i]
                情况2：如果正好dp[i]=maxLen，则将count[i]累加到num。说明最长递增子序列
             的数量增加了。
             */
            if (dp[i]>maxLen){
                maxLen = dp[i];
                num = count[i];
            } else if (dp[i] == maxLen) {
                num += count[i];
            }
        }
        return num;
    }
}
