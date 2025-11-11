package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/9/9 10:10
 */
public class codetop_unskilled_6_10 {
//    /*手撕堆排序
//
//     */
//
//
//
//
    /*297
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在
一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原
数据。
请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格
式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
* */

    /**
     【说明】
            第一种大情况， 如果存储null，则————
                ①层序遍历可以实现序列化和反序列化
                ②先序遍历、后序遍历也可以实现此题
                ③❗❗❗❗但是中序遍历是不能实现的，比如：
                  中序序列里 “当前节点” 在左、右子树之间；我们没有像先序或后序那样的“根节点顺
                序”，所以无法仅靠中序序列恢复原树，哪怕有 #，举个例子
                  第一棵树
                          1
                         / \
                        2  3
                  第二棵树
                         2
                          \
                           1
                            \
                             3
                  会发现上面两棵树中序遍历的结果，即使是存储null节点，中序遍历也是一样的，即"#,2,#,1,#,3,#"
            第二种大情况， 如果不存储null，则————
                  ①三种遍历，单一使用的话，都不能实现 二叉树的还原
                  ②中序+先序；中序+后序，这2种可以实现二叉树的还原
                  ③❗❗❗❗先序+后序，不能实现二叉树的还原，深度解释原因：
                        1️⃣原因：先序和后序遍历都只告诉你“访问顺序”，但没有标记左右孩子的存在与否。对于只有一个孩
                    子的节点，你根本不知道它是左孩子还是右孩子。所以在构建树时会产生多种可能。
                        2️⃣什么时候可以唯一恢复
                             a.如果是满二叉树（full binary tree）
                                 每个非叶子节点一定有两个孩子，那么先序+后序可以唯一恢复。因为不可能出现“单孩子节点”带来的歧义。

                            b.如果把空节点也存储
                                 比如先序里写成 1,2,#,#,#，这样可以区分左右孩子缺不缺，那么用先序（或后序）一个序列就可以唯一
                            恢复；先序+后序更没问题。
                        3️⃣举个例子
                            第一棵树A：
                                     1
                                    /
                                   2
                            第二棵树B：
                                     1
                                      \
                                       2
                            此时A、B两个树的先序遍历都是“1，2”；后序遍历都是“2，1”。但是很明显2个树是不一样的，因此反序列化
                            的结果到底是哪一棵树呢？？综上，不能唯一恢复一颗二叉树。
     */
    /*解法1：层序遍历的写法*/
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root==null) return "null";
            StringBuilder res = new StringBuilder();
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                TreeNode cur = queue.poll();
                /**
                 这里有两种写法：
                 一种就是下面的if里面添加continue，因此命中if后后面的就不会执行了。
                 第二种就是将后面的代码整体写入到else分支
                 * */
                if (cur==null){
                    res.append("null,");
                    continue;
                }
                res.append(cur.val).append(",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            return res.toString();
        }

        /**
         【层序遍历的反序列化】
            总体的流程————
                step1：如果序列化的结果data就是“null”，说明根节点就是null，因此直接返回null。
                step2：将序列化的结果data根据“,”进行划分成数组strs。
                step3：先根据第一个串解析出root节点的值，创建节点。记录root节点 并且 入队列。
                step4：记录index。因为后面需要不断的依次从strs中拿每个字符串进行解析，因此需要使
            用index记录当前拿到哪一个位置。
                step5：使用while循环完成节点的左右孩子的拼接。详细如下————
                    ①先从queue弹出一个节点，表示父节点；
                    ②拿到index位置的字符串————
                      如果不是null，则创建节点给cur的left孩子，并且将这个节点入队列；这个操作是
                    第一个if块完成
                      如果index位置的字符串就是null，则由于指针的默认值就是null，因此不用额外的
                    操作。
                       更新index，即index++;
                    ③拿到index位置的字符串————
                      跟②是类似的操作，只不过这里针对的是right孩子
         */
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("null")) return null;
            String[] split = data.split(",");
            LinkedList<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            queue.offer(root);
            int index = 1; /*标识当前应该解析哪一个位置了*/

            while (!queue.isEmpty()){
                TreeNode curnode = queue.poll();
                /**chatgpt：需要判断index是不是超出了strs的长度，不校验应该是有问题的..
                    但是实际上不限制也没问题的*/
                /*拼接构造左孩子。如果是null就不用管只用更新index，因为指针的默认值就是null*/
                if (!"null".equals(split[index])){
                    TreeNode node = new TreeNode(Integer.parseInt(split[index]));
                    curnode.left = node;
                    queue.offer(node);
                }
                index++;

                if (!"null".equals(split[index])){
                    TreeNode node = new TreeNode(Integer.parseInt(split[index]));
                    curnode.right = node;
                    queue.offer(node);
                }
                index++;
            }
            return root;
        }
    }

    /*方法2：前序遍历的方式实现*/
    class Codec_preorder {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root==null) return "#";
            return root.val + "," + serialize(root.left) + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            LinkedList<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
            return buildTree(list);
        }


        private TreeNode buildTree(LinkedList<String> list) {
            String str = list.removeFirst();
            if (str.equals("#")) return null;
            int value = Integer.parseInt(str);
            TreeNode root = new TreeNode(value);
            root.left = buildTree(list);
            root.right = buildTree(list);
            return root;
        }
    }


    /*方法3：后序遍历实现*/
    public class Codec_postorder {
        // 后序遍历序列化
        public String serialize(TreeNode root) {
            if (root == null) return "#";
            return serialize(root.left) + "," + serialize(root.right) + "," + root.val;
        }

        // 反序列化
        public TreeNode deserialize(String data) {
            LinkedList<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
            return build(list);
        }

        private TreeNode build(LinkedList<String> list) {
            if (list.isEmpty()) return null;
            String val = list.removeLast(); //从后往前
            if (val.equals("#")) return null;
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.right = build(list); //注意顺序：后序逆序恢复时，先构建右子树
            node.left = build(list);
            return node;
        }
    }
