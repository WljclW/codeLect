package leecode_Debug._hot100;

import leecode_Debug.BTree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class _07binarytree {
    /*94中序遍历*/
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode cur = stack.pop();
                res.add(cur.val);
                root = cur.right;
            }
        }
        return res;
    }



    /*104
    * 给定一个二叉树 root ，返回其最大深度。
    二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。*/
    /**【】理解二叉树的深度优先遍历 和 广度优先遍历
     * 深度优先遍历————递归
     * 广度优先遍历————原型就是二叉树的层次遍历*/
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /*迭代法：层序遍历的迭代*/
    public int maxDepth_diedai(TreeNode root) {
        if (root==null) return 0;
        int depth = 0;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root); /**当作队列使用，因此使用offer方法*/
        while (!deque.isEmpty()){
            depth++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                if (cur.left!=null){
                    deque.offer(cur.left);
                }
                if (cur.right!=null){
                    deque.offer(cur.right);
                }
            }
        }
        return depth;
    }


    /*
    * 226.给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * */
    public TreeNode invertTree(TreeNode root) {
        /*step1：basecase*/
        if (root==null) return root;
        if (root.left==null&&root.right==null) return root; /*没有这一句也OK*/
        /*step2：完成左右孩子的操作*/
        /**
         * err：这里必须使用临时变量记录一下，不能这么写：
         *      root.left = invertTree(root.right);
         *      root.right = invertTree(root.left);
         *    错误原因：在第一行代码之后，改变了原始的root.left！！
         */
        TreeNode L = invertTree(root.right);
        TreeNode R = invertTree(root.left);
        /*step3：当前节点的操作。左孩子作为右孩子、右孩子作为左孩子*/
        root.left = L;
        root.right = R;
        return root;
    }


    /*101.给你一个二叉树的根节点 root ， 检查它是否轴对称。*/
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        if(root.left==null&&root.right==null) return true;
        if (root.left==null || root.right==null) return false;
        return isSymmetric(root.left,root.right);
    }

    public boolean isSymmetric(TreeNode l,TreeNode r){
        if (l==null&&r==null) return true;
        if (l==null||r==null) return false;
        return l.val==r.val&&isSymmetric(l.right,r.left)&&isSymmetric(l.left,r.right);
    }

    /*543.
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
    /**
     *124与543有类似的道理
     *    543中每个节点返回给父节点的信息是以它为根的子树高度，但是每个节点的决策信息是左右子树高度的最大
     *值+1。因此需要使用额外的函数来改变返回值信息，同时在这个过程中更新结果。
     *    ①我们期望节点的返回信息：每个节点的返回信息是它最大的贡献值，左右子树只能选一；
     *    ②每一个节点的决策信息：每个结点的决策值，是左边的贡献，右边的贡献，再加节点的值
     *    这两者之间的差异，再加上原方法要求返回整个树的最大路径，也不是节点的最大贡献。因此必须使用两个方
     *法，新建一个方法返回值是节点的最大贡献，并在决策中更新结果——即要求的直径
     */
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDiameter;
    }

    private int dfs(TreeNode root) {
        if (root==null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        maxDiameter = Math.max(left+right,maxDiameter);
        return 1 + Math.max(left,right);
    }


    /*102.层序遍历*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        if (root==null) return res;
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            LinkedList<Integer> ele = new LinkedList<>();
            for (int i=0;i<size;i++){
                TreeNode poll = deque.poll();
                ele.add(poll.val);
                if (poll.left!=null) deque.offer(poll.left);
                if (poll.right!=null) deque.offer(poll.right);
            }
            res.add(new LinkedList<Integer>(ele));
        }
        return res;
    }


    /*108.升序数组转换为平衡二叉搜索树*/
    /**
     *【总结】所有根据“某些数据”构造二叉树的题都是一样的套路————
     *      step1：每一步进行时选出一个数创建节点作为当前的根节点；
     *      step2：判断左右子树的节点值都是哪些————即确定索引范围；
     *      step3：确定出左右子树节点在数组中的范围后，递归调用构造出左右子树；
     *      step4：将递归的返回值拼接到step2中的根节点；
     *      step5：返回step2中的根节点
     *   综上，这样的递归方法通常含有三个参数：①是数组；②、③是指明使用哪些数构造当前这
     *      棵树————即数据在数组中的左右区间（通常是左闭右闭区间）。
     *【同类型的题目】根据中序和先序、中序和后序还原出二叉树。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums,0,nums.length-1);
    }

    private TreeNode build(int[] nums, int l, int r) {
        if (l>r) return null; /**err："l>r"不要写错了，如果写错了发现最后的返回值永远是空，啥也没有*/
        int mid = l+(r-l)/2;
        TreeNode root = new TreeNode(mid);
        root.left = build(nums,l,mid-1);
        root.right = build(nums,mid+1,r);
        return root;
    }


    /*98.
    * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。*/
    /*方法1：递归的方式来完成*/
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }

    public boolean isValidBST(TreeNode root,Integer min,Integer max) { /*min、max分别标识当前的root节点值最小值、最大值分别是多少*/
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    /*方法2：中序迭代来看是不是升序*/
    public boolean isValidBST_inorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Integer preVal = null;
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root  =root.left;
            }else {
                TreeNode cur = stack.pop();
                if (preVal==null) preVal= cur.val;
                else {
                    if (cur.val<=preVal) return false;
                    preVal = cur.val;
                }
                root = cur.right;
            }
        }
        return true;
    }



    /*230.
    * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。*/
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode cur = stack.pop();
                if (--k==0) return cur.val;
                root = cur.right;
            }
        }
        return -1;
    }



    /*199.
    给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    * */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        if (root==null) return res;
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i=0;i<size;i++){
                TreeNode cur = deque.poll();
                if (cur.left!=null) deque.offer(cur.left);
                if (cur.right!=null) deque.offer(cur.right);
                if (i==size-1) res.add(cur.val); //层序遍历的基础上只把每一层最后一个元素添加进res
            }
        }
        return res;
    }


    /*114.
    * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。*/
    public void flatten(TreeNode root) {
        if (root==null) return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode cur = null;
        while (!stack.isEmpty()){
            TreeNode nowNode = stack.pop();
            if (cur==null){ //cur是null，说明nowNode是先序遍历的第一个节点
                cur = nowNode;
            }else{ //否则的话将nowNode拼接到cur节点右边
                cur.right = nowNode;
                cur.left = null; /**由于是先序遍历，因此到任何一个孩子的时候，左孩子节点一定已经研究过了，因此这一步不会导致错过节点*/
                cur = cur.right;
            }
            if (nowNode.right!=null){
                stack.push(nowNode.right);
            }
            if (nowNode.left!=null){
                stack.push(nowNode.left);
            }
        }
    }


    /*105.
    * 从前序 和 中序 构造出二叉树*/
    HashMap<Integer,Integer> inorderMap = new HashMap<>();
    int preOrderIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
        if (l>r) return null; /**这里只限制l<r就可以，这个条件就包含了preOrderIndex不越界的情况*/
        int curVal = preorder[preOrderIndex++];
        TreeNode root = new TreeNode(curVal);
        Integer inorderIndex = inorderMap.get(curVal);
        root.left = buildTree(preorder,inorder,l,inorderIndex-1); /*左边范围到inorderIndex-1位置*/
        root.right = buildTree(preorder,inorder,inorderIndex+1,r); /*右边是从inorderIndex+1开始*/
        return root;
    }

    /*437.
    * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
    路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。*/
