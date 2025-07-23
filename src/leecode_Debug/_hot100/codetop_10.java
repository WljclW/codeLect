package leecode_Debug._hot100;

import leecode_Debug.linkList.ListNode;
import leecode_Debug.top100.TreeNode;

import javax.sound.sampled.Line;
import java.lang.reflect.Array;
import java.util.*;

public class codetop_10 {
    /*297
    序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在
    一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原
    数据。
请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
    你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格
    式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
    * */
    class Codec {

        // Encodes a tree to a single string.
//        public String serialize(TreeNode root) {
//
//        }

        // Decodes your encoded data to tree.
//        public TreeNode deserialize(String data) {
//
//        }
    }



    /*47
    给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     */
    List<List<Integer>> resPermuteUnique;
    List<Integer> pathPermuteUnique;
    boolean[] flag;
    public List<List<Integer>> permuteUnique(int[] nums) {
        resPermuteUnique = new LinkedList<>();
        pathPermuteUnique = new LinkedList<>();
        flag = new boolean[nums.length];
        Arrays.sort(nums);
        back(nums);
        return resPermuteUnique;
    }

    private void back(int[] nums) {
        if (pathPermuteUnique.size()==nums.length){
            resPermuteUnique.add(new LinkedList<>(pathPermuteUnique));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!flag[i]){
                if (i>0&&!flag[i-1]&&nums[i]==nums[i-1]) continue;
                flag[i] = true;
                pathPermuteUnique.add(nums[i]);
                back(nums);
                flag[i] = false;
                pathPermuteUnique.remove(pathPermuteUnique.size()-1);
            }
        }
    }

    /*402
    给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字
    符串形式返回这个最小的数字。
    * */

    /**
     *【思路】先依照最小栈的原则遍历一次num，每次删除字符维护k的值；如果k>0，则从栈的末尾删k个字符。。。最后栈中的字符组成字符串返回。
     *【关键】这个题虽然用到了单调栈，但是由于最后拼接字符串需要从栈底开始拿字符，因此实际上最好不用使用Stack类，而是使用LinkedList，
     *      把LinkedList当作双端队列使用
     */
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        /*step1：维持一个最小栈————只要当前的数比栈顶的数小，栈顶的数就出栈*/
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && c<stack.peekLast()) { /**err：这里直接入栈的是字符，而不是索引，因此比较的是"c<stack.peekLast()"*/
                stack.pollLast();
                k--;
            }
            stack.offerLast(c);
        }
        /*step2：剩下的是单调递增的。倒着删除剩余的k位*/
        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }
        /*step3：使用StringBuilder手机结果并返回*/
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            if (sb.length() == 0 && c == '0') continue;
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /*下面是自己的写法，自己的写法细节太多了，很容易错*/
    public String removeKdigits_own(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty()&&c-'0'<stack.peekLast()-'0'&&k>0){
                k--;
                stack.pollLast();
            }
            stack.offerLast(c);
        }

        while (k>0){
            k--;
            stack.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            Character c = stack.pollFirst();
            if (sb.length()==0&&c=='0') continue;
            sb.append(c);
        }
        return sb.toString()==""?"0":sb.toString();
    }



    /*LCR 170. 交易逆序对的总数
    在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。请设计一个程序，输入一段时
    间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
    * */
//    public int reversePairs(int[] record) {
//
//    }

    /*40
    给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的每个数字在每个组合中只能使用 一次 。
    注意：解集不能包含重复的组合。
     */
    List<List<Integer>> resCombinationSum2;
    List<Integer> pathCombinationSum2;
    boolean[] used;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        resCombinationSum2 = new LinkedList<>();
        pathCombinationSum2 = new LinkedList<>();
        Arrays.sort(candidates);
        used = new boolean[candidates.length];
        combinationSum2Back(candidates,target,0);
        return resCombinationSum2;
    }

    private void combinationSum2Back(int[] candidates, int target, int index) {
        if (target==0){
            resCombinationSum2.add(new LinkedList<>(pathCombinationSum2));
        }
        if (target<0 || index==candidates.length){
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i>0&&candidates[i]==candidates[i-1]&&!used[i]){
                continue;
            }
            pathCombinationSum2.add(candidates[i]);
            used[i] = true;
            target -= candidates[i];
            combinationSum2Back(candidates,target,i+1);
            pathCombinationSum2.remove(pathCombinationSum2.size()-1);
            used[i] = false;
            target += candidates[i];
        }
    }

    /*61
    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
//    public ListNode rotateRight(ListNode head, int k) {
//
//    }

    /*114
    给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */
    public void flatten(TreeNode root) {
        if (root==null) return;
        TreeNode cur = null; /*声明cur节点，将所有的节点串起来*/
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode nowNode = stack.pop();
            if (cur==null)  /*cur如果是null说明是第一个节点，将cur指向根节点*/
                cur = nowNode;
            else{   /*否则的话将节点拼接到right指针，然后cur指针右移*/
                cur.left = null;
                cur.right = nowNode;
                cur = cur.right;        /**err：更新cur指针*/
            }
            if (nowNode.right!=null) stack.push(nowNode.right);
            if (nowNode.left!=null) stack.push(nowNode.left);
        }
    }


    /*958
    给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。

在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
     */
