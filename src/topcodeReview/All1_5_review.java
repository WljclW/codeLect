package topcodeReview;

import leecode_Debug.BTree.TreeNode;
import leecode_Debug.top100.ListNode;
import zuo_course_01base.No01_sort.GetRandom;

import java.util.*;

/**
 * 2025.9.26
 */

/**
 * 470需要理解一下官方的解、283有没有直接交换的方法省略最后的遍历操作
 * err：234、179、69、146
 * 468、1324、113、堆排序、148、151、76、8、93、227
 * 72、1143写出空间优化版本
 */

/**
 * 2025.10.13......
 *      单行注释都是以前写的代码
 *      给“leecode_Debug._hot100._11stack.MinStack”补充判断相等的另外方法，使用“.intValue()”
 *   err:5、113、112、、、、、、、
 */
public class All1_5_review {
    /*215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,nums.length-k);
    }

    private int findKthLargest(int[] nums, int left, int right, int k) {
        if (left==right) return nums[left];
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap1(nums,pivotIndex,right);
        pivotIndex = partion1(nums,left,right);
        if (pivotIndex==k){
            return nums[pivotIndex];
        } else if (pivotIndex > k) {
            return findKthLargest(nums,left,pivotIndex-1,k);
        }else {
            return findKthLargest(nums,pivotIndex+1,right,k);
        }
    }

    private int partion1(int[] nums, int left, int right) {
        int cur = left;
        for (int i = left; i < right; i++) {
            if (nums[i]<=nums[right]){
                swap1(nums,cur++,i);
            }
        }
        swap1(nums,left,cur); /**与for循环块内的swap参数有区别。这里不能再让cur加加了，快排的代码中也是一样的道理。。因为partion1方法要返回随机选的数放在哪个位置了(位置指的是索引值)*/
        return cur;
    }

    private void swap1(int[] nums, int pivotIndex, int right) {
        int tmp = nums[pivotIndex];
        nums[pivotIndex] = nums[right];
        nums[right] = tmp;
    }

    /**
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     * ============================================2=========================
     */
    /*
    143重排链表
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return;
        ListNode mid = findLastMid(head);
        ListNode head2 = mid.next;
        mid.next = null;

        ListNode odd = head,even = head2;
        while (even!=null){
            ListNode oddnext = odd.next;
            ListNode evenNext = even.next;
            odd.next = evenNext;
            even.next = oddnext;

            odd = oddnext;
            even = evenNext;
        }
        /**这里for循环的写法有点不一样，不能像“奇偶链表”或者“复制链表”一样使用“odd=odd.next.next”这样的更新操作*/
//        while (even!=null){
//            ListNode oddnext = odd.next;
//            ListNode evenNext = even.next;
//            odd.next = evenNext;
//            even.next = oddnext;
//
//            odd = odd.next.next;
//            even = even;
//        }
    }

    private ListNode findLastMid(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }



    /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
    "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过
    在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
    * */
//    public List<String> restoreIpAddresses(String s) {
//
//    }

    /**
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     * =======================================3================================
     */
    /*151. 反转字符串中的单词
    给你一个字符串 s ，请你反转字符串中 单词 的顺序。
    单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
    返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
    注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。*/
//    public String reverseWords(String s) {
//
//    }


    /**chatgpt给出的代码如下，但是下面的方法还不如直接使用方法 reverseWords____，直接倒着台南佳单词，都不用反转了*/
//    public String reverseWords(String s) {
//        String trim = s.trim();
//        String s1 = reverString(trim);
//        String[] s2 = s1.split("\\s+"); // ✅ 用正则去除多余空格
//        StringBuilder sb = new StringBuilder();
//        for (String str : s2) {
//            sb.append(reverString(str)).append(" ");
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        return sb.toString();
//    }
//
//    private String reverString(String s) {
//        return new StringBuilder(s).reverse().toString();
//    }
//
//    /**
//     split("\\s+") 会按“至少一个空白字符”去拆分字符串，并自动去掉多余的空白。
//         String s1 = "  hello   world  ";
//         String[] s2 = s1.split("\\s+");
//             拆出来结果：
//                 s2[0] = "hello"
//                 s2[1] = "world"
//     如果写 split(" ")：
//            " hello world ".split(" ")会得到：["", "", "hello", "", "", "world"] 里面有空字符串。
//     */
//    /*下面的代码是自己写的，可能会出错*/
//    public String reverseWords_(String s) {
//        String trim = s.trim();
//        String s1 = reverString1(trim);
//        String[] s2 = s1.split(" ");
//        StringBuilder sb = new StringBuilder();
//        for (String str:s2){
//            sb.append(reverString1(str));
//            sb.append(" ");
//        }
//        sb.deleteCharAt(sb.length()-1);
//        return sb.toString();
//    }
//
//    private String reverString1(String s) {
//        StringBuilder sb = new StringBuilder(s);
//        return sb.reverse().toString();
//    }
    /*更简单的方法，甚至不用反转单词。直接倒着添加单词，添加到结果*/
