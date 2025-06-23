package baseReview;

/**
 * @author mini-zch
 * @date 2025/6/11 15:10
 */
public class GenericsDemo06 {
    public static void main(String[] args) {
        Point<String> point = new Point<>();
        point.setVal(String.valueOf(10));
        System.out.println(point.getVal().getClass());
    }
}

class Point<T>{
    private T val;
    public T getVal(){
        return val;
    }

    public void setVal(T val){
        this.val = val;
    }
}

