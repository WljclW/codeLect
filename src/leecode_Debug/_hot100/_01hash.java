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
     *      解法1：对每一个字符串排序。排序后的作为键，原始串作为值，存入到map;
     *      解法2：统计每一个字符串将统计值作为键值。
     *   总之，两种解法都是把键-->字符串存入到map，区别在于键是什么的问题！!
     * */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if (map.containsKey(s)){
                map.get(s).add(str);
            }else{
                LinkedList<String> ele = new LinkedList<>();
                ele.add(str);
                map.put(s,ele);
            }
        }
        return new LinkedList<>(map.values()); /**err：返回之前强转类型*/
    }


    /*128.
    * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。*/
    /**
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
        /**err：不能从nums拿取值，会导致重复元素不断被研究。提交后会发现：78/81用例超时*/
//        int res = 1;
//        for (int i=0;i<nums.length;i++){
//            if (!set.contains(nums[i]-1)){
//                int j=0;
//                while(set.contains(nums[i]+j)) j++;
//                res = Math.max(res,j);
//            }
//        }
//        return res;

        int res = 1;
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
