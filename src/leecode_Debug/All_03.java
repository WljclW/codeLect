package leecode_Debug;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/5/21 16:06
 */
public class All_03 {
    //581
    public int findUnsortedSubarray(int[] nums) {
        int res = 0,left=0;
        int leftMax = Integer.MIN_VALUE,rightBoun = -1;
        int rightMin = Integer.MAX_VALUE,leftBoun = nums.length;
        while(left<nums.length){
            if (nums[left]>leftMax){
                leftMax = nums[left];
            }else{
                rightBoun = left;
            }
            if(nums[nums.length-1-left]<rightMin){
                rightMin = nums[nums.length-1-left];
            }else{
                leftBoun = nums.length-1-left;
            }
        }
        return rightBoun==-1?0:rightBoun-leftBoun+1;
    }


    /*49.
    * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    字母异位词 是由重新排列源单词的所有字母得到的一个新单词。*/
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str:strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            if (map.containsKey(charArray.toString())){
                List<String> strings = map.get(charArray.toString());
                strings.add(str);
            }else{
                LinkedList<String> cur = new LinkedList<>();
                cur.add(str);
                map.put(charArray.toString(),cur);
            }
        }
        return new LinkedList<>(map.values());
    }


    /*128.
    * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。*/
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max = 1;
        for (int num:nums){
            set.add(num);
        }

        for (int num:set){
            if (!set.contains(num-1)){
                int length = 0;
                while(set.contains(num+length)) length++;
                max = Math.max(max,length);
            }
        }
        return max;
    }


        /*
    239.
    * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
    * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
            返回 滑动窗口中的最大值 。
    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();

        for (int i=0;i<k;i++){
            while (!deque.isEmpty()&&nums[deque.peek()]<nums[i]){
                Integer index = deque.pollLast();
            }
            deque.offerLast(nums[i]);
        }
        res[0] = nums[deque.peek()];

        for (int i=k;i<nums.length;i++){
            if (i-k==deque.peek()){
                deque.pollFirst();
            }
            while (!deque.isEmpty()&&nums[deque.peek()]<nums[i]){
                Integer index = deque.pollLast();
            }
            deque.offerLast(nums[i]);
            res[i-k+1] = nums[deque.peek()];
        }
        return res;
    }


       /*560.
    * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int pre = 0;
        map.put(0,1);
        int res = 0;
        for (int num:nums){
            pre += num;
            if (map.containsKey(pre-k)){
                res += map.get(num-k);
            }
            map.put(pre,map.getOrDefault(pre,0)+1);
        }
        return res;
    }


    /*189.
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * */
    public void rotate(int[] nums, int k) {
        swap(nums,0,nums.length-1);
        swap(nums,0,k-1);
        swap(nums,k,nums.length-1);
    }
    public void swap(int[] nums,int l,int r){
        while(l<r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }


    //跑一下方法：leecode_Debug._hot100._04normalArr.firstMissingPositive


        /*74.
    给你一个满足下述两条属性的 m x n 整数矩阵：
    每行中的整数从左到右按非严格递增顺序排列。
    每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
    * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m =matrix.length,n = matrix[0].length;
        int right = m*n,left = 0;
        while (left<right){
            int mid = left + (right-left)>>1;
            int cur = matrix[mid/n][mid%n];
            if (cur>target){
                left = mid+1;
            } else if (cur<target) {
                right = mid;
            }else{
                return true;
            }
        }
        return false;
    }


    /*139.
    * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利
    * 用字典中出现的一个或多个单词拼接出 s 则返回 true。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] flag = new boolean[s.length() + 1];
        flag[0] = true;
        for (int i=1;i<s.length();i++){
            for (int j=0;j<i;j++){
                if (flag[j]&&set.contains(s.substring(j,i))){
                    flag[i] = true;
                    break;
                }
            }
        }
        return flag[s.length()];
    }


    /*45.
    * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
    每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你
    * 可以跳转到任意 nums[i + j] 处:
    0 <= j <= nums[i]
    i + j < n
    返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
    * */
//    public int jump(int[] nums) {
//
//    }


}
