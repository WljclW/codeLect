package leecode_Debug._hot100;

import leecode_Debug.BTree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**DFS三种遍历的核心对比
 🌲 一、DFS 三种形式的核心区别（直观对比）
         遍历方式	访问顺序	关键点
         前序遍历 (preorder)	先访问当前节点 → 再访问左右子树	当前节点在子树之前，用于“前向传播”信息
         中序遍历 (inorder)	左子树 → 当前节点 → 右子树	主要用于「二叉搜索树」的有序性问题
         后序遍历 (postorder)	先访问左右子树 → 最后访问当前节点	当前节点依赖子树结果，用于“自底向上”汇总信息
 */
/**1. 中序遍历
 （1） 👉 中序遍历（inorder traversal）在普通树题中确实很少单独使用（对「普通二叉树」题来说，中序遍历几乎用不到；）
 但在「特定类型的树」——尤其是 二叉搜索树（BST） 里，它的作用极其关键。
 （2） 常见的题目比如：
         题号	题目	为什么用中序遍历
         94	二叉树的中序遍历	最基本题，输出访问顺序
         98	验证二叉搜索树	中序遍历结果应严格递增
         99	恢复二叉搜索树	找到中序序列中两个交换的节点
         230	BST 第 K 小元素	中序遍历第 k 次访问的节点即答案
         501	BST 众数	中序遍历可顺序统计频率
         530	BST 最小差值	相邻节点差值最小，中序遍历自然相邻
         700 / 701 / 450	BST 查找 / 插入 / 删除节点	用中序结构辅助定位区间
 */
/**2. 先序遍历
 （1）使用场景：当当前节点的决策依赖于「从上往下传递的信息」时，比如路径、深度、状态等，需要“带着信息下去”的题。
 （2）特征总结：当前节点独立决策，不依赖子树返回值 或者 需要把父节点的信息传下去。。典型的代码结构如下
         void dfs(TreeNode root, int pathSum) {
             if (root == null) return;
             // 先处理当前节点
             pathSum += root.val;
             // 再往下递归
             dfs(root.left, pathSum);
             dfs(root.right, pathSum);
         }
 （3）必须使用先序遍历的题目比如：
         题号	题目	原因
         257	二叉树的所有路径	当前路径信息从上到下延伸，遍历时即记录路径
         112	路径总和（是否存在）	每访问一个节点，就更新剩余目标和
         113	路径总和 II	同上，只是要保存路径
         129	求根到叶子节点数字之和	“从根到叶”形成数字，信息从上往下传递
         100/101	相同树、对称树	比较结构，从根开始递归
 */
/**3. 后序遍历
 （1）使用场景：当当前节点的计算依赖于左右子树的结果时，必须等左右子树都算完，再算当前节点。
 （2）特征总结：当前节点依赖子树结果；“自底向上”的信息汇总；通常在「返回值」中带有子树信息；。。。典型的代码结构如下
         int dfs(TreeNode root) {
             if (root == null) return 0;
             int left = dfs(root.left);
             int right = dfs(root.right);
             return Math.max(left, right) + 1; // 依赖左右
         }
 （3）必须使用后序遍历的题目比如：
     题号	题目	原因
     104	二叉树的最大深度	当前节点深度 = max(左,右)+1
     110	平衡二叉树	依赖左右子树高度差
     543	二叉树直径	当前节点的直径 = 左高+右高
     124	二叉树最大路径和	当前节点最大路径依赖左右子树最大贡献
     337	打家劫舍 III	当前节点能偷的最大值取决于子节点偷/不偷
     226	翻转二叉树	先翻转左右，再翻转根（后序自然）
 */
/**4. 什么时候”先序遍历“和”后序遍历“都可以使用
 （1）使用场景：当题目是“路径搜索”类（回溯、组合、排列、子集），你可以在进入子节点时做操作，也可以在退出子节点时做操作。
 两种方式都可以，只是处理逻辑稍不同。
 （2）典型的题目比如：
 题号	题目	特点
 46	全排列	前序添加元素、后序回溯都可
 78	子集	前序/后序都能在不同位置收集结果
 39/40	组合总和	前序加元素，后序删除元素（回溯模板）
 17	电话号码的字母组合	同上，回溯树结构
 */
