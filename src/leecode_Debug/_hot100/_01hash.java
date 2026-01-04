package leecode_Debug._hot100;

import java.util.*;

public class _01hash {
    /*1.
    * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
    你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
    你可以按任意顺序返回答案。*/
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])) return new int[]{i,map.get(nums[i])};
            map.put(target-nums[i],i);
        }
        return new int[]{-1,-1};
    }

    /*49.
    * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    字母异位词 是由重新排列源单词的所有字母得到的一个新单词。*/
    /**
     * 【解题思路】
     *      解法1：对每一个字符串排序。排序后的作为键key，原始串作为key对应value的一个元素————
     *  即list中的一个元素，存入到map<String,List<String>>;
     *      解法2：统计每一个字符串将统计值作为键值。
     *   总之，两种解法都是把键-->字符串存入到map，区别在于键是什么的问题！!
     * 【难点】
     *      1. 最后的返回值需要使用“new LinkedList<>(map.values());”。注意不能使用下面的方
     *  式：“(List<List<String>>) map.values()”，错误原因：
     *       java.lang.ClassCastException: class java.util.HashMap$Values cannot be cast
     *  to class java.util.List (java.util.HashMap$Values and java.util.List are in module
     *  java.base of loader 'bootstrap')
     * */
    /*解法1：规规矩矩，字符串的字符排序。
        复杂度比较：O(nk log k)，其中 n 是字符串的个数，k 是每个字符串的长度。
                  n 是字符串的数量，k log k 是排序的时间复杂度。每一个字符串都需要对它的所有字符排序，因此
              时间复杂度是相乘
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            //①把字符串转换为字符数组
            char[] chars = str.toCharArray();
            //②排序字符数组
            Arrays.sort(chars);
            //③转换为字符串
            String s = new String(chars);
            //④在map中添加到字符串对应的list
            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                LinkedList<String> ele = new LinkedList<>();
                ele.add(str);
                map.put(s, ele);
            }
        }
        return new LinkedList<>(map.values()); /**🔺err：返回之前强转类型。。。注意的是强转类型不能像平时的那样，要使用new的方式*/
    }

    /*解法2（其实相比较 解法1 也没优化）：使用gerOrDefault方法。。其实跟 解法1 是类似的*/
    public List<List<String>> groupAnagrams_1(String[] strs) {
        LinkedList<List<String>> res = new LinkedList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            List<String> ele = map.getOrDefault(new String(chars), new LinkedList<String>());
            ele.add(strs[i]);
            /*可以使用下面的三行代替上面的两行*/
            /**可以推断出：map的key如果是字符串，其实是使用”equals“方法来判断key是不是相等的，而不是引用*/
//            String s = new String(chars);
//            List<String> ele = map.getOrDefault(s, new LinkedList<String>());
//            ele.add(strs[i]);
            map.put(new String(chars),ele);
        }

        return new LinkedList<List<String>>(map.values());
    }

    /*解法3：统计字符的数量。
        时间复杂度分析：O(nk)，其中 n 是字符串的个数，k 是字符串的最大长度。
                    解释：每一个字符串需要遍历一遍，统计每个字符的数量，因此是 nk
        【说明】这种方法省去了字符数组的排序，只需要过一遍字符串的字符，因此复杂度是n；如果是排序，最优的时
     间复杂度也是O(n*logn)，比仅仅遍历一遍字符的复杂度高
    */
    public List<List<String>> groupAnagrams_best(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str:strs){
            //①：统计 str 中每一个字符的数量
            int[] flags = new int[26];
            for (char c:str.toCharArray()){
                flags[c-'a']++;
            }
            //②：把 flags 数组中的每一个数添加到sb。（注：需要使用”#“分隔，否则会出错）
            StringBuilder sb = new StringBuilder();
            for(int i:flags){
                /**疑问：和”sb.append(i).append("#")“ 的区别是什么？？
                 【注意】这里就仅仅是把每隔字符出现的次数记录到 StringBuilder 中了。
                 */
                sb.append(i).append('#');
            }
            //③：把 str 存进 key 对应value即列表中
            String key = sb.toString();
            map.putIfAbsent(key,new LinkedList<>());
            map.get(key).add(str);
        }

        return new LinkedList<>(map.values());
    }


    /*128. 最长连续序列
    * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。*/
    /**
     * 【思路】
     *      1. 把数组中所有的数放进set；
     *      2. 遍历set中的数，求解以它开始的最长序列是多长。。。。如何确保以它开始（即set中没有它前面的那个数）
     * 【关键的有两个点】
     *      1. 研究每一个数nums[i]时，如果nums[i]-1的数不存在，才会计算此时的连续序
     * 列长度。
     *      2. 研究的时候从set里面拿值（而不是从nums拿值），能避免反复研究重复的元素
     * */
    public int longestConsecutive(int[] nums) {
        if (nums.length==0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i:nums){
            set.add(i);
        }
        /**err：不能从nums拿取值，会导致重复元素不断被研究。提交后会发现：78/81用例超时（改用例几乎都是重复元素）*/
//        int res = 1;
//        for (int i=0;i<nums.length;i++){
//            if (!set.contains(nums[i]-1)){
//                int j=0;
//                while(set.contains(nums[i]+j)) j++;
//                res = Math.max(res,j);
//            }
//        }
//        return res;

        int res = 0; /**err：res的初始值必须是0，因为数组可能没有元素*/
        for (int num:set){ /**err：这个题的关键，从set中拿取数进行研究以它开始的序列长度*/
            if (!set.contains(num-1)){ //要求这个num必须是序列开始的那个数
                int length = 0;
                while(set.contains(length+num)) length++;
                res=Math.max(length,res);
            }
        }
        return res;
    }
}
