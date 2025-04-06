package leecode_Debug.BTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution_110 {
    /**
     * 判断一棵树是不是二叉树。
     *      判断每一个节点是不是满足要求。。此时需要高度这个值
     * */
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (Math.abs(deep(root.left)-deep(root.right))>1){      //这种写法中如果这里写为<=，return true就是错的，为什么？？
            return false;
        }
        boolean left = isBalanced(root.left);
        boolean right = isBalanced(root.right);
        if (left&&right)
            return true;
        return false;
    }
    public int deep(TreeNode node){         //获取树中任意一点的高度
        if (node == null)
            return 0;
        return Math.max(deep(node.left),deep(node.right))+1;        //返回当前节点的高度，因此要加1.
    }
}

public class IsBanceBTree_110 {
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

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            boolean ret = new Solution_110().isBalanced(root);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}