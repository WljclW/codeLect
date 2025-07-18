package leecode_Debug._hot100;

import leecode_Debug.linkList.ListNode;
import leecode_Debug.top100.TreeNode;

import javax.sound.sampled.Line;
import java.util.LinkedList;
import java.util.List;

/**
 * 快排
 * 5. 最长回文子串
 * 143. 重排链表
 * 82. 删除排序链表中的重复元素 II
 * 93. 复原 IP 地址
 * 堆排序
 */
public class codetop_5 {
    /*93
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
    StringBuilder sb;
    List<String> res;
    public List<String> restoreIpAddresses(String s) {
        sb = new StringBuilder(s);
        res = new LinkedList<>();
        traceback(s,0,0);
        return res;
    }

    private void traceback(String s, int index, int pointNum) {
        if (pointNum == 3 && isValid(s.substring(index))) {
            res.add(new String(sb));
        }
        for (int i = index; i < s.length() - 1; i++) {
            if (isValid(s.substring(index, i + 1))) {
                sb.insert(i + pointNum, '.');
                traceback(s, i, pointNum + 1); /**这里不执行pointNum+=1行不行*/
                sb.deleteCharAt(i + pointNum);
            }
        }
    }

    private boolean isValid(String substring) {
        if (substring.length() != 1 && substring.charAt(0) == '0') {
            return false;
        }
        if (substring.length() > 3 || (substring.length() == 3 && Integer.valueOf(substring) > 255)) {
            return false;
        }
        return true;
    }

    /*
    82
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
    /**
     * 【与83的区别】由于要删除所有值相等的节点，因此最后链表中可能一个节点都不剩，因此必须借助dummy节点来解题
     * */
//    public ListNode deleteDuplicates(ListNode head) {
//        if (head==null||head.next==null) return head;
//        ListNode dummy = new ListNode(-1, head),cur = dummy;
//        while (cur.next!=null&&cur.next.next!=null){
//            int val = cur.next.val;
//            while (cur.next.next!=null&&cur.next.next.val==val){
//                cur.next = cur.next.next;
//            }
//            cur = cur.next;
//        }
//        return dummy.next;
//    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (head!=null){
            if (head.next!=null&&head.val==head.next.val){
                while(head.next!=null&&head.val==head.next.val){
                    head = head.next;
                }
                cur.next = head.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }




    /*83*/
    /**
     * 【注意区分82】这个题会保留一个相同值的节点，82题会删除所有值相等的节点。
     * 【解题思路】不需要使用dummy节点，思考角度————”当前的节点是cur，如果下一个节点的value等于cur。value，就删除下一个节点————即等
     *      价于语句cur.next = cur.next.next，确保不会发生空指针即可“。
     * */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        /*至少保证还有2个节点，才有研究判断的必要*/
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) { //如果现在节点cur的值等于下一个节点值，则将cur节点的next指向下下一个节点————即删除下一个节点
                cur.next = cur.next.next;
            } else { //否则的话，说明不相等。cur后移继续研究后面的节点
                cur = cur.next;
            }
        }
        return head;
    }



    /*124
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
//    public int maxPathSum(TreeNode root) {
//
//    }

    /*165
    给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。

比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。

返回规则如下：

如果 version1 < version2 返回 -1，
如果 version1 > version2 返回 1，
除此之外返回 0。
    * */
