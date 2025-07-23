package leecode_Debug._hot100;

public class _10binarySearch {
    /*35.
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    请必须使用时间复杂度为 O(log n) 的算法。
    * */
    /**
     * 【题目的实质】查找某一个数应该插在排序数组的什么位置。
     * 用"5，7，7，8，8，10"来捋过程
     * 下面是关于闭区间的理解————
     * 首先明确一点：闭区间的写法中，出循环时l一定等于r+1，因此返回值写l和r+1是等效的！！
     * 【建议的解法】看searchInsert_01
     * 【关键!!】题目的本质是(左边界问题)：查找给定元素第一次出现的位置(如果数组中有的话)
     *                或者  查找大于目标元素的第一个数的位置(如果数组没有给定的元素)
     * 【进一步理解】这个题是寻找目标值左边界的方法（注意：理论上这个题求的是目标值target应该插入到什么地方。并且如果数组中有多个目标值
     *      的话，应该返回第一次出现的位置）。。。详细的说，所有情况分为三种：
     *          情况1：如果目标值是在数组的最大值最小值之间(闭区间)，返回r+1或者l都可以。【注】返回的数也不一定就等于目标值，可能是大于
     *      目标值的第一个位置。
     *          情况2：如果目标值严格大于数组的最大值，则最后l会来到nums.length的位置，这个位置很明显越界了。此时说明数组中肯定没有目标
     *      值。
     *          情况3：如果目标值严格小于数组的最小值，则最后l会来到0的位置，这个位置不越界。【注】但是因为target小于数组的最小值，因此
     *      0位置的数很明显不等于目标值。
     *          问题：情况2和情况3都是严格的大于小于，那如果目标值等于数组的最大值 或者 等于数组的最小值呢？
     *            答：其实这种情况跟情况1是一样的。具体来说分为四种情况————
     *               ①如果等于数组最小值，且最小值唯一。则l指针会指向0；
     *               ②如果等于数组最小值，且最小值不唯一。则l指针会指向0；
     *               ③如果等于数组最大值，且最大值唯一。则l会指向nums.length-1;
     *               ④如果等于数组最大值，且最大值不唯一。则l指针会指向第一个最大值所在的位置
     *               虽然分为以上四种情况，但是总结起来就一句话：指向目标值第一次出现的位置
     * 【说明】
     *      1. 二分查找有很多个版本，以后就使用“左闭右闭”的写法
     *      2. 这个题不同区间的写法建议看"https://leetcode.cn/problems/search-insert-position/solutions/2023391/er-fen-cha-zhao-zong-shi-xie-bu-dui-yi-g-nq23/?envType=study-plan-v2&envId=top-100-liked"
     * */
    public int searchInsert(int[] nums, int target) {
        int l =0,r = nums.length-1;
        while (l<=r){
            int mid = l+((r-l)>>1);
            /*情况1：如果找到目标元素，可能并不是第一个位置，因此移动r指针*/
            if (nums[mid]==target){ /**err：由于要找第一个大于等于target的位置，因此相等时还不能返回，要让右边界r左移*/
                r = mid-1;
//                return mid;
            }else if (nums[mid]>target){ /*情况2：如果发现mid对应的数比目标数大，说明在左边，更新r为mid-1*/
                r = mid-1;
            }else { /*情况3：如果发现mid对应的数比目标数小，说明在右边，更新l为mid+1*/
                l = mid+1;
            }
        }
        /*出了循环时，l必然等于r+1。返回l或者r+1均可*/
        /**err：为什么返回l(这里返回r+1也是等价的)*/
        return l; //等价于return r+1;
    }

