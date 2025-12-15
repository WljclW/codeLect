package leecode_Debug._hot100;

import leecode_Debug.top100.ListNode;
import leecode_Debug.top100.TreeNode;

import java.util.*;

/**
 * 快排
 * 5. 最长回文子串
 * 143. 重排链表
 * 82. 删除排序链表中的重复元素 II
 * 93. 复原 IP 地址
 * 堆排序
 */
public class codetop_5 {
    /*
    82
    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    * */
    /**
     * 【建议】建议看官方的解法：见方法 deleteDuplicates.....
     *          但是练习中发现 deleteDuplicates_82 解法用的熟
     * 【常错的点】（1）不论哪种解法，“cur = cur.next;”都要放在else块，这是关键！！
     *            （2）不论哪一种解法，cur指针不是指向dummy，就是指向结果链表中的最后一个节点(即保证这个节点一定是在结果中)
     * 【与83的区别】由于要删除所有值相等的节点，因此最后链表中可能一个节点都不剩，因此必须借助dummy节点来解题
     * */
    /**
     *【易错点】不论哪种解法，“cur = cur.next;”都必须要放在else块中，这是关键
     *      如果写错了位置，会出现如下的错：
                 输入
                 head =
                 [1,2,3,3,4,4,5]
                输出
                [1,2,4,4,5]
                预期结果
                [1,2,5]
     */
    /*官网的解法*/
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head), cur = dummy;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) { /**出了这个while循环head会来到相等的所有节点的最后一个，因此在出了while循环有“cur.next=head.next”，跳过这最后一个重复值的节点*/
                    head = head.next; //此时head节点其实也不应该存在，因为它和前面的节点值是相同的
                }
                cur.next = head.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /*自己的写法。建议看官方的写法，比较清晰*/
    /**【变量说明】cur指向的是已经拼接到结果链表中的最后一个节点（即cur及之前的位置一定是在结果链表中（除了dummy），即保证不会有重复值的节点）；
     cur.next指向的是第一个没有研究的节点————即目前还不确定它是不是应该留下来
      【步骤详解】
            step1：特殊情况的判断。
            step2：设置虚拟头节点。。因为要删除所有的相同节点，因此最后链表可能是空，即一个节点都没有。
            step3：只要cur后面还有两个节点，就进入while进行研究————
                   ----情况1：如果"cur.next.val"等于"cur.next.next.val"，即cur后两个节点的值相等，
               此时进入到if语句块。。。需要做什么？使用while循环跳过所有值为"cur.next.val"的节点，此
               时就是把cur.next.next节点删除————语句就是"cur.next.next  = cur.next.next.next;"。while
               循环结束后，cur.next.next要麽是null，要麽不是null值也不是cur.next.val。但是由于cur.next
               的值出现过相等的情况，因此还要删除cur.next节点————语句就是"cur.next = cur.next.next;"
                   ----情况2：如果"cur.next.val"不等于"cur.next.next.val"。说明cur.next的值只出现一次，
               此时需要保留cur.next节点————对应的语句就是"cur = cur.next;"
            step4：结束while循环，说明cur后面不足两个节点了，就不用研究了，返回dummy.next。
            [补充说明]由于while循环内部已经完成了 无用节点的删除，因此出了while循环不用特殊处理就可以，直
                接返回
     */
    public ListNode deleteDuplicates_82(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode dummy = new ListNode(-1, head),cur = dummy;
        /**解释这里while的循环条件
         * 1. 明白一点：任何一个时刻cur指针要么指向dummy(初始情况)、要么指向结果链表中的最后一个节点(即确保该节点在结果中并且是
         *      目前已知的最后一个，后面的结果还没有研究)
         * 2. while的循环条件"cur.next!=null&&cur.next.next!=null"就是保证后面至少还有两个节点。为什么这样做？？因为至少
         *      有两个节点的时候，才能讨论是不是有相同数值的节点。。。。。cur及之前(包括cur)的节点研究过了，肯定没有重复，因此
         *      后面要求至少还有两个节点才继续研究，否则如果只有一个节点那必然没有与它值相同的节点了
         * */
        while (cur.next!=null&&cur.next.next!=null){
            int val = cur.next.val; /**记录相等的值是多少*/
            if (cur.next.next.val==val){ /**如果相等，用while循环跳过所有相等的节点*/
                while (cur.next.next!=null&&cur.next.next.val==val){
                    /**删除掉cur后面的第二个节点。即”cur===>node1====>node2====>node3“变
                     为”cur===>node1====>node3”，删除中间的node2。。。因为node2.val与node1.val
                     的值是相等的。*/
                    cur.next.next  = cur.next.next.next;
                }
                //进入到if，说明cur.next节点的值后面出现了，因此删除cur.next。
                cur.next = cur.next.next;
            }else { /**err：这里的else是精髓，必须使用else来实现*/
                cur = cur.next;
            }
        }
//        cur.next = null; /**有这一句是不对的！！原因：上面的if-else已经把节点拼接完成了，即使最后一个节点是null，也拼接上了，思考思考~~*/
        return dummy.next;
    }

    public ListNode deleteDuplicates_82_1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        /**解释这里while的循环条件
         * 1. 明白一点：任何一个时刻cur指针要么指向dummy(初始情况)、要么指向结果链表中的最后一个节点(即确保该节点在结果中并且是
         *      目前已知的最后一个，后面的结果还没有研究)
         * 2. while的循环条件"cur.next!=null&&cur.next.next!=null"就是保证后面至少还有两个节点。为什么这样做？？因为至少
         *      有两个节点的时候，才能讨论是不是有相同数值的节点。。。。。cur及之前(包括cur)的节点研究过了，肯定没有重复，因此
         *      后面要求至少还有两个节点才继续研究，否则如果只有一个节点那必然没有与它值相同的节点了
         * */
        while (cur.next!=null&&cur.next.next!=null){
            int curVal = cur.next.val; //step1：先记录一下cur.next的值（cur.next就是第一个还没有研究的节点）
            if (cur.next.next.val==curVal){ //step2:如果cur后面两个节点的值相等，则进入if将这些相等值的节点全部删除
                ListNode tmp = cur.next.next;
                while (tmp!=null&&tmp.val==curVal){
                    tmp = tmp.next;
                }
                /**到这里只能说明tmp节点和前面的节点不相等，不能说明tmp和后面的节点值不相等，链表需要删除中间
                 * 重复值的所有节点，但是cur指针不能动！！！！比如“2-2-2-3-3-4-5”，刚开始tmp指针会来到第一个3，
                 * cur指针其实还是在dummy节点，此时会让cur.next=第一个3，但是cur指针不能后移！！因此下面的代码
                 * “cur=cur.next”必须写在else块中。————换句话说，只有一个cur.next.val!=cur.next.next.val的时候
                 * cur指针才能后移*/
                cur.next = tmp;
            }else //step3：如果cur.next的值和cur.next.next的值不相等，则需要将cur.next拼接到cur的后面————表示cur.next需要在结果链表中
                cur = cur.next; /**err：这里必须有else子句*/
        }
        return dummy.next;
    }





    /*83
    删除排序链表中的重复元素
给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
    * */
    /**
     * 【建议】建议使用双指针的解法，见方法deleteDuplicates_83。。。
     * 【注意区分82】这个题会保留一个相同值的节点，82题会删除所有值相等的节点。造成的区别：
     *          83题可以使用双指针 且 不用使用虚拟头节点，因为相同的节点会保留一个，不会说到
     *      最后一个节点都没有；
     *          而82题就不一定了，可能到最后一个节点都没有，因此必须要使用一个虚拟头结点！！
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
        出错原因：出了while循环时fast指向null，slow指向第一个3，但是第一个3和第二个3中间的链没有断开！！
               因此出了while循环需要执行"slow.next = null;"
    * */
    public ListNode deleteDuplicates_83(ListNode head) {
        if (head == null) return head; /**err：必须要有，因为下面fast指针的初始值是head.next*/

        ListNode slow = head,fast = head.next;

        while (fast != null) {
            if (fast.val != slow.val) { /*slow和fast的值不相等：说明这个节点应该在结果的链表中，因此fast拼接到slow的后面*/
                slow.next = fast;
                slow = slow.next;  // 只有“当新的节点添加到结果链中”时，才更新 slow
            }
            fast = fast.next; /*无论slow和fast的值是否重复，fast 都前进*/
        }
        slow.next = null; /**err：如果没有这句话，初始的测试用例中，有测试用例是错的*/

        return head; /*这个题返回head是没有问题的，因为相同的节点会保留一个，因此head一定是保留的！！*/
    }

    /**双指针的另一种写法*/
    public ListNode deleteDuplicates_(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode slow = head,fast = head.next;
        while (fast!=null){
            while (fast!=null&&fast.val==slow.val){
                fast = fast.next;
            }
            slow.next = fast;
            slow = slow.next;
        }
        return head;
    }


    /*124
    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列
    中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    /**
     *【启发】通过这个题，结合543题。。感受一下：决策信息 跟 返回信息不一样的情况。两个题都是借助一
     *   个额外的函数来完成信息的处理和决策，但是其实参数与原题参数是一样的。。。就是这种两信息的不
     *   一致，导致必须额外的使用另一个方法来完成dfs，并在这个过程中做决策、更新结果，最后返回结果。
     *      比如，这个题来说：决策信息是该节点的最大路径和，即"left+right+root.val"(就是当前
     *   的节点，可以去右子树、可以去左子树，能得到的路径最大值)；但是返回的信息是该节点的贡献值，
     *   即"Math.max(left,right)+root.val"(任何一个节点的贡献值，只能选择一个分支去计算，因此
     *   只能走左子树 或者 右子树，这个贡献值是给它的父节点使用的)
     *【为什么需要使用额外的方法，并且看起来都只有一个参数区别不大？？总结】
     *    1. 124二叉树的最大路径和。为什么需要两个方法，原因就在于决策的表达式（即代表最终返回值的
     * 计算），与每个结点应该返回给父节点的信息是不一样的！！每个节点的返回信息是它最大的贡献值，左
     * 右子树只能选一；每个结点的决策值，是左边的贡献，右边的贡献，再加节点的值。这两者之间的差异，再
     * 加上原方法要求返回整个树的最大路径，也不是节点的最大贡献。因此必须使用两个方法，新建一个方法
     * 返回值是节点的最大贡献，并在决策中更新结果——即要求的最大路径
     *    2. 124与543有类似的道理。543中每个节点返回给父节点的信息是以它为根的子树高度（即它的左右
     * 子树高度的最大值+1），但是每个节点的决策信息是左右子树高度之和。因此需要使用额外的函数来改变
     * 返回值信息，同时在这个过程中更新结果。
     * */
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) { /*方法作用：返回“以root为根的树 的最大路径和”*/
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode root) { /*方法作用：返回以root为根的树 的高度*/
        /*step1：特殊情况的考虑*/
        if (root==null) return 0;
        /*step2：计算左、右孩子节点的贡献————即左子树上包含左孩子节点的最大路径和，右子树上包含右孩子节点的
        *   最大路径和。
        *   【注】要求贡献的最小值时0。原因：求解的是最大路径和，因此如果小于0，则它带来负收益，大不了那个分
        * 支不走呗！也不能说就这样带来负收益~~~*/
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
     *【思路】双指针，从左往右依次判断。
     *【关键】while循环条件的“||”暗含着两个版本号长度不一致的情况。出现版本号长度不一样的时候，版本号短的那一个
     *      后面的值就是0.
     * */
    public int compareVersion(String version1, String version2) {
        int len1 = version1.length(),len2 = version2.length();
        int cur1 = 0,cur2 = 0;
        while (cur1 < len1 || cur2 < len2) { /**err：注意这里必须是“||”连接，多次错。。。跟“链表两数相加”类似，只要有一个还没到头就继续*/
            /*step1：分别初始化两个val1和val2，计算两个'.'之间的数；并且保证如果没有数也是初始值0*/
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

            /*step2：如果不相等就说明已经比较出两个的大小关系了*/
            if (val1 != val2) {
                return val1 > val2 ? 1 : -1;
            }
        }
        return 0;
    }

    /*写法2：分割字符串*/
    public int compareVersion2(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int n = Math.max(v1.length, v2.length);
        for (int i = 0; i < n; i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 != num2) {
                return num1 > num2 ? 1 : -1;
            }
        }
        return 0;
    }


    /*105
    给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
    * */
    /**见codetop_10*/
    HashMap<Integer,Integer> inorderMap = new HashMap<>();
    int preOrderIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
        if (l>r) return null; /**err：这个条件不能少，否则会导致不断递归的时候“preOrderIndex越界”*/
        int rootVal = preorder[preOrderIndex++];
        TreeNode root = new TreeNode(rootVal);
        Integer index = inorderMap.get(rootVal);
        root.left = buildTree(preorder,inorder,l,index-1);
        root.right = buildTree(preorder,inorder,index+1,r);
        return root;
    }


    /*129
    给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    每条从根节点到叶节点的路径都代表一个数字：

    例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    计算从根节点到叶节点生成的 所有数字之和 。
    叶节点 是指没有子节点的节点。
    * */

    /**
     *【解法】关于深度优先的解析看：https://leetcode.cn/problems/sum-root-to-leaf-numbers/solutions/2730644/jian-ji-xie-fa-pythonjavacgojsrust-by-en-gbu9/
     *      深度优先的 有返回值版本 和 无返回值版本需要搞清楚
     *【补充】从下面的两个解法中，理解 深度优先“有返回值”和“无返回值而是用全局变量记录结果”的区别
     */
    /*解法1：深度优先遍历写法*/
    public int sumNumbers(TreeNode root) {
        return dfsSumNumbers(root, 0);
    }

    private int dfsSumNumbers(TreeNode root, int curSum /*从根节点到root父节点的路径和*/) {
        if (root == null) return 0;
        /*step1：更新curSum*/
        curSum = curSum * 10 + root.val;
        /*step2：如果当前的节点是叶子节点，则更新答案*/
        if (root.left == null && root.right == null) {
            return curSum;
        }
        /*step3：递归它的左右孩子*/
        return dfsSumNumbers(root.left, curSum) +
                dfsSumNumbers(root.right, curSum);
    }

    /*写法2：深度优先遍历的另一种写法....使用全局变量来记录结果*/
    int maxSumNumbers = 0;
    public int sumNumbers_(TreeNode root) {
        dfsSumNumbers1(root,0);
        return maxSumNumbers;
    }

    private void dfsSumNumbers1(TreeNode root, int curSum) {
        if (root==null) return;
        curSum = curSum*10 + root.val;
        if (root.left==null&&root.right==null)
            maxSumNumbers += curSum; /**遍历的过程中，碰到叶子节点更新全局变量maxSumNumbers*/
        dfsSumNumbers(root.left, curSum);
        dfsSumNumbers(root.right, curSum);
    }

    /*写法2的变形*/
    private int ansSumNumbers;

    public int sumNumbers_2(TreeNode root) {
        if (root == null) return 0;
        dfs(root, root.val); /**【说明】与写法2的区别在于初始调用的时候不再是"dfs(root,0)"*/
        return ans;
    }

    private void dfs(TreeNode node, int x) {
        /**
         *写在这里处理数据，就相当于在先序的位置添加处理逻辑
         */
        if (node.left == null && node.right == null) { // 叶子节点
            ans += x;
            return;
        }
        /**【注】因为这种方法在初始调用时就要把root节点的值传进去，因此必须先判断不能是null*/
        if (node.left != null) {
            dfs(node.left, x * 10 + node.left.val);
        }
        if (node.right != null) {
            dfs(node.right, x * 10 + node.right.val);
        }
    }




    /*解法2：层序遍历的解法*/
    /**
     *【层序遍历的思路】
     *      使用两个队列，一个队列用于存放二叉树的节点（与层序遍历原始版本的队列作用相同）；一个用于存放“从root到当
     * 前节点的路径和”。【具体操作】每次将一个节点放进队列，就对应的把该节点对应的路径和也放进队列！！————这种“使用两
     * 个队列并行操作”的思想和做法其实是层序遍历在具体场景使用时的常规操作！！！要记住。
     *      然后进入while循环研究每一个节点————如果某一个节点的左右孩子都是空，则说明这是一个路径，需要累加到最终的
     * 结果sum；否则将不为null的结果添加到第一个队列，并且将它对应的路径添加到第二个队列。【注意】孩子的路径和计算：父
     * 节点的路径和*10 + 孩子节点的value
     *【补充说明】
     *      ”并行操作“的含义就是说：其中一个队列用于存放二叉树的节点；另一个队列用于存放节点所对应的信息(比如：编号信
     * 息、路径和信息...)。然后每一次节点入队1时它对应的信息也入队2、节点出队1的时候它对应的信息也出队2，并在出队入队
     * 时根据”题目要求“进行处理
     */
    public int sumNumbers1(TreeNode root) {
        if (root==null) return 0;
        int sum = 0; //记录答案
        LinkedList<TreeNode> treeNodes = new LinkedList<>(); //存放二叉树的节点
        LinkedList<Integer> integers = new LinkedList<>(); //存放节点对应的路径和(即从根节点按照1-->2-->3这样组成的123这种数)
        treeNodes.offer(root);
        integers.offer(root.val);
        while (!treeNodes.isEmpty()){
            /*step1：弹出一个节点 以及 从根节点到它的路径和。下面 研究它的孩子节点*/
            TreeNode cur = treeNodes.poll();
            Integer curVal = integers.poll();
            /*step2：如果发现了叶子节点，则更新sum。。
            * 说明，现在的这个节点就是符合条件的节点，需要更新sum*/
            /**
             *这里也是同样的道理，不能到了null节点才更新res，那样就错了，相当于叶子节点的
             * 两个孩子都统计了，不对
             */
            if (cur.left==null&&cur.right==null){
                sum += curVal;
            }
            /*step3：将不是null的孩子节点添加进treeNodes，并且在integers中加入孩子节点对应的路径和*/
            if (cur.left!=null){
                treeNodes.offer(cur.left);
                integers.offer(curVal *10+cur.left.val); //一个节点到根节点的路径和，就是 父节点路径和的10倍 + 节点自身的值（其实就是1、2、3组装成123的过程）
            }
            if (cur.right!=null){
                treeNodes.offer(cur.right);
                integers.offer(curVal*10 + cur.right.val); //右孩子也是同样的道理
            }
        }
        return sum;
    }

    /*解法3：层序遍历的另一种写法*/
    public int sumNumbers_3(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> vals = new LinkedList<>();
        if (root == null) return 0;
        /**
         与解法2的区别在下面的地方————
                由于开始的时候采用的是：queue.offer(root);
                                       vals.offer(0);
                因此vals对应的值是“从根节点到它的父节点的路径和”，这个定义就影响了后续“如果是叶子节点时计算路
            径的过程”、“普通节点入队列后，vals对应的入队值的问题”
         根据解法2和解法3可以知道，初始的时候显示root节点入队列，同时会入队列一个值。这个值可以是root.val也可
         以是0，重要的是在后续更新答案的时候明确这一点，并能形成闭环
         */
        queue.offer(root);
        vals.offer(0);
        int res = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            Integer curVal = vals.poll();
            if (cur.left == null && cur.right == null) {
                res += (curVal * 10 + cur.val);
            }
            if (cur.left != null) {
                queue.offer(cur.left);
                vals.offer(curVal * 10 + cur.val);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                vals.offer(curVal * 10 + cur.val);
            }
        }
        return res;
    }



    /*98
    给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

    有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。
    * */
    /*递归判断*/
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /* 方法参数含义：限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
    boolean isValidBST(TreeNode root, TreeNode min/*当前节点左子树的最小值*/, TreeNode max/*当前节点的最大值*/) {
        // step1：base case
        if (root == null) return true;
        /*step2：检查root.val是不是满足最值要求。。注意：不包含等于*/
        if (min != null && root.val <= min.val) return false; /**搜索二叉树要求”严格单调“，相等是不对的*/
        if (max != null && root.val >= max.val) return false;
        /*step3：返回”左子树“和”右子树“的判断结果
            限定左子树的最大值是 root.val，右子树的最小值是 root.val*/
        return isValidBST(root.left, min, root) //root节点左子树的max是root.val
                && isValidBST(root.right, root, max); //root节点右子树的最小值是root.val
    }


    /**
     * 【关键】中序遍历，使用一个变量记录中序遍历前一个节点的值。。
     *      如果发现某一个节点的值不大于记录变量的值，就返回false；
     *      否则的话，中序遍历结束，返回true。
     * 【易错点】记录前一个值的变量初始值不能使用Integr.MIN_VALUE，而要使用“long preValent = Long.MIN_VALUE;”。
     *      用Integr.MIN_VALUE或导致测试用例"[-2147483648]"报错，而-2147483648其实就正好是Integer.MIN_VALUE.
     *      [补充说明]题目中说树中节点值的范围为"-231 <= Node.val <= 231 - 1"~~~是可以取到Integer的边界值的！！
     * */
    public boolean isValidBST1(TreeNode root) {
        if (root==null) return true;
        LinkedList<TreeNode> deque = new LinkedList<>();
        TreeNode cur = root;
//        int preValent= Integer.MIN_VALUE; /*这里用int类型是错误的！！！用int范围过小了————原因：系欸但的最小值是int的最小值*/
        long preValent = Long.MIN_VALUE; /**err~~~：注意这里不能使用Integer.MIN_VALUE；并且不能声明为Long类型（声明为对象类型使没有int->long的隐式转换的，就会报错）。*/
        while (cur!=null || !deque.isEmpty()) {
            if (cur != null) {
                deque.push(cur);
                cur = cur.left;
            } else {  /*修改中序遍历中打印节点的值的过程*/
                TreeNode nowNode = deque.pop();
                if (nowNode.val <= preValent) { /**err：二叉搜索树是严格升序的。相邻值是不行的*/
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
     * 【建议】建议使用解法reorderList3，代码逻辑更清晰
     * 【解题的关键】
     *      找到链表中间节点、翻转链表、合并两个链表
     *      ①反转链表时最好从slow.next进行翻转；同时注意slow。next=null，否则链表会出现环
     * 【这道题的特殊之处】
     *      情况1：奇数个节点，比如1——>2———>3——>4——>5——>null，重排之后变为1——>5——>2——>4——>3——>null。而奇数节点slow指针会来到最中间
     *  的节点3，此时后半部分只用从slow.next翻转即可，反转后第一个链表1——>2———>3——>null，后一个链表5——>4——>null，循环的判断条件是head2!=null
     *      情况2：偶数个节点，比如1——>2———>3——>4——>null，重拍之后需要变为1——>4———>2——>3——>null。。而偶数个节点slow指针会来到中间两
     *  个的后一个节点即节点3，此时后半部分只用从slow.next翻转即可，反转后第一个链表是1——>2———>3——>null,后一个链表变为4——>null，循环结
     *  束的条件依然是head2!=null。这种情况下后一半链表的节点数比前一半链表的节点数少2。
     *      综上，不论是奇数节点还是偶数节点(slow会来到中间节点 或者 中间的后一个节点)，只用从slow.next翻转后半部分的链表；然后把slow.next
     *  置为null
     *      其实，这个题之所以能从中间节点的下一位开始翻转的底层细节原理：如果链表的节点数是偶数，则中间的两个节点比如上面的“2——>3”在重排
     *  链表之后就位于结果的最后面，并且顺序不变，因此翻转3这个节点之后的链表就可以了！！————本质原因
     * */
    /*第一种写法，建议*/
    public void reorderList1(ListNode head) {
        if (head==null||head.next==null) return;
        /*step1：找到链表的中间节点。常规做法*/
        ListNode mid = findMid(head);
        /*step2：翻转后半部分的链表
        * 【说明】下面的方式翻转链表之后，后半部分链表以head2开头，比前一半链表的节点数少一个节点（如果原
        * 来链表的节点数是奇数）或者少两个节点（如果原来链表的节点数是偶数）*/
        ListNode head2 = rever2(mid.next); /**err：注意是从slow.next，因此严格来说并不是翻转后半部分链表，而是从后半部分的第二个节点开始翻转*/
        mid.next = null; /**err：注意需要将前半部分的最后一个节点置为null，否则会出现环*/
        /*step3：合并反转后的链表*/
        merge(head,head2);
    }

    /*合并两个链表。
    *      此时后面那一个链表的节点数一定比前面一半链表的节点数少。。少一个（如果原链表节点数是奇数）或者两个（如果原链表节点数是偶数）*/
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


    /*第二种写法，建议使用下面的写法*/
    public void reorderList2(ListNode head) {
        ListNode mid = findMid(head);
        ListNode start = mid.next;
        mid.next =  null; /**err：这种写法中这一步是不能少的*/
        ListNode head2 = rever2(start);
        while (head2!=null){
            ListNode next1 = head.next;
            ListNode next2 = head2.next;
            head.next = head2;
            head2.next = next1;
            head = next1;
            head2 = next2;
        }
    }

    private ListNode rever2(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*常规的寻找中间节点的做法*/
    private ListNode findMid(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     第三种写法，不用特别的找到哪个中间节点以及和前面的节点断开。。。
        step1：常规的寻找到中间节点，返回即可（不用将next置为null等操作）
        step2：常规的反转链表的操作，头节点是step1得到的中间节点
        step3：合并step2反转得到的头节点 以及 原始头节点的两半链表
     */
    public void reorderList3(ListNode head) {
        if (head==null||head.next==null) return; /**这个特例的判断没有也是ok的*/
        ListNode mid = findMid1(head); //step1：找到链表的中间节点
        ListNode head2 = reverse1(mid); //step2：反转以“step1返回的中间节点”后面的那部分链表
        merge2(head,head2); //step3：合并以head、head2为头的两个链表
    }

    private void merge2(ListNode head, ListNode head2) {
        ListNode cur = head;
        ListNode cur2 = head2;
        while (cur2!=null&&cur2.next!=null){ /**注意：这里的两个条件，一个都不能少*/
            /*①先记录一下两半链表中，当前位置的下一个节点*/
            ListNode next1 = cur.next;
            ListNode next2 = cur2.next;
            /*②完成节点的连接：cur——>cur2———>cur.next（即next1）*/
            cur.next = cur2;
            cur2.next = next1;
            /*③调整当前研究位置：cur来到next1（即初始时记录的cur.next）;cur2来到next2（即初始时记录的cur2.next）*/
            cur = next1;
            cur2 = next2;
        }
    }

    /*常规的翻转链表的方法*/
    private ListNode reverse1(ListNode head) {
        ListNode pre = null,cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*常规的寻找中间节点的方法（奇数——>中间节点；偶数——>中间偏后的节点）*/
    private ListNode findMid1(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null &&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /*合并链表的代码对比如下“328 奇偶链表”的代码*/
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return head;
        ListNode odd = head,even = head.next;
        ListNode evenHead = head.next;
        while (even!=null&&even.next!=null){
            /**拆分的时候相对简单一点，只需要设置好while条件，然后拆分即可*/
            odd.next = odd.next.next;
            odd = odd.next;

            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }


    /*43
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    * */
    /**
     *【说明】
     *      1. i位数的整数 和 j位数的整数相乘，结果最多是“i+j”位数，最少是“i+j-1”位数。
     *      2. 第一个数第i位的数 和 第二个数第j位的数 相乘，结果对应的是第”i+j+1“位置
     *      3. 【注意】在计算过程中，结果位置的数是直接赋值，赋值给res[i+j+1]；但是进位信息是加和的，需要将计算出来的进位
     *   信息，加到res[i+j]。。。。为什么？因为计算的时候已经加了res[i+j+1]位置的值，因此计算出来的就是结果；但是进位信息
     *   是当前计算新带来的，因此要加到以前的进位信息res[i+j]上
     *【解题思路】声明一个len1+len2长度的数组，来存放计算出来的每一位。第一个数index=i的数和第二个数index=j的
     *      数相乘后的结果应该放在结果中的i+j+1的位置。
     *【错误】
     *      1. 两个数相乘是每一位数都会进行相乘，因此需要使用双层循环！
     *      2. 每一位置的计算“int curSum = digit1 * digit2 + multiRes[i + j + 1]”————即nums1和nums2对应位置的数相乘再
     *  加res中index=i+j+1位置本来就有的数。nums1的i位置 和 nums2的j位置 相乘的结果应该在res的index=i+j+1位置。
     *
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0"; /**err：没有不行。没有的话，如果结果是0，会输出""，两个数相乘再怎么也得有个数*/
        /*前提：声明len1+len2长度的数组暂存计算的结果*/
        int len1 = num1.length(), len2 = num2.length();
        /**【说明】i位数的整数 和 j位数的整数相乘，结果是“i+j”位数，最少是“i+j-1”位数*/
        int[] multiRes = new int[len1 + len2]; /**err：【注意】这里的长度是两个数字的长度和，如果写成了[len1+len2+1]，会导致计算结果扩大了10倍，结果总是正确结果的十倍*/
        /*step1：两个数依次倒着相乘。i位置和j位置的相乘结果对应在结果数组第”i+j+1“的位置*/
        for (int i = len1 - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                int curSum = digit1 * digit2 + multiRes[i + j + 1]; //digit1和digit2相乘的结果对应第”i+j+1“位，还要加上这个位置本来的数（是上一轮计算出来的）。
                multiRes[i + j] += curSum / 10; /**err：把进位的信息加到前一个位置，注意是加到前一位，而不是赋值给前一位。如果使用"="导致之前相乘的信息就丢失了*/
                multiRes[i + j + 1] = curSum % 10; //”i+j+1“位置存放digit1和digit2的相乘信息
            }
        }
        /*step2：将存放结果的数组拼到StringBuilder中*/
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < multiRes.length; i++) {
            if (sb.length() == 0 && multiRes[i] == 0) continue; /**err：跳过前导零的代码实现*/
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
     *【题解的原理】比较中间位置mid的值 和 下一个位置mid+1的值，沿着值大的单个方向一直下去，一定能找到峰值。
     *      比如：nums[mid]>nums[mid+1]，则r=mid，朝着左边一定能找到峰值。但是如果去右边找，可能会有，但
     * 是也可能没有峰值。————为什么？因为朝着大的一边走，走到最后是-∞，题中说了两边的边界认为是-∞，根据峰值的
     * 定义可知这一区域必存在峰值。
     *      通俗的理解：中点所在地方，可能是某座山的山峰，山的下坡处，山的上坡处，如果是山峰，最后会二分终
     * 止也会找到，关键是我们的二分方向，并不知道山峰在我们左边还是右边，送你两个字你就明白了，爬山（没错，
     * 就是带你去爬山），如果你往下坡方向走，也许可能遇到新的山峰，但是也许是一个一直下降的坡，最后到边界。
     * 但是如果你往上坡方向走，就算最后一直上的边界，由于最边界是负无穷，所以就一定能找到山峰，总的一句话，
     * 往递增的方向上，二分，一定能找到，往递减的方向只是可能找到，也许没有。—————通俗易懂
     * */
    public int findPeakElement(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l < r) { /**err：要找峰值，由于一定存在，因此最后指针的位置就是峰值位置（不用带等号）*/
            int mid = l + (r - l) / 2;
            if (nums[mid] >= nums[mid + 1]) { /**err：这里带不带等于都是可以的（本质原因：其实相等的时候朝哪一个方向进行都可以）。但是按照原理来讲，最好不带。*/
                /**err：沿着大数一边走必然能遇到峰值，并且nums[mid]可能就是峰值，因此“r=mid-1”是错误的，可能
                 错过峰值！！！
                    如果使用“r=mid-1”，就错误过峰值，比如提交时case2报错————
                             nums =
                             [1,2,1,3,5,6,4]
                             输出
                             4
                             预期结果
                             5
                 */
                r = mid;    /**【注】因为mid位置可能就是峰值 且 使用的搜索区间是闭区间，因此r不能赋值为mid-1，只能赋值为mid*/
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**chatgpt给出的while循环条件带等于的写法，没懂？？但是是正确的
     【while循环条件带不带等于的区别————以这个题为例】
        方式一：while(left<right)
             ✅ 用 while (left < right) 时：
                 循环条件保证 mid + 1 <= right；
                 所以 nums[mid + 1] 永远合法，不会越界；
                 最后退出时 left == right，直接返回即可。
             🟢 不需要额外判断。
        方式二：while(left <= right)
            ⚠️ 用 while (left <= right) 时：
                 循环最后一次可能出现：
                 left == right == nums.length - 1
                此时 mid == right == nums.length - 1
                再访问 nums[mid + 1] ➜ 数组越界！
             🛑 所以必须额外判断："if (mid == nums.length - 1 || nums[mid] >= nums[mid + 1])"
        综上，二分查找的题目中如果使用"(left<=right)"的循环判断条件，就必须注意left=right=0的时候 以
     及 left=right=nums.length-1的时候，若前者后续流程再执行“right=mid-1”就会越界，若后者后续流程再
     执行“left=mid+1”也会越界，需要注意额外的判断逻辑。
     */
    public int findPeakElement_chatgpt(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            /**会发现与“while (left < right)”写法的版本对比，就是if循环的条件多了判断的逻辑，为什么？？有什么原因呢？？
             见此方法上的注释
             */
            // 处理边界：如果 mid 是最后一个元素
            if (mid == nums.length - 1 || nums[mid] >= nums[mid + 1]) {
                // 峰值在左边（包含 mid）
                right = mid - 1;
            } else {
                // 峰值在右边
                left = mid + 1;
            }
        }

        // 最后 left 会指向峰值位置
        return left;
    }



    /*662
    给你一棵二叉树的根节点 root ，返回树的 最大宽度 。树的 最大宽度 是所有层中最大的 宽度 。
    每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构
    相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
    题目数据保证答案将会在  32 位 带符号整数范围内。
    * */
    /**理解一下这个题的BFS写法。
     1. 这个题目BFS就必须要严格的区分每一层，不区分每一层你就不知道哪个是第一个节点，哪个是第二个节点
     2. 二叉树层序遍历的题目中常见的做法：
            使用多个队列，分别存放TreeNode本身以及后续需要该节点的对应信息！！比如：这个题中需要给每一个节点编
        号。因此一个队列存储节点本身/一个队列存储该节点对应的编号，两个队列需要同步的入队列/出队列。*/
    /*写法1：宽度优先遍历（层序遍历）……
            两个队列（一个放节点，一个放节点对应的编号），也可以使用一个队
       列（队列中存放Pair<TreeNode,Integer>）。两种做法其实本质是一样的。*/
    public int widthOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        int res = 0;
        /*step1：声明变量 及 队列的初始化————
        *   1. 记录每一层第一个节点编号first、最后一个节点的编号last
        *   2. 声明一个队列treeNodes存放每一层的节点；声明一个队列integers存放每一个节点对应的编号
        *   3. 放入根节点 以及 根节点对应的编号
        */
        int first = 0,last = 0;
        LinkedList<TreeNode> nodeQueue = new LinkedList<>(); //存放每一个节点
        LinkedList<Integer> valQueue = new LinkedList<>(); //存放该节点对应的编号
        nodeQueue.add(root);
        valQueue.add(0); /**【说明】root节点对应的值是0、是1都是可以的*/
        /*step2：遍历其中的每一个节点进行研究*/
        while (!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                /*2.1：弹出一个节点，以及该节点的编号*/
                TreeNode cur = nodeQueue.poll();
                Integer curVal = valQueue.poll();
                /*2.2：如果索引i来到某层的第一个或者最后一个，需要更新first以及last的值*/
                if (i==0) first = curVal;
                if (i==size-1) last = curVal;
                /*2.3：把不是null的节点及它的编号加进队列。————这一步是层序遍历的常规操作*/
                if (cur.left!=null){
                    nodeQueue.add(cur.left);
                    valQueue.add(curVal*2);
                }
                if (cur.right!=null){
                    nodeQueue.add(cur.right);
                    valQueue.add(curVal*2+1);
                }
            }
            /*2.4：遍历完一层，更新res。。。因为包括first和last两个数，因此需要+1*/
            res = Math.max(last - first + 1, res); /**err：for循环会完成某一层节点的挨个遍历，出了for循环统计该层的宽度*/
        }
        return res;
    }

    /*解法2：递归写法*/
    int maxWidth = 0;
    Map<Integer, Long> leftMostMap = new HashMap<>();

    public int widthOfBinaryTree_digui(TreeNode root) {
        dfs(root, 0, 1L); // 从层0，编号1开始
        return maxWidth;
    }

    private void dfs(TreeNode node, int depth, long index) {
        if (node == null) return;

        // 记录该层最左边节点的编号
        leftMostMap.putIfAbsent(depth, index);

        // 当前层宽度 = 当前节点编号 - 该层最左编号 + 1
        int curWidth = (int)(index - leftMostMap.get(depth) + 1);
        maxWidth = Math.max(maxWidth, curWidth);

        // 递归左右子树
        dfs(node.left, depth + 1, index * 2);
        dfs(node.right, depth + 1, index * 2 + 1);
    }


    /*113/路径总和 II
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
    叶子节点 是指没有子节点的节点。
    * */
    /**
     这个题往往会错误的写成下面的样子。此时第一个用例就会报错————
             输入
             root =
             [5,4,8,11,null,13,4,7,2,null,null,5,1]
             targetSum =
             22
             输出
             [[5,4,11],[5,8,4]]
             预期结果
             [[5,4,11,2],[5,8,4,5]]
     看测试结果可以发现：每一个路径中都少了最后一个数，原因在于path添加进答案的时候，忘记把最后一个节点添加进path了。。
     */
//    List<List<Integer>> resPathSum;
//    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//        resPathSum  =new LinkedList<>();
//        dfsPathSum(root,targetSum,new LinkedList<Integer>());
//        return resPathSum;
//    }
//
//    private void dfsPathSum(TreeNode root, int targetSum, LinkedList<Integer> path) {
//        if (root==null) return;
    /**错误的地方集中在这里，这里直接使用path创建会导致最后一个节点没有被加进去*/
//        if (root.left==null&&root.right==null&&targetSum==root.val;
//            resPathSum.add(new LinkedList<>(path));
//        path.add(root.val);
//        dfsPathSum(root.left,targetSum-root.val,path);
//        dfsPathSum(root.right,targetSum-root.val,path);
//        path.removeLast();
//    }


    /*写法1：递归的写法*/
    /**递归的写法总是写错！！！！！！！！*/
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
            /**err：不能在这里return。如果这里return就错了！！*/
        }
        /*step3：递归的研究root的左右子树*/
        pathSumBack(root.left, targetSum);
        pathSumBack(root.right, targetSum);
        /*step4：撤销选择*/
        pathPathSum.removeLast();
        targetSum += root.val; /**看着后面没有再调用过这句，是不是这一句可以省略？？省略是可以的，但是写上也不错！*/
    }

    /*下面是另一种写法*/
    List<List<Integer>> resPathSum_2;
    public List<List<Integer>> pathSum_2(TreeNode root, int targetSum) {
        resPathSum_2 = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs(root,targetSum,path);
        return resPathSum_2;
    }

    private void dfs(TreeNode root, int targetSum, LinkedList<Integer> path) {
        if (root==null) return;
        path.add(root.val);
        if (root.left==null&&root.right==null&&targetSum==root.val){
            resPathSum_2.add(new LinkedList<>(path));
            /**
             *这里直接return是错误的，原因在于没有回溯状态
             * 原因 1：缺少回溯
                    你的 dfs 是一个「先加入当前节点到 path → 深入递归 → 回溯删除」的标准写法。
              如果你在这里直接 return，就会跳过后面的 path.removeLast()。结果就是，当前节点值
              没有被移除，path 状态被污染，回溯失败，导致上层递归时 path 中残留了错误的节点。

              原因 2：不是所有 return 都能提前结束
                    在找到一条符合要求的路径后，我们确实可以“终止这一条递归路径”，但必须先回溯干
              净，保证 path 状态正确，才能 return。如果直接 return，就破坏了递归的对称性。如果
             想在这里return，必须先回溯状态，然后再return，正确应该写成下面的（完整的写法见pathSum_3）————
                     path.removeLast(); //①先回溯状态（因为之前root.val已经添加进path了）
                     return; //②然后才能return
             */
//            return;  /**err：这里return是错误的*/
        }
        dfs(root.left,targetSum-root.val,path);
        dfs(root.right,targetSum-root.val,path);
        path.removeLast(); /**err：切记撤销选择*/
    }


    List<List<Integer>> resPathSum_3;
    public List<List<Integer>> pathSum_3(TreeNode root, int targetSum) {
        resPathSum_3 = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs1(root,targetSum,path);
        return resPathSum_3;
    }

    private void dfs1(TreeNode root, int targetSum, LinkedList<Integer> path) {
        if (root==null) return;
        path.add(root.val);
        if (root.left==null&&root.right==null&&targetSum==root.val){
            resPathSum_3.add(new LinkedList<>(path));
            /**
             1. 这里如果想return，必须先回溯状态。参考方法“pathSum_2”中的注释。
             2. 回忆“排列”、“子集”、“集合”等问题中开始判断后往往可以直接return，虽然不return一般也没事。但是往往
             return也没问题。。。但是这个题就不一样了！！
                根本原因就是这个题在path添加进答案以前，会先将root.val添加到path，就导致这一轮递归其实状态已经变
             了，因此返回前必须回归状态。索引return之前需要先执行“path.removeLast()”恢复path的状态
             */
            path.removeLast();
            return;
        }
        dfs(root.left,targetSum-root.val,path);
        dfs(root.right,targetSum-root.val,path);
        path.removeLast();
    }


    /**113题BFS的迭代写法（强烈建议结合112题的BFS写法理解）。。。
     112题仅仅是判断有没有那样的路径，遍历时需要的信息：节点、从根节点到这个节点的路径和。因此使用到
     两个队列————一个存储节点，一个存储从根节点到这个节点的路径和。。
     疑问1：为什么使用队列？？因为是BFS的写法，BFS需要使用队列；
     疑问2：为什么使用两个队列，因为需要两个信息（其实这里使用Pair这个类型也可以，但是为了逻辑清
     晰，二叉树中的BFS通常将不同的信息存储到不同的队列）
     113题不仅仅需要得到是不是有这样的路径，还要将所有满足的路径返回。此时我们需要的信息：节点（判断是
     不是叶子节点以及得到它的左右孩子，BFS遍历必须的一个元素）、从根节点到该节点的路径和信息（判断是不是符
     合题目要求的路径）、从根节点到该节点的路径（如果满足条件时需要打印）。因此此时需要使用3个队列分别存储
     这些不同的信息
     */
    public List<List<Integer>> pathSum_diedai(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;

        LinkedList<TreeNode> queueNodes = new LinkedList<>();
        LinkedList<Integer> queueVal = new LinkedList<>();
        LinkedList<List<Integer>> queuePath = new LinkedList<>();
        queueNodes.offer(root);
        queueVal.offer(root.val);
        queuePath.offer(new LinkedList<>(List.of(root.val)));
        while (!queueNodes.isEmpty()){
            TreeNode curNode = queueNodes.poll();
            Integer curVal = queueVal.poll();
            List<Integer> curPath = queuePath.poll();
            if (curNode.left==null&&curNode.right==null&&curVal==targetSum){
                res.add(curPath);
            }

            if (curNode.left!=null){
                queueNodes.offer(curNode.left);
                queueVal.offer(curVal+curNode.left.val);
                LinkedList<Integer> newPath = new LinkedList<>(curPath);
                newPath.add(curNode.left.val);
                queuePath.offer(newPath);
            }

            if (curNode.right != null) {
                queueNodes.offer(curNode.right);
                List<Integer> newPath = new LinkedList<>(curPath);
                newPath.add(curNode.right.val);
                queuePath.offer(newPath);
                queueVal.offer(curVal + curNode.right.val);
            }
        }

        return res;
    }

    /**同理，我们只要任何时刻能拿到节点、节点对应的路径和、从根到节点的路径，且这三个信息是对应的，这就OK。
     因此还可以写出下面的dfs的迭代版本。
     1. 113题bfs的迭代版本，依然可以结合112题的bfs迭代版本理解。
     2. 与bfs的代码比起来，几乎是一摸一样的，唯一不同的就是使用栈还是队列 以及 左右孩子入队的顺序。由于
     下面的dfs是先序遍历的迭代，因此入栈的顺序是“右孩子————>再左孩子”
     * */
    public List<List<Integer>> pathSum_diedaidfs(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root==null) return res;
        Stack<TreeNode> stackNode = new Stack<>();
        Stack<Integer> stackVal = new Stack<>();
        Stack<List<Integer>> stackPath = new Stack<>();
        stackNode.push(root);
        stackVal.push(root.val);
        stackPath.push(new LinkedList<>(List.of(root.val)));
        while (!stackNode.isEmpty()){
            TreeNode curNode = stackNode.pop();
            Integer curVal = stackVal.pop();
            List<Integer> curPath = stackPath.pop();
            if (curNode.left==null&&curNode.right==null&&curVal==targetSum){
                res.add(new LinkedList<>(curPath));
            }

            if (curNode.right!=null){
                stackNode.push(curNode.right);
                stackVal.push(curVal+curNode.right.val);
                LinkedList<Integer> newPath = new LinkedList<>(curPath);
                newPath.add(curNode.right.val);
                stackPath.push(newPath);
            }

            if (curNode.left!=null){
                stackNode.push(curNode.left);
                stackVal.push(curVal+curNode.left.val);
                LinkedList<Integer> newPath = new LinkedList<>(curPath);
                newPath.add(curNode.left.val);
                stackPath.push(newPath);
            }
        }
        return res;
    }

    /*209
    给定一个含有 n 个正整数的数组和一个正整数 target 。
    找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返
    回其长度。如果不存在符合条件的子数组，返回 0 。
    * */
    /**
     *【题解思路】研究每一个数，放进窗口，更新窗口内的和sum；只要这个和不小于target，就更新res 并且 尝试缩小
     *      窗口的长度（即更新left的值）；研究完所有数之后返回res
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, cur = 0; //窗口的左、右边界
        int sum = 0; //窗口内所有数的和
        int res = Integer.MAX_VALUE; //窗口内数字和不小于target的最小窗口大小
        while (cur < nums.length) {
            /*step1：不管三七二十一，都先放进窗口*/
            sum += nums[cur];
            /*step2：满足条件时（只要窗口内的和大于等于target），不断的缩小窗口*/
            while (sum >= target) {
                res = Math.min(res, cur - left + 1); /**err：窗口必须是满足条件的时候才能更新窗口的大小，因此这个题必须是在while循环里面更新答案res*/
                sum -= nums[left++];
            }
            cur++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


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
            2. 用后序遍历计算每个节点的左右子树深度，并在过程中更新最大直径。使用全局变量记录最大直径。
       【注意】看上面124题的注释————题目要求的信息和每个节点返回给父节点的信息不一致，这种不一致导致：必须使用额外的方法来进行遍历。
     * */
    int maxDiameterOfBinaryTree = 0;
    public int diameterOfBinaryTree(TreeNode root) { /*原始方法：需要返回最大直径这个信息*/
        depth(root);
        return maxDiameterOfBinaryTree;
    }

    private int depth(TreeNode root) { /*新创建函数，返回以root为根节点树的高度*/
        if (root == null) return 0;
        /*step1：分别计算出这个节点的左子树、右子树的高度*/
        int left = depth(root.left);
        int right = depth(root.right);
        /*step2：求出这个节点作为根的子树的直径————该节点左子树、右子树的高度和*/
        maxDiameterOfBinaryTree = Math.max(maxDiameterOfBinaryTree, left + right);
        /*step3：返回以这个节点为根的子树的高度————该节点的左子树、右子树的最大高度 + 1*/
        return Math.max(left, right) + 1;
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
    /**
     *【注意】
     *      1. 题中的判断条件必须是“root.left==null&&root.right==null”，不能使用root==null！！！原因：前面
     *  的条件能确定当前的节点一定是叶子节点，但是后面的节点就不一定了。比如：
     *                      5
     *                    /
     *                   4
     *                  /  \
     *                null  6
     *           如果此时求解targetSum==9存不存在，实际上是不存在的，但是到节点4的左孩子时发现root==null并且
     *   targetSum==0，就会认为存在，这是错误的，因为null不是叶子节点 并且 它的父节点4也不是叶子节点。
     *          因此，必须保证是”叶子节点“的时候决定是不是更新答案
     *
     *      ⚠️综上所述：二叉树中“路径总和”相关的题目不能到了“root==null”才校验，校验的时机应该是“root是叶子节点的
     *   时候即root.left==null&&root.right==null”。很重要！！！
     *【补充说明】
     *      1. 这个题无非就是到叶子节点的时候判断从根节点到这个节点的路径和问题，因此到叶子结点的时候知道“路径和”这个数
     *   据就可以了。。。因此下面的DFS的迭代、BFS的层序遍历，虽然一个使用的是栈，一个使用的是队列，都是可以的。。根本原
     *   因：①在DFS的迭代中， 每一次从栈中分别弹出节点以及路径和，这个路径和就是从根节点到这个节点的路径和。形成闭环；
     *   ②在BFS的层序遍历中，每一次从队列中分别出队节点以及路径和，这个路径和就是从根节点到这个节点的路径和，形成闭环，因
     *   此也是OK的
     */
    /*写法1：DFS递归方法*/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null)
            return false;
        if (root.left==null&&root.right==null)
            return targetSum==root.val;
        return hasPathSum(root.left,targetSum-root.val)||
                hasPathSum(root.right,targetSum-root.val);
    }

    /*错误的写法，解释见上面的注释*/
//    public boolean hasPathSum(TreeNode root, int targetSum){
//        if (root==null) return false;
//        return dfs(root,targetSum);
//
//    }
//    boolean dfs(TreeNode root, int targetSum){
//        if (root==null&&targetSum==0) return true; /**【err】这里是错误的!!!!!!不能等到root来到null才进行判断*/
//        if(root==null) return false;
//        return dfs(root.left,targetSum-root.val)||
//                dfs(root.right,targetSum-root.val);
//    }

    /**写法2：DFS的迭代写法
     *      做法：用栈模拟递归，每个元素存 (node, 当前路径和)。
     * */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) return false;
        Stack<TreeNode> stackNode = new Stack<>();
        Stack<Integer> stackSum = new Stack<>();
        stackNode.push(root);
        stackSum.push(root.val);

        while (!stackNode.isEmpty()) {
            TreeNode node = stackNode.pop();
            int curSum = stackSum.pop();

            // 如果是叶子节点，判断路径和
            if (node.left == null && node.right == null) {
                if (curSum == targetSum) return true;
            }

            if (node.right != null) {
                stackNode.push(node.right);
                stackSum.push(curSum + node.right.val);
            }

            if (node.left != null) {
                stackNode.push(node.left);
                stackSum.push(curSum + node.left.val);
            }
        }
        return false;
    }

    /**写法3：非递归的写法，使用层序遍历
     *      做法：层序遍历，每个节点带上「从根到该节点的路径和」————言外之意需要多一个队列记录每个节点的时候记录它对应的信息。
     */
    public boolean hasPathSum_cengxu(TreeNode root, int targetSum) {
        if (root == null) return false;
        LinkedList<TreeNode> nodesQueue = new LinkedList<>();
        LinkedList<Integer> valQueue = new LinkedList<>();
        nodesQueue.offer(root);
        valQueue.offer(targetSum);
        while (!nodesQueue.isEmpty()) {
            /**
             *这种就是借助层序遍历来完成节点的处理，因此并不要求每一层的节点清晰区分，体现在代码中就是每一层
             * 的研究并不需要先获取“size”，及下面的两行代码可以省略————
             *        int size = nodesQueue.size();   //正规层序遍历时，需要获取size，知道这一层有多少个节点
             *        for (int i = 0; i < size; i++) {}
             */
            //①分别从队列中弹出节点 以及 它对应的路径和
            TreeNode curNode = nodesQueue.poll();
            Integer curVal = valQueue.poll();
            //②判断是不是答案————这个节点是叶子节点  &&  路径和是targetSum
            if (curNode.left == null && curNode.right == null && curVal == curNode.val) {
                return true;
            }
            //③依次将 左右孩子 以及 对应的路径和 添加到队列
            if (curNode.left != null) {
                nodesQueue.offer(curNode.left);
                valQueue.offer(curVal - curNode.val);
            }
            if (curNode.right != null) {
                nodesQueue.offer(curNode.right);
                valQueue.offer(curVal - curNode.val);
            }

        }
        return false;
    }


    /**层序遍历的另外的写法*/
    public boolean hasPathSum_cengxu(leecode_Debug.BTree.TreeNode root, int targetSum) {
        if (root==null) return false;
        LinkedList<leecode_Debug.BTree.TreeNode> queueNodes = new LinkedList<>();
        LinkedList<Integer> queueVal = new LinkedList<>();
        queueNodes.offer(root);
        queueVal.offer(root.val); //与写法3的区别....一个是存储差值，一个存储和
        while (!queueNodes.isEmpty()){
            leecode_Debug.BTree.TreeNode curNode = queueNodes.poll();
            Integer curVal = queueVal.poll();

            if (curNode.left==null&&curNode.right==null&&curVal==targetSum){ //与写法3的区别
                return true;
            }
            /**因为这里会保证不是null的时候才添加，因此上面的判断可以直接判断，不用预先判断“curNode是空的情况”，因
             * 为curNode就不可能是null。。。写法3也是同样的道理*/
            if (curNode.left!=null){
                queueNodes.offer(curNode.left);
                queueVal.offer(curVal+curNode.left.val);
            }

            if (curNode.right!=null){
                queueNodes.offer(curNode.right);
                queueVal.offer(curVal+curNode.right.val);
            }
        }
        return false;
    }


    /*437. 路径总和 III。与560题是类似的
    给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
    路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
    * */
    /**
     * 【思路】用一个map存放前缀和，此时前缀和的含义：从根节点到这个节点的路径和。。。每一次存放之前先看map中有没有
     *      符合条件的前缀和，即map.get(curSum-targetSum)的值
     *【关于思路的解析 以及 多个疑问，参看（灵茶山艾府）：
     *      https://leetcode.cn/problems/path-sum-iii/solutions/2784856/zuo-fa-he-560-ti-shi-yi-yang-de-pythonja-fmzo/
     *】
     * */
    /*写法1：有返回值的写法*/
    public int pathSum_437(TreeNode root, int targetSum) {
        HashMap<Long, Integer> perfixMap = new HashMap<>();
        perfixMap.put(0L,1);
        return dfs(root,0,perfixMap,targetSum);
    }

    private int dfs(TreeNode root, long curSum, HashMap<Long, Integer> perfixMap, int targetSum) {
        if (root == null) return 0;

        int res = 0;
        curSum += root.val;
        res += perfixMap.getOrDefault(curSum - targetSum, 0);
        perfixMap.put(curSum, perfixMap.getOrDefault(curSum, 0) + 1);
        res += dfs(root.left, curSum, perfixMap, targetSum);
        res += dfs(root.right, curSum, perfixMap, targetSum);
        perfixMap.put(curSum, perfixMap.get(curSum) - 1);
        return res;
    }

    /*写法2：无返回值的写法*/
    /**
     *【技巧】更新map中key的值，可以使用merge操作，见下面注释的代码————cnt.merge(i,-1,Integer::sum);
     */
    private int ans;
    public int pathSum_437_1(TreeNode root, int targetSum) {
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        dfs(root, 0, targetSum, cnt);
        return ans;
    }

    private void dfs(TreeNode root, long i, int targetSum, Map<Long, Integer> cnt) {
        if (root==null) return;
        i += root.val;
        ans += cnt.getOrDefault(i-targetSum,0);
        cnt.put(i,cnt.getOrDefault(i,0)+1);
//        cnt.merge(i,1,Integer::sum); /*这种写法跟上一行是同样的效果*/
        dfs(root.left,i,targetSum,cnt);
        dfs(root.right,i,targetSum,cnt);
//        cnt.merge(i,-1,Integer::sum);
        cnt.put(i,cnt.getOrDefault(i,0)-1); /*这种写法跟下一行是同样的效果*/
    }
}