package leecode_Debug._hot100;

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
//        public TreeNode deserialize(String data) {
//
//        }
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




}
