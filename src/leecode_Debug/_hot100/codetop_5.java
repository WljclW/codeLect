package leecode_Debug._hot100;

import leecode_Debug.linkList.ListNode;
import leecode_Debug.top100.TreeNode;

import javax.sound.sampled.Line;
import java.util.HashMap;
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
            return;
        }
        if (pointNum==3){ /**err：这里必须加这一句，因为前面的if是两个条件，可以发现如果pointNum等于3但是不符合条件会走到这里，就会导致栈溢出*/
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isValid(s.substring(index, i + 1))) {
                sb.insert(i+1 + pointNum, '.');
                traceback(s, i+1, pointNum + 1); /**这里不执行pointNum+=1行不行*/
                sb.deleteCharAt(i+1 + pointNum);
            }
        }
    }

    private boolean isValid(String substring) {
        if (substring.length()==0) return false;
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
     * 【建议】建议看官方的解法：见方法deleteDuplicates
     * 【与83的区别】由于要删除所有值相等的节点，因此最后链表中可能一个节点都不剩，因此必须借助dummy节点来解题
     * */
    /*官网的解法*/
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (head!=null){
            if (head.next!=null&&head.val==head.next.val){
                while(head.next!=null&&head.val==head.next.val){
                    head = head.next; //此时head节点其实也不应该存在，因为它和前面的节点值是相同的
                }
                cur.next = head.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /*自己的写法。建议看官方的写法，比较清晰*/
    public ListNode deleteDuplicates_82(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        while (cur.next!=null&&cur.next.next!=null){
            int val = cur.next.val;
            if (cur.next.next.val==val){
                while (cur.next.next!=null&&cur.next.next.val==val){
                    cur.next = cur.next.next;
                }
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }




    /*83*/
    /**
     * 【建议】建议使用双指针的解法，见方法deleteDuplicates_83
     * 【注意区分82】这个题会保留一个相同值的节点，82题会删除所有值相等的节点。
     * 【解题思路】不需要使用dummy节点，思考角度————”当前的节点是cur，如果下一个节点的value等于cur。value，就删除下一个节点————即等
     *      价于语句cur.next = cur.next.next，确保不会发生空指针即可“。
     * */
    /*官方的解*/
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

    /*双指针的解法，看起来更清晰。
    * 易错点————出了while循环需要将slow的next指针置为null!!，否则下面的测试用例会出错：
            输入
            head =
            [1,1,2,3,3]
            输出
            [1,2,3,3]
            预期结果
            [1,2,3]
    * */
    public ListNode deleteDuplicates_83(ListNode head) {
        if (head == null||head.next==null) return head;

        ListNode slow = head,fast = head.next;

        while (fast != null) {
            if (fast.val != slow.val) { /*说明这个节点应该在结果的链表中，因此拼接到slow的后面*/
                slow.next = fast;
                slow = slow.next;  // 更新 slow
            }
            fast = fast.next; // 无论是否重复，fast 都前进
        }
        slow.next = null; /**err：如果没有这句话，有测试用例是错的*/

        return head;
    }


    /*124
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列
    中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    /**
     *【启发】通过这个题，结合543题。。感受一下：决策信息 跟 返回信息不一样的情况。两个题都是借助一
     *   个额外的函数来完成信息的处理和决策，但是其实参数与原题参数是一样的
     *      决策信息是该节点的最大路径和，即"left+right+root.val"；但是返回的信息是该节点的贡
     *  献值，即"Math.max(left,right)+root.val"
     *【为什么需要使用额外的方法，并且看起来都只有一个参数区别不大？？总结】
     *    1. 124二叉树的最大路径和。为什么需要两个方法，原因就在于决策的表达式（即代表最终返回值的
     * 计算），与每个结点应该返回给父节点的信息是不一样的！！每个节点的返回信息是它最大的贡献值，左
     * 右子树只能选一；每个结点的决策值，是左边的贡献，右边的贡献，再加节点的值这两者之间的差异，再
     * 加上原方法要求返回整个树的最大路径，也不是节点的最大贡献。因此必须使用两个方法，新建一个方法
     * 返回值是节点的最大贡献，并在决策中更新结果——即要求的最大路径
     *    2. 124与543有类似的道理。543中每个节点返回给父节点的信息是以它为根的子树高度（即它的左右
     * 子树高度的最大值+1），但是每个节点的决策信息是左右子树高度之和。因此需要使用额外的函数来改变
     * 返回值信息，同时在这个过程中更新结果
     * */
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode root) {
        /*step1：特殊情况的考虑*/
        if (root==null) return 0;
        /*step2：计算左、右孩子节点的贡献————即左子树上包含左孩子节点的最大路径和，右子树上包含右孩子节点的
        *   最大路径和*/
        int left = Math.max(maxGain(root.left), 0);
        int right = Math.max(maxGain(root.right), 0);
        /*step3：更新答案*/
        maxSum = Math.max(maxSum,left+right+root.val);
        /*step4：返回该节点的贡献值*/
        return Math.max(left,right)+root.val;
    }



    /*165
    给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
    比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
    返回规则如下：
    如果 version1 < version2 返回 -1，
    如果 version1 > version2 返回 1，
    除此之外返回 0。
    * */
    /**
     *【思路】就是从左往右一次判断。
     *【关键】while循环条件的“||”暗含着两个版本号长度不一致的情况。出现版本号长度不一样的适合，版本号短的那一个
     *      后面的值就是0.
     * */
    public int compareVersion(String version1, String version2) {
        int len1 = version1.length(),len2 = version2.length();
        int cur1 = 0,cur2 = 0;
        while (cur1 < len1 || cur2 < len2) { /**err：注意这里必须是“||”连接*/
            int val1 = 0;
            for (; cur1 < len1 && version1.charAt(cur1) != '.'; cur1++) {
                val1 = val1 * 10 + version1.charAt(cur1) - '0';
            }
            cur1++; //跳过版本号之间的"."

            int val2 = 0;
            for (; cur2 < len2 && version2.charAt(cur2) != '.'; cur2++) {
                val2 = val2 * 10 + version2.charAt(cur2) - '0';
            }
            cur2++;
            if (val1 != val2) {
                return val1 > val2 ? 1 : -1;
            }
        }
        return 0;
    }

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
    /*解法1：深度优先遍历写法*/
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




    /*解法2：层序遍历的解法*/
    /**
     *【层序遍历的思路】
     *      使用两个队列，一个队列用于存放二叉树的节点（与层序遍历原始版本的队列作用相同）；一个用于存放”从root到当前节点的路径和“。然后进
     *  入while循环研究每一个节点————如果某一个节点的左右孩子都是空，则说明这是一个路径，需要累加到最终的结果sum；否则将不为null的结果添
     *  加到第一个队列，并且将它对应的路径添加到第二个队列。【注意】孩子的路径和计算：父节点的路径和*10 + 孩子节点的value
     */
    public int sumNumbers1(TreeNode root) {
        if (root==null) return 0;
        int sum = 0; //记录答案
        LinkedList<TreeNode> treeNodes = new LinkedList<>(); //存放二叉树的节点
        LinkedList<Integer> integers = new LinkedList<>(); //存放节点对应的路径和
        treeNodes.offer(root);
        integers.offer(root.val);
        while (!treeNodes.isEmpty()){
            /*step1：弹出一个节点 以及 从根节点到它的路径和。下面 研究它的孩子节点*/
            TreeNode cur = treeNodes.poll();
            Integer curVal = integers.poll();
            /*step2：如果发现了叶子节点，则更新sum。。
            * 说明，现在的这个节点就是符合条件的节点，需要更新sum*/
            if (cur.left==null&&cur.right==null){
                sum += curVal;
            }
            /*step3：将不是null的节点添加进treeNodes，并且在integers中加入对应的路径和*/
            if (cur.left!=null){
                treeNodes.offer(cur.left);
                integers.offer(curVal *10+cur.left.val); //一个节点到根节点的路径和，就是 父节点路径和的10倍+节点自身的值
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
     *
     * @param root
     * @return
     */
    /*递归判断*/
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        // step1：base case
        if (root == null) return true;
        /*step2：检查root.val是不是满足最值要求。。注意：不包含等于*/
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
        return isValidBST(root.left, min, root) //左子树的max是root.val
                && isValidBST(root.right, root, max); //右子树的最小值是root.val
    }


    /**
     * 【关键】中序遍历，使用一个变量记录中序遍历前一个节点的值。。
     *      如果发现某一个节点的值不大于记录变量的值，就返回false；
     *      否则的话，中序遍历结束，返回true。
     * 【易错点】记录前一个值的变量初始值不能使用Integr.MIN_VALUE，而要使用“long preValent = Long.MIN_VALUE;”。
     *      用Integr.MIN_VALUE或导致测试用例"[-2147483648]"报错，而-2147483648其实就正好是Integer.MIN_VALUE.
     * */
    public boolean isValidBST1(TreeNode root) {
        if (root==null) return true;
        LinkedList<TreeNode> deque = new LinkedList<>();
        TreeNode cur = root;
//        int preValent= Integer.MIN_VALUE; /*这里用int类型是错误的！！！*/
        long preValent = Long.MIN_VALUE; /**err：注意这里不能使用Integer.MIN_VALUE；并且不能声明为Long类型。*/
        while (cur!=null || !deque.isEmpty()){
            if (cur!=null){
                deque.push(cur);
                cur = cur.left;
            }else{  /*修改中序遍历中打印节点的值的过程*/
                TreeNode nowNode = deque.pop();
                if (nowNode.val<=preValent){ /**err：二叉搜索树是严格升序的*/
                    return false;
                }
                preValent = nowNode.val; /*preValent声明为long类型时这里才能隐式转换完成；否则如果声明为Long类型，这里会编译报错*/
                cur = nowNode.right;
            }
        }

        return true;
    }

    /*
    143
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
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
     *      其实，这个题之所以能从中间节点的下一位开始翻转的底层细节原理：如果链表的节点数是偶数，则中间的两个节点比如上面的“2——>3”在重排
     *  链表之后就位于结果的最后面，并且顺序不变，因此翻转3这个节点之后的链表就可以了
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
        /*step2：翻转后半部分的链表
        * 【说明】下面的方式翻转链表之后，后半部分链表以head2开头，比前一半链表的节点数少一个节点（如果原
        * 来链表的节点数是奇数）或者少两个节点（如果原来链表的节点数是偶数）*/
        ListNode head2 = reverse(mid.next); /**err：注意是从slow。next，因此严格来说并不是翻转后半部分链表，而是从后半部分的第二个节点开始翻转*/
        mid.next = null; /**err：注意需要将前半部分的最后一个节点置为null，否则会出现环*/
        /*step3：合并反转后的链表*/
        merge(head,head2);
    }

    private void merge(ListNode first, ListNode second) {
        while (second != null) {
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
    /**
     *【说明】i位数的整数 和 j位数的整数相乘，结果是“i+j”位数，最少是“i+j-1”位数
     *【解题思路】声明一个len1+len2长度的数组，来存放计算出来的每一位。第一个数index=i的数和第二个数index=j的
     *      数相乘后的结果应该放在结果中的i+j+1的位置。
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";

        int len1 = num1.length(), len2 = num2.length();
        /**【说明】i位数的整数 和 j位数的整数相乘，结果是“i+j”位数，最少是“i+j-1”位数*/
        int[] multiRes = new int[len1 + len2];
        /*step1：两个数依次倒着相乘。i位置和j位置的相乘结果对应在结果数组第”i+j+1“的位置*/
        for (int i = len1 - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                int curSum = digit1 * digit2 + multiRes[i + j + 1]; //digit1和digit2相乘的结果对应第”i+j+1“位，还要加上这个位置本来的数（是上一轮计算出来的）。
                multiRes[i + j] += curSum / 10; /**err：把进位的信息加到前一个位置，注意是加到前一位，而不是赋值给前一位*/
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
    给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在
    位置即可。你可以假设 nums[-1] = nums[n] = -∞ 。
    你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
    * */
    /**
     *【题解的原理】比较中间位置mid的值 和 下一个位置mid+1的值，沿着值大的单个方向一直大去，一定能找到峰值。
     *      比如：nums[mid]>nums[mid+1]，则r=mid，朝着左边一定能找到峰值。但是如果去左边找，可能会有，但
     * 是也可能没有峰值。
     *      通俗的理解：中点所在地方，可能是某座山的山峰，山的下坡处，山的上坡处，如果是山峰，最后会二分终
     * 止也会找到，关键是我们的二分方向，并不知道山峰在我们左边还是右边，送你两个字你就明白了，爬山（没错，
     * 就是带你去爬山），如果你往下坡方向走，也许可能遇到新的山峰，但是也许是一个一直下降的坡，最后到边界。
     * 但是如果你往上坡方向走，就算最后一直上的边界，由于最边界是负无穷，所以就一定能找到山峰，总的一句话，
     * 往递增的方向上，二分，一定能找到，往递减的方向只是可能找到，也许没有。—————通俗易懂
     * */
    public int findPeakElement(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) { /**err：这里带等于也是可以的。但是按照原理来讲，最好不带*/
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


    /*662
    给你一棵二叉树的根节点 root ，返回树的 最大宽度 。树的 最大宽度 是所有层中最大的 宽度 。
    每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构
    相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
    题目数据保证答案将会在  32 位 带符号整数范围内。
    * */
    public int widthOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        int res = 0;
        /*step1：声明变量 及 队列的初始化————
        *   1. 记录每一层第一个节点编号first、最后一个节点的编号last
        *   2. 声明一个队列treeNodes存放每一层的节点，声明一个队列integers存放每一层节点对应的编号
        *   3. 放入根节点 以及 根节点的编号
        */
        int first = 0,last = 0;
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        LinkedList<Integer> integers = new LinkedList<>();
        treeNodes.add(root);
        integers.add(0);
        /*step2：遍历其中的每一个节点进行研究*/
        while (!treeNodes.isEmpty()){
            int size = treeNodes.size();
            for (int i = 0; i < size; i++) {
                /*2.1：弹出一个节点，以及该节点的编号*/
                TreeNode cur = treeNodes.poll();
                Integer curVal = integers.poll();
                /*2.2：如果索引i来到第一个或者最后一个，需要更新first以及last的值*/
                if (i==0) first = curVal;
                if (i==size-1) last = curVal;
                /*2.3：把不是null的节点及它的编号加进队列*/
                if (cur.left!=null){
                    treeNodes.add(cur.left);
                    integers.add(curVal*2);
                }
                if (cur.right!=null){
                    treeNodes.add(cur.right);
                    integers.add(curVal*2+1);
                }
                /*2.4：来到每一层的最后一个节点，则本层的节点数是last-first+1，更新res*/
                if (i==size-1){
                    res = Math.max(last-first+1,res);
                }
            }
        }
        return res;
    }


    /*113
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
    叶子节点 是指没有子节点的节点。
    * */
    LinkedList<List<Integer>> resPathSum;
    LinkedList<Integer> pathPathSum;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        resPathSum = new LinkedList<>();
        pathPathSum = new LinkedList<>();
        pathSumBack(root, targetSum);
        return resPathSum;
    }

    private void pathSumBack(TreeNode root, int targetSum) {
        if (root == null) return;
        /*step1：路径中添加当前的节点值*/
        pathPathSum.offer(root.val);
        targetSum -= root.val;
        /*step2：判断当前的路径是不是符合条件的解*/
        if (targetSum == 0 && root.left == null && root.right == null) {
            resPathSum.offer(new LinkedList<>(pathPathSum));
        }
        /*step3：递归的研究root的左右子树*/
        pathSumBack(root.left, targetSum);
        pathSumBack(root.right, targetSum);
        /*step4：撤销选择*/
        pathPathSum.removeLast();
        targetSum += root.val;
    }


    /*209
    给定一个含有 n 个正整数的数组和一个正整数 target 。
    找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返
    回其长度。如果不存在符合条件的子数组，返回 0 。
    * */
    /**
     *【题解思路】研究每一个数，放进窗口，更新窗口内的和sum；只要这个和不小于target，就更新res 并且 尝试缩小
     *      窗口的长度（即更新left的值）；研究完每一个数之后返回res
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0,cur = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (cur<nums.length){
            /*step1：不管三七二十一，都先放进窗口*/
            sum += nums[cur];
            /*step2：满足条件时，不断的缩小窗口*/
            while (sum >= target) {
                res = Math.min(res, cur - left + 1); /**err：窗口必须是满足条件的时候才能更新窗口的大小，因此这个题必须是在while循环里面更新答案res*/
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
    /**    下面的解法是错误的，根节点的结果不一定就是最大值，即并不是真正的结果。。因此需要针对于每一个节点，需
     * 要求出以这个节点为根的树的直径，并使用Math.max更新结果
     * */
//    public int diameterOfBinaryTree(TreeNode root) {
//        if (root==null) return 0;
//        int leftDepth = depth(root.left);
//        int rightDepth = depth(root.right);
//        return leftDepth+rightDepth;
//    }
//
//    private int depth(TreeNode root) {
//        if (root==null) return 0;
//        int left = depth(root.left);
//        int right = depth(root.right);
//        return 1+Math.max(left,right);
//    }

    /**
     * 【思路】
     *      1. 树的直径 = 某个节点左子树深度 + 右子树深度
            2. 用后序遍历计算每个节点的左右子树深度，并在过程中更新最大直径。
            使用全局变量记录最大直径。
     * */
    int maxDiameterOfBinaryTree = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDiameterOfBinaryTree;
    }

    private int depth(TreeNode root) {
        if (root==null) return 0;
        /*step1：分别计算出这个节点的左子树、右子树的高度*/
        int left = depth(root.left);
        int right = depth(root.right);
        /*step2：求出这个节点作为根的子树的直径————该节点左子树、右子树的高度和*/
        maxDiameterOfBinaryTree = Math.max(maxDiameterOfBinaryTree,left+right);
        /*step3：返回以这个节点为根的子树的高度————该节点的左子树、右子树的最大高度 + 1*/
        return Math.max(left,right)+1;
    }




    /**====================================================================================================*/
    /**112问的是有没有这样的路径和
     * 113题要求求出所有符合的路径
     * 437题不要求从根节点出发，求出这样的路径的数量
     * */
    /*112
    给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条
    路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。叶子节点 是指没有子节
    点的节点。
    */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null)
            return false;
        if (root.left==null&&root.right==null)
            return targetSum==root.val;
        return hasPathSum(root.left,targetSum-root.val)||
                hasPathSum(root.right,targetSum-root.val);
    }


    /*437,与560题是类似的
    给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
    路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
    * */
    /**
     * 【思路】用一个map存放前缀和，此时前缀和的含义：从根节点到这个节点的路径和。。。灭一次存放之前先看map中有没有
     *      符合条件的前缀和，即map.get(curSum-targetSum)的值
     * */
    public int pathSum_437(TreeNode root, int targetSum) {
        HashMap<Long, Integer> perfixMap = new HashMap<>();
        perfixMap.put(0L,1);
        return dfs(root,0,perfixMap,targetSum);
    }

    private int dfs(TreeNode root, long curSum, HashMap<Long, Integer> perfixMap, int targetSum) {
        if (root==null) return 0;

        int res = 0;
        curSum += root.val;
        res += perfixMap.getOrDefault(curSum-targetSum,0);
        perfixMap.put(curSum,perfixMap.getOrDefault(curSum,0)+1);
        res += dfs(root.left,curSum,perfixMap,targetSum);
        res += dfs(root.right,curSum,perfixMap,targetSum);
        perfixMap.put(curSum,perfixMap.get(curSum)-1);
        return res;
    }
}