//    public int compareVersion(String version1, String version2) {
//
//    }

    /*105
    给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
    * */
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//
//    }


    /*129
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
每条从根节点到叶节点的路径都代表一个数字：

例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。

叶节点 是指没有子节点的节点。
    * */
    /*深度优先遍历写法*/
    public int sumNumbers(TreeNode root) {
        return dfsSumNumbers(root, 0);
    }

    private int dfsSumNumbers(TreeNode root, int curSum) {
        if (root == null) return 0;
        curSum = curSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return curSum;
        }
        return dfsSumNumbers(root.left, curSum) + dfsSumNumbers(root.right, curSum);
    }




    /*层序遍历的解法*/
    /**
     *【层序遍历的思路】
     *      使用两个队列，一个队列用于存放二叉树的节点（与层序遍历原始版本的队列作用类似）；一个用于存放”从root到当前节点的路径和“。然后进
     *  入while循环研究每一个节点————如果某一个节点的左右孩子都是空，则说明这是一个路径，需要累加到最终的结果sum；否则将不为null的结果添
     *  加到第一个队列，并且将它对应的路径添加到第二个队列。【注意】孩子的路径和计算：父节点的路径和*10 + 孩子节点的value
     */
    public int sumNumbers1(TreeNode root) {
        if (root==null) return 0;
        int sum = 0;
        LinkedList<TreeNode> treeNodes = new LinkedList<>(); //存放二叉树的节点
        LinkedList<Integer> integers = new LinkedList<>(); //存放节点对应的路径和
        treeNodes.offer(root);
        integers.offer(root.val);
        while (!treeNodes.isEmpty()){
            /*step1：弹出一个节点 以及 它的路径和，研究它的孩子节点*/
            TreeNode cur = treeNodes.poll();
            Integer curVal = integers.poll();
            /*step2：如果发现了叶子节点，则更新sum*/
            if (cur.left==null&&cur.right==null){
                sum += curVal;
            }
            /*step3：将不是null的节点添加进treeNodes，并且在integers中加入对应的路径和*/
            if (cur.left!=null){
                treeNodes.offer(cur.left);
                integers.offer(curVal *10+cur.left.val); //需要将父节点的路径和*10,并且加上它自己的值
            }
            if (cur.right!=null){
                treeNodes.offer(cur.right);
                integers.offer(curVal*10 + cur.right.val); //右孩子也是同样的道理
            }
        }
        return sum;
    }


    /*98
    给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左子树只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
    * */
    /**
     * 【关键】中序遍历，使用一个变量记录中序遍历前一个节点的值。。
     *      如果发现某一个节点的值不大于记录变量的值，就返回false；
     *      否则的话，中序遍历结束，返回true。
     * */
    public boolean isValidBST(TreeNode root) {
        if (root==null) return true;
        LinkedList<TreeNode> deque = new LinkedList<>();
        TreeNode cur = root;
        int preValent= Integer.MIN_VALUE;
        while (cur!=null || !deque.isEmpty()){
            if (cur!=null){
                deque.push(cur);
                cur = cur.left;
            }else{
                TreeNode nowNode = deque.pop();
                if (nowNode.val<=preValent){
                    return false;
                }
                preValent = nowNode.val;
                cur = cur.right;
            }
        }

        return true;
    }


    //143
