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

    /**
     * ==============================1~5 page====================================================================================
     * ==============================1~5 page====================================================================================
     * ==============================1~5 page====================================================================================
     * ==============================1~5 page====================================================================================
     * ==================================================================================================================
     */
    /*215. 数组中的第K个最大元素
    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    /**
     TODO:理解chatgpt给出的三种解法
    方法1：调用Arrays.sort()进行完整的排序。时间复杂度——O(n log n)，空间复杂度O(1).
    方法2：借助优先级队列。只要优先级队列的数字超过k，就弹出。
            时间复杂度——O(n logK),空间复杂度——O(k)
            适合处理数据流或 n 很大但 k 较小的情况。
    方法3：快排思想的排序。
     TODO:借助这个题继续思考，“Hoare分区的快排版本”怎么写？
     */
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

    /*解法2：快排的思路。下面的写法会超时，思考为什么？？？
    *   快排是每一轮会把一个数放在正确的位置，如果某一轮结束某个数被放在n-k的位置，说明这个数就是答案。
    * 为什么最优？这个题求解的是第K个最大数，其他的数并不要求有序！排序的话会强制全数组变得有序，这种肯定
    * 多干了许多事。
    *   此时的复杂度分析：
    *       时间复杂度：O(N)
    *       空间复杂度：O(1)（原地操作，递归栈深度 O(log n)）。
    * 【注意】部分用例会超时，尤其是数组中重复数很多的时候！
    * */
    /**注意：随机快排的这种解法会导致个别用例超时（43/44用例超时，这个用例nums几乎都是重复的1）！！！*/
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
        int cur = l;
        for (int i = l; i < r; i++) {
            if (nums[i] < nums[r]) { //快排的版本这里带不带等于都可以
                swap(nums, cur++, i);
            }
        }
        swap(nums, cur, r);
        return cur;
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    /**
     chatgpt给出的第一个版本，下面是迭代版本的写法。还是会超时了~~
     */
    public int findKthLargest_chatgpt1(int[] nums, int k) {
        int left = 0, right = nums.length - 1, target = nums.length - k;
        Random rand = new Random();
        while (left <= right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int index = partition1(nums, left, right, pivotIndex);
            if (index == target) return nums[index];
            else if (index < target) left = index + 1;
            else right = index - 1;
        }
        return -1;
    }

    private int partition1(int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        swap_chatgpt(nums, pivotIndex, right);
        int cur = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) swap_chatgpt(nums, cur++, i);
        }
        swap_chatgpt(nums, cur, right);
        return cur;
    }

    private void swap_chatgpt(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    /**
     chatgpt给出的第二个版本的代码。————Hoare 分区法（推荐）
     为什么采用这种分区方法就不会超时，为什么？？
     */
    public int findKthLargest_chatgpt2(int[] nums, int k) {
        if (nums.length==1 || k==0) return nums[0];
        return findKthLargest(nums,0,nums.length-1,k);
    }

    public int findKthLargest(int[] nums, int left, int right, int k) {
        if (left==right) return nums[left];
        int pivotIndex = left + new Random().nextInt(0,right-left+1);
        swap_chatgpt2(nums,pivotIndex,right);

        pivotIndex = partitionHoare(nums, left, right);
        if (pivotIndex >= nums.length - k)
            return findKthLargest(nums, left, pivotIndex, k);
        else
            return findKthLargest(nums, pivotIndex + 1, right, k);
    }

    /*下面是Hoare分区的详细原理————
    do { i++; } while (nums[i] < pivot);
        从左向右找到第一个 不小于 pivot 的元素（即 nums[i] >= pivot），并停下，i 指向该元素。
    do { j--; } while (nums[j] > pivot);
        从右向左找到第一个 不大于 pivot 的元素（即 nums[j] <= pivot），并停下，j 指向该元素。
    如果 i >= j：说明左右指针已经相遇或交错，划分完成，返回 j（注意返回 j，不是 i）。
    否则 i < j，说明 nums[i] 在右半区、nums[j] 在左半区（相对 pivot），将它们交换 swap(nums, i, j)，然后继续循环。
    这种做法保证被交换的两个元素都放到了各自正确的半区。
    * */
    private int partitionHoare(int[] nums, int left, int right) {
        /**pivot取中间的数 或者 末尾的数都是可以的*/
//        int pivot = nums[left + (right - left) / 2];
        int pivot = nums[right];
        int i = left - 1, j = right + 1;
        while (true) {
            do { i++; } while (nums[i] < pivot);
            do { j--; } while (nums[j] > pivot);
            if (i >= j) return j;
            /*【注】这里交换之后i和j的值没有变，表面上看会死循环，其实不会。。。原因：
                由于整体是“while(true)”，因此还会进入循环；由于是“do-while”循环，因此会先改变i和j的值，因此不会造成死循环
             */
            swap_chatgpt2(nums, i, j);
        }
    }

    private void swap_chatgpt2(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    /**
     chatgpt给出的第三个版本代码————三向划分
     */
    public int findKthLargest_chatgpt3(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int target = nums.length - k; // 第 k 大的索引（升序排列后）

        Random random = new Random();

        while (left <= right) {
            // 1. 随机选择 pivot
            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivot = nums[pivotIndex];

            /* 2. 三向划分。划分的最终结果：
                ①左闭右开区间 [left,lt）这些元素都是小于pivot的元素
                ②闭区间 [lt,gt]这些元素都是等于pivot的元素。
                ③左开右闭区间（gt，right]都是大于pivot的元素
             */
            int[] range = partition3Way(nums, left, right, pivot);
            int lt = range[0], gt = range[1];

            // 3. 根据 pivot 区间调整搜索范围
            if (target < lt) {
                right = lt - 1;      // 目标在左半部分
            } else if (target > gt) {
                left = gt + 1;       // 目标在右半部分
            } else {
                return nums[lt];     // 目标在 pivot 区间内
            }
        }

        return -1; // 理论不会走到这
    }

    /**
      1. 方法的功能：三向划分（Dutch National Flag）,使得区间被分为：
                [left, lt-1] < pivot
                [lt, gt] == pivot
                [gt+1, right] > pivot
      2. 理解此方法，有以下几个关键点
          关键点1：这种方法中，任意时刻，数组会被划分为4部分（有些部分可能为空）————
                 nums[left .. lt-1]：所有元素 < pivot
                 nums[lt .. i-1]：所有元素 == pivot
                 nums[i .. gt]：未检查/未知区
                 nums[gt+1 .. right]：所有元素 > pivot
             这条不变式是算法正确性的关键。每一步的 swap/指针移动都维护这个不变式，并逐步缩小未知区 [i..gt]。
          关键点2：结束时 lt 和 gt 指向什么位置？有什么特征？
                 当循环结束（i > gt）时，未知区已经被处理完毕（为空）。因此：
             lt：等于段（== pivot）区间的起始索引。也就是说，lt 指向第一个等于 pivot 的元素（如果存在）。
             gt：等于段（== pivot）区间的结束索引。也就是说，gt 指向最后一个等于 pivot 的元素（如果存在）。
             换言之，等于 pivot 的元素全部位于 nums[lt .. gt]（闭区间）。
          关键点3：等于区可能为空（【说明】但是这个题等于的区域不可能是空，因为pivot就是从数组中选出来的）
                 如果没有任何元素等于 pivot，则等于区为空，表现为 lt > gt。例如：若所有元素 < pivot，循环结束
             时 lt == right+1，gt == right → lt > gt。若所有元素 > pivot，循环结束时 lt == left，gt == left-1 → lt > gt。
             因此必须在调用方处理 lt>gt 表示“无等于元素”的情况。
     */
    private int[] partition3Way(int[] nums, int left, int right, int pivot) {
        int lt = left;    // 小于 pivot 的区域边界
        int i = left;     // 当前扫描指针
        int gt = right;   // 大于 pivot 的区域边界

        while (i <= gt) {
            if (nums[i] < pivot) {
                swap_chatgpt3(nums, lt++, i++);
            } else if (nums[i] > pivot) {
                swap_chatgpt3(nums, i, gt--);
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }

    private void swap_chatgpt3(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    /**
        K神快排的写法，这种写法就不会超时，为什么？？
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


    /*5 最长回文子串
    给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
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
                      （2）如果现在研究的位置i不超过“最远回文右边界”right，则可以快速计算出p[i]————这一步会充分用到之前已经计算的信
                  息。。达成的效果就是”每次到达i位置时，（i,j]子串如果回文它的回文长度是多少？ 且 j∈(i,right]————这些位置的答案就不
                  用计算了，可以直接得到结果“*/
            int mirror = 2*center-i;
            if (i<right){ /**有一个疑问，这里带等于的时候对不对*/
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

    /**基于上面的写法延申，可知下面的写法也是正确。其中：
        写法“(rand7()-1)*7+rand7()”的范围是[1,49]，取[1,40]这40个数计算。
        写法“(rand7()-1)*7+(rand7()-1)”的范围是[0,48]，取[0,39]这40个数计算。
     这两种写法中每一个结果都是等概率的，因此哪一种写法都可以，但是要和取定的范围相匹配*/
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


    /*LCR 170 数组中的逆序对总数
    在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
     */
    /**
     【强烈建议】使用写法 reversePairs1
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
        if (nums == null || nums.length <= 1) return 0;
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
            if (nums[p1] <= nums[p2]) { /**err：只有“nums[p1]>nums[p2]”严格大于的时候才算逆序对！！“严格大于”很关键，因此这里需要带等于*/
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
            第二种：扩展的方法直接返回int类型；（见方法 reversePairs1）
            第三种：由于java是值传递，因此考虑传递引用类型（比如这个题的话可以使用int数组,见方法 reversePairs2）
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
        if(record==null||record.length<=1) return 0;
        return mergeSort(record,0,record.length-1);
    }

    private int mergeSort(int[] record, int left, int right) {
        if (left>=right) return 0;
        int mid = left+(right-left)/2;
        int leftNum = mergeSort(record, left, mid);
        int rightNum = mergeSort(record, mid + 1, right);
        return leftNum+rightNum+merge(record,left,mid,right);
    }

    private int merge(int[] record, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int index = 0, count = 0;
        int p1 = left, p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (record[p1] > record[p2]) { /**"严格大于"时才计算逆序对。*/
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

    /**全局变量的写法。
        思路：使用归并排序数组，过程中记录逆序对数量~
     */
    /**reversePairs1 和 reversePairs3 两种写法的对比
     【两种写法的比较】
     维度	    你的这份写法（局部统计）	                    上一份写法（全局变量）
     计数方式	每层递归返回当前区间的逆序对数，累加子问题的结果	使用全局变量 res 在递归过程中累积计数
     函数返回值	每次 mergeSort 都返回 int	mergeSort           无返回值，只更新全局变量
     变量作用域	局部统计，函数调用独立，不依赖全局状态	        依赖全局变量 res（共享状态）
     代码可复用性	✅ 高（更纯粹，无副作用）	                    ⚠️ 较低（依赖全局变量）
     性能表现	⚙️ 理论上一样（O(n log n)）	                ⚙️ 理论上一样（O(n log n)）
     微观性能差异	略慢（多一次返回值相加）	                    略快（直接累加）

     【可维护性的比较】
     特性	    全局变量写法	        返回值写法
     线程安全	❌ 不安全（共享状态）	✅ 安全（无共享）
     逻辑清晰	中等（依赖外部状态）	✅ 更清晰（函数自洽）
     调试便利	较难（全局状态不直观）	✅ 容易（局部调试独立）
     扩展性	    一般	✅               高（纯函数式）
     */
    int resReversePairs3 = 0;
    public int reversePairs3(int[] record) {
        if (record.length<=1) return 0;
        mergeSort11(record,0,record.length-1);
        return resReversePairs3;
    }

    private void mergeSort11(int[] record, int left, int right) {
        if (left>=right) return;
        int mid = left+(right-left)/2;
        mergeSort11(record,left,mid);
        mergeSort11(record,mid+1,right);
        merge111(record,left,mid,right);
    }

    /**
     【关键】只有在两边数组合并的时候才会涉及到逆序对，因此仅仅在”合并两半“的时候统计逆序对的数量，更新全局的答案应该就是ok的
     */
    private void merge111(int[] record, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int cur = 0;
        int i = left,j = mid+1;
        while (i<=mid&&j<=right){
            if (record[i]>record[j]){
                tmp[cur++] = record[j++];
                resReversePairs3 += (mid-i+1);
            }else {
                tmp[cur++]  =record[i++];
            }
        }
        /**下面的两个步骤中，还涉及res的更新吗？？？
         TODO：这里的思想也有点绕。。得看”站在什么角度“思考问题。有点类似于”有效三角形的数量“这个题目
         */
        while (i<=mid) tmp[cur++]=record[i++];
        while (j<=right) tmp[cur++]=record[j++];
        for(int k=0;k<tmp.length;k++){
            record[left+k] = tmp[k];
        }
    }

    /*59 螺旋矩阵Ⅱ
        给定参数n，产生一个矩阵，顺时针填写1，2，.....
     */
    public int[][] generateMatrix(int n) {
        int cur = 1;
        int left=0,top=0,right=n-1,bottom=n-1;
        int[][] res = new int[n][n];
        while (cur<=n*n){ /**这里写成”while(true)“也没问题！！！*/
            for (int i = left; i <= right; i++) {
                res[top][i] = cur++;
            }
            if (++top>bottom) break;

            for (int i = top; i <= bottom; i++) {
                res[i][right] = cur++;
            }
            if (--right<left) break;

            for (int i = right; i >= left; i--) {
                res[bottom][i] = cur++;
            }
            if (--bottom<top) break;

            for (int i = bottom; i >= top; i--) {
                res[left][i] = cur++;
            }
            if (++left>right) break;
        }
        return res;
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
                 * 同一层跳到下一个节点即跳过以prefix为根的子树，比如：
                 *     现在所在的节点是“10”，同一层的所有节点有“11、12、13、14、15.....19”，它
                 * 孩子节点呢？就是以“10”为前缀的节点，即“100、101、102、103、104、105.....109”。
                 * 进入这个if会导致prefix++来到11。
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

    /*方法的作用：
        算出以 cur 为根的树 ≤n 的数字 有多少个。（包含cur本身）
            也可以说为
        ”统计从 cur 开始到 cur+1（不含）之间有多少个数字在 [1, n] 范围内。“
    * */
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
     *【说明】动态规划的版本见方法 longestIncreasingPath__，常规的是使用记忆化搜索的版本见方法 longestIncreasingPath
     * dp数组定义：dp[i][j] 表示从单元格 (i,j) 出发的最长递增路径的长度。
     * 转移方程：从 (i,j) 出发，尝试走到上下左右四个方向 (x,y)，前提是 matrix[x][y] > matrix[i][j]；
            dp[i][j] = 1 + max(dp[x][y])。
     * 记忆化优化：每个格子只算一次，后续直接复用结果，避免指数级复杂度。
     * 整体结果：遍历所有单元格计算对应的dp值，返回 max(dp[i][j])。
     */
    /*二维数组的初始化方式~~~，不用使用“new int[][]。这样初始化反而简单”...
    建议”回溯类“题目使用这样的方法来定义方向*/
    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    /**
     * 【主函数的思路】
     *      ①声明二维数组memo存储每一个点的最长递增子路径；
     *      ②双层for循环遍历matrix的每一个位置计算出该位置的最长递增路径（任何位置的结果都会记录在memo
     * 数组），用res记录遇到的最大值(memo中每一点的值怎么求解？使用dfs方法，dfs的作用就是求解(i，j)点的
     * 最长递增子路径)；
     *      ③返回res
     * 【补充说明】
     *      1. 这个主函数的逻辑有点类似于题目”79.单词搜索“。。。共同点：不方便转换为dp的写法，因此使用回溯方
     *  法，从每一个位置出发得到局部解；进而得到全局解
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] memo = new int[m][n];
        int res = 0;
        /*从每一个位置开始，调用dfs得到”以（i,j）为起点的最长递增路径长度“*/
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
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[i][j] < matrix[x][y]) { /**err：比较之前先判断x和y不能越界....最后一个条件使用"matrix[i][j] > matrix[x][y]"应该也是OK的*/
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

    /*错误写法1*/
//    int[][] direct = {{1,0},{-1,0},{0,1},{0,-1}};
//    public int longestIncreasingPath___(int[][] matrix) {
//        int m = matrix.length,n = matrix[0].length;
//        int[][] memo = new int[m][n];
//        int res = 0;
//        for (int[] cur:memo){
//            Arrays.fill(cur,-1);
//        }
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                /**dfs函数有返回值，在主方法中更新res*/
//                res = Math.max(res,dfsLongestIncreasingPath(matrix,memo,i,j));
//            }
//        }
//        return res;
//    }
//
//    private int dfsLongestIncreasingPath(int[][] matrix, int[][] memo, int i, int j) {
//        if (memo[i][j]!=-1) return memo[i][j];
//        memo[i][j] = 1;
//        for (int[] cur:direct){
//            int x = i+cur[0],y = j+cur[1];
    /**错误点1：x,y的要求是大于等于0，是带等于的*/
//            if (x>0&&x<matrix.length&&j>0&&j<matrix[0].length&&matrix[x][y]>matrix[i][j]){
    /**错误点2：不能直接使用"memo[x][y]"，因为该位置可能还没计算呢，应该使用"dfsLongestIncreasingPath(matrix,memo,x,y)+1"*/
//                memo[i][j] = Math.max(memo[i][j],memo[x][y]);
//            }
//        }
//        return memo[i][j];
//    }

    /*错误写法2：这种形式写dp应该是错误的。
        这个题不像"编辑距离"，"最长回文子串"等dp有明显的位置依赖关系，任何一个位置可能依赖到周围的四个位置，
    如果这样理解，是没有一种合理的计算顺序的！！！
    * */
//    public int longestIncreasingPath(int[][] matrix) {
//        int[][] directs = {{1,0},{-1,0},{0,1},{0,-1}};
//        int res = 0;
//        int m = matrix.length,n = matrix[0].length;
//        int[][] dp = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i==0||j==0) dp[i][j] = 1;
//                else {
//                    for (int[] cur:directs){
//                        int x = i+cur[0];
//                        int y = j+cur[1];
//                        if (matrix[i][j]>matrix[x][y])
//                            dp[i][j] = Math.max(dp[x][y]+1,dp[i][j]);
//                    }
//                }
//                res = Math.max(dp[i][j],res);
//            }
//        }
//        return res;
//    }

    /**
     * 329能不能修改成“动态规划”的版本呢？？可以，就是下面的形式
     【思想】
     1. “递归、回溯遍历”计算答案的解法 改成 “动态规划”的解法，最最最关键、核心的问题（或者说前提条件）————确保每到达
     一个位置，所有它依赖位置的答案已经计算出来了！！（这一点很重要，要求熟记于心）但是前提是对于任何一个节点它的依赖位置
     是明确的。
     2. 换到此题，每一个位置（i，j）可能依赖于四周的四个位置，从这个角度看，是不能进行dp的，没有任何一种遍历方式能满足
     这个二要求。。。下面的longestIncreasingPath__是基于这样的思想，任何一个位置的dp。一定依赖于比他小的位置的dp，因此
     在计算所有位置的dp时，先对matrix的所有值排序，保证计算顺序
     ——————因此，DP的写法中，重要的是”所有位置计算顺序的确定“。这种“计算顺序”，可能是像“编辑距离”、“爬楼梯”等在二
     维表中明确的位置依赖关系，也可能是像这个题一样在二维表中无法确定明确的依赖位置，需要结合matrix该位置的值来判断
     */
    int[][] dirs11 = {{1,0},{-1,0},{0,1},{0,-1}};
    public int longestIncreasingPath__(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                cells.add(new int[]{i, j});
        /*cells中添加所有的位置数组；这一步按照matrix中该位置的值进行排序。
        因此最终cells中的数组，就是matrix元素升序排序后的位置顺序
        */
        cells.sort((a,b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);

        int[][] dp = new int[m][n];
        int ans = 1;
        for (int[] c : cells) {
            int i = c[0], j = c[1];
            dp[i][j] = 1;
            for (int[] dir : dirs11) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[x][y] + 1);
                }
            }
            ans = Math.max(ans, dp[i][j]);
        }
        return ans;
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

        //或者 使用下面的方法
//        public int[] shuffle() {
//            for (int i = 0; i < array.length; i++) {
                /**对于每一个位置i，从[i,array.length-1]随机选择一个数进行交换*/
//                int index = new Random().nextInt(i, array.length);
//                swap2(array,i,index);
//            }
//            return array;
//        }

        private void swap2(int[] array, int i, int j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
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
}