    //下面的写法仅仅是上面写法的合并版本。没有本质的区别，这两种都是闭区间的写法
    public int searchInsert_01(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else { /**因为本质上想求解的是左边界，因此即使nums[mid]与target相等了，right也左移*/
                right = mid - 1;
            }
        }
        return left;
    }

 /**
=====================================================================================
* */

    /*74.
    给你一个满足下述两条属性的 m x n 整数矩阵：
    每行中的整数从左到右按非严格递增顺序排列。
    每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
    * */
    /**
     * 【建议的解法】见方法searchMatrix
     * 【迷茫的点】
     *      1. 建议使用左闭右闭的写法。即开始时left=0,right=nums.length-1;
     *      2. 无论是左闭右开，还是左闭右闭区间的写法。根据mid计算该位置的坐
     *  标方法是一样的，都是[mid/n][mid%n]。————这一点很重要！！
     * */
    //左闭右闭的写法
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int right = m * n - 1, left = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cur = matrix[mid / n][mid % n]; /**err：其实这里就直接取余就可以。比如：一共8列，当前是16索引，应该是第三行的第0个位置，16/8=2因此是第三行，16%8=0因此是索引为0的数*/
            if (cur > target) {
                right = mid - 1; /**err：当前区间包括right位置，mid位置不符合，因此right指向mid-1*/
            } else if (cur < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /*左闭右开的写法。
    * 【说明】由于是左闭右开的，因此每一次的区间是不包括r指针位置的！！基于这一点，每一次移动r指
    * 针时都要注意。比如：如果mid位置的值大于target，则r指针移动到mid即r=mid。此时搜索的区间其
    * 实变成了[l,mid)*/
    public boolean searchMatrix_01(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int right = m * n, left = 0;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int cur = matrix[mid / n][mid % n];
            if (cur < target) {
                left = mid + 1;
            } else if (cur > target) {
                right = mid; /**当前的区间不包括right，且mid位置的值比目标值大，因此right指向mid————但是区间是不包括right位置的*/
            } else {
                return true;
            }
        }
        return false;
    }