//    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
//
//    L0 → L1 → … → Ln - 1 → Ln
//    请将其重新排列后变为：
//
//    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
//    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    /**
     * 【建议】建议使用解法reorderList1，代码逻辑更清晰
     * 【解题的关键】
     *      找到链表中间节点、翻转链表、合并两个链表
     *      ①反转链表时最好从slow.next进行翻转；同时注意slow。next=null，否则链表会出现环
     * 【这道题的特殊之处】
     *      情况1：奇数个节点，比如1——>2———>3——>4——>5——>null，重排之后变为1——>5——>2——>4——>3——>null。而奇数节点slow指针会来到最中间
     *  的节点3，此时后半部分只用从slow.next翻转即可，反转后第一个链表1——>2———>3——>null，后一个链表5——>4——>null，循环的判断条件是head2!=null
     *      情况2：偶数个节点，比如1——>2———>3——>4——>null，重拍之后需要变为1——>4———>2——>3——>null。。而偶数个节点slow指针会来到中间两
     *  个的后一个节点即节点3，此时后半部分只用从slow.next翻转即可，反转后第一个链表是1——>2———>3——>null,后一个链表变为4——>null，循环结
     *  束的条件依然是head2!=null。
     *      综上，不论是奇数节点还是偶数节点(slow会来到中间节点 或者 中间的后一个节点)，只用从slow.next翻转后半部分的链表；然后把slow.next
     *  置为null
     * */
    public void reorderList(ListNode head) {
        if (head==null||head.next==null) return;
        ListNode mid = findMid(head);
        ListNode head2 = reverse(mid);
        while (head2!=null){
            ListNode head2Next = head2.next;
            ListNode headNext = head.next;
            head.next = head2;
            head2.next = headNext;
            head = headNext;
            head2 = head2Next;
        }
        head.next = null;
    }

    /*反转链表的原始代码，hot100*/
    private ListNode reverse(ListNode mid) {
        ListNode pre = null,cur = mid;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*找中间节点的代码。奇数个会来到最中间的节点；偶数个会来到中间两个的后一个节点*/
    private ListNode findMid(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast  =fast.next.next;
        }
        return slow;
    }


    public void reorderList1(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return;
        /*step1：找到链表的中间节点。常规做法*/
        ListNode mid = findMid(head);
        /*step2：翻转后半部分的链表*/
        ListNode head2 = reverse(mid.next); /**err：注意是从slow。next，因此严格来说并不是翻转后半部分链表，而是从后半部分的第二个节点开始翻转*/
        mid.next = null; /**err：注意需要将前半部分的最后一个节点置为null，否则会出现环*/
        /*step3：合并反转后的链表*/
        merge(head,head2);
    }

    private void merge(ListNode first, ListNode second) {
        while (second!=null){
            /*step1：记录两个节点的下一个节点*/
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;
            /*step2：组装节点的连接关系*/
            first.next = second;
            second.next = firstNext;
            /*step3：移动两个指针*/
            first = firstNext;
            second = secondNext;
        }
    }


    /*43
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    * */
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();

        int[] multiRes = new int[len1 + len2];
        /*step1：两个数依次倒着相乘。i位置和j位置的相乘结果对应在结果数组第”i+j+1“的位置*/
        for (int i = len1 - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                int curSum = digit1 * digit2 + multiRes[i + j + 1]; //digit1和digit2相乘的结果对应第”i+j+1“位，还要加上这个位置本来的数（是上一轮计算出来的）。
                multiRes[i + j] += curSum / 10; /**err：把进位的信息加到前一个位置*/
                multiRes[i + j + 1] = curSum % 10; //”i+j+1“位置存放digit1和digit2的相乘信息
            }
        }
        /*step2：将存放结果的数组拼到StringBuilder中*/
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < multiRes.length; i++) {
            if (sb.length() == 0 && multiRes[i] == 0) continue; /**err：跳过前导零*/
            sb.append(multiRes[i]);
        }
        return sb.toString();
    }




    /*162
    峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
    * */
    public int findPeakElement(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (nums[mid]>=nums[mid+1]){ /**err：带等于的话行不行？？*/
                r = mid;
            }else {
                l = mid+1;
            }
        }
        return l;
    }


    /*662
    给你一棵二叉树的根节点 root ，返回树的 最大宽度 。

树的 最大宽度 是所有层中最大的 宽度 。

每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。

题目数据保证答案将会在  32 位 带符号整数范围内。
    * */
    public int widthOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        int res = 0;

        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        LinkedList<Integer> integers = new LinkedList<>();
        treeNodes.add(root);
        integers.add(0);
        while (!treeNodes.isEmpty()){
            int size = treeNodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = treeNodes.poll();
                Integer curVal = integers.poll();
                if (cur.left!=null){
                    treeNodes.add(cur.left);
                    integers.add(curVal*2);
                }
                if (cur.right!=null){
                    treeNodes.add(cur.right);
                    integers.add(curVal*2+1);
                }
                if (i==size-1){
                    res = Math.max(curVal+1,res);
                }
            }
        }
        return res;
    }


    /*113
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。
    * */
//    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//
//    }


    /*209
    给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    * */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0,cur = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (cur<nums.length){
            /*step1：不管三七二十一，都先放进窗口*/
            sum += nums[cur];
            /*step2：满足条件时，不断的缩小窗口*/
            while (sum >= target) {
                res = Math.min(res, cur - left + 1); /**err：窗口必须是满足条件的时候才能更新窗口的大小*/
                sum -= nums[left++];
            }
            cur++;
        }
        return res==Integer.MAX_VALUE?0:res;
    }


    /*718
    给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
    * */
//    public int findLength(int[] nums1, int[] nums2) {
//
//    }


    /*543
    给你一棵二叉树的根节点，返回该树的 直径 。

二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。

两节点之间路径的 长度 由它们之间边数表示。
    * */
//    public int diameterOfBinaryTree(TreeNode root) {
//
//    }
}
