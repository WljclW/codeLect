package zuo_course_01base.No_5.ershua;


import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author mini-zch
 * @date 2025/4/14 15:22
 */
public class review {
    public static void main(String[] args) {
        Node head=new Node(1);
        head.left=new Node(5);
        head.left.right = new Node(9);
        head.right=new Node(2);
        head.right.left=new Node(3);
        head.right.right=new Node(4);

//        diguiOrder(head);
//        preOrder(head);
//        System.out.println("=========");
//        preOrder1(head);

//        posOrder(head);
//        System.out.println("===========");
//        posOrder1(head);

//        inOrder(head);

        wi(head);
    }

    /**
     * 打印出一棵二叉树的 递归序..
     * 一个节点会明显的到达3次，控制打印的时机可以实现先序、中序、后序
     * */
    public static void diguiOrder(Node head){
        if(head==null) return;
        System.out.println(head.value);
        diguiOrder(head.left);
        System.out.println(head.value);
        diguiOrder(head.right);
        System.out.println(head.value);
    }

    public static void preOrder(Node head){
        if(head==null) return;
        System.out.println(head.value);
        preOrder(head.left);
        preOrder(head.right);
    }

    public static void posOrder(Node head){
        if(head==null) return;
        posOrder(head.left);
        posOrder(head.right);
        System.out.println(head.value);
    }

    public static void inOrder(Node head){
        if(head==null) return;
        inOrder(head.left);
        System.out.println(head.value);
        inOrder(head.right);
    }


    public static void preOrder1(Node head){
        if(head==null) return;
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(head);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.value);
            if(head.right!=null) stack.push(head.right);
            if(head.left!=null) stack.push(head.left);
        }
    }


    public static void posOrder1(Node head){
        if(head==null) return;
        LinkedList<Node> stack1 = new LinkedList<>();
        LinkedList<Node> stack2 = new LinkedList<>();
        stack1.push(head);
        while(!stack1.isEmpty()){
            Node cur = stack1.pop();
            stack2.push(cur);
            if(cur.left!=null) stack1.push(cur.left);
            if(cur.right!=null) stack1.push(cur.right);
        }
        while(!stack2.isEmpty()){
            System.out.println(stack2.pop().value);
        }
    }

    public static void wi(Node head){
        if (head==null) return;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(head);
        int max = Integer.MIN_VALUE;
        while(!queue.isEmpty()){
            int size = queue.size();
            max = Math.max(max,size);
            ArrayList<Integer> level = new ArrayList<>();
            for(int i=0;i<size;i++){
                Node cur = queue.poll();
                level.add(cur.value);
                if(cur.left!=null) queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
            }
            level.forEach(item-> System.out.println(item));
        }
    }

}
