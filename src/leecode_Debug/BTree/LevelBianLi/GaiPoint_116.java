//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Deque;
//import java.util.LinkedList;
//
//package leecode_Debug.BTree.LevelBianLi;
//
//import leecode_Debug.BTree.TreeNode;
//
//import javax.sql.DataSource;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.Queue;
//
//
//class Node {
//    public int val;
//    public Node left;
//    public Node right;
//    public Node next;
//
//    public Node() {}
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, Node _left, Node _right, Node _next) {
//        val = _val;
//        left = _left;
//        right = _right;
//        next = _next;
//    }
//}
//
////class Solution_116 {
/////**
//// * 下面的做法为什么是错的？？
//// * */
////    public Node connect(Node root) {
////        if (root == null)
////            return root;
////        Deque<Node> que = new LinkedList<>();
////        que.add(root);
////        while(!que.isEmpty()){
////            int size = que.size();
////            for (int i=0;i<size-1;i++){
////                Node node = que.poll();
////                Node next = que.peek();
////                node.next = next;
////                if (node.left!=null)
////                    que.add(node.left);
////                if (node.right!=null)
////                    que.add(node.right);
////            }
////            Node last = que.poll();
////            last.next = null;
////        }
////        return root;
////    }
////}
//
//class Solution_116 {
//    public Node connect(Node root) {
//        if (root == null)
//            return root;
//        Deque<Node> que = new LinkedList<>();
//        que.add(root);
//        while(!que.isEmpty()){
//            int size = que.size();
//            for (int i=1;i<size;i++){
//                Node node = que.poll();
//                if (i<size-1)
//                    node.next = que.peek();
//                if (node.left!=null)
//                    que.add(node.left);
//                if (node.right!=null)
//                    que.add(node.right);
//            }
//        }
//        return root;
//    }
//}
//
//
//
//
//public class GaiPoint_116 {
//    public static TreeNode stringToTreeNode(String input) {
//        input = input.trim();
//        input = input.substring(1, input.length() - 1);
//        if (input.length() == 0) {
//            return null;
//        }
//
//        String[] parts = input.split(",");
//        String item = parts[0];
//        TreeNode root = new TreeNode(Integer.parseInt(item));
//        Queue<TreeNode> nodeQueue = new LinkedList<>();
//        nodeQueue.add(root);
//
//        int index = 1;
//        while(!nodeQueue.isEmpty()) {
//            TreeNode node = nodeQueue.remove();
//
//            if (index == parts.length) {
//                break;
//            }
//
//            item = parts[index++];
//            item = item.trim();
//            if (!item.equals("null")) {
//                int leftNumber = Integer.parseInt(item);
//                node.left = new TreeNode(leftNumber);
//                nodeQueue.add(node.left);
//            }
//
//            if (index == parts.length) {
//                break;
//            }
//
//            item = parts[index++];
//            item = item.trim();
//            if (!item.equals("null")) {
//                int rightNumber = Integer.parseInt(item);
//                node.right = new TreeNode(rightNumber);
//                nodeQueue.add(node.right);
//            }
//        }
//        return root;
//    }
//
//    public static String treeNodeToString(TreeNode root) {
//        if (root == null) {
//            return "[]";
//        }
//
//        String output = "";
//        Queue<TreeNode> nodeQueue = new LinkedList<>();
//        nodeQueue.add(root);
//        while(!nodeQueue.isEmpty()) {
//            TreeNode node = nodeQueue.remove();
//
//            if (node == null) {
//                output += "null, ";
//                continue;
//            }
//
//            output += String.valueOf(node.val) + ", ";
//            nodeQueue.add(node.left);
//            nodeQueue.add(node.right);
//        }
//        return "[" + output.substring(0, output.length() - 2) + "]";
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            TreeNode root = stringToTreeNode(line);
//
//            TreeNode ret = new Solution_116().connect(root);
//
//            String out = treeNodeToString(ret);
//
//            System.out.print(out);
//        }
//    }
//}
//
//
//