//    public boolean isCompleteTree(TreeNode root) {
//
//    }

    /*LCR 155. 将二叉搜索树转化为排序的双向链表
    将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     */
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
//    public Node treeToDoublyList(Node root) {
//
//    }

    /*
    16. 最接近的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        int res = 0,max = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            int l = i+1,r = nums.length-1;
            while (l<r){
                int curSum = nums[i]+nums[l]+nums[r];
                if (Math.abs(curSum-target)<max){ /*如果curSum和target更接近则更新最后返回的结果res*/
                    max = Math.abs(curSum-target);
                    res = curSum;
                    if (res==target) return res;
                }
                if (curSum>target){
                    r--;
                }else{
                    l++;
                }
            }
        }
        return res;
    }

    /*LCR 143. 子结构判断
    给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
注意，空树 不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
     */
//    public boolean isSubStructure(TreeNode A, TreeNode B) {
//
//    }


    /*145
    给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
    * */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        /**这个LinkedList要充当栈的作用，因此建议直接将变量名改为stack；并且使用方法时注意：
         *      ①如果想添加元素使用push()；————使用offer是错误的！
         *      ②如果想弹出元素使用pop()；*/
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (root==null) return res;
        treeNodes.push(root);
        while (!treeNodes.isEmpty()){
            TreeNode cur = treeNodes.pop();
            res.add(cur.val);
            if (cur.left!=null){
                treeNodes.push(cur.left);
            }
            if (cur.right!=null){
                treeNodes.push(cur.right);
            }
        }

        Collections.reverse(res); /**err：将res的顺序反序用此方法."Collections.reverse原地翻转collection"*/
        /**使用下面的代码进行反序时，会超时。。。
         * 【注意】下面的方法翻转链表是错误的！！！因为”res.add(i,right);“是在特定的位置插入一个值，但是没有删除之前i位置的值，因此
         *      会导致链表不断的变长
         * */
        // for (int i = 0; i < res.size()/2; i++) {
        //     Integer left = res.get(i);
        //     Integer right = res.get(res.size() - 1 - i);
        //     res.add(i,right);
        //     res.add(res.size() - 1 - i,left);
        // }
        return res;
    }


    /*572
    给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。

二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
    * */
//    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//
//    }


    /*328
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */
    /**
     *【思路】奇数位置、偶数位置使用不同的头节点
     */
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return head;
        ListNode evenHead = head.next,even = evenHead;
        ListNode odd = head;
        while (even!=null&&even.next!=null){
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /*另一种写法，区别就在于while循环中*/
    public ListNode oddEvenList_(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode evenHead = head.next, even = evenHead;
        ListNode odd = head;
        while (even != null && even.next != null) { /**注意，while循环内会使用到”even.next.next“，因此while条件需要保证”even.next!=null“*/
            odd.next = odd.next.next;
            odd = odd.next;

            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }


    /*230
    给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
     */
//    public int kthSmallest(TreeNode root, int k) {
//
//    }


    /*135
    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

    你需要按照以下要求，给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */
    /**
     *【思路】两次遍历。
     *      ①一次是从左向右遍历，满足左边的条件。对于index位置，如果index-1的位置值小，则index位置的最小值是index位置的糖果+1.这样
     *  一轮结束可以保证每一个位置左边条件是满足的
     *      ②一次是从右向左遍历，满足右边的条件。对于index位置，如果index+1的位置值小，则index位置的最小值是index+1位置的糖果+1，这
     *  样一轮结束可以保证每一个位置右边条件是满足的
     */
    public int candy(int[] ratings) {
        int[] flags = new int[ratings.length];
        Arrays.fill(flags,1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i]>ratings[i-1]){
                flags[i] = Math.max(flags[i-1]+1, flags[i]);
            }
        }

        for (int i = ratings.length-2; i >=0 ; i--) {
            if (ratings[i]>ratings[i+1]){
                flags[i] = Math.max(flags[i+1]+1,flags[i]);
            }
        }

        int sum = 0;
        for (int num:flags){
            sum += num;
        }
        return sum;
    }


    /*450
    给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
     */
