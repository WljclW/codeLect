package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import javax.sound.sampled.Line;
import javax.xml.stream.events.Characters;
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


    /*
    460. LFU 缓存
    请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。

实现 LFUCache 类：

LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。

当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。

函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     */
//    class LFUCache {
//
//        public LFUCache(int capacity) {
//
//        }
//
//        public int get(int key) {
//
//        }
//
//        public void put(int key, int value) {
//
//        }
//    }



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
     *【思路】
     *      1. 先依照最小栈的原则遍历一次num，每次删除字符维护k的值；
     *      2. 如果1结束后，k>0，则从栈的末尾删k个字符。。。
     *      3. 最后栈中的字符组成字符串返回。但是需要去除前导零
     *【关键】这个题虽然用到了单调栈，但是由于最后拼接字符串需要从栈底开始拿字符，因此实际上最好不用使用Stack类，而是使用LinkedList，
     *   把LinkedList当作双端队列使用；
     *      从前向后遍历的时候、以及最后需要从双端队列头开始挨个获取元素，都建议使用”for (char c : xxxxx)“的方法，可以知道这种方
     *   法就是从头开始拿取元素的
     *【说明】这个题可以入栈字符，代码会简化很多。。因为题目中不会用到索引 或者 间距 等类似的信息……
     */
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        /*step1：将每一位入栈的过程中维持最小栈结构————只要当前的数比栈顶的数小，栈顶的数就出栈*/
        for (char c : num.toCharArray()) {
            /**err：①这里直接入栈的是字符，而不是索引，因此比较的是"c<stack.peekLast()"。小于的时候
             *  才尝试把栈顶的元素弹出来，等于的时候不用动。
             *      ②k的含义是要删除的字符数量，因此k-1应该绑定在删除字符（从栈中弹出字符）的时
             * 候，每一次字符弹出双端队列时k-1
             *      ③k的条件是"k>0"，不能带等于。因为如果带等于，则k的变化过程为：k、k-1、k-2....2、1、0.
             * 会发现这其实是k+1个数，相当于删了k+1个数字，就删多了*/
            while (!stack.isEmpty() && k > 0 && c<stack.peekLast()) {
                stack.pollLast();
                k--;
            }
            stack.offerLast(c);
        }
        /*step2：如果k还是大于0则需要从尾部继续删数字。
                stack中剩下的数字是单调递增的。倒着删除k位*/
        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }
        /*step3：使用StringBuilder收集结果并返回*/
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            if (sb.length() == 0 && c == '0') continue; /*去除前导零的核心逻辑*/
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString(); /**err：这里需要判断sb是不是没东西，如果没东西需要返回“0”*/
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
    /**
     *【思路】两次遍历：第一次遍历做两件事————①统计链表的节点数；②将链表的尾部和首部串起来；
     *               第二次遍历做两件事————①找到第size-k个节点；②将next指针置null（但要先记录一下，因为要返回，它时新的头节点）
     *【难点】第一次遍历时：size的初始值是1，循环的条件是while(cur.next！=null)————这样最终cur会指向最后一个非null节点
     *       第二次遍历时：也是从head开始，因此此题没有使用dummy节点，并且也尽量不要使用，就会导致其实初始就已经过了一个节点————因此
     *   for循环遍历i的范围是“i<size-k-1”。
     *【易错点】
     *      1. 开始的时候，特殊情况的判断————"head==null" 提前返回；
     *      2. 计算节点数目的时候。开始位置是head，初始的size值是1.
     *      3. 从前开始数节点的时候，i的范围是"0~size-k-1"，因为"0、1、2、3....size-k-1"就是size-k个节点了
     *      4. 最后需要先记录一下head.next节点，然后再执行head.next=null。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head; //特殊情况的判断。”head==null“的判断是必不可少的，其他的可以省
        /*step1：计算出链表的节点数量。
        【难点】size的初始值需要设置为1；并且while的循环条件应该是”cur.next!=null“
               原因：size的初始值是1，因为head已经计数过了；如果cur.next不是null的时候才能更新size*/
        int size = 1;
        ListNode cur = head;
        while (cur.next!=null){
            cur = cur.next;
            size++;
        }
        /*step2：首尾相连。
            经过上面的while循环之后，cur指针会来到最后一个非null的节点————此时需要将cur.next指向head,完成首尾相接*/
        cur.next = head;
        k %= size; /**err：k需要对size取余*/
        /*step3：从头开始遍历，找到需要断开连接的地方。因为要旋转k位，因此应该是在第(size-k)个节点之后断开连接。
                【注意】但是由于我们是从head节点开始数的，意味着还需要移动（可以理解为操作）"size-k-1"次。循环
             变量i是0开始，还需要移动"size-k-1"次，因此循环条件"i<size-k-1"————此时循环变量i的范围[0,size-k-2]，
             因此整个循环会进行“size-k-1”次，也就是cur移动了“size-k-1”次（由于cur开始是在head，因此最后会指向
             第“size-k”个节点）。
                 【结论】循环变量i从0开始的话————
                        （1）如果循环条件是“i<M”，则整个循环体会执行M次。因为i的范围是[0,M-1]，共M个可取值。
                        （2）如果循环条件是“i>-M”，则整个循环体会执行M次。因为i的范围是[-(M-1)，0]，共M个可选值*/
        cur = head; /**这里如果cur不动，应该更好懂，见rotateRight1*/
        for (int i = 0; i < size - k - 1; i++) { /**err：注意每个节点向右移动k，因此要从前往后数size-k个节点，而不是数k个节点！！之所以是“ i < size - k - 1”，是因为开始就在head，但是计数是0*/
            cur = cur.next;
        }
        /*step4：先记录下cur.next，这是要返回的头；然后cur.next=null————断开cur节点和后面节点的连接*/
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }


    public ListNode rotateRight1(ListNode head, int k) {
        if(head==null||head.next==null||k==0) return head;
        int size = 1;
        ListNode cur = head;
        while (cur.next!=null){
            cur = cur.next;
            size++;
        }
        ListNode tail = cur;
        tail.next = head;
        k %= size;
        /**
         这里cur处于链表的最后一个节点，如果cur不动，反而更清晰一点
         */
        for (int i = 0; i <size - k; i++) {
            cur = cur.next;
        }
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }


    /*114
    给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */
    /**
     * 【思路】使用前序比哪里依次将节点串起来。
     * 【难点】方法是没有返回值的————因此不能虚拟头节点。导致拼接指针的初始值只能初始化为null 或者 root(建议初始化为null)
     * @param root
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
                cur = cur.right;        /**err：更新cur指针。。。。如果忘记变更，会导致最终的结果最多只有两个节点*/
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
    /**
     *【注意】题目要求返回的是“和”，而不是差值的绝对值。
     *【说明】这个题甚至不用跳过相同的组合，因为相同的组合不影响结果，结果只是求最接近target的三数之
     *      和是多少？而不是求有多少种，因此比"15三数之和"题目简单
     */
    public int threeSumClosest(int[] nums, int target) {
        int res = 0,max = Integer.MAX_VALUE; /*res记录最接近的三数和；max记录最接近的差值绝对值是多少，max的初始化很重要，要初始化为最大值*/
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            int l = i+1,r = nums.length-1;
            while (l<r){
                int curSum = nums[i]+nums[l]+nums[r];
                if (Math.abs(curSum-target)<max){ /**如果curSum和target更接近则更新res*/
                    max = Math.abs(curSum-target);
                    res = curSum;
                    if (res==target) return res; /**如果找到等于target的组合，就直接返回，这就是最接近的答案了*/
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

    /*另外的写法*/
    public int threeSumClosest1(int[] nums, int target) {
        int res = Integer.MAX_VALUE;
        int flag = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1,right = nums.length-1;
            while (left<right){
                int curVal = nums[i]+nums[left]+nums[right];
                if (Math.abs(curVal-target)<flag){
                    res = curVal;
                    flag = Math.abs(curVal-target); /**err：注意更新flag*/
                }
                if (curVal<target){
                    left++;
                }else if (curVal>target){
                    right--;
                }else {
                    return curVal; /**err：要返回的是三数之和，而不是和target的差值*/
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

    /*
    91. 解码方法
    一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

"1" -> 'A'

"2" -> 'B'

...

"25" -> 'Y'

"26" -> 'Z'

然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。

例如，"11106" 可以映射为：

"AAJF" ，将消息分组为 (1, 1, 10, 6)
"KJF" ，将消息分组为 (11, 10, 6)
消息不能分组为  (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
注意，可能存在无法解码的字符串。

给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。

题目数据保证答案肯定是一个 32 位 的整数。
     */
//    public int numDecodings(String s) {
//
//    }


    /*
    440. 字典序的第K小数字
    给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
        示例 1:
        输入: n = 13, k = 2
        输出: 10
        解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
     */
//    public int findKthNumber(int n, int k) {
//
//    }


    /*145
    给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
    * */
    /*解法1：非递归方法*/
    /**
        后序遍历的顺序是“左、右、根”，但是遍历的时候我们使用“根、右、左”。。因此碰到节点就先打印，然后先是左孩
     子入栈，这样的话右孩子先出栈，因此就是“根、右、左”，然后将收集结果的集合reverse就是正确的后序遍历了
     */
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

    /*解法2：递归方法*/
    public List<Integer> postorderTraversal_digui(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        dfsPostorder(root,res);
        return res;
    }

    private void dfsPostorder(TreeNode root, LinkedList<Integer> res) {
        if (root==null) return;
        dfsPostorder(root.left,res);
        dfsPostorder(root.right,res);
        res.add(root.val);
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
     *【思路】奇数位置、偶数位置使用不同的头节点；出了while循环需要把奇数链表的头结点拼接到偶数节点
     *      的后面（偶数节点的最后一个就是出了while循环时odd的值）
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
        /*step1：特殊情况的判断————因为下一步需要取head.next，因此第一部必须先保证head不是null*/
        if (head == null || head.next == null) return head;
        /*step2：取三个指针————①偶数节点的头节点，不动值；②偶数节点的指针，变化值，用于整个过程中
        *   移动拼接偶数节点链表；③奇数节点的指针，变化值，用于整个过程中移动拼接奇数节点链表*/
        ListNode evenHead = head.next, even = evenHead;
        ListNode odd = head;
        /*step3：循环拼接链表。
                【注】两个条件缺一不可，跟“复制随机链表”不一样，原因————
                “复制随机链表”题目中"even.next!=null"就注定了后面至少还有一对节点（因此even!=null这个
            条件可以省略）；但是这个题就不一样了，在while循环内部"even=even.next"之后even是可能是null
            的，因此在while条件中必须判断是不是even是null。
        */
        while (even != null && even.next != null) { /**注意，while循环内会使用到”even.next.next“，因此while条件需要保证”even.next!=null“*/
            odd.next = odd.next.next;
            odd = odd.next;

            even.next = even.next.next;
            even = even.next;
        }
        /*step4：将奇数节点的链表拼到偶数链表的后面*/
        odd.next = evenHead;
        return head;
    }


    /*230
    给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
     */
//    public int kthSmallest(TreeNode root, int k) {
//
//    }


    /*442
    给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 最多两次 。请你找出所有出现 两次 的整数，并以数组形式返回。

你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间（不包括存储输出所需的空间）的算法解决此问题。
    * */
    /**
     *【思路】碰到一个数num，将num-1位置的值改为负数。遍历过程中，如果发现某个数应该放在的位置存的是一个负
     *      数，说明这个数重复了
     */
    public List<Integer> findDuplicates(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.abs(nums[i]); /**i位置的数可能已经变成负数，因此这里需要取绝对值*/
            /*
            依据我们的想法，num这个数应该放在num-1的位置，我们将对应位置的数标记为负数。
                if块：表明对应位置的数已经是负数了，则说明这个数之前遇到过，说明重复了，需要添加到结果集；
                else块：表明当前数cur是第一次遇到，因此将cur-1位置的数变为负数，说明是第一次遇到。
             */
            if (nums[cur-1]<0){
                res.add(cur);
            }else {
                nums[cur-1] *= -1;
            }
        }

        return res;
    }



    /*135
    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

    你需要按照以下要求，给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */
    /**
     *【思路】两次遍历。
     *      ①一次是从左向右遍历，满足左边的条件。对于index位置，如果index-1的位置值小，则index位置的最小值是index位置的糖果+1.
     *          ————这样一轮结束可以保证每一个位置左边条件是满足的
     *      ②一次是从右向左遍历，满足右边的条件。对于index位置，如果index+1的位置值小，则index位置的最小值是index+1位置的糖果+1。
     *          ————这样一轮结束可以保证每一个位置右边条件是满足的
     */
    public int candy(int[] ratings) {
        int[] flags = new int[ratings.length];
        Arrays.fill(flags,1);
        /*step1：从左到右遍历。如果某同学比左边的同学高，则该同学糖果数加1————只关注自己左边的同学*/
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i]>ratings[i-1]){
                flags[i] = Math.max(flags[i-1]+1, flags[i]); //这里不要Math.max也可以，因此初始值都是1，flags[i-1]+1肯定比1大
            }
        }
        /*step2：从右到左遍历。如果某同学比右边的同学高，则该同学糖果数取最大值————只关注右边的同学*/
        for (int i = ratings.length-2; i >=0 ; i--) { /**err：关注右边的同学，因索引范围0~rating.length-2*/
            if (ratings[i]>ratings[i+1]){
                flags[i] = Math.max(flags[i+1]+1,flags[i]);
            }
        }
        /*step3：计算需要的糖果……数组元素求和可使用“Arrays.stream(res).sum();”*/
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

    /*LCR 144(226相同). 翻转二叉树
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

        /*或者使用下面的代码*/
//        TreeNode left = flipTree(root.left);
//        TreeNode right = flipTree(root.right);
//        root.left = right;
//        root.right = left;

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


    /*316
    给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     */
    /**
     *【说明】这个题的最小栈中，如果放Character就方便很多，如果放的是索引，效果差了很多！！见removeDuplicateLetters_04
     *【思路】
     *      1. 一个int [25]————统计每一个字符出现的次数；（也可以用出现的最晚位置代替）
     *         一个boolean [26]————统计栈里面是不是有某个字符
     *      2. 维护一个栈来构建答案（想法是最小栈————即只要当前字符比栈顶元素小，栈顶元素就需要出栈）【但是】与之前常规的最最小栈有
     *  区别，这里并不能仅仅依靠字符的大小来决定出栈，而是要结合后面剩下的串中还有没有栈顶字符来决定。。。。。因为题目要求最后每一个字
     *  符都必须出现且仅出现一次！！
     *         综上，元素出栈的条件：①当前遍历到的字符比栈顶元素小；②当前字符后面的子串中还有栈顶字符。满足①和②时，栈顶元素才能出栈。
     *      3. 基于2，需要引入一个数组————用一个集合 visited[] 标记哪些字符已经在栈中，防止重复加入
     *      4. 最后从”栈底“一次弹出每一个字符组装成结果
     *【tips】使用”最小栈“的思想，但是最后从栈底依次弹出每一个元素组装成最后的节点。常见两种处理方式——————
     *          方式1：最后从栈底依次弹出每一个元素时，使用 “ for (char ch : stack) ”
     *          方式2：虽然使用的是栈，但是变量使用LinkedList。此时入栈、出栈分别使用”offerLast()“、”pollLast()“，最后从栈底弹出元
     *    素时使用方法”pollFirst()“——————即”把栈顶想象成双端队列尾部，把栈底想象成双端队列头部“
     */
    /*解法1：记录出现的次数 以及 栈中是不是有某个字符*/
    public String removeDuplicateLetters(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int[] count = new int[26]; // 每个字符剩余的出现次数
        boolean[] visited = new boolean[26]; // 当前栈中是否已有该字符
        /*step1：统计每一个字符出现的次数*/
        for (int i=0;i<s.length();i++){
            count[s.charAt(i)-'a']++;
        }
        /*step2：遍历研究每一个字符，代码的主要内容*/
        for (char c:s.toCharArray()) {
            /*第一步：更新剩下的子串中c字符还有几个*/
            count[c - 'a']--; /**err：必须先更新count数组的值，再更新visited数组*/
            /*第二步：如果c字符已经在栈中了，就不能再添加了，直接跳过*/
            if (visited[c]) {
                continue;
            }
            /*第三步：维护最小栈的结构！但是有附加条件————栈顶的字符在剩下的字串中还有。也就是说：即使栈顶的字符c1
             *     可能大于当前的字符c，但是后面再也碰不到字符c1了，此时栈顶的字符c1也不能出栈。因为每一个字符都必须
             *     出现在结果中，如果此时c1出栈，但是剩下的子串又没有c1了，最后的结果也就不会有c1
             *     综上：此题严格意义来说时最小栈的思想 ，但是跟最小栈是由区别的*/
            while (!stack.isEmpty() && count[stack.peekLast() - 'a'] > 0 && c < stack.peekLast()) {
                visited[stack.pollLast() - 'a'] = false;
            }
            stack.offerLast(c);
            visited[c - 'a'] = true;
        }
        /*step3：将栈中的内容从栈底挨个拿出来添加到StringBuilder*/
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }


    /*解法2：记录出现的最晚位置（最后出现的位置） 以及 栈中是不是有某个字符*/
    public String removeDuplicateLetters_1(String s) {
        int[] lastIndex = new int[26];
        boolean[] isExit = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i)-'a'] = i;
        }

        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isExit[c-'a']){
                continue;
            }
            while (!stack.isEmpty()&&c<stack.peekLast()&&lastIndex[stack.peekLast()-'a']>=i){
                Character c1 = stack.pollLast(); /**LinkedList充当栈的时候使用“offerLast/pollLast”代替入栈/出栈*/
                isExit[c1-'a']=false;
            }
            stack.offerLast(c);
            isExit[c-'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        for(char c:stack){
            sb.append(c);
        }
        return sb.toString();
    }

    /*解法3：只记录每一个字符最后出现的位置*/
    public String removeDuplicateLetters_03(String s) {
        int[] flags = new int[26];  /**官方这里还会使用一个布尔数组记录栈或队列出现的字符，不使用也能判断，但是时间复杂度高*/
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            flags[c-'a'] = i;
        }

        LinkedList<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!deque.contains(c)){
                while (c<deque.peekLast()&&flags[deque.peekLast()-'a']>i){
                    deque.pollLast();
                }
                deque.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c:deque){
            sb.append(c);
        }
        return sb.toString();
    }

    /*补充解法，最小栈中存的是索引。。。
            此时的写法复杂很多！！一方面如果是数组的话直接通过下标就可以拿；但是换成字符串后必须使用方
        法“s.charAt()”*/
    public String removeDuplicateLetters_04(String s) {
        int[] flags = new int[26];
        boolean[] used = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            flags[s.charAt(i)-'a'] = i;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!used[c-'a']){
                while (!stack.isEmpty()&&flags[s.charAt(stack.peek())-'a']>i&&c<s.charAt(stack.peek())){
                    Integer index = stack.pop();
                    used[s.charAt(index)-'a'] = false;
                }
                used[c-'a'] = true;
                stack.push(i);
            }
        }

        StringBuilder res = new StringBuilder();
        for (int index:stack){
            res.append(s.charAt(index));
        }
        return res.toString();
    }





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
    public int compress(char[] chars) {
        int read = 0;/*一个指针，用于指示遍历到原字符串的什么位置*/
        int write = 0;/*写入压缩结果的位置*/
        int n = chars.length;
        while (read < n) {
            char currentChar = chars[read];
            int start = read; /**为了currentChar的数量需要记录一下开始的read位置，【注意】不能使用write位置代替，此时write和read之间可能还有一段位置*/
            while (read < n && chars[read] == currentChar) {
                read++;
            }

            int count = read - start;
            chars[write++] = currentChar;
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        return write;
    }


    /*134
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     */
    /**
     * 【注意】这个必须用两个sum————
     *      ①一个curSum表示当前的总剩余油量。每当它小于0，就重新置0，并更新res为i+1。暗示从下一个位置开
     *始"才有可能!!!"能转一圈。【重要】说明了0~i任何位置开始都不可能走一圈。
     *      ②一个totalSum表示总的gas-总的cost。如果这个值小于0，说明从哪里开始都不行。因为转一圈能得到的油 少于 转一圈需要花费
     *  的油
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSum = 0 /*记录总体的gas-cost*/, curSum = 0 /*记录当前所有已遍历位置的gas-cost*/;
        int res = 0; /**err：存在一种特殊情况，curSum最小值大于等于0，此时不会进入for循环中的if导致res的值不变，因此res初始值为-1不合适*/
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
     *【说明】这个题目中：①人员的编号是从0开始的；②报数是从1开始的，报target的那个人需要删除
     *【约瑟夫环问题】定义f(n, m)：表示 n 个人、每次数到第 m 个出局，最终剩下的人的编号。则有递推公式————
     *      f(1,m)=0         //只剩下一个人，不论m是多少，最后剩下的那个人编号都是0.
     *      f(n,m)=(f(n-1,m) + m) % n
     *   这个公式表示：当前人数是 n 时的生还者编号 等于 上一步 n - 1 人时生还者编号向右移动 m 位后（需要取余
     * 体现循环的特点）的结果。
     *
     */
    public int iceBreakingGame(int num, int target) {
        /*base case：如果只有一个数，则最后留下的那个数的编号就是0*/
        int res = 0;
        /*从两个数开始推，一直推到第num个数的答案。
            如果开始有m个数，最后剩下的那个数编号是digit；
            则如果开始有m+1个数时，最后剩下的那个数编号是（digit+target）% （m+1）。
            ......依次类推
        * */
        for (int i = 2; i <= num; i++) {
            res = (res + target) % i;
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
     *      low含义：最少可能的 未匹配的左括号数量；
     *      high含义：最大可能的 未匹配的左括号数量。
     *      这个两个变量构成一个区间[low,high],未匹配的左括号数量位于这个区间
     *【具体的思路】在上面关键信息的基础上————
     *      每次碰到'('，则low和high都得+1.因为左括号是确认的，必须要匹配到右括号；
     *      每次碰到')'，则low和high都得-1，因为右括号必须明确的匹配左括号；
     *      每次碰到‘*’。如果我们把它当作左括号，则此时未匹配的右括号数量多1，即high需要加1；如果我们把它当
     * 右括号，则未匹配的右括号数量少1，即low需要减1
     *【易错点】整个过程中low和high的最小值都是0———
     *       ①如果high小于0，直接返回false。（表示最多未匹配的左括号是-high个，不可能哇）
     *       ②如果low小于0，则将low重置为0。low < 0 是无效的逻辑，因为它意味着我们尝试把更多的 * 当成 )，而
     * 多出来的 ) 在语法上是非法的。为了确保合法，必须在每一步中 low ≥ 0
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
            /**关键的校验流程2个步骤，缺一不可！！*/
            if (high < 0) return false; /**err：high只有在碰到右括号才会减1，小于0说明右括号明确的多了，返回false*/
            if (low < 0) low = 0; /**err：每一轮结束是low小于0的时候必须置为0，符合实际含义*/
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
     *【总结】中序-先序还原二叉树、中序-后序还原二叉树是一样的道理。区别就在于前者是每一次从先序中拿出
     *      第一个数作为当前的根；后者是每一次从后序中拿出最后一个数作为当前子树的根————即思路的第1点。
     *      除此以外，是一样的操作。
     *【思路】构造出map存放中序遍历节点值——>索引的映射；声明全局变量标识在postorder中研究到哪个位置了
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
        if (left>right) return null; //条件也可以写成"if (left>right || postIndex<0)"
        /*step1：拿到当前的根节点的值，并构造出节点*/
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);
        /*step2：拿到rootVal在中序遍历中的位置index*/
        Integer index = inorderMap.get(rootVal);
        /*step3：递归index左右两部分完成root.left、root.right的构造
        * 【出错点】是从后往前遍历（postIndex--），顺序是：根 -> 右子树 -> 左子树，所以在构造时必须先构造右子
        *       树，再构造左子树。
        * 【注意】
        *       切记在"后序-中序"构造二叉树时必须先构造右子树，再构造出左子树。否则会出现如下的报错——————
        *       java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 5
                  at line 30, Solution.buildTree
                  at line 34, Solution.buildTree
                  at line 33, Solution.buildTree
                  at line 33, Solution.buildTree
                  at line 33, Solution.buildTree
                  at line 33, Solution.buildTree
                  at line 25, Solution.buildTree
                  at line 56, __DriverSolution__.__helper__
                  at line 89, __Driver__.main
                 看着错误像是索引越界的问题，其实是因为这里写错了。如果先构造左子树就会出现这样的报错
        */
        root.right = build(postorder,index+1,right); /**err：这里的顺序重要*/
        root.left = build(postorder,left,index-1);
        return root;
    }


   /*105.
    * 从前序 和 中序 构造出二叉树*/
    HashMap<Integer,Integer> inorderMap1 = new HashMap<>();
    int preorderIndex;
    public TreeNode buildTree_preorder_inorder(int[] preorder, int[] inorder) {
        for (int i = 0; i <inorder.length; i++) {
            inorderMap1.put(inorder[i],i); /**【注】因为构造的过程中要根据一个数，找到它在中序inorder的位置，因此时nums[i]->i的映射*/
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int left, int right) {
        if (preorderIndex>=inorder.length) return null;
        int val = preorder[preorderIndex++];
        Integer index = inorderMap1.get(val);
        TreeNode root = new TreeNode(val);
        root.left = buildTree(preorder,inorder,left,index-1);
        root.right = buildTree(preorder,inorder,index+1,right);
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
     * 【关键】遍历每一行，在每一行的时候研究每一列的高度，并调用类似84题的方法求解此时的最大矩形，那这个
     * 结果去更新最终的返回结果。
     *【难点】遍历每一行的时候，heights数组如何计算————只要(row,col)位置的值是0，就将heights[col]重置为0；
     * 否则的话说明该位置是1，heights[col]++。
     */
    public int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int res = 0;
        for (int row = 0; row < matrix.length; row++) { /*研究每一行*/
            for (int col = 0; col < matrix[0].length; col++) { /*在第row行中，计算每一个柱状图的高度*/
                if (matrix[row][col] == '1') /**err：是和字符比较！！如果误写成“matrix[row][col] == 1”也不会报错，但是发现得到的结果永远都是0！！*/
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
                Integer cur = stack.pop(); /**err：要使用“pop()”弹出，否则85提交后会提示“超出时间限制”*/
                int left = stack.isEmpty() ? -1 : stack.peek();
                int curVal = heights[cur] * (i - left - 1);
                curRowMaxArea = Math.max(curVal, curRowMaxArea);
            }
            stack.push(i); /**err：注意，这里记得将i入栈，否则样例的结果一直是0！！！*/
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
     *【关键！！！】
     *     1. 这个题最大的关键是最后确定在哪一个数字使用"num = start + (n-1)/digit",必须使用"n-1"去
     *     除！！！！
     *     2. start 和 出现的数num要使用long来存放，int可能发生越界。。导致提交后有错误的测试用例，如
     *     下————
                             n =1000000000
                             输出
                             2
                             预期结果
                             1
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
        /*step2：看第n个数位对应的是哪一个整数*/
        /**
         如果直接用 n/digit，相当于从 1 开始计数，这样在整数除法下会“过早跳到下一个数字”。
         用 (n-1)/digit，相当于把计数从 0 开始，这样能正确定位在当前数字内部。
         这是一种典型的 从 0 起始索引 的处理方式，就像数组下标一样。
         * */
        long num = start + (n-1)/digit; /**🔺err：n-1的原因是索引是从0开始的，这里必须进行n-1，否则后面的操作太复杂了，还要加额外的判断*/
        /*step3：将对应的数转换为字符串，并取出对应位置的字符*/
        String s = String.valueOf(num);
        return s.charAt((n - 1) % digit) - '0';
    }

    public int findNthDigit_own(int n) {
        long start = 1; /**start这个数int不一定放得下，因此start和后续计算的num应该使用long来计算*/
        int digit = 1;
        while (n>start*digit*9){
            n -= start*digit*9;
            digit++;
            start *= 10;
        }
        /**err：需要注意的就是下面获取num以及index的时候都是使用n-1。*/
        long num = start+(n-1)/digit; /**看一下所求的数位在哪一个数*/
        int index = (n-1)%digit; /**计算所求的数位是num的哪一位*/
        return String.valueOf(num).charAt(index)-'0';
    }

    /*611
    给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
     */
    /**
     * 【关键】————很关键！
     *      1. 这个题最关键的是“要从大到小枚举最大边”，不要枚举最小边！！！需要多思考思考为啥（本质原因是每一次不能确定一批有
     *  效解！！）
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
                    /*  想象成right不变，left可以选择left、left+1、left+2...right-2、right-1。因此选择后下
                    一行改变right就不会有重复的组合
                        此时三角形的三条边从小到大依次是：nums[left]、nums[right]、nums[i]。。。因此下一行
                    改变right，三角形的三条边就一定是新的组合了。
                    */
                    res += (right-left);
                    right--; /**为什么这里需要right--，不会重复吗？？不会，因为right--之后，right肯定不一样了。结合上一行的注解可知不会重复*/
                }else {
                    left++;
                }
            }
        }
        return res;
    }

    /**
     * 下面是错误的解法！！见for循环的注释说明。结论————
     *      一个有序的数组，能快速的找出“两个数之和满足某种条件”的所有组合，重点是“两数之和满足
     *  某种条件”，三数之和、最接近的三数之和、这个题它们都是使用的这个特性。
     *      本质原因：一个有序数组，求解两数和满足某一条件的时候，可以实现简化。
     *      换到这个题，“有效三角形”的定义是“较小两边之和大于第三遍”，因此我们可以枚举最大的那
     *  条边，然后剩下两个较小的边的要求就是和大于最大的边！！————因此需要枚举最大边。。。反之，
     *  如果枚举的是最小边，有效三角形的定义式“较大的两边之差小于最小的边”，但是这个“两边之差”的
     *  方案数寻找问题不能用有序数组来简化
     */
