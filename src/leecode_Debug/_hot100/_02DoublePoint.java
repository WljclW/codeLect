package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//15，42
public class _02DoublePoint {
    /*
    * 283.给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    * 【升级题目】：熟悉颜色分类，75。。。。
    *       这种题目的思路就是：假想left指向左边界的最后一个；right指向右边界的第一个；cur表示当前
    *    研究到的位置
    * */
    /**
     * 【建议】使用解法moveZeroes1、moveZeroes2（moveZeroes2更符合自己的想法）。直接采用交换的方式，这样就不用
     * 对cur后面的位置再赋值了
     * */
    public void moveZeroes(int[] nums) {
        int left = 0;
        int cur = 0;
        while(cur<nums.length){
            if(nums[cur]!=0){
                nums[left++] = nums[cur++]; /**【注】left位置必然已经研究过了，因此left也要加加————荷兰国旗也是一样的*/
            }else
                cur++;
        }
        /**err：需要将left及后续的都置零*/
        for (int j=left;j< nums.length;j++){
            nums[j] = 0;
        }
    }


    //解法1：使用两个指针。如果快指针的数不是0，则直接交换快慢两个指针所指向的数
    public void moveZeroes1(int[] nums) {
        int left = 0,cur = 0;
        for (cur = 0;cur<nums.length;cur++){
            if (nums[cur]!=0){
                swap(nums,cur,left++);
            }
        }
    }
    private void swap(int[] nums, int left, int right) {
        int tmp  =nums[left];
        nums[left] = nums[right];
        nums[right] =tmp;
    }

    public void moveZeroes2(int[] nums) {
        int left = 0,cur = 0;
        while (cur<nums.length){
            /**情况1：如果cur位置的数不是0，则交换到left的位置*/
            if (nums[cur]!=0){
                swap1(nums,left++,cur);
            }
            /**情况2：说明cur位置的数就是0，此时仅仅需要移动cur指针。
                【说明】由于if块中交换后没有移动cur指针，因此这个“cur++”不用写在else块中。。因此见下面的注释说明，两种while
             循环的写法
             */
            cur++;
        }
        /**while循环的写法与下面的写法是等价的*/
        /*
        while (cur<nums.length){
            if (nums[cur]!=0){
                swap1(nums,left++,cur++);
            }else{
                cur++;
            }
        }
         */
    }