//    public TreeNode deleteNode(TreeNode root, int key) {
//
//    }

    /*329
    给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
     */
//    public int longestIncreasingPath(int[][] matrix) {
//
//    }

    /*LCR 144. 翻转二叉树
    给定一棵二叉树的根节点 root，请左右翻转这棵二叉树，并返回其根节点。
    * */
    /**
     *【思路】使用前序遍历，遍历到每一个节点的时候交换它的左右孩子。。不要使用中序遍历.
     *      层序遍历也是一样的道理
     */
    /*方法1：迭代法前序遍历，对于遍历到的每一个节点，交换它的左、右孩子节点*/
    public TreeNode flipTree(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            swap(cur);
            if (cur.right!=null){
                stack.push(cur.right);
            }
            if (cur.left!=null){
                stack.push(cur.left);
            }
        }

        return root;
    }

    private void swap(TreeNode cur) {
        TreeNode left = cur.left;
        cur.left = cur.right;
        cur.right = left;
    }


    /*方法2：递归的方式*/
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // 交换左右子树
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);

        return root;
    }

    /*方法3：层序遍历的过程中交换*/
    public TreeNode invertTree_layer(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);

        while (!deque.isEmpty()){
            TreeNode curNode = deque.poll();
            swap(curNode);

            if (curNode.left!=null) deque.offer(curNode.left);
            if (curNode.right!=null) deque.offer(curNode.right);
        }
        return root;
    }



    /*395
    给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

如果不存在这样的子字符串，则返回 0。
     */
//    public int longestSubstring(String s, int k) {
//
//    }


    /*316
    给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     */
//    public String removeDuplicateLetters(String s) {
//
//    }

    /*673. 最长递增子序列的个数
给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。

注意 这个数列必须是 严格 递增的。
     */
//    public int findNumberOfLIS(int[] nums) {
//
//    }



    /*443
    给你一个字符数组 chars ，请使用下述算法压缩：

从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：

如果这一组长度为 1 ，则将字符追加到 s 中。
否则，需要向 s 追加字符，后跟这一组的长度。
压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。

请在 修改完输入数组后 ，返回该数组的新长度。

你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
     */
