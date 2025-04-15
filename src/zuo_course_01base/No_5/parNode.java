package zuo_course_01base.No_5;

/**
 * 这种二叉树的节点，多了一个指向父节点的指针
 * */
public class parNode {
    int value;
    parNode left;
    parNode right;
    parNode parent;
    public parNode(int value) {
        // super();
        this.value = value;
    }

    public parNode(int value,parNode left,parNode right){
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