//    public int triangleNumber(int[] nums) {
//        Arrays.sort(nums);
//        int res = 0;
    /**常见的错误在下面的地方，不能枚举最短边！！！即i必须从nums.length-1开始枚举！！*/
//        for (int i = 0; i < nums.length - 2; i++) {
//            int left = i+1,right = nums.length-1;
//            while (left<right){
//                int val = nums[i]+nums[left]-nums[right];
//                if (val>0){
//                    res += (right-left);
//                    right--;
//                }else {
//                    left++;
//                }
//            }
//        }
//        return res;
//    }

    /*
    264. 丑数 II
    给你一个整数 n ，请你找出并返回第 n 个 丑数 。
    丑数 就是质因子只包含 2、3 和 5 的正整数。
     */
    /**
     【建议】建议使用nthUglyNumber
     【关键】①第一个丑数是1。
            ②后面的任何一个丑数，都”一定“是由前面的某一个丑数”乘以2 或者 乘以3 或者 乘以5“得
        到的。——————这很关键！！是由前面的某一个丑数乘出来的，出处还是丑数~~
     【思路要点（核心直观解释）】
          丑数集合是闭合的：若 x 是丑数，则 2x, 3x, 5x 也都是丑数。
          如果按从小到大生成丑数序列 dp[0]=1, dp[1]=2, dp[2]=3, ...，每个后续丑数一定是之前某
       个丑数乘以 2、3 或 5 得到的最小未被使用的值。
          用三个指针 p2, p3, p5 分别指向当前可以乘以 2,3,5 的最小位置（即下一个可能产生新丑
       数的位置）。
          每一步取 candidate = min(dp[p2]*2, dp[p3]*3, dp[p5]*5) 作为下一个丑数，并相应
       地移动等于 candidate 的每个指针（可能同时移动多个，以避免重复）。
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1; /*dp[i]理解为”第i+1个丑数“*/
        int p2 = 0,p3 = 0,p5 = 0;
        for (int i = 1; i < n; i++) {
            /**err：这里是关键—————任何一个丑数都是由前面的某个丑数乘2、3或5得到的。因此计算的是”某个位置dp值“的
             *      2、3以及5倍；那到底是”某个位置“呢？？就由p2、P3、P5指向乘以2、3、5分别到哪个位置了*/
            int val2 = dp[p2]*2;
            int val3 = dp[p3]*3;
            int val5 = dp[p5]*5;
            dp[i] = Math.min(Math.min(val2,val3),val5);
            if (dp[i]==val2) p2++; /*注意这里的三个都必须写成if，否则会有重复的数，比如：6————因为它是2和3的公倍数（因此如果得到6需要将p2和p3都进行++）*/
            if (dp[i]==val3) p3++;
            if (dp[i]==val5) p5++;
        }
        return dp[n-1];
    }


    /**总体上就是上面的形式，但是可以多申请一个空间————数组长度为n+1，此时体现在for循环条件以及返回值
     */
    public int nthUglyNumber_(int n) {
        int[] dp = new int[n+1];
        /*多一个空间的话index=0的位置相当于没有作用，因此从index=1开始，同时p2、p3、p5三个指针
         也都是从1开始了。。*/
        dp[1] = 1;
        int p2 =1,p3 =1,p5 =1;
        for (int i = 2; i <= n; i++) {
            int val2 = dp[p2]*2;
            int val3 = dp[p3]*3;
            int val5 = dp[p5]*5;
            dp[i] = Math.min(Math.min(val2,val3),val5);
            if (dp[i]==val2) p2++;
            if (dp[i]==val3) p3++;
            if (dp[i]==val5) p5++;
        }
        return dp[n];
    }



    /*1004
    给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
     */
    /**
     *【解题关键】一句话：滑动窗口解，把握核心————维护窗口内0的数量必须小于等于k
     *【感悟】滑动窗口问题的关键是：从题目中找出窗口应该满足的条件！！！比如这个题"最多可以翻转
     *    K个0"，因此就是说：窗口内0的个数必须不大于K
     *【说明】
     *    1. 下面两个方法的区别主要是由于right更新的时机不同，导致计算最大连续1的长度计算时
     *也不相同。去别的根本原因：长度算不算right位置本身！！————这个问题只要涉及“双指针”、“滑
     * 动窗口”、“最小栈”等问题都会涉及。
     *    2.  不论right更新的时机怎样，right位置可能包含在区间，也可能不被包含在区间；但是left
     *位置永远是包含在区间内的！
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int zeroCount = 0, maxLen = 0;
        while (right<nums.length){
            if (nums[right]==0) zeroCount++;
            while (zeroCount>k){
                if (nums[left++]==0) zeroCount--;
            }
            maxLen = Math.max(maxLen,right-left+1);
            right++;
        }
        return maxLen;
    }

    public int longestOnes_01(int[] nums, int k) {
        int left = 0,right = 0;
        int zeroCount = 0;
        int res =0;
        while (right<nums.length){
            if (nums[right++]==0){ /**right指针更新的位置*/
                zeroCount++;
            }
            while (zeroCount>k){
                int leftNum = nums[left++];
                if (leftNum==0){
                    zeroCount--;
                }
            }
            res = Math.max(res,right-left); /**【注】由于right在开始就更新了，因此长度计算使用“right-left”*/
        }
        return res;
    }

    public int longestOnes_02(int[] nums, int k) {
        int count = 0;
        int left = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==0) count++;
            while (count>k){
                if (nums[left++]==0) count--;
            }
            res = Math.max(res,i-left+1);
        }
        return res;
    }

    /*63
    给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。

    网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。

    返回机器人能够到达右下角的不同路径数量。

    测试用例保证答案小于等于 2 * 109。
     */
    /*解法1：二维数组*/
    /**
     【关键】
            1. 二维dp时第一行 和 第一列 的初始化。。如果第一行某个位置有障碍物，则第一行后面的位置的路径数量
        都是0。。。。第一列也是同样的道理。
            第一行和第一列的初始化不能仅仅看当前位置的obstacleGrid是不是有障碍物，还要看它前面或者上面的那个
        位置。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        /*step1：初始条件的赋值dp[0][0]*/
        dp[0][0] = obstacleGrid[0][0]==0?1:0;
        /*step2：第一行和第一列的初始化。这里的初始化有点细节————
        *   条件一：obstacleGrid当前位置是可达的，即obstacleGrid的位置没有障碍物。
        *   条件二：dp前一个位置是可达的。因为第一行和第一列每一个位置只有一条路径，*/
        for (int i = 1; i < m; i++) {
            dp[i][0] = (obstacleGrid[i][0]==0&&dp[i-1][0]==1)?1:0;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = (obstacleGrid[0][i]==0&&dp[0][i-1]==1)?1:0;
        }
        /*step3：对于普遍位置的研究*/
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j]==0){
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m-1][n-1];
    }

    /*二维dp也可以使用下面的写法，总之这个题中二维的初始化是关键，不能放在双层for循环*/
    public int uniquePathsWithObstacles_1(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i]==1) break;
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0]==1) break;
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /*解法2：一维数组的优化*/
    public int uniquePathsWithObstacles_1dim(int[][] obstacleGrid) {
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0]==0?1:0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j]==0){
                    if (j-1>=0&&obstacleGrid[i][j-1]==0){
                        dp[j] += dp[j-1];
                    }
                }else
                    dp[j] = 0;
            }
        }
        return dp[n-1];
    }


    /*
    125. 验证回文串
    如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
    字母和数字都属于字母数字字符。

    给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     */
    /**
     *【思路】双指针相向而行。
     *【关键点】
     *      1. Character.isLetterOrDigit：判断一个字符是不是字母和数字
     *      2. Character.toLowerCase：将字母转换为小写字母
     *      3. while (l<r&&!Character.isLetterOrDigit(s.charAt(l))) l++：从l开
     *  始向右，跳过所有的不是字母和数字的字符
     *【错误点】
     *      1. 注意的是全程判断的是left位置以及right位置的字符！要使用“s.charAt(left)”获
     *  取某位置的字符。
     */
    public boolean isPalindrome(String s) {
        int l = 0,r = s.length()-1;
        while (l<r){
            /*step1：从left开始，跳过所有的不是”字母、数字“的字符。。
                这种写法类似于”三树之和“跳过相同值的写法，while循环中”l<r“这个条件不可少*/
            while (l<r&&!Character.isLetterOrDigit(s.charAt(l))){
                l++;
            }
            /*step2：从right开始，向左跳过所有不是”字母、数字“的字符。。*/
            while (l<r&&!Character.isLetterOrDigit(s.charAt(r))){
                r--;
            }
            /*step3：比较两个位置的字符是不是相等，如果不相等就直接返回false。
                【注意】到了这一步包含很多情况，但是这样的写法这些情况都包含了。可能的情况如下————
                    ①l和r是相等。此时下面的if一定是false，因此返回true。。。但是此时其实包含了
                 s没有一个字母数字，导致取出后就是空串，此时s是回文的；也可能l==r时就是唯一的合规
                 的字符，由于只有一个字符，因此s也是回文的。
                    ②l和r不相等，这就是一般情况。
            */
            if (Character.toLowerCase(s.charAt(l))!=Character.toLowerCase(s.charAt(r))){
                return false;
            }
            /*step4：经过上一步的判断以后，需要更新指针*/
            l++;
            r--;
        }
        return true;
    }


    /*另一种解法。自己的写法*/
    /**下面的写法应该有问题吧？？？？*/
    public boolean isPalindrome_own(String s) {
        int l=0,r=s.length()-1;
        while (l<r){
            char c1 = s.charAt(l);
            char c2 = s.charAt(r);
            if (!Character.isLetterOrDigit(c1)){
                l++;
            }else if (!Character.isLetterOrDigit(c2)){
                r--;
            }else if (Character.toLowerCase(c1)!=Character.toLowerCase(c2)){
                return false;
            }else {
                l++;
                r--;
            }
        }
        return true;
    }



    /*
    71. 简化路径
    给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为 更加简洁的规范路径。

    在 Unix 风格的文件系统中规则如下：

    一个点 '.' 表示当前目录本身。
    此外，两个点 '..' 表示将目录切换到上一级（指向父目录）。
    任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
    任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。
    返回的 简化路径 必须遵循下述格式：

    始终以斜杠 '/' 开头。
    两个目录名之间必须只有一个斜杠 '/' 。
    最后一个目录名（如果存在）不能 以 '/' 结尾。
    此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
    返回简化后得到的 规范路径 。
     */
    public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList<>();
        /*step1：将原字符串以"/"进行分割*/
        String[] split = path.split("/");
        /*step2：循环研究每一个分割的子串————
        *   ①如果是"."或者是""：说明没有实际意义，continue;
        *       【补充说明】为什么这里会有""的可能性？答：因为我们以'/'进行分割，像"//"、"////"这样的
        *     部分就会变成""、""！
        *   ②如果是".."：说明是上一级目录，要从栈中弹出一个
        *   ③其他的情况需要入栈*/
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("")||split[i].equals(".")){
                continue;
            }else if (split[i].equals("..")){
                if (!stack.isEmpty()){ /*从栈中弹出元素需要确保栈不是空*/
                    stack.pollLast(); /**err：不能使用pop()，只能使用pollLast()*/
                }
            }else {
                stack.offerLast(split[i]); /**err：*/
            }
        }
        /*step3：从栈底一次弹出每一个串拼接成答案。【注意】要挨个从栈底弹出*/
        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }
        return sb.length()==0?"/":sb.toString(); /**err：如果没有东西需要返回"/"*/
    }

    /**
     * 下面是使用Stack的写法，目的是为了说明区别“LinkedList也可以当作栈”使用。。使用LinkedList或
     *      者ArrayDeque代替栈使用时，入栈、出栈操作使用"offerLast"、"pollLast"代替！！
     */
    public String simplifyPath_stack(String path) {
        Stack<String> stack = new Stack<>();
        String[] split = path.split("/");

        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("")||split[i].equals(".")){
                continue;
            }else if (split[i].equals("..")){
                if (!stack.isEmpty()){
                    stack.pop();
                }
            }else {
                stack.push(split[i]);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);  // 因为是栈，从底部拼接
        }
        return result.length()==0?"/":result.toString();
    }


    /*97. 交错字符串
    给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

    两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

    s = s1 + s2 + ... + sn
    t = t1 + t2 + ... + tm
    |n - m| <= 1
    交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
    注意：a + b 意味着字符串 a 和 b 连接。
    * */
    /*解法1：网友解法。官方有滚动数组优化的解法*/
    public boolean isInterleave(String s1, String s2, String s3) {
        // 获取 s1 和 s2 的长度
        int m = s1.length(), n = s2.length();

        // 如果 s3 的长度不等于 s1 和 s2 长度之和，直接返回 false
        if (s3.length() != m + n) return false;

        // 初始化动态规划表 dp，
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 初始状态：两个空字符串可以组成一个空字符串
        dp[0][0] = true;

        // 处理 s1 为空的情况，检查 s2 是否能单独组成 s3 的前 j 个字符
        for (int j = 1; j <= n; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[0][j] = dp[0][j - 1];
            } else {
                break; // 如果字符不匹配，后续也无法匹配，直接退出循环
            }
        }

        // 处理 s2 为空的情况，检查 s1 是否能单独组成 s3 的前 i 个字符
        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = dp[i - 1][0];
            } else {
                break; // 如果字符不匹配，后续也无法匹配，直接退出循环
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        // 返回结果，dp[m][n] 表示整个 s1 和 s2 是否能组成 s3
        return dp[m][n];
    }


    /*230. 二叉搜索树中第 K 小的元素
     */
    /**
     *【说明】第k小就是从小到大排序的第k个；第k大就是从大到小排序的第k个。。。。
     *      体现再二叉搜索树的区别无非就是：先往哪个孩子节点走的问题
     *【思路】二叉树第k小的元素比较简单，就是中序遍历的第k个元素
     *【注意】官方的解法中，有一些更骚的解法
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode cur = stack.pop();
                k--;
                if (k==0){
                    return cur.val;
                }
                root  = cur.right;
            }
        }
        return -1;
    }

    /*LCR 174. 寻找二叉搜索树中的目标节点
    某公司组织架构以二叉搜索树形式记录，节点值为处于该职位的员工编号。请返回第 cnt 大的员工编号。
     */
    /**
     *【思路】二叉搜索树中序是递增序列，左-中-右。————因此右-中-左是降序的，此时的第cnt个节点值就是答案了。
     * */
    /*解法1：递归*/
    int resFindTargetNode, cnt;
    public int findTargetNode(TreeNode root, int cnt) {
        this.cnt = cnt;
        dfs(root);
        return resFindTargetNode;
    }

    private void dfs(TreeNode root) {
        if (root==null) return;
        /**中序遍历是“左-中-右”，这里要改成“右-中-左”*/
        dfs(root.right); //先是右
        if (--cnt==0){
            resFindTargetNode = root.val;
            return;
        }
        dfs(root.left);
    }

    /*解法2：迭代*/
    public int findTargetNode_diedai(TreeNode root, int cnt) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.right; /**由于寻找第cnt大的节点，因此需要是中序遍历的倒序，即：右、根、左。。。因此root先一直往右子树走*/
            } else {
                TreeNode cur = stack.pop();
                if (--cnt == 0) {
                    return cur.val;
                }
                root = cur.left;
            }
        }
        return -1;
    }


    /*395. 至少有 K 个重复字符的最长子串
    给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

如果不存在这样的子字符串，则返回 0。
     */