//
//
//
    /*349
        给定两个数组 nums1 和 nums2，返回它们的交集。

        结果中的每个元素 唯一

        顺序不限
     */
    /**使用两个set的解法————
        1. 一个set用于存储nums1中所有的数。
        2. 一个set用于存放共有的数。如果遍历到nums2的数在set中就放进result，说明是两个数组共有的
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int num:nums1){
            set.add(num);
        }

        HashSet<Integer> result = new HashSet<>();
        for (int num:nums2){
            if (set.contains(num)){
                result.add(num);
            }
        }

        int[] res = new int[set.size()];
        int i=0;
        for (Integer cur:set){
            res[i++] = cur;
        }
        return res;
    }
//
//
    /**解法2：排序+双指针的解法
      步骤：①对两个数组分别排序；
            ②从索引为0的位置开始，根据nums1[i]和nums2[j]的大小分别移动指针。任何一个指针越界停止即可*/
    public int[] intersection_1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                // 避免重复加入
                if (result.isEmpty() || result.get(result.size() - 1) != nums1[i]) {
                    result.add(nums1[i]);
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] ans = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            ans[k] = result.get(k);
        }
        return ans;
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
    class LFUCache {
        private final int capacity;
        private int minFreq; /*目前LFU中所有节点频率的最小值。作用是啥？？在容量满的时候，需要删节点，能快速的根据频率得到删除的节点(准确的说应该是”最小频率对应的链表的头节点“)*/
        private final Map<Integer,Node> map; /*key——>Node。根据key获取Node，用于快速的查询*/
        private final Map<Integer,DoubleLinkedList> freqTable; /*访问频率——>链表。即访问频率相同的节点组成链表，根据访问频率能得到这个链表的头节点*/


        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
            this.map = new HashMap<>();
            this.freqTable = new HashMap<>();
        }


        /*获取节点————啥都不说直接从map获取。得到的Node不是null，说明有此key*/
        public int get(int key) {
            /*step1：先根据key从map拿Node*/
            Node node = map.get(key);
            /*情况1：拿到了Node，说明是有这样的key————即访问节点成功。
                访问节点后应该做什么？？应该更新这个Node的访问频率
             */
            if (node!=null){
                increaseFreq(node);
                return node.value;
            }
            //情况2：如果得到的Node是null，说明没有key对应的节点返回-1。
            return -1;
        }

        /*存节点————啥都不说先从map中获取。
            情况1：如果获取到的Node不是null，说明访问节点成功。则①更新Node对应的value；②更
                新Node对应的频率。
            情况2：如果获取到的节点是null，说明LFU中并没有这个key，需要新创建节点。则①先判断
                LFU的容量是不是满了(如果满了则需要删除一个节点，注意从mao中移除)；②*/
        public void put(int key, int value) {
            if (capacity==0) return;

            Node node = map.get(key);
            if (node!=null){
                node.value = value;
                increaseFreq(node);
            }else {
                if (map.size()==capacity){
                    DoubleLinkedList minList = freqTable.get(minFreq);
                    Node toRemove = minList.removeLast();
                    map.remove(toRemove.key);
                }
                Node newNode = new Node(key, value);
                map.put(key,newNode);
                freqTable.computeIfAbsent(1,k->new DoubleLinkedList()).addFirst(newNode);
                minFreq = 1;
            }
        }

        /*增加某个node的频率。
        * 【注】某个node的频率增加了，就会导致————
        *       它要从原来频率的链表移除；
        *       如果移除后链表为空并且还是minFreq,则需要更新minFreq；
        *       最后要把这个node添加到新的频率对应的链表，要添加到头部(同时新
        *    频率对应的链表可能还没有呢，这种情况下需要创建链表并把这个节点放进去)。*/
        private void increaseFreq(Node node) {
            /*step1：将node从原来频率的链表删除*/
            int freq = node.freq; //①得到节点的访问频率
            DoubleLinkedList list = freqTable.get(freq); //②拿到这个频率值对应的链表
            list.remove(node); //③从链表中删除
            /*step2：判断是否需要更新最小频率*/
            if (freq==minFreq&&list.isEmpty())
                minFreq++;
            /*step3：更新node频率 并将 node添加到对应的链表的首位*/
            node.freq++; //①更新node对应的频率
            freqTable.computeIfAbsent(node.freq, k -> new DoubleLinkedList()).addFirst(node); //②将node添加到访问频率对应的链表首位
        }


        /**
         * Node内部类：用于存放一个节点的完整信息(仅仅是信息的封装，因此不涉及节点的操作)，这些信息包
         *      含————key(键)、value(值)、freq(对应的访问频率)
         */
        private static class Node {
            int key, value, freq;
            Node prev, next;
            Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.freq = 1; /**【注】新创建的节点访问频率是1*/
            }
        }

        /**
         * Node组成的链表的形式，并包含常见的操作
         */
        private static class DoubleLinkedList {
            Node head, tail;
            int size;

            DoubleLinkedList() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            /*将node添加到链表首位————即添加到head后面。
            * 什么时候使用？？任何时候需要访问（包括创建node、更新node频率或者value）时，都需要将node移动到
            *       最新freq值对应链表的首位*/
            void addFirst(Node node) {
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
                size++;
            }

            /*删除某个节点————就是将它的prev和它的next连接起来
            * 什么时候使用？？
            *       node的频率增加时需要从原来的频率链表删除*/
            void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            Node removeLast() {
                if (size > 0) {
                    Node node = tail.prev;
                    remove(node);
                    return node;
                }
                return null;
            }

            boolean isEmpty() {
                return size == 0;
            }
        }
    }

    /*47 全排列Ⅱ
            见回溯795行的写法
     */
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, boolean[] used, ArrayList<Integer> path) {
        if(path.size()==nums.length){
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                if (i>0&&nums[i-1]==nums[i]&&!used[i-1]) continue;
                used[i] = true;
                path.add(nums[i]);
                backtrack(nums,used,path);
                used[i] = false;
                path.remove(path.size()-1);
            }
        }
    }

    /*123. 买卖股票的最佳时机 III
    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */


    /*7.整数反转
        给定一个 32 位有符号整数 x，返回将其数字部分反转后的结果。如果反转后超出了 32 位有符号整数的范围 [-2^31, 2^31 - 1]，则返回 0
     */
    /**
     *【强烈建议】强烈建议使用解法 reverse_
     *【关键】必须先在step2进行校验，校验后才能在step3更新res。
     *【说明】
     *      1. 在计算的过程中会发现————
     *        ①（这个结论比较重要，因为一般除数都是正数）负数除以一个正数，不论是求整除还是求余结果都是负数，比如：
     *          int x = -19;======> x%2 = -1
     *                      ======> x/2 = -9
     *        ②负数除以一个负数，求余的结果是负数，但是求整除的结果是正数，比如：
     *          int x = -19;======> x%(-18) = -1
     *                      ======> x/-2 = 9
     */
    public int reverse(int x) {
        int res = 0;
        while (x!=0){ /**【注】结束的标志是x==0，而不是x<0（每一轮就将x除10留下整数部分）*/
            /*step1：拿出最后一个数字digit；并更新x*/
            int digit = x%10;
            x /= 10;
            /*step2：校验不会越界。【说明】到这里为止最后一个数字digit还没有加到结果上
                ①如果res已经大于”Integer.MAX_VALUE/10“，由于还要加digit，因此必然越界；
                  如果res正好等于”Integer.MAX_VALUE/10“，由于还要加digit，因此要确保digit小于7.
                ②如果res已经小于”Integer.MIN_VALUE/10“，由于还要加digit，因此必然越界；
                  如果res正好等于”Integer.MIN_VALUE/10“，因此要确保digit<-8*/
            if (res>Integer.MAX_VALUE/10||            /**err：这里需要对res进行校验，而不是校验x!!!!*/
                    (res==Integer.MAX_VALUE/10 &&digit>7))
                return 0;
            if (res<Integer.MIN_VALUE/10||
                    (res==Integer.MIN_VALUE/10 &&digit<-8))
                return 0;
            /*step3：每一次res需要乘以10然后再加digit*/
            res = res*10 +digit;
        }
        return res;
    }


    /**
     说明：由于初始的数是x，即输入的 -2147483648<=x<=2147483647，所以翻转后的最后一位是1或2并不会
     导致溢出。。因此其实下面的写法中 根本不存在“res==Integer.MAX_VALUE/10”或者“res=Integer.MIN_VALUE/10”的
     可能的，具体原因如下————
            情况1：如果最终不会越界int，则res必然符合要求。
            情况2：如果最终会越界int，则res越界之前的部分一定是“大于Integer.MAX_VALUE/10”或者“小于Integer.MIN_VALUE/10”
        的。————不存在等于的情况
        综上，整个题目的代码可以简化为下面的形式
     */
    public int reverse_(int x) {
        int res = 0;
        while (x != 0) { /**【注】结束的标志是x==0，而不是x<0（每一轮就将x除10留下整数部分）*/
            int digit = x % 10; /**根据注释中的”【说明】“可知如果x是负数，digit永远是负数，因此从开始更新res就永远都是负数*/
            x /= 10;
            if (res > Integer.MAX_VALUE / 10) return 0; /*判断越界可以简化为这里的形式*/
            if (res < Integer.MIN_VALUE / 10) return 0;
            res = res * 10 + digit;
        }
        return res;
    }


    /*958
    给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。

在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
     */
    /**层序遍历方法解题的时候，每一层的节点必须分明吗？？——————即访问每一层之前必须结合”queue.size()以
     及for循环来进行吗“？？，应该是不需要的！！
     【补充说明】完全二叉树的定义是：任何一层节点必须是从左到右依次填满。如果某个节点是null，则它所处的那
     层右边的节点都是null，它下面所有层的节点都是null。
     【思考】二叉树层序遍历的题目中，什么时候必须严格区分每一层？？什么时候不用严格区分每一层？？
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root==null) return true;
        boolean hasNull = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (hasNull&&cur!=null){
                return false;
            }
            if (cur==null){
                hasNull = true;
            }else {
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return true;
    }

    /**另一种写法*/
    public boolean isCompleteTree_(TreeNode root) {
        if (root==null) return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasNull = false;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur.left!=null){
                if (hasNull) return false;
                queue.offer(cur.left);
            }else {
                hasNull  =true;
            }

            if (cur.right!=null){
                if (hasNull) return false;
                queue.offer(cur.right);
            }else {
                hasNull = true;
            }
        }
        return true;
    }

    /*LCR 155. 将二叉搜索树转化为排序的双向链表
将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
 */
    /**
     【强烈建议】建议使用解法 treeToDoublyList
     */
    class NodeNode {
        public int val;
        public NodeNode left;
        public NodeNode right;

        public NodeNode() {}

        public NodeNode(int _val) {
            val = _val;
        }

        public NodeNode(int _val, NodeNode _left, NodeNode _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /**
     * 自己写的代码是有问题的
     */
//    public Node treeToDoublyList(Node root) {
//        if (root==null) return root;
//        Stack<Node> stack = new Stack<>();
//        Node prev = null;
//        Node head = null;
//        while (root!=null||!stack.isEmpty()){
//            if (root!=null){
//                stack.push(root);
//                root = root.left;
//            }else {
//                Node cur = stack.pop();
//                if (prev==null) prev = cur; //错误1：没有记录链表头，导致最后返回prev时其实仅仅返回了最后一个节点
//                else{
//                    prev.right = cur;
//                    prev.left = null; //错误2：不能把prev.left置为null，会导致前向链断开
//                    prev = prev.right;
//                }
//                root = cur.right;
//            }
//        }
//        return prev;
//    }

    /*自己重新写的一版代码*/
    public NodeNode treeToDoublyList_review(NodeNode root) {
        NodeNode prev = null,head = null;
        if (root==null) return root;
        Stack<NodeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                NodeNode cur = stack.pop();
                if (head==null) {
                    head = cur;
                    prev = head; /**这里head已经是cur了，因此可以写成”prev=cur“*/
                } else {
                    prev.right = cur;
                    cur.left = prev;
                    prev = prev.right; /**这里prev.right已经是cur了，因此可以写成”prev=cur“*/
                }
                /**由于if-else中都会有”prev=cur“，因此可以把”prev=cur“写到这里，就不用在if-else重复写了*/
                root = cur.right;
            }
        }
        /**err：记得首尾相连*/
        head.left = prev;
        prev.right = head;
        return head;
    }


    /*非递归的写法
        你在第一次弹栈时用 if (head==null) head=cur; 记录了头结点。
        每次访问节点时把 prev 和 cur 互相链接：prev.right = cur; cur.left = prev;
        每轮结束后 prev = cur。
        遍历完后再首尾相连：head.left = prev; prev.right = head;
        返回 head。
     */
    /**
     * 【关键】这个题目中必须使用两个局部变量————
     *      ①一个指针prev用于在遍历过程中完成节点的拼接
     *      ②第二个指针head用于记录头结点，以及最后进行首尾节点的拼接。
     *      【注意】中序遍历的第一个节点大概率不是root，因此必须要使用额外的节点来记录头结点
     * */
    public NodeNode treeToDoublyList(NodeNode root) {
        if (root == null) return root;
        Stack<NodeNode> stack = new Stack<>();
        NodeNode prev = null; /**prev记录的是已经连接部分的链表的最后一个节点————即实时操作的指针*/
        NodeNode head = null; /**head仅仅是记录头节点*/
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                NodeNode cur = stack.pop();
                if (prev == null){
                    head = cur; /**err：这里是记录头节点，其实就是中序遍历的最小值节点*/
                    prev = cur;
                } else {
                    prev.right = cur;
                    cur.left = prev; /**err：真正要做的是“把当前节点的left指针指向prev节点，而不是修改prev节点的左指针”*/
                    prev = prev.right;
                }
                root = cur.right; /**err：记得更新root走cur的右子树*/
            }
        }
        /*最后记得首尾相接，构成循环链表*/
        prev.right = head; /**prev指针指向已经连接链表的最后一个节点，因此出了while循环时prev指向的就是中序遍历的最后一个节点*/
        head.left = prev;
        return prev;
    }


    /*递归形式的写法*/
    NodeNode prev = null,head = null;
    public NodeNode treeToDoublyList_digui(NodeNode root) {
        if (root==null) return null;
        dfs(root);

        head.left = prev;
        prev.right = head;
        return head;
    }

    private void dfs(NodeNode root) {
        if (root==null) return;
        /*step1：先去遍历它的左子树*/
        dfs(root.left);
        /*step2：访问当前节点。
        【二叉树题目的本质】四种遍历方式中，在访问节点的时候做一定的逻辑处理*/
        if (head==null){
            head = root;
        }else {
            prev.right = root;
            root.left = null; //这里为什么是root.left=null
        }
        prev = root;
        /*step3：访问它的右子树*/
        dfs(root.right);
    }


    /*518.。。。322求解的是最少的硬币数，518求解所有的方案数。。。
        给定不同面额的硬币 coins 和一个总金额 amount，计算并返回可以凑成总金额的硬币组合数。硬币可以无限次使用，顺序不同的组合
    视为同一种（只要硬币种类和个数一样就算同一组合）。
     */

    /**
      【说明】322和518的区别最终就体现在了dp[i]的求解————即区别集中体现在了每一个位置的决策过程
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <=amount; j++) {
                dp[j] = dp[j] + dp[i-coins[i]]; /**【注意区别】方案数的话就是对应位置的方案加上去；类似518的话就要通过“Math.min”计算较小值*/
            }
        }
        return dp[amount];
    }

    /*LCR 143 子结构判断
    给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
注意，空树 不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
     */
    /**
     *【复杂度分析】
        时间：最坏情况要对 A 的每个节点调用一次 match，match 最坏遍历 B 所有节点，所以 O(|A|*|B|)。
        空间：递归深度 O(h)，h 为树高。
     */
    /*方法1：DFS递归的写法
        A、A.left、A.right分别匹配，有一个匹配成功即返回true
    * */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A==null&&B==null) return true; /**【注】chatgpt说不要这一句就可以，空树不是任何树的子树*/
        if (A==null||B==null) return false;
        return match(A,B)||
                isSubStructure(A.left,B)||
                isSubStructure(A.right,B);
    }

    private boolean match(TreeNode A, TreeNode B) { /*递归的写法判断是不是匹配*/
        /*base case:
            B == null 在 match 中返回 true：因为 match 的语义是“B 的所有节点是否能在 A 对应位置上被匹配”。如果 B 已经
        走完（所有子节点都验证完），说明匹配成功（即 B 的结构已全部匹配），所以返回 true。
            A == null 而 B != null 返回 false：说明 A 的这条路径结束了，但 B 还有节点需要匹配，无法满足。
            A.val != B.val 返回 false，说明遍历到这个节点发现匹配不上了，因此返回false
        * */
        if (B == null) return true;
        if (A == null) return false;
        if (A.val != B.val) return false;
        return match(A.left, B.left) && match(A.right, B.right);
    }


    /*方法2：将上面的递归改成迭代的写法
        主方法完成以A为根的树的遍历，如果发现某个节点和B的值相等，则调用”isMatch“看看是不是能完成B的匹配
        外层用一个栈遍历 A 的每个节点。内层用另一个栈匹配 B 的每个节点。这样就完全不依赖递归调用栈，适合处
    理特别深的大树。
    */
    /**
     【复杂度分析】
         时间复杂度：和递归版一样，最坏 O(|A| * |B|)。
         空间复杂度：O(h1 + h2)，h1 为遍历 A 用的栈深度，h2 为匹配 B 时用的栈深度，总体受树高限制。
     【方法的作用】
        层序遍历A树，如果发现某个节点和B的值相等，则从这个节点开始调用isMatch看看是不是能和B匹配上
     */
    public boolean isSubStructure_diedai(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(A);
        while (!queue.isEmpty()) {
            /**与层序遍历的区别1：研究每一层节点之前不用获取queue的size。*/
            TreeNode cur = queue.poll();
            /**与层序遍历的区别2：添加逻辑————如果发现和B的value相同，则使用”isMatch“方法判断是不是可以匹配成功*/
            if (cur.val == B.val && isMatch(cur, B)) return true;
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
        return false;
    }

    private boolean isMatch(TreeNode A, TreeNode B) { /*A,B两棵树同步进行层序遍历*/
        LinkedList<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{A,B});
        while (!queue.isEmpty()){
            TreeNode[] cur = queue.poll();
            TreeNode A0 = cur[0],B0 = cur[1];
            if (B0==null) continue;
            if (A0==null) return false;
            if (A0.val!=B0.val) return false;
            /*
                由于上面已经充分考虑过了A0、B0是不是null的情况，因此这里直接offer就可以。。也不用提前判
             断是不是空。。。这种集合放入null并不会报空指针异常(那些涉及排序的集合才会报空指针异常)
             */
            queue.offer(new TreeNode[]{A0.left,B0.left});
            queue.offer(new TreeNode[]{A0.right,B0.right});
        }
        return true;
    }

    /**
     *需要判断一下下面的代码有没有问题？？？？
     */