//    public int compress(char[] chars) {
//
//    }


    /*134
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     */
    /**
     * 【注意】这个必须用两个sum————
     *      ①一个curSum表示当前的总剩余油量。每当它小于0，就重新置0，并更新res为i+1。暗示从下一个位置开始”才有可能!!!“能转一圈
     *      ②一个totalSum表示总的gas-总的cost。如果这个值小于0，说明从哪里开始都不行。因为转一圈能得到的油 少于 转一圈需要花费
     *  的油
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSum = 0 /*记录总体的gas-cost*/, curSum = 0 /*记录当前遍历的gas-cost*/;
        int res = 0; /**err：存在一种特殊情况，curSum最小值为0，此时不会进入for循环中的if，因此res初始值为-1不合适*/
        for (int i = 0; i < gas.length; i++) {
            totalSum += (gas[i] - cost[i]);
            curSum += (gas[i] - cost[i]);
            if (curSum < 0) {
                res = i + 1; //每次curSum<0，说明即使存在解，也得从下一位开始尝试，因此更新答案res为i+1
                curSum = 0;
            }
        }
        return totalSum < 0 ? -1 : res; //如果totalSum小于0，说明实现不了
    }

    /*LCR 187. 破冰游戏
    社团共有 num 位成员参与破冰游戏，编号为 0 ~ num-1。成员们按照编号顺序围绕圆桌而坐。社长抽取一个数字 target，从 0 号成员起开始计数，排在第 target 位的成员离开圆桌，且成员离开后从下一个成员开始计数。请返回游戏
    结束时最后一位成员的编号。
     */
    /**
     *【约瑟夫环问题】定义f(n, m)：表示 n 个人、每次数到第 m 个出局，最终剩下的人的编号。则有递推公式————
     *      f(1,m)=0         //只剩下一个人，不论m是多少，最后剩下的那个人编号都是0.
     *      f(n,m)=(f(n-1,m) + m) % n
     *   这个公式表示：当前人数是 n 时的生还者编号等于上一步 n - 1 人时生还者编号向右移动 m 位后的结果。
     *
     */
    public int iceBreakingGame(int num, int target) {
        int res = 0;
        for (int i = 2; i <= num; i++) {
            res = (res+target)%i;
        }
        return res;
    }


    /*678
    给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。

有效 字符串符合如下规则：

任何左括号 '(' 必须有相应的右括号 ')'。
任何右括号 ')' 必须有相应的左括号 '(' 。
左括号 '(' 必须在对应的右括号之前 ')'。
'*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
     */
    /**
     *【关键】使用两个变量。
     *      low含义：最少可能的未匹配的左括号数量；
     *      high含义：最多可能的未匹配的左括号数量。
     *      这个两个变量构成一个区间[low,high],未匹配的左括号数量位于这个区间
     *【具体的思路】在上面关键信息的基础上————
     *      每次碰到'('，则low和high都得+1.因为左括号是确认的，必须要匹配到右括号；
     *      每次碰到')'，则low和high都得-1，因为右括号能明确的匹配左括号；
     *      每次碰到‘*’。如果我们把它当作左括号，则high需要加1；如果我们把它当右括号，则low需要加1
     */

    /*解法1：贪心的解法*/
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') { //碰到左括号，low和high都增大
                low++;
                high++;
            } else if (c == ')') { //碰到右括号，low和high都减小
                low--;
                high--;
            } else { //否则说明是*,乐观情况下当作右括号，因此low--；悲观情况下当作左括号，因此high++
                low--;
                high++;
            }
            if (high < 0) return false; /**high只有在碰到右括号才会减1，小于0说明右括号明确的多了，返回false*/
            if (low < 0) low = 0; /**low小于0的时候置为0，符合实际含义*/
        }
        return low == 0;
    }


    /*解法2：回溯的解法。。。。会超出时间限制*/
    public boolean checkValidString_huisu(String s) {
        return dfs(s, 0, 0);
    }

    private boolean dfs(String s, int index /*当前需要遍历的位置*/, int count/*当前未闭合的左括号数*/) {
        // 剪枝：右括号多于左括号，非法
        if (count < 0) return false;

        // 结束条件：遍历完整个字符串
        if (index == s.length()) return count == 0;

        if (s.charAt(index)=='('){ //如果是左括号，则未闭合的左括号数+1
            return dfs(s,index+1,count+1);
        } else if (s.charAt(index)==')') { //如果是右括号，则未闭合的左括号数-1
            return dfs(s,index+1,count-1);
        }else { //否则。*当作是(，未闭合的左括号数+1；*当作是)，未闭合的左括号数-1；*当作是空串，未闭合的左括号数不变
            return dfs(s,index+1,count+1) ||
                    dfs(s,index+1,count-1) ||
                    dfs(s,index+1,count);
        }
    }


    /*106
    给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     */
    /**
     *【思路】构造出map存放节点值——>索引的映射；声明全局变量标识在postorder中研究到哪个位置了
     *      1.每一次从postorder中拿出最后一个值，就是当前的根节点root。
     *      2.从map中找到这个值在inorder的位置index。（此时index左边的节点就是root的左子树，index右边的节点就是root的右子树）
     *      3.根据2中的备注，递归的调用左、右两部分即可构造出root的左。右子树
     */
    int postIndex;
    HashMap<Integer,Integer> inorderMap = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = inorder.length-1; //标识当前postorder研究到哪个位置了
        for (int i = 0; i < inorder.length; i++) { //意义：根据从postorder拿到的值快速得到它在inorder中的位置
            inorderMap.put(inorder[i],i);
        }
        return build(postorder,0,inorder.length-1);
    }

    private TreeNode build(int[] postorder, int left, int right) {
        if (left>right || postIndex<0) return null;
        /*step1：拿到当前的根节点的值，并构造出节点*/
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);
        /*step2：拿到rootVal在中序遍历中的位置index*/
        Integer index = inorderMap.get(rootVal);
        /*step3：递归index左右两部分完成root.left、root.right的构造
        * 【出错点】是从后往前遍历（postIndex--），顺序是：根 -> 右子树 -> 左子树，所以在构造时必须先构造右子
        *       树，再构造左子树。*/
        root.right = build(postorder,index+1,right); /**err：这里的顺序重要*/
        root.left = build(postorder,left,index-1);
        return root;
    }


    /*887. 鸡蛋掉落
    给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     */
