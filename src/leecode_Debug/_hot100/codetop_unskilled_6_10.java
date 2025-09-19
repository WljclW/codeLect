package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * @author mini-zch
 * @date 2025/9/9 10:10
 */
public class codetop_unskilled_6_10 {
    /*手撕堆排序

     */




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
    class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root==null) return "null";
            StringBuilder res = new StringBuilder();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()){
                TreeNode cur = stack.pop();
                if (cur==null)
                    res.append("null,");
                else {
                    res.append(root.val).append(",");
                    stack.push(cur.right);
                    stack.push(cur.left);
                }
            }
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("null")) return null;
            String[] strs = data.split(",");
            TreeNode root = new TreeNode(Integer.valueOf(strs[0]));
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int index = 1;
            while (!queue.isEmpty()){
                TreeNode cur = queue.poll();
                if (!strs[index].equals("null")){
                    TreeNode node = new TreeNode(Integer.valueOf(strs[index]));
                    cur.left = node;
                    queue.offer(node);
                }
                index++;
                if (!strs[index].equals("null")){
                    TreeNode node = new TreeNode(Integer.valueOf(strs[index]));
                    cur.right = node;
                    queue.offer(node);
                }
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



    /*349
        给定两个数组 nums1 和 nums2，返回它们的交集。

        结果中的每个元素 唯一

        顺序不限
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


    /*排序+双指针的解法*/
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

    /*7
        给定一个 32 位有符号整数 x，返回将其数字部分反转后的结果。如果反转后超出了 32 位有符号整数的范围 [-2^31, 2^31 - 1]，则返回 0
     */
    /**
     *【关键】必须先在step2进行校验，校验后才能在step3更新res。
     *【说明】
     *      1. 在计算的过程中会发现————
     *        ①负数除以一个正数，不论是求整除还是求余结果都是负数，比如：
     *          int x = -19;======> x%2 = -1
     *                      ======> x/2 = -9
     *        ②负数除以一个负数，求余的结果是负数，但是求整除的结果是正数，比如：
     *          int x = -19;======> x%(-18) = -1
     *                      ======> x/-2 = 9
     */
    public int reverse(int x) {
        int res = 0;
        while (x!=0){
            /*step1：拿出最后一个数字digit；并更新x*/
            int digit = x%10;
            x /= 10;
            /*step2：校验不会越界。【说明】到这里为止最后一个数字digit还没有加到结果上
                ①如果res已经大于”Integer.MAX_VALUE/10“，由于还要加digit，因此必然越界；
                  如果res正好等于”Integer.MAX_VALUE/10“，由于还要加digit，因此要确保digit小于7.
                ②如果res已经小于”Integer.MIN_VALUE/10“，由于还要加digit，因此必然越界；
                  如果res正好等于”Integer.MIN_VALUE/10“，因此要确保digit<-8*/
            if (res>Integer.MAX_VALUE/10||
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



    /*518.。。。322求解的是最少的硬币数，518求解所有的方案数
    给定不同面额的硬币 coins 和一个总金额 amount，计算并返回可以凑成总金额的硬币组合数。硬币可以无限次使用，顺序不同的组合
    视为同一种（只要硬币种类和个数一样就算同一组合）。
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <=amount; j++) {
                dp[j] = dp[j] + dp[i-coins[i]];
            }
        }
        return dp[amount];
    }

    /*LCR 143 子结构判断

     */
    /**
     *【复杂度分析】
        时间：最坏情况要对 A 的每个节点调用一次 match，match 最坏遍历 B 所有节点，所以 O(|A|*|B|)。
        空间：递归深度 O(h)，h 为树高。
     */
    /*方法1：DFS递归的写法*/
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A==null&&B==null) return true;
        if (A==null||B==null) return false;
        return match(A,B)||
                isSubStructure(A.left,B)||
                isSubStructure(A.right,B);
    }

    private boolean match(TreeNode A, TreeNode B) {
        /*base case:
            B == null 在 match 中返回 true：因为 match 的语义是“B 的所有节点是否能在 A 对应位置上被匹配”。如果 B 已经
        走完（所有子节点都验证完），说明匹配成功（即 B 的结构已全部匹配），所以返回 true。
            A == null 而 B != null 返回 false：说明 A 的这条路径结束了，但 B 还有节点需要匹配，无法满足。
        * */
        if (B==null) return true;
        if (A==null) return false;
        if (A.val!=B.val) return false;
        return match(A.left,B.left)&&match(A.right,B.right);
    }


    /*方法2：将上面的递归改成迭代的写法
        外层用一个栈遍历 A 的每个节点。内层用另一个栈匹配 B 的每个节点。
    这样就完全不依赖递归调用栈，适合处理特别深的大树。
    */
    /**
     【复杂度分析】
         时间复杂度：和递归版一样，最坏 O(|A| * |B|)。
         空间复杂度：O(h1 + h2)，h1 为遍历 A 用的栈深度，h2 为匹配 B 时用的栈深度，总体受树高限制。
     */
    public boolean isSubStructure_diedai(TreeNode A, TreeNode B) { //实现层序遍历A的每一个节点
        if (A==null||B==null) return false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(A);
        while (!queue.isEmpty()){
            /**与层序遍历的区别1：研究每一层节点之前不用获取queue的size。*/
            TreeNode cur = queue.poll();
            /**与层序遍历的区别2：添加逻辑————如果发现和B的value相同，则使用”isMatch“方法判断是不是可以匹配成功*/
            if (cur.val==B.val&&isMatch(cur,B)) return true;
            if (cur.left!=null) queue.offer(cur.left);
            if (cur.right!=null) queue.offer(cur.right);
        }
        return false;
    }

    private boolean isMatch(TreeNode A, TreeNode B) {
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



    /*572
    给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
    二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
    * */
    /**
     【题目的理解】
         题目与“子结构”题（LCR 143）相似，递归和迭代的写法和非递归也是类似的代码几乎没啥区别。。。但这个题判
     断条件更严格————root中的某一个子树 和 subRoot必须一摸一样，即subRoot是null的时候root子树对应的节点也
     必须是null...但是LCR 143就不是，143中root的子树包含subRoot的结构就可以，两个子树不要求完全相等
     */
    /*解法1：递归的写法*/
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root==null&&subRoot==null) return true;
        if (root==null) return false;
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
        if (subRoot==null) return true;
        if (root==null) return false;

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
            if (rootNode==null&&subRootNode==null) return true;
            if (rootNode==null||subRootNode==null) return false;
            if (rootNode.val!=subRootNode.val) return false;
            queue.offer(new TreeNode[]{rootNode.left,subRootNode.left});
            queue.offer(new TreeNode[]{rootNode.right,subRootNode.right});
        }
        return true;
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
     */
    public int numDecodings(String s) {
        if(s==null||s.length()==0||s.charAt(0)=='0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <=s.length(); i++) {
            char c2 = s.charAt(i - 1);
            char c1 = s.charAt(i - 2);
            if (c2!='0') dp[i] += dp[i-1]; //①i位置的值依赖于 i-1位置的值
            int val = (c1-'0')*10+c2-'0';
            if (val>=10&&val<=26) dp[i] += dp[i-2]; //②i位置的值依赖于 i-2位置的值
        }
        return dp[s.length()];
    }

    /*空间优化到滚动变量*/
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
          3. 如果该节点的左右孩子都不是null，则有两种做法：一种是让左子树的最大值代替这个节点，另一种是让右
        子树的最小值代替这个节点......下面的代码使用右子树的最小值来代替要被删除的节点
          【说明】下面的代码中，实际上将1和2的逻辑写在了一起
     */
    /*解法1：递归方法删除*/
    public TreeNode deleteNode(TreeNode root, int key) {
        /*step1：特殊情况的考虑*/
        if(root==null) return null;
        /*step2：外层的if-else if-else会先找到要删除的节点。进入else块说明找到了要删除的节点*/
        if (key<root.val){
            return deleteNode(root.left,key);
        } else if (key>root.val) {
            return deleteNode(root.right,key);
        }else { /**进入到else，说明定位到了要删除的节点*/
            /*情况1：如果只有一个子节点 或者 一个子节点都没有，则返回不是null的节点*/
            if (root.left==null) return root.right;
            if (root.right==null) return root.left;
            /*情况2：代码走到这里说明左右子树都不是null，因此————
                首先，去找右子树的最小值来代替这个节点；
                然后，取右子树递归删除最小值的那个节点*/
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            return deleteNode(root.right, minNode.val);
        }
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


    /*328
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */

    /**
     【出错点】
            1. 拼接下一个节点的代码“curEven.next = curEven.next.next;”——————即“把下下一个节点拼接到当
        前节点的后面”。。容易误写成“curEven = curEven.next.next;”
     */
    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode odd = head; /**奇数节点的头*/
        ListNode even = head.next,curEven = even; /**偶数节点的头*/
        while (curEven!=null&&curEven.next!=null){
            odd.next = odd.next.next; //连接下一个奇数节点
            odd = odd.next;

            curEven.next = curEven.next.next; //连接下一个偶数节点
            curEven = curEven.next;
        }
        odd.next = even;
        return head;
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
            TrieNode[] children = new TrieNode[26];
            boolean isEnd;
        }

        private final TrieNode root;

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



    /*225
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
            外(即循环变量”i<size-1“，除了它)的其他所有元素依次弹出 并 重新添加进队列。。。
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
            int size = queue.size();
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
    /**
     DP数组的含义：设 dp[i][j] 表示字符串 s[i..j]（从 i 到 j 的子串）中，最长回文子序列的长度。
     状态转移方程：
            情况1：如果i、j位置的字符相同，则dp[i][j] = dp[i+1][j-1] + 2
            情况2：如果i、j位置的字符不相同，则dp[i][j] = max(dp[i+1][j], dp[i][j-1])
     初始化：i和j相等的时候，子串的长度是1，回文串的长度是1
     遍历顺序的确定：因为 dp[i][j] 依赖于 dp[i+1][j-1]，所以必须从 区间长度 递增的顺序来填表（i 从大到小，j 从小到大）。
     */
    /*二维的dp，下面是“宫水题解”，这种枚举子串长度的思想比较难想.....*/
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len<=n; len++) {
            for (int i = 0; i+len-1 < n; i++) {
                int j = i+len-1;
                if (s.charAt(i)==s.charAt(j)){
                    dp[i][j] = dp[i-1][j-1]+2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }


}