//    public boolean isSubStructure1(TreeNode A, TreeNode B) {
//        if (A==null&&B==null) return true;
//        if (A==null) return false;
//        return isMatch1(A,B)||
//                isSubStructure1(A.left,B)||
//                isSubStructure1(A.right,B);
//    }
//
//    private boolean isMatch1(TreeNode a, TreeNode b) {
//        if (a==null&&b==null) return true;
//        if (b==null) return true;
    /**应该是有问题的，这里直接通过”a.val“获取a的值，但是没有判断它不是空*/
//        return a.val==b.val&&
//                isMatch1(a.left,b.left)&&
//                isMatch1(a.right,b.right);
//    }
//
//
    /*572. 另一个树的子树
    给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
    * */
    /**
     【总述】这个题要求“树长的一模一样”，是null的时候都得是null！
     【题目的理解】
         题目与“子结构”题（LCR 143）相似，递归和迭代的写法和非递归也是类似的代码几乎没啥区别。。。但这个题判
     断条件更严格————root中的某一个子树 和 subRoot必须一摸一样，即subRoot是null的时候root子树对应的节点也
     必须是null...但是LCR 143就不是，143中root的子树包含subRoot的结构就可以，两个子树不要求完全相等
     【复杂度分析】
         解法1：递归。
          复杂度：时间复杂度O(mn)，空间复杂度O(h)。。。。其中m，n分别是root、subRoot的节点数；h是递归的深度
         解法2：最优的解法应该是序列化出来(对于null节点也需要记录)，转换成求解子串存在与否的问题，使用KMP算法求解。
         复杂度：时间复杂度O(m+n)，空间复杂度O(m+n)————空间复杂度主要体现在存储序列化结果
     */
    /*解法1：递归的写法*/
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root==null&&subRoot==null) return true;
        if (root==null||subRoot==null) return false;
        return isSameTree(root,subRoot)||
                isSameTree(root.left,subRoot)||
                isSameTree(root.right,subRoot);
    }

    private boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root==null&&subRoot==null) return true;
        if (root==null||subRoot==null) return false;
        if (root.val!=subRoot.val) return false;
        return isSameTree(root.left,subRoot.left)&&
                isSameTree(root.right,subRoot.right);
    }

    /*解法2：迭代法的实现*/
    public boolean isSubtree_diedai(TreeNode root, TreeNode subRoot) {
        if (subRoot==null&&subRoot==null) return true;
        if (root==null||subRoot==null) return false;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur.val==subRoot.val&&isSame(cur,subRoot)) return true;
            if (cur.left!=null) queue.offer(cur.left);
            if (cur.right!=null) queue.offer(cur.right);
        }
        return false;
    }

    private boolean isSame(TreeNode root, TreeNode subRoot) {
        LinkedList<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{root,subRoot});

        while (!queue.isEmpty()){
            TreeNode[] cur = queue.poll();
            TreeNode rootNode = cur[0],subRootNode = cur[1];
            /**err：这里如果return true，就是错误的。第二个用例过不了.*/
            if (rootNode==null&&subRootNode==null) continue;
            if (rootNode==null||subRootNode==null) return false;
            if (rootNode.val!=subRootNode.val) return false;
            queue.offer(new TreeNode[]{rootNode.left,subRootNode.left});
            queue.offer(new TreeNode[]{rootNode.right,subRootNode.right});
        }
        return true;
    }


    /*498.对角线遍历
        给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
     */
    /**【模拟法】
     1. i、j变量表示当前研究的位置，dir表示走向（dir=1表示向右上方移动，dir=-1表示向左下方移动）
     2. 如果正在向右上方（即dir=1）移动，有三种情况————
     情况1：i==0。即来到了第一行，此时从右边的元素接着研究（即j++,dir=-1）;
     情况2：j==n-1.即来到了最后一列，此时从下边的元素接着研究（即i++,dir=-1）;
     情况3：其他的普遍位置（i--,j++,dir不变）
     如果正在向左下方（即dir=-1）移动，也有三种情况————
     情况1：i==m-1.即来到最后一行，此时应该从右边的元素继续研究（即j++，dir=1）;
     情况2：j==0。即来到了第一列，此时应该从下边的元素继续研究（即i++，dir=1）;
     情况3：其他的普遍位置（i++,j--,dir不变）
     【关键点、易错点】
     1. dir=1时，向右上方移动，此时有一个特殊位置即右上角的位置（i=0,j=n-1），到了这个位置
     应该向下移继续，因此这个位置应该当作最后一列的位置处理————因此dir=1时的三种情况，应该先判断”j==n-1“
     2. dir=-1时，向左下方移动，此时也有一个特殊位置即左下角的位置（i=m-1,j=0），到了这个
     位置应该向右移，因此这个位置应该当作最后一行的位置处理————因此dir=-1时的三种情况，应该先判断”i==m-1“
     综上，应该先判断是不是到最后一列、最后一行这种情况~~~~
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        int i = 0, j = 0, dir = 1;
        for (int k = 0; k < m * n; k++) {
            res[k] = mat[i][j];
            if (dir == 1) {
                /*下面的判断顺序会导致类似错误“java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3”
                因为如果此时(i,j)来到右上角的位置（0，n-1），会发现i==0，因此执行j++，j变成了n，发生越界！
                if (i == 0) {
                    j++;
                    dir = -1;
                } else if (j == n - 1) {
                    i++;
                    dir = -1;
                } else {
                    i--;
                    j++;
                }
                 */
                if (j == n - 1) {
                    i++;
                    dir = -1;
                }else if (i == 0) { /**应该要先判断j==n-1，这么写肯定会报”索引越界异常“*/
                    j++;
                    dir = -1;
                } else {
                    i--;
                    j++;
                }
            } else {
                /*下面的判断顺序会导致类似错误“java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 2”
                因为如果此时(i,j)来到左下角的位置（m-1，0），会发现j==0，因此执行i++，i变成了m，发生越界！
                if (j == 0) {
                    i++;
                    dir = 1;
                } else if (i == m - 1) {
                    j++;
                    dir = 1;
                } else {
                    i++;
                    j--;
                }
                 */
                if (i == m - 1) {
                    j++;
                    dir = 1;
                }else if (j == 0) { /**应该先判断i==m-1，如果先判断j==0肯定会报”索引越界异常“*/
                    i++;
                    dir = 1;
                } else {
                    i++;
                    j--;
                }
            }
        }
        return res;
    }


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
    /**
     *【易错点】其实最关键的是dp数组的长度多1，并且dp[0]的值是1————表示空串有一种解码方法.....
     *     但是其实这并不是必须的吧？？
     【dp思路】
     初始化：
         dp[0] = 1（空字符串有 1 种解码方式）；dp[1] 视第一个字符是否为 '0' 决定：
             '0' → dp[1]=0
             非 '0' → dp[1]=1
               但是在下面的写法中会在开始的时候就判断了数组的0位置是字符0的情况，因此看到代码中就直
            接将dp[1]赋值为1
     状态转移(比如：现在正在求解dp[i])：
         看最后一位单独成字母的情况：如果 s[i-1] != '0'，那么 dp[i] += dp[i-1]。
         看最后两位组成字母的情况：如果 10 <= s[i-2..i-1] <= 26，那么 dp[i] += dp[i-2]。
     【优化】
        根据方法numDecodings中备注的①、②，可以发现可以将1维的动态规划优化到“常数空间”的动态规划
     【dp问题初始化的注意】
        我们定义 dp[i] 表示 前 i 个字符（s[0..i-1]）的解码方法数。如果 i=0，表示“没有字符”（空串），那
     么解码方法有多少呢？
        如果你要拼接结果，空串本身只有一种“空解码”：什么都不做。这并不是一种实际的编码方式，而是 递推的基底。
        动态规划常常这样定义：
     “有一个空集/空字符串/空背包，只有一种处理方式——不选/不做”。所以我们把 dp[0]=1 当成初始条件，保证后面的转移公式可以正确套用。
     */
    /*【补充说明】
     动态规划里常见的“空集一法”
         走台阶：dp[0]=1 表示爬 0 级台阶有 1 种走法（啥也不做）。
         子集和：dp[0]=1 表示不选任何元素时和为 0 有 1 种方案。
         背包问题：容量为 0 时有 1 种方案（不放任何物品）。
         同理，这里空串解码方式“1 种”就是“啥都不做”的那 1 种，为了递推方便。

     总结
         dp[0]=1 并不是说空串真的有一个字母解码，而是动态规划的基底：
         它表示在还没有开始处理任何字符时，只有 1 种“空操作”。
         这样递推时才能正确地累加第一位、前两位等情况。
     */
    public int numDecodings(String s) {
        if(s==null||s.length()==0||s.charAt(0)=='0') return 0;
        int[] dp = new int[s.length() + 1];
        /*base case：需要初始化两个。其中dp[0]作为启动的参数、dp[1]是判断第一个字符是不是0(如果第一个字
            符是0，说白了这个字符串是不能被解码的。因为0不能解码并且它前面也没有字符不能组成“10”、“20”的样
            子)*/
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <=s.length(); i++) {
            char c2 = s.charAt(i - 1);
            char c1 = s.charAt(i - 2);
            /*
             根据1位数进行解码 和 2位数解码，更新dp[i].
                   方案1：和前面的数合起来进行解码。前提条件————是不是能和前面的数组成[10,26]中的一个数，不在这个范围就不行
                   方案2：当前字符(即index=i-1的字符)单独及逆行解码。前提条件————当前的这一位数位于区间(0,9]，如果不在这个
                        区间，则不行
             */
            if (c2!='0') dp[i] += dp[i-1]; //①i位置的值不是0，因此可以单独进行解码
            int val = (c1-'0')*10+c2-'0';
            if (val>=10&&val<=26) dp[i] += dp[i-2]; //②i位置的值 和 i-1位置的值 组合起来位于[10,26]，因此可以组合起来解码
        }
        return dp[s.length()];
    }

    /*空间优化到滚动变量*/
    /**由于一个位置最多依赖于之前的两个位置，因此简化为仅仅使用2个变量来滚动计算....
       写出一维的dp以后，这里的优化其实就很像”斐波那契数列“的题目*/
    public int numDecodings_(String s) {
        if (s==null||s.length()==0||s.charAt(0)=='0') return 0;
        int fir = 1;
        int sec = 1;
        for (int i = 2; i <=s.length(); i++) {
            int curVal = 0;
            char c2 = s.charAt(i - 1);
            char c1 = s.charAt(i - 2);
            if (c2!='0') curVal += fir;
            int val = (c1-'0')*10+c2-'0';
            if (val>=10&&val<=26) curVal += sec;

            fir = sec;
            sec = curVal;
        }
        return sec;
    }


    /*440. 字典序的第K小数字
        给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
            示例 1:
            输入: n = 13, k = 2
            输出: 10
            解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
    */
