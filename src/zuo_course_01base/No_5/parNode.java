package zuo_course_01base.No_5;

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