//    public int longestSubstring(String s, int k) {
//
//    }


    /*329. 矩阵中的最长递增路径
    给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
     */
//    public int longestIncreasingPath(int[][] matrix) {
//
//    }


    //581
    public int findUnsortedSubarray(int[] nums) {
        int start = -1, end = -2;
        int maxNum = nums[0], minNum = nums[nums.length - 1];
        /*step1：从左向右遍历。标记这样的位置————
        *       我们当前来到的位置是i，之前碰到的最大数是maxNum，如果nums[i]<maxNum（说明nums[i]比它前面的最大值要小，理论上至少
        * 要等于前面所有数的最大值），则位置i更新为结束边界*/
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < maxNum) {
                end = i;
            }
            maxNum = Math.max(maxNum, nums[i]);
        }
        /*step2：从后向前遍历。标记这样的位置————
        *       我们当前来到的位置是i，之前的遍历中碰到的最小值是minNum，如果nums[i]>minNum（说明nums[i]比它右边的最小值要大，理论上
        * 任何一个nums[i]应该不大于它右边所有数的最小值），则位置i更新为开始边界*/
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > minNum) {
                start = i;
            }
            minNum = Math.min(minNum, nums[i]);
        }
        return end - start + 1; /**【注】start、end的初始值就包含了“原数组就完整有序”的情况。*/
    }


    //221最大正方形

    /**
     *【解题思路】
     *      1. 边缘位置的初始化；
     *      2. (1,1)开始的右下区域，任何一个位置的正常形边长等于“左上角三个位置值”的最小值 再加1；
     */
    public int max(char[][] matrix){
        int m = matrix.length,n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i=0;i<n;i++){
            dp[0][i] = matrix[0][i]-'0';
            max = Math.max(max,dp[0][i]);
        }
        for (int i=0;i<m;i++){
            dp[i][0] = matrix[i][0]-'0';
            max = Math.max(max,dp[i][0]);
        }
        for (int i=1;i<m;i++)
            for (int j=1;j<n;j++){
                if (matrix[i][j]=='1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    max = Math.max(dp[i][j],max);
                }
            }
        return max*max;
    }






    /*120
    给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     */
