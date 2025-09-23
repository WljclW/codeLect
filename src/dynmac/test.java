package dynmac;

/**
 * @author mini-zch
 * @date 2025/5/6 11:00
 */
public class test {
    static calcu cal = new calcuImpl();
    public static void main(String[] args) {
        System.out.println(cal.add(1, 2));
    }
}
