package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Solution_114 {
    public static LinkedList<TreeNode> list  = new LinkedList<TreeNode>();
    public void flatten(TreeNode root) {
//        if(root == null)
//            return ;
//        if(root.right!=null&&root.left==null)
//            return ;
//        Stack<TreeNode> stack = new Stack<TreeNode>();
//        LinkedList<TreeNode> list = new LinkedList<>();
//        stack.add(root);
//        while(!stack.isEmpty()){
//            TreeNode node = stack.pop();
//            list.add(node);
//            if(node.right!=null)
//                stack.push(node.right);
//            if(node.left!=null)
//                stack.push(node.left);
//        }
//        TreeNode res = list.poll();
//        root = res;
//        while(!list.isEmpty()){
//            TreeNode node = list.poll();
//            res.right = node;
//            res.left = null;
//            res = node;
//        }
        preOrder_(root);
        TreeNode res = list.poll();
        while(!list.isEmpty()){
            TreeNode node = list.poll();
            res.right = node;
            res.left = null;
            res = node;
        }
        return ;
    }
    public static void preOrder_(TreeNode root){
        if (root == null)
            return ;
        list.add(root);
        preOrder_(root.left);
        preOrder_(root.right);
    }
}

public class BTreeFlat_114 {
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

            new Solution_114().flatten(root);
            String out = treeNodeToString(root);

            System.out.print(out);
        }
    }
}