package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;


class Solution_142 {
    /**
     * 方法1：就是借助hashset，每到一个节点先判断集合中有没有，如果有则表示它就是入环节点。
     *          否则一直便利，如果发现后面没有节点了，就返回null，表示没有环
     * */
//    public ListNode detectCycle(ListNode head) {
//        HashSet<ListNode> set = new HashSet<>();
//        while(head != null){
//            if (! set.contains(head)){
//                set.add(head);
//                head = head.next;
//            }else
//                return head;
//        }
//        return null;
//    }


    /**
     * 方法2：
     *
     * */
    public ListNode detectCycle(ListNode head) {
        ListNode f = head;
        ListNode s = head;
        while(f.next!=null && f.next.next!=null&&s!=f){
            f = f.next.next;
            s = s.next;
        }
        if (f.next==null || f.next.next==null){
            return null;
        }else{
            f = head;
            while(f!=s){
                f = f.next;
                s = s.next;
            }
            return f;
        }
    }
}

public class HuanLink_142 {
//    public static int[] stringToIntegerArray(String input) {
//        input = input.trim();
//        input = input.substring(1, input.length() - 1);
//        if (input.length() == 0) {
//            return new int[0];
//        }
//
//        String[] parts = input.split(",");
//        int[] output = new int[parts.length];
//        for(int index = 0; index < parts.length; index++) {
//            String part = parts[index].trim();
//            output[index] = Integer.parseInt(part);
//        }
//        return output;
//    }
//
//    public static ListNode stringToListNode(String input) {
//        // Generate array from the input
//        int[] nodeValues = stringToIntegerArray(input);
//
//        // Now convert that list into linked list
//        ListNode dummyRoot = new ListNode(0);
//        ListNode ptr = dummyRoot;
//        for(int item : nodeValues) {
//            ptr.next = new ListNode(item);
//            ptr = ptr.next;
//        }
//        return dummyRoot.next;
//    }
//
//    public static String listNodeToString(ListNode node) {
//        if (node == null) {
//            return "[]";
//        }
//
//        String result = "";
//        while (node != null) {
//            result += Integer.toString(node.val) + ", ";
//            node = node.next;
//        }
//        return "[" + result.substring(0, result.length() - 2) + "]";
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            ListNode head = stringToListNode(line);
//            line = in.readLine();
//            int pos = Integer.parseInt(line);
//
//            ListNode ret = new Solution_142().detectCycle(head, pos);
//
//            String out = listNodeToString(ret);
//
//            System.out.print(out);
//        }
//    }
}