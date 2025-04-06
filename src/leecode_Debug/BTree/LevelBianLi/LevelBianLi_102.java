package leecode_Debug.BTree.LevelBianLi;
import leecode_Debug.BTree.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class Solution_102 {
    /**
     * @description: 对二叉树进行层序遍历，需要把同一层的节点放在一个大数组中的小数组中
     * @param root:
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author: Zhou
     * @date: 2022/11/24 11:08
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();                //存放最终遍历的结果
        LinkedList<TreeNode> deque = new LinkedList<>();        //动态的存储节点
        if(root != null)
            deque.add(root);
        while(!deque.isEmpty()){
            int size= deque.size();             //先得到size，size就是当前层的节点数量
            LinkedList<Integer> list = new LinkedList<>();      //这个list用来存放每一层遍历结果。需要声明在while内部但是在for外面
            for(int i=0;i<size;i++){
                /**
                 * ① 循环内部会给deque中添加节点，因此循环前需要记录当前层有几个节点
                 * ② 如果想在同一层节点间做操作。比如：求一层节点的和、求一层节点的最大值、改指针等，都需要在for循环内部添加操作逻辑
                 * */
                TreeNode node = deque.poll();
                list.add(node.val);
                //如果出队列的节点右左孩子或右孩子，需要将它们入队列
                if (node.left != null)
                    deque.add(node.left);
                if (node.right != null)
                    deque.add(node.right);
            }
            res.add(list);              //出for循环就表示一层循环遍历完成，需要将那层的遍历结果加入到res。
        }
        return res;
    }
}

public class LevelBianLi_102 {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            List<List<Integer>> ret = new Solution_102().levelOrder(root);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }
}