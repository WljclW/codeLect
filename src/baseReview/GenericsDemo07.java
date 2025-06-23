package baseReview;

/**
 * @author mini-zch
 * @date 2025/6/11 15:22
 */
public class GenericsDemo07 {
    public static void main(String[] args) {
        Notepad<String, Integer> clasz = new Notepad<>();
        clasz.setKey(String.valueOf(10));
        clasz.setValue(Integer.valueOf("31"));
        System.out.println(System.getProperty("java.class.path"));
    }
}


class Notepad<K,V>{       // 此处指定了两个泛型类型
    private K key ;     // 此变量的类型由外部决定
    private V value ;   // 此变量的类型由外部决定
    public K getKey(){
        return this.key ;
    }
    public V getValue(){
        return this.value ;
    }
    public void setKey(K key){
        this.key = key ;
    }
    public void setValue(V value){
        this.value = value ;
    }
}