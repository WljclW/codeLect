package ZZZtest;

public class Main {
    public static void main(String[] args) {
        String str = "RRRGGBB";
        int minRemovals = minRemovalsToAvoidAdjacentDuplicates(str);
        System.out.println("至少需要去除的字符数: " + minRemovals);
    }
    
    public static int minRemovalsToAvoidAdjacentDuplicates(String str) {
        int count = 0;
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            }
        }
        
        return count;
    }
}