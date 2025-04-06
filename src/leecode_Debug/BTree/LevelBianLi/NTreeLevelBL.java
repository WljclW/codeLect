//package leecode_Debug.BTree.LevelBianLi;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.List;
//
//class Node {
//    public int val;
//    public List<Node> children;
//
//    public Node() {}
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, List<Node> _children) {
//        val = _val;
//        children = _children;
//    }
//}
//
//
//
//class Solution_429 {
//    public List<List<Integer>> levelOrder(Node root) {
//        List<List<Integer>> res = new LinkedList<>();
//        if (root == null)
//            return res;
//        new
//        return res;
//    }
//}
//
//public class NTreeLevelBL_429 {
//    public static String integerArrayListToString(List<Integer> nums, int length) {
//        if (length == 0) {
//            return "[]";
//        }
//
//        String result = "";
//        for(int index = 0; index < length; index++) {
//            Integer number = nums.get(index);
//            result += Integer.toString(number) + ", ";
//        }
//        return "[" + result.substring(0, result.length() - 2) + "]";
//    }
//
//    public static String integerArrayListToString(List<Integer> nums) {
//        return integerArrayListToString(nums, nums.size());
//    }
//
//    public static String int2dListToString(List<List<Integer>> nums) {
//        StringBuilder sb = new StringBuilder("[");
//        for (List<Integer> list: nums) {
//            sb.append(integerArrayListToString(list));
//            sb.append(",");
//        }
//
//        sb.setCharAt(sb.length() - 1, ']');
//        return sb.toString();
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            int root = Integer.parseInt(line);
//
//            List<List<Integer>> ret = new Solution_429().levelOrder(root);
//
//            String out = int2dListToString(ret);
//
//            System.out.print(out);
//        }
//    }
//}