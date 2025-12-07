package leecode_Debug._hot100;

import java.util.HashMap;
import java.util.LinkedList;

public class _03subString {
    /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回 滑动窗口中的最大值 。
    * */
    /**
     * 【思路】使用双端队列，队列内的数严格单调减（换言之，只要当前的数nums[i]"大于等于"双端队列最后一个
     * 数nums[m]，则双端队列尾部的nums[m]永远不可能是最大值了，因此双端队列尾部的元素弹出）。。详细步骤
     * 如下：
     *      1。先将前面的k个数组添加进双端队列，此时会生成0位置的信息（前面K个数进入双端队列后，双端队列
     *          的头部即为0位置的信息）；
     *      2. for循环内依次将剩下的元素(i从k开始)添加进双端队列；每一轮循环需要确保双端队列头的位置还在
     *          窗口内(即队首的索引不能是i-k)，并生成对应位置的信息(生成i-k+1位置的信息)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1]; //比如nums长度为3，窗口大小为3，则res中应该包含一个数
        LinkedList<Integer> queue = new LinkedList<>();
        /*step1：先将窗口的大小扩大为k，结束后生成res[0]的信息*/
        for (int i = 0; i < k; i++) {
            //队列内要求单调减
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) { /**等于的时候队尾也要出队，比如"2,4"（2,4位置的值都是7）则2位置的7永远不会成为窗口内的最大值了*/
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        res[0] = nums[queue.peekFirst()]; /**err：注意要去nums中取值*/
        /*step2：对于剩下的元素依次进入，每次窗口进入一个元素，就需要生成一个位置的信息————i-k+1（比如k位置的
            元素进入到队列时，就需要生成1位置的最大值信息，因为1~k这样的窗口大小是k，因此对应i-k+1位置的信息）。
            【注意】由于窗口的大小固定k，因此要注意判断队列头的元素是不是已经出窗口了！！*/
        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) { //①先将当前位置的元素入栈。前提：进行合规检查，把不合规的踢出去
                queue.pollLast();
            }
            queue.offerLast(i);
            if (queue.peekFirst() == i - k) { //②判断队头元素是不是变得不合法（即当前队列头的索引是不是已经出窗口了），不合法时弹出
                queue.pollFirst();
            }
            res[i - k + 1] = nums[queue.peekFirst()]; //③这一轮会生成res的（注意别写错了）"i-k+1位置"的信息
        }
        return res;
    }


    /*
    * 76.给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
    * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    * */
    /**
     *【思路】
     *【建议的写法】解法minWindow_ano 和 解法minWindow 二选一
     *【说明】这个题的特殊之处在于：“while循环”内部的if和while可以并列写，也可以把while写在
     *      if的内部（关于这点见 方法minWindow_ano 和 方法minWindow）。。其中if循环的作用：
     *      将对应的字符纳入到窗口；while循环的作用：把窗口缩小到 合法/非法 的边界，此时才能
     *      得到最优解————最小的合法窗口大小
     */
    public String minWindow(String s, String t) {
        if (s.length()<t.length()) return "";
        /*step1：使用need这个map存储目标子串t中每一个字符对应的数量*/
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }
        /*变量等需要的时候再过来声明*/
        int start = 0;
        int valid = 0;
        int left = 0,right = 0;
        int len = Integer.MAX_VALUE;
        HashMap<Character, Integer> window = new HashMap<>();
        /*step2：双指针滑动窗口研究s字符串*/
        while (right<s.length()){
            char c = s.charAt(right);
            /*2.1：只研究t字符串有的字符————等价于need这个map有。
            *   第一步：如果有的话，则将字符放进window；
            *   第二步：如果window中字符c的数量 等于 need中字符c的数量，则说明c这个字符通过校验，因此valid++*/
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                /**err：
                 *    1. map.get比较值相等的时候必须使用“.intValue()”方法才可以，跟“最小栈”题目是类似的
                 * 道理。。
                 *      这里如果不使用“.intValue()”会导致倒数第二个测试用例过不了！！，因为数太大了，超过
                 * 了Integer缓存的整数范围。
                 *    2. 只有在相等的时候才会更新valid！！
                 * */
                if (window.get(c).intValue()==need.get(c).intValue()){
                    valid++;
                }
            }
            /*2.2：只要通过校验的字符数量 等于 need的size，就说明s中当前窗口是覆盖字符串t的，
             因此可以更新答案；并且尝试缩小窗口，直到窗口中的子串不覆盖字符串t为止。
                    while循环的完整逻辑————
                        ①现根据窗口的大小判断是不是需要更新start以及len。【注意】这里窗口的大小在计算的时
                    候会根据right指针更新的时机有所变化。这段代码中right指针更新是在while循环之后，因此这
                    里right指针对应的字符还是在窗口的，因此窗口的大小是“right-left+1”。
                          ————再总结一句话，
                            滑动窗口的题目中，任何时刻窗口一定包含left位置，但是right位置具体包含不包含需
                            要看right指针更新的时机！！！
                        ②拿到left位置对应的字符c1；
                        ③如果need这个map包含c1，则说名window中必然包含，因此需要更新window。。并且还要在
                    更新后判断window中c1的数量 是不是还 符合need的要求！！如果window中的值小于need.get(c1)，
                    说明c1字符已经达不到需要的数量，因此valid--。
                        ④更新left指针，left++。
            * */
            while (valid==need.size()){
                if (right-left+1<len){ //注释①
                    len = right-left+1;
                    start = left;
                }
                char c1 = s.charAt(left); //注释②
                if (need.containsKey(c1)){ //注释③
                    window.put(c1,window.get(c1)-1);
                    if (window.get(c1)<need.get(c1)){
                        valid--;
                    }
                }
                left++; //注释④
            }
            right++;
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }

    /*另外一种写法如下。其实没有本质的思想区别。唯一的区别就在于left、right更新时机导致的
    * 长度计算的区别
    */
    public String minWindow_(String s, String t) {
        if (s.length()<t.length()) return "";

        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int left = 0,right = 0;
        int valid = 0;
        int start = 0,len = Integer.MAX_VALUE;

        HashMap<Character, Integer> window = new HashMap<>();
        while (right<s.length()){
            char c = s.charAt(right);
            /**区别1：放进去一个字符，就更新right指针。。。上面的方法是每一轮换最后才更新。。造成的区别
             *    就是这种方法下面的代码中right位置是不包含的，因此计算子串的长度就是“right-left”*/
            right++;
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c).intValue()==need.get(c).intValue()){  /**这里拿到的是Integer，用“==”会不会有问题*/
                    valid++;
                }
            }

            while (valid==need.size()){
                /**区别2：计算子串的长度时，这种方法是“right-left”。原因：刚放进right位置的字符right就右
                 *    移动了，因此此时right并不在窗口内，还没研究呢。。就比如：本来窗口是2、3、4；然后4放
                 *    进map后right更新到5，此时窗口的长度就是5-3，即right-left。*/
                if (right-left<len){
                    start = left;
                    len = right-left;
                }

                char leftChar = s.charAt(left);
                left++;
                if (need.containsKey(leftChar)){
                    window.put(leftChar,window.get(leftChar)-1);
                    if (window.get(leftChar)<need.get(leftChar)){
                        valid--;
                    }
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }

    /*用 for 的写法*/
    public String minWindow_wh(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        HashMap<Character, Integer> window = new HashMap<>();
        int start = -1,len = Integer.MAX_VALUE;
        int valid = 0,left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c).intValue()==need.get(c)){
                    valid++;
                }
            }
            while (valid==need.size()){
                if (i-left+1<len){
                    len = i-left+1;
                    start = left;
                }
                char c1 = s.charAt(left++);
                if (need.containsKey(c1)){
                    window.put(c1,window.get(c1)-1);
                    if (window.get(c1)<need.get(c1)){
                        valid--;
                    }
                }
            }
        }
        return start==-1?"":s.substring(start,start+len);
    }

    /*除了上面的写法以外，还有下面的方法....
        总的来说，方案的思路就是：for循环/while循环 的选择、里面while和if是并列还是包含关系 的选择，一
     共2*2=4种写法
    * */
    public String minWindow_ano(String s, String t) {
        if (s.length()<t.length()) return "";
        HashMap<Character, Integer> need = new HashMap<>();
        for(char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int start = 0;
        int left = 0,right = 0;
        int len = Integer.MAX_VALUE;
        int valid = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        while (right<s.length()){
            char c = s.charAt(right);
            right++;

            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c).intValue()==need.get(c).intValue()){
                    valid++;
                }
                /**下面代码的位置跟原来方案是不一样的，也是可行的！！即————
                 *      下面的while循环写在if (need.containsKey(c))里面；
                 *   或者 写在if (need.containsKey(c))外面都是可以的*/
                while (valid==need.size()){
                    if (right-left<len){
                        len = right-left;
                        start = left;
                    }
                    char c1 = s.charAt(left);
                    left++;
                    if (need.containsKey(c1)){
                        window.put(c1,window.get(c1)-1);
                        if (window.get(c1)<need.get(c1)) /**【注意】这里必须认真判断一下c1字符的数量是不是不够要求了*/
                            valid--;
                    }
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }

    /* minWindow_ano 写成while循环的版本*/
    public String minWindow_ano1(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int valid = 0;
        int start = -1,minLen = Integer.MAX_VALUE;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (need.containsKey(c)){
                map.put(c,map.getOrDefault(c,0)+1);
                if (map.get(c).intValue()==need.get(c).intValue()){
                    valid++;
                }
                while (valid==need.size()){
                    if (i-left+1<minLen){
                        start = left;
                        minLen = i-left+1;
                    }
                    char c1 = s.charAt(left++);
                    if (need.containsKey(c1)){
                        map.put(c1,map.get(c1)-1);
                        if (map.get(c1)<need.get(c1)){
                            valid--;
                        }
                    }
                }
            }
        }
        return start==-1?"":s.substring(start,start+minLen);
    }

    /**下面的写法是常见的错误写法。。。。
     * 错误原因1：
     *      出现在“if (window.get(c)==need.get(c))”，map得到的值是Integer，判断相等不能用==。报错的信息————
     *      “提交”时有长的字符串的报错
     * 错误原因2：
     *      出现在“char c1 = s.charAt(left);”没有更新left。报错的信息————
     *      都不用提交，运行是第一个用例报错，如下：
                 输入
                 s =
                 "ADOBECODEBANC"
                 t =
                 "ABC"
                 输出
                 "ADOBEC"
                 预期结果
                 "BANC"
     *             */