    private void swap1(int[] nums, int l, int r) {
        int tmp  =nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    /*
    * 11.给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直
    * 线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最
    * 多的水。
    * 【】：注意这个题和接雨水是不一样的，这个题目中挡板的宽度忽略不计。但是接雨水问题42其实是一个个柱子组
    *    成的，柱子之间是没有间隙的。
    * */
    /**
     * 【步骤】双指针相向而行，计算以它们为边界能盛多少水。。只要中间有间距（即left<right）
     *      1. 每到一个位置计算能盛的水（高度取决于左右指针位置height的最小值，宽度取决于两个指针的距离）
     *      2. 看两个指针对应的height哪一个高，更新height小的那一个指针。
     */
    public int maxArea(int[] height) {
        int left = 0,right = height.length-1;
        int result = Integer.MIN_VALUE;
        while(left<right){
            /*step1：计算当前left和right能盛的水，并更新结果*/
            int cur = (right-left)*Math.min(height[left],height[right]);
            result = Math.max(cur,result);
            /*step2：看看是更新left指针还是更新right指针。————哪一个位置的height小更新哪一个指针*/
            if(height[left]<height[right]){
                left++;
            }else{
                right--; //right指针需要减1.
            }
        }
        return result;
    }

    /*
    * 15.给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    * 【】：关键的步骤在于双指针 过程中，如何跳过所有相同的元素。
    * */
    /**
     * TODO：拿到“while (left<right && nums[left]==nums[++left]);”的字节码看一下
     * 【关键】先排序；然后从左向右遍历位置cur，每到一处位置cur，设置left、right指针，根据当前三个位置数的和
     *      与0的关系，来移动left或者right指针。
     * 【步骤】
     *      1. 对数组进行排序；
     *      2. for循环研究每一个位置i，范围[0，nums.length-3]，因为至少要保证后面还有两个数。。。后续
     *          所有的逻辑均在for循环内
     *      3. left指针为i+1，right指针为nums.length-1...根据i、left、right三处的数之和判断指针如何移
     *          动
     *      4. 如果三数之和大于0，说明数要变小————左移right;
     *         如果三数之和小于0，说明数要变大————右移left;
     *         如果三数之和等于0，说明要添加进结果。并且 移动left、right跳过中间相等的数
     * 【去重】必须去重的地方有两个————
     *      ①遍历到位置cur，如果它和前面的数相等即nums[cur-1]==nums[cur]，说明cur-1位置的时候已经研究过
     *  了，cur位置需要跳过。【举例】：{-2，-2，-1，0，2}如果0位置的-2作为cur已经研究过了，cur来到1时就要跳
     *  过，直接来到2位置的-1。。如果不跳过就会出现相同的组合：[-2(索引0的-2)，0，2]、[-2(索引1的-2)，0，2]，
     *  实际上是同一种解，就出现重复了。
     *      ②如果找到一个可行解即nums[cur]+nums[left]+nums[right]==0，此时需要移动left指针和right指针，
     *  移动时也必须跳过所有相同的元素。【举例】：{-2，-1，0，0，1，1，2，2}在cur=0、left=2、right=7时
     *  得到了一组可行解[-2,0,2]....此时需要left++，right--，left来到了索引3处的0，right指针指向了索
     *  引6处的2，此时还是一个可行解[-2,0,2]，如果这种情况不排重就会导致添加了重复解。
     *      综上，这两个地方必须要去重。剩下的>0、<0的情况下去重不去重都是可以的（此时去重仅仅是把排除
     *  操作提前了，本质上在于他们并不会影响结果）。
     * */
    /**
     *【注意】找到一组解时，要去重，写法是"while (left<right && nums[left]==nums[++left]);"。如果写
     *      成了"while (left<right && nums[++left]==nums[left]);"是错误的，初始的测试用例就过不了。
     *      错误的用例如下：
                     输入
                     nums =
                     [-1,0,1,2,-1,-4]
                     输出
                     [[-1,-1,2]]
                     预期结果
                     [[-1,-1,2],[-1,0,1]]
            二者的区别————
            1. while (left < right && nums[left] == nums[++left]);
             执行顺序：
                ①先判断 left < right。
                ②++left 会让 left 先加 1，然后用新值去取数组元素。因此比较的是 旧位置的值 和 新位置的值。
                如果相等，继续循环（重复 1、2）。
             特点：
                 每次比较的两个元素是 相邻的（前一个和当前自增后的那个）。
                 不会出现用同一个索引取值再比较的情况。
                 常用于跳过重复元素（例如 nums[left] 连续相等时，left 往右移）。
            2. while (left<right && nums[++left]==nums[left])
             执行顺序：
                ①先判断 left < right。
                ②++left 先让 left 加 1，然后比较 nums[left] == nums[left]。
             问题：
                    2的写法永远是 同一个位置和自己比较，所以一定为 true（除非数组里有 NaN 这种不相等的特殊情
                 况，但 Java 的 int/long 等不会这样）。
                    如果没有别的 break 条件，会死循环，直到 left >= right 才会退出。
     */
    //写法1
    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i=0;i< nums.length-2;i++){
            /*step1：第一个指针就是i位置，需要跳过和前面元素相等的位置*/
            if(i>0&&nums[i]==nums[i-1]){ /**err：用if不用while，while里面使用continue就只是回到了外层循环*/
                continue;
            }
            int left = i+1,right = nums.length-1;
            /*step2：只要left<right就持续寻找可行解。
            * 根据i、left、right指针指向的三个数与0的大小关系来移动left、right指针。具体的来说————
            *       ①如果他们三个指向的数之和小于0，说明需要变大一些，因此需要移动left指针，即left++;
            *       ②如果他们三个指向的数之和大于0，说明需要变小一些，因此需要移动right指针，即right--
            *       ③如果等于0需要移动left、right指针，并且此过程需要跳过相同的元素*/
            while (left<right){
                int curRes = nums[i]+nums[left]+nums[right];
                if(curRes<0){
                    /*>0，<0的时候不去重也可以，但是=0必须去重。
                    * 比如：这里是<0，则下面的两句使用哪一句都可以，后面的一句去重只是把去重操作提前了*/
//                    left++; //这麽写表示不去重，也是可以的
                    while (left<right && nums[left]==nums[++left]); /*跳过所有相等的值*/
                }else if(curRes>0){
//                    right--; //这麽写表示不去重，也是可以的
                    while(left<right && nums[right]==nums[--right]);
                }else {
                    res.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right]))); /**err：使用这个添加list方便*/
                    while (left<right && nums[left]==nums[++left]); /**【注意】其实保证left<right就能保证l和r都不越界*/
                    while (left<right && nums[right]==nums[--right]); /**并且这两的“++left”和“--right”必须放在后面。补充：如果放在前面就是先移动指针，再判断的时候就会发现永远是相等的*/
                }
            }
        }
        return res;
    }


    //另一种写法其实思想是一样的。。。主要区别在于">、<"不会进行排重
    public List<List<Integer>> threeSum02(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            if (i>0&&nums[i]==nums[i-1]) continue;  /**这里必须进行去重*/
            int left = i+1,right = nums.length-1;
            while(left<right){
                int cur = nums[i] + nums[left] + nums[right];
                if (cur>0){
                    right--;
                }else if (cur<0){
                    left++;
                }else{
                    LinkedList<Integer> ele = new LinkedList<>();
                    ele.add(nums[i]);
                    ele.add(nums[left]);
                    ele.add(nums[right]);
                    res.add(ele);
                    left++;
                    right--;
                    /**得出一种可行解，也必须进行去重*/
                    while(left<right&&nums[left]==nums[left-1]) left++;
                    while(left<right&&nums[right]==nums[right+1]) right--;
                }
            }
        }
        return res;
    }


    /*
    * 42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    * */
    /**
     *【双指针的思路】left从index=0开始向右，right从index=height.length-1开始向左。过程中左右两边分别记录碰到的最大值。
     *      每一时刻————
     *      ①根据left、right的位置更新leftmax、rightMax。二者分别标识left指针、right指针遍历时遇到的height最大值
     *      ②根据leftMax、rightMax的最小值 更新left位置能存的雨水高度 或者 更新right位置能存的雨水高度。————这里
     *      有一个trike，left和right位置height元素小的那个，xxxxMax的值也小。比如：height[right]<height[left]，
     *      则rightMax<=leftMax，为什么？？？反证法
     *      ③将②中计算的值累加到结果res；并移动height小的那一侧对应的指针。（比如height[left]<height[right]则移
     *      动left）
     *      ④重复①~③过程，直到left==right
     */
    public int trap(int[] height) {
        int res = 0;
        int left = 0,right = height.length-1;
        int leftMax = 0,rightMax = 0;
        while (left<right){ /*这里带等于也是可以的，为什么呢？答案见下面的扩展解析即方法trap_kuozhan注释*/
            leftMax = Math.max(height[left],leftMax);
            rightMax = Math.max(height[right],rightMax);
            if (height[left]<height[right]){
                res += (leftMax-height[left]);
                left++; /**这里的left++写在上一行也是可以的，这样的话if块就只有一句话，即“res += (leftMax-height[left++]);”，亲测可行。下面的right--也是一个道理*/
            }else{
                res += (rightMax-height[right]);
                right--;
            }
        }
        return res;
    }

    /**
     * 【接雨水问题的扩展】
     *1. 下面的代码本质上最后在出while循环的时候l是等于r的，此时他们指向的是从左边开始遇到的第一个最大值
     *  位置，举个例子，比如："4,2,0,9,8,7,8,3,9,2,5"，则最后l==r，都是3，因为数组中最大的数是9，并且
     *  第一个9对应的索引是3.
     *2. 基于1就可以发现为什么在42题中，使用"while(l<=r)"是可行的。因为不论哪一个指针更新到这里，都是最
     *  大的，因此发现更新后的xMax和height[index]是相等的，导致"res += (rMax-height[r])"或者"res += (lMax-height[l]);"其实
     *  加了个寂寞，加的值是0，因此带等于的条件判断是可以的
     *3. 同理，如果代码中的while循环变为下面的代码：
     *          while (l<r){
     *             if (height[l]<height[r]){
     *                 l++;
     *             }else {
     *                 r--;
     *             }
     *         }
     *   则出了while循环l和r指向的是：从右边开始向左遍历的过程中，遇到的第一个最大值对应的索引
     *4. 基于上述的扩展，同样也可以在数组中查找出不同方向遍历过程中，遇到的第一个最小值对应的位置。
     */
    public int trap_kuozhan(int[] height) {
        int l = 0,r = height.length-1;
        int lMax = 0,rMax = 0;
        int res = 0;
        while (l<r){
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        List<List<Integer>> lists = new _02DoublePoint().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }
}