//    public int minimumTotal(List<List<Integer>> triangle) {
//        LinkedList<Integer> res = new LinkedList<>();
//        res.add(triangle.get(0).get(0));
//        for (int i = 1; i < triangle.size(); i++) {
//            List<Integer> integers = triangle.get(i);
//            for (int j = 0; j < integers.size(); j++) {
//                Integer cur = integers.get(i);
//                res.add(j);
//            }
//        }
//    }


    /*210 课程表Ⅱ
    现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     */



    /*96. 不同的二叉搜索树
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */
    /**
     【建议看讲解】https://leetcode.cn/problems/unique-binary-search-trees/solutions/6693/hua-jie-suan-fa-96-bu-tong-de-er-cha-sou-suo-shu-b/
     * */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; /*代表：0个节点的二叉搜索树有1种*/
        dp[1] = 1; /*代表：1个节点的二叉搜索树有1种*/
        /**关于双重for循环的几个重要问题=====
         * 1. 双层for循环的目的：求解dp[i]————即i个节点构成多少种二叉搜索树。
         * 2. 如何计算呢？计算dp[i]时，我们分别假设以j为根节点，因此dp[i]是以下所有方案的结果和：
         *      方案1：如果是第一个节点为根节点，则左子树有0个节点，右子树有i-1个节点，此时有dp[0]*dp[i-1]种
         *  二叉搜索树；
         *      方案2：如果是第二个节点为根节点，则左子树有1个节点，右子树有i-2个节点，此时有dp[1]*dp[i-2]种
         * 二叉搜索树；
         *      ……………………
         *      方案j：如果是第j个节点为根节点，则左子树有j-1个节点，右子树有i-j个节点，此时有dp[j-1]*dp[i-j]种
         * 二叉搜索树；
         *      综上，上述的所有方案加和即为总数。
         */
        for (int i = 2; i < n+1; i++) { /*依次求解：i个节点组成的二叉搜索树有多少种*/
//            for (int j = 1; j <= n; j++) { /*求解方式：分别以第j个节点为根的搜索树有多少种，加和就是dp[i]。。。【补充说明】如果这里j从0开始，则范围j<n，就不能带等于了*/
//                dp[i] = dp[j-1]*dp[i-j]; /*以j为根的二叉搜索树有多少种？左边就是j-1个节点；右边就是i-j个节点。（左右子树一共i-1个节点正确）*/
            for (int j = 1; j <= n; j++) { /*求解方式：分别求出以“第j个节点为根”的搜索树有多少种，加和就是dp[i]*/
                /**【注】这是一个累加的过程。分别是第1个节点作为根的方案数、第2个节点作为根的方案数.....因此总方案数是这些的加和。
                 【注】j的含义是”把第几个节点作为根节点“，因此j取0是没有意义的，应该从1开始取*/
                dp[i] += dp[j-1]*dp[i-j]; /*求出以j（第j个节点）为根的二叉搜索树有多少种？左边就是j-1个节点；右边就是i-j个节点。（左右子树一共i个节点正确）*/
            }
        }
        return dp[n];
    }


    /*516. 最长回文子序列
    给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     */
//    public int longestPalindromeSubseq(String s) {
//
//    }
}