/**
 ======================================================================
 * */

    /*34.”第一次和最后一次出现的位置“
    给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
    如果数组中不存在目标值 target，返回 [-1, -1]。
    你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
    * */
    /**
     * 解法1（见方法searchRange）：分别求出左右边界————
     *      区别1：相等时指针的移动。对于nums[mid]>target 以及 nums[mid]<target 的情况，指针的变化
     *  都是一样的，唯一的区别在于nums[mid]==target的时候：
     *          如果是求解target左边界，则nums[mid]==target的时候，需要收缩右边界，因此做操作：right = mid-1;
     *          如果是求解target右边界，则nums[mid]==target的时候，需要收缩左边界，因此做操作：left = mid+1;
     *     区别2：返回值的区别。
     *          如果是求解左边界，则返回值应该是left；如果是求解右边界则返回值应该是right.
     *
     * 解法2（见方法searchRange_01）：求出target的左边界 以及 target+1的左边界.
     *     如果target的左边界对应的数是target，说明一定也有右边界；否则说明数组中没
     * 有target，因此也必然没有右边界。
     *          如果左边界对应的值不是target，说明数组中压根就没有target这个数，因此直接返回[-1,-1]；
     *          如果求得左边界不越界，且数组这个位置的值是target，则再去求target+1的左边界。target+1的
     *     左边界-1就是target的右边界；
     * */
    public int[] searchRange(int[] nums, int target) {
        int left = searchLeftBound(nums,target);
        int right = searchRightBound(nums,target);
        return new int[]{left,right};
    }

    /*排序数组中寻找target第一次出现的位置，没有出现返回-1。
    实际上更透彻的表述是：如果想在数组中插入target(如果数组中有多个target则应该插入到第一个位置)，应该插入到什么位置
    最后l指针即为应该插入的位置
     */
    private int searchLeftBound(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<target){
                l = mid+1;
            }else if (nums[mid]==target){
                r = mid-1;
            }else {
                r=mid-1;
            }
        }
        /**
         * 总结，经过上面的代码后：
         *      如果target在数组出现过，则l指向了数组中target第一次出现的位置；
         *      如果没有出现过：
         *             ①且小于数组的最小值，则l会指向0；
         *             ②且大于数组的最大值，则l会指向nums.length；
         *             ③target介于数组最大值和数组最小值之间，l指向第一个大于等于target的位置
         *      综上所述：l指针指向的实质性含义是：如果我们想把target这个数添加到数组中(如果数组中原来就存在多个等于target的数，则要求放
         * 在第一个)，应该插入到l指向的位置。
         * */
        /*
        【思考】如果数组中有target，找到的左边界的实际值就是target。因此如果最后l指针的值不是target，就说明数组中是没有target的
        l==nums.length || nums[l]!=target表明target并没有出现在数组中。
        "l==nums.length"表示target大于数组的最大值(即比数组所有元素都大)，因此应该在数组的最后添加；（形象理解：想插的位置是在数组
            的末尾，说明数组的元素都比他小，它只能添加到末尾）
        "nums[l]!=target"表示target小于数组的最大值，但是没有出现在数组中；（形象理解：l是应该插入的位置，但是发现这个位置的数并不
            为target，意味着数组中也没有target。比如”[5，7，7，8，8，10]“，target=9时左边界返回的l应该是5，5位置的数其实是10，但
            是如果想插入9，就应该添加在索引为5的位置）
        **/
        if (l==nums.length || nums[l]!=target){
            return -1;
        }
        return l;
    }

    /*寻找target在数组中最后一次出现的位置，如果没有出现过返回-1。
    实际上更透彻的表述是：如果想在数组插入target(如果有多个则应该插入到最后)，应该插入到什么位置！！！
    最后l指针即为应该插入的位置！！！
     */
    private int searchRightBound(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<target){
                l = mid+1;
            }else if (nums[mid]>target){
                r=mid-1;
            }else {
                l = mid+1;
            }
        }
        /**
         * 总结：经过上面的代码后：
         *      如果target在数组中出现过，则l会来到大于target数的第一个位置，r来到target的最后一个位置；
         *      如果target在数组中没有出现过：
         *             ①且target大于数组的最大值，则l会来到nums.length
         *             ②且target小于数组的最小值，则l会来到0
         *             ③target位于数组的最小值和最大值之间，则l会指向第一个大于target这个数的位置
         *      综上所述，l指向位置的实质含义是：如果我们想要把target插入到数组中(如果数组中有等于target的数，则需要将新插入的数放在这一
         * 堆相等的最后面)，则应该插入到l指向的位置。
         * */
        /*上面的代码的求左边界相比，唯一的区别就是相等的时候l右移。。。重点是下面特殊值的判断。
        l==0说明target比数组的最小值还小，即小于数组的所有数。（形象理解：我们想在最右边添加target，结果找到的）
        * */
        if (l==0 || nums[l-1]!=target){
            return -1;
        }
        return r;
    }




    /**解法2————求两次左边界，分别求target和target+1的左边界,其中target+1的左边界-1就是target的右边界
     * 【分为三步】
     *      step1：寻找target数字的左边界(其实这个左边界是target应该插入的位置)。
     *      step2：验证step1中找到的位置是不是索引越界？该位置的数是不是等于target？为什么可能出现这两种情况，见35题的
     * 注释（方法searchInsert）。
     *      step3：如果step2验证是符合的，则直接返回[-1,-1]————说明数组中压根就没有target这个数；否则说明找到的左边界
     * 的数就是target，表明数组中肯定存在target（只要存在target这个数，就一定存在这个数的右边界）。做法：继续寻找
     * 出target+1的左边界，target+1的左边界-1就是target的右边界（因为数组中的数都是int类型）。
     */
    public int[] searchRange_01(int[] nums, int target) {
        int left = searchLeft(nums,target); //step1：找target的左边界
        if (left==nums.length || nums[left]!=target){ //step2：验证左边界是不是越界？左边界的数是不是target？？
            return new int[]{-1,-1}; //说明数组中压根就没有target这个数
        }
        int right = searchLeft(nums,target+1); //step3：求target+1的左边界，该左边界-1即为target的右边界
        return new int[]{left,right-1}; /**【注意】right-1表示求出的target+1的左边界-1，因为right其实返回的是target+1的左边界left，因此target的右边界需要往左*/
    }
    /*【目的】求target在数组中应该插入的位置————如果存在target求出的就是target的左边界
    * 【说明】就是53题的解法，一模一样*/
    private int searchLeft(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]>target){
                r = mid-1;
            }else if (nums[mid]==target){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return l;
    }


    /*
        跟方法searchRange大体上是一样的，只不过判断是不是有target的操作放在searchL方法中了，并且
    根据searchL的返回值如果判断没有target就直接返回了。
    * */
    public int[] searchRange_02(int[] nums, int target) {
        int left = searchL(nums, target);
        if (left==nums.length||nums[left]!=target){
            return new int[]{-1,-1};
        }
        int right = searchR(nums, target);
        return new int[]{left,right};
    }

    private int searchL(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<target){
                l = mid+1;
            }else{ /**求左右边界的区别1：nums[mid]==target时那个指针移动？怎么移动？*/
                r = mid-1;
            }
        }
        return l; /**求左右边界的区别2：返回哪一个指针*/
    }

    private int searchR(int[] nums, int target) {
        int l =0,r = nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]>target){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return r;
    }



    /*33.....81是扩展（允许有重复元素）
    整数数组 nums 按升序排列，数组中的值 互不相同 。
在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    * */
    /**
     * 【关键】理解二分查找的本质：是能根据一个条件排除一半的区间，每一步我们都知道下一步应该去哪一半查找
     * 【注意区别】二分的本质时每一次二分后排除一个区间，在另一个区间内继续二分！！！！
     *      在"旋转数组中寻找target"中，每一步的nums[mid]需要和nums[0]比较。只要nums[mid]>=nums[0]说明
     *  mid位置的左边一定是有序的（不论数组是整体有序还是分两段有序，mid的左边一定是有序的）；否则的话即
     *  nums[mid]<nums[0]说明数组必然是两段，并且mid位置一定是在第二段中，且mid的右边一定是有序的！！
     *      在"寻找旋转数组最小值"中，每一步的nums[mid]需要和nums[nums.length-1]比较。只要nums[mid]>=
     *  nums[nums.length-1]，则数组必然分为两半有序并且mid是在前一段中，此时最小值位置一定是在mid的右边
     *  并且不包括mid位置；否则nums[mid]<nums[nums.length-1]的时候数组可能是整体有序也可能是分两段有序，
     *  但是最小值的位置一定是在mid位置的左边，但包含mid位置（此时可以额外变量记录一下mid，也可以看官方解）
     * 【解题的核心】对于任意一个index，其左区间和右区间至少有一个是有序的，那么就可以根据这个有序区间的
     *      最大值和最小值来判断Target是否在该区间内(不再该区间就自然在另外一段内了)————确定了接下来去哪
     *      一半查找。
     * 【此题的解题思路】
     *      step1：基于二分查找的本质，我们需要先确定哪一边是有序的。如何确定？nums[mid]和nums[0]比较，
     *  如果大于等于nums[0]说明左边是有序的（外层if干的事）；否则说明右边是有序的（外层的else干的事）；
     *      step2：step1中确定了两边有一边是有序的。在这个基础上我们需要进一步判断去哪一边继续查找，如
     *  何确定？？利用有序的一边来确定。【思想就是：判断target是不是在有序部分边界值的区间内】
     *          比如右边有序则右边值的区间(nums[mid],nums[r]]，左开右闭，如果target在这个区间就往右
     *       边的那部分跑，因此l=mid+1；
     *          比如左边有序则左边值的区间是[nums[0],nums[mid])，左闭右开，如果target在这个区间就往
     *       左边的那部分找，因此r=mid-1。
     * */
    /*
    ==================错误点1
    输入
        nums =
        [3,1]
        target =
        1
    输出
        -1
    预期结果
        1
        原因：“if (nums[mid] >= nums[0])”说明左边是有序的，不能漏掉等于。
    * */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            //step1：①nums[mid]与nums[0]判断，可以知道哪边是有序的；②看看target是不是在有序的一边。。详细解释见if内的的注释
            if (nums[mid] >= nums[0]) { /**🔺err：这里必须带等于。不带的话“[3,1]”这个测试用例过不了*/
                /*内部继续比较时就是利用有序的一部分看看target是不是在这个范围，并且target和nums[mid]比较时
                不用带等于，因为"nums[mid]==target"在进入if之前已经判断过了；但是target和nums[0]是可能相等
                的！！else中的情况也同理，在else中target可能和nums[nums.length-1]是相等的。
                因此这里if条件说人话就是“nums[0]≤target<nums[mid]”，并且mid的那个边界之所以不带等于是因为
                进入if之前已经判断过了。
                 */
                if (target >= nums[0] && target < nums[mid]) { /*【说明】条件语句实际上就是“nums[0]<=target<nums[mid]”，表明target位于有序的那一边*/
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { //else说明右边是有序的
                if (target <= nums[nums.length - 1] && target > nums[mid]) { /**右边有序，看看target是不是在右边！注意等于*/
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1; /**err：说明没有找到返回-1.*/
    }


    /*153....154是这个的拓展（允许有重复元素）
    返回最小元素的值
    已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    * */
    /**
     * 【解题关键】
     *      1. 必须是和数组中的最后一个元素比！！！！原因：比之后能知道 应该向左还是向右继续寻
     * 找，才能找到最小值。。即和最后一个元素比能把搜寻区间减半。解释：
     *          ①如果nums[mid]大于等于最后一个数，则这个数组一定是分为两段有序，并且最小值是在
     *      mid的右边，因此这种情况下更新left为mid+1；
     *          ②如果nums[mid]小于最后一个数，则这个数组不一定是分为一段还是两段。但是最小值一
     *      定是在mid往左的区域中（包含mid位置），因此这种情况下更新right为mid或者mid+1——此时
     *      有两种方案。
     *
     * 【推荐解法】解析的话就看官方的
     *      1. 写法的话推荐解法1。————多使用一个变量，存储遇到过的最小值，这样的写法与"寻找插入位置"题目的代码类似
     *      2.
     * */
    /*解法1：建议使用的(因为right和left指针的变化规律与常规一致)。
    *    两种解法最大的区别在于：当找到小于的数时，右边界right更新到哪里的问题！
    *       解法1中会把right更新为mid-1，但是此时可能错过最小值，mid位置可能就是最小值。。因此使用一个变量记录；
    *       解法2中right不会更新为mid-1（防止错过最小值），但是循环的条件就不能带“=”了（带等于会导致死循环）*/
    public int findMin(int[] nums) {
        // 1.min初始值为第一段升序数组的最小值，而且目前不知道数组是有两段升序还是只有一段升序
        /**err【重要】：使用一个变量标记碰到的最小值。。
         * 并且min必须初始化为nums[nums.length-1]，如果初始化为nums[0]，则"[2,1]"这个测试用例过不了*/
        int min = nums[nums.length-1];

        // 2.先正常二分查找
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;

            // 3.如果中间位置比min小，那么这个mid位置一定在第二段升序数组中，那么最小值一定在mid或者它的左边，这是因为每段都是升序的
            // 先更新min，然后向左边遍历
            if(nums[mid] < min){ /**err：（由于数组不存在重复数，这里带不带等于都可以）小于min的时候，可能当前的数nums[mid]就是最小的数，使用min变量记录*/
                min = nums[mid];
                right = mid - 1;
            }
            // 4. 如果中间位置比min大，那么mid位置一定在第一段升序部分 并且 此时数组必然是分两段有序，因为第
            // 二段升序的最大值也要小于第一段升序的最小值所以直接向mid的右边遍历————更新左边界
            else {
                left = mid + 1;
            }
        }
        return nums[min]; /**▲err：题目要求返回最小值，而不是最小值的位置！！！*/
    }

    /*
    解法2：就是当mid数小于最后一个数时,right指针更新为mid，而不是mid-1。
     */
    public int findMin_01(int[] nums) {
        int left = 0,right = nums.length-1;
        /*
            1. 这里必须是小于，因为一旦等于的时候就只有一个数了，其实这个数就是最小值。
            2. 如果这里写的是“小于等于”，则对于[5,8,9,10]这个数组，运行时会出现死循环。想象一种场景：l和r都指向某一个
         位置m，并且这个数小于数组的最后一个数，则按照代码逻辑“right=mid”，执行后l指向m，r也指向m.....由于l永远不大于
         r，因此成死循环。。所以求最小值的时候，这里必须是“left<right”
            ————死循环的本质原因在于：最后相等的时候，left指针和right指针是一样的；并且这个数小于nums[nums.length-1]，
         逻辑上需要更新right，但是right更新为mid，因此更新后left==right依旧成立。
        * */
        while(left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]>nums[nums.length-1]){
                left = mid+1;
            }else{
                right = mid; /*此时mid位置的值可能就是最小值。并且是闭区间，因此right更新为mid而不是mid-1*/
            }
        }
        return nums[right];
    }



    /*4.
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
    * */
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//    }

    public static void main(String[] args) {
        _10binarySearch thisClass = new _10binarySearch();
//        thisClass.searchInsert(new int[]{1,3,5,6},5);

        thisClass.search(new int[]{3,1},1);
    }

}
