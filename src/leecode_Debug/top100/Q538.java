package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution538 {
    /**
     * 将二叉搜索树中任何一个节点的值改为  大于等于 该节点的所有值的和
     * */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        pro(root);
        return root;
    }

    private void pro(TreeNode root) {
        if(root==null){
            return ;
        }
        pro(root.right);
        sum += root.val;
        root.val = sum;
        pro(root.left);
    }


    /**
     * 如果去掉等于呢
     * */
//    int sum = 0;
//    public TreeNode convertBST(TreeNode root) {
//        pro(root);
//        return root;
//    }
//
//    private void pro(TreeNode root) {
//        if(root==null){
//            return ;
//        }
//        pro(root.right);
//        int val = root.val;
////        sum += root.val;
//        root.val = sum;             //到任何一个点先把sum的值赋给当前节点，然后再更新sum。
//        sum += val;                 //利用当前节点原来的值更新sum，而不是累加后的。因此前面需要变量val来记录初始时该点的值。
//        pro(root.left);
//    }
}

public class Q538 {
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

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            TreeNode ret = new Solution538().convertBST(root);

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }
}