//    public int superEggDrop(int k, int n) {
//
//    }

    /*85
    给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     */
    /**
     * 【关键】遍历每一行，在每一行的时候研究每一列的高度，并调用类似84题的方法求解此时的最大矩形，那这个结果去更新最终的返回结果。
     * 【难点】遍历每一行的时候，heights数组如何计算————只要(row,col)位置的值是0，就将heights[col]置为0；否则的话说明该位置
     *      是1，heights[col]++。
     */
    public int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int res = 0;
        for (int row = 0; row < matrix.length; row++) { /*研究每一行*/
            for (int col = 0; col < matrix[0].length; col++) { /*在第row行中，计算每一个柱状图的高度*/
                if (matrix[row][col] == '1')
                    heights[col]++;
                else
                    heights[col] = 0; /**每次碰到一个0，heights数组对应位置的值归零*/
            }
            /*此时的heights存放着第row行每一列柱状图的高度，用方法largestRectangleArea计算这个heights的最大矩形————即方
            法largestRectangleArea的返回值。根据结果更新全局最大值res*/
            res = Math.max(res, largestRectangleArea(heights));
        }
        return res;
    }

    /*第84题的原代码，不用动*/
    private int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int curRowMaxArea = 0; /*此时这个方法的返回值代表的是heights这一行数据能画出的最大矩形*/
        for (int i = 0; i <= heights.length; i++) {
            int curHeight = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && curHeight < heights[stack.peek()]) {
                Integer cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int curVal = heights[cur] * (i - left - 1);
                curRowMaxArea = Math.max(curVal, curRowMaxArea);
            }
            stack.push(i); /**err：注意，这里记得将i入栈，否则样例的结果一直是0*/
        }
        return curRowMaxArea;
    }


    /*44
    给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符序列（包括空字符序列）。
判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
     */
//    public boolean isMatch(String s, String p) {
//
//    }

    /*679
    给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。

你须遵守以下规则:

除法运算符 '/' 表示实数除法，而不是整数除法。
例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
你不能把数字串在一起
例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
     */
//    public boolean judgePoint24(int[] cards) {
//
//    }


    /*400
    给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
     */
    /**
     *【思路】规律：1位数共有1*9*1位；2位数共有10*9*2位；3位数共有100*9*3位
     *     1. 根据规律，利用while循环每一次过掉所有的m位数
     *     2. 最后如果n对应的是i位数，则start就是10^(i-1)次方————即i位数中最小的那个数
     * */
    public int findNthDigit(int n) {
        int digit = 1; //当前研究的轮次是几位数。1位数、2位数、3位数....
        long start = 1; //当前研究轮次的第一个数。1位数的第一个数是1、2位数的第一个数是10、3位数的第一个数是100....
        long count = 9; //当前研究的这轮次所有数的总位数。count其实就是9*digit*start
        /*step1：遍历不同的轮次，每一轮次分别是————所有的2位数、所有的3位数...*/
        while (n>count){
            n -= count;
            digit++;
            start *= 10;
            count = digit*start*9;
        }
        /*step2：看第n个数对应的是哪一个整数*/
        long num = start + (n-1)/digit; /**err：n-1的原因是索引是从0开始的*/
        /*step3：将对应的数转换为字符串，并取出对应位置的字符*/
        String s = String.valueOf(num);
        return s.charAt((n - 1) % digit) - '0';
    }

    /*611
    给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
     */
    /**
     * 【思路】首先对整个数组排序，然后倒着研究每一个数
     *      每次研究到一个数（比如索引是k）时，固定left指针为0，right指针为k-1。根据left、right位置两数和与m位置数的大小关系
     *  来移动指针————
     *          ①如果nums[left]+nums[right]>nums[k]，则这是一组解。可知left指针从left可以移动到right-1都是可行解，因此
     *      一共有”right-left“这麽多的可行解。更新完总数，移动right指针，right--。
     *          ②如果nums[left]+nums[right]≤nums[k]，则说明这个和太小了，和需要变大一点，因此left指针++。
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int k = nums.length-1; k >= 2; k--) { /**err：k=2的时候也需要执行循环。k=2时，i=0,j=1*/
            int left = 0,right = k-1;
            while (left<right){
                if (nums[left]+nums[right]>nums[k]){
                    res += (right-left); /*想象成j不变，i可以选择i、i+1、i+2...j-2、j-1。因此选择后下一行改变j就不会有重复的组合*/
                    right--; /**为什么这里需要j--，不会重复吗？？不会，因为j--之后，j肯定不一样了。结合上一行的注解可知不会重复*/
                }else {
                    left++;
                }
            }
        }
        return res;
    }

    /*120
    给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     */
//    public int minimumTotal(List<List<Integer>> triangle) {
//        LinkedList<Integer> ls = new LinkedList<>();
//        ls.add(triangle.get(0).get(0));
//        for (int i=0;i<triangle.size();i++){
//            List<Integer> integers = triangle.get(i);
//            for (int j = 0; j < integers.size(); j++) {
//                ls.add(0,ls.get(0)+integers.get(0));
//            }
//        }
//    }
}
