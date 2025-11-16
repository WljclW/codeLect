package leecode_Debug._hot100_NeedMore;

/**
 * @author: Zhou
 * @date: 2025/6/21 16:23
 *  31. 下一个排列
 *  287. 寻找重复数
 *  5. 最长回文子串
 *  300. 最长递增子序列
 *  322. 零钱兑换
 *  279. 完全平方数
 *  394. 字符串解码
 *  4. 寻找两个正序数组的中位数
 *  131. 分割回文串
 *  22. 括号生成
 */

import leecode_Debug.top100.ListNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Zhou
 * @date: 2025/6/29 19:50
 * 【合并区间】将List转换为数组：return res.toArray(new int[res.size()][]);
 */
public class NeedMore01 {

    /*
     * 438.给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * */
//    public List<Integer> findAnagrams(String s, String p) {
//
//    }

    /*
     * 76.给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
     * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * */
//    public String minWindow(String s, String t) {
//
//    }

    /*73.
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和
     * 列的所有元素都设为 0 。请使用 原地 算法。
     * */
//    public void setZeroes(int[][] matrix) {
//
//    }

    /*4.
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
    * */
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//    }


    //下一个排列
//    public void nextPermutation(int[] nums) {
//
//    }

    /*148.
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。*/
//    public ListNode sortList(ListNode head) {
//    }


    /*146.
    请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    实现 LRUCache 类：
    LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
    * */
    class LRUCache {
        /*
        1. LRU中每一个节点的结构
        2. 每一个节点都是key-value的形式存储值，并且有pre、next指针————可以实现前溯、后溯遍历*/
        class DouListNode{
            int key;
            int value;
            DouListNode pre;
            DouListNode next;
            public DouListNode(int key, int value) { /**【注】必须使用key-value构造器，涉及到put操作的时候新建节点存储key-value*/
                this.key = key;
                this.value = value;
            }
        }

        int capacity;
        int size;
        DouListNode head;
        DouListNode tail;
        HashMap<Integer,DouListNode> map = new HashMap<Integer,DouListNode>();

        /*
        LRUCache构造器中完成：
            ①capacity的指定；②size的初始化；③head节点、tail节点的初始化 以及 指针的连接
        */
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new DouListNode(-1, -1);
            tail = new DouListNode(-1, -1);
            head.next = tail;
            tail.pre = head;
        }

        /*
        get的思路：从map里面获取————
            情况1：如果找到的节点是null，说明没有直接返回-1；
            情况2：如果找到了节点，则：①把这个节点移动到头节点；②返回节点的值————即node.value
        * */
        public int get(int key) {
            DouListNode node = map.get(key);
            if (node==null){
                return -1;
            }else {
                removeNode(node);
                addNodeToHead(node);
                return node.value;
            }
        }

        /*
        put操作的思路：从map中获取节点node————
            情况1：如果找到了node不是null，说明LRU链表中已经有key对应的节点。则：①移动这个节点到LRU的头结点；
        ②把这个node的值更新为put方法的参数value
            情况2：如果找到的node是null，说明LRU链表中之前没有key对应的节点。则：①使用key-value创建新的节点newNode；
        ②map中添加key-newNode的映射关系；③将newNode添加到LRU的head节点；------至此完成了新节点的添加操作
        ④更新size变量；④判断是不是超出容量，超出的话需要删除尾节点。------这两步完成添加节点的后置校验
        * */
        public void put(int key, int value) {
            DouListNode node = map.get(key);
            if (node==null){
                DouListNode newNode = new DouListNode(key,value);
                map.put(key,newNode);
                addNodeToHead(newNode);
                size++;
                if (size>this.capacity){
                    removeRealTail();
                }
            }else {
                node.value = value;
                removeNode(node);
                addNodeToHead(node);
            }
        }

        /*
        删除尾部节点：
            ①通过tail.pre拿到真正的尾节点删除；②从map中删除节点
        * */
        private void removeRealTail() {
            DouListNode tailReal = tail.pre;
            removeNode(tailReal);
            map.remove(tailReal.key);
        }

        /*删除一个节点：*/
        private void removeNode(DouListNode node) {
            DouListNode pre = node.pre;
            DouListNode next = node.next;
            pre.next = next;
            next.pre = pre;
        }

        /*把一个节点添加为头节点*/
        private void addNodeToHead(DouListNode newNode) {
            DouListNode next = head.next;
            head.next = newNode;
            newNode.next = next;
            next.pre = newNode;
            newNode.pre = head;
        }
    }

    /*138复制链表*/
    public Node copyRandomList(Node head) {
        Node cur = head;
        while (cur!=null){
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next;
        }

        cur =  head;
        while (cur!=null){
            if (cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        Node res = head.next;
        cur = res;
        while (cur.next!=null){
            head.next = head.next.next;
            cur.next = cur.next.next;
            head = head.next;
            cur = cur.next;
        }
        return res.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    /*322.
    * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总
    * 金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。*/
//    public int coinChange(int[] coins, int amount) {}

    /*300.
    * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺
    * 序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
//    public int lengthOfLIS1(int[] nums) {}


    /*279.
    * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

       完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数
       * 自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。*/
//    public int numSquares(int n) {
//
//    }


    //最长回文子串
//    public String longestPalindrome(String s) {
//
//    }


    /*287.
    * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1
    * 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
    * */
//    public int findDuplicate(int[] nums) {}
}