/**5. 二叉树4种遍历顺序的使用场景 以及 可替换性 分类
     题目特征	推荐遍历	可替代遍历	说明
     ✅ 路径型（路径和 / 所有路径 / 路径回溯）	前序	（部分 BFS）	需要边走边维护路径
     ✅ 统计型（最大/最小深度、每层统计）	层序 / 后序	二者互通	一层层统计 or 自底向上
     ✅ 构建型（翻转、合并、连接）	前序 / 层序	二者互通	从上到下传递状态
     ✅ DP型（树上最值 / 打家劫舍）	后序	无	必须先计算子树结果
     ✅ BST型（有序特性）	中序	无	依赖中序有序性
     ✅ 搜索型（剪枝、匹配）	前序	（部分 BFS）	需控制递归或剪枝时机
 【口诀】
     前序：传递状态（路径/构建）
     后序：汇总计算（DP/深度）
     中序：顺序输出（BST）
     层序：分层观察（统计/构建）
 */
/**6. 关于二叉树几个典型题目的说明
 🧩 举几个代表性对比
         题目	        可用遍历	            说明
         104. 最大深度	后序 ✅ / 层序 ✅	后序自底向上，层序数层数
         111. 最小深度	层序 ✅ / 后序 ✅	层序最快找到叶子，后序递归计算
         112. 路径总和	前序 ✅ / 层序 ✅	前序传递和，层序带状态遍历
         113. 路径总和 II	前序 ✅	        路径需回溯，层序不方便
         124. 最大路径和	后序 ✅	            必须自底向上汇总子树最大贡献
         226. 翻转二叉树	前序 ✅ / 层序 ✅	同时交换子树，层序迭代也行
         98. 验证 BST	中序 ✅	            需要中序单调性判断
         543. 直径	    后序 ✅	            需要汇总左右子树深度
 */
public class _07binarytree {
    /*94中序遍历*/
    /*非递归形式的写法*/
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

    /*递归形式的写法*/
    List<Integer> resInorder;
    public List<Integer> inorderTraversal_(leecode_Debug.top100.TreeNode root) {
        resInorder = new LinkedList<>();
        if (root==null) return resInorder;
        dfs1(root);
        return resInorder;
    }

    private void dfs1(leecode_Debug.top100.TreeNode root) {
        dfs1(root.left);
        resInorder.add(root.val);
        dfs1(root.right);
    }



    /*104
    * 给定一个二叉树 root ，返回其最大深度。
    二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。*/
    /**【】理解二叉树的深度优先遍历 和 广度优先遍历
     * 深度优先遍历————递归
     * 广度优先遍历————原型就是二叉树的层次遍历*/
    /*递归法：本质上是后序遍历的思想。
        即 先处理左子树、右子树，最后在访问当前节点的节点计算信息
        可以参考“https://leetcode.cn/problems/maximum-depth-of-binary-tree/solutions/2361697/104-er-cha-shu-de-zui-da-shen-du-hou-xu-txzrx/”
     看这里递归的理解*/
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
            depth++; /**关键：在新的一层的时候更新结果变量depth*/
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                /**这里就不用判断”cur是不是null“！！因为第一次加入的时候确
                 保了cur不是null，并且后续加入的时候都确保不是null，因此弹出来的节点必然不是null*/
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