//    public String reverseWords____(String s) {
//        String[] words = s.trim().split("\\s+");
//        StringBuilder sb = new StringBuilder();
//        for (int i = words.length - 1; i >= 0; i--) {
//            sb.append(words[i]);
//            if (i != 0) sb.append(" ");
//        }
//        return sb.toString();
//    }

//
    /**
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     *=================================================5=====================================
     */

    /*468. 验证IP地址
    给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
    有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
    一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
    1 <= xi.length <= 4
    xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
    在 xi 中允许前导零。
    例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
     */
    /**
     *什么是合法的IP地址？
     *     IPV4（三个条件缺一不可）：必须是4段；每一段不能有前导零；每一段不能超过255；
     *     IPV6：
     *【完整步骤】
     *
     */
//    public String validIPAddress(String queryIP) {
//
//    }


    //93
    List<String> resRestoreIpAddresses;
    public List<String> restoreIpAddresses(String s) {
        StringBuilder path = new StringBuilder(s);
        resRestoreIpAddresses = new LinkedList<>();
        restoreIpAddresses(path,0,0);
        return resRestoreIpAddresses;
    }

    private void restoreIpAddresses(StringBuilder path, int startIndex, int dotNum) {
        if (dotNum==3){
            if (isValid(path,startIndex,path.length()-1)){
                resRestoreIpAddresses.add(new String(path));
            }
            return;
        }

        for (int i = startIndex; i < path.length() - 1; i++) {
            if (isValid(path,startIndex,i)){
                path.insert(startIndex+1,'.');
                dotNum++;
                restoreIpAddresses(path,startIndex+2,dotNum);
                dotNum--;
                path.deleteCharAt(startIndex+1);
            }else {
                break;
            }
        }
    }

    private boolean isValid(StringBuilder path, int left, int right) {
        if (left>right) return false;
        if (path.charAt(left)=='0' && left!=right) return false;
        int res = 0;
        for (int i = left; i < right; i++) {
            int cur = path.charAt(i) - '0';
            res = res*10 + cur;
            if (res>255){
                return false;
            }
        }
        return true;
    }


}


//下面的这段话补充在”leecode_Debug._hot100._07binarytree.maxPathSum“124题注释的下面
/**
 任何一个节点有如下的2种可能————
 1️⃣ 作为路径“中间点”（此时需要判断是否更新结果）
 可以连接它的左子树路径和右子树路径；此时的路径形态：left → root → right；这时的路径和为：leftGain + root.val + rightGain。
 2️⃣ 作为“上行路径”的一部分（此时需要返回给父节点信息————以它为根的这段路程的最大路径和，此时左右子树只能二选一）
 只能选择一边（因为路径不能分叉）；返回给父节点的最大贡献是：root.val + max(leftGain, rightGain)
 因此到一个节点的时候，需要根据左右子树的情况来更新”全局的最大路径和“，同时任何一个节点需要返回给父节点以它为根的最大路径和信息。这两个值
 是不一样的，尤其是题目要求返回的是”最大路径和“信息，但是任一节点需要返回的是”以它为根左右子树选其一“的最大路径和，这种差异就以为着必须使
 用额外的方法来完成遍历
 */
/**【补充说明】
 1. 这个题实际上使用的是后序遍历DFS，为什么必须用后序遍历，使用先序/中序不可以吗？
 ✅ 必须用后序遍历，因为：
 只有当我们「先拿到左右子树的最大贡献值」之后，才能计算以当前节点为“最高点”的最大路径和。
 换句话说：当前节点的计算依赖于左右子树的结果，这正是「后序遍历」的定义：先处理子树 → 再处理自己。
 */


//大总结。关于先序遍历、后序遍历、中序遍历的思考。。大总结的代码也尽量拷贝到_07binarytree.java
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