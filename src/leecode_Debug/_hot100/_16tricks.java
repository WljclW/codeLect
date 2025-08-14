package leecode_Debug._hot100;

import java.util.Arrays;

/**
 * 25.4.22
 * */
public class _16tricks {
    /*136.
    * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均
    * 出现两次。找出那个只出现了一次的元素。
    你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。*/
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i:nums){
            res ^= i;
        }
        return res;
    }

    /*169.
    题解：https://leetcode.cn/problems/majority-element/solutions/2362000/169-duo-shu-yuan-su-mo-er-tou-piao-qing-ledrh/
    * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现
    * 次数 大于 ⌊ n/2 ⌋ 的元素。
    你可以假设数组是非空的，并且给定的数组总是存在多数元素。*/
    /**
     * 整体的思想：
     *      假设数组中的数进行投票，每一个数都投给自己一票，不是自己的话就-1，则最终多数元素的票数绝对是正值。
     * 解题关键：
     *      每一次碰到票数是0，就更新标记值（候选值），然后针对这个数投票
     */
    public int majorityElement(int[] nums) {
        int total = 0; //标记当前的票数
        int flag = -1; //标记值，初始值无所谓
        for (int i : nums) {
            if (total == 0) { //如果票数为0，就先更新标记值。。。total每次到0，说明一部分数已经抵消掉了
                flag = i;
            }
            total = flag == i ? total + 1 : total - 1; //票数不为0则投票，只要不是标记值，票数-1
        }
        return flag;
    }

    /*75.
    * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使
    * 得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    必须在不使用库内置的 sort 函数的情况下解决这个问题。*/
    /*
    * 【注意】cur位置小于1的时候，left和cur指针都需要++。。。否则会报错，如下：
    *       输入
            nums =
            [2,0,2,1,1,0]
            输出
            [1,1,2,2,0,0]
            预期结果
            [0,0,1,1,2,2]
    * */
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        int cur = 0;
        while (cur <= right) {
            if (nums[cur] < 1) { /*如果是0，交换到left指向位置，*/
                swap(nums, left++, cur++); /**err：注意，这种情况cur也得++，因为此时cur位置的值要麽是0要麽是1，left的位置要么是0要么是1，这两个位置的值不可能是2*/
            } else if (nums[cur] == 1) {
                cur++;
            } else { //交换后cur交换来的数还没有比较，所以cur指针不能动!!!
                swap(nums, right--, cur);
            }
        }
    }
    public void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
    /**
     *【注意】这个题要求不能修改数组
     *【总结】关于数组中重复数的题目————
     *      ①136：只出现一次的数字：使用异或运算，出现两次的数异或为0.
     *      ②442：标记num应该出现的位置，因为都是正数，此时将对应的位置|num|-1标记为负数，说
     *  明这个位置已经有对应的数了，如果下次哪个数对应的位置时负数，说明这个数就重复了
     *      ③287：只有一个重复的数，但是不允许修改数组元素，因此使用的是“foleyd判圈”算法
     * */
    /*
      解法1：快慢指针的解法
     *【快慢指针（Floyd 判圈法）】把数组中的每一个数看作是链表的next指针。
     */
    public int findDuplicate(int[] nums) {
        int slow = 0,fast = 0;
        /*step1：类似于环形链表找环。区别————
        *   区别1：链表通过fast.next.next实现fast一次走两步；数组这里需要通过nums[nums[fast]]实现一次走两步。
        *   区别2：由于链表可以使用“while(fast!=null&&fast.next!=null)”作为while条件，因此在链表中可以使用
        * “while(){}”即while循环结构；但是数组这里没有明确的终止条件，因此只能使用“do{}while();”这种循环方式。
        *   共同点：链表中的while是slow、fast先走才判断是不是相等；数组这里do{}while()会保证循环体至少执行一次
        * 才进行判断，因此虽然使用的结构不一样，但是本质内容是一样的。————其实这种现象的本质原因是因为“slow、fast指
        * 针开始的位置是一样的”，如果不先走直接判断就错了。*/
        do{ /**do{}while();这样的循环方法，循环体至少会执行一次。因为第一次执行完才会判断while条件*/
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow!=fast);
        /*step2：跟链表一样，重置其中的一个指针为初始位置。然后每个指针每一步都只走一步，slow、fast相等时即为所求*/
        slow = 0;
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    /* chatgpt给出的是下面的这种形式，有什么区别？
     * 疑问？最开始slow和fast的初始值，是不是必须和阶段2中slow的重置值一样，如果不一样是不
     *      是就错了.
     *      答：对，确实是这样的。为什么？？
     * */
    public int findDuplicate_chatgpt(int[] nums) {
        // 阶段 1：快慢指针找相遇点
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 阶段 2：找到入口点（重复数）
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    /*解法2：二分法。但有几点需要说明
    *   1. 特殊之处：二分法分的是值域，而不是索引位置
    *   2. 思路解析：我们不是对数组下标/元素位置二分，而是对取值范围 [1, n] 二分。
                定义谓词（判断条件）：
                    P(x) := 数组中 ≤ x 的元素个数 > x
                直觉：如果 ≤ x 的数比“理论最多”还多，那重复的数一定落在 [1, x]（抽屉原理）。若 count(≤x) > x，说明多出
             来的那一次来自某个 ≤x 的重复数。所以我们是在找使得 P(x) 为真的最小的 x（lower bound）。这就是重复数。
        3.
    */
    public int findDuplicate_erfen_02(int[] nums) {
        int left = 1, right = nums.length - 1; // [1, n]
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int count = 0;              // 统计 ≤ mid 的个数
            for (int v : nums) if (v <= mid) count++;

            if (count > mid) {          // P(mid) 为真 → 重复数在 [left, mid]
                ans = mid;              // 记录当前满足的位置
                right = mid - 1;        // 缩到左半边，继续找“第一个为真”
            } else {                    // P(mid) 为假 → 在右半边
                left = mid + 1;
            }
        }
        return ans; // 最后 ans 就是最小使 P(x) 为真的 x，也就是重复值 d
    }

    public int findDuplicate_erfen_01(int[] nums) {
        int l = 1, r = nums.length - 1;     // [1, n]
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cnt = 0;
            for (int v : nums) if (v <= mid) cnt++;
            if (cnt > mid) r = mid;         // P(mid) 真，答案在 [l, mid]
            else l = mid + 1;               // 假，在 [mid+1, r]
        }
        return l;                           // l==r 即为最小使 P(x) 为真的点
    }





    /*31.下一个排列*/
    /**
     * 【强烈建议】使用官方解，见方法nextPermutation_offical
     * 【如果有下一个更大的排列，则做法分为三步】
     *      1. 从最后一个数倒着找，找到第一次升序的位置。即数组元素满足nums[i]<nums[i+1]。【注
     * 意】这里必须找出严格小于的，等于的话不行！！
     *      2. 从最后一个数倒着找，找第一个大于nums[i]的数。。【注意】这里一定会找到，因为
     *  由第一步可以知道最起码nums[i+1]是大于nums[i]的。
     *      3. 倒序"从i+1位置往后的数"。。。【注意】此时i+1往后所有数一定是降序的，因为根据
     *  第一步原来就是降序的，然后第2步将一个数换成了比它小但比后面数大的nums[i]，因此此时还
     *  是严格降序的
     *  【如果没有下一个更大的排列，说明原始数组就是最大的排列，因此逆序整个数组】
     *
     * */
    public void nextPermutation(int[] nums) {
        int i = nums.length-2;
        while (i>=0){
            /*step1：先是从后面开始找到第一个升序的位置。。【即满足nums[i]<nums[i+1】*/
            if (nums[i]<nums[i+1]){
                /*step2：再从后面开始找第一个大于nums[i]的数，并交换。【一定会找到，因为至少nums[i+1]>nums[i]】*/
                for (int j=nums.length-1;j>=i+1;j--){
                    if (nums[j]>nums[i]){
                        int tmp = nums[j];
                        nums[j]= nums[i];
                        nums[i] = tmp;
                        break;
                    }
                }
                /*step3：将从i+1索引开始的子数组倒序*/
                reverse(nums,i+1);
                break;
            }
            i--; /**err：更新变量i*/
        }
        /*step4：如果原数组是严格降序的，说明此时就是最大的序列。。要改变为最小的序列————将原数组倒序*/
        if (i==-1)
            reverse(nums,0);
    }

    public void reverse(int[] nums,int l) {
        int r = nums.length - 1;
        while (l < r) {
            int tmp = nums[r];
            nums[r--] = nums[l];
            nums[l++] = tmp;
        }
    }

    /**下一个排列的官方写法。。。官方的写法比较优雅且清晰。。
     * 注意区别nextPermutation_offical写法 和 nextPermutation_another写法：
     *      ①nextPermutation_offical方法中flag变量标记的是倒着数第一个降序位置，比如初始数组如果
     * 是"[7,4,5,6,3,2,1]"，则最后flag会为2，即指向nums[2]=5。最后倒序需要从index=3开始————即从
     * flag+1的位置开始倒序。
     *      ②nextPermutation_another方法中flag变量标记的是倒着数第一个降序位置的后一个位置，比如
     * 初始数组如果是"[7,4,5,6,3,2,1]"，则最后flag会为3，即指向nums[3]=6。最后倒序需要从index=3
     * 开始————即flag的位置
     *      因此两种写法会有区别！！*/
    public void nextPermutation_offical(int[] nums) {
        int flag = nums.length - 2;
        //step1：只要不是严格升序，就不断的向前找。最后来到第一个严格升序的位置
        while (flag >= 0 && nums[flag] >= nums[flag + 1]) { //只要不满足要求，就执行j--。。while循环结束就找到了第一个严格升序的位置
            flag--;
        }
        //step2，说明确实找到了升序的位置
        if (flag >= 0) {
            int j = nums.length - 1;
            //倒着找第一个nums[j]大于nums[i]的位置
            while (j >= 0 && nums[flag] >= nums[j]) {
                j--;
            }
            swap1(nums, flag, j);
        }
        //step3：倒序flag+1开始的子数组。。并且还包含了特殊情况：整个数组已经是最大的排列了,直接翻转整个数组
        reverse(nums, flag + 1);
    }

    public void swap1(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*下面的解法中flag变量标记的是————"正向看第一个升序的位置"*/
    public void nextPermutation_another(int[] nums) {
        int flag = nums.length-1;
        while(flag>0&&nums[flag-1]>=nums[flag]){
            flag--;
        }

        if (flag>0){
            int j = nums.length-1;
            while(j>flag-1&&nums[j]<=nums[flag-1]){
                j--;
            }
            int tmp = nums[flag-1];
            nums[flag-1] = nums[j];
            nums[j] = tmp;
        }
        reverse_another(nums,flag);
    }

    private void reverse_another(int[] nums, int i) {
        int left = i,right = nums.length-1;
        while(left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }


    /*下一个排列自己的写法*/
    /**
     * 结合官方解写法 和 自己的写法 可以发现：
     *      如果想做这样的事：“在数组中查找满足指定条件的第一个位置就终止”。做这种事有两种角度—————
     *          角度1：从满足条件的角度考虑。遍历时如果满足条件则用额外的变量来记录位置；
     *          角度2：从不满足条件的角度考虑。”while(不满足条件) 循环变量--“。。。更推荐这种写法，这种写法
     *      while结束时循环变量就来到第一个满足的位置
     */
    public void nextPermutation_myMethod(int[] nums) {
        int flag = nums.length; /**【注意】这里尽量不要取Integer.MAX_VALUE!!!*/
        /*step1：从后面开始找到第一个降序的位置，即nums[i]<nums[i+1]*/
        for (int i=nums.length-2;i>=0;i--){
            if (nums[i]<nums[i+1]){
                flag = i;
                break;  /**err：找到第一个就返回*/
            }
        }
        /*如果数组整体是倒序的，即flag的值没有得到更新*/
        if (flag==nums.length){ //flag没有更新说明原来的排列就是最大的排列，需要整体逆序
            reverse1(nums,0,nums.length-1);
//            return;  //【注】如果flag初始值取得是Integer.MAX_VALUE，则这里必须加return。
        }

        /*step2：倒着找第一个大于升序位置数nums[flag]的数nums[i]，并交换；交换后把flag（不包含
            flag）之后的所有数逆序。*/
        for (int i=nums.length-1;i>=flag;i--){
            if (nums[i]>nums[flag]){
                int tmp = nums[flag];
                nums[flag] = nums[i];
                nums[i] = tmp;
                reverse1(nums,flag+1,nums.length-1);
                break;  /**err：【注意】找到第一个大于的操作，完成后直接返回*/
            }

        }
    }

    private void reverse1(int[] nums, int l, int r) {
        while(l<r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }


    /**下一个排列易忽视的点
     * */
    public void nextPermutation_note(int[] nums) {
        int flag = Integer.MAX_VALUE;
        for (int i = nums.length-2; i >=0 ; i--) {
            if (nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }
        if (flag == Integer.MAX_VALUE){
            rever(nums,0,nums.length-1);
            /*因为flag的初始值是Integer.MAX_VALUE，因此这里必须加return语句。如果不加后果就是————
            *       下面的for循环不会执行，因为"i >=flag"是不成立的；
            *       但是“rever(nums,flag+1,nums.length-1);”就有问题了，flag的初始值是“Integer.MAX_VALUE”，
            *   但是“flag+1”就变成了Integer.MIN_VALUE，因此导致索引变成了负数。
            *       综上，如果flag初始值是Integer.MAX_VALUE，这里就必须加return；如果初始值是nums.length，这
            *   里不用加return就可以。*/
            return;
        }

        for (int i = nums.length-1; i >=flag ; i--) {
            if (nums[i]>nums[flag]){
                int tmp = nums[i];
                nums[i] = nums[flag];
                nums[flag] = tmp;
                break;
            }
        }
        rever(nums,flag+1,nums.length-1);
    }

    private void rever(int[] nums, int l, int r) {
        while (l<r){
            int tmp  =nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            r--;
            l++;
        }
    }



    public static void main(String[] args) {
//        int[] arr = {0,0,0,2,0,2,2,0};
//        new _16tricks().sortColors(arr);
//        System.out.println(Arrays.toString(arr));
//
//        int[] par = {1, 2, 3, 2};
//        int duplicate = new _16tricks().findDuplicate(par);
//        System.out.println(duplicate);


        int[] a = new int[]{3,2,1};
        new _16tricks().nextPermutation(a);
    }
}