    /* 226. 翻转二叉树
    * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * */
    /**
     *【建议】层序遍历的思路采用写法 invertTree_no_size；递归的思路写法 invertTree
     */
    /*解法1：递归的写法*/
    public TreeNode invertTree(TreeNode root) {
        /*step1：base case*/
        if (root==null) return root;
        if (root.left==null&&root.right==null) return root; /*没有这一句也OK*/
        /*step2：左右子树分别研究。完成左右孩子的翻转*/
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

    /*解法2：层序遍历的写法*/
    /**
     *【说明】这个题的目的仅仅是翻转二叉树，因此孩子的遍历顺序没有关系。。。体现在代码中就是：
     *      ①~③的顺序不重要；只要保证②和③相邻即可。比如：①②③、①③②、②③①、③②①这样的顺序都是可以的
     * @param root
     * @return
     */
    public TreeNode invertTree_cengxu(TreeNode root) {
        if (root==null) return root;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                swap(cur); //①
                if (cur.left!=null) queue.offer(cur.left);//②
                if (cur.right!=null)queue.offer(cur.right);//③
            }
        }
        return root;
    }

    private void swap(TreeNode cur) {
        TreeNode tmp = cur.left;
        cur.left =  cur.right;
        cur.right = tmp;
    }

    /*写法3:同时这个题并不需要严格的区分不同层的节点，只需要挨个研究不是null的节点，因此不需要每一层之前先记录queue的size*/
    public TreeNode invertTree_no_size(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        if (root==null) return root;
        deque.offer(root);
        while (!deque.isEmpty()){
            TreeNode cur = deque.poll();
            swap(cur);
            if (cur.left!=null) deque.offer(cur.left);
            if (cur.right!=null) deque.offer(cur.right);
        }
        return root;
    }


    /*101. 对称二叉树
    给你一个二叉树的根节点 root ， 检查它是否轴对称。*/
    /*解法1：递归法。*/
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true; //因为递归的方法需要使用到“root.”，因此必须先判空
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode l, TreeNode r) {
        /*
        base case：（1）如果两个都是空，返回true；
                   （2）如果有且仅有一个是空，返回false
        说明两个都不是空，则①判断节点的值是否相等；②左边的右孩子 和 右边的左孩子 是不是对称；③左边的左孩子 和 右边的
                    右孩子是否对称。三者同时满足才返回true。
         */
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        return l.val == r.val &&
                isSymmetric(l.right, r.left) &&
                isSymmetric(l.left, r.right);
    }


    /*解法2：迭代法
    *【说明】
    *       1. 由于入栈的永远都是TreeNode，而不涉及到值。因此使用一个队列就可以，保证每一次入栈的时候成对就可以（这两
    * 个成对的要求是对称的两个节点）。出栈的时候也是每一次同时弹出两个
    *       2. 碰到null的时候需要正常入栈。【注】PriorityQueue.offer的参数不能是null，是null的话会报空指针异常，但是
    * LinkedList、Queue的offer方法是可以是null，因此碰到null直接加进去没问题。
    * */
    public boolean isSymmetric_diedai(TreeNode root) {
        if (root==null) return true;
        return isSymmetric1(root,root);
    }

    private boolean isSymmetric1(TreeNode root, TreeNode root1) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root1);
        while (!queue.isEmpty()){
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            /*step1：判断是不是符合条件*/
            if (node1==null&&node2==null)
                continue; /**err：都是null，说明当前步骤是对称的，没问题，需要继续判断。。不能直接返回true！！因为其他的节点还没判断，结束判断的标准就是确定不对称直接返回false，或者队列为空返回true*/
            if ((node1==null||node2==null)||node1.val!=node2.val)
                return false; /*“两个节点有且仅有一个是null”或者“两个节点的value不一样”，就需要直接返回false。说明压根不对称*/
            /*step2：分别将node1、node2节点对称的孩子节点相继入队列*/
            queue.offer(node1.left);
            queue.offer(node2.right);

            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }

    /*迭代法 的不同的写法*/
    /**非递归的写法采用层次遍历的写法。。。。把握关键点：每次入队列、出队列是 左右两边的对称节点同时进行的*/
    public boolean isSymmetric(leecode_Debug.top100.TreeNode root) {
        if (root==null) return true;
        LinkedList<leecode_Debug.top100.TreeNode> left = new LinkedList<>();
        LinkedList<leecode_Debug.top100.TreeNode> right = new LinkedList<>();
        left.push(root.left);
        right.push(root.right);
        while (!left.isEmpty()){
            leecode_Debug.top100.TreeNode leftCur = left.pop();
            leecode_Debug.top100.TreeNode rightCur = right.pop();
            if (leftCur==null&&rightCur==null) continue;
            if (leftCur==null||rightCur==null) return false;
            if (leftCur.val!=rightCur.val) return false;

            left.push(leftCur.left);
            right.push(rightCur.right);
            left.push(leftCur.right);
            right.push(rightCur.left);
        }
        return true;
    }


    /*543. 二叉树的直径
    * 给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。*/
    /**
     * 体会下面的两个题都必须额外的使用一个方法来深度优先遍历二叉树的原因。
     */
    /**
     *124与543有类似的道理
     *    124中每个节点返回给父节点的信息是以它为根的子树高度，但是每个节点的决策信息是左右子树高度的最大
     *值+1（1代表自身）。因此需要使用额外的函数来改变返回值信息，同时在这个过程中更新结果。
     *    ①我们期望节点的返回信息：每个节点的返回信息是它最大的贡献值，左右子树只能选一；
     *    ②每一个节点的决策信息：每个结点的决策值，是左边的贡献，右边的贡献，再加节点
     *    这两者之间的差异，再加上原方法要求返回整个树的最大路径，也不是节点的最大贡献。因此必须使用两个方
     *法，新建一个方法返回值是节点的最大贡献，并在决策中更新结果——即要求的直径
     */
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDiameter;
    }

    /**
    1.     其实这个题的辅助函数就是计算以root为根的树的高度
     同时
           在这个过程中更新答案
    2. 从下面的方法注释中，可以明显的看出来：到每一个节点时，该节点需要计算的决策信息 和 该节点需要给上层节点的返回值信息，这两
     者之间是有差异的
     */
    private int dfs(TreeNode root) {
        if (root==null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        /**
         【注】路径的长度就是"左右高度之和"，不能再加1了！！
                比如，左右子树的高度都是1，则路径长度就是2，如下图，6节点有两个孩子，路径长度就
            是2————即左右子树的高度和：
                                6
                               / \
                              4  3
         */
        maxDiameter = Math.max(left+right,maxDiameter); /*来到每一个节点时决策信息：到每一个节点后，对于题目所求信息的决策*/
        return 1 + Math.max(left,right); /*每一个节点返回值信息：每一个节点返回给父节点的信息*/
    }


    /*102.层序遍历*/
    /**
            【注意】层序遍历必须区分每一行，因此在while循环内，必须先使用“deque.size()”获得出当
     前这一层的节点数；但是对于二叉树的题目中，有的就不需要要个区分每一层节点，因此就可以省略“while
     循环内使用size()获取节点数的操作，以及for循环的操作。而是poll()然后继续操作就行”，此时的代码大
     致如下：
               while (!deque.isEmpty()){
                    TreeNode cur = deque.poll(); //①弹出节点
                    //……………………………………处于cur节点的处理
                    //②研究左右孩子。有的题目需要判断null,有的题目不需要判断null
                }
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        if (root==null) return res;
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size(); /**每一轮循环之前需要记录一下size，这样才知道这一层有多少节点。不然弹出节点总会添加左右孩子，一直不会是空*/
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


    /*108. 将有序数组转换为二叉搜索树*/
    /**
     *【总结】所有根据“某些数据”构造二叉树的题都是一样的套路————
     *      step1：每一步进行时选出一个数创建节点作为当前的根节点；
     *      step2：判断左右子树的节点值都是哪些————即确定索引范围；
     *      step3：确定出左右子树节点在数组中的范围后，递归调用此方法构造出左右子树；
     *      step4：将递归的返回值拼接到step2中的根节点；
     *      step5：返回step2中的根节点
     *   综上，这样的递归方法通常含有三个参数：①是数组；②、③是指明使用哪些数构造当前这
     *      棵树————即数据在数组中的左右区间（通常是左闭右闭区间）。
     *【同类型的题目】根据中序和先序、中序和后序还原出二叉树。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums,0,nums.length-1);
    }

    /*调用的方法的含义：使用nums数组[l,r]这区间内的所有数，构建出二叉搜索树，并返回根节点*/
    private TreeNode build(int[] nums, int l, int r) {
        if (l>r) return null; /**err："l>r"不要写错了，如果写错了发现最后的返回值永远是空，啥也没有*/
//        if (l==r) return new TreeNode(nums[l]);  /*没有这一句也是对的*/
        int mid = l+(r-l)/2; /**err：错误的写法“mid = l+(r-l)>>1”，原因：>>运算符优先级低于+*/
        /**err：注意是用"中间位置的值"来构建根节点，不要写成"new TreeNode(mid)"，如果写错了会发现测试用例
         全是错的，其中第一个测试用例报错如下————
                     输入
                        nums = [-10,-3,0,5,9]
                     输出
                        [2,0,3,null,1,null,4]
                     预期结果
                        [0,-3,9,-10,null,5]
         */
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums,l,mid-1);
        root.right = build(nums,mid+1,r);
        return root;
    }


    /*98. 验证二叉搜索树
    * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。*/
    /**
     【建议的写法】递归的写法采用官方给出的 isValidBST；迭代的写法参考 isValidBST_Integer（注意：迭代
        的写法中建议将 prev的初始值设置为null。否则就要初始化为 Long.MIN_VALUE）。
            迭代的写法 就是中序遍历的迭代写法，在访问节点的时候修改标记变量 并 判断是不是严格升序。
     */
    /*方法1：递归的方式来完成*/
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }

    public boolean isValidBST(TreeNode root,Integer min,Integer max) { /*min、max分别标识当前的root节点值最小值、最大值分别是多少*/
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isValidBST(root.left, min, root.val /*root.left 代表去左子树，左子树的节点值必须小于等于根 root.val*/) &&
                isValidBST(root.right, root.val, max); /*同理：root.right 代表去右子树，右子树的节点值不能小于等于根 root.val*/
    }

    /*递归方法，官方给出的写法*/
    public boolean isValidBST_offical(TreeNode root) {
        return isValidBST_offical(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST_offical(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST_offical(node.left, lower, node.val) && isValidBST_offical(node.right, node.val, upper);
    }

    /*方法2：中序迭代来看是不是升序。
        写法1：声明为引用类型，初始化为 null；见方法 isValidBST_Integer
        写法2：声明为long，初始化为 Long.MIN_VALUE；见方法 isValidBST_long
    * */
    /**
     【注】
        1. 这个题使用 preVal 记录前面一个节点的值，用于判断是不是严格升序。但是这个值不能初始化为"Integer.MIN_VALUE",
     会有测试用例报错，如下：
                             输入
                                root = [-2147483648,-2147483648]
                             输出
                                 true
                             预期结果
                                 false
        正确：要麽 preVal声明为Integer 初始化为 null；要麽 preVal声明为long，初始化为 Long.MIN_VALUE
     */
    public boolean isValidBST_Integer(TreeNode root) {
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

    /*不建议的写法*/
    public boolean isValidBST_long(leecode_Debug.top100.TreeNode root) {
        Stack<leecode_Debug.top100.TreeNode> stack = new Stack<>();
        if (root==null) return true;
        long prev = Long.MIN_VALUE; /**如果不初始化未null，这里就不能初始化为“Integer.MIN_VALUE”。。。。因此建议使用写法 isValidBST_Integer*/
        while (!stack.isEmpty()||root!=null){
            if (root!=null){
                stack.push(root);
                root = root.left;
            }else {
                leecode_Debug.top100.TreeNode cur = stack.pop();
                if (prev==Long.MIN_VALUE) prev = cur.val;
                else {
                    if (prev>=cur.val) return false;
                    prev  =cur.val;
                }
                root = cur.right;
            }
        }
        return true;
    }



    /*230. 二叉搜索树中第 K 小的元素
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

    /*写法2：递归的写法*/
    int res, k;
    void dfs3(TreeNode root) {
        if (root == null) return;
        /**1. 访问左子树*/
        dfs3(root.left);
        /**2. 访问当前节点。。。本质是使用中序遍历二叉树，过程中记录结果*/
        if (k == 0) return;
        if (--k == 0) res = root.val;
        /**3. 访问右子树*/
        dfs3(root.right);
    }
    public int kthSmallest_digui(TreeNode root, int k) {
        this.k = k;
        dfs3(root);
        return res;
    }



    /*199. 二叉树的右视图
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


    /*114. 二叉树展开为链表
    * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。*/
    /**TODO：思考递归的写法*/
    public void flatten(TreeNode root) {
        if (root==null) return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode cur = null; /**cur代表当前已经拼接得到的那部分链表 的最后一个节点，下一个节点就拼接到 cur.right*/
        while (!stack.isEmpty()){
            TreeNode nowNode = stack.pop();
            if (cur==null){ //cur是null，说明nowNode是先序遍历的第一个节点
                cur = nowNode;
            }else{ //否则的话将nowNode拼接到cur节点右边
                cur.right = nowNode;
                cur.left = null; /**由于是先序遍历，因此到任何一个孩子的时候，左孩子节点一定已经研究过了，因此这一步不会导致错过节点*/
                cur = cur.right; /*这里的cur.right其实就是nowNode，因此这一句等同于写成“cur=nowNode。”*/
            }
            /**上面的if-else逻辑可以简化为下面的形式。原因在于if-else有公用的一句“cur = nowNode;”，并且
             if语句块只有这一句话。因此可以省略if块，原来的else块写为if语句块，把这个赋值的逻辑写在if块之
             外。*/
//            if (curNode!=null){
//                curNode.right = cur;
//                curNode.left = null;
//            }
//            curNode = cur;
            if (nowNode.right!=null) stack.push(nowNode.right);
            if (nowNode.left!=null) stack.push(nowNode.left);
        }
    }


    /*105. 从前序与中序遍历序列构造二叉树
    * 从前序 和 中序 构造出二叉树*/
    /**
     * 对于任何一颗树，前序的第一个节点一定是树的根；中序遍历中这个根节点的左边就是左子树，右边就是右子树
     *【注意】“中序-先序构造二叉树”和“中序-后序构造二叉树”的题目中，递归的时候必须有终止条件————"if (left>right) return null;"
     *【思路】
     *      1. 从先序遍历中拿到第一个节点，是当前树的根节点————构造出TreeNode root；
     *      2. 在中序遍历中找到该节点所在的位置————利用左边的节点构造root.left、利用右边的节点构造出
     *          root.right
     *      3. 返回root
     *【补充说明】
     *      1. 为了从中序遍历快速得到某个数据所在的位置，通常使用map来优化
     *      2. “先序-后序”来构建的时候有区别。
     *          2.1 因为后序遍历的顺序是“左-右-根”，此时要倒序拿，因为根在最后；然后接着拿到节点其实是根节点的右
     *      子树；中序遍历的顺序是“左-根-右”，因此先使用右半部分数据构建右子树，再使用左半部分构建左子树。顺序不
     *      能错！！
     *          2.2 而先序遍历的顺序是“根-左-右”，此时要顺序拿，因为根在前面；然后接着拿的时候其实是拿到了根节
     *      点的左子树；中序遍历的顺序就是“左=根-右”，因此先使用左半部分构建左子树，再使用右半部分构建右子树。
     *【复杂度分析——比如数组中有n个数据】
     *      时间复杂度：因为每一个节点都会进行创建，因此递归的调用深度（递归次数）是n；每一次递归是都是先创建二叉树节点，然后在中序
     *  遍历中查找它的位置，由于使用HashMap因此每一次递归时间复杂度是O(1)，因此总的时间复杂度O(n).
     *      空间复杂度：O(n)。使用了HashMap存储”数据——>索引“之间的映射关系
     */
    HashMap<Integer,Integer> inorderMap = new HashMap<>();
    int preOrderIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
        if (l>r) return null; /**err：这里只限制l<=r就可以，这个条件就包含了preOrderIndex不越界的情况*/
        int curVal = preorder[preOrderIndex++];
        TreeNode root = new TreeNode(curVal);
        Integer inorderIndex = inorderMap.get(curVal);
        root.left = buildTree(preorder,inorder,l,inorderIndex-1); /*使用inorder中左边的数据构建左子树，左边范围到inorderIndex-1位置*/
        root.right = buildTree(preorder,inorder,inorderIndex+1,r); /*使用inorder中右边的数据构建右子树，右边是从inorderIndex+1开始*/
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
     *【建议】递归法使用解法 lowestCommonAncestor。
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


    /*124. 二叉树中的最大路径和
    * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
    路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。
    * */
    /**【其他的附加解释】
     一、任何一个节点有如下的2种可能————
     1️⃣ 作为路径“中间点”（此时需要判断是否更新结果）
        可以连接它的左子树路径和右子树路径；此时的路径形态：left → root → right；这时的路径和为：leftGain + root.val + rightGain。
     2️⃣ 作为“上行路径”的一部分（此时需要返回给父节点信息————以它为根的这段路程的最大路径和，此时左右子树只能二选一）
        只能选择一边（因为路径不能分叉）；返回给父节点的最大贡献是：root.val + max(leftGain, rightGain)
     因此到一个节点的时候，需要根据左右子树的情况来更新”全局的最大路径和“，同时任何一个节点需要返回给父节点以它为根的最大路径和信息。这
     两个值是不一样的，尤其是题目要求返回的是”最大路径和“信息，但是任一节点需要返回的是”以它为根左右子树选其一“的最大路径和，这种差异就
     以为着必须使用额外的方法来完成遍历
     二、这个题实际上使用的是后序遍历DFS，为什么必须用后序遍历，使用先序/中序不可以吗？
        ✅ 必须用后序遍历，因为：
        只有当我们「先拿到左右子树的最大贡献值」之后，才能计算以当前节点为“最高点”的最大路径和。换句话说：当前节点的计算依赖于左右子
     树的结果，这正是「后序遍历」的定义：先处理子树 → 再处理自己。
     */
    /**
     *【易错点】
     *      1. 的初始值必须是“”，或者更小的数。err：初始化为0是错误的，提交代码有用例报错如下————
     *      输入
     *      root =
     *      [-3]
     *      输出
     *      0
     *      预期结果
     *      -3
     *【关键要理解】为什么需要两个方法？
     *      原因就在于决策的表达式（即代表方法maxPathSum最终返回值的计算），与每个结点应该返回给父节点的信息
     * 是不一样的。不一样在哪里？
     *      到每一个节点，我们期望计算出以这个节点为根的子树的最大路径和是多少；但是对于每一个节点我们希望它
     * 返回什么？希望它返回朝着右子树下去的最大路径和 以及 朝着左子树路径下去的最大路径和 中的最大值。————很
     * 明显，这两个信息是不一样的，这种差别就注定了必须使用额外的方法。如果写在原函数的话，就无法协调了到底返
     * 回什么信息.......
     *
     *      * 1.实际上更新结果的时候是左右两边都能走的
     *      * 2. 对于每一个节点，我们希望左右子树返回什么信息？
     *      *      比如，左子树，我们希望它返回以他为根的数的最大路径和（注意和题目提到的路径是有区别的，这里的路径不能既包括左子树又包括右
     *      *  子树。只能包括左右子树中的一边！！）
     *      * 3. 1是题目需要求解的信息，2是题目中我们期望左右孩子节点返回的信息————但这两者很明显是不一样的，这种区别就造成了必须使用额外的
     *      *      方法来完成遍历二叉树，并在这个过程中更新结果。
     */
    int resMaxPathSum = Integer.MIN_VALUE; /**err：注意初始化为0是不行的，因为可能所有的节点值都小于0，因此最大路径必小于0*/
    public int maxPathSum(TreeNode root) {
        /*备注：没有下面的两个 特殊情况 的考虑也是ok的，代码没问题！*/
        if (root==null) return 0;
        if (root.left==null&&root.right==null) return root.val;
        dfsMaxPathSum(root);
        return resMaxPathSum;
    }

    private int dfsMaxPathSum(TreeNode root) {
        if (root==null) return 0;
        int lSum = Math.max(dfsMaxPathSum(root.left),0); /**err：在这里就必须将返回值和0取较大值！！宁可决策中不含小于的子树，也不能让小于的子树起副作用*/
        int rSum = Math.max(dfsMaxPathSum(root.right),0);
        int curSum = lSum+rSum+root.val;
        resMaxPathSum = Math.max(curSum,resMaxPathSum);
        return Math.max(lSum,rSum)+root.val;
    }
}
