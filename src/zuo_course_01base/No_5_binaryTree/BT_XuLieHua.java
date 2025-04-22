package zuo_course_01base.No_5_binaryTree;

import java.util.LinkedList;

/**
 * 二叉树的序列化和反序列化
 *
 * */
public class BT_XuLieHua {
    public static String serialByPre(Node head){
        /**
         *  根据前序遍历来进行序列化
         * */
        if (head == null)
            return "#_";
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static Node reconByPreString(String preStr){
        String[] values = preStr.split("_");
        LinkedList<String> deque = new LinkedList<>();
        for (int i=0;i!=values.length;i++){
            deque.add(values[i]);
        }
        return reconPreOrder(deque);
    }

    private static Node reconPreOrder(LinkedList<String> deque) {
        String value = deque.poll();
        if (value.equals("#"))
            return null;
        Node head = new Node(Integer.parseInt(value));
        head.left = reconPreOrder(deque);
        head.right = reconPreOrder(deque);
        return head;
    }
}
