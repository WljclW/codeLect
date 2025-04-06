package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution_234 {
    /**
     * 方法1：借助递归遍历链表，后序位置添加逻辑代码
     * */
//    ListNode left;
//    public boolean isPalindrome(ListNode head) {
//        left = head;
//        return reverse(head);
//    }
//
//    private boolean reverse(ListNode head) {
//        if (head==null)
//            return true;
//        boolean res = reverse(head.next);
//
//
//        if (res && left.val == head.val){
//            left = left.next;
//            return true;
//        }else
//            return false;
//    }

    /**
     * 方法2：双指针找中间节点，然后反转后半部分链表，接着借助两个指针分别指向两部分，遍历的同时判断即可。
     * */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast!=null)
            slow = slow.next;

        ListNode left = head;
        ListNode right = rever(slow);
        while(right!=null){
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }


    public ListNode rever(ListNode slow){
        ListNode pre = null;
        ListNode cur = slow;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}

public class HuiWenLink_234 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            boolean ret = new Solution_234().isPalindrome(head);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}