//    public int pathSum(TreeNode root, int targetSum) {
//
//    }


    /*236.二叉树最近公共祖先
    * */
    /**
     * 解析建议看K神：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/solutions/240096/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //1. 终止条件：为null，或者等于p，或者等于q
        if (root == null) return null;
        if (root == p || root == q) return root;
        //2. 假设拿到了左右子树的信息
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        //3. 根据拿到的信息决策出当前节点的返回值————形成闭环！！
        if (leftNode != null && rightNode != null) return root;
        return (leftNode == null) ? rightNode : leftNode;
    }

    /*下面的写法也是正确的*/
    public TreeNode lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        if (root==q||root==p) return root;
        TreeNode l = lowestCommonAncestor_(root.left, p, q);
        TreeNode r = lowestCommonAncestor_(root.right, p, q);
        if (l==null&&r==null) return null;
        if (l==null||r==null) return l==null?r:l;
        return root;
    }


    /*124
    * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
    路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    /**
     *【关键要理解】为什么需要两个方法？
     *      原因就在于决策的表达式（即代表方法maxPathSum最终返回值的计算），与每个结点应该返回给父节点的信息
     * 是不一样的。不一样在哪里？
     *      到每一个节点，我们期望计算出以这个节点为根的子树的最大路径和是多少；但是对于每一个节点我们希望它
     * 返回什么？希望它返回朝着右子树下去的最大路径和 以及 朝着左子树路径下去的最大路径和 中的最大值。————很
     * 明显，这两个信息是不一样的，这种差别就注定了必须使用额外的方法。如果写在原函数的话，就无法协调了到底返
     * 回什么信息.......
     */
    int resMaxPathSum = Integer.MIN_VALUE; /**err：注意初始化为0是不行的，因为可能所有的节点值都小于0，因此最大路径必小于0*/
    public int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        if (root.left==null&&root.right==null) return root.val;
        dfsMaxPathSum(root);
        return resMaxPathSum;
    }

    private int dfsMaxPathSum(TreeNode root) {
        if (root==null) return 0;
        int lSum = Math.max(dfsMaxPathSum(root.left),0); /**err：在这里就必须将返回值和0取较大值*/
        int rSum = Math.max(dfsMaxPathSum(root.right),0);
        int curSum = lSum+rSum+root.val;
        resMaxPathSum = Math.max(curSum,resMaxPathSum);
        return Math.max(lSum,rSum)+root.val;
    }
}
