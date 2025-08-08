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
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDiameter;
    }

    private int dfs(TreeNode root) {
        if (root==null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        maxDiameter = Math.max(left+right+1,maxDiameter);
        return 1 + Math.max(left,right)+1;
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
//    public TreeNode sortedArrayToBST(int[] nums) {
//
//    }


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
            }else{ //否则的话将nowNode拼接到cur节点后面
                cur.right = nowNode;
                cur.left = null;
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
        if (l>r) return null; /**这里只限制l<r行不行？这个条件能不能包含preOrderIndex不越界的情况*/
        int curVal = preorder[preOrderIndex++];
        TreeNode root = new TreeNode(curVal);
        Integer inorderIndex = inorderMap.get(curVal);
        root.left = buildTree(preorder,inorder,l,inorderIndex-1);
        root.right = buildTree(preorder,inorder,inorderIndex+1,r);
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


    /*124
    * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
    路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
//    public int maxPathSum(TreeNode root) {
//
//    }
}