//    public String minWindow(String s, String t) {
//        HashMap<Character, Integer> need = new HashMap<>();
//        for (int i = 0; i < t.length(); i++) {
//            char c = t.charAt(i);
//            need.put(c,need.getOrDefault(c,0)+1);
//        }
//
//        int valid = 0;
//        int start = -1,maxLen = Integer.MAX_VALUE;
//        int left = 0;
//        HashMap<Character, Integer> window = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (need.containsKey(c)){
//                window.put(c,window.getOrDefault(c,0)+1);
                  /**出错地方1：应该改为if (window.get(c).intValue()==need.get(c).intValue())*/
//                if (window.get(c)==need.get(c)){
//                    valid++;
//                }
//            }
//
//            while (valid==need.size()){
//                if (maxLen>i-left+1){
//                    maxLen = i-left+1;
//                    start = left;
//                }
                  /**出错地方2：应该改为char c1 = s.charAt(left);*/
//                char c1 = s.charAt(left);
//                if (window.containsKey(c1)){
//                    window.put(c1,window.get(c1)-1);
//                    if (window.get(c1)<need.get(c1)){
//                        valid--;
//                    }
//                }
//            }
//        }
//        return start==-1?"":s.substring(start,start+maxLen);
//    }




    /*560.
    * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
    /**
     * 【解题关键】计算每一个位置的前缀和pre，放入map前判断是不是有pre-k。map中存放的是
     *      每一个前缀和数值 对应的数量。详细步骤如下：
     *          1. 创建map，并先放入 map.put(0,1);
     *          2. for循环从index=0开始，依次研究每一个位置。做如下操作————
     *              ①先更新前缀和；②再从map获取满足条件前缀和的数量即"map.getOrDefault(pre-k,0)"，
     *              并将结果添加到res；③将此位置的前缀和放进map。
     *          3. 返回res.
     *      最关键的是for循环内，①先更新pre、②再更新结果res、③最后才把pre放进map。其中②必
     *      须是在③之前
     * */
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int pre = 0;
        int res = 0;
        map.put(0,1);   /**err：需要先放入pre为0的key-value*/
        for (int num:nums){
            //先更新前缀和，然后看前缀和pre-k的数量，有的话累加到res.（pre-k到当前位置的子数组和就是k）
            pre += num;
            /**err：下面两行交换就错了！！
             *      必须是先更新res，然后再将pre放进map，如果先把pre放进map会影响结果！！！
             *      比如：k=0的时候会出错，此时会出现pre-k=pre，如果先把pre放进去，就导致map.get(pre-k)的
             *  数值比正确的多了。——————比如：放进去之前get的结果是m，放进去之后再获取结果就是m+1了！！*/
            res += map.getOrDefault(pre-k,0);
            map.put(pre,map.getOrDefault(pre,0)+1); /**err：更新了前缀和，不要忘记放进map*/
        }
        return res;
    }
}