//    public int findKthNumber(int n, int k) {
//
//    }

    /*9. 回文数的判断*/
    public boolean isPalindrome(int x) {
        /*step1：base case的考虑。
            ①负数一定不是回文数；
            ②如果一个非零数的最后一个数是0，一定不是回文数*/
        /**如果一个数的最后一位是0，说明肯定不是回文数，一个数不会以0开头（但是有一个例外，0本身）*/
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false; /**err：这里的“x % 10 == 0”不能省略。否则用例“x =10”是错的*/

        /*step2：使用rev记录反转后得到的数，只要rev<x就一直反转*/
        int rev = 0;
        /**不论x是什么数，也不管是不是回文。。循环条件while中”res<x“就保证了res最多只会比x多一位数*/
        while (rev < x) {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }
        /*step3：除了while循环有两种可能。
            ①如果形参x是偶数位的话，两个部分如果是相等的，说明是回文数；
            ②如果形参x是奇数位的话，两个部分一定不相等且rev一定大，因为此时最中间的那个数
         在rev中，此时如果x==rev/10的话，说明是回文数。。比如：初始时形参x是53135，则出了
         while循环时x的值时53，rev的值是531.(满足x==rev/10)*/
        return x == rev || x == rev / 10;
    }


    /*450
    给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
    一般来说，删除节点可分为两个步骤：

    首先找到需要删除的节点；
    如果找到了，删除它。
     */
    /**
     【解题思路】删除二叉搜索树中的一个节点，根据该节点的处境分为几种情况————
          1. 如果该节点是叶子节点，则直接删除就可以。由于它就在叶子节点，因此也不需要用其他节点代替它的位置；
          2. 如果该节点只有一个孩子非null————换言之相等于只有一个孩子；
          3. 如果该节点的左右孩子都不是null，则有两种做法(两种做法是等价的)：一种是让左子树的最大值代替这个
        节点，另一种是让右子树的最小值代替这个节点......下面的代码使用右子树的最小值来代替要被删除的节点
          【说明】下面的代码中，实际上将1和2的逻辑写在了一起
     */
    /*解法1：递归方法删除*/
    public TreeNode deleteNode(TreeNode root, int key) {
        /*step1：特殊情况的考虑*/
        if(root==null) return null;
        /*step2：外层的if-else if-else会先找到要删除的节点。进入else块说明找到了要删除的节点*/
        if (key<root.val){
//            return deleteNode(root.left,key); //这样写是错误的，这样写“只是把删除后替换的节点返回了，并没有在树中完成节点的替换”
            root.left = deleteNode(root.left,key); /**err：注意必须要把递归的返回值赋给root.left，才会真正从树中删除节点*/
        } else if (key>root.val) {
//            return deleteNode(root.right,key);
            root.right = deleteNode(root.right,key);
        }else { /**进入到else，说明定位到了要删除的节点*/
            /*情况1：如果只有一个子节点 或者 一个子节点都没有，则返回不是null的节点*/
            if (root.left==null) return root.right;
            if (root.right==null) return root.left;
            /*情况2：代码走到这里说明左右子树都不是null，因此————
                首先，去找右子树的最小值来代替这个节点；
                然后，取右子树递归删除最小值的那个节点*/
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
//            return deleteNode(root.right, minNode.val);
            root.right = deleteNode(root.right,minNode.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode right) {
        while (right.left!=null){
            right = right.left;
        }
        return right;
    }


    /*解法2：迭代的方法*/



    /*10.正则表达式匹配
      给你一个字符串 s 和一个模式串 p，实现支持 . 和 * 的正则匹配：

        . 匹配任意单个字符。

        * 匹配零个或多个前一个元素。

        要求匹配必须 覆盖整个字符串。
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        /*step1：初始化包含两个重要步骤。
            ①空串和空串是匹配成功的————dp[0][0]是true；
            ②如果p串是”a*“或者”a*b*“之类的情况————即下面的for循环处理*/
        dp[0][0] = true; //对应的是step1的①
        //for循环对应step1的②。为什么i从2开始，因为*最早会出现在索引为1的位置，dp[][2]计算的就是匹配串的0位置和1位置的匹配
        for (int i = 2; i <=n; i++) {
            if (p.charAt(i-1)=='*'){
                /**为什么是下面的表达式？？因为第0行s还是空串，所以只能匹配0次，匹配0次的话就是dp[0][i-2]*/
                dp[0][i] = dp[0][i-2];
            }
        }


        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc=='.'||sc==pc){
                    dp[i][j] = dp[i-1][j-1];
                }else if (pc=='*'){
                    /**如果前面的字符匹配了0次*/
                    dp[i][j] = dp[i][j-2];
                    /**如果前面的字符匹配了1次 或者 多次。。。。
                     疑问：”多次“是如何实现匹配的？？*/
                    char pre = p.charAt(j - 2); //j-1是现在研究的字符，j-2就是研究字符的前面一个字符————这里说的就是*前面的字符
                    if (pre=='.'||pre==sc){
                        dp[i][j] |= dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }


    /*
    LCR 161. 连续天数的最高销售额
某公司每日销售额记于整数数组 sales，请返回所有 连续 一或多天销售额总和的最大值。

要求实现时间复杂度为 O(n) 的算法。
     */
    /*下面是错误的写法*/
    public int maxSales(int[] sales) {
        int res = Integer.MIN_VALUE;
        int preSum = 0;
        for (int i = 0; i < sales.length; i++) {
            /**这个题下面的这种写法是错的，53题也不能这麽写？？
             已知一个数组，求解最大的子数组和。是不一定大于等于0的，因为这个数组的子数组是明确
             的，任何子数组的和也是明确的。。如果一个数组的所有数都是负数，则子数组的最大和一定是小
             于0的！！
             不像“买卖股票Ⅰ”，可以选择不买，因此利润的下限就是0，不可能说利润反而是负数！！
             */
            preSum = Math.max(preSum,preSum+sales[i]);
            res = Math.max(preSum,res);
        }
        return res;
    }

    /*下面是正确的解法*/
    public int maxSales_(int[] sales) {
        /**
         这种类型的题目中，如果开始的时候难考虑。。。则建议使用nums[0]初始化，然后从nums[1]开始研究剩下位置的元素
         */
        int res = sales[0];
        int preSum = sales[0];
        for (int i = 1; i < sales.length; i++) {
            preSum = Math.max(preSum+sales[i],sales[i]);
            res = Math.max(res,preSum);
        }
        return res;
    }


    /*208.
    Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

    请你实现 Trie 类：
    Trie() 初始化前缀树对象。
    void insert(String word) 向前缀树中插入字符串 word 。
    boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
    boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
    **/

    /**
     【解题关键】关键是大脑中有一个26叉树，树中的每一个节点存储的是一个TrieNode结构————这个结
            构中包含了26个孩子的指针（即TrieNode[26]），以及是不是某一个单词结尾的标志（即变
            量isEnd）
     【代码的重点】①声明TrieNode，标志着节点的样子；②类内声明一个root作为根节点，每一次查找
        和 想插入的时候 都必须从root开始。
     【疑问】
            1. 前缀树的方法中第一行往往都是"TrieNode node = root;"，原因是什么？？
        答：每一次操作都是从根节点出发一步一步进行的，因此要保证整个树根是确定的，所以每一次
        方法执行之前要拷贝一份根节点，来使用拷贝后的指针node来执行操作。。。否则上一步操作完
        root节点就变了，因此就不对了
     【其他说明】
             ①每个节点存的是一个“分支数组 + 是否是单词结尾”；
             ②单词的路径就是从 root 一路走下去；
             ③search 就是看路径能不能走完，并且 isEnd=true；
             ④startsWith 就是看路径能不能走完，不管 isEnd。
            【注意】每一个位置并不用存储字符，而是对应的索引位置不是null的时候，就说明有对应的字符，
         因此是用下标index来暗示是不是有对应字符的。比如：index=0的位置如果不是null，就说明当前的
         位置是可以匹配到字符a的；index=2的位置如果不是null，就说明当前位置是可以匹配到字符b的....
         这才是这个题最抽象的地方~~
     */
    class Trie {

        class TrieNode{
            TrieNode[] children = new TrieNode[26]; /**err：注意在声明的时候进行初始化，或者 在空参的构造器中进行初始化也可以*/
            boolean isEnd;
        }

        private final TrieNode root; /**标识整个“前缀树”的根节点*/

        public Trie() {
            root = new TrieNode();
        }

        /**
         1. 插入的具体思路————
             for循环拿到word的每一个字符，如果字符对应位置的TrieNode是null，则创建；node来
         到它孩子数组的对应位置。
         2. 插入 和 查找前缀/查找单词 的区别————
            插入的时候如果到某一步发现children对应位置的TrieNode是null，则会创建TrieNode。
         但是”查找前缀/查找单词“的时候如果到某一步发现children对应位置的TrieNode是null，就直
         接返回false了。
         */
        public void insert(String word) {
            /**疑问？每一个方法之前都会这样记录一下这个变量，不记录的话行不行？？记录的意义又
             是什么？？
                答：不记录是不行的。意义就是保证全局记录的根节点root是正确的。如果这里不记录
             的话，会导致下面的问题————
                以insert为例，最终root指针将会来到插入节点的最后的位置，后面的操作就不知道根
             节点是啥了，所有的查找、插入都无从下手了*/
            TrieNode node = root;
            for (char c:word.toCharArray()){
                int index = c-'a';
                if (node.children[index]==null){
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        /**
         【思路】
         【search和startsWith的唯一区别在于：方法的最后返回值】
             startsWith要求有这样的前缀就可以，并不一定匹配一个确定的单词；但是search方法的要求是
         必须完整的匹配到一个单词…………因此最后的一个字符的isEnd必须是true才表示找到了
         */
        public boolean search(String word) {
            TrieNode node = root;
            for (char c:word.toCharArray()){
                int index = c-'a';
                if (node.children[index]==null) return false;
                node = node.children[index];
            }
            return node.isEnd; /**和startsWith方法的唯一区别*/
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c:prefix.toCharArray()){
                int index = c-'a';
                if (node.children[index]==null) return false;
                node =node.children[index];
            }
            return true;
        }
    }



    /*225. 用队列实现栈
    要求用 队列（Queue） 来实现一个 栈（Stack），支持以下操作：
        push(x) – 压入元素 x
        pop() – 弹出栈顶元素
        top() – 获取栈顶元素
        empty() – 判断栈是否为空
     */
    /**
     【思想解释】
          1. 栈是先进后出的结构，队列是先进先出的结构。
          2. 如何让栈先进先出？
                维护两个栈：添加元素时都先进入到inStack；出栈时必须从outStack出栈。要
            求outStack为空的时候，把inStack的元素依次弹出并加入到outStack。。（其中
            inStack是先进后出，然后到outStack是后进先出，因此”先进后出-后进先出“就变
            成了先进先出）
          3. 如何用队列实现栈？
                使用一个队列：元素添加进队列，要求每次添加进一个元素，就把队列中 除它以
            外(即循环变量”i<size-1“，除了它)的其他所有元素依次弹出 并 重新添加进队列。。
            然后就等价于最后进入的那个元素是最先进入的，因此出队列的时候也是最先出，即"后
            进先出"。
     【API说明】
          1. LinkedList就是Queue，方法offer()、poll()就天然的实现了先进先出的结构————
            1.1 offer()是向队列尾部添加元素
            1.2 poll()是从队列头弹出元素
     【关键】其实最关键的是push方法，push方法就实现了在元素添加的时候把队列处理成了栈的结构。最
          终的效果就是”队列头部就是栈顶元素，队列尾就是栈底“。。。因此获取栈顶元素就是peek()方
          法，弹出栈顶元素就是调用poll()方法。
     【补充说明】虽然只改动了push方法，但是队列任意时刻都是栈的结构，详细过程如下——————
            时刻a，添加1======>1
            时刻b，添加2======>1,2，按照push方法处理后========>2,1
            时刻c，添加3======>2,1,3，按照push方法处理后（除了最后一个元素3的其他元素，依
        次执行poll然后再offer）========>3,2,1
            时刻d，添加4======>3,2,1,4，按照push方法处理后========>（除了最后一个元素4以外的
        其他元素，依次执行poll然后offer）======>4,3,2,1
            ………………
            综上，任何一个时刻，队列中的数据都是栈的样式，队头的元素是最后添加的；队尾的元素是最早
        添加的。
     */
    class MyStack {

        private Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
            int size = queue.size(); //必须使用额外的变量来记录此时的size，这个思想类似于“层序遍历时，遍历某一层节点之前记录queue中有多少节点————即本层有多少节点”————因为在遍历时回不断的向queue中添加节点，导致queue的节点不断的变，因此.size()不能放在for循环的条件中
            for (int i = 0; i < size - 1; i++) { /**【注】这里是”i<size-1“，即将”除当前元素x以外的其他元素出队列“*/
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll(); // 队首就是栈顶
        }

        public int top() {
            return queue.peek(); // 队首就是栈顶
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }


    /*516. 最长回文子序列
    给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     */
    /*填表的写法————这种写法更好理解
        情况1：如果首尾的字符相同，则取决于中间子串的dp值，即dp[i+1][j-1]。
        情况2：如果首尾的字符不相同，则取决于舍弃首字符 或者 舍弃尾字符 的dp值，
            即Math.max(dp[i+1][j],dp[i][j-1])。
        综上，可以看出（i，j）位置的计算依赖于位置（i+1,j-1）、（i+1,j）、
            （i,j-1）。其中前面两个是第i+1行的dp值，第三个是第i行前一个位置的
            dp值，因此不同行需要倒着遍历，同一行内需要从前往后遍历
     */
    /**
     DP数组的含义：设 dp[i][j] 表示字符串 s[i..j]（从 i 到 j 的子串）中，最长回文子序列的长度。
     状态转移方程：
            情况1：如果i、j位置的字符相同，则dp[i][j] = dp[i+1][j-1] + 2
            情况2：如果i、j位置的字符不相同，则dp[i][j] = max(dp[i+1][j], dp[i][j-1])
     初始化：i和j相等的时候，子串的长度是1，“最大回文子序列”的长度是1
     遍历顺序的确定：
            ①当i和j位置字符相等的时候，因为 dp[i][j] 依赖于 dp[i+1][j-1]，所以 i的计算方向必
        须是从大到小的，这一步不能确定出j的遍历方向
            ②当i和j位置字符不相等的时候，此时 dp[i][j] 会依赖于 dp[i+1][j]、dp[i][j-1]，即
        会依赖于下一行的同一位置，因此要先计算出下一行的值，即i的方向是从大到小；也会依赖于同一行前
        一个位置的数，因此同一行计算的时候需要先计算前面的数，从前往后依次计算。
            综上，行的顺序是从下往上计算每一行的元素、列的顺序是从前往后计算
     */
     /**建议的解法：常规的思想————二维表的行列分别标识s串对应位置的字符
        dp数组的含义：dp[i][j]标识“i....j”这个子串的最大回文子序列长度（因此只有右上半的二维表位置
     才有实际意义，这些位置的j>i）；
        状态转移：
            情况1：如果i位置的字符 和 j位置的字符 是一样的，则dp[i][j] = dp[i+1][j-1] + 2;
            情况2：如果i位置的字符 和 j位置的字符 是不一样的，则dp[i][j] = Math.max(dp[i+1][j],
         dp[i][j-1])。即dp[i][j]取决于中间子串的最优解
    */
    public int longestPalindromeSubseq(String s) {
        /*step1：声明dp数组。
            这里不用多1维，原因在于dp[i][j]的含义————代表子串“i....j”中最长的回文子序列长度，因此
         是包含左右边界的，因此不用多一维就可以*/
        int n = s.length();
        int[][] dp = new int[n][n];
        /*step2：base case。主对角线就是只有一个字符的情况，此时的最长回文子序列长度就是1*/
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        /*step3：由于最后一行只有一个元素dp[n-1][n-1],这个位置其实在base case就填充了。因此
            这里直接从倒数第二行(索引为n-2)开始向上研究。
            研究每一行时，j从i+1开始研究。原因：首先“i....j”的子串因此j必须不小于i；同时“i..i”
         这样长度位1的子串在base case就已经设置完成了，因此j直接从i+1开始。
        */
        for (int i = n-2; i >=0; i--) {
            for (int j = i+1; j < n; j++) {
                //①拿出当前子串的首尾字符
                char l = s.charAt(i);
                char r = s.charAt(j);
                if (l==r){ //②首尾字符相同的时候
                    dp[i][j] = dp[i+1][j-1]+2;
                }else { //③首尾字符不相同的时候
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        /*step4：因为求解的是s中最长的回文子序列长度。因此返回dp[0][n-1]—————“代表子串0....n-1中
            最长回文子序列的长度”*/
        return dp[0][n-1];
    }

    /**两行数组dp的写法*/
    public int longestPalindromeSubseq_2row(String s) {
        int n = s.length();
        int[] prev = new int[n];
        prev[n-1] = 1;
        int[] cur = new int[n];

        for (int i = n-2; i >= 0; i--) { /**err：只要i>=0的时候索引有效，都得进入到这个for循环*/
            cur[i] = 1;
            char ci = s.charAt(i);
            for (int j = i+1; j < n; j++) {
                char cj = s.charAt(j);
                if (ci==cj){
                    cur[j] = prev[j-1] + 2;
                }else {
                    cur[j] = Math.max(prev[j],cur[j-1]);
                }
            }
            int[] tmp = prev;
            prev = cur;
            cur = tmp; /**还是需要认真理解一下，为什么滚动数组的情形下，prev赋值给cur不会出问题*/
        }
        return prev[n-1];
    }

    /**一行数组更新的形式。。
        【感悟】像下面的依赖于左上角 或者 左下角 或者 右下角 等三个位置的二维dp，优化成一行数组更
     新的时候，都要像下面的写法
     */
    public int longestPalindromeSubseq_1row(String s) {
        int n = s.length();
        int[] dp = new int[n];
        dp[n-1] = 1;
        for(int i=n-2;i>=0;i--){
            /*关注点1（下面两行）：使用prev记录dp[i]；然后更新dp[i]*/
            int prev = dp[i];
            dp[i] = 1;
            for(int j=i+1;j<n;j++){
                /*关注点2（下面一行）：使用tmp记录dp[j]；然后更新dp[j]*/
                int tmp = dp[j];
                char ci = s.charAt(i);
                char cj = s.charAt(j);
                if(ci==cj){
                    dp[j] = prev+2;
                }else{
                    dp[j] = Math.max(dp[j-1],dp[j]);
                }
                /*关注点3（下面一行）：使用tmp更新prev*/
                prev = tmp;
            }
        }
        return dp[n-1];
    }


        /*673. 最长递增子序列的个数
给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。

注意 这个数列必须是 严格 递增的。
     */
    /**
     【建议的解法】建议使用解法“findNumberOfLIS”。即采取如下的步骤————
            step1：先把dp数组以及cnt数组计算完成；
            step2：然后遍历dp数组，如果dp[i]等于最长长度，则将cnt[i]累加到返回值。
            [说明]不建议在计算dp数组和cnt的过程中就计算结果，容易出错~~
     【注意】
        1. dp数组中dp[i]表示以nums[i]结尾的最长递增子序列的长度。。。这样的定义和
     ”300 最长递增子序列“最优解中dp的定义是有区别的！！！！【重要区别】
        2. 但是这样的长度有多少呢？这个是需要知道的，因为我们需要计算“最长递增子序列
     的数量”，因此这个值在后面会用到。比如：nums[i]<nums[i+m]，因此会有有一个递增子
     序列，这样长度的子序列有多少个呢？？？答：以nums[i]结尾的最长递增子序列的个数就是
     “.......nums[i]、nums[i+m]”这样递增子序列的个数，因此我们需要能快速的得到以nums[i]结
     尾的最长递增子序列的个数
         综上，额外需要一个数组cnt，其中cnt[i]表示“以nums[i]结尾的最长递增子序列 的个数”
     */
//    public int findNumberOfLIS(int[] nums) {
//        int res = 0,maxLen = 0;
//        int[] dp = new int[nums.length];
//        Arrays.fill(dp,1);
//        Arrays.fill(count,1);
//        for (int i = 1; i < nums.length; i++) {
//            int newVal = dp[i];
//            for (int j = 0; j < i; j++) {
//                if (nums[i]>nums[j]){
//                    newVal = Math.max(dp[j]+1,dp[i]);
//                }
//                dp[i] = newVal;
//                if (newVal>maxLen){
//                    maxLen = newVal;
//                    res = dp[j];
//                } else if (newVal == maxLen) {
//                    res += dp[j];
//                }
//            }
//        }
//        return res;
//    }

    /**写法1：先使用双层for循环更新完两个数组；然后再遍历一遍”长度“数组更新答案*/
    public int findNumberOfLIS(int[] nums) {
        int maxLen = 0;
        /*step1：初始化两个数组————dp以及cnt
            dp数组：dp[i]的含义————以nums[i]结尾的最长递增子序列的长度。
            cnt数组：cnt[i]的含义————以nums[i]结尾的最长递增子序列的方案数————即以nums[i]结
         尾的长度为dp[i]的最长递增子序列的方案数。
            初始时，两个数组的元素值都是1，因为“以nums[i]结尾的最长递增子序列”的长度至少
        是1，此时的子序列仅仅包含nums[i]自身————即dp数组的任何一个值至少是1；“以nums[i]
        结尾的最长递增子序列”的种类数此时也是1，因为仅仅包含nums[i]自身————即cnt数组的任何
        一个值至少是1.
        */
        int[] dp = new int[nums.length];
        int[] cnt = new int[nums.length];
        Arrays.fill(dp,1);
        Arrays.fill(cnt,1);
        /*step2：使用for循环完成dp、cnt数组的计算*/
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                /**条件1：只需要研究nums[j]<nums[i]的情况，因此此时才会更新dp、cnt数组，其他的情况数组的默认值不用动*/
                if (nums[i]>nums[j]){
                    /**条件2：在满足条件1的基础上，还必须满足“dp[j]+1>=dp[i]”，此时才会涉及到更新操作*/
                    if (dp[j]+1>dp[i]){ //情况1：此时对于nums[i]结尾的子序列，有一个更大的递增子序列长度。因此更新dp[i]，同时更新cnt
                        dp[i] = dp[j]+1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) { //情况2：对于nums[i]来说，找到了同样长度的最长递增子序列，因此需要更新cnt
                        cnt[i] += cnt[j];
                    }
                }
            }
            /*到了这里说明"以nums[i]结尾的最长子序列长度、数量"都知道了，因此下面尝试更新全局信息。
                     【补充说明】
                          写法1. 这里仅仅是更新的全局的”最长子序列长度“这个信息。所以出了双层for循环需要统计一下”这种长度递增子序列“的总数量
                          写法2. 在双层for循环中，不仅仅更新longth、num数组的信息，同时更新所有的全局信息，见方法 findNumberOfLIS_
             */
            //更新整个过程中遇到的“最长递增子序列的长度”。。经过内层的for循环会计算出i位置的dp值以及cnt值
            maxLen = Math.max(maxLen,dp[i]);
        }
        /*step3：遍历dp数组，如果nums[i]结尾的最长递增子序列的长度等于maxLen。
            说明：以nums[i]结尾的最长递增子序列的长度就是“整个数组的最长递增子序列长度”，因此需要将
        以nums[i]结尾的最长递增子序列的方案数累加到res，这个方案数是多少呢？？就是cnt[i]！！*/
        int res  =0;
        for (int i = 0; i < cnt.length; i++) {
            if (dp[i]==maxLen){
                res += cnt[i];
            }
        }
        return res;
    }

    /**写法2：在一遍遍历的过程中更新全局要返回的答案*/
    public int findNumberOfLIS_(int[] nums) {
        /*step1：声明两个数组
              dp数组用于存放“以nums[i]结尾的最长递增子序列的长度”；
              count数组用于存放“以nums[i]结尾的最长递增子序列的数量”。
              比如：以nums[8]结尾的最长递增子序列的长度是5，则dp[8]=5；同时以nums[8]结
           尾且长度是5的子序列一共有4种，则count[8]=4。
         */
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int[] count = new int[nums.length];
        Arrays.fill(count,1);
        /*step2：声明两个变量
             maxLen————最长递增子序列的长度
             num存放————长度为maxLen的递增子序列一共有多少种
         */
        int maxLen = 0,num = 0;
        /*step3：计算dp以及count数组
            对于任意的位置i，j从0位置开始，看看nums[i]放在nums[j]后面能不能形成一个更长
         的递增子序列
         */
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                /*双重循环的核心
                    【核心的话】因为j<i，所有可以得出：“如果nums[j]<nums[i]，则nums[i]可以放
                 在nums[j]的后面形成一个更长的递增子序列”
                    此时，可能会有两种情况————
                        情况1：如果dp[j]+1>dp[i]，说明nums[i]放在nums[j]后面以后形成的递增子
                    序列长度超过了目前已经发现的“以nums[i]结尾的最长递增子序列”。则说明对于nums[i]
                    而言，找到了一个更长的递增子序列，那此时最长递增子序列长度是多少呢？？这样长度的
                    递增子序列又有多少呢？？首先最长递增子序列的长度就是dp[j]+1————即把nums[i]放在
                    nums[j]后面即可。。这样递增子序列的长度是多少呢？应该就是count[j]，因为nums[i]
                    放在nums[j]后面形成递增子序列，但是以nums[j]结尾的子序列有count[j]种，因此以nums[i]
                    结尾的最长递增子序列也有count[j]种
                        情况2：如果dp[j]+1=dp[i]，则更新以nums[i]即为最长递增子序列的样本数
                 */
                if (nums[j]<nums[i]){
                    if (dp[j]+1>dp[i]){
                        dp[i] = dp[j]+1;
                        count[i] = count[j]; /**【注】这里不要初始化为1*/
                    }else if (dp[j]+1==dp[i]){
                        count[i] += count[i];
                    }
                }
            }
            /*step4：更新maxLen 以及 num.
                情况1：如果dp[i]是最大的，则更新maxLen = dp[i];并且num更新为count[i]
                情况2：如果正好dp[i]=maxLen，则将count[i]累加到num。说明最长递增子序列
             的数量增加了。
             */
            if (dp[i]>maxLen){
                maxLen = dp[i];
                num = count[i];
            } else if (dp[i] == maxLen) {
                num += count[i];
            }
        }
        return num;
    }



    /*344
    给定一个字符数组 s，请你原地反转这个字符串，不要分配额外空间。
    示例：
    输入：s = ['h','e','l','l','o']
    输出：['o','l','l','e','h']
     */

    /**
     【说明】注意感受一下 while循环 以及 for循环 的写法，以及区别
     */
    public void reverseString(char[] s) {
        int left = 0,right = s.length-1;
        while (left<right){
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    public void reverseString_for(char[] s) {
        for (int left = 0,right = s.length-1;left<right;left++,right--){
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }



        /*120. 三角形最小路径和
    给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     */
    /**
     * 可以将空间优化到O(N),时间复杂度为O(n^2)
      【关键一句话】从最后一行倒着遍历；每一行内从前往后遍历
      【建议的解法】见方法 minimumTotal_ 或者 minimumTotal_own（这样写 初始化 和 双层for循环计算过程 很清晰！）
      【关键】需要从最后一行倒着遍历。最后返回dp[0]。原因解释————
          第m行的i位置能移动到第m+1行的i位置和i+1位置；第m行的i+1位置能移动到第m+1行的i+1位置和i+2位置。
          比如“第m行的i位置能移动到第m+1行的i位置和i+1位置”，此时如果是从第一行往下面的行遍历，dp[i]和dp[i+1]
          就可能会被更新，更新后会发现dp[i+1]存的就是第m+1行的信息；但是继续计算时“第m行的i+1位置能移动到第m+1
          行的i+1位置和i+2位置”需要使用到第m行的dp[i+1]信息，但是在前一步的时候这个信息被覆盖了！！
          结论：空间优化为O(N)时，遍历顺序必须时从最后一行倒着遍历。（从最后一行遍历，但是同一行内从前往后遍
          历就可以）
      【补充说明】
          1. 一定必须从最后一行倒着遍历？
            答：不一定，从第一行开始往后遍历也没问题，见方法 minimumTotal__ 以及 minimumTotal___，因此从第0行开始
          正着遍历 或者 从最后一行倒着遍历，都是ok的，并且空间复杂度都能优化到O(N)。
          2. dp的题目中，以下的题目最好从最后一行倒着填表！！
                （1）516. 最长回文子序列。必须要倒着填表，因为第i行依赖于第i+1行
                （2）120. 三角形最小路径和。不是必须从最后一行倒着填，但是倒着填代码好写
     */
    public int minimumTotal_(List<List<Integer>> triangle) {
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        int[] dp = new int[lastRow.size()];
        for (int i = 0; i < lastRow.size(); i++) {
            dp[i] = lastRow.get(i);
        }

        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> curRow = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - 1]) + curRow.get(j);
            }
        }
        return dp[0];
    }

    public int minimumTotal_own(List<List<Integer>> triangle) {
        int size = triangle.get(triangle.size() - 1).size();
        int[] dp = new int[size];

        /**自己写的代码，没有赋初始值，是有问题的。。。看看问题的表现是什么*/
        for (int j = 0; j < size; j++) {
            dp[j] = triangle.get(triangle.size() - 1).get(j);
        }

        for (int i = triangle.size()-2; i >=0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int curVal = triangle.get(i).get(j);
                dp[j] = Math.min(dp[j],dp[j+1]) + curVal;
            }
        }
        return dp[0];
    }


    /**
     【关键】
        1. 从最后一行倒着遍历，在每一行的时候就能从前往后顺序更新了。会简化很多
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1]; /**空间多1防止越界*/
        for (int i = n-1; i > 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                /**
                 【这里的理解】
                        比如当前研究第m行，(m,j)位置的最小值，就取决于下一行的j位置、j+1位置（因为题目已知每一个位置
                 可以到下一行的相同索引 以及 相同索引加1的位置），那这两个位置的值是多少呢？？其实就是dp[j]、dp[j+1]，
                 那当前位置的值是多少呢？是triangle.get(i).get(j)
                 */
                dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        }

        return dp[0];
    }

    public int minimumTotal__(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int val = triangle.get(i).get(j);
                if (j == 0) dp[i][j] = dp[i - 1][j] + val; // 第一列
                else if (j == i) dp[i][j] = dp[i - 1][j - 1] + val; // 最后一列
                else dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + val;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }
        return ans;
    }

    /**从上往下研究每一行，并且空间复杂度为O(N)*/
    public int minimumTotal___(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            List<Integer> row = triangle.get(i);
            // 倒序更新，防止覆盖
            for (int j = i; j >= 0; j--) { /**从上往下遍历时，每一行必须倒序更新*/
                int val = row.get(j);
                if (j == 0) {
                    dp[j] = dp[j] + val; // 第一列，只能来自上一行的 dp[0]
                } else if (j == i) {
                    dp[j] = dp[j - 1] + val; // 最后一列，只能来自上一行的 dp[j-1]
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + val;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, dp[j]);
        }
        return ans;
    }


    /*207.课程表
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
    * */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<>());
        }

        int[] indgree = new int[numCourses];
        for (int[] cur : prerequisites) {
            int index = cur[0], ele = cur[1];
            graph.get(ele).add(index);
            indgree[index]++;
        }

        LinkedList<Integer> zeroIndgree = new LinkedList<>();
        for (int i = 0; i < indgree.length; i++) {
            if (indgree[i] == 0) zeroIndgree.offer(i);
        }

        int count = 0;
        while (!zeroIndgree.isEmpty()) {
            Integer curIndex = zeroIndgree.poll();
            count++;
            for (int index : graph.get(curIndex)) {
                indgree[index]--;
                if (indgree[index] == 0) {
                    zeroIndgree.offer(index);
                }
            }
        }
        return count == numCourses;
    }


    /*210 课程表Ⅱ
        有 numCourses 门课程（编号 0…numCourses-1）。
        给出先修关系 prerequisites[i] = [a,b] 表示必须先修 b 才能修 a。

        要求返回一种可行的修课顺序（拓扑序）。
        如果不存在（有环）则返回空数组。
    */
    /**标准的有向图的拓扑排序问题*/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        /*1. 构建存储图的架子*/
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<>());
        }
        /*2. 构建图、入度表*/
        int[] indgree = new int[numCourses];
        for (int[] cur:prerequisites){
            int prereq  =cur[1],course = cur[0];
            graph.get(prereq).add(course);
            indgree[course]++;
        }
        /*3. 初始化队列存储“入度为0”的节点*/
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indgree.length; i++) {
            if (indgree[i]==0) queue.offer(i);
        }
        /**4. 创建res来收集拓扑排序的结果
            【说明】上面的1~3和207题课程表的代码甚至是一样的，最主要的区别是下面的代码，使用res数组来收集结果
         */
        int[] res = new int[numCourses]; /**err：这里直接使用数组来“边出队边存储”。不要使用LinkedList，很不方便！！*/
        int index = 0;
        while (!queue.isEmpty()){
            Integer curIndex = queue.poll();
            res[index++] = curIndex;
            for (int inx:graph.get(curIndex)){
                indgree[inx]--;
                if (indgree[inx]==0) queue.offer(inx);
            }
        }
        if (index!=numCourses) return new int[0]; //如果res中存放的节点数小于numCourses，说明有课程遍历不到，即存在环
        return res;
    }



    /*50,计算幂
    实现一个函数计算 xⁿ（x 的 n 次幂）。
     */
    /**
     【建议的解法】见方法 myPow_own
     【错误点】
            1. 要正确处理n是负数的情况————
                如果用long类型N来存放（其实不用也可以，但是容易超时）。此时N是奇数的时候使用不使用“N--”都
            是🆗的。见写法 myPow_own
                如果不使用long类型来存放，此时n是奇数的时候如果出现“n--”就会出现用例超时！！！

     */
    public double myPow(double x, int n) {
        long N = n;
        return N>0?quickPow(x,N):1.0/quickPow(x,-N);
    }

    private double quickPow(double x, long n) {
        if (n==0) return 1.0;
        double y = quickPow(x,n/2);
        return n%2==0?y*y:y*y*x;
    }


    public double myPow_diedai(double x, int n) {
        long N = n;
        if (N<0){
            x = 1.0/x;
            N = -N;
        }

        double ans = 1.0;
        while (N>0){
            if (N%2==0){
                N /= 2;
                x *= x;
            }else {
                N = N-1;
                ans *= x;
            }
        }
        return ans;
    }


    /**迭代的官方写法
     下面的写法是最简化的写法
     */
    public double myPow_diedai_offical(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1.0 / x;
        }

        double res = 1.0;
        double base = x; //存储底数，初始的时候底数是x，指数是N
        while (N > 0) {
            /*步骤1：如果N是奇数的话需要特殊处理，将base先成到res，此时N就已经变成了N-1，因此指数变为偶数*/
            if ((N & 1) == 1) {
                res = res * base;
            }
            /*步骤2：到了这里指数一定是偶数了。。比如4^6相当于16^3即底数变为4*4，指数缩为原来的一半！！
                 因此做如下操作————
                    ①更新底数base。更新为原来的平方；
                    ②更新指数N。注意N为奇数的时候if块内没有更新N，但是“N/2”和“(N-1)/2”的结果一样。比
                 如”7/2==3“并且”6/2==3“，是一样的
                */
            base = base * base;
            N /= 2;
        }
        return res;
    }

    /*另外的写法*/
    public double myPow_own(double x, int n) {
        long N = n;
        double res = 1;
        double base = x;
        if (N<0){
            N *= -1;
            base = 1/x;
        }
        while (N!=0){
            if (N%2!=0){
                res *= base;
                /**这样的写法中，这里出现“N--”无所谓，都是可以的*/
                // N--;
            }
            base *= base;
            N /= 2;
        }
        return res;
    }

    public double myPow_own_1(double x, int n) {
        double res = 1;
        double base = x;
        if (n<0){
            n *= -1;
            base = 1/x;
        }
        while (n!=0){
            if (n%2!=0){
                res *= base;
                /**这样的写法，这里就不能出现“n--”，否则下面的测试用例会超时
                         x =
                         1.00000
                         n =
                         -2147483648
                 */
                // n--;
            }
            base *= base;
            n /= 2;
        }
        return res;
    }

    /*10.正则表达式匹配
    给你一个字符串 s 和一个字符模式 p，请实现支持 . 和 * 的正则表达式匹配：
        . 匹配任意单个字符。
        * 匹配零个或多个前面的那一个元素。
        匹配应该覆盖整个字符串（而不是部分字符串）。
     */
    /**
     【详细的解释】
        1.dp数组的含义： dp[i][j] 表示 s[0..i-1]（长度 i）能否被模式 p[0..j-1]（长度 j）完全匹配。
     下标的含义是很重要：s 的第 i 个字符是 s[i-1]，p 的第 j 个字符是 p[j-1]。
        2. 状态转移：
            情况1：如果s的i-1字符和p的j-1字符相同 或者 p的j-1字符是“。”，说明这两个位置是可以匹配的，因
        此dp[i][j]取决于dp[i-1][j-1].
            情况2：如果p串j-1位置是“*”。则继续讨论————
                ①匹配0次。此时p串的形式类似于“。。。。。。。p.charAt(j-2)*”，如果p串的“p.charAt(j-2)*”
            匹配0次，就相当于p串去掉这两个字符，因此dp[i][j]=dp[i][j-2].
                ②匹配1次或者多次。此时的要求就是：p.charAt(j-2)==‘。’ 或者 p.charAt(j-2)==s.charAt(i-1),
            用语言来描述的话就是说 p子串中*前面的字符 必须等于 s子串的最后一个字符~~~~其中*获取的方式是p.charAt(j-1)，
            因此“*前面的那个字符”就是p.charAt(j-2)，“s子串的最后一个字符”就是现在研究的位置，由于现在计算的是
            dp[i][j]，因此“s子串的最后一个字符”就是s.charAt(i-1).
     */
    /*正则表达式的多维版本*/
    public boolean isMatch_twoDim(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i=2;i<=n;i++){
            if (p.charAt(i-1)=='*'){
                dp[0][i] = dp[0][i-2];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc==pc || pc =='.'){
                    dp[i][j] = dp[i-1][j-1];
                } else if (pc == '*') {
                    /**10题与44题最大的区别，在于pc是*的情况，此时必须要看*号之前的那个字符*/
                    /*情况1——匹配0次，模式串p中的“字母*”并不匹配s串中的任何字符*/
                    dp[i][j] = dp[i][j-2];
                    /*情况2——匹配多次*/
                    /**10题中，*号要想匹配，必须满足*前面的字符和s子串的最后一个字符是相同的，否则不能匹配；
                     区别于44题，44题中，*号可以匹配任意长度的字符，不需要看*前面的字符是什么
                     */
                    char pre = p.charAt(j - 2); //拿到“*”前面的那个字符
                    if (pre=='.' || pre==sc){ //如果“*”前面的字符是“.”或者等于sc，则说明“*”前面的那个字符可以匹配s中的字符
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }


    /*正则表达式2个一维的交替版本*/
    /**
     【和二维动规的区别】
         两行滚动的dp中prev就是当前位置的上一行数据，因此“prev就相当于二维中的dp[i-1]”，代码中出
     现prev的地方换成“dp[i-1]”跟二维的写法就是一样的了；
         同理代码中的cur就是当前的这一行，因此“cur就相当于二维中的dp[i]”，代码中出现cur的地方换成
     “dp[i]”跟二维的写法就一样了
     */
    public boolean isMatch_twoRow(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[] prev = new boolean[n + 1];
        boolean[] cur = new boolean[n + 1];

        prev[0] = true;
        for (int i = 2; i <= n; i++) {
            if (p.charAt(i-1)=='*'){
                prev[i] = prev[i-2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc==pc||pc=='.'){
                    cur[j] = prev[j-1]; /**1. 相当于二维中的“dp[i][j] = dp[i-1][j-1]”*/
                } else if (pc == '*') {
                    cur[j] = cur[j-2];  /**2. 相当于二维中的“dp[i][j] = dp[i][j-2]”*/
                    char pre = p.charAt(j - 2);
                    if (pre==sc||pre=='.'){
                        cur[j] = cur[j] || prev[j]; /**3. 相当于二维中的“dp[i][j] = dp[i][j] || dp[i-1][j]”*/
                    }
                }
            }
            boolean[] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        return prev[n];
    }


    /*一行数组+常数变量*/
    public boolean isMatch_oneRow(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 2; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[i] = dp[i - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            boolean prevDiagonal = dp[0]; /**记录上一行的dp[0]*/
            dp[0] = false;
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                boolean tmp = dp[j]; /**这一轮内层循环结束时dp[j]就是当前行的了，但是计算dp[j+1]时可能还会用到上一行的dp[j]，因此需要记录旧值*/
                if (sc == pc || pc == '.') {
                    dp[j] = prevDiagonal;
                } else if (pc == '*') {
                    char prev = p.charAt(j - 2);
                    if (prev == sc || prev == '.') {
                        dp[j] = dp[j - 2] || dp[j];
                    }
                }
                prevDiagonal = tmp; /**将上一行的dp[j]（即二维中的dp[i-1][j]）回填到prevDiagonal，因为计算dp[j+1]的时候可能会用到（即二维中的dp[i][j+1]）*/
            }
        }
        return dp[n];
    }


    /*44 通配符匹配
    给定一个字符串 s 和一个模式 p，p 中可以包含普通字符、?（匹配任意单个字符）和 *（匹配任意长度字符串，包括空串）。判断 p 是否能匹配整个 s。
     */
    /**
     【第一说明】二维的dp中位置（i,j）依赖于（i-1,j-1）、（i,j-1）、（i-1,j），因此这个依赖关系和”编辑距离“这种题的依赖关系是一样的。。所
     以简化到1维的时候和”编辑距离“也有相似的写法
     **/
    /**
     【基础说明】
        1. dp数组的含义：我们设 dp[i][j] 表示s[0..i-1] 是否可以被 p[0..j-1] 匹配。
        2. 状态转移：
             如果 p[j-1] == s[i-1] 或 p[j-1] == '?'======> dp[i][j] = dp[i-1][j-1]
                    含义：最后一位匹配成功，就看前面的子串是不是匹配了
             如果 p[j-1] == '*'==========>dp[i][j] = dp[i][j-1] || dp[i-1][j]
                    含义：由于匹配串的最后一位是*号，因此匹配串的*号可以匹配一次或者0次。
                如果匹配0次，就取决于dp[i][j-1]即相当于p的最后一位不参与忽略它的存在；如
                果匹配多次，则s的最后一位可以匹配忽略它（表示s的最后一位可以匹配成功），取
                决于dp[i-1][j]
          解释：
            ①dp[i][j-1]：* 匹配空串；因为*匹配空串，因此dp[i][j] = dp[i][j-1]。
                ❗：注意匹配一次或者多次不是dp[i-1][j-1]，因为匹配一个字符后*号还可以继续匹
            配前面的其他字符，因此这里应该取决于dp[i-1][j]，*还需要继续存在。
            ②dp[i-1][j]：* 匹配一个或多个字符。因此dp[i][j] = dp[i-1][j]
            ③否则：dp[i][j] = false
        3. 初始化
         [第一行的初始化]————第一行表示s串是空串，但是匹配串p不是空串，此时只有前面的连续*位置的结果是true
                dp[0][0] = true；dp[0][j] = dp[0][j-1] && p[j-1]=='*'————当 s 为空串时，p 只有全是 * 才能匹配
         [第一列的初始化]————第一列表示匹配串p是空串，但是s串不是空串，此时就没法匹配，因此都是false
     【“通配符匹配”和“正则表达式”匹配的区别】
           区别1：44题中“?”可以匹配原字符串中的单个字符；10题中“。”表示可以匹配源字符串的单个字符。因此
       这两个字符在两个题目中的作用其实是一样的，地位是等价的。。。。并且这个逻辑在代码中也是一样的，如下
       (所以从本质上来说，“区别1”其实是“相同点”)
                                 if (sc==pc||pc=='?'){
                                    dp[i][j] = dp[i-1][j-1];
                                 }
                    【补充信息】if语句块也可以这么写，下面的写法跟“交错字符串”的写法是类似的
                                 if (pc!='*'){
                                    dp[i][j] = (sc==pc||pc=='?') && dp[i-1][j-1];
                                 }
            区别2：10题中的“*”表示前面的字符可以匹配0次、1次或者任意多次；但是44题中的“*”表示仅仅这个*
       就能匹配原字符串中的任意多个字符。————这是两个题最本质、最根本的区别。导致最终在代码中最直接的区别
       就是————
                44中的实现：if (pc=='*'){
                              dp[i][j] = dp[i-1][j] || dp[i][j-1];
                          }
                10中的实现：if (pc == '*') {
                             char prev = p.charAt(j - 2); //拿出“*”前面的那个字符
                             if (prev == sc || prev == '.') { //如果“*”前面的那个字符可以匹配s字符串当前研究的字符即s.charAt(i-1)
                                dp[j] = dp[j - 2] || dp[j];
                             }
                          }
    */
    public boolean isMatch_44(String s, String p) {
        /*step1：dp数组的声明 以及 初始值设置*/
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        /*step2：base case
            第一行的初始化————第一行的时候表示s是空串，则p能匹配成功的情况，当且仅当当前位置是*号 并且 当前位置之前
        可以成功的匹配空串。。。换言之只要从i=1开始，有一个位置不是*号，则后面的就不用看了，肯定的都是false，举个形象
        的例子，比如：s=”“,p="****3***"，则dp[0][5]、dp[0][6]、dp[0][7]....都是false，也就是说从3位置（index=4）开
        始，后面的就不能匹配成功空串了
            第一列的初始化————第一列表示p是空串，但是s是有数据的，此时压根就不可能匹配成功。因为匹配串是空的。。。由于boolean数
        组的默认值就是false，因此第一列的初始化可以省略
        * */
        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc == pc || pc == '?') {
                    /*if：当前的位置可以匹配成功，因此取决于前面的匹配结果*/
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    /*else-if：匹配串p的当前位置是*，可以匹配0次或者多次。
                    dp[i-1][j]：s除去当前位置，p串包括现在的位置————表示匹配1次 或者 多次
                    dp[i][j-1]：s串包括当前位置，p串除去当前位置————表示匹配0次
                    * */
                    dp[i][j] = dp[i - 1][j] ||
                            dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    /*994 腐烂的橘子 (Rotting Oranges)
    在一个 m x n 的网格中，每个单元格有三种值：
        0 表示空格
        1 表示新鲜橘子
        2 表示腐烂橘子

        每过 1 分钟，所有腐烂橘子都会让上下左右四个方向的新鲜橘子变腐烂。
        求需要多少分钟，才能让所有新鲜橘子都腐烂。
        如果不可能让所有橘子都腐烂，返回 -1。
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        LinkedList<int[]> queue = new LinkedList<>();
        int fresh = 0;
        /*step1：包括2个内容，一是把所有腐烂的位置添加到queue、二是将计算新鲜的橘子有多少个*/
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        /*step2：如果开始的时候新鲜的橘子数量就是0，说明不需要耗费时间————直接返回0*/
        if (fresh == 0) return 0;

        int minute = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        /*step3：根据腐烂橘子的位置，进行扩散，每扩散一轮需要更新花费的时间minute*/
        while (!queue.isEmpty()) {
            int size = queue.size();
            minute++;    /**err：这里应该是有问题的，判断是否还需要进行的标准应该是“queue不是空 并且 新鲜橘子的数量fresh大于0”*/
            for (int i = 0; i < size; i++) {
                /**有一个疑问：为什么对于腐烂的橘子，研究一遍就能直接出队列？？
                 解释：因为一个腐烂的橘子，经过一分钟已经把周围的橘子都变腐烂了，因此只需要从4周已经腐烂的橘子继续
                 感染下去。。。所以每一个腐烂的橘子只需要研究一次，它的任务就完成了，因为被它传染的橘子会继续传染下去，
                 因此研究一次后腐烂的橘子就需要弹出队列。
                 反之，如果它不出队，则下一次从它再次向周围感染橘子，花费的时间肯定比它四周橘子出发感染下去的花费时
                 间更长
                 */
                int[] cur = queue.poll();
                //for循环尝试从cur位置向4个方向扩散，碰到正常的橘子就变腐烂
                for (int[] d : dirs) {
                    int x = cur[0] + d[0], y = cur[1] + d[1];
                    /*做三个事：如果得到的某个方位不越界，并且新位置的橘子是好的橘子（因为0是空格，1才是新鲜的橘子），就让
                    它变腐烂（即grid对应位置的值变为2）；新鲜橘子的数量-1；将腐烂橘子的位置添加进queue*/
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        fresh--;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return fresh == 0 ? minute : -1;
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
        /*step1：获取长度并进行前置判断*/
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;
        /*step2：dp[i][j] 表示 s1 的前 i 个字符和 s2 的前 j 个字符是否能组成 s3 的前 i+j 个字符。
            ① 其中dp[0][0]的含义表示三个串此时都是空串，因此s3可以由s1和s2拼接来
        * */
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        /*step3：base case的计算
        情况1：如果s1是空串，则此时就是依次看s2的前i个字符的子串 是不是 等同于s3的前i个字符（即”dp中第一行“数据的初始化）
        情况2：如果s2是空串，此时就需要依次看s1的前i个字符的子串，是不是 等同于s3的前i个字符（即”dp中第一列数据的初始化“）
        * */
        for (int i = 1; i <= n; i++) {  //代表s1是空的情况
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        /*也可以使用下面的写法*/
//        for (int j = 1; j <= n; j++) {
//            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
//                dp[0][j] = dp[0][j - 1];
//            } else {
//                break; // 如果字符不匹配，后续也无法匹配，直接退出循环
//            }
//        }
        for (int i = 1; i <= m; i++) { //代表s2是空的情况
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        /*step4：研究剩下的所有位置*/
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                /**
                 10、44、还有这个题很明显的一个特征是————
                    dp[i][j]代表的是“第一个字符串前i个字符”和“第二个字符串前j个字符”的某种匹配关系，因此
                 当前研究的字符其实是“s1.charAt(i-1)”和“s2.charAt(j-1)”。
                 */
                char s3c = s3.charAt(i + j - 1);   //这里应该是-2吧？？应该是-1
                /*
                    “dp[i - 1][j] && s1.charAt(i - 1) == s3c”代表：①s1的“0、1、2....i-2”这个子串 和 s2的“0、
                1、2、.....j-1”这个子串 可以拼成s3的前面的子串；②并且s1的第i个字符(即当前研究的字符)和s3当前研究的字
                符是一样的，因此dp[i][j]是true；
                    “dp[i][j - 1] && s2.charAt(j - 1) == s3c”也是同理
                 */
                // 状态转移方程：
                // 1. 如果 s1 的第 i 个字符等于 s3 的第 i+j 个字符，则 dp[i][j] 取决于 dp[i-1][j]
                // 2. 如果 s2 的第 j 个字符等于 s3 的第 i+j 个字符，则 dp[i][j] 取决于 dp[i][j-1]
                // 只要有一种情况成立，dp[i][j] 就为 true
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3c) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3c);
            }
        }
        return dp[m][n];
    }